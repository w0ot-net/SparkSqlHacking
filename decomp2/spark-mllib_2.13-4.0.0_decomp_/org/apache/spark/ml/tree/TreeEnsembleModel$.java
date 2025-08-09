package org.apache.spark.ml.tree;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.ml.linalg.Vector;
import org.apache.spark.util.collection.OpenHashMap;
import scala.MatchError;
import scala.Tuple2;
import scala.collection.IterableOnceOps;
import scala.collection.IterableOps;
import scala.collection.immutable.Seq;
import scala.reflect.ClassTag;
import scala.reflect.ClassTag.;
import scala.runtime.BoxedUnit;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction0;
import scala.runtime.java8.JFunction1;

public final class TreeEnsembleModel$ {
   public static final TreeEnsembleModel$ MODULE$ = new TreeEnsembleModel$();

   public Vector featureImportances(final DecisionTreeModel[] trees, final int numFeatures, final boolean perTreeNormalization) {
      OpenHashMap totalImportances = new OpenHashMap.mcD.sp(.MODULE$.Int(), .MODULE$.Double());
      scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.refArrayOps(trees), (tree) -> {
         $anonfun$featureImportances$1(perTreeNormalization, totalImportances, tree);
         return BoxedUnit.UNIT;
      });
      this.normalizeMapValues(totalImportances);
      int var10000;
      if (numFeatures != -1) {
         var10000 = numFeatures;
      } else {
         int maxFeatureIndex = BoxesRunTime.unboxToInt(scala.Predef..MODULE$.wrapIntArray((int[])scala.collection.ArrayOps..MODULE$.map$extension(scala.Predef..MODULE$.refArrayOps(trees), (x$8) -> BoxesRunTime.boxToInteger($anonfun$featureImportances$6(x$8)), .MODULE$.Int())).max(scala.math.Ordering.Int..MODULE$));
         var10000 = maxFeatureIndex + 1;
      }

      int d = var10000;
      if (d == 0) {
         scala.Predef..MODULE$.assert(totalImportances.size() == 0, () -> "Unknown error in computing feature importance: No splits found, but some non-zero importances.");
      }

