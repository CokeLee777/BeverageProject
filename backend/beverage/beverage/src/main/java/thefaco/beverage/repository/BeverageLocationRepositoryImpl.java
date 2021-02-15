package thefaco.beverage.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import thefaco.beverage.domain.BeverageLocation;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BeverageLocationRepositoryImpl implements BeverageLocationRepository{

    private final EntityManager em;

    @Override
    public void save(BeverageLocation beverageLocation) {
        if(beverageLocation.getId() == null){
            em.persist(beverageLocation);
        } else {
            em.merge(beverageLocation);
        }
    }

    @Override
    public BeverageLocation findOne(Long id) {
        return em.find(BeverageLocation.class, id);
    }

    @Override
    public List<BeverageLocation> findAll() {
        return em.createQuery("select bl from BeverageLocation bl", BeverageLocation.class)
                .getResultList();
    }

}
