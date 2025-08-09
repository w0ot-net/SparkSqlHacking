package io.vertx.ext.web.common;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.codegen.annotations.VertxGen;

@VertxGen
public interface WebEnvironment {
   String SYSTEM_PROPERTY_NAME = "vertxweb.environment";
   String ENV_VARIABLE_NAME = "VERTXWEB_ENVIRONMENT";

   static boolean development() {
      String mode = mode();
      return "dev".equalsIgnoreCase(mode) || "Development".equalsIgnoreCase(mode);
   }

   static @Nullable String mode() {
      return System.getProperty("vertxweb.environment", System.getenv("VERTXWEB_ENVIRONMENT"));
   }
}
