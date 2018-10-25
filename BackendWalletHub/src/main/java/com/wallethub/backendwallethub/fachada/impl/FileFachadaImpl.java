/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wallethub.backendwallethub.fachada.impl;

import com.wallethub.backendwallethub.dao.IFileDao;
import com.wallethub.backendwallethub.fachada.IFileFachada;
import com.wallethub.backendwallethub.vo.FileInfoVo;
import com.wallethub.backendwallethub.vo.SearchDataVo;
import com.wallethub.backendwallethub.vo.SearchLogDataVo;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cesar
 */
@Service("FileService")
@Transactional
public class FileFachadaImpl implements IFileFachada {
    
private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(FileFachadaImpl.class);
@Autowired
private IFileDao fileDao; 

    @Override
    public String saveFileIntoMysql(byte[] bytes) {
        String mensaje = "";
        boolean banderaInsert = true;
        try {
            //guardo reservacion
            banderaInsert = banderaInsert && fileDao.saveFileIntoMysql(bytes);
            //valido insert    
            if (banderaInsert) {
                mensaje = "Guardado satisfactoriamente";
            } else {
                
                mensaje = "Error al guardar archivo";
            }
        } catch (Exception e) {
            LOGGER.log(org.apache.logging.log4j.Level.ERROR, e.getMessage(), e);
        }
        return mensaje;
    }

    
    @Override
    public ArrayList<SearchDataVo> getDataFile(String date, String hour, String duration, String threshold) {
        
        ArrayList<SearchDataVo> dataFileList = new ArrayList<>();
        try {
            dataFileList = fileDao.getDataFile(date, hour, duration, threshold);
        } catch (Exception ex) {
            LOGGER.log(org.apache.logging.log4j.Level.ERROR, ex.getMessage(), ex);
        }
        return dataFileList;
        
        
    }

    @Override
    public ArrayList<SearchLogDataVo> getDataFileMysql() {
        ArrayList<SearchLogDataVo> dataFileList = new ArrayList<>();
        try {
            dataFileList = fileDao.getDataFileMysql();
        } catch (Exception ex) {
            LOGGER.log(org.apache.logging.log4j.Level.ERROR, ex.getMessage(), ex);
        }
        return dataFileList;
    }
    
    
}
