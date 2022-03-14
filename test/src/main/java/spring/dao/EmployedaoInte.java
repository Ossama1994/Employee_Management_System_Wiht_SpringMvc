package spring.dao;

import spring.entity.Employee;

import java.util.List;

public interface EmployedaoInte {

    List<Employee> getEmployes();

    void saveEmployee(Employee employee);

    void updateEmployee(Employee employee);

    Employee getEmployee(Long Id);

    void deleteEmployee(Long theId);

    Employee findByEmail(String email);
}
