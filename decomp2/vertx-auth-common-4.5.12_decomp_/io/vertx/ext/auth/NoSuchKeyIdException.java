package io.vertx.ext.auth;

public final class NoSuchKeyIdException extends RuntimeException {
   private final String id;

   public NoSuchKeyIdException(String alg) {
      this(alg, "<null>");
   }

   public NoSuchKeyIdException(String alg, String kid) {
      super("algorithm [" + alg + "]: " + kid);
      this.id = alg + "#" + kid;
   }

   public String id() {
      return this.id;
   }
}
