package org.apache.spark.rdd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.NarrowDependency;
import org.apache.spark.Partition;
import org.apache.spark.TaskContext;
import scala.MatchError;
import scala.Option;
import scala.collection.IterableOnceOps;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005-d!\u0002\n\u0014\u0001UY\u0002\u0002\u0003\u0019\u0001\u0005\u0003\u0007I\u0011A\u0019\t\u0011I\u0002!\u00111A\u0005\u0002MB\u0001\"\u000f\u0001\u0003\u0002\u0003\u0006K!\b\u0005\t}\u0001\u0011\t\u0011)A\u0005\u007f!A!\t\u0001B\u0001B\u0003%1\t\u0003\u0005J\u0001\t\r\t\u0015a\u0003K\u0011\u0015\u0001\u0006\u0001\"\u0001R\u0011\u0015A\u0006\u0001\"\u0011Z\u0011\u0015\t\u0007\u0001\"\u0011c\u0011\u00151\b\u0001\"\u0011x\u0011\u001d\t)\u0001\u0001C!\u0003\u000fAq!!\u0003\u0001\t\u0003\nYa\u0002\u0006\u0002\"M\t\t\u0011#\u0001\u0016\u0003G1\u0011BE\n\u0002\u0002#\u0005Q#!\n\t\rAsA\u0011AA\u001f\u0011%\tyDDI\u0001\n\u0003\t\t\u0005C\u0005\u0002\\9\t\t\u0011\"\u0003\u0002^\ta1i\\1mKN\u001cW\r\u001a*E\t*\u0011A#F\u0001\u0004e\u0012$'B\u0001\f\u0018\u0003\u0015\u0019\b/\u0019:l\u0015\tA\u0012$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00025\u0005\u0019qN]4\u0016\u0005q\u00193C\u0001\u0001\u001e!\rqr$I\u0007\u0002'%\u0011\u0001e\u0005\u0002\u0004%\u0012#\u0005C\u0001\u0012$\u0019\u0001!Q\u0001\n\u0001C\u0002\u0019\u0012\u0011\u0001V\u0002\u0001#\t9S\u0006\u0005\u0002)W5\t\u0011FC\u0001+\u0003\u0015\u00198-\u00197b\u0013\ta\u0013FA\u0004O_RD\u0017N\\4\u0011\u0005!r\u0013BA\u0018*\u0005\r\te._\u0001\u0005aJ,g/F\u0001\u001e\u0003!\u0001(/\u001a<`I\u0015\fHC\u0001\u001b8!\tAS'\u0003\u00027S\t!QK\\5u\u0011\u001dA$!!AA\u0002u\t1\u0001\u001f\u00132\u0003\u0015\u0001(/\u001a<!Q\t\u00191\b\u0005\u0002)y%\u0011Q(\u000b\u0002\niJ\fgn]5f]R\fQ\"\\1y!\u0006\u0014H/\u001b;j_:\u001c\bC\u0001\u0015A\u0013\t\t\u0015FA\u0002J]R\f!\u0003]1si&$\u0018n\u001c8D_\u0006dWm]2feB\u0019\u0001\u0006\u0012$\n\u0005\u0015K#AB(qi&|g\u000e\u0005\u0002\u001f\u000f&\u0011\u0001j\u0005\u0002\u0013!\u0006\u0014H/\u001b;j_:\u001cu.\u00197fg\u000e,'/\u0001\u0006fm&$WM\\2fIE\u00022a\u0013(\"\u001b\u0005a%BA'*\u0003\u001d\u0011XM\u001a7fGRL!a\u0014'\u0003\u0011\rc\u0017m]:UC\u001e\fa\u0001P5oSRtD\u0003\u0002*V-^#\"a\u0015+\u0011\u0007y\u0001\u0011\u0005C\u0003J\u000f\u0001\u000f!\nC\u00031\u000f\u0001\u0007Q\u0004C\u0003?\u000f\u0001\u0007q\bC\u0004C\u000fA\u0005\t\u0019A\"\u0002\u001b\u001d,G\u000fU1si&$\u0018n\u001c8t+\u0005Q\u0006c\u0001\u0015\\;&\u0011A,\u000b\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003=~k\u0011!F\u0005\u0003AV\u0011\u0011\u0002U1si&$\u0018n\u001c8\u0002\u000f\r|W\u000e];uKR\u00191m\\9\u0011\u0007\u0011d\u0017E\u0004\u0002fU:\u0011a-[\u0007\u0002O*\u0011\u0001.J\u0001\u0007yI|w\u000e\u001e \n\u0003)J!a[\u0015\u0002\u000fA\f7m[1hK&\u0011QN\u001c\u0002\t\u0013R,'/\u0019;pe*\u00111.\u000b\u0005\u0006a&\u0001\r!X\u0001\na\u0006\u0014H/\u001b;j_:DQA]\u0005A\u0002M\fqaY8oi\u0016DH\u000f\u0005\u0002_i&\u0011Q/\u0006\u0002\f)\u0006\u001c8nQ8oi\u0016DH/A\bhKR$U\r]3oI\u0016t7-[3t+\u0005A\bc\u00013zw&\u0011!P\u001c\u0002\u0004'\u0016\f\bg\u0001?\u0002\u0002A\u0019a,`@\n\u0005y,\"A\u0003#fa\u0016tG-\u001a8dsB\u0019!%!\u0001\u0005\u0015\u0005\r!\"!A\u0001\u0002\u000b\u0005aEA\u0002`II\n\u0011c\u00197fCJ$U\r]3oI\u0016t7-[3t)\u0005!\u0014!F4fiB\u0013XMZ3se\u0016$Gj\\2bi&|gn\u001d\u000b\u0005\u0003\u001b\ty\u0002\u0005\u0003es\u0006=\u0001\u0003BA\t\u00033qA!a\u0005\u0002\u0016A\u0011a-K\u0005\u0004\u0003/I\u0013A\u0002)sK\u0012,g-\u0003\u0003\u0002\u001c\u0005u!AB*ue&twMC\u0002\u0002\u0018%BQ\u0001\u001d\u0007A\u0002u\u000bAbQ8bY\u0016\u001c8-\u001a3S\t\u0012\u0003\"A\b\b\u0014\u000b9\t9#!\f\u0011\u0007!\nI#C\u0002\u0002,%\u0012a!\u00118z%\u00164\u0007\u0003BA\u0018\u0003si!!!\r\u000b\t\u0005M\u0012QG\u0001\u0003S>T!!a\u000e\u0002\t)\fg/Y\u0005\u0005\u0003w\t\tD\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u0002$\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIM*B!a\u0011\u0002ZU\u0011\u0011Q\t\u0016\u0004\u0007\u0006\u001d3FAA%!\u0011\tY%!\u0016\u000e\u0005\u00055#\u0002BA(\u0003#\n\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005M\u0013&\u0001\u0006b]:|G/\u0019;j_:LA!a\u0016\u0002N\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0005\u000b\u0011\u0002\"\u0019\u0001\u0014\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005}\u0003\u0003BA1\u0003Oj!!a\u0019\u000b\t\u0005\u0015\u0014QG\u0001\u0005Y\u0006tw-\u0003\u0003\u0002j\u0005\r$AB(cU\u0016\u001cG\u000f"
)
public class CoalescedRDD extends RDD {
   private transient RDD prev;
   private final int maxPartitions;
   private final Option partitionCoalescer;
   private final ClassTag evidence$1;

