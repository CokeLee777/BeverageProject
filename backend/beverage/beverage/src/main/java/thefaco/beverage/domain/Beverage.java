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
    private Long id;

    private String name;
    private int price;
    private String type;
    private int size;

    @Override
    public String toString() {
        return "음료 " +
                "이름은'" + name + '\'' +
                ", 가격은'" + price + "원" + '\'' +
                ", 종류는'" + type + '\'' +
                ", 크기는'" + size + '\'' + "입니다" +
                ' ';
    }
}
