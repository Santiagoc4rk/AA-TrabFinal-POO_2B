package Controlador;

import ModeloFactura.Factura;
import ModeloPlanCelular.PlanCelular;
import ModeloPlanCelular.PlanPostPagoMegas; // Ejemplo de plan concreto
import Util.ConexionSQLite;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

public class FacturaControladorTest {

    private FacturaControlador controlador;

    @Before
    public void setUp() throws Exception {
        controlador = new FacturaControlador();

        // Limpiar la tabla factura antes de cada prueba
        try (Connection conn = ConexionSQLite.conectar();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM factura");
        }
    }

    @Test
    public void testCrearFactura() {
        PlanCelular plan = new PlanPostPagoMegas();
        plan.setNombrePlan("Plan Test");
        plan.setTipoPlan("PlanPostPagoMegas");
        plan.setPagoMensual(50.0);

        boolean creado = controlador.crearFactura(1, "ClienteTest", plan, "2025-07-30");
        assertTrue("Debe crear la factura", creado);

        // Crear factura con plan nulo (debe fallar)
        boolean creado2 = controlador.crearFactura(2, "ClienteTest2", null, "2025-07-30");
        assertFalse("No debe crear factura con plan nulo", creado2);
    }

    @Test
    public void testListarFacturas() {
        PlanCelular plan = new PlanPostPagoMegas();
        plan.setNombrePlan("Plan 1");
        plan.setTipoPlan("PlanPostPagoMegas");
        plan.setPagoMensual(45.0);

        controlador.crearFactura(10, "Cliente1", plan, "2025-07-30");
        controlador.crearFactura(11, "Cliente2", plan, "2025-07-30");

        List<Factura> facturas = controlador.listarFacturas();
        assertEquals("Debe listar 2 facturas", 2, facturas.size());
    }

    @Test
    public void testBuscarFactura() {
        PlanCelular plan = new PlanPostPagoMegas();
        plan.setNombrePlan("Plan Buscar");
        plan.setTipoPlan("PlanPostPagoMegas");
        plan.setPagoMensual(30.0);

        controlador.crearFactura(20, "ClienteBuscar", plan, "2025-07-30");

        Factura factura = controlador.buscarFactura(20);
        assertNotNull("Debe encontrar factura por número", factura);
        assertEquals("Número de factura debe coincidir", 20, factura.getNumFactura());
        assertEquals("Cliente debe coincidir", "ClienteBuscar", factura.getCliente());

        Factura noEncontrada = controlador.buscarFactura(999);
        assertNull("No debe encontrar factura inexistente", noEncontrada);
    }

    @Test
    public void testActualizarFactura() {
        PlanCelular plan = new PlanPostPagoMegas();
        plan.setNombrePlan("Plan Actualizar");
        plan.setTipoPlan("PlanPostPagoMegas");
        plan.setPagoMensual(40.0);

        controlador.crearFactura(30, "ClienteActualizar", plan, "2025-07-30");

        Factura factura = controlador.buscarFactura(30);
        factura.setCliente("ClienteActualizado");
        factura.getPlan().setPagoMensual(60.0);

        boolean actualizado = controlador.actualizarFactura(factura);
        assertTrue("Debe actualizar factura", actualizado);

        Factura facturaActualizada = controlador.buscarFactura(30);
        assertEquals("Cliente debe estar actualizado", "ClienteActualizado", facturaActualizada.getCliente());
        assertEquals(60.0, facturaActualizada.getTotalAPagar(), 0.01);
    }

    @Test
    public void testEliminarFactura() {
        PlanCelular plan = new PlanPostPagoMegas();
        plan.setNombrePlan("Plan Eliminar");
        plan.setTipoPlan("PlanPostPagoMegas");
        plan.setPagoMensual(35.0);

        controlador.crearFactura(40, "ClienteEliminar", plan, "2025-07-30");

        boolean eliminado = controlador.eliminarFactura(40);
        assertTrue("Debe eliminar factura", eliminado);

        Factura factura = controlador.buscarFactura(40);
        assertNull("Factura ya no debe existir", factura);
    }
}
