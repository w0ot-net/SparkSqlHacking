package org.apache.spark;

import org.apache.spark.annotation.DeveloperApi;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u00193q\u0001B\u0003\u0011\u0002\u0007\u0005B\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0003\u001d\u0001\u0019\u0005Q\u0004C\u0003*\u0001\u0011\u0005!F\u0001\tUCN\\g)Y5mK\u0012\u0014V-Y:p]*\u0011aaB\u0001\u0006gB\f'o\u001b\u0006\u0003\u0011%\ta!\u00199bG\",'\"\u0001\u0006\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001i1\u0003\u0005\u0002\u000f#5\tqBC\u0001\u0011\u0003\u0015\u00198-\u00197b\u0013\t\u0011rB\u0001\u0004B]f\u0014VM\u001a\t\u0003)Ui\u0011!B\u0005\u0003-\u0015\u0011Q\u0002V1tW\u0016sGMU3bg>t\u0017A\u0002\u0013j]&$H\u0005F\u0001\u001a!\tq!$\u0003\u0002\u001c\u001f\t!QK\\5u\u00035!x.\u0012:s_J\u001cFO]5oOV\ta\u0004\u0005\u0002 M9\u0011\u0001\u0005\n\t\u0003C=i\u0011A\t\u0006\u0003G-\ta\u0001\u0010:p_Rt\u0014BA\u0013\u0010\u0003\u0019\u0001&/\u001a3fM&\u0011q\u0005\u000b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005\u0015z\u0011\u0001G2pk:$Hk\\<be\u0012\u001cH+Y:l\r\u0006LG.\u001e:fgV\t1\u0006\u0005\u0002\u000fY%\u0011Qf\u0004\u0002\b\u0005>|G.Z1oS%\u0001q&M\u001a6oeZT(\u0003\u00021\u000b\t\u0001R\t_2faRLwN\u001c$bS2,(/Z\u0005\u0003e\u0015\u00111#\u0012=fGV$xN\u001d'pgR4\u0015-\u001b7ve\u0016L!\u0001N\u0003\u0003\u0017\u0019+Go\u00195GC&dW\r\u001a\u0006\u0003m\u0015\t1BU3tk\nl\u0017\u000e\u001e;fI&\u0011\u0001(\u0002\u0002\u0011)\u0006\u001c8nQ8n[&$H)\u001a8jK\u0012L!AO\u0003\u0003\u0015Q\u000b7o[&jY2,GM\u0003\u0002=\u000b\u0005qA+Y:l%\u0016\u001cX\u000f\u001c;M_N$(B\u0001 \u0006\u00035)fn\u001b8po:\u0014V-Y:p]\"\u0012\u0001\u0001\u0011\t\u0003\u0003\u0012k\u0011A\u0011\u0006\u0003\u0007\u0016\t!\"\u00198o_R\fG/[8o\u0013\t)%I\u0001\u0007EKZ,Gn\u001c9fe\u0006\u0003\u0018\u000e"
)
public interface TaskFailedReason extends TaskEndReason {
   String toErrorString();

   // $FF: synthetic method
   static boolean countTowardsTaskFailures$(final TaskFailedReason $this) {
      return $this.countTowardsTaskFailures();
   }

   default boolean countTowardsTaskFailures() {
      return true;
   }

   static void $init$(final TaskFailedReason $this) {
   }
}
