/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import bean.UserBean;
import connection.ConnectToClient;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



/**
 *
 * @author Hp
 */
public class LoginServices {
    
    public static String getUserEmail(String name)
    {
         Connection conn= null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareStatement("select email,password from usermaster where username = ?");
            pst.setString(1,name);
            
            rst = pst.executeQuery();
            System.out.println(pst);
            if(rst.next())
            {
               String uname = rst.getString("email");
             String pwd = rst.getString("userpassword");
                 
               return uname+":"+pwd;
                //System.out.println(rst.getString(1));
            }
            
        } catch (Exception e) {
            System.out.println("Exception in Login services"+e);
        }
        finally
        {
            try {
                rst.close();
                pst.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("Exception in finally of login services"+e);
            }
        }
       return null;
        //select email from username where esername = ? 
    }
    
    public static UserBean authenticateUser(String un,String pwd)
    {
        Connection conn= null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareStatement("select userid,password,usertype,email from usermaster where username = ?");
            pst.setString(1,un);
            System.out.println(pst);
            rst = pst.executeQuery();
            if(rst.next())
            {
                if(pwd.equals(rst.getString("password")))
                {
                    UserBean objbean = new UserBean();
                    objbean.setUserid(rst.getInt("userid"));
                    objbean.setUsertype(rst.getString("usertype"));
                    objbean.setEmail(rst.getString("email"));
                    System.out.println("Login successful");
                    return objbean;
                    
                }
            }
            
        } catch (Exception e) {
            System.out.println("Exception in Login services"+e);
        }
        finally
        {
            try {
                rst.close();
                pst.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("Exception in finally of login services"+e);
            }
        }
    return null;
    }
    
}
