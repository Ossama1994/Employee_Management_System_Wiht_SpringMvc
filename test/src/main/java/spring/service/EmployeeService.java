package spring.service;

import spring.entity.Employee;

import java.util.List;

public interface EmployeeService {

    public List<Employee> getEmployes();
    public void saveEmployee(Employee employee);
    public void updateEmployee(Employee employee);
    public Employee getEmployee(Long theId) ;
    public void deleteEmployee(Long theId);
    public Employee findByEmail(String email);
}
