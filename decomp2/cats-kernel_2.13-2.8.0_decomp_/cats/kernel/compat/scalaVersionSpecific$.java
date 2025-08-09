package cats.kernel.compat;

import scala.collection.IterableOnce;

public final class scalaVersionSpecific$ {
   public static final scalaVersionSpecific$ MODULE$ = new scalaVersionSpecific$();

   public IterableOnce iterableOnceExtension(final IterableOnce io) {
      return io;
   }

   private scalaVersionSpecific$() {
   }
}
