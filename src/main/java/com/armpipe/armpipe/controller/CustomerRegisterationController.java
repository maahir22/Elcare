package com.armpipe.armpipe.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.armpipe.armpipe.dao.CustomerDao;
import com.armpipe.armpipe.dto.Customer;

@RestController
public class CustomerRegisterationController {
	@Autowired
	private CustomerDao customerDao;
	
	@PostMapping("/customer/register")
	public String registerCustomer(@RequestBody Customer customer) {
		customerDao.save(customer);
		return "Customer Saved";
	}
	
	@PostMapping("/customer/update")
	public String updateCustomer(@RequestBody Customer customer) {
		customerDao.save(customer);
		return "Customer Saved";
	}
	
	@PostMapping("/customer/delete")
	public String deleteCustomer(@RequestBody Customer customer) {
		customerDao.delete(customer);
		return "Customer Deleted";
	}
}
