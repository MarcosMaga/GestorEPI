/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.dao;

import com.mycompany.gestaoepi.database.DatabaseReturn;
import com.mycompany.gestaoepi.models.Epi;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcos
 */
public class EpiDao {
    public void insertEpi(String userId, String nomeEpi, String ca, String marcaEpi, String dataValidade, String vidaUtil, String quant){
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "INSERT INTO epis VALUES (DEFAULT, " + userId + ", '" + nomeEpi +"', '" + ca + "', '" + marcaEpi +"', " + vidaUtil + ", '" + dataValidade +"', " + quant + ");";
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
    
    public List<Epi> SelectEpi(String user_id){
        List<Epi> epis = new ArrayList<Epi>();
        
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "SELECT id_epi, nome_epi, ca_epi, marca_epi, vida_util_epi, quant_epi, data_validade_epi FROM gestao_epi.epis WHERE id_user = " + user_id + ";";
            System.out.println(sql);
            st = (Statement) con.createStatement();
            rs = st.executeQuery(sql);
              
            while(rs.next()){
                Epi set = new Epi(Integer.toString(rs.getInt("id_epi")), rs.getString("nome_epi"), rs.getString("ca_epi"), rs.getString("marca_epi"), rs.getInt("vida_util_epi"), rs.getInt("quant_epi"), rs.getDate("data_validade_epi"));
                epis.add(set);
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
        
        if(epis.size() > 0)
            return epis;
        return null;
    }
    
    public List<Epi> SelectEpiToVenc(String user_id){
        List<Epi> epis = new ArrayList<Epi>();
        
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-mm-dd");  
        LocalDate now = LocalDate.now();
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "CALL epis_to_venc(" + user_id + ", '" + now + "');";
            System.out.println(sql);
            st = (Statement) con.createStatement();
            rs = st.executeQuery(sql);
              
            while(rs.next()){
                Epi set = new Epi(Integer.toString(rs.getInt("id_epi")), rs.getString("nome_epi"), rs.getString("ca_epi"), rs.getString("marca_epi"), rs.getInt("vida_util_epi"), rs.getInt("quant_epi"), rs.getDate("data_validade_epi"));
                epis.add(set);
                System.out.println(set.getNome());
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
            if(epis.size() > 0)
                return epis;
        } catch(Exception ex){
            return null;        
        }
        return null;
    }
    
    public List<Epi> SelectEpiByName(String user_id, String name){
        List<Epi> epis = new ArrayList<Epi>();
        
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "SELECT id_epi, nome_epi, ca_epi, marca_epi, vida_util_epi, quant_epi, data_validade_epi FROM gestao_epi.epis WHERE id_user = " + user_id + " LIKE '%" + name + "%';";
            System.out.println(sql);
            st = (Statement) con.createStatement();
            rs = st.executeQuery(sql);
              
            while(rs.next()){
                Epi set = new Epi(Integer.toString(rs.getInt("id_epi")), rs.getString("nome_epi"), rs.getString("ca_epi"), rs.getString("marca_epi"), rs.getInt("vida_util_epi"), rs.getInt("quant_epi"), rs.getDate("data_validade_epi"));
                epis.add(set);
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
        
        if(epis.size() > 0)
            return epis;
        return null;
    }
    
    public void deleteEpi(String userId, String epiCod){
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "DELETE FROM epis WHERE id_user = "+ userId + " AND id_epi = " + epiCod + ";";
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
    
    public void updateEpi(String userId, String epiCod, String nome, String ca, String marca, String vidaUtil, String data, String quant){
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "UPDATE epis SET nome_epi = '" + nome + "', ca_epi = '" + ca + "', marca_epi = '" + marca + "', vida_util_epi = " + vidaUtil + ", quant_epi = " + quant + ", data_validade_epi = '" + data + "' WHERE id_user = "+ userId + " AND id_epi = " + epiCod + ";";
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
    
}
