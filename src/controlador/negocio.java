package controlador;

import java.sql.*;
import modelo.*;
import util.MySQLConexion;
import java.util.*;

public class negocio {

    public boolean Login(Usuario usu) {
        try {
            Connection cn = MySQLConexion.getConexion();
            String sql = "select clave from USUARIO where USUARIO=? ";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, usu.getCode());
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                if (usu.getClave().equals(rs.getString(1))) {
                    return true;

                } else {
                    return false;
                }
 
            }
            return false;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public List<Ruta> ListRuta() {
        List<Ruta> lista = new ArrayList();
        try {
            Connection cn = MySQLConexion.getConexion();
            String sql = "select rutcod,rutnom,pago_cho nomesp from ruta";
            PreparedStatement st = cn.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Ruta n = new Ruta();
                n.setRutcod(rs.getString(1));
                n.setRutnom(rs.getString(2));
                n.setPagocho(rs.getDouble(3));
                lista.add(n);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    //lista de cursos de una especialidad
    public List<Viaje> ListViaje(String id) {
        List<Viaje> lista = new ArrayList();
        try {
            Connection cn = MySQLConexion.getConexion();
            String sql = "SELECT vianro, viafch, cosvia FROM ruta r, viaje v  WHERE r.rutcod=v.rutcod AND r.rutnom=?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Viaje n = new Viaje();
                n.setVianro(rs.getString(1));
                n.setViafh(rs.getString(2));
                n.setCostvia(rs.getDouble(3));
                lista.add(n);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    public List<Pasajero> ListPasajeros(String id) {
        List<Pasajero> lista = new ArrayList();
        try {
            Connection cn = MySQLConexion.getConexion();
            String sql = "select bolnro,nom_pas,tipo,pago from pasajeros where vianro=?";
            PreparedStatement st = cn.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Pasajero n = new Pasajero();
                n.setBolnro(rs.getString(1));
                n.setNom_pas(rs.getString(2));
                n.setTipo(rs.getString(2));
                n.setPago(rs.getDouble(4));
                lista.add(n);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return lista;
    }

    //adicionar alumnos
    public void GrabaPasajero(Pasajero a) {
        try {
            Connection cn = MySQLConexion.getConexion();
            String sql = "insert into pasajeros values(?,?,?,?,?,?)";
            CallableStatement st = cn.prepareCall(sql);
            st.setString(1, generar());
            st.setString(2, a.getVIANRO());
            st.setString(3, a.getNom_pas());
            st.setInt(4, a.getNro_asi());
            st.setString(5, a.getTipo());
            st.setDouble(6, a.getPago());
            st.executeUpdate();//hace la grabacion
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public int anular(int cod) {
        int resp = 0;
        Connection conn = null;
        try {
            String sql = "Delete from pasajeros where bolnro=?";
            conn = MySQLConexion.getConexion();//abre la conexion
            //prepara una intruccion para ejecutar una intruccion sql a traves de la conexion
            PreparedStatement st = conn.prepareStatement(sql);
            //Antes de ejectuar relaciona los atributos 
            st.setInt(1, cod);
            resp = st.executeUpdate();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }

        return resp;
    }

    public String generar() {
        String nro = null;
        Connection conn = null;
        try {
            conn = MySQLConexion.getConexion();
            String sql = "SELECT LPAD(MAX(bolnro)+1,6,'0') from pasajeros";
            PreparedStatement st = conn.prepareStatement(sql);
            //st.setString(1, cad);
            //resultset lleva a memoria la ejecucion de los datos
            ResultSet rs = st.executeQuery();
            //llenar el arraylist con la clase entidad
            if (rs.next()) { //lee el resulset filaxfila
                nro = rs.getString(1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {

                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e2) {
            }
        }

        return nro;
    }

}
