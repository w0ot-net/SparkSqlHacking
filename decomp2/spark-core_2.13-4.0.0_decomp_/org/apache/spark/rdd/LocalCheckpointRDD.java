package org.apache.spark.rdd;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import org.apache.spark.errors.SparkCoreErrors$;
import org.apache.spark.storage.RDDBlockId;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00154Q!\u0003\u0006\u0001\u0019IA\u0001b\n\u0001\u0003\u0002\u0003\u0006I\u0001\u000b\u0005\tY\u0001\u0011\t\u0011)A\u0005[!A\u0001\u0007\u0001B\u0001B\u0003%Q\u0006\u0003\u00052\u0001\t\r\t\u0015a\u00033\u0011\u0015A\u0004\u0001\"\u0001:\u0011\u0015A\u0004\u0001\"\u0001A\u0011\u0015A\u0005\u0001\"\u0015J\u0011\u0015\u0001\u0006\u0001\"\u0011R\u0005IaunY1m\u0007\",7m\u001b9pS:$(\u000b\u0012#\u000b\u0005-a\u0011a\u0001:eI*\u0011QBD\u0001\u0006gB\f'o\u001b\u0006\u0003\u001fA\ta!\u00199bG\",'\"A\t\u0002\u0007=\u0014x-\u0006\u0002\u00145M\u0011\u0001\u0001\u0006\t\u0004+YAR\"\u0001\u0006\n\u0005]Q!!D\"iK\u000e\\\u0007o\\5oiJ#E\t\u0005\u0002\u001a51\u0001A!B\u000e\u0001\u0005\u0004i\"!\u0001+\u0004\u0001E\u0011a\u0004\n\t\u0003?\tj\u0011\u0001\t\u0006\u0002C\u0005)1oY1mC&\u00111\u0005\t\u0002\b\u001d>$\b.\u001b8h!\tyR%\u0003\u0002'A\t\u0019\u0011I\\=\u0002\u0005M\u001c\u0007CA\u0015+\u001b\u0005a\u0011BA\u0016\r\u00051\u0019\u0006/\u0019:l\u0007>tG/\u001a=u\u0003\u0015\u0011H\rZ%e!\tyb&\u0003\u00020A\t\u0019\u0011J\u001c;\u0002\u001b9,X\u000eU1si&$\u0018n\u001c8t\u0003))g/\u001b3f]\u000e,G%\r\t\u0004gYBR\"\u0001\u001b\u000b\u0005U\u0002\u0013a\u0002:fM2,7\r^\u0005\u0003oQ\u0012\u0001b\u00117bgN$\u0016mZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\tijdh\u0010\u000b\u0003wq\u00022!\u0006\u0001\u0019\u0011\u0015\tT\u0001q\u00013\u0011\u00159S\u00011\u0001)\u0011\u0015aS\u00011\u0001.\u0011\u0015\u0001T\u00011\u0001.)\t\tE\t\u0006\u0002<\u0005\"91IBA\u0001\u0002\b\u0011\u0014AC3wS\u0012,gnY3%e!)1B\u0002a\u0001\u000bB\u0019QC\u0012\r\n\u0005\u001dS!a\u0001*E\t\u0006iq-\u001a;QCJ$\u0018\u000e^5p]N,\u0012A\u0013\t\u0004?-k\u0015B\u0001'!\u0005\u0015\t%O]1z!\tIc*\u0003\u0002P\u0019\tI\u0001+\u0019:uSRLwN\\\u0001\bG>l\u0007/\u001e;f)\r\u0011f\f\u0019\t\u0004'nCbB\u0001+Z\u001d\t)\u0006,D\u0001W\u0015\t9F$\u0001\u0004=e>|GOP\u0005\u0002C%\u0011!\fI\u0001\ba\u0006\u001c7.Y4f\u0013\taVL\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0015\tQ\u0006\u0005C\u0003`\u0011\u0001\u0007Q*A\u0005qCJ$\u0018\u000e^5p]\")\u0011\r\u0003a\u0001E\u000691m\u001c8uKb$\bCA\u0015d\u0013\t!GBA\u0006UCN\\7i\u001c8uKb$\b"
)
public class LocalCheckpointRDD extends CheckpointRDD {
   private final int rddId;
   private final int numPartitions;

   public Partition[] getPartitions() {
      return (Partition[]).MODULE$.map$extension(scala.Predef..MODULE$.intArrayOps((int[])scala.runtime.RichInt..MODULE$.until$extension(scala.Predef..MODULE$.intWrapper(0), this.numPartitions).toArray(scala.reflect.ClassTag..MODULE$.Int())), (i) -> $anonfun$getPartitions$1(BoxesRunTime.unboxToInt(i)), scala.reflect.ClassTag..MODULE$.apply(Partition.class));
   }

   public Iterator compute(final Partition partition, final TaskContext context) {
      throw SparkCoreErrors$.MODULE$.checkpointRDDBlockIdNotFoundError(new RDDBlockId(this.rddId, partition.index()));
   }

   // $FF: synthetic method
   public static final CheckpointRDDPartition $anonfun$getPartitions$1(final int i) {
      return new CheckpointRDDPartition(i);
   }

   public LocalCheckpointRDD(final SparkContext sc, final int rddId, final int numPartitions, final ClassTag evidence$1) {
      super(sc, evidence$1);
      this.rddId = rddId;
      this.numPartitions = numPartitions;
   }

   public LocalCheckpointRDD(final RDD rdd, final ClassTag evidence$2) {
      this(rdd.context(), rdd.id(), rdd.partitions().length, evidence$2);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
