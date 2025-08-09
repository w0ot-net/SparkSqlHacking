package org.apache.spark.deploy;

import scala.Tuple2;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Q:a!\u0002\u0004\t\u0002!qaA\u0002\t\u0007\u0011\u0003A\u0011\u0003C\u0003\u0019\u0003\u0011\u0005!\u0004\u0003\u0004\u001c\u0003\u0011\u0005a\u0001\b\u0005\u0006[\u0005!\tAL\u0001\u0011'B\f'o[*vE6LG/\u0016;jYNT!a\u0002\u0005\u0002\r\u0011,\u0007\u000f\\8z\u0015\tI!\"A\u0003ta\u0006\u00148N\u0003\u0002\f\u0019\u00051\u0011\r]1dQ\u0016T\u0011!D\u0001\u0004_J<\u0007CA\b\u0002\u001b\u00051!\u0001E*qCJ\\7+\u001e2nSR,F/\u001b7t'\t\t!\u0003\u0005\u0002\u0014-5\tACC\u0001\u0016\u0003\u0015\u00198-\u00197b\u0013\t9BC\u0001\u0004B]f\u0014VMZ\u0001\u0007y%t\u0017\u000e\u001e \u0004\u0001Q\ta\"A\nhKR\u001cVOY7ji>\u0003XM]1uS>t7\u000f\u0006\u0002\u001eAA\u0011qBH\u0005\u0003?\u0019\u0011Ac\u00159be.\u001cVOY7ji>\u0003XM]1uS>t\u0007\"B\u0011\u0004\u0001\u0004\u0011\u0013AB7bgR,'\u000f\u0005\u0002$U9\u0011A\u0005\u000b\t\u0003KQi\u0011A\n\u0006\u0003Oe\ta\u0001\u0010:p_Rt\u0014BA\u0015\u0015\u0003\u0019\u0001&/\u001a3fM&\u00111\u0006\f\u0002\u0007'R\u0014\u0018N\\4\u000b\u0005%\"\u0012A\u00069beN,7\u000b]1sW\u000e{gN\u001a)s_B,'\u000f^=\u0015\u0005=\u0012\u0004\u0003B\n1E\tJ!!\r\u000b\u0003\rQ+\b\u000f\\33\u0011\u0015\u0019D\u00011\u0001#\u0003\u0011\u0001\u0018-\u001b:"
)
public final class SparkSubmitUtils {
   public static Tuple2 parseSparkConfProperty(final String pair) {
      return SparkSubmitUtils$.MODULE$.parseSparkConfProperty(pair);
   }
}
