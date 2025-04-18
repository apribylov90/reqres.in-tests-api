package ru.alex.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.alex.models.user.UserResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.alex.specs.Specs.requestSpec;
import static ru.alex.specs.Specs.responseSpec;

@DisplayName("Проверка User API")
public class UsersTests extends BaseTest {

    @DisplayName("Успешное получение информации о пользователе")
    @Test
    public void getUserSuccessfulTest() {
        String expectedEmail = "janet.weaver@reqres.in";
        String expectedFirstName = "Janet";
        String expectedLastName = "Weaver";

        UserResponseModel response = given(requestSpec)
                .when()
                .get("/users/2")
                .then()
                .spec(responseSpec(200))
                .extract().as(UserResponseModel.class);

        step("Проверка ответа", () -> {
            assertThat(response.getData()).isNotNull();
            assertThat(response.getSupport()).isNotNull();

            assertEquals(expectedEmail, response.getData().getEmail());
            assertEquals(expectedFirstName, response.getData().getFirstName());
            assertEquals(expectedLastName, response.getData().getLastName());
        });

    }

    @DisplayName("Неудачное получение информации о пользователе")
    @Test
    public void getUserUnSuccessfulTest() {

        step("Неудачный запрос и ответ 404", () -> {
            given(requestSpec)
                    .when()
                    .get("/users/404")
                    .then()
                    .spec(responseSpec(404));
        });

    }
}
