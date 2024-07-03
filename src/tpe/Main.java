package tpe;

import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String args[]) {
            Servicios servicios = new Servicios("./src/tpe/datasets/Procesadores.csv", "./src/tpe/datasets/Tareas.csv");
       String id="T3";
       System.out.println("Traer Tarea por id:");
        Tarea t3=servicios.servicio1(id);
        if(t3!=null){
            System.out.println(servicios.servicio1("T3"));
        }
        else{
            System.out.println("No se encontro "+id);
        }


        System.out.println("Traer Tarea teniendo en cuenta si es critica:");
          List<Tarea> tareasCriticas=servicios.servicio2(true);
          for(Tarea t:tareasCriticas){
               System.out.println(t);
           }


        System.out.println("Traer Tarea teniendo en cuenta la franja de prioridades:");
          List<Tarea> tareaPorPrioridad=servicios.servicio3(30, 60);
            for(Tarea t:tareaPorPrioridad){
                System.out.println(t);
            }

    }
}