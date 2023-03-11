package com.example.lab2.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ordr")
public class OrderEntity {
    @Id
    private int id;
    private LocalDate date;
    private String status;
    @OneToMany(cascade = CascadeType.REMOVE,mappedBy = "order",fetch = FetchType.EAGER)
    private List<PositionEntity> positionList;
    public double getWeightOfOrder(){
        Double weight = 0.0;
        for(PositionEntity position : positionList){
            weight += position.getWeight();
        }
        return weight;
    }

}
