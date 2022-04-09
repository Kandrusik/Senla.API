package utilsAPI;

import endPoint.EndPointBASEURI;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class APISpecification {

    public static RequestSpecification getRequestSpecificationWithXForm(){
        return new RequestSpecBuilder()
                .setBaseUri(EndPointBASEURI.BASEURI)
                .setContentType("application/x-www-form-urlencoded; charset=utf-8")
                .log(LogDetail.METHOD)
                .log(LogDetail.URI)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .build();
    }


    public static RequestSpecification getRequestSpecification(){
        return new RequestSpecBuilder()
                .setBaseUri(EndPointBASEURI.BASEURI)
                .setContentType(ContentType.JSON)
                .log(LogDetail.METHOD)
                .log(LogDetail.URI)
                .log(LogDetail.HEADERS)
                .log(LogDetail.BODY)
                .build();
    }

    public static ResponseSpecification getResponseSpec(int statusCode){
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .build();
    }

    public static void installSpecification(RequestSpecification request, ResponseSpecification response){
        RestAssured.requestSpecification = request;
        RestAssured.responseSpecification = response;
    }
}
