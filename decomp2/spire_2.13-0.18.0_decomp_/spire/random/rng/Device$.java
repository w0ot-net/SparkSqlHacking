package spire.random.rng;

import java.io.File;

public final class Device$ {
   public static final Device$ MODULE$ = new Device$();

   public Device apply(final String path) {
      return new Device(new File(path));
   }

   public Device random() {
      return new Device(new File("/dev/random"));
   }

   public Device urandom() {
      return new Device(new File("/dev/urandom"));
   }

   private Device$() {
   }
}
