package tpe;

import tpe.utils.CSVReader;

import java.util.ArrayList;
import java.util.List;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {

	/*
     * Expresar la complejidad temporal del constructor.
     */
	public Servicios(String pathProcesadores, String pathTareas)
	{
		CSVReader reader = new CSVReader();
		reader.readProcessors(pathProcesadores);
		reader.readTasks(pathTareas);
	}
	
	/*
     * Expresar la complejidad temporal del servicio 1.
     */
	public Tarea servicio1(String ID) {
		return new Tarea(1, "tarea", 5, false, 110);
	}
    
    /*
     * Expresar la complejidad temporal del servicio 2.
     */
	public List<Tarea> servicio2(boolean esCritica) {
		return new ArrayList<Tarea>();
	}

    /*
     * Expresar la complejidad temporal del servicio 3.
     */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		return new ArrayList<Tarea>();
	}

}
