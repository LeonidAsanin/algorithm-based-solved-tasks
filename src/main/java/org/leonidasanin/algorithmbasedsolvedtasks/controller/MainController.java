package org.leonidasanin.algorithmbasedsolvedtasks.controller;

import org.leonidasanin.algorithmbasedsolvedtasks.model.Task;
import org.leonidasanin.algorithmbasedsolvedtasks.service.TaskService;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Controller
@RequestMapping("/")
public class MainController {
    private final TaskService taskService;

    public MainController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String mainPage(@ModelAttribute(name = "error") String error,
                           @ModelAttribute(name = "input") String input,
                           @ModelAttribute(name = "result") String result,
                           @ModelAttribute Task task,
                           Model model) {
        var tasks = taskService.getAllTasks();
        if (task.getId() == 0) {
            task = taskService.getDefaultTask();
        }
        var chosenTaskId = task.getId();
        var taskDescription = task.getDescription();
        if (input == null || input.equals("")) {
            input = task.getInputExample();
        }
        error = Objects.requireNonNullElse(error, "");

        model.addAttribute("tasks", tasks);
        model.addAttribute("chosenTaskId", chosenTaskId);
        model.addAttribute("taskDescription", taskDescription);
        model.addAttribute("input", input);
        model.addAttribute("error", error);
        model.addAttribute("result", result);

        return "index";
    }

    @PostMapping
    public String updatePage(@RequestParam(name = "task") int taskId,
                             @RequestParam(name = "file", required = false) MultipartFile multipartFile,
                             RedirectAttributes redirectAttributes) {
        Task task = taskService.getDefaultTask();

        if (multipartFile.isEmpty()) {
            task = taskService.getTaskById(taskId);
        } else {
            try {
                task = taskService.getTaskFromFile(multipartFile);

                var input = taskService.getInputFromFile(multipartFile);
                redirectAttributes.addFlashAttribute("input", input);

            } catch (Exception e) {
                redirectAttributes.addFlashAttribute("error",
                                                     "Error while downloading from the file");
            }
        }

        redirectAttributes.addFlashAttribute("task", task);

        return "redirect:/";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam(name = "input") String input,
                            @RequestParam(name = "task") int taskId,
                            RedirectAttributes redirectAttributes) {
        var result = taskService.solveTaskById(taskId, input);

        redirectAttributes.addFlashAttribute("input", input);
        redirectAttributes.addFlashAttribute("result", result);

        return "redirect:/";
    }

    @PostMapping("/save")
    public String save(@RequestParam(name = "input") String input,
                       @RequestParam(name = "task") int taskId) {
        taskService.saveInputForTask(taskId, input);
        return "redirect:/";
    }

    public String download() {return "redirect:/";}

    @PostMapping("/export")
    @ResponseBody
    public ResponseEntity<Resource> exportToFile(@RequestParam(name = "input") String input,
                                                 @RequestParam(name = "task") int taskId) throws IOException {
        var task = taskService.getTaskById(taskId);
        var pathname = task.getName() + "_" + LocalDateTime.now() + ".txt";
        var file = new File(pathname);
        var printWriter = new PrintWriter(file);
        printWriter.println(taskId);
        printWriter.println(input);
        printWriter.flush();
        var fileLength = file.length();

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        file.delete();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + pathname)
                .contentLength(fileLength)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
