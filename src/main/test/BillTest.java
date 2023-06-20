package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Stack;

import org.junit.jupiter.api.Test;
import app.*;

public class BillTest {
    @Test
    public void smallBillBought() {
        var order = new Order();
        var hm = new HashMap<Integer, Item>();
        var t1 = new Carrier();
        t1.setTaxSmall(.25);
        Bag bag = new Bag("mala", "null", 10, t1, 0.5,
                null, 1500, "null", null, 0);

        var u1 = new User("test", "test", "t", 1, "test");
        order.addItem(bag, u1);

        var bill = new Bill(null, hm, 0, order);
        bill.setBought();

        bill.addItem(bag, 1);
        bill.calculateTotalCostItems();

        assertEquals("", 3.8, bill.getportsTax(), 0);
        assertEquals("", 3.5, bill.gettotalCost(), 0.02);
        assertEquals("", 7.3, bill.getAmount(), 0);
    }

    @Test
    public void mediumBillSameCarriersBought() {
        var hm = new HashMap<Integer, Item>();
        var order = new Order();
        var t1 = new Carrier();

        Bag bag = new Bag(null, null, 10, t1, 0.5,
                null, 1500, "null", null, 0);

        var camisa = new Tshirt("null", "null", 30, t1, 0.7,
                null, Util.toTshirtSize("M"), Util.toTshirtPattern("Smooth"), 0);

        var u1 = new User("test", "test", "t", 1, "test");
        t1.setTaxSmall(.25);
        t1.setTaxMedium(.5);
        order.addItem(bag, u1);
        order.addItem(camisa, u1);

        var bill = new Bill(null, hm, 0, order);
        bill.setBought();

        bill.addItem(bag, 2);
        bill.addItem(camisa, 2);
        bill.calculateTotalCostItems();

        assertEquals("", 25.2, bill.getportsTax(), 0);
        assertEquals("", 33.5, bill.gettotalCost(), 0.02);
        assertEquals("", 58.7, bill.getAmount(), 0);

        bill.removeItem(camisa, 2);

        assertEquals("", 3.8, bill.getportsTax(), 0.02);
        assertEquals("", 3.5, bill.gettotalCost(), 0.02);
        assertEquals("", 7.3, bill.getAmount(), 0.02);
    }

    @Test
    public void mediumBillDiffCarriersBought() {
        var hm = new HashMap<Integer, Item>();
        var order = new Order();
        var t1 = new Carrier();
        var t2 = new Carrier();
        t1.setName("tp1");
        t2.setName("tp2");
        Bag bag = new Bag(null, null, 10, t1, 0.5,
                null, 1500, "null", null, 0);

        var camisa = new Tshirt("null", "null", 30, t2, 0.7,
                null, Util.toTshirtSize("M"), Util.toTshirtPattern("Smooth"), 0);

        var u1 = new User("test", "test", "t", 1, "test");
        t1.setTaxSmall(.25);
        t2.setTaxSmall(.5);
        order.addItem(bag, u1);
        order.addItem(camisa, u1);
        var bill = new Bill(null, hm, 0, order);
        bill.setBought();

        bill.addItem(bag, 1);
        bill.addItem(camisa, 1);
        bill.calculateTotalCostItems();
       
        assertEquals("", 33.5, bill.gettotalCost(), 0.02);
        assertEquals("", 56.2, bill.getAmount(), 0.02);
    }

    @Test
    public void BigBillBought() {
        SystemDate.setDate(LocalDate.now());
        var hm = new HashMap<Integer, Item>();
        var order = new Order();
        var t1 = new Carrier();
        var pO = new Stack<Integer>();
        pO.add(1);

        var bag = new Bag(null, null, 10, t1, 0.5, null,
                1500, null, LocalDate.of(2021, 1, 8), 0);

        var sneak = new Sneaker(null, null, 20, t1, 1, pO,
                41, Util.toSneakerType("LACES"), null, LocalDate.of(2005, 1, 8), 0);

        var camisa = new Tshirt(null, null, 30, t1, 0, null,
                null, Util.toTshirtPattern("Stripes"), 0);

        var Pbag = new PremiumBag(null, null, 10, t1, .5, null,
                1500, null, LocalDate.of(2021, 1, 8), 0);

        var Psneak = new PremiumSneaker(null, null, 20, t1, 1, pO, 41,
                Util.toSneakerType("LACES"), null, LocalDate.of(2005, 1, 8), 0);

        var ts = new Tshirt(null, null, 20, t1, 1, null,
                null, Util.toTshirtPattern("PalmTrees"), 0);

        var u1 = new User("test", "test", "t", 1, "test");
        t1.setTaxSmall(.25);
        t1.setTaxMedium(.5);
        t1.setTaxBig(.75);

        order.addItem(bag, u1);
        order.addItem(camisa, u1);
        order.addItem(camisa, u1);

        var bill = new Bill(null, hm, 0, order);
        bill.setBought();

        bill.addItem(bag, 6);
        bill.addItem(camisa, 6);
        bill.addItem(sneak, 6);
        bill.addItem(Pbag, 6);
        bill.addItem(Psneak, 6);
        bill.addItem(ts, 6);
        bill.calculateTotalCostItems();

        assertEquals("", 3.5, bag.getPrice(), 0);
        assertEquals("", 15, camisa.getPrice(), 0);
        assertEquals("", 20, sneak.getPrice(), 0);
        assertEquals("", 10.5, Pbag.getPrice(), 0);
        assertEquals("", 29, Psneak.getPrice(), 0);
        assertEquals("", 20, ts.getPrice(), 0);

        assertEquals("", 96.8, bill.getportsTax(), 0.1);
        assertEquals("", 98, bill.gettotalCost(), 0.01);
        assertEquals("", 194.8, bill.getAmount(), 0);

        bill.removeItem(camisa, 6);

        assertEquals("", 50.4, bill.getportsTax(), 0.02);
        assertEquals("", 83, bill.gettotalCost(), 0.02);
        assertEquals("", 133.4, bill.getAmount(), 0.02);

        bill.removeItem(Psneak, 5);
        bill.removeItem(Pbag, 4);

        assertEquals("", 31.5, bill.getportsTax(), 0.02);
        assertEquals("", 43.5, bill.gettotalCost(), 0.02);
        assertEquals("", 75, bill.getAmount(), 0.02);
    }

