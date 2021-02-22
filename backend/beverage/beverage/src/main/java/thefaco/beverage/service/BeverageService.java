package thefaco.beverage.service;

import thefaco.beverage.domain.Beverage;

import java.util.List;

public interface BeverageService {

    //새로운 음료 저장
    void save(Beverage beverage);
    //음료 수정
    void update(Long beverageId, String name, int price, String type, int size);
    //음료 삭제
    void delete(Long beverageId);
    //음료 전체 조회
    List<Beverage> findBeverages();
    //음료 한개 조회
    Beverage findOne(Long beverageId);
}
