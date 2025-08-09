package org.apache.spark.sql.streaming;

import java.util.concurrent.TimeUnit;
import org.apache.spark.annotation.Evolving;
import org.apache.spark.sql.execution.streaming.AvailableNowTrigger$;
import org.apache.spark.sql.execution.streaming.ContinuousTrigger;
import org.apache.spark.sql.execution.streaming.OneTimeTrigger$;
import org.apache.spark.sql.execution.streaming.ProcessingTimeTrigger;
import scala.concurrent.duration.Duration;

@Evolving
public class Trigger {
   public static Trigger ProcessingTime(long intervalMs) {
      return ProcessingTimeTrigger.create(intervalMs, TimeUnit.MILLISECONDS);
   }

   public static Trigger ProcessingTime(long interval, TimeUnit timeUnit) {
      return ProcessingTimeTrigger.create(interval, timeUnit);
   }

   public static Trigger ProcessingTime(Duration interval) {
      return ProcessingTimeTrigger.apply(interval);
   }

   public static Trigger ProcessingTime(String interval) {
      return ProcessingTimeTrigger.apply(interval);
   }

   /** @deprecated */
   @Deprecated(
      since = "3.4.0"
   )
   public static Trigger Once() {
      return OneTimeTrigger$.MODULE$;
   }

   public static Trigger AvailableNow() {
      return AvailableNowTrigger$.MODULE$;
   }

   public static Trigger Continuous(long intervalMs) {
      return ContinuousTrigger.apply(intervalMs);
   }

   public static Trigger Continuous(long interval, TimeUnit timeUnit) {
      return ContinuousTrigger.create(interval, timeUnit);
   }

   public static Trigger Continuous(Duration interval) {
      return ContinuousTrigger.apply(interval);
   }

   public static Trigger Continuous(String interval) {
      return ContinuousTrigger.apply(interval);
   }
}
