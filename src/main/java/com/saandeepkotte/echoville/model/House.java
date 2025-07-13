package com.saandeepkotte.echoville.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class House extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "house_id")
    private Long id;

    private String block;
    private String floor;
    private String number;
    private String type; // 2BHK, etc.

    @ManyToOne
    @JoinColumn(name = "community_id")
    private Community community;

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<EchoUser> residents = new ArrayList<>();

    @OneToMany(mappedBy = "house", cascade = CascadeType.ALL)
    private List<Bill> bills = new ArrayList<>();
}

