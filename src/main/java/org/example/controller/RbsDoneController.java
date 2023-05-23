package org.example.controller;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 14/04/2023 13:37
Last Modified on 14/04/2023 13:37
Version 1.0
*/

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.apache.commons.codec.binary.Base64;
import org.example.configuration.ApplicationConstant;
import org.example.model.EmployeeFrom;
import org.example.services.RbsDoneService;
import org.example.services.SftpUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.web.bind.annotation.*;

import javax.jms.TextMessage;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api")
public class RbsDoneController {

    @Value("${sftp.host}")
    private String host;

    @Value("${sftp.port}")
    private int port;

    @Value("${sftp.password}")
    private String username;

    @Value("${sftp.password}")
    private String password;

    @Autowired
    private RbsDoneService rbsDoneService;

    private final SftpRemoteFileTemplate sftpTemplate;

    private static final Logger logger = LoggerFactory.getLogger(RbsDoneController.class);

    @Autowired
    public RbsDoneController(RbsDoneService rbsDoneService,  SftpRemoteFileTemplate sftpTemplate){
        this.rbsDoneService = rbsDoneService;
        this.sftpTemplate = sftpTemplate;
    }

    @JmsListener(destination = ApplicationConstant.SLIK_QUEUE, concurrency = ApplicationConstant.THREAD_COUNT)
    public void saveDocument(TextMessage textMessage) {
        try {
            rbsDoneService.saveDocument(textMessage);
        }catch (Exception e){
            logger.error("Exception Occured : "+e.getMessage());
        }
    }


    @GetMapping("/individu")
    public void uploadFileIndividu(@RequestParam("id") String id,
                                   @RequestParam("reqId") String reqId,
                                   @RequestParam("source") String source) throws Exception {
        rbsDoneService.SlikIndividu(id,reqId, source);
    }

    @GetMapping("/rbs")
    public void uploadFileRbs( @RequestParam("id") String id,
                               @RequestParam("reqId") String reqId,
                               @RequestParam("source") String source) throws Exception {
        rbsDoneService.SlikRBS(id,reqId, source);
    }

    @PostMapping("/sftp/upload")
     public String sftp(@RequestBody Map<String, String> request) {

        String localFilePath = request.get("localFilePath");
        String remoteFileName = request.get("remoteFileName");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String base64 = request.get("base64");
        String namaFolder = request.get("namaFolder");
        byte[] data = Base64.decodeBase64(base64);
        String namaFile = dateFormat.format(new Date()) + ".pdf";

        try {
            FileOutputStream outputStream = new FileOutputStream(namaFile);
            outputStream.write(data);
            SftpUtils SftpUtils = new SftpUtils(host, port, username, password);
            SftpUtils.uploadFile(namaFolder,namaFile, namaFile);
            //            SftpUtils.uploadFile(localFilePath, remoteFileName);
            return "File berhasil diupload";
        } catch (Exception e) {
            e.printStackTrace();
            return "Gagal mengupload file";
        }
    }




}
