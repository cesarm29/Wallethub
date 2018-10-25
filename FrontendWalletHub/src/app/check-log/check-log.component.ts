import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Router, ActivatedRoute } from '@angular/router';
import { FileService } from '../services/file.service';
import { DataTablesModule } from 'angular-datatables';
import { Subject } from 'rxjs/Rx';
declare var $: any;


@Component({
  selector: 'app-check-log',
  templateUrl: './check-log.component.html',
  styleUrls: ['./check-log.component.css']
})
export class CheckLogComponent implements OnInit {
  model: any = {};
  msg: string = '';
  msgex: string = '';
  checkForm: FormGroup;
  submitted = false;
  //var listado check 
  listaCheck: {};

  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();



  constructor(private formBuilder: FormBuilder, private router: Router, private newService: FileService) { }

  ngOnInit() {
    this.checkForm = this.formBuilder.group({
      date: ['', Validators.required],
      hour: ['', Validators.required],
      duration: ['', Validators.required],
      threshold: ['', Validators.compose([
        Validators.required,
        Validators.max(500), Validators.min(1)
      ])]

    });

    this.dtOptions = {   
      pageLength: 10,
      search: true,
      paging: true,
    };
  }



  get f() { return this.checkForm.controls; }

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.checkForm.invalid) {
      return;
    } else {
      //Destroy data when performing more than one search
      $('#DataTables_Table_0').DataTable().destroy();
      //send to ws
      this.newService.getDatasource(this.checkForm.value.date, this.checkForm.value.hour, this.checkForm.value.duration, this.checkForm.value.threshold)
        .subscribe(
          response => {
            console.log(response);
            //list to table
            this.listaCheck = response;
            // Calling the DT trigger to manually render the table
            this.dtTrigger.next();
            //message
            this.msgex = 'get data';
          },
          error => {
            console.log('error', error)
          });


    }
  }

  

  

}
