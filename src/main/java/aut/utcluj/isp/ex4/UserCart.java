package aut.utcluj.isp.ex4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author stefan
 */
public class UserCart implements ICartDetails {

    private List<Product> cardProducts;
    private double totalPrice;

    public UserCart() {
        this.cardProducts = new ArrayList<>();
        this.cardProducts.clear();
        this.totalPrice = 0;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public List<Product> getCardProducts() {
        return cardProducts;
    }

    /**
     * Add new product to user cart
     *
     * @param product - product to be added
     * @param quantity - number of products of the same type to be added
     */
    public void addProductToCart(final Product product, int quantity) {
        if (quantity > 0) {
            for (int i = 0; i < quantity; i++) {
                cardProducts.add(product);
                this.totalPrice += product.getPrice();
            }
            System.out.println("The product with id: " + product.getProductId() + " has been added to the cart " + quantity + " times.");
            System.out.println("The total price is now " + this.totalPrice);
        } else {
            System.out.println("The quantity must be greater than 0.");
        }
    }

    /**
     * Remove one product with product id from cart If the product with desired
     * id not found in the card, an {@link ProductNotFoundException} exception
     * will be thrown
     *
     * @param productId - unique product id
     */
    public void removeProductFromCart(final String productId) throws ProductNotFoundException {
        int sizeBeforeRemove = cardProducts.size();
        Iterator<Product> productIterator = cardProducts.iterator();
        boolean doneRemoving = false;
        while (productIterator.hasNext() && !doneRemoving) {
            Product product = productIterator.next();
            if (product.getProductId().equals(productId)) {
                productIterator.remove();
                this.totalPrice -= product.getPrice();
                doneRemoving = true;
            }
        }
        if (sizeBeforeRemove == cardProducts.size()) {
            System.out.println("The product with the id " + productId + " does not exist.");
            throw new ProductNotFoundException();
        }
        System.out.println("All the products with the id " + productId + " has been removed from cart.");
    }

    /**
     * Reset user cart Reset products and total price to default values
     */
    public void resetCart() {
        this.totalPrice = 0;
        this.cardProducts.clear();
        System.out.println("The cart has been cleared. Total price is now : " + this.totalPrice + " and the list is empty : " + cardProducts.isEmpty());
    }

    @Override
    public String getCartDetails() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Product productIterator : cardProducts) {
            int counter = 0;
            if (!stringBuilder.toString().contains(productIterator.getProductId())) {
                for (Product productCounter : cardProducts) {
                    if (productCounter.getProductId().equals(productIterator.getProductId())) {
                        counter++;
                    }
                }
                stringBuilder.append("Product id: " + productIterator.getProductId() + ", Items: " + counter + "\n");
            }
        }
        stringBuilder.append("Total price: " + this.totalPrice);
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }
}
