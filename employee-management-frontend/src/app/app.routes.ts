import { Route } from '@angular/router';
import {EmployeeListComponent} from "./features/employee/component/employee-list/employee-list.component";
import {CreateEmployeeComponent} from "./features/employee/component/create-employee/create-employee.component";
import {UpdateEmployeeComponent} from "./features/employee/component/update-employee/update-employee.component";
import {EmployeeDetailsComponent} from "./features/employee/component/employee-details/employee-details.component";

export const appRoutes: Route[] = [
    {path: 'employees', component: EmployeeListComponent},
    {path: 'create-employee', component: CreateEmployeeComponent},
    {path: '', redirectTo: 'employees', pathMatch: 'full'},
    {path: 'update-employee/:id', component: UpdateEmployeeComponent},
    {path: 'employee-details/:id', component: EmployeeDetailsComponent}
];
