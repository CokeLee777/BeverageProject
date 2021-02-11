package thefaco.beverage.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class Beverage {

    @Id @GeneratedValue
    private Long id;

    private String name;

    private int price;

    private String type;

    private int size;
}
