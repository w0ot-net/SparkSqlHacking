package io.vertx.ext.auth.impl.hash;

public class SHA1 extends AbstractMDHash {
   public SHA1() {
      super("SHA-1");
   }

   public String id() {
      return "sha1";
   }
}
