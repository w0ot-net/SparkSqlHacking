package org.apache.spark.rdd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.OneToOneDependency;
import org.apache.spark.Partition;
import org.apache.spark.Partitioner;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.RichInt.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ua!\u0002\b\u0010\u0001E9\u0002\u0002\u0003\u0017\u0001\u0005\u0003\u0005\u000b\u0011B\u0017\t\u0011E\u0002!\u00111A\u0005\u0002IB\u0001b\u0010\u0001\u0003\u0002\u0004%\t\u0001\u0011\u0005\t\r\u0002\u0011\t\u0011)Q\u0005g!Aq\t\u0001B\u0002B\u0003-\u0001\nC\u0003O\u0001\u0011\u0005q\nC\u0004V\u0001\t\u0007I\u0011\t,\t\ru\u0003\u0001\u0015!\u0003X\u0011\u0015q\u0006\u0001\"\u0011`\u0011\u00151\u0007\u0001\"\u0011h\u0011\u0015\u0019\b\u0001\"\u0011u\u0011\u0015q\b\u0001\"\u0011\u0000\u0011\u001d\t\t\u0001\u0001C\u0005\u0003\u0007\u0011\u0001\u0004U1si&$\u0018n\u001c8fe\u0006;\u0018M]3V]&|gN\u0015#E\u0015\t\u0001\u0012#A\u0002sI\u0012T!AE\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005Q)\u0012AB1qC\u000eDWMC\u0001\u0017\u0003\ry'oZ\u000b\u00031}\u0019\"\u0001A\r\u0011\u0007iYR$D\u0001\u0010\u0013\tarBA\u0002S\t\u0012\u0003\"AH\u0010\r\u0001\u0011)\u0001\u0005\u0001b\u0001E\t\tAk\u0001\u0001\u0012\u0005\rJ\u0003C\u0001\u0013(\u001b\u0005)#\"\u0001\u0014\u0002\u000bM\u001c\u0017\r\\1\n\u0005!*#a\u0002(pi\"Lgn\u001a\t\u0003I)J!aK\u0013\u0003\u0007\u0005s\u00170\u0001\u0002tGB\u0011afL\u0007\u0002#%\u0011\u0001'\u0005\u0002\r'B\f'o[\"p]R,\u0007\u0010^\u0001\u0005e\u0012$7/F\u00014!\r!D(\u0007\b\u0003kir!AN\u001d\u000e\u0003]R!\u0001O\u0011\u0002\rq\u0012xn\u001c;?\u0013\u00051\u0013BA\u001e&\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0010 \u0003\u0007M+\u0017O\u0003\u0002<K\u0005A!\u000f\u001a3t?\u0012*\u0017\u000f\u0006\u0002B\tB\u0011AEQ\u0005\u0003\u0007\u0016\u0012A!\u00168ji\"9QiAA\u0001\u0002\u0004\u0019\u0014a\u0001=%c\u0005)!\u000f\u001a3tA\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\u0007%cU$D\u0001K\u0015\tYU%A\u0004sK\u001adWm\u0019;\n\u00055S%\u0001C\"mCN\u001cH+Y4\u0002\rqJg.\u001b;?)\r\u00016\u000b\u0016\u000b\u0003#J\u00032A\u0007\u0001\u001e\u0011\u00159e\u0001q\u0001I\u0011\u0015ac\u00011\u0001.\u0011\u0015\td\u00011\u00014\u0003-\u0001\u0018M\u001d;ji&|g.\u001a:\u0016\u0003]\u00032\u0001\n-[\u0013\tIVE\u0001\u0004PaRLwN\u001c\t\u0003]mK!\u0001X\t\u0003\u0017A\u000b'\u000f^5uS>tWM]\u0001\ra\u0006\u0014H/\u001b;j_:,'\u000fI\u0001\u000eO\u0016$\b+\u0019:uSRLwN\\:\u0016\u0003\u0001\u00042\u0001J1d\u0013\t\u0011WEA\u0003BeJ\f\u0017\u0010\u0005\u0002/I&\u0011Q-\u0005\u0002\n!\u0006\u0014H/\u001b;j_:\fQcZ3u!J,g-\u001a:sK\u0012dunY1uS>t7\u000f\u0006\u0002icB\u0019A\u0007P5\u0011\u0005)tgBA6m!\t1T%\u0003\u0002nK\u00051\u0001K]3eK\u001aL!a\u001c9\u0003\rM#(/\u001b8h\u0015\tiW\u0005C\u0003s\u0015\u0001\u00071-A\u0001t\u0003\u001d\u0019w.\u001c9vi\u0016$2!\u001e=z!\r!d/H\u0005\u0003oz\u0012\u0001\"\u0013;fe\u0006$xN\u001d\u0005\u0006e.\u0001\ra\u0019\u0005\u0006u.\u0001\ra_\u0001\bG>tG/\u001a=u!\tqC0\u0003\u0002~#\tYA+Y:l\u0007>tG/\u001a=u\u0003E\u0019G.Z1s\t\u0016\u0004XM\u001c3f]\u000eLWm\u001d\u000b\u0002\u0003\u0006a1-\u001e:s!J,g\rT8dgR)\u0001.!\u0002\u0002\u0012!1\u0001#\u0004a\u0001\u0003\u000f\u0001D!!\u0003\u0002\u000eA!!dGA\u0006!\rq\u0012Q\u0002\u0003\f\u0003\u001f\t)!!A\u0001\u0002\u000b\u0005!EA\u0002`IIBa!a\u0005\u000e\u0001\u0004\u0019\u0017\u0001\u00029beR\u0004"
)
public class PartitionerAwareUnionRDD extends RDD {
   private Seq rdds;
   private final Option partitioner;

