public class Alumno {
    private String nombre;

    public Alumno() {
        nombre = "";
    }

    public String getNombreAdmiracion() {
        return nombre.concat("!");
    }

    public static void main(String[] args) {
        Alumno alumno = new Alumno();
        System.out.println(alumno.getNombreAdmiracion());
    }
}

//El error es que nombre queda en null y null.concat("!") lanza NullPointerException
//Para solcuonarlo hay que iniciar nombre como una cadena vacia
