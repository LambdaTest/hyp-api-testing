import groovy.json.JsonParser;
import io.restassured.RestAssured;
import io.restassured.response.Response;
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
import java.io.PrintStream;
import java.net.MalformedURLException;
import java.net.URL;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.sessionId;


public class apitestingsample {

    public String username = System.getenv("LT_USERNAME");    //lambda UserName
    public String accesskey = System.getenv("LT_ACCESS_KEY"); //lambda accessKey
    public RemoteWebDriver driver;
    public String gridURL = "@hub.lambdatest.com/wd/hub";
    final static String url="http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1";

    String status;

    @BeforeTest
    @org.testng.annotations.Parameters(value = {"browser", "version", "platform"})
    public void setUp(String browser, String version, String platform) throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platform", System.getProperty("platform"));
        capabilities.setCapability("browserName", System.getProperty("browser"));
        capabilities.setCapability("version", System.getProperty("version"));
        capabilities.setCapability("build", "API_TESTING_SAMPLE_LAMBDATEST");
        capabilities.setCapability("console",true);
        capabilities.setCapability("terminal",true);

        try {
            String url = "https://" + username + ":" + accesskey + gridURL;
            System.out.println("Starting test : ");
//            driver = new RemoteWebDriver(new URL(url), capabilities);

        } catch (Exception e) {
            System.out.println("Invalid grid URL");
        }
    }

    @Test(priority = 1)
    @org.testng.annotations.Parameters(value = {"platform", "browser", "version", "resolution"})
    public void sample() {

        try {

            given().when().get(url).then().log()
                    .all();

            given().queryParam("CUSTOMER_ID","68195")
                    .queryParam("PASSWORD","1234!")
                    .queryParam("Account_No","1") .when().get("http://demo.guru99.com/V4/sinkministatement.php").then().log().body();
            int statusCode= given().queryParam("CUSTOMER_ID","68195")
                    .queryParam("PASSWORD","1234!")
                    .queryParam("Account_No","1")
                    .when().get("http://demo.guru99.com/V4/sinkministatement.php").getStatusCode();
            System.out.println("The response status is "+statusCode);

            given().when().get(url).then().assertThat().statusCode(200);



        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @AfterTest
    @org.testng.annotations.Parameters(value = {"platform", "browser", "version"})
    public void tearDown(String platform, String browser, String version) throws Exception {

//        if (driver != null) {
            System.out.println("Ending test");
//        }
    }

}