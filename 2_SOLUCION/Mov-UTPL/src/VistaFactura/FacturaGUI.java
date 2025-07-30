package VistaFactura;

import Controlador.FacturaControlador;
import Controlador.PlanCelularControlador;
import ModeloFactura.Factura;
import ModeloPlanCelular.PlanCelular;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FacturaGUI extends JFrame {

    private JTextField txtNumFactura, txtCliente, txtCodigoPlan, txtFechaEmision, txtTotalAPagar;
    private JButton btnCrear, btnBuscar, btnActualizar, btnEliminar, btnListar;

    private JTable tablaFacturas;

    private FacturaControlador facturaControlador;
    private PlanCelularControlador planControlador;

    public FacturaGUI() {
        facturaControlador = new FacturaControlador();
        planControlador = new PlanCelularControlador();

        setTitle("Gestión de Facturas");
        setSize(600, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initComponents();
    }

    private void initComponents() {
        // Cambiamos a BorderLayout para que la tabla tenga su espacio
        setLayout(new BorderLayout());

        // Panel formulario arriba
        JPanel panelForm = new JPanel(new GridLayout(6, 2, 5, 5));

        panelForm.add(new JLabel("Número Factura:"));
        txtNumFactura = new JTextField();
        panelForm.add(txtNumFactura);

        panelForm.add(new JLabel("Cliente (Cédula o Nombre):"));
        txtCliente = new JTextField();
        panelForm.add(txtCliente);

        panelForm.add(new JLabel("Código Plan Celular:"));
        txtCodigoPlan = new JTextField();
        panelForm.add(txtCodigoPlan);

        panelForm.add(new JLabel("Fecha Emisión (yyyy-MM-dd):"));
        txtFechaEmision = new JTextField(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        panelForm.add(txtFechaEmision);

        panelForm.add(new JLabel("Total a Pagar:"));
        txtTotalAPagar = new JTextField();
        txtTotalAPagar.setEditable(false);
        panelForm.add(txtTotalAPagar);

        // Botones crear, buscar, actualizar, eliminar
        btnCrear = new JButton("Crear Factura");
        btnBuscar = new JButton("Buscar Factura");
        btnActualizar = new JButton("Actualizar Factura");
        btnEliminar = new JButton("Eliminar Factura");
        btnListar = new JButton("Listar Facturas");

        // Panel para botones
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnCrear);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnListar);

        // Tabla facturas
        tablaFacturas = new JTable();
        JScrollPane scrollPane = new JScrollPane(tablaFacturas);
        scrollPane.setPreferredSize(new Dimension(580, 200));

        // Añadimos todo al JFrame
        add(panelForm, BorderLayout.NORTH);
        add(panelBotones, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Acciones botones
        btnCrear.addActionListener(e -> crearFactura());
        btnBuscar.addActionListener(e -> buscarFactura());
        btnActualizar.addActionListener(e -> actualizarFactura());
        btnEliminar.addActionListener(e -> eliminarFactura());
        btnListar.addActionListener(e -> listarFacturas());
    }

    // Métodos crearFactura, buscarFactura, actualizarFactura, eliminarFactura quedan igual que antes

   private void crearFactura() {
        try {
            int numFactura = Integer.parseInt(txtNumFactura.getText().trim());
            String cliente = txtCliente.getText().trim();
            int codigoPlan = Integer.parseInt(txtCodigoPlan.getText().trim());
            String fecha = txtFechaEmision.getText().trim();

            PlanCelular plan = planControlador.buscarPlan(codigoPlan);
            if (plan == null) {
                JOptionPane.showMessageDialog(this, "No se encontró el plan con código " + codigoPlan);
                return;
            }

            plan.calcularPagoMensual(); 

            boolean exito = facturaControlador.crearFactura(numFactura, cliente, plan, fecha);
            if (exito) {
                txtTotalAPagar.setText(String.valueOf(plan.getPagoMensual()));
                JOptionPane.showMessageDialog(this, "Factura creada correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "Error al crear la factura");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese números válidos para factura y código plan");
        }
    }


    private void buscarFactura() {
        try {
            int numFactura = Integer.parseInt(txtNumFactura.getText().trim());
            Factura factura = facturaControlador.buscarFactura(numFactura);
            if (factura == null) {
                JOptionPane.showMessageDialog(this, "No se encontró factura con número " + numFactura);
                return;
            }

            txtCliente.setText(factura.getCliente());
            txtCodigoPlan.setText(String.valueOf(factura.getPlan().getCodigoPlan()));
            txtFechaEmision.setText(factura.getFechaEmision());
            txtTotalAPagar.setText(String.valueOf(factura.getTotalAPagar()));

            JOptionPane.showMessageDialog(this, "Factura encontrada");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número de factura válido");
        }
    }

    private void actualizarFactura() {
        try {
            int numFactura = Integer.parseInt(txtNumFactura.getText().trim());
            String cliente = txtCliente.getText().trim();
            int codigoPlan = Integer.parseInt(txtCodigoPlan.getText().trim());
            String fecha = txtFechaEmision.getText().trim();

            PlanCelular plan = planControlador.buscarPlan(codigoPlan);
            if (plan == null) {
                JOptionPane.showMessageDialog(this, "No se encontró el plan con código " + codigoPlan);
                return;
            }

            Factura factura = new Factura(numFactura, cliente, plan, fecha);
            factura.calcularTotal();

            boolean exito = facturaControlador.actualizarFactura(factura);
            if (exito) {
                txtTotalAPagar.setText(String.valueOf(factura.getTotalAPagar()));
                JOptionPane.showMessageDialog(this, "Factura actualizada correctamente");
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar la factura");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese números válidos para factura y código plan");
        }
    }

    private void eliminarFactura() {
        try {
            int numFactura = Integer.parseInt(txtNumFactura.getText().trim());
            boolean exito = facturaControlador.eliminarFactura(numFactura);
            if (exito) {
                JOptionPane.showMessageDialog(this, "Factura eliminada correctamente");
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar la factura");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un número de factura válido");
        }
    }

    private void limpiarCampos() {
        txtNumFactura.setText("");
        txtCliente.setText("");
        txtCodigoPlan.setText("");
        txtFechaEmision.setText(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        txtTotalAPagar.setText("");
    }

    // Nuevo método para listar facturas en la tabla
    private void listarFacturas() {
        try {
            List<Factura> facturas = facturaControlador.listarFacturas();

            String[] columnas = {"N° Factura", "Cliente", "Código Plan", "Fecha Emisión", "Total a Pagar"};
            Object[][] datos = new Object[facturas.size()][5];

            for (int i = 0; i < facturas.size(); i++) {
                Factura f = facturas.get(i);
                datos[i][0] = f.getNumFactura();
                datos[i][1] = f.getCliente();
                datos[i][2] = f.getPlan().getCodigoPlan();
                datos[i][3] = f.getFechaEmision();
                datos[i][4] = f.getTotalAPagar();
            }

            tablaFacturas.setModel(new javax.swing.table.DefaultTableModel(datos, columnas));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al listar facturas: " + ex.getMessage());
        }
    }
}
