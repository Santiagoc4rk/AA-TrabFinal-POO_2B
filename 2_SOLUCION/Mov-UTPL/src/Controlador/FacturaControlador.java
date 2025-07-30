package Controlador;

import ModeloFactura.Factura;
import ModeloFactura.FacturaDAO;
import ModeloPlanCelular.PlanCelular;

import java.util.List;

public class FacturaControlador {
    private FacturaDAO facturaDAO;

    public FacturaControlador() {
        facturaDAO = new FacturaDAO();
    }

    // Método para crear y guardar una factura
    public boolean crearFactura(int numFactura, String cliente, PlanCelular plan, String fechaEmision) {
        if (plan == null) {
            System.out.println("Error: El plan es nulo.");
            return false;
        }
        Factura factura = new Factura(numFactura, cliente, plan, fechaEmision);
        factura.calcularTotal();  // Calcula el total a pagar según el plan
        facturaDAO.insertar(factura);
        return true;
    }

    // Método para obtener todas las facturas
    public List<Factura> listarFacturas() {
        return facturaDAO.obtenerTodas();
    }

    // Método para buscar una factura por número
    public Factura buscarFactura(int numFactura) {
        List<Factura> facturas = facturaDAO.obtenerTodas();
        for (Factura f : facturas) {
            if (f.getNumFactura() == numFactura) {
                return f;
            }
        }
        return null; // No encontrada
    }

    // Método para eliminar una factura (puedes implementar en DAO primero)
    public boolean eliminarFactura(int numFactura) {
        return facturaDAO.eliminar(numFactura);
    }

    // Método para actualizar factura (puede ser necesario en algún caso)
    public boolean actualizarFactura(Factura factura) {
        if (factura == null) {
            return false;
        }
        factura.calcularTotal();
        return facturaDAO.actualizar(factura);
    }
}