package org.multibit.mbm.web.rest.v1.controller;

import org.multibit.mbm.customer.dto.Customer;
import org.multibit.mbm.customer.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Handles the RESTful APIs for the {@link Customer}s
 */
@Controller
@RequestMapping(value = "/v1")
public class CustomerController {

  Customer customer;

  @Resource(name="customerService")
  private CustomerService customerService=null;

}

