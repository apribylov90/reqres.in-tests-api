package ru.alex.tests;

import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.BeforeAll;
import ru.alex.config.GeneralConfig;

public class BaseTest {
    public static GeneralConfig config = ConfigFactory.create(GeneralConfig.class);

    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = config.baseUrl();
    }
}
