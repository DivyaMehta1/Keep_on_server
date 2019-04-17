/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import db.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Hp
 */
       /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Hp
 */
public class UserActivityServices {

    private static String getCurrentTime() {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd hh:mm:ss");

        String date = sdf.format(d);
        return date;
    }

    public static int addLoginTime(int userid) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareStatement("insert into activitymaster (userid,logintime) values (?,?)");
            pst.setInt(1, userid);
            pst.setString(2, getCurrentTime());
            System.out.println(pst);

            int i = pst.executeUpdate();
            if (i > 0) {
                pst = conn.prepareStatement("select max(activityid) as activityid from activitymaster");
                rs = pst.executeQuery();
                if (rs.next()) {

                    return rs.getInt("activityid");
                }
            }
            return i;
        } catch (Exception e) {
            System.out.println("Exception in addloginTime" + e);
        } finally {
            try {
                rs.close();
                pst.close();
                conn.close();
            } catch (Exception e) {
                System.out.println("Exception in finally of addlogin");
            }
        }
        return 0;
    }

    public static boolean updateLogoutTime(int activity) {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareStatement(" update  activitymaster set logouttime =?  where activityid=?");
            pst.setInt(2, activity);
            pst.setString(1, getCurrentTime());
            System.out.println(pst);
            int i = pst.executeUpdate();
            if (i > 0) {
                return true;

            }
        } catch (Exception e) {
            System.out.println("Exception in addlogoutTime" + e);
        } finally {
            try {

                pst.close();

                conn.close();
            } catch (Exception e) {
                System.out.println("Exception in finally of addlogout" + e);
            }
        }
        return false;
    }

}

    

