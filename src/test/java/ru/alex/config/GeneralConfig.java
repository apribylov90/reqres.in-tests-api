package ru.alex.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:config/auth.properties"})
public interface GeneralConfig extends Config {

    @Key("baseUrl")
    @DefaultValue("https://reqres.in/api")
    String baseUrl();
}
