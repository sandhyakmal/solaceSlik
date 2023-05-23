package org.example.configuration;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 05/05/2023 15:31
Last Modified on 05/05/2023 15:31
Version 1.0
*/

import com.jcraft.jsch.ChannelSftp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.integration.file.remote.session.SessionFactory;
import org.springframework.integration.sftp.outbound.SftpMessageHandler;
import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpRemoteFileTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
public class SftpConfig {
    @Value("${sftp.host}")
    private String host;

    @Value("${sftp.port}")
    private int port;

    @Value("${sftp.password}")
    private String username;

    @Value("${sftp.password}")
    private String password;


    @Bean
    public DefaultSftpSessionFactory sftpSessionFactory() {
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUser(username);
        factory.setPassword(password);
        factory.setAllowUnknownKeys(true);
        return factory;
    }

    @Bean
    public SftpRemoteFileTemplate sftpTemplate() {
        return new SftpRemoteFileTemplate(sftpSessionFactory());
    }


//    @Bean
//    public SessionFactory<ChannelSftp.LsEntry> sftpSessionFactory() {
//        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();
//        factory.setHost(host);
//        factory.setPort(port);
//        factory.setUser(username);
//        factory.setPassword(password);
//        factory.setAllowUnknownKeys(true);
//        return new CachingSessionFactory<ChannelSftp.LsEntry>(factory);
//    }
//
//    @Bean
//    public SftpRemoteFileTemplate sftpRemoteFileTemplate() {
//        return new SftpRemoteFileTemplate(sftpSessionFactory());
//    }
//
//    @Bean
//    @ServiceActivator(inputChannel = "toSftpChannel")
//    public MessageHandler sftpMessageHandler() {
//        SftpMessageHandler handler = new SftpMessageHandler(sftpSessionFactory());
//        handler.setRemoteDirectoryExpression(new LiteralExpression("/SFTP"));
//        return handler;
//    }
//
//    @Bean
//    public MessageChannel toSftpChannel() {
//        return new DirectChannel();
//    }

}