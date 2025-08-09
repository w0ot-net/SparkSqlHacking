package org.apache.spark.rdd;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Aggregator;
import org.apache.spark.MapOutputTrackerMaster;
import org.apache.spark.Partition;
import org.apache.spark.Partitioner;
import org.apache.spark.ShuffleDependency;
import org.apache.spark.ShuffleDependency$;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.executor.TempShuffleReadMetrics;
import org.apache.spark.serializer.Serializer;
import org.apache.spark.serializer.SerializerManager;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.Option.;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.math.Ordering;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005]h\u0001B\u0011#\u0001-B\u0001\"\u0012\u0001\u0003\u0002\u0004%\tA\u0012\u0005\t1\u0002\u0011\t\u0019!C\u00013\"AA\n\u0001B\u0001B\u0003&q\t\u0003\u0005`\u0001\t\u0005\t\u0015!\u0003a\u0011!!\u0007AaA!\u0002\u0017)\u0007\u0002C6\u0001\u0005\u0007\u0005\u000b1\u00027\t\u00115\u0004!1!Q\u0001\f9DQa\u001c\u0001\u0005\u0002ADq\u0001 \u0001A\u0002\u0013%Q\u0010C\u0005\u0002\u0010\u0001\u0001\r\u0011\"\u0003\u0002\u0012!9\u0011Q\u0003\u0001!B\u0013q\b\"CA\f\u0001\u0001\u0007I\u0011BA\r\u0011%\t)\u0004\u0001a\u0001\n\u0013\t9\u0004\u0003\u0005\u0002<\u0001\u0001\u000b\u0015BA\u000e\u0011%\ti\u0004\u0001a\u0001\n\u0013\ty\u0004C\u0005\u0002J\u0001\u0001\r\u0011\"\u0003\u0002L!A\u0011q\n\u0001!B\u0013\t\t\u0005C\u0005\u0002R\u0001\u0001\r\u0011\"\u0003\u0002T!I\u00111\f\u0001A\u0002\u0013%\u0011Q\f\u0005\t\u0003C\u0002\u0001\u0015)\u0003\u0002V!9\u00111\r\u0001\u0005\u0002\u0005\u0015\u0004bBA5\u0001\u0011\u0005\u00111\u000e\u0005\b\u0003_\u0002A\u0011AA9\u0011\u001d\t)\b\u0001C\u0001\u0003oBq!a\u001f\u0001\t\u0003\ni\bC\u0005\u0002\u0014\u0002\u0011\r\u0011\"\u0011\u0002\u0016\"A\u0011Q\u0014\u0001!\u0002\u0013\t9\nC\u0004\u0002 \u0002!\t%!)\t\u000f\u0005=\u0006\u0001\"\u0015\u00022\"9\u0011\u0011\u001a\u0001\u0005B\u0005-\u0007bBAq\u0001\u0011\u0005\u00131\u001d\u0005\t\u0003K\u0004A\u0011\t\u0013\u0002h\nY1\u000b[;gM2,GM\u0015#E\u0015\t\u0019C%A\u0002sI\u0012T!!\n\u0014\u0002\u000bM\u0004\u0018M]6\u000b\u0005\u001dB\u0013AB1qC\u000eDWMC\u0001*\u0003\ry'oZ\u0002\u0001+\u0011a\u0013HV\"\u0014\u0005\u0001i\u0003c\u0001\u00180c5\t!%\u0003\u00021E\t\u0019!\u000b\u0012#\u0011\tI*tGQ\u0007\u0002g)\tA'A\u0003tG\u0006d\u0017-\u0003\u00027g\t1A+\u001e9mKJ\u0002\"\u0001O\u001d\r\u0001\u0011)!\b\u0001b\u0001w\t\t1*\u0005\u0002=\u007fA\u0011!'P\u0005\u0003}M\u0012qAT8uQ&tw\r\u0005\u00023\u0001&\u0011\u0011i\r\u0002\u0004\u0003:L\bC\u0001\u001dD\t\u0015!\u0005A1\u0001<\u0005\u0005\u0019\u0015\u0001\u00029sKZ,\u0012a\u0012\u0019\u0003\u0011*\u00032AL\u0018J!\tA$\nB\u0005L\u0007\u0005\u0005\t\u0011!B\u0001#\n\u0019q\fJ\u0019\u0002\u000bA\u0014XM\u001e\u0011)\u0005\rq\u0005C\u0001\u001aP\u0013\t\u00016GA\u0005ue\u0006t7/[3oiF\u0011AH\u0015\t\u0005eM;T+\u0003\u0002Ug\tA\u0001K]8ek\u000e$(\u0007\u0005\u00029-\u0012)q\u000b\u0001b\u0001w\t\ta+\u0001\u0005qe\u00164x\fJ3r)\tQV\f\u0005\u000237&\u0011Al\r\u0002\u0005+:LG\u000fC\u0004_\u0005\u0005\u0005\t\u0019A$\u0002\u0007a$\u0013'\u0001\u0003qCJ$\bCA1c\u001b\u0005!\u0013BA2%\u0005-\u0001\u0016M\u001d;ji&|g.\u001a:\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$\u0013\u0007E\u0002gS^j\u0011a\u001a\u0006\u0003QN\nqA]3gY\u0016\u001cG/\u0003\u0002kO\nA1\t\\1tgR\u000bw-\u0001\u0006fm&$WM\\2fII\u00022AZ5V\u0003))g/\u001b3f]\u000e,Ge\r\t\u0004M&\u0014\u0015A\u0002\u001fj]&$h\bF\u0002rmn$BA]:ukB)a\u0006A\u001cV\u0005\")A\r\u0003a\u0002K\")1\u000e\u0003a\u0002Y\")Q\u000e\u0003a\u0002]\")Q\t\u0003a\u0001oB\u0012\u0001P\u001f\t\u0004]=J\bC\u0001\u001d{\t%Ye/!A\u0001\u0002\u000b\u0005\u0011\u000bC\u0003`\u0011\u0001\u0007\u0001-A\fvg\u0016\u00148\u000b]3dS\u001aLW\rZ*fe&\fG.\u001b>feV\ta\u0010\u0005\u00033\u007f\u0006\r\u0011bAA\u0001g\t1q\n\u001d;j_:\u0004B!!\u0002\u0002\f5\u0011\u0011q\u0001\u0006\u0004\u0003\u0013!\u0013AC:fe&\fG.\u001b>fe&!\u0011QBA\u0004\u0005)\u0019VM]5bY&TXM]\u0001\u001ckN,'o\u00159fG&4\u0017.\u001a3TKJL\u0017\r\\5{KJ|F%Z9\u0015\u0007i\u000b\u0019\u0002C\u0004_\u0015\u0005\u0005\t\u0019\u0001@\u00021U\u001cXM]*qK\u000eLg-[3e'\u0016\u0014\u0018.\u00197ju\u0016\u0014\b%A\u0006lKf|%\u000fZ3sS:<WCAA\u000e!\u0011\u0011t0!\b\u0011\u000b\u0005}\u0011qF\u001c\u000f\t\u0005\u0005\u00121\u0006\b\u0005\u0003G\tI#\u0004\u0002\u0002&)\u0019\u0011q\u0005\u0016\u0002\rq\u0012xn\u001c;?\u0013\u0005!\u0014bAA\u0017g\u00059\u0001/Y2lC\u001e,\u0017\u0002BA\u0019\u0003g\u0011\u0001b\u0014:eKJLgn\u001a\u0006\u0004\u0003[\u0019\u0014aD6fs>\u0013H-\u001a:j]\u001e|F%Z9\u0015\u0007i\u000bI\u0004\u0003\u0005_\u001b\u0005\u0005\t\u0019AA\u000e\u00031YW-_(sI\u0016\u0014\u0018N\\4!\u0003)\twm\u001a:fO\u0006$xN]\u000b\u0003\u0003\u0003\u0002BAM@\u0002DA1\u0011-!\u00128+\nK1!a\u0012%\u0005)\tum\u001a:fO\u0006$xN]\u0001\u000fC\u001e<'/Z4bi>\u0014x\fJ3r)\rQ\u0016Q\n\u0005\t=B\t\t\u00111\u0001\u0002B\u0005Y\u0011mZ4sK\u001e\fGo\u001c:!\u00039i\u0017\r]*jI\u0016\u001cu.\u001c2j]\u0016,\"!!\u0016\u0011\u0007I\n9&C\u0002\u0002ZM\u0012qAQ8pY\u0016\fg.\u0001\nnCB\u001c\u0016\u000eZ3D_6\u0014\u0017N\\3`I\u0015\fHc\u0001.\u0002`!AalEA\u0001\u0002\u0004\t)&A\bnCB\u001c\u0016\u000eZ3D_6\u0014\u0017N\\3!\u00035\u0019X\r^*fe&\fG.\u001b>feR\u0019!/a\u001a\t\u000f\u0005%Q\u00031\u0001\u0002\u0004\u0005q1/\u001a;LKf|%\u000fZ3sS:<Gc\u0001:\u0002n!9\u0011q\u0003\fA\u0002\u0005u\u0011!D:fi\u0006;wM]3hCR|'\u000fF\u0002s\u0003gBq!!\u0010\u0018\u0001\u0004\t\u0019%A\ttKRl\u0015\r]*jI\u0016\u001cu.\u001c2j]\u0016$2A]A=\u0011\u001d\t\t\u0006\u0007a\u0001\u0003+\nqbZ3u\t\u0016\u0004XM\u001c3f]\u000eLWm]\u000b\u0003\u0003\u007f\u0002b!a\b\u0002\u0002\u0006\u0015\u0015\u0002BAB\u0003g\u00111aU3ra\u0011\t9)a$\u0011\u000b\u0005\fI)!$\n\u0007\u0005-EE\u0001\u0006EKB,g\u000eZ3oGf\u00042\u0001OAH\t)\t\t*GA\u0001\u0002\u0003\u0015\ta\u000f\u0002\u0004?\u0012\u0012\u0014a\u00039beRLG/[8oKJ,\"!a&\u0011\tI\nI\nY\u0005\u0004\u00037\u001b$\u0001B*p[\u0016\fA\u0002]1si&$\u0018n\u001c8fe\u0002\nQbZ3u!\u0006\u0014H/\u001b;j_:\u001cXCAAR!\u0015\u0011\u0014QUAU\u0013\r\t9k\r\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0004C\u0006-\u0016bAAWI\tI\u0001+\u0019:uSRLwN\\\u0001\u0016O\u0016$\bK]3gKJ\u0014X\r\u001a'pG\u0006$\u0018n\u001c8t)\u0011\t\u0019,!2\u0011\r\u0005}\u0011\u0011QA[!\u0011\t9,a0\u000f\t\u0005e\u00161\u0018\t\u0004\u0003G\u0019\u0014bAA_g\u00051\u0001K]3eK\u001aLA!!1\u0002D\n11\u000b\u001e:j]\u001eT1!!04\u0011\u001d\t9-\ba\u0001\u0003S\u000b\u0011\u0002]1si&$\u0018n\u001c8\u0002\u000f\r|W\u000e];uKR1\u0011QZAj\u0003/\u0004R!a\b\u0002PFJA!!5\u00024\tA\u0011\n^3sCR|'\u000fC\u0004\u0002Vz\u0001\r!!+\u0002\u000bM\u0004H.\u001b;\t\u000f\u0005eg\u00041\u0001\u0002\\\u000691m\u001c8uKb$\bcA1\u0002^&\u0019\u0011q\u001c\u0013\u0003\u0017Q\u000b7o[\"p]R,\u0007\u0010^\u0001\u0012G2,\u0017M\u001d#fa\u0016tG-\u001a8dS\u0016\u001cH#\u0001.\u0002\u0013%\u001c()\u0019:sS\u0016\u0014HCAA+Q\r\u0001\u00111\u001e\t\u0005\u0003[\f\u00190\u0004\u0002\u0002p*\u0019\u0011\u0011\u001f\u0013\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002v\u0006=(\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0007"
)
public class ShuffledRDD extends RDD {
   private transient RDD prev;
   private final Partitioner part;
   private final ClassTag evidence$1;
   private final ClassTag evidence$2;
   private final ClassTag evidence$3;
   private Option userSpecifiedSerializer;
   private Option keyOrdering;
   private Option aggregator;
   private boolean mapSideCombine;
   private final Some partitioner;

