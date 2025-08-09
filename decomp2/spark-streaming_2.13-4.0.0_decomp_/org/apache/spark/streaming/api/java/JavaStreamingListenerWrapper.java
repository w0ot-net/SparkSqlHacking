package org.apache.spark.streaming.api.java;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.streaming.scheduler.BatchInfo;
import org.apache.spark.streaming.scheduler.OutputOperationInfo;
import org.apache.spark.streaming.scheduler.ReceiverInfo;
import org.apache.spark.streaming.scheduler.StreamInputInfo;
import org.apache.spark.streaming.scheduler.StreamingListener;
import org.apache.spark.streaming.scheduler.StreamingListenerBatchCompleted;
import org.apache.spark.streaming.scheduler.StreamingListenerBatchStarted;
import org.apache.spark.streaming.scheduler.StreamingListenerBatchSubmitted;
import org.apache.spark.streaming.scheduler.StreamingListenerOutputOperationCompleted;
import org.apache.spark.streaming.scheduler.StreamingListenerOutputOperationStarted;
import org.apache.spark.streaming.scheduler.StreamingListenerReceiverError;
import org.apache.spark.streaming.scheduler.StreamingListenerReceiverStarted;
import org.apache.spark.streaming.scheduler.StreamingListenerReceiverStopped;
import org.apache.spark.streaming.scheduler.StreamingListenerStreamingStarted;
import scala.collection.Map;
import scala.jdk.CollectionConverters.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ub!\u0002\t\u0012\u0001Ui\u0002\u0002\u0003\u0016\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0017\t\u000bA\u0002A\u0011A\u0019\t\u000bQ\u0002A\u0011B\u001b\t\u000by\u0002A\u0011B \t\u000b!\u0003A\u0011B%\t\u000bI\u0003A\u0011B*\t\u000bq\u0003A\u0011I/\t\u000b\u0019\u0004A\u0011I4\t\u000b5\u0004A\u0011\t8\t\u000bQ\u0004A\u0011I;\t\u000bm\u0004A\u0011\t?\t\u000f\u0005\u0015\u0001\u0001\"\u0011\u0002\b!9\u00111\u0003\u0001\u0005B\u0005U\u0001bBA\u0011\u0001\u0011\u0005\u00131\u0005\u0005\b\u0003_\u0001A\u0011IA\u0019\u0005qQ\u0015M^1TiJ,\u0017-\\5oO2K7\u000f^3oKJ<&/\u00199qKJT!AE\n\u0002\t)\fg/\u0019\u0006\u0003)U\t1!\u00199j\u0015\t1r#A\u0005tiJ,\u0017-\\5oO*\u0011\u0001$G\u0001\u0006gB\f'o\u001b\u0006\u00035m\ta!\u00199bG\",'\"\u0001\u000f\u0002\u0007=\u0014xmE\u0002\u0001=\u0011\u0002\"a\b\u0012\u000e\u0003\u0001R\u0011!I\u0001\u0006g\u000e\fG.Y\u0005\u0003G\u0001\u0012a!\u00118z%\u00164\u0007CA\u0013)\u001b\u00051#BA\u0014\u0016\u0003%\u00198\r[3ek2,'/\u0003\u0002*M\t\t2\u000b\u001e:fC6Lgn\u001a'jgR,g.\u001a:\u0002+)\fg/Y*ue\u0016\fW.\u001b8h\u0019&\u001cH/\u001a8fe\u000e\u0001\u0001CA\u0017/\u001b\u0005\t\u0012BA\u0018\u0012\u0005UQ\u0015M^1TiJ,\u0017-\\5oO2K7\u000f^3oKJ\fa\u0001P5oSRtDC\u0001\u001a4!\ti\u0003\u0001C\u0003+\u0005\u0001\u0007A&\u0001\nu_*\u000bg/\u0019*fG\u0016Lg/\u001a:J]\u001a|GC\u0001\u001c:!\tis'\u0003\u00029#\t\u0001\"*\u0019<b%\u0016\u001cW-\u001b<fe&sgm\u001c\u0005\u0006u\r\u0001\raO\u0001\re\u0016\u001cW-\u001b<fe&sgm\u001c\t\u0003KqJ!!\u0010\u0014\u0003\u0019I+7-Z5wKJLeNZ8\u0002+Q|'*\u0019<b'R\u0014X-Y7J]B,H/\u00138g_R\u0011\u0001i\u0011\t\u0003[\u0005K!AQ\t\u0003')\u000bg/Y*ue\u0016\fW.\u00138qkRLeNZ8\t\u000b\u0011#\u0001\u0019A#\u0002\u001fM$(/Z1n\u0013:\u0004X\u000f^%oM>\u0004\"!\n$\n\u0005\u001d3#aD*ue\u0016\fW.\u00138qkRLeNZ8\u00023Q|'*\u0019<b\u001fV$\b/\u001e;Pa\u0016\u0014\u0018\r^5p]&sgm\u001c\u000b\u0003\u00156\u0003\"!L&\n\u00051\u000b\"a\u0006&bm\u0006|U\u000f\u001e9vi>\u0003XM]1uS>t\u0017J\u001c4p\u0011\u0015qU\u00011\u0001P\u0003MyW\u000f\u001e9vi>\u0003XM]1uS>t\u0017J\u001c4p!\t)\u0003+\u0003\u0002RM\t\u0019r*\u001e;qkR|\u0005/\u001a:bi&|g.\u00138g_\u0006yAo\u001c&bm\u0006\u0014\u0015\r^2i\u0013:4w\u000e\u0006\u0002U/B\u0011Q&V\u0005\u0003-F\u0011QBS1wC\n\u000bGo\u00195J]\u001a|\u0007\"\u0002-\u0007\u0001\u0004I\u0016!\u00032bi\u000eD\u0017J\u001c4p!\t)#,\u0003\u0002\\M\tI!)\u0019;dQ&sgm\\\u0001\u0013_:\u001cFO]3b[&twm\u0015;beR,G\r\u0006\u0002_CB\u0011qdX\u0005\u0003A\u0002\u0012A!\u00168ji\")!m\u0002a\u0001G\u0006\u00012\u000f\u001e:fC6LgnZ*uCJ$X\r\u001a\t\u0003K\u0011L!!\u001a\u0014\u0003CM#(/Z1nS:<G*[:uK:,'o\u0015;sK\u0006l\u0017N\\4Ti\u0006\u0014H/\u001a3\u0002#=t'+Z2fSZ,'o\u0015;beR,G\r\u0006\u0002_Q\")\u0011\u000e\u0003a\u0001U\u0006y!/Z2fSZ,'o\u0015;beR,G\r\u0005\u0002&W&\u0011AN\n\u0002!'R\u0014X-Y7j]\u001ed\u0015n\u001d;f]\u0016\u0014(+Z2fSZ,'o\u0015;beR,G-A\bp]J+7-Z5wKJ,%O]8s)\tqv\u000eC\u0003q\u0013\u0001\u0007\u0011/A\u0007sK\u000e,\u0017N^3s\u000bJ\u0014xN\u001d\t\u0003KIL!a\u001d\u0014\u0003=M#(/Z1nS:<G*[:uK:,'OU3dK&4XM]#se>\u0014\u0018!E8o%\u0016\u001cW-\u001b<feN#x\u000e\u001d9fIR\u0011aL\u001e\u0005\u0006o*\u0001\r\u0001_\u0001\u0010e\u0016\u001cW-\u001b<feN#x\u000e\u001d9fIB\u0011Q%_\u0005\u0003u\u001a\u0012\u0001e\u0015;sK\u0006l\u0017N\\4MSN$XM\\3s%\u0016\u001cW-\u001b<feN#x\u000e\u001d9fI\u0006\u0001rN\u001c\"bi\u000eD7+\u001e2nSR$X\r\u001a\u000b\u0003=vDQA`\u0006A\u0002}\faBY1uG\"\u001cVOY7jiR,G\rE\u0002&\u0003\u0003I1!a\u0001'\u0005}\u0019FO]3b[&tw\rT5ti\u0016tWM\u001d\"bi\u000eD7+\u001e2nSR$X\rZ\u0001\u000f_:\u0014\u0015\r^2i'R\f'\u000f^3e)\rq\u0016\u0011\u0002\u0005\b\u0003\u0017a\u0001\u0019AA\u0007\u00031\u0011\u0017\r^2i'R\f'\u000f^3e!\r)\u0013qB\u0005\u0004\u0003#1#!H*ue\u0016\fW.\u001b8h\u0019&\u001cH/\u001a8fe\n\u000bGo\u00195Ti\u0006\u0014H/\u001a3\u0002!=t')\u0019;dQ\u000e{W\u000e\u001d7fi\u0016$Gc\u00010\u0002\u0018!9\u0011\u0011D\u0007A\u0002\u0005m\u0011A\u00042bi\u000eD7i\\7qY\u0016$X\r\u001a\t\u0004K\u0005u\u0011bAA\u0010M\ty2\u000b\u001e:fC6Lgn\u001a'jgR,g.\u001a:CCR\u001c\u0007nQ8na2,G/\u001a3\u00021=tw*\u001e;qkR|\u0005/\u001a:bi&|gn\u0015;beR,G\rF\u0002_\u0003KAq!a\n\u000f\u0001\u0004\tI#\u0001\fpkR\u0004X\u000f^(qKJ\fG/[8o'R\f'\u000f^3e!\r)\u00131F\u0005\u0004\u0003[1#aJ*ue\u0016\fW.\u001b8h\u0019&\u001cH/\u001a8fe>+H\u000f];u\u001fB,'/\u0019;j_:\u001cF/\u0019:uK\u0012\f!d\u001c8PkR\u0004X\u000f^(qKJ\fG/[8o\u0007>l\u0007\u000f\\3uK\u0012$2AXA\u001a\u0011\u001d\t)d\u0004a\u0001\u0003o\t\u0001d\\;uaV$x\n]3sCRLwN\\\"p[BdW\r^3e!\r)\u0013\u0011H\u0005\u0004\u0003w1#!K*ue\u0016\fW.\u001b8h\u0019&\u001cH/\u001a8fe>+H\u000f];u\u001fB,'/\u0019;j_:\u001cu.\u001c9mKR,G\r"
)
public class JavaStreamingListenerWrapper implements StreamingListener {
   private final JavaStreamingListener javaStreamingListener;

