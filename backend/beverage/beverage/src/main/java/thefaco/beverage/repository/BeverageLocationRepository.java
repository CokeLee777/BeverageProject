package thefaco.beverage.repository;

import thefaco.beverage.domain.BeverageLocation;

import java.util.List;

public interface BeverageLocationRepository {

    void save(BeverageLocation beverageLocation);
    BeverageLocation findOne(Long id);
    List<BeverageLocation> findAll();
}
