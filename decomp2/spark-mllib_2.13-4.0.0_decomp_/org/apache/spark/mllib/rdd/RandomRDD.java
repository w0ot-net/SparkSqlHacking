package org.apache.spark.mllib.rdd;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import org.apache.spark.mllib.random.RandomDataGenerator;
import org.apache.spark.rdd.RDD;
import scala.collection.Iterator;
import scala.collection.immutable.Nil.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005}e!\u0002\u000b\u0016\u0001]y\u0002\u0002C\u001b\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u001c\t\u0011i\u0002!\u0011!Q\u0001\nmB\u0001B\u0010\u0001\u0003\u0002\u0003\u0006Ia\u0010\u0005\t\u0005\u0002\u0011)\u0019!C\u0005\u0007\"A!\n\u0001B\u0001B\u0003%A\t\u0003\u0005P\u0001\t\u0015\r\u0011\"\u0003Q\u0011!\t\u0006A!A!\u0002\u0013Y\u0004\u0002C*\u0001\u0005\u0007\u0005\u000b1\u0002+\t\u000bi\u0003A\u0011A.\t\u000b\u0015\u0004A\u0011\t4\t\u000bu\u0004A\u0011\t@\b\u0011\u0005\u0015Q\u0003#\u0001\u0018\u0003\u000f1q\u0001F\u000b\t\u0002]\tI\u0001\u0003\u0004[\u001b\u0011\u0005\u0011\u0011\u0005\u0005\u0007{6!\t!a\t\t\u000f\u0005UR\u0002\"\u0001\u00028!9\u00111K\u0007\u0005\u0002\u0005U\u0003\"CA:\u001bE\u0005I\u0011AA;\u0011%\ty)DA\u0001\n\u0013\t\tJA\u0005SC:$w.\u001c*E\t*\u0011acF\u0001\u0004e\u0012$'B\u0001\r\u001a\u0003\u0015iG\u000e\\5c\u0015\tQ2$A\u0003ta\u0006\u00148N\u0003\u0002\u001d;\u00051\u0011\r]1dQ\u0016T\u0011AH\u0001\u0004_J<WC\u0001\u0011)'\t\u0001\u0011\u0005E\u0002#I\u0019j\u0011a\t\u0006\u0003-eI!!J\u0012\u0003\u0007I#E\t\u0005\u0002(Q1\u0001A!B\u0015\u0001\u0005\u0004Y#!\u0001+\u0004\u0001E\u0011AF\r\t\u0003[Aj\u0011A\f\u0006\u0002_\u0005)1oY1mC&\u0011\u0011G\f\u0002\b\u001d>$\b.\u001b8h!\ti3'\u0003\u00025]\t\u0019\u0011I\\=\u0002\u0005M\u001c\u0007CA\u001c9\u001b\u0005I\u0012BA\u001d\u001a\u00051\u0019\u0006/\u0019:l\u0007>tG/\u001a=u\u0003\u0011\u0019\u0018N_3\u0011\u00055b\u0014BA\u001f/\u0005\u0011auN\\4\u0002\u001b9,X\u000eU1si&$\u0018n\u001c8t!\ti\u0003)\u0003\u0002B]\t\u0019\u0011J\u001c;\u0002\u0007Itw-F\u0001E!\r)\u0005JJ\u0007\u0002\r*\u0011qiF\u0001\u0007e\u0006tGm\\7\n\u0005%3%a\u0005*b]\u0012|W\u000eR1uC\u001e+g.\u001a:bi>\u0014\u0018\u0001\u0002:oO\u0002B#!\u0002'\u0011\u00055j\u0015B\u0001(/\u0005%!(/\u00198tS\u0016tG/\u0001\u0003tK\u0016$W#A\u001e\u0002\u000bM,W\r\u001a\u0011)\u0005\u001da\u0015AC3wS\u0012,gnY3%cA\u0019Q\u000b\u0017\u0014\u000e\u0003YS!a\u0016\u0018\u0002\u000fI,g\r\\3di&\u0011\u0011L\u0016\u0002\t\u00072\f7o\u001d+bO\u00061A(\u001b8jiz\"b\u0001\u00181bE\u000e$GCA/`!\rq\u0006AJ\u0007\u0002+!)1+\u0003a\u0002)\")Q'\u0003a\u0001m!)!(\u0003a\u0001w!)a(\u0003a\u0001\u007f!)!)\u0003a\u0001\t\"9q*\u0003I\u0001\u0002\u0004Y\u0014aB2p[B,H/\u001a\u000b\u0004OND\bc\u00015qM9\u0011\u0011N\u001c\b\u0003U6l\u0011a\u001b\u0006\u0003Y*\na\u0001\u0010:p_Rt\u0014\"A\u0018\n\u0005=t\u0013a\u00029bG.\fw-Z\u0005\u0003cJ\u0014\u0001\"\u0013;fe\u0006$xN\u001d\u0006\u0003_:BQ\u0001\u001e\u0006A\u0002U\fqa\u001d9mSRLe\u000e\u0005\u00028m&\u0011q/\u0007\u0002\n!\u0006\u0014H/\u001b;j_:DQ!\u001f\u0006A\u0002i\fqaY8oi\u0016DH\u000f\u0005\u00028w&\u0011A0\u0007\u0002\f)\u0006\u001c8nQ8oi\u0016DH/A\u0007hKR\u0004\u0016M\u001d;ji&|gn]\u000b\u0002\u007fB!Q&!\u0001v\u0013\r\t\u0019A\f\u0002\u0006\u0003J\u0014\u0018-_\u0001\n%\u0006tGm\\7S\t\u0012\u0003\"AX\u0007\u0014\u000b5\tY!!\u0005\u0011\u00075\ni!C\u0002\u0002\u00109\u0012a!\u00118z%\u00164\u0007\u0003BA\n\u0003;i!!!\u0006\u000b\t\u0005]\u0011\u0011D\u0001\u0003S>T!!a\u0007\u0002\t)\fg/Y\u0005\u0005\u0003?\t)B\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u0002\bU!\u0011QEA\u0019)%y\u0018qEA\u0015\u0003W\t\u0019\u0004C\u0003;\u001f\u0001\u00071\bC\u0003?\u001f\u0001\u0007q\b\u0003\u0004C\u001f\u0001\u0007\u0011Q\u0006\t\u0005\u000b\"\u000by\u0003E\u0002(\u0003c!Q!K\bC\u0002-BQaT\bA\u0002m\n\u0001cZ3u!>Lg\u000e^%uKJ\fGo\u001c:\u0016\t\u0005e\u0012\u0011\t\u000b\u0005\u0003w\tI\u0005\u0006\u0003\u0002>\u0005\r\u0003\u0003\u00025q\u0003\u007f\u00012aJA!\t\u0015I\u0003C1\u0001,\u0011%\t)\u0005EA\u0001\u0002\b\t9%\u0001\u0006fm&$WM\\2fII\u0002B!\u0016-\u0002@!9\u00111\n\tA\u0002\u00055\u0013!\u00039beRLG/[8o!\u0015q\u0016qJA \u0013\r\t\t&\u0006\u0002\u0013%\u0006tGm\\7S\t\u0012\u0003\u0016M\u001d;ji&|g.A\thKR4Vm\u0019;pe&#XM]1u_J$b!a\u0016\u0002f\u0005=\u0004\u0003\u00025q\u00033\u0002B!a\u0017\u0002b5\u0011\u0011Q\f\u0006\u0004\u0003?:\u0012A\u00027j]\u0006dw-\u0003\u0003\u0002d\u0005u#A\u0002,fGR|'\u000fC\u0004\u0002LE\u0001\r!a\u001a\u0011\u000by\u000by%!\u001b\u0011\u00075\nY'C\u0002\u0002n9\u0012a\u0001R8vE2,\u0007BBA9#\u0001\u0007q(\u0001\u0006wK\u000e$xN]*ju\u0016\f1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012*T\u0003BA<\u0003\u001b+\"!!\u001f+\u0007m\nYh\u000b\u0002\u0002~A!\u0011qPAE\u001b\t\t\tI\u0003\u0003\u0002\u0004\u0006\u0015\u0015!C;oG\",7m[3e\u0015\r\t9IL\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BAF\u0003\u0003\u0013\u0011#\u001e8dQ\u0016\u001c7.\u001a3WCJL\u0017M\\2f\t\u0015I#C1\u0001,\u000319(/\u001b;f%\u0016\u0004H.Y2f)\t\t\u0019\n\u0005\u0003\u0002\u0016\u0006mUBAAL\u0015\u0011\tI*!\u0007\u0002\t1\fgnZ\u0005\u0005\u0003;\u000b9J\u0001\u0004PE*,7\r\u001e"
)
public class RandomRDD extends RDD {
   private final long size;
   private final int numPartitions;
   private final transient RandomDataGenerator rng;
   private final transient long seed;
   private final ClassTag evidence$1;

