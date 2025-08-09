package org.apache.spark.scheduler;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.nio.ByteBuffer;
import java.util.Properties;
import org.apache.spark.JobArtifactSet;
import org.apache.spark.Partition;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.rdd.RDD;
import org.apache.spark.serializer.SerializerInstance;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=g!B\u000f\u001f\u0001\u00012\u0003\"C\"\u0001\u0005\u0003\u0005\u000b\u0011\u0002#H\u0011%A\u0005A!A!\u0002\u0013!\u0015\n\u0003\u0005K\u0001\t\u0005\t\u0015!\u0003L\u0011!9\u0006A!A!\u0002\u0013A\u0006\"\u0003/\u0001\u0005\u0003\u0005\u000b\u0011\u0002#^\u0011!q\u0006A!A!\u0002\u0013y\u0006\u0002\u00038\u0001\u0005\u000b\u0007I\u0011A8\t\u0011A\u0004!\u0011!Q\u0001\n\u0011C\u0011\"\u001d\u0001\u0003\u0002\u0003\u0006IA];\t\u0011Y\u0004!\u0011!Q\u0001\n]D\u0001\" \u0001\u0003\u0002\u0003\u0006I!\u0015\u0005\u000b}\u0002\u0011\t\u0011)A\u0005\u007f\u0006\u0015\u0001\u0002DA\u0004\u0001\t\u0005\t\u0015!\u0003\u0002\n\u0005m\u0001\u0002DA\u000f\u0001\t\u0005\t\u0015!\u0003\u0002\n\u0005}\u0001\u0002DA\u0011\u0001\t\u0005\t\u0015!\u0003\u0002$\u0005%\u0002bBA\u0016\u0001\u0011\u0005\u0011Q\u0006\u0005\b\u0003'\u0002\u0001\u0015!\u0003`\u0011\u001d\ti\u0006\u0001C!\u0003?Bq!a\u001b\u0001\t\u0003\ni\u0007C\u0004\u0002p\u0001!\t%!\u001d\b\u0015\u0005Md$!A\t\u0002\u0001\n)HB\u0005\u001e=\u0005\u0005\t\u0012\u0001\u0011\u0002x!9\u00111\u0006\f\u0005\u0002\u0005}\u0004\"CAA-E\u0005I\u0011AAB\u0011%\tyJFI\u0001\n\u0003\t\t\u000bC\u0005\u0002,Z\t\n\u0011\"\u0001\u0002.\"I\u00111\u0017\f\u0012\u0002\u0013\u0005\u0011Q\u0017\u0005\n\u0003\u007f3\u0012\u0011!C\u0005\u0003\u0003\u0014!BU3tk2$H+Y:l\u0015\ty\u0002%A\u0005tG\",G-\u001e7fe*\u0011\u0011EI\u0001\u0006gB\f'o\u001b\u0006\u0003G\u0011\na!\u00199bG\",'\"A\u0013\u0002\u0007=\u0014x-\u0006\u0003(\u0003gq3c\u0001\u0001)wA\u0019\u0011F\u000b\u0017\u000e\u0003yI!a\u000b\u0010\u0003\tQ\u000b7o\u001b\t\u0003[9b\u0001\u0001B\u00030\u0001\t\u0007\u0011GA\u0001V\u0007\u0001\t\"A\r\u001d\u0011\u0005M2T\"\u0001\u001b\u000b\u0003U\nQa]2bY\u0006L!a\u000e\u001b\u0003\u000f9{G\u000f[5oOB\u00111'O\u0005\u0003uQ\u00121!\u00118z!\ta\u0014)D\u0001>\u0015\tqt(\u0001\u0002j_*\t\u0001)\u0001\u0003kCZ\f\u0017B\u0001\">\u00051\u0019VM]5bY&T\u0018M\u00197f\u0003\u001d\u0019H/Y4f\u0013\u0012\u0004\"aM#\n\u0005\u0019#$aA%oi&\u00111IK\u0001\u000fgR\fw-Z!ui\u0016l\u0007\u000f^%e\u0013\tA%&\u0001\u0006uCN\\')\u001b8bef\u00042\u0001T(R\u001b\u0005i%B\u0001(!\u0003%\u0011'o\\1eG\u0006\u001cH/\u0003\u0002Q\u001b\nI!I]8bI\u000e\f7\u000f\u001e\t\u0004gI#\u0016BA*5\u0005\u0015\t%O]1z!\t\u0019T+\u0003\u0002Wi\t!!)\u001f;f\u0003%\u0001\u0018M\u001d;ji&|g\u000e\u0005\u0002Z56\t\u0001%\u0003\u0002\\A\tI\u0001+\u0019:uSRLwN\\\u0001\u000e]Vl\u0007+\u0019:uSRLwN\\:\n\u0005qS\u0013\u0001\u00027pGN\u00042\u0001\u00195l\u001d\t\tgM\u0004\u0002cK6\t1M\u0003\u0002ea\u00051AH]8pizJ\u0011!N\u0005\u0003OR\nq\u0001]1dW\u0006<W-\u0003\u0002jU\n\u00191+Z9\u000b\u0005\u001d$\u0004CA\u0015m\u0013\tigD\u0001\u0007UCN\\Gj\\2bi&|g.\u0001\u0005pkR\u0004X\u000f^%e+\u0005!\u0015!C8viB,H/\u00133!\u0003%\t'\u000f^5gC\u000e$8\u000f\u0005\u0002Zg&\u0011A\u000f\t\u0002\u000f\u0015>\u0014\u0017I\u001d;jM\u0006\u001cGoU3u\u0013\t\t(&A\bm_\u000e\fG\u000e\u0015:pa\u0016\u0014H/[3t!\tA80D\u0001z\u0015\tQx(\u0001\u0003vi&d\u0017B\u0001?z\u0005)\u0001&o\u001c9feRLWm]\u0001\u0016g\u0016\u0014\u0018.\u00197ju\u0016$G+Y:l\u001b\u0016$(/[2t\u0003\u0015QwNY%e!\u0011\u0019\u0014\u0011\u0001#\n\u0007\u0005\rAG\u0001\u0004PaRLwN\\\u0005\u0003}*\nQ!\u00199q\u0013\u0012\u0004RaMA\u0001\u0003\u0017\u0001B!!\u0004\u0002\u00169!\u0011qBA\t!\t\u0011G'C\u0002\u0002\u0014Q\na\u0001\u0015:fI\u00164\u0017\u0002BA\f\u00033\u0011aa\u0015;sS:<'bAA\ni%\u0019\u0011q\u0001\u0016\u0002\u0019\u0005\u0004\b/\u0011;uK6\u0004H/\u00133\n\u0007\u0005u!&A\u0005jg\n\u000b'O]5feB\u00191'!\n\n\u0007\u0005\u001dBGA\u0004C_>dW-\u00198\n\u0007\u0005\u0005\"&\u0001\u0004=S:LGO\u0010\u000b\u001f\u0003_\t9$!\u000f\u0002<\u0005u\u0012qHA!\u0003\u0007\n)%a\u0012\u0002J\u0005-\u0013QJA(\u0003#\u0002R!\u000b\u0001\u000221\u00022!LA\u001a\t\u0019\t)\u0004\u0001b\u0001c\t\tA\u000bC\u0003D!\u0001\u0007A\tC\u0003I!\u0001\u0007A\tC\u0003K!\u0001\u00071\nC\u0003X!\u0001\u0007\u0001\fC\u0003]!\u0001\u0007A\tC\u0003_!\u0001\u0007q\fC\u0003o!\u0001\u0007A\tC\u0003r!\u0001\u0007!\u000fC\u0003w!\u0001\u0007q\u000fC\u0003~!\u0001\u0007\u0011\u000bC\u0004\u007f!A\u0005\t\u0019A@\t\u0013\u0005\u001d\u0001\u0003%AA\u0002\u0005%\u0001\"CA\u000f!A\u0005\t\u0019AA\u0005\u0011%\t\t\u0003\u0005I\u0001\u0002\u0004\t\u0019#A\u0007qe\u00164WM\u001d:fI2{7m\u001d\u0015\u0004#\u0005]\u0003cA\u001a\u0002Z%\u0019\u00111\f\u001b\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018a\u0002:v]R\u000b7o\u001b\u000b\u0004Y\u0005\u0005\u0004bBA2%\u0001\u0007\u0011QM\u0001\bG>tG/\u001a=u!\rI\u0016qM\u0005\u0004\u0003S\u0002#a\u0003+bg.\u001cuN\u001c;fqR\f!\u0003\u001d:fM\u0016\u0014(/\u001a3M_\u000e\fG/[8ogV\tq,\u0001\u0005u_N#(/\u001b8h)\t\tY!\u0001\u0006SKN,H\u000e\u001e+bg.\u0004\"!\u000b\f\u0014\tY\tIh\u000f\t\u0004g\u0005m\u0014bAA?i\t1\u0011I\\=SK\u001a$\"!!\u001e\u00029\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132cU1\u0011QQAN\u0003;+\"!a\"+\u0007}\fIi\u000b\u0002\u0002\fB!\u0011QRAL\u001b\t\tyI\u0003\u0003\u0002\u0012\u0006M\u0015!C;oG\",7m[3e\u0015\r\t)\nN\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BAM\u0003\u001f\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u0019\t)\u0004\u0007b\u0001c\u0011)q\u0006\u0007b\u0001c\u0005aB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE\u0012TCBAR\u0003O\u000bI+\u0006\u0002\u0002&*\"\u0011\u0011BAE\t\u0019\t)$\u0007b\u0001c\u0011)q&\u0007b\u0001c\u0005aB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE\u001aTCBAR\u0003_\u000b\t\f\u0002\u0004\u00026i\u0011\r!\r\u0003\u0006_i\u0011\r!M\u0001\u001dI1,7o]5oSR$sM]3bi\u0016\u0014H\u0005Z3gCVdG\u000fJ\u00195+\u0019\t9,a/\u0002>V\u0011\u0011\u0011\u0018\u0016\u0005\u0003G\tI\t\u0002\u0004\u00026m\u0011\r!\r\u0003\u0006_m\u0011\r!M\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u0007\u0004B!!2\u0002L6\u0011\u0011q\u0019\u0006\u0004\u0003\u0013|\u0014\u0001\u00027b]\u001eLA!!4\u0002H\n1qJ\u00196fGR\u0004"
)
public class ResultTask extends Task {
   private final Broadcast taskBinary;
   private final Partition partition;
   private final int outputId;
   private final transient Seq preferredLocs;

