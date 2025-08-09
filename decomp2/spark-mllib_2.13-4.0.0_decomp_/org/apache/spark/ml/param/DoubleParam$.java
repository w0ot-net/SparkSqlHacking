package org.apache.spark.ml.param;

import java.io.Serializable;
import org.json4s.JDouble;
import org.json4s.JString;
import org.json4s.JValue;
import scala.runtime.ModuleSerializationProxy;

public final class DoubleParam$ implements Serializable {
   public static final DoubleParam$ MODULE$ = new DoubleParam$();

   public JValue jValueEncode(final double value) {
      if (Double.isNaN(value)) {
         return new JString("NaN");
      } else if (Double.NEGATIVE_INFINITY == value) {
         return new JString("-Inf");
      } else {
         return (JValue)(Double.POSITIVE_INFINITY == value ? new JString("Inf") : new JDouble(value));
      }
   }

   public double jValueDecode(final JValue jValue) {
      boolean var4 = false;
      JString var5 = null;
      if (jValue instanceof JString) {
         var4 = true;
         var5 = (JString)jValue;
         String var7 = var5.s();
         if ("NaN".equals(var7)) {
            return Double.NaN;
         }
      }

      if (var4) {
         String var8 = var5.s();
         if ("-Inf".equals(var8)) {
            return Double.NEGATIVE_INFINITY;
         }
      }

      if (var4) {
         String var9 = var5.s();
         if ("Inf".equals(var9)) {
            return Double.POSITIVE_INFINITY;
         }
      }

      if (jValue instanceof JDouble var10) {
         double x = var10.num();
         return x;
      } else {
         throw new IllegalArgumentException("Cannot decode " + jValue + " to Double.");
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(DoubleParam$.class);
   }

   private DoubleParam$() {
   }
}
