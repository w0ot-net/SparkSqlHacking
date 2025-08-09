package org.apache.spark.ml.tree.impl;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import org.apache.spark.mllib.tree.impurity.Entropy$;
import org.apache.spark.mllib.tree.impurity.EntropyAggregator;
import org.apache.spark.mllib.tree.impurity.Gini$;
import org.apache.spark.mllib.tree.impurity.GiniAggregator;
import org.apache.spark.mllib.tree.impurity.Impurity;
import org.apache.spark.mllib.tree.impurity.ImpurityAggregator;
import org.apache.spark.mllib.tree.impurity.ImpurityCalculator;
import org.apache.spark.mllib.tree.impurity.Variance$;
import org.apache.spark.mllib.tree.impurity.VarianceAggregator;
import scala.Option;
import scala.Predef.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.java8.JFunction1;
import scala.runtime.java8.JFunction2;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u001dc!B\u000e\u001d\u0001\tB\u0003\u0002\u0003\u001f\u0001\u0005\u000b\u0007I\u0011A\u001f\t\u0011\t\u0003!\u0011!Q\u0001\nyB\u0001b\u0011\u0001\u0003\u0002\u0003\u0006I\u0001\u0012\u0005\u0006\u001b\u0002!\tA\u0014\u0005\b%\u0002\u0011\r\u0011\"\u0001T\u0011\u0019i\u0006\u0001)A\u0005)\"9a\f\u0001b\u0001\n\u0013y\u0006B\u00021\u0001A\u0003%!\nC\u0004b\u0001\t\u0007I\u0011\u00022\t\r\r\u0004\u0001\u0015!\u0003H\u0011\u001d!\u0007A1A\u0005\n\tDa!\u001a\u0001!\u0002\u00139\u0005b\u00024\u0001\u0005\u0004%Ia\u0018\u0005\u0007O\u0002\u0001\u000b\u0011\u0002&\t\u000f!\u0004!\u0019!C\u0005S\"1a\u000e\u0001Q\u0001\n)Dqa\u001c\u0001C\u0002\u0013%\u0011\u000e\u0003\u0004q\u0001\u0001\u0006IA\u001b\u0005\u0006c\u0002!\tA\u001d\u0005\u0006u\u0002!\ta\u001f\u0005\u0006y\u0002!\t! \u0005\b\u0003+\u0001A\u0011AA\f\u0011\u001d\ty\u0002\u0001C\u0001\u0003CAq!!\f\u0001\t\u0003\ty\u0003C\u0004\u00024\u0001!\t!!\u000e\t\u000f\u0005}\u0002\u0001\"\u0001\u0002B\t\tB\tV*uCR\u001c\u0018iZ4sK\u001e\fGo\u001c:\u000b\u0005uq\u0012\u0001B5na2T!a\b\u0011\u0002\tQ\u0014X-\u001a\u0006\u0003C\t\n!!\u001c7\u000b\u0005\r\"\u0013!B:qCJ\\'BA\u0013'\u0003\u0019\t\u0007/Y2iK*\tq%A\u0002pe\u001e\u001c2\u0001A\u00150!\tQS&D\u0001,\u0015\u0005a\u0013!B:dC2\f\u0017B\u0001\u0018,\u0005\u0019\te.\u001f*fMB\u0011\u0001'\u000f\b\u0003c]r!A\r\u001c\u000e\u0003MR!\u0001N\u001b\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011\u0001L\u0005\u0003q-\nq\u0001]1dW\u0006<W-\u0003\u0002;w\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0001hK\u0001\t[\u0016$\u0018\rZ1uCV\ta\b\u0005\u0002@\u00016\tA$\u0003\u0002B9\t!B)Z2jg&|g\u000e\u0016:fK6+G/\u00193bi\u0006\f\u0011\"\\3uC\u0012\fG/\u0019\u0011\u0002\u001b\u0019,\u0017\r^;sKN+(m]3u!\rQSiR\u0005\u0003\r.\u0012aa\u00149uS>t\u0007c\u0001\u0016I\u0015&\u0011\u0011j\u000b\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003U-K!\u0001T\u0016\u0003\u0007%sG/\u0001\u0004=S:LGO\u0010\u000b\u0004\u001fB\u000b\u0006CA \u0001\u0011\u0015aD\u00011\u0001?\u0011\u0015\u0019E\u00011\u0001E\u0003IIW\u000e];sSRL\u0018iZ4sK\u001e\fGo\u001c:\u0016\u0003Q\u0003\"!V.\u000e\u0003YS!a\u0016-\u0002\u0011%l\u0007/\u001e:jifT!aH-\u000b\u0005i\u0013\u0013!B7mY&\u0014\u0017B\u0001/W\u0005IIU\u000e];sSRL\u0018iZ4sK\u001e\fGo\u001c:\u0002'%l\u0007/\u001e:jif\fum\u001a:fO\u0006$xN\u001d\u0011\u0002\u0013M$\u0018\r^:TSj,W#\u0001&\u0002\u0015M$\u0018\r^:TSj,\u0007%A\u0004ok6\u0014\u0015N\\:\u0016\u0003\u001d\u000b\u0001B\\;n\u0005&t7\u000fI\u0001\u000fM\u0016\fG/\u001e:f\u001f\u001a47/\u001a;t\u0003=1W-\u0019;ve\u0016|eMZ:fiN\u0004\u0013\u0001D1mYN#\u0018\r^:TSj,\u0017!D1mYN#\u0018\r^:TSj,\u0007%\u0001\u0005bY2\u001cF/\u0019;t+\u0005Q\u0007c\u0001\u0016IWB\u0011!\u0006\\\u0005\u0003[.\u0012a\u0001R8vE2,\u0017!C1mYN#\u0018\r^:!\u0003-\u0001\u0018M]3oiN#\u0018\r^:\u0002\u0019A\f'/\u001a8u'R\fGo\u001d\u0011\u0002+\u001d,G/S7qkJLG/_\"bY\u000e,H.\u0019;peR\u00191O\u001e=\u0011\u0005U#\u0018BA;W\u0005IIU\u000e];sSRL8)\u00197dk2\fGo\u001c:\t\u000b]\u001c\u0002\u0019\u0001&\u0002\u001b\u0019,\u0017\r^;sK>3gm]3u\u0011\u0015I8\u00031\u0001K\u0003!\u0011\u0017N\\%oI\u0016D\u0018aG4fiB\u000b'/\u001a8u\u00136\u0004XO]5us\u000e\u000bGnY;mCR|'\u000fF\u0001t\u0003\u0019)\b\u000fZ1uKRYa0a\u0001\u0002\b\u0005%\u0011QBA\t!\tQs0C\u0002\u0002\u0002-\u0012A!\u00168ji\"1\u0011QA\u000bA\u0002)\u000bABZ3biV\u0014X-\u00138eKbDQ!_\u000bA\u0002)Ca!a\u0003\u0016\u0001\u0004Y\u0017!\u00027bE\u0016d\u0007BBA\b+\u0001\u0007!*\u0001\u0006ok6\u001c\u0016-\u001c9mKNDa!a\u0005\u0016\u0001\u0004Y\u0017\u0001D:b[BdWmV3jO\"$\u0018\u0001D;qI\u0006$X\rU1sK:$Hc\u0002@\u0002\u001a\u0005m\u0011Q\u0004\u0005\u0007\u0003\u00171\u0002\u0019A6\t\r\u0005=a\u00031\u0001K\u0011\u0019\t\u0019B\u0006a\u0001W\u0006ia-Z1ukJ,W\u000b\u001d3bi\u0016$2B`A\u0012\u0003K\t9#!\u000b\u0002,!)qo\u0006a\u0001\u0015\")\u0011p\u0006a\u0001\u0015\"1\u00111B\fA\u0002-Da!a\u0004\u0018\u0001\u0004Q\u0005BBA\n/\u0001\u00071.\u0001\thKR4U-\u0019;ve\u0016|eMZ:fiR\u0019!*!\r\t\r\u0005\u0015\u0001\u00041\u0001K\u0003=iWM]4f\r>\u0014h)Z1ukJ,Gc\u0002@\u00028\u0005e\u00121\b\u0005\u0006of\u0001\rA\u0013\u0005\u0006sf\u0001\rA\u0013\u0005\u0007\u0003{I\u0002\u0019\u0001&\u0002\u001b=$\b.\u001a:CS:Le\u000eZ3y\u0003\u0015iWM]4f)\ry\u00151\t\u0005\u0007\u0003\u000bR\u0002\u0019A(\u0002\u000b=$\b.\u001a:"
)
public class DTStatsAggregator implements Serializable {
   private final DecisionTreeMetadata metadata;
   private final ImpurityAggregator impurityAggregator;
   private final int statsSize;
   private final int[] numBins;
   private final int[] featureOffsets;
   private final int allStatsSize;
   private final double[] allStats;
   private final double[] parentStats;

