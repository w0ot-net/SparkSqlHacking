package org.apache.spark.rdd;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.OneToOneDependency;
import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import scala.Option;
import scala.Array.;
import scala.collection.SeqOps;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005mcA\u0002\n\u0014\u0003\u0003)2\u0004\u0003\u00051\u0001\t\u0005\t\u0015!\u00032\u0011!)\u0004A!a\u0001\n\u00031\u0004\u0002C%\u0001\u0005\u0003\u0007I\u0011\u0001&\t\u0011!\u0003!\u0011!Q!\n]B\u0001\u0002\u0015\u0001\u0003\u0002\u0003\u0006I!\u0015\u0005\t)\u0002\u0011\u0019\u0011)A\u0006+\")1\f\u0001C\u00019\"9\u0001\u000e\u0001b\u0001\n\u0003J\u0007B\u00029\u0001A\u0003%!\u000eC\u0003r\u0001\u0011\u0005#\u000fC\u0003z\u0001\u0011\u0005#\u0010C\u0004\u0002\u000e\u0001!\t%a\u0004\b\u0015\u0005E1#!A\t\u0002U\t\u0019BB\u0005\u0013'\u0005\u0005\t\u0012A\u000b\u0002\u0016!11L\u0004C\u0001\u0003[A\u0011\"a\f\u000f#\u0003%\t!!\r\t\u0013\u0005-c\"!A\u0005\n\u00055#a\u0006.jaB,G\rU1si&$\u0018n\u001c8t\u0005\u0006\u001cXM\u0015#E\u0015\t!R#A\u0002sI\u0012T!AF\f\u0002\u000bM\u0004\u0018M]6\u000b\u0005aI\u0012AB1qC\u000eDWMC\u0001\u001b\u0003\ry'oZ\u000b\u00039\r\u001a\"\u0001A\u000f\u0011\u0007yy\u0012%D\u0001\u0014\u0013\t\u00013CA\u0002S\t\u0012\u0003\"AI\u0012\r\u0001\u0011)A\u0005\u0001b\u0001M\t\tak\u0001\u0001\u0012\u0005\u001dj\u0003C\u0001\u0015,\u001b\u0005I#\"\u0001\u0016\u0002\u000bM\u001c\u0017\r\\1\n\u00051J#a\u0002(pi\"Lgn\u001a\t\u0003Q9J!aL\u0015\u0003\u0007\u0005s\u00170\u0001\u0002tGB\u0011!gM\u0007\u0002+%\u0011A'\u0006\u0002\r'B\f'o[\"p]R,\u0007\u0010^\u0001\u0005e\u0012$7/F\u00018!\rA\u0004i\u0011\b\u0003syr!AO\u001f\u000e\u0003mR!\u0001P\u0013\u0002\rq\u0012xn\u001c;?\u0013\u0005Q\u0013BA *\u0003\u001d\u0001\u0018mY6bO\u0016L!!\u0011\"\u0003\u0007M+\u0017O\u0003\u0002@SA\u0012AI\u0012\t\u0004=})\u0005C\u0001\u0012G\t%9E!!A\u0001\u0002\u000b\u0005aEA\u0002`II\nQA\u001d3eg\u0002\n\u0001B\u001d3eg~#S-\u001d\u000b\u0003\u0017:\u0003\"\u0001\u000b'\n\u00055K#\u0001B+oSRDqaT\u0002\u0002\u0002\u0003\u0007q'A\u0002yIE\nQ\u0003\u001d:fg\u0016\u0014h/Z:QCJ$\u0018\u000e^5p]&tw\r\u0005\u0002)%&\u00111+\u000b\u0002\b\u0005>|G.Z1o\u0003))g/\u001b3f]\u000e,G%\r\t\u0004-f\u000bS\"A,\u000b\u0005aK\u0013a\u0002:fM2,7\r^\u0005\u00035^\u0013\u0001b\u00117bgN$\u0016mZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\tu\u0003\u0017m\u001a\u000b\u0003=~\u00032A\b\u0001\"\u0011\u0015!v\u0001q\u0001V\u0011\u0015\u0001t\u00011\u00012\u0011\u0015)t\u00011\u0001c!\rA\u0004i\u0019\u0019\u0003I\u001a\u00042AH\u0010f!\t\u0011c\rB\u0005HC\u0006\u0005\t\u0011!B\u0001M!9\u0001k\u0002I\u0001\u0002\u0004\t\u0016a\u00039beRLG/[8oKJ,\u0012A\u001b\t\u0004Q-l\u0017B\u00017*\u0005\u0019y\u0005\u000f^5p]B\u0011!G\\\u0005\u0003_V\u00111\u0002U1si&$\u0018n\u001c8fe\u0006a\u0001/\u0019:uSRLwN\\3sA\u0005iq-\u001a;QCJ$\u0018\u000e^5p]N,\u0012a\u001d\t\u0004QQ4\u0018BA;*\u0005\u0015\t%O]1z!\t\u0011t/\u0003\u0002y+\tI\u0001+\u0019:uSRLwN\\\u0001\u0016O\u0016$\bK]3gKJ\u0014X\r\u001a'pG\u0006$\u0018n\u001c8t)\rY\u0018\u0011\u0002\t\u0004q\u0001c\bcA?\u0002\u00049\u0011ap \t\u0003u%J1!!\u0001*\u0003\u0019\u0001&/\u001a3fM&!\u0011QAA\u0004\u0005\u0019\u0019FO]5oO*\u0019\u0011\u0011A\u0015\t\r\u0005-1\u00021\u0001w\u0003\u0005\u0019\u0018!E2mK\u0006\u0014H)\u001a9f]\u0012,gnY5fgR\t1*A\f[SB\u0004X\r\u001a)beRLG/[8og\n\u000b7/\u001a*E\tB\u0011aDD\n\u0006\u001d\u0005]\u0011Q\u0004\t\u0004Q\u0005e\u0011bAA\u000eS\t1\u0011I\\=SK\u001a\u0004B!a\b\u0002*5\u0011\u0011\u0011\u0005\u0006\u0005\u0003G\t)#\u0001\u0002j_*\u0011\u0011qE\u0001\u0005U\u00064\u0018-\u0003\u0003\u0002,\u0005\u0005\"\u0001D*fe&\fG.\u001b>bE2,GCAA\n\u0003m!C.Z:tS:LG\u000fJ4sK\u0006$XM\u001d\u0013eK\u001a\fW\u000f\u001c;%gU!\u00111GA%+\t\t)DK\u0002R\u0003oY#!!\u000f\u0011\t\u0005m\u0012QI\u0007\u0003\u0003{QA!a\u0010\u0002B\u0005IQO\\2iK\u000e\\W\r\u001a\u0006\u0004\u0003\u0007J\u0013AC1o]>$\u0018\r^5p]&!\u0011qIA\u001f\u0005E)hn\u00195fG.,GMV1sS\u0006t7-\u001a\u0003\u0006IA\u0011\rAJ\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u001f\u0002B!!\u0015\u0002X5\u0011\u00111\u000b\u0006\u0005\u0003+\n)#\u0001\u0003mC:<\u0017\u0002BA-\u0003'\u0012aa\u00142kK\u000e$\b"
)
public abstract class ZippedPartitionsBaseRDD extends RDD {
   private Seq rdds;
   private final Option partitioner;

