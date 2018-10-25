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
public class FileInfoVo {
    
    private String fileName;
    private long fileSize;
    private byte[] archivo;
    private String mensaje;

    public FileInfoVo() {
    }

    public FileInfoVo(String fileName, long fileSize, byte[] archivo, String mensaje) {
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.archivo = archivo;
        this.mensaje = mensaje;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    } 
}
