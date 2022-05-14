package org.leonidasanin.algorithmbasedsolvedtasks.model;

import org.leonidasanin.algorithmbasedsolvedtasks.exception.TaskException;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class OptimalSemiMagicSquare3By3Task extends Task {
    {
        setId(1);
        setName("Optimal (Semi)Magic Square 3 By 3");
        setDescription("""
                Given matrix 3х3, turn it into a (semi)magic square at the lowest cost. <br><br>

                The cost is calculated, as the difference between the original digit in the matrix and the final one.
                <br><br>

                For example:<br>
                1 2 3 <br>
                4 5 6 <br>
                7 8 9 <br><br>

                Turns into <br>
                4 3 8 <br>
                2 7 6 <br>
                9 5 1 <br><br>

                at a cost equal to 26. <br><br>
                
                Enter the input as a sequence of x11, x12, x13, x21, ... , x32, x33""");
        setInputExample("1 2 3 4 5 6 7 8 9");
    }

    @Override
    public String solve(String input) throws TaskException {
        super.solve(input); // check of the input for being correct

        //TODO: implement solve() method of the OptimalSemiMagicSquare3By3Task class
        return "result2 with input " + input;
    }

    @Override
    public boolean isInputCorrect(String input) {
        var inputDigitStrings = input.split(" ");
        if (inputDigitStrings.length != 9) return false;

        var digits = new HashSet<Integer>();
        try {
            for (int i = 0; i < 9; i++) {
                var digit = Integer.parseInt(inputDigitStrings[i]);
                if (digit < 1 || digit > 9) return false;
                digits.add(digit);
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return digits.size() == 9;
    }
}
