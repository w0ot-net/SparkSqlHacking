package org.apache.spark.rdd;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.Dependency;
import org.apache.spark.InterruptibleIterator;
import org.apache.spark.OneToOneDependency;
import org.apache.spark.Partition;
import org.apache.spark.Partitioner;
import org.apache.spark.ShuffleDependency;
import org.apache.spark.ShuffleDependency$;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.annotation.DeveloperApi;
import org.apache.spark.executor.TempShuffleReadMetrics;
import org.apache.spark.serializer.Serializer;
import org.apache.spark.util.collection.CompactBuffer;
import org.apache.spark.util.collection.ExternalAppendOnlyMap;
import org.apache.spark.util.collection.ExternalAppendOnlyMap$;
import scala.Function1;
import scala.Function2;
import scala.MatchError;
import scala.Option;
import scala.Some;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rg\u0001B\u000b\u0017\u0001}A\u0001\"\u0013\u0001\u0003\u0002\u0004%\tA\u0013\u0005\tC\u0002\u0011\t\u0019!C\u0001E\"A1\u000b\u0001B\u0001B\u0003&1\n\u0003\u0005i\u0001\t\u0005\t\u0015!\u0003j\u0011!i\u0007AaA!\u0002\u0017q\u0007\"\u0002;\u0001\t\u0003)XABA\u0007\u0001\u0011\ty!\u0002\u0004\u0002 \u0001!\u0011\u0011E\u0003\u0007\u0003S\u0001A!a\u000b\t\u0013\u0005E\u0002\u00011A\u0005\n\u0005M\u0002\"CA \u0001\u0001\u0007I\u0011BA!\u0011!\t)\u0005\u0001Q!\n\u0005U\u0002bBA$\u0001\u0011\u0005\u0011\u0011\n\u0005\b\u0003\u001b\u0002A\u0011IA(\u0011\u001d\t\t\u0007\u0001C!\u0003GB\u0011\"!\u001c\u0001\u0005\u0004%\t%a\u001c\t\u0011\u0005]\u0004\u0001)A\u0005\u0003cBq!!\u001f\u0001\t\u0003\nY\bC\u0004\u0002 \u0002!I!!)\t\u000f\u0005E\u0006\u0001\"\u0011\u00024\na1i\\$s_V\u0004X\r\u001a*E\t*\u0011q\u0003G\u0001\u0004e\u0012$'BA\r\u001b\u0003\u0015\u0019\b/\u0019:l\u0015\tYB$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002;\u0005\u0019qN]4\u0004\u0001U\u0011\u0001%L\n\u0003\u0001\u0005\u00022AI\u0012&\u001b\u00051\u0012B\u0001\u0013\u0017\u0005\r\u0011F\t\u0012\t\u0005M%Zc'D\u0001(\u0015\u0005A\u0013!B:dC2\f\u0017B\u0001\u0016(\u0005\u0019!V\u000f\u001d7feA\u0011A&\f\u0007\u0001\t\u0015q\u0003A1\u00010\u0005\u0005Y\u0015C\u0001\u00194!\t1\u0013'\u0003\u00023O\t9aj\u001c;iS:<\u0007C\u0001\u00145\u0013\t)tEA\u0002B]f\u00042AJ\u001c:\u0013\tAtEA\u0003BeJ\f\u0017\u0010\r\u0002;\u000fB\u00191h\u0011$\u000f\u0005q\neBA\u001fA\u001b\u0005q$BA \u001f\u0003\u0019a$o\\8u}%\t\u0001&\u0003\u0002CO\u00059\u0001/Y2lC\u001e,\u0017B\u0001#F\u0005!IE/\u001a:bE2,'B\u0001\"(!\tas\tB\u0005I\u0001\u0005\u0005\t\u0011!B\u0001_\t\u0019q\f\n\u001b\u0002\tI$Gm]\u000b\u0002\u0017B\u00191\b\u0014(\n\u00055+%aA*fcB\u0012q*\u0015\t\u0004E\r\u0002\u0006C\u0001\u0017R\t%\u00116!!A\u0001\u0002\u000b\u0005\u0001LA\u0002`II\nQA\u001d3eg\u0002B#aA+\u0011\u0005\u00192\u0016BA,(\u0005%!(/\u00198tS\u0016tG/\u0005\u000213B\u0012!L\u0018\t\u0005Mm[S,\u0003\u0002]O\tA\u0001K]8ek\u000e$(\u0007\u0005\u0002-=\u0012Iq\fYA\u0001\u0002\u0003\u0015\ta\f\u0002\u0004?\u0012\u001aD!\u0003*\u0004\u0003\u0003\r\tQ!\u0001Y\u0003!\u0011H\rZ:`I\u0015\fHCA2g!\t1C-\u0003\u0002fO\t!QK\\5u\u0011\u001d9'!!AA\u0002-\u000b1\u0001\u001f\u00132\u0003\u0011\u0001\u0018M\u001d;\u0011\u0005)\\W\"\u0001\r\n\u00051D\"a\u0003)beRLG/[8oKJ\f!\"\u001a<jI\u0016t7-\u001a\u00132!\ry'oK\u0007\u0002a*\u0011\u0011oJ\u0001\be\u00164G.Z2u\u0013\t\u0019\bO\u0001\u0005DY\u0006\u001c8\u000fV1h\u0003\u0019a\u0014N\\5u}Q!a/_A\u0006)\t9\b\u0010E\u0002#\u0001-BQ!\u001c\u0004A\u00049DQ!\u0013\u0004A\u0002i\u00042a\u000f'|a\tah\u0010E\u0002#Gu\u0004\"\u0001\f@\u0005\u0013IK\u0018\u0011!A\u0001\u0006\u0003y\u0018c\u0001\u0019\u0002\u0002A\"\u00111AA\u0004!\u001513lKA\u0003!\ra\u0013q\u0001\u0003\u000b?\u0006%\u0011\u0011!A\u0001\u0006\u0003yC!\u0003*z\u0003\u0003\r\tQ!\u0001\u0000\u0011\u0015Ag\u00011\u0001j\u0005\u001d\u0019un\u0012:pkB\u0004R!!\u0005\u0002\u001cMj!!a\u0005\u000b\t\u0005U\u0011qC\u0001\u000bG>dG.Z2uS>t'bAA\r1\u0005!Q\u000f^5m\u0013\u0011\ti\"a\u0005\u0003\u001b\r{W\u000e]1di\n+hMZ3s\u00051\u0019un\u0012:pkB4\u0016\r\\;f!\u00151\u0013fMA\u0012!\r1\u0013QE\u0005\u0004\u0003O9#aA%oi\ny1i\\$s_V\u00048i\\7cS:,'\u000f\u0005\u0003'o\u00055\u0002cAA\u0018\u000f5\t\u0001!\u0001\u0006tKJL\u0017\r\\5{KJ,\"!!\u000e\u0011\t\u0005]\u00121H\u0007\u0003\u0003sQ1!!\r\u0019\u0013\u0011\ti$!\u000f\u0003\u0015M+'/[1mSj,'/\u0001\btKJL\u0017\r\\5{KJ|F%Z9\u0015\u0007\r\f\u0019\u0005\u0003\u0005h\u0017\u0005\u0005\t\u0019AA\u001b\u0003-\u0019XM]5bY&TXM\u001d\u0011\u0002\u001bM,GoU3sS\u0006d\u0017N_3s)\r9\u00181\n\u0005\b\u0003ci\u0001\u0019AA\u001b\u0003=9W\r\u001e#fa\u0016tG-\u001a8dS\u0016\u001cXCAA)!\u0011YD*a\u00151\t\u0005U\u0013Q\f\t\u0006U\u0006]\u00131L\u0005\u0004\u00033B\"A\u0003#fa\u0016tG-\u001a8dsB\u0019A&!\u0018\u0005\u0015\u0005}c\"!A\u0001\u0002\u000b\u0005qFA\u0002`IU\nQbZ3u!\u0006\u0014H/\u001b;j_:\u001cXCAA3!\u00111s'a\u001a\u0011\u0007)\fI'C\u0002\u0002la\u0011\u0011\u0002U1si&$\u0018n\u001c8\u0002\u0017A\f'\u000f^5uS>tWM]\u000b\u0003\u0003c\u0002BAJA:S&\u0019\u0011QO\u0014\u0003\tM{W.Z\u0001\ra\u0006\u0014H/\u001b;j_:,'\u000fI\u0001\bG>l\u0007/\u001e;f)\u0019\ti(!%\u0002\u0016B)1(a \u0002\u0004&\u0019\u0011\u0011Q#\u0003\u0011%#XM]1u_J\u0004RAJ\u0015,\u0003\u000b\u0003BAJ\u001c\u0002\bB\"\u0011\u0011RAG!\u0011Y4)a#\u0011\u00071\ni\t\u0002\u0006\u0002\u0010J\t\t\u0011!A\u0003\u0002=\u00121a\u0018\u0013:\u0011\u001d\t\u0019J\u0005a\u0001\u0003O\n\u0011a\u001d\u0005\b\u0003/\u0013\u0002\u0019AAM\u0003\u001d\u0019wN\u001c;fqR\u00042A[AN\u0013\r\ti\n\u0007\u0002\f)\u0006\u001c8nQ8oi\u0016DH/A\tde\u0016\fG/Z#yi\u0016\u0014h.\u00197NCB$B!a)\u0002.BI\u0011\u0011CASW\u0005%\u00161V\u0005\u0005\u0003O\u000b\u0019BA\u000bFqR,'O\\1m\u0003B\u0004XM\u001c3P]2LX*\u00199\u0011\u0007\u0005=\u0002\u0002E\u0002\u00020%Aq!a,\u0014\u0001\u0004\t\u0019#A\u0004ok6\u0014F\rZ:\u0002#\rdW-\u0019:EKB,g\u000eZ3oG&,7\u000fF\u0001dQ\r\u0001\u0011q\u0017\t\u0005\u0003s\u000by,\u0004\u0002\u0002<*\u0019\u0011Q\u0018\r\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002B\u0006m&\u0001\u0004#fm\u0016dw\u000e]3s\u0003BL\u0007"
)
public class CoGroupedRDD extends RDD {
   private transient Seq rdds;
   private final Partitioner part;
   private final ClassTag evidence$1;
   private Serializer serializer;
   private final Some partitioner;

