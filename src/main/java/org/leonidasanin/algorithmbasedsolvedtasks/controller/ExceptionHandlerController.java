package org.leonidasanin.algorithmbasedsolvedtasks.controller;

import org.leonidasanin.algorithmbasedsolvedtasks.exception.TaskException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(TaskException.class)
    public String handleIOException(TaskException exception, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("task", exception.getPreviousTask());
        redirectAttributes.addFlashAttribute("error", exception.getMessage());
        return "redirect:/";
    }
}
