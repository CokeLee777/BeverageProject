package thefaco.beverage.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BeverageRepositoryImplTest {

    @Autowired
    BeverageRepositoryImpl beverageRepository;
    @Test
    @Transactional
    public void testBeverage() throws Exception{
        //given
        Beverage beverage = new Beverage();
        beverage.setName("콜라");
        beverage.setPrice(1200);
        //when
        Long savedId = beverageRepository.save(beverage);
        Beverage findBeverage = beverageRepository.find(savedId);
        //then
        assertThat(beverage.getName()).isEqualTo(findBeverage.getName());
        assertThat(beverage.getId()).isEqualTo(findBeverage.getId());
        assertThat(beverage.getPrice()).isEqualTo(findBeverage.getPrice());

        Beverage beverage1 = beverageRepository.find(5L);
        assertThat(beverage1).isEqualTo(null);
    }
}