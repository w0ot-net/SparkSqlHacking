package scala.sys;

import java.lang.invoke.SerializedLambda;
import scala.runtime.BoxesRunTime;

public final class BooleanProp$ {
   public static final BooleanProp$ MODULE$ = new BooleanProp$();

   public BooleanProp valueIsTrue(final String key) {
      return new BooleanProp.BooleanPropImpl(key, (x$1) -> BoxesRunTime.boxToBoolean($anonfun$valueIsTrue$1(x$1)));
   }

   public BooleanProp keyExists(final String key) {
      return new BooleanProp.BooleanPropImpl(key, (s) -> BoxesRunTime.boxToBoolean($anonfun$keyExists$1(s)));
   }

   public BooleanProp constant(final String key, final boolean isOn) {
      return new BooleanProp.ConstantImpl(key, isOn);
   }

   public boolean booleanPropAsBoolean(final BooleanProp b) {
      return b.value();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$valueIsTrue$1(final String x$1) {
      String var10000 = x$1.toLowerCase();
      String var1 = "true";
      if (var10000 != null) {
         if (var10000.equals(var1)) {
            return true;
         }
      }

      return false;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$keyExists$1(final String s) {
      String var1 = "";
      if (s != null) {
         if (s.equals(var1)) {
            return true;
         }
      }

      if (!s.equalsIgnoreCase("true")) {
         return false;
      } else {
         return true;
      }
   }

   private BooleanProp$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
