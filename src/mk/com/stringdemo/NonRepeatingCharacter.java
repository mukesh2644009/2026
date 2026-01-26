package mk.com.stringdemo;

import java.util.LinkedHashMap;
import java.util.Map;

public class NonRepeatingCharacter 
{

    public static Character firstNonRepeatingChar(String str) {
        Map<Character, Integer> countMap = new LinkedHashMap<>();

        // Count character occurrences
        for (char c : str.toCharArray()) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        // Find first character with count 1
        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 1) {
                return entry.getKey();
            }
        }

        return null; // No non-repeating character
    }

    public static void main(String[] args) {
        System.out.println(firstNonRepeatingChar("swiss"));   // w
        System.out.println(firstNonRepeatingChar("aabbcc")); // null
    }
		
}
