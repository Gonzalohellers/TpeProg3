package tpe;

import java.util.ArrayList;
import java.util.List;

public class Estado {
    private List<Tarea> tareas=new ArrayList<>();
    private int indice;
    private List<Procesador> procesadores=new ArrayList<>();

    public Estado(List<Tarea> tareas, List<Procesador> procesadores) {
       for(Tarea tarea : tareas){
            this.tareas.add(new Tarea(tarea));
       }
       for(Procesador procesador : procesadores){
           this.procesadores.add(new Procesador(procesador));
       }
       indice=0;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public Tarea getTarea(){
        return tareas.get(indice);
    }

    public void decrementarIndice(){
        if(indice>0){
        this.indice--;}
    }

    public boolean recorriTareas(){
        return indice==tareas.size();
    }

    public void incrementarIndice(){
        if(indice<tareas.size()){
        this.indice++;}
    }

    public boolean tareasEmpty() {
        return tareas.isEmpty();
    }

    public Tarea eliminarTarea(int index) {
        return tareas.remove(index);
    }

    public void reasignar(Tarea t) {
        tareas.add(0, t);
    }

    public int procesadoresSize() {
        return procesadores.size();
    }

    public Procesador getProcesador(int index) {
        return procesadores.get(index);
    }

    public int tiempoFinalEjecucion() {
        int maxTiempo = 0;
        for (Procesador p : procesadores) {
            if (p.getTiempoActual() > maxTiempo) {
                maxTiempo = p.getTiempoActual();
            }
        }

        return maxTiempo;
    }

    public List<Procesador> solucionado() {
        return procesadores;
    }
}