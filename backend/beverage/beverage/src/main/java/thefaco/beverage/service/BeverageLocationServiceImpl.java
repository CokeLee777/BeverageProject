package thefaco.beverage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thefaco.beverage.controller.BeverageLocationJsonForm;
import thefaco.beverage.domain.BeverageLocation;
import thefaco.beverage.repository.BeverageLocationRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BeverageLocationServiceImpl implements BeverageLocationService{

    private final BeverageLocationRepository beverageLocationRepository;

    @Transactional
    @Override
    public void save(BeverageLocation beverageLocation) {
        beverageLocationRepository.save(beverageLocation);
    }

    @Transactional
    @Override
    public void update(Long beverageLocationId, String c1, String c2, String c3, String c4) {
        BeverageLocation findBeverageLocation = beverageLocationRepository.findOne(beverageLocationId);
        findBeverageLocation.setC1(c1);
        findBeverageLocation.setC2(c2);
        findBeverageLocation.setC3(c3);
        findBeverageLocation.setC4(c4);
    }

    @Transactional
    @Override
    public void delete(Long beverageLocationId) {
        BeverageLocation beverageLocation = beverageLocationRepository.findOne(beverageLocationId);
        beverageLocationRepository.delete(beverageLocation);
    }

    @Override
    public List<BeverageLocation> findBeverageLocations() {
        return beverageLocationRepository.findAll();
    }

    @Override
    public BeverageLocation findOne(Long id) {
        return beverageLocationRepository.findOne(id);
    }

    @Override
    public String findByName(String name) {
        return beverageLocationRepository.findByName(name);
    }

    @Override
    public BeverageLocationJsonForm findObjectByName(String name) {
        return beverageLocationRepository.findObjectByName(name);
    }
}
