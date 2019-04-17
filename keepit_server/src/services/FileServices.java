/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import bean.FileBean;
import db.DBConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author Hp
 */
public class FileServices {
    public static boolean addFile(FileBean objbean)
    {
         Connection conn = null;
        PreparedStatement pst = null;
        try {
            conn = DBConnection.connect();
            pst = conn.prepareStatement("insert into  filemaster(fileid,userid,filename,date,size,fileaccess) values (?,?,?,?,?,?)");
        pst.setInt(1, objbean.getFileid());
        pst.setInt(2, objbean.getUserid());
        pst.setString(3, objbean.getFilename());
        pst.setString(4, objbean.getDate());
        pst.setInt(5, objbean.getSize());
        pst.setBoolean(6, objbean.isFileaccess());
        }
        
        catch(Exception e)
        {}
        finally
        {}
        return false;
    }
    
public static boolean updateFile(int userid, String filename, String newname) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBConnection.connect();
            pstmt = conn.prepareStatement("update filemaster set filename=? where userid=? and filename=?");
            pstmt.setString(1, newname);
            System.out.println(newname);
            pstmt.setInt(2, userid);
            pstmt.setString(3, filename);
            int i = pstmt.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Exception in updateFile : " + e);
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Exception in finally of updateFile : " + e);
            }
        }
        return false;
    }
public static boolean deleteFile(int userid, String filename) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBConnection.connect();
            pstmt = conn.prepareStatement("delete from filemaster where userid=? and filename=?");
            pstmt.setInt(1, userid);
            pstmt.setString(2, filename);
            int i = pstmt.executeUpdate();
            if (i > 0) {
                return true;
            }
        } catch (Exception e) {
            System.out.println("Exception in deleteFile : " + e);
        } finally {
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("Exception in finally of deleteFile : " + e);
            }
        }
        return false;
    }

    
    public static boolean copyFile(String srcpath,String dstpath) {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        try {
            fi = new FileInputStream(srcpath);
            fo = new FileOutputStream(new File(dstpath));
            int c;
            byte[] b = new byte[1024];
            while ((c = fi.read(b)) != -1) {
                fo.write(b, 0, c);
            }
            return true;
        } catch (Exception e) {
            System.out.println("Exception in copyFile : "+e);
        } finally {
            try {
                fo.close();
                fi.close();
            } catch (Exception e) {
                System.out.println("Exception in finally of copyFile : "+e);
            }
        }
        return false;
    }
    
    
}
