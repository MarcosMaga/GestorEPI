/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.dao;

import com.mycompany.gestaoepi.database.DatabaseReturn;
import com.mycompany.gestaoepi.models.Entrega;
import com.mycompany.gestaoepi.models.Epi;
import com.mycompany.gestaoepi.models.Funcionario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;

/**
 *
 * @author Marcos
 */
public class EntregaDao {
    
    public void insertEntregaFunc(String userId, String func, String epi, String dataEntre, String quant, String code){
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "INSERT INTO entregas VALUES (DEFAULT, " + userId + ", " + func +", " + epi + ", '" + dataEntre +"', DEFAULT, " + quant + ", '" + code + "', DEFAULT);";
            System.out.println(sql);
            st = (Statement) con.createStatement();
            st.execute(sql);

        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null)
                    con.close();
                
                if (st != null) 
                    st.close();
                
                if (rs != null) 
                    rs.close();
            
            } catch (SQLException ex) {
                Logger.getLogger(SetorDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void deleteEntrega(String userId, String id_entrega){
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "DELETE FROM entregas WHERE id_entrega = " + id_entrega + " AND id_user = " + userId + ";";
            System.out.println(sql);
            st = (Statement) con.createStatement();
            st.execute(sql);

        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null)
                    con.close();
                
                if (st != null) 
                    st.close();
                
                if (rs != null) 
                    rs.close();
            
            } catch (SQLException ex) {
                Logger.getLogger(SetorDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void updateEntregaDataDevolucao(String userId, String id_entrega, String data_devolucao){
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "UPDATE entregas SET data_devolucao = '" + data_devolucao + "' WHERE id_entrega=" + id_entrega + " AND id_user = " + userId + ";";
            System.out.println(sql);
            st = (Statement) con.createStatement();
            st.execute(sql);

        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (con != null)
                    con.close();
                
                if (st != null) 
                    st.close();
                
                if (rs != null) 
                    rs.close();
            
            } catch (SQLException ex) {
                Logger.getLogger(SetorDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public List<Entrega> SelectEntrega(String user_id){
        List<Entrega> entregas = new ArrayList<Entrega>();
        
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "CALL entregas_user(" + user_id +");";
            System.out.println(sql);
            st = (Statement) con.createStatement();
            rs = st.executeQuery(sql);
              
            while(rs.next()){
                String data;
                
                try{
                    data = rs.getDate("data_devolucao").toString();
                } catch(Exception ex){
                    data = null;
                }
                
                Entrega set = new Entrega(Integer.toString(rs.getInt("id_entrega")), rs.getString("nome"), rs.getString("nome_epi"), rs.getInt("vida_util_epi"), rs.getDate("data_entrega").toString(), data, rs.getInt("quant_entrega"), rs.getBoolean("confirma_entrega"));
                entregas.add(set);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                if (con != null)
                    con.close();
                
                if (st != null) 
                    st.close();
                
                if (rs != null) 
                    rs.close();
            
            } catch (SQLException ex) {
                Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        
        if(entregas.size() > 0)
            return entregas;
        return null;
    }
    
     public List<Entrega> SelectEntregaWeek(String user_id){
        List<Entrega> entregas = new ArrayList<Entrega>();
        
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");  
        LocalDate now = LocalDate.now();
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "CALL entregas_week(" + user_id +", '" + now.toString() + "');";
            System.out.println(sql);
            st = (Statement) con.createStatement();
            rs = st.executeQuery(sql);
              
            while(rs.next()){
                String data;
                
                try{
                    data = rs.getDate("data_devolucao").toString();
                } catch(Exception ex){
                    data = null;
                }
                
                Entrega set = new Entrega(Integer.toString(rs.getInt("id_entrega")), rs.getString("nome"), rs.getString("nome_epi"), rs.getInt("vida_util_epi"), rs.getDate("data_entrega").toString(), data, rs.getInt("quant_entrega"), rs.getBoolean("confirma_entrega"));
                entregas.add(set);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                if (con != null)
                    con.close();
                
                if (st != null) 
                    st.close();
                
                if (rs != null) 
                    rs.close();
            
            } catch (SQLException ex) {
                Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        
        try{
            if(entregas.size() > 0)
                return entregas;
        } catch(Exception ex){
            return null;        
        }

        return null;
    }
    
    public List<Entrega> SelectEntregaArquivada(String user_id, String nome){
        List<Entrega> entregas = new ArrayList<Entrega>();
        
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "CALL entregas_user_arquivadas(" + user_id +", '%" + nome + "%');";
            System.out.println(sql);
            st = (Statement) con.createStatement();
            rs = st.executeQuery(sql);
              
            while(rs.next()){
                String data;
                
                try{
                    data = rs.getDate("data_devolucao").toString();
                } catch(Exception ex){
                    data = null;
                }
                
                Entrega set = new Entrega(Integer.toString(rs.getInt("id_entrega")), rs.getString("nome"), rs.getString("nome_epi"), rs.getInt("vida_util_epi"), rs.getDate("data_entrega").toString(), data, rs.getInt("quant_entrega"), rs.getBoolean("confirma_entrega"));
                entregas.add(set);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                if (con != null)
                    con.close();
                
                if (st != null) 
                    st.close();
                
                if (rs != null) 
                    rs.close();
            
            } catch (SQLException ex) {
                Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        
        if(entregas.size() > 0)
            return entregas;
        return null;
    }
        
    public Entrega ConfirmEntrega(String code){
        Entrega a = null;
        
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "CALL confirm_entrega('" + code +"');";
            System.out.println(sql);
            st = (Statement) con.createStatement();
            rs = st.executeQuery(sql);
              
            try{
                while(rs.next()){
                    String data;
                
                    try{
                        data = rs.getDate("data_devolucao").toString();
                    } catch(Exception ex){
                        data = null;
                    }
                
                    a = new Entrega(Integer.toString(rs.getInt("id_entrega")), rs.getString("nome"), rs.getString("nome_epi"), rs.getInt("vida_util_epi"), rs.getDate("data_entrega").toString(), data, rs.getInt("quant_entrega"), rs.getBoolean("confirma_entrega"));
                }
            } catch(Exception ex){
                System.out.println("banana limpio");
                return null;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        } finally {
            try {
                if (con != null)
                    con.close();
                
                if (st != null) 
                    st.close();
                
                if (rs != null) 
                    rs.close();
            
            } catch (SQLException ex) {
                Logger.getLogger(UsersDao.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        
        return a;
    }
}
