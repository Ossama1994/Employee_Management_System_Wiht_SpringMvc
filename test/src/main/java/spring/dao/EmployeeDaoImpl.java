package spring.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import spring.entity.Employee;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;

@Repository
public class EmployeeDaoImpl implements EmployedaoInte{

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Employee> getEmployes() {
        Session session = sessionFactory.getCurrentSession();
        List<Employee> EmployeeList = session.createQuery("from Employee").list();
        return EmployeeList;
    }

    @Override
    public void saveEmployee(Employee employee) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.saveOrUpdate(employee);
    }

    @Override
    public void updateEmployee(Employee employee) {
        Session currentSession = sessionFactory.getCurrentSession();
        currentSession.merge(employee);
    }

    @Override
    public Employee getEmployee(Long Id) {
        Employee employee = null;
        Session currentSession = sessionFactory.getCurrentSession();
        employee = currentSession.get(Employee.class, Id);
        return employee;
    }

    @Override
    public void deleteEmployee(Long theId) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.byId(Employee.class).load(theId);
        session.delete(employee);
    }

    @Override
    public Employee findByEmail(String email) throws NoResultException {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("SELECT e FROM Employee e WHERE e.email=:email", Employee.class);
        query.setParameter("email", email);
        query.setMaxResults(1);
        return  (Employee) query.getSingleResult();

    }

}


