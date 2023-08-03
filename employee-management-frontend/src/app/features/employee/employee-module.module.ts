import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { EmployeeModuleRoutingModule } from './employee-module-routing.module';
import { CreateEmployeeComponent } from './component/create-employee/create-employee.component';
import { FormsModule } from '@angular/forms';
import { UpdateEmployeeComponent } from './component/update-employee/update-employee.component';
import { EmployeeDetailsComponent } from './component/employee-details/employee-details.component';
import { EmployeeListComponent } from "./component/employee-list/employee-list.component";
import { HttpClientModule } from "@angular/common/http";

@NgModule({
  declarations: [
    CreateEmployeeComponent,
    UpdateEmployeeComponent,
    EmployeeDetailsComponent,
    EmployeeListComponent
  ],
  imports: [CommonModule, EmployeeModuleRoutingModule, FormsModule, HttpClientModule],
})
export class EmployeeModuleModule {}
