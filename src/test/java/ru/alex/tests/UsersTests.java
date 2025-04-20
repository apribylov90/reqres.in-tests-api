package ru.alex.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.alex.models.user.*;

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

    @DisplayName("Успешное получение списка пользователей")
    @ParameterizedTest
    @ValueSource(ints= {1, 2})
    public void getUsersSuccessfulTest(int page) {

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

    @DisplayName("Успешное создание пользователя")
    @Test
    public void createUserSuccessfulTest() {
        CreateUpdateUserRequestModel userBody = new CreateUpdateUserRequestModel();
        userBody.setName("Alex");
        userBody.setJob("Tester");

        CreateUserResponseModel response = given(requestSpec)
                .body(userBody)
                .when()
                .post("/users")
                .then()
                .spec(responseSpec(201))
                .extract().as(CreateUserResponseModel.class);


        step("Проверка ответа", () -> {
            assertThat(response.getId()).isNotNull();
            assertThat(response.getCreatedAt()).isNotNull();
            assertThat(response.getName()).isEqualTo(userBody.getName());
            assertThat(response.getJob()).isEqualTo(userBody.getJob());
        });
    }

    @DisplayName("Успешное обновление пользователя")
    @Test
    public void updateUserSuccessfulTest() {
        CreateUpdateUserRequestModel userBody = new CreateUpdateUserRequestModel();
        userBody.setName("AlexNew");
        userBody.setJob("TesterNew");

        UpdateUserResponseModel response = given(requestSpec)
                .body(userBody)
                .when()
                .put("/users/2")
                .then()
                .spec(responseSpec(200))
                .extract().as(UpdateUserResponseModel.class);


        step("Проверка ответа", () -> {
            assertThat(response.getName()).isEqualTo(userBody.getName());
            assertThat(response.getJob()).isEqualTo(userBody.getJob());
            assertThat(response.getUpdatedAt()).isNotNull();
        });
    }


    @DisplayName("Успешное удаления пользователя")
    @Test
    public void deleteUserSuccessfulTest() {
        given(requestSpec)
                .when()
                .delete("/users/2")
                .then()
                .spec(responseSpec(204));

    }


}
