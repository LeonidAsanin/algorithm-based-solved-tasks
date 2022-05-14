package org.leonidasanin.algorithmbasedsolvedtasks.controller;

import org.leonidasanin.algorithmbasedsolvedtasks.exception.TaskException;
import org.leonidasanin.algorithmbasedsolvedtasks.model.Task;
import org.leonidasanin.algorithmbasedsolvedtasks.service.FileHandlerService;
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
    private final FileHandlerService fileHandlerService;

    public MainController(TaskService taskService, FileHandlerService fileHandlerService) {
        this.taskService = taskService;
        this.fileHandlerService = fileHandlerService;
    }

    @GetMapping
    public String mainPage(@ModelAttribute(name = "error") String error,
                           @ModelAttribute(name = "input") String input,
                           @ModelAttribute(name = "result") String result,
                           @ModelAttribute Task task,
                           Model model) {
        var tasks = taskService.getAllTasks();
        if (task == null || task.getId() == 0) {
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

        var downloadedInputs = taskService.getInputsByTaskId(chosenTaskId);
        model.addAttribute("downloadedInputs", downloadedInputs);

        return "index";
    }

    @PostMapping
    public String updateTaskAndHandleTaskCalculationFromFile(@RequestParam(name = "task") int taskId,
                                     @RequestParam(name = "file", required = false) MultipartFile multipartFile,
                                     RedirectAttributes redirectAttributes) throws Exception {
        Task task;

        if (multipartFile.isEmpty()) {
            task = taskService.getTaskById(taskId);
        } else {
            task = fileHandlerService.getTaskFromFileElseById(multipartFile, taskId);
            var input = fileHandlerService.getInputFromFile(multipartFile);
            var result = task.solve(input);
            redirectAttributes.addFlashAttribute("input", input);
            redirectAttributes.addFlashAttribute("result", result);
        }

        redirectAttributes.addFlashAttribute("task", task);

        return "redirect:/";
    }

    @PostMapping("/calculate")
    public String calculate(@RequestParam(name = "input") String input,
                            @RequestParam(name = "task") int taskId,
                            RedirectAttributes redirectAttributes) throws TaskException {
        var task = taskService.getTaskById(taskId);
        var result = task.solve(input);
        redirectAttributes.addFlashAttribute("task", task);
        redirectAttributes.addFlashAttribute("input", input);
        redirectAttributes.addFlashAttribute("result", result);

        return "redirect:/";
    }

    @PostMapping("/save")
    public String save(@RequestParam(name = "input") String input,
                       @RequestParam(name = "task") int taskId,
                       RedirectAttributes redirectAttributes) throws TaskException {
        taskService.saveInputForTask(taskId, input);
        redirectAttributes.addFlashAttribute("task", taskService.getTaskById(taskId));
        return "redirect:/";
    }

    @PostMapping("/download/{inputId}")
    public String downloadAndCalculate(@PathVariable(name = "inputId") String inputId,
                           @RequestParam(name = "task") int taskId,
                           RedirectAttributes redirectAttributes) throws TaskException {
        var task = taskService.getTaskById(taskId);
        var input = taskService.getInputById(inputId);
        var result = task.solve(input);
        redirectAttributes.addFlashAttribute("task", task);
        redirectAttributes.addFlashAttribute("input", input);
        redirectAttributes.addFlashAttribute("result", result);
        return "redirect:/";
    }

    @PostMapping("/export")
    @ResponseBody
    public ResponseEntity<Resource> exportToFile(@RequestParam(name = "input") String input,
                                                 @RequestParam(name = "task") int taskId) throws IOException {
        var task = taskService.getTaskById(taskId);
        var filename = task.getName() + "_" + LocalDateTime.now() + ".txt";
        var file = new File(filename);
        var printWriter = new PrintWriter(file);
        printWriter.println(task.getName());
        printWriter.println(input);
        printWriter.flush();
        var fileLength = file.length();

        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));

        file.delete();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename)
                .contentLength(fileLength)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(resource);
    }
}
