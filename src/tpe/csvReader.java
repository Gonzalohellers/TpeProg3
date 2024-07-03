package tpe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class csvReader {
    private static String tarCsvPath;
    private static String procCsvPath;

    public csvReader(String tarCsvPath, String procCsvPath){
        this.procCsvPath=procCsvPath;
        this.tarCsvPath = tarCsvPath;
    }

    public static List<Tarea> obtenerTareas(){
        String line = "";
        List<Tarea> Tareas = new ArrayList<Tarea>();
        try(BufferedReader br=new BufferedReader(new FileReader(tarCsvPath))){
            while((line=br.readLine())!=null){
                String[] items=line.split(";");
                    boolean esUrgente = Boolean.parseBoolean(items[3].trim());
                    String idTarea = items[0].trim();
                    String nombreTarea = items[1].trim();
                    int duracion = Integer.parseInt(items[2].trim());
                    int prioridad = Integer.parseInt(items[4].trim());
                    Tareas.add(new Tarea(idTarea, nombreTarea, duracion, esUrgente, prioridad));

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return Tareas;
    }

    public static List<Procesador> obtenerProcesadores(){
        String line = "";
        List<Procesador> Procesadores = new ArrayList<>();
        try(BufferedReader br=new BufferedReader(new FileReader(procCsvPath))){
            while((line=br.readLine())!=null){
                String[] items=line.split(";");
                String idProcesador = items[0].trim();
                String nombreTarea = items[1].trim();
                boolean estaRefrigerado = Boolean.parseBoolean(items[2].trim());
                int anioFuncionamiento = Integer.parseInt(items[3].trim());
                Procesadores.add(new Procesador(idProcesador, nombreTarea, estaRefrigerado, anioFuncionamiento));

            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return Procesadores;
    }


}
