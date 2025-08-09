package org.apache.spark.mllib.tree.loss;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005m:Q\u0001B\u0003\t\u0002I1Q\u0001F\u0003\t\u0002UAQ\u0001H\u0001\u0005\u0002uAQAH\u0001\u0005\u0002}\ta\u0001T8tg\u0016\u001c(B\u0001\u0004\b\u0003\u0011awn]:\u000b\u0005!I\u0011\u0001\u0002;sK\u0016T!AC\u0006\u0002\u000b5dG.\u001b2\u000b\u00051i\u0011!B:qCJ\\'B\u0001\b\u0010\u0003\u0019\t\u0007/Y2iK*\t\u0001#A\u0002pe\u001e\u001c\u0001\u0001\u0005\u0002\u0014\u00035\tQA\u0001\u0004M_N\u001cXm]\n\u0003\u0003Y\u0001\"a\u0006\u000e\u000e\u0003aQ\u0011!G\u0001\u0006g\u000e\fG.Y\u0005\u00037a\u0011a!\u00118z%\u00164\u0017A\u0002\u001fj]&$h\bF\u0001\u0013\u0003)1'o\\7TiJLgn\u001a\u000b\u0003A\r\u0002\"aE\u0011\n\u0005\t*!\u0001\u0002'pgNDQ\u0001J\u0002A\u0002\u0015\nAA\\1nKB\u0011a%\f\b\u0003O-\u0002\"\u0001\u000b\r\u000e\u0003%R!AK\t\u0002\rq\u0012xn\u001c;?\u0013\ta\u0003$\u0001\u0004Qe\u0016$WMZ\u0005\u0003]=\u0012aa\u0015;sS:<'B\u0001\u0017\u0019Q\r\u0019\u0011g\u000e\t\u0003eUj\u0011a\r\u0006\u0003i-\t!\"\u00198o_R\fG/[8o\u0013\t14GA\u0003TS:\u001cW-I\u00019\u0003\u0015\tdF\r\u00181Q\r\t\u0011g\u000e\u0015\u0004\u0001E:\u0004"
)
public final class Losses {
   public static Loss fromString(final String name) {
      return Losses$.MODULE$.fromString(name);
   }
}
