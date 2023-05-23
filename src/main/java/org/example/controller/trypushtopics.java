package org.example.controller;


import bcaf.sdk.config.SolaceConfig;
import bcaf.sdk.controller.SolaceConnect;
import org.example.model.RequestModel;
import org.example.model.ResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class trypushtopics {
    @PostMapping("/push-solace")
    public ResponseEntity<ResponseModel> pushSolace(@RequestBody RequestModel request){
        ResponseModel resp = new ResponseModel();

        SolaceConfig config = new SolaceConfig();
        config.setHostName("smf://10.10.30.50:55555");
        config.setPassword("default");
        config.setUserName("default");
        config.setVpn("default");

        SolaceConnect connect = new SolaceConnect();
        try {
            bcaf.sdk.model.ResponseModel solaceResponse = connect.sendMessageToSolace(request.getData(), request.getTopic(), config);
            resp.setErrorCode(solaceResponse.getErrorCode());
            resp.setErrorDescription(solaceResponse.getErrorDescription());
            resp.setLog(solaceResponse.getLog());
        }catch (Exception e){
            resp.setErrorCode("99");
            resp.setErrorDescription(e.getMessage());
            resp.setLog("");
        }
        return ResponseEntity.ok(resp);
    }


}
