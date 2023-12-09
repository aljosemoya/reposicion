package com.reposicion.reposicion.modelos;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="posiciones")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter


public class Posicion {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="codigoequipo", referencedColumnName = "codigoequipo")
    private Equipo codigoEquipo;
    
    private int empates;

    private int ganados;

    private int perdidos;

    @Column(name="golesfavor")
    private int golesFavor;

    @Column(name="golescontra")
    private int golesContra;

    private int puntos;
}