/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.dao;

import com.mycompany.gestaoepi.database.DatabaseReturn;
import com.mycompany.gestaoepi.models.Funcionario;
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
public class FuncDao {
    public void insertFunc(String userId, String reg, String nome, String idSetor, String email){
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "INSERT INTO funcionarios VALUES (DEFAULT, " + userId + ", '" + reg +"', '" + nome + "', "+ idSetor + ", '" + email +"');";
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
    
    public List<Funcionario> SelectFunc(String user_id){
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "SELECT id_funcionario, reg, nome, id_setor, func_email FROM gestao_epi.funcionarios WHERE id_user = " + user_id + ";";
            System.out.println(sql);
            st = (Statement) con.createStatement();
            rs = st.executeQuery(sql);
              
            while(rs.next()){
                Funcionario set = new Funcionario(Integer.toString(rs.getInt("id_funcionario")), rs.getString("reg"), rs.getString("nome"), rs.getString("func_email"), Integer.toString(rs.getInt("id_setor")));
                funcionarios.add(set);
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
        
        if(funcionarios.size() > 0)
            return funcionarios;
        return null;
    }
    
    public List<Funcionario> SelectFuncBySetor(String user_id, String idSetor){
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "SELECT id_funcionario, reg, nome, id_setor, func_email FROM gestao_epi.funcionarios WHERE id_user = " + user_id + " AND id_setor = " + idSetor + ";";
            System.out.println(sql);
            st = (Statement) con.createStatement();
            rs = st.executeQuery(sql);
              
            while(rs.next()){
                Funcionario set = new Funcionario(Integer.toString(rs.getInt("id_funcionario")), rs.getString("reg"), rs.getString("nome"), rs.getString("func_email"), Integer.toString(rs.getInt("id_setor")));
                funcionarios.add(set);
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
        
        if(funcionarios.size() > 0)
            return funcionarios;
        return null;
    }
    
    public void deleteFunc(String userId, String userCod){
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "DELETE FROM funcionarios WHERE id_user = "+ userId + " AND id_setor = " + userCod + ";";
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
    
    public List<Funcionario> SelectFuncByName(String user_id, String nome){
        List<Funcionario> funcionarios = new ArrayList<Funcionario>();
        
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "SELECT id_funcionario, reg, nome, id_setor, func_email FROM gestao_epi.funcionarios WHERE id_user = " + user_id + " AND nome LIKE '%" + nome + "%';";
            System.out.println(sql);
            st = (Statement) con.createStatement();
            rs = st.executeQuery(sql);
              
            while(rs.next()){
                Funcionario set = new Funcionario(Integer.toString(rs.getInt("id_funcionario")), rs.getString("reg"), rs.getString("nome"), rs.getString("func_email"), Integer.toString(rs.getInt("id_setor")));
                funcionarios.add(set);
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
        
        if(funcionarios.size() > 0)
            return funcionarios;
        return null;
    }
    
    public void updateFunc(String userId, String funcCod, String nome, String reg, String id_setor, String email){
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "UPDATE funcionarios SET nome = '" + nome + "', reg = '" + reg + "', func_email = '" + email + "', id_setor = " + id_setor + " WHERE id_user = "+ userId + " AND id_funcionario = " + funcCod + ";";
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