   public static boolean $lessinit$greater$default$3() {
      return ZippedPartitionsBaseRDD$.MODULE$.$lessinit$greater$default$3();
   }

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
      int numParts = ((RDD)this.rdds().head()).partitions().length;
      if (!this.rdds().forall((rdd) -> BoxesRunTime.boxToBoolean($anonfun$getPartitions$1(numParts, rdd)))) {
         Seq var10002 = this.rdds();
         throw new IllegalArgumentException("Can't zip RDDs with unequal numbers of partitions: " + var10002.map((x$1) -> BoxesRunTime.boxToInteger($anonfun$getPartitions$2(x$1))));
      } else {
         return (Partition[]).MODULE$.tabulate(numParts, (i) -> $anonfun$getPartitions$3(this, BoxesRunTime.unboxToInt(i)), scala.reflect.ClassTag..MODULE$.apply(Partition.class));
      }
   }

   public Seq getPreferredLocations(final Partition s) {
      return ((ZippedPartitionsPartition)s).preferredLocations();
   }

   public void clearDependencies() {
      super.clearDependencies();
      this.rdds_$eq((Seq)null);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getPartitions$1(final int numParts$1, final RDD rdd) {
      return rdd.partitions().length == numParts$1;
   }

   // $FF: synthetic method
   public static final int $anonfun$getPartitions$2(final RDD x$1) {
      return x$1.partitions().length;
   }

   // $FF: synthetic method
   public static final ZippedPartitionsPartition $anonfun$getPartitions$3(final ZippedPartitionsBaseRDD $this, final int i) {
      Seq prefs = (Seq)$this.rdds().map((rdd) -> rdd.preferredLocations(rdd.partitions()[i]));
      Seq exactMatchLocations = (Seq)prefs.reduce((x, y) -> (Seq)x.intersect(y));
      Seq locs = !exactMatchLocations.isEmpty() ? exactMatchLocations : (Seq)((SeqOps)prefs.flatten(scala.Predef..MODULE$.$conforms())).distinct();
      return new ZippedPartitionsPartition(i, $this.rdds(), locs);
   }

   public ZippedPartitionsBaseRDD(final SparkContext sc, final Seq rdds, final boolean preservesPartitioning, final ClassTag evidence$1) {
      this.rdds = rdds;
      super(sc, (Seq)rdds.map(new Serializable() {
         private static final long serialVersionUID = 0L;

         public final OneToOneDependency apply(final RDD x) {
            return new OneToOneDependency(x);
         }
      }), evidence$1);
      this.partitioner = (Option)(preservesPartitioning ? this.firstParent(scala.reflect.ClassTag..MODULE$.Any()).partitioner() : scala.None..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
