package thefaco.beverage.service;

import thefaco.beverage.domain.BeverageLocation;

import java.util.List;

public interface BeverageLocationService {
    //새로운 음료 저장
    void save(BeverageLocation beverageLocation);
    //음료 전체 조회
    List<BeverageLocation> findBeverageLocations();
    //음료 위치 한개 조회
    BeverageLocation findOne(Long row);
}
