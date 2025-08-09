package io.vertx.ext.auth.impl.hash;

public class SHA512 extends AbstractMDHash {
   public SHA512() {
      super("SHA-512");
   }

   public String id() {
      return "sha512";
   }
}
