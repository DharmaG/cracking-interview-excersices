package com.ryabokon.datastructures.arraystrings;

import com.ecyrd.speed4j.StopWatch;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.util.HashSet;
import java.util.function.Function;

/**
 * Implement an algorithm to determine if a string has all unique characters. What
 * if you cannot use additional data structures?
 * -------------------------------------------------------------------------------
 * For a random string,
 * sometimes it's a bit cheaper to use all-to-all compare with O(n^2)
 * than to create an additional array each time and use O(n) check.
 * Creating a Set each time is always more expensive.
 * <p>
 * array: 1615 ms
 * doublepass: 1996 ms
 * set: 4124 ms
 * stream: 160 s
 * <p>
 * For a worst case scenario array check is much better
 * array: 2902 ms
 * slow: 19508 ms
 * set: 47759 ms
 */
public class UniqueCharsInString {

    /**
     * No-brainer, check each char vs. each other chars. O(n^2)
     */
    public boolean doublePassCheck(String string) {
        char[] chars = string.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            for (int j = i + 1; j < chars.length; j++) {
                if (chars[i] == chars[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Use power of set to check for unique chars
     */
    public boolean putInSetCheck(String string) {
        char[] chars = string.toCharArray();
        HashSet<Character> occurances = new HashSet<>();
        for (Character c : chars) {
            if (!occurances.add(c)) {
                return false;
            }
        }
        return true;

    }

    /**
     * Use an additional boolean array, size of allowed chars amount.
     * To "count" previous char occurrences
     * <p>
     * should be O(n)
     */
    public boolean counterCheck(String string) {

        char[] chars = string.toCharArray();

        boolean[] occurances = new boolean[26]; // A=65, a=97, z=122
        for (int i = 0; i < chars.length; i++) {
            int c = chars[i] - 97;
            if (occurances[c]) {
                return false;
            }
            occurances[c] = true;
        }

        return true;
    }

    /**
     * Stream approach
     */
    public boolean streamCheck(String string) {
        return string.length() == string.chars().distinct().count();
    }

    @Test
    public void worstCaseTest() {
        System.out.println("Worst case string");

        String string = "qwertyuiopasdfghjklzxcvbnm";
        testInLoop(this::doublePassCheck, string, "Double check");
        testInLoop(this::putInSetCheck, string, "Set check");
        testInLoop(this::counterCheck, string, "Counter check");
        testInLoop(this::streamCheck, string, "Stream check");
    }

    @Test
    public void randomStringTest() {
        System.out.println("Random string");

        String string = RandomStringUtils.random(26, "qwertyuiopasdfghjklzxcvbnm");
        testInLoop(this::doublePassCheck, string, "Double check");
        testInLoop(this::putInSetCheck, string, "Set check");
        testInLoop(this::counterCheck, string, "Counter check");
        testInLoop(this::streamCheck, string, "Stream check");
    }

    public void testInLoop(Function<String, Boolean> tester, String string, String id) {
        StopWatch sw = new StopWatch();
        for (int i = 0; i < 100000000; i++) {
            tester.apply(string);
        }
        sw.stop(id, 100000000);
        System.out.println(sw);
    }


}
