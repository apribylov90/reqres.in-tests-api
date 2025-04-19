package ru.alex.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.alex.models.login.LoginRequestModel;
import ru.alex.models.login.LoginSuccessfulResponseModel;
import ru.alex.models.register.RegisterRequestModel;
import ru.alex.models.register.RegisterResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.alex.specs.Specs.requestSpec;
import static ru.alex.specs.Specs.responseSpec;

@DisplayName("API Register")
public class RegisterTests extends BaseTest {

    @DisplayName("Удачная регистрация пользователя")
    @Test
    public void loginSuccessfulTest() {
        RegisterRequestModel registerRequestBody = new RegisterRequestModel();
        registerRequestBody.setEmail("eve.holt@reqres.in");
        registerRequestBody.setPassword("pistol");

        RegisterResponseModel response = given(requestSpec)
                .body(registerRequestBody)
                .when()
                .post("/register")
                .then()
                .spec(responseSpec(200))
                .extract().as(RegisterResponseModel.class);


        step("Проверка ответа", () -> {
            assertThat(response.getId()).isNotNull();
            assertThat(response.getToken()).isNotEmpty();
        });
    }
}
