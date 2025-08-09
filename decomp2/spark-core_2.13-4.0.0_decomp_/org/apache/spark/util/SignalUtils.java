package org.apache.spark.util;

import java.lang.invoke.SerializedLambda;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.apache.spark.internal.Logging;
import org.apache.spark.internal.MessageWithContext;
import org.slf4j.Logger;
import scala.Function0;
import scala.StringContext;
import scala.collection.IterableOnceOps;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import sun.misc.Signal;
import sun.misc.SignalHandler;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005esAB\u000b\u0017\u0011\u0003AbD\u0002\u0004!-!\u0005\u0001$\t\u0005\u0006]\u0005!\t\u0001\r\u0005\bc\u0005\u0001\r\u0011\"\u00033\u0011\u001d1\u0014\u00011A\u0005\n]Ba!P\u0001!B\u0013\u0019\u0004\"\u0002 \u0002\t\u0003y\u0004\"\u0002%\u0002\t\u0003I\u0005\"\u0002%\u0002\t\u0003i\u0006b\u00025\u0002#\u0003%\t!\u001b\u0004\u0005i\u0006!Q\u000fC\u0005R\u0015\t\u0005\t\u0015!\u0003\u0002\u000e!1aF\u0003C\u0001\u0003'A\u0011\"a\u0007\u000b\u0005\u0004%I!!\b\t\u0011\u0005=\"\u0002)A\u0005\u0003?A\u0011\"!\r\u000b\u0005\u0004%I!a\r\t\u000f\u0005U\"\u0002)A\u0005}\"9\u0011q\u0007\u0006\u0005B\u0005e\u0002B\u0002%\u000b\t\u0003\ty\u0004C\u0005\u0002D\u0005\u0011\r\u0011\"\u0003\u0002F!A\u0011qK\u0001!\u0002\u0013\t9%A\u0006TS\u001et\u0017\r\\+uS2\u001c(BA\f\u0019\u0003\u0011)H/\u001b7\u000b\u0005eQ\u0012!B:qCJ\\'BA\u000e\u001d\u0003\u0019\t\u0007/Y2iK*\tQ$A\u0002pe\u001e\u0004\"aH\u0001\u000e\u0003Y\u00111bU5h]\u0006dW\u000b^5mgN\u0019\u0011A\t\u0015\u0011\u0005\r2S\"\u0001\u0013\u000b\u0003\u0015\nQa]2bY\u0006L!a\n\u0013\u0003\r\u0005s\u0017PU3g!\tIC&D\u0001+\u0015\tY\u0003$\u0001\u0005j]R,'O\\1m\u0013\ti#FA\u0004M_\u001e<\u0017N\\4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012AH\u0001\u0011Y><w-\u001a:SK\u001eL7\u000f^3sK\u0012,\u0012a\r\t\u0003GQJ!!\u000e\u0013\u0003\u000f\t{w\u000e\\3b]\u0006!Bn\\4hKJ\u0014VmZ5ti\u0016\u0014X\rZ0%KF$\"\u0001O\u001e\u0011\u0005\rJ\u0014B\u0001\u001e%\u0005\u0011)f.\u001b;\t\u000fq\"\u0011\u0011!a\u0001g\u0005\u0019\u0001\u0010J\u0019\u0002#1|wmZ3s%\u0016<\u0017n\u001d;fe\u0016$\u0007%\u0001\bsK\u001eL7\u000f^3s\u0019><w-\u001a:\u0015\u0005a\u0002\u0005\"B!\u0007\u0001\u0004\u0011\u0015a\u00017pOB\u00111IR\u0007\u0002\t*\u0011Q\tH\u0001\u0006g24GG[\u0005\u0003\u000f\u0012\u0013a\u0001T8hO\u0016\u0014\u0018\u0001\u0003:fO&\u001cH/\u001a:\u0015\u0005)\u0003FC\u0001\u001dL\u0011\u0019au\u0001\"a\u0001\u001b\u00061\u0011m\u0019;j_:\u00042a\t(4\u0013\tyEE\u0001\u0005=Eft\u0017-\\3?\u0011\u0015\tv\u00011\u0001S\u0003\u0019\u0019\u0018n\u001a8bYB\u00111K\u0017\b\u0003)b\u0003\"!\u0016\u0013\u000e\u0003YS!aV\u0018\u0002\rq\u0012xn\u001c;?\u0013\tIF%\u0001\u0004Qe\u0016$WMZ\u0005\u00037r\u0013aa\u0015;sS:<'BA-%)\u0011q\u0006-\u00194\u0015\u0005az\u0006B\u0002'\t\t\u0003\u0007Q\nC\u0003R\u0011\u0001\u0007!\u000bC\u0003c\u0011\u0001\u00071-A\u0006gC&dW*Z:tC\u001e,\u0007CA\u0015e\u0013\t)'F\u0001\nNKN\u001c\u0018mZ3XSRD7i\u001c8uKb$\bbB4\t!\u0003\u0005\raM\u0001\u000eY><7\u000b^1dWR\u0013\u0018mY3\u0002%I,w-[:uKJ$C-\u001a4bk2$HeM\u000b\u0002U*\u00121g[\u0016\u0002YB\u0011QN]\u0007\u0002]*\u0011q\u000e]\u0001\nk:\u001c\u0007.Z2lK\u0012T!!\u001d\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002t]\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0003\u001b\u0005\u001bG/[8o\u0011\u0006tG\r\\3s'\rQaO \t\u0003orl\u0011\u0001\u001f\u0006\u0003sj\fA\u0001\\1oO*\t10\u0001\u0003kCZ\f\u0017BA?y\u0005\u0019y%M[3diB\u0019q0!\u0003\u000e\u0005\u0005\u0005!\u0002BA\u0002\u0003\u000b\tA!\\5tG*\u0011\u0011qA\u0001\u0004gVt\u0017\u0002BA\u0006\u0003\u0003\u0011QbU5h]\u0006d\u0007*\u00198eY\u0016\u0014\bcA@\u0002\u0010%!\u0011\u0011CA\u0001\u0005\u0019\u0019\u0016n\u001a8bYR!\u0011QCA\r!\r\t9BC\u0007\u0002\u0003!1\u0011\u000b\u0004a\u0001\u0003\u001b\tq!Y2uS>t7/\u0006\u0002\u0002 A1\u0011\u0011EA\u0013\u0003Si!!a\t\u000b\u0005]Q\u0018\u0002BA\u0014\u0003G\u0011A\u0001T5tiB!1%a\u000b4\u0013\r\ti\u0003\n\u0002\n\rVt7\r^5p]B\n\u0001\"Y2uS>t7\u000fI\u0001\faJ,g\u000fS1oI2,'/F\u0001\u007f\u00031\u0001(/\u001a<IC:$G.\u001a:!\u0003\u0019A\u0017M\u001c3mKR\u0019\u0001(a\u000f\t\u000f\u0005u\u0012\u00031\u0001\u0002\u000e\u0005\u00191/[4\u0015\u0007a\n\t\u0005\u0003\u0004M%\u0011\u0005\r!T\u0001\tQ\u0006tG\r\\3sgV\u0011\u0011q\t\t\b\u0003\u0013\n\u0019FUA\u000b\u001b\t\tYE\u0003\u0003\u0002N\u0005=\u0013aB7vi\u0006\u0014G.\u001a\u0006\u0004\u0003#\"\u0013AC2pY2,7\r^5p]&!\u0011QKA&\u0005\u001dA\u0015m\u001d5NCB\f\u0011\u0002[1oI2,'o\u001d\u0011"
)
public final class SignalUtils {
   public static boolean register$default$3() {
      return SignalUtils$.MODULE$.register$default$3();
   }