   public RDD prev() {
      return this.prev;
   }

   public void prev_$eq(final RDD x$1) {
      this.prev = x$1;
   }

   private Option userSpecifiedSerializer() {
      return this.userSpecifiedSerializer;
   }

   private void userSpecifiedSerializer_$eq(final Option x$1) {
      this.userSpecifiedSerializer = x$1;
   }

   private Option keyOrdering() {
      return this.keyOrdering;
   }

   private void keyOrdering_$eq(final Option x$1) {
      this.keyOrdering = x$1;
   }

   private Option aggregator() {
      return this.aggregator;
   }

   private void aggregator_$eq(final Option x$1) {
      this.aggregator = x$1;
   }

   private boolean mapSideCombine() {
      return this.mapSideCombine;
   }

   private void mapSideCombine_$eq(final boolean x$1) {
      this.mapSideCombine = x$1;
   }

   public ShuffledRDD setSerializer(final Serializer serializer) {
      this.userSpecifiedSerializer_$eq(.MODULE$.apply(serializer));
      return this;
   }

   public ShuffledRDD setKeyOrdering(final Ordering keyOrdering) {
      this.keyOrdering_$eq(.MODULE$.apply(keyOrdering));
      return this;
   }

   public ShuffledRDD setAggregator(final Aggregator aggregator) {
      this.aggregator_$eq(.MODULE$.apply(aggregator));
      return this;
   }

