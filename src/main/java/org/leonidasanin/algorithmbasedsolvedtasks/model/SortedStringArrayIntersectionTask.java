package org.leonidasanin.algorithmbasedsolvedtasks.model;

import org.leonidasanin.algorithmbasedsolvedtasks.exception.TaskException;
import org.springframework.stereotype.Component;

@Component
public class SortedStringArrayIntersectionTask extends Task {
    {
        setId(2);
        setName("Sorted String Array Intersection");
        setDescription("""
                Given two arrays of strings a1 and a2 return a sorted array r in lexicographical order of the\s
                strings of a1 which are substrings of strings of a2. <br><br>

                <i>Example 1:</i> <br>
                a1 = ["arp", "live", "strong"] <br>
                a2 = ["lively", "alive", "harp", "sharp", "armstrong"] <br>
                returns ["arp", "live", "strong"] <br><br>
                
                <i>Example 2:</i> <br>
                a1 = ["tarp", "mice", "bull"] <br>
                a2 = ["lively", "alive", "harp", "sharp", "armstrong"] <br>
                returns [] <br><br>
                
                Beware: r must be without duplicates.<br><br>
                
                Enter the input as a sequence of a11, a12, ... , a1n; a21, a22, ... , a2n""");
        setInputExample("arp, live, strong; lively, alive, harp, sharp, armstrong");
    }

    @Override
    public String solve(String input) throws TaskException {
        super.solve(input); // check of the input for being correct

        //TODO: implement solve() method of the SortedStringArrayIntersectionTask class
        return "result1 with input " + input;
    }

    @Override
    public boolean isInputCorrect(String input) {
        var arrays = input.split(";");
        return arrays.length != 0 && arrays.length <= 2;
    }
}
