package org.leonidasanin.algorithmbasedsolvedtasks.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.leonidasanin.algorithmbasedsolvedtasks.exception.TaskException;

import static org.junit.jupiter.api.Assertions.*;

class OptimalSemiMagicSquare3By3TaskTests {

    Task task = new OptimalSemiMagicSquare3By3Task();

    @Test
    void solve() throws Exception {
        //given
        var correctInput1 = "1 2 3 4 5 6 7 8 9";
        var correctInput2 = "1 2 3 6 5 4 7 8 9";
        var incorrectInput = "foo";

        //when
        var result1 = task.solve(correctInput1);
        var result2 = task.solve(correctInput2);
        Executable throwing = () -> task.solve(incorrectInput);

        //then
        assertAll(
                () -> assertThrows(TaskException.class, throwing),
                () -> assertEquals("2 4 9<br>7 3 5<br>6 8 1<br>cost = 24", result1),
                () -> assertEquals("2 9 4<br>7 5 3<br>6 1 8<br>cost = 20", result2)
        );
    }

    @Test
    void isInputCorrect() {
        //given
        var correctInput1 = "1 2 3 4 5 6 7 8 9";
        var correctInput2 = " 1   2  3  4 5 6 7      8 9  ";
        var incorrectInput1 = "1 1 1 2 2 2 3 3 3";
        var incorrectInput2 = "12 34 56 78 9";
        var incorrectInput3 = "1 2 3 4 5 6 7 8 9 1";
        var incorrectInput4 = "1 2 3 4 5 6 7 8 9 10";
        var incorrectInput5 = "a s d f g h j k l";

        //when
        var result1 = task.isInputCorrect(correctInput1);
        var result2 = task.isInputCorrect(correctInput2);
        var result3 = task.isInputCorrect(incorrectInput1);
        var result4 = task.isInputCorrect(incorrectInput2);
        var result5 = task.isInputCorrect(incorrectInput3);
        var result6 = task.isInputCorrect(incorrectInput4);
        var result7 = task.isInputCorrect(incorrectInput5);

        //then
        assertAll(
                () -> assertTrue(result1),
                () -> assertTrue(result2),
                () -> assertFalse(result3),
                () -> assertFalse(result4),
                () -> assertFalse(result5),
                () -> assertFalse(result6),
                () -> assertFalse(result7)
        );
    }
}