   public ShuffledRDD setMapSideCombine(final boolean mapSideCombine) {
      this.mapSideCombine_$eq(mapSideCombine);
      return this;
   }

   public Seq getDependencies() {
      Serializer serializer = (Serializer)this.userSpecifiedSerializer().getOrElse(() -> {
         SerializerManager serializerManager = SparkEnv$.MODULE$.get().serializerManager();
         return this.mapSideCombine() ? serializerManager.getSerializer((ClassTag)scala.Predef..MODULE$.implicitly(this.evidence$1), (ClassTag)scala.Predef..MODULE$.implicitly(this.evidence$3)) : serializerManager.getSerializer((ClassTag)scala.Predef..MODULE$.implicitly(this.evidence$1), (ClassTag)scala.Predef..MODULE$.implicitly(this.evidence$2));
      });
      return new scala.collection.immutable..colon.colon(new ShuffleDependency(this.prev(), this.part, serializer, this.keyOrdering(), this.aggregator(), this.mapSideCombine(), ShuffleDependency$.MODULE$.$lessinit$greater$default$7(), this.evidence$1, this.evidence$2, this.evidence$3), scala.collection.immutable.Nil..MODULE$);
   }

   public Some partitioner() {
      return this.partitioner;
   }

   public Partition[] getPartitions() {
      return (Partition[])scala.Array..MODULE$.tabulate(this.part.numPartitions(), (i) -> $anonfun$getPartitions$1(BoxesRunTime.unboxToInt(i)), scala.reflect.ClassTag..MODULE$.apply(Partition.class));
   }