   public static void register(final String signal, final MessageWithContext failMessage, final boolean logStackTrace, final Function0 action) {
      SignalUtils$.MODULE$.register(signal, failMessage, logStackTrace, action);
   }

   public static void register(final String signal, final Function0 action) {
      SignalUtils$.MODULE$.register(signal, action);
   }

   public static void registerLogger(final Logger log) {
      SignalUtils$.MODULE$.registerLogger(log);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return SignalUtils$.MODULE$.LogStringContext(sc);
   }

   private static class ActionHandler implements SignalHandler {
      private final Signal signal;
      private final List actions;
      private final SignalHandler prevHandler;

      private List actions() {
         return this.actions;
      }

      private SignalHandler prevHandler() {
         return this.prevHandler;
      }

      public void handle(final Signal sig) {
         Signal.handle(this.signal, this.prevHandler());
         boolean escalate = ((IterableOnceOps).MODULE$.ListHasAsScala(this.actions()).asScala().map((action) -> BoxesRunTime.boxToBoolean($anonfun$handle$1(action)))).forall((x$1) -> BoxesRunTime.boxToBoolean($anonfun$handle$2(BoxesRunTime.unboxToBoolean(x$1))));
         if (escalate) {
            this.prevHandler().handle(sig);
         }

         Signal.handle(this.signal, this);
      }

      public void register(final Function0 action) {
         this.actions().add(action);
      }

      // $FF: synthetic method
      public static final boolean $anonfun$handle$1(final Function0 action) {
         return action.apply$mcZ$sp();
      }

      // $FF: synthetic method
      public static final boolean $anonfun$handle$2(final boolean x$1) {
         return !x$1;
      }

      public ActionHandler(final Signal signal) {
         this.signal = signal;
         this.actions = Collections.synchronizedList(new LinkedList());
         this.prevHandler = Signal.handle(signal, this);
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return Class.lambdaDeserialize<invokedynamic>(var0);
      }
   }
}
