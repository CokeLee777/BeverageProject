package com.example.thefacoBP.object;

import com.example.thefacoBP.AppConfig;
import com.example.thefacoBP.client.ClientService;
import com.example.thefacoBP.shop.Beverage;
import com.example.thefacoBP.shop.BottleType;
import com.example.thefacoBP.shop.ShopRepository;
import com.example.thefacoBP.shop.ShopService;
import org.junit.Assert;
import org.junit.Test;

public class SameObjectTest {

    //컨트롤러
    private static final AppConfig appConfig = new AppConfig();
    //고객 서비스, 음성 서비스, 저장소 변수
    private static final ClientService clientService = appConfig.clientService();
    private static final ShopService shopService = appConfig.shopService();
    private static final ShopRepository shopRepository = appConfig.shopRepository();

    @Test
    public void 같은객체인지판단하는테스트(){
        Beverage coke = new Beverage("1-1", "콜라", BottleType.CAN);
        Beverage water = new Beverage("1-2", "물", BottleType.BOTTLE);

        clientService.join(coke);
        clientService.join(water);

        Beverage findbeverage = clientService.findBeverage("콜라");

        Assert.assertEquals("1-1", findbeverage.getLocation());

    }

    @Test
    public void NullTest(){
        Beverage coke = new Beverage("1-1", "콜라", BottleType.CAN);
        Beverage water = new Beverage("1-2", "물", BottleType.BOTTLE);

        clientService.join(coke);
        clientService.join(water);

        String a = "헬";
        Beverage findBeverage = clientService.findBeverage(a);
        Assert.assertSame(findBeverage, null);

    }

    public void setDB(ShopRepository shopRepository){

    }
}
