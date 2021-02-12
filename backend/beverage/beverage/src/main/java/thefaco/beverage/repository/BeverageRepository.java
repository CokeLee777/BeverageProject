package thefaco.beverage.repository;

import thefaco.beverage.domain.Beverage;

import java.util.List;

public interface BeverageRepository {

    void save(Beverage beverage);
    Beverage findOne(Long id);
    List<Beverage> findAll();
    List<Beverage> findByName(String name);
}
