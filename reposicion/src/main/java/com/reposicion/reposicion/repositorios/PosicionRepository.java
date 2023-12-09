package com.reposicion.reposicion.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;


import com.reposicion.reposicion.modelos.Posicion;

public interface PosicionRepository extends JpaRepository<Posicion, Integer> {
    
}
