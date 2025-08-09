package org.apache.spark.rdd;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.InterruptibleIterator;
import org.apache.spark.Partition;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import scala.collection.Iterator;
import scala.collection.Map;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055c!\u0002\t\u0012\u0001MI\u0002\u0002\u0003\u0018\u0001\u0005\u0003\u0005\u000b\u0011B\u0018\t\u0011M\u0002!Q1A\u0005\nQB\u0001\"\u0011\u0001\u0003\u0002\u0003\u0006I!\u000e\u0005\t\r\u0002\u0011\t\u0011)A\u0005\u000f\"A!\n\u0001B\u0001B\u0003%1\n\u0003\u0005[\u0001\t\r\t\u0015a\u0003\\\u0011\u0015\t\u0007\u0001\"\u0001c\u0011\u0015Q\u0007\u0001\"\u0011l\u0011\u0015\u0011\b\u0001\"\u0011t\u0011\u0015q\b\u0001\"\u0011\u0000\u000f\u001d\t\u0019!\u0005E\u0005\u0003\u000b1a\u0001E\t\t\n\u0005\u001d\u0001BB1\r\t\u0003\ty\u0002C\u0004\u0002\"1!\t!a\t\t\u0013\u0005uB\"!A\u0005\n\u0005}\"!\u0006)be\u0006dG.\u001a7D_2dWm\u0019;j_:\u0014F\t\u0012\u0006\u0003%M\t1A\u001d3e\u0015\t!R#A\u0003ta\u0006\u00148N\u0003\u0002\u0017/\u00051\u0011\r]1dQ\u0016T\u0011\u0001G\u0001\u0004_J<WC\u0001\u000e\"'\t\u00011\u0004E\u0002\u001d;}i\u0011!E\u0005\u0003=E\u00111A\u0015#E!\t\u0001\u0013\u0005\u0004\u0001\u0005\u000b\t\u0002!\u0019\u0001\u0013\u0003\u0003Q\u001b\u0001!\u0005\u0002&WA\u0011a%K\u0007\u0002O)\t\u0001&A\u0003tG\u0006d\u0017-\u0003\u0002+O\t9aj\u001c;iS:<\u0007C\u0001\u0014-\u0013\tisEA\u0002B]f\f!a]2\u0011\u0005A\nT\"A\n\n\u0005I\u001a\"\u0001D*qCJ\\7i\u001c8uKb$\u0018\u0001\u00023bi\u0006,\u0012!\u000e\t\u0004myzbBA\u001c=\u001d\tA4(D\u0001:\u0015\tQ4%\u0001\u0004=e>|GOP\u0005\u0002Q%\u0011QhJ\u0001\ba\u0006\u001c7.Y4f\u0013\ty\u0004IA\u0002TKFT!!P\u0014\u0002\u000b\u0011\fG/\u0019\u0011)\u0005\r\u0019\u0005C\u0001\u0014E\u0013\t)uEA\u0005ue\u0006t7/[3oi\u0006Ia.^7TY&\u001cWm\u001d\t\u0003M!K!!S\u0014\u0003\u0007%sG/A\u0007m_\u000e\fG/[8o!J,gm\u001d\t\u0005\u0019>;\u0015+D\u0001N\u0015\tqu%\u0001\u0006d_2dWm\u0019;j_:L!\u0001U'\u0003\u00075\u000b\u0007\u000fE\u00027}I\u0003\"aU,\u000f\u0005Q+\u0006C\u0001\u001d(\u0013\t1v%\u0001\u0004Qe\u0016$WMZ\u0005\u00031f\u0013aa\u0015;sS:<'B\u0001,(\u0003))g/\u001b3f]\u000e,GE\r\t\u00049~{R\"A/\u000b\u0005y;\u0013a\u0002:fM2,7\r^\u0005\u0003Av\u0013\u0001b\u00117bgN$\u0016mZ\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000b\r4w\r[5\u0015\u0005\u0011,\u0007c\u0001\u000f\u0001?!)!l\u0002a\u00027\")af\u0002a\u0001_!)1g\u0002a\u0001k!)ai\u0002a\u0001\u000f\")!j\u0002a\u0001\u0017\u0006iq-\u001a;QCJ$\u0018\u000e^5p]N,\u0012\u0001\u001c\t\u0004M5|\u0017B\u00018(\u0005\u0015\t%O]1z!\t\u0001\u0004/\u0003\u0002r'\tI\u0001+\u0019:uSRLwN\\\u0001\bG>l\u0007/\u001e;f)\r!x/\u001f\t\u0004mU|\u0012B\u0001<A\u0005!IE/\u001a:bi>\u0014\b\"\u0002=\n\u0001\u0004y\u0017!A:\t\u000biL\u0001\u0019A>\u0002\u000f\r|g\u000e^3yiB\u0011\u0001\u0007`\u0005\u0003{N\u00111\u0002V1tW\u000e{g\u000e^3yi\u0006)r-\u001a;Qe\u00164WM\u001d:fI2{7-\u0019;j_:\u001cHcA)\u0002\u0002!)\u0001P\u0003a\u0001_\u0006)\u0002+\u0019:bY2,GnQ8mY\u0016\u001cG/[8o%\u0012#\u0005C\u0001\u000f\r'\u0015a\u0011\u0011BA\b!\r1\u00131B\u0005\u0004\u0003\u001b9#AB!osJ+g\r\u0005\u0003\u0002\u0012\u0005mQBAA\n\u0015\u0011\t)\"a\u0006\u0002\u0005%|'BAA\r\u0003\u0011Q\u0017M^1\n\t\u0005u\u00111\u0003\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u000b\u0003\u0003\u000b\tQa\u001d7jG\u0016,B!!\n\u00020Q1\u0011qEA\u001c\u0003w!B!!\u000b\u00022A!aGPA\u0016!\u00111d(!\f\u0011\u0007\u0001\ny\u0003B\u0003#\u001d\t\u0007A\u0005C\u0005\u000249\t\t\u0011q\u0001\u00026\u0005QQM^5eK:\u001cW\rJ\u001a\u0011\tq{\u0016Q\u0006\u0005\b\u0003sq\u0001\u0019AA\u0016\u0003\r\u0019X-\u001d\u0005\u0006\r:\u0001\raR\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003\u0003\u0002B!a\u0011\u0002J5\u0011\u0011Q\t\u0006\u0005\u0003\u000f\n9\"\u0001\u0003mC:<\u0017\u0002BA&\u0003\u000b\u0012aa\u00142kK\u000e$\b"
)
public class ParallelCollectionRDD extends RDD {
   private final transient Seq data;
   private final int numSlices;
   private final Map locationPrefs;
   private final ClassTag evidence$2;

