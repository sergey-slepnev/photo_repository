import java.util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.Comparator.naturalOrder;
import static java.util.stream.Collectors.toList;

public class Example {
    public static void main(String[] args) {

        String[] array = {"eat", "tea", "tan", "ate", "nat", "bat"};
        TreeSet<String> result = new TreeSet<>();
        var b = result.addAll(Arrays.asList(array));


    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                int cf = map.get(ch);
                int nf = cf + 1;
                map.put(ch, nf);
            } else {
                map.put(ch, 1);
            }
        }
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (map.containsKey(ch) && map.get(ch) != 0) {
                int cf = map.get(ch);
                int nf = cf - 1;
                map.put(ch, nf);
            } else {
                return false;
            }
        }
        return true;
    }

    public class GroupAnagram {
        public List<List<String>> groupAnagrams(String[] items) {
            Map<String, List<String>> map = new LinkedHashMap<>();
            for (String currentItem : items) {
                char[] currentCharArray = currentItem.toCharArray();
                Arrays.sort(currentCharArray);
                String mapKey = new String(currentCharArray);
                List<String> mapValue = map.computeIfAbsent(mapKey, k -> new ArrayList<>());
                mapValue.add(currentItem);
            }

            return map.values().stream()
                    .peek(list -> list.sort(naturalOrder()))
                    .collect(toList());
        }
    }
}

/**
 * Find All Anagrams in a String
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
