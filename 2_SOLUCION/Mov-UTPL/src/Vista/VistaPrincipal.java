package Vista;

import VistaCliente.ClienteGUI;
import VistaFactura.FacturaGUI;
import javax.swing.*;
import java.awt.*;

public class VistaPrincipal extends JFrame {

    private JButton btnClientes;
    private JButton btnPlanes;
    private JButton btnFacturar;

    public VistaPrincipal() {
        setTitle("Sistema de Gestión de Clientes y Facturación Mov-UTPL");
        setSize(500, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(3, 1, 10, 10)); // Cambié de 4 filas a 3 filas

        btnClientes = new JButton("Gestionar Clientes");
        btnPlanes = new JButton("Gestionar Planes Celulares");
        btnFacturar = new JButton("Emitir Factura");

        add(btnClientes);
        add(btnPlanes);
        add(btnFacturar);

        btnClientes.addActionListener(e -> {
            ClienteGUI clienteGUI = new ClienteGUI();
            clienteGUI.setVisible(true);
        });

        btnPlanes.addActionListener(e -> {
            PlanCelularGUI planGUI = new PlanCelularGUI();
            planGUI.setVisible(true);
        });

        btnFacturar.addActionListener(e -> {
            FacturaGUI facturaGUI = new FacturaGUI();
            facturaGUI.setVisible(true);
        });
    }

}
