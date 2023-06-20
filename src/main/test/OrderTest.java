package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Stack;

import org.junit.jupiter.api.Test;
import app.*;

public class OrderTest {
        @Test
        public void smallOrder() {
                var order = new Order();
                var t1 = new Carrier();
                Bag bag = new Bag("mala", "null", 10, t1, 0.5,
                                null, 1500, "null", null, 1);

                var u1 = new User("test", "test", "t", 1, "test");
                order.addItem(bag, u1);
                t1.setTaxSmall(.25);
                assertEquals("", 3.5, bag.getPrice(), 0);
                assertEquals("", 0.38, t1.getTaxSmallWithIva(), 0);
                assertEquals("", 5.08, order.calculateFinalPrice(), 0.02);
        }

        @Test
        public void mediumOrder() {
                var order = new Order();
                var t1 = new Carrier();
                var t2 = new Carrier();
                var pO = new Stack<Integer>();
                pO.add(1);

                Bag bag = new Bag(null, null, 10, t1, 0.5,
                                null, 1500, "null", null, 1);

                var sneak = new Sneaker(null, null, 20, t2, 1,
                                null, 42, Util.toSneakerType("LACES"), "black",
                                null, 1);
                sneak.setPreviousOwners(pO);

                var camisa = new Tshirt("null", "null", 30, t1, 0.5,
                                null, Util.toTshirtSize("M"), Util.toTshirtPattern("Smooth"), 1);

                var u1 = new User("test", "test", "t", 1, "test");
                t1.setTaxSmall(.25);
                t1.setTaxMedium(.5);
                t2.setTaxSmall(.15);
                t2.setTaxMedium(.3);
                order.addItem(bag, u1);
                order.addItem(sneak, u1);
                order.addItem(camisa, u1);
                assertEquals("", 3.5, bag.getPrice(), 0);
                assertEquals("", 20, sneak.getPrice(), 0);
                assertEquals("", 30, camisa.getPrice(), 0);
                assertEquals("", 53.5, order.getItemPrice(), 0);
                assertEquals("", 1, order.getSatisfactionPrice(), 0);
                // ----------------------------------------------------
                assertEquals("", 84.205, order.calculateFinalPrice(), 0.001);
                assertEquals("", 1, order.getSatisfactionPrice(), 0.01);
                order.removeItem(sneak, u1);
                assertEquals("", 0.5, order.getSatisfactionPrice(), 0.01);
                assertEquals("", 55.105, order.calculateFinalPrice(), 0.001);
                order.removeItem(bag, u1);
                assertEquals("", 0.25, order.getSatisfactionPrice(), 0.01);
                assertEquals("", 41.65, order.calculateFinalPrice(), 0.01);
        }

        @Test
        public void bigOrder() {
                SystemDate.setDate(LocalDate.now());
                var order = new Order();
                var t1 = new Carrier();
                var t2 = new Carrier();
                var pO = new Stack<Integer>();
                var p1 = new Stack<Integer>();
                pO.add(1);

                Bag bag = new Bag(null, null, 10, t1, .5,
                                null, 1500, "null", null, 1);

                var sneak = new Sneaker(null, null, 20, t2, 1,
                                pO, 42, Util.toSneakerType("LACES"), "black",
                                null, 1);

                var camisa1 = new Tshirt("null", "null", 30, t1, 0.5,
                                null, Util.toTshirtSize("M"), Util.toTshirtPattern("Smooth"), 1);

                var camisa2 = new Tshirt("null", "null", 32, t1, 0.7,
                                null, Util.toTshirtSize("M"), Util.toTshirtPattern("Stripes"), 1);

                Bag newbag = new Bag(null, null, 15, t2, .3,
                                null, 300, "null", null, 1);

                var sneak2 = new Sneaker(null, null, 25, t1, .2,
                                p1, 40, Util.toSneakerType("LACES"), "white",
                                null, 1);

                var u1 = new User("test", "test", "t", 1, "test");
                t1.setTaxSmall(.25);
                t1.setTaxMedium(.5);
                t1.setTaxBig(0.75);
                t2.setTaxSmall(.15);
                t2.setTaxMedium(.3);
                t2.setTaxBig(0.45);
                t1.setName("temp1");
                t2.setName("temp2");
                // -------------------------------------------------------
                order.addItem(bag, u1);
                order.addItem(sneak, u1);
                order.addItem(camisa1, u1);
                order.addItem(camisa2, u1);
                order.addItem(sneak2, u1);
                order.addItem(newbag, u1);

                assertEquals("", 3.5, bag.getPrice(), 0);
                assertEquals("", 20, sneak.getPrice(), 0);
                assertEquals("", 30, camisa1.getPrice(), 0);
                assertEquals("", 16, camisa2.getPrice(), 0);
                assertEquals("", 4.05, newbag.getPrice(), 0.01);
                assertEquals("", 1, sneak2.getPrice(), 0);
                assertEquals("", 74.55, order.getItemPrice(), 0.01);
                // ----------------------------------------------------
                assertEquals("", 1.75, order.getSatisfactionPrice(), 0);
                assertEquals("", 74.55, order.getItemPrice(), 0);
                // -------------------------------------------------------
                assertEquals("", 118.4565, order.calculateFinalPrice(), 0.001);
                order.removeItem(sneak, u1);
                assertEquals("", 1.25, order.getSatisfactionPrice(), 0);
                assertEquals("", 88.749, order.calculateFinalPrice(), 0.001);
        }

}