import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {EmployeeManagementService} from "../../service/employee-management.service";
import {EmployeeDto} from "../../types/employee-management-type";

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css'],
})
export class EmployeeDetailsComponent implements OnInit {
  id: string = "";
  employee: EmployeeDto = <EmployeeDto>{};
  constructor(
      private route: ActivatedRoute,
      private employeeManagementService: EmployeeManagementService
  ) { }

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];
    this.employeeManagementService.getEmployeeById(this.id).subscribe( data => {
      this.employee = data;
    });
  }
}
