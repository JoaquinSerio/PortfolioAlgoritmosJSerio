package interfacesYUtilTA2;

public class Producto {
    private String codigo;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(String codigo, String nombre, double precio, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }

    public void agregarStock(int cantidad) { stock += cantidad; }

    public int reducirStock(int cantidad) {
        int vendidos = Math.min(stock, cantidad);
        stock -= vendidos;
        return vendidos;
    }
}
