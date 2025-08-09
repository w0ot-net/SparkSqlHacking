package org.apache.spark.status.api.v1;

import jakarta.ws.rs.GET;
import java.lang.invoke.SerializedLambda;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005%2Qa\u0001\u0003\u0001\tAAQ!\u0006\u0001\u0005\u0002]AQ!\u0007\u0001\u0005\u0002i\u0011Qd\u00148f\u0003B\u0004H.[2bi&|g.\u0011;uK6\u0004HOU3t_V\u00148-\u001a\u0006\u0003\u000b\u0019\t!A^\u0019\u000b\u0005\u001dA\u0011aA1qS*\u0011\u0011BC\u0001\u0007gR\fG/^:\u000b\u0005-a\u0011!B:qCJ\\'BA\u0007\u000f\u0003\u0019\t\u0007/Y2iK*\tq\"A\u0002pe\u001e\u001c\"\u0001A\t\u0011\u0005I\u0019R\"\u0001\u0003\n\u0005Q!!aG!cgR\u0014\u0018m\u0019;BaBd\u0017nY1uS>t'+Z:pkJ\u001cW-\u0001\u0004=S:LGOP\u0002\u0001)\u0005A\u0002C\u0001\n\u0001\u0003)9W\r^!ui\u0016l\u0007\u000f\u001e\u000b\u00027A\u0011!\u0003H\u0005\u0003;\u0011\u0011a#\u00119qY&\u001c\u0017\r^5p]\u0006#H/Z7qi&sgm\u001c\u0015\u0003\u0005}\u0001\"\u0001I\u0014\u000e\u0003\u0005R!AI\u0012\u0002\u0005I\u001c(B\u0001\u0013&\u0003\t98OC\u0001'\u0003\u001dQ\u0017m[1si\u0006L!\u0001K\u0011\u0003\u0007\u001d+E\u000b"
)
public class OneApplicationAttemptResource extends AbstractApplicationResource {
   @GET
   public ApplicationAttemptInfo getAttempt() {
      return (ApplicationAttemptInfo)this.uiRoot().getApplicationInfo(this.appId()).flatMap((app) -> app.attempts().find((x$11) -> BoxesRunTime.boxToBoolean($anonfun$getAttempt$2(this, x$11)))).getOrElse(() -> {
         String var10002 = this.appId();
         throw new NotFoundException("unknown app " + var10002 + ", attempt " + this.attemptId());
      });
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getAttempt$2(final OneApplicationAttemptResource $this, final ApplicationAttemptInfo x$11) {
      return x$11.attemptId().contains($this.attemptId());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
