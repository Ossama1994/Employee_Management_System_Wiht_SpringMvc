package spring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import spring.entity.Employee;
import spring.service.EmployeeService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/showForm")
    public String showFormForAdd(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employe", employee);
        return "addForm";
    }

    @GetMapping("/edit")
    public String showFormEdit(Model model,@RequestParam("id") Long id) {
        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employe", employee);
        return "editForm";
    }

    @PostMapping("/saveEmployee")
    @ResponseBody
    public String saveEmployee(@ModelAttribute("employee") Employee employe) {
        employeeService.saveEmployee(employe);
        return "redirect:/employee/list";
    }

    @PostMapping("/update")
    public String updateEmploye(@ModelAttribute("employee") Employee employe){
        employeeService.updateEmployee(employe);
        return "redirect:/employee/list";
    }


    @GetMapping("/list")
    public String listCustomers(Model theModel) {
        List<Employee> employees = employeeService.getEmployes();
        theModel.addAttribute("employe", employees);
        return "list-employees";
    }
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("id") Long theId) {
        employeeService.deleteEmployee(theId);
        return "redirect:/employee/list";
    }
    @GetMapping("/login")
    public String showLoginForm(){
        return "login";
    }

/*    @GetMapping("/page")
    public ModelAndView passParametersWithModelAndView() {
        ModelAndView modelAndView = new ModelAndView("welcome");
        modelAndView.addObject("message", "Baeldung");
        return modelAndView;
    }*/

    @PostMapping("/Signin")
    public String login(
                        @ModelAttribute(value ="employe") Employee emp ,
                        ModelMap model ,
                        HttpSession session)
    {
        if (employeeService.findByEmail(emp.getEmail()) != null)
        {
            session.setAttribute("SessinoName" ,"Login");
            return "redirect:/employee/list";
        }
        else {
            model.put("message","Account doen't exist");
            return "login";
        }

    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("SessinoName");
        return "login";
    }
}
