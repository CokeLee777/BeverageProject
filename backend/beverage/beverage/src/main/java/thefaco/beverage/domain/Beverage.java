package thefaco.beverage.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "beveragetest")
@Getter @Setter
public class Beverage {

    @Id @GeneratedValue
    @Column(name = "ROW")
    private Long id;

    @Column(name = "BEVERAGE_NAME")
    private String name;

    private int price;

    private String type;

    private int size;
}
