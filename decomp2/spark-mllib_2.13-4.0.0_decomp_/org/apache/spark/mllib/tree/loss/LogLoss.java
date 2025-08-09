package org.apache.spark.mllib.tree.loss;

import org.apache.spark.mllib.tree.model.TreeEnsembleModel;
import org.apache.spark.rdd.RDD;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005);Qa\u0002\u0005\t\u0002U1Qa\u0006\u0005\t\u0002aAQAI\u0001\u0005\u0002\rBQ\u0001J\u0001\u0005B\u0015BaAN\u0001\u0005B99\u0004B\u0002\u001e\u0002\t\u0003r1\bC\u0004?\u0003\u0005\u0005I\u0011B \u0002\u000f1{w\rT8tg*\u0011\u0011BC\u0001\u0005Y>\u001c8O\u0003\u0002\f\u0019\u0005!AO]3f\u0015\tia\"A\u0003nY2L'M\u0003\u0002\u0010!\u0005)1\u000f]1sW*\u0011\u0011CE\u0001\u0007CB\f7\r[3\u000b\u0003M\t1a\u001c:h\u0007\u0001\u0001\"AF\u0001\u000e\u0003!\u0011q\u0001T8h\u0019>\u001c8oE\u0002\u00023}\u0001\"AG\u000f\u000e\u0003mQ\u0011\u0001H\u0001\u0006g\u000e\fG.Y\u0005\u0003=m\u0011a!\u00118z%\u00164\u0007C\u0001\f!\u0013\t\t\u0003B\u0001\nDY\u0006\u001c8/\u001b4jG\u0006$\u0018n\u001c8M_N\u001c\u0018A\u0002\u001fj]&$h\bF\u0001\u0016\u0003!9'/\u00193jK:$Hc\u0001\u0014*WA\u0011!dJ\u0005\u0003Qm\u0011a\u0001R8vE2,\u0007\"\u0002\u0016\u0004\u0001\u00041\u0013A\u00039sK\u0012L7\r^5p]\")Af\u0001a\u0001M\u0005)A.\u00192fY\"\u001a1A\f\u001b\u0011\u0005=\u0012T\"\u0001\u0019\u000b\u0005Er\u0011AC1o]>$\u0018\r^5p]&\u00111\u0007\r\u0002\u0006'&t7-Z\u0011\u0002k\u0005)\u0011G\f\u001a/a\u0005a1m\\7qkR,WI\u001d:peR\u0019a\u0005O\u001d\t\u000b)\"\u0001\u0019\u0001\u0014\t\u000b1\"\u0001\u0019\u0001\u0014\u0002%\r|W\u000e];uKB\u0013xNY1cS2LG/\u001f\u000b\u0003MqBQ!P\u0003A\u0002\u0019\na!\\1sO&t\u0017\u0001D<sSR,'+\u001a9mC\u000e,G#\u0001!\u0011\u0005\u00053U\"\u0001\"\u000b\u0005\r#\u0015\u0001\u00027b]\u001eT\u0011!R\u0001\u0005U\u00064\u0018-\u0003\u0002H\u0005\n1qJ\u00196fGRD3!\u0001\u00185Q\r\u0001a\u0006\u000e"
)
public final class LogLoss {
   public static double gradient(final double prediction, final double label) {
      return LogLoss$.MODULE$.gradient(prediction, label);
   }

   public static double computeError(final TreeEnsembleModel model, final RDD data) {
      return LogLoss$.MODULE$.computeError(model, data);
   }
}
