package org.apache.spark.mllib.tree.impurity;

import scala.collection.ArrayOps.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-3Q!\u0002\u0004\u0001\u0019IA\u0001B\n\u0001\u0003\u0002\u0003\u0006Ia\n\u0005\u0006W\u0001!\t\u0001\f\u0005\u0006_\u0001!\t\u0001\r\u0005\u0006\t\u0002!\t!\u0012\u0002\u000f\u000f&t\u0017.Q4he\u0016<\u0017\r^8s\u0015\t9\u0001\"\u0001\u0005j[B,(/\u001b;z\u0015\tI!\"\u0001\u0003ue\u0016,'BA\u0006\r\u0003\u0015iG\u000e\\5c\u0015\tia\"A\u0003ta\u0006\u00148N\u0003\u0002\u0010!\u00051\u0011\r]1dQ\u0016T\u0011!E\u0001\u0004_J<7c\u0001\u0001\u0014/A\u0011A#F\u0007\u0002\r%\u0011aC\u0002\u0002\u0013\u00136\u0004XO]5us\u0006;wM]3hCR|'\u000f\u0005\u0002\u0019G9\u0011\u0011\u0004\t\b\u00035yi\u0011a\u0007\u0006\u00039u\ta\u0001\u0010:p_Rt4\u0001A\u0005\u0002?\u0005)1oY1mC&\u0011\u0011EI\u0001\ba\u0006\u001c7.Y4f\u0015\u0005y\u0012B\u0001\u0013&\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\t\t#%\u0001\u0006ok6\u001cE.Y:tKN\u0004\"\u0001K\u0015\u000e\u0003\tJ!A\u000b\u0012\u0003\u0007%sG/\u0001\u0004=S:LGO\u0010\u000b\u0003[9\u0002\"\u0001\u0006\u0001\t\u000b\u0019\u0012\u0001\u0019A\u0014\u0002\rU\u0004H-\u0019;f)\u0019\tD\u0007\u0010 A\u0005B\u0011\u0001FM\u0005\u0003g\t\u0012A!\u00168ji\")Qg\u0001a\u0001m\u0005A\u0011\r\u001c7Ti\u0006$8\u000fE\u0002)oeJ!\u0001\u000f\u0012\u0003\u000b\u0005\u0013(/Y=\u0011\u0005!R\u0014BA\u001e#\u0005\u0019!u.\u001e2mK\")Qh\u0001a\u0001O\u00051qN\u001a4tKRDQaP\u0002A\u0002e\nQ\u0001\\1cK2DQ!Q\u0002A\u0002\u001d\n!B\\;n'\u0006l\u0007\u000f\\3t\u0011\u0015\u00195\u00011\u0001:\u00031\u0019\u0018-\u001c9mK^+\u0017n\u001a5u\u000359W\r^\"bY\u000e,H.\u0019;peR\u0019a)\u0013&\u0011\u0005Q9\u0015B\u0001%\u0007\u000599\u0015N\\5DC2\u001cW\u000f\\1u_JDQ!\u000e\u0003A\u0002YBQ!\u0010\u0003A\u0002\u001d\u0002"
)
public class GiniAggregator extends ImpurityAggregator {
   private final int numClasses;

   public void update(final double[] allStats, final int offset, final double label, final int numSamples, final double sampleWeight) {
      if (label >= (double)this.numClasses) {
         throw new IllegalArgumentException("GiniAggregator given label " + label + " but requires label < numClasses (= " + this.numClasses + ").");
      } else if (label < (double)0) {
         throw new IllegalArgumentException("GiniAggregator given label " + label + "but requires label to be non-negative.");
      } else {
         int var8 = offset + (int)label;
         allStats[var8] += (double)numSamples * sampleWeight;
         int var9 = offset + this.statsSize() - 1;
         allStats[var9] += (double)numSamples;
      }
   }

   public GiniCalculator getCalculator(final double[] allStats, final int offset) {
      return new GiniCalculator((double[]).MODULE$.slice$extension(scala.Predef..MODULE$.doubleArrayOps(allStats), offset, offset + this.statsSize() - 1), (long)allStats[offset + this.statsSize() - 1]);
   }

   public GiniAggregator(final int numClasses) {
      super(numClasses + 1);
      this.numClasses = numClasses;
   }
}
