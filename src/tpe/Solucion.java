package tpe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solucion {
    private List<Procesador> resultado;
    private int tiempoMinimo;
    private int estadosGenerados;

    public Solucion() {
        resultado = new ArrayList<>();
        tiempoMinimo = Integer.MAX_VALUE;
        estadosGenerados = 0;
    }

    public void aniadir(List<Procesador> solucionActual) {
        resultado.clear();
        for (Procesador p : solucionActual) {
            resultado.add(new Procesador(p));
        }
    }

    public void setEstadosGenerados(int generados){
        this.estadosGenerados = generados;
    }

    public void setTiempoMinimo(int tiempoActual) {
        this.tiempoMinimo = tiempoActual;
    }

    public int getTiempoMinimo() {
        return tiempoMinimo;
    }

    public void incrementarEstado() {
        this.estadosGenerados++;
    }

    public List<Procesador> getResultado() {
        return resultado;
    }

    public int getEstadosGenerados() {
        return this.estadosGenerados;
    }


}
