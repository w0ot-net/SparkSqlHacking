package org.apache.spark.ml.clustering;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.param.Param;
import org.apache.spark.ml.util.DefaultParamsReader;
import org.apache.spark.util.VersionUtils.;
import org.json4s.Formats;
import org.json4s.JObject;
import org.json4s.JValue;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.immutable.List;
import scala.runtime.BoxedUnit;
import scala.runtime.ModuleSerializationProxy;

public final class LDAParams$ implements Serializable {
   public static final LDAParams$ MODULE$ = new LDAParams$();

   public void getAndSetParams(final LDAParams model, final DefaultParamsReader.Metadata metadata) {
      Tuple2 var5 = .MODULE$.majorMinorVersion(metadata.sparkVersion());
      if (var5 != null) {
         int var6 = var5._1$mcI$sp();
         int var7 = var5._2$mcI$sp();
         if (1 == var6 && 6 == var7) {
            Formats format = org.json4s.DefaultFormats..MODULE$;
            JValue var9 = metadata.params();
            if (var9 instanceof JObject) {
               JObject var10 = (JObject)var9;
               List pairs = var10.obj();
               pairs.foreach((x0$1) -> {
                  if (x0$1 == null) {
                     throw new MatchError(x0$1);
                  } else {
                     JValue jsonValue;
                     String var10000;
                     label21: {
                        label20: {
                           String paramName = (String)x0$1._1();
                           jsonValue = (JValue)x0$1._2();
                           String var7 = "topicDistribution";
                           if (paramName == null) {
                              if (var7 == null) {
                                 break label20;
                              }
                           } else if (paramName.equals(var7)) {
                              break label20;
                           }

                           var10000 = paramName;
                           break label21;
                        }

                        var10000 = "topicDistributionCol";
                     }

                     String origParam = var10000;
                     Param param = model.getParam(origParam);
                     Object value = param.jsonDecode(org.json4s.jackson.JsonMethods..MODULE$.compact(org.json4s.jackson.JsonMethods..MODULE$.render(jsonValue, org.json4s.jackson.JsonMethods..MODULE$.render$default$2(), org.json4s.jackson.JsonMethods..MODULE$.render$default$3())));
                     return (LDAParams)model.set(param, value);
                  }
               });
               BoxedUnit var12 = BoxedUnit.UNIT;
               var12 = BoxedUnit.UNIT;
               return;
            }

            throw new IllegalArgumentException("Cannot recognize JSON metadata: " + metadata.metadataJson() + ".");
         }
      }

      metadata.getAndSetParams(model, metadata.getAndSetParams$default$2());
      BoxedUnit var10000 = BoxedUnit.UNIT;
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(LDAParams$.class);
   }

   private LDAParams$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
