package org.apache.spark.mllib.random;

import org.apache.commons.math3.distribution.PoissonDistribution;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005U3A!\u0003\u0006\u0001+!A1\u0005\u0001BC\u0002\u0013\u0005A\u0005\u0003\u0005/\u0001\t\u0005\t\u0015!\u0003!\u0011\u0015\u0001\u0004\u0001\"\u00012\u0011\u001d1\u0004A1A\u0005\n]BaA\u0011\u0001!\u0002\u0013A\u0004\"B\"\u0001\t\u0003\"\u0005\"\u0002$\u0001\t\u0003:\u0005\"B)\u0001\t\u0003\u0012&\u0001\u0005)pSN\u001cxN\\$f]\u0016\u0014\u0018\r^8s\u0015\tYA\"\u0001\u0004sC:$w.\u001c\u0006\u0003\u001b9\tQ!\u001c7mS\nT!a\u0004\t\u0002\u000bM\u0004\u0018M]6\u000b\u0005E\u0011\u0012AB1qC\u000eDWMC\u0001\u0014\u0003\ry'oZ\u0002\u0001'\r\u0001a\u0003\b\t\u0003/ii\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0007uq\u0002%D\u0001\u000b\u0013\ty\"BA\nSC:$w.\u001c#bi\u0006<UM\\3sCR|'\u000f\u0005\u0002\u0018C%\u0011!\u0005\u0007\u0002\u0007\t>,(\r\\3\u0002\t5,\u0017M\\\u000b\u0002A!\u001a\u0011A\n\u0017\u0011\u0005\u001dRS\"\u0001\u0015\u000b\u0005%r\u0011AC1o]>$\u0018\r^5p]&\u00111\u0006\u000b\u0002\u0006'&t7-Z\u0011\u0002[\u0005)\u0011GL\u0019/a\u0005)Q.Z1oA!\u001a!A\n\u0017\u0002\rqJg.\u001b;?)\t\u00114\u0007\u0005\u0002\u001e\u0001!)1e\u0001a\u0001A!\u001a1G\n\u0017)\u0007\r1C&A\u0002s]\u001e,\u0012\u0001\u000f\t\u0003s\u0001k\u0011A\u000f\u0006\u0003wq\nA\u0002Z5tiJL'-\u001e;j_:T!!\u0010 \u0002\u000b5\fG\u000f[\u001a\u000b\u0005}\u0002\u0012aB2p[6|gn]\u0005\u0003\u0003j\u00121\u0003U8jgN|g\u000eR5tiJL'-\u001e;j_:\fAA\u001d8hA\u0005Ia.\u001a=u-\u0006dW/\u001a\u000b\u0002A!\u001aaA\n\u0017\u0002\u000fM,GoU3fIR\u0011\u0001j\u0013\t\u0003/%K!A\u0013\r\u0003\tUs\u0017\u000e\u001e\u0005\u0006\u0019\u001e\u0001\r!T\u0001\u0005g\u0016,G\r\u0005\u0002\u0018\u001d&\u0011q\n\u0007\u0002\u0005\u0019>tw\rK\u0002\bM1\nAaY8qsR\t!\u0007K\u0002\tM1B3\u0001\u0001\u0014-\u0001"
)
public class PoissonGenerator implements RandomDataGenerator {
   private final double mean;
   private final PoissonDistribution rng;

   public double mean() {
      return this.mean;
   }

   private PoissonDistribution rng() {
      return this.rng;
   }

   public double nextValue() {
      return (double)this.rng().sample();
   }

   public void setSeed(final long seed) {
      this.rng().reseedRandomGenerator(seed);
   }

   public PoissonGenerator copy() {
      return new PoissonGenerator(this.mean());
   }

   public PoissonGenerator(final double mean) {
      this.mean = mean;
      this.rng = new PoissonDistribution(mean);
   }
}