   public Seq rdds() {
      return this.rdds;
   }

   public void rdds_$eq(final Seq x$1) {
      this.rdds = x$1;
   }

   private Serializer serializer() {
      return this.serializer;
   }

   private void serializer_$eq(final Serializer x$1) {
      this.serializer = x$1;
   }

   public CoGroupedRDD setSerializer(final Serializer serializer) {
      this.serializer_$eq(serializer);
      return this;
   }

   public Seq getDependencies() {
      return (Seq)this.rdds().map((rdd) -> {
         label14: {
            Option var10000 = rdd.partitioner();
            Some var2 = new Some(this.part);
            if (var10000 == null) {
               if (var2 == null) {
                  break label14;
               }
            } else if (var10000.equals(var2)) {
               break label14;
            }

            this.logDebug(() -> "Adding shuffle dependency with " + rdd);
            return new ShuffleDependency(rdd, this.part, this.serializer(), ShuffleDependency$.MODULE$.$lessinit$greater$default$4(), ShuffleDependency$.MODULE$.$lessinit$greater$default$5(), ShuffleDependency$.MODULE$.$lessinit$greater$default$6(), ShuffleDependency$.MODULE$.$lessinit$greater$default$7(), this.evidence$1, .MODULE$.Any(), .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(CompactBuffer.class)));
         }

         this.logDebug(() -> "Adding one-to-one dependency with " + rdd);
         return new OneToOneDependency(rdd);
      });
   }

   public Partition[] getPartitions() {
      Partition[] array = new Partition[this.part.numPartitions()];
      scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(array)).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> array[i] = new CoGroupPartition(i, (Option[])((IterableOnceOps)((IterableOps)this.rdds().zipWithIndex()).map((x0$1) -> {
            if (x0$1 != null) {
               RDD rdd = (RDD)x0$1._1();
               int j = x0$1._2$mcI$sp();
               Dependency var8 = (Dependency)this.dependencies().apply(j);
               return (Option)(var8 instanceof ShuffleDependency ? scala.None..MODULE$ : new Some(new NarrowCoGroupSplitDep(rdd, i, rdd.partitions()[i])));
            } else {
               throw new MatchError(x0$1);
            }
         })).toArray(.MODULE$.apply(Option.class))));
      return array;
   }

   public Some partitioner() {
      return this.partitioner;
   }

   public Iterator compute(final Partition s, final TaskContext context) {
      CoGroupPartition split = (CoGroupPartition)s;
      int numRdds = this.dependencies().length();
      ArrayBuffer rddIterators = new ArrayBuffer();
      ((IterableOps)this.dependencies().zipWithIndex()).withFilter((check$ifrefutable$1) -> BoxesRunTime.boxToBoolean($anonfun$compute$1(check$ifrefutable$1))).foreach((x$1) -> {
         if (x$1 != null) {
            Dependency dep = (Dependency)x$1._1();
            int depNum = x$1._2$mcI$sp();
            if (dep instanceof OneToOneDependency) {
               OneToOneDependency var10 = (OneToOneDependency)dep;
               Partition dependencyPartition = ((NarrowCoGroupSplitDep)split.narrowDeps()[depNum].get()).split();
               Iterator it = var10.rdd().iterator(dependencyPartition, context);
               return (ArrayBuffer)rddIterators.$plus$eq(new Tuple2(it, BoxesRunTime.boxToInteger(depNum)));
            } else if (dep instanceof ShuffleDependency) {
               ShuffleDependency var13 = (ShuffleDependency)dep;
               TempShuffleReadMetrics metrics = context.taskMetrics().createTempShuffleReadMetrics();
               Iterator it = SparkEnv$.MODULE$.get().shuffleManager().getReader(var13.shuffleHandle(), split.index(), split.index() + 1, context, metrics).read();
               return (ArrayBuffer)rddIterators.$plus$eq(new Tuple2(it, BoxesRunTime.boxToInteger(depNum)));
            } else {
               throw new MatchError(dep);
            }
         } else {
            throw new MatchError(x$1);
         }
      });
      ExternalAppendOnlyMap map = this.createExternalMap(numRdds);
      rddIterators.withFilter((check$ifrefutable$2) -> BoxesRunTime.boxToBoolean($anonfun$compute$3(check$ifrefutable$2))).foreach((x$2) -> {
         $anonfun$compute$4(map, x$2);
         return BoxedUnit.UNIT;
      });
      context.taskMetrics().incMemoryBytesSpilled(map.memoryBytesSpilled());
      context.taskMetrics().incDiskBytesSpilled(map.diskBytesSpilled());
      context.taskMetrics().incPeakExecutionMemory(map.peakMemoryUsedBytes());
      return new InterruptibleIterator(context, map.iterator());
   }

   private ExternalAppendOnlyMap createExternalMap(final int numRdds) {
      Function1 createCombiner = (value) -> {
         CompactBuffer[] newCombiner = (CompactBuffer[])scala.Array..MODULE$.fill(numRdds, () -> new CompactBuffer(.MODULE$.Any()), .MODULE$.apply(CompactBuffer.class));
         newCombiner[value._2$mcI$sp()].$plus$eq(value._1());
         return newCombiner;
      };
      Function2 mergeValue = (combiner, value) -> {
         combiner[value._2$mcI$sp()].$plus$eq(value._1());
         return combiner;
      };
      Function2 mergeCombiners = (combiner1, combiner2) -> {
         for(int depNum = 0; depNum < numRdds; ++depNum) {
            combiner1[depNum].$plus$plus$eq(combiner2[depNum]);
         }

         return combiner1;
      };
      return new ExternalAppendOnlyMap(createCombiner, mergeValue, mergeCombiners, ExternalAppendOnlyMap$.MODULE$.$lessinit$greater$default$4(), ExternalAppendOnlyMap$.MODULE$.$lessinit$greater$default$5(), ExternalAppendOnlyMap$.MODULE$.$lessinit$greater$default$6(), ExternalAppendOnlyMap$.MODULE$.$lessinit$greater$default$7());
   }

   public void clearDependencies() {
      super.clearDependencies();
      this.rdds_$eq((Seq)null);
   }

   // $FF: synthetic method
   public static final boolean $anonfun$compute$1(final Tuple2 check$ifrefutable$1) {
      return check$ifrefutable$1 != null;
   }

   // $FF: synthetic method
   public static final boolean $anonfun$compute$3(final Tuple2 check$ifrefutable$2) {
      return check$ifrefutable$2 != null;
   }

   // $FF: synthetic method
   public static final void $anonfun$compute$4(final ExternalAppendOnlyMap map$1, final Tuple2 x$2) {
      if (x$2 != null) {
         Iterator it = (Iterator)x$2._1();
         int depNum = x$2._2$mcI$sp();
         map$1.insertAll(it.map((pair) -> new Tuple2(pair._1(), new Tuple2(pair._2(), BoxesRunTime.boxToInteger(depNum)))));
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(x$2);
      }
   }

   public CoGroupedRDD(final Seq rdds, final Partitioner part, final ClassTag evidence$1) {
      this.rdds = rdds;
      this.part = part;
      this.evidence$1 = evidence$1;
      super(((RDD)rdds.head()).context(), scala.collection.immutable.Nil..MODULE$, .MODULE$.apply(Tuple2.class));
      this.serializer = SparkEnv$.MODULE$.get().serializer();
      this.partitioner = new Some(part);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
