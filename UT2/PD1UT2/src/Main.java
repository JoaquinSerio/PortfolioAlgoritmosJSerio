import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        // Leer todas las líneas del archivo
        java.util.List<String> lineas = Files.readAllLines(Paths.get("UT2", "UT2/PD2UT2", "src", "numeros.txt"));
        int N = Integer.parseInt(lineas.get(0));
        int[] arreglo = new int[N];
        for (int i = 0; i < N; i++) {
            arreglo[i] = Integer.parseInt(lineas.get(i + 1));
        }

        long contador = 0;
        for (int i = 1; i < N; i++) {
            for (int j = N - 1; j >= i; j--) {
                contador++;
                if (arreglo[j] < arreglo[j - 1]) {
                    int temp = arreglo[j];
                    arreglo[j] = arreglo[j - 1];
                    arreglo[j - 1] = temp;
                }
            }
        }

        System.out.println(N);
        System.out.println(contador);
        System.out.println(arreglo[0]);
        System.out.println(arreglo[N - 1]);
    }
}
