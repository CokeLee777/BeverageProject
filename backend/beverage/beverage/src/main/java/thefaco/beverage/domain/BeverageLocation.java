package thefaco.beverage.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "beverageLocation")
@Getter @Setter
@RequiredArgsConstructor
public class BeverageLocation {

    @Id @GeneratedValue
    private Long id;

    private String c1;
    private String c2;
    private String c3;
    private String c4;

}
