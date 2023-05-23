package org.example.handler;

import org.example.utils.ConstantMessage;

import java.util.regex.Pattern;

public class FormatValidation {

    public static void emailFormatValidation(String email) throws Exception
    {
        if(!(Pattern.compile(ConstantMessage.REGEX_EMAIL_STANDARD_RFC5322)
                .matcher(email)
                .matches()))
        {
            throw new ResourceNotFoundException(ConstantMessage.ERROR_EMAIL_FORMAT_INVALID);

        }
    }

    public static void phoneNumberFormatValidation(String phoneNumner) throws Exception
    {
        if(!(Pattern.compile(ConstantMessage.REGEX_PHONE)
                .matcher(phoneNumner)
                .matches()))
        {
            throw new ResourceNotFoundException(ConstantMessage.ERROR_PHONE_NUMBER_FORMAT_INVALID);
        }
    }

    public static void dateFormatYYYYMMDDValidation(String date) throws Exception
    {
        if(!(Pattern.compile(ConstantMessage.REGEX_DATE_YYYYMMDD)
                .matcher(date)
                .matches()))
        {
            throw new ResourceNotFoundException(ConstantMessage.ERROR_DATE_FORMAT_YYYYMMDD);
        }
//        REGEX_DATE_YYYYMMDD
    }
}
