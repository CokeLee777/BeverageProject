package thefaco.beverage.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import thefaco.beverage.domain.BeverageLocation;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeverageLocationRepositoryImplTest {

    @Autowired BeverageLocationRepository beverageLocationRepository;

    @Test
    public void findByNameJPQL테스트() throws Exception {
        //given
        String beverage = "콜라";
        //when
        String findName = beverageLocationRepository.findByName(beverage);
        //then
        System.out.println("findName = " + findName);
    }
}