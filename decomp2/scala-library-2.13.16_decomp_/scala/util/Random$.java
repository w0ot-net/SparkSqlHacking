package scala.util;

import scala.runtime.ModuleSerializationProxy;

public final class Random$ extends Random {
   public static final Random$ MODULE$ = new Random$();

   public Random javaRandomToRandom(final java.util.Random r) {
      return new Random(r);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Random$.class);
   }

   private Random$() {
   }
}
