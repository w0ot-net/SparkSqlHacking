package io.vertx.ext.auth.impl.hash;

public class SHA256 extends AbstractMDHash {
   public SHA256() {
      super("SHA-256");
   }

   public String id() {
      return "sha256";
   }
}
