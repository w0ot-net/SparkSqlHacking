package org.apache.spark.mllib.clustering;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Random;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors$;
import org.apache.spark.rdd.RDD;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;
import scala.collection.immutable.Set;
import scala.math.package.;
import scala.runtime.BoxesRunTime;
import scala.runtime.IntRef;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

public final class BisectingKMeans$ implements Serializable {
   public static final BisectingKMeans$ MODULE$ = new BisectingKMeans$();
   private static final long org$apache$spark$mllib$clustering$BisectingKMeans$$ROOT_INDEX = 1L;
   private static final long MAX_DIVISIBLE_CLUSTER_INDEX = 4611686018427387903L;
   private static final double org$apache$spark$mllib$clustering$BisectingKMeans$$LEVEL_LIMIT;

   static {
      org$apache$spark$mllib$clustering$BisectingKMeans$$LEVEL_LIMIT = .MODULE$.log10((double)Long.MAX_VALUE) / .MODULE$.log10((double)2.0F);
   }

   public long org$apache$spark$mllib$clustering$BisectingKMeans$$ROOT_INDEX() {
      return org$apache$spark$mllib$clustering$BisectingKMeans$$ROOT_INDEX;
   }

   private long MAX_DIVISIBLE_CLUSTER_INDEX() {
      return MAX_DIVISIBLE_CLUSTER_INDEX;
   }

   public double org$apache$spark$mllib$clustering$BisectingKMeans$$LEVEL_LIMIT() {
      return org$apache$spark$mllib$clustering$BisectingKMeans$$LEVEL_LIMIT;
   }

   public long org$apache$spark$mllib$clustering$BisectingKMeans$$leftChildIndex(final long index) {
      scala.Predef..MODULE$.require(index <= this.MAX_DIVISIBLE_CLUSTER_INDEX(), () -> "Child index out of bound: 2 * " + index + ".");
      return 2L * index;
   }

   public long org$apache$spark$mllib$clustering$BisectingKMeans$$rightChildIndex(final long index) {
      scala.Predef..MODULE$.require(index <= this.MAX_DIVISIBLE_CLUSTER_INDEX(), () -> "Child index out of bound: 2 * " + index + " + 1.");
      return 2L * index + 1L;
   }

   public long org$apache$spark$mllib$clustering$BisectingKMeans$$parentIndex(final long index) {
      return index / 2L;
   }

   public Map org$apache$spark$mllib$clustering$BisectingKMeans$$summarize(final int d, final RDD assignments, final DistanceMeasure distanceMeasure) {
      return scala.Predef..MODULE$.wrapRefArray(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(org.apache.spark.rdd.RDD..MODULE$.rddToPairRDDFunctions(assignments, scala.reflect.ClassTag..MODULE$.Long(), scala.reflect.ClassTag..MODULE$.apply(VectorWithNorm.class), scala.math.Ordering.Long..MODULE$).aggregateByKey(new BisectingKMeans.ClusterSummaryAggregator(d, distanceMeasure), (agg, v) -> agg.add(v), (agg1, agg2) -> agg1.merge(agg2), scala.reflect.ClassTag..MODULE$.apply(BisectingKMeans.ClusterSummaryAggregator.class)), scala.reflect.ClassTag..MODULE$.Long(), scala.reflect.ClassTag..MODULE$.apply(BisectingKMeans.ClusterSummaryAggregator.class), scala.math.Ordering.Long..MODULE$).mapValues((x$9) -> x$9.summary()).collect()).toMap(scala..less.colon.less..MODULE$.refl());
   }

   public Tuple2 org$apache$spark$mllib$clustering$BisectingKMeans$$splitCenter(final VectorWithNorm center, final Random random, final DistanceMeasure distanceMeasure) {
      int d = center.vector().size();
      double norm = center.norm();
      double level = 1.0E-4 * norm;
      Vector noise = Vectors$.MODULE$.dense((double[])scala.Array..MODULE$.fill(d, (JFunction0.mcD.sp)() -> random.nextDouble(), scala.reflect.ClassTag..MODULE$.Double()));
      return distanceMeasure.symmetricCentroids(level, noise, center.vector());
   }

