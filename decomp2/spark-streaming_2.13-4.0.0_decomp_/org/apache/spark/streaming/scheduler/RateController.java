package org.apache.spark.streaming.scheduler;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.scheduler.rate.RateEstimator;
import scala.Option;
import scala.collection.immutable.Map;
import scala.concurrent.ExecutionContext;
import scala.concurrent.ExecutionContext.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}cA\u0002\f\u0018\u0003\u0003I\u0012\u0005\u0003\u0005:\u0001\t\u0015\r\u0011\"\u0001;\u0011!q\u0004A!A!\u0002\u0013Y\u0004\u0002C \u0001\u0005\u0003\u0005\u000b\u0011\u0002!\t\u000b\u0019\u0003A\u0011A$\t\u000b-\u0003a\u0011\u0003'\t\u0013Q\u0003\u0001\u0019!a\u0001\n\u0017)\u0006\"\u0003/\u0001\u0001\u0004\u0005\r\u0011\"\u0003^\u0011%\u0001\u0007\u00011A\u0001B\u0003&a\u000bC\u0005f\u0001\u0001\u0007\t\u0019!C\u0005M\"I!\u000f\u0001a\u0001\u0002\u0004%Ia\u001d\u0005\nk\u0002\u0001\r\u0011!Q!\n\u001dDQa\u001e\u0001\u0005\naDQ!\u001f\u0001\u0005\niDq!a\u0002\u0001\t\u0013\tI\u0001C\u0004\u0002\u001c\u0001!\t!!\b\t\u000f\u0005}\u0001\u0001\"\u0011\u0002\"\u001d9\u0011QF\f\t\u0002\u0005=bA\u0002\f\u0018\u0011\u0003\t\t\u0004\u0003\u0004G%\u0011\u0005\u0011q\u0007\u0005\b\u0003s\u0011B\u0011AA\u001e\u0011%\tyEEA\u0001\n\u0013\t\tF\u0001\bSCR,7i\u001c8ue>dG.\u001a:\u000b\u0005aI\u0012!C:dQ\u0016$W\u000f\\3s\u0015\tQ2$A\u0005tiJ,\u0017-\\5oO*\u0011A$H\u0001\u0006gB\f'o\u001b\u0006\u0003=}\ta!\u00199bG\",'\"\u0001\u0011\u0002\u0007=\u0014xm\u0005\u0003\u0001E!b\u0003CA\u0012'\u001b\u0005!#\"A\u0013\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u001d\"#AB!osJ+g\r\u0005\u0002*U5\tq#\u0003\u0002,/\t\t2\u000b\u001e:fC6Lgn\u001a'jgR,g.\u001a:\u0011\u000552dB\u0001\u00185\u001d\ty3'D\u00011\u0015\t\t$'\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005)\u0013BA\u001b%\u0003\u001d\u0001\u0018mY6bO\u0016L!a\u000e\u001d\u0003\u0019M+'/[1mSj\f'\r\\3\u000b\u0005U\"\u0013!C:ue\u0016\fW.V%E+\u0005Y\u0004CA\u0012=\u0013\tiDEA\u0002J]R\f!b\u001d;sK\u0006lW+\u0013#!\u00035\u0011\u0018\r^3FgRLW.\u0019;peB\u0011\u0011\tR\u0007\u0002\u0005*\u00111iF\u0001\u0005e\u0006$X-\u0003\u0002F\u0005\ni!+\u0019;f\u000bN$\u0018.\\1u_J\fa\u0001P5oSRtDc\u0001%J\u0015B\u0011\u0011\u0006\u0001\u0005\u0006s\u0011\u0001\ra\u000f\u0005\u0006\u007f\u0011\u0001\r\u0001Q\u0001\baV\u0014G.[:i)\ti\u0005\u000b\u0005\u0002$\u001d&\u0011q\n\n\u0002\u0005+:LG\u000fC\u0003D\u000b\u0001\u0007\u0011\u000b\u0005\u0002$%&\u00111\u000b\n\u0002\u0005\u0019>tw-\u0001\tfq\u0016\u001cW\u000f^5p]\u000e{g\u000e^3yiV\ta\u000b\u0005\u0002X56\t\u0001L\u0003\u0002ZI\u0005Q1m\u001c8dkJ\u0014XM\u001c;\n\u0005mC&\u0001E#yK\u000e,H/[8o\u0007>tG/\u001a=u\u0003Q)\u00070Z2vi&|gnQ8oi\u0016DHo\u0018\u0013fcR\u0011QJ\u0018\u0005\b?\u001e\t\t\u00111\u0001W\u0003\rAH%M\u0001\u0012Kb,7-\u001e;j_:\u001cuN\u001c;fqR\u0004\u0003F\u0001\u0005c!\t\u00193-\u0003\u0002eI\tIAO]1og&,g\u000e^\u0001\ne\u0006$X\rT5nSR,\u0012a\u001a\t\u0003QBl\u0011!\u001b\u0006\u0003U.\fa!\u0019;p[&\u001c'BA-m\u0015\tig.\u0001\u0003vi&d'\"A8\u0002\t)\fg/Y\u0005\u0003c&\u0014!\"\u0011;p[&\u001cGj\u001c8h\u00035\u0011\u0018\r^3MS6LGo\u0018\u0013fcR\u0011Q\n\u001e\u0005\b?*\t\t\u00111\u0001h\u0003)\u0011\u0018\r^3MS6LG\u000f\t\u0015\u0003\u0017\t\fA!\u001b8jiR\tQ*\u0001\u0006sK\u0006$wJ\u00196fGR$\"!T>\t\u000bql\u0001\u0019A?\u0002\u0007=L7\u000fE\u0002\u007f\u0003\u0007i\u0011a \u0006\u0004\u0003\u0003q\u0017AA5p\u0013\r\t)a \u0002\u0012\u001f\nTWm\u0019;J]B,Ho\u0015;sK\u0006l\u0017!E2p[B,H/Z!oIB+(\r\\5tQRIQ*a\u0003\u0002\u0010\u0005M\u0011q\u0003\u0005\u0007\u0003\u001bq\u0001\u0019A)\u0002\tQLW.\u001a\u0005\u0007\u0003#q\u0001\u0019A)\u0002\u000b\u0015dW-\\:\t\r\u0005Ua\u00021\u0001R\u0003%9xN]6EK2\f\u0017\u0010\u0003\u0004\u0002\u001a9\u0001\r!U\u0001\no\u0006LG\u000fR3mCf\fQbZ3u\u0019\u0006$Xm\u001d;SCR,G#A)\u0002!=t')\u0019;dQ\u000e{W\u000e\u001d7fi\u0016$GcA'\u0002$!9\u0011Q\u0005\tA\u0002\u0005\u001d\u0012A\u00042bi\u000eD7i\\7qY\u0016$X\r\u001a\t\u0004S\u0005%\u0012bAA\u0016/\ty2\u000b\u001e:fC6Lgn\u001a'jgR,g.\u001a:CCR\u001c\u0007nQ8na2,G/\u001a3\u0002\u001dI\u000bG/Z\"p]R\u0014x\u000e\u001c7feB\u0011\u0011FE\n\u0005%\t\n\u0019\u0004E\u0002\u007f\u0003kI!aN@\u0015\u0005\u0005=\u0012!F5t\u0005\u0006\u001c7\u000e\u0015:fgN,(/Z#oC\ndW\r\u001a\u000b\u0005\u0003{\t\u0019\u0005E\u0002$\u0003\u007fI1!!\u0011%\u0005\u001d\u0011un\u001c7fC:Dq!!\u0012\u0015\u0001\u0004\t9%\u0001\u0003d_:4\u0007\u0003BA%\u0003\u0017j\u0011aG\u0005\u0004\u0003\u001bZ\"!C*qCJ\\7i\u001c8g\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\u0019\u0006\u0005\u0003\u0002V\u0005mSBAA,\u0015\r\tIF\\\u0001\u0005Y\u0006tw-\u0003\u0003\u0002^\u0005]#AB(cU\u0016\u001cG\u000f"
)
public abstract class RateController implements StreamingListener, Serializable {
   private final int streamUID;
   private final RateEstimator rateEstimator;
   private transient ExecutionContext executionContext;
   private transient AtomicLong rateLimit;

