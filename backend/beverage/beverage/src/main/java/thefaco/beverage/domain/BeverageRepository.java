package thefaco.beverage.domain;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BeverageRepository {

    @PersistenceContext
    private EntityManager em;

    public Long save(Beverage beverage){
        em.persist(beverage);
        return beverage.getId();
    }

    public Beverage find(Long id){
        return em.find(Beverage.class, id);
    }
}
