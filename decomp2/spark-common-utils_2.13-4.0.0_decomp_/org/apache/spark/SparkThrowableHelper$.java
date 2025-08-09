package org.apache.spark;

import com.fasterxml.jackson.core.JsonGenerator;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.util.JsonUtils$;
import org.apache.spark.util.SparkClassUtils$;
import scala.Enumeration;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.runtime.BoxedUnit;

public final class SparkThrowableHelper$ {
   public static final SparkThrowableHelper$ MODULE$ = new SparkThrowableHelper$();
   private static final ErrorClassesJsonReader errorReader;

   static {
      errorReader = new ErrorClassesJsonReader(new .colon.colon(SparkClassUtils$.MODULE$.getSparkClassLoader().getResource("error/error-conditions.json"), scala.collection.immutable.Nil..MODULE$));
   }

   public ErrorClassesJsonReader errorReader() {
      return errorReader;
   }

   public String getMessage(final String errorClass, final Map messageParameters) {
      return this.getMessage(errorClass, messageParameters, "");
   }

   public String getMessage(final String errorClass, final java.util.Map messageParameters) {
      return this.getMessage(errorClass, scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(messageParameters).asScala().toMap(scala..less.colon.less..MODULE$.refl()), "");
   }

   public String getMessage(final String errorClass, final Map messageParameters, final String context) {
      String displayMessage = this.errorReader().getErrorMessage(errorClass, messageParameters);
      String sqlState = this.getSqlState(errorClass);
      String displaySqlState = sqlState == null ? "" : " SQLSTATE: " + sqlState;
      String var10000 = context.isEmpty() ? "" : "\n";
      String displayQueryContext = var10000 + context;
      String prefix = errorClass.startsWith("_LEGACY_ERROR_") ? "" : "[" + errorClass + "] ";
      return prefix + displayMessage + displaySqlState + displayQueryContext;
   }

   public String getSqlState(final String errorClass) {
      return this.errorReader().getSqlState(errorClass);
   }

   public boolean isValidErrorClass(final String errorClass) {
      return this.errorReader().isValidErrorClass(errorClass);
   }

   public Seq getMessageParameters(final String errorClass) {
      return this.errorReader().getMessageParameters(errorClass);
   }

   public boolean isInternalError(final String errorClass) {
      return errorClass != null && errorClass.startsWith("INTERNAL_ERROR");
   }

