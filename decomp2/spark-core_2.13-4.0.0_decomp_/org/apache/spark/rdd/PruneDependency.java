package org.apache.spark.rdd;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.NarrowDependency;
import org.apache.spark.Partition;
import scala.Function1;
import scala.MatchError;
import scala.collection.immutable.List;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005U3Qa\u0002\u0005\u0001\u0015AA\u0001\"\u0003\u0001\u0003\u0002\u0003\u0006I!\n\u0005\tS\u0001\u0011\t\u0011)A\u0005U!)1\u0007\u0001C\u0001i!9\u0001\b\u0001b\u0001\n\u0003I\u0004B\u0002!\u0001A\u0003%!\bC\u0003F\u0001\u0011\u0005cIA\bQeVtW\rR3qK:$WM\\2z\u0015\tI!\"A\u0002sI\u0012T!a\u0003\u0007\u0002\u000bM\u0004\u0018M]6\u000b\u00055q\u0011AB1qC\u000eDWMC\u0001\u0010\u0003\ry'oZ\u000b\u0003#a\u0019\"\u0001\u0001\n\u0011\u0007M!b#D\u0001\u000b\u0013\t)\"B\u0001\tOCJ\u0014xn\u001e#fa\u0016tG-\u001a8dsB\u0011q\u0003\u0007\u0007\u0001\t\u0015I\u0002A1\u0001\u001c\u0005\u0005!6\u0001A\t\u00039\t\u0002\"!\b\u0011\u000e\u0003yQ\u0011aH\u0001\u0006g\u000e\fG.Y\u0005\u0003Cy\u0011qAT8uQ&tw\r\u0005\u0002\u001eG%\u0011AE\b\u0002\u0004\u0003:L\bc\u0001\u0014(-5\t\u0001\"\u0003\u0002)\u0011\t\u0019!\u000b\u0012#\u0002'A\f'\u000f^5uS>tg)\u001b7uKJ4UO\\2\u0011\tuYS\u0006M\u0005\u0003Yy\u0011\u0011BR;oGRLwN\\\u0019\u0011\u0005uq\u0013BA\u0018\u001f\u0005\rIe\u000e\u001e\t\u0003;EJ!A\r\u0010\u0003\u000f\t{w\u000e\\3b]\u00061A(\u001b8jiz\"2!\u000e\u001c8!\r1\u0003A\u0006\u0005\u0006\u0013\r\u0001\r!\n\u0005\u0006S\r\u0001\rAK\u0001\u000ba\u0006\u0014H/\u001b;j_:\u001cX#\u0001\u001e\u0011\u0007uYT(\u0003\u0002==\t)\u0011I\u001d:bsB\u00111CP\u0005\u0003\u007f)\u0011\u0011\u0002U1si&$\u0018n\u001c8\u0002\u0017A\f'\u000f^5uS>t7\u000f\t\u0015\u0003\u000b\t\u0003\"!H\"\n\u0005\u0011s\"!\u0003;sC:\u001c\u0018.\u001a8u\u0003)9W\r\u001e)be\u0016tGo\u001d\u000b\u0003\u000fN\u00032\u0001\u0013).\u001d\tIeJ\u0004\u0002K\u001b6\t1J\u0003\u0002M5\u00051AH]8pizJ\u0011aH\u0005\u0003\u001fz\tq\u0001]1dW\u0006<W-\u0003\u0002R%\n!A*[:u\u0015\tye\u0004C\u0003U\r\u0001\u0007Q&A\u0006qCJ$\u0018\u000e^5p]&#\u0007"
)
public class PruneDependency extends NarrowDependency {
   private final Function1 partitionFilterFunc;
   private final transient Partition[] partitions;

   public Partition[] partitions() {
      return this.partitions;
   }

   public List getParents(final int partitionId) {
      return (List).MODULE$.List().apply(scala.runtime.ScalaRunTime..MODULE$.wrapIntArray(new int[]{((PartitionPruningRDDPartition)this.partitions()[partitionId]).parentSplit().index()}));
   }

   // $FF: synthetic method
   public static final boolean $anonfun$partitions$1(final PruneDependency $this, final Partition s) {
      return $this.partitionFilterFunc.apply$mcZI$sp(s.index());
   }

   public PruneDependency(final RDD rdd, final Function1 partitionFilterFunc) {
      super(rdd);
      this.partitionFilterFunc = partitionFilterFunc;
      this.partitions = (Partition[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps((Object[])scala.collection.ArrayOps..MODULE$.zipWithIndex$extension(scala.Predef..MODULE$.refArrayOps(scala.collection.ArrayOps..MODULE$.filter$extension(scala.Predef..MODULE$.refArrayOps(rdd.partitions()), (s) -> BoxesRunTime.boxToBoolean($anonfun$partitions$1(this, s)))))), (x0$1) -> {
         if (x0$1 != null) {
            Partition split = (Partition)x0$1._1();
            int idx = x0$1._2$mcI$sp();
            return new PartitionPruningRDDPartition(idx, split);
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Partition.class));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
