package exercises.shopping_app;

/**
 * Requirements for ShoppingItem
 * Fields:
 * String aisle; (you may also use int if you prefer numeric aisles)
 * String name;
 * A constructor that sets both fields.
 * getAisle() and getName() accessor methods (and toString() for nice printing).
 * Implement Comparable<ShoppingItem> so that items are ordered by:
 * Aisle (ascending), then
 * Name (alphabetically) when aisles are equal
 */
public class ShoppingItem implements Comparable<ShoppingItem> {
    private int aisle;
    private String name;

    ShoppingItem(int aisle, String name) {
        this.aisle = aisle;
        this.name = name;
    }
    public int getAisle() { return aisle; }
    public String getName() { return name; }

    @Override
    public String toString() {
        return name + " (aisle " + aisle + ")";
    }

    @Override
    public int compareTo(ShoppingItem other) {
        int aisleCompare =  this.aisle - other.aisle;
        if (aisleCompare != 0) {
            return aisleCompare;
        }
        return this.name.compareToIgnoreCase(other.name);
    }

}