   public String getMessage(final Throwable e, final Enumeration.Value format) {
      Enumeration.Value var10000 = ErrorMessageFormat$.MODULE$.PRETTY();
      if (var10000 == null) {
         if (format == null) {
            return e.getMessage();
         }
      } else if (var10000.equals(format)) {
         return e.getMessage();
      }

      label77: {
         label86: {
            var10000 = ErrorMessageFormat$.MODULE$.MINIMAL();
            if (var10000 == null) {
               if (format == null) {
                  break label86;
               }
            } else if (var10000.equals(format)) {
               break label86;
            }

            label69: {
               var10000 = ErrorMessageFormat$.MODULE$.STANDARD();
               if (var10000 == null) {
                  if (format == null) {
                     break label69;
                  }
               } else if (var10000.equals(format)) {
                  break label69;
               }

               var15 = false;
               break label77;
            }

            var15 = true;
            break label77;
         }

         var15 = true;
      }

      if (var15 && ((SparkThrowable)e).getCondition() == null) {
         return JsonUtils$.MODULE$.toJsonString((generator) -> {
            $anonfun$getMessage$1(e, generator);
            return BoxedUnit.UNIT;
         });
      } else {
         label61: {
            label87: {
               var10000 = ErrorMessageFormat$.MODULE$.MINIMAL();
               if (var10000 == null) {
                  if (format == null) {
                     break label87;
                  }
               } else if (var10000.equals(format)) {
                  break label87;
               }

               label53: {
                  var10000 = ErrorMessageFormat$.MODULE$.STANDARD();
                  if (var10000 == null) {
                     if (format == null) {
                        break label53;
                     }
                  } else if (var10000.equals(format)) {
                     break label53;
                  }

                  var18 = false;
                  break label61;
               }

               var18 = true;
               break label61;
            }

            var18 = true;
         }

         if (var18) {
            String errorClass = ((SparkThrowable)e).getCondition();
            return JsonUtils$.MODULE$.toJsonString((generator) -> {
               $anonfun$getMessage$2(errorClass, format, e, generator);
               return BoxedUnit.UNIT;
            });
         } else {
            throw new MatchError(format);
         }
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$getMessage$1(final Throwable e$1, final JsonGenerator generator) {
      JsonGenerator g = generator.useDefaultPrettyPrinter();
      g.writeStartObject();
      g.writeStringField("errorClass", "LEGACY");
      g.writeObjectFieldStart("messageParameters");
      g.writeStringField("message", e$1.getMessage());
      g.writeEndObject();
      g.writeEndObject();
   }

   // $FF: synthetic method
   public static final void $anonfun$getMessage$4(final JsonGenerator g$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         String name = (String)x0$1._1();
         String value = (String)x0$1._2();
         g$1.writeStringField(name, value.replaceAll("#\\d+", "#x"));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$getMessage$5(final JsonGenerator g$1, final QueryContext c) {
      g$1.writeStartObject();
      QueryContextType var3 = c.contextType();
      if (QueryContextType.SQL.equals(var3)) {
         g$1.writeStringField("objectType", c.objectType());
         g$1.writeStringField("objectName", c.objectName());
         int startIndex = c.startIndex() + 1;
         if (startIndex > 0) {
            g$1.writeNumberField("startIndex", startIndex);
         }

         int stopIndex = c.stopIndex() + 1;
         if (stopIndex > 0) {
            g$1.writeNumberField("stopIndex", stopIndex);
         }

         g$1.writeStringField("fragment", c.fragment());
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         if (!QueryContextType.DataFrame.equals(var3)) {
            throw new MatchError(var3);
         }

         g$1.writeStringField("fragment", c.fragment());
         g$1.writeStringField("callSite", c.callSite());
         BoxedUnit var6 = BoxedUnit.UNIT;
      }

      g$1.writeEndObject();
   }

   // $FF: synthetic method
   public static final void $anonfun$getMessage$2(final String errorClass$1, final Enumeration.Value format$1, final Throwable e$1, final JsonGenerator generator) {
      JsonGenerator g;
      label26: {
         g = generator.useDefaultPrettyPrinter();
         g.writeStartObject();
         g.writeStringField("errorClass", errorClass$1);
         Enumeration.Value var5 = ErrorMessageFormat$.MODULE$.STANDARD();
         if (format$1 == null) {
            if (var5 != null) {
               break label26;
            }
         } else if (!format$1.equals(var5)) {
            break label26;
         }

         g.writeStringField("messageTemplate", MODULE$.errorReader().getMessageTemplate(errorClass$1));
      }

      String sqlState = ((SparkThrowable)e$1).getSqlState();
      if (sqlState != null) {
         g.writeStringField("sqlState", sqlState);
      }

      java.util.Map messageParameters = ((SparkThrowable)e$1).getMessageParameters();
      if (!messageParameters.isEmpty()) {
         g.writeObjectFieldStart("messageParameters");
         ((IterableOnceOps)scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(messageParameters).asScala().toMap(scala..less.colon.less..MODULE$.refl()).toSeq().sortBy((x$1) -> (String)x$1._1(), scala.math.Ordering.String..MODULE$)).foreach((x0$1) -> {
            $anonfun$getMessage$4(g, x0$1);
            return BoxedUnit.UNIT;
         });
         g.writeEndObject();
      }

      QueryContext[] queryContext = ((SparkThrowable)e$1).getQueryContext();
      if (!scala.collection.ArrayOps..MODULE$.isEmpty$extension(scala.Predef..MODULE$.refArrayOps(queryContext))) {
         g.writeArrayFieldStart("queryContext");
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(((SparkThrowable)e$1).getQueryContext()), (c) -> {
            $anonfun$getMessage$5(g, c);
            return BoxedUnit.UNIT;
         });
         g.writeEndArray();
      }

      g.writeEndObject();
   }

   private SparkThrowableHelper$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
