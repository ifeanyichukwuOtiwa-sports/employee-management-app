import {Component, OnInit} from '@angular/core';
import {EmployeeManagementService} from "../../service/employee-management.service";
import {Router} from "@angular/router";
import {EmployeeDto} from "../../types/employee-management-type";
import {map} from "rxjs";

@Component({
    selector: 'app-employee-list',
    templateUrl: './employee-list.component.html',
    styleUrls: ['./employee-list.component.css'],
})
export class EmployeeListComponent implements OnInit {

    public employees: EmployeeDto[] = [];

    constructor(
        private employeeManagementService: EmployeeManagementService,
        private router: Router
    ) {
    }

    ngOnInit(): void {
        this.getEmployees();
    }

    private getEmployees() {
        this.employeeManagementService.getEmployeesList().subscribe(data => {
            this.employees = data;
        });
    }

    public employeeDetails(id: string) {
        this.router.navigate(['employee-details', id]);
    }

    public updateEmployee(id: string) {
        this.router.navigate(['update-employee', id]);
    }

    public deleteEmployee(id: string) {
        this.employeeManagementService.deleteEmployee(id).subscribe(data => {
            console.log(data);
            this.getEmployees();
        })
    }
}
