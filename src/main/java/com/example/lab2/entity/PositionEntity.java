package com.example.lab2.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "position")
public class PositionEntity {
    @Id
    private int id;
    private Double weight;
    @ManyToOne
    private ProviderEntity provider;
    @ManyToOne
    @JsonIgnore
    private OrderEntity order;
}
