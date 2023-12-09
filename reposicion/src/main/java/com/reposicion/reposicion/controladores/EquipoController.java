package com.reposicion.reposicion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reposicion.reposicion.modelos.Equipo;
import com.reposicion.reposicion.modelos.EquipoDTO;
import com.reposicion.reposicion.servicios.impl.EquipoServiceImpl;

@RestController
@RequestMapping("/api/equipos")

public class EquipoController {
    
    @Autowired
    private EquipoServiceImpl equipoServiceImpl;

    @PostMapping("/crear")
    public Equipo crearEquipo(EquipoDTO equipo){
        Equipo equipo2 = new Equipo();

        equipo2.setNombre(equipo.getNombre());
        equipo2.setDefensa(equipo.getDefensa());
        equipo2.setAtaque(equipo.getAtaque());

        return this.equipoServiceImpl.crearEquipo(equipo2);
    }

    @DeleteMapping("/eliminar/{codigoEquipo}")
    public String eliminarEquipo(@PathVariable(name="codigoEquipo") int codigoEquipo){
        return this.equipoServiceImpl.eliminarEquipo(codigoEquipo);    
    }

    @GetMapping("/buscar/{codigoEquipo}")
    public Equipo buscarEquipo(@PathVariable(name="codigoEquipo") int codigoEquipo){
        return this.equipoServiceImpl.buscarEquipo(codigoEquipo);
    }
}
