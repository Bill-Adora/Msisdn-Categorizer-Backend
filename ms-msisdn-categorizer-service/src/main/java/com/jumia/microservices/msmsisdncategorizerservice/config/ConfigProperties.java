package com.jumia.microservices.msmsisdncategorizerservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * This class is used to manage configuration in our Spring application.
 * <p>
 * It contains getters and setters of the specific configuration values that
 * are dynamic and  might need to change during runtime.
 *
 * This is becomes useful when these values are stored in a config server.
 */
@Component
@ConfigurationProperties(prefix = "app")
public class ConfigProperties {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