    @Test
    public void getAmountSmallSold() {
        var order = new Order();
        var hm = new HashMap<Integer, Item>();
        var t1 = new Carrier();
        t1.setTaxSmall(.25);
        Bag bag = new Bag("mala", "null", 10, t1, .5,
                null, 1500, "null", null, 0);

        var u1 = new User("test", "test", "t", 1, "test");
        order.addItem(bag, u1);

        var bill = new Bill(null, hm, 0, order);
        bill.setSold();

        bill.addItem(bag, 1);
        bill.calculateTotalCostItems();

        assertEquals("", 0, bill.getportsTax(), 0);
        assertEquals("", 3.5, bill.gettotalCost(), 0.02);
        assertEquals("", 3.458, bill.getAmount(), 0.001);
    }

    @Test
    public void getAmountMediumSold() {
        var hm = new HashMap<Integer, Item>();
        var order = new Order();
        var t1 = new Carrier();

        Bag bag = new Bag(null, null, 10, t1, 0.5,
                null, 1500, "null", null, 0);

        var camisa = new Tshirt("null", "null", 30, t1, 0.7,
                null, Util.toTshirtSize("M"), Util.toTshirtPattern("Smooth"), 0);

        var u1 = new User("test", "test", "t", 1, "test");
        t1.setTaxSmall(.25);
        t1.setTaxMedium(.5);
        order.addItem(bag, u1);
        order.addItem(camisa, u1);

        var bill = new Bill(null, hm, 0, order);
        bill.setSold();

        bill.addItem(bag, 2);
        bill.addItem(camisa, 2);
        bill.calculateTotalCostItems();

        assertEquals("", 0, bill.getportsTax(), 0);
        assertEquals("", 33.5, bill.gettotalCost(), 0.02);
        assertEquals("", 33.098, bill.getAmount(), 0.001);

        bill.removeItem(camisa, 2);

        assertEquals("", 0, bill.getportsTax(), 0.02);
        assertEquals("", 3.5, bill.gettotalCost(), 0.02);
        assertEquals("", 3.458, bill.getAmount(), 0.001);
    }

    @Test
    public void getAmountBigSold() {
        SystemDate.setDate(LocalDate.now());
        var hm = new HashMap<Integer, Item>();
        var order = new Order();
        var t1 = new Carrier();
        var pO = new Stack<Integer>();
        pO.add(1);

        var bag = new Bag(null, null, 10, t1, 0.5, null,
                1500, null, LocalDate.of(2021, 1, 8), 0);

        var sneak = new Sneaker(null, null, 20, t1, 1, pO,
                41, Util.toSneakerType("LACES"), null, LocalDate.of(2005, 1, 8), 0);

        var camisa = new Tshirt(null, null, 30, t1, 0, null,
                null, Util.toTshirtPattern("Stripes"), 0);

        var Pbag = new PremiumBag(null, null, 10, t1, 0.5, null,
                1500, null, LocalDate.of(2021, 1, 8), 0);

        var Psneak = new PremiumSneaker(null, null, 20, t1, 1, pO, 41,
                Util.toSneakerType("LACES"), null, LocalDate.of(2005, 1, 8), 0);

        var ts = new Tshirt(null, null, 20, t1, 1, null,
                null, Util.toTshirtPattern("PalmTrees"), 0);

        var u1 = new User("test", "test", "t", 1, "test");
        t1.setTaxSmall(.25);
        t1.setTaxMedium(.5);
        t1.setTaxBig(.75);

        order.addItem(bag, u1);
        order.addItem(camisa, u1);
        order.addItem(camisa, u1);

        var bill = new Bill(null, hm, 0, order);
        bill.setSold();

        bill.addItem(bag, 6);
        bill.addItem(camisa, 6);
        bill.addItem(sneak, 6);
        bill.addItem(Pbag, 6);
        bill.addItem(Psneak, 6);
        bill.addItem(ts, 6);
        bill.calculateTotalCostItems();

        assertEquals("", 0, bill.getportsTax(), 0.1);
        assertEquals("", 98, bill.gettotalCost(), 0.01);
        assertEquals("", 96.824, bill.getAmount(), 0.001);

        bill.removeItem(camisa, 6);

        assertEquals("", 0, bill.getportsTax(), 0.02);
        assertEquals("", 83, bill.gettotalCost(), 0.02);
        assertEquals("", 82.004, bill.getAmount(), 0.001);

        bill.removeItem(Psneak, 5);
        bill.removeItem(Pbag, 4);

        assertEquals("", 0, bill.getportsTax(), 0.02);
        assertEquals("", 43.5, bill.gettotalCost(), 0.02);
        assertEquals("", 42.978, bill.getAmount(), 0.001);
    }

}