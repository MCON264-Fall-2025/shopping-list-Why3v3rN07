package exercises.shopping_app;

import edu.touro.mcon264.apps.collections.ArrayBasedList;
import edu.touro.mcon264.apps.collections.LinkedBasedList;
import edu.touro.mcon264.apps.collections.ListInterface;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Creates a ListInterface<ShoppingItem> instance.
 * Has a main method that runs a console application.
 * Provides a simple text-based menu to:
 * Add items (in sorted order).
 * View the current list.
 * “Shop” the next item on the list.
 * Exit the program.
 */
public class ShoppingListApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ListInterface<ShoppingItem> shoppingList = new LinkedBasedList<>();
        boolean quit = false;
        while (!quit) {
            System.out.print("""
                    \n=== Shopping List Menu ===
                    1. Add item
                    2. Show current shopping list
                    3. Shop next item
                    0. Exit
                    Enter your choice:\s""");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    System.out.print("Enter item name: ");
                    String itemName = sc.nextLine();
                    System.out.print("Enter item aisle: ");
                    boolean gotAisle = false;
                    while (!gotAisle) {
                        try {
                            int aisle = sc.nextInt();
                            sc.nextLine();
                            ShoppingItem newItem = new ShoppingItem(aisle, itemName);
                            insertSorted(shoppingList, newItem);
                            gotAisle = true;
                        } catch (InputMismatchException e) {
                            System.out.print("Aisle must be an integer, try again: ");
                            sc.nextLine();
                        }
                    }
                    System.out.println(itemName + " added to list.");
                    break;
                case "2":
                    if (shoppingList.isEmpty()) {
                        System.out.println("There is nothing on the list.");
                    }
                    else {
                        System.out.println("Current shopping list: ");
                        for (int i = 0; i < shoppingList.size(); i++) {
                            System.out.println(i+1 + " - " + shoppingList.get(i).toString());
                        }
                    }
                    break;
                case "3":
                    ShoppingItem purchase = shopNext(shoppingList);
                    if (purchase != null) {
                        System.out.println("Shopped for " + purchase.getName() + ". ");
                    }
                    else System.out.println("There is nothing on the list.");
                    break;
                case "0":
                    System.out.println("Goodbye.");
                    quit = true;
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
    /**
     * Inserts the given item into the list so that the list remains sorted
     * by aisle and then item name.
     */
    public static void insertSorted(ListInterface<ShoppingItem> list, ShoppingItem item) {
        for (int i = 0; i < list.size(); i++) {
            if (item.compareTo(list.get(i)) < 0) {
                list.add(i, item);
                return;
            }
        }
        list.add(item);
    }
    /**
     * Returns the "next" item to shop and removes it from the list.
     * If the list is empty, returns null.
     */
    public static ShoppingItem shopNext(ListInterface<ShoppingItem> list) {
        if (list.iterator().hasNext()) return list.remove(0);
        else return null;
    }
}