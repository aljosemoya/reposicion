package com.reposicion.reposicion.servicios.impl;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reposicion.reposicion.modelos.Equipo;
import com.reposicion.reposicion.modelos.Posicion;
import com.reposicion.reposicion.repositorios.EquipoRepository;
import com.reposicion.reposicion.repositorios.PosicionRepository;
import com.reposicion.reposicion.servicios.SimulacionService;

@Service
public class SimulacionServiceImpl implements SimulacionService {
    @Autowired
    private EquipoRepository equipoRepository;

    @Autowired
    private PosicionRepository posicionRepository;

    private static final List<String> NOMBRES_EQUIPOS = List.of(
            "Leones", "Tigres", "Águilas", "Tiburones", "Cocodrilos", "Jaguares", "Osos", "Halcones", "Panteras",
            "Lobos");

    public String simularPartidos() {
        List<Equipo> equipos = equipoRepository.findAll();

        // Verificar que hay al menos 6 equipos
        if (equipos.size() < 6) {
            // Crea nuevos equipos si no hay suficientes
            for (int i = 0; i < (6 - equipos.size()); i++) {
                Equipo equipo = new Equipo();

                equipo.setNombre(generarNombreEquipoAleatorio());
                equipo.setAtaque(generarValorDoubleAleatorio());
                equipo.setDefensa(generarValorDoubleAleatorio());

                this.equipoRepository.save(equipo);
            }

        }

        // Simular partidos
        for (int i = 0; i < equipos.size(); i++) {
            Equipo equipoA = equipos.get(i);

            for (int j = i + 1; j < equipos.size(); j++) {
                Equipo equipoB = equipos.get(j);

                int golesEquipoA = generarGolesAleatorios();
                int golesEquipoB = generarGolesAleatorios();

                // Actualizar posiciones
                actualizarPosiciones(equipoA, equipoB, golesEquipoA, golesEquipoB);
            }
        }

        return "Simulacion hecha con exito";
    }

    private int generarGolesAleatorios() {
        // Lógica para generar un número aleatorio de goles
        Random random = new Random();
        return random.nextInt(5); // Cambia el rango según tus necesidades
    }

    private void actualizarPosiciones(Equipo equipoA, Equipo equipoB, int golesEquipoA, int golesEquipoB) {
        // Obtener o crear registros de posiciones para los equipos
        Posicion posicionEquipoA = obtePosicion(equipoA.getCodigoEquipo());
        Posicion posicionEquipoB = obtePosicion(equipoB.getCodigoEquipo());

        Posicion posicionNueva = new Posicion();

        

        if(posicionEquipoA==null){
            posicionNueva.setCodigoEquipo(equipoA);
            posicionNueva.setEmpates(0);
            posicionNueva.setGanados(0);
            posicionNueva.setGolesContra(0);
            posicionNueva.setGolesFavor(0);
            posicionNueva.setPuntos(0);

            this.posicionRepository.save(posicionNueva);

        }else if(posicionEquipoB==null){
            posicionNueva.setCodigoEquipo(equipoB);
            posicionNueva.setEmpates(0);
            posicionNueva.setGanados(0);
            posicionNueva.setGolesContra(0);
            posicionNueva.setGolesFavor(0);
            posicionNueva.setPuntos(0);

            this.posicionRepository.save(posicionNueva);
        }

        posicionEquipoA = obtePosicion(equipoA.getCodigoEquipo());
        posicionEquipoB = obtePosicion(equipoB.getCodigoEquipo());
        // Lógica para actualizar posiciones según el resultado del partido
        if (golesEquipoA > golesEquipoB) {
            posicionEquipoA.setPuntos(posicionEquipoA.getPuntos() + 3);
            posicionEquipoA.setGolesFavor(posicionEquipoA.getGolesFavor() + golesEquipoA);
            posicionEquipoA.setGolesContra(posicionEquipoA.getGolesContra() + golesEquipoB);
            posicionEquipoA.setGanados(posicionEquipoA.getGanados() + 1);

            posicionEquipoB.setGolesFavor(posicionEquipoB.getGolesFavor() + golesEquipoB);
            posicionEquipoB.setGolesContra(posicionEquipoB.getGolesContra() + golesEquipoA);
            posicionEquipoB.setPerdidos(posicionEquipoB.getPerdidos() + 1);
        } else if (golesEquipoA < golesEquipoB) {

            posicionEquipoB.setPuntos(posicionEquipoB.getPuntos() + 3);
            posicionEquipoB.setGolesFavor(posicionEquipoB.getGolesFavor() + golesEquipoB);
            posicionEquipoB.setGolesContra(posicionEquipoB.getGolesContra() + golesEquipoA);
            posicionEquipoB.setGanados(posicionEquipoB.getGanados() + 1);

            posicionEquipoA.setGolesFavor(posicionEquipoA.getGolesFavor() + golesEquipoA);
            posicionEquipoA.setGolesContra(posicionEquipoA.getGolesContra() + golesEquipoB);
            posicionEquipoA.setPerdidos(posicionEquipoA.getPerdidos() + 1);

        } else {
            posicionEquipoA.setPuntos(posicionEquipoA.getPuntos() + 1);
            posicionEquipoA.setGolesFavor(posicionEquipoA.getGolesFavor() + golesEquipoA);
            posicionEquipoA.setGolesContra(posicionEquipoA.getGolesContra() + golesEquipoB);
            
            posicionEquipoB.setPuntos(posicionEquipoB.getPuntos() + 1);
            posicionEquipoB.setGolesFavor(posicionEquipoB.getGolesFavor() + golesEquipoB);
            posicionEquipoB.setGolesContra(posicionEquipoB.getGolesContra() + golesEquipoA);
        }
        // ...

        // Guardar o actualizar los registros de posiciones en la base de datos
        posicionRepository.save(posicionEquipoA);
        posicionRepository.save(posicionEquipoB);

        
    }

    public static String generarNombreEquipoAleatorio() {
        Random random = new Random();
        int indiceAleatorio = random.nextInt(NOMBRES_EQUIPOS.size());
        return NOMBRES_EQUIPOS.get(indiceAleatorio);
    }

    public static double generarValorDoubleAleatorio() {
        double rangoMinimo = 1.0;
        double rangoMaximo = 10.0;
        Random random = new Random();
        return rangoMinimo + (rangoMaximo - rangoMinimo) * random.nextDouble();
    }

    private Posicion obtePosicion(int codigoEquipo) {
        List<Posicion> posiciones = this.posicionRepository.findAll();
        Posicion posicionBuscada = new Posicion();

        
        for (Posicion posicion : posiciones) {
            if (posicion.getCodigoEquipo().getCodigoEquipo() == codigoEquipo) {
                posicionBuscada = posicion;
            }
        }
        
        return posicionBuscada;
    }
}
