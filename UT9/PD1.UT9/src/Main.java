//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String args[]) {
        TClasificador clasif = new TClasificador();
        int[] vectorPrueba = {5, 2, 8, 1, 9};

        System.out.println("Vector original:");
        for (int valor : vectorPrueba) {
            System.out.print(valor + " ");
        }
        System.out.println();

        int[] resultado = clasif.clasificar(vectorPrueba.clone(), TClasificador.METODO_CLASIFICACION_INSERCION);

        System.out.println("Vector ordenado:");
        for (int valor : resultado) {
            System.out.print(valor + " ");
        }
        System.out.println();

        System.out.println("¿Esta ordenado? " + clasif.estaOrdenado(resultado));
    }
}