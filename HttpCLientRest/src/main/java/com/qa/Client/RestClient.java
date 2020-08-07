package com.qa.Client;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


import javax.swing.text.html.parser.Entity;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RestClient {
    //get method
    public static JSONObject GetApiWithoutHeader(String url) throws IOException
    {
        //get method without sending headers
      CloseableHttpClient httpclient =  HttpClients.createDefault();
        HttpGet httpget = new HttpGet(url);//this will create request get

        CloseableHttpResponse response = httpclient.execute(httpget);
        int statuscode = response.getStatusLine().getStatusCode();
        System.out.println("status is "+statuscode);
       String responseString = EntityUtils.toString(response.getEntity(),"UTF-8");
        JSONObject responseJson = new JSONObject(responseString);
        System.out.println("complete response is -->"+responseJson);
        Header[] headers = response.getAllHeaders();
        HashMap<String,String> HashHeaders = new HashMap<String, String>();
        for(Header header:headers)
        {
            HashHeaders.put(header.getName(),header.getValue());
        }
        System.out.println("All Header Values are "+HashHeaders);
        System.out.println("nothing");
return responseJson;
    }

    public static CloseableHttpResponse GetApiWithHeader(String url,HashMap<String,String> HashHeader)
    {
        CloseableHttpResponse response = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpGet httpGet = new HttpGet(url);
for(Map.Entry<String,String> entry : HashHeader.entrySet())
            {
                httpGet.addHeader(entry.getKey(),entry.getValue());
            }


             response = httpClient.execute(httpGet);
            
        }
        catch (Exception e)
        {
            System.out.println("error is "+e);
        }
        return response; 
    }
}
