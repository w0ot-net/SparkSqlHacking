package org.apache.spark.rpc.netty;

import java.lang.invoke.SerializedLambda;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;
import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext$;
import org.apache.spark.internal.config.ConfigEntry;
import org.apache.spark.internal.config.Network$;
import org.apache.spark.internal.config.package$;
import org.apache.spark.rpc.RpcEndpoint;
import org.apache.spark.util.ThreadUtils$;
import scala.Option;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005E4A!\u0004\b\u00053!Aa\u0004\u0001B\u0001B\u0003%q\u0004\u0003\u0005$\u0001\t\u0005\t\u0015!\u0003%\u0011!9\u0003A!A!\u0002\u0013A\u0003\"\u0002\u0018\u0001\t\u0003y\u0003b\u0002\u001b\u0001\u0005\u0004%I!\u000e\u0005\u0007\u001d\u0002\u0001\u000b\u0011\u0002\u001c\t\u000b=\u0003A\u0011\u0002)\t\u000fI\u0003!\u0019!C)'\"1q\u000b\u0001Q\u0001\nQCQ\u0001\u0017\u0001\u0005BeCQ\u0001\u001a\u0001\u0005B\u0015DQ\u0001\u001b\u0001\u0005\u0002%\u0014\u0011c\u00155be\u0016$W*Z:tC\u001e,Gj\\8q\u0015\ty\u0001#A\u0003oKR$\u0018P\u0003\u0002\u0012%\u0005\u0019!\u000f]2\u000b\u0005M!\u0012!B:qCJ\\'BA\u000b\u0017\u0003\u0019\t\u0007/Y2iK*\tq#A\u0002pe\u001e\u001c\u0001a\u0005\u0002\u00015A\u00111\u0004H\u0007\u0002\u001d%\u0011QD\u0004\u0002\f\u001b\u0016\u001c8/Y4f\u0019>|\u0007/\u0001\u0003d_:4\u0007C\u0001\u0011\"\u001b\u0005\u0011\u0012B\u0001\u0012\u0013\u0005%\u0019\u0006/\u0019:l\u0007>tg-\u0001\u0006eSN\u0004\u0018\r^2iKJ\u0004\"aG\u0013\n\u0005\u0019r!A\u0003#jgB\fGo\u00195fe\u0006qa.^7Vg\u0006\u0014G.Z\"pe\u0016\u001c\bCA\u0015-\u001b\u0005Q#\"A\u0016\u0002\u000bM\u001c\u0017\r\\1\n\u00055R#aA%oi\u00061A(\u001b8jiz\"B\u0001M\u00193gA\u00111\u0004\u0001\u0005\u0006=\u0011\u0001\ra\b\u0005\u0006G\u0011\u0001\r\u0001\n\u0005\u0006O\u0011\u0001\r\u0001K\u0001\nK:$\u0007o\\5oiN,\u0012A\u000e\t\u0005oy\u00025*D\u00019\u0015\tI$(\u0001\u0006d_:\u001cWO\u001d:f]RT!a\u000f\u001f\u0002\tU$\u0018\u000e\u001c\u0006\u0002{\u0005!!.\u0019<b\u0013\ty\u0004HA\tD_:\u001cWO\u001d:f]RD\u0015m\u001d5NCB\u0004\"!\u0011%\u000f\u0005\t3\u0005CA\"+\u001b\u0005!%BA#\u0019\u0003\u0019a$o\\8u}%\u0011qIK\u0001\u0007!J,G-\u001a4\n\u0005%S%AB*ue&twM\u0003\u0002HUA\u00111\u0004T\u0005\u0003\u001b:\u0011Q!\u00138c_b\f!\"\u001a8ea>Lg\u000e^:!\u0003=9W\r\u001e(v[>3G\u000b\u001b:fC\u0012\u001cHC\u0001\u0015R\u0011\u0015qr\u00011\u0001 \u0003)!\bN]3bIB|w\u000e\\\u000b\u0002)B\u0011q'V\u0005\u0003-b\u0012!\u0003\u00165sK\u0006$\u0007k\\8m\u000bb,7-\u001e;pe\u0006YA\u000f\u001b:fC\u0012\u0004xn\u001c7!\u0003\u0011\u0001xn\u001d;\u0015\u0007ikv\f\u0005\u0002*7&\u0011AL\u000b\u0002\u0005+:LG\u000fC\u0003_\u0015\u0001\u0007\u0001)\u0001\u0007f]\u0012\u0004x.\u001b8u\u001d\u0006lW\rC\u0003a\u0015\u0001\u0007\u0011-A\u0004nKN\u001c\u0018mZ3\u0011\u0005m\u0011\u0017BA2\u000f\u00051IeNY8y\u001b\u0016\u001c8/Y4f\u0003))hN]3hSN$XM\u001d\u000b\u00035\u001aDQaZ\u0006A\u0002\u0001\u000bAA\\1nK\u0006A!/Z4jgR,'\u000fF\u0002[U.DQa\u001a\u0007A\u0002\u0001CQ\u0001\u001c\u0007A\u00025\f\u0001\"\u001a8ea>Lg\u000e\u001e\t\u0003]>l\u0011\u0001E\u0005\u0003aB\u00111B\u00159d\u000b:$\u0007o\\5oi\u0002"
)
public class SharedMessageLoop extends MessageLoop {
   private final int numUsableCores;
   private final ConcurrentHashMap endpoints;
   private final ThreadPoolExecutor threadpool;

