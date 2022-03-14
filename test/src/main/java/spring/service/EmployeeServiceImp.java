package spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.dao.EmployedaoInte;
import spring.entity.Employee;

import java.util.List;

@Service
public class EmployeeServiceImp implements EmployeeService {

    @Autowired
    private EmployedaoInte employedaoInte;



    @Override
    @Transactional
    public List<Employee> getEmployes() {
        return employedaoInte.getEmployes();
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
         employedaoInte.saveEmployee(employee);
    }

    @Override
    @Transactional
    public void updateEmployee(Employee employee) {
        employedaoInte.updateEmployee(employee);
    }
    @Override
    @Transactional
    public Employee getEmployee(Long theId) {
        return employedaoInte.getEmployee(theId);
    }

    @Override
    @Transactional
    public void deleteEmployee(Long theId) {
        employedaoInte.deleteEmployee(theId);
    }

    @Override
    @Transactional
    public Employee findByEmail(String email) {
        return employedaoInte.findByEmail(email);
    }
}
