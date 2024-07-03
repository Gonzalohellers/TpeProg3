package tpe;

public class Tarea implements Comparable{
    private String Id;
    private String Nombre;
    private int tiempoEjecucion;
    private boolean esCritica;
    private int nivelPrioridad;

    public Tarea(String id, String nombre, int tiempoEjecucion, boolean esCritica, int nivelPrioridad) {
        Id = id;
        Nombre = nombre;
        this.tiempoEjecucion = tiempoEjecucion;
        this.esCritica = esCritica;
        this.nivelPrioridad = nivelPrioridad;
    }

    public Tarea(Tarea t){
        this.Id = t.Id;
        this.Nombre = t.Nombre;
        this.tiempoEjecucion = t.tiempoEjecucion;
        this.esCritica = t.esCritica;
        this.nivelPrioridad = t.nivelPrioridad;
    }

    public String getId() {
        return Id;
    }

    public int getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public boolean isEsCritica() {
        return esCritica;
    }

    public int getNivelPrioridad() {
        return nivelPrioridad;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "Id=" + Id +
                ", Nombre='" + Nombre + '\'' +
                ", tiempoEjecucion=" + tiempoEjecucion +
                ", esCritica=" + esCritica +
                ", nivelPrioridad=" + nivelPrioridad +
                '}';
    }

    @Override
    public int compareTo(Object o) {
        return this.nivelPrioridad>((Tarea)o).nivelPrioridad ? -1 : 1;
    }
}
