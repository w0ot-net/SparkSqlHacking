package spire.random.rng;

import java.security.SecureRandom;

public final class SecureJava$ {
   public static final SecureJava$ MODULE$ = new SecureJava$();

   /** @deprecated */
   public SecureJava fromBytes(final byte[] bytes) {
      return new SecureJava(new SecureRandom(bytes));
   }

   public SecureJava apply() {
      return new SecureJava(new SecureRandom());
   }

   private SecureJava$() {
   }
}
