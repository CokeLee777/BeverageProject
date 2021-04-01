package thefaco.beverage.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BeverageLocationJsonForm {

    private Long id;
    private String name;
    private Long row;
    private int column;
}
