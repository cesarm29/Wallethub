import { Injectable } from '@angular/core';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';

@Injectable()
export class FileService {

  constructor(private http: Http) { }
  //service to backend api mysql insert file in .txt or .log
  uploadDatasource(formData) {
    //headers 
    let headers = new Headers();
    headers.append('Accept', 'application/json');
    let options = new RequestOptions({ headers: headers });
    return this.http.post('http://localhost:8081/BackendWalletHub/api/archivos/upload', formData, options)
      .map((response: Response) => response.json())
  }

  //service to backend api get data file from form search
  getDatasource(date, hour, duration, threshold) {
    //json body send 
    var body = "date="+date+"&hour="+hour+"&duration="+duration+"&threshold="+threshold;
    //headers
    let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' });
    let options = new RequestOptions({ headers: headers });
    return this.http.post('http://localhost:8081/BackendWalletHub/api/archivos/obtainDataSearchMysql', body, options)
      .map((response: Response) => response.json())
  }

    //service to backend api get data mysql log_search 
    getDatasourceMysql() {
      //json body send 
      var body = "";
      //headers
      let headers = new Headers({ 'Content-Type': 'application/x-www-form-urlencoded' });
      let options = new RequestOptions({ headers: headers });
      return this.http.post('http://localhost:8081/BackendWalletHub/api/archivos/obtainDataSearchMysqlLog', body, options)
        .map((response: Response) => response.json())
    }






}
