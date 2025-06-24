import java.util.Objects;

public class Alumno extends Object {
    private int ID;
    private String fullName;
    private String email;

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Alumno alumno = (Alumno) obj;
        return ID == alumno.ID &&
                Objects.equals(fullName, alumno.fullName) &&
                Objects.equals(email, alumno.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, fullName, email);
    }
}


//Características necesarias:

//Usar todos los campos que participan en equals()
//Distribución uniforme de valores hash
//Eficiencia computacional
//Inmutabilidad respecto a campos usados en equals()
