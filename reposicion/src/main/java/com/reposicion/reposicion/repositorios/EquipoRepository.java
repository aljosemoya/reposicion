package com.reposicion.reposicion.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reposicion.reposicion.modelos.Equipo;

public interface EquipoRepository extends JpaRepository<Equipo, Integer> {
    
}
