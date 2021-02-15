package thefaco.beverage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Override
    public List<BeverageLocation> findBeverageLocations() {
        return beverageLocationRepository.findAll();
    }

    @Override
    public BeverageLocation findOne(Long id) {
        return beverageLocationRepository.findOne(id);
    }
}