   public DecisionTreeMetadata metadata() {
      return this.metadata;
   }

   public ImpurityAggregator impurityAggregator() {
      return this.impurityAggregator;
   }

   private int statsSize() {
      return this.statsSize;
   }

   private int[] numBins() {
      return this.numBins;
   }

   private int[] featureOffsets() {
      return this.featureOffsets;
   }

   private int allStatsSize() {
      return this.allStatsSize;
   }

   private double[] allStats() {
      return this.allStats;
   }

   private double[] parentStats() {
      return this.parentStats;
   }

   public ImpurityCalculator getImpurityCalculator(final int featureOffset, final int binIndex) {
      return this.impurityAggregator().getCalculator(this.allStats(), featureOffset + binIndex * this.statsSize());
   }

   public ImpurityCalculator getParentImpurityCalculator() {
      return this.impurityAggregator().getCalculator(this.parentStats(), 0);
   }

   public void update(final int featureIndex, final int binIndex, final double label, final int numSamples, final double sampleWeight) {
      int i = this.featureOffsets()[featureIndex] + binIndex * this.statsSize();
      this.impurityAggregator().update(this.allStats(), i, label, numSamples, sampleWeight);
   }

   public void updateParent(final double label, final int numSamples, final double sampleWeight) {
      this.impurityAggregator().update(this.parentStats(), 0, label, numSamples, sampleWeight);
   }

