package org.leonidasanin.algorithmbasedsolvedtasks.controller;

import org.leonidasanin.algorithmbasedsolvedtasks.exception.DownloadFromFileException;
import org.leonidasanin.algorithmbasedsolvedtasks.exception.NoTaskWithGivenIdException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(DownloadFromFileException.class)
    public String handleIOException(DownloadFromFileException exception, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("task", exception.getPreviousTask());
        redirectAttributes.addFlashAttribute("error", exception.getMessage());
        return "redirect:/";
    }

    @ExceptionHandler(NoTaskWithGivenIdException.class)
    public String handleNoSuchElementException(NoTaskWithGivenIdException exception, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("task", exception.getPreviousTask());
        redirectAttributes.addFlashAttribute("error", exception.getMessage());
        return "redirect:/";
    }
}
