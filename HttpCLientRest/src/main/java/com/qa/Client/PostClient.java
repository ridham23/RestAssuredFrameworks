package com.qa.Client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.Base.TestBase;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
//import sun.net.www.http.HttpClient;

import java.util.HashMap;
import java.util.Map;

public class PostClient extends TestBase {
  static  CloseableHttpResponse response;
    public static CloseableHttpResponse PostApiWithHeaders(String url, String Entity, HashMap<String,String> HeaderMap)
    {
       try {
           CloseableHttpClient Client = HttpClients.createDefault();
           HttpPost PostRequest = new HttpPost(url);

           PostRequest.setEntity(new StringEntity(Entity));
           for(Map.Entry<String,String> entry: HeaderMap.entrySet())
           {
PostRequest.addHeader(entry.getKey(),entry.getValue());
           }


           response = Client.execute(PostRequest);
       }
       catch (Exception e)
       {
           System.out.println("Exception is "+e);
       }

       return response;
    }

}
