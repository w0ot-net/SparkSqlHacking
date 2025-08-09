package org.apache.spark.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!2\u0001BB\u0004\u0011\u0002\u0007\u0005\u0011b\u0004\u0005\u0006-\u0001!\t\u0001\u0007\u0005\u00069\u0001!\t!H\u0004\u0006C\u001dA\tA\t\u0004\u0006\r\u001dA\t\u0001\n\u0005\u0006M\u0011!\ta\n\u0002\u000e'B\f'o[#omV#\u0018\u000e\\:\u000b\u0005!I\u0011\u0001B;uS2T!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'oZ\n\u0003\u0001A\u0001\"!\u0005\u000b\u000e\u0003IQ\u0011aE\u0001\u0006g\u000e\fG.Y\u0005\u0003+I\u0011a!\u00118z%\u00164\u0017A\u0002\u0013j]&$He\u0001\u0001\u0015\u0003e\u0001\"!\u0005\u000e\n\u0005m\u0011\"\u0001B+oSR\f\u0011\"[:UKN$\u0018N\\4\u0016\u0003y\u0001\"!E\u0010\n\u0005\u0001\u0012\"a\u0002\"p_2,\u0017M\\\u0001\u000e'B\f'o[#omV#\u0018\u000e\\:\u0011\u0005\r\"Q\"A\u0004\u0014\u0007\u0011\u0001R\u0005\u0005\u0002$\u0001\u00051A(\u001b8jiz\"\u0012A\t"
)
public interface SparkEnvUtils {
   // $FF: synthetic method
   static boolean isTesting$(final SparkEnvUtils $this) {
      return $this.isTesting();
   }

   default boolean isTesting() {
      return System.getenv("SPARK_TESTING") != null || System.getProperty("spark.testing") != null;
   }

   static void $init$(final SparkEnvUtils $this) {
   }
}
