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
    public void delete(BeverageLocation beverageLocation) {
        em.remove(beverageLocation);
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

    @Override
    public String findByName(String name) {
        List<BeverageLocation> findBeverageLocation = em.createQuery("select bl from BeverageLocation bl", BeverageLocation.class)
                .getResultList();
        String result = null;
        for (BeverageLocation beverageLocation : findBeverageLocation) {
            Long id = beverageLocation.getId();
            if(beverageLocation.getC1().equals(name)){
                result = id + "행 " + 1 + "열";
                break;
            } else if(beverageLocation.getC2().equals(name)) {
                result = id + "행 " + 2 + "열";
                break;
            } else if(beverageLocation.getC3().equals(name)){
                result = id + "행 " + 3 + "열";
                break;
            } else if(beverageLocation.getC4().equals(name)){
                result = id + "행 " + 4 + "열";
                break;
            } else {
                result = null;
            }
        }
        return result;
    }

}
