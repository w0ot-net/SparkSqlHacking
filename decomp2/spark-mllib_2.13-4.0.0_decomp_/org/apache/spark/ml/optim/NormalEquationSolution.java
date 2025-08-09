package org.apache.spark.ml.optim;

import scala.Option;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M2Q\u0001C\u0005\u0001\u0013MA\u0001B\u0007\u0001\u0003\u0006\u0004%\t\u0001\b\u0005\tG\u0001\u0011\t\u0011)A\u0005;!AA\u0005\u0001BC\u0002\u0013\u0005Q\u0005\u0003\u0005*\u0001\t\u0005\t\u0015!\u0003'\u0011!Q\u0003A!b\u0001\n\u0003)\u0003\u0002C\u0016\u0001\u0005\u0003\u0005\u000b\u0011\u0002\u0014\t\u000b1\u0002A\u0011A\u0017\u0003-9{'/\\1m\u000bF,\u0018\r^5p]N{G.\u001e;j_:T!AC\u0006\u0002\u000b=\u0004H/[7\u000b\u00051i\u0011AA7m\u0015\tqq\"A\u0003ta\u0006\u00148N\u0003\u0002\u0011#\u00051\u0011\r]1dQ\u0016T\u0011AE\u0001\u0004_J<7C\u0001\u0001\u0015!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f*fM\u0006a1m\\3gM&\u001c\u0017.\u001a8ug\u000e\u0001Q#A\u000f\u0011\u0007Uq\u0002%\u0003\u0002 -\t)\u0011I\u001d:bsB\u0011Q#I\u0005\u0003EY\u0011a\u0001R8vE2,\u0017!D2pK\u001a4\u0017nY5f]R\u001c\b%A\u0003bC&sg/F\u0001'!\r)r%H\u0005\u0003QY\u0011aa\u00149uS>t\u0017AB1b\u0013:4\b%\u0001\tpE*,7\r^5wK\"K7\u000f^8ss\u0006\trN\u00196fGRLg/\u001a%jgR|'/\u001f\u0011\u0002\rqJg.\u001b;?)\u0011q\u0003'\r\u001a\u0011\u0005=\u0002Q\"A\u0005\t\u000bi9\u0001\u0019A\u000f\t\u000b\u0011:\u0001\u0019\u0001\u0014\t\u000b):\u0001\u0019\u0001\u0014"
)
public class NormalEquationSolution {
   private final double[] coefficients;
   private final Option aaInv;
   private final Option objectiveHistory;

   public double[] coefficients() {
      return this.coefficients;
   }

   public Option aaInv() {
      return this.aaInv;
   }

   public Option objectiveHistory() {
      return this.objectiveHistory;
   }

   public NormalEquationSolution(final double[] coefficients, final Option aaInv, final Option objectiveHistory) {
      this.coefficients = coefficients;
      this.aaInv = aaInv;
      this.objectiveHistory = objectiveHistory;
   }
}
