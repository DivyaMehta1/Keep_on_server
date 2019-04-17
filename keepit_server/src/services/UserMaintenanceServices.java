/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import bean.UserBean;
import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author Hp
 */
public class UserMaintenanceServices {
    public static boolean addUser(UserBean objb) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareStatement("insert into  usermaster(userid,username,password,usertype,name,email,contact,address) values (?,?,?,?,?,?,?,?)");
            pst.setInt(1, objb.getUserid());
            pst.setString(2, objb.getUsername());
            pst.setString(3, objb.getPassword());
            pst.setString(4, objb.getUsertype());
            pst.setString(6, objb.getName());
            pst.setString(7, objb.getEmail());
            pst.setString(5, objb.getContact());
           pst.setString(8, objb.getAddress());
             
            
            System.out.println(pst);
            int i = pst.executeUpdate();
            if (i > 0) {
                return true;
            } 
        } catch (Exception e) {
            System.out.println("Exception in addUser " + e);
        } finally {
            try {
                pst.close();
                conn.close();

            } catch (Exception e) {
                System.out.println("Exception in addUsr " + e);
            }
        }
        return false;

    }
     public static boolean UpdateUser(UserBean objb) {
        Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareStatement("update usermaster set username=?,userpassword=?,usertype=?,name=?,email=?,contact=?,address=? where userid =? ");
            pst.setInt(8, objb.getUserid());
            pst.setString(1, objb.getUsername());
            pst.setString(2, objb.getPassword());
            pst.setString(3, objb.getUsertype());
            pst.setString(4, objb.getName());
            pst.setString(5, objb.getEmail());
            pst.setString(6, objb.getContact());
           pst.setString(7, objb.getAddress());
           
            int i = pst.executeUpdate();
            if (i > 0) {
                return true;
            }

        } catch (Exception e) {
            System.out.println("Exception in Update" + e);
        } finally {
            try {
                pst.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("Exception in finally of Update" + e);
            }
        }

        return false;
    }

    public static boolean checkAvailableUsername(String un) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareStatement("select username from usermaster where username =?");
            pst.setString(1, un);
            rst = pst.executeQuery();
            if (rst.next()) {
                return false;
            }

        } catch (Exception e) {
            System.out.println("Exception in checkAvailability" + e);
        } finally {
            try {
                pst.close();
                conn.close();
                rst.close();
            } catch (Exception e) {
                System.out.println("Exception in finally of getAllEmployees" + e);
            }
        }
        return true;
    }

    public static int getMaxUserid() {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareStatement("select Max(userid) from usermaster");

            rst = pst.executeQuery();
            rst.next();
            int i = rst.getInt("Max(userid)");
            return ++i;
        } catch (Exception e) {
            System.out.println("Exception in getmaxuserid" + e);
        } finally {
            try {
                pst.close();
                conn.close();
                rst.close();
            } catch (Exception e) {
                System.out.println("Exception in finally of getmaxuserid" + e);
            }
        }

        return 0;
    }

    public static ArrayList<UserBean> getAllUsers() {
        ArrayList<UserBean> emp = null;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rst = null;

        try {
            conn = DBConnection.connect();
            pst = conn.prepareStatement("select * from usermaster");
            rst = pst.executeQuery();
            emp = new ArrayList<UserBean>();
            while (rst.next()) {
                UserBean objbean = new UserBean();
                objbean.setUserid(rst.getInt("userid"));
                objbean.setUsername(rst.getString("username"));
                objbean.setPassword(rst.getString("password"));
                objbean.setUsertype(rst.getString("usertype"));
                objbean.setName(rst.getString("name"));
                objbean.setEmail(rst.getString("email"));
                objbean.setContact(rst.getString("contact"));
                objbean.setAddress(rst.getString("address"));
                emp.add(objbean);
            }
            return emp;

        } catch (Exception e) {
            System.out.println("Exception in getAllEmployees" + e);
        } finally {
            try {
                pst.close();
                conn.close();
                rst.close();
            } catch (Exception e) {
                System.out.println("Exception in finally of getAllEmployees" + e);
            }
        }
        return null;

    }
    
}
