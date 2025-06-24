import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        TArbolTrieTelefonos trieAbonados = new TArbolTrieTelefonos();

        String[] lineasAbonados = ManejadorArchivosGenerico.leerArchivo("C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT5\\PD7UT5\\src\\abonados.txt");
        for (String linea : lineasAbonados) {
            String[] partes = linea.split(",");
            if (partes.length == 2) {
                String telefono = partes[0].trim();
                String nombre = partes[1].trim();
                TAbonado abonado = new TAbonado(nombre, telefono);
                trieAbonados.insertar(abonado);
            }
        }

        String[] lineasCodigos = ManejadorArchivosGenerico.leerArchivo("C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT5\\PD7UT5\\src\\codigos1.txt");
        String codigoPais = "";
        String codigoArea = "";

        for (String linea : lineasCodigos) {
            if (linea.startsWith("CODIGO PAIS:")) {
                codigoPais = linea.split(":")[1].trim();
            }
            if (linea.startsWith("CODIGO AREA:")) {
                codigoArea = linea.split(":")[1].trim();
            }
        }

        LinkedList<TAbonado> ab = trieAbonados.buscarTelefonos(codigoPais, codigoArea);

        String[] lineasSalida = new String[ab.size()];
        for (int i = 0; i < ab.size(); i++) {
            TAbonado abonado = ab.get(i);
            lineasSalida[i] = abonado.getNombre() + "," + abonado.getTelefono();
        }

        ManejadorArchivosGenerico.escribirArchivo("C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT5\\PD7UT5\\src\\salida.txt", lineasSalida);
    }
}