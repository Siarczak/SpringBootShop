package pl.szymanowski.demo.repository;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

@Repository

public class Product {

    private String name;
    private double price;
    BigDecimal vat;
    BigDecimal discount;


    public Product() {
    }

    public Product(String name) {
        this.name = name;
        this.price = randomPriceGenerator();
        this.vat = getVat();
        this.discount = getDiscount();

    }

    public BigDecimal getVat() {
        return vat;
    }

    public void setVat(BigDecimal vat) {
        this.vat = vat;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public double getPrice() {
        return price;
    }

    private double randomPriceGenerator() {

        double random = (ThreadLocalRandom.current().nextDouble(50, 300)) * 100.00 / 100.0;
        price = Math.round(random * 100.0) / 100.0;
        return price;

    }

    public BigDecimal grossPrice() {

        Double priceWithVat = (1+vat.doubleValue()/100) * price;
        BigDecimal pricewithVatRounded = new BigDecimal(priceWithVat);
        return pricewithVatRounded.setScale(2, RoundingMode.FLOOR);

    }

    public BigDecimal discountedPrice() {

        Double priceAfterDiscount = (1-discount.doubleValue()/100) * grossPrice().doubleValue();
        BigDecimal pricewihDiscountRounded = new BigDecimal(priceAfterDiscount);
        return pricewihDiscountRounded.setScale(2, RoundingMode.FLOOR);
        
    }

    @Override
    public String toString() {

        if (vat != null && discount == null) {
            return "Product name: " + name + " Product Price: " + price +" PLN " +"\n" +" Vat rate: " + vat +"% "+ " Gross Price: " + grossPrice()+" PLN";

        }
        if (vat != null && discount != null) {
            return "Product name: " + name + " product Price: " + price +" PLN" +"\n" +" Vat rate: " + vat +"% "+ " Gross Price: " + grossPrice() +" PLN " + "Discount rate:" + discount +"% "+ " Gross pirce after discount " + discountedPrice()+" PLN";


        } else {
            return "Product name: " + name + " product Price: " + price+" PLN";

        }

    }
}



