public class Main {

    public static int factorial(int num) {
        if (num < 0) throw new IllegalArgumentException();
        int resultado = 1;
        for (int i = 1; i <= num; i++) {
            resultado *= i;
        }
        return resultado;
    }

    public static boolean esPrimo(long n) {
        if (n == 2) return true;
        if (n < 2 || n % 2 == 0) return false;
        for (long i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) return false;
        }
        return true;
    }

    public static int sumaParesHasta(int n) {
        int suma = 0;
        int i = 0;
        while (i <= n) {
            if (i % 2 == 0) suma += i;
            i++;
        }
        return suma;
    }

    public static int sumaImparesHasta(int n) {
        int suma = 0;
        int i = 0;
        while (i <= n) {
            if (i % 2 != 0) suma += i;
            i++;
        }
        return suma;
    }

    public static void main(String[] args) {
        int pruebaFactorial = 5;
        long pruebaPrimo    = 8;

        int fact = factorial(pruebaFactorial);
        System.out.println("Factorial de " + pruebaFactorial + " = " + fact);

        boolean primo = esPrimo(pruebaPrimo);
        System.out.println("¿" + pruebaPrimo + " es primo? " + primo);

        if (primo) {
            int sumaPares = sumaParesHasta((int) pruebaPrimo);
            System.out.println("Suma de pares desde 0 hasta " + pruebaPrimo + " = " + sumaPares);
        } else {
            int sumaImpares = sumaImparesHasta((int) pruebaPrimo);
            System.out.println("Suma de impares desde 0 hasta " + pruebaPrimo + " = " + sumaImpares);
        }

        pruebaFactorial = 7;
        pruebaPrimo     = 7;

        fact = factorial(pruebaFactorial);
        System.out.println("\nFactorial de " + pruebaFactorial + " = " + fact);

        primo = esPrimo(pruebaPrimo);
        System.out.println("¿" + pruebaPrimo + " es primo? " + primo);

        if (primo) {
            int sumaPares = sumaParesHasta((int) pruebaPrimo);
            System.out.println("Suma de pares desde 0 hasta " + pruebaPrimo + " = " + sumaPares);
        } else {
            int sumaImpares = sumaImparesHasta((int) pruebaPrimo);
            System.out.println("Suma de impares desde 0 hasta " + pruebaPrimo + " = " + sumaImpares);
        }
    }
}