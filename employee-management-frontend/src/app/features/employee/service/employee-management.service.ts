import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http'
import {map, Observable} from "rxjs";
import {EmployeeDto, ListResponse, RestResponse} from "../types/employee-management-type";

@Injectable({
  providedIn: 'root'
})
export class EmployeeManagementService {
  private BASE_URL: string = 'http://localhost:8080/api/employees';

  constructor(
      private httpClient: HttpClient
  ) { }

  public getEmployeesList(): Observable<EmployeeDto[]>{
    return this.httpClient.get<ListResponse<EmployeeDto>>(`${this.BASE_URL}/list`).pipe(
        map(resp => resp.items)
    );
  }

  public createEmployee(employee: EmployeeDto): Observable<EmployeeDto>{
    return this.httpClient.post<RestResponse<EmployeeDto>>(`${this.BASE_URL}/save`, employee).pipe(
        map(resp => resp.employeeItem)
    );
  }

  public getEmployeeById(id: string): Observable<EmployeeDto>{
    return this.httpClient.get<RestResponse<EmployeeDto>>(`${this.BASE_URL}/${id}`).pipe(
        map(resp => resp.employeeItem)
    );
  }

  public updateEmployee(id: string, employee: EmployeeDto): Observable<EmployeeDto>{
    return this.httpClient.put<RestResponse<EmployeeDto>>(`${this.BASE_URL}/${id}`, employee).pipe(
        map(resp => resp.employeeItem)
    );
  }

  public deleteEmployee(id: string): Observable<Object>{
    return this.httpClient.delete(`${this.BASE_URL}/${id}`);
  }
}
