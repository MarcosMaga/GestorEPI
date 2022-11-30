/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gestaoepi.dao;

import com.mycompany.gestaoepi.database.DatabaseReturn;
import com.mycompany.gestaoepi.models.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcos
 */
public class UsersDao{
    public User getLoginUser(String userEmail, String pass){
        Connection con = null;
        ResultSet rs = null;
        Statement st = null;
        User user = null;
        
        try {
            con = DatabaseReturn.getConnection();
            String sql = "SELECT id, email, nome, empresa FROM gestao_epi.users WHERE email = '" + userEmail + "' AND senha = '" + pass + "';";
            System.out.println(sql);
            st = (Statement) con.createStatement();
            rs = st.executeQuery(sql);
            
            
            
            while(rs.next()){
                user = new User(Integer.toString(rs.getInt("id")), rs.getString("email"), rs.getString("nome"), rs.getString("empresa"));
                break;
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
        
        if(user != null)
            return user;
        return null;
    }
}
