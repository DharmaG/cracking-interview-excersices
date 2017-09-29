package com.ryabokon.datastructures.arraystrings;

import org.junit.Assert;
import org.junit.Test;

public class RotateString {

    public String rotateFromBothSides(String string) {
        char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length / 2; i++) {
            char tmp = chars[i];
            chars[i] = chars[chars.length - 1 - i];
            chars[chars.length - 1 - i] = tmp;
        }
        return String.valueOf(chars);
    }


    @Test
    public void rotateStringTest() {
        String string = "abcdefgh";

        Assert.assertEquals("hgfedcba", rotateFromBothSides(string));
    }

}
