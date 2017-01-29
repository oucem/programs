package ru.epatko.qualityControl;

/**
 * @author Mikhail Epatko (epatko-m-i@rambler.ru).
 *         29.01.17.
 */
public class Meat implements Food {

    /**
     * Product name.
     */
    private String name;
    /**
     * Product expiration date.
     */
    private long expirationDate;
    /**
     * Product creation date.
     */
    private long createDate;
    /**
     * Product price.
     */
    private double price;
    /**
     * Product discount.
     */
    private double discount;
    /**
     * Product quantity.
     */
    private double quantity;

    /**
     * Set name.
     * @param name - product name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Set product expiration date.
     * @param expirationDate - product expiration date.
     */
    public void setExpirationDate(long expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Set product creation date.
     * @param createDate - product creation date.
     */
    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    /**
     * Set product quantity.
     * @param quantity - product quantity.
     */
    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    /**
     * Set product price.
     * @param price - product price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Set discount.
     * @param discount - discount.
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * get product name.
     * @return -  product name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get product expiration date.
     * @return - product expiration date (long).
     */
    public long getExpirationDate() {
        return expirationDate;
    }

    /**
     * Get product creation date.
     * @return - product creation date (long).
     */
    public long getCreateDate() {
        return createDate;
    }

    /**
     * Get product quantity.
     * @return - product quantity.
     */
    public double getQuantity() {
        return this.quantity;
    }

    /**
     * Get product price.
     * @return - product price.
     */
    public double getPrice() {
        double priceWithDiscount;
        if (discount != 0) {
            priceWithDiscount = this.price * this.discount / 100;
        } else {
            priceWithDiscount = this.price;
        }
        return priceWithDiscount;
    }

    /**
     * Get product discount.
     * @return - discount.
     */
    public double getDiscount() {
        return discount;
    }
}
