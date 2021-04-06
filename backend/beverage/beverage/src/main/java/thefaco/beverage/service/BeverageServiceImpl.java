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

    //음료 수정
    @Override
    @Transactional
    public void update(Long beverageId, String name, int price, String type, int size) {
        Beverage findBeverage = beverageRepository.findOne(beverageId);
        findBeverage.setName(name);
        findBeverage.setPrice(price);
        findBeverage.setType(type);
        findBeverage.setSize(size);
    }
    //음료 삭제
    @Override
    @Transactional
    public void delete(Long beverageId) {
        Beverage beverage = beverageRepository.findOne(beverageId);
        //음료 삭제
        beverageRepository.delete(beverage);
    }

    @Override
    public List<Beverage> findBeverages() {
        return beverageRepository.findAll();
    }

    @Override
    public Beverage findOne(Long beverageId) {
        return beverageRepository.findOne(beverageId);
    }

    @Override
    public List<Beverage> findBeveragesByName(String name) {
        return beverageRepository.findByName(name);
    }
}
