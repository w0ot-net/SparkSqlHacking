package org.apache.spark.sql.catalyst.streaming;

import java.util.Locale;
import org.apache.spark.SparkIllegalArgumentException;
import org.apache.spark.sql.streaming.OutputMode;
import scala.Tuple2;
import scala.Predef.;
import scala.collection.immutable.Map;

public final class InternalOutputModes$ {
   public static final InternalOutputModes$ MODULE$ = new InternalOutputModes$();

   public OutputMode apply(final String outputMode) {
      String var3 = outputMode.toLowerCase(Locale.ROOT);
      switch (var3 == null ? 0 : var3.hashCode()) {
         case -1411068134:
            if ("append".equals(var3)) {
               return OutputMode.Append();
            }
            break;
         case -838846263:
            if ("update".equals(var3)) {
               return OutputMode.Update();
            }
            break;
         case -599445191:
            if ("complete".equals(var3)) {
               return OutputMode.Complete();
            }
      }

      throw new SparkIllegalArgumentException("STREAMING_OUTPUT_MODE.INVALID", (Map).MODULE$.Map().apply(scala.runtime.ScalaRunTime..MODULE$.wrapRefArray((Object[])(new Tuple2[]{scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(.MODULE$.ArrowAssoc("outputMode"), outputMode)}))));
   }

   private InternalOutputModes$() {
   }
}
