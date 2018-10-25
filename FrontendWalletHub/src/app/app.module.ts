import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { AppRoutingModule } from './app-routing.module';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { LoadLogComponent } from './load-log/load-log.component';
import { CheckLogComponent } from './check-log/check-log.component';
import { HttpClientModule } from '@angular/common/http';
import { HttpModule } from '@angular/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {FileService} from './services/file.service';
import { DataTablesModule } from 'angular-datatables';
import { LogMysqlComponent } from './log-mysql/log-mysql.component'; 

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    HeaderComponent,
    FooterComponent,
    LoadLogComponent,
    CheckLogComponent,
    LogMysqlComponent
  ],
  imports: [
    BrowserModule,
    NgbModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpModule,
    HttpClientModule,
    DataTablesModule
  ],
  providers: [FileService],
  bootstrap: [AppComponent]
})
export class AppModule { }
