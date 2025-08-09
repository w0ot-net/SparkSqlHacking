package org.apache.spark.mllib.rdd;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import org.apache.spark.TaskContext;
import org.apache.spark.rdd.RDD;
import scala.MatchError;
import scala.Tuple2;
import scala.Array.;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.mutable.ListBuffer;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005i4Q\u0001D\u0007\u0001\u001f]A\u0001\u0002\r\u0001\u0003\u0006\u0004%\t!\r\u0005\tg\u0001\u0011\t\u0011)A\u0005e!A\u0001\b\u0001BC\u0002\u0013\u0005\u0011\b\u0003\u0005>\u0001\t\u0005\t\u0015!\u0003;\u0011!q\u0004A!b\u0001\n\u0003I\u0004\u0002C \u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001e\t\u0011\u0001\u0003!1!Q\u0001\f\u0005CQa\u0012\u0001\u0005\u0002!CQ\u0001\u0015\u0001\u0005BECQ!\u001b\u0001\u0005B)DQa\u001e\u0001\u0005Ba\u0014!b\u00157jI&twM\u0015#E\u0015\tqq\"A\u0002sI\u0012T!\u0001E\t\u0002\u000b5dG.\u001b2\u000b\u0005I\u0019\u0012!B:qCJ\\'B\u0001\u000b\u0016\u0003\u0019\t\u0007/Y2iK*\ta#A\u0002pe\u001e,\"\u0001\u0007\u0014\u0014\u0005\u0001I\u0002c\u0001\u000e\u001d=5\t1D\u0003\u0002\u000f#%\u0011Qd\u0007\u0002\u0004%\u0012#\u0005cA\u0010#I5\t\u0001EC\u0001\"\u0003\u0015\u00198-\u00197b\u0013\t\u0019\u0003EA\u0003BeJ\f\u0017\u0010\u0005\u0002&M1\u0001A!B\u0014\u0001\u0005\u0004I#!\u0001+\u0004\u0001E\u0011!&\f\t\u0003?-J!\u0001\f\u0011\u0003\u000f9{G\u000f[5oOB\u0011qDL\u0005\u0003_\u0001\u00121!\u00118z\u0003\u0019\u0001\u0018M]3oiV\t!\u0007E\u0002\u001b9\u0011\nq\u0001]1sK:$\b\u0005\u000b\u0002\u0003kA\u0011qDN\u0005\u0003o\u0001\u0012\u0011\u0002\u001e:b]NLWM\u001c;\u0002\u0015]Lg\u000eZ8x'&TX-F\u0001;!\ty2(\u0003\u0002=A\t\u0019\u0011J\u001c;\u0002\u0017]Lg\u000eZ8x'&TX\rI\u0001\u0005gR,\u0007/A\u0003ti\u0016\u0004\b%\u0001\u0006fm&$WM\\2fIE\u00022AQ#%\u001b\u0005\u0019%B\u0001#!\u0003\u001d\u0011XM\u001a7fGRL!AR\"\u0003\u0011\rc\u0017m]:UC\u001e\fa\u0001P5oSRtD\u0003B%N\u001d>#\"A\u0013'\u0011\u0007-\u0003A%D\u0001\u000e\u0011\u0015\u0001\u0005\u0002q\u0001B\u0011\u0015\u0001\u0004\u00021\u00013\u0011\u0015A\u0004\u00021\u0001;\u0011\u0015q\u0004\u00021\u0001;\u0003\u001d\u0019w.\u001c9vi\u0016$2A\u00150e!\r\u00196L\b\b\u0003)fs!!\u0016-\u000e\u0003YS!a\u0016\u0015\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0013B\u0001.!\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001X/\u0003\u0011%#XM]1u_JT!A\u0017\u0011\t\u000b}K\u0001\u0019\u00011\u0002\u000bM\u0004H.\u001b;\u0011\u0005\u0005\u0014W\"A\t\n\u0005\r\f\"!\u0003)beRLG/[8o\u0011\u0015)\u0017\u00021\u0001g\u0003\u001d\u0019wN\u001c;fqR\u0004\"!Y4\n\u0005!\f\"a\u0003+bg.\u001cuN\u001c;fqR\fQcZ3u!J,g-\u001a:sK\u0012dunY1uS>t7\u000f\u0006\u0002lmB\u00191\u000b\u001c8\n\u00055l&aA*fcB\u0011qn\u001d\b\u0003aF\u0004\"!\u0016\u0011\n\u0005I\u0004\u0013A\u0002)sK\u0012,g-\u0003\u0002uk\n11\u000b\u001e:j]\u001eT!A\u001d\u0011\t\u000b}S\u0001\u0019\u00011\u0002\u001b\u001d,G\u000fU1si&$\u0018n\u001c8t+\u0005I\bcA\u0010#A\u0002"
)
public class SlidingRDD extends RDD {
   private final transient RDD parent;
   private final int windowSize;
   private final int step;
   private final ClassTag evidence$1;