   public static boolean isBackPressureEnabled(final SparkConf conf) {
      return RateController$.MODULE$.isBackPressureEnabled(conf);
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

   public int streamUID() {
      return this.streamUID;
   }

   public abstract void publish(final long rate);

   private ExecutionContext executionContext() {
      return this.executionContext;
   }

   private void executionContext_$eq(final ExecutionContext x$1) {
      this.executionContext = x$1;
   }

   private AtomicLong rateLimit() {
      return this.rateLimit;
   }

   private void rateLimit_$eq(final AtomicLong x$1) {
      this.rateLimit = x$1;
   }

   private void init() {
      this.executionContext_$eq(.MODULE$.fromExecutorService(org.apache.spark.util.ThreadUtils..MODULE$.newDaemonSingleThreadExecutor("stream-rate-update")));
      this.rateLimit_$eq(new AtomicLong(-1L));
   }

   private void readObject(final ObjectInputStream ois) {
      org.apache.spark.util.Utils..MODULE$.tryOrIOException((JFunction0.mcV.sp)() -> {
         ois.defaultReadObject();
         this.init();
      });
   }

   private void computeAndPublish(final long time, final long elems, final long workDelay, final long waitDelay) {
      scala.concurrent.Future..MODULE$.apply((JFunction0.mcV.sp)() -> {
         Option newRate = this.rateEstimator.compute(time, elems, workDelay, waitDelay);
         newRate.foreach((JFunction1.mcVD.sp)(s) -> {
            this.rateLimit().set((long)s);
            this.publish(this.getLatestRate());
         });
      }, this.executionContext());
   }

   public long getLatestRate() {
      return this.rateLimit().get();
   }

   public void onBatchCompleted(final StreamingListenerBatchCompleted batchCompleted) {
      Map elements = batchCompleted.batchInfo().streamIdToInputInfo();
      batchCompleted.batchInfo().processingEndTime().foreach((JFunction1.mcVJ.sp)(processingEnd) -> batchCompleted.batchInfo().processingDelay().foreach((JFunction1.mcVJ.sp)(workDelay) -> batchCompleted.batchInfo().schedulingDelay().foreach((JFunction1.mcVJ.sp)(waitDelay) -> elements.get(BoxesRunTime.boxToInteger(this.streamUID())).map((x$3) -> BoxesRunTime.boxToLong($anonfun$onBatchCompleted$4(x$3))).foreach((JFunction1.mcVJ.sp)(elems) -> this.computeAndPublish(processingEnd, elems, workDelay, waitDelay)))));
   }

   // $FF: synthetic method
   public static final long $anonfun$onBatchCompleted$4(final StreamInputInfo x$3) {
      return x$3.numRecords();
   }

   public RateController(final int streamUID, final RateEstimator rateEstimator) {
      this.streamUID = streamUID;
      this.rateEstimator = rateEstimator;
      StreamingListener.$init$(this);
      this.init();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