   public Seq rdds() {
      return this.rdds;
   }

   public void rdds_$eq(final Seq x$1) {
      this.rdds = x$1;
   }

   public Option partitioner() {
      return this.partitioner;
   }

   public Partition[] getPartitions() {
      int numPartitions = ((Partitioner)this.partitioner().get()).numPartitions();
      return (Partition[]).MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), numPartitions).map((index) -> $anonfun$getPartitions$1(this, BoxesRunTime.unboxToInt(index))).toArray(scala.reflect.ClassTag..MODULE$.apply(Partition.class));
   }

   public Seq getPreferredLocations(final Partition s) {
      this.logDebug(() -> "Finding preferred location for " + this + ", partition " + s.index());
      Partition[] parentPartitions = ((PartitionerAwareUnionRDDPartition)s).parents();
      Seq locations = (Seq)((IterableOps)this.rdds().zip(scala.Predef..MODULE$.wrapRefArray(parentPartitions))).flatMap((x0$1) -> {
         if (x0$1 != null) {
            RDD rdd = (RDD)x0$1._1();
            Partition part = (Partition)x0$1._2();
            Seq parentLocations = this.currPrefLocs(rdd, part);
            this.logDebug(() -> "Location of " + rdd + " partition " + part.index() + " = " + parentLocations);
            return parentLocations;
         } else {
            throw new MatchError(x0$1);
         }
      });
      Option location = (Option)(locations.isEmpty() ? scala.None..MODULE$ : new Some(((Tuple2)locations.groupBy((x) -> x).maxBy((x$6) -> BoxesRunTime.boxToInteger($anonfun$getPreferredLocations$5(x$6)), scala.math.Ordering.Int..MODULE$))._1()));
      this.logDebug(() -> "Selected location for " + this + ", partition " + s.index() + " = " + location);
      return scala.Option..MODULE$.option2Iterable(location).toSeq();
   }

   public Iterator compute(final Partition s, final TaskContext context) {
      Partition[] parentPartitions = ((PartitionerAwareUnionRDDPartition)s).parents();
      return ((IterableOnce)this.rdds().zip(scala.Predef..MODULE$.wrapRefArray(parentPartitions))).iterator().flatMap((x0$1) -> {
         if (x0$1 != null) {
            RDD rdd = (RDD)x0$1._1();
            Partition p = (Partition)x0$1._2();
            return rdd.iterator(p, context);
         } else {
            throw new MatchError(x0$1);
         }
      });
   }

   public void clearDependencies() {
      super.clearDependencies();
      this.rdds_$eq((Seq)null);
   }

   private Seq currPrefLocs(final RDD rdd, final Partition part) {
      return (Seq)rdd.context().getPreferredLocs(rdd, part.index()).map((tl) -> tl.host());
   }

   // $FF: synthetic method
   public static final boolean $anonfun$new$1(final RDD x$3) {
      return x$3.partitioner().isDefined();
   }

   // $FF: synthetic method
   public static final PartitionerAwareUnionRDDPartition $anonfun$getPartitions$1(final PartitionerAwareUnionRDD $this, final int index) {
      return new PartitionerAwareUnionRDDPartition($this.rdds(), index);
   }

   // $FF: synthetic method
   public static final int $anonfun$getPreferredLocations$5(final Tuple2 x$6) {
      return ((SeqOps)x$6._2()).length();
   }

   public PartitionerAwareUnionRDD(final SparkContext sc, final Seq rdds, final ClassTag evidence$1) {
      this.rdds = rdds;
      super(sc, (Seq)rdds.map(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final OneToOneDependency apply(final RDD x) {
            return new OneToOneDependency(x);
         }
      }), evidence$1);
      scala.Predef..MODULE$.require(this.rdds().nonEmpty());
      scala.Predef..MODULE$.require(this.rdds().forall((x$3) -> BoxesRunTime.boxToBoolean($anonfun$new$1(x$3))));
      scala.Predef..MODULE$.require(((IterableOnceOps)this.rdds().flatMap((x$4) -> x$4.partitioner())).toSet().size() == 1, () -> {
         Seq var10000 = this.rdds();
         return "Parent RDDs have different partitioners: " + var10000.flatMap((x$5) -> x$5.partitioner());
      });
      this.partitioner = ((RDD)this.rdds().head()).partitioner();
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
