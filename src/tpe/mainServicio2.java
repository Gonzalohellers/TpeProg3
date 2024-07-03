package tpe;

import java.util.Iterator;
import java.util.List;

public class mainServicio2 {
    public static void main(String[] args) {
        servicioParte2 servicio = new servicioParte2("./src/tpe/datasets/Procesadores.csv", "./src/tpe/datasets/Tareas.csv", 122);
        Solucion solucion = servicio.Backtracking();
        System.out.println("Backtracking:");
        List<Procesador> it=solucion.getResultado();
        for(Procesador resultado:it){
            System.out.println("Procesador "+resultado.getId()+" [ ");
            List<Tarea> tareasAsignadas=resultado.getTareas();
            for(Tarea tarea:tareasAsignadas){
                System.out.println(tarea+" , ");
            }
            System.out.print("] ");
            System.out.println(" ");
        }
        System.out.println("");
        System.out.println("tiempo Maximo de procesador " + solucion.getTiempoMinimo());
        System.out.println("Estados generados " + solucion.getEstadosGenerados());
        System.out.println("");

        System.out.println("Greedy:");
        Solucion solucion1=servicio.Greedy();
        List<Procesador> it1=solucion1.getResultado();
        for(Procesador resultado:it1){
            System.out.println("Procesador "+resultado.getId()+" [ ");
                List<Tarea> tareasAsignadas=resultado.getTareas();
            for(Tarea tarea:tareasAsignadas){
                System.out.print(tarea+" , ");
            }
            System.out.print("] ");
            System.out.println(" ");
            }
        System.out.println(" ");
        System.out.println("tiempo Maximo de procesador "+solucion1.getTiempoMinimo());
        System.out.println("Cantidad de candidatos considerados " + solucion1.getEstadosGenerados());
    }


    }

