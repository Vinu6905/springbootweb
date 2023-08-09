package org.jsp.springbootwebapp.controller;

import java.util.List;

import org.jsp.springbootwebapp.entity.Employee;
import org.jsp.springbootwebapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

	@Autowired
	private EmployeeRepository eRepo;
	
	@GetMapping({"/showEmployees","/","list"})
	public ModelAndView showEmployees() {
		 ModelAndView mav=new ModelAndView("list-employees");
		 List<Employee> list=eRepo.findAll();
		 mav.addObject("employees" , list);
		 return mav;
	}
	
	@GetMapping("/addEmployeeForm")
	public ModelAndView addEmployeeForm() {
		ModelAndView mav=new ModelAndView("add-Employee-Form");
		Employee newEmployee=new Employee();
		mav.addObject("employee",newEmployee);
		return mav;
	}
	
	@PostMapping("/saveEmployee")
	public String saveEmployee(@ModelAttribute Employee  employee) {
		eRepo.save(employee);
		return "redirect:/list";
	}
	
	@GetMapping("/showUpdateForm")
	public ModelAndView showUpdateForm(@RequestParam Integer employeeId) {
		ModelAndView andView=new ModelAndView("add-employee-form");
		Employee employee=eRepo.findById(employeeId).get();
		andView.addObject("employee" ,employee);
		return andView;
	}
	
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam Integer employeeId) {
		eRepo.deleteById(employeeId);
		return "redirect:/list";
	}
	
}
