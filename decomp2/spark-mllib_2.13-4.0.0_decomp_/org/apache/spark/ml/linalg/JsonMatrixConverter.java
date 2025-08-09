package org.apache.spark.ml.linalg;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}:aa\u0002\u0005\t\u0002)\u0011bA\u0002\u000b\t\u0011\u0003QQ\u0003C\u0003\u001d\u0003\u0011\u0005a\u0004C\u0004 \u0003\t\u0007I\u0011\u0001\u0011\t\r%\n\u0001\u0015!\u0003\"\u0011\u0015Q\u0013\u0001\"\u0001,\u0011\u0015Y\u0014\u0001\"\u0001=\u0003MQ5o\u001c8NCR\u0014\u0018\u000e_\"p]Z,'\u000f^3s\u0015\tI!\"\u0001\u0004mS:\fGn\u001a\u0006\u0003\u00171\t!!\u001c7\u000b\u00055q\u0011!B:qCJ\\'BA\b\u0011\u0003\u0019\t\u0007/Y2iK*\t\u0011#A\u0002pe\u001e\u0004\"aE\u0001\u000e\u0003!\u00111CS:p]6\u000bGO]5y\u0007>tg/\u001a:uKJ\u001c\"!\u0001\f\u0011\u0005]QR\"\u0001\r\u000b\u0003e\tQa]2bY\u0006L!a\u0007\r\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\n\u0002\u0013\rd\u0017m]:OC6,W#A\u0011\u0011\u0005\t:S\"A\u0012\u000b\u0005\u0011*\u0013\u0001\u00027b]\u001eT\u0011AJ\u0001\u0005U\u00064\u0018-\u0003\u0002)G\t11\u000b\u001e:j]\u001e\f!b\u00197bgNt\u0015-\\3!\u0003!1'o\\7Kg>tGC\u0001\u00170!\t\u0019R&\u0003\u0002/\u0011\t1Q*\u0019;sSbDQ\u0001M\u0003A\u0002E\nAA[:p]B\u0011!'\u000f\b\u0003g]\u0002\"\u0001\u000e\r\u000e\u0003UR!AN\u000f\u0002\rq\u0012xn\u001c;?\u0013\tA\u0004$\u0001\u0004Qe\u0016$WMZ\u0005\u0003QiR!\u0001\u000f\r\u0002\rQ|'j]8o)\t\tT\bC\u0003?\r\u0001\u0007A&A\u0001n\u0001"
)
public final class JsonMatrixConverter {
   public static String toJson(final Matrix m) {
      return JsonMatrixConverter$.MODULE$.toJson(m);
   }

   public static Matrix fromJson(final String json) {
      return JsonMatrixConverter$.MODULE$.fromJson(json);
   }

   public static String className() {
      return JsonMatrixConverter$.MODULE$.className();
   }
}
