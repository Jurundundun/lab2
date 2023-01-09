package com.example.lab2.entity;

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
@Table(name = "provider")
public class ProviderEntity {
    @Id
    private int id;
    private String address;
    private String phoneNumber;
    private String productName;
}
