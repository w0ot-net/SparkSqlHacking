package org.apache.spark.rdd;

import java.lang.invoke.SerializedLambda;
import java.util.HashMap;
import org.apache.spark.Dependency;
import org.apache.spark.OneToOneDependency;
import org.apache.spark.Partition;
import org.apache.spark.Partitioner;
import org.apache.spark.ShuffleDependency;
import org.apache.spark.ShuffleDependency$;
import org.apache.spark.SparkEnv$;
import org.apache.spark.TaskContext;
import org.apache.spark.executor.TempShuffleReadMetrics;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product2;
import scala.Some;
import scala.Tuple2;
import scala.collection.IndexedSeqOps;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.immutable.;
import scala.collection.immutable.Seq;
import scala.collection.mutable.ArrayBuffer;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;
import scala.runtime.java8.JFunction1;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rd!\u0002\n\u0014\u0001UY\u0002\u0002\u0003\u001c\u0001\u0005\u0003\u0007I\u0011A\u001c\t\u0011\u0019\u0003!\u00111A\u0005\u0002\u001dC\u0001\"\u0010\u0001\u0003\u0002\u0003\u0006K\u0001\u000f\u0005\t\u001b\u0002\u0011\t\u0019!C\u0001\u001d\"A1\f\u0001BA\u0002\u0013\u0005A\f\u0003\u0005U\u0001\t\u0005\t\u0015)\u0003P\u0011!q\u0006A!A!\u0002\u0013y\u0006\u0002C2\u0001\u0005\u0007\u0005\u000b1\u00023\t\u0011)\u0004!1!Q\u0001\f-D\u0001\u0002\u001c\u0001\u0003\u0004\u0003\u0006Y!\u001c\u0005\u0006]\u0002!\ta\u001c\u0005\b\u0003\u0003\u0001A\u0011IA\u0002\u0011\u001d\tY\u0003\u0001C!\u0003[A\u0011\"a\u000f\u0001\u0005\u0004%\t%!\u0010\t\u0011\u0005\u0015\u0003\u0001)A\u0005\u0003\u007fAq!a\u0012\u0001\t\u0003\nI\u0005C\u0004\u0002`\u0001!\t%!\u0019\u0003\u001bM+(\r\u001e:bGR,GM\u0015#E\u0015\t!R#A\u0002sI\u0012T!AF\f\u0002\u000bM\u0004\u0018M]6\u000b\u0005aI\u0012AB1qC\u000eDWMC\u0001\u001b\u0003\ry'oZ\u000b\u00059%\"\u0014l\u0005\u0002\u0001;A\u0019adH\u0011\u000e\u0003MI!\u0001I\n\u0003\u0007I#E\t\u0005\u0003#K\u001d\u001aT\"A\u0012\u000b\u0003\u0011\nQa]2bY\u0006L!AJ\u0012\u0003\rQ+\b\u000f\\33!\tA\u0013\u0006\u0004\u0001\u0005\u000b)\u0002!\u0019\u0001\u0017\u0003\u0003-\u001b\u0001!\u0005\u0002.aA\u0011!EL\u0005\u0003_\r\u0012qAT8uQ&tw\r\u0005\u0002#c%\u0011!g\t\u0002\u0004\u0003:L\bC\u0001\u00155\t\u0015)\u0004A1\u0001-\u0005\u00051\u0016\u0001\u0002:eIF*\u0012\u0001\u000f\u0019\u0003sm\u00022AH\u0010;!\tA3\bB\u0005=\u0007\u0005\u0005\t\u0011!B\u0001\u0005\n\u0019q\fJ\u0019\u0002\u000bI$G-\r\u0011)\u0005\ry\u0004C\u0001\u0012A\u0013\t\t5EA\u0005ue\u0006t7/[3oiF\u0011Qf\u0011\t\u0005E\u0011;3'\u0003\u0002FG\tA\u0001K]8ek\u000e$('\u0001\u0005sI\u0012\ft\fJ3r)\tA5\n\u0005\u0002#\u0013&\u0011!j\t\u0002\u0005+:LG\u000fC\u0004M\u0005\u0005\u0005\t\u0019\u0001\u001d\u0002\u0007a$\u0013'\u0001\u0003sI\u0012\u0014T#A(1\u0005A\u0013\u0006c\u0001\u0010 #B\u0011\u0001F\u0015\u0003\n'\u001a\t\t\u0011!A\u0003\u0002Y\u00131a\u0018\u00133\u0003\u0015\u0011H\r\u001a\u001a!Q\t1q(\u0005\u0002./B!!\u0005R\u0014Y!\tA\u0013\fB\u0003[\u0001\t\u0007AFA\u0001X\u0003!\u0011H\r\u001a\u001a`I\u0015\fHC\u0001%^\u0011\u001daU!!AA\u0002=\u000bA\u0001]1siB\u0011\u0001-Y\u0007\u0002+%\u0011!-\u0006\u0002\f!\u0006\u0014H/\u001b;j_:,'/\u0001\u0006fm&$WM\\2fIE\u00022!\u001a5(\u001b\u00051'BA4$\u0003\u001d\u0011XM\u001a7fGRL!!\u001b4\u0003\u0011\rc\u0017m]:UC\u001e\f!\"\u001a<jI\u0016t7-\u001a\u00133!\r)\u0007nM\u0001\u000bKZLG-\u001a8dK\u0012\u001a\u0004cA3i1\u00061A(\u001b8jiz\"B\u0001];{\u007fR!\u0011O]:u!\u0015q\u0002aJ\u001aY\u0011\u0015\u00197\u0002q\u0001e\u0011\u0015Q7\u0002q\u0001l\u0011\u0015a7\u0002q\u0001n\u0011\u001514\u00021\u0001wa\t9\u0018\u0010E\u0002\u001f?a\u0004\"\u0001K=\u0005\u0013q*\u0018\u0011!A\u0001\u0006\u0003\u0011\u0005\"B'\f\u0001\u0004Y\bG\u0001?\u007f!\rqr$ \t\u0003Qy$\u0011b\u0015>\u0002\u0002\u0003\u0005)\u0011\u0001,\t\u000by[\u0001\u0019A0\u0002\u001f\u001d,G\u000fR3qK:$WM\\2jKN,\"!!\u0002\u0011\r\u0005\u001d\u0011qCA\u000f\u001d\u0011\tI!a\u0005\u000f\t\u0005-\u0011\u0011C\u0007\u0003\u0003\u001bQ1!a\u0004,\u0003\u0019a$o\\8u}%\tA%C\u0002\u0002\u0016\r\nq\u0001]1dW\u0006<W-\u0003\u0003\u0002\u001a\u0005m!aA*fc*\u0019\u0011QC\u00121\t\u0005}\u0011q\u0005\t\u0006A\u0006\u0005\u0012QE\u0005\u0004\u0003G)\"A\u0003#fa\u0016tG-\u001a8dsB\u0019\u0001&a\n\u0005\u0015\u0005%B\"!A\u0001\u0002\u000b\u0005AFA\u0002`IM\nQbZ3u!\u0006\u0014H/\u001b;j_:\u001cXCAA\u0018!\u0015\u0011\u0013\u0011GA\u001b\u0013\r\t\u0019d\t\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0004A\u0006]\u0012bAA\u001d+\tI\u0001+\u0019:uSRLwN\\\u0001\fa\u0006\u0014H/\u001b;j_:,'/\u0006\u0002\u0002@A!!%!\u0011`\u0013\r\t\u0019e\t\u0002\u0005'>lW-\u0001\u0007qCJ$\u0018\u000e^5p]\u0016\u0014\b%A\u0004d_6\u0004X\u000f^3\u0015\r\u0005-\u0013\u0011KA+!\u0015\t9!!\u0014\"\u0013\u0011\ty%a\u0007\u0003\u0011%#XM]1u_JDq!a\u0015\u0011\u0001\u0004\t)$A\u0001q\u0011\u001d\t9\u0006\u0005a\u0001\u00033\nqaY8oi\u0016DH\u000fE\u0002a\u00037J1!!\u0018\u0016\u0005-!\u0016m]6D_:$X\r\u001f;\u0002#\rdW-\u0019:EKB,g\u000eZ3oG&,7\u000fF\u0001I\u0001"
)
public class SubtractedRDD extends RDD {
   private transient RDD rdd1;
   private transient RDD rdd2;
   private final Partitioner part;
   private final ClassTag evidence$1;
   private final ClassTag evidence$2;
   private final ClassTag evidence$3;
   private final Some partitioner;

