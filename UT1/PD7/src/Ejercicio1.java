public class Ejercicio1 {
    public static void main(String[] args) {
        String s = "1";
        while (s.intern() != "1000") {
            s += "0";
        }
        System.out.println(s);
    }
}

