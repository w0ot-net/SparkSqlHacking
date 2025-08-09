package org.apache.spark.streaming.dstream;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.rdd.BlockRDD;
import org.apache.spark.rdd.RDD;
import org.apache.spark.storage.BlockId;
import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.Time;
import org.apache.spark.streaming.rdd.WriteAheadLogBackedBlockRDD;
import org.apache.spark.streaming.rdd.WriteAheadLogBackedBlockRDD$;
import org.apache.spark.streaming.receiver.Receiver;
import org.apache.spark.streaming.scheduler.RateController;
import org.apache.spark.streaming.scheduler.RateController$;
import org.apache.spark.streaming.scheduler.ReceivedBlockInfo;
import org.apache.spark.streaming.scheduler.ReceiverTracker;
import org.apache.spark.streaming.scheduler.StreamInputInfo;
import org.apache.spark.streaming.scheduler.StreamInputInfo$;
import org.apache.spark.streaming.scheduler.rate.RateEstimator;
import org.apache.spark.streaming.scheduler.rate.RateEstimator$;
import org.apache.spark.streaming.util.WriteAheadLogRecordHandle;
import org.apache.spark.streaming.util.WriteAheadLogUtils$;
import scala.Option;
import scala.Some;
import scala.Array.;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mb!\u0002\t\u0012\u0003\u0003a\u0002\u0002\u0003\u0019\u0001\u0005\u0003\u0005\u000b\u0011B\u0019\t\u0011U\u0002!1!Q\u0001\fYBQ\u0001\u0010\u0001\u0005\u0002uB\u0001B\u0011\u0001C\u0002\u0013E3c\u0011\u0005\u0007\u001b\u0002\u0001\u000b\u0011\u0002#\t\u000b9\u0003a\u0011A(\t\u000bY\u0003A\u0011A,\t\u000bm\u0003A\u0011A,\t\u000bq\u0003A\u0011I/\t\r)\u0004A\u0011A\nl\r\u0019y\b\u0001A\n\u0002\u0002!a\u00111A\u0006\u0003\u0002\u0003\u0006I!!\u0002\u0002\f!Q\u0011qB\u0006\u0003\u0002\u0003\u0006I!!\u0005\t\rqZA\u0011AA\u000f\u0011\u001d\t9c\u0003C!\u0003S\u0011ACU3dK&4XM]%oaV$Hi\u0015;sK\u0006l'B\u0001\n\u0014\u0003\u001d!7\u000f\u001e:fC6T!\u0001F\u000b\u0002\u0013M$(/Z1nS:<'B\u0001\f\u0018\u0003\u0015\u0019\b/\u0019:l\u0015\tA\u0012$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00025\u0005\u0019qN]4\u0004\u0001U\u0011Q\u0004J\n\u0003\u0001y\u00012a\b\u0011#\u001b\u0005\t\u0012BA\u0011\u0012\u00051Ie\u000e];u\tN#(/Z1n!\t\u0019C\u0005\u0004\u0001\u0005\u000b\u0015\u0002!\u0019\u0001\u0014\u0003\u0003Q\u000b\"aJ\u0017\u0011\u0005!ZS\"A\u0015\u000b\u0003)\nQa]2bY\u0006L!\u0001L\u0015\u0003\u000f9{G\u000f[5oOB\u0011\u0001FL\u0005\u0003_%\u00121!\u00118z\u0003\u0011y6o]2\u0011\u0005I\u001aT\"A\n\n\u0005Q\u001a\"\u0001E*ue\u0016\fW.\u001b8h\u0007>tG/\u001a=u\u0003))g/\u001b3f]\u000e,G%\r\t\u0004oi\u0012S\"\u0001\u001d\u000b\u0005eJ\u0013a\u0002:fM2,7\r^\u0005\u0003wa\u0012\u0001b\u00117bgN$\u0016mZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005y\nECA A!\ry\u0002A\t\u0005\u0006k\r\u0001\u001dA\u000e\u0005\u0006a\r\u0001\r!M\u0001\u000fe\u0006$XmQ8oiJ|G\u000e\\3s+\u0005!\u0005c\u0001\u0015F\u000f&\u0011a)\u000b\u0002\u0007\u001fB$\u0018n\u001c8\u0011\u0005![U\"A%\u000b\u0005)\u001b\u0012!C:dQ\u0016$W\u000f\\3s\u0013\ta\u0015J\u0001\bSCR,7i\u001c8ue>dG.\u001a:\u0002\u001fI\fG/Z\"p]R\u0014x\u000e\u001c7fe\u0002\n1bZ3u%\u0016\u001cW-\u001b<feR\t\u0001\u000bE\u0002R)\nj\u0011A\u0015\u0006\u0003'N\t\u0001B]3dK&4XM]\u0005\u0003+J\u0013\u0001BU3dK&4XM]\u0001\u0006gR\f'\u000f\u001e\u000b\u00021B\u0011\u0001&W\u0005\u00035&\u0012A!\u00168ji\u0006!1\u000f^8q\u0003\u001d\u0019w.\u001c9vi\u0016$\"AX3\u0011\u0007!*u\fE\u0002aG\nj\u0011!\u0019\u0006\u0003EV\t1A\u001d3e\u0013\t!\u0017MA\u0002S\t\u0012CQAZ\u0005A\u0002\u001d\f\u0011B^1mS\u0012$\u0016.\\3\u0011\u0005IB\u0017BA5\u0014\u0005\u0011!\u0016.\\3\u0002\u001d\r\u0014X-\u0019;f\u00052|7m\u001b*E\tR\u0019q\f\u001c8\t\u000b5T\u0001\u0019A4\u0002\tQLW.\u001a\u0005\u0006_*\u0001\r\u0001]\u0001\u000bE2|7m[%oM>\u001c\bcA9zy:\u0011!o\u001e\b\u0003gZl\u0011\u0001\u001e\u0006\u0003kn\ta\u0001\u0010:p_Rt\u0014\"\u0001\u0016\n\u0005aL\u0013a\u00029bG.\fw-Z\u0005\u0003un\u00141aU3r\u0015\tA\u0018\u0006\u0005\u0002I{&\u0011a0\u0013\u0002\u0012%\u0016\u001cW-\u001b<fI\ncwnY6J]\u001a|'A\u0006*fG\u0016Lg/\u001a:SCR,7i\u001c8ue>dG.\u001a:\u0014\u0005-9\u0015AA5e!\rA\u0013qA\u0005\u0004\u0003\u0013I#aA%oi&\u0019\u0011QB&\u0002\u0013M$(/Z1n+&#\u0015!C3ti&l\u0017\r^8s!\u0011\t\u0019\"!\u0007\u000e\u0005\u0005U!bAA\f\u0013\u0006!!/\u0019;f\u0013\u0011\tY\"!\u0006\u0003\u001bI\u000bG/Z#ti&l\u0017\r^8s)\u0019\ty\"a\t\u0002&A\u0019\u0011\u0011E\u0006\u000e\u0003\u0001Aq!a\u0001\u000f\u0001\u0004\t)\u0001C\u0004\u0002\u00109\u0001\r!!\u0005\u0002\u000fA,(\r\\5tQR\u0019\u0001,a\u000b\t\u000f\u0005]q\u00021\u0001\u0002.A\u0019\u0001&a\f\n\u0007\u0005E\u0012F\u0001\u0003M_:<\u0007"
)
public abstract class ReceiverInputDStream extends InputDStream {
   private final ClassTag evidence$1;
   private final Option rateController;

