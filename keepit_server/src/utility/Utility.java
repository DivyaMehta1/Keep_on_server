/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utility;

import java.awt.Component;
import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author Hp
 */
public class Utility {

    public static void showMessage(Component c, String msg, int flag) {
        if (flag == 0) {
            JOptionPane.showMessageDialog(c, msg, "MSG", JOptionPane.INFORMATION_MESSAGE);
        } else if (flag == 1) {
            JOptionPane.showMessageDialog(c, msg, "MSG", JOptionPane.WARNING_MESSAGE);
        } else if (flag == 2) {
            JOptionPane.showMessageDialog(c, msg, "MSG", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void showImage(JLabel img,String path)
    {
        ImageIcon imgicon = new ImageIcon(path);
       img.setIcon(new ImageIcon(imgicon.getImage().getScaledInstance(100, 100,Image.SCALE_DEFAULT )));
       //writeData(path,"1.jpg");
    }

    public static void writeData(String path, String filename) {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        try {
            fi = new FileInputStream(path);
            fo = new FileOutputStream(new File("C:\\Users\\Hp\\Documents\\NetBeansProjects\\user_maintenance\\images\\"+filename));
            int c;
            byte b[] = new byte[1024];
            while ((c = fi.read(b)) != -1) // returntpe of read is int .. returns index of byte array			{
            {
                fo.write(b, 0, c);
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            try {
                fo.close();
                fi.close();
            } catch (Exception e) {
                System.out.println("Exception in writeDAta"+e);
                
            }

        }
    }

}
