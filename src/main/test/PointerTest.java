package test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import org.junit.jupiter.api.Test;
import app.*;

public class PointerTest{
    @Test
    public void userManager() {
        int res1 = 0;
        int res2 = 0;
        int res3 = 0;
        double res4 = 0;

        var pO = new Stack<Integer>();

        var carrier = new Carrier("a1", .1, .2, .3, 0);

        var bag = new Bag("a", "a", 15, carrier, .5, pO,
                          1500, "Cotton", null, 0);

        var bills = new HashMap<Integer,Bill>();
        var sysI = new ArrayList<Item>();
        var sell = new ArrayList<Item>();

        sysI.add(0, bag);

        var lista = new UserManager();
        var user1 = new User("a", "David", "a", 0, bills, "a", sysI, sell);
        var user2 = new User("b", "Pastore", "b", 1, bills, "b", sysI, sell);
        var user3 = new User("c", "Nuno", "c", 2, bills, "c", sysI, sell);
        lista.addUser(user1);
        lista.addUser(user2);
        lista.addUser(user3);

        if (lista.getUser(1).getName().equals("David")) res1 = 1;
        if (lista.getUser(3).getName().equals("Nuno")) res2 = 1;
        res3 = lista.getUser(3).getNif();
        if (lista.getUser(2).getSystemItems().get(0).getDescription().equals("a")) res4 = 1;

        assertEquals(null, res1, 1, 0);
        assertEquals(null, res2, 1, 0);
        assertEquals(null, res3, 2, 0);
        assertEquals(null, res4, 1, 0);
    }

    @Test
    public void itemManager() {
        int res1 = 0;
        int res2 = 0;
        double res3 = 0;
        int res4 = 0;

        var pO = new Stack<Integer>();

        var carrier = new Carrier("a1", .1, .2, .3, 0);

        var lista = new ItemManager();

        var bag = new Bag("a", "a", 15, carrier, .5, pO,
                          1500, "Cotton", null, 0);
        var tshirt = new Tshirt("b", "b", 29, carrier, .76, pO,
                                Util.toTshirtSize("M"), Util.toTshirtPattern("Smooth"), 0);
        var sneaker = new Sneaker("c", "c", 35, carrier, .12, pO,
                                  42, Util.toSneakerType("LACES"), "black", Util.toDate("2012-12-12"), 1);

        lista.addListedItem(bag);
        lista.addListedItem(tshirt);

        assertEquals(null, sneaker.getBasePrice(), 35, 0);

        if (lista.getItem(1).getCarrier().getName().equals("a1")) res1 = 1;
        res2 = lista.getListedItems().size();
        res3 = lista.getItem(2).getConditionScore();

        assertEquals(null, res1, 1, 0);
        assertEquals(null, res2, 2, 0);
        assertEquals(null, res3, .76, 0);

        lista.addSoldItem(sneaker);
        lista.soldToListed(3);
        res4 = lista.getListedItems().size();

        assertEquals(null, res4, 3, 0);
    }

    @Test
    public void orderManager(){
        SystemDate.setDate(LocalDate.now());
        int res1 = 0;
        double res2 = 0;
        double res3 = 0;
        int res4 = 0;

        var pO = new Stack<Integer>();
        var carrier = new Carrier("a1", .1, .2, .3, 0);
        
        var hash = new HashMap<String,Integer>();
        var bills = new HashMap<Integer,Bill>();
        var sysI = new ArrayList<Item>();
        var sell = new ArrayList<Item>();

        var lista = new OrderManager();

        var bag = new Bag("a", "a", 15, carrier, .5, pO,
                          1500, "Cotton", Util.toDate("2012-12-12"), 0);
        var tshirt = new Tshirt("b", "b", 29, carrier, .76, pO,
                                Util.toTshirtSize("M"), Util.toTshirtPattern("Smooth"), 0);
        var sneaker = new Sneaker("c", "c", 35, carrier, .2, pO,
                                  42, Util.toSneakerType("LACES"), "black", Util.toDate("2012-12-12"), 1);

        var o = new Order();

        var user = new User("a", "David", "a", 0, bills, "a", sysI, sell);

        hash.put("a1", 1);

        o.addItem(bag, user);
        o.addItem(sneaker, user);
        o.addItem(tshirt, user);
        o.calculateFinalPrice();
        o.setBuyer(user);

        lista.addOrder(o);

        if(lista.getOrder(o.getID()).getCarrierHelper().get(carrier.getName()) == 3) res1 = 1;
        res2 = lista.getOrder(o.getID()).getCollection().get(0).getPrice();
        res3 = lista.getOrder(o.getID()).getItemPrice();
        if (lista.getOrder(o.getID()).getBuyer().getName().equals("David")) res4 = 1;

        assertEquals(null, res1, 1, 0);
        assertEquals(null, res2, 5.25, 0);
        assertEquals(null, res3, 35.65, 0.01);
        assertEquals(null, res4, 1, 0);
    }

    @Test
    public void carrierManager() throws CarrierAlreadyExistsException{
        double res1 = 0;
        double res2 = 0;
        int res3 = 0;

        var lista = new CarrierManager();

        var carrier1 = new Carrier("a1", .1, .2, .3, 0);
        var carrier2 = new Carrier("a2", .25, .5, .75, 0);
        var carrier3 = new Carrier("a3", .4, .45, .5, 0);

        lista.addCarrier(carrier1);
        lista.addCarrier(carrier2);
        lista.addCarrier(carrier3);
        lista.removeCarrier("a3");

        res1 = lista.getCarrier("a1").getTaxSmallWithIva();
        if(lista.getCarrier("a2").getName().equals("a2")) res2 = 1;
        res3 = lista.getCarriers().size();

        assertEquals(null, res1, .23, 0);
        assertEquals(null, res2, 1, 0);
        assertEquals(null, res3, 2, 0);
    }
}