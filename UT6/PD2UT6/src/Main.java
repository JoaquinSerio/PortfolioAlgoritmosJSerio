import tdas.EvaluadorRendimiento;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== EVALUACIÓN DE RENDIMIENTO DE TABLA HASH ===");
        System.out.println("Evaluando factores de carga del 70% al 99%");
        System.out.println("Tamaño de tabla: 1000 elementos");
        System.out.println("Repeticiones por medición: 100");
        System.out.println();

        EvaluadorRendimiento.evaluarRendimiento();

        System.out.println();
        System.out.println("=== ANÁLISIS TEÓRICO ===");
        System.out.println("Los resultados deberían mostrar:");
        System.out.println("1. Incremento exponencial en comparaciones a medida que aumenta el factor de carga");
        System.out.println("2. Búsquedas sin éxito requieren más comparaciones que búsquedas exitosas");
        System.out.println("3. A partir del 90% de factor de carga, degradación significativa del rendimiento");
        System.out.println("4. Cerca del 99%, el número de comparaciones se acerca al tamaño de la tabla");
    }
}