   private JavaReceiverInfo toJavaReceiverInfo(final ReceiverInfo receiverInfo) {
      return new JavaReceiverInfo(receiverInfo.streamId(), receiverInfo.name(), receiverInfo.active(), receiverInfo.location(), receiverInfo.executorId(), receiverInfo.lastErrorMessage(), receiverInfo.lastError(), receiverInfo.lastErrorTime());
   }

   private JavaStreamInputInfo toJavaStreamInputInfo(final StreamInputInfo streamInputInfo) {
      return new JavaStreamInputInfo(streamInputInfo.inputStreamId(), streamInputInfo.numRecords(), .MODULE$.MapHasAsJava(streamInputInfo.metadata()).asJava(), (String)streamInputInfo.metadataDescription().orNull(scala..less.colon.less..MODULE$.refl()));
   }

   private JavaOutputOperationInfo toJavaOutputOperationInfo(final OutputOperationInfo outputOperationInfo) {
      return new JavaOutputOperationInfo(outputOperationInfo.batchTime(), outputOperationInfo.id(), outputOperationInfo.name(), outputOperationInfo.description(), BoxesRunTime.unboxToLong(outputOperationInfo.startTime().getOrElse((JFunction0.mcJ.sp)() -> -1L)), BoxesRunTime.unboxToLong(outputOperationInfo.endTime().getOrElse((JFunction0.mcJ.sp)() -> -1L)), (String)outputOperationInfo.failureReason().orNull(scala..less.colon.less..MODULE$.refl()));
   }

