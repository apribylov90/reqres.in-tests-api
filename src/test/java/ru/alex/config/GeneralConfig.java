package ru.alex.config;

import org.aeonbits.owner.Config;


@Config.Sources({
        "classpath:config/settings.properties"})
public interface GeneralConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://reqres.in/api")
    String baseUrl();
}
