package org.example.model;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 15/05/2023 13:34
Last Modified on 15/05/2023 13:34
Version 1.0
*/

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "app_fd_employee_form")
//@Data
//@NoArgsConstructor
public class EmployeeFrom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String c_nama;
    private String c_kode;
    private String c_namaRbs1;
    private String c_namaRbs2;
    private String c_namaRbs3;
    private String c_namaIndividu1;
    private String c_namaIndividu2;
    private String c_namaIndividu3;
    private String c_namaIndividu4;
    private String c_namaIndividu5;
    private String c_namaIndividu6;
    private String c_namaIndividu7;
    private String c_namaIndividu8;
    private String c_namaIndividu9;
    private String c_namaIndividu10;
    private String c_namaIndividu11;
    private String c_namaIndividu12;
    private String c_namaIndividu13;
    private String c_namaIndividu14;
    private String c_namaIndividu15;
    private String c_namaIndividu16;
    private String c_namaIndividu17;
    private String c_namaIndividu18;
    private String c_namaIndividu19;
    private String c_namaIndividu20;

    public String getC_namaIndividu5() {
        return c_namaIndividu5;
    }

    public void setC_namaIndividu5(String c_namaIndividu5) {
        this.c_namaIndividu5 = c_namaIndividu5;
    }

    public String getC_namaIndividu6() {
        return c_namaIndividu6;
    }

    public void setC_namaIndividu6(String c_namaIndividu6) {
        this.c_namaIndividu6 = c_namaIndividu6;
    }

    public String getC_namaIndividu7() {
        return c_namaIndividu7;
    }

    public void setC_namaIndividu7(String c_namaIndividu7) {
        this.c_namaIndividu7 = c_namaIndividu7;
    }

    public String getC_namaIndividu8() {
        return c_namaIndividu8;
    }

    public void setC_namaIndividu8(String c_namaIndividu8) {
        this.c_namaIndividu8 = c_namaIndividu8;
    }

    public String getC_namaIndividu9() {
        return c_namaIndividu9;
    }

    public void setC_namaIndividu9(String c_namaIndividu9) {
        this.c_namaIndividu9 = c_namaIndividu9;
    }

    public String getC_namaIndividu10() {
        return c_namaIndividu10;
    }

    public void setC_namaIndividu10(String c_namaIndividu10) {
        this.c_namaIndividu10 = c_namaIndividu10;
    }

    public String getC_namaIndividu11() {
        return c_namaIndividu11;
    }

    public void setC_namaIndividu11(String c_namaIndividu11) {
        this.c_namaIndividu11 = c_namaIndividu11;
    }

    public String getC_namaIndividu12() {
        return c_namaIndividu12;
    }

    public void setC_namaIndividu12(String c_namaIndividu12) {
        this.c_namaIndividu12 = c_namaIndividu12;
    }

    public String getC_namaIndividu13() {
        return c_namaIndividu13;
    }

    public void setC_namaIndividu13(String c_namaIndividu13) {
        this.c_namaIndividu13 = c_namaIndividu13;
    }

    public String getC_namaIndividu14() {
        return c_namaIndividu14;
    }

    public void setC_namaIndividu14(String c_namaIndividu14) {
        this.c_namaIndividu14 = c_namaIndividu14;
    }

    public String getC_namaIndividu15() {
        return c_namaIndividu15;
    }

    public void setC_namaIndividu15(String c_namaIndividu15) {
        this.c_namaIndividu15 = c_namaIndividu15;
    }

    public String getC_namaIndividu16() {
        return c_namaIndividu16;
    }

    public void setC_namaIndividu16(String c_namaIndividu16) {
        this.c_namaIndividu16 = c_namaIndividu16;
    }

    public String getC_namaIndividu17() {
        return c_namaIndividu17;
    }

    public void setC_namaIndividu17(String c_namaIndividu17) {
        this.c_namaIndividu17 = c_namaIndividu17;
    }

    public String getC_namaIndividu18() {
        return c_namaIndividu18;
    }

    public void setC_namaIndividu18(String c_namaIndividu18) {
        this.c_namaIndividu18 = c_namaIndividu18;
    }

    public String getC_namaIndividu19() {
        return c_namaIndividu19;
    }

    public void setC_namaIndividu19(String c_namaIndividu19) {
        this.c_namaIndividu19 = c_namaIndividu19;
    }

    public String getC_namaIndividu20() {
        return c_namaIndividu20;
    }

    public void setC_namaIndividu20(String c_namaIndividu20) {
        this.c_namaIndividu20 = c_namaIndividu20;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getC_nama() {
        return c_nama;
    }

    public void setC_nama(String c_nama) {
        this.c_nama = c_nama;
    }

    public String getC_kode() {
        return c_kode;
    }

    public void setC_kode(String c_kode) {
        this.c_kode = c_kode;
    }

    public String getC_namaRbs1() {
        return c_namaRbs1;
    }

    public void setC_namaRbs1(String c_namaRbs1) {
        this.c_namaRbs1 = c_namaRbs1;
    }

    public String getC_namaRbs2() {
        return c_namaRbs2;
    }

    public void setC_namaRbs2(String c_namaRbs2) {
        this.c_namaRbs2 = c_namaRbs2;
    }

    public String getC_namaRbs3() {
        return c_namaRbs3;
    }

    public void setC_namaRbs3(String c_namaRbs3) {
        this.c_namaRbs3 = c_namaRbs3;
    }

    public String getC_namaIndividu1() {
        return c_namaIndividu1;
    }

    public void setC_namaIndividu1(String c_namaIndividu1) {
        this.c_namaIndividu1 = c_namaIndividu1;
    }

    public String getC_namaIndividu2() {
        return c_namaIndividu2;
    }

    public void setC_namaIndividu2(String c_namaIndividu2) {
        this.c_namaIndividu2 = c_namaIndividu2;
    }

    public String getC_namaIndividu3() {
        return c_namaIndividu3;
    }

    public void setC_namaIndividu3(String c_namaIndividu3) {
        this.c_namaIndividu3 = c_namaIndividu3;
    }

    public String getC_namaIndividu4() {
        return c_namaIndividu4;
    }

    public void setC_namaIndividu4(String c_namaIndividu4) {
        this.c_namaIndividu4 = c_namaIndividu4;
    }
}
