package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Stack;

import org.junit.jupiter.api.Test;
import app.*;

public class ItemTest {

    @Test
    public void bagPrice() {
        var bag = new Bag();
        bag.setBasePrice(10);
        bag.setDimension(1500);
        bag.setConditionScore(0.3);
        assertEquals("", 1.5, bag.getPrice(), 0);
    }

    @Test
    public void tshirtPriceSmooth() {
        var tshirt = new Tshirt();
        tshirt.setBasePrice(15);
        tshirt.setPattern(Tshirt.TshirtPattern.Smooth);
        assertEquals("", 15, tshirt.getPrice(), 0);
    }

    @Test
    public void tshirtPriceNotSmooth() {
        var tshirt = new Tshirt();
        tshirt.setBasePrice(15);
        tshirt.setPattern(Tshirt.TshirtPattern.Stripes);
        assertEquals("", 7.5, tshirt.getPrice(), 0);
        tshirt.setPattern(Tshirt.TshirtPattern.PalmTrees);
        assertEquals("", 7.5, tshirt.getPrice(), 0);
    }

    @Test
    public void sneakerPrice() {
        var sneaker = new Sneaker();
        var pO = new Stack<Integer>();
        pO.add(1);
        sneaker.setPreviousOwners(pO);
        sneaker.setBasePrice(20);
        sneaker.setConditionScore(0.3);
        assertEquals("", 3.6666, sneaker.getPrice(), 0.01);
    }

    @Test
    public void premiumBag() {
        SystemDate.setDate(LocalDate.now());
        var malaNP = new Bag();
        malaNP.setBasePrice(10);
        malaNP.setDimension(1500);
        malaNP.setConditionScore(0.3);
        malaNP.setReleaseDate(LocalDate.of(2020, 1, 8));
        var malaP = new PremiumBag(malaNP);
        assertEquals("", 1.5, malaNP.getPrice(), 0);
        assertEquals("", 10.75, malaP.getPrice(), 0);
    }

    @Test
    public void premiumSneakerNoOwners() {
        SystemDate.setDate(LocalDate.now());
        var carrier = new Carrier("carrier", 0.25, 0.5, 0.75, 0);
        var pO = new Stack<Integer>();
        var tilhaNP = new Sneaker("null", "null", 20, carrier, 0.3, pO,
                                  42, Util.toSneakerType("LACES"), "null", LocalDate.of(2020, 1, 8), 0);
        var tilhasP = new PremiumSneaker(tilhaNP);
        //var tilhaP = new PremiumSneaker(tilhaNP);
        assertEquals("", 3.2, tilhaNP.getPrice(), 0);
        assertEquals("", 21.5, tilhasP.getPrice(), 0);
    }

    @Test
    public void premiumSneakerWithOwners() {
        SystemDate.setDate(LocalDate.now());
        var tilhaNP = new Sneaker();
        var pO = new Stack<Integer>();
        pO.add(1);
        tilhaNP.setPreviousOwners(pO);
        tilhaNP.setBasePrice(20);
        tilhaNP.setConditionScore(0.3);
        tilhaNP.setReleaseDate(LocalDate.of(2020, 1, 8));
        var tilhaP = new PremiumSneaker(tilhaNP);
        assertEquals("", 3.66, tilhaNP.getPrice(), 0.01);
        assertEquals("", 21.5, tilhaP.getPrice(), 0);
    }
}