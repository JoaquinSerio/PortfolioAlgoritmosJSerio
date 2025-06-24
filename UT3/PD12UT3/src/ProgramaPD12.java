
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ProgramaPD12 {
    public static void main(String[] args) throws IOException {
        String basePath = "C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT3\\PD12UT3\\src\\";
        // curso BasicoIng
        Conjunto<Alumno> basicoIng = new Conjunto<>();
        String[] ingLines = ManejadorArchivosGenerico.leerArchivo(basePath + "basico-ing.txt");
        for (String raw : ingLines) {
            if (raw == null || raw.isBlank()) continue;
            String[] parts = raw.split(",");
            int codigo = Integer.parseInt(parts[0].trim());
            String nombre = parts[1].trim();
            basicoIng.insertar(new Nodo<Alumno>(codigo, new Alumno(codigo, nombre)));        }
        // curso BasicoEmp
        Conjunto<Alumno> basicoEmp = new Conjunto<>();
        String[] empLines = ManejadorArchivosGenerico.leerArchivo(basePath + "basico-emp.txt");
        for (String raw : empLines) {
            if (raw == null || raw.isBlank()) continue;
            String[] parts = raw.split(",");
            int codigo = Integer.parseInt(parts[0].trim());
            String nombre = parts[1].trim();
            basicoEmp.insertar(new Nodo<Alumno>(codigo, new Alumno(codigo, nombre)));
        }
        // curso integrador101 = Union
        Conjunto<Alumno> integrador = (Conjunto<Alumno>) basicoIng.union(basicoEmp);
        guardarCurso(integrador, basePath + "integrador101.txt");
        // curso exigente102 = Interseccion
        Conjunto<Alumno> exigente = (Conjunto<Alumno>) basicoIng.interseccion(basicoEmp);
        guardarCurso(exigente, basePath + "exigente102.txt");
    }

    private static void guardarCurso(Conjunto<Alumno> curso, String filePath) throws IOException {
        List<Alumno> lista = new ArrayList<>();
        Nodo<Alumno> nodo = curso.getPrimero();
        while (nodo != null) {
            lista.add(nodo.getDato());
            nodo = nodo.getSiguiente();
        }
        // ordenar por codigo
        Collections.sort(lista, Comparator.comparingInt(Alumno::getCodigo));
        String[] lines = new String[lista.size()];
        for (int i = 0; i < lista.size(); i++) {
            Alumno a = lista.get(i);
            lines[i] = a.getCodigo() + "," + a.getNombre();
        }
        ManejadorArchivosGenerico.escribirArchivo(filePath, lines);
    }
}
