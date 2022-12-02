import com.google.common.net.MediaType;
import groovy.json.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.json.simple.parser.JSONParser;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import org.hamcrest.Matchers.*;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.sessionId;


public class postapi {

    public String username = System.getenv("LT_USERNAME");    //lambda UserName
    public String accesskey = System.getenv("LT_ACCESS_KEY"); //lambda accessKey
    public RemoteWebDriver driver;
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    final static String url="http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1";

    String status;

    @BeforeTest
    public void setUp() throws Exception {


        try {
            String url = "https://" + username + ":" + accesskey + gridURL;
            System.out.println("Starting test : ");
//            driver = new RemoteWebDriver(new URL(url), capabilities);

        } catch (Exception e) {
            System.out.println("Invalid grid URL");
        }
    }



    @Test(priority = 1)

    public void sample() {

        try {


            JSONParser parser = new JSONParser();

            Object obj = parser.parse(new FileReader("session.json"));

            JSONObject jsonObject = (JSONObject)obj;
            String session = (String)jsonObject.get("session_id");

            System.out.println("session : "  +session);

            RestAssured.baseURI = "https://api.lambdatest.com/automation/api/v1/sessions/"+ session;
            Response session_response = given().auth().basic(username,accesskey)
                    .when().get();
            System.out.println("Status code: "+session_response.getStatusCode());
            System.out.println(session_response.asString());


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }



    @AfterTest
    public void tearDown() throws Exception {

//        if (driver != null) {
        System.out.println("Ending test");
//        }
    }



}

