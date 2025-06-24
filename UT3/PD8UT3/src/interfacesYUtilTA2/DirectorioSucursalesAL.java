package interfacesYUtilTA2;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DirectorioSucursalesAL {
    private final List<String> sucursales;

    public DirectorioSucursalesAL() {
        sucursales = new ArrayList<>();
    }

    public void cargarDesdeArchivo(String ruta) throws IOException {
        String[] lineas = ManejadorArchivosGenerico.leerArchivo(ruta);
        for (String raw : lineas) {
            String ciudad = raw.replace("\uFEFF", "").trim();
            sucursales.add(ciudad);
        }
    }

    public boolean agregarSucursal(String ciudad) {
        if (!sucursales.contains(ciudad)) {
            sucursales.add(ciudad);
            return true;
        }
        return false;
    }

    public boolean buscarSucursal(String ciudad) {
        return sucursales.contains(ciudad);
    }

    public boolean quitarSucursal(String ciudad) {
        return sucursales.remove(ciudad);
    }

    public List<String> listarSucursales() {
        return new ArrayList<>(sucursales);
    }

    public int cantidadSucursales() {
        return sucursales.size();
    }

    public boolean estaVacio() {
        return sucursales.isEmpty();
    }

    public static void main(String[] args) throws IOException {
        String ruta = "C:\\Users\\Estudiante UCU\\Documents\\AED\\PortfolioAlgoritmosJSerio\\UT3\\PD6UT3\\src\\interfacesYUtilTA2\\sucursales.txt";
        DirectorioSucursalesAL dir = new DirectorioSucursalesAL();
        dir.cargarDesdeArchivo(ruta);

        System.out.println("Inicial - cantidad: " + dir.cantidadSucursales());
        System.out.println("Listado: " + dir.listarSucursales());

        System.out.println("Agregar 'NuevaCiudad': " + dir.agregarSucursal("Solymar"));
        System.out.println("Buscar 'Pando': " + dir.buscarSucursal("Pando"));
        System.out.println("Quitar 'Rocha': " + dir.quitarSucursal("Rocha"));
        System.out.println("Después - cantidad: " + dir.cantidadSucursales());
        System.out.println("Listado final: " + dir.listarSucursales());
        System.out.println("Está vacío? " + dir.estaVacio());
    }
}