   public Seq getPreferredLocations(final Partition partition) {
      MapOutputTrackerMaster tracker = (MapOutputTrackerMaster)SparkEnv$.MODULE$.get().mapOutputTracker();
      ShuffleDependency dep = (ShuffleDependency)this.dependencies().head();
      return tracker.getPreferredLocationsForShuffle(dep, partition.index());
   }

   public Iterator compute(final Partition split, final TaskContext context) {
      ShuffleDependency dep = (ShuffleDependency)this.dependencies().head();
      TempShuffleReadMetrics metrics = context.taskMetrics().createTempShuffleReadMetrics();
      return SparkEnv$.MODULE$.get().shuffleManager().getReader(dep.shuffleHandle(), split.index(), split.index() + 1, context, metrics).read();
   }

   public void clearDependencies() {
      super.clearDependencies();
      this.prev_$eq((RDD)null);
   }

   public boolean isBarrier() {
      return false;
   }

   // $FF: synthetic method
   public static final ShuffledRDDPartition $anonfun$getPartitions$1(final int i) {
      return new ShuffledRDDPartition(i);
   }

   public ShuffledRDD(final RDD prev, final Partitioner part, final ClassTag evidence$1, final ClassTag evidence$2, final ClassTag evidence$3) {
      this.prev = prev;
      this.part = part;
      this.evidence$1 = evidence$1;
      this.evidence$2 = evidence$2;
      this.evidence$3 = evidence$3;
      super(prev.context(), scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      this.userSpecifiedSerializer = scala.None..MODULE$;
      this.keyOrdering = scala.None..MODULE$;
      this.aggregator = scala.None..MODULE$;
      this.mapSideCombine = false;
      this.partitioner = new Some(part);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
