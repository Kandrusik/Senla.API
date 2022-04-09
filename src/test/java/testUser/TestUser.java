package testUser;

import endPoint.EndPointUser;
import model.User;
import org.junit.jupiter.api.*;
import utilsAPI.APISpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("API tests of User")
public class TestUser {


    @Order(1)
    @DisplayName("Sent POST user")
    @Test
    public void postUserTest(){

        APISpecification.installSpecification(APISpecification.getRequestSpecification(),APISpecification.getResponseSpec(200));

        User user = new User(13,"Olegiii","Olegi",
                "Bekasov","pervi@gmail.com","Str0H9pa$$","+375333456789",200);

        List<User> oleg = new ArrayList<>();
        oleg.add(user);

                given()
                .body(oleg)
                .when()
                .post(EndPointUser.USER + "/createWithList")
                        .prettyPeek()
                        .then().body("message", equalTo("ok"));
    }


    @Order(2)
    @DisplayName("Sent GET user")
    @Test
    public void getUserTest(){

        APISpecification.installSpecification(APISpecification.getRequestSpecification(),APISpecification.getResponseSpec(200));
        given()
                .when()
                .get(EndPointUser.USER + "/Olegiii")
                .prettyPeek()
                .then().body("lastName", equalTo("Bekasov"));
    }

}