   public RDD rdd1() {
      return this.rdd1;
   }

   public void rdd1_$eq(final RDD x$1) {
      this.rdd1 = x$1;
   }

   public RDD rdd2() {
      return this.rdd2;
   }

   public void rdd2_$eq(final RDD x$1) {
      this.rdd2 = x$1;
   }

   public Seq getDependencies() {
      return new .colon.colon(this.rddDependency$1(this.rdd1(), this.evidence$1, this.evidence$2), new .colon.colon(this.rddDependency$1(this.rdd2(), this.evidence$1, this.evidence$3), scala.collection.immutable.Nil..MODULE$));
   }

   public Partition[] getPartitions() {
      Partition[] array = new Partition[this.part.numPartitions()];
      scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(array)).foreach$mVc$sp((JFunction1.mcVI.sp)(i) -> array[i] = new CoGroupPartition(i, (Option[])((IterableOnceOps)((IterableOps)(new .colon.colon(this.rdd1(), new .colon.colon(this.rdd2(), scala.collection.immutable.Nil..MODULE$))).zipWithIndex()).map((x0$1) -> {
            if (x0$1 != null) {
               RDD rdd = (RDD)x0$1._1();
               int j = x0$1._2$mcI$sp();
               Dependency var8 = (Dependency)this.dependencies().apply(j);
               return (Option)(var8 instanceof ShuffleDependency ? scala.None..MODULE$ : new Some(new NarrowCoGroupSplitDep(rdd, i, rdd.partitions()[i])));
            } else {
               throw new MatchError(x0$1);
            }
         })).toArray(scala.reflect.ClassTag..MODULE$.apply(Option.class))));
      return array;
   }

   public Some partitioner() {
      return this.partitioner;
   }

   public Iterator compute(final Partition p, final TaskContext context) {
      CoGroupPartition partition = (CoGroupPartition)p;
      HashMap map = new HashMap();
      this.integrate$1(0, (t) -> {
         $anonfun$compute$1(map, t);
         return BoxedUnit.UNIT;
      }, partition, context);
      this.integrate$1(1, (t) -> {
         $anonfun$compute$2(map, t);
         return BoxedUnit.UNIT;
      }, partition, context);
      return scala.jdk.CollectionConverters..MODULE$.MapHasAsScala(map).asScala().iterator().flatMap((t) -> ((IndexedSeqOps)t._2()).iterator().map((x$1) -> new Tuple2(t._1(), x$1)));
   }

   public void clearDependencies() {
      super.clearDependencies();
      this.rdd1_$eq((RDD)null);
      this.rdd2_$eq((RDD)null);
   }

   private final Dependency rddDependency$1(final RDD rdd, final ClassTag evidence$4, final ClassTag evidence$5) {
      label14: {
         Option var10000 = rdd.partitioner();
         Some var4 = new Some(this.part);
         if (var10000 == null) {
            if (var4 == null) {
               break label14;
            }
         } else if (var10000.equals(var4)) {
            break label14;
         }

         this.logDebug(() -> "Adding shuffle dependency with " + rdd);
         return new ShuffleDependency(rdd, this.part, ShuffleDependency$.MODULE$.$lessinit$greater$default$3(), ShuffleDependency$.MODULE$.$lessinit$greater$default$4(), ShuffleDependency$.MODULE$.$lessinit$greater$default$5(), ShuffleDependency$.MODULE$.$lessinit$greater$default$6(), ShuffleDependency$.MODULE$.$lessinit$greater$default$7(), evidence$4, evidence$5, scala.reflect.ClassTag..MODULE$.Any());
      }

      this.logDebug(() -> "Adding one-to-one dependency with " + rdd);
      return new OneToOneDependency(rdd);
   }

   private static final ArrayBuffer getSeq$1(final Object k, final HashMap map$1) {
      ArrayBuffer seq = (ArrayBuffer)map$1.get(k);
      if (seq != null) {
         return seq;
      } else {
         ArrayBuffer seq = new ArrayBuffer();
         map$1.put(k, seq);
         return seq;
      }
   }

   private final void integrate$1(final int depNum, final Function1 op, final CoGroupPartition partition$1, final TaskContext context$1) {
      Dependency var6 = (Dependency)this.dependencies().apply(depNum);
      if (var6 instanceof OneToOneDependency var7) {
         Partition dependencyPartition = ((NarrowCoGroupSplitDep)partition$1.narrowDeps()[depNum].get()).split();
         var7.rdd().iterator(dependencyPartition, context$1).foreach(op);
         BoxedUnit var12 = BoxedUnit.UNIT;
      } else if (var6 instanceof ShuffleDependency var9) {
         TempShuffleReadMetrics metrics = context$1.taskMetrics().createTempShuffleReadMetrics();
         Iterator iter = SparkEnv$.MODULE$.get().shuffleManager().getReader(var9.shuffleHandle(), partition$1.index(), partition$1.index() + 1, context$1, metrics).read();
         iter.foreach(op);
         BoxedUnit var10000 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(var6);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$compute$1(final HashMap map$1, final Product2 t) {
      getSeq$1(t._1(), map$1).$plus$eq(t._2());
   }

   // $FF: synthetic method
   public static final void $anonfun$compute$2(final HashMap map$1, final Product2 t) {
      map$1.remove(t._1());
   }

   public SubtractedRDD(final RDD rdd1, final RDD rdd2, final Partitioner part, final ClassTag evidence$1, final ClassTag evidence$2, final ClassTag evidence$3) {
      this.rdd1 = rdd1;
      this.rdd2 = rdd2;
      this.part = part;
      this.evidence$1 = evidence$1;
      this.evidence$2 = evidence$2;
      this.evidence$3 = evidence$3;
      super(rdd1.context(), scala.collection.immutable.Nil..MODULE$, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
      this.partitioner = new Some(part);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
