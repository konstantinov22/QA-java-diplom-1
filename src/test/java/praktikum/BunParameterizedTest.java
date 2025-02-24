package praktikum;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import java.util.Random;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunParameterizedTest {

    private String name;
    private float price;
    private Bun bun; // Объект Bun, который будет инициализирован в методе setUp()

    public BunParameterizedTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "Тестовые данные: имя={0}, цена={1}")
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

    @Before
    public void setUp() {
        // Инициализация объекта Bun перед каждым тестом
        bun = new Bun(name, price);
    }

    @Test
    public void getNameTest() {
        String actual = bun.getName();
        assertEquals("Проверка имени булочки не пройдена", name, actual);
    }

    @Test
    public void getPriceTest() {
        float actual = bun.getPrice();
        assertEquals("Проверка цены булочки не пройдена", price, actual, 0.0001f);
    }
}