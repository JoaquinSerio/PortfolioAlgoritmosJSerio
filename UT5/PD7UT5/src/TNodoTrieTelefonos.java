import java.util.LinkedList;

public class TNodoTrieTelefonos implements INodoTrieTelefonos {

    private TNodoTrieTelefonos[] hijos;
    private TAbonado abonado;

    public TNodoTrieTelefonos() {
        hijos = new TNodoTrieTelefonos[10];
        abonado = null;
    }

    @Override
    public void buscarTelefonos(String primerosDigitos, LinkedList<TAbonado> abonados) {
        if (primerosDigitos.isEmpty()) {
            obtenerTodosAbonados(abonados);
        } else {
            int digito = Character.getNumericValue(primerosDigitos.charAt(0));
            if (hijos[digito] != null) {
                hijos[digito].buscarTelefonos(primerosDigitos.substring(1), abonados);
            }
        }
    }

    private void obtenerTodosAbonados(LinkedList<TAbonado> abonados) {
        if (abonado != null) {
            abonados.add(abonado);
        }
        for (TNodoTrieTelefonos hijo : hijos) {
            if (hijo != null) {
                hijo.obtenerTodosAbonados(abonados);
            }
        }
    }

    @Override
    public void insertar(TAbonado unAbonado) {
        String telefono = unAbonado.getTelefono();
        insertar(telefono, unAbonado, 0);
    }

    private void insertar(String telefono, TAbonado unAbonado, int indice) {
        if (indice == telefono.length()) {
            this.abonado = unAbonado;
            return;
        }

        int digito = Character.getNumericValue(telefono.charAt(indice));
        if (hijos[digito] == null) {
            hijos[digito] = new TNodoTrieTelefonos();
        }
        hijos[digito].insertar(telefono, unAbonado, indice + 1);
    }
}