package PD7;

public class Tarea {
    private String codigo;
    private int tiempo;

    public Tarea(String codigo, int tiempo) {
        this.codigo = codigo;
        this.tiempo = tiempo;
    }

    public String getCodigo() {
        return codigo;
    }

    public int getTiempo() {
        return tiempo;
    }

    @Override
    public String toString() {
        return codigo;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Tarea tarea = (Tarea) obj;
        return codigo.equals(tarea.codigo);
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}