
import java.io.IOException;
import java.util.List;
import interfacesYUtilTA2.Almacen;
import interfacesYUtilTA2.Lista;
import interfacesYUtilTA2.ManejadorArchivosGenerico;
import interfacesYUtilTA2.Producto;

public class Main {
    public static void main(String[] args) throws IOException {
        Almacen almacen = new Almacen("G.E.AN.T", "Av. Central 123", "098000000") {
            @Override
            public Lista getListaProductos() {
                return null;
            }
        };

        String[] altas = ManejadorArchivosGenerico.leerArchivo("C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT3\\PD4UT3\\src\\interfacesYUtilTA2\\archivos_almacen\\altas.txt");
        double totalAltas = 0;
        for (String linea : altas) {
            String[] campos = linea.split(",");
            if (campos.length == 4) {
                String precioStr = campos[2].replace("\"", "").trim();
                String cantidadStr = campos[3].replace("\"", "").trim();
                double precio = Double.parseDouble(precioStr);
                int cantidad = Integer.parseInt(cantidadStr);
                totalAltas += precio * cantidad;
                almacen.insertarProducto(
                        new Producto(
                                campos[0].replace("\"", "").trim(),
                                campos[1].replace("\"", "").trim(),
                                precio,
                                cantidad
                        )
                );
            }
        }
        System.out.println("Total gastado en altas: " + totalAltas);

        String[] ventas = ManejadorArchivosGenerico.leerArchivo("C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT3\\PD4UT3\\src\\interfacesYUtilTA2\\archivos_almacen\\ventas.txt");
        double totalVentas = 0;
        for (String linea : ventas) {
            String[] campos = linea.split(",");
            int vendidos = almacen.restarStock(campos[0].replace("\"", "").trim(), Integer.parseInt(campos[1].replace("\"", "").trim()));
            Producto p = almacen.buscarPorCodigo(campos[0].replace("\"", "").trim());
            totalVentas += vendidos * (p != null ? p.getPrecio() : 0);
        }
        System.out.println("Total recuperado en ventas: " + totalVentas);

        System.out.println("Listado de productos y stock:");
        almacen.listarOrdenadoPorNombre();
    }
}