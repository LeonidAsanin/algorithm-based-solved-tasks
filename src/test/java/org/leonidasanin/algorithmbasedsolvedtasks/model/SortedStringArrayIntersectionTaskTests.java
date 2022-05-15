package org.leonidasanin.algorithmbasedsolvedtasks.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.leonidasanin.algorithmbasedsolvedtasks.exception.TaskException;

import static org.junit.jupiter.api.Assertions.*;

class SortedStringArrayIntersectionTaskTests {

    Task task = new SortedStringArrayIntersectionTask();

    @Test
    void solve() throws Exception {
        //given
        var correctInput1 = "\"arp\", \"live\", \"strong\"; \"lively\", \"alive\", \"harp\", \"sharp\", \"armstrong\"";
        var correctInput2 = "\"tarp\", \"mice\", \"bull\"; \"lively\", \"alive\", \"harp\", \"sharp\", \"armstrong\"";
        var incorrectInput = "f;o;o";

        //when
        var result1 = task.solve(correctInput1);
        var result2 = task.solve(correctInput2);
        Executable throwing = () -> task.solve(incorrectInput);

        //then
        assertAll(
                () -> assertThrows(TaskException.class, throwing),
                () -> assertEquals("arp, live, strong", result1),
                () -> assertEquals("", result2)
        );
    }

    @Test
    void isInputCorrect() {
        //given
        var correctInput1 = "asd, 1232; 13d, asd123";
        var correctInput2 = "  \"e sf/22\"  ;  fve ef/ , se   ";
        var correctInput3 = "  e sf/22  ;   se   ";
        var correctInput4 = "  e s, f/22  ;   se   ";
        var correctInput5 = "  e s, f/22";
        var incorrectInput1 = "asd; asd; asd";
        var incorrectInput2 = "k;l;  ";

        //when
        var result1 = task.isInputCorrect(correctInput1);
        var result2 = task.isInputCorrect(correctInput2);
        var result3 = task.isInputCorrect(correctInput3);
        var result4 = task.isInputCorrect(correctInput4);
        var result5 = task.isInputCorrect(correctInput5);
        var result6 = task.isInputCorrect(incorrectInput1);
        var result7 = task.isInputCorrect(incorrectInput2);

        //then
        assertAll(
                () -> assertTrue(result1),
                () -> assertTrue(result2),
                () -> assertTrue(result3),
                () -> assertTrue(result4),
                () -> assertTrue(result5),
                () -> assertFalse(result6),
                () -> assertFalse(result7)
        );
    }
}