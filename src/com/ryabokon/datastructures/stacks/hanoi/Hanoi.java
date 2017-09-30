package com.ryabokon.datastructures.stacks.hanoi;

import org.junit.Assert;
import org.junit.Test;

public class Hanoi {

    Tower towerA = new Tower();
    Tower towerB = new Tower();
    Tower towerC = new Tower();


    @Test
    public void solution() {
        towerA.push("C");
        towerA.push("B");
        towerA.push("A");

        move(3, towerA, towerC, towerB);

        Assert.assertEquals("A",towerC.pop());
        Assert.assertEquals("B",towerC.pop());
        Assert.assertEquals("C",towerC.pop());
    }

    private void move(int discs, Tower source, Tower destination, Tower temp) {
        if (discs > 1) {
            move(discs - 1, source, temp, destination);
            move(1, source, destination, temp);
            move(discs - 1, temp, destination, source);
        } else {
            String item = source.pop();
            destination.push(item);
        }
    }
}
