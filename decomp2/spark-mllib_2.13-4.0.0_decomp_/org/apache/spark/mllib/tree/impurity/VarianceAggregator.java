package org.apache.spark.mllib.tree.impurity;

import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!3Q\u0001B\u0003\u0001\u0017EAQ!\n\u0001\u0005\u0002\u0019BQ\u0001\u000b\u0001\u0005\u0002%BQ!\u0011\u0001\u0005\u0002\t\u0013!CV1sS\u0006t7-Z!hOJ,w-\u0019;pe*\u0011aaB\u0001\tS6\u0004XO]5us*\u0011\u0001\"C\u0001\u0005iJ,WM\u0003\u0002\u000b\u0017\u0005)Q\u000e\u001c7jE*\u0011A\"D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001d=\ta!\u00199bG\",'\"\u0001\t\u0002\u0007=\u0014xmE\u0002\u0001%Y\u0001\"a\u0005\u000b\u000e\u0003\u0015I!!F\u0003\u0003%%k\u0007/\u001e:jif\fum\u001a:fO\u0006$xN\u001d\t\u0003/\tr!\u0001G\u0010\u000f\u0005eiR\"\u0001\u000e\u000b\u0005ma\u0012A\u0002\u001fs_>$hh\u0001\u0001\n\u0003y\tQa]2bY\u0006L!\u0001I\u0011\u0002\u000fA\f7m[1hK*\ta$\u0003\u0002$I\ta1+\u001a:jC2L'0\u00192mK*\u0011\u0001%I\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003\u001d\u0002\"a\u0005\u0001\u0002\rU\u0004H-\u0019;f)\u0019QcFN\u001e>\u007fA\u00111\u0006L\u0007\u0002C%\u0011Q&\t\u0002\u0005+:LG\u000fC\u00030\u0005\u0001\u0007\u0001'\u0001\u0005bY2\u001cF/\u0019;t!\rY\u0013gM\u0005\u0003e\u0005\u0012Q!\u0011:sCf\u0004\"a\u000b\u001b\n\u0005U\n#A\u0002#pk\ndW\rC\u00038\u0005\u0001\u0007\u0001(\u0001\u0004pM\u001a\u001cX\r\u001e\t\u0003WeJ!AO\u0011\u0003\u0007%sG\u000fC\u0003=\u0005\u0001\u00071'A\u0003mC\n,G\u000eC\u0003?\u0005\u0001\u0007\u0001(\u0001\u0006ok6\u001c\u0016-\u001c9mKNDQ\u0001\u0011\u0002A\u0002M\nAb]1na2,w+Z5hQR\fQbZ3u\u0007\u0006d7-\u001e7bi>\u0014HcA\"G\u000fB\u00111\u0003R\u0005\u0003\u000b\u0016\u0011!CV1sS\u0006t7-Z\"bY\u000e,H.\u0019;pe\")qf\u0001a\u0001a!)qg\u0001a\u0001q\u0001"
)
public class VarianceAggregator extends ImpurityAggregator {
   public void update(final double[] allStats, final int offset, final double label, final int numSamples, final double sampleWeight) {
      double instanceWeight = (double)numSamples * sampleWeight;
      allStats[offset] += instanceWeight;
      int var10 = offset + 1;
      allStats[var10] += instanceWeight * label;
      int var11 = offset + 2;
      allStats[var11] += instanceWeight * label * label;
      int var12 = offset + 3;
      allStats[var12] += (double)numSamples;
   }

   public VarianceCalculator getCalculator(final double[] allStats, final int offset) {
      return new VarianceCalculator((double[]).MODULE$.slice$extension(scala.Predef..MODULE$.doubleArrayOps(allStats), offset, offset + this.statsSize() - 1), (long)allStats[offset + this.statsSize() - 1]);
   }

   public VarianceAggregator() {
      super(4);
   }
}
