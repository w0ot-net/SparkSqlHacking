package org.apache.spark.mllib.evaluation.binary;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000512\u0001\u0002C\u0005\u0011\u0002\u0007\u00051\"\u0006\u0005\u00069\u0001!\tA\b\u0005\u0006E\u00011\ta\t\u0005\u0006O\u00011\ta\t\u0005\u0006Q\u00011\ta\t\u0005\u0006S\u00011\ta\t\u0005\u0006U\u0001!\ta\t\u0005\u0006W\u0001!\ta\t\u0002\u0016\u0005&t\u0017M]=D_:4Wo]5p]6\u000bGO]5y\u0015\tQ1\"\u0001\u0004cS:\f'/\u001f\u0006\u0003\u00195\t!\"\u001a<bYV\fG/[8o\u0015\tqq\"A\u0003nY2L'M\u0003\u0002\u0011#\u0005)1\u000f]1sW*\u0011!cE\u0001\u0007CB\f7\r[3\u000b\u0003Q\t1a\u001c:h'\t\u0001a\u0003\u0005\u0002\u001855\t\u0001DC\u0001\u001a\u0003\u0015\u00198-\u00197b\u0013\tY\u0002D\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0004\u0001Q\tq\u0004\u0005\u0002\u0018A%\u0011\u0011\u0005\u0007\u0002\u0005+:LG/A\u000bxK&<\u0007\u000e^3e)J,X\rU8tSRLg/Z:\u0016\u0003\u0011\u0002\"aF\u0013\n\u0005\u0019B\"A\u0002#pk\ndW-\u0001\fxK&<\u0007\u000e^3e\r\u0006d7/\u001a)pg&$\u0018N^3t\u0003Y9X-[4ii\u0016$g)\u00197tK:+w-\u0019;jm\u0016\u001c\u0018!F<fS\u001eDG/\u001a3UeV,g*Z4bi&4Xm]\u0001\u0012o\u0016Lw\r\u001b;fIB{7/\u001b;jm\u0016\u001c\u0018!E<fS\u001eDG/\u001a3OK\u001e\fG/\u001b<fg\u0002"
)
public interface BinaryConfusionMatrix {
   double weightedTruePositives();

   double weightedFalsePositives();

   double weightedFalseNegatives();

   double weightedTrueNegatives();

   // $FF: synthetic method
   static double weightedPositives$(final BinaryConfusionMatrix $this) {
      return $this.weightedPositives();
   }

   default double weightedPositives() {
      return this.weightedTruePositives() + this.weightedFalseNegatives();
   }

   // $FF: synthetic method
   static double weightedNegatives$(final BinaryConfusionMatrix $this) {
      return $this.weightedNegatives();
   }

   default double weightedNegatives() {
      return this.weightedFalsePositives() + this.weightedTrueNegatives();
   }

   static void $init$(final BinaryConfusionMatrix $this) {
   }
}