      Tuple2 var9 = ((IterableOps)totalImportances.iterator().toSeq().sortBy((x$9) -> BoxesRunTime.boxToInteger($anonfun$featureImportances$8(x$9)), scala.math.Ordering.Int..MODULE$)).unzip(scala.Predef..MODULE$.$conforms());
      if (var9 != null) {
         Seq indices = (Seq)var9._1();
         Seq values = (Seq)var9._2();
         Tuple2 var8 = new Tuple2(indices, values);
         Seq indices = (Seq)var8._1();
         Seq values = (Seq)var8._2();
         return org.apache.spark.ml.linalg.Vectors..MODULE$.sparse(d, (int[])indices.toArray(.MODULE$.Int()), (double[])values.toArray(.MODULE$.Double()));
      } else {
         throw new MatchError(var9);
      }
   }

   public Vector featureImportances(final DecisionTreeModel tree, final int numFeatures, final ClassTag evidence$1) {
      Object var4 = evidence$1.newArray(1);
      scala.runtime.ScalaRunTime..MODULE$.array_update(var4, 0, tree);
      return this.featureImportances((DecisionTreeModel[])var4, numFeatures, this.featureImportances$default$3());
   }

   public boolean featureImportances$default$3() {
      return true;
   }

   public void computeFeatureImportance(final Node node, final OpenHashMap importances) {
      while(node instanceof InternalNode) {
         InternalNode var6 = (InternalNode)node;
         int feature = var6.split().featureIndex();
         double scaledGain = var6.gain() * var6.impurityStats().count();
         importances.changeValue$mcD$sp(BoxesRunTime.boxToInteger(feature), (JFunction0.mcD.sp)() -> scaledGain, (JFunction1.mcDD.sp)(x$11) -> x$11 + scaledGain);
         this.computeFeatureImportance(var6.leftChild(), importances);
         Node var10000 = var6.rightChild();
         importances = importances;
         node = var10000;
      }

      if (node instanceof LeafNode) {
         BoxedUnit var10 = BoxedUnit.UNIT;
         var10 = BoxedUnit.UNIT;
      } else {
         throw new MatchError(node);
      }
   }

   public void normalizeMapValues(final OpenHashMap map) {
      double total = BoxesRunTime.unboxToDouble(((IterableOnceOps)map.map((x$12) -> BoxesRunTime.boxToDouble($anonfun$normalizeMapValues$1(x$12)))).sum(scala.math.Numeric.DoubleIsFractional..MODULE$));
      if (total != (double)0) {
         int[] keys = (int[])map.iterator().map((x$13) -> BoxesRunTime.boxToInteger($anonfun$normalizeMapValues$2(x$13))).toArray(.MODULE$.Int());
         scala.collection.ArrayOps..MODULE$.foreach$extension(scala.Predef..MODULE$.intArrayOps(keys), (JFunction1.mcDI.sp)(key) -> map.changeValue$mcD$sp(BoxesRunTime.boxToInteger(key), (JFunction0.mcD.sp)() -> (double)0.0F, (JFunction1.mcDD.sp)(x$14) -> x$14 / total));
      }
   }

   // $FF: synthetic method
   public static final double $anonfun$featureImportances$2(final Tuple2 x$6) {
      return x$6._2$mcD$sp();
   }

   // $FF: synthetic method
   public static final double $anonfun$featureImportances$3(final boolean perTreeNormalization$1, final double treeNorm$1, final OpenHashMap totalImportances$1, final Tuple2 x0$1) {
      if (x0$1 != null) {
         int idx = x0$1._1$mcI$sp();
         double impt = x0$1._2$mcD$sp();
         double normImpt = perTreeNormalization$1 ? impt / treeNorm$1 : impt;
         return totalImportances$1.changeValue$mcD$sp(BoxesRunTime.boxToInteger(idx), (JFunction0.mcD.sp)() -> normImpt, (JFunction1.mcDD.sp)(x$7) -> x$7 + normImpt);
      } else {
         throw new MatchError(x0$1);
      }
   }

   // $FF: synthetic method
   public static final void $anonfun$featureImportances$1(final boolean perTreeNormalization$1, final OpenHashMap totalImportances$1, final DecisionTreeModel tree) {
      OpenHashMap importances = new OpenHashMap.mcD.sp(.MODULE$.Int(), .MODULE$.Double());
      MODULE$.computeFeatureImportance(tree.rootNode(), importances);
      double treeNorm = perTreeNormalization$1 ? BoxesRunTime.unboxToDouble(((IterableOnceOps)importances.map((x$6) -> BoxesRunTime.boxToDouble($anonfun$featureImportances$2(x$6)))).sum(scala.math.Numeric.DoubleIsFractional..MODULE$)) : Double.NaN;
      if (treeNorm != (double)0) {
         importances.foreach((x0$1) -> BoxesRunTime.boxToDouble($anonfun$featureImportances$3(perTreeNormalization$1, treeNorm, totalImportances$1, x0$1)));
      }
   }

   // $FF: synthetic method
   public static final int $anonfun$featureImportances$6(final DecisionTreeModel x$8) {
      return x$8.maxSplitFeatureIndex();
   }

   // $FF: synthetic method
   public static final int $anonfun$featureImportances$8(final Tuple2 x$9) {
      return x$9._1$mcI$sp();
   }

   // $FF: synthetic method
   public static final double $anonfun$normalizeMapValues$1(final Tuple2 x$12) {
      return x$12._2$mcD$sp();
   }

   // $FF: synthetic method
   public static final int $anonfun$normalizeMapValues$2(final Tuple2 x$13) {
      return x$13._1$mcI$sp();
   }

   private TreeEnsembleModel$() {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
