package Controlador;

import ModeloCliente.Cliente;
import ModeloCliente.ClienteDAO;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Statement;
import java.util.List;

import static org.junit.Assert.*;

public class ClienteControladorTest {

    private ClienteControlador controlador;
    private ClienteDAO dao;

    @Before
    public void setUp() throws Exception {
        controlador = new ClienteControlador();
        dao = new ClienteDAO();

        // Limpiar tabla Cliente antes de cada prueba
        try (Connection conn = Util.ConexionSQLite.conectar(); Statement stmt = conn.createStatement()) {
            stmt.executeUpdate("DELETE FROM Cliente");
        }
    }

    @Test
    public void testCrearCliente() {
        Cliente cliente = crearClienteEjemplo("001");
        boolean creado = controlador.crearCliente(cliente);
        assertTrue("El cliente debe ser creado", creado);

        // Intentar duplicado
        boolean duplicado = controlador.crearCliente(cliente);
        assertFalse("No debe permitir clientes duplicados", duplicado);
    }

    @Test
    public void testListarClientes() {
        controlador.crearCliente(crearClienteEjemplo("002"));
        controlador.crearCliente(crearClienteEjemplo("003"));

        List<Cliente> lista = controlador.listarClientes();
        assertEquals("Debe haber 2 clientes", 2, lista.size());
    }

    @Test
    public void testBuscarCliente() {
        Cliente cliente = crearClienteEjemplo("004");
        controlador.crearCliente(cliente);

        Cliente buscado = controlador.buscarCliente("004");
        assertNotNull("El cliente debe existir", buscado);
        assertEquals("Los nombres deben coincidir", cliente.getNombre(), buscado.getNombre());
    }

    @Test
    public void testActualizarCliente() {
        Cliente cliente = crearClienteEjemplo("005");
        controlador.crearCliente(cliente);

        cliente.setNombre("Nombre Actualizado");
        boolean actualizado = controlador.actualizarCliente(cliente);
        assertTrue("El cliente debe ser actualizado", actualizado);

        Cliente actualizadoCliente = controlador.buscarCliente("005");
        assertEquals("El nombre debe actualizarse", "Nombre Actualizado", actualizadoCliente.getNombre());
    }

    @Test
    public void testEliminarCliente() {
        Cliente cliente = crearClienteEjemplo("006");
        controlador.crearCliente(cliente);

        boolean eliminado = controlador.eliminarCliente("006");
        assertTrue("El cliente debe eliminarse", eliminado);

        Cliente eliminadoCliente = controlador.buscarCliente("006");
        assertNull("El cliente ya no debe existir", eliminadoCliente);
    }

    // Método de utilidad para generar un cliente base
    private Cliente crearClienteEjemplo(String id) {
        Cliente c = new Cliente();
        c.setIdentificacion(id);
        c.setNombre("TestNombre");
        c.setCiudad("Loja");
        c.setMarca("Samsung");
        c.setModelo("A14");
        c.setNumeroCelular("0987654321");
        c.setPagoMensual(25.5f);
        c.setFechaRegistro("2025-07-30");
        return c;
    }
}

