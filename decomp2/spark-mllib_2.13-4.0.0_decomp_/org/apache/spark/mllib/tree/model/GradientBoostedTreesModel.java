package org.apache.spark.mllib.tree.model;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.SparkContext;
import org.apache.spark.broadcast.Broadcast;
import org.apache.spark.mllib.regression.LabeledPoint;
import org.apache.spark.mllib.tree.configuration.Algo$;
import org.apache.spark.mllib.tree.configuration.EnsembleCombiningStrategy$;
import org.apache.spark.mllib.tree.loss.Loss;
import org.apache.spark.mllib.util.Saveable;
import org.apache.spark.rdd.RDD;
import scala.Enumeration;
import scala.collection.IndexedSeqOps;
import scala.collection.immutable.IndexedSeq;
import scala.collection.immutable.Range;
import scala.reflect.ScalaSignature;
import scala.reflect.ClassTag.;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ug\u0001B\u000b\u0017\u0001\rB\u0001B\f\u0001\u0003\u0006\u0004%\te\f\u0005\n#\u0002\u0011\t\u0011)A\u0005aIC\u0001\u0002\u0016\u0001\u0003\u0006\u0004%\t%\u0016\u0005\nA\u0002\u0011\t\u0011)A\u0005-\u0006D\u0001b\u0019\u0001\u0003\u0006\u0004%\t\u0005\u001a\u0005\nU\u0002\u0011\t\u0011)A\u0005K.DQ!\u001c\u0001\u0005\u00029DQa\u001e\u0001\u0005BaDq!a\b\u0001\t\u0003\t\tcB\u0004\u0002VYA\t!a\u0016\u0007\rU1\u0002\u0012AA-\u0011\u0019i7\u0002\"\u0001\u0002x!9\u0011\u0011P\u0006\u0005\u0002\u0005m\u0004bBAJ\u0017\u0011\u0005\u0011Q\u0013\u0005\b\u0003O[A\u0011IAU\u000f\u001d\t\tl\u0003E\u0005\u0003g3q!a.\f\u0011\u0013\tI\f\u0003\u0004n#\u0011\u0005\u00111\u0018\u0005\b\u0003{\u000bB\u0011AA`\u0011%\t\tmCA\u0001\n\u0013\t\u0019MA\rHe\u0006$\u0017.\u001a8u\u0005>|7\u000f^3e)J,Wm]'pI\u0016d'BA\f\u0019\u0003\u0015iw\u000eZ3m\u0015\tI\"$\u0001\u0003ue\u0016,'BA\u000e\u001d\u0003\u0015iG\u000e\\5c\u0015\tib$A\u0003ta\u0006\u00148N\u0003\u0002 A\u00051\u0011\r]1dQ\u0016T\u0011!I\u0001\u0004_J<7\u0001A\n\u0004\u0001\u0011B\u0003CA\u0013'\u001b\u00051\u0012BA\u0014\u0017\u0005E!&/Z3F]N,WN\u00197f\u001b>$W\r\u001c\t\u0003S1j\u0011A\u000b\u0006\u0003Wi\tA!\u001e;jY&\u0011QF\u000b\u0002\t'\u00064X-\u00192mK\u0006!\u0011\r\\4p+\u0005\u0001\u0004CA\u0019F\u001d\t\u0011$I\u0004\u00024\u0001:\u0011Ag\u0010\b\u0003kyr!AN\u001f\u000f\u0005]bdB\u0001\u001d<\u001b\u0005I$B\u0001\u001e#\u0003\u0019a$o\\8u}%\t\u0011%\u0003\u0002 A%\u0011QDH\u0005\u00037qI!!\u0007\u000e\n\u0005\u0005C\u0012!D2p]\u001aLw-\u001e:bi&|g.\u0003\u0002D\t\u0006!\u0011\t\\4p\u0015\t\t\u0005$\u0003\u0002G\u000f\n!\u0011\t\\4p\u0015\t\u0019E\tK\u0002\u0002\u0013>\u0003\"AS'\u000e\u0003-S!\u0001\u0014\u000f\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0002O\u0017\n)1+\u001b8dK\u0006\n\u0001+A\u00032]Ir\u0003'A\u0003bY\u001e|\u0007%\u0003\u0002/M!\u001a!!S(\u0002\u000bQ\u0014X-Z:\u0016\u0003Y\u00032a\u0016.]\u001b\u0005A&\"A-\u0002\u000bM\u001c\u0017\r\\1\n\u0005mC&!B!se\u0006L\bCA\u0013^\u0013\tqfCA\tEK\u000eL7/[8o)J,W-T8eK2D3aA%P\u0003\u0019!(/Z3tA%\u0011AK\n\u0015\u0004\t%{\u0015a\u0003;sK\u0016<V-[4iiN,\u0012!\u001a\t\u0004/j3\u0007CA,h\u0013\tA\u0007L\u0001\u0004E_V\u0014G.\u001a\u0015\u0004\u000b%{\u0015\u0001\u0004;sK\u0016<V-[4iiN\u0004\u0013BA2'Q\r1\u0011jT\u0001\u0007y%t\u0017\u000e\u001e \u0015\t=\u0004(\u000f\u001e\t\u0003K\u0001AQAL\u0004A\u0002AB3\u0001]%P\u0011\u0015!v\u00011\u0001WQ\r\u0011\u0018j\u0014\u0005\u0006G\u001e\u0001\r!\u001a\u0015\u0004i&{\u0005fA\u0004J\u001f\u0006!1/\u0019<f)\u0011IH0!\u0002\u0011\u0005]S\u0018BA>Y\u0005\u0011)f.\u001b;\t\u000buD\u0001\u0019\u0001@\u0002\u0005M\u001c\u0007cA@\u0002\u00025\tA$C\u0002\u0002\u0004q\u0011Ab\u00159be.\u001cuN\u001c;fqRDq!a\u0002\t\u0001\u0004\tI!\u0001\u0003qCRD\u0007\u0003BA\u0006\u0003'qA!!\u0004\u0002\u0010A\u0011\u0001\bW\u0005\u0004\u0003#A\u0016A\u0002)sK\u0012,g-\u0003\u0003\u0002\u0016\u0005]!AB*ue&twMC\u0002\u0002\u0012aCC\u0001C%\u0002\u001c\u0005\u0012\u0011QD\u0001\u0006c9\u001ad\u0006M\u0001\u0016KZ\fG.^1uK\u0016\u000b7\r[%uKJ\fG/[8o)\u0015)\u00171EA \u0011\u001d\t)#\u0003a\u0001\u0003O\tA\u0001Z1uCB1\u0011\u0011FA\u0018\u0003gi!!a\u000b\u000b\u0007\u00055B$A\u0002sI\u0012LA!!\r\u0002,\t\u0019!\u000b\u0012#\u0011\t\u0005U\u00121H\u0007\u0003\u0003oQ1!!\u000f\u001b\u0003)\u0011Xm\u001a:fgNLwN\\\u0005\u0005\u0003{\t9D\u0001\u0007MC\n,G.\u001a3Q_&tG\u000fC\u0004\u0002B%\u0001\r!a\u0011\u0002\t1|7o\u001d\t\u0005\u0003\u000b\nI%\u0004\u0002\u0002H)\u0019\u0011\u0011\t\r\n\t\u0005-\u0013q\t\u0002\u0005\u0019>\u001c8\u000f\u000b\u0003\n\u0013\u0006=\u0013EAA)\u0003\u0015\td\u0006\u000e\u00181Q\r\u0001\u0011jT\u0001\u001a\u000fJ\fG-[3oi\n{wn\u001d;fIR\u0013X-Z:N_\u0012,G\u000e\u0005\u0002&\u0017M91\"a\u0017\u0002b\u0005\u001d\u0004cA,\u0002^%\u0019\u0011q\f-\u0003\r\u0005s\u0017PU3g!\u0011I\u00131M8\n\u0007\u0005\u0015$F\u0001\u0004M_\u0006$WM\u001d\t\u0005\u0003S\n\u0019(\u0004\u0002\u0002l)!\u0011QNA8\u0003\tIwN\u0003\u0002\u0002r\u0005!!.\u0019<b\u0013\u0011\t)(a\u001b\u0003\u0019M+'/[1mSj\f'\r\\3\u0015\u0005\u0005]\u0013\u0001I2p[B,H/Z%oSRL\u0017\r\u001c)sK\u0012L7\r^5p]\u0006sG-\u0012:s_J$\"\"! \u0002\u0006\u0006\u001d\u00151RAH!\u0019\tI#a\f\u0002\u0000A)q+!!gM&\u0019\u00111\u0011-\u0003\rQ+\b\u000f\\33\u0011\u001d\t)#\u0004a\u0001\u0003OAa!!#\u000e\u0001\u00041\u0017AD5oSR$&/Z3XK&<\u0007\u000e\u001e\u0005\u0007\u0003\u001bk\u0001\u0019\u0001/\u0002\u0011%t\u0017\u000e\u001e+sK\u0016Dq!!\u0011\u000e\u0001\u0004\t\u0019\u0005\u000b\u0003\u000e\u0013\u0006=\u0013!F;qI\u0006$X\r\u0015:fI&\u001cG/[8o\u000bJ\u0014xN\u001d\u000b\r\u0003{\n9*!'\u0002\u001e\u0006\u0005\u00161\u0015\u0005\b\u0003Kq\u0001\u0019AA\u0014\u0011\u001d\tYJ\u0004a\u0001\u0003{\n!\u0003\u001d:fI&\u001cG/[8o\u0003:$WI\u001d:pe\"1\u0011q\u0014\bA\u0002\u0019\f!\u0002\u001e:fK^+\u0017n\u001a5u\u0011\u0015Ib\u00021\u0001]\u0011\u001d\t\tE\u0004a\u0001\u0003\u0007BCAD%\u0002P\u0005!An\\1e)\u0015y\u00171VAW\u0011\u0015ix\u00021\u0001\u007f\u0011\u001d\t9a\u0004a\u0001\u0003\u0013ACaD%\u0002\u001c\u0005a1+\u0019<f\u0019>\fGMV\u0019`aA\u0019\u0011QW\t\u000e\u0003-\u0011AbU1wK2{\u0017\r\u001a,2?B\u001a2!EA.)\t\t\u0019,A\u0007uQ&\u001c8\t\\1tg:\u000bW.Z\u000b\u0003\u0003\u0013\tAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\"!!2\u0011\t\u0005\u001d\u0017QZ\u0007\u0003\u0003\u0013TA!a3\u0002p\u0005!A.\u00198h\u0013\u0011\ty-!3\u0003\r=\u0013'.Z2uQ\u0011Y\u0011*a\u0007)\t)I\u00151\u0004"
)
public class GradientBoostedTreesModel extends TreeEnsembleModel implements Saveable {
   public static GradientBoostedTreesModel load(final SparkContext sc, final String path) {
      return GradientBoostedTreesModel$.MODULE$.load(sc, path);
   }

