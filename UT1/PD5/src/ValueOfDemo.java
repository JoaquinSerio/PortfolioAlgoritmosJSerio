public class ValueOfDemo {
    public static void main(String[] args) {
        if (args.length == 2) {
            float a = Float.valueOf(args[0]);
            float b = Float.valueOf(args[1]);

            System.out.println("a + b = " + (a + b));
            System.out.println("a - b = " + (a - b));
            System.out.println("a * b = " + (a * b));
            System.out.println("a / b = " + (a / b));
            System.out.println("a % b = " + (a % b));
        } else {
            System.out.println("This program requires two command-line arguments.");
        }
    }
}

//Salida al invocar con 13.4 66.1:
//a + b = 79.5
//a - b = -52.7
//a * b = 916.74
//a / b = 0.20351256
//a % b = 13.4


//Para aceptar solo enteros positivos:
//int a = Integer.parseInt(args[0]);
//int b = Integer.parseInt(args[1]);
//y declarar a y b de tipo int