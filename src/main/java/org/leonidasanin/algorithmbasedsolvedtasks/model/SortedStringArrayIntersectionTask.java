package org.leonidasanin.algorithmbasedsolvedtasks.model;

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
                
                Beware: r must be without duplicates.""");
        setInputExample("arp, live, strong; lively, alive, harp, sharp, armstrong");
    }

    @Override
    public String solve(String input) {
        //TODO: implement solve() method of the SortedStringArrayIntersectionTask class
        return "result1 with input " + input;
    }
}
