package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BunTest {

    @Test
    public void getPriceTest_NegativePrice() {
        String name = "Bun";
        float negativePrice = -10.5f;
        Bun bun = new Bun(name, negativePrice);
        float actual = bun.getPrice();
        assertEquals(negativePrice, actual, 0);
    }

    @Test
    public void getPriceTest_ZeroPrice() {
        String name = "Bun";
        float zeroPrice = 0.0f;
        Bun bun = new Bun(name, zeroPrice);
        float actual = bun.getPrice();
        assertEquals(zeroPrice, actual, 0);
    }

    @Test
    public void getPriceTest_PositiveFractionalPrice() {
        String name = "Bun";
        float positiveFractionalPrice = 15.75f;
        Bun bun = new Bun(name, positiveFractionalPrice);
        float actual = bun.getPrice();
        assertEquals(positiveFractionalPrice, actual, 0);
    }

    @Test
    public void getNameTest_EmptyName() {
        String name = "";
        float price = 10.0f;
        Bun bun = new Bun(name, price);
        String actual = bun.getName();
        assertEquals(name, actual);
    }

    @Test
    public void getNameTest_NullName() {
        String name = null;
        float price = 10.0f;
        Bun bun = new Bun(name, price);
        String actual = bun.getName();
        assertEquals(name, actual);
    }

    @Test
    public void getNameTest_SpecialCharacters() {
        String name = "Bun#1@";
        float price = 10.0f;
        Bun bun = new Bun(name, price);
        String actual = bun.getName();
        assertEquals(name, actual);
    }
}