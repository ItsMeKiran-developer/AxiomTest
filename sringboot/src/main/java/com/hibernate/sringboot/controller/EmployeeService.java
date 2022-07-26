package com.hibernate.sringboot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hibernate.sringboot.model.Employee;
import com.hibernate.sringboot.repository.EmployeReppository;

@Controller
public class EmployeeService {

	@Autowired
	public EmployeReppository repo;
	
	@RequestMapping("/home")
	public String homePage(Model model){
		
		/* This request mapping is used to display all the employees present 
		 * in the database and 'model' is the attribute binds to the
		 *  'result' view template which is having all employees data in the 
		 *  added attribute 'employees" assigned with empList
		 */
		List<Employee>	empList=new ArrayList<>();
		empList.addAll(repo.findAll());
		model.addAttribute("employees", empList);
		return "result";
	}
	
//	@GetMapping("/empinfo")
//	public String getEmpInfo(@ModelAttribute ("id") long id, Model model){
//		
//		/*This  request mapping is used to display particular emp details
//		 * from the UI template 'empdetails' and the model attribute is bind to it by name 'emp'
//		 */
//		model.addAttribute(id);
//		model.addAttribute("emp", repo.findById(id));
//		return "empdetails";
//	}
	
	@RequestMapping("/add_emp")
	public String addNewEmp(Model model){
		
		/*This  request mapping is used to display input fields to enter employee details
		 * from the UI template 'persistNew' and the model attribute is bind to it with name 'emp'
		 */
		model.addAttribute("emp", new Employee());
		return "persistNew";
	}
	
	@PostMapping("/saveEmpInfo")
	public String saveEmpInfo(@ModelAttribute("employee") Employee emp,Model model, BindingResult result){
		
		/*
		 * This post request mapping is used to persist the employee data into database
		 * captured from the UI template 'persistNew' and display it along with the
		 * other employees data in the UI template 'result'
		 * 
		 */
		try {
			repo.save(emp);
			List<Employee> employeeList = repo.findAll();
			model.addAttribute("employees", employeeList);
			model.addAttribute("employeeAttr", emp);
			return "result";
		} catch (Exception exe) {
			return "error";
		}
	}
	
	
}