   public static RDD updatePredictionError(final RDD data, final RDD predictionAndError, final double treeWeight, final DecisionTreeModel tree, final Loss loss) {
      return GradientBoostedTreesModel$.MODULE$.updatePredictionError(data, predictionAndError, treeWeight, tree, loss);
   }

   public static RDD computeInitialPredictionAndError(final RDD data, final double initTreeWeight, final DecisionTreeModel initTree, final Loss loss) {
      return GradientBoostedTreesModel$.MODULE$.computeInitialPredictionAndError(data, initTreeWeight, initTree, loss);
   }

   public Enumeration.Value algo() {
      return super.algo();
   }

   public DecisionTreeModel[] trees() {
      return super.trees();
   }

   public double[] treeWeights() {
      return super.treeWeights();
   }

   public void save(final SparkContext sc, final String path) {
      TreeEnsembleModel.SaveLoadV1_0$.MODULE$.save(sc, path, this, GradientBoostedTreesModel.SaveLoadV1_0$.MODULE$.thisClassName());
   }

   public double[] evaluateEachIteration(final RDD data, final Loss loss) {
      SparkContext sc;
      RDD var14;
      label18: {
         label17: {
            sc = data.sparkContext();
            Enumeration.Value var6 = this.algo();
            Enumeration.Value var10000 = Algo$.MODULE$.Classification();
            if (var10000 == null) {
               if (var6 == null) {
                  break label17;
               }
            } else if (var10000.equals(var6)) {
               break label17;
            }

            var14 = data;
            break label18;
         }

         var14 = data.map((x) -> new LabeledPoint(x.label() * (double)2 - (double)1, x.features()), .MODULE$.apply(LabeledPoint.class));
      }

      RDD remappedData = var14;
      Broadcast broadcastTrees = sc.broadcast(this.trees(), .MODULE$.apply(scala.runtime.ScalaRunTime..MODULE$.arrayClass(DecisionTreeModel.class)));
      double[] localTreeWeights = this.treeWeights();
      Range treesIndices = scala.collection.ArrayOps..MODULE$.indices$extension(scala.Predef..MODULE$.refArrayOps(this.trees()));
      long dataCount = remappedData.count();
      IndexedSeq evaluation = (IndexedSeq)((IndexedSeqOps)remappedData.map((point) -> (IndexedSeq)((IndexedSeqOps)((IndexedSeqOps)treesIndices.map((JFunction1.mcDI.sp)(idx) -> ((DecisionTreeModel[])broadcastTrees.value())[idx].predict(point.features()) * localTreeWeights[idx]).scanLeft(BoxesRunTime.boxToDouble((double)0.0F), (JFunction2.mcDDD.sp)(x$4, x$5) -> x$4 + x$5)).drop(1)).map((JFunction1.mcDD.sp)(prediction) -> loss.computeError(prediction, point.label())), .MODULE$.apply(IndexedSeq.class)).aggregate(treesIndices.map((JFunction1.mcDI.sp)(x$6) -> (double)0.0F), (aggregated, row) -> treesIndices.map((JFunction1.mcDI.sp)(idx) -> BoxesRunTime.unboxToDouble(aggregated.apply(idx)) + BoxesRunTime.unboxToDouble(row.apply(idx))), (a, b) -> treesIndices.map((JFunction1.mcDI.sp)(idx) -> BoxesRunTime.unboxToDouble(a.apply(idx)) + BoxesRunTime.unboxToDouble(b.apply(idx))), .MODULE$.apply(IndexedSeq.class))).map((JFunction1.mcDD.sp)(x$7) -> x$7 / (double)dataCount);
      broadcastTrees.destroy();
      return (double[])evaluation.toArray(.MODULE$.Double());
   }

   public GradientBoostedTreesModel(final Enumeration.Value algo, final DecisionTreeModel[] trees, final double[] treeWeights) {
      super(algo, trees, treeWeights, EnsembleCombiningStrategy$.MODULE$.Sum());
      scala.Predef..MODULE$.require(trees.length == treeWeights.length);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }

   private static class SaveLoadV1_0$ {
      public static final SaveLoadV1_0$ MODULE$ = new SaveLoadV1_0$();

      public String thisClassName() {
         return "org.apache.spark.mllib.tree.model.GradientBoostedTreesModel";
      }

      public SaveLoadV1_0$() {
      }
   }
}
