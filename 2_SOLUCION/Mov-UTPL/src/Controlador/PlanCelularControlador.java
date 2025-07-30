package Controlador;

import ModeloCliente.Cliente;
import ModeloPlanCelular.PlanCelular;
import ModeloPlanCelular.PlanCelularServicio;
import java.util.List;

public class PlanCelularControlador {

    private PlanCelularServicio servicio;

    public PlanCelularControlador() {
        servicio = new PlanCelularServicio();
    }

    public boolean asignarPlan(Cliente cliente, PlanCelular plan) {
        return servicio.asignarPlanACliente(cliente, plan);
    }

    public List<PlanCelular> obtenerPlanesPorCliente(String cedula) {
        return servicio.listarPlanesPorCliente(cedula);
    }

    public PlanCelular buscarPlan(int codigo) {
        return servicio.buscarPlan(codigo);
    }

    public boolean actualizarPlan(PlanCelular plan) {
        return servicio.actualizarPlan(plan);
    }

    public boolean eliminarPlan(int codigo) {
        return servicio.eliminarPlan(codigo);
    }

    // 🔽 Este es el método que te faltaba
    public boolean insertarPlan(PlanCelular plan) {
        Cliente cliente = plan.getCliente();
        if (cliente != null) {
            return servicio.asignarPlanACliente(cliente, plan);
        } else {
            System.out.println("Error: El plan no tiene un cliente asignado.");
            return false;
        }
    }
}
