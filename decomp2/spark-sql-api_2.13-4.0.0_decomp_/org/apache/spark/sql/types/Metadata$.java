package org.apache.spark.sql.types;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.Stable;
import org.apache.spark.sql.errors.DataTypeErrors$;
import org.json4s.JArray;
import org.json4s.JBool;
import org.json4s.JDouble;
import org.json4s.JInt;
import org.json4s.JLong;
import org.json4s.JObject;
import org.json4s.JString;
import org.json4s.JValue;
import scala.MatchError;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.List;
import scala.collection.immutable.Map;
import scala.math.BigInt;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.Statics;

@Stable
public final class Metadata$ implements Serializable {
   public static final Metadata$ MODULE$ = new Metadata$();
   private static final Metadata _empty;

   static {
      _empty = new Metadata(.MODULE$.Map().empty());
   }

   public Metadata empty() {
      return _empty;
   }

   public Metadata fromJson(final String json) {
      return this.fromJObject((JObject)org.json4s.jackson.JsonMethods..MODULE$.parse(json, org.json4s.jackson.JsonMethods..MODULE$.parse$default$2(), org.json4s.jackson.JsonMethods..MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput()));
   }

   public Metadata fromJObject(final JObject jObj) {
      MetadataBuilder builder = new MetadataBuilder();
      jObj.obj().foreach((x0$1) -> {
         if (x0$1 != null) {
            String key = (String)x0$1._1();
            JValue var6 = (JValue)x0$1._2();
            if (var6 instanceof JInt) {
               JInt var7 = (JInt)var6;
               BigInt value = var7.num();
               return builder.putLong(key, value.toLong());
            }
         }

         if (x0$1 != null) {
            String key = (String)x0$1._1();
            JValue var10 = (JValue)x0$1._2();
            if (var10 instanceof JLong) {
               JLong var11 = (JLong)var10;
               long value = var11.num();
               return builder.putLong(key, value);
            }
         }

         if (x0$1 != null) {
            String key = (String)x0$1._1();
            JValue var15 = (JValue)x0$1._2();
            if (var15 instanceof JDouble) {
               JDouble var16 = (JDouble)var15;
               double value = var16.num();
               return builder.putDouble(key, value);
            }
         }

         if (x0$1 != null) {
            String key = (String)x0$1._1();
            JValue var20 = (JValue)x0$1._2();
            if (var20 instanceof JBool) {
               JBool var21 = (JBool)var20;
               boolean value = var21.value();
               return builder.putBoolean(key, value);
            }
         }

         if (x0$1 != null) {
            String key = (String)x0$1._1();
            JValue var24 = (JValue)x0$1._2();
            if (var24 instanceof JString) {
               JString var25 = (JString)var24;
               String value = var25.s();
               return builder.putString(key, value);
            }
         }

         if (x0$1 != null) {
            String key = (String)x0$1._1();
            JValue o = (JValue)x0$1._2();
            if (o instanceof JObject) {
               JObject var29 = (JObject)o;
               return builder.putMetadata(key, MODULE$.fromJObject(var29));
            }
         }

         if (x0$1 != null) {
            String key = (String)x0$1._1();
            JValue var31 = (JValue)x0$1._2();
            if (var31 instanceof JArray) {
               JArray var32 = (JArray)var31;
               List value = var32.arr();
               if (value.isEmpty()) {
                  return builder.putLongArray(key, (long[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Long()));
               }

               JValue var34 = (JValue)value.head();
               if (var34 instanceof JInt) {
                  return builder.putLongArray(key, (long[])value.map((x$1) -> BoxesRunTime.boxToLong($anonfun$fromJObject$2(x$1))).toArray(scala.reflect.ClassTag..MODULE$.Long()));
               }

               if (var34 instanceof JLong) {
                  return builder.putLongArray(key, (long[])value.map((x$2) -> BoxesRunTime.boxToLong($anonfun$fromJObject$3(x$2))).toArray(scala.reflect.ClassTag..MODULE$.Long()));
               }

               if (var34 instanceof JDouble) {
                  return builder.putDoubleArray(key, (double[])value.map((x$3) -> BoxesRunTime.boxToDouble($anonfun$fromJObject$4(x$3))).toArray(scala.reflect.ClassTag..MODULE$.Double()));
               }

               if (var34 instanceof JBool) {
                  return builder.putBooleanArray(key, (boolean[])value.map((x$4) -> BoxesRunTime.boxToBoolean($anonfun$fromJObject$5(x$4))).toArray(scala.reflect.ClassTag..MODULE$.Boolean()));
               }

               if (var34 instanceof JString) {
                  return builder.putStringArray(key, (String[])value.map((x$5) -> x$5.s()).toArray(scala.reflect.ClassTag..MODULE$.apply(String.class)));
               }

               if (var34 instanceof JObject) {
                  return builder.putMetadataArray(key, (Metadata[])value.map((jObj) -> MODULE$.fromJObject(jObj)).toArray(scala.reflect.ClassTag..MODULE$.apply(Metadata.class)));
               }

               throw DataTypeErrors$.MODULE$.unsupportedArrayTypeError(var34.getClass());
            }
         }

         if (x0$1 != null) {
            String key = (String)x0$1._1();
            JValue var36 = (JValue)x0$1._2();
            if (org.json4s.JNull..MODULE$.equals(var36)) {
               return builder.putNull(key);
            }
         }

         if (x0$1 != null) {
            JValue other = (JValue)x0$1._2();
            throw DataTypeErrors$.MODULE$.unsupportedJavaTypeError(other.getClass());
         } else {
            throw new MatchError(x0$1);
         }
      });
      return builder.build();
   }

   public JValue org$apache$spark$sql$types$Metadata$$toJsonValue(final Object obj) {
      while(!(obj instanceof Map)) {
         if (scala.runtime.ScalaRunTime..MODULE$.isArray(obj, 1)) {
            List values = .MODULE$.genericWrapArray(obj).toList().map((objx) -> MODULE$.org$apache$spark$sql$types$Metadata$$toJsonValue(objx));
            return new JArray(values);
         }

         if (obj instanceof Long) {
            long var9 = BoxesRunTime.unboxToLong(obj);
            return new JInt(scala.math.BigInt..MODULE$.long2bigInt(var9));
         }

         if (obj instanceof Double) {
            double var11 = BoxesRunTime.unboxToDouble(obj);
            return new JDouble(var11);
         }

         if (obj instanceof Boolean) {
            boolean var13 = BoxesRunTime.unboxToBoolean(obj);
            return org.json4s.JBool..MODULE$.apply(var13);
         }

         if (obj instanceof String var14) {
            return new JString(var14);
         }

         if (obj == null) {
            return org.json4s.JNull..MODULE$;
         }

         if (!(obj instanceof Metadata)) {
            throw DataTypeErrors$.MODULE$.unsupportedJavaTypeError(obj.getClass());
         }

         Metadata var15 = (Metadata)obj;
         obj = var15.map();
      }

      Map var5 = (Map)obj;
      List fields = var5.toList().map((x0$1) -> {
         if (x0$1 != null) {
            Object k = x0$1._1();
            Object v = x0$1._2();
            return new Tuple2(k.toString(), MODULE$.org$apache$spark$sql$types$Metadata$$toJsonValue(v));
         } else {
            throw new MatchError(x0$1);
         }
      });
      return new JObject(fields);
   }

   public int org$apache$spark$sql$types$Metadata$$hash(final Object obj) {
      while(!(obj instanceof Map)) {
         if (scala.runtime.ScalaRunTime..MODULE$.isArray(obj, 1)) {
            return Statics.anyHash(org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(obj).toImmutableArraySeq().map((objx) -> BoxesRunTime.boxToInteger($anonfun$hash$2(objx))));
         }

         if (obj instanceof Long) {
            long var7 = BoxesRunTime.unboxToLong(obj);
            return Statics.longHash(var7);
         }

         if (obj instanceof Double) {
            double var9 = BoxesRunTime.unboxToDouble(obj);
            return Statics.doubleHash(var9);
         }

         if (obj instanceof Boolean) {
            boolean var11 = BoxesRunTime.unboxToBoolean(obj);
            if (var11) {
               return 1231;
            }

            return 1237;
         }

         if (obj instanceof String var12) {
            return Statics.anyHash(var12);
         }

         if (!(obj instanceof Metadata)) {
            if (obj == null) {
               return 0;
            }

            throw DataTypeErrors$.MODULE$.unsupportedJavaTypeError(obj.getClass());
         }

         Metadata var13 = (Metadata)obj;
         obj = var13.map();
      }

      Map var5 = (Map)obj;
      return Statics.anyHash(var5.transform((x$6, v) -> BoxesRunTime.boxToInteger($anonfun$hash$1(x$6, v))));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Metadata$.class);
   }

   // $FF: synthetic method
   public static final long $anonfun$fromJObject$2(final JInt x$1) {
      return x$1.num().toLong();
   }

   // $FF: synthetic method
   public static final long $anonfun$fromJObject$3(final JLong x$2) {
      return x$2.num();
   }

   // $FF: synthetic method
   public static final double $anonfun$fromJObject$4(final JDouble x$3) {
      return x$3.num();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$fromJObject$5(final JBool x$4) {
      return x$4.value();
   }

   // $FF: synthetic method
   public static final int $anonfun$hash$1(final Object x$6, final Object v) {
      return MODULE$.org$apache$spark$sql$types$Metadata$$hash(v);
   }

   // $FF: synthetic method
   public static final int $anonfun$hash$2(final Object obj) {
      return MODULE$.org$apache$spark$sql$types$Metadata$$hash(obj);
   }

   private Metadata$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
