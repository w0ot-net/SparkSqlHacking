package org.apache.spark.mllib.rdd;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.random.RandomDataGenerator;
import org.apache.spark.rdd.RDD;
import scala.collection.Iterator;
import scala.collection.immutable.Nil.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eb!B\t\u0013\u0001Qa\u0002\u0002\u0003\u0015\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0016\t\u00119\u0002!\u0011!Q\u0001\n=B\u0001\"\u000e\u0001\u0003\u0002\u0003\u0006IA\u000e\u0005\ts\u0001\u0011\t\u0011)A\u0005m!A!\b\u0001BC\u0002\u0013%1\b\u0003\u0005F\u0001\t\u0005\t\u0015!\u0003=\u0011!Q\u0005A!b\u0001\n\u0013Y\u0005\u0002\u0003'\u0001\u0005\u0003\u0005\u000b\u0011B\u0018\t\u000b9\u0003A\u0011A(\t\u000ba\u0003A\u0011I-\t\u000bA\u0004A\u0011K9\b\u0011U\u0014\u0012\u0011!E\u0001)Y4\u0001\"\u0005\n\u0002\u0002#\u0005Ac\u001e\u0005\u0007\u001d6!\t!a\u0002\t\u0013\u0005%Q\"%A\u0005\u0002\u0005-\u0001\"CA\u0011\u001b\u0005\u0005I\u0011BA\u0012\u0005=\u0011\u0016M\u001c3p[Z+7\r^8s%\u0012#%BA\n\u0015\u0003\r\u0011H\r\u001a\u0006\u0003+Y\tQ!\u001c7mS\nT!a\u0006\r\u0002\u000bM\u0004\u0018M]6\u000b\u0005eQ\u0012AB1qC\u000eDWMC\u0001\u001c\u0003\ry'oZ\n\u0003\u0001u\u00012A\b\u0011#\u001b\u0005y\"BA\n\u0017\u0013\t\tsDA\u0002S\t\u0012\u0003\"a\t\u0014\u000e\u0003\u0011R!!\n\u000b\u0002\r1Lg.\u00197h\u0013\t9CE\u0001\u0004WK\u000e$xN]\u0001\u0003g\u000e\u001c\u0001\u0001\u0005\u0002,Y5\ta#\u0003\u0002.-\ta1\u000b]1sW\u000e{g\u000e^3yi\u0006!1/\u001b>f!\t\u00014'D\u00012\u0015\u0005\u0011\u0014!B:dC2\f\u0017B\u0001\u001b2\u0005\u0011auN\\4\u0002\u0015Y,7\r^8s'&TX\r\u0005\u00021o%\u0011\u0001(\r\u0002\u0004\u0013:$\u0018!\u00048v[B\u000b'\u000f^5uS>t7/A\u0002s]\u001e,\u0012\u0001\u0010\t\u0004{\u0001\u0013U\"\u0001 \u000b\u0005}\"\u0012A\u0002:b]\u0012|W.\u0003\u0002B}\t\u0019\"+\u00198e_6$\u0015\r^1HK:,'/\u0019;peB\u0011\u0001gQ\u0005\u0003\tF\u0012a\u0001R8vE2,\u0017\u0001\u0002:oO\u0002B#AB$\u0011\u0005AB\u0015BA%2\u0005%!(/\u00198tS\u0016tG/\u0001\u0003tK\u0016$W#A\u0018\u0002\u000bM,W\r\u001a\u0011)\u0005!9\u0015A\u0002\u001fj]&$h\bF\u0004Q%N#VKV,\u0011\u0005E\u0003Q\"\u0001\n\t\u000b!J\u0001\u0019\u0001\u0016\t\u000b9J\u0001\u0019A\u0018\t\u000bUJ\u0001\u0019\u0001\u001c\t\u000beJ\u0001\u0019\u0001\u001c\t\u000biJ\u0001\u0019\u0001\u001f\t\u000f)K\u0001\u0013!a\u0001_\u000591m\\7qkR,Gc\u0001.gWB\u00191l\u0019\u0012\u000f\u0005q\u000bgBA/a\u001b\u0005q&BA0*\u0003\u0019a$o\\8u}%\t!'\u0003\u0002cc\u00059\u0001/Y2lC\u001e,\u0017B\u00013f\u0005!IE/\u001a:bi>\u0014(B\u000122\u0011\u00159'\u00021\u0001i\u0003\u001d\u0019\b\u000f\\5u\u0013:\u0004\"aK5\n\u0005)4\"!\u0003)beRLG/[8o\u0011\u0015a'\u00021\u0001n\u0003\u001d\u0019wN\u001c;fqR\u0004\"a\u000b8\n\u0005=4\"a\u0003+bg.\u001cuN\u001c;fqR\fQbZ3u!\u0006\u0014H/\u001b;j_:\u001cX#\u0001:\u0011\u0007A\u001a\b.\u0003\u0002uc\t)\u0011I\u001d:bs\u0006y!+\u00198e_64Vm\u0019;peJ#E\t\u0005\u0002R\u001bM\u0019Q\u0002_>\u0011\u0005AJ\u0018B\u0001>2\u0005\u0019\te.\u001f*fMB\u0019A0a\u0001\u000e\u0003uT!A`@\u0002\u0005%|'BAA\u0001\u0003\u0011Q\u0017M^1\n\u0007\u0005\u0015QP\u0001\u0007TKJL\u0017\r\\5{C\ndW\rF\u0001w\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%mU\u0011\u0011Q\u0002\u0016\u0004_\u0005=1FAA\t!\u0011\t\u0019\"!\b\u000e\u0005\u0005U!\u0002BA\f\u00033\t\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005m\u0011'\u0001\u0006b]:|G/\u0019;j_:LA!a\b\u0002\u0016\t\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\u0005\u0015\u0002\u0003BA\u0014\u0003[i!!!\u000b\u000b\u0007\u0005-r0\u0001\u0003mC:<\u0017\u0002BA\u0018\u0003S\u0011aa\u00142kK\u000e$\b"
)
public class RandomVectorRDD extends RDD {
   private final long size;
   private final int vectorSize;
   private final int numPartitions;
   private final transient RandomDataGenerator rng;
   private final transient long seed;

