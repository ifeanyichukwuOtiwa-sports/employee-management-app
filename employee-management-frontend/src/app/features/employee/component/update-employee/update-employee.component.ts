import {Component, OnInit} from '@angular/core';
import {EmployeeManagementService} from "../../service/employee-management.service";
import {ActivatedRoute, Router} from "@angular/router";
import {EmployeeDto} from "../../types/employee-management-type";

@Component({
    selector: 'app-update-employee',
    templateUrl: './update-employee.component.html',
    styleUrls: ['./update-employee.component.css'],
})
export class UpdateEmployeeComponent implements OnInit {
    id: string = "";
    employee: EmployeeDto = {email: "", firstName: "", id: "", lastName: ""};

    constructor(private employeeService: EmployeeManagementService,
                private route: ActivatedRoute,
                private router: Router) {
    }


    ngOnInit(): void {
        this.employeeService.getEmployeeById(this.id).subscribe(data => {
                this.employee = data;
            },
            error => console.log(error)
        );
    }


    onSubmit() {
        this.employeeService.updateEmployee(this.id, this.employee)
            .subscribe(data => this.goToEmployeeList()
                , error => console.log(error)
            );
    }

    goToEmployeeList() {
        this.router.navigate(['/employees']);
    }
}
