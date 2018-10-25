/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wallethub.backendwallethub.controller;

import com.wallethub.backendwallethub.fachada.IFileFachada;
import com.wallethub.backendwallethub.vo.FileInfoVo;
import com.wallethub.backendwallethub.vo.SearchDataVo;
import com.wallethub.backendwallethub.vo.SearchLogDataVo;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author cesar
 */
@RestController
@RequestMapping("/api/archivos")
public class FileController {
    
    
    private static final org.apache.logging.log4j.Logger LOGGER = org.apache.logging.log4j.LogManager.getLogger(FileController.class);
    @Autowired
    private IFileFachada fileFachada;
    
    //save file
    @RequestMapping(value = "/upload", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileInfoVo> uploadFile(@RequestParam("file") MultipartFile file) { 
        LOGGER.log(org.apache.logging.log4j.Level.INFO, "upload");
        if(!file.isEmpty()){
            String mensaje = "";
            try{
                 byte[] bytes = file.getBytes();  
                 mensaje = fileFachada.saveFileIntoMysql(bytes);
                //send data file
                FileInfoVo fileInfo = new FileInfoVo();
                fileInfo.setMensaje(mensaje);
                //send headers
                HttpHeaders headers = new HttpHeaders();               
                return new ResponseEntity<FileInfoVo>(fileInfo,headers,HttpStatus.OK);            
            }catch(Exception ex){
                return new ResponseEntity<FileInfoVo>(HttpStatus.BAD_REQUEST);
            }    
        }else{
            return new ResponseEntity<FileInfoVo>(HttpStatus.BAD_REQUEST);
        } 
    }
    
    
   // get data for filter search form
   @RequestMapping(value = "/obtainDataSearchMysql", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<SearchDataVo>> obtainDataSearchMysql(@RequestParam(value = "date") String date, @RequestParam(value = "hour") String hour, @RequestParam(value = "duration") String duration, @RequestParam(value = "threshold") String threshold) {
        try {
            LOGGER.log(org.apache.logging.log4j.Level.INFO, "obtainDataSearchMysql");
            ArrayList<SearchDataVo> dataFile = new ArrayList<>();
            dataFile = fileFachada.getDataFile(date, hour, duration, threshold);
            HttpHeaders headers = new HttpHeaders();
            LOGGER.log(org.apache.logging.log4j.Level.INFO, "Antes de responder");
            return new ResponseEntity<ArrayList<SearchDataVo>>(dataFile, headers, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.log(org.apache.logging.log4j.Level.ERROR, ex.getMessage(), ex);
            return new ResponseEntity<ArrayList<SearchDataVo>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    // get data for mysql log_search
   @RequestMapping(value = "/obtainDataSearchMysqlLog", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ArrayList<SearchLogDataVo>> obtainDataSearchMysqlLog() {
        try {
            LOGGER.log(org.apache.logging.log4j.Level.INFO, "obtainDataSearchMysqlLog");
            ArrayList<SearchLogDataVo> dataFile = new ArrayList<>();
            dataFile = fileFachada.getDataFileMysql();
            HttpHeaders headers = new HttpHeaders();
            LOGGER.log(org.apache.logging.log4j.Level.INFO, "Antes de responder");
            return new ResponseEntity<ArrayList<SearchLogDataVo>>(dataFile, headers, HttpStatus.OK);
        } catch (Exception ex) {
            LOGGER.log(org.apache.logging.log4j.Level.ERROR, ex.getMessage(), ex);
            return new ResponseEntity<ArrayList<SearchLogDataVo>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    
    
    
    
}
