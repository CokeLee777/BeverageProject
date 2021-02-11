package thefaco.beverage.domain;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Repository
public class BeverageRepositoryImpl implements BeverageRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long save(Beverage beverage){
        em.persist(beverage);
        return beverage.getId();
    }

    @Override
    public Beverage find(Long id){
        return em.find(Beverage.class, id);
    }

    @Override
    public void update(Long id, String name, int price, int size, String type) {
        Beverage findBeverage = em.find(Beverage.class, id);
        if(findBeverage != null){
            findBeverage.setName(name);
            findBeverage.setPrice(price);
            findBeverage.setSize(size);
            findBeverage.setType(type);
        }
    }
}
