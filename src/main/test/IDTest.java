package test;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import app.*;

public class IDTest {
    @Test
    public void userID() {
        var u1 = new User();
        var u2 = new User();
        var m = new UserManager();
        m.addUser(u1);
        m.addUser(u2);
        assertEquals("", 1, u1.getId(), 0);
        assertEquals("", 2, u2.getId(), 0);
        var u3 = new User();
        m.addUser(u3);
        m.removeUser(u2.getId());
        assertEquals("", 3, u3.getId(), 0);
    }

    @Test
    public void itemID() {
        var a = new Bag();
        var b = new Sneaker();
        assertEquals("", 1, a.getID(), 0);
        assertEquals("", 2, b.getID(), 0);
    }
}