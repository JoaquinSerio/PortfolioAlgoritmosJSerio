import java.io.*;
import java.util.*;

public class Ejercicio4 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Uso: java Ejercicio4 <archivo> <numero_lineas>");
            return;
        }

        String nombreArchivo = args[0];
        int numLineasImprimir = Integer.parseInt(args[1]);

        try {
            File archivo = new File(nombreArchivo);
            long tamanoArchivo = archivo.length();
            int tamanoLineaPromedio = 50;
            int capacidadEstimada = (int) (tamanoArchivo / tamanoLineaPromedio) + 1;

            List<String> lineas = new ArrayList<>(capacidadEstimada);

            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                lineas.add(linea);
            }
            br.close();

            Random random = new Random();
            for (int i = 0; i < Math.min(numLineasImprimir, lineas.size()); i++) {
                int indiceAleatorio = random.nextInt(lineas.size());
                System.out.println(lineas.get(indiceAleatorio));
            }

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