   private JavaBatchInfo toJavaBatchInfo(final BatchInfo batchInfo) {
      return new JavaBatchInfo(batchInfo.batchTime(), .MODULE$.MapHasAsJava((Map)batchInfo.streamIdToInputInfo().transform((x$1, v) -> $anonfun$toJavaBatchInfo$1(this, BoxesRunTime.unboxToInt(x$1), v))).asJava(), batchInfo.submissionTime(), BoxesRunTime.unboxToLong(batchInfo.processingStartTime().getOrElse((JFunction0.mcJ.sp)() -> -1L)), BoxesRunTime.unboxToLong(batchInfo.processingEndTime().getOrElse((JFunction0.mcJ.sp)() -> -1L)), BoxesRunTime.unboxToLong(batchInfo.schedulingDelay().getOrElse((JFunction0.mcJ.sp)() -> -1L)), BoxesRunTime.unboxToLong(batchInfo.processingDelay().getOrElse((JFunction0.mcJ.sp)() -> -1L)), BoxesRunTime.unboxToLong(batchInfo.totalDelay().getOrElse((JFunction0.mcJ.sp)() -> -1L)), batchInfo.numRecords(), .MODULE$.MapHasAsJava((Map)batchInfo.outputOperationInfos().transform((x$2, v) -> $anonfun$toJavaBatchInfo$7(this, BoxesRunTime.unboxToInt(x$2), v))).asJava());
   }

