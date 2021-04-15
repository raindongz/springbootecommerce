/*package com.cwmf.ecommerceservice.ecommerce.service;

import com.cwmf.ecommerceservice.ecommerce.exception.CustomerException;
import com.cwmf.ecommerceservice.ecommerce.exception.EmployeeException;
import com.cwmf.ecommerceservice.ecommerce.model.Customer;
import com.cwmf.ecommerceservice.ecommerce.model.Employee;
import com.cwmf.ecommerceservice.ecommerce.repo.EmployeeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    //create
    public Employee createEmployee(Employee employee){
        if(StringUtils.isNotBlank(employee.getId())){
            throw new EmployeeException("Employee id must be empty");
        }
        if(StringUtils.isBlank(employee.getName())){
            throw new EmployeeException("Employee must have a name");
        }
        if(!isPasswordSafe(employee.getPassword())){
            throw new EmployeeException("Password too simple");
        }
        employee.setId(null);
        Employee saved=employeeRepository.save(employee);
        return saved;
    }

    //password complexity check helper method
    private boolean isPasswordSafe(String passWord){
        if(passWord.matches("(?=.*[0-9]).*") && passWord.matches("(?=.*[a-z]).*") && passWord.matches("(?=.*[A-Z]).*") && passWord.matches("(?=.*[~!@#$%^&*()_-]).*")){
            return true;
        }else{
            return false;
        }
    }

    //delete
    public boolean deleteEmployee(String id){
        if(employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    //Update
    public Employee updateEmployee(Employee employee){
        if(StringUtils.isBlank(employee.getId())){
            throw new EmployeeException("Employee id must not be empty");
        }
        if(StringUtils.isBlank(employee.getName())){
            throw new EmployeeException("Employee must have a name");
        }
        if(!isPasswordSafe(employee.getPassword())){
            throw new EmployeeException("Password too simple");
        }
        Employee saved=employeeRepository.save(employee);
        return saved;
    }

    //Read
    public Optional<Employee> getEmployeeById(String employeeId){
        if(employeeRepository.existsById(employeeId)){
            throw new EmployeeException("customer" + employeeId+" not exist");
        }
        Optional<Employee> find=employeeRepository.findById(employeeId);
        return find;
    }
}


 */