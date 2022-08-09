package uz.pdp.appnewssitegit.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.appnewssitegit.entity.enums.Huquq;
import uz.pdp.appnewssitegit.entity.template.AbstractEntity;

import javax.persistence.*;
import java.util.List;


@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Lavozim extends AbstractEntity {

    @Column(unique = true,nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @ElementCollection
    private List<Huquq> huquqList;

    @Column(length = 500)
    private String description;




}
