package org.apache.spark.status.api.v1.streaming;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.scheduler.SparkListener;
import org.apache.spark.status.api.v1.BaseAppResource;
import org.apache.spark.status.api.v1.NotFoundException;
import org.apache.spark.streaming.ui.StreamingJobProgressListener;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.None.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005u2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005aA\u0005\u0005\u0006;\u0001!\ta\b\u0005\u0006G\u0001!\t\u0002\n\u0002\u0019\u0005\u0006\u001cXm\u0015;sK\u0006l\u0017N\\4BaB\u0014Vm]8ve\u000e,'BA\u0003\u0007\u0003%\u0019HO]3b[&twM\u0003\u0002\b\u0011\u0005\u0011a/\r\u0006\u0003\u0013)\t1!\u00199j\u0015\tYA\"\u0001\u0004ti\u0006$Xo\u001d\u0006\u0003\u001b9\tQa\u001d9be.T!a\u0004\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0012aA8sON\u0019\u0001aE\r\u0011\u0005Q9R\"A\u000b\u000b\u0003Y\tQa]2bY\u0006L!\u0001G\u000b\u0003\r\u0005s\u0017PU3g!\tQ2$D\u0001\u0007\u0013\tabAA\bCCN,\u0017\t\u001d9SKN|WO]2f\u0003\u0019!\u0013N\\5uI\r\u0001A#\u0001\u0011\u0011\u0005Q\t\u0013B\u0001\u0012\u0016\u0005\u0011)f.\u001b;\u0002\u0019]LG\u000f\u001b'jgR,g.\u001a:\u0016\u0005\u0015BCC\u0001\u00142!\t9\u0003\u0006\u0004\u0001\u0005\u000b%\u0012!\u0019\u0001\u0016\u0003\u0003Q\u000b\"a\u000b\u0018\u0011\u0005Qa\u0013BA\u0017\u0016\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001F\u0018\n\u0005A*\"aA!os\")!G\u0001a\u0001g\u0005\u0011aM\u001c\t\u0005)Q2d%\u0003\u00026+\tIa)\u001e8di&|g.\r\t\u0003omj\u0011\u0001\u000f\u0006\u0003si\n!!^5\u000b\u0005\u0015a\u0011B\u0001\u001f9\u0005q\u0019FO]3b[&twMS8c!J|wM]3tg2K7\u000f^3oKJ\u0004"
)
public interface BaseStreamingAppResource extends BaseAppResource {
   // $FF: synthetic method
   static Object withListener$(final BaseStreamingAppResource $this, final Function1 fn) {
      return $this.withListener(fn);
   }

   default Object withListener(final Function1 fn) {
      return this.withUI((ui) -> {
         Option var4 = ui.getStreamingJobProgressListener();
         if (var4 instanceof Some var5) {
            SparkListener listener = (SparkListener)var5.value();
            StreamingJobProgressListener listener = (StreamingJobProgressListener)listener;
            synchronized(listener){}

            Object var8;
            try {
               var8 = fn.apply(listener);
            } catch (Throwable var10) {
               throw var10;
            }

            return var8;
         } else if (.MODULE$.equals(var4)) {
            throw new NotFoundException("no streaming listener attached to " + ui.getAppName());
         } else {
            throw new MatchError(var4);
         }
      });
   }

   static void $init$(final BaseStreamingAppResource $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
