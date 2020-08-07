package com.qa.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.Base.TestBase;
import com.qa.Client.PostClient;
import com.qa.Client.RestClient;
import com.qa.Client.users;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.util.Util;

import java.io.DataInput;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class GetApiTest extends TestBase {
    String url;
    String path;
    String CompleteUrl;
    TestBase testBase;
    RestClient Restclient ;
    @BeforeClass
    public void setup()
    {
        testBase = new TestBase();
url = prop.getProperty("Url");
 path = prop.getProperty("serviceurl");
CompleteUrl= url+path;

}
@Test(enabled=false)
    public void getApiTest() throws IOException {

   JSONObject responsejson1 = Restclient.GetApiWithoutHeader(CompleteUrl);
   String Val = Util.getValueByJPath(responsejson1,"/data[0]/id");
    System.out.println("data[0][id] value is "+Val );

}
@Test(enabled=false)
    public void getApiTestWIthHeader()
        {
            HashMap<String,String> HeaderEntry = new HashMap<String,String>();
            HeaderEntry.put("Content-Type","application/json");
        CloseableHttpResponse Responsejson2 = RestClient.GetApiWithHeader(CompleteUrl,HeaderEntry);
            System.out.println("Response is " +Responsejson2.toString());
    }

@Test(priority=1)
public void GetPostWithHeader() throws IOException {
    HashMap<String,String> HeaderEntry = new HashMap<String,String>();
    HeaderEntry.put("Content-Type","application/json");
    ObjectMapper mapper = new ObjectMapper();
    users DUser = new users("phoebe","actor");
   // mapper.writeValue(new File("target/car.json"), DUser);
    String JsonVal=  mapper.writeValueAsString(DUser);//writing pojo to json as string
  CloseableHttpResponse response3 =  PostClient.PostApiWithHeaders(CompleteUrl,JsonVal,HeaderEntry);
  int code =   response3.getStatusLine().getStatusCode();
    Assert.assertEquals(code,201);
    System.out.println("Response API is "+response3);
    String responseString = EntityUtils.toString(response3.getEntity(),"UTF-8");
    System.out.println("Response API is "+responseString);
    JSONObject responseJson= new JSONObject(responseString);
    System.out.println("Response API is "+responseJson);
    //json to java object
    users userResp =mapper.readValue(String.valueOf(responseJson),users.class);
    System.out.println(userResp);
    Assert.assertTrue(DUser.getName().equals(userResp.getName()));
}

}
