import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Router, ActivatedRoute } from '@angular/router';
import { FileService } from '../services/file.service';
import { DataTablesModule } from 'angular-datatables';
import { Subject } from 'rxjs/Rx';
declare var $: any;

@Component({
  selector: 'app-log-mysql',
  templateUrl: './log-mysql.component.html',
  styleUrls: ['./log-mysql.component.css']
})
export class LogMysqlComponent implements OnInit {

  //var list
  list: {};
  //options datatable
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();

  constructor(private formBuilder: FormBuilder, private router: Router, private newService: FileService) { }

  ngOnInit() {
    //init get all table log_search
    this.getDataAllMysql();
    //init options data table
    this.dtOptions = {   
      pageLength: 10,
      search: true,
      paging: true,
    };
  }
 //get data all from mysql table log_search
  getDataAllMysql() {
      //send to ws
      this.newService.getDatasourceMysql()
        .subscribe(
          response => {
            console.log(response);
            //list to table
            this.list = response;
            // Calling the DT trigger to manually render the table
            this.dtTrigger.next();
          },
          error => {
            console.log('error', error)
          });
    }
  

}
