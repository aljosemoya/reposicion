package com.reposicion.reposicion.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.reposicion.reposicion.servicios.impl.SimulacionServiceImpl;

@RestController
@RequestMapping("/api/simulacion")
public class SimulacionController {
    
    @Autowired
    private SimulacionServiceImpl simulacionServiceImpl;
    
    @GetMapping("/simular")
    public String simular(){
        
        return this.simulacionServiceImpl.simularPartidos();
    }

}
