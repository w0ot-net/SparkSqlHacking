package org.apache.spark.streaming.scheduler;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.scheduler.StatsReportListener.;
import scala.Function1;
import scala.Option;
import scala.collection.Iterable;
import scala.collection.StrictOptimizedIterableOps;
import scala.collection.mutable.Queue;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction1;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005a\u0001B\u0007\u000f\u0001eA\u0001\u0002\n\u0001\u0003\u0002\u0003\u0006I!\n\u0005\u0006Q\u0001!\t!\u000b\u0005\bY\u0001\u0011\r\u0011\"\u0001.\u0011\u0019I\u0004\u0001)A\u0005]!)!\b\u0001C!w!)A\t\u0001C\u0001\u000b\")a\t\u0001C\u0001\u000f\")\u0001\r\u0001C\u0001C\u001e9\u0011ODA\u0001\u0012\u0003\u0011haB\u0007\u000f\u0003\u0003E\ta\u001d\u0005\u0006Q)!\t\u0001\u001e\u0005\bk*\t\n\u0011\"\u0001w\u0005M\u0019F/\u0019;t%\u0016\u0004xN\u001d;MSN$XM\\3s\u0015\ty\u0001#A\u0005tG\",G-\u001e7fe*\u0011\u0011CE\u0001\ngR\u0014X-Y7j]\u001eT!a\u0005\u000b\u0002\u000bM\u0004\u0018M]6\u000b\u0005U1\u0012AB1qC\u000eDWMC\u0001\u0018\u0003\ry'oZ\u0002\u0001'\r\u0001!\u0004\t\t\u00037yi\u0011\u0001\b\u0006\u0002;\u0005)1oY1mC&\u0011q\u0004\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0005\u0005\u0012S\"\u0001\b\n\u0005\rr!!E*ue\u0016\fW.\u001b8h\u0019&\u001cH/\u001a8fe\u0006ia.^7CCR\u001c\u0007.\u00138g_N\u0004\"a\u0007\u0014\n\u0005\u001db\"aA%oi\u00061A(\u001b8jiz\"\"AK\u0016\u0011\u0005\u0005\u0002\u0001b\u0002\u0013\u0003!\u0003\u0005\r!J\u0001\u000bE\u0006$8\r[%oM>\u001cX#\u0001\u0018\u0011\u0007=\"d'D\u00011\u0015\t\t$'A\u0004nkR\f'\r\\3\u000b\u0005Mb\u0012AC2pY2,7\r^5p]&\u0011Q\u0007\r\u0002\u0006#V,W/\u001a\t\u0003C]J!\u0001\u000f\b\u0003\u0013\t\u000bGo\u00195J]\u001a|\u0017a\u00032bi\u000eD\u0017J\u001c4pg\u0002\n\u0001c\u001c8CCR\u001c\u0007nQ8na2,G/\u001a3\u0015\u0005qz\u0004CA\u000e>\u0013\tqDD\u0001\u0003V]&$\b\"\u0002!\u0006\u0001\u0004\t\u0015\u0001\u00042bi\u000eD7\u000b^1si\u0016$\u0007CA\u0011C\u0013\t\u0019eBA\u0010TiJ,\u0017-\\5oO2K7\u000f^3oKJ\u0014\u0015\r^2i\u0007>l\u0007\u000f\\3uK\u0012\f!\u0002\u001d:j]R\u001cF/\u0019;t)\u0005a\u0014AF:i_^l\u0015\u000e\u001c7jg\u0012K7\u000f\u001e:jEV$\u0018n\u001c8\u0015\u0007qBU\u000bC\u0003J\u000f\u0001\u0007!*A\u0004iK\u0006$\u0017N\\4\u0011\u0005-\u0013fB\u0001'Q!\tiE$D\u0001O\u0015\ty\u0005$\u0001\u0004=e>|GOP\u0005\u0003#r\ta\u0001\u0015:fI\u00164\u0017BA*U\u0005\u0019\u0019FO]5oO*\u0011\u0011\u000b\b\u0005\u0006-\u001e\u0001\raV\u0001\nO\u0016$X*\u001a;sS\u000e\u0004Ba\u0007-75&\u0011\u0011\f\b\u0002\n\rVt7\r^5p]F\u00022aG.^\u0013\taFD\u0001\u0004PaRLwN\u001c\t\u00037yK!a\u0018\u000f\u0003\t1{gnZ\u0001\u0014Kb$(/Y2u\t&\u001cHO]5ckRLwN\u001c\u000b\u0003E&\u00042aG.d!\t!w-D\u0001f\u0015\t1'#\u0001\u0003vi&d\u0017B\u00015f\u00051!\u0015n\u001d;sS\n,H/[8o\u0011\u00151\u0006\u00021\u0001XQ\t\u00011\u000e\u0005\u0002m_6\tQN\u0003\u0002o%\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005Al'\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0017aE*uCR\u001c(+\u001a9peRd\u0015n\u001d;f]\u0016\u0014\bCA\u0011\u000b'\tQ!\u0004F\u0001s\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%cU\tqO\u000b\u0002&q.\n\u0011\u0010\u0005\u0002{}6\t1P\u0003\u0002}{\u0006IQO\\2iK\u000e\\W\r\u001a\u0006\u0003]rI!a`>\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW\r"
)
public class StatsReportListener implements StreamingListener {
   private final int numBatchInfos;
   private final Queue batchInfos;

