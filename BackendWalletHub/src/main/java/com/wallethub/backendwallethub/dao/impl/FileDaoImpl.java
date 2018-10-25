/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wallethub.backendwallethub.dao.impl;

import com.wallethub.backendwallethub.dao.AbstractDao;
import com.wallethub.backendwallethub.dao.IFileDao;
import com.wallethub.backendwallethub.vo.SearchDataVo;
import com.wallethub.backendwallethub.vo.SearchLogDataVo;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cesar
 */
@Repository("FileDao")
public class FileDaoImpl extends AbstractDao implements IFileDao {

    //save file into mysql
    @Override
    public boolean saveFileIntoMysql(byte[] bytes) {
        //init session 
        Session session = getSession();
        String squery = "";
        SQLQuery query = null;
        int filas = 0;
        //convert bytes for string
        String completeData = new String(bytes);
        //split for rows
        String[] rows = completeData.split("\\n");
        //sum total rows
        long sum = rows.length;
        //loop for sum rows to columns
        for (int i = 0; i < sum; i++) {
            String[] columns = rows[i].split("\\|");
            //insert  load_log data
            squery = "INSERT INTO load_log  (datetime, ip, action, state, navigator) values ('" + columns[0] + "','" + columns[1] + "', " + columns[2] + ", " + columns[3] + " , " + columns[4] + "  ) ";
            //execute
            query = session.createSQLQuery(squery);
            //count execute update query
            filas = filas + query.executeUpdate();
        }
        //validation of insert
        if (filas == 1) {
            return true;
        } else {
            return false;
        }
    }

