package org.apache.spark.rdd;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import org.apache.spark.TaskContext;
import org.apache.spark.util.Utils$;
import scala.Function1;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005E4Q!\u0003\u0006\u0001\u0019IA\u0001\"\f\u0001\u0003\u0002\u0003\u0006IA\f\u0005\t_\u0001\u0011\u0019\u0011)A\u0006a!)a\u0007\u0001C\u0001o!9A\b\u0001b\u0001\n\u0013i\u0004BB!\u0001A\u0003%a\bC\u0003G\u0001\u0011\u0005s\tC\u0003N\u0001\u0011\u0005c\nC\u0003f\u0001\u0011\u0005cM\u0001\n[SB\u0004X\rZ,ji\"Le\u000eZ3y%\u0012#%BA\u0006\r\u0003\r\u0011H\r\u001a\u0006\u0003\u001b9\tQa\u001d9be.T!a\u0004\t\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005\t\u0012aA8sOV\u00111\u0003I\n\u0003\u0001Q\u00012!\u0006\f\u0019\u001b\u0005Q\u0011BA\f\u000b\u0005\r\u0011F\t\u0012\t\u00053qq\"&D\u0001\u001b\u0015\u0005Y\u0012!B:dC2\f\u0017BA\u000f\u001b\u0005\u0019!V\u000f\u001d7feA\u0011q\u0004\t\u0007\u0001\t\u0015\t\u0003A1\u0001$\u0005\u0005!6\u0001A\t\u0003I\u001d\u0002\"!G\u0013\n\u0005\u0019R\"a\u0002(pi\"Lgn\u001a\t\u00033!J!!\u000b\u000e\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u001aW%\u0011AF\u0007\u0002\u0005\u0019>tw-\u0001\u0003qe\u00164\bcA\u000b\u0017=\u0005QQM^5eK:\u001cW\rJ\u0019\u0011\u0007E\"d$D\u00013\u0015\t\u0019$$A\u0004sK\u001adWm\u0019;\n\u0005U\u0012$\u0001C\"mCN\u001cH+Y4\u0002\rqJg.\u001b;?)\tA4\b\u0006\u0002:uA\u0019Q\u0003\u0001\u0010\t\u000b=\u001a\u00019\u0001\u0019\t\u000b5\u001a\u0001\u0019\u0001\u0018\u0002\u0019M$\u0018M\u001d;J]\u0012L7-Z:\u0016\u0003y\u00022!G +\u0013\t\u0001%DA\u0003BeJ\f\u00170A\u0007ti\u0006\u0014H/\u00138eS\u000e,7\u000f\t\u0015\u0003\u000b\r\u0003\"!\u0007#\n\u0005\u0015S\"!\u0003;sC:\u001c\u0018.\u001a8u\u000359W\r\u001e)beRLG/[8ogV\t\u0001\nE\u0002\u001a\u007f%\u0003\"AS&\u000e\u00031I!\u0001\u0014\u0007\u0003\u0013A\u000b'\u000f^5uS>t\u0017!F4fiB\u0013XMZ3se\u0016$Gj\\2bi&|gn\u001d\u000b\u0003\u001f\u000e\u00042\u0001\u0015-\\\u001d\t\tfK\u0004\u0002S+6\t1K\u0003\u0002UE\u00051AH]8pizJ\u0011aG\u0005\u0003/j\tq\u0001]1dW\u0006<W-\u0003\u0002Z5\n\u00191+Z9\u000b\u0005]S\u0002C\u0001/a\u001d\tif\f\u0005\u0002S5%\u0011qLG\u0001\u0007!J,G-\u001a4\n\u0005\u0005\u0014'AB*ue&twM\u0003\u0002`5!)Am\u0002a\u0001\u0013\u0006)1\u000f\u001d7ji\u000691m\\7qkR,GcA4kYB\u0019\u0001\u000b\u001b\r\n\u0005%T&\u0001C%uKJ\fGo\u001c:\t\u000b-D\u0001\u0019A%\u0002\u000fM\u0004H.\u001b;J]\")Q\u000e\u0003a\u0001]\u000691m\u001c8uKb$\bC\u0001&p\u0013\t\u0001HBA\u0006UCN\\7i\u001c8uKb$\b"
)
public class ZippedWithIndexRDD extends RDD {
   private final ClassTag evidence$1;
   private final transient long[] startIndices;

   private long[] startIndices() {
      return this.startIndices;
   }

   public Partition[] getPartitions() {
      return (Partition[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.firstParent(this.evidence$1).partitions()), (x) -> new ZippedWithIndexRDDPartition(x, this.startIndices()[x.index()]), scala.reflect.ClassTag..MODULE$.apply(Partition.class));
   }

   public Seq getPreferredLocations(final Partition split) {
      return this.firstParent(this.evidence$1).preferredLocations(((ZippedWithIndexRDDPartition)split).prev());
   }

   public Iterator compute(final Partition splitIn, final TaskContext context) {
      ZippedWithIndexRDDPartition split = (ZippedWithIndexRDDPartition)splitIn;
      Iterator parentIter = this.firstParent(this.evidence$1).iterator(split.prev(), context);
      return Utils$.MODULE$.getIteratorZipWithIndex(parentIter, split.startIndex());
   }

   // $FF: synthetic method
   public static final long $anonfun$startIndices$1(final Iterator iterator) {
      return Utils$.MODULE$.getIteratorSize(iterator);
   }

   public ZippedWithIndexRDD(final RDD prev, final ClassTag evidence$1) {
      super(prev, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      this.evidence$1 = evidence$1;
      int n = prev.partitions().length;
      this.startIndices = n == 0 ? (long[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.Long()) : (n == 1 ? new long[]{0L} : (long[]).MODULE$.scanLeft$extension(scala.Predef..MODULE$.longArrayOps((long[])prev.context().runJob(prev, (Function1)((iterator) -> BoxesRunTime.boxToLong($anonfun$startIndices$1(iterator))), (Seq)scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), n - 1), scala.reflect.ClassTag..MODULE$.Long())), BoxesRunTime.boxToLong(0L), (JFunction2.mcJJJ.sp)(x$1, x$2) -> x$1 + x$2, scala.reflect.ClassTag..MODULE$.Long()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
