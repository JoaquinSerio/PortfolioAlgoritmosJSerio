


import java.io.*;
        import java.nio.file.*;
        import java.util.*;

public class Principal {

    public static void imprimirTablero(int largo, int ancho) {
        for (int i = 0; i < largo; i++) {
            if (i % 2 != 0) {
                System.out.print(" ");
            }
            for (int j = 0; j < ancho; j++) {
                System.out.print("# ");
            }
            System.out.println();
        }
    }


    public static void leerEntradaArchivo() throws IOException {
        String contenido = Files.readString(
                Paths.get("UT1", "PD6", "src", "entrada.txt")
        );
        String[] partes = contenido.split("\\s+");
        int entero      = Integer.parseInt(partes[0]);
        double flotante = Double.parseDouble(partes[1]);
        String cadena   = partes[2];
        System.out.println(entero);
        System.out.println(flotante);
        System.out.println(cadena);
        System.out.println("¡Hola " + cadena + "! La suma de " + entero + " y " + flotante + " es " + (entero + flotante));
        int parteEntera = (int)(flotante / entero);
        double resto   = flotante - parteEntera * entero;
        System.out.println("La división entera de " + flotante + " y " + entero + " es " + parteEntera + " y su resto es " + resto);
    }

    public static void leerRadio() throws IOException {
        String contenido = Files.readString(
                Paths.get("UT1", "PD6", "src", "entrada.txt")
        );
        String[] partes = contenido.split("\\s+");
        double radio    = Double.parseDouble(partes[2]);
        double area     = Math.PI * radio * radio;
        double perimetro= 2 * Math.PI * radio;
        System.out.println("El área es: " + area);
        System.out.println("El perímetro es: " + perimetro);
    }

    public static void transformarTextoT9(String rutaArchivo) throws IOException {
        Path entrada = Paths.get(rutaArchivo);
        Path salida = Paths.get("salida.txt");
        try (BufferedReader br = Files.newBufferedReader(entrada);
             BufferedWriter bw = Files.newBufferedWriter(salida)) {
            int c;
            while ((c = br.read()) != -1) {
                char ch = (char) c;
                bw.write(mapearT9(ch));
            }
        }
    }

    public static void transformarT9Texto(String rutaArchivo) throws IOException {
        Path entrada = Paths.get(rutaArchivo);
        Path salida = Paths.get("salida.txt");
        String texto = new String(Files.readAllBytes(entrada));
        StringBuilder invertido = new StringBuilder(texto).reverse();
        try (BufferedWriter bw = Files.newBufferedWriter(salida)) {
            for (int i = 0; i < invertido.length(); i++) {
                bw.write(mapearT9(invertido.charAt(i)));
            }
        }
    }

    private static char mapearT9(char ch) {
        ch = Character.toUpperCase(ch);
        if ("ABC".indexOf(ch) >= 0) return '2';
        if ("DEF".indexOf(ch) >= 0) return '3';
        if ("GHI".indexOf(ch) >= 0) return '4';
        if ("JKL".indexOf(ch) >= 0) return '5';
        if ("MNO".indexOf(ch) >= 0) return '6';
        if ("PQRS".indexOf(ch) >= 0) return '7';
        if ("TUV".indexOf(ch) >= 0) return '8';
        if ("WXYZ".indexOf(ch) >= 0) return '9';
        if (ch == ' ') return '0';
        if (ch == '.') return '1';
        return ch;
    }

    public static void main(String[] args) throws IOException {
        imprimirTablero(7, 7);
        leerEntradaArchivo();
    }
}
