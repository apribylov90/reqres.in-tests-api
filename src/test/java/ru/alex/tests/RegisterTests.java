package ru.alex.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.alex.models.register.ErrorRegisterResponseModel;
import ru.alex.models.register.RegisterRequestModel;
import ru.alex.models.register.RegisterResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static ru.alex.specs.Specs.requestSpec;
import static ru.alex.specs.Specs.responseSpec;

@DisplayName("Проверка Register API")
public class RegisterTests extends BaseTest {

    @DisplayName("Успешная регистрация пользователя")
    @Test
    public void registerSuccessfulTest() {
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

    @DisplayName("Неудачная регистрация пользователя")
    @Test
    public void registerUnSuccessfulTest() {
        RegisterRequestModel registerRequestBody = new RegisterRequestModel();
        registerRequestBody.setEmail("test@test.com");
        registerRequestBody.setPassword("test");

        String expectedErrorMessage = "Note: Only defined users succeed registration";

        ErrorRegisterResponseModel response = given(requestSpec)
                .body(registerRequestBody)
                .when()
                .post("/register")
                .then()
                .spec(responseSpec(400))
                .extract().as(ErrorRegisterResponseModel.class);


        step("Проверка ответа", () -> {
            assertThat(response.getError()).isNull();
            assertThat(response.getError()).isEqualTo(expectedErrorMessage);
        });
    }
}
