package ru.alex.specs;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.ALL;
import static ru.alex.utils.CustomAllureFilter.withCustomAllureFilter;

public class Specs {
    public static RequestSpecification requestSpec = with()
            .filter(withCustomAllureFilter())
            .log().uri()
            .contentType("application/json");

    public static ResponseSpecification responseSpec(int statusCode) {
        return new ResponseSpecBuilder()
                .expectStatusCode(statusCode)
                .log(ALL)
                .build();
    }
}