   public static long $lessinit$greater$default$6() {
      return RandomVectorRDD$.MODULE$.$lessinit$greater$default$6();
   }

   private RandomDataGenerator rng() {
      return this.rng;
   }

   private long seed() {
      return this.seed;
   }

   public Iterator compute(final Partition splitIn, final TaskContext context) {
      RandomRDDPartition split = (RandomRDDPartition)splitIn;
      return RandomRDD$.MODULE$.getVectorIterator(split, this.vectorSize);
   }

   public Partition[] getPartitions() {
      return RandomRDD$.MODULE$.getPartitions(this.size, this.numPartitions, this.rng(), this.seed());
   }

   public RandomVectorRDD(final SparkContext sc, final long size, final int vectorSize, final int numPartitions, final RandomDataGenerator rng, final long seed) {
      super(sc, .MODULE$, scala.reflect.ClassTag..MODULE$.apply(Vector.class));
      this.size = size;
      this.vectorSize = vectorSize;
      this.numPartitions = numPartitions;
      this.rng = rng;
      this.seed = seed;
      scala.Predef..MODULE$.require(size > 0L, () -> "Positive RDD size required.");
      scala.Predef..MODULE$.require(numPartitions > 0, () -> "Positive number of partitions required");
      scala.Predef..MODULE$.require(vectorSize > 0, () -> "Positive vector size required.");
      scala.Predef..MODULE$.require(scala.math.package..MODULE$.ceil((double)size / (double)numPartitions) <= (double)Integer.MAX_VALUE, () -> "Partition size cannot exceed Int.MaxValue");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
