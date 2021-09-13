/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import dominio.ClsVendedores;
import java.sql.*;
import java.util.*;

/**
 *
 * @author HP
 */
public class ClsVendedorJDBC {
    private static final String SQL_SELECT = "select * from tb_PrgIIVendedor";
    private static final String SQL_UPDATE = "update tb_PrgIIVendedor set Nombre=?,Enero=?,Febrero=?,Marzo=? where Codigo=? ";
    private static final String SQL_INSERT = "insert into tb_PrgIIVendedor (Nombre,Enero,Febrero,Marzo) values(?,?,?,?)";
    private static final String SQL_DELETE = "delete from tb_PrgIIVendedor where Codigo=? ";
    public List<ClsVendedores> seleccion(){
       Connection conn = null; 
       PreparedStatement stmt = null;
       ResultSet rs = null;
       ClsVendedores vendedores = null;
       List<ClsVendedores> vendedor = new ArrayList<ClsVendedores>();
       
        try {
            conn = ClsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            
            while (rs.next()){
                int codigo = rs.getInt("Codigo");
                String nombre = rs.getString("Nombre");
                int enero = rs.getInt("Enero");
                int febrero = rs.getInt("Febrero");
                int marzo = rs.getInt("Marzo");
                vendedores = new ClsVendedores();
                vendedores.setCodigo_de_vendedor(codigo);
                vendedores.setNombre(nombre);
                vendedores.setEnero(enero);
                vendedores.setFebrero(febrero);
                vendedores.setMarzo(marzo);
                vendedor.add(vendedores);
                
            }
            
        } catch (SQLException ex) {
           ex.printStackTrace(System.out);
        }finally{
            ClsConexion.close(conn);
            ClsConexion.close(rs);
            ClsConexion.close(stmt);
        }
        return vendedor;
       
       
       
    }
    
    public int insert(ClsVendedores vendedor){
        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            conn = ClsConexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, vendedor.getNombre());
            stmt.setInt(2, vendedor.getEnero());
            stmt.setInt(3, vendedor.getFebrero());
            stmt.setInt(4, vendedor.getMarzo());
         
            
            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        }
        finally{
            ClsConexion.close(stmt);
            ClsConexion.close(conn);
        }
        
        return rows;
    }
    
    public int delete(ClsVendedores vendedor) {
        Connection cxn = null;
        PreparedStatement Prstmt = null;
        int rows = 0;
        try {
            cxn = ClsConexion.getConnection();
            Prstmt = cxn.prepareStatement(SQL_DELETE);
            Prstmt.setInt(1, vendedor.getCodigo_de_vendedor());

            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = Prstmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ClsConexion.close(Prstmt);
            ClsConexion.close(cxn);
        }

        return rows;
    }
    
   public int update(ClsVendedores vendedor) {
        Connection cxn = null;
        PreparedStatement stmt = null;
        int rows = 0;
        try {
            cxn = ClsConexion.getConnection();
            stmt = cxn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, vendedor.getNombre());
            stmt.setInt(2, vendedor.getEnero());
            stmt.setInt(3, vendedor.getFebrero());
            stmt.setInt(4, vendedor.getMarzo());
            stmt.setInt(5, vendedor.getCodigo_de_vendedor());

            System.out.println("ejecutando query:" + SQL_INSERT);
            rows = stmt.executeUpdate();
            System.out.println("Registros afectados:" + rows);
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            ClsConexion.close(stmt);
            ClsConexion.close(cxn);
        }

        return rows;
    }
    
  }

