package com.hotel.hoteleasy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.awt.Desktop;
import java.net.URI;

@SpringBootApplication
public class HotelEaseApplication {

    public static void main(String[] args) {
        // Levantar la aplicación y obtener el contexto
        ConfigurableApplicationContext context = SpringApplication.run(HotelEaseApplication.class, args);

        // Abrir Swagger después de que la app esté levantada
        abrirSwagger();
    }

    private static void abrirSwagger() {
       System.out.println("http://localhost:8080/swagger-ui.html");
    }
}
