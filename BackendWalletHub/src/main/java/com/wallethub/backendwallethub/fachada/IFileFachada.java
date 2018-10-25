/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wallethub.backendwallethub.fachada;

import com.wallethub.backendwallethub.vo.SearchDataVo;
import com.wallethub.backendwallethub.vo.SearchLogDataVo;
import java.util.ArrayList;

/**
 *
 * @author cesar
 */
public interface IFileFachada {
    //save file to mysql
    public String saveFileIntoMysql(byte[] bytes);
    //get list of check log file
    public ArrayList<SearchDataVo> getDataFile(String date,String hour,String duration,String threshold);
    //get list of log mysql
    public ArrayList<SearchLogDataVo> getDataFileMysql();
    
}
