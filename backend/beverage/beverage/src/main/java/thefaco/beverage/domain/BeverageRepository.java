package thefaco.beverage.domain;

public interface BeverageRepository {

    Long save(Beverage beverage);
    Beverage find(Long id);
    void update(Long id, String name, int price, int size, String type);

}
