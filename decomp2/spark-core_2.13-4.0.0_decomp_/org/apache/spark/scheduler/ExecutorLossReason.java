package org.apache.spark.scheduler;

import java.io.Serializable;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q2Q!\u0002\u0004\u0001\u00119A\u0001B\t\u0001\u0003\u0006\u0004%\ta\t\u0005\tY\u0001\u0011\t\u0011)A\u0005I!)Q\u0006\u0001C\u0001]!)!\u0007\u0001C!g\t\u0011R\t_3dkR|'\u000fT8tgJ+\u0017m]8o\u0015\t9\u0001\"A\u0005tG\",G-\u001e7fe*\u0011\u0011BC\u0001\u0006gB\f'o\u001b\u0006\u0003\u00171\ta!\u00199bG\",'\"A\u0007\u0002\u0007=\u0014xmE\u0002\u0001\u001fU\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0007C\u0001\f \u001d\t9RD\u0004\u0002\u001995\t\u0011D\u0003\u0002\u001b7\u00051AH]8piz\u001a\u0001!C\u0001\u0013\u0013\tq\u0012#A\u0004qC\u000e\\\u0017mZ3\n\u0005\u0001\n#\u0001D*fe&\fG.\u001b>bE2,'B\u0001\u0010\u0012\u0003\u001diWm]:bO\u0016,\u0012\u0001\n\t\u0003K%r!AJ\u0014\u0011\u0005a\t\u0012B\u0001\u0015\u0012\u0003\u0019\u0001&/\u001a3fM&\u0011!f\u000b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005!\n\u0012\u0001C7fgN\fw-\u001a\u0011\u0002\rqJg.\u001b;?)\ty\u0013\u0007\u0005\u00021\u00015\ta\u0001C\u0003#\u0007\u0001\u0007A%\u0001\u0005u_N#(/\u001b8h)\u0005!\u0003"
)
public class ExecutorLossReason implements Serializable {
   private final String message;

   public String message() {
      return this.message;
   }

   public String toString() {
      return this.message();
   }

   public ExecutorLossReason(final String message) {
      this.message = message;
   }
}
