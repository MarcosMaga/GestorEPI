/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.dao;

import com.mycompany.gestaoepi.database.DatabaseReturn;
import com.mycompany.gestaoepi.models.Setor;
import com.mycompany.gestaoepi.models.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcos
 */
public class SetorDao {
    public void insertSetor(String userId, String setorCod, String setorNome){
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "INSERT INTO setores VALUES (DEFAULT, " + userId + ", '" + setorCod +"', '" + setorNome + "');";
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
    
        public void updateSetor(String userId, String setorCod, String code, String nome){
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "UPDATE setores SET setor_codigo = '" + code + "', setor_nome = '" + nome + "' WHERE id_user = "+ userId + " AND id_setor = " + setorCod + ";";
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
    
    public void deleteSetor(String userId, String setorCod){
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "DELETE FROM setores WHERE id_user = "+ userId + " AND id_setor = " + setorCod + ";";
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
    
    public List<Setor> SelectSetor(String user_id){
        List<Setor> setores = new ArrayList<Setor>();
        
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "SELECT id_setor, setor_codigo, setor_nome FROM gestao_epi.setores WHERE id_user = " + user_id + ";";
            System.out.println(sql);
            st = (Statement) con.createStatement();
            rs = st.executeQuery(sql);
              
            while(rs.next()){
                Setor set = new Setor(Integer.toString(rs.getInt("id_setor")), rs.getString("setor_codigo"), rs.getString("setor_nome"));
                setores.add(set);
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
        
        if(setores.size() > 0)
            return setores;
        return null;
    }
    
    public List<Setor> SelectSetorByName(String user_id, String nome){
        List<Setor> setores = new ArrayList<Setor>();
        
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "SELECT id_setor, setor_codigo, setor_nome FROM gestao_epi.setores WHERE id_user = " + user_id + " AND setor_nome LIKE '%" + nome + "%';";
            System.out.println(sql);
            st = (Statement) con.createStatement();
            rs = st.executeQuery(sql);
              
            while(rs.next()){
                Setor set = new Setor(Integer.toString(rs.getInt("id_setor")), rs.getString("setor_codigo"), rs.getString("setor_nome"));
                setores.add(set);
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
        
        if(setores.size() > 0)
            return setores;
        return null;
    }
}