   public static boolean $lessinit$greater$default$14() {
      return ResultTask$.MODULE$.$lessinit$greater$default$14();
   }

   public static Option $lessinit$greater$default$13() {
      return ResultTask$.MODULE$.$lessinit$greater$default$13();
   }

   public static Option $lessinit$greater$default$12() {
      return ResultTask$.MODULE$.$lessinit$greater$default$12();
   }

   public static Option $lessinit$greater$default$11() {
      return ResultTask$.MODULE$.$lessinit$greater$default$11();
   }

   public int outputId() {
      return this.outputId;
   }

   public Object runTask(final TaskContext context) {
      ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
      long deserializeStartTimeNs = System.nanoTime();
      long deserializeStartCpuTime = threadMXBean.isCurrentThreadCpuTimeSupported() ? threadMXBean.getCurrentThreadCpuTime() : 0L;
      SerializerInstance ser = SparkEnv$.MODULE$.get().closureSerializer().newInstance();
      Tuple2 var10 = (Tuple2)ser.deserialize(ByteBuffer.wrap((byte[])this.taskBinary.value()), Thread.currentThread().getContextClassLoader(), .MODULE$.apply(Tuple2.class));
      if (var10 != null) {
         RDD rdd = (RDD)var10._1();
         Function2 func = (Function2)var10._2();
         Tuple2 var9 = new Tuple2(rdd, func);
         RDD rdd = (RDD)var9._1();
         Function2 func = (Function2)var9._2();
         this._executorDeserializeTimeNs_$eq(System.nanoTime() - deserializeStartTimeNs);
         this._executorDeserializeCpuTime_$eq(threadMXBean.isCurrentThreadCpuTimeSupported() ? threadMXBean.getCurrentThreadCpuTime() - deserializeStartCpuTime : 0L);
         return func.apply(context, rdd.iterator(this.partition, context));
      } else {
         throw new MatchError(var10);
      }
   }

   public Seq preferredLocations() {
      return this.preferredLocs;
   }

   public String toString() {
      int var10000 = super.stageId();
      return "ResultTask(" + var10000 + ", " + this.partitionId() + ")";
   }

   public ResultTask(final int stageId, final int stageAttemptId, final Broadcast taskBinary, final Partition partition, final int numPartitions, final Seq locs, final int outputId, final JobArtifactSet artifacts, final Properties localProperties, final byte[] serializedTaskMetrics, final Option jobId, final Option appId, final Option appAttemptId, final boolean isBarrier) {
      super(stageId, stageAttemptId, partition.index(), numPartitions, artifacts, localProperties, serializedTaskMetrics, jobId, appId, appAttemptId, isBarrier);
      this.taskBinary = taskBinary;
      this.partition = partition;
      this.outputId = outputId;
      this.preferredLocs = (Seq)(locs == null ? scala.collection.immutable.Nil..MODULE$ : (Seq)locs.distinct());
   }
}
