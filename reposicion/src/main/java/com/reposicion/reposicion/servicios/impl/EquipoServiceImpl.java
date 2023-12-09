package com.reposicion.reposicion.servicios.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reposicion.reposicion.modelos.Equipo;
import com.reposicion.reposicion.repositorios.EquipoRepository;
import com.reposicion.reposicion.servicios.EquipoService;

@Service
public class EquipoServiceImpl implements EquipoService {

    @Autowired
    private EquipoRepository equipoRepository;

    @Override
    public Equipo crearEquipo(Equipo equipo) {
        return this.equipoRepository.save(equipo);
    }

    @Override
    public String eliminarEquipo(int codigoEquipo) {
        Equipo equipoEliminar = this.equipoRepository.findById(codigoEquipo).get();

        if(equipoEliminar!=null){
            this.equipoRepository.delete(equipoEliminar);
            return "Equipo eliminado con exito";
        }

        return "Equipo no ha sido encontrado o eliminado";
    }

    @Override
    public Equipo buscarEquipo(int codigoEquipo) {
        return this.equipoRepository.findById(codigoEquipo).get();
    }
    
}
