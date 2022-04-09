package testStore;

import endPoint.EndPointOder;
import io.restassured.http.ContentType;
import model.Oder;
import org.junit.jupiter.api.*;

import utilsAPI.APISpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("API tests of Store")
public class TestStore {


    @Test
    @Order(1)
    @DisplayName("Send POST with body")
    public void testToPostRequestOder() {

        APISpecification.installSpecification(APISpecification.getRequestSpecification(),APISpecification.getResponseSpec(200));

        Oder oder = new Oder(11, 23, 1, "2022-03-31T12:28:04.807Z", "placed", true);

         given()
                .accept(ContentType.JSON)
                 .body(oder)
                 .when()
                .post(EndPointOder.ORDER)
                 .prettyPeek()
                 .then()
                .statusCode(200)
                 .and()
                .body("petId",is(23));
    }


    @Test
    @Order(2)
    @DisplayName("Send GET Oder")
    public void testToGetRequestOder() {

        APISpecification.installSpecification(APISpecification.getRequestSpecification(),APISpecification.getResponseSpec(200));

        given()
                .when()
                .get(EndPointOder.ORDER + "/11")
                .prettyPeek()
                .then()
                .body("petId", is(23));
    }


    @Test
    @Order(3)
    @DisplayName("Send GET Oder")
    public void testToGetOder() {

        APISpecification.installSpecification(APISpecification.getRequestSpecification(),APISpecification.getResponseSpec(200));

        given()
                .when()
                .get(EndPointOder.ORDER + "/11")
                .prettyPeek()
                .then()
                .statusCode(200);
    }


    @Test
    @Order(4)
    @DisplayName("Send DELETE Oder")
    public void testToDeleteRequestOder() {

        APISpecification.installSpecification(APISpecification.getRequestSpecification(),APISpecification.getResponseSpec(200));

        given()
                .accept(ContentType.JSON)
                .when()
                .delete(EndPointOder.ORDER + "/11")
                .then()
                .statusCode(200);
    }
}