   public Option rateController() {
      return this.rateController;
   }

   public abstract Receiver getReceiver();

   public void start() {
   }

   public void stop() {
   }

   public Option compute(final Time validTime) {
      Object var10000;
      if (validTime.$less(this.graph().startTime())) {
         var10000 = new BlockRDD(this.ssc().sc(), (BlockId[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(BlockId.class)), this.evidence$1);
      } else {
         ReceiverTracker receiverTracker = this.ssc().scheduler().receiverTracker();
         Seq blockInfos = (Seq)receiverTracker.getBlocksOfBatch(validTime).getOrElse(BoxesRunTime.boxToInteger(this.id()), () -> (Seq)scala.package..MODULE$.Seq().empty());
         StreamInputInfo inputInfo = new StreamInputInfo(this.id(), BoxesRunTime.unboxToLong(((IterableOnceOps)blockInfos.flatMap((x$1) -> x$1.numRecords())).sum(scala.math.Numeric.LongIsIntegral..MODULE$)), StreamInputInfo$.MODULE$.apply$default$3());
         this.ssc().scheduler().inputInfoTracker().reportInfo(validTime, inputInfo);
         var10000 = this.createBlockRDD(validTime, blockInfos);
      }

      RDD blockRDD = (RDD)var10000;
      return new Some(blockRDD);
   }

   public RDD createBlockRDD(final Time time, final Seq blockInfos) {
      if (blockInfos.nonEmpty()) {
         BlockId[] blockIds = (BlockId[])((IterableOnceOps)blockInfos.map((x$2) -> x$2.blockId())).toArray(scala.reflect.ClassTag..MODULE$.apply(BlockId.class));
         boolean areWALRecordHandlesPresent = blockInfos.forall((x$3) -> BoxesRunTime.boxToBoolean($anonfun$createBlockRDD$2(x$3)));
         if (areWALRecordHandlesPresent) {
            boolean[] isBlockIdValid = (boolean[])((IterableOnceOps)blockInfos.map((x$4) -> BoxesRunTime.boxToBoolean($anonfun$createBlockRDD$3(x$4)))).toArray(scala.reflect.ClassTag..MODULE$.Boolean());
            WriteAheadLogRecordHandle[] walRecordHandles = (WriteAheadLogRecordHandle[])((IterableOnceOps)blockInfos.map((x$5) -> (WriteAheadLogRecordHandle)x$5.walRecordHandleOption().get())).toArray(scala.reflect.ClassTag..MODULE$.apply(WriteAheadLogRecordHandle.class));
            return new WriteAheadLogBackedBlockRDD(this.ssc().sparkContext(), blockIds, walRecordHandles, isBlockIdValid, WriteAheadLogBackedBlockRDD$.MODULE$.$lessinit$greater$default$5(), WriteAheadLogBackedBlockRDD$.MODULE$.$lessinit$greater$default$6(), this.evidence$1);
         } else {
            if (blockInfos.exists((x$6) -> BoxesRunTime.boxToBoolean($anonfun$createBlockRDD$5(x$6)))) {
               if (WriteAheadLogUtils$.MODULE$.enableReceiverLog(this.ssc().conf())) {
                  this.logError(() -> "Some blocks do not have Write Ahead Log information; this is unexpected and data may not be recoverable after driver failures");
               } else {
                  this.logWarning(() -> "Some blocks have Write Ahead Log information; this is unexpected");
               }
            }

            BlockId[] validBlockIds = (BlockId[])scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps((Object[])blockIds), (id) -> BoxesRunTime.boxToBoolean($anonfun$createBlockRDD$8(this, id)));
            if (validBlockIds.length != blockIds.length) {
               this.logWarning(() -> "Some blocks could not be recovered as they were not found in memory. To prevent such data loss, enable Write Ahead Log (see programming guide for more details.");
            }

            return new BlockRDD(this.ssc().sc(), validBlockIds, this.evidence$1);
         }
      } else {
         return (RDD)(WriteAheadLogUtils$.MODULE$.enableReceiverLog(this.ssc().conf()) ? new WriteAheadLogBackedBlockRDD(this.ssc().sparkContext(), (BlockId[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(BlockId.class)), (WriteAheadLogRecordHandle[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(WriteAheadLogRecordHandle.class)), (boolean[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.Boolean()), WriteAheadLogBackedBlockRDD$.MODULE$.$lessinit$greater$default$5(), WriteAheadLogBackedBlockRDD$.MODULE$.$lessinit$greater$default$6(), this.evidence$1) : new BlockRDD(this.ssc().sc(), (BlockId[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(BlockId.class)), this.evidence$1));
      }
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createBlockRDD$2(final ReceivedBlockInfo x$3) {
      return x$3.walRecordHandleOption().nonEmpty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createBlockRDD$3(final ReceivedBlockInfo x$4) {
      return x$4.isBlockIdValid();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createBlockRDD$5(final ReceivedBlockInfo x$6) {
      return x$6.walRecordHandleOption().nonEmpty();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$createBlockRDD$8(final ReceiverInputDStream $this, final BlockId id) {
      return $this.ssc().sparkContext().env().blockManager().master().contains(id);
   }

   public ReceiverInputDStream(final StreamingContext _ssc, final ClassTag evidence$1) {
      super(_ssc, evidence$1);
      this.evidence$1 = evidence$1;
      this.rateController = (Option)(RateController$.MODULE$.isBackPressureEnabled(this.ssc().conf()) ? new Some(new ReceiverRateController(this.id(), RateEstimator$.MODULE$.create(this.ssc().conf(), this.ssc().graph().batchDuration()))) : scala.None..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   public class ReceiverRateController extends RateController {
      // $FF: synthetic field
      public final ReceiverInputDStream $outer;

      public void publish(final long rate) {
         this.org$apache$spark$streaming$dstream$ReceiverInputDStream$ReceiverRateController$$$outer().ssc().scheduler().receiverTracker().sendRateUpdate(super.streamUID(), rate);
      }

      // $FF: synthetic method
      public ReceiverInputDStream org$apache$spark$streaming$dstream$ReceiverInputDStream$ReceiverRateController$$$outer() {
         return this.$outer;
      }

      public ReceiverRateController(final int id, final RateEstimator estimator) {
         if (ReceiverInputDStream.this == null) {
            throw null;
         } else {
            this.$outer = ReceiverInputDStream.this;
            super(id, estimator);
         }
      }
   }
}
