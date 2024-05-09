package tpe;

public class Tarea {
    private int Id;
    private String Nombre;
    private int tiempoEjecucion;
    private boolean esCritica;
    private int nivelPrioridad;

    public Tarea(int id, String nombre, int tiempoEjecucion, boolean esCritica, int nivelPrioridad) {
        Id = id;
        Nombre = nombre;
        this.tiempoEjecucion = tiempoEjecucion;
        this.esCritica = esCritica;
        this.nivelPrioridad = nivelPrioridad;
    }
}
