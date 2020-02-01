package datos;

import dominio.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    
    // SQL REST QUERY'S
    private static final String SQL_SELECT = "SELECT id_cliente, nombre, apellido, email, telefono, saldo FROM cliente";
    
    private static final String SQL_SELECT_BY_ID = "SELECT id_cliente, nombre, apellido, email, telefono, saldo FROM cliente WHERE id_cliente = ?";
    
    private static final String SQL_INSERT = "INSERT INTO cliente(nombre, apellido, email, telefono, saldo) VALUES(?, ?, ?, ?)";
    
    private static final String SQL_UPDATE = "UPDATE cliente SET nombre=?, apellido=?, email=?, telefono=?, saldo=? WHERE id_cliente = ?";
    
    private static final String SQL_DELETE = "DELETE FROM cliente WHERE id_cliente = ?";
    
    public List<Cliente> listarClientes() {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        Cliente cliente = null;
        List<Cliente> clientes = new ArrayList<Cliente>();
        
        try {
            conn = Conexion.getConection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while(rs.next()) {
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String email = rs.getString("email");
                String telefono = rs.getString("telefono");
                double saldo = rs.getDouble("saldo");
                int id_cliente = rs.getInt("id_cliente");
                
                cliente = new Cliente(id_cliente, nombre, apellido, email, telefono, saldo);
                clientes.add(cliente);
            }
            
        } catch(SQLException ex){
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return clientes;
    }
    
    public Cliente selectCliente(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try{
            conn = Conexion.getConection();
            stmt = conn.prepareCall(SQL_SELECT_BY_ID);
            // Gets the client by id
            stmt.setInt(1, cliente.getId_cliente());
            rs = stmt.executeQuery();
            // Position the client in the first line
            rs.absolute(1);
            
            // Getting the user
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String email = rs.getString("email");
            String telefono = rs.getString("telefono");
            double saldo = rs.getDouble("saldo");
            int id_cliente = rs.getInt("id_cliente");
            
            // Setting the user
            cliente.setNombre(nombre);
            cliente.setApellido(apellido);
            cliente.setEmail(email);
            cliente.setTelefono(telefono);
            cliente.setSaldo(saldo);
            cliente.setId_cliente(id_cliente);
            
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return cliente;
    }
    
    public int insertCliente(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConection();
            stmt = conn.prepareCall(SQL_INSERT);
            
            // Getting the user
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());
            
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int updateCliente(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConection();
            stmt = conn.prepareCall(SQL_UPDATE);
            
            // Getting the user
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getApellido());
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefono());
            stmt.setDouble(5, cliente.getSaldo());
            stmt.setInt(5, cliente.getId_cliente());
            
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
    
    public int deleteCliente(Cliente cliente) {
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        
        try {
            conn = Conexion.getConection();
            stmt = conn.prepareCall(SQL_DELETE);
            
            // Getting the user
            stmt.setInt(5, cliente.getId_cliente());
            
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return rows;
    }
}
