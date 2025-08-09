package scala.reflect.internal.util;

import java.lang.invoke.SerializedLambda;
import scala.Function1;
import scala.collection.ArrayOps;
import scala.collection.MapFactory;
import scala.collection.mutable.HashMap;
import scala.collection.mutable.HashMap.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;

public final class Origins$ {
   public static final Origins$ MODULE$ = new Origins$();
   private static final HashMap counters;
   private static final String thisClass;

   static {
      counters = (HashMap)MapFactory.apply$(.MODULE$, scala.collection.immutable.Nil..MODULE$);
      thisClass = MODULE$.getClass().getName();
      Runtime.getRuntime().addShutdownHook(new Thread(() -> counters.values().foreach((x$4) -> {
            $anonfun$new$2(x$4);
            return BoxedUnit.UNIT;
         })));
   }

   public Origins lookup(final String tag, final Function1 orElse) {
      return (Origins)counters.getOrElseUpdate(tag, () -> (Origins)orElse.apply(tag));
   }

   public Origins register(final Origins x) {
      counters.update(x.tag(), x);
      return x;
   }

   private boolean preCutoff(final StackTraceElement el) {
      String var10000 = el.getClassName();
      String var2 = thisClass;
      if (var10000 == null) {
         if (var2 == null) {
            return true;
         }
      } else if (var10000.equals(var2)) {
         return true;
      }

      if (!el.getClassName().startsWith("java.lang.")) {
         return false;
      } else {
         return true;
      }
   }

   private Origins.OriginId findCutoff() {
      ArrayOps var10000 = scala.collection.ArrayOps..MODULE$;
      ArrayOps var10001 = scala.collection.ArrayOps..MODULE$;
      Object[] refArrayOps_xs = Thread.currentThread().getStackTrace();
      Object var8 = null;
      Object dropWhile$extension_$this = refArrayOps_xs;
      ArrayOps dropWhile$extension_this = var10001;
      int dropWhile$extension_indexWhere$extension_i = 0;

      while(true) {
         if (dropWhile$extension_indexWhere$extension_i >= ((Object[])dropWhile$extension_$this).length) {
            var11 = -1;
            break;
         }

         if (!$anonfun$findCutoff$1((StackTraceElement)((Object[])dropWhile$extension_$this)[dropWhile$extension_indexWhere$extension_i])) {
            var11 = dropWhile$extension_indexWhere$extension_i;
            break;
         }

         ++dropWhile$extension_indexWhere$extension_i;
      }

      int dropWhile$extension_i = var11;
      int dropWhile$extension_lo = dropWhile$extension_i < 0 ? ((Object[])dropWhile$extension_$this).length : dropWhile$extension_i;
      Object var12 = dropWhile$extension_this.slice$extension(dropWhile$extension_$this, dropWhile$extension_lo, ((Object[])dropWhile$extension_$this).length);
      Object var9 = null;
      dropWhile$extension_$this = null;
      StackTraceElement cutoff = (StackTraceElement)var10000.head$extension(var12);
      return new Origins.OriginId(cutoff.getClassName(), cutoff.getMethodName());
   }

   public Origins apply(final String tag) {
      return (Origins)counters.getOrElseUpdate(tag, () -> new Origins.OneLine(tag, MODULE$.findCutoff()));
   }

   public Origins apply(final String tag, final int frames) {
      return (Origins)counters.getOrElseUpdate(tag, () -> new Origins.MultiLine(tag, MODULE$.findCutoff(), frames));
   }

   // $FF: synthetic method
   public static final void $anonfun$new$2(final Origins x$4) {
      x$4.purge();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$findCutoff$1(final StackTraceElement el) {
      return MODULE$.preCutoff(el);
   }

   private Origins$() {
   }

   // $FF: synthetic method
   public static final Object $anonfun$findCutoff$1$adapted(final StackTraceElement el) {
      return BoxesRunTime.boxToBoolean($anonfun$findCutoff$1(el));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
