package interfacesYUtilTA2;

public class DemoConjunto {
    public static void main(String[] args) {
        Conjunto<TAlumno> curso1 = new Conjunto<>();
        Conjunto<TAlumno> curso2 = new Conjunto<>();
        TAlumno a1 = new TAlumno(1234, "Ana", "Pérez");
        TAlumno a2 = new TAlumno(2345, "Luis", "Gómez");
        TAlumno a3 = new TAlumno(3456, "María", "López");
        curso1.insertar(a1.getCedula(), a1);
        curso1.insertar(a2.getCedula(), a2);
        curso2.insertar(a2.getCedula(), a2);
        curso2.insertar(a3.getCedula(), a3);

        System.out.println("Matriculados en alguno:");
        System.out.println(curso1.union(curso2).imprimir(", "));

        System.out.println("Matriculados en ambos:");
        System.out.println(curso1.interseccion(curso2).imprimir(", "));
    }
}