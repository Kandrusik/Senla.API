package testPet;

import com.google.gson.JsonParser;
import endPoint.EndPointPet;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.Category;
import model.Pet;
import model.TagPet;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utilsAPI.PetAPISpecification;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;
import static utilsAPI.Status.AVAILABLE;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("API tests for pet user")
public class TestPet {

    private static RequestSpecification requestSpec = PetAPISpecification.getRequestSpecification();
    private static File jsonSchema = new File("src/test/resources/json/petJsonSchema.json");
    private String GustavKlimtID = "";

    @Test
    @Order(1)
    @DisplayName("Send POST with body")
    public void testValidateJsonScheme() {
        TagPet tag = new TagPet(13, "Oleg");
        Category category = new Category(1, "Cat");
        Pet pet = new Pet(24, category, "Missi", new ArrayList<>(), new ArrayList<>(Collections.singletonList(tag)), AVAILABLE);

        given().spec(requestSpec)
                .when()
                .body(pet)
                .post(EndPointPet.PET)
                .then()
                .assertThat()
                .body(matchesJsonSchema(jsonSchema));
    }


    @Test
    @Order(5)
    @DisplayName("Get request from Pet ID")
    public void testToGetRequestPetID() {

        TagPet tag = new TagPet(13, "Oleg");
        Category category = new Category(1, "Cat");
        Pet pet = new Pet(24, category, "Missi", new ArrayList<>(), new ArrayList<>(Collections.singletonList(tag)), AVAILABLE);
        
        given().spec(requestSpec)
                .when()
                .body(pet)
                .get(EndPointPet.PET)
                .then()
                .assertThat()
                .body(matchesJsonSchema(jsonSchema));
    }

    @Test
    @Order(2)
    @DisplayName("Check status of ID")
    public void testGetStatus() {
        Response response = given().spec(requestSpec)
                .when()
                .get(EndPointPet.STATUS + "?status=available");
        response.prettyPrint();

        String responseBody = response.getBody().asString();
        JsonParser parser = new JsonParser();
        Long id = parser.parse(responseBody).getAsJsonArray().get(0).getAsJsonObject().get("id").getAsLong();

        System.out.println(id);
    }

    @Test
    @Order(3)
    @DisplayName("Check Gustav Klimt ID")
    public void testLoginIn() {
        Response response = given().spec(requestSpec)
                .when()
                .get(EndPointPet.INVENTORY);
        response.prettyPrint();
    }

    @Test
    @Order(4)
    @DisplayName("Check Gustav Klimt ID")
    public void testPutPet() {
        Response response = given().spec(requestSpec)
                .when()
                .post(EndPointPet.PET);
        response.prettyPrint();

    }
}