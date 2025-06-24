import java.util.*;

public class Ejercicio1 {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put("a", "valor1");
        map.put("b", null);
        map.put("c", "valor3");
        map.put("d", null);

        map.entrySet().removeIf(entry -> entry.getValue() == null);

        System.out.println(map);
    }
}

