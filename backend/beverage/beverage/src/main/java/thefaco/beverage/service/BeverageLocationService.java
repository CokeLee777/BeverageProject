package thefaco.beverage.service;

import thefaco.beverage.domain.BeverageLocation;

import java.util.List;

public interface BeverageLocationService {
    //새로운 음료 저장
    void save(BeverageLocation beverageLocation);
    //음료 수정
    void update(Long beverageLocationId, String c1, String c2, String c3, String c4);
    //음료 삭제
    void delete(Long beverageLocationId);
    //음료 전체 조회
    List<BeverageLocation> findBeverageLocations();
    //음료 위치 한개 조회
    BeverageLocation findOne(Long row);
    //음료 이름으로 위치 조회
    String findByName(String name);
}
