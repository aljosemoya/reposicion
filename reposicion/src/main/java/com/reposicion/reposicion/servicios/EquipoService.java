package com.reposicion.reposicion.servicios;

import com.reposicion.reposicion.modelos.Equipo;

public interface EquipoService {
    
    public Equipo crearEquipo(Equipo equipo);

    public String eliminarEquipo(int codigoEquipo);

    public Equipo buscarEquipo(int codigoEquipo);

    
}
