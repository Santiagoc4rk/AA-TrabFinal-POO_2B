package ModeloPlanCelular;

import ModeloCliente.Cliente;
import java.util.List;

public class PlanCelularServicio {

    private PlanCelularDAO planDAO;

    public PlanCelularServicio() {
        planDAO = new PlanCelularDAO();
    }

    public boolean asignarPlanACliente(Cliente cliente, PlanCelular plan) {
        List<PlanCelular> planesCliente = planDAO.listarPlanesPorCliente(cliente.getIdentificacion());

        if (planesCliente.size() >= 2) {
            System.out.println("Error: El cliente ya tiene asignados 2 planes.");
            return false;
        }

        plan.setCliente(cliente);

        // El método insertarPlan es void, no retorna boolean, así que capturamos excepción para saber si fue ok
        try {
            planDAO.insertarPlan(plan);
            return true;
        } catch (Exception e) {
            System.err.println("Error al insertar plan: " + e.getMessage());
            return false;
        }
    }

    public List<PlanCelular> listarPlanesPorCliente(String cedulaCliente) {
        return planDAO.listarPlanesPorCliente(cedulaCliente);
    }

    public PlanCelular buscarPlan(int codigo) {
        return planDAO.buscarPorCodigo(codigo);
    }

    public boolean actualizarPlan(PlanCelular plan) {
        return planDAO.actualizar(plan);
    }

    public boolean eliminarPlan(int codigo) {
        try {
            planDAO.eliminar(codigo);
            return true;
        } catch (Exception e) {
            System.err.println("Error al eliminar plan: " + e.getMessage());
            return false;
        }
    }
}

    

