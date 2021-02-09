package com.example.thefaco.object;

import com.example.thefaco.AppConfig;
import com.example.thefaco.client.ClientService;
import com.example.thefaco.shop.Beverage;
import com.example.thefaco.shop.BottleType;
import com.example.thefaco.shop.ShopRepository;
import com.example.thefaco.shop.ShopService;
import org.junit.Assert;
import org.junit.Test;

public class SameObjectTest {

    public static AppConfig appConfig = new AppConfig();

    @Test
    public void 같은객체인지판단하는테스트(){
        Beverage coke = new Beverage("1-1", "콜라", BottleType.CAN);
        Beverage water = new Beverage("1-2", "물", BottleType.BOTTLE);
        ClientService clientService = appConfig.clientService();
        ShopService shopService = appConfig.shopService();
        ShopRepository shopRepository = appConfig.shopRepository();

        shopRepository.save(coke);
        shopRepository.save(water);

        String location = shopRepository.findBeverageLocation("콜라");
        String location2 = clientService.sayBeverageName("콜라");
        String location3 = shopService.findBeverageByShop(coke);

        Assert.assertEquals("1-1", location);
        Assert.assertEquals("1-1", location2);
        Assert.assertEquals("1-1", location3);
        Assert.assertSame(location, location2, location3);

        String location4 = shopRepository.findBeverageLocation("물");
        String location5 = clientService.sayBeverageName("물");
        String location6 = shopService.findBeverageByShop(water);

        Assert.assertEquals("1-2", location4);
        Assert.assertEquals("1-2", location5);
        Assert.assertEquals("1-2", location6);
        Assert.assertSame(location4, location5, location6);
    }

    public void setDB(ShopRepository shopRepository){

    }
}
