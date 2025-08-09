package org.apache.spark.rdd;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Partition;
import org.apache.spark.RangeDependency;
import org.apache.spark.SparkContext;
import org.apache.spark.TaskContext;
import org.apache.spark.annotation.DeveloperApi;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOnce;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.collection.parallel.immutable.ParVector;
import scala.math.Numeric.IntIsIntegral.;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005%t!B\n\u0015\u0011\u0003ib!B\u0010\u0015\u0011\u0003\u0001\u0003\"B\u0018\u0002\t\u0003\u0001\u0004\"C\u0019\u0002\u0011\u000b\u0007I\u0011\u0001\f3\u0011\u001dY\u0014!!A\u0005\nq2Aa\b\u000b\u0001\u0007\"A1+\u0002B\u0001B\u0003%A\u000b\u0003\u0005Y\u000b\t\u0005\r\u0011\"\u0001Z\u0011!1WA!a\u0001\n\u00039\u0007\u0002C7\u0006\u0005\u0003\u0005\u000b\u0015\u0002.\t\u00119,!1!Q\u0001\f=DQaL\u0003\u0005\u0002UD\u0001b_\u0003C\u0002\u0013\u0005a\u0003 \u0005\b\u0003\u0003)\u0001\u0015!\u0003~\u0011\u001d\t\u0019!\u0002C!\u0003\u000bAq!a\u0005\u0006\t\u0003\n)\u0002C\u0004\u0002(\u0015!\t%!\u000b\t\u000f\u0005}R\u0001\"\u0011\u0002B!9\u0011qK\u0003\u0005B\u0005e\u0013\u0001C+oS>t'\u000b\u0012#\u000b\u0005U1\u0012a\u0001:eI*\u0011q\u0003G\u0001\u0006gB\f'o\u001b\u0006\u00033i\ta!\u00199bG\",'\"A\u000e\u0002\u0007=\u0014xm\u0001\u0001\u0011\u0005y\tQ\"\u0001\u000b\u0003\u0011Us\u0017n\u001c8S\t\u0012\u001b2!A\u0011(!\t\u0011S%D\u0001$\u0015\u0005!\u0013!B:dC2\f\u0017B\u0001\u0014$\u0005\u0019\te.\u001f*fMB\u0011\u0001&L\u0007\u0002S)\u0011!fK\u0001\u0003S>T\u0011\u0001L\u0001\u0005U\u00064\u0018-\u0003\u0002/S\ta1+\u001a:jC2L'0\u00192mK\u00061A(\u001b8jiz\"\u0012!H\u0001\u0019a\u0006\u0014H/\u001b;j_:,e/\u00197UCN\\7+\u001e9q_J$X#A\u001a\u0011\u0005QJT\"A\u001b\u000b\u0005Y:\u0014\u0001\u00039be\u0006dG.\u001a7\u000b\u0005a\u001a\u0013AC2pY2,7\r^5p]&\u0011!(\u000e\u0002\u0014\r>\u00148NS8j]R\u000b7o[*vaB|'\u000f^\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002{A\u0011a(Q\u0007\u0002\u007f)\u0011\u0001iK\u0001\u0005Y\u0006tw-\u0003\u0002C\u007f\t1qJ\u00196fGR,\"\u0001\u0012&\u0014\u0005\u0015)\u0005c\u0001\u0010G\u0011&\u0011q\t\u0006\u0002\u0004%\u0012#\u0005CA%K\u0019\u0001!QaS\u0003C\u00021\u0013\u0011\u0001V\t\u0003\u001bB\u0003\"A\t(\n\u0005=\u001b#a\u0002(pi\"Lgn\u001a\t\u0003EEK!AU\u0012\u0003\u0007\u0005s\u00170\u0001\u0002tGB\u0011QKV\u0007\u0002-%\u0011qK\u0006\u0002\r'B\f'o[\"p]R,\u0007\u0010^\u0001\u0005e\u0012$7/F\u0001[!\rY6-\u0012\b\u00039\u0006t!!\u00181\u000e\u0003yS!a\u0018\u000f\u0002\rq\u0012xn\u001c;?\u0013\u0005!\u0013B\u00012$\u0003\u001d\u0001\u0018mY6bO\u0016L!\u0001Z3\u0003\u0007M+\u0017O\u0003\u0002cG\u0005A!\u000f\u001a3t?\u0012*\u0017\u000f\u0006\u0002iWB\u0011!%[\u0005\u0003U\u000e\u0012A!\u00168ji\"9A\u000eCA\u0001\u0002\u0004Q\u0016a\u0001=%c\u0005)!\u000f\u001a3tA\u0005QQM^5eK:\u001cW\r\n\u001a\u0011\u0007A\u001c\b*D\u0001r\u0015\t\u00118%A\u0004sK\u001adWm\u0019;\n\u0005Q\f(\u0001C\"mCN\u001cH+Y4\u0015\u0007YL(\u0010\u0006\u0002xqB\u0019a$\u0002%\t\u000b9\\\u00019A8\t\u000bM[\u0001\u0019\u0001+\t\u000ba[\u0001\u0019\u0001.\u00025%\u001c\b+\u0019:uSRLwN\u001c'jgRLgn\u001a)be\u0006dG.\u001a7\u0016\u0003u\u0004\"A\t@\n\u0005}\u001c#a\u0002\"p_2,\u0017M\\\u0001\u001cSN\u0004\u0016M\u001d;ji&|g\u000eT5ti&tw\rU1sC2dW\r\u001c\u0011\u0002\u001b\u001d,G\u000fU1si&$\u0018n\u001c8t+\t\t9\u0001E\u0003#\u0003\u0013\ti!C\u0002\u0002\f\r\u0012Q!\u0011:sCf\u00042!VA\b\u0013\r\t\tB\u0006\u0002\n!\u0006\u0014H/\u001b;j_:\fqbZ3u\t\u0016\u0004XM\u001c3f]\u000eLWm]\u000b\u0003\u0003/\u0001BaW2\u0002\u001aA\"\u00111DA\u0012!\u0015)\u0016QDA\u0011\u0013\r\tyB\u0006\u0002\u000b\t\u0016\u0004XM\u001c3f]\u000eL\bcA%\u0002$\u0011Q\u0011QE\b\u0002\u0002\u0003\u0005)\u0011\u0001'\u0003\u0007}#\u0013'A\u0004d_6\u0004X\u000f^3\u0015\r\u0005-\u0012\u0011GA\u001b!\u0011Y\u0016Q\u0006%\n\u0007\u0005=RM\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0011\u001d\t\u0019\u0004\u0005a\u0001\u0003\u001b\t\u0011a\u001d\u0005\b\u0003o\u0001\u0002\u0019AA\u001d\u0003\u001d\u0019wN\u001c;fqR\u00042!VA\u001e\u0013\r\tiD\u0006\u0002\f)\u0006\u001c8nQ8oi\u0016DH/A\u000bhKR\u0004&/\u001a4feJ,G\rT8dCRLwN\\:\u0015\t\u0005\r\u0013Q\u000b\t\u00057\u000e\f)\u0005\u0005\u0003\u0002H\u0005=c\u0002BA%\u0003\u0017\u0002\"!X\u0012\n\u0007\u000553%\u0001\u0004Qe\u0016$WMZ\u0005\u0005\u0003#\n\u0019F\u0001\u0004TiJLgn\u001a\u0006\u0004\u0003\u001b\u001a\u0003bBA\u001a#\u0001\u0007\u0011QB\u0001\u0012G2,\u0017M\u001d#fa\u0016tG-\u001a8dS\u0016\u001cH#\u00015)\u0007\u0015\ti\u0006\u0005\u0003\u0002`\u0005\u0015TBAA1\u0015\r\t\u0019GF\u0001\u000bC:tw\u000e^1uS>t\u0017\u0002BA4\u0003C\u0012A\u0002R3wK2|\u0007/\u001a:Ba&\u0004"
)
public class UnionRDD extends RDD {
   private Seq rdds;
   private final ClassTag evidence$2;
   private final boolean isPartitionListingParallel;

