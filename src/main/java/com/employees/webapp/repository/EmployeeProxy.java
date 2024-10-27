package com.employees.webapp.repository;

import com.employees.webapp.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class EmployeeProxy {

    @Autowired
    private CustomProperties props;

    /**
     * Get all employees
     * @return An iterable of all employees
     */

    public Iterable<Employee> getEmployees() {
        String baseApiUrl = props.getApiUrl();
        String getEmployeesUrl = baseApiUrl + "/employees";


        System.out.println("EmployeeProxy *** getEmployees() getEmployeesUrl  " +getEmployeesUrl);


                RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Employee>> response =
                restTemplate.exchange(
                        getEmployeesUrl,
                        HttpMethod.GET,
                        null,
                        new
                                ParameterizedTypeReference<Iterable<Employee>>() {}
                );
        System.out.println("EmployeeProxy *** getEmployees() response.getBody()  " +response.getBody());

                log.debug("Get Employees call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Employee getEmployeeById(Long id) {
        String baseApiUrl = props.getApiUrl();
        String getEmployeeByIdUrl = baseApiUrl + "/employees/{id}";

        System.out.println("EmployeeProxy *** getEmployeeById getEmployeeByIdUrl  " +getEmployeeByIdUrl);


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response =
                restTemplate.exchange(
                        getEmployeeByIdUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Employee>() {}
                );
        System.out.println("EmployeeProxy *** getEmployees() response.getBody()  " +response.getBody());

        log.debug("Get Employees call " + response.getStatusCode().toString());

        return response.getBody();
    }
    public void deleteEmployee(Long id) {
        String baseApiUrl = props.getApiUrl();
        String deleteEmployeeUrl = baseApiUrl + "/delete/{id}";
        log.debug("Deleting employee with ID: {} from {}", id, deleteEmployeeUrl);

        Map<String, Long> uriParams = new HashMap<>();
        uriParams.put("id", id);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(
                deleteEmployeeUrl,
                HttpMethod.DELETE,
                null,
                Void.class,
                uriParams
        );

        log.debug("Employee with ID: {} deleted successfully", id);
    }



    public Employee createEmployee(Employee employee) {
        String baseApiUrl = props.getApiUrl();
        String createEmployeeURL = baseApiUrl + "/create";

        System.out.println("EmployeeProxy *** getEmployeeById getEmployeeByIdUrl  " +createEmployeeURL);


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response =
                restTemplate.exchange(
                        createEmployeeURL,
                        HttpMethod.POST,
                        null,
                        new ParameterizedTypeReference<Employee>() {}
                );
        System.out.println("Creating new employee at {} " +response.getBody());

        log.debug("Create Employee call status: {} " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Employee updateEmployee(Employee employee) {
        String baseApiUrl = props.getApiUrl();
        String updateEmployeeURL = baseApiUrl + "/update/{id}";

        System.out.println("Updating employee with ID: {} at {} " +updateEmployeeURL);


        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response =
                restTemplate.exchange(
                        updateEmployeeURL,
                        HttpMethod.PUT,
                        null,
                        new ParameterizedTypeReference<Employee>() {}
                );
        System.out.println("Updating employee with ID: {} at {} response.getBody()  " +response.getBody());

        log.debug("Get Employees call " + response.getStatusCode().toString());

        return response.getBody();
    }


}
