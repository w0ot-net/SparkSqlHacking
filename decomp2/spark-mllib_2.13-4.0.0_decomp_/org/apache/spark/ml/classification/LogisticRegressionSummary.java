package org.apache.spark.ml.classification;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!3q!\u0002\u0004\u0011\u0002\u0007\u0005\u0012\u0003C\u0003\u001d\u0001\u0011\u0005Q\u0004C\u0003\"\u0001\u0019\u0005!\u0005C\u00038\u0001\u0019\u0005!\u0005C\u0003<\u0001\u0011\u0005AHA\rM_\u001eL7\u000f^5d%\u0016<'/Z:tS>t7+^7nCJL(BA\u0004\t\u00039\u0019G.Y:tS\u001aL7-\u0019;j_:T!!\u0003\u0006\u0002\u00055d'BA\u0006\r\u0003\u0015\u0019\b/\u0019:l\u0015\tia\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u001f\u0005\u0019qN]4\u0004\u0001M\u0019\u0001A\u0005\r\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\r\u0005s\u0017PU3g!\tI\"$D\u0001\u0007\u0013\tYbAA\u000bDY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8Tk6l\u0017M]=\u0002\r\u0011Jg.\u001b;%)\u0005q\u0002CA\n \u0013\t\u0001CC\u0001\u0003V]&$\u0018A\u00049s_\n\f'-\u001b7jif\u001cu\u000e\\\u000b\u0002GA\u0011Ae\u000b\b\u0003K%\u0002\"A\n\u000b\u000e\u0003\u001dR!\u0001\u000b\t\u0002\rq\u0012xn\u001c;?\u0013\tQC#\u0001\u0004Qe\u0016$WMZ\u0005\u0003Y5\u0012aa\u0015;sS:<'B\u0001\u0016\u0015Q\r\u0011q&\u000e\t\u0003aMj\u0011!\r\u0006\u0003e)\t!\"\u00198o_R\fG/[8o\u0013\t!\u0014GA\u0003TS:\u001cW-I\u00017\u0003\u0015\td&\u000e\u00181\u0003-1W-\u0019;ve\u0016\u001c8i\u001c7)\u0007\ry\u0013(I\u0001;\u0003\u0015\tdF\u000e\u00181\u0003!\t7OQ5oCJLX#A\u001f\u0011\u0005eq\u0014BA \u0007\u0005}\u0011\u0015N\\1ss2{w-[:uS\u000e\u0014Vm\u001a:fgNLwN\\*v[6\f'/\u001f\u0015\u0004\t=\n\u0015%\u0001\"\u0002\u000bIr3G\f\u0019*\t\u0001qDIR\u0005\u0003\u000b\u001a\u0011Q\u0004T8hSN$\u0018n\u0019*fOJ,7o]5p]N+X.\\1ss&k\u0007\u000f\\\u0005\u0003\u000f\u001a\u0011\u0011\u0005T8hSN$\u0018n\u0019*fOJ,7o]5p]R\u0013\u0018-\u001b8j]\u001e\u001cV/\\7bef\u0004"
)
public interface LogisticRegressionSummary extends ClassificationSummary {
   String probabilityCol();

   String featuresCol();

   // $FF: synthetic method
   static BinaryLogisticRegressionSummary asBinary$(final LogisticRegressionSummary $this) {
      return $this.asBinary();
   }

   default BinaryLogisticRegressionSummary asBinary() {
      if (this instanceof BinaryLogisticRegressionSummary var3) {
         return var3;
      } else {
         throw new RuntimeException("Cannot cast to a binary summary.");
      }
   }

   static void $init$(final LogisticRegressionSummary $this) {
   }
}
