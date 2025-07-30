package Vista;

import Controlador.PlanCelularControlador;
import javax.swing.*;
import java.awt.*;

import ModeloCliente.Cliente;
import ModeloCliente.ClienteDAO;
import ModeloPlanCelular.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class PlanCelularGUI extends JFrame {

    // Campos comunes
    private JTextField txtCodigo, txtNombre, txtCliente, txtPagoMensual;
    private JComboBox<String> cbTipoPlan;

    // Paneles específicos
    private JPanel panelMegas, panelMinutos, panelMinutosMegas, panelEconomico;

    // Campos PlanPostPagoMegas
    private JTextField txtMegasExceso, txtCostoExceso, txtCostoImpuestoMegas;

    // Campos PlanPostPagoMinutos
    private JTextField txtMinutosAdicionales, txtCostoMinutoNacional, txtCostoMinutoInternacional, txtCostoMinutoInterprovincial;

    // Campos PlanPostPagoMinutosMegas
    private JTextField txtMinutosExceso, txtCostoMinuto, txtCostoMega, txtCostoImpuestoMinutosMegas, txtMegasExcesoMinutosMegas;

    // Campos PlanPostPagoMinutosMegasEconomico
    private JTextField txtMinutosEco, txtCostoExcesoEco, txtCostoGBEco, txtPorcentajeDescuentoEco, txtCostoImpuestoEconomico;

    // Controlador
    private PlanCelularControlador controlador;

    public PlanCelularGUI() {
        setTitle("Gestión de Planes Celulares");
        setSize(600, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        controlador = new PlanCelularControlador();

        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));

        // Campos comunes
        txtCodigo = new JTextField(10);
        txtCodigo.setEditable(true);
        txtNombre = new JTextField(20);
        txtNombre.setEditable(true);
        txtCliente = new JTextField(20);
        txtCliente.setEditable(true);
        txtPagoMensual = new JTextField(10);
        txtPagoMensual.setEditable(true);

        panelMain.add(new JLabel("Código Plan:"));
        panelMain.add(txtCodigo);

        panelMain.add(new JLabel("Nombre Plan:"));
        panelMain.add(txtNombre);

        panelMain.add(new JLabel("Cliente (Cédula):"));
        panelMain.add(txtCliente);

        panelMain.add(new JLabel("Pago Mensual:"));
        panelMain.add(txtPagoMensual);

        // JComboBox tipo de plan
        cbTipoPlan = new JComboBox<>(new String[] {
            "PlanPostPagoMegas",
            "PlanPostPagoMinutos",
            "PlanPostPagoMinutosMegas",
            "PlanPostPagoMinutosMegasEconomico"
        });
        panelMain.add(new JLabel("Tipo de Plan:"));
        panelMain.add(cbTipoPlan);

        // Panel PlanPostPagoMegas
        panelMegas = new JPanel(new GridLayout(3, 2));
        txtMegasExceso = new JTextField(10);
        txtMegasExceso.setEditable(true);
        txtCostoExceso = new JTextField(10);
        txtCostoExceso.setEditable(true);
        txtCostoImpuestoMegas = new JTextField(10);
        txtCostoImpuestoMegas.setEditable(true);
        
        

        panelMegas.setBorder(BorderFactory.createTitledBorder("Datos PlanPostPagoMegas"));
        panelMegas.add(new JLabel("Megas Exceso:"));
        panelMegas.add(txtMegasExceso);
        panelMegas.add(new JLabel("Costo Exceso:"));
        panelMegas.add(txtCostoExceso);
        panelMegas.add(new JLabel("Costo Impuesto:"));
        panelMegas.add(txtCostoImpuestoMegas);
        panelMain.add(panelMegas);

        // Panel PlanPostPagoMinutos
        panelMinutos = new JPanel(new GridLayout(5, 2));
        txtMinutosAdicionales = new JTextField(10);
        txtMinutosAdicionales.setEditable(true);
        txtCostoMinutoNacional = new JTextField(10);
        txtCostoMinutoNacional.setEditable(true);
        txtCostoMinutoInternacional = new JTextField(10);
        txtCostoMinutoInternacional.setEditable(true);
        txtCostoMinutoInterprovincial = new JTextField(10);
        txtCostoMinutoInterprovincial.setEditable(true);

        panelMinutos.setBorder(BorderFactory.createTitledBorder("Datos PlanPostPagoMinutos"));
        panelMinutos.add(new JLabel("Minutos Adicionales:"));
        panelMinutos.add(txtMinutosAdicionales);
        panelMinutos.add(new JLabel("Costo Minuto Nacional:"));
        panelMinutos.add(txtCostoMinutoNacional);
        panelMinutos.add(new JLabel("Costo Minuto Internacional:"));
        panelMinutos.add(txtCostoMinutoInternacional);
        panelMinutos.add(new JLabel("Costo Minuto Interprovincial:"));
        panelMinutos.add(txtCostoMinutoInterprovincial);
        panelMain.add(panelMinutos);

        // Panel PlanPostPagoMinutosMegas
        panelMinutosMegas = new JPanel(new GridLayout(5, 2));
        txtMinutosExceso = new JTextField(10);
        txtMinutosExceso.setEditable(true);
        txtCostoMinuto = new JTextField(10);
        txtCostoMinuto.setEditable(true);
        txtCostoMega = new JTextField(10);
        txtCostoMega.setEditable(true);
        txtCostoImpuestoMinutosMegas = new JTextField(10);
        txtCostoImpuestoMinutosMegas.setEditable(true);
        txtMegasExcesoMinutosMegas = new JTextField(10);
        txtMegasExcesoMinutosMegas.setEditable(true);
        

        panelMinutosMegas.setBorder(BorderFactory.createTitledBorder("Datos PlanPostPagoMinutosMegas"));
        panelMinutosMegas.add(new JLabel("Minutos Exceso:"));
        panelMinutosMegas.add(txtMinutosExceso);
        panelMinutosMegas.add(new JLabel("Megas Exceso:"));  
        panelMinutosMegas.add(txtMegasExcesoMinutosMegas);
        panelMinutosMegas.add(new JLabel("Costo Minuto:"));
        panelMinutosMegas.add(txtCostoMinuto);
        panelMinutosMegas.add(new JLabel("Costo Mega:"));
        panelMinutosMegas.add(txtCostoMega);
        panelMinutosMegas.add(new JLabel("Costo Impuesto:"));
        panelMinutosMegas.add(txtCostoImpuestoMinutosMegas);
        panelMain.add(panelMinutosMegas);

        // Panel PlanPostPagoMinutosMegasEconomico
        panelEconomico = new JPanel(new GridLayout(5, 2));
        txtMinutosEco = new JTextField(10);
        txtMinutosEco.setEditable(true);
        txtCostoExcesoEco = new JTextField(10);
        txtCostoExcesoEco.setEditable(true);
        txtCostoGBEco = new JTextField(10);
        txtCostoGBEco.setEditable(true);
        txtPorcentajeDescuentoEco = new JTextField(10);
        txtPorcentajeDescuentoEco.setEditable(true);
        txtCostoImpuestoEconomico = new JTextField(10);
        txtCostoImpuestoEconomico.setEditable(true);

        panelEconomico.setBorder(BorderFactory.createTitledBorder("Datos PlanPostPagoMinutosMegasEconomico"));
        panelEconomico.add(new JLabel("Minutos:"));
        panelEconomico.add(txtMinutosEco);
        panelEconomico.add(new JLabel("Costo Exceso:"));
        panelEconomico.add(txtCostoExcesoEco);
        panelEconomico.add(new JLabel("Costo GB:"));
        panelEconomico.add(txtCostoGBEco);
        panelEconomico.add(new JLabel("Porcentaje Descuento:"));
        panelEconomico.add(txtPorcentajeDescuentoEco);
        panelEconomico.add(new JLabel("Costo Impuesto:"));
        panelEconomico.add(txtCostoImpuestoEconomico);
        panelMain.add(panelEconomico);

        // Ocultar todos menos el primero
        mostrarPanelPorTipo("PlanPostPagoMegas");

        cbTipoPlan.addActionListener(e -> {
            String tipo = (String) cbTipoPlan.getSelectedItem();
            mostrarPanelPorTipo(tipo);
        });

        add(panelMain, BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();

        JButton btnInsertar = new JButton("Insertar");
        JButton btnBuscar = new JButton("Buscar");
        JButton btnActualizar = new JButton("Actualizar");

        panelBotones.add(btnInsertar);
        panelBotones.add(btnBuscar);
        panelBotones.add(btnActualizar);

        add(panelBotones, BorderLayout.SOUTH);

        // Eventos botones
        btnInsertar.addActionListener(e -> insertarPlan());
        btnBuscar.addActionListener(e -> buscarPlan());
        btnActualizar.addActionListener(e -> actualizarPlan());
    }

    private void mostrarPanelPorTipo(String tipo) {
        panelMegas.setVisible(false);
        panelMinutos.setVisible(false);
        panelMinutosMegas.setVisible(false);
        panelEconomico.setVisible(false);

        switch (tipo) {
            case "PlanPostPagoMegas":
                panelMegas.setVisible(true);
                break;
            case "PlanPostPagoMinutos":
                panelMinutos.setVisible(true);
                break;
            case "PlanPostPagoMinutosMegas":
                panelMinutosMegas.setVisible(true);
                break;
            case "PlanPostPagoMinutosMegasEconomico":
                panelEconomico.setVisible(true);
                break;
        }

        revalidate();
        repaint();
    }

    // Métodos para crear objeto PlanCelular según inputs y tipo seleccionado
    private PlanCelular crearPlanDesdeFormulario() {
        String tipo = (String) cbTipoPlan.getSelectedItem();
        int codigo = Integer.parseInt(txtCodigo.getText());
        String nombre = txtNombre.getText();
        String cedulaCliente = txtCliente.getText();
        double pagoMensual = Double.parseDouble(txtPagoMensual.getText());

        ClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = clienteDAO.buscarPorIdentificacion(cedulaCliente);
        if (cliente == null) {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado: " + cedulaCliente);
            return null;
        }

        switch (tipo) {
            case "PlanPostPagoMegas": {
                PlanPostPagoMegas plan = new PlanPostPagoMegas();
                plan.setCodigoPlan(codigo);
                plan.setNombrePlan(nombre);
                plan.setCliente(cliente);
                plan.setPagoMensual(pagoMensual);
                plan.setTipoPlan(tipo);

                plan.setCupoMinutos(0);
                plan.setCostoMinutoAdicional(0);

                plan.setMegasExceso(Double.parseDouble(txtMegasExceso.getText()));
                plan.setCostoExceso(Double.parseDouble(txtCostoExceso.getText()));
                plan.setCostoImpuesto(Double.parseDouble(txtCostoImpuestoMegas.getText()));
                return plan;
            }

            case "PlanPostPagoMinutos": {
                PlanPostPagoMinutos plan = new PlanPostPagoMinutos();
                plan.setCodigoPlan(codigo);
                plan.setNombrePlan(nombre);
                plan.setCliente(cliente);
                plan.setPagoMensual(pagoMensual);
                plan.setTipoPlan(tipo);

                plan.setCupoMinutos(0);
                plan.setCostoMinutoAdicional(0);

                plan.setMinutosAdicionales(Integer.parseInt(txtMinutosAdicionales.getText()));
                plan.setCostoMinutoNacional(Double.parseDouble(txtCostoMinutoNacional.getText()));
                plan.setCostoMinutoInternacional(Double.parseDouble(txtCostoMinutoInternacional.getText()));
                plan.setCostoMinutoInterprovincial(Double.parseDouble(txtCostoMinutoInterprovincial.getText()));
                return plan;
            }

            case "PlanPostPagoMinutosMegas": {
                PlanPostPagoMinutosMegas plan = new PlanPostPagoMinutosMegas();
                plan.setCodigoPlan(codigo);
                plan.setNombrePlan(nombre);
                plan.setCliente(cliente);
                plan.setPagoMensual(pagoMensual);
                plan.setTipoPlan(tipo);

                plan.setCupoMinutos(0);
                plan.setCostoMinutoAdicional(0);

                plan.setMinutosExceso(Integer.parseInt(txtMinutosExceso.getText()));
                plan.setMegasExceso(Double.parseDouble(txtMegasExcesoMinutosMegas.getText()));  // USAR EL NUEVO CAMPO
                plan.setCostoMinuto(Double.parseDouble(txtCostoMinuto.getText()));
                plan.setCostoMega(Double.parseDouble(txtCostoMega.getText()));
                plan.setCostoImpuesto(Double.parseDouble(txtCostoImpuestoMinutosMegas.getText()));
                return plan;
            }

            case "PlanPostPagoMinutosMegasEconomico": {
                PlanPostPagoMinutosMegasEconomico plan = new PlanPostPagoMinutosMegasEconomico();
                plan.setCodigoPlan(codigo);
                plan.setNombrePlan(nombre);
                plan.setCliente(cliente);
                plan.setPagoMensual(pagoMensual);
                plan.setTipoPlan(tipo);

                plan.setCupoMinutos(0);
                plan.setCostoMinutoAdicional(0);

                plan.setMinutos(Integer.parseInt(txtMinutosEco.getText()));
                plan.setCostoExceso(Double.parseDouble(txtCostoExcesoEco.getText()));
                plan.setCostoGB(Double.parseDouble(txtCostoGBEco.getText()));
                plan.setCostoImpuesto(Double.parseDouble(txtCostoImpuestoEconomico.getText()));
                plan.setPorcentajeDescuento(Double.parseDouble(txtPorcentajeDescuentoEco.getText()));
                return plan;
            }

            default:
                JOptionPane.showMessageDialog(this, "Tipo de plan inválido");
                return null;
        }
    }

    private void insertarPlan() {
        PlanCelular plan = crearPlanDesdeFormulario();
        if (plan == null) return;

        boolean exito = controlador.insertarPlan(plan);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Plan insertado correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "Error al insertar plan");
        }
    }

    private void buscarPlan() {
        try {
            int codigo = Integer.parseInt(txtCodigo.getText());
            PlanCelular plan = controlador.buscarPlan(codigo);

            if (plan == null) {
                JOptionPane.showMessageDialog(this, "No se encontró el plan con código " + codigo);
                return;
            }

            // Cargar datos comunes
            txtNombre.setText(plan.getNombrePlan());
            txtCliente.setText(plan.getCliente().getIdentificacion());
            txtPagoMensual.setText(String.valueOf(plan.getPagoMensual()));
            cbTipoPlan.setSelectedItem(plan.getTipoPlan());

            mostrarPanelPorTipo(plan.getTipoPlan());

            switch (plan.getTipoPlan()) {
                case "PlanPostPagoMegas":
                    PlanPostPagoMegas pMegas = (PlanPostPagoMegas) plan;
                    txtMegasExceso.setText(String.valueOf(pMegas.getMegasExceso()));
                    txtCostoExceso.setText(String.valueOf(pMegas.getCostoExceso()));
                    txtCostoImpuestoMegas.setText(String.valueOf(pMegas.getCostoImpuesto()));
                    break;
                case "PlanPostPagoMinutos":
                    PlanPostPagoMinutos pMinutos = (PlanPostPagoMinutos) plan;
                    txtMinutosAdicionales.setText(String.valueOf(pMinutos.getMinutosAdicionales()));
                    txtCostoMinutoNacional.setText(String.valueOf(pMinutos.getCostoMinutoNacional()));
                    txtCostoMinutoInternacional.setText(String.valueOf(pMinutos.getCostoMinutoInternacional()));
                    txtCostoMinutoInterprovincial.setText(String.valueOf(pMinutos.getCostoMinutoInterprovincial()));
                    break;
                case "PlanPostPagoMinutosMegas":
                    PlanPostPagoMinutosMegas pMinutosMegas = (PlanPostPagoMinutosMegas) plan;
                    txtMinutosExceso.setText(String.valueOf(pMinutosMegas.getMinutosExceso()));
                    txtMegasExceso.setText(String.valueOf(pMinutosMegas.getMegasExceso()));
                    txtCostoMinuto.setText(String.valueOf(pMinutosMegas.getCostoMinuto()));
                    txtCostoMega.setText(String.valueOf(pMinutosMegas.getCostoMega()));
                    txtCostoImpuestoMinutosMegas.setText(String.valueOf(pMinutosMegas.getCostoImpuesto()));  
                    break;
                case "PlanPostPagoMinutosMegasEconomico":
                    PlanPostPagoMinutosMegasEconomico pEco = (PlanPostPagoMinutosMegasEconomico) plan;
                    txtMinutosEco.setText(String.valueOf(pEco.getMinutos()));
                    txtCostoExcesoEco.setText(String.valueOf(pEco.getCostoExceso()));
                    txtCostoGBEco.setText(String.valueOf(pEco.getCostoGB()));
                    txtCostoImpuestoEconomico.setText(String.valueOf(pEco.getCostoImpuesto()));
                    txtPorcentajeDescuentoEco.setText(String.valueOf(pEco.getPorcentajeDescuento()));
                    break;
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Ingrese un código válido");
        }
    }

    private void actualizarPlan() {
        PlanCelular plan = crearPlanDesdeFormulario();
        if (plan == null) return;

        boolean exito = controlador.actualizarPlan(plan);
        if (exito) {
            JOptionPane.showMessageDialog(this, "Plan actualizado correctamente");
        } else {
            JOptionPane.showMessageDialog(this, "Error al actualizar plan");
        }
    }
}
