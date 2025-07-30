package ModeloCliente;

import Util.ConexionSQLite;

import java.sql.*;
import java.util.*;

public class ClienteDAO {

    public void insertar(Cliente c) {
        String sql = "INSERT INTO Cliente (identificacion, nombre, ciudad, marca, modelo, numeroCelular, pagoMensual, fechaRegistro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionSQLite.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getIdentificacion());
            ps.setString(2, c.getNombre());
            ps.setString(3, c.getCiudad());
            ps.setString(4, c.getMarca());
            ps.setString(5, c.getModelo());
            ps.setString(6, c.getNumeroCelular());
            ps.setFloat(7, c.getPagoMensual());
            ps.setString(8, c.getFechaRegistro());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al insertar cliente: " + ex.getMessage());
        }
    }

    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cliente";
        try (Connection conn = ConexionSQLite.conectar(); Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente c = new Cliente();
                c.setIdentificacion(rs.getString("identificacion"));
                c.setNombre(rs.getString("nombre"));
                c.setCiudad(rs.getString("ciudad"));
                c.setMarca(rs.getString("marca"));
                c.setModelo(rs.getString("modelo"));
                c.setNumeroCelular(rs.getString("numeroCelular"));
                c.setPagoMensual(rs.getFloat("pagoMensual"));
                c.setFechaRegistro(rs.getString("fechaRegistro"));
                lista.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar clientes: " + ex.getMessage());
        }
        return lista;
    }

    public Cliente buscarPorIdentificacion(String identificacion) {
        String sql = "SELECT * FROM Cliente WHERE identificacion = ?";
        try (Connection conn = ConexionSQLite.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, identificacion);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Cliente c = new Cliente();
                    c.setIdentificacion(rs.getString("identificacion"));
                    c.setNombre(rs.getString("nombre"));
                    c.setCiudad(rs.getString("ciudad"));
                    c.setMarca(rs.getString("marca"));
                    c.setModelo(rs.getString("modelo"));
                    c.setNumeroCelular(rs.getString("numeroCelular"));
                    c.setPagoMensual(rs.getFloat("pagoMensual"));
                    c.setFechaRegistro(rs.getString("fechaRegistro"));
                    return c;
                }
            }
        } catch (SQLException ex) {
            System.out.println("Error al buscar cliente: " + ex.getMessage());
        }
        return null;
    }

    public void actualizar(Cliente c) {
        String sql = "UPDATE Cliente SET nombre=?, ciudad=?, marca=?, modelo=?, numeroCelular=?, pagoMensual=?, fechaRegistro=? WHERE identificacion=?";
        try (Connection conn = ConexionSQLite.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, c.getNombre());
            ps.setString(2, c.getCiudad());
            ps.setString(3, c.getMarca());
            ps.setString(4, c.getModelo());
            ps.setString(5, c.getNumeroCelular());
            ps.setFloat(6, c.getPagoMensual());
            ps.setString(7, c.getFechaRegistro());
            ps.setString(8, c.getIdentificacion());

            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar cliente: " + ex.getMessage());
        }
    }

    public void eliminar(String identificacion) {
        String sql = "DELETE FROM Cliente WHERE identificacion = ?";
        try (Connection conn = ConexionSQLite.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, identificacion);
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar cliente: " + ex.getMessage());
        }
    }
}

