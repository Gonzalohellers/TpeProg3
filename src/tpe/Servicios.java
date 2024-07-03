package tpe;

import java.util.*;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {

	private String csvTareas;
	protected List<Tarea> tareas;
	Hashtable<String, Tarea> tareasHash = new Hashtable<>();
	protected List<Procesador> procesadores;
	private csvReader CSVReader;
	private List<Tarea> listaPrioridades=new ArrayList<>();

	/*
      Expresar la complejidad temporal del constructor.

       Asignación de atributos: O(1)
   	   Creación de instancia csvReader: O(1)
	   Obtención de tareas: O(n) donde n es el número de tareas en la lista de tareas.
	   Invocación de generarTareasHash(): O(n)
	   Pasar tareas sin ordenar a lista de prioridades o(n)
	   ordenar lista de prioridades o(n) Lo hace el mecanimos sort de la clase collections
	   Obtención de procesadores: O(m) donde m es el número de procesadores en la lista de procesadores.
	   En general, la complejidad del constructor Servicios es O(n + m).
     */
	public Servicios(String pathProcesadores, String pathTareas)
	{
		this.csvTareas = pathTareas;
		this.CSVReader=new csvReader(pathTareas, pathProcesadores);
		this.tareas=csvReader.obtenerTareas();
		generarPrioridadesList();
		generarTareasHash();
		this.procesadores=csvReader.obtenerProcesadores();
	}

	public void generarTareasHash(){
		for(int i=0; i<this.tareas.size(); i++){
			Tarea actual=this.tareas.get(i);
			tareasHash.put(actual.getId(), actual);
		}
	}

	public void generarPrioridadesList(){
		listaPrioridades.clear();
		listaPrioridades.addAll(this.tareas);
		Collections.sort(listaPrioridades, Comparator.comparingInt(Tarea::getNivelPrioridad));
	}


	/*
     * Expresar la complejidad temporal del servicio 1.
     *
     * O(1). Solo debe realizar un acceso
     */
	public Tarea servicio1(String ID) {
		if (tareasHash.containsKey(ID)) {
			return tareasHash.get(ID);}
		else{
			return null;
		}

	}
    
    /*
     * Expresar la complejidad temporal del servicio 2.
     *
     *O(n) en el peor de los casos debemos recorrer toda la lista
     */
	public List<Tarea> servicio2(boolean esCritica) {
		List<Tarea> lista=new ArrayList<>();
		for(Tarea t:this.tareas){
			if(t.isEsCritica()==esCritica){
				lista.add(t);
			}
		}
		return lista;
	}

    /*
     * Expresar la complejidad temporal del servicio 3.
     *O(n) en el peor de los casos debemos recorrer toda la lista, aunque al estar ordenados de menor a mayor,
     * dificilmente llegamos a n
     */
	public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
		List<Tarea> lista = new ArrayList<>();
		int size = this.listaPrioridades.size();  // Almacena el tamaño de la lista de prioridades


		for (int pos = 0; pos < size; pos++) {
			Tarea t = this.listaPrioridades.get(pos);
			if (t.getNivelPrioridad() > prioridadInferior && t.getNivelPrioridad()<prioridadSuperior) {
				lista.add(t);
			}
			else if (t.getNivelPrioridad() >= prioridadSuperior) {
				break; // Detenemos el bucle
			}
		}
		return lista;
	}
}
