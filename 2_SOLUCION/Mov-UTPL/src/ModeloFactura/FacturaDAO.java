package ModeloFactura;

import ModeloPlanCelular.*;
import Util.ConexionSQLite;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {

    public void insertar(Factura factura) {
        String sql = "INSERT INTO factura (numFactura, cliente, nombrePlan, tipoPlan, fechaEmision, totalAPagar) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, factura.getNumFactura());
            ps.setString(2, factura.getCliente());
            ps.setString(3, factura.getPlan().getNombrePlan());
            ps.setString(4, factura.getPlan().getTipoPlan());
            ps.setString(5, factura.getFechaEmision());
            ps.setDouble(6, factura.getTotalAPagar());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al insertar factura: " + e.getMessage());
        }
    }

    public List<Factura> obtenerTodas() {
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT * FROM factura";

        try (Connection conn = ConexionSQLite.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int numFactura = rs.getInt("numFactura");
                String cliente = rs.getString("cliente");
                String nombrePlan = rs.getString("nombrePlan");
                String tipoPlan = rs.getString("tipoPlan");
                String fechaEmision = rs.getString("fechaEmision");
                double totalAPagar = rs.getDouble("totalAPagar");

                PlanCelular plan = crearPlanPorTipo(tipoPlan);
                if (plan != null) {
                    plan.setNombrePlan(nombrePlan);
                    plan.setPagoMensual(totalAPagar);
                    plan.setTipoPlan(tipoPlan);

                    Factura factura = new Factura(numFactura, cliente, plan, fechaEmision);
                    lista.add(factura);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener facturas: " + e.getMessage());
        }

        return lista;
    }

    public List<Factura> obtenerFacturasPorCliente(String cedulaCliente) {
        List<Factura> lista = new ArrayList<>();
        String sql = "SELECT * FROM factura WHERE cliente = ?";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cedulaCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int numFactura = rs.getInt("numFactura");
                    String cliente = rs.getString("cliente");
                    String nombrePlan = rs.getString("nombrePlan");
                    String tipoPlan = rs.getString("tipoPlan");
                    String fechaEmision = rs.getString("fechaEmision");
                    double totalAPagar = rs.getDouble("totalAPagar");

                    PlanCelular plan = crearPlanPorTipo(tipoPlan);
                    if (plan != null) {
                        plan.setNombrePlan(nombrePlan);
                        plan.setPagoMensual(totalAPagar);
                        plan.setTipoPlan(tipoPlan);

                        Factura factura = new Factura(numFactura, cliente, plan, fechaEmision);
                        lista.add(factura);
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener facturas por cliente: " + e.getMessage());
        }
        return lista;
    }

    public Factura buscarPorNumero(int numFactura) {
        String sql = "SELECT * FROM factura WHERE numFactura = ?";
        Factura factura = null;

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, numFactura);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String cliente = rs.getString("cliente");
                    String nombrePlan = rs.getString("nombrePlan");
                    String tipoPlan = rs.getString("tipoPlan");
                    String fechaEmision = rs.getString("fechaEmision");
                    double totalAPagar = rs.getDouble("totalAPagar");

                    PlanCelular plan = crearPlanPorTipo(tipoPlan);
                    if (plan != null) {
                        plan.setNombrePlan(nombrePlan);
                        plan.setPagoMensual(totalAPagar);
                        plan.setTipoPlan(tipoPlan);

                        factura = new Factura(numFactura, cliente, plan, fechaEmision);
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar factura: " + e.getMessage());
        }
        return factura;
    }

    public boolean eliminar(int numFactura) {
        String sql = "DELETE FROM factura WHERE numFactura = ?";
        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, numFactura);
            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar factura: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Factura factura) {
        String sql = "UPDATE factura SET cliente = ?, nombrePlan = ?, tipoPlan = ?, fechaEmision = ?, totalAPagar = ? WHERE numFactura = ?";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, factura.getCliente());
            ps.setString(2, factura.getPlan().getNombrePlan());
            ps.setString(3, factura.getPlan().getTipoPlan());
            ps.setString(4, factura.getFechaEmision());
            ps.setDouble(5, factura.getTotalAPagar());
            ps.setInt(6, factura.getNumFactura());

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar factura: " + e.getMessage());
            return false;
        }
    }

    public int obtenerProximoNumeroFactura() {
        int maxNumero = 0;
        String sql = "SELECT MAX(numFactura) AS maxNum FROM factura";

        try (Connection conn = ConexionSQLite.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            if (rs.next()) {
                maxNumero = rs.getInt("maxNum");
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener el último número de factura: " + e.getMessage());
        }

        return maxNumero + 1;
    }

    private PlanCelular crearPlanPorTipo(String tipoPlan) {
        switch (tipoPlan) {
            case "PlanPostPagoMegas":
                return new PlanPostPagoMegas();
            case "PlanPostPagoMinutos":
                return new PlanPostPagoMinutos();
            case "PlanPostPagoMinutosMegas":
                return new PlanPostPagoMinutosMegas();
            case "PlanPostPagoMinutosMegasEconomico":
                return new PlanPostPagoMinutosMegasEconomico();
            default:
                System.err.println("Tipo de plan desconocido: " + tipoPlan);
                return null;
        }
    }
}



