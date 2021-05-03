package pl.szymanowski.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.szymanowski.demo.repository.Product;
import pl.szymanowski.demo.repository.ProductsList;

import java.math.BigDecimal;
import java.util.List;

@Service
@Profile("Plus")
public class ShopPlus {

ProductsList productsList;
    @Value("${vat}")
    BigDecimal vatRate;


@Autowired
ShopPlus(ProductsList productsList)
{

    this.productsList = productsList;

}

@EventListener(ApplicationReadyEvent.class)
public void getListOfPurchasedProducts()
{

    List<Product> purchasedProducts = productsList.getProducts();
    for(Product productWithVat : purchasedProducts)
    {
        productWithVat.setVat(vatRate);
        System.out.println(productWithVat);
        System.out.println("---------------------------------");

    }
    System.out.println("Total price is : "+ productsList.showTotalPrie()+" PLN");
}

}
