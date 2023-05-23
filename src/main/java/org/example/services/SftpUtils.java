package org.example.services;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 05/05/2023 14:04
Last Modified on 05/05/2023 14:04
Version 1.0
*/

import com.jcraft.jsch.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class SftpUtils{
    private String host;
    private int port;
    private String username;
    private String password;

    public SftpUtils(String host, int port, String username, String password) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public void uploadFile(String id,String localFilePath, String remoteFileName) throws JSchException, SftpException, IOException {
        JSch jsch = new JSch();
        Session session = jsch.getSession(username, host, port);
        session.setPassword(password);
        session.setConfig("StrictHostKeyChecking", "no");
        session.connect();
        ChannelSftp channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();
        String folderName = "/" + id ;
        try {
            channelSftp.ls("/" + id);
            channelSftp.cd("/" + id);
        } catch (SftpException e) {
            channelSftp.mkdir("/" + id);
            channelSftp.cd("/" + id);
        }
        channelSftp.put(localFilePath, remoteFileName);
        channelSftp.disconnect();
        session.disconnect();
    }
}
