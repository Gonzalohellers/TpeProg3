package tpe;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class csvReader {

    public List<Tarea> obtenerTareas(String csvPath){
        String line = "";
        List<Tarea> Tareas = new ArrayList<>();
        try(BufferedReader br=new BufferedReader(new FileReader(csvPath))){
            while((line=br.readLine())!=null){
                String[] items=line.split(",");
                if(!items[0].equals("")){

                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return Tareas;
    }

}
