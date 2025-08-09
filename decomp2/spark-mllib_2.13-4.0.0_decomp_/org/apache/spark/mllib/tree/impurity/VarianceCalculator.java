package org.apache.spark.mllib.tree.impurity;

import java.lang.invoke.SerializedLambda;
import scala.Predef.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I3Q\u0001D\u0007\u0001'eA\u0011B\b\u0001\u0003\u0002\u0003\u0006I\u0001I\u0015\t\u0011)\u0002!\u00111A\u0005\u0002-B\u0001b\f\u0001\u0003\u0002\u0004%\t\u0001\r\u0005\tm\u0001\u0011\t\u0011)Q\u0005Y!)q\u0007\u0001C\u0001q!)A\b\u0001C\u0001{!)a\b\u0001C\u0001\u007f!)\u0001\t\u0001C\u0001\u0003\")!\t\u0001C\u0001\u0003\")1\t\u0001C!\t\"Y\u0001\u000b\u0001I\u0001\u0004\u0003\u0005I\u0011B)*\u0005I1\u0016M]5b]\u000e,7)\u00197dk2\fGo\u001c:\u000b\u00059y\u0011\u0001C5naV\u0014\u0018\u000e^=\u000b\u0005A\t\u0012\u0001\u0002;sK\u0016T!AE\n\u0002\u000b5dG.\u001b2\u000b\u0005Q)\u0012!B:qCJ\\'B\u0001\f\u0018\u0003\u0019\t\u0007/Y2iK*\t\u0001$A\u0002pe\u001e\u001c\"\u0001\u0001\u000e\u0011\u0005maR\"A\u0007\n\u0005ui!AE%naV\u0014\u0018\u000e^=DC2\u001cW\u000f\\1u_J\fQa\u001d;biN\u001c\u0001\u0001E\u0002\"I\u0019j\u0011A\t\u0006\u0002G\u0005)1oY1mC&\u0011QE\t\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003C\u001dJ!\u0001\u000b\u0012\u0003\r\u0011{WO\u00197f\u0013\tqB$\u0001\u0005sC^\u001cu.\u001e8u+\u0005a\u0003CA\u0011.\u0013\tq#E\u0001\u0003M_:<\u0017\u0001\u0004:bo\u000e{WO\u001c;`I\u0015\fHCA\u00195!\t\t#'\u0003\u00024E\t!QK\\5u\u0011\u001d)4!!AA\u00021\n1\u0001\u001f\u00132\u0003%\u0011\u0018m^\"pk:$\b%\u0001\u0004=S:LGO\u0010\u000b\u0004siZ\u0004CA\u000e\u0001\u0011\u0015qR\u00011\u0001!\u0011\u0015QS\u00011\u0001-\u0003\u0011\u0019w\u000e]=\u0016\u0003e\n\u0011bY1mGVd\u0017\r^3\u0015\u0003\u0019\nQaY8v]R,\u0012AJ\u0001\baJ,G-[2u\u0003!!xn\u0015;sS:<G#A#\u0011\u0005\u0019keBA$L!\tA%%D\u0001J\u0015\tQu$\u0001\u0004=e>|GOP\u0005\u0003\u0019\n\na\u0001\u0015:fI\u00164\u0017B\u0001(P\u0005\u0019\u0019FO]5oO*\u0011AJI\u0001\fgV\u0004XM\u001d\u0013ti\u0006$8/F\u0001!\u0001"
)
public class VarianceCalculator extends ImpurityCalculator {
   private long rawCount;

   // $FF: synthetic method
   private double[] super$stats() {
      return super.stats();
   }

   public long rawCount() {
      return this.rawCount;
   }

   public void rawCount_$eq(final long x$1) {
      this.rawCount = x$1;
   }

   public VarianceCalculator copy() {
      return new VarianceCalculator((double[])super.stats().clone(), this.rawCount());
   }

   public double calculate() {
      return Variance$.MODULE$.calculate(super.stats()[0], super.stats()[1], super.stats()[2]);
   }

   public double count() {
      return super.stats()[0];
   }

   public double predict() {
      return this.count() == (double)0 ? (double)0.0F : super.stats()[1] / this.count();
   }

   public String toString() {
      double var10000 = super.stats()[0];
      return "VarianceAggregator(cnt = " + var10000 + ", sum = " + super.stats()[1] + ", sum2 = " + super.stats()[2] + ")";
   }

   public VarianceCalculator(final double[] stats, final long rawCount) {
      this.rawCount = rawCount;
      super(stats);
      .MODULE$.require(super.stats().length == 3, () -> "VarianceCalculator requires sufficient statistics array stats to be of length 3, but was given array of length " + this.super$stats().length + ".");
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
