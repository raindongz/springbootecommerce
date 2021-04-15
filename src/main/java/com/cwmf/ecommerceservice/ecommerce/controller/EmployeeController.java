/*package com.cwmf.ecommerceservice.ecommerce.controller;

import com.cwmf.ecommerceservice.ecommerce.model.Customer;
import com.cwmf.ecommerceservice.ecommerce.model.Employee;
import com.cwmf.ecommerceservice.ecommerce.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee){return this.employeeService.createEmployee(employee);}

    @DeleteMapping("/{id}")
    public boolean deleteEmployee(@PathVariable String id){
        return this.employeeService.deleteEmployee(id);
    }

    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee){return this.employeeService.updateEmployee(employee);}

    @GetMapping("/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable String id){return this.employeeService.getEmployeeById(id);}
}


 */