   public static long $lessinit$greater$default$5() {
      return RandomRDD$.MODULE$.$lessinit$greater$default$5();
   }

   public static Iterator getVectorIterator(final RandomRDDPartition partition, final int vectorSize) {
      return RandomRDD$.MODULE$.getVectorIterator(partition, vectorSize);
   }

   public static Iterator getPointIterator(final RandomRDDPartition partition, final ClassTag evidence$2) {
      return RandomRDD$.MODULE$.getPointIterator(partition, evidence$2);
   }

   private RandomDataGenerator rng() {
      return this.rng;
   }

   private long seed() {
      return this.seed;
   }

   public Iterator compute(final Partition splitIn, final TaskContext context) {
      RandomRDDPartition split = (RandomRDDPartition)splitIn;
      return RandomRDD$.MODULE$.getPointIterator(split, this.evidence$1);
   }

   public Partition[] getPartitions() {
      return RandomRDD$.MODULE$.getPartitions(this.size, this.numPartitions, this.rng(), this.seed());
   }

   public RandomRDD(final SparkContext sc, final long size, final int numPartitions, final RandomDataGenerator rng, final long seed, final ClassTag evidence$1) {
      super(sc, .MODULE$, evidence$1);
      this.size = size;
      this.numPartitions = numPartitions;
      this.rng = rng;
      this.seed = seed;
      this.evidence$1 = evidence$1;
      scala.Predef..MODULE$.require(size > 0L, () -> "Positive RDD size required.");
      scala.Predef..MODULE$.require(numPartitions > 0, () -> "Positive number of partitions required");
      scala.Predef..MODULE$.require(scala.math.package..MODULE$.ceil((double)size / (double)numPartitions) <= (double)Integer.MAX_VALUE, () -> "Partition size cannot exceed Int.MaxValue");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
