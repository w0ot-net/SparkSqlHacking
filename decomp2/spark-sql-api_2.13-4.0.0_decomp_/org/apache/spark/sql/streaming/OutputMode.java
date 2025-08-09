package org.apache.spark.sql.streaming;

import org.apache.spark.annotation.Evolving;
import org.apache.spark.sql.catalyst.streaming.InternalOutputModes;

@Evolving
public class OutputMode {
   public static OutputMode Append() {
      return InternalOutputModes.Append$.MODULE$;
   }

   public static OutputMode Complete() {
      return InternalOutputModes.Complete$.MODULE$;
   }

   public static OutputMode Update() {
      return InternalOutputModes.Update$.MODULE$;
   }
}
