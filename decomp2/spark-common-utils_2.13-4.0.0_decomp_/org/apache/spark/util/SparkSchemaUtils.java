package org.apache.spark.util;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%:a\u0001B\u0003\t\u0002\u001diaAB\b\u0006\u0011\u00039\u0001\u0003C\u0003\u0018\u0003\u0011\u0005\u0011\u0004C\u0003\u001b\u0003\u0011\u00051$\u0001\tTa\u0006\u00148nU2iK6\fW\u000b^5mg*\u0011aaB\u0001\u0005kRLGN\u0003\u0002\t\u0013\u0005)1\u000f]1sW*\u0011!bC\u0001\u0007CB\f7\r[3\u000b\u00031\t1a\u001c:h!\tq\u0011!D\u0001\u0006\u0005A\u0019\u0006/\u0019:l'\u000eDW-\\1Vi&d7o\u0005\u0002\u0002#A\u0011!#F\u0007\u0002')\tA#A\u0003tG\u0006d\u0017-\u0003\u0002\u0017'\t1\u0011I\\=SK\u001a\fa\u0001P5oSRt4\u0001\u0001\u000b\u0002\u001b\u0005!Rm]2ba\u0016lU\r^1DQ\u0006\u0014\u0018m\u0019;feN$\"\u0001H\u0014\u0011\u0005u!cB\u0001\u0010#!\ty2#D\u0001!\u0015\t\t\u0003$\u0001\u0004=e>|GOP\u0005\u0003GM\ta\u0001\u0015:fI\u00164\u0017BA\u0013'\u0005\u0019\u0019FO]5oO*\u00111e\u0005\u0005\u0006Q\r\u0001\r\u0001H\u0001\u0004gR\u0014\b"
)
public final class SparkSchemaUtils {
   public static String escapeMetaCharacters(final String str) {
      return SparkSchemaUtils$.MODULE$.escapeMetaCharacters(str);
   }
}
