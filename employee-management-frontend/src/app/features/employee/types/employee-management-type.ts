export interface EmployeeDto {
    id: string;
    firstName: string;
    lastName: string;
    email: string;
}

export interface RestResponse<T> {
    employeeItem: T;
    hasItem: boolean;
}

export interface ListResponse<T> {
    items: T[];
    count: number;
}