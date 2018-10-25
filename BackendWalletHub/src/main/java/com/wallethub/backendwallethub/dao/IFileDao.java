/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wallethub.backendwallethub.dao;

import com.wallethub.backendwallethub.vo.SearchDataVo;
import com.wallethub.backendwallethub.vo.SearchLogDataVo;
import java.util.ArrayList;

/**
 *
 * @author cesar
 */
public interface IFileDao {
    //save file into mysql
    public boolean saveFileIntoMysql(byte[] bytes);
    //get data file search from mysql
    public ArrayList<SearchDataVo> getDataFile(String date,String hour,String duration,String threshold);
    //get data from mysql
    public ArrayList<SearchLogDataVo> getDataFileMysql();

    
    
}
