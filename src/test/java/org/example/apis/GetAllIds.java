package org.example.apis;

import pojo.CreateTokenPojo;
import pojo.GetAllIdsPojo;

public class GetAllIds {

    private String endPoint = "/booking";
    private CreateTokenPojo getAllIds = new CreateTokenPojo();
    private String expectedToken ;

    public  CreateTokenPojo getTokenBody(String username , String password){
        getAllIds.setUsername(username);
        getAllIds.setPassword(password);
        return  getAllIds ;
    }

    public String getEndPoint() {
        return endPoint;
    }
    public void setToken(String token){
        this.expectedToken = token ;
    }
    public String getToken(){
        return expectedToken;
    }
}
