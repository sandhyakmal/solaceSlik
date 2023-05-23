package org.example.repos;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 15/05/2023 13:44
Last Modified on 15/05/2023 13:44
Version 1.0
*/


import org.example.model.EmployeeFrom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EmployeeFormRepo extends JpaRepository<EmployeeFrom, String> {

    @Query(value = "SELECT * FROM app_fd_employee_form WHERE c_kode = :kode", nativeQuery = true)
    Optional<EmployeeFrom> findIdByKode(@Param("kode") String kode);

    @Modifying
    @Query(value = "UPDATE app_fd_employee_form SET c_namaRbs1 = :namaRbs1, c_namaRbs2 = :namaRbs2, c_namaRbs3 = :namaRbs3 WHERE id = :id", nativeQuery = true)
    void updateEmployeeFormById(@Param("id") String id, @Param("namaRbs1") String namaRbs1, @Param("namaRbs2") String namaRbs2, @Param("namaRbs3") String namaRbs3);

    @Modifying
    @Query(value = "UPDATE app_fd_employee_form SET c_namaIndividu1 = :namaIndividu1, c_namaIndividu2 = :namaIndividu2, c_namaIndividu3 = :namaIndividu3, c_namaIndividu4 = :namaIndividu4 WHERE id = :id", nativeQuery = true)
    void updateEmployeeById(@Param("id") String id, @Param("namaIndividu1") String namaIndividu1, @Param("namaIndividu2") String namaIndividu2, @Param("namaIndividu3") String namaIndividu3, @Param("namaIndividu4") String namaIndividu4);


}