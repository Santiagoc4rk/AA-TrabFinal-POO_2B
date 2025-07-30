package ModeloPlanCelular;

import ModeloCliente.Cliente;
import ModeloCliente.ClienteDAO;
import Util.ConexionSQLite;
import java.sql.*;
import java.util.*;

public class PlanCelularDAO {
    
    public boolean insertarPlan(PlanCelular plan) {
        String sql = "INSERT INTO planCelular (" +
                    "codigoPlan, nombrePlan, cupoMinutos, costoMinutoAdicional, " +
                    "clienteCedula, pagoMensual, tipoPlan, " +
                    "megasExceso, costoExceso, costoImpuesto, " +
                    "minutosAdicionales, costoMinutoNacional, costoMinutoInternacional, costoMinutoInterprovincial, " +
                    "minutosExceso, costoMinuto, costoMega, " +
                    "minutos, costoGb, porcentajeDescuento) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";


       try (Connection conn = ConexionSQLite.conectar(); PreparedStatement cs = conn.prepareStatement(sql)) {

            // Campos comunes
            cs.setInt(1, plan.getCodigoPlan());
            cs.setString(2, plan.getNombrePlan());
            cs.setDouble(3, plan.getCupoMinutos());
            cs.setDouble(4, plan.getCostoMinutoAdicional());
            cs.setString(5, plan.getCliente().getIdentificacion());
            cs.setDouble(6, plan.getPagoMensual());
            cs.setString(7, plan.getTipoPlan());

            // Inicializamos variables específicas en null
            Double megasExceso = null;
            Double costoExceso = null;
            Double costoImpuesto = null;

            Integer minutosAdicionales = null;
            Double costoMinutoNacional = null;
            Double costoMinutoInternacional = null;
            Double costoMinutoInterprovincial = null;

            Integer minutosExceso = null;
            Double costoMinuto = null;
            Double costoMega = null;
            
            

            Integer minutos = null;
            Double costoGB = null;
            Double porcentajeDescuento = null;

            // Asignar variables según el tipo de plan
            if (plan instanceof PlanPostPagoMegas) {
                PlanPostPagoMegas p = (PlanPostPagoMegas) plan;
                megasExceso = p.getMegasExceso();
                costoExceso = p.getCostoExceso();
                costoImpuesto = p.getCostoImpuesto();
            } else if (plan instanceof PlanPostPagoMinutos) {
                PlanPostPagoMinutos p = (PlanPostPagoMinutos) plan;
                minutosAdicionales = p.getMinutosAdicionales();
                costoMinutoNacional = p.getCostoMinutoNacional();
                costoMinutoInternacional = p.getCostoMinutoInternacional();
                costoMinutoInterprovincial = p.getCostoMinutoInterprovincial();
            } else if (plan instanceof PlanPostPagoMinutosMegas) {
                PlanPostPagoMinutosMegas p = (PlanPostPagoMinutosMegas) plan;
                minutosExceso = p.getMinutosExceso();
                megasExceso = p.getMegasExceso();
                costoMinuto = p.getCostoMinuto();
                costoMega = p.getCostoMega();
                costoImpuesto = p.getCostoImpuesto();
            } else if (plan instanceof PlanPostPagoMinutosMegasEconomico) {
                PlanPostPagoMinutosMegasEconomico p = (PlanPostPagoMinutosMegasEconomico) plan;
                minutos = p.getMinutos();
                costoExceso = p.getCostoExceso();
                costoGB = p.getCostoGB();
                costoImpuesto = p.getCostoImpuesto();
                porcentajeDescuento = p.getPorcentajeDescuento();
            }

            // Insertar valores o NULL en PreparedStatement
            if (megasExceso != null) {
                cs.setDouble(8, megasExceso);
            } else {
                cs.setNull(8, java.sql.Types.DOUBLE);
            }

            if (costoExceso != null) {
                cs.setDouble(9, costoExceso);
            } else {
                cs.setNull(9, java.sql.Types.DOUBLE);
            }

            if (costoImpuesto != null) {
                cs.setDouble(10, costoImpuesto);
            } else {
                cs.setNull(10, java.sql.Types.DOUBLE);
            }

            if (minutosAdicionales != null) {
                cs.setInt(11, minutosAdicionales);
            } else {
                cs.setNull(11, java.sql.Types.INTEGER);
            }

            if (costoMinutoNacional != null) {
                cs.setDouble(12, costoMinutoNacional);
            } else {
                cs.setNull(12, java.sql.Types.DOUBLE);
            }

            if (costoMinutoInternacional != null) {
                cs.setDouble(13, costoMinutoInternacional);
            } else {
                cs.setNull(13, java.sql.Types.DOUBLE);
            }

            if (costoMinutoInterprovincial != null) {
                cs.setDouble(14, costoMinutoInterprovincial);
            } else {
                cs.setNull(14, java.sql.Types.DOUBLE);
            }

            if (minutosExceso != null) {
                cs.setInt(15, minutosExceso);
            } else {
                cs.setNull(15, java.sql.Types.INTEGER);
            }

            if (costoMinuto != null) {
                cs.setDouble(16, costoMinuto);
            } else {
                cs.setNull(16, java.sql.Types.DOUBLE);
            }

            if (costoMega != null) {
                cs.setDouble(17, costoMega);
            } else {
                cs.setNull(17, java.sql.Types.DOUBLE);
            }

            if (minutos != null) {
                cs.setInt(18, minutos);
            } else {
                cs.setNull(18, java.sql.Types.INTEGER);
            }

            if (costoGB != null) {
                cs.setDouble(19, costoGB);
            } else {
                cs.setNull(19, java.sql.Types.DOUBLE);
            }

            if (porcentajeDescuento != null) {
                cs.setDouble(20, porcentajeDescuento);
            } else {
                cs.setNull(20, java.sql.Types.DOUBLE);
            }

            // Ejecutar
            cs.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error insertando plan celular: " + e.getMessage());
        }
        return false;
    }
    
