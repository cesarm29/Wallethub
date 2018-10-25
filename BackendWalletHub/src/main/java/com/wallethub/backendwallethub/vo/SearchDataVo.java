/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wallethub.backendwallethub.vo;

/**
 *
 * @author cesar
 */
public class SearchDataVo {
    
   //data response to query mysql to table search
    private String dateTime;
    private String ip;
    private String action;
    private int state;
    private String navigator;
    //constructor empty
    public SearchDataVo() {
    }
    //constructor all data
    public SearchDataVo(String dateTime, String ip, String action, int state, String navigator) {
        
        this.dateTime = dateTime;
        this.ip = ip;
        this.action = action;
        this.state = state;
        this.navigator = navigator;
    }
    //getters and setters
    

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getNavigator() {
        return navigator;
    }

    public void setNavigator(String navigator) {
        this.navigator = navigator;
    }          
}
