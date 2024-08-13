package com.ejemplo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/sendMessage").permitAll()  // Permitir acceso a estas rutas sin autenticación
                .anyRequest().authenticated()  // Cualquier otra ruta requiere autenticación
                .and()
            .csrf().disable()  // Desactivar CSRF para permitir peticiones POST sin token CSRF
            .formLogin().disable();  // Desactivar el formulario de inicio de sesión predeterminado
    }
}
