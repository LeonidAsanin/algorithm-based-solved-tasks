package org.leonidasanin.algorithmbasedsolvedtasks.model;

import org.leonidasanin.algorithmbasedsolvedtasks.exception.TaskException;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class OptimalSemiMagicSquare3By3Task extends Task {
    private static final List<List<Integer>> SEMI_MAGIC_SQUARES = new ArrayList<>();

    static {
        LinkedList<Integer> digits = new LinkedList<>();
        computeSemiMagicSquares(digits);
    }

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
                2 4 9 <br>
                7 3 5 <br>
                6 8 1 <br><br>

                at a cost equal to 24. <br><br>
                
                Enter the input as a sequence of x<sub>11</sub>, x<sub>12</sub>, x<sub>13</sub>, x<sub>21</sub>, ... ,\s
                x<sub>32</sub>, x<sub>33</sub>""");
        setInputExample("1 2 3 4 5 6 7 8 9");
    }

    private static void computeSemiMagicSquares(LinkedList<Integer> digits) {
        if (digits.size() == 9
                && digits.get(0) + digits.get(1) + digits.get(2) == 15
                && digits.get(3) + digits.get(4) + digits.get(5) == 15
                && digits.get(6) + digits.get(7) + digits.get(8) == 15
                && digits.get(0) + digits.get(3) + digits.get(6) == 15
                && digits.get(1) + digits.get(4) + digits.get(7) == 15
                && digits.get(2) + digits.get(5) + digits.get(8) == 15
        ) {
            SEMI_MAGIC_SQUARES.add((List<Integer>) digits.clone());
            return;
        }
        for (int i = 1; i < 10; i++) {
            if (!digits.contains(i)) {
                digits.addLast(i);
                computeSemiMagicSquares(digits);
                digits.removeLast();
            }
        }
    }

    @Override
    public String solve(String input) throws TaskException {
        super.solve(input); // check of the input for being correct

        var inputDigitStrings = input.split(" ");
        var initialSquare = Arrays.stream(inputDigitStrings)
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<Integer> optimalSemiMagicSquare = List.of();
        var minCost = 100;

        for (List<Integer> magicSquare : SEMI_MAGIC_SQUARES) {
            var cost = 0;
            for (int j = 0; j < initialSquare.size(); j++) {
                cost += Math.abs(initialSquare.get(j) - magicSquare.get(j));
            }
            if (cost < minCost) {
                minCost = cost;
                optimalSemiMagicSquare = magicSquare;
            }
        }

        return optimalSemiMagicSquare.get(0) + " " + optimalSemiMagicSquare.get(1) + " "
                                                                               + optimalSemiMagicSquare.get(2) + "<br>"
                + optimalSemiMagicSquare.get(3) + " " + optimalSemiMagicSquare.get(4) + " "
                                                                               + optimalSemiMagicSquare.get(5) + "<br>"
                + optimalSemiMagicSquare.get(6) + " " + optimalSemiMagicSquare.get(7) + " "
                                                                               + optimalSemiMagicSquare.get(8) + "<br>"
                + "cost = " + minCost;
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
