package org.apache.spark.deploy.master;

import org.apache.spark.annotation.DeveloperApi;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005)2q\u0001B\u0003\u0011\u0002\u0007\u0005\u0001\u0003C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0004\u001d\u0001\t\u0007i\u0011A\u000f\t\u000b\t\u0002A\u0011\u0001\r\u0003'1+\u0017\rZ3s\u000b2,7\r^5p]\u0006;WM\u001c;\u000b\u0005\u00199\u0011AB7bgR,'O\u0003\u0002\t\u0013\u00051A-\u001a9m_fT!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'oZ\u0002\u0001'\t\u0001\u0011\u0003\u0005\u0002\u0013+5\t1CC\u0001\u0015\u0003\u0015\u00198-\u00197b\u0013\t12C\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003e\u0001\"A\u0005\u000e\n\u0005m\u0019\"\u0001B+oSR\fa\"\\1ti\u0016\u0014\u0018J\\:uC:\u001cW-F\u0001\u001f!\ty\u0002%D\u0001\u0006\u0013\t\tSAA\bMK\u0006$WM]#mK\u000e$\u0018M\u00197f\u0003\u0011\u0019Ho\u001c9)\u0005\u0001!\u0003CA\u0013)\u001b\u00051#BA\u0014\n\u0003)\tgN\\8uCRLwN\\\u0005\u0003S\u0019\u0012A\u0002R3wK2|\u0007/\u001a:Ba&\u0004"
)
public interface LeaderElectionAgent {
   LeaderElectable masterInstance();

   // $FF: synthetic method
   static void stop$(final LeaderElectionAgent $this) {
      $this.stop();
   }

   default void stop() {
   }

   static void $init$(final LeaderElectionAgent $this) {
   }
}
