package org.apache.spark.scheduler;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.nio.ByteBuffer;
import java.util.Properties;
import org.apache.spark.JobArtifactSet;
import org.apache.spark.Partition;
import org.apache.spark.ShuffleDependency;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.rdd.RDD;
import org.apache.spark.serializer.SerializerInstance;
import scala.Option;
import scala.Tuple2;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005g!B\u0010!\u0001\tB\u0003\"\u0003\u001c\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001d?\u0011%y\u0004A!A!\u0002\u0013A\u0004\t\u0003\u0005B\u0001\t\u0005\t\u0015!\u0003C\u0011!q\u0005A!A!\u0002\u0013y\u0005\"C*\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001dU\u0011!)\u0006A!a\u0001\n\u00131\u0006\u0002\u00034\u0001\u0005\u0003\u0007I\u0011B4\t\u00115\u0004!\u0011!Q!\n]C\u0011B\u001d\u0001\u0003\u0002\u0003\u0006Ia\u001d<\t\u0011]\u0004!\u0011!Q\u0001\naD\u0011\"!\u0001\u0001\u0005\u0003\u0005\u000b\u0011\u0002%\t\u0019\u0005\r\u0001A!A!\u0002\u0013\t)!a\u0003\t\u0019\u00055\u0001A!A!\u0002\u0013\ty!!\t\t\u0019\u0005\r\u0002A!A!\u0002\u0013\ty!!\n\t\u0019\u0005\u001d\u0002A!A!\u0002\u0013\tI#a\f\t\u000f\u0005E\u0002\u0001\"\u0001\u00024!9\u0011\u0011\u0007\u0001\u0005\u0002\u0005E\u0003\u0002CA,\u0001\t\u0007I\u0011\u0002,\t\u000f\u0005e\u0003\u0001)A\u0005/\"9\u0011Q\f\u0001\u0005B\u0005}\u0003BBA6\u0001\u0011\u0005c\u000bC\u0004\u0002n\u0001!\t%a\u001c\b\u0015\u0005E\u0004%!A\t\u0002\t\n\u0019HB\u0005 A\u0005\u0005\t\u0012\u0001\u0012\u0002v!9\u0011\u0011\u0007\r\u0005\u0002\u0005%\u0005\"CAF1E\u0005I\u0011AAG\u0011%\t\u0019\u000bGI\u0001\n\u0003\t)\u000bC\u0005\u0002*b\t\n\u0011\"\u0001\u0002&\"I\u00111\u0016\r\u0012\u0002\u0013\u0005\u0011Q\u0016\u0005\n\u0003cC\u0012\u0011!C\u0005\u0003g\u0013ab\u00155vM\u001adW-T1q)\u0006\u001c8N\u0003\u0002\"E\u0005I1o\u00195fIVdWM\u001d\u0006\u0003G\u0011\nQa\u001d9be.T!!\n\u0014\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u00059\u0013aA8sON\u0019\u0001!\u000b\u0019\u0011\u0007)ZS&D\u0001!\u0013\ta\u0003E\u0001\u0003UCN\\\u0007C\u0001\u0016/\u0013\ty\u0003EA\u0005NCB\u001cF/\u0019;vgB\u0011\u0011\u0007N\u0007\u0002e)\u00111GI\u0001\tS:$XM\u001d8bY&\u0011QG\r\u0002\b\u0019><w-\u001b8h\u0003\u001d\u0019H/Y4f\u0013\u0012\u001c\u0001\u0001\u0005\u0002:y5\t!HC\u0001<\u0003\u0015\u00198-\u00197b\u0013\ti$HA\u0002J]RL!AN\u0016\u0002\u001dM$\u0018mZ3BiR,W\u000e\u001d;JI&\u0011qhK\u0001\u000bi\u0006\u001c8NQ5oCJL\bcA\"G\u00116\tAI\u0003\u0002FE\u0005I!M]8bI\u000e\f7\u000f^\u0005\u0003\u000f\u0012\u0013\u0011B\u0011:pC\u0012\u001c\u0017m\u001d;\u0011\u0007eJ5*\u0003\u0002Ku\t)\u0011I\u001d:bsB\u0011\u0011\bT\u0005\u0003\u001bj\u0012AAQ=uK\u0006I\u0001/\u0019:uSRLwN\u001c\t\u0003!Fk\u0011AI\u0005\u0003%\n\u0012\u0011\u0002U1si&$\u0018n\u001c8\u0002\u001b9,X\u000eU1si&$\u0018n\u001c8t\u0013\t\u00196&\u0001\u0003m_\u000e\u001cX#A,\u0011\u0007a\u00037M\u0004\u0002Z=:\u0011!,X\u0007\u00027*\u0011AlN\u0001\u0007yI|w\u000e\u001e \n\u0003mJ!a\u0018\u001e\u0002\u000fA\f7m[1hK&\u0011\u0011M\u0019\u0002\u0004'\u0016\f(BA0;!\tQC-\u0003\u0002fA\taA+Y:l\u0019>\u001c\u0017\r^5p]\u0006AAn\\2t?\u0012*\u0017\u000f\u0006\u0002iWB\u0011\u0011([\u0005\u0003Uj\u0012A!\u00168ji\"9AnBA\u0001\u0002\u00049\u0016a\u0001=%c\u0005)An\\2tA!\u0012\u0001b\u001c\t\u0003sAL!!\u001d\u001e\u0003\u0013Q\u0014\u0018M\\:jK:$\u0018!C1si&4\u0017m\u0019;t!\t\u0001F/\u0003\u0002vE\tq!j\u001c2BeRLg-Y2u'\u0016$\u0018B\u0001:,\u0003=awnY1m!J|\u0007/\u001a:uS\u0016\u001c\bCA=\u007f\u001b\u0005Q(BA>}\u0003\u0011)H/\u001b7\u000b\u0003u\fAA[1wC&\u0011qP\u001f\u0002\u000b!J|\u0007/\u001a:uS\u0016\u001c\u0018!F:fe&\fG.\u001b>fIR\u000b7o['fiJL7m]\u0001\u0006U>\u0014\u0017\n\u001a\t\u0005s\u0005\u001d\u0001(C\u0002\u0002\ni\u0012aa\u00149uS>t\u0017bAA\u0002W\u0005)\u0011\r\u001d9JIB)\u0011(a\u0002\u0002\u0012A!\u00111CA\u000e\u001d\u0011\t)\"a\u0006\u0011\u0005iS\u0014bAA\ru\u00051\u0001K]3eK\u001aLA!!\b\u0002 \t11\u000b\u001e:j]\u001eT1!!\u0007;\u0013\r\tiaK\u0001\rCB\u0004\u0018\t\u001e;f[B$\u0018\nZ\u0005\u0004\u0003GY\u0013!C5t\u0005\u0006\u0014(/[3s!\rI\u00141F\u0005\u0004\u0003[Q$a\u0002\"p_2,\u0017M\\\u0005\u0004\u0003OY\u0013A\u0002\u001fj]&$h\b\u0006\u000f\u00026\u0005]\u0012\u0011HA\u001e\u0003{\ty$!\u0011\u0002D\u0005\u0015\u0013qIA%\u0003\u0017\ni%a\u0014\u0011\u0005)\u0002\u0001\"\u0002\u001c\u0011\u0001\u0004A\u0004\"B \u0011\u0001\u0004A\u0004\"B!\u0011\u0001\u0004\u0011\u0005\"\u0002(\u0011\u0001\u0004y\u0005\"B*\u0011\u0001\u0004A\u0004\"B+\u0011\u0001\u00049\u0006\"\u0002:\u0011\u0001\u0004\u0019\b\"B<\u0011\u0001\u0004A\bBBA\u0001!\u0001\u0007\u0001\nC\u0005\u0002\u0004A\u0001\n\u00111\u0001\u0002\u0006!I\u0011Q\u0002\t\u0011\u0002\u0003\u0007\u0011q\u0002\u0005\n\u0003G\u0001\u0002\u0013!a\u0001\u0003\u001fA\u0011\"a\n\u0011!\u0003\u0005\r!!\u000b\u0015\t\u0005U\u00121\u000b\u0005\u0007\u0003+\n\u0002\u0019\u0001\u001d\u0002\u0017A\f'\u000f^5uS>t\u0017\nZ\u0001\u000eaJ,g-\u001a:sK\u0012dunY:\u0002\u001dA\u0014XMZ3se\u0016$Gj\\2tA!\u00121c\\\u0001\beVtG+Y:l)\ri\u0013\u0011\r\u0005\b\u0003G\"\u0002\u0019AA3\u0003\u001d\u0019wN\u001c;fqR\u00042\u0001UA4\u0013\r\tIG\t\u0002\f)\u0006\u001c8nQ8oi\u0016DH/\u0001\nqe\u00164WM\u001d:fI2{7-\u0019;j_:\u001c\u0018\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u0005E\u0011AD*ik\u001a4G.Z'baR\u000b7o\u001b\t\u0003Ua\u0019R\u0001GA<\u0003{\u00022!OA=\u0013\r\tYH\u000f\u0002\u0007\u0003:L(+\u001a4\u0011\t\u0005}\u0014QQ\u0007\u0003\u0003\u0003S1!a!}\u0003\tIw.\u0003\u0003\u0002\b\u0006\u0005%\u0001D*fe&\fG.\u001b>bE2,GCAA:\u0003q!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%cA*\"!a$+\t\u0005\u0015\u0011\u0011S\u0016\u0003\u0003'\u0003B!!&\u0002 6\u0011\u0011q\u0013\u0006\u0005\u00033\u000bY*A\u0005v]\u000eDWmY6fI*\u0019\u0011Q\u0014\u001e\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002\"\u0006]%!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006aB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE\nTCAATU\u0011\ty!!%\u00029\u0011bWm]:j]&$He\u001a:fCR,'\u000f\n3fM\u0006,H\u000e\u001e\u00132e\u0005aB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIE\u001aTCAAXU\u0011\tI#!%\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005U\u0006\u0003BA\\\u0003{k!!!/\u000b\u0007\u0005mF0\u0001\u0003mC:<\u0017\u0002BA`\u0003s\u0013aa\u00142kK\u000e$\b"
)
public class ShuffleMapTask extends Task {
   private final Broadcast taskBinary;
   private final Partition partition;
   private transient Seq locs;
   private final transient Seq preferredLocs;

