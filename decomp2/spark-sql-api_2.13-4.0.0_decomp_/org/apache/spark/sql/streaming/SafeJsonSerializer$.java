package org.apache.spark.sql.streaming;

import java.lang.invoke.SerializedLambda;
import java.util.Map;
import org.json4s.JDouble;
import org.json4s.JValue;
import scala.Function1;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.runtime.RichDouble.;

public final class SafeJsonSerializer$ {
   public static final SafeJsonSerializer$ MODULE$ = new SafeJsonSerializer$();

   public JValue safeDoubleToJValue(final double value) {
      return (JValue)(!Double.isNaN(value) && !.MODULE$.isInfinity$extension(scala.Predef..MODULE$.doubleWrapper(value)) ? new JDouble(value) : org.json4s.JNothing..MODULE$);
   }

   public JValue safeMapToJValue(final Map map, final Function1 valueToJValue) {
      if (map != null && !map.isEmpty()) {
         Seq keys = (Seq)scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(map).asScala().keySet().toSeq().sorted(scala.math.Ordering.String..MODULE$);
         return (JValue)((IterableOnceOps)keys.map((k) -> org.json4s.JsonDSL..MODULE$.pair2jvalue(scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(k), valueToJValue.apply(map.get(k))), scala.Predef..MODULE$.$conforms()))).reduce((x$8, x$9) -> org.json4s.JsonListAssoc..MODULE$.$tilde$extension(org.json4s.JsonDSL..MODULE$.jobject2assoc(x$8), x$9));
      } else {
         return org.json4s.JNothing..MODULE$;
      }
   }

   private SafeJsonSerializer$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
