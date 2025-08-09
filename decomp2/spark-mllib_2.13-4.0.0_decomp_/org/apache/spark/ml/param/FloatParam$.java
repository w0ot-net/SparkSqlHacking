package org.apache.spark.ml.param;

import java.io.Serializable;
import org.json4s.JDouble;
import org.json4s.JString;
import org.json4s.JValue;
import scala.runtime.ModuleSerializationProxy;

public final class FloatParam$ implements Serializable {
   public static final FloatParam$ MODULE$ = new FloatParam$();

   public JValue jValueEncode(final float value) {
      if (Float.isNaN(value)) {
         return new JString("NaN");
      } else if (Float.NEGATIVE_INFINITY == value) {
         return new JString("-Inf");
      } else {
         return (JValue)(Float.POSITIVE_INFINITY == value ? new JString("Inf") : new JDouble((double)value));
      }
   }

   public float jValueDecode(final JValue jValue) {
      boolean var3 = false;
      JString var4 = null;
      if (jValue instanceof JString) {
         var3 = true;
         var4 = (JString)jValue;
         String var6 = var4.s();
         if ("NaN".equals(var6)) {
            return Float.NaN;
         }
      }

      if (var3) {
         String var7 = var4.s();
         if ("-Inf".equals(var7)) {
            return Float.NEGATIVE_INFINITY;
         }
      }

      if (var3) {
         String var8 = var4.s();
         if ("Inf".equals(var8)) {
            return Float.POSITIVE_INFINITY;
         }
      }

      if (jValue instanceof JDouble var9) {
         double x = var9.num();
         return (float)x;
      } else {
         throw new IllegalArgumentException("Cannot decode " + jValue + " to Float.");
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(FloatParam$.class);
   }

   private FloatParam$() {
   }
}
