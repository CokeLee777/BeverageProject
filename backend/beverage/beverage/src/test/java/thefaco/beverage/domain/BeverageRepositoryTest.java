package thefaco.beverage.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeverageRepositoryTest {

    @Autowired BeverageRepository beverageRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void testBeverage() throws Exception{
        //given
        Beverage beverage = new Beverage();
        beverage.setName("콜라");
        beverage.setPrice(1200);
        //when
        Long savedId = beverageRepository.save(beverage);
        Beverage findBeverage = beverageRepository.find(savedId);
        //then


    }
}