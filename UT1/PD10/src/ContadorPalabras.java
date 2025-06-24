import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContadorPalabras {

    public static String[] palabrasComunes(String[] palabras1, String[] palabras2) {
        List<String> comunes = new ArrayList<>();
        for (String p1 : palabras1) {
            for (String p2 : palabras2) {
                if (p1.equals(p2) && !comunes.contains(p1)) {
                    comunes.add(p1);
                }
            }
        }
        return comunes.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String[] Ar1 = { "Hola", "mundo", "de", "los", "algoritmos" };
        String[] Ar2 = { "Hola", "mundo", "de", "la", "Formula 1" };
        String[] resultado = palabrasComunes(Ar1, Ar2);
        System.out.println(Arrays.toString(resultado));
    }
}


//El método palabrasComunes recibe dos arreglos de cadenas y devuelve un nuevo arreglo con las palabras
// que aparecen en ambos, sin duplicados y respetando el orden de la primera aparición.
//
//Se recorre cada palabra del primer arreglo y, para cada una, todas las del segundo usando bucles
// for. Cuando coinciden y aún no está en la lista de resultados, se añade.