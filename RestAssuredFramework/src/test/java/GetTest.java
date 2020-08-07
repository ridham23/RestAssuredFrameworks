import  static io.restassured.RestAssured.*;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import  io.restassured.response.Response;

import org.hamcrest.Matcher;
import org.json.JSONObject;
import org.json.JSONString;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.http.ContentType.*;

public class GetTest

{
   // @Test(priority = 1)
   public void Test_Get()
    {
        Response response = get("https://reqres.in/api/users");
        System.out.println(response.getBody());
        System.out.println(response.getBody().asString());
        System.out.println(response.asString());
        System.out.println(response);
        System.out.println(response.getStatusCode());
        System.out.println(response.getContentType());
        int statuscode = response.getStatusCode();
        Assert.assertEquals(statuscode,200);
    }
//@Test(priority = 2)
    public void Test_Get_Bdd()
{

given()
        .get("https://reqres.in/api/users?page=2")
        .then()
        .statusCode(200)
        .body("data.id[0]",equalTo(7)).
body("data.first_name",hasItems("Michael","Lindsay")).log().all();
}
@DataProvider(name = "DataForPost")
        public Object[][] dataforPost()
{
Object[][] data = new Object[2][3];
data[0][0]="Albert";
data[0][1]="Leader";
data[0][2]=2;

    data[1][0]="Blbert";
    data[1][1]="Beader";
    data[1][2]=1;

return data;
}

@Test(dataProvider="DataForPost")
public void Test_Post_Bdd(String name,String job,int id)
{
    JSONObject request = new JSONObject();
    request.put("name",name);
    request.put("job",job);
    request.put("id",id);
    System.out.println(request);

    baseURI="https://reqres.in";
    basePath="/api";
  String ID=  given()
            .header("Content-Type","application/json")
           .contentType(ContentType.JSON)
           .accept(ContentType.JSON)
            .body(request)
.when()
            .post("/users")

            .then()
            .statusCode(201)
            .log().all()
            .extract().path("id");
    System.out.println(ID);
            //.body(contains("morpheus"));


}

}
