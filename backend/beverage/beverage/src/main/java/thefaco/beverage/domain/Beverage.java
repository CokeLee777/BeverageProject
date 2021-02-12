package thefaco.beverage.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@RequiredArgsConstructor
public class Beverage {

    @Id @GeneratedValue
    @Column(name = "beverage_id")
    private Long id;

    private String name;

    private int price;

    @Enumerated(EnumType.STRING)
    private BeverageType type;

    private int size;

    //==음료 생성 메서드==//
}
