package org.leonidasanin.algorithmbasedsolvedtasks.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.leonidasanin.algorithmbasedsolvedtasks.exception.TaskException;
import org.leonidasanin.algorithmbasedsolvedtasks.model.SortedStringArrayIntersectionTask;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ExceptionHandlerControllerTests {

    @Mock
    RedirectAttributes redirectAttributes;

    @Test
    void handleTaskException() {
        //given
        var previousTask = new SortedStringArrayIntersectionTask();
        var message = "message";
        var exception = new TaskException(message, previousTask);
        var exceptionHandler = new ExceptionHandlerController();

        //when
        var result = exceptionHandler.handleTaskException(exception, redirectAttributes);

        //then
        assertEquals("redirect:/", result);
        Mockito.verify(redirectAttributes).addFlashAttribute("task", previousTask);
        Mockito.verify(redirectAttributes).addFlashAttribute("error", message);
    }

    @Test
    void handleIOException() {
        //given
        var message = "message";
        var exception = new IOException(message);
        var exceptionHandler = new ExceptionHandlerController();

        //when
        var result = exceptionHandler.handleIOException(exception, redirectAttributes);

        //then
        assertEquals("redirect:/", result);
        Mockito.verify(redirectAttributes).addFlashAttribute("error", message);
    }
}