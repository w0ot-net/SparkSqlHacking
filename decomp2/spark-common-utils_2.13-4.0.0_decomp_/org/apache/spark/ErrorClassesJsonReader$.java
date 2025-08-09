package org.apache.spark;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.json.JsonMapper;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.net.URL;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.StringOps.;
import scala.collection.immutable.Map;
import scala.runtime.BoxesRunTime;
import scala.util.matching.Regex;

public final class ErrorClassesJsonReader$ {
   public static final ErrorClassesJsonReader$ MODULE$ = new ErrorClassesJsonReader$();
   private static final Regex org$apache$spark$ErrorClassesJsonReader$$TEMPLATE_REGEX;
   private static final String[] org$apache$spark$ErrorClassesJsonReader$$MORE_PARAMS_ALLOWLIST;
   private static final JsonMapper mapper;

   static {
      org$apache$spark$ErrorClassesJsonReader$$TEMPLATE_REGEX = .MODULE$.r$extension(scala.Predef..MODULE$.augmentString("<([a-zA-Z0-9_-]+)>"));
      org$apache$spark$ErrorClassesJsonReader$$MORE_PARAMS_ALLOWLIST = (String[])((Object[])(new String[]{"CAST_INVALID_INPUT", "CAST_OVERFLOW"}));
      mapper = (JsonMapper)JsonMapper.builder().addModule(com.fasterxml.jackson.module.scala.DefaultScalaModule..MODULE$).build();
   }

   public Regex org$apache$spark$ErrorClassesJsonReader$$TEMPLATE_REGEX() {
      return org$apache$spark$ErrorClassesJsonReader$$TEMPLATE_REGEX;
   }

   public String[] org$apache$spark$ErrorClassesJsonReader$$MORE_PARAMS_ALLOWLIST() {
      return org$apache$spark$ErrorClassesJsonReader$$MORE_PARAMS_ALLOWLIST;
   }

   private JsonMapper mapper() {
      return mapper;
   }

   public Map org$apache$spark$ErrorClassesJsonReader$$readAsMap(final URL url) {
      Map map = (Map)this.mapper().readValue(url, new TypeReference() {
      });
      Option errorClassWithDots = map.collectFirst(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final Object applyOrElse(final Tuple2 x1, final Function1 default) {
            if (x1 != null) {
               String errorClass = (String)x1._1();
               if (.MODULE$.contains$extension(scala.Predef..MODULE$.augmentString(errorClass), '.')) {
                  return errorClass;
               }
            }

            if (x1 != null) {
               ErrorInfo var6 = (ErrorInfo)x1._2();
               if (var6 != null) {
                  Option var7 = var6.subClass();
                  if (var7 instanceof Some) {
                     Some var8 = (Some)var7;
                     Map map = (Map)var8.value();
                     if (map.keys().exists((x$5) -> BoxesRunTime.boxToBoolean($anonfun$applyOrElse$1(x$5)))) {
                        return map.keys().collectFirst(new Serializable() {
                           private static final long serialVersionUID = 0L;

                           public final Object applyOrElse(final String x1, final Function1 default) {
                              return .MODULE$.contains$extension(scala.Predef..MODULE$.augmentString(x1), '.') ? x1 : default.apply(x1);
                           }

                           public final boolean isDefinedAt(final String x1) {
                              return .MODULE$.contains$extension(scala.Predef..MODULE$.augmentString(x1), '.');
                           }
                        }).get();
                     }
                  }
               }
            }

            return default.apply(x1);
         }

         public final boolean isDefinedAt(final Tuple2 x1) {
            if (x1 != null) {
               String errorClass = (String)x1._1();
               if (.MODULE$.contains$extension(scala.Predef..MODULE$.augmentString(errorClass), '.')) {
                  return true;
               }
            }

            if (x1 != null) {
               ErrorInfo var5 = (ErrorInfo)x1._2();
               if (var5 != null) {
                  Option var6 = var5.subClass();
                  if (var6 instanceof Some) {
                     Some var7 = (Some)var6;
                     Map map = (Map)var7.value();
                     if (map.keys().exists((x$5) -> BoxesRunTime.boxToBoolean($anonfun$isDefinedAt$1(x$5)))) {
                        return true;
                     }
                  }
               }
            }

            return false;
         }

         // $FF: synthetic method
         public static final boolean $anonfun$applyOrElse$1(final String x$5) {
            return .MODULE$.contains$extension(scala.Predef..MODULE$.augmentString(x$5), '.');
         }

         // $FF: synthetic method
         public static final boolean $anonfun$isDefinedAt$1(final String x$5) {
            return .MODULE$.contains$extension(scala.Predef..MODULE$.augmentString(x$5), '.');
         }

         // $FF: synthetic method
         private static Object $deserializeLambda$(SerializedLambda var0) {
            return Class.lambdaDeserialize<invokedynamic>(var0);
         }
      });
      if (errorClassWithDots.isEmpty()) {
         return map;
      } else {
         throw SparkException$.MODULE$.internalError("Found the (sub-)error class with dots: " + errorClassWithDots.get());
      }
   }

   private ErrorClassesJsonReader$() {
   }
}
