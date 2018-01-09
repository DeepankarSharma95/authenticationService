package com.pavoindus.authentication;

import com.pavoindus.authentication.context.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * Created 12/11/2017 21:15
 *
 * @author Deepankar Sharma
 */
@SpringBootApplication
public class AuthenticationApplication extends SpringBootServletInitializer {

  @Override protected SpringApplicationBuilder configure(
      SpringApplicationBuilder builder) {
    return builder.sources(AuthenticationApplication.class);
  }

  public static void main(String[] args) {
    ApplicationContext.init();
    SpringApplication.run(AuthenticationApplication.class, args);
  }
}
