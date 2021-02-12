package thefaco.beverage.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import thefaco.beverage.domain.Beverage;
import thefaco.beverage.repository.BeverageRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BeverageServiceImpl implements BeverageService{

    private final BeverageRepository beverageRepository;

    //새로운 음료 저장
    @Override
    @Transactional
    public void save(Beverage beverage) {
        beverageRepository.save(beverage);
    }

    @Override
    public List<Beverage> findBeverages() {
        return beverageRepository.findAll();
    }

    @Override
    public Beverage findOne(Long beverageId) {
        return beverageRepository.findOne(beverageId);
    }
}
