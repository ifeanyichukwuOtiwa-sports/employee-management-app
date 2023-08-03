import {Component, OnInit} from '@angular/core';
import {EmployeeDto} from "../../types/employee-management-type";
import {Router} from "@angular/router";
import {EmployeeManagementService} from "../../service/employee-management.service";

@Component({
  selector: 'app-create-employee',
  templateUrl: './create-employee.component.html',
  styleUrls: ['./create-employee.component.css']
})
export class CreateEmployeeComponent implements OnInit {
  employee: EmployeeDto = {email: "", firstName: "", id: "", lastName: ""};
  constructor(private employeeService: EmployeeManagementService,
              private router: Router) { }

  ngOnInit(): void {}

  saveEmployee(){
    this.employeeService.createEmployee(this.employee).subscribe( data =>{
          console.log(data);
          this.goToEmployeeList();
        },
        error => console.log(error));
  }

  goToEmployeeList(){
    this.router.navigate(['/employees']);
  }

  onSubmit(){
    console.log(this.employee);
    this.saveEmployee();
  }
}
