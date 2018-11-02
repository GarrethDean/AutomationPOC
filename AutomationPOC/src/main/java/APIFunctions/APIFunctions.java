package APIFunctions;

import Config.Hetzner;
import ExcelFunctions.ExcelFunctions;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;

import java.io.IOException;

import CustomUtils.ErrorLog;

import static com.jayway.restassured.RestAssured.*;


public class APIFunctions {
    private ErrorLog myErrorLog;
    private ExcelFunctions myExcel;
    private String accessToken;
    private String userName;
    private String password;

    public APIFunctions(String sheetName, String userName, String password) throws IOException{
        myErrorLog = new ErrorLog();
        myExcel = new ExcelFunctions(sheetName);
        this.userName = userName;
        this.password = password;
    }



    public ResponseBody getResponseBody(String urlName) throws IOException{

        // Get the access token

        // Get the url
        String url = Hetzner.RootURL + myExcel.buildExcelList("URL", urlName).get(0);

        Response response = given().
                auth().preemptive().oauth2(accessToken).
                when().
                get(url).
                then().
                contentType(ContentType.JSON).
                extract().
                response();

        // Return the response item as a float
        return response.getBody();
    }



}