   public RDD org$apache$spark$mllib$clustering$BisectingKMeans$$updateAssignments(final RDD assignments, final Set divisibleIndices, final Map newClusterCenters, final DistanceMeasure distanceMeasure) {
      return assignments.map((x0$1) -> {
         if (x0$1 != null) {
            long index = x0$1._1$mcJ$sp();
            VectorWithNorm v = (VectorWithNorm)x0$1._2();
            if (divisibleIndices.contains(BoxesRunTime.boxToLong(index))) {
               Seq children = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapLongArray(new long[]{MODULE$.org$apache$spark$mllib$clustering$BisectingKMeans$$leftChildIndex(index), MODULE$.org$apache$spark$mllib$clustering$BisectingKMeans$$rightChildIndex(index)}));
               Seq newClusterChildren = (Seq)children.filter((JFunction1.mcZJ.sp)(key) -> newClusterCenters.contains(BoxesRunTime.boxToLong(key)));
               Map newClusterChildrenCenterToId = ((IterableOnceOps)newClusterChildren.map((idx) -> $anonfun$updateAssignments$3(newClusterCenters, BoxesRunTime.unboxToLong(idx)))).toMap(scala..less.colon.less..MODULE$.refl());
               VectorWithNorm[] newClusterChildrenCenters = (VectorWithNorm[])newClusterChildrenCenterToId.keys().toArray(scala.reflect.ClassTag..MODULE$.apply(VectorWithNorm.class));
               if (newClusterChildren.nonEmpty()) {
                  int selected = distanceMeasure.findClosest(newClusterChildrenCenters, v)._1$mcI$sp();
                  VectorWithNorm center = newClusterChildrenCenters[selected];
                  long id = BoxesRunTime.unboxToLong(newClusterChildrenCenterToId.apply(center));
                  return new Tuple2(BoxesRunTime.boxToLong(id), v);
               } else {
                  return new Tuple2(BoxesRunTime.boxToLong(index), v);
               }
            } else {
               return new Tuple2(BoxesRunTime.boxToLong(index), v);
            }
         } else {
            throw new MatchError(x0$1);
         }
      }, scala.reflect.ClassTag..MODULE$.apply(Tuple2.class));
   }

   public ClusteringTreeNode org$apache$spark$mllib$clustering$BisectingKMeans$$buildTree(final Map clusters, final DistanceMeasure distanceMeasure) {
      IntRef leafIndex = IntRef.create(0);
      IntRef internalIndex = IntRef.create(-1);
      return this.buildSubTree$1(this.org$apache$spark$mllib$clustering$BisectingKMeans$$ROOT_INDEX(), clusters, internalIndex, distanceMeasure, leafIndex);
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(BisectingKMeans$.class);
   }

   // $FF: synthetic method
   public static final Tuple2 $anonfun$updateAssignments$3(final Map newClusterCenters$2, final long id) {
      return scala.Predef.ArrowAssoc..MODULE$.$minus$greater$extension(scala.Predef..MODULE$.ArrowAssoc(newClusterCenters$2.apply(BoxesRunTime.boxToLong(id))), BoxesRunTime.boxToLong(id));
   }

   // $FF: synthetic method
   public static final ClusteringTreeNode $anonfun$buildTree$3(final BisectingKMeans$ $this, final Map clusters$1, final IntRef internalIndex$1, final DistanceMeasure distanceMeasure$2, final IntRef leafIndex$1, final long rawIndex) {
      return $this.buildSubTree$1(rawIndex, clusters$1, internalIndex$1, distanceMeasure$2, leafIndex$1);
   }

   private final ClusteringTreeNode buildSubTree$1(final long rawIndex, final Map clusters$1, final IntRef internalIndex$1, final DistanceMeasure distanceMeasure$2, final IntRef leafIndex$1) {
      BisectingKMeans.ClusterSummary cluster = (BisectingKMeans.ClusterSummary)clusters$1.apply(BoxesRunTime.boxToLong(rawIndex));
      long size = cluster.size();
      VectorWithNorm center = cluster.center();
      double cost = cluster.cost();
      boolean isInternal = clusters$1.contains(BoxesRunTime.boxToLong(this.org$apache$spark$mllib$clustering$BisectingKMeans$$leftChildIndex(rawIndex)));
      if (isInternal) {
         int index = internalIndex$1.elem--;
         long leftIndex = this.org$apache$spark$mllib$clustering$BisectingKMeans$$leftChildIndex(rawIndex);
         long rightIndex = this.org$apache$spark$mllib$clustering$BisectingKMeans$$rightChildIndex(rawIndex);
         Seq indexes = (Seq)scala.package..MODULE$.Seq().apply(scala.runtime.ScalaRunTime..MODULE$.wrapLongArray(new long[]{leftIndex, rightIndex})).filter((JFunction1.mcZJ.sp)(key) -> clusters$1.contains(BoxesRunTime.boxToLong(key)));
         double height = BoxesRunTime.unboxToDouble(((IterableOnceOps)indexes.map((JFunction1.mcDJ.sp)(childIndex) -> distanceMeasure$2.distance(center, ((BisectingKMeans.ClusterSummary)clusters$1.apply(BoxesRunTime.boxToLong(childIndex))).center()))).max(scala.math.Ordering.DeprecatedDoubleOrdering..MODULE$));
         ClusteringTreeNode[] children = (ClusteringTreeNode[])((IterableOnceOps)indexes.map((rawIndexx) -> $anonfun$buildTree$3(this, clusters$1, internalIndex$1, distanceMeasure$2, leafIndex$1, BoxesRunTime.unboxToLong(rawIndexx)))).toArray(scala.reflect.ClassTag..MODULE$.apply(ClusteringTreeNode.class));
         return new ClusteringTreeNode(index, size, center, cost, height, children);
      } else {
         int index = leafIndex$1.elem++;
         double height = (double)0.0F;
         return new ClusteringTreeNode(index, size, center, cost, height, (ClusteringTreeNode[])scala.Array..MODULE$.empty(scala.reflect.ClassTag..MODULE$.apply(ClusteringTreeNode.class)));
      }
   }

   private BisectingKMeans$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
