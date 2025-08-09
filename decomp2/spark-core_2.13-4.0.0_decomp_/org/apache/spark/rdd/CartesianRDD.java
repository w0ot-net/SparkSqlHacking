package org.apache.spark.rdd;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.NarrowDependency;
import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import org.apache.spark.errors.SparkCoreErrors$;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.SeqOps;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}b!\u0002\n\u0014\u0001UY\u0002\u0002\u0003\"\u0001\u0005\u0003\u0005\u000b\u0011B\"\t\u0011\u001d\u0003!\u00111A\u0005\u0002!C\u0001B\u0013\u0001\u0003\u0002\u0004%\ta\u0013\u0005\t#\u0002\u0011\t\u0011)Q\u0005\u0013\"A!\u000b\u0001BA\u0002\u0013\u00051\u000b\u0003\u0005V\u0001\t\u0005\r\u0011\"\u0001W\u0011!A\u0006A!A!B\u0013!\u0006\u0002C-\u0001\u0005\u0007\u0005\u000b1\u0002.\t\u0011\u0001\u0004!1!Q\u0001\f\u0005DQA\u0019\u0001\u0005\u0002\rDqa\u001b\u0001C\u0002\u0013\u0005A\u000e\u0003\u0004q\u0001\u0001\u0006I!\u001c\u0005\u0006c\u0002!\tE\u001d\u0005\u0006s\u0002!\tE\u001f\u0005\b\u0003#\u0001A\u0011IA\n\u0011\u001d\t9\u0003\u0001C!\u0003SAq!a\u000f\u0001\t\u0003\niD\u0001\u0007DCJ$Xm]5b]J#EI\u0003\u0002\u0015+\u0005\u0019!\u000f\u001a3\u000b\u0005Y9\u0012!B:qCJ\\'B\u0001\r\u001a\u0003\u0019\t\u0007/Y2iK*\t!$A\u0002pe\u001e,2\u0001H\u00155'\r\u0001QD\u000e\t\u0004=}\tS\"A\n\n\u0005\u0001\u001a\"a\u0001*E\tB!!%J\u00144\u001b\u0005\u0019#\"\u0001\u0013\u0002\u000bM\u001c\u0017\r\\1\n\u0005\u0019\u001a#A\u0002+va2,'\u0007\u0005\u0002)S1\u0001A!\u0002\u0016\u0001\u0005\u0004a#!\u0001+\u0004\u0001E\u0011Q\u0006\r\t\u0003E9J!aL\u0012\u0003\u000f9{G\u000f[5oOB\u0011!%M\u0005\u0003e\r\u00121!\u00118z!\tAC\u0007B\u00036\u0001\t\u0007AFA\u0001V!\t9tH\u0004\u00029{9\u0011\u0011\bP\u0007\u0002u)\u00111hK\u0001\u0007yI|w\u000e\u001e \n\u0003\u0011J!AP\u0012\u0002\u000fA\f7m[1hK&\u0011\u0001)\u0011\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003}\r\n!a]2\u0011\u0005\u0011+U\"A\u000b\n\u0005\u0019+\"\u0001D*qCJ\\7i\u001c8uKb$\u0018\u0001\u0002:eIF*\u0012!\u0013\t\u0004=}9\u0013\u0001\u0003:eIFzF%Z9\u0015\u00051{\u0005C\u0001\u0012N\u0013\tq5E\u0001\u0003V]&$\bb\u0002)\u0004\u0003\u0003\u0005\r!S\u0001\u0004q\u0012\n\u0014!\u0002:eIF\u0002\u0013\u0001\u0002:eIJ*\u0012\u0001\u0016\t\u0004=}\u0019\u0014\u0001\u0003:eIJzF%Z9\u0015\u00051;\u0006b\u0002)\u0007\u0003\u0003\u0005\r\u0001V\u0001\u0006e\u0012$'\u0007I\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004cA._O5\tAL\u0003\u0002^G\u00059!/\u001a4mK\u000e$\u0018BA0]\u0005!\u0019E.Y:t)\u0006<\u0017AC3wS\u0012,gnY3%eA\u00191LX\u001a\u0002\rqJg.\u001b;?)\u0011!\u0007.\u001b6\u0015\u0007\u00154w\r\u0005\u0003\u001f\u0001\u001d\u001a\u0004\"B-\u000b\u0001\bQ\u0006\"\u00021\u000b\u0001\b\t\u0007\"\u0002\"\u000b\u0001\u0004\u0019\u0005\"B$\u000b\u0001\u0004I\u0005\"\u0002*\u000b\u0001\u0004!\u0016a\u00058v[B\u000b'\u000f^5uS>t7/\u00138SI\u0012\u0014T#A7\u0011\u0005\tr\u0017BA8$\u0005\rIe\u000e^\u0001\u0015]Vl\u0007+\u0019:uSRLwN\\:J]J#GM\r\u0011\u0002\u001b\u001d,G\u000fU1si&$\u0018n\u001c8t+\u0005\u0019\bc\u0001\u0012um&\u0011Qo\t\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003\t^L!\u0001_\u000b\u0003\u0013A\u000b'\u000f^5uS>t\u0017!F4fiB\u0013XMZ3se\u0016$Gj\\2bi&|gn\u001d\u000b\u0004w\u00065\u0001cA\u001c}}&\u0011Q0\u0011\u0002\u0004'\u0016\f\bcA@\u0002\b9!\u0011\u0011AA\u0002!\tI4%C\u0002\u0002\u0006\r\na\u0001\u0015:fI\u00164\u0017\u0002BA\u0005\u0003\u0017\u0011aa\u0015;sS:<'bAA\u0003G!1\u0011q\u0002\bA\u0002Y\fQa\u001d9mSR\fqaY8naV$X\r\u0006\u0004\u0002\u0016\u0005m\u0011Q\u0004\t\u0005o\u0005]\u0011%C\u0002\u0002\u001a\u0005\u0013\u0001\"\u0013;fe\u0006$xN\u001d\u0005\u0007\u0003\u001fy\u0001\u0019\u0001<\t\u000f\u0005}q\u00021\u0001\u0002\"\u000591m\u001c8uKb$\bc\u0001#\u0002$%\u0019\u0011QE\u000b\u0003\u0017Q\u000b7o[\"p]R,\u0007\u0010^\u0001\u0010O\u0016$H)\u001a9f]\u0012,gnY5fgV\u0011\u00111\u0006\t\u0005oq\fi\u0003\r\u0003\u00020\u0005]\u0002#\u0002#\u00022\u0005U\u0012bAA\u001a+\tQA)\u001a9f]\u0012,gnY=\u0011\u0007!\n9\u0004\u0002\u0006\u0002:A\t\t\u0011!A\u0003\u00021\u00121a\u0018\u00134\u0003E\u0019G.Z1s\t\u0016\u0004XM\u001c3f]\u000eLWm\u001d\u000b\u0002\u0019\u0002"
)
public class CartesianRDD extends RDD {
   private RDD rdd1;
   private RDD rdd2;
   private final int numPartitionsInRdd2;