   public static int $lessinit$greater$default$1() {
      return StatsReportListener$.MODULE$.$lessinit$greater$default$1();
   }

   public void onStreamingStarted(final StreamingListenerStreamingStarted streamingStarted) {
      StreamingListener.onStreamingStarted$(this, streamingStarted);
   }

   public void onReceiverStarted(final StreamingListenerReceiverStarted receiverStarted) {
      StreamingListener.onReceiverStarted$(this, receiverStarted);
   }

   public void onReceiverError(final StreamingListenerReceiverError receiverError) {
      StreamingListener.onReceiverError$(this, receiverError);
   }

   public void onReceiverStopped(final StreamingListenerReceiverStopped receiverStopped) {
      StreamingListener.onReceiverStopped$(this, receiverStopped);
   }

   public void onBatchSubmitted(final StreamingListenerBatchSubmitted batchSubmitted) {
      StreamingListener.onBatchSubmitted$(this, batchSubmitted);
   }

   public void onBatchStarted(final StreamingListenerBatchStarted batchStarted) {
      StreamingListener.onBatchStarted$(this, batchStarted);
   }

   public void onOutputOperationStarted(final StreamingListenerOutputOperationStarted outputOperationStarted) {
      StreamingListener.onOutputOperationStarted$(this, outputOperationStarted);
   }

   public void onOutputOperationCompleted(final StreamingListenerOutputOperationCompleted outputOperationCompleted) {
      StreamingListener.onOutputOperationCompleted$(this, outputOperationCompleted);
   }

   public Queue batchInfos() {
      return this.batchInfos;
   }

   public void onBatchCompleted(final StreamingListenerBatchCompleted batchStarted) {
      this.batchInfos().enqueue(batchStarted.batchInfo());
      if (this.batchInfos().size() > this.numBatchInfos) {
         this.batchInfos().dequeue();
      } else {
         BoxedUnit var10000 = BoxedUnit.UNIT;
      }

      this.printStats();
   }

   public void printStats() {
      this.showMillisDistribution("Total delay: ", (x$1) -> x$1.totalDelay());
      this.showMillisDistribution("Processing time: ", (x$2) -> x$2.processingDelay());
   }

   public void showMillisDistribution(final String heading, final Function1 getMetric) {
      .MODULE$.showMillisDistribution(heading, this.extractDistribution(getMetric));
   }

   public Option extractDistribution(final Function1 getMetric) {
      return org.apache.spark.util.Distribution..MODULE$.apply((Iterable)((StrictOptimizedIterableOps)this.batchInfos().flatMap((x$3) -> (Option)getMetric.apply(x$3))).map((JFunction1.mcDJ.sp)(x$4) -> (double)x$4));
   }

   public StatsReportListener(final int numBatchInfos) {
      this.numBatchInfos = numBatchInfos;
      StreamingListener.$init$(this);
      this.batchInfos = new Queue(scala.collection.mutable.Queue..MODULE$.$lessinit$greater$default$1());
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
