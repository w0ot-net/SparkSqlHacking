package org.apache.spark.rdd;

import java.lang.invoke.SerializedLambda;
import java.util.Random;
import org.apache.spark.Partition;
import org.apache.spark.TaskContext;
import org.apache.spark.util.random.RandomSampler;
import scala.Enumeration;
import scala.Option;
import scala.collection.Iterator;
import scala.collection.ArrayOps.;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ee!\u0002\u000b\u0016\u0001]i\u0002\u0002\u0003\u001a\u0001\u0005\u0003\u0005\u000b\u0011B\u001a\t\u0011]\u0002!\u0011!Q\u0001\naB\u0001\u0002\u0011\u0001\u0003\u0002\u0003\u0006I!\u0011\u0005\t\t\u0002\u0011)\u0019!C\u0005\u000b\"A\u0011\n\u0001B\u0001B\u0003%a\t\u0003\u0005O\u0001\t\r\t\u0015a\u0003P\u0011!)\u0006AaA!\u0002\u00171\u0006\"B,\u0001\t\u0003A\u0006bB1\u0001\u0005\u0004%\tE\u0019\u0005\u0007U\u0002\u0001\u000b\u0011B2\t\u000b1\u0004A\u0011I7\t\u000bQ\u0004A\u0011I;\t\u000f\u0005e\u0001\u0001\"\u0011\u0002\u001c!9\u0011\u0011\u0007\u0001\u0005R\u0005MrACA#+\u0005\u0005\t\u0012A\f\u0002H\u0019IA#FA\u0001\u0012\u00039\u0012\u0011\n\u0005\u0007/B!\t!!\u0019\t\u0013\u0005\r\u0004#%A\u0005\u0002\u0005\u0015\u0004\"CAA!\u0005\u0005I\u0011BAB\u0005]\u0001\u0016M\u001d;ji&|gn^5tKN\u000bW\u000e\u001d7fIJ#EI\u0003\u0002\u0017/\u0005\u0019!\u000f\u001a3\u000b\u0005aI\u0012!B:qCJ\\'B\u0001\u000e\u001c\u0003\u0019\t\u0007/Y2iK*\tA$A\u0002pe\u001e,2AH\u001b&'\t\u0001q\u0004E\u0002!C\rj\u0011!F\u0005\u0003EU\u00111A\u0015#E!\t!S\u0005\u0004\u0001\u0005\u000b\u0019\u0002!\u0019\u0001\u0015\u0003\u0003U\u001b\u0001!\u0005\u0002*_A\u0011!&L\u0007\u0002W)\tA&A\u0003tG\u0006d\u0017-\u0003\u0002/W\t9aj\u001c;iS:<\u0007C\u0001\u00161\u0013\t\t4FA\u0002B]f\fA\u0001\u001d:fmB\u0019\u0001%\t\u001b\u0011\u0005\u0011*D!\u0002\u001c\u0001\u0005\u0004A#!\u0001+\u0002\u000fM\fW\u000e\u001d7feB!\u0011H\u0010\u001b$\u001b\u0005Q$BA\u001e=\u0003\u0019\u0011\u0018M\u001c3p[*\u0011QhF\u0001\u0005kRLG.\u0003\u0002@u\ti!+\u00198e_6\u001c\u0016-\u001c9mKJ\fQ\u0003\u001d:fg\u0016\u0014h/Z:QCJ$\u0018\u000e^5p]&tw\r\u0005\u0002+\u0005&\u00111i\u000b\u0002\b\u0005>|G.Z1o\u0003\u0011\u0019X-\u001a3\u0016\u0003\u0019\u0003\"AK$\n\u0005![#\u0001\u0002'p]\u001e\fQa]3fI\u0002B#!B&\u0011\u0005)b\u0015BA',\u0005%!(/\u00198tS\u0016tG/\u0001\u0006fm&$WM\\2fIE\u00022\u0001U*5\u001b\u0005\t&B\u0001*,\u0003\u001d\u0011XM\u001a7fGRL!\u0001V)\u0003\u0011\rc\u0017m]:UC\u001e\f!\"\u001a<jI\u0016t7-\u001a\u00133!\r\u00016kI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u000bekfl\u00181\u0015\u0007i[F\f\u0005\u0003!\u0001Q\u001a\u0003\"\u0002(\t\u0001\by\u0005\"B+\t\u0001\b1\u0006\"\u0002\u001a\t\u0001\u0004\u0019\u0004\"B\u001c\t\u0001\u0004A\u0004\"\u0002!\t\u0001\u0004\t\u0005b\u0002#\t!\u0003\u0005\rAR\u0001\fa\u0006\u0014H/\u001b;j_:,'/F\u0001d!\rQCMZ\u0005\u0003K.\u0012aa\u00149uS>t\u0007CA4i\u001b\u00059\u0012BA5\u0018\u0005-\u0001\u0016M\u001d;ji&|g.\u001a:\u0002\u0019A\f'\u000f^5uS>tWM\u001d\u0011)\u0005)Y\u0015!D4fiB\u000b'\u000f^5uS>t7/F\u0001o!\rQs.]\u0005\u0003a.\u0012Q!\u0011:sCf\u0004\"a\u001a:\n\u0005M<\"!\u0003)beRLG/[8o\u0003U9W\r\u001e)sK\u001a,'O]3e\u0019>\u001c\u0017\r^5p]N$2A^A\u000b!\u00119x0!\u0002\u000f\u0005alhBA=}\u001b\u0005Q(BA>(\u0003\u0019a$o\\8u}%\tA&\u0003\u0002\u007fW\u00059\u0001/Y2lC\u001e,\u0017\u0002BA\u0001\u0003\u0007\u00111aU3r\u0015\tq8\u0006\u0005\u0003\u0002\b\u0005=a\u0002BA\u0005\u0003\u0017\u0001\"!_\u0016\n\u0007\u000551&\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003#\t\u0019B\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003\u001bY\u0003BBA\f\u0019\u0001\u0007\u0011/A\u0003ta2LG/A\u0004d_6\u0004X\u000f^3\u0015\r\u0005u\u00111EA\u0014!\u00119\u0018qD\u0012\n\t\u0005\u0005\u00121\u0001\u0002\t\u0013R,'/\u0019;pe\"1\u0011QE\u0007A\u0002E\fqa\u001d9mSRLe\u000eC\u0004\u0002*5\u0001\r!a\u000b\u0002\u000f\r|g\u000e^3yiB\u0019q-!\f\n\u0007\u0005=rCA\u0006UCN\\7i\u001c8uKb$\u0018aG4fi>+H\u000f];u\t\u0016$XM]7j]&\u001cH/[2MKZ,G.\u0006\u0002\u00026A!\u0011qGA\u001f\u001d\r\u0001\u0013\u0011H\u0005\u0004\u0003w)\u0012A\u0005#fi\u0016\u0014X.\u001b8jgRL7\rT3wK2LA!a\u0010\u0002B\t)a+\u00197vK&\u0019\u00111I\u0016\u0003\u0017\u0015sW/\\3sCRLwN\\\u0001\u0018!\u0006\u0014H/\u001b;j_:<\u0018n]3TC6\u0004H.\u001a3S\t\u0012\u0003\"\u0001\t\t\u0014\u000bA\tY%!\u0015\u0011\u0007)\ni%C\u0002\u0002P-\u0012a!\u00118z%\u00164\u0007\u0003BA*\u0003;j!!!\u0016\u000b\t\u0005]\u0013\u0011L\u0001\u0003S>T!!a\u0017\u0002\t)\fg/Y\u0005\u0005\u0003?\n)F\u0001\u0007TKJL\u0017\r\\5{C\ndW\r\u0006\u0002\u0002H\u0005YB\u0005\\3tg&t\u0017\u000e\u001e\u0013he\u0016\fG/\u001a:%I\u00164\u0017-\u001e7uIQ*b!a\u001a\u0002~\u0005}TCAA5U\r1\u00151N\u0016\u0003\u0003[\u0002B!a\u001c\u0002z5\u0011\u0011\u0011\u000f\u0006\u0005\u0003g\n)(A\u0005v]\u000eDWmY6fI*\u0019\u0011qO\u0016\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002|\u0005E$!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0012)aG\u0005b\u0001Q\u0011)aE\u0005b\u0001Q\u0005aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\u0011\t\u0005\u0003\u000f\u000bi)\u0004\u0002\u0002\n*!\u00111RA-\u0003\u0011a\u0017M\\4\n\t\u0005=\u0015\u0011\u0012\u0002\u0007\u001f\nTWm\u0019;"
)
public class PartitionwiseSampledRDD extends RDD {
   private final RDD prev;
   private final RandomSampler sampler;
   private final transient long seed;
   private final ClassTag evidence$1;
   private final transient Option partitioner;

