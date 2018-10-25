import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Http, Response, Headers, RequestOptions } from '@angular/http';
import { Router, ActivatedRoute } from '@angular/router';
import { FileService } from '../services/file.service';


@Component({
  selector: 'app-load-log',
  templateUrl: './load-log.component.html',
  styleUrls: ['./load-log.component.css']
})
export class LoadLogComponent implements OnInit {
  //vars msj
  msg: string = '';
  msgex: string = '';
  loading: string = '';
  //var form
  loadForm: FormGroup;
  //var submitted
  submitted = false;
  //var listado load 
  listaLoad: {};

  selectedFile: File;
  newJson = '';


  constructor(private http: Http, private formBuilder: FormBuilder, private newService: FileService, private router: Router) { }

  ngOnInit() {
    //init form
    this.loadForm = this.formBuilder.group({
      files: ['', Validators.required]
    });
  }
  //get form controls
  get f() { return this.loadForm.controls; }

  //get txt to json for the ws
  onFileChange(event) {

    this.submitted = true;
    // stop here if form is invalid
    if (this.loadForm.invalid) {
      return;
    } else {
      //obtain the file
      let fileList: FileList = event.target.files;
      //validation
      if (fileList.length > 0) {
        this.loading = 'cargando';
        let file: File = fileList[0];
        let formData: FormData = new FormData();
        formData.append('file', file, file.name);
        //send to ws
        this.newService.uploadDatasource(formData)
          .subscribe(
            response => {
              this.msgex = 'uploading success';
              this.loading = '';
            },
            error => {
              console.log('error', error)
            });
      }
    }
  }


  closeAlert(): void {
    this.msg = '';
  }

  closeAlertEx(): void {
    this.msgex = '';
  }



}