   private ConcurrentHashMap endpoints() {
      return this.endpoints;
   }

   private int getNumOfThreads(final SparkConf conf) {
      int availableCores = this.numUsableCores > 0 ? this.numUsableCores : Runtime.getRuntime().availableProcessors();
      int modNumThreads = BoxesRunTime.unboxToInt(((Option)conf.get((ConfigEntry)Network$.MODULE$.RPC_NETTY_DISPATCHER_NUM_THREADS())).getOrElse((JFunction0.mcI.sp)() -> .MODULE$.max(2, availableCores)));
      return BoxesRunTime.unboxToInt(((Option)conf.get((ConfigEntry)package$.MODULE$.EXECUTOR_ID())).map((id) -> BoxesRunTime.boxToInteger($anonfun$getNumOfThreads$2(conf, modNumThreads, id))).getOrElse((JFunction0.mcI.sp)() -> modNumThreads));
   }

   public ThreadPoolExecutor threadpool() {
      return this.threadpool;
   }

   public void post(final String endpointName, final InboxMessage message) {
      Inbox inbox = (Inbox)this.endpoints().get(endpointName);
      inbox.post(message);
      this.setActive(inbox);
   }

   public void unregister(final String name) {
      Inbox inbox = (Inbox)this.endpoints().remove(name);
      if (inbox != null) {
         inbox.stop();
         this.setActive(inbox);
      }
   }

   public void register(final String name, final RpcEndpoint endpoint) {
      Inbox inbox = new Inbox(name, endpoint);
      this.endpoints().put(name, inbox);
      this.setActive(inbox);
   }

   // $FF: synthetic method
   public static final int $anonfun$getNumOfThreads$2(final SparkConf conf$1, final int modNumThreads$1, final String id) {
      String var10000;
      label17: {
         label16: {
            String var4 = SparkContext$.MODULE$.DRIVER_IDENTIFIER();
            if (id == null) {
               if (var4 == null) {
                  break label16;
               }
            } else if (id.equals(var4)) {
               break label16;
            }

            var10000 = "executor";
            break label17;
         }

         var10000 = "driver";
      }

      String role = var10000;
      return conf$1.getInt("spark." + role + ".rpc.netty.dispatcher.numThreads", modNumThreads$1);
   }

   public SharedMessageLoop(final SparkConf conf, final Dispatcher dispatcher, final int numUsableCores) {
      super(dispatcher);
      this.numUsableCores = numUsableCores;
      this.endpoints = new ConcurrentHashMap();
      int numThreads = this.getNumOfThreads(conf);
      ThreadPoolExecutor pool = ThreadUtils$.MODULE$.newDaemonFixedThreadPool(numThreads, "dispatcher-event-loop");
      scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numThreads).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> pool.execute(this.receiveLoopRunnable()));
      this.threadpool = pool;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
