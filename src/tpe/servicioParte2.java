package tpe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
/*
* Ahora se desea establecer una asignación de todas las tareas a los distintos procesadores con el
objetivo de minimizar el tiempo final de ejecución. Establecemos el tiempo final de ejecución como el
tiempo máximo de ejecución de un procesador una vez que todas las tareas fueron asignadas.
Además, se sabe que tenemos ciertas restricciones para asignar una tarea a un procesador:
● Primero, ningún procesador podrá ejecutar 2 tareas críticas de forma consecutiva.
● Segundo, los procesadores no refrigerados no podrán dedicar más de X tiempo de ejecución a
las tareas asignadas. El tiempo X será un parámetro establecido por el usuario al momento de
solicitar la asignación de las tareas a los procesadores.
* */
public class servicioParte2 extends Servicios{
    private int tiempoMaximoCpuNoRefrigerado;
    private Solucion solucion;
    private Solucion solucion1;
    private Estado estado;
/*
    La complejidad del constructor de la superclase (Servicios) es O(n + m), como mencionamos anteriormente.
    La creación de una instancia de Solucion es O(1).
*/

    public servicioParte2(String pathProcesadores, String pathTareas) {
        super(pathProcesadores, pathTareas);
        solucion = new Solucion();
        tiempoMaximoCpuNoRefrigerado = 0;
    }

    public servicioParte2(String pathProcesadores, String pathTareas, int tiempoMaximoCpuNoRefrigerado) {
        super(pathProcesadores, pathTareas);
        solucion = new Solucion();
        this.tiempoMaximoCpuNoRefrigerado = tiempoMaximoCpuNoRefrigerado;
    }

    private Estado generarEstado() {
        List<Tarea> tareasNuevo=new ArrayList<Tarea>();
        return new Estado(tareas, procesadores);
    }

    /*
    *Backtracking: Si no hay tareas pendientes y el tiempo calculado es menor que el tiempo mínimo registrado,
    * se actualiza la solución. Si aún hay tareas, se toma la primera tarea y se intenta asignar a cada procesador.
    *  Si un procesador puede tomar la tarea sin exceder el tiempo máximo permitido, se asigna y se llama
    * recursivamente al método . Si esta llamada recursiva encuentra una solución, se devuelve.
    *  Si no, se remueve la tarea del procesador y se restaura al inicio de la lista para intentar con otro
    * procesador.*/

    public Solucion Backtracking() {
        this.estado = generarEstado();
        this.solucion = new Solucion(); // Inicializamos la solución
        solucionBacktracking();
        return solucion;
    }

    private void solucionBacktracking() {
        solucion.incrementarEstado();

        if (estado.recorriTareas()) {
            int tiempoActual = estado.tiempoFinalEjecucion();
            if (tiempoActual < solucion.getTiempoMinimo() || solucion.getTiempoMinimo() == 0) {
                solucion.setTiempoMinimo(tiempoActual);
                solucion.aniadir(estado.solucionado());
            }
        } else {
            Tarea tareaActual = estado.getTarea();
            for (int i = 0; i < estado.procesadoresSize(); i++) {
                Procesador p = estado.getProcesador(i);
                if (p.puedeAsignar(tareaActual, tiempoMaximoCpuNoRefrigerado)) {
                    p.asignarTarea(tareaActual);
                    estado.incrementarIndice();
                    solucionBacktracking(); // Llamada recursiva sin modificar el índice de tarea
                    p.removerTarea(tareaActual);
                    estado.decrementarIndice();
                }
            }
        }
    }

/*
    //Solucion implementada con Greedy.
/* Para cada tarea, se intenta asignarla al primer procesador que pueda tomarla sin exceder el tiempo máximo
permitido.
Este proceso es greedy porque toma la primera opción viable sin considerar las consecuencias a largo plazo,
buscando una solución rápida y sencilla.
Una vez que todas las tareas han sido asignadas o rechazadas, se calcula el tiempo máximo que tomó completar todas
las tareas, se actualiza el tiempo mínimo en la solución y se añade el estado actual como parte de la solución.
Finalmente, se incrementa el estado de la solución para reflejar que se ha encontrado una solución viable.*/

    public Solucion Greedy() {
        this.estado = generarEstado();
        this.solucion1 = new Solucion();
        obtenerSolucionGreedy();
        return solucion1;
    }

    private void obtenerSolucionGreedy() {
        // Ordenar las tareas por duración
        ordenar(estado.getTareas());

        int estados = 0;

        // Mientras haya tareas por asignar
        while (!estado.tareasEmpty()) {
            // Obtener la primera tarea (la de menor duración)
            Tarea t = estado.eliminarTarea(0);

            // Encontrar el procesador con el menor tiempo de ejecución acumulado que pueda aceptar la tarea
            Procesador mejorProcesador = null;
            int mejorTiempoEjecucion = Integer.MAX_VALUE;

            for (int i = 0; i < estado.procesadoresSize(); i++) {
                estados++;
                Procesador p = estado.getProcesador(i);

                if (p.puedeAsignar(t, tiempoMaximoCpuNoRefrigerado) && p.getTiempoActual() < mejorTiempoEjecucion) {
                    mejorProcesador = p;
                    mejorTiempoEjecucion = p.getTiempoActual();
                }
            }

            // Asignar la tarea al mejor procesador encontrado
            if (mejorProcesador != null) {
                mejorProcesador.asignarTarea(t);
            } else {
                // Si no se puede asignar la tarea, manejar el error adecuadamente
                throw new IllegalStateException("No se pudo asignar la tarea: " + t);
            }
        }

        // Calcular el tiempo final de ejecución
        int tiempoActual = estado.tiempoFinalEjecucion();
        System.out.println("Tiempo actual " + tiempoActual);
        solucion1.setTiempoMinimo(tiempoActual);
        solucion1.aniadir(estado.solucionado());
        solucion1.setEstadosGenerados(estados);
    }




    public void ordenar(List<Tarea> tareas){
        Collections.sort(tareas, new comparadorPorDuracion());
    }

}