   public void onStreamingStarted(final StreamingListenerStreamingStarted streamingStarted) {
      this.javaStreamingListener.onStreamingStarted(new JavaStreamingListenerStreamingStarted(streamingStarted.time()));
   }

   public void onReceiverStarted(final StreamingListenerReceiverStarted receiverStarted) {
      this.javaStreamingListener.onReceiverStarted(new JavaStreamingListenerReceiverStarted(this.toJavaReceiverInfo(receiverStarted.receiverInfo())));
   }

   public void onReceiverError(final StreamingListenerReceiverError receiverError) {
      this.javaStreamingListener.onReceiverError(new JavaStreamingListenerReceiverError(this.toJavaReceiverInfo(receiverError.receiverInfo())));
   }

   public void onReceiverStopped(final StreamingListenerReceiverStopped receiverStopped) {
      this.javaStreamingListener.onReceiverStopped(new JavaStreamingListenerReceiverStopped(this.toJavaReceiverInfo(receiverStopped.receiverInfo())));
   }

   public void onBatchSubmitted(final StreamingListenerBatchSubmitted batchSubmitted) {
      this.javaStreamingListener.onBatchSubmitted(new JavaStreamingListenerBatchSubmitted(this.toJavaBatchInfo(batchSubmitted.batchInfo())));
   }

   public void onBatchStarted(final StreamingListenerBatchStarted batchStarted) {
      this.javaStreamingListener.onBatchStarted(new JavaStreamingListenerBatchStarted(this.toJavaBatchInfo(batchStarted.batchInfo())));
   }

   public void onBatchCompleted(final StreamingListenerBatchCompleted batchCompleted) {
      this.javaStreamingListener.onBatchCompleted(new JavaStreamingListenerBatchCompleted(this.toJavaBatchInfo(batchCompleted.batchInfo())));
   }

   public void onOutputOperationStarted(final StreamingListenerOutputOperationStarted outputOperationStarted) {
      this.javaStreamingListener.onOutputOperationStarted(new JavaStreamingListenerOutputOperationStarted(this.toJavaOutputOperationInfo(outputOperationStarted.outputOperationInfo())));
   }

   public void onOutputOperationCompleted(final StreamingListenerOutputOperationCompleted outputOperationCompleted) {
      this.javaStreamingListener.onOutputOperationCompleted(new JavaStreamingListenerOutputOperationCompleted(this.toJavaOutputOperationInfo(outputOperationCompleted.outputOperationInfo())));
   }

   // $FF: synthetic method
   public static final JavaStreamInputInfo $anonfun$toJavaBatchInfo$1(final JavaStreamingListenerWrapper $this, final int x$1, final StreamInputInfo v) {
      return $this.toJavaStreamInputInfo(v);
   }

   // $FF: synthetic method
   public static final JavaOutputOperationInfo $anonfun$toJavaBatchInfo$7(final JavaStreamingListenerWrapper $this, final int x$2, final OutputOperationInfo v) {
      return $this.toJavaOutputOperationInfo(v);
   }

   public JavaStreamingListenerWrapper(final JavaStreamingListener javaStreamingListener) {
      this.javaStreamingListener = javaStreamingListener;
      StreamingListener.$init$(this);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