   public static Option $lessinit$greater$default$3() {
      return CoalescedRDD$.MODULE$.$lessinit$greater$default$3();
   }

   public RDD prev() {
      return this.prev;
   }

   public void prev_$eq(final RDD x$1) {
      this.prev = x$1;
   }

   public Partition[] getPartitions() {
      PartitionCoalescer pc = (PartitionCoalescer)this.partitionCoalescer.getOrElse(() -> new DefaultPartitionCoalescer(DefaultPartitionCoalescer$.MODULE$.$lessinit$greater$default$1()));
      return (Partition[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[]).MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps(pc.coalesce(this.maxPartitions, this.prev())))), (x0$1) -> {
         if (x0$1 != null) {
            PartitionGroup pg = (PartitionGroup)x0$1._1();
            int i = x0$1._2$mcI$sp();
            int[] ids = (int[])((IterableOnceOps)pg.partitions().map((x$4) -> BoxesRunTime.boxToInteger($anonfun$getPartitions$3(x$4)))).toArray(scala.reflect.ClassTag..MODULE$.Int());
            return new CoalescedRDDPartition(i, this.prev(), ids, pg.prefLoc());
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Partition.class));
   }

   public Iterator compute(final Partition partition, final TaskContext context) {
      return ((CoalescedRDDPartition)partition).parents().iterator().flatMap((parentPartition) -> this.firstParent(this.evidence$1).iterator(parentPartition, context));
   }

   public Seq getDependencies() {
      return new scala.collection.immutable..colon.colon(new NarrowDependency() {
         // $FF: synthetic field
         private final CoalescedRDD $outer;

         public Seq getParents(final int id) {
            return org.apache.spark.util.ArrayImplicits..MODULE$.SparkArrayOps(((CoalescedRDDPartition)this.$outer.partitions()[id]).parentsIndices()).toImmutableArraySeq();
         }

         public {
            if (CoalescedRDD.this == null) {
               throw null;
            } else {
               this.$outer = CoalescedRDD.this;
            }
         }
      }, scala.collection.immutable.Nil..MODULE$);
   }

   public void clearDependencies() {
      super.clearDependencies();
      this.prev_$eq((RDD)null);
   }

   public Seq getPreferredLocations(final Partition partition) {
      return scala.Option..MODULE$.option2Iterable(((CoalescedRDDPartition)partition).preferredLocation()).toSeq();
   }

   // $FF: synthetic method
   public static final int $anonfun$getPartitions$3(final Partition x$4) {
      return x$4.index();
   }

   public CoalescedRDD(final RDD prev, final int maxPartitions, final Option partitionCoalescer, final ClassTag evidence$1) {
      this.prev = prev;
      this.maxPartitions = maxPartitions;
      this.partitionCoalescer = partitionCoalescer;
      this.evidence$1 = evidence$1;
      super(prev.context(), scala.collection.immutable.Nil..MODULE$, evidence$1);
      scala.Predef..MODULE$.require(maxPartitions > 0 || maxPartitions == this.prev().partitions().length, () -> "Number of partitions (" + this.maxPartitions + ") must be positive.");
      if (partitionCoalescer.isDefined()) {
         scala.Predef..MODULE$.require(partitionCoalescer.get() instanceof Serializable, () -> "The partition coalescer passed in must be serializable.");
      }

   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
