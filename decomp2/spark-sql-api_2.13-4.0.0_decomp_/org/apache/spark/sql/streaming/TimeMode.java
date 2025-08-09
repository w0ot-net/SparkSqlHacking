package org.apache.spark.sql.streaming;

import org.apache.spark.annotation.Evolving;
import org.apache.spark.sql.catalyst.plans.logical.EventTime$;
import org.apache.spark.sql.catalyst.plans.logical.NoTime$;
import org.apache.spark.sql.catalyst.plans.logical.ProcessingTime$;

@Evolving
public class TimeMode {
   public static final TimeMode None() {
      return NoTime$.MODULE$;
   }

   public static final TimeMode ProcessingTime() {
      return ProcessingTime$.MODULE$;
   }

   public static final TimeMode EventTime() {
      return EventTime$.MODULE$;
   }
}
