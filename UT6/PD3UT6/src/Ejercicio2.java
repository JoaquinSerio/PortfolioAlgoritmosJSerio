import java.util.*;

public class Ejercicio2 {
    public static Map<String, String> intercambiarClavesValores(Map<String, String> mapa) {
        Map<String, String> resultado = new HashMap<>();
        Set<String> valoresVistos = new HashSet<>();

        for (Map.Entry<String, String> entry : mapa.entrySet()) {
            String valor = entry.getValue();
            if (valoresVistos.contains(valor)) {
                throw new IllegalArgumentException("Valor duplicado: " + valor);
            }
            valoresVistos.add(valor);
            resultado.put(valor, entry.getKey());
        }

        return resultado;
    }

    public static void main(String[] args) {
        Map<String, String> mapa = new HashMap<>();
        mapa.put("clave1", "valor1");
        mapa.put("clave2", "valor2");
        mapa.put("clave3", "valor3");

        Map<String, String> intercambiado = intercambiarClavesValores(mapa);
        System.out.println(intercambiado);
    }
}