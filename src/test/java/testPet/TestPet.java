package testPet;

import endPoint.EndPointPet;
import io.restassured.response.Response;
import model.Category;
import model.Pet;
import model.TagPet;
import org.junit.jupiter.api.*;

import utilsAPI.APISpecification;

import java.util.ArrayList;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static utilsAPI.Status.AVAILABLE;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("API tests of Pet")
public class TestPet {


    @Order(1)
    @Test
    @DisplayName("Send POST with body")
    public void testPostPet() {

        APISpecification.installSpecification(APISpecification.getRequestSpecification(),APISpecification.getResponseSpec(200));

        TagPet tags = new TagPet(29, "Oleg");
        Category category = new Category(13, "Cat");
        Pet pet = new Pet(43, category, "Missi", new ArrayList<>(), new ArrayList<>(Collections.singletonList(tags)), AVAILABLE);

        given()
                .body(pet)
                .when()
                .post(EndPointPet.PET)
                .then()
                .body("name",equalTo("Missi"));
    }


    @Order(2)
    @Test
    @DisplayName("Send PUT with body")
    public void testPutPet(){

        APISpecification.installSpecification(APISpecification.getRequestSpecification(),APISpecification.getResponseSpec(200));

        TagPet tags = new TagPet(99, "Pasha");
        Category category = new Category(101, "Dog");
        Pet pet = new Pet(43, category, "Missi", new ArrayList<>(), new ArrayList<>(Collections.singletonList(tags)), AVAILABLE);

        given()
                .body(pet)
                .when()
                .put(EndPointPet.PET)
                .then()
                .body("name",equalTo("Missi"))
                .statusCode(200);
    }


    @Order(3)
    @Test
    @DisplayName("Send GET Pet")
    public void testGetPet(){

        APISpecification.installSpecification(APISpecification.getRequestSpecification(),APISpecification.getResponseSpec(200));

        given()
                .when()
                .get(EndPointPet.PET + "/43")
                .prettyPeek()
                .then()
                .body("name", equalTo("Missi"));
    }


    @Order(4)
    @Test
    @DisplayName("Send POST Pet of ID")
    public void testPostPetId() {

        APISpecification.installSpecification(APISpecification.getRequestSpecificationWithXForm(),APISpecification.getResponseSpec(200));

        given()
                .formParam("name", "One")
                .formParam("status", "Eagle")
                .when()
                .post(EndPointPet.PET + "/43")
                .prettyPeek()
                .then()
                .body("code", equalTo(200));
    }


    @Order(5)
    @Test
    @DisplayName("Send DELETE Pet")
    public void testDeletePet() {

        APISpecification.installSpecification(APISpecification.getRequestSpecification(),APISpecification.getResponseSpec(200));

        given()
                .when()
                .delete(EndPointPet.PET + "/43")
                .prettyPeek()
                .then().statusCode(200);
    }


    @Order(6)
    @Test
    @DisplayName("Check status of ID")
    public void testGetStatusAvailable() {

        APISpecification.installSpecification(APISpecification.getRequestSpecification(),APISpecification.getResponseSpec(200));

        Response response = given()
                .when()
                .get(EndPointPet.STATUS + "?status=available");
        response.prettyPrint();
        Long id = response.getBody().jsonPath().get("id[0]");
        System.out.println(id);
    }
}