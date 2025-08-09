package org.apache.spark.mllib.random;

import org.apache.commons.math3.distribution.WeibullDistribution;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)3Aa\u0003\u0007\u0001/!AQ\u0005\u0001BC\u0002\u0013\u0005a\u0005\u0003\u0005(\u0001\t\u0005\t\u0015!\u0003#\u0011!A\u0003A!b\u0001\n\u00031\u0003\u0002C\u0015\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0012\t\u000b)\u0002A\u0011A\u0016\t\u000f=\u0002!\u0019!C\u0005a!11\b\u0001Q\u0001\nEBQ\u0001\u0010\u0001\u0005BuBQA\u0010\u0001\u0005B}BQ\u0001\u0013\u0001\u0005B%\u0013\u0001cV3jEVdGnR3oKJ\fGo\u001c:\u000b\u00055q\u0011A\u0002:b]\u0012|WN\u0003\u0002\u0010!\u0005)Q\u000e\u001c7jE*\u0011\u0011CE\u0001\u0006gB\f'o\u001b\u0006\u0003'Q\ta!\u00199bG\",'\"A\u000b\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001Ab\u0004\u0005\u0002\u001a95\t!DC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\ti\"D\u0001\u0004B]f\u0014VM\u001a\t\u0004?\u0001\u0012S\"\u0001\u0007\n\u0005\u0005b!a\u0005*b]\u0012|W\u000eR1uC\u001e+g.\u001a:bi>\u0014\bCA\r$\u0013\t!#D\u0001\u0004E_V\u0014G.Z\u0001\u0006C2\u0004\b.Y\u000b\u0002E\u00051\u0011\r\u001c9iC\u0002\nAAY3uC\u0006)!-\u001a;bA\u00051A(\u001b8jiz\"2\u0001L\u0017/!\ty\u0002\u0001C\u0003&\u000b\u0001\u0007!\u0005C\u0003)\u000b\u0001\u0007!%A\u0002s]\u001e,\u0012!\r\t\u0003eej\u0011a\r\u0006\u0003iU\nA\u0002Z5tiJL'-\u001e;j_:T!AN\u001c\u0002\u000b5\fG\u000f[\u001a\u000b\u0005a\u0012\u0012aB2p[6|gn]\u0005\u0003uM\u00121cV3jEVdG\u000eR5tiJL'-\u001e;j_:\fAA\u001d8hA\u0005Ia.\u001a=u-\u0006dW/\u001a\u000b\u0002E\u000591/\u001a;TK\u0016$GC\u0001!D!\tI\u0012)\u0003\u0002C5\t!QK\\5u\u0011\u0015!\u0015\u00021\u0001F\u0003\u0011\u0019X-\u001a3\u0011\u0005e1\u0015BA$\u001b\u0005\u0011auN\\4\u0002\t\r|\u0007/\u001f\u000b\u0002Y\u0001"
)
public class WeibullGenerator implements RandomDataGenerator {
   private final double alpha;
   private final double beta;
   private final WeibullDistribution rng;

   public double alpha() {
      return this.alpha;
   }

   public double beta() {
      return this.beta;
   }

   private WeibullDistribution rng() {
      return this.rng;
   }

   public double nextValue() {
      return this.rng().sample();
   }

   public void setSeed(final long seed) {
      this.rng().reseedRandomGenerator(seed);
   }

   public WeibullGenerator copy() {
      return new WeibullGenerator(this.alpha(), this.beta());
   }

   public WeibullGenerator(final double alpha, final double beta) {
      this.alpha = alpha;
      this.beta = beta;
      this.rng = new WeibullDistribution(alpha, beta);
   }
}
