package org.apache.spark.rpc.netty;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.ExecutorService;
import org.apache.spark.rpc.IsolatedRpcEndpoint;
import org.apache.spark.util.ThreadUtils$;
import scala.Predef;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005q3Aa\u0003\u0007\u0005/!AA\u0004\u0001B\u0001B\u0003%Q\u0004\u0003\u0005+\u0001\t\u0005\t\u0015!\u0003,\u0011!y\u0003A!A!\u0002\u0013\u0001\u0004\"B\u001a\u0001\t\u0003!\u0004bB\u001d\u0001\u0005\u0004%IA\u000f\u0005\u0007}\u0001\u0001\u000b\u0011B\u001e\t\u000f}\u0002!\u0019!C)\u0001\"11\n\u0001Q\u0001\n\u0005CQ\u0001\u0014\u0001\u0005B5CQ!\u0017\u0001\u0005Bi\u0013A\u0003R3eS\u000e\fG/\u001a3NKN\u001c\u0018mZ3M_>\u0004(BA\u0007\u000f\u0003\u0015qW\r\u001e;z\u0015\ty\u0001#A\u0002sa\u000eT!!\u0005\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005M!\u0012AB1qC\u000eDWMC\u0001\u0016\u0003\ry'oZ\u0002\u0001'\t\u0001\u0001\u0004\u0005\u0002\u001a55\tA\"\u0003\u0002\u001c\u0019\tYQ*Z:tC\u001e,Gj\\8q\u0003\u0011q\u0017-\\3\u0011\u0005y9cBA\u0010&!\t\u00013%D\u0001\"\u0015\t\u0011c#\u0001\u0004=e>|GO\u0010\u0006\u0002I\u0005)1oY1mC&\u0011aeI\u0001\u0007!J,G-\u001a4\n\u0005!J#AB*ue&twM\u0003\u0002'G\u0005AQM\u001c3q_&tG\u000f\u0005\u0002-[5\ta\"\u0003\u0002/\u001d\t\u0019\u0012j]8mCR,GM\u00159d\u000b:$\u0007o\\5oi\u0006QA-[:qCR\u001c\u0007.\u001a:\u0011\u0005e\t\u0014B\u0001\u001a\r\u0005)!\u0015n\u001d9bi\u000eDWM]\u0001\u0007y%t\u0017\u000e\u001e \u0015\tU2t\u0007\u000f\t\u00033\u0001AQ\u0001\b\u0003A\u0002uAQA\u000b\u0003A\u0002-BQa\f\u0003A\u0002A\nQ!\u001b8c_b,\u0012a\u000f\t\u00033qJ!!\u0010\u0007\u0003\u000b%s'm\u001c=\u0002\r%t'm\u001c=!\u0003)!\bN]3bIB|w\u000e\\\u000b\u0002\u0003B\u0011!)S\u0007\u0002\u0007*\u0011A)R\u0001\u000bG>t7-\u001e:sK:$(B\u0001$H\u0003\u0011)H/\u001b7\u000b\u0003!\u000bAA[1wC&\u0011!j\u0011\u0002\u0010\u000bb,7-\u001e;peN+'O^5dK\u0006YA\u000f\u001b:fC\u0012\u0004xn\u001c7!\u0003\u0011\u0001xn\u001d;\u0015\u00079\u0013F\u000b\u0005\u0002P!6\t1%\u0003\u0002RG\t!QK\\5u\u0011\u0015\u0019\u0016\u00021\u0001\u001e\u00031)g\u000e\u001a9pS:$h*Y7f\u0011\u0015)\u0016\u00021\u0001W\u0003\u001diWm]:bO\u0016\u0004\"!G,\n\u0005ac!\u0001D%oE>DX*Z:tC\u001e,\u0017AC;oe\u0016<\u0017n\u001d;feR\u0011aj\u0017\u0005\u0006'*\u0001\r!\b"
)
public class DedicatedMessageLoop extends MessageLoop {
   private final String name;
   private final Inbox inbox;
   private final ExecutorService threadpool;

   private Inbox inbox() {
      return this.inbox;
   }

   public ExecutorService threadpool() {
      return this.threadpool;
   }

   public void post(final String endpointName, final InboxMessage message) {
      Predef var10000;
      boolean var10001;
      label17: {
         label16: {
            var10000 = .MODULE$;
            String var3 = this.name;
            if (endpointName == null) {
               if (var3 == null) {
                  break label16;
               }
            } else if (endpointName.equals(var3)) {
               break label16;
            }

            var10001 = false;
            break label17;
         }

         var10001 = true;
      }

      var10000.require(var10001);
      this.inbox().post(message);
      this.setActive(this.inbox());
   }

   public synchronized void unregister(final String endpointName) {
      Predef var10000;
      boolean var10001;
      label17: {
         label16: {
            var10000 = .MODULE$;
            String var2 = this.name;
            if (endpointName == null) {
               if (var2 == null) {
                  break label16;
               }
            } else if (endpointName.equals(var2)) {
               break label16;
            }

            var10001 = false;
            break label17;
         }

         var10001 = true;
      }

      var10000.require(var10001);
      this.inbox().stop();
      this.setActive(this.inbox());
      this.setActive(MessageLoop$.MODULE$.PoisonPill());
      this.threadpool().shutdown();
   }

   public DedicatedMessageLoop(final String name, final IsolatedRpcEndpoint endpoint, final Dispatcher dispatcher) {
      super(dispatcher);
      this.name = name;
      this.inbox = new Inbox(name, endpoint);
      this.threadpool = endpoint.threadCount() > 1 ? ThreadUtils$.MODULE$.newDaemonCachedThreadPool("dispatcher-" + name, endpoint.threadCount(), ThreadUtils$.MODULE$.newDaemonCachedThreadPool$default$3()) : ThreadUtils$.MODULE$.newDaemonSingleThreadExecutor("dispatcher-" + name);
      scala.runtime.RichInt..MODULE$.to$extension(.MODULE$.intWrapper(1), endpoint.threadCount()).foreach$mVc$sp((JFunction1.mcVI.sp)(x$1) -> this.threadpool().execute(this.receiveLoopRunnable()));
      this.setActive(this.inbox());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
