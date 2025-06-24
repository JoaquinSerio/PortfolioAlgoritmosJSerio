package interfacesYUtilTA2;

public class Almacen implements IAlmacen {
    private Lista<Producto> productos = new Lista<>();
    private String direccion;
    private String telefono;
    private String nombre;

    public Almacen(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    @Override
    public Lista<Producto> getListaProductos() {
        return productos;
    }

    @Override
    public String getDireccion() { return direccion; }

    @Override
    public void setDireccion(String direccion) { this.direccion = direccion; }

    @Override
    public String getTelefono() { return telefono; }

    @Override
    public void setTelefono(String telefono) { this.telefono = telefono; }

    @Override
    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public void insertarProducto(Producto unProducto) {
        for (String codigo : productos.imprimir(",").split(",")) {
            if (codigo.equals(unProducto.getCodigo())) {
                agregarStock(unProducto.getCodigo(), unProducto.getStock());
                return;
            }
        }
        productos.insertar(unProducto.getCodigo(), unProducto);
    }

    @Override
    public boolean eliminar(Comparable clave) {
        return productos.eliminar(clave);
    }

    @Override
    public String imprimirProductos() {
        return productos.imprimir("\n");
    }

    @Override
    public String imprimirSeparador(String separador) {
        return productos.imprimir(separador);
    }

    @Override
    public Boolean agregarStock(Comparable clave, Integer cantidad) {
        Nodo<Producto> nodo = productos.buscar(clave);
        if (nodo != null) {
            nodo.getDato().agregarStock(cantidad);
            return true;
        }
        return false;
    }

    @Override
    public Integer restarStock(Comparable clave, Integer cantidad) {
        Nodo<Producto> nodo = productos.buscar(clave);
        if (nodo != null) {
            return nodo.getDato().reducirStock(cantidad);
        }
        return 0;
    }

    @Override
    public Producto buscarPorCodigo(Comparable clave) {
        Nodo<Producto> nodo = productos.buscar(clave);
        return (nodo != null) ? nodo.getDato() : null;
    }

    @Override
    public void listarOrdenadoPorNombre() {
        productos.getPrimero(); // implementación interna de impresión
    }

    @Override
    public Producto buscarPorDescripcion(String descripcion) {
        return null;
    }

    @Override
    public int cantidadProductos() {
        return productos.cantElementos();
    }
}
