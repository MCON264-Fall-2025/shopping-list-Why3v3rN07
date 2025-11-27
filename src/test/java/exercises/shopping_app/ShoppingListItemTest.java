package exercises.shopping_app;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingListItemTest {

    @Test
    void compareToOrdersByAisleFirst() {
        ShoppingItem milkAisle1 = new ShoppingItem(1, "Milk");
        ShoppingItem milkAisle2 = new ShoppingItem(2, "Milk");

        assertTrue(milkAisle1.compareTo(milkAisle2) < 0,
                "Item in aisle 1 should come before item in aisle 2");
        assertTrue(milkAisle2.compareTo(milkAisle1) > 0,
                "Item in aisle 2 should come after item in aisle 1");
    }

    @Test
    void compareToOrdersByNameWithinSameAisle() {
        ShoppingItem apples = new ShoppingItem(3, "Apples");
        ShoppingItem bread = new ShoppingItem(3, "Bread");
        ShoppingItem cheese = new ShoppingItem(3, "Cheese");

        assertTrue(apples.compareTo(bread) < 0,
                "Apples should come before Bread in the same aisle");
        assertTrue(bread.compareTo(cheese) < 0,
                "Bread should come before Cheese in the same aisle");
        assertTrue(cheese.compareTo(apples) > 0,
                "Cheese should come after Apples in the same aisle");
    }

    @Test
    void compareToReturnsZeroForSameAisleAndName() {
        ShoppingItem item1 = new ShoppingItem(5, "Eggs");
        ShoppingItem item2 = new ShoppingItem(5, "Eggs");

        assertEquals(0, item1.compareTo(item2),
                "Items with same aisle and name should compare as equal");
    }

    @Test
    void toStringContainsAisleAndName() {
        ShoppingItem item = new ShoppingItem(7, "Yogurt");
        String s = item.toString();

        assertTrue(s.contains("7") || s.toLowerCase().contains("aisle"),
                "toString should mention the aisle");
        assertTrue(s.toLowerCase().contains("yogurt"),
                "toString should mention the item name");
    }
}