   public static long $lessinit$greater$default$4() {
      return PartitionwiseSampledRDD$.MODULE$.$lessinit$greater$default$4();
   }

   private long seed() {
      return this.seed;
   }

   public Option partitioner() {
      return this.partitioner;
   }

   public Partition[] getPartitions() {
      Random random = new Random(this.seed());
      return (Partition[]).MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(this.firstParent(this.evidence$1).partitions()), (x) -> new PartitionwiseSampledRDDPartition(x, random.nextLong()), scala.reflect.ClassTag..MODULE$.apply(Partition.class));
   }

   public Seq getPreferredLocations(final Partition split) {
      return this.firstParent(this.evidence$1).preferredLocations(((PartitionwiseSampledRDDPartition)split).prev());
   }

   public Iterator compute(final Partition splitIn, final TaskContext context) {
      PartitionwiseSampledRDDPartition split = (PartitionwiseSampledRDDPartition)splitIn;
      RandomSampler thisSampler = this.sampler.clone();
      thisSampler.setSeed(split.seed());
      return thisSampler.sample(this.firstParent(this.evidence$1).iterator(split.prev(), context));
   }

   public Enumeration.Value getOutputDeterministicLevel() {
      Enumeration.Value var10000 = this.prev.outputDeterministicLevel();
      Enumeration.Value var1 = DeterministicLevel$.MODULE$.UNORDERED();
      if (var10000 == null) {
         if (var1 == null) {
            return DeterministicLevel$.MODULE$.INDETERMINATE();
         }
      } else if (var10000.equals(var1)) {
         return DeterministicLevel$.MODULE$.INDETERMINATE();
      }

      return super.getOutputDeterministicLevel();
   }

   public PartitionwiseSampledRDD(final RDD prev, final RandomSampler sampler, final boolean preservesPartitioning, final long seed, final ClassTag evidence$1, final ClassTag evidence$2) {
      super(prev, evidence$2);
      this.prev = prev;
      this.sampler = sampler;
      this.seed = seed;
      this.evidence$1 = evidence$1;
      this.partitioner = (Option)(preservesPartitioning ? prev.partitioner() : scala.None..MODULE$);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
