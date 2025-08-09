package io.vertx.core.http;

import io.vertx.codegen.annotations.VertxGen;

@VertxGen
public enum CookieSameSite {
   NONE("None"),
   STRICT("Strict"),
   LAX("Lax");

   private final String label;

   private CookieSameSite(String label) {
      this.label = label;
   }

   public String toString() {
      return this.label;
   }
}
