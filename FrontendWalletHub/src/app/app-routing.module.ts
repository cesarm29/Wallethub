import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { MenuComponent } from './menu/menu.component';
import { LoadLogComponent } from './load-log/load-log.component';
import { CheckLogComponent } from './check-log/check-log.component';
import { LogMysqlComponent } from './log-mysql/log-mysql.component'; 

//const routes
const routes: Routes = [
		{
            path: '',
            component: MenuComponent,
        },
        {
            path: 'loadLog',
            component: LoadLogComponent,
        },
        {
            path: 'checkLog',
            component: CheckLogComponent,
        },
        {
            path: 'mysqlLog',
            component: LogMysqlComponent,
        }
        
    ];


    @NgModule({
        imports: [
            RouterModule.forRoot(routes)
        ],
        exports: [
            RouterModule
        ],
        declarations: []
    })
    export class AppRoutingModule { }

