package org.apache.spark.mllib.tree.impurity;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M;Qa\u0002\u0005\t\u0002U1Qa\u0006\u0005\t\u0002aAQAI\u0001\u0005\u0002\rBQ\u0001J\u0001\u0005B\u0015BQ\u0001J\u0001\u0005BeBQaQ\u0001\u0005\u0002\u0011CqaR\u0001\u0002\u0002\u0013%\u0001*\u0001\u0003HS:L'BA\u0005\u000b\u0003!IW\u000e];sSRL(BA\u0006\r\u0003\u0011!(/Z3\u000b\u00055q\u0011!B7mY&\u0014'BA\b\u0011\u0003\u0015\u0019\b/\u0019:l\u0015\t\t\"#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002'\u0005\u0019qN]4\u0004\u0001A\u0011a#A\u0007\u0002\u0011\t!q)\u001b8j'\r\t\u0011d\b\t\u00035ui\u0011a\u0007\u0006\u00029\u0005)1oY1mC&\u0011ad\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0005Y\u0001\u0013BA\u0011\t\u0005!IU\u000e];sSRL\u0018A\u0002\u001fj]&$h\bF\u0001\u0016\u0003%\u0019\u0017\r\\2vY\u0006$X\rF\u0002'S9\u0002\"AG\u0014\n\u0005!Z\"A\u0002#pk\ndW\rC\u0003+\u0007\u0001\u00071&\u0001\u0004d_VtGo\u001d\t\u0004512\u0013BA\u0017\u001c\u0005\u0015\t%O]1z\u0011\u0015y3\u00011\u0001'\u0003)!x\u000e^1m\u0007>,h\u000e\u001e\u0015\u0004\u0007E:\u0004C\u0001\u001a6\u001b\u0005\u0019$B\u0001\u001b\u000f\u0003)\tgN\\8uCRLwN\\\u0005\u0003mM\u0012QaU5oG\u0016\f\u0013\u0001O\u0001\u0006c9\nd\u0006\r\u000b\u0005Mibd\bC\u0003<\t\u0001\u0007a%A\u0003d_VtG\u000fC\u0003>\t\u0001\u0007a%A\u0002tk6DQa\u0010\u0003A\u0002\u0019\n!b];n'F,\u0018M]3tQ\r!\u0011'Q\u0011\u0002\u0005\u0006)\u0011G\f\u0019/a\u0005A\u0011N\\:uC:\u001cW-F\u0001F\u001b\u0005\t\u0001fA\u00032o\u0005aqO]5uKJ+\u0007\u000f\\1dKR\t\u0011\n\u0005\u0002K\u001f6\t1J\u0003\u0002M\u001b\u0006!A.\u00198h\u0015\u0005q\u0015\u0001\u00026bm\u0006L!\u0001U&\u0003\r=\u0013'.Z2uQ\r\t\u0011'\u0011\u0015\u0004\u0001E\n\u0005"
)
public final class Gini {
   public static Gini$ instance() {
      return Gini$.MODULE$.instance();
   }

   public static double calculate(final double count, final double sum, final double sumSquares) {
      return Gini$.MODULE$.calculate(count, sum, sumSquares);
   }

   public static double calculate(final double[] counts, final double totalCount) {
      return Gini$.MODULE$.calculate(counts, totalCount);
   }
}