   public void featureUpdate(final int featureOffset, final int binIndex, final double label, final int numSamples, final double sampleWeight) {
      this.impurityAggregator().update(this.allStats(), featureOffset + binIndex * this.statsSize(), label, numSamples, sampleWeight);
   }

   public int getFeatureOffset(final int featureIndex) {
      return this.featureOffsets()[featureIndex];
   }

   public void mergeForFeature(final int featureOffset, final int binIndex, final int otherBinIndex) {
      this.impurityAggregator().merge(this.allStats(), featureOffset + binIndex * this.statsSize(), featureOffset + otherBinIndex * this.statsSize());
   }

   public DTStatsAggregator merge(final DTStatsAggregator other) {
      .MODULE$.require(this.allStatsSize() == other.allStatsSize(), () -> {
         int var10000 = this.allStatsSize();
         return "DTStatsAggregator.merge requires that both aggregators have the same length stats vectors. This aggregator is of length " + var10000 + ", but the other is " + other.allStatsSize() + ".";
      });

      for(int i = 0; i < this.allStatsSize(); ++i) {
         this.allStats()[i] += other.allStats()[i];
      }

      .MODULE$.require(this.statsSize() == other.statsSize(), () -> {
         int var10000 = this.statsSize();
         return "DTStatsAggregator.merge requires that both aggregators have the same length parent stats vectors. This aggregator's parent stats are length " + var10000 + ", but the other is " + other.statsSize() + ".";
      });

      for(int j = 0; j < this.statsSize(); ++j) {
         this.parentStats()[j] += other.parentStats()[j];
      }

      return this;
   }

   public DTStatsAggregator(final DecisionTreeMetadata metadata, final Option featureSubset) {
      this.metadata = metadata;
      Impurity var4 = metadata.impurity();
      Object var10001;
      if (Gini$.MODULE$.equals(var4)) {
         var10001 = new GiniAggregator(metadata.numClasses());
      } else if (Entropy$.MODULE$.equals(var4)) {
         var10001 = new EntropyAggregator(metadata.numClasses());
      } else {
         if (!Variance$.MODULE$.equals(var4)) {
            throw new IllegalArgumentException("Bad impurity parameter: " + metadata.impurity());
         }

         var10001 = new VarianceAggregator();
      }

      this.impurityAggregator = (ImpurityAggregator)var10001;
      this.statsSize = this.impurityAggregator().statsSize();
      this.numBins = featureSubset.isDefined() ? (int[])scala.collection.ArrayOps..MODULE$.map$extension(.MODULE$.intArrayOps((int[])featureSubset.get()), (JFunction1.mcII.sp)(x$1) -> this.metadata().numBins()[x$1], scala.reflect.ClassTag..MODULE$.Int()) : metadata.numBins();
      this.featureOffsets = (int[])scala.collection.ArrayOps..MODULE$.scanLeft$extension(.MODULE$.intArrayOps(this.numBins()), BoxesRunTime.boxToInteger(0), (JFunction2.mcIII.sp)(total, nBins) -> total + this.statsSize() * nBins, scala.reflect.ClassTag..MODULE$.Int());
      this.allStatsSize = BoxesRunTime.unboxToInt(scala.collection.ArrayOps..MODULE$.last$extension(.MODULE$.intArrayOps(this.featureOffsets())));
      this.allStats = new double[this.allStatsSize()];
      this.parentStats = new double[this.statsSize()];
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
