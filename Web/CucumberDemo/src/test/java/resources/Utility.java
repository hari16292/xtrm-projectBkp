package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Utility {

    RequestSpecification reqSpec;
    PrintStream log;
    String baseURI = "https://reqres.in";

    public RequestSpecification requestSpecification() throws Exception {
        log = new PrintStream(Files.newOutputStream(Paths.get("logFile.txt")));
        reqSpec = new RequestSpecBuilder().setBaseUri(baseURI).setRelaxedHTTPSValidation()
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
        return reqSpec;
    }



}
