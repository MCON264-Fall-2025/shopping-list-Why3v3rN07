package exercises.shopping_app;

import edu.touro.mcon264.apps.collections.ArrayBasedList;
import edu.touro.mcon264.apps.collections.LinkedBasedList;
import edu.touro.mcon264.apps.collections.ListInterface;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingListAppTest {

    // ---------- Helpers to create lists with each implementation ----------

    private ListInterface<ShoppingItem> createArrayBasedList() {
        return new ArrayBasedList<>();
    }

    private ListInterface<ShoppingItem> createLinkedBasedList() {
        return new LinkedBasedList<>();
    }

    // Small helper to extract list contents as strings for easier assertions
    private List<String> toStringList(ListInterface<ShoppingItem> list) {
        List<String> result = new ArrayList<>();
        Iterator<ShoppingItem> iterator = list.iterator();
        while (iterator.hasNext()) {
            ShoppingItem item = iterator.next();
            // encode as "aisle:name" for easier comparison
            result.add(item.getAisle() + ":" + item.getName());
        }
        return result;
    }

    // ---------- Tests for insertSorted (ArrayBasedList) ----------

    @Test
    void insertSorted_keepsOrderByAisleAndName_arrayBased() {
        ListInterface<ShoppingItem> list = createArrayBasedList();

        // Add in unsorted order
        ShoppingListApp.insertSorted(list, new ShoppingItem(5, "Milk"));
        ShoppingListApp.insertSorted(list, new ShoppingItem(1, "Apples"));
        ShoppingListApp.insertSorted(list, new ShoppingItem(3, "Bread"));
        ShoppingListApp.insertSorted(list, new ShoppingItem(3, "Apples"));
        ShoppingListApp.insertSorted(list, new ShoppingItem(1, "Bananas"));

        List<String> actual = toStringList(list);

        List<String> expected = List.of(
                "1:Apples",
                "1:Bananas",
                "3:Apples",
                "3:Bread",
                "5:Milk"
        );

        assertEquals(expected, actual, "List should be sorted by aisle, then name (ArrayBasedList)");
    }

    // ---------- Tests for insertSorted (LinkedBasedList) ----------

    @Test
    void insertSorted_keepsOrderByAisleAndName_linkedBased() {
        ListInterface<ShoppingItem> list = createLinkedBasedList();

        // Add in unsorted order
        ShoppingListApp.insertSorted(list, new ShoppingItem(5, "Milk"));
        ShoppingListApp.insertSorted(list, new ShoppingItem(1, "Apples"));
        ShoppingListApp.insertSorted(list, new ShoppingItem(3, "Bread"));
        ShoppingListApp.insertSorted(list, new ShoppingItem(3, "Apples"));
        ShoppingListApp.insertSorted(list, new ShoppingItem(1, "Bananas"));

        List<String> actual = toStringList(list);

        List<String> expected = List.of(
                "1:Apples",
                "1:Bananas",
                "3:Apples",
                "3:Bread",
                "5:Milk"
        );

        assertEquals(expected, actual,
                "List should be sorted by aisle, then name (LinkedBasedList)");
    }

    // ---------- Tests for shopNext (ArrayBasedList) ----------

    @Test
    void shopNext_returnsItemsInSortedOrder_andRemoves_arrayBased() {
        ListInterface<ShoppingItem> list = createArrayBasedList();

        ShoppingListApp.insertSorted(list, new ShoppingItem(2, "Eggs"));
        ShoppingListApp.insertSorted(list, new ShoppingItem(1, "Bread"));
        ShoppingListApp.insertSorted(list, new ShoppingItem(1, "Apples"));

        // List should be: [1:Apples, 1:Bread, 2:Eggs]

        ShoppingItem first = ShoppingListApp.shopNext(list);
        ShoppingItem second = ShoppingListApp.shopNext(list);
        ShoppingItem third = ShoppingListApp.shopNext(list);
        ShoppingItem fourth = ShoppingListApp.shopNext(list); // should be null

        assertNotNull(first);
        assertNotNull(second);
        assertNotNull(third);
        assertNull(fourth, "After shopping all items, next should be null");

        assertEquals(1, first.getAisle());
        assertEquals("Apples", first.getName());

        assertEquals(1, second.getAisle());
        assertEquals("Bread", second.getName());

        assertEquals(2, third.getAisle());
        assertEquals("Eggs", third.getName());

        assertEquals(0, list.size(), "List should be empty after shopping all items");
    }

    // ---------- Tests for shopNext (LinkedBasedList) ----------

    @Test
    void shopNext_returnsItemsInSortedOrder_andRemoves_linkedBased() {
        ListInterface<ShoppingItem> list = createLinkedBasedList();

        ShoppingListApp.insertSorted(list, new ShoppingItem(2, "Eggs"));
        ShoppingListApp.insertSorted(list, new ShoppingItem(1, "Bread"));
        ShoppingListApp.insertSorted(list, new ShoppingItem(1, "Apples"));

        // List should be: [1:Apples, 1:Bread, 2:Eggs]

        ShoppingItem first = ShoppingListApp.shopNext(list);
        ShoppingItem second = ShoppingListApp.shopNext(list);
        ShoppingItem third = ShoppingListApp.shopNext(list);
        ShoppingItem fourth = ShoppingListApp.shopNext(list); // should be null

        assertNotNull(first);
        assertNotNull(second);
        assertNotNull(third);
        assertNull(fourth, "After shopping all items, next should be null");

        assertEquals(1, first.getAisle());
        assertEquals("Apples", first.getName());

        assertEquals(1, second.getAisle());
        assertEquals("Bread", second.getName());

        assertEquals(2, third.getAisle());
        assertEquals("Eggs", third.getName());

        assertEquals(0, list.size(), "List should be empty after shopping all items");
    }

    // ---------- Edge cases ----------

    @Test
    void insertSorted_onEmptyList_placesSingleElementAtIndexZero() {
        ListInterface<ShoppingItem> list = createArrayBasedList();

        ShoppingItem item = new ShoppingItem(4, "Milk");
        ShoppingListApp.insertSorted(list, item);

        assertEquals(1, list.size(), "List should contain one element");
        ShoppingItem stored = list.get(0);
        assertEquals(item.getAisle(), stored.getAisle());
        assertEquals(item.getName(), stored.getName());
    }

    @Test
    void shopNext_onEmptyListReturnsNull() {
        ListInterface<ShoppingItem> arrayList = createArrayBasedList();
        ListInterface<ShoppingItem> linkedList = createLinkedBasedList();

        ShoppingItem fromArray = ShoppingListApp.shopNext(arrayList);
        ShoppingItem fromLinked = ShoppingListApp.shopNext(linkedList);

        assertNull(fromArray, "shopNext on empty ArrayBasedList should return null");
        assertNull(fromLinked, "shopNext on empty LinkedBasedList should return null");
    }

    @Test
    void iterator_traversesInSortedOrder() {
        ListInterface<ShoppingItem> list = createArrayBasedList();

        ShoppingListApp.insertSorted(list, new ShoppingItem(3, "Cheese"));
        ShoppingListApp.insertSorted(list, new ShoppingItem(1, "Bananas"));
        ShoppingListApp.insertSorted(list, new ShoppingItem(1, "Apples"));
        ShoppingListApp.insertSorted(list, new ShoppingItem(2, "Donuts"));

        List<String> actual = toStringList(list);

        List<String> expected = List.of(
                "1:Apples",
                "1:Bananas",
                "2:Donuts",
                "3:Cheese"
        );

        assertEquals(expected, actual,
                "Iterator / for-each should see items in sorted order");
    }
}

