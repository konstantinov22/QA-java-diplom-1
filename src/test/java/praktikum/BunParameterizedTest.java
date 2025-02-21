package praktikum;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunParameterizedTest {
    private String name;
    private float price;

    public BunParameterizedTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {RandomStringUtils.randomAlphabetic(10), new Random().nextFloat()}, // случайное имя и цена
                {RandomStringUtils.randomAlphabetic(10), 135.22f}, // случайное имя и фиксированная цена
                {"", 10.0f}, // пустое имя
                {null, 10.0f}, // имя null
                {"Special Bun", 0.0f}, // имя с пробелами и нулевая цена
                {"Bun#1", -5.0f}, // имя с символами и отрицательная цена
                {"Bun", 8.5f} // имя с символами и дробная цена
        };
    }

    @Test
    public void getNameTest() {
        Bun bun = new Bun(name, price);
        String actual = bun.getName();
        assertEquals(name, actual);
    }

    @Test
    public void getPriceTest() {
        Bun bun = new Bun(name, price);
        float actual = bun.getPrice();
        assertEquals(price, actual, 0);
    }
}