    //get data search file
    @Override
    public ArrayList<SearchDataVo> getDataFile(String date, String hour, String duration, String threshold) {
        //init session 
        Session session = getSession();
        String squeryCreate = "";
        SQLQuery queryCreate = null;
        int filas = 0;
        ArrayList<SearchDataVo> dataList = new ArrayList<>();
        Session sesion = getSession();
        //format generic for data bd mysql
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //validacion of hourly of daily
        if (duration.equals("hourly")) {
            //part of hour
            try {
                //date format parse string
                Date dates = formatter.parse(date + " " + hour);
                //calendar instance
                Calendar calendar = Calendar.getInstance();
                //date receives
                calendar.setTime(dates);
                //number for add hours
                calendar.add(Calendar.HOUR, 1);
                //result of time
                Date resultado = calendar.getTime();
                //date format parse string + 1 hour
                String dateAddHour = formatter.format(resultado);
                //validation vars != empty
                if (!threshold.isEmpty()) {
                    String squery = "SELECT datetime,ip, action, state, navigator from `load_log` where  ip in(SELECT ip FROM `load_log` GROUP BY ip HAVING COUNT(ip) >= " + threshold + " ) ";
                    if (!date.isEmpty() && !hour.isEmpty()) {
                        squery = squery + " and  datetime  BETWEEN '" + date + " " + hour + "' AND '" + dateAddHour + "'  ";
                    }
                    SQLQuery query = sesion.createSQLQuery(squery);
                    ArrayList<Object[]> dataObject = (ArrayList<Object[]>) query.list();

                    if (dataObject.size() > 0) {
                        //select the ip
                        String squeryIp = "SELECT ip FROM `load_log` GROUP BY ip  HAVING COUNT(ip) >= " + threshold + "  ";
                        SQLQuery queryIp = sesion.createSQLQuery(squeryIp);
                        ArrayList<Object[]> dataObjectIp = (ArrayList<Object[]>) queryIp.list();
                        //loop for sum rows to columns
                        for (int i = 0; i < dataObjectIp.size(); i++) {
                            //insert in log data from mysql
                            squeryCreate = "INSERT INTO log_search  (ip, comment) values ('" + dataObjectIp.get(i) + "',' has " + threshold + " or more requests between " + date + " " + hour + " and " + dateAddHour + " '  ) ";
                            //execute
                            queryCreate = session.createSQLQuery(squeryCreate);
                            //count execute update query
                            filas = filas + queryCreate.executeUpdate();
                        }
                        //cicle for objects SearchDataVo
                        for (Object[] object : dataObject) {
                            SearchDataVo dataType = mapeoGetDataFile(object);
                            dataList.add(dataType);
                        }
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        } else {
            //part of daily
            try {
                //date format parse string
                Date dates = formatter.parse(date + " " + hour);
                //calendar instance
                Calendar calendar = Calendar.getInstance();
                //date receives
                calendar.setTime(dates);
                //number for add hours
                calendar.add(Calendar.DAY_OF_YEAR, 1);
                //result of time
                Date resultado = calendar.getTime();
                //date format parse string + 1 day
                String dateAddDay = formatter.format(resultado);
                //validation vars != empty
                if (!threshold.isEmpty()) {
                    String squery = "SELECT datetime,ip, action, state, navigator from `load_log` where  ip in(SELECT ip FROM `load_log` GROUP BY ip HAVING COUNT(ip)  >= " + threshold + " ) ";
                    if (!date.isEmpty() && !hour.isEmpty()) {
                        squery = squery + " and  datetime  BETWEEN '" + date + " " + hour + "' AND '" + dateAddDay + "' GROUP BY datetime asc  ";
                    }
                    SQLQuery query = sesion.createSQLQuery(squery);
                    ArrayList<Object[]> dataObject = (ArrayList<Object[]>) query.list();

                    if (dataObject.size() > 0) {
                        //select the ip
                        String squeryIp = "SELECT ip FROM `load_log` GROUP BY ip  HAVING COUNT(ip)  >= " + threshold + "  ";
                        SQLQuery queryIp = sesion.createSQLQuery(squeryIp);
                        ArrayList<Object[]> dataObjectIp = (ArrayList<Object[]>) queryIp.list();
                        //loop for sum rows to columns
                        for (int i = 0; i < dataObjectIp.size(); i++) {
                            //insert in log data from mysql
                            squeryCreate = "INSERT INTO log_search  (ip, comment) values ('" + dataObjectIp.get(i) + "',' has " + threshold + " or more requests between " + date + " " + hour + " and " + dateAddDay + " '  ) ";
                            //execute
                            queryCreate = session.createSQLQuery(squeryCreate);
                            //count execute update query
                            filas = filas + queryCreate.executeUpdate();
                        }
                        for (Object[] object : dataObject) {
                            SearchDataVo dataType = mapeoGetDataFile(object);
                            dataList.add(dataType);
                        }
                    }
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return dataList;
    }

    //generate mapping from data file
    public SearchDataVo mapeoGetDataFile(Object[] retorno) {
        SearchDataVo data = new SearchDataVo();
        data.setDateTime(retorno[0] != null ? retorno[0].toString() : null);
        data.setIp(retorno[1] != null ? retorno[1].toString() : null);
        data.setAction(retorno[2] != null ? retorno[2].toString() : null);
        data.setState(retorno[3] != null ? (Integer) retorno[3] : 0);
        data.setNavigator(retorno[4] != null ? retorno[4].toString() : null);
        return data;
    }

    //get data from mysql table log_search
    @Override
    public ArrayList<SearchLogDataVo> getDataFileMysql() {
        //init session 
        Session session = getSession();
        String squeryCreate = "";
        SQLQuery queryCreate = null;
        int filas = 0;

        ArrayList<SearchLogDataVo> dataList = new ArrayList<>();
        Session sesion = getSession();
        //query select all for log_search
        String squery = "SELECT ip,comment from `log_search` ";
        SQLQuery query = sesion.createSQLQuery(squery);
        ArrayList<Object[]> dataObject = (ArrayList<Object[]>) query.list();
        //validation data
        if (dataObject.size() > 0) {
            //cicle for objects SearchDataVo
            for (Object[] object : dataObject) {
                SearchLogDataVo dataType = mapeoGetDataFileMysql(object);
                dataList.add(dataType);
            }
        }
        return dataList;
    }

    //generate mapping from mysql log data
    public SearchLogDataVo mapeoGetDataFileMysql(Object[] retorno) {
        SearchLogDataVo data = new SearchLogDataVo();
        data.setIp(retorno[0] != null ? retorno[0].toString() : null);
        data.setComment(retorno[1] != null ? retorno[1].toString() : null);
        return data;
    }

}
