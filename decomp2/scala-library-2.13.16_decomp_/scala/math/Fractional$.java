package scala.math;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Fractional$ implements Serializable {
   public static final Fractional$ MODULE$ = new Fractional$();

   public Fractional apply(final Fractional frac) {
      return frac;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Fractional$.class);
   }

   private Fractional$() {
   }
}
