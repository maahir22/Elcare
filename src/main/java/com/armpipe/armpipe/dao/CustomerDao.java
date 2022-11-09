package com.armpipe.armpipe.dao;

import org.springframework.data.repository.CrudRepository;

import com.armpipe.armpipe.dto.Customer;


public interface CustomerDao extends CrudRepository<Customer, Long>{

}
