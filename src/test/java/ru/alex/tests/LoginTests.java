package ru.alex.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.alex.models.login.LoginErrorResponseModel;
import ru.alex.models.login.LoginRequestModel;
import ru.alex.models.login.LoginSuccessfulResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.alex.specs.Specs.requestSpec;
import static ru.alex.specs.Specs.responseSpec;

@DisplayName("Login API")
public class LoginTests extends BaseTest{

    @DisplayName("Удачный вход пользователем")
    @Test
    public void loginSuccessfulTest() {
        LoginRequestModel loginRequestBody = new LoginRequestModel();
        loginRequestBody.setEmail("eve.holt@reqres.in");
        loginRequestBody.setPassword("cityslicka");

        LoginSuccessfulResponseModel response = given(requestSpec)
                .body(loginRequestBody)
                .when()
                .post("/login")
                .then()
                .spec(responseSpec(200))
                .extract().as(LoginSuccessfulResponseModel.class);


        step("Проверка ответа", () -> {
           assertThat(response.getToken()).isNotEmpty();
        });
    }

    @DisplayName("Неудачный вход пользователем")
    @Test
    public void loginUnSuccessfulTest() {
        LoginRequestModel loginRequestBody = new LoginRequestModel();
        loginRequestBody.setEmail("test@test.com");
        loginRequestBody.setPassword("test");

        LoginErrorResponseModel response = given(requestSpec)
                .body(loginRequestBody)
                .when()
                .post("/login")
                .then()
                .spec(responseSpec(400))
                .extract().as(LoginErrorResponseModel.class);


        step("Проверка ответа", () -> {
            assertThat(response.getError()).isNotEmpty();
            assertThat(response.getError()).isEqualTo("user not found");
        });
    }

}
