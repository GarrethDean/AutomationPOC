package APIFunctions;

import Config.GlobalVariables;
import ExcelFunctions.ExcelFunctions;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import com.jayway.restassured.response.ResponseBody;
import com.jayway.restassured.parsing.Parser;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.*;

import CustomUtils.ErrorLog;

import com.jayway.restassured.*;
import static com.jayway.restassured.RestAssured.*;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.equalTo;


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
        String url = GlobalVariables.RootURL + myExcel.buildExcelList("URL", urlName).get(0);

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
