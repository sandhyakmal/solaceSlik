package org.example.utils;

import org.example.handler.ResourceNotFoundException;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

public class ReadTextFile {

    private FileInputStream fInput;
    private BufferedInputStream bInput;
    private String[] exceptionString = new String[2];
    private String contentFile;
    private byte[] contentOfFile;

    public ReadTextFile(String pathFile) throws ResourceNotFoundException {
        exceptionString[0] = "ReadTextFile";
        setContentFile(pathFile);
    }

    public void setContentFile(String pathFile) throws ResourceNotFoundException {

        try {
            fInput = new FileInputStream(new File(pathFile));
            bInput = new BufferedInputStream(fInput);
            this.contentOfFile = bInput.readAllBytes();
            this.contentFile = new String(contentOfFile, StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new ResourceNotFoundException(e.getMessage());
        }
        finally {
            try {
                fInput.close();
                bInput.close();
            } catch (Exception e) {
                throw new ResourceNotFoundException(e.getMessage());
            }
        }
    }

    public String getContentFile()
    {
        return contentFile;
    }

    public byte[] getByteOfFile()
    {
        return contentOfFile;
    }
//	public static void main(String[] args) {
//		ReadTextFile rtf = new ReadTextFile(System.getProperty("user.dir")+"\\data\\MailHTMLExample.txt");
//		System.out.println(rtf.getContentFile());
//	}
}