   public static boolean $lessinit$greater$default$13() {
      return ShuffleMapTask$.MODULE$.$lessinit$greater$default$13();
   }

   public static Option $lessinit$greater$default$12() {
      return ShuffleMapTask$.MODULE$.$lessinit$greater$default$12();
   }

   public static Option $lessinit$greater$default$11() {
      return ShuffleMapTask$.MODULE$.$lessinit$greater$default$11();
   }

   public static Option $lessinit$greater$default$10() {
      return ShuffleMapTask$.MODULE$.$lessinit$greater$default$10();
   }

   private Seq locs() {
      return this.locs;
   }

   private void locs_$eq(final Seq x$1) {
      this.locs = x$1;
   }

   private Seq preferredLocs() {
      return this.preferredLocs;
   }

   public MapStatus runTask(final TaskContext context) {
      ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
      long deserializeStartTimeNs = System.nanoTime();
      long deserializeStartCpuTime = threadMXBean.isCurrentThreadCpuTimeSupported() ? threadMXBean.getCurrentThreadCpuTime() : 0L;
      SerializerInstance ser = SparkEnv$.MODULE$.get().closureSerializer().newInstance();
      Tuple2 rddAndDep = (Tuple2)ser.deserialize(ByteBuffer.wrap((byte[])this.taskBinary.value()), Thread.currentThread().getContextClassLoader(), .MODULE$.apply(Tuple2.class));
      this._executorDeserializeTimeNs_$eq(System.nanoTime() - deserializeStartTimeNs);
      this._executorDeserializeCpuTime_$eq(threadMXBean.isCurrentThreadCpuTimeSupported() ? threadMXBean.getCurrentThreadCpuTime() - deserializeStartCpuTime : 0L);
      RDD rdd = (RDD)rddAndDep._1();
      ShuffleDependency dep = (ShuffleDependency)rddAndDep._2();
      long mapId = BoxesRunTime.unboxToBoolean(SparkEnv$.MODULE$.get().conf().get(org.apache.spark.internal.config.package$.MODULE$.SHUFFLE_USE_OLD_FETCH_PROTOCOL())) ? (long)this.partitionId() : context.taskAttemptId();
      return dep.shuffleWriterProcessor().write(rdd.iterator(this.partition, context), dep, mapId, this.partitionId(), context);
   }

