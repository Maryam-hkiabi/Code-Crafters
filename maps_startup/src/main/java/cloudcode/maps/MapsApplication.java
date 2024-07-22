package cloudcode.maps;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.maps.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.model.GeocodingResult;

/** This class serves as an entry point for the Spring Boot app. */
@SpringBootApplication
public class MapsApplication {

  private static final Logger logger = LoggerFactory.getLogger(MapsApplication.class);

  public static void main(final String[] args) throws Exception {
    String port = System.getenv("PORT");
    if (port == null) {
      port = "8080";
      logger.warn("$PORT environment variable not set, defaulting to 8080");
    }
    SpringApplication app = new SpringApplication(MapsApplication.class);
    app.setDefaultProperties(Collections.singletonMap("server.port", port));

    // Start the Spring Boot application.
    app.run(args);
    logger.info(
        "Hello from Cloud Run! The container started successfully and is listening for HTTP requests on " + port);
  }
}