   public Seq rdds() {
      return this.rdds;
   }

   public void rdds_$eq(final Seq x$1) {
      this.rdds = x$1;
   }

   public boolean isPartitionListingParallel() {
      return this.isPartitionListingParallel;
   }

   public Partition[] getPartitions() {
      Object var10000;
      if (this.isPartitionListingParallel()) {
         ParVector parArray = new ParVector(this.rdds().toVector());
         parArray.tasksupport_$eq(UnionRDD$.MODULE$.partitionEvalTaskSupport());
         var10000 = parArray;
      } else {
         var10000 = this.rdds();
      }

      Object parRDDs = var10000;
      Partition[] array = new Partition[BoxesRunTime.unboxToInt(((IterableOnce)parRDDs).iterator().map((x$1) -> BoxesRunTime.boxToInteger($anonfun$getPartitions$1(x$1))).sum(.MODULE$))];
      IntRef pos = IntRef.create(0);
      ((IterableOps)this.rdds().zipWithIndex()).withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$getPartitions$2(check$ifrefutable$1))).foreach((x$2) -> {
         $anonfun$getPartitions$3(this, array, pos, x$2);
         return BoxedUnit.UNIT;
      });
      return array;
   }

   public Seq getDependencies() {
      ArrayBuffer deps = new ArrayBuffer();
      IntRef pos = IntRef.create(0);
      this.rdds().foreach((rdd) -> {
         $anonfun$getDependencies$1(deps, pos, rdd);
         return BoxedUnit.UNIT;
      });
      return deps.toSeq();
   }

   public Iterator compute(final Partition s, final TaskContext context) {
      UnionPartition part = (UnionPartition)s;
      return this.parent(part.parentRddIndex(), this.evidence$2).iterator(part.parentPartition(), context);
   }

   public Seq getPreferredLocations(final Partition s) {
      return ((UnionPartition)s).preferredLocations();
   }

   public void clearDependencies() {
      super.clearDependencies();
      this.rdds_$eq((Seq)null);
   }

   // $FF: synthetic method
   public static final int $anonfun$getPartitions$1(final RDD x$1) {
      return x$1.partitions().length;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$getPartitions$2(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$getPartitions$4(final UnionRDD $this, final Partition[] array$1, final IntRef pos$1, final RDD rdd$1, final int rddIndex$1, final Partition split) {
      array$1[pos$1.elem] = new UnionPartition(pos$1.elem, rdd$1, rddIndex$1, split.index(), $this.evidence$2);
      ++pos$1.elem;
   }

   // $FF: synthetic method
   public static final void $anonfun$getPartitions$3(final UnionRDD $this, final Partition[] array$1, final IntRef pos$1, final Tuple2 x$2) {
      if (x$2 != null) {
         RDD rdd = (RDD)x$2._1();
         int rddIndex = x$2._2$mcI$sp();
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(rdd.partitions()), (split) -> {
            $anonfun$getPartitions$4($this, array$1, pos$1, rdd, rddIndex, split);
            return BoxedUnit.UNIT;
         });
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$2);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$getDependencies$1(final ArrayBuffer deps$1, final IntRef pos$2, final RDD rdd) {
      deps$1.$plus$eq(new RangeDependency(rdd, 0, pos$2.elem, rdd.partitions().length));
      pos$2.elem += rdd.partitions().length;
   }

   public UnionRDD(final SparkContext sc, final Seq rdds, final ClassTag evidence$2) {
      this.rdds = rdds;
      this.evidence$2 = evidence$2;
      super(sc, scala.collection.immutable.Nil..MODULE$, evidence$2);
      this.isPartitionListingParallel = this.rdds().length() > BoxesRunTime.unboxToInt(this.conf().get(org.apache.spark.internal.config.package$.MODULE$.RDD_PARALLEL_LISTING_THRESHOLD()));
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
