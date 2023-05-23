package org.example.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponseModel {
    private String errorCode;
    private String errorDescription;
    private String log;
}
