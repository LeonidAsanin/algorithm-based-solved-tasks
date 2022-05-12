package org.leonidasanin.algorithmbasedsolvedtasks.controller;

import org.leonidasanin.algorithmbasedsolvedtasks.model.Task;
import org.leonidasanin.algorithmbasedsolvedtasks.service.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequestMapping("/")
public class MainController {
    private final TaskService taskService;

    public MainController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String mainPage(@RequestParam(name = "error", required = false) Boolean error,
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
        if (input.equals("")) {
            input = task.getInputExample();
        }
        error = Objects.requireNonNullElse(error, Boolean.FALSE);

        model.addAttribute("tasks", tasks);
        model.addAttribute("chosenTaskId", chosenTaskId);
        model.addAttribute("taskDescription", taskDescription);
        model.addAttribute("input", input);
        model.addAttribute("error", error);
        model.addAttribute("result", result);

        return "index";
    }

    @PostMapping
    public String updatePage(@RequestParam(name = "task") int taskId, RedirectAttributes redirectAttributes) {
        var task = taskService.getTaskById(taskId);

        redirectAttributes.addFlashAttribute(task);

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
}
