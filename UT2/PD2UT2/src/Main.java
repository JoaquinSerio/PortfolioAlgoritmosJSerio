public class Main {

    public static long factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static int sumaLineal(int[] A, int n) {
        if (n <= 0) {
            return 0;
        }
        return A[n - 1] + sumaLineal(A, n - 1);
    }

    public static double potencia(double x, int exp) {
        if (exp == 0) {
            return 1;
        }
        if (exp < 0) {
            return 1.0 / potencia(x, -exp);
        }
        return x * potencia(x, exp - 1);
    }

    public static void invertir(int[] A, int i, int j) {
        if (A == null || i >= j || i < 0 || j >= A.length) {
            return;
        }
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
        invertir(A, i + 1, j - 1);
    }

    public static void main(String[] args) {
        // Ejercicio 1: factorial
        System.out.println(factorial(4));
        System.out.println(factorial(5));
        System.out.println(factorial(0));
        // Ejercicio 2: sumaLineal
        int[] V1 = {1, 2, 3, 4, 5};
        System.out.println(sumaLineal(V1, V1.length));
        System.out.println(sumaLineal(V1, -1));
        System.out.println(sumaLineal(new int[0], 0));
        // Ejercicio 3: potencia
        System.out.println(potencia(2, 3));
        System.out.println(potencia(2, 0));
        System.out.println(potencia(2, -2));
        System.out.println(potencia(1.5, 2));
        // Ejercicio 4: invertir
        int[] V2 = {1, 2, 3, 4, 5};
        invertir(V2, 0, V2.length - 1);
        printArray(V2);
        invertir(new int[0], 0, -1);
        int[] V3 = {42};
        invertir(V3, 0, 0);
    }

    private static void printArray(int[] A) {
        for (int x : A) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
