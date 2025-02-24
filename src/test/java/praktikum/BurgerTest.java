package praktikum;

import praktikum.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;

    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient;
    @Mock
    private Ingredient ingredient2;
    @Mock
    private Ingredient ingredient3;

    @Before
    public void createBurger() {
        burger = new Burger();
    }

    @Test
    public void setBunTest() {
        burger.setBuns(bun);

        assertEquals(bun.getName(), burger.bun.getName());
    }

    @Test
    public void addIngredientTest() {
        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientTest_SizeAfterRemoval() {
        // Добавляем ингредиенты
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        // Удаляем один ингредиент
        burger.removeIngredient(2);

        // Проверяем, что размер списка уменьшился
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void removeIngredientTest_CorrectIngredientRemoved() {
        // Добавляем ингредиенты
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        // Удаляем ингредиент на позиции 2
        burger.removeIngredient(2);

        // Проверяем, что удален правильный ингредиент
        assertFalse(burger.ingredients.contains(ingredient3));
    }

    @Test
    public void moveIngredientTest_NewPositionCorrect() {
        // Добавляем ингредиенты
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        // Перемещаем ингредиент с позиции 1 на позицию 2
        burger.moveIngredient(1, 2);

        // Проверяем, что ингредиент оказался на новой позиции
        assertEquals(ingredient2, burger.ingredients.get(2));
    }

    @Test
    public void moveIngredientTest_OldPositionCorrect() {
        // Добавляем ингредиенты
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        // Перемещаем ингредиент с позиции 1 на позицию 2
        burger.moveIngredient(1, 2);

        // Проверяем, что на старой позиции теперь другой ингредиент
        assertEquals(ingredient3, burger.ingredients.get(1));
    }

    @Test
    public void getPriceTest() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);

        when(bun.getPrice()).thenReturn(30F);
        when(ingredient.getPrice()).thenReturn(15F);
        when(ingredient2.getPrice()).thenReturn(3F);

        float expected = (30F * 2) + 15F + 3F;

        assertEquals(expected, burger.getPrice(), 0);
    }

    @Test
    public void getReceiptTest() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient2);

        when(bun.getName()).thenReturn("burger");
        when(ingredient.getName()).thenReturn("ingredient1");
        when(ingredient2.getName()).thenReturn("ingredient2");
        when(bun.getPrice()).thenReturn(20F);
        when(ingredient.getPrice()).thenReturn(10F);
        when(ingredient2.getPrice()).thenReturn(2F);
        when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        when(ingredient2.getType()).thenReturn(IngredientType.FILLING);

        String expected = "(==== burger ====)\r\n" +
                "= sauce ingredient1 =\r\n" +
                "= filling ingredient2 =\r\n"    +
                "(==== burger ====)\r\n" +
                "\r\n" +
                "Price: 52,000000\r\n";

        assertEquals(expected, burger.getReceipt());
    }

}