    public List<PlanCelular> listarPlanesPorCliente(String cedulaCliente) {
        List<PlanCelular> lista = new ArrayList<>();
        String sql = "SELECT * FROM planCelular WHERE clienteCedula = ?";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cedulaCliente);
            ResultSet rs = ps.executeQuery();

            ClienteDAO clienteDAO = new ClienteDAO(); // Para obtener clientes

            while (rs.next()) {
                String tipo = rs.getString("tipoPlan");
                PlanCelular plan = null;

                switch (tipo) {
                    case "PlanPostPagoMegas":
                        PlanPostPagoMegas planMegas = new PlanPostPagoMegas();
                        planMegas.setMegasExceso(rs.getDouble("megasExceso"));
                        planMegas.setCostoExceso(rs.getDouble("costoExceso"));
                        planMegas.setCostoImpuesto(rs.getDouble("costoImpuesto"));
                        plan = planMegas;
                        break;

                    case "PlanPostPagoMinutos":
                        PlanPostPagoMinutos planMinutos = new PlanPostPagoMinutos();
                        planMinutos.setMinutosAdicionales(rs.getInt("minutosAdicionales"));
                        planMinutos.setCostoMinutoNacional(rs.getDouble("costoMinutoNacional"));
                        planMinutos.setCostoMinutoInternacional(rs.getDouble("costoMinutoInternacional"));
                        planMinutos.setCostoMinutoInterprovincial(rs.getDouble("costoMinutoInterprovincial"));
                        plan = planMinutos;
                        break;

                    case "PlanPostPagoMinutosMegas":
                        PlanPostPagoMinutosMegas planMinutosMegas = new PlanPostPagoMinutosMegas();
                        planMinutosMegas.setMinutosExceso(rs.getInt("minutosExceso"));
                        planMinutosMegas.setMegasExceso(rs.getDouble("megasExceso"));
                        planMinutosMegas.setCostoMinuto(rs.getDouble("costoMinuto"));
                        planMinutosMegas.setCostoMega(rs.getDouble("costoMega"));
                        planMinutosMegas.setCostoImpuesto(rs.getDouble("costoImpuesto"));
                        plan = planMinutosMegas;
                        break;

                    case "PlanPostPagoMinutosMegasEconomico":
                        PlanPostPagoMinutosMegasEconomico planEco = new PlanPostPagoMinutosMegasEconomico();
                        planEco.setMinutos(rs.getInt("minutos"));
                        planEco.setCostoExceso(rs.getDouble("costoExceso"));
                        planEco.setCostoGB(rs.getDouble("costoGb"));
                        planEco.setCostoImpuesto(rs.getDouble("costoImpuesto"));
                        planEco.setPorcentajeDescuento(rs.getDouble("porcentajeDescuento"));
                        plan = planEco;
                        break;

                    default:
                        continue;
                }

                // Setear campos comunes
                plan.setCodigoPlan(rs.getInt("codigoPlan"));
                plan.setNombrePlan(rs.getString("nombrePlan"));
                plan.setCupoMinutos(rs.getDouble("cupoMinutos"));
                plan.setCostoMinutoAdicional(rs.getDouble("costoMinutoAdicional"));

                Cliente cliente = clienteDAO.buscarPorIdentificacion(cedulaCliente);
                plan.setCliente(cliente);

                plan.setPagoMensual(rs.getDouble("pagoMensual"));
                plan.setTipoPlan(tipo);

                lista.add(plan);
            }

        } catch (SQLException e) {
            System.err.println("Error al listar planes: " + e.getMessage());
        }

        return lista;
    }
    
    public PlanCelular buscarPorCodigo(int codigoPlan) {
        PlanCelular plan = null;
        String sql = "SELECT * FROM planCelular WHERE codigoPlan = ?";

        try (Connection conn = ConexionSQLite.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, codigoPlan);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String tipo = rs.getString("tipoPlan");

                    switch (tipo) {
                        case "PlanPostPagoMegas":
                            PlanPostPagoMegas planMegas = new PlanPostPagoMegas();
                            planMegas.setMegasExceso(rs.getDouble("megasExceso"));
                            planMegas.setCostoExceso(rs.getDouble("costoExceso"));
                            planMegas.setCostoImpuesto(rs.getDouble("costoImpuesto"));
                            plan = planMegas;
                            break;

                        case "PlanPostPagoMinutos":
                            PlanPostPagoMinutos planMinutos = new PlanPostPagoMinutos();
                            planMinutos.setMinutosAdicionales(rs.getInt("minutosAdicionales"));
                            planMinutos.setCostoMinutoNacional(rs.getDouble("costoMinutoNacional"));
                            planMinutos.setCostoMinutoInternacional(rs.getDouble("costoMinutoInternacional"));
                            planMinutos.setCostoMinutoInterprovincial(rs.getDouble("costoMinutoInterprovincial"));
                            plan = planMinutos;
                            break;

                        case "PlanPostPagoMinutosMegas":
                            PlanPostPagoMinutosMegas planMinutosMegas = new PlanPostPagoMinutosMegas();
                            planMinutosMegas.setMinutosExceso(rs.getInt("minutosExceso"));
                            planMinutosMegas.setMegasExceso(rs.getDouble("megasExceso"));
                            planMinutosMegas.setCostoMinuto(rs.getDouble("costoMinuto"));
                            planMinutosMegas.setCostoMega(rs.getDouble("costoMega"));
                            planMinutosMegas.setCostoImpuesto(rs.getDouble("costoImpuesto"));
                            plan = planMinutosMegas;
                            break;

                        case "PlanPostPagoMinutosMegasEconomico":
                            PlanPostPagoMinutosMegasEconomico planEco = new PlanPostPagoMinutosMegasEconomico();
                            planEco.setMinutos(rs.getInt("minutos"));
                            planEco.setCostoExceso(rs.getDouble("costoExceso"));
                            planEco.setCostoGB(rs.getDouble("costoGb"));
                            planEco.setCostoImpuesto(rs.getDouble("costoImpuesto"));
                            planEco.setPorcentajeDescuento(rs.getDouble("porcentajeDescuento"));
                            plan = planEco;
                            break;

                        default:
                            // Puedes lanzar excepción o manejar error aquí
                            System.err.println("Tipo de plan desconocido: " + tipo);
                            return null;
                    }

                    // Campos comunes
                    plan.setCodigoPlan(rs.getInt("codigoPlan"));
                    plan.setNombrePlan(rs.getString("nombrePlan"));
                    plan.setCupoMinutos(rs.getDouble("cupoMinutos"));
                    plan.setCostoMinutoAdicional(rs.getDouble("costoMinutoAdicional"));

                    // Obtener cliente
                    ClienteDAO clienteDAO = new ClienteDAO();
                    String cedulaCliente = rs.getString("clienteCedula");
                    Cliente cliente = clienteDAO.buscarPorIdentificacion(cedulaCliente);
                    plan.setCliente(cliente);

                    plan.setPagoMensual(rs.getDouble("pagoMensual"));
                    plan.setTipoPlan(tipo);
                }
            }

        } catch (SQLException e) {
            System.err.println("Error buscando plan: " + e.getMessage());
        }

        return plan;
        }
    
    public boolean actualizar(PlanCelular plan) {
        String sql = "UPDATE planCelular SET nombrePlan = ?, cupoMinutos = ?, costoMinutoAdicional = ?, " +
                     "pagoMensual = ?, tipoPlan = ?, clienteCedula = ?, " +
                        "megasExceso = ?, costoExceso = ?, costoImpuesto = ?, " +
                        "minutosAdicionales = ?, costoMinutoNacional = ?, costoMinutoInternacional = ?, " +
                        "costoMinutoInterprovincial = ?, minutosExceso = ?, costoMinuto = ?, costoMega = ?, " +
                        "minutos = ?, costoGb = ?, porcentajeDescuento = ? " +
                        "WHERE codigoPlan = ?";


            try (Connection conn = ConexionSQLite.conectar();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, plan.getNombrePlan());
                pstmt.setDouble(2, plan.getCupoMinutos());
                pstmt.setDouble(3, plan.getCostoMinutoAdicional());
                pstmt.setDouble(4, plan.getPagoMensual());
                pstmt.setString(5, plan.getTipoPlan());
                pstmt.setString(6, plan.getCliente().getIdentificacion());

                // Valores por defecto (nulls)
                pstmt.setNull(7, java.sql.Types.DOUBLE);  // megasExceso
                pstmt.setNull(8, java.sql.Types.DOUBLE);  // costoExceso
                pstmt.setNull(9, java.sql.Types.DOUBLE);  // costoImpuesto

                pstmt.setNull(10, java.sql.Types.INTEGER); // minutosAdicionales
                pstmt.setNull(11, java.sql.Types.DOUBLE); // costoMinutoNacional
                pstmt.setNull(12, java.sql.Types.DOUBLE); // costoMinutoInternacional
                pstmt.setNull(13, java.sql.Types.DOUBLE); // costoMinutoInterprovincial

                pstmt.setNull(14, java.sql.Types.INTEGER); // minutosExceso
                pstmt.setNull(15, java.sql.Types.DOUBLE);  // costoMinuto
                pstmt.setNull(16, java.sql.Types.DOUBLE);  // costoMega

                pstmt.setNull(17, java.sql.Types.INTEGER); // minutos
                pstmt.setNull(18, java.sql.Types.DOUBLE);  // costoGb
                pstmt.setNull(19, java.sql.Types.DOUBLE);  // porcentajeDescuento

                // Lógica según el tipo de plan
                switch (plan.getTipoPlan()) {
                    case "PlanPostPagoMegas":
                        PlanPostPagoMegas megas = (PlanPostPagoMegas) plan;
                        pstmt.setDouble(7, megas.getMegasExceso());
                        pstmt.setDouble(8, megas.getCostoExceso());
                        pstmt.setDouble(9, megas.getCostoImpuesto());
                        break;

                    case "PlanPostPagoMinutos":
                        PlanPostPagoMinutos minutos = (PlanPostPagoMinutos) plan;
                        pstmt.setInt(10, minutos.getMinutosAdicionales());
                        pstmt.setDouble(11, minutos.getCostoMinutoNacional());
                        pstmt.setDouble(12, minutos.getCostoMinutoInternacional());
                        pstmt.setDouble(13, minutos.getCostoMinutoInterprovincial());
                        break;

                    case "PlanPostPagoMinutosMegas":
                        PlanPostPagoMinutosMegas minutosMegas = (PlanPostPagoMinutosMegas) plan;
                        pstmt.setInt(14, minutosMegas.getMinutosExceso());
                        pstmt.setDouble(7, minutosMegas.getMegasExceso());
                        pstmt.setDouble(15, minutosMegas.getCostoMinuto());
                        pstmt.setDouble(16, minutosMegas.getCostoMega());
                        pstmt.setDouble(9, minutosMegas.getCostoImpuesto());
                        break;

                    case "PlanPostPagoMinutosMegasEconomico":
                        PlanPostPagoMinutosMegasEconomico eco = (PlanPostPagoMinutosMegasEconomico) plan;
                        pstmt.setInt(17, eco.getMinutos());
                        pstmt.setDouble(8, eco.getCostoExceso());
                        pstmt.setDouble(18, eco.getCostoGB());
                        pstmt.setDouble(9, eco.getCostoImpuesto());
                        pstmt.setDouble(19, eco.getPorcentajeDescuento());
                        break;

                    default:
                        // Manejar caso no esperado
                        break;
                }

                pstmt.setInt(20, plan.getCodigoPlan()); // Código del plan para el WHERE

                return pstmt.executeUpdate() > 0;

            } catch (SQLException e) {
                System.err.println("Error al actualizar plan: " + e.getMessage());
                return false;
            }
        }
        
        public void eliminar(int codigoPlan) {
            Connection conn = null;
            PreparedStatement pstmt = null;

            try {
                conn = ConexionSQLite.conectar();
                String sql = "DELETE FROM planCelular WHERE codigoPlan = ?";
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, codigoPlan);

                int filas = pstmt.executeUpdate();
                if (filas > 0) {
                    System.out.println("Plan eliminado correctamente.");
                } else {
                    System.out.println("No se encontró un plan con ese código.");
                }

            } catch (SQLException e) {
                System.out.println("Error al eliminar el plan: " + e.getMessage());
            } finally {
                try {
                    if (pstmt != null) pstmt.close();
                    if (conn != null) conn.close();
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión: " + e.getMessage());
                }
            }
        }
    }
    
    
  

