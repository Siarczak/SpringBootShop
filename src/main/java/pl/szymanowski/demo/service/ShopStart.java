package pl.szymanowski.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.szymanowski.demo.repository.ProductsList;

@Service
@Profile("Start")
public class ShopStart {

ProductsList productsList;


@Autowired
ShopStart(ProductsList productsList)
{

    this.productsList = productsList;

}

@EventListener(ApplicationReadyEvent.class)
public void getListOfPurchasedProducts()
{
    System.out.println("Plus Shop");
    productsList.showPruchasedProduts();
    System.out.println("Total pirce is: " + productsList.showTotalPrie());
}

}
