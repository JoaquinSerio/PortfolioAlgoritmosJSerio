public class Contador {
    private final int MAX_CONT = 50;
    private int incremento = 1;
    private int contador = 1;

    public void displayCount() {
        while (contador <= MAX_CONT) {
            System.out.println(contador);
            contador = contador + incremento;
        }
    }

    public void displayCountDoWhile() {
        contador = 1;
        do {
            System.out.println(contador);
            contador = contador + incremento;
        } while (contador <= MAX_CONT);
    }

    public void displayCountFor() {
        for (int contador = 1; contador <= MAX_CONT; contador = contador + incremento) {
            System.out.println(contador);
        }
    }

    public static void main(String[] args) {
        Contador c = new Contador();
        c.displayCount();
        c.displayCountDoWhile();
        c.displayCountFor();
    }
}
