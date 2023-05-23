package org.example.services;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 15/05/2023 13:45
Last Modified on 15/05/2023 13:45
Version 1.0
*/

import org.example.model.EmployeeFrom;
import org.example.repos.EmployeeFormRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeFormService {

    @Autowired
    private EmployeeFormRepo employeeFormRepo;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeFormService.class);

    public List<EmployeeFrom> findAll()
    {
        return employeeFormRepo.findAll();
    }

    public void getMyColumnById(String kode) {
        Optional<EmployeeFrom> Id = employeeFormRepo.findIdByKode(kode);
        logger.info("Id adalah "+ Id.get().getId());
    }

//    @Transactional(rollbackFor = {Exception.class})
//    public void updateEmployeeForm(Long id, String namaRbs1, String namaRbs2, String namaRbs3) {
//        employeeFormRepo.updateEmployeeFormById(id,namaRbs1, namaRbs2, namaRbs3);
//    }
}
