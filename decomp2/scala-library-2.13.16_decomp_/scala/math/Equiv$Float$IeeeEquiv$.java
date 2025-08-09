package scala.math;

import scala.runtime.ModuleSerializationProxy;

public class Equiv$Float$IeeeEquiv$ implements Equiv$Float$IeeeEquiv {
   public static final Equiv$Float$IeeeEquiv$ MODULE$ = new Equiv$Float$IeeeEquiv$();

   static {
      Equiv$Float$IeeeEquiv$ var10000 = MODULE$;
   }

   public boolean equiv(final float x, final float y) {
      return Equiv$Float$IeeeEquiv.equiv$(this, x, y);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Equiv$Float$IeeeEquiv$.class);
   }
}
