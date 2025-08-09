package org.apache.spark.ml.param;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkException;
import org.apache.spark.ml.linalg.JsonMatrixConverter$;
import org.apache.spark.ml.linalg.JsonVectorConverter$;
import org.json4s.Formats;
import org.json4s.JObject;
import org.json4s.JString;
import org.json4s.JValue;
import org.json4s.jackson.JsonMethods.;
import scala.collection.immutable.List;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

public final class Param$ implements Serializable {
   public static final Param$ MODULE$ = new Param$();

   public Object jsonDecode(final String json) {
      JValue jValue = .MODULE$.parse(json, .MODULE$.parse$default$2(), .MODULE$.parse$default$3(), org.json4s.AsJsonInput..MODULE$.stringAsJsonInput());
      if (jValue instanceof JString var6) {
         String x = var6.s();
         return x;
      } else if (!(jValue instanceof JObject var8)) {
         throw new UnsupportedOperationException("The default jsonDecode only supports string, vector and matrix. " + this.getClass().getName() + " must override jsonDecode to support its value type.");
      } else {
         List v = var8.obj();
         List keys = v.map((x$1) -> (String)x$1._1());
         if (keys.contains("class")) {
            label29: {
               Formats formats = org.json4s.DefaultFormats..MODULE$;
               String className = (String)org.json4s.ExtractableJsonAstNode..MODULE$.extract$extension(org.json4s.package..MODULE$.jvalue2extractable(org.json4s.MonadicJValue..MODULE$.$bslash$extension(org.json4s.package..MODULE$.jvalue2monadic(jValue), "class")), formats, scala.reflect.ManifestFactory..MODULE$.classType(String.class));
               String var10000 = JsonMatrixConverter$.MODULE$.className();
               if (var10000 == null) {
                  if (className == null) {
                     break label29;
                  }
               } else if (var10000.equals(className)) {
                  break label29;
               }

               throw new SparkException("unrecognized class " + className + " in " + json);
            }

            String[] checkFields = (String[])((Object[])(new String[]{"numRows", "numCols", "values", "isTransposed", "type"}));
            scala.Predef..MODULE$.require(scala.collection.ArrayOps..MODULE$.forall$extension(scala.Predef..MODULE$.refArrayOps((Object[])checkFields), (elem) -> BoxesRunTime.boxToBoolean($anonfun$jsonDecode$2(keys, elem))), () -> {
               String var10000 = scala.Predef..MODULE$.wrapRefArray((Object[])checkFields).mkString(", ");
               return "Expect a JSON serialized Matrix but cannot find fields " + var10000 + " in " + json + ".";
            });
            return JsonMatrixConverter$.MODULE$.fromJson(json);
         } else {
            scala.Predef..MODULE$.require(keys.contains("type") && keys.contains("values"), () -> "Expect a JSON serialized vector/matrix but cannot find fields 'type' and 'values' in " + json + ".");
            return JsonVectorConverter$.MODULE$.fromJson(json);
         }
      }
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(Param$.class);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$jsonDecode$2(final List keys$1, final Object elem) {
      return keys$1.contains(elem);
   }

   private Param$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