   public static Seq slice(final Seq seq, final int numSlices, final ClassTag evidence$3) {
      return ParallelCollectionRDD$.MODULE$.slice(seq, numSlices, evidence$3);
   }

   private Seq data() {
      return this.data;
   }

   public Partition[] getPartitions() {
      Seq[] slices = (Seq[])ParallelCollectionRDD$.MODULE$.slice(this.data(), this.numSlices, this.evidence$2).toArray(.MODULE$.apply(Seq.class));
      return (Partition[])scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps((Object[])slices)).map((i) -> $anonfun$getPartitions$1(this, slices, BoxesRunTime.unboxToInt(i))).toArray(.MODULE$.apply(Partition.class));
   }

   public Iterator compute(final Partition s, final TaskContext context) {
      return new InterruptibleIterator(context, ((ParallelCollectionPartition)s).iterator());
   }

   public Seq getPreferredLocations(final Partition s) {
      return (Seq)this.locationPrefs.getOrElse(BoxesRunTime.boxToInteger(s.index()), () -> scala.collection.immutable.Nil..MODULE$);
   }

   // $FF: synthetic method
   public static final ParallelCollectionPartition $anonfun$getPartitions$1(final ParallelCollectionRDD $this, final Seq[] slices$1, final int i) {
      return new ParallelCollectionPartition((long)$this.id(), i, slices$1[i], $this.evidence$2);
   }

   public ParallelCollectionRDD(final SparkContext sc, final Seq data, final int numSlices, final Map locationPrefs, final ClassTag evidence$2) {
      super(sc, scala.collection.immutable.Nil..MODULE$, evidence$2);
      this.data = data;
      this.numSlices = numSlices;
      this.locationPrefs = locationPrefs;
      this.evidence$2 = evidence$2;
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