   public RDD rdd1() {
      return this.rdd1;
   }

   public void rdd1_$eq(final RDD x$1) {
      this.rdd1 = x$1;
   }

   public RDD rdd2() {
      return this.rdd2;
   }

   public void rdd2_$eq(final RDD x$1) {
      this.rdd2 = x$1;
   }

   public int numPartitionsInRdd2() {
      return this.numPartitionsInRdd2;
   }

   public Partition[] getPartitions() {
      long partitionNum = (long)this.numPartitionsInRdd2() * (long)this.rdd1().partitions().length;
      if (partitionNum > 2147483647L) {
         throw SparkCoreErrors$.MODULE$.tooManyArrayElementsError(partitionNum, Integer.MAX_VALUE);
      } else {
         Partition[] array = new Partition[(int)partitionNum];
         .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(this.rdd1().partitions()), (s1) -> {
            $anonfun$getPartitions$1(this, array, s1);
            return BoxedUnit.UNIT;
         });
         return array;
      }
   }

   public Seq getPreferredLocations(final Partition split) {
      CartesianPartition currSplit = (CartesianPartition)split;
      return (Seq)((SeqOps)this.rdd1().preferredLocations(currSplit.s1()).$plus$plus(this.rdd2().preferredLocations(currSplit.s2()))).distinct();
   }

   public Iterator compute(final Partition split, final TaskContext context) {
      CartesianPartition currSplit = (CartesianPartition)split;
      return this.rdd1().iterator(currSplit.s1(), context).flatMap((x) -> this.rdd2().iterator(currSplit.s2(), context).map((y) -> new Tuple2(x, y)));
   }

   public Seq getDependencies() {
      return new scala.collection.immutable..colon.colon(new NarrowDependency() {
         // $FF: synthetic field
         private final CartesianRDD $outer;

         public Seq getParents(final int id) {
            return (Seq)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{id / this.$outer.numPartitionsInRdd2()}));
         }

         public {
            if (CartesianRDD.this == null) {
               throw null;
            } else {
               this.$outer = CartesianRDD.this;
            }
         }
      }, new scala.collection.immutable..colon.colon(new NarrowDependency() {
         // $FF: synthetic field
         private final CartesianRDD $outer;

         public Seq getParents(final int id) {
            return (Seq)scala.package..MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{id % this.$outer.numPartitionsInRdd2()}));
         }

         public {
            if (CartesianRDD.this == null) {
               throw null;
            } else {
               this.$outer = CartesianRDD.this;
            }
         }
      }, scala.collection.immutable.Nil..MODULE$));
   }

   public void clearDependencies() {
      super.clearDependencies();
      this.rdd1_$eq((RDD)null);
      this.rdd2_$eq((RDD)null);
   }

   // $FF: synthetic method
   public static final void $anonfun$getPartitions$2(final CartesianRDD $this, final Partition s1$1, final Partition[] array$1, final Partition s2) {
      int idx = s1$1.index() * $this.numPartitionsInRdd2() + s2.index();
      array$1[idx] = new CartesianPartition(idx, $this.rdd1(), $this.rdd2(), s1$1.index(), s2.index());
   }

   // $FF: synthetic method
   public static final void $anonfun$getPartitions$1(final CartesianRDD $this, final Partition[] array$1, final Partition s1) {
      .MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps($this.rdd2().partitions()), (s2) -> {
         $anonfun$getPartitions$2($this, s1, array$1, s2);
         return BoxedUnit.UNIT;
      });
   }

   public CartesianRDD(final SparkContext sc, final RDD rdd1, final RDD rdd2, final ClassTag evidence$1, final ClassTag evidence$2) {
      this.rdd1 = rdd1;
      this.rdd2 = rdd2;
      super(sc, scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      this.numPartitionsInRdd2 = this.rdd2().partitions().length;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
