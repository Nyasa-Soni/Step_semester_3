package encapsulation.assigment_problems;

/**
 * Assignment Problem 5: The Shopping Cart
 * 
 * Demonstrates internal collection encapsulation with dynamic on-demand calculation:
 * - Prices are stored in a private array of fixed maximum capacity.
 * - Array of prices is NEVER exposed directly or indirectly (no price array getter).
 * - Cart ID is final and locked upon creation.
 * - getTotal() computes the sum on demand by iterating the private array.
 *   No separate running-total field is maintained.
 * - getItemCount() provides read-only access to the count of added items.
 */
public class L5_ShoppingCart {

    /**
     * Cart model class.
     */
    static class Cart {
        // Unique cart identifier locked at creation
        private final String cartId;

        // Private array storing item prices; never leaked outside
        private final int[] prices;

        // Tracks number of items currently in the cart
        private int count;

        /**
         * Constructs a Cart with a fixed cart ID and maximum item capacity.
         *
         * @param cartId   the unique identifier for the cart
         * @param capacity maximum number of items the cart can hold
         */
        public Cart(String cartId, int capacity) {
            this.cartId = cartId;
            int size = Math.max(0, capacity);
            this.prices = new int[size];
            this.count = 0;
        }

        /**
         * Adds an item price to the cart if capacity permits and price is non-negative.
         *
         * @param price the price of the item
         * @return true if added successfully, false if cart is full or price invalid
         */
        public boolean addItem(int price) {
            if (price >= 0 && this.count < this.prices.length) {
                this.prices[this.count] = price;
                this.count++;
                return true;
            }
            return false;
        }

        /**
         * Overloaded convenience method to accept double prices.
         *
         * @param price decimal item price
         * @return true if added, false otherwise
         */
        public boolean addItem(double price) {
            return addItem((int) Math.round(price));
        }

        /**
         * Computes and returns the total sum of all item prices currently in the cart.
         * Dynamically calculated on request by looping through the private array.
         *
         * @return total cost of all added items
         */
        public int getTotal() {
            int total = 0;
            for (int i = 0; i < this.count; i++) {
                total += this.prices[i];
            }
            return total;
        }

        /**
         * Returns the number of items currently in the cart.
         *
         * @return item count
         */
        public int getItemCount() {
            return this.count;
        }

        /**
         * Read-only getter for the cart ID.
         *
         * @return the cart ID
         */
        public String getCartId() {
            return this.cartId;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Problem 5: The Shopping Cart ===");

        // PDF Sample demonstration
        System.out.println("\n--- PDF Sample Run ---");
        Cart cart = new Cart("CART-5", 20);
        System.out.println("Created cart ID: " + cart.getCartId());

        cart.addItem(250);
        cart.addItem(99);
        cart.addItem(151);

        System.out.println("cart.getTotal() -> " + cart.getTotal());
        System.out.println("cart.getItemCount() -> " + cart.getItemCount());

        // Edge Cases
        System.out.println("\n--- Edge Case Testing ---");

        // Edge Case 1: Empty cart
        Cart emptyCart = new Cart("CART-EMPTY", 10);
        System.out.println("Empty cart: total = " + emptyCart.getTotal() + ", count = " + emptyCart.getItemCount());

        // Edge Case 2: Single item cart
        Cart singleItem = new Cart("CART-SINGLE", 5);
        singleItem.addItem(42);
        System.out.println("Single item cart: total = " + singleItem.getTotal() + ", count = " + singleItem.getItemCount());

        // Edge Case 3: Cart capacity boundary & overflow
        Cart tinyCart = new Cart("CART-TINY", 2);
        boolean i1 = tinyCart.addItem(100);
        boolean i2 = tinyCart.addItem(200);
        boolean i3 = tinyCart.addItem(300); // Exceeds capacity
        System.out.println("Tiny cart additions: item1=" + i1 + ", item2=" + i2 + ", item3 (overflow)=" + i3);
        System.out.println("Tiny cart total = " + tinyCart.getTotal() + ", count = " + tinyCart.getItemCount());

        // Edge Case 4: Re-calculating total dynamically after subsequent item added
        cart.addItem(100);
        System.out.println("Added 100 to main cart -> new total = " + cart.getTotal() + ", new count = " + cart.getItemCount());
    }
}