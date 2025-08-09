package org.apache.spark.mllib.tree.impurity;

import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-3Q!\u0002\u0004\u0001\u0019IA\u0001B\n\u0001\u0003\u0002\u0003\u0006Ia\n\u0005\u0006W\u0001!\t\u0001\f\u0005\u0006_\u0001!\t\u0001\r\u0005\u0006\t\u0002!\t!\u0012\u0002\u0012\u000b:$(o\u001c9z\u0003\u001e<'/Z4bi>\u0014(BA\u0004\t\u0003!IW\u000e];sSRL(BA\u0005\u000b\u0003\u0011!(/Z3\u000b\u0005-a\u0011!B7mY&\u0014'BA\u0007\u000f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0001#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002#\u0005\u0019qN]4\u0014\u0007\u0001\u0019r\u0003\u0005\u0002\u0015+5\ta!\u0003\u0002\u0017\r\t\u0011\u0012*\u001c9ve&$\u00180Q4he\u0016<\u0017\r^8s!\tA2E\u0004\u0002\u001aA9\u0011!DH\u0007\u00027)\u0011A$H\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\tq$A\u0003tG\u0006d\u0017-\u0003\u0002\"E\u00059\u0001/Y2lC\u001e,'\"A\u0010\n\u0005\u0011*#\u0001D*fe&\fG.\u001b>bE2,'BA\u0011#\u0003)qW/\\\"mCN\u001cXm\u001d\t\u0003Q%j\u0011AI\u0005\u0003U\t\u00121!\u00138u\u0003\u0019a\u0014N\\5u}Q\u0011QF\f\t\u0003)\u0001AQA\n\u0002A\u0002\u001d\na!\u001e9eCR,GCB\u00195yy\u0002%\t\u0005\u0002)e%\u00111G\t\u0002\u0005+:LG\u000fC\u00036\u0007\u0001\u0007a'\u0001\u0005bY2\u001cF/\u0019;t!\rAs'O\u0005\u0003q\t\u0012Q!\u0011:sCf\u0004\"\u0001\u000b\u001e\n\u0005m\u0012#A\u0002#pk\ndW\rC\u0003>\u0007\u0001\u0007q%\u0001\u0004pM\u001a\u001cX\r\u001e\u0005\u0006\u007f\r\u0001\r!O\u0001\u0006Y\u0006\u0014W\r\u001c\u0005\u0006\u0003\u000e\u0001\raJ\u0001\u000b]Vl7+Y7qY\u0016\u001c\b\"B\"\u0004\u0001\u0004I\u0014\u0001D:b[BdWmV3jO\"$\u0018!D4fi\u000e\u000bGnY;mCR|'\u000fF\u0002G\u0013*\u0003\"\u0001F$\n\u0005!3!!E#oiJ|\u0007/_\"bY\u000e,H.\u0019;pe\")Q\u0007\u0002a\u0001m!)Q\b\u0002a\u0001O\u0001"
)
public class EntropyAggregator extends ImpurityAggregator {
   private final int numClasses;

   public void update(final double[] allStats, final int offset, final double label, final int numSamples, final double sampleWeight) {
      if (label >= (double)this.numClasses) {
         throw new IllegalArgumentException("EntropyAggregator given label " + label + " but requires label < numClasses (= " + this.numClasses + ").");
      } else if (label < (double)0) {
         throw new IllegalArgumentException("EntropyAggregator given label " + label + "but requires label is non-negative.");
      } else {
         int var8 = offset + (int)label;
         allStats[var8] += (double)numSamples * sampleWeight;
         int var9 = offset + this.statsSize() - 1;
         allStats[var9] += (double)numSamples;
      }
   }

   public EntropyCalculator getCalculator(final double[] allStats, final int offset) {
      return new EntropyCalculator((double[]).MODULE$.slice$extension(scala.Predef..MODULE$.doubleArrayOps(allStats), offset, offset + this.statsSize() - 1), (long)allStats[offset + this.statsSize() - 1]);
   }

   public EntropyAggregator(final int numClasses) {
      super(numClasses + 1);
      this.numClasses = numClasses;
   }
}
