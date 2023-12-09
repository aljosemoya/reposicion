package com.reposicion.reposicion.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reposicion.reposicion.modelos.Posicion;
import com.reposicion.reposicion.servicios.impl.PosicionServiceImpl;

@RestController
@RequestMapping("/api/posiciones")
public class PosicionController {
    
    @Autowired
    private PosicionServiceImpl posicionServiceImpl;

    @GetMapping("/obtener/todos")
    public List<Posicion> obtenerTodos(){
        return this.posicionServiceImpl.obtenerPosiciones();
    }
}
