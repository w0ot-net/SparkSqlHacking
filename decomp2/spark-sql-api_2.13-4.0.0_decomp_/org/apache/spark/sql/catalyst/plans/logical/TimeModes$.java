package org.apache.spark.sql.catalyst.plans.logical;

import java.util.Locale;
import org.apache.spark.SparkIllegalArgumentException;
import org.apache.spark.sql.streaming.TimeMode;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;

public final class TimeModes$ {
   public static final TimeModes$ MODULE$ = new TimeModes$();

   public TimeMode apply(final String timeMode) {
      String var3 = timeMode.toLowerCase(Locale.ROOT);
      switch (var3 == null ? 0 : var3.hashCode()) {
         case 3387192:
            if ("none".equals(var3)) {
               return NoTime$.MODULE$;
            }
            break;
         case 32368743:
            if ("eventtime".equals(var3)) {
               return EventTime$.MODULE$;
            }
            break;
         case 196919392:
            if ("processingtime".equals(var3)) {
               return ProcessingTime$.MODULE$;
            }
      }

      throw new SparkIllegalArgumentException("STATEFUL_PROCESSOR_UNKNOWN_TIME_MODE", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("timeMode"), timeMode)}))));
   }

   private TimeModes$() {
   }
}
