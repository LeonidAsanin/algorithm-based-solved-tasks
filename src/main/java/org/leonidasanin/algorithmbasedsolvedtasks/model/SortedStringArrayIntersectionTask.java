package org.leonidasanin.algorithmbasedsolvedtasks.model;

import org.leonidasanin.algorithmbasedsolvedtasks.exception.TaskException;
import org.springframework.stereotype.Component;

import java.util.TreeSet;

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
                
                Enter the input as a sequence of a11, a12, ... , a1n; a21, a22, ... , a2n <br>
                P.S. " symbol is ignored""");
        setInputExample("arp, live, strong; lively, alive, harp, sharp, armstrong");
    }

    @Override
    public String solve(String input) throws TaskException {
        super.solve(input); // check of the input for being correct

        var arrays = input.split(";");
        if (arrays.length == 1) return "";

        var a1 = arrays[0].split(",");
        var a2 = arrays[1].split(",");
        for (int i = 0; i < a1.length; i++) {
            a1[i] = a1[i].replace("\"", "");
            a1[i] = a1[i].trim();
        }
        for (int i = 0; i < a2.length; i++) {
            a2[i] = a2[i].replace("\"", "");
            a2[i] = a2[i].trim();
        }
        var resultSet = new TreeSet<String>();

        for (var a1Element : a1) {
            for (var a2Element : a2) {
                if (a2Element.contains(a1Element)) resultSet.add(a1Element);
            }
        }

        return resultSet.stream()
                        .reduce((e1, e2) -> e1 + ", " + e2)
                        .orElse("");
    }

    @Override
    public boolean isInputCorrect(String input) {
        var arrays = input.split(";");
        return arrays.length != 0 && arrays.length <= 2;
    }
}
