
public class Main {
    public static void main(String[] args) {
        System.out.println(recorrer("hola como estas"));
        System.out.println(getValor());
        System.out.println(getPrimerCaracter("Hola"));
        System.out.println(paraAString(123));
    }

    public static int recorrer(String cadena) {
        int res = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) != ' ') {
                res++;
            }
        }
        return res;
    }

    //índice comienza en 1 y llega a length(), provocando StringIndexOutOfBoundsException
    //para corregirlo hay que iterar de 0 a < cadena.length()

    public static int getValor() {
        int vector[] = { 6, 16, 26, 36, 46, 56, 66, 76 };
        int idx = vector.length - 1;
        return vector[idx];
    }

    //Error: idx = 8 fuera de rango (vector tiene índices 0…7), lanza ArrayIndexOutOfBoundsException.
    //Corrección: usar índice válido, por ejemplo idx = vector.length - 1;

    public static char getPrimerCaracter(String palabra) {
        return palabra.charAt(0);
    }

    //Error: crea un array de String sin inicializar, string[1] es null → NullPointerException.
    //Corrección: devolver el primer carácter de la palabra recibida.


    public static String paraAString(int a) {
        return String.valueOf(a);
    }

    //Error: Object x1 = new Integer(a); (String)(x1) lanza ClassCastException.
    //Corrección: convertir el entero a cadena con String.valueOf(a).


}