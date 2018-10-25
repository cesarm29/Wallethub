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
public class SearchLogDataVo {
    
    private String ip;
    private String comment;

    public SearchLogDataVo() {
    }

    public SearchLogDataVo(String ip, String comment) {
        this.ip = ip;
        this.comment = comment;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    
    
}
