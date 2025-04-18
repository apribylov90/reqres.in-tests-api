package ru.alex.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.alex.models.user.UserResponseModel;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Проверка User API")
public class UsersTests extends BaseTest {

    @DisplayName("Успешное получение информации о пользователе")
    @Test
    public void getUserTest() {
        String expectedEmail = "janet.weaver@reqres.in";
        String expectedFirstName = "Janet";
        String expectedLastName = "Weaver";

        UserResponseModel response = given()
                .log().uri()
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)
                .extract().as(UserResponseModel.class);


        assertThat(response.getData()).isNotNull();
        assertThat(response.getSupport()).isNotNull();
        assertThat(response.getSupport()).isNotNull();

        assertEquals(expectedEmail, response.getData().getEmail());
        assertEquals(expectedFirstName, response.getData().getFirstName());
        assertEquals(expectedLastName, response.getData().getLastName());
    }
}
