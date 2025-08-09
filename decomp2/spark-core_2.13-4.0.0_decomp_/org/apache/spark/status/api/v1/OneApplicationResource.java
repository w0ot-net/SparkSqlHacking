package org.apache.spark.status.api.v1;

import jakarta.ws.rs.GET;
import java.lang.invoke.SerializedLambda;
import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%2Qa\u0001\u0003\u0001\tAAQ!\u0006\u0001\u0005\u0002]AQ!\u0007\u0001\u0005\u0002i\u0011ac\u00148f\u0003B\u0004H.[2bi&|gNU3t_V\u00148-\u001a\u0006\u0003\u000b\u0019\t!A^\u0019\u000b\u0005\u001dA\u0011aA1qS*\u0011\u0011BC\u0001\u0007gR\fG/^:\u000b\u0005-a\u0011!B:qCJ\\'BA\u0007\u000f\u0003\u0019\t\u0007/Y2iK*\tq\"A\u0002pe\u001e\u001c\"\u0001A\t\u0011\u0005I\u0019R\"\u0001\u0003\n\u0005Q!!aG!cgR\u0014\u0018m\u0019;BaBd\u0017nY1uS>t'+Z:pkJ\u001cW-\u0001\u0004=S:LGOP\u0002\u0001)\u0005A\u0002C\u0001\n\u0001\u0003\u00199W\r^!qaR\t1\u0004\u0005\u0002\u00139%\u0011Q\u0004\u0002\u0002\u0010\u0003B\u0004H.[2bi&|g.\u00138g_\"\u0012!a\b\t\u0003A\u001dj\u0011!\t\u0006\u0003E\r\n!A]:\u000b\u0005\u0011*\u0013AA<t\u0015\u00051\u0013a\u00026bW\u0006\u0014H/Y\u0005\u0003Q\u0005\u00121aR#U\u0001"
)
public class OneApplicationResource extends AbstractApplicationResource {
   @GET
   public ApplicationInfo getApp() {
      Option app = this.uiRoot().getApplicationInfo(this.appId());
      return (ApplicationInfo)app.getOrElse(() -> {
         throw new NotFoundException("unknown app: " + this.appId());
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
