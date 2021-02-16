package thefaco.beverage.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import thefaco.beverage.domain.Beverage;

import javax.persistence.*;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BeverageRepositoryImpl implements BeverageRepository {

//    @PersistenceContext
    private final EntityManager em;

    @Override
    public void save(Beverage beverage){
        if(beverage.getId() == null){
            em.persist(beverage);
        } else {
            em.merge(beverage);
        }

    }

    @Override
    public void delete(Beverage beverage) {
        em.remove(beverage);
    }

    @Override
    public Beverage findOne(Long id){
        return em.find(Beverage.class, id);
    }

    @Override
    public List<Beverage> findAll() {
        return em.createQuery("select b from Beverage b", Beverage.class)
                .getResultList();
    }

    @Override
    public List<Beverage> findByName(String name) {
        return em.createQuery("select b from Beverage b where b.name = :name", Beverage.class)
                .setParameter("name", name)
                .getResultList();
    }

}
