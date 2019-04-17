/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author Hp
 */
public class ActivityBean {
    private String name,username;
    private String logintime,logouttime;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the logintime
     */
    public String getLogintime() {
        return logintime;
    }

    /**
     * @param logintime the logintime to set
     */
    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }

    /**
     * @return the logouttime
     */
    public String getLogouttime() {
        return logouttime;
    }

    /**
     * @param logouttime the logouttime to set
     */
    public void setLogouttime(String logouttime) {
        this.logouttime = logouttime;
    }
    
     
    
}
