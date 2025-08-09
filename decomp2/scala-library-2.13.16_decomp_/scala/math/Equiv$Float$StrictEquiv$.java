package scala.math;

import scala.runtime.ModuleSerializationProxy;

public class Equiv$Float$StrictEquiv$ implements Equiv$Float$StrictEquiv {
   public static final Equiv$Float$StrictEquiv$ MODULE$ = new Equiv$Float$StrictEquiv$();

   static {
      Equiv$Float$StrictEquiv$ var10000 = MODULE$;
   }

   public boolean equiv(final float x, final float y) {
      return Equiv$Float$StrictEquiv.equiv$(this, x, y);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Equiv$Float$StrictEquiv$.class);
   }
}
