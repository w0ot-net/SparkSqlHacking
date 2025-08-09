package scala.math;

import scala.runtime.ModuleSerializationProxy;

public class Equiv$Double$StrictEquiv$ implements Equiv$Double$StrictEquiv {
   public static final Equiv$Double$StrictEquiv$ MODULE$ = new Equiv$Double$StrictEquiv$();

   static {
      Equiv$Double$StrictEquiv$ var10000 = MODULE$;
   }

   public boolean equiv(final double x, final double y) {
      return Equiv$Double$StrictEquiv.equiv$(this, x, y);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Equiv$Double$StrictEquiv$.class);
   }
}
