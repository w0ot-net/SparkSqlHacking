package scala.math;

import scala.runtime.ModuleSerializationProxy;

public class Equiv$Double$IeeeEquiv$ implements Equiv$Double$IeeeEquiv {
   public static final Equiv$Double$IeeeEquiv$ MODULE$ = new Equiv$Double$IeeeEquiv$();

   static {
      Equiv$Double$IeeeEquiv$ var10000 = MODULE$;
   }

   public boolean equiv(final double x, final double y) {
      return Equiv$Double$IeeeEquiv.equiv$(this, x, y);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Equiv$Double$IeeeEquiv$.class);
   }
}
