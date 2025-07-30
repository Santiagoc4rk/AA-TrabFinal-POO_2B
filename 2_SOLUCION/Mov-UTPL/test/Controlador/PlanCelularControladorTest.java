package Controlador;

import ModeloCliente.Cliente;
import ModeloCliente.ClienteDAO;
import ModeloPlanCelular.*;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

public class PlanCelularControladorTest {

    private PlanCelularControlador controlador;
    private Cliente cliente;

    @Before
    public void setUp() {
        controlador = new PlanCelularControlador();

        // Obtener o crear cliente de prueba
        ClienteDAO clienteDAO = new ClienteDAO();
        cliente = clienteDAO.buscarPorIdentificacion("0102030405");
        if (cliente == null) {
            cliente = new Cliente();
            cliente.setIdentificacion("0102030405");
            cliente.setNombre("Cliente Prueba");
            // Aquí puedes insertar el cliente si la BD lo permite
        }
    }

    @After
    public void tearDown() {
        // Opcional: eliminar planes creados en test para no dejar basura
        List<PlanCelular> planes = controlador.obtenerPlanesPorCliente(cliente.getIdentificacion());
        for (PlanCelular p : planes) {
            if (p.getCodigoPlan() >= 9000) { // Supongamos que los planes de test usan código >=9000
                controlador.eliminarPlan(p.getCodigoPlan());
            }
        }
    }

    @Test
    public void testInsertarPlan() {
        PlanPostPagoMegas plan = new PlanPostPagoMegas();
        plan.setCodigoPlan(9001);
        plan.setNombrePlan("Plan Test Insertar");
        plan.setCupoMinutos(100);
        plan.setCostoMinutoAdicional(0.2);
        plan.setPagoMensual(20);
        plan.setTipoPlan("PlanPostPagoMegas");
        plan.setMegasExceso(500);
        plan.setCostoExceso(1);
        plan.setCostoImpuesto(2);
        plan.setCliente(cliente);

        boolean resultado = controlador.insertarPlan(plan);
        assertTrue("Debe insertar el plan correctamente", resultado);
    }

    @Test
    public void testObtenerPlanesPorCliente() {
        List<PlanCelular> planes = controlador.obtenerPlanesPorCliente(cliente.getIdentificacion());
        assertNotNull("La lista de planes no debe ser nula", planes);
    }

    @Test
    public void testBuscarPlan() {
        int codigo = 9001;
        PlanCelular plan = controlador.buscarPlan(codigo);
        assertNull("Antes de insertar plan, no debe existir", plan);

        // Insertamos plan para probar buscar
        PlanPostPagoMegas planNuevo = new PlanPostPagoMegas();
        planNuevo.setCodigoPlan(codigo);
        planNuevo.setNombrePlan("Plan Buscar");
        planNuevo.setCliente(cliente);
        planNuevo.setTipoPlan("PlanPostPagoMegas");
        controlador.insertarPlan(planNuevo);

        PlanCelular encontrado = controlador.buscarPlan(codigo);
        assertNotNull("Debe encontrar el plan después de insertarlo", encontrado);
        assertEquals(codigo, encontrado.getCodigoPlan());
    }

    @Test
    public void testActualizarPlan() {
        int codigo = 9002;
        PlanPostPagoMegas plan = new PlanPostPagoMegas();
        plan.setCodigoPlan(codigo);
        plan.setNombrePlan("Plan para actualizar");
        plan.setCliente(cliente);
        plan.setTipoPlan("PlanPostPagoMegas");
        controlador.insertarPlan(plan);

        // Cambiamos nombre y pagomensual
        plan.setNombrePlan("Plan actualizado");
        plan.setPagoMensual(50);

        boolean actualizado = controlador.actualizarPlan(plan);
        assertTrue("Debe actualizar plan correctamente", actualizado);

        PlanCelular actualizadoEnBD = controlador.buscarPlan(codigo);
        assertEquals("Plan actualizado", actualizadoEnBD.getNombrePlan());
        assertEquals(50, actualizadoEnBD.getPagoMensual(), 0.01);
    }

    @Test
    public void testEliminarPlan() {
        int codigo = 9003;
        PlanPostPagoMegas plan = new PlanPostPagoMegas();
        plan.setCodigoPlan(codigo);
        plan.setNombrePlan("Plan para eliminar");
        plan.setCliente(cliente);
        plan.setTipoPlan("PlanPostPagoMegas");
        controlador.insertarPlan(plan);

        boolean eliminado = controlador.eliminarPlan(codigo);
        assertTrue("Debe eliminar el plan correctamente", eliminado);

        PlanCelular planBuscado = controlador.buscarPlan(codigo);
        assertNull("El plan no debe existir después de eliminarlo", planBuscado);
    }

    @Test
    public void testAsignarPlan() {
        PlanPostPagoMegas plan = new PlanPostPagoMegas();
        plan.setCodigoPlan(9004);
        plan.setNombrePlan("Plan para asignar");
        plan.setCliente(cliente);
        plan.setTipoPlan("PlanPostPagoMegas");

        boolean asignado = controlador.asignarPlan(cliente, plan);
        assertTrue("Debe asignar plan correctamente", asignado);
    }
}
