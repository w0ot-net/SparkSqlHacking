package spire.std;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.Function2;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.runtime.BoxesRunTime;

public final class SeqSupport$ {
   public static final SeqSupport$ MODULE$ = new SeqSupport$();
   private static final Function1 spire$std$SeqSupport$$falsef = (x$2) -> BoxesRunTime.boxToBoolean($anonfun$spire$std$SeqSupport$$falsef$1(x$2));

   public final boolean forall(final Iterator x, final Iterator y, final Function2 f, final Function1 g) {
      boolean var10000;
      while(true) {
         if (x.hasNext() && y.hasNext()) {
            if (BoxesRunTime.unboxToBoolean(f.apply(x.next(), y.next()))) {
               g = g;
               f = f;
               y = y;
               x = x;
            } else {
               var10000 = false;
               break;
            }
         } else if (x.hasNext()) {
            if (BoxesRunTime.unboxToBoolean(g.apply(x.next()))) {
               g = g;
               f = f;
               y = y;
               x = x;
            } else {
               var10000 = false;
               break;
            }
         } else if (y.hasNext()) {
            if (BoxesRunTime.unboxToBoolean(g.apply(y.next()))) {
               g = g;
               f = f;
               y = y;
               x = x;
            } else {
               var10000 = false;
               break;
            }
         } else {
            var10000 = true;
            break;
         }
      }

      return var10000;
   }

   public Function1 spire$std$SeqSupport$$falsef() {
      return spire$std$SeqSupport$$falsef;
   }

   public final boolean forall(final SeqOps x, final SeqOps y, final Function2 f, final Function1 g) {
      return this.forall(x.iterator(), y.iterator(), f, g);
   }

   public final Function1 forall$default$4(final SeqOps x, final SeqOps y) {
      return this.spire$std$SeqSupport$$falsef();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$spire$std$SeqSupport$$falsef$1(final Object x$2) {
      return false;
   }

   private SeqSupport$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
