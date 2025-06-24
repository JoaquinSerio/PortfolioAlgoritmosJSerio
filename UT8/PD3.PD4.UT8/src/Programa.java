import java.util.Collection;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Programa {

    public static void main(String[] args) {
        TGrafoRedElectrica laRed = (TGrafoRedElectrica) UtilGrafos.cargarGrafo(
                "C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT8\\PD3.PD4.UT8\\src\\barrio.txt",
                "C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT8\\PD3.PD4.UT8\\src\\distancias.txt",
                false, TGrafoRedElectrica.class);

        TAristas mejorRed = laRed.mejorRedElectrica();

        double costoTotal = 0;
        StringBuilder resultado = new StringBuilder();
        resultado.append("Algoritmo: Prim (Arbol Abarcador Minimo)\n");

        for (TArista arista : mejorRed) {
            costoTotal += arista.getCosto();
            resultado.append(arista.getEtiquetaOrigen())
                    .append(",")
                    .append(arista.getEtiquetaDestino())
                    .append(",")
                    .append(arista.getCosto())
                    .append("\n");
        }

        String[] lineasSalida = new String[mejorRed.size() + 2];
        lineasSalida[0] = "Algoritmo: Prim (Arbol Abarcador Minimo)";
        lineasSalida[1] = "Costo total: " + costoTotal;

        int i = 2;
        for (TArista arista : mejorRed) {
            lineasSalida[i] = arista.getEtiquetaOrigen() + "," +
                    arista.getEtiquetaDestino() + "," +
                    arista.getCosto();
            i++;
        }

        ManejadorArchivosGenerico.escribirArchivo("C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT8\\PD3.PD4.UT8\\src\\redelectrica.txt", lineasSalida);

        System.out.println("Costo total: " + costoTotal);
        System.out.println("Archivo 'redelectrica.txt' generado exitosamente");
    }
}