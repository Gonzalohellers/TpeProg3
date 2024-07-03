package tpe;

import java.util.ArrayList;
import java.util.List;

public class Procesador {
    private String id;
    private String codigo;
    private boolean refrigerado;
    private int capacidad;
    private int tiempoActual;
    private short tareasCriticas;

    private List<Tarea> asignadas=new ArrayList<>();

    public Procesador(String id, String codigo, boolean refrigerado, int capacidad) {
        this.id = id;
        this.codigo = codigo;
        this.refrigerado = refrigerado;
        this.capacidad = capacidad;
        this.tareasCriticas=0;
        this.tiempoActual = 0;
        this.asignadas=new ArrayList<Tarea>();
    }

    public Procesador(Procesador procesador) {
    this.id = procesador.id;
    this.codigo = procesador.codigo;
    this.refrigerado = procesador.refrigerado;
    this.capacidad = procesador.capacidad;
    this.tiempoActual = procesador.tiempoActual;
    this.tareasCriticas = procesador.tareasCriticas;
    this.asignadas.addAll(procesador.asignadas);
    }

    public void setTareasCriticas(short x){
        tareasCriticas=x;
    }

    public String getId() {
        return id;
    }

    public boolean puedeAsignar(Tarea tarea, int tiempoMaximoCpuNoRefrigerado) {
        if (!refrigerado && tarea.getTiempoEjecucion()>tiempoMaximoCpuNoRefrigerado) {
            return false;
        }
        if (tarea.isEsCritica()) {
            if(this.tareasCriticas==2) {
                return false;
            }
        }
        return true;
    }

    public void asignarTarea(Tarea tarea) {
        if(tarea.isEsCritica()){
            this.tareasCriticas+=1;
        }
        this.tiempoActual += tarea.getTiempoEjecucion();
        asignadas.add(tarea);
    }

    public List<Tarea> getTareas(){
        return asignadas;
    }


    public void removerTarea(Tarea t){
        if(this.asignadas.contains(t)) {
            this.asignadas.remove(t);
            if (t.isEsCritica()) {
                this.tareasCriticas--;
            }
            this.tiempoActual -= t.getTiempoEjecucion();
        }
    }

    public int getTiempoActual() {
        return tiempoActual;
    }

    public boolean isRefrigerado() {
        return refrigerado;
    }

}
