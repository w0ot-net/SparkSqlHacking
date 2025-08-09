package scala.math;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Integral$ implements Serializable {
   public static final Integral$ MODULE$ = new Integral$();

   public Integral apply(final Integral int) {
      return int;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Integral$.class);
   }

   private Integral$() {
   }
}
