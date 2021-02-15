package thefaco.beverage.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BeverageForm {

    private Long id;

    private String name;
    private int price;
    private int size;
    private String type;
}
