package Controlador;

import ModeloCliente.Cliente;
import ModeloCliente.ClienteDAO;
import java.util.List;

public class ClienteControlador {
    private ClienteDAO clienteDAO;

    public ClienteControlador() {
        clienteDAO = new ClienteDAO();
    }

    // Crear un cliente
    public boolean crearCliente(Cliente cliente) {
        if (cliente == null || cliente.getIdentificacion() == null || cliente.getIdentificacion().isEmpty()) {
            System.out.println("Cliente inválido o identificación vacía.");
            return false;
        }
        Cliente existe = clienteDAO.buscarPorIdentificacion(cliente.getIdentificacion());
        if (existe != null) {
            System.out.println("Ya existe un cliente con esa identificación.");
            return false;
        }
        clienteDAO.insertar(cliente);
        return true;
    }

    // Listar todos los clientes
    public List<Cliente> listarClientes() {
        return clienteDAO.listar();
    }

    // Buscar cliente por identificación
    public Cliente buscarCliente(String identificacion) {
        return clienteDAO.buscarPorIdentificacion(identificacion);
    }

    // Actualizar cliente
    public boolean actualizarCliente(Cliente cliente) {
        if (cliente == null || cliente.getIdentificacion() == null || cliente.getIdentificacion().isEmpty()) {
            System.out.println("Cliente inválido o identificación vacía.");
            return false;
        }
        Cliente existe = clienteDAO.buscarPorIdentificacion(cliente.getIdentificacion());
        if (existe == null) {
            System.out.println("No existe un cliente con esa identificación para actualizar.");
            return false;
        }
        clienteDAO.actualizar(cliente);
        return true;
    }

    // Eliminar cliente
    public boolean eliminarCliente(String identificacion) {
        Cliente existe = clienteDAO.buscarPorIdentificacion(identificacion);
        if (existe == null) {
            System.out.println("No existe un cliente con esa identificación para eliminar.");
            return false;
        }
        clienteDAO.eliminar(identificacion);
        return true;
    }
}
