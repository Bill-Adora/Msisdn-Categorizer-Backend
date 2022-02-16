package com.jumia.microservices.msmsisdncategorizerservice.config;

import com.jumia.microservices.msmsisdncategorizerservice.security.CustomAuthenticationEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private ConfigProperties configProperties;
    private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

    @Autowired
    public  SecurityConfig(ConfigProperties configProperties,
                           CustomAuthenticationEntryPoint customAuthenticationEntryPoint) {
        this.configProperties = configProperties;
        this.customAuthenticationEntryPoint = customAuthenticationEntryPoint;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //This method validates users by fetching credentials from the config server
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser(configProperties.getUsername()).password(passwordEncoder().encode(configProperties.getPassword())).roles("USER");
    }

    //This method defines access levels for each user
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/actuator/**", "/csrf", "/v2/api-docs", "/swagger-resources/configuration/ui",
                        "/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security",
                        "/configuration/security", "/swagger-ui.html", "/webjars/**")
                .permitAll()
                .anyRequest()
                .fullyAuthenticated()
                .and()
                .httpBasic();

        //Ensures the requests behave in a stateless manner i.e. username and password are authenticated per request
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(customAuthenticationEntryPoint)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .headers()
                .frameOptions()
                .disable();
    }
}
