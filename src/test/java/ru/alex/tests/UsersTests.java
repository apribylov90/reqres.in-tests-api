package ru.alex.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.alex.models.user.UserResponseModel;
import ru.alex.models.user.UsersResponseModel;

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

    @DisplayName("Удачное получение списка пользователей")
    @ParameterizedTest
    @ValueSource(ints= {1, 2})
    public void getUsersTest(int page) {

        UsersResponseModel response = given(requestSpec)
                .when()
                .get("/users?page=" + page)
                .then()
                .spec(responseSpec(200))
                .extract().as(UsersResponseModel.class);


        step("Проверка ответа", () -> {
            assertThat(response.getSupport()).isNotNull();
            assertThat(response.getData()).isNotNull();

            assertEquals(6, response.getData().size());
            assertEquals(page, response.getPage());
            assertThat(response.getData().get(0).getFirstName()).isNotNull();

        });
    }
}
