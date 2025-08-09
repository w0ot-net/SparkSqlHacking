package org.apache.spark.rdd;

import org.apache.spark.Partition;
import org.apache.spark.TaskContext;
import org.apache.spark.annotation.DeveloperApi;
import scala.Function1;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ua\u0001\u0002\u0007\u000e\u0001YA\u0001B\u000b\u0001\u0003\u0002\u0003\u0006I\u0001\u0007\u0005\tW\u0001\u0011\t\u0011)A\u0005Y!AQ\u0007\u0001B\u0002B\u0003-a\u0007C\u0003=\u0001\u0011\u0005Q\bC\u0003D\u0001\u0011\u0005C\tC\u0003]\u0001\u0011ESlB\u0003i\u001b!\u0005\u0011NB\u0003\r\u001b!\u0005!\u000eC\u0003=\u0011\u0011\u0005a\u000fC\u0003x\u0011\u0011\u0005\u0001\u0010C\u0005\u0002\u0002!\t\t\u0011\"\u0003\u0002\u0004\t\u0019\u0002+\u0019:uSRLwN\u001c)sk:Lgn\u001a*E\t*\u0011abD\u0001\u0004e\u0012$'B\u0001\t\u0012\u0003\u0015\u0019\b/\u0019:l\u0015\t\u00112#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002)\u0005\u0019qN]4\u0004\u0001U\u0011qCH\n\u0003\u0001a\u00012!\u0007\u000e\u001d\u001b\u0005i\u0011BA\u000e\u000e\u0005\r\u0011F\t\u0012\t\u0003;ya\u0001\u0001B\u0003 \u0001\t\u0007\u0001EA\u0001U#\t\ts\u0005\u0005\u0002#K5\t1EC\u0001%\u0003\u0015\u00198-\u00197b\u0013\t13EA\u0004O_RD\u0017N\\4\u0011\u0005\tB\u0013BA\u0015$\u0005\r\te._\u0001\u0005aJ,g/A\nqCJ$\u0018\u000e^5p]\u001aKG\u000e^3s\rVt7\r\u0005\u0003#[=\u0012\u0014B\u0001\u0018$\u0005%1UO\\2uS>t\u0017\u0007\u0005\u0002#a%\u0011\u0011g\t\u0002\u0004\u0013:$\bC\u0001\u00124\u0013\t!4EA\u0004C_>dW-\u00198\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u00028uqi\u0011\u0001\u000f\u0006\u0003s\r\nqA]3gY\u0016\u001cG/\u0003\u0002<q\tA1\t\\1tgR\u000bw-\u0001\u0004=S:LGO\u0010\u000b\u0004}\u0005\u0013ECA A!\rI\u0002\u0001\b\u0005\u0006k\u0011\u0001\u001dA\u000e\u0005\u0006U\u0011\u0001\r\u0001\u0007\u0005\u0006W\u0011\u0001\r\u0001L\u0001\bG>l\u0007/\u001e;f)\r)\u0015k\u0016\t\u0004\r:cbBA$M\u001d\tA5*D\u0001J\u0015\tQU#\u0001\u0004=e>|GOP\u0005\u0002I%\u0011QjI\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0005K\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0015\ti5\u0005C\u0003S\u000b\u0001\u00071+A\u0003ta2LG\u000f\u0005\u0002U+6\tq\"\u0003\u0002W\u001f\tI\u0001+\u0019:uSRLwN\u001c\u0005\u00061\u0016\u0001\r!W\u0001\bG>tG/\u001a=u!\t!&,\u0003\u0002\\\u001f\tYA+Y:l\u0007>tG/\u001a=u\u000359W\r\u001e)beRLG/[8ogV\ta\fE\u0002#?NK!\u0001Y\u0012\u0003\u000b\u0005\u0013(/Y=)\u0005\u0001\u0011\u0007CA2g\u001b\u0005!'BA3\u0010\u0003)\tgN\\8uCRLwN\\\u0005\u0003O\u0012\u0014A\u0002R3wK2|\u0007/\u001a:Ba&\f1\u0003U1si&$\u0018n\u001c8QeVt\u0017N\\4S\t\u0012\u0003\"!\u0007\u0005\u0014\u0007!Yg\u000e\u0005\u0002#Y&\u0011Qn\t\u0002\u0007\u0003:L(+\u001a4\u0011\u0005=$X\"\u00019\u000b\u0005E\u0014\u0018AA5p\u0015\u0005\u0019\u0018\u0001\u00026bm\u0006L!!\u001e9\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0003%\faa\u0019:fCR,WCA=})\rQXp \t\u00043\u0001Y\bCA\u000f}\t\u0015y\"B1\u0001!\u0011\u0015q!\u00021\u0001\u007f!\rI\"d\u001f\u0005\u0006W)\u0001\r\u0001L\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u000b\u0001B!a\u0002\u0002\u000e5\u0011\u0011\u0011\u0002\u0006\u0004\u0003\u0017\u0011\u0018\u0001\u00027b]\u001eLA!a\u0004\u0002\n\t1qJ\u00196fGRD#\u0001\u00032)\u0005\u001d\u0011\u0007"
)
public class PartitionPruningRDD extends RDD {
   private final ClassTag evidence$1;

   public static PartitionPruningRDD create(final RDD rdd, final Function1 partitionFilterFunc) {
      return PartitionPruningRDD$.MODULE$.create(rdd, partitionFilterFunc);
   }

   public Iterator compute(final Partition split, final TaskContext context) {
      return this.firstParent(this.evidence$1).iterator(((PartitionPruningRDDPartition)split).parentSplit(), context);
   }

   public Partition[] getPartitions() {
      return ((PruneDependency)this.dependencies().head()).partitions();
   }

   public PartitionPruningRDD(final RDD prev, final Function1 partitionFilterFunc, final ClassTag evidence$1) {
      super(prev.context(), new .colon.colon(new PruneDependency(prev, partitionFilterFunc), scala.collection.immutable.Nil..MODULE$), evidence$1);
      this.evidence$1 = evidence$1;
   }
}