   public RDD parent() {
      return this.parent;
   }

   public int windowSize() {
      return this.windowSize;
   }

   public int step() {
      return this.step;
   }

   public Iterator compute(final Partition split, final TaskContext context) {
      SlidingRDDPartition part = (SlidingRDDPartition)split;
      return this.firstParent(this.evidence$1).iterator(part.prev(), context).$plus$plus(() -> part.tail()).drop(part.offset()).sliding(this.windowSize(), this.step()).withPartial(false).map((x$1) -> x$1.toArray(this.evidence$1));
   }

   public Seq getPreferredLocations(final Partition split) {
      return this.firstParent(this.evidence$1).preferredLocations(((SlidingRDDPartition)split).prev());
   }

   public Partition[] getPartitions() {
      Partition[] parentPartitions = this.parent().partitions();
      int n = parentPartitions.length;
      if (n == 0) {
         return (Partition[]).MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(Partition.class));
      } else if (n == 1) {
         return (Partition[])((Object[])(new Partition[]{new SlidingRDDPartition(0, parentPartitions[0], (Seq)scala.package..MODULE$.Seq().empty(), 0)}));
      } else {
         int w1 = this.windowSize() - 1;
         Tuple2 var6 = scala.collection.ArrayOps..MODULE$.unzip$extension(scala.Predef..MODULE$.refArrayOps(this.parent().mapPartitions((iter) -> {
            Object w1Array = iter.take(w1).toArray(this.evidence$1);
            return scala.package..MODULE$.Iterator().single(new Tuple2(BoxesRunTime.boxToInteger(scala.runtime.ScalaRunTime..MODULE$.array_length(w1Array) + iter.length()), w1Array));
         }, this.parent().mapPartitions$default$2(), scala.reflect.ClassTag..MODULE$.apply(Tuple2.class)).collect()), scala.Predef..MODULE$.$conforms(), scala.reflect.ClassTag..MODULE$.Int(), scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(this.evidence$1.runtimeClass())));
         if (var6 == null) {
            throw new MatchError(var6);
         } else {
            int[] sizes = (int[])var6._1();
            Object[] heads = var6._2();
            Tuple2 var5 = new Tuple2(sizes, heads);
            int[] sizes = (int[])var5._1();
            Object[] heads = var5._2();
            ArrayBuffer partitions = scala.collection.mutable.ArrayBuffer..MODULE$.empty();
            int i = 0;
            int cumSize = 0;

            for(int partitionIndex = 0; i < n; ++i) {
               int mod = cumSize % this.step();
               int offset = mod == 0 ? 0 : this.step() - mod;
               int size = sizes[i];
               if (offset < size) {
                  ListBuffer tail = scala.collection.mutable.ListBuffer..MODULE$.empty();

                  for(int j = i + 1; j < n && tail.length() < w1; ++j) {
                     tail.$plus$plus$eq(scala.Predef..MODULE$.genericWrapArray(scala.collection.ArrayOps..MODULE$.take$extension(scala.Predef..MODULE$.genericArrayOps(heads[j]), w1 - tail.length())));
                  }

                  if (sizes[i] + tail.length() >= offset + this.windowSize()) {
                     partitions.$plus$eq(new SlidingRDDPartition(partitionIndex, parentPartitions[i], tail.toSeq(), offset));
                     ++partitionIndex;
                  }
               }

               cumSize += size;
            }

            return (Partition[])partitions.toArray(scala.reflect.ClassTag..MODULE$.apply(Partition.class));
         }
      }
   }

   public SlidingRDD(final RDD parent, final int windowSize, final int step, final ClassTag evidence$1) {
      super(parent, scala.reflect.ClassTag..MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(evidence$1.runtimeClass())));
      this.parent = parent;
      this.windowSize = windowSize;
      this.step = step;
      this.evidence$1 = evidence$1;
      scala.Predef..MODULE$.require(windowSize > 0 && step > 0 && (windowSize != 1 || step != 1), () -> {
         int var10000 = this.windowSize();
         return "Window size and step must be greater than 0, and they cannot be both 1, but got windowSize = " + var10000 + " and step = " + this.step() + ".";
      });
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
