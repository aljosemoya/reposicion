package com.reposicion.reposicion.servicios.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reposicion.reposicion.modelos.Posicion;
import com.reposicion.reposicion.repositorios.PosicionRepository;
import com.reposicion.reposicion.servicios.PosicionService;

@Service
public class PosicionServiceImpl implements PosicionService {

    @Autowired
    private PosicionRepository posicionRepository;

    @Override
    public List<Posicion> obtenerPosiciones() {
        return this.posicionRepository.findAll();
    }

    
    
}
