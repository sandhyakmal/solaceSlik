package org.example.services;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 14/04/2023 11:07
Last Modified on 14/04/2023 11:07
Version 1.0
*/

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.apache.commons.codec.binary.Base64;
import org.example.model.EmployeeFrom;
import org.example.repos.EmployeeFormRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import javax.jms.TextMessage;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class RbsDoneService  {

    @Value("${sftp.host}")
    private String host;

    @Value("${sftp.port}")
    private int port;

    @Value("${sftp.password}")
    private String username;

    @Value("${sftp.password}")
    private String password;

    @Value("${pathFile}")
    private String pathFile;

    private static final Logger logger = LoggerFactory.getLogger(RbsDoneService.class);

    @Autowired
    private EmployeeFormRepo employeeFormRepo;


    @Autowired
    public RbsDoneService(EmployeeFormRepo employeeFormRepo){this.employeeFormRepo = employeeFormRepo;}

    public OkHttpClient okHttpClient() throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                // Do nothing
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                // Do nothing
            }

            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        }};

        SSLContext sslContext = SSLContext.getInstance("SSL");
        sslContext.init(null, trustAllCerts, new SecureRandom());


        return new OkHttpClient.Builder()
                .sslSocketFactory(sslContext.getSocketFactory(), (X509TrustManager) trustAllCerts[0])
                .hostnameVerifier((hostname, session) -> true)
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
    }

    TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // Do nothing
        }
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            // Do nothing
        }
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }};

    public void saveDocument(TextMessage textMessage){
        try {
            String getTopic = textMessage.getJMSDestination().toString();
            String getMessage = textMessage.getText();
            logger.info("Topic adalah : "+ getTopic);
            logger.info("Message adalah : " + getMessage);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(getMessage);
            String status = jsonNode.get("status").asText();
            String reqId = jsonNode.get("reqId").asText();
            String source = jsonNode.get("source").asText();

            Optional<EmployeeFrom> employeeFrom = employeeFormRepo.findIdByKode(reqId);
            String id =  employeeFrom.get().getId();
            SlikIndividu(id,reqId,source);
            SlikRBS(id,reqId,source);
        } catch (Exception e){
            logger.error("Exception Occured : "+e.getMessage());
        }
    }
    @Transactional
    public void SlikIndividu(String id,String reqId, String source) throws Exception {
        OkHttpClient client = okHttpClient();
        Request request = new Request.Builder()
                .url("https://engine-slik.idofocus.co.id/api/v1/download/file?type=INDIVIDU&reqId="+reqId+"&source="+source+"")
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        String requestID = jsonNode.path("entry").path("reqId").asText();
        JsonNode files = jsonNode.path("files");
        String[] FileName = new String[files.size()];

        for (int i = 0; i < files.size(); i++) {
            String arrayBase64 = files.get(i).path("base64").asText();
            String arrayFileName = files.get(i).path("fileName").asText();
            FileName[i] = (i)+ "_" + files.get(i).path("fileName").asText();
            String path = pathFile + (i) + "_" + arrayFileName;
            byte[] data = Base64.decodeBase64(arrayBase64);
            File file = new File(path);
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(data);
                SftpUtils SftpUtils = new SftpUtils(host, port, username, password);
                SftpUtils.uploadFile(id, file.getAbsolutePath(), FileName[i] );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Optional<EmployeeFrom> employeeFromOptional = employeeFormRepo.findIdByKode(reqId);
        if (employeeFromOptional.isPresent()) {
            EmployeeFrom employeeFrom = employeeFromOptional.get();
            if (files.size() == 1) {
                employeeFrom.setC_namaIndividu1(FileName[0]);
            } else if (files.size() == 2) {
                employeeFrom.setC_namaIndividu1(FileName[0]);
                employeeFrom.setC_namaIndividu2(FileName[1]);
            } else if (files.size() == 3) {
                employeeFrom.setC_namaIndividu1(FileName[0]);
                employeeFrom.setC_namaIndividu2(FileName[1]);
                employeeFrom.setC_namaIndividu3(FileName[2]);
            } else if (files.size() == 4) {
                employeeFrom.setC_namaIndividu1(FileName[0]);
                employeeFrom.setC_namaIndividu2(FileName[1]);
                employeeFrom.setC_namaIndividu3(FileName[2]);
                employeeFrom.setC_namaIndividu4(FileName[3]);
            } else if (files.size() == 5) {
                employeeFrom.setC_namaIndividu1(FileName[0]);
                employeeFrom.setC_namaIndividu2(FileName[1]);
                employeeFrom.setC_namaIndividu3(FileName[2]);
                employeeFrom.setC_namaIndividu4(FileName[3]);
                employeeFrom.setC_namaIndividu5(FileName[4]);
            } else if (files.size() == 6) {
                employeeFrom.setC_namaIndividu1(FileName[0]);
                employeeFrom.setC_namaIndividu2(FileName[1]);
                employeeFrom.setC_namaIndividu3(FileName[2]);
                employeeFrom.setC_namaIndividu4(FileName[3]);
                employeeFrom.setC_namaIndividu5(FileName[4]);
                employeeFrom.setC_namaIndividu6(FileName[5]);
            } else if (files.size() == 7) {
                employeeFrom.setC_namaIndividu1(FileName[0]);
                employeeFrom.setC_namaIndividu2(FileName[1]);
                employeeFrom.setC_namaIndividu3(FileName[2]);
                employeeFrom.setC_namaIndividu4(FileName[3]);
                employeeFrom.setC_namaIndividu5(FileName[4]);
                employeeFrom.setC_namaIndividu6(FileName[5]);
                employeeFrom.setC_namaIndividu7(FileName[6]);
            } else if (files.size() == 8) {
                employeeFrom.setC_namaIndividu1(FileName[0]);
                employeeFrom.setC_namaIndividu2(FileName[1]);
                employeeFrom.setC_namaIndividu3(FileName[2]);
                employeeFrom.setC_namaIndividu4(FileName[3]);
                employeeFrom.setC_namaIndividu5(FileName[4]);
                employeeFrom.setC_namaIndividu6(FileName[5]);
                employeeFrom.setC_namaIndividu7(FileName[6]);
                employeeFrom.setC_namaIndividu8(FileName[7]);
            } else if (files.size() == 9) {
                employeeFrom.setC_namaIndividu1(FileName[0]);
                employeeFrom.setC_namaIndividu2(FileName[1]);
                employeeFrom.setC_namaIndividu3(FileName[2]);
                employeeFrom.setC_namaIndividu4(FileName[3]);
                employeeFrom.setC_namaIndividu5(FileName[4]);
                employeeFrom.setC_namaIndividu6(FileName[5]);
                employeeFrom.setC_namaIndividu7(FileName[6]);
                employeeFrom.setC_namaIndividu8(FileName[7]);
                employeeFrom.setC_namaIndividu9(FileName[8]);
            } else if (files.size() == 10) {
                employeeFrom.setC_namaIndividu1(FileName[0]);
                employeeFrom.setC_namaIndividu2(FileName[1]);
                employeeFrom.setC_namaIndividu3(FileName[2]);
                employeeFrom.setC_namaIndividu4(FileName[3]);
                employeeFrom.setC_namaIndividu5(FileName[4]);
                employeeFrom.setC_namaIndividu6(FileName[5]);
                employeeFrom.setC_namaIndividu7(FileName[6]);
                employeeFrom.setC_namaIndividu8(FileName[7]);
                employeeFrom.setC_namaIndividu9(FileName[8]);
                employeeFrom.setC_namaIndividu10(FileName[9]);
            } else if (files.size() == 11) {
                employeeFrom.setC_namaIndividu1(FileName[0]);
                employeeFrom.setC_namaIndividu2(FileName[1]);
                employeeFrom.setC_namaIndividu3(FileName[2]);
                employeeFrom.setC_namaIndividu4(FileName[3]);
                employeeFrom.setC_namaIndividu5(FileName[4]);
                employeeFrom.setC_namaIndividu6(FileName[5]);
                employeeFrom.setC_namaIndividu7(FileName[6]);
                employeeFrom.setC_namaIndividu8(FileName[7]);
                employeeFrom.setC_namaIndividu9(FileName[8]);
                employeeFrom.setC_namaIndividu10(FileName[9]);
                employeeFrom.setC_namaIndividu11(FileName[10]);
            } else if (files.size() == 12) {
                employeeFrom.setC_namaIndividu1(FileName[0]);
                employeeFrom.setC_namaIndividu2(FileName[1]);
                employeeFrom.setC_namaIndividu3(FileName[2]);
                employeeFrom.setC_namaIndividu4(FileName[3]);
                employeeFrom.setC_namaIndividu5(FileName[4]);
                employeeFrom.setC_namaIndividu6(FileName[5]);
                employeeFrom.setC_namaIndividu7(FileName[6]);
                employeeFrom.setC_namaIndividu8(FileName[7]);
                employeeFrom.setC_namaIndividu9(FileName[8]);
                employeeFrom.setC_namaIndividu10(FileName[9]);
                employeeFrom.setC_namaIndividu11(FileName[10]);
                employeeFrom.setC_namaIndividu12(FileName[11]);
            } else if (files.size() == 13) {
                employeeFrom.setC_namaIndividu1(FileName[0]);
                employeeFrom.setC_namaIndividu2(FileName[1]);
                employeeFrom.setC_namaIndividu3(FileName[2]);
                employeeFrom.setC_namaIndividu4(FileName[3]);
                employeeFrom.setC_namaIndividu5(FileName[4]);
                employeeFrom.setC_namaIndividu6(FileName[5]);
                employeeFrom.setC_namaIndividu7(FileName[6]);
                employeeFrom.setC_namaIndividu8(FileName[7]);
                employeeFrom.setC_namaIndividu9(FileName[8]);
                employeeFrom.setC_namaIndividu10(FileName[9]);
                employeeFrom.setC_namaIndividu11(FileName[10]);
                employeeFrom.setC_namaIndividu12(FileName[11]);
                employeeFrom.setC_namaIndividu13(FileName[12]);
            } else if (files.size() == 14) {
                employeeFrom.setC_namaIndividu1(FileName[0]);
                employeeFrom.setC_namaIndividu2(FileName[1]);
                employeeFrom.setC_namaIndividu3(FileName[2]);
                employeeFrom.setC_namaIndividu4(FileName[3]);
                employeeFrom.setC_namaIndividu5(FileName[4]);
                employeeFrom.setC_namaIndividu6(FileName[5]);
                employeeFrom.setC_namaIndividu7(FileName[6]);
                employeeFrom.setC_namaIndividu8(FileName[7]);
                employeeFrom.setC_namaIndividu9(FileName[8]);
                employeeFrom.setC_namaIndividu10(FileName[9]);
                employeeFrom.setC_namaIndividu11(FileName[10]);
                employeeFrom.setC_namaIndividu12(FileName[11]);
                employeeFrom.setC_namaIndividu13(FileName[12]);
                employeeFrom.setC_namaIndividu14(FileName[13]);
            } else if (files.size() == 15) {
                employeeFrom.setC_namaIndividu1(FileName[0]);
                employeeFrom.setC_namaIndividu2(FileName[1]);
                employeeFrom.setC_namaIndividu3(FileName[2]);
                employeeFrom.setC_namaIndividu4(FileName[3]);
                employeeFrom.setC_namaIndividu5(FileName[4]);
                employeeFrom.setC_namaIndividu6(FileName[5]);
                employeeFrom.setC_namaIndividu7(FileName[6]);
                employeeFrom.setC_namaIndividu8(FileName[7]);
                employeeFrom.setC_namaIndividu9(FileName[8]);
                employeeFrom.setC_namaIndividu10(FileName[9]);
                employeeFrom.setC_namaIndividu11(FileName[10]);
                employeeFrom.setC_namaIndividu12(FileName[11]);
                employeeFrom.setC_namaIndividu13(FileName[12]);
                employeeFrom.setC_namaIndividu14(FileName[13]);
                employeeFrom.setC_namaIndividu15(FileName[14]);
            } else if (files.size() == 16) {
                employeeFrom.setC_namaIndividu1(FileName[0]);
                employeeFrom.setC_namaIndividu2(FileName[1]);
                employeeFrom.setC_namaIndividu3(FileName[2]);
                employeeFrom.setC_namaIndividu4(FileName[3]);
                employeeFrom.setC_namaIndividu5(FileName[4]);
                employeeFrom.setC_namaIndividu6(FileName[5]);
                employeeFrom.setC_namaIndividu7(FileName[6]);
                employeeFrom.setC_namaIndividu8(FileName[7]);
                employeeFrom.setC_namaIndividu9(FileName[8]);
                employeeFrom.setC_namaIndividu10(FileName[9]);
                employeeFrom.setC_namaIndividu11(FileName[10]);
                employeeFrom.setC_namaIndividu12(FileName[11]);
                employeeFrom.setC_namaIndividu13(FileName[12]);
                employeeFrom.setC_namaIndividu14(FileName[13]);
                employeeFrom.setC_namaIndividu15(FileName[14]);
                employeeFrom.setC_namaIndividu16(FileName[15]);
            } else if (files.size() == 17) {
                employeeFrom.setC_namaIndividu1(FileName[0]);
                employeeFrom.setC_namaIndividu2(FileName[1]);
                employeeFrom.setC_namaIndividu3(FileName[2]);
                employeeFrom.setC_namaIndividu4(FileName[3]);
                employeeFrom.setC_namaIndividu5(FileName[4]);
                employeeFrom.setC_namaIndividu6(FileName[5]);
                employeeFrom.setC_namaIndividu7(FileName[6]);
                employeeFrom.setC_namaIndividu8(FileName[7]);
                employeeFrom.setC_namaIndividu9(FileName[8]);
                employeeFrom.setC_namaIndividu10(FileName[9]);
                employeeFrom.setC_namaIndividu11(FileName[10]);
                employeeFrom.setC_namaIndividu12(FileName[11]);
                employeeFrom.setC_namaIndividu13(FileName[12]);
                employeeFrom.setC_namaIndividu14(FileName[13]);
                employeeFrom.setC_namaIndividu15(FileName[14]);
                employeeFrom.setC_namaIndividu16(FileName[15]);
                employeeFrom.setC_namaIndividu17(FileName[16]);
            } else if (files.size() == 18) {
                employeeFrom.setC_namaIndividu1(FileName[0]);
                employeeFrom.setC_namaIndividu2(FileName[1]);
                employeeFrom.setC_namaIndividu3(FileName[2]);
                employeeFrom.setC_namaIndividu4(FileName[3]);
                employeeFrom.setC_namaIndividu5(FileName[4]);
                employeeFrom.setC_namaIndividu6(FileName[5]);
                employeeFrom.setC_namaIndividu7(FileName[6]);
                employeeFrom.setC_namaIndividu8(FileName[7]);
                employeeFrom.setC_namaIndividu9(FileName[8]);
                employeeFrom.setC_namaIndividu10(FileName[9]);
                employeeFrom.setC_namaIndividu11(FileName[10]);
                employeeFrom.setC_namaIndividu12(FileName[11]);
                employeeFrom.setC_namaIndividu13(FileName[12]);
                employeeFrom.setC_namaIndividu14(FileName[13]);
                employeeFrom.setC_namaIndividu15(FileName[14]);
                employeeFrom.setC_namaIndividu16(FileName[15]);
                employeeFrom.setC_namaIndividu17(FileName[16]);
                employeeFrom.setC_namaIndividu18(FileName[17]);
            } else if (files.size() == 19) {
                employeeFrom.setC_namaIndividu1(FileName[0]);
                employeeFrom.setC_namaIndividu2(FileName[1]);
                employeeFrom.setC_namaIndividu3(FileName[2]);
                employeeFrom.setC_namaIndividu4(FileName[3]);
                employeeFrom.setC_namaIndividu5(FileName[4]);
                employeeFrom.setC_namaIndividu6(FileName[5]);
                employeeFrom.setC_namaIndividu7(FileName[6]);
                employeeFrom.setC_namaIndividu8(FileName[7]);
                employeeFrom.setC_namaIndividu9(FileName[8]);
                employeeFrom.setC_namaIndividu10(FileName[9]);
                employeeFrom.setC_namaIndividu11(FileName[10]);
                employeeFrom.setC_namaIndividu12(FileName[11]);
                employeeFrom.setC_namaIndividu13(FileName[12]);
                employeeFrom.setC_namaIndividu14(FileName[13]);
                employeeFrom.setC_namaIndividu15(FileName[14]);
                employeeFrom.setC_namaIndividu16(FileName[15]);
                employeeFrom.setC_namaIndividu17(FileName[16]);
                employeeFrom.setC_namaIndividu18(FileName[17]);
                employeeFrom.setC_namaIndividu19(FileName[18]);
            } else if (files.size() == 20) {
                employeeFrom.setC_namaIndividu1(FileName[0]);
                employeeFrom.setC_namaIndividu2(FileName[1]);
                employeeFrom.setC_namaIndividu3(FileName[2]);
                employeeFrom.setC_namaIndividu4(FileName[3]);
                employeeFrom.setC_namaIndividu5(FileName[4]);
                employeeFrom.setC_namaIndividu6(FileName[5]);
                employeeFrom.setC_namaIndividu7(FileName[6]);
                employeeFrom.setC_namaIndividu8(FileName[7]);
                employeeFrom.setC_namaIndividu9(FileName[8]);
                employeeFrom.setC_namaIndividu10(FileName[9]);
                employeeFrom.setC_namaIndividu11(FileName[10]);
                employeeFrom.setC_namaIndividu12(FileName[11]);
                employeeFrom.setC_namaIndividu13(FileName[12]);
                employeeFrom.setC_namaIndividu14(FileName[13]);
                employeeFrom.setC_namaIndividu15(FileName[14]);
                employeeFrom.setC_namaIndividu16(FileName[15]);
                employeeFrom.setC_namaIndividu17(FileName[16]);
                employeeFrom.setC_namaIndividu18(FileName[17]);
                employeeFrom.setC_namaIndividu19(FileName[18]);
                employeeFrom.setC_namaIndividu20(FileName[19]);
            }
            employeeFormRepo.save(employeeFrom);
        }
    }

    @Transactional
    public void SlikRBS(String id ,String reqId, String source) throws Exception {
        OkHttpClient client = okHttpClient();
        Request request = new Request.Builder()
                .url("https://engine-slik.idofocus.co.id/api/v1/download/file?type=RBS&reqId="+reqId+"&source="+source+"")
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
//        String requestID = jsonNode.path("entry").path("reqId").asText();
        JsonNode files = jsonNode.path("files");
        String[] FileName = new String[files.size()];
        for (int i = 0; i < files.size(); i++) {
            String arrayBase64 = files.get(i).path("base64").asText();
            String arrayFileName = files.get(i).path("fileName").asText();
            FileName[i] = files.get(i).path("fileName").asText();
            String path = pathFile + arrayFileName;
            byte[] data = Base64.decodeBase64(arrayBase64);
            File file = new File(path);
            try {
                FileOutputStream outputStream = new FileOutputStream(file);
                outputStream.write(data);
                SftpUtils SftpUtils = new SftpUtils(host, port, username, password);
                SftpUtils.uploadFile(id, file.getAbsolutePath(), FileName[i] );
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Optional<EmployeeFrom> employeeFromOptional = employeeFormRepo.findIdByKode(reqId);
        if (employeeFromOptional.isPresent()) {
            EmployeeFrom employeeFrom = employeeFromOptional.get();
            if (files.size() == 1) {
                employeeFrom.setC_namaRbs1(FileName[0]);
            } else if (files.size() == 2) {
                employeeFrom.setC_namaRbs1(FileName[0]);
                employeeFrom.setC_namaRbs2(FileName[1]);
            } else if (files.size() == 3) {
                employeeFrom.setC_namaRbs1(FileName[0]);
                employeeFrom.setC_namaRbs2(FileName[1]);
                employeeFrom.setC_namaRbs3(FileName[2]);
            }
            employeeFormRepo.save(employeeFrom);
        }
    }



}
