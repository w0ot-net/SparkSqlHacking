package scala.math;

import java.io.Serializable;
import scala.runtime.ModuleSerializationProxy;

public final class Numeric$ implements Serializable {
   public static final Numeric$ MODULE$ = new Numeric$();

   public Numeric apply(final Numeric num) {
      return num;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Numeric$.class);
   }

   private Numeric$() {
   }
}
