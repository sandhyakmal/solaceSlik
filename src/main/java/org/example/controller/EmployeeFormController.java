package org.example.controller;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 15/05/2023 13:47
Last Modified on 15/05/2023 13:47
Version 1.0
*/

import lombok.Getter;
import org.example.handler.ResourceNotFoundException;
import org.example.handler.ResponseHandler;
import org.example.model.EmployeeFrom;
import org.example.services.EmployeeFormService;
import org.example.services.RbsDoneService;
import org.example.utils.ConstantMessage;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api")
public class EmployeeFormController {

    @Autowired
    private EmployeeFormService employeeFormService;


    private static final Logger logger = LoggerFactory.getLogger(EmployeeFormController.class);

    @Autowired
    public EmployeeFormController(EmployeeFormService employeeFormService){
        this.employeeFormService = employeeFormService;
    }

    @GetMapping("/v1/employee/all")
    public ResponseEntity<Object> getfindAll() {
        try {
            List<EmployeeFrom> lsEmployee = employeeFormService.findAll();
            if (lsEmployee.isEmpty()) {
                throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
            }
            return new ResponseEntity<>(lsEmployee, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/v1/employe/{kode}")
    public void getIdByKode(@PathVariable("kode") String kode)throws Exception{
        employeeFormService.getMyColumnById(kode);
    }
}