   public Seq preferredLocations() {
      return this.preferredLocs();
   }

   public String toString() {
      return scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("ShuffleMapTask(%d, %d)"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{BoxesRunTime.boxToInteger(super.stageId()), BoxesRunTime.boxToInteger(this.partitionId())}));
   }

   public ShuffleMapTask(final int stageId, final int stageAttemptId, final Broadcast taskBinary, final Partition partition, final int numPartitions, final Seq locs, final JobArtifactSet artifacts, final Properties localProperties, final byte[] serializedTaskMetrics, final Option jobId, final Option appId, final Option appAttemptId, final boolean isBarrier) {
      this.taskBinary = taskBinary;
      this.partition = partition;
      this.locs = locs;
      super(stageId, stageAttemptId, partition.index(), numPartitions, artifacts, localProperties, serializedTaskMetrics, jobId, appId, appAttemptId, isBarrier);
      this.preferredLocs = (Seq)(this.locs() == null ? scala.collection.immutable.Nil..MODULE$ : (Seq)this.locs().distinct());
   }

   public ShuffleMapTask(final int partitionId) {
      this(0, 0, (Broadcast)null, new Partition() {
         // $FF: synthetic method
         public boolean org$apache$spark$Partition$$super$equals(final Object x$1) {
            return super.equals(x$1);
         }

         public int hashCode() {
            return Partition.hashCode$(this);
         }

         public boolean equals(final Object other) {
            return Partition.equals$(this, other);
         }

         public int index() {
            return 0;
         }

         public {
            Partition.$init$(this);
         }
      }, 1, (Seq)null, (JobArtifactSet)null, new Properties(), (byte[])null, ShuffleMapTask$.MODULE$.$lessinit$greater$default$10(), ShuffleMapTask$.MODULE$.$lessinit$greater$default$11(), ShuffleMapTask$.MODULE$.$lessinit$greater$default$12(), ShuffleMapTask$.MODULE$.$lessinit$greater$default$13());
   }
}
