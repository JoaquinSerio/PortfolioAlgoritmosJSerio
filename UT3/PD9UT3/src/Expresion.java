
import java.util.List;
import java.util.Stack;

public class Expresion {
    /**
     * Verifica si la secuencia de llaves '{' y '}' en la lista de entrada está bien formada.
     * @param listaDeEntrada Lista de caracteres que contienen corchetes.
     * @return true si la secuencia está correctamente balanceada, false en caso contrario.
     */
    public static boolean controlCorchetes(List<Character> listaDeEntrada) {
        Stack<Character> pila = new Stack<>();
        for (Character c : listaDeEntrada) {
            if (c == '{') {
                pila.push(c);
            } else if (c == '}') {
                if (pila.isEmpty()) {
                    return false;
                }
                pila.pop();
            }
        }
        return pila.isEmpty();
    }

    public static void main(String[] args) {
        List<String> pruebas = List.of(
                "{}{{}}",
                "{{}{{}",
                "{}}",
                "",
                "{}{}{}}{"
        );
        for (String exp : pruebas) {
            List<Character> lista = exp.chars()
                    .mapToObj(c -> (char) c)
                    .toList();
            boolean correcto = controlCorchetes(lista);
            System.out.printf("Expresión '%s' -> %b%n", exp, correcto);
        }
    }
}
