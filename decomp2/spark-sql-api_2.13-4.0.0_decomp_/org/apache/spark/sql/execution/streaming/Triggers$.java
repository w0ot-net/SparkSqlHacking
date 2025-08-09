package org.apache.spark.sql.execution.streaming;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.TimeUnit;
import org.apache.spark.SparkIllegalArgumentException;
import org.apache.spark.sql.catalyst.util.SparkDateTimeUtils$;
import org.apache.spark.sql.catalyst.util.SparkIntervalUtils$;
import org.apache.spark.unsafe.types.CalendarInterval;
import org.apache.spark.unsafe.types.UTF8String;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;
import scala.concurrent.duration.Duration;

public final class Triggers$ {
   public static final Triggers$ MODULE$ = new Triggers$();

   public void validate(final long intervalMs) {
      .MODULE$.require(intervalMs >= 0L, () -> "the interval of trigger should not be negative");
   }

   public long convert(final String interval) {
      CalendarInterval cal = SparkIntervalUtils$.MODULE$.stringToInterval(UTF8String.fromString(interval));
      if (cal.months != 0) {
         throw new SparkIllegalArgumentException("_LEGACY_ERROR_TEMP_3262", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("interval"), interval)}))));
      } else {
         long microsInDays = Math.multiplyExact((long)cal.days, 86400000000L);
         return SparkDateTimeUtils$.MODULE$.microsToMillis(Math.addExact(cal.microseconds, microsInDays));
      }
   }

   public long convert(final Duration interval) {
      return interval.toMillis();
   }

   public long convert(final long interval, final TimeUnit unit) {
      return unit.toMillis(interval);
   }

   private Triggers$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
