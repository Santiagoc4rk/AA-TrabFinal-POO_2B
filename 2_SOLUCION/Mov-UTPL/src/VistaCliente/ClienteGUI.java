package VistaCliente;

import Controlador.ClienteControlador;
import ModeloCliente.Cliente;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ClienteGUI extends JFrame {

    private ClienteControlador controlador;

    private JTextField txtIdentificacion, txtNombre, txtCiudad, txtMarca, txtModelo, txtNumeroCelular, txtPagoMensual, txtFechaRegistro;
    private JTextArea areaResultado;

    public ClienteGUI() {
        controlador = new ClienteControlador();

        setTitle("Gestión de Clientes");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Panel de formularios
        JPanel panelForm = new JPanel(new GridLayout(8, 2, 5, 5));
        panelForm.setBorder(BorderFactory.createTitledBorder("Datos del Cliente"));

        txtIdentificacion = new JTextField();
        txtNombre = new JTextField();
        txtCiudad = new JTextField();
        txtMarca = new JTextField();
        txtModelo = new JTextField();
        txtNumeroCelular = new JTextField();
        txtPagoMensual = new JTextField();
        txtFechaRegistro = new JTextField();

        panelForm.add(new JLabel("Identificación:"));
        panelForm.add(txtIdentificacion);
        panelForm.add(new JLabel("Nombre:"));
        panelForm.add(txtNombre);
        panelForm.add(new JLabel("Ciudad:"));
        panelForm.add(txtCiudad);
        panelForm.add(new JLabel("Marca:"));
        panelForm.add(txtMarca);
        panelForm.add(new JLabel("Modelo:"));
        panelForm.add(txtModelo);
        panelForm.add(new JLabel("Número Celular:"));
        panelForm.add(txtNumeroCelular);
        panelForm.add(new JLabel("Pago Mensual:"));
        panelForm.add(txtPagoMensual);
        panelForm.add(new JLabel("Fecha de Registro:"));
        panelForm.add(txtFechaRegistro);

        // Panel de botones
        JPanel panelBotones = new JPanel(new GridLayout(1, 5, 5, 5));
        JButton btnInsertar = new JButton("Insertar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnListar = new JButton("Listar");

        panelBotones.add(btnInsertar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);

        // Área de resultado
        areaResultado = new JTextArea(10, 50);
        areaResultado.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaResultado);

        // Agregar a la ventana
        add(panelForm, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(scroll, BorderLayout.SOUTH);

        // Acciones
        btnInsertar.addActionListener(e -> insertarCliente());
        btnBuscar.addActionListener(e -> buscarCliente());
        btnActualizar.addActionListener(e -> actualizarCliente());
        btnEliminar.addActionListener(e -> eliminarCliente());
        btnListar.addActionListener(e -> listarClientes());
    }

    private void insertarCliente() {
        Cliente c = leerClienteDesdeFormulario();
        if (c.getIdentificacion().isEmpty()) {
            mostrarMensaje("La identificación es obligatoria.");
            return;
        }

        if (controlador.crearCliente(c)) {
            mostrarMensaje("Cliente insertado correctamente.");
            limpiarFormulario();
        } else {
            mostrarMensaje("Error al insertar cliente.");
        }
    }

    private void buscarCliente() {
        String id = txtIdentificacion.getText().trim();
        if (id.isEmpty()) {
            mostrarMensaje("Ingrese una identificación para buscar.");
            return;
        }

        Cliente c = controlador.buscarCliente(id);
        if (c != null) {
            mostrarClienteEnFormulario(c);
            mostrarMensaje("Cliente encontrado.");
        } else {
            mostrarMensaje("Cliente no encontrado.");
        }
    }

    private void actualizarCliente() {
        Cliente c = leerClienteDesdeFormulario();
        if (c.getIdentificacion().isEmpty()) {
            mostrarMensaje("La identificación es obligatoria para actualizar.");
            return;
        }

        if (controlador.actualizarCliente(c)) {
            mostrarMensaje("Cliente actualizado correctamente.");
        } else {
            mostrarMensaje("Error al actualizar cliente.");
        }
    }

    private void eliminarCliente() {
        String id = txtIdentificacion.getText().trim();
        if (id.isEmpty()) {
            mostrarMensaje("Ingrese una identificación para eliminar.");
            return;
        }

        if (controlador.eliminarCliente(id)) {
            mostrarMensaje("Cliente eliminado.");
            limpiarFormulario();
        } else {
            mostrarMensaje("No se pudo eliminar (cliente no encontrado).");
        }
    }

    private void listarClientes() {
        List<Cliente> lista = controlador.listarClientes();
        if (lista.isEmpty()) {
            mostrarMensaje("No hay clientes.");
        } else {
            StringBuilder sb = new StringBuilder("Clientes:\n");
            for (Cliente c : lista) {
                sb.append(c.getIdentificacion()).append(" - ")
                        .append(c.getNombre()).append(" - ")
                        .append(c.getCiudad()).append(" - ")
                        .append(c.getNumeroCelular()).append("\n");
            }
            areaResultado.setText(sb.toString());
        }
    }

    private Cliente leerClienteDesdeFormulario() {
        Cliente c = new Cliente();
        c.setIdentificacion(txtIdentificacion.getText().trim());
        c.setNombre(txtNombre.getText().trim());
        c.setCiudad(txtCiudad.getText().trim());
        c.setMarca(txtMarca.getText().trim());
        c.setModelo(txtModelo.getText().trim());
        c.setNumeroCelular(txtNumeroCelular.getText().trim());

        try {
            String pagoTexto = txtPagoMensual.getText().trim();
            c.setPagoMensual(pagoTexto.isEmpty() ? 0f : Float.parseFloat(pagoTexto));
        } catch (NumberFormatException e) {
            mostrarMensaje("Pago mensual no válido. Se establecerá en 0.");
            c.setPagoMensual(0.0f);
        }

        c.setFechaRegistro(txtFechaRegistro.getText().trim());
        return c;
    }

    private void mostrarClienteEnFormulario(Cliente c) {
        txtIdentificacion.setText(c.getIdentificacion());
        txtNombre.setText(c.getNombre());
        txtCiudad.setText(c.getCiudad());
        txtMarca.setText(c.getMarca());
        txtModelo.setText(c.getModelo());
        txtNumeroCelular.setText(c.getNumeroCelular());
        txtPagoMensual.setText(String.valueOf(c.getPagoMensual()));
        txtFechaRegistro.setText(c.getFechaRegistro());
    }

    private void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }

    private void limpiarFormulario() {
        txtIdentificacion.setText("");
        txtNombre.setText("");
        txtCiudad.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtNumeroCelular.setText("");
        txtPagoMensual.setText("");
        txtFechaRegistro.setText("");
    }
}
