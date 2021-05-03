package pl.szymanowski.demo.repository;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductsList {

    private final List<Product> products = new ArrayList<>();
    Double totalPrice= 0.0;

    public ProductsList() {
        Product product = new Product("cheese");
        Product product2 = new Product("bread");
        Product product3 = new Product("butter");
        Product product4 = new Product("milk");
        Product product5 = new Product("ham");
        products.add(product);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);

    }

    public List<Product> getProducts() {

        return products;
    }

    public void showPruchasedProduts() {
        System.out.println("Purchased products: ");
        for (Product product : products) {

            System.out.println(product);
        }

    }

    public BigDecimal showTotalPrie()
    {

        for (Product product : products) {

            if (product.getVat() != null && product.getDiscount()== null) {

                totalPrice += product.grossPrice().doubleValue();

            }
            if (product.getVat() != null && product.getDiscount() != null) {

                totalPrice += product.discountedPrice().doubleValue();

            }
            else {

                totalPrice += product.getPrice();
            }
        }
        BigDecimal total = new BigDecimal(totalPrice);
        return total.setScale(2, RoundingMode.FLOOR);
    }

}


