package org.apache.spark.ml.util;

import org.apache.spark.ml.param.Params;
import scala.collection.immutable.Map;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0011;a!\u0002\u0004\t\u0002!\u0001bA\u0002\n\u0007\u0011\u0003A1\u0003C\u0003\u001b\u0003\u0011\u0005A\u0004C\u0003\u001e\u0003\u0011\u0005a\u0004C\u00036\u0003\u0011%a'\u0001\fNKR\f\u0017\t\\4pe&$\b.\u001c*fC\u0012<&/\u001b;f\u0015\t9\u0001\"\u0001\u0003vi&d'BA\u0005\u000b\u0003\tiGN\u0003\u0002\f\u0019\u0005)1\u000f]1sW*\u0011QBD\u0001\u0007CB\f7\r[3\u000b\u0003=\t1a\u001c:h!\t\t\u0012!D\u0001\u0007\u0005YiU\r^1BY\u001e|'/\u001b;i[J+\u0017\rZ,sSR,7CA\u0001\u0015!\t)\u0002$D\u0001\u0017\u0015\u00059\u0012!B:dC2\f\u0017BA\r\u0017\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\u001a\u0001\u0001F\u0001\u0011\u0003%9W\r^+jI6\u000b\u0007\u000f\u0006\u0002 gA!\u0001e\n\u0016.\u001d\t\tS\u0005\u0005\u0002#-5\t1E\u0003\u0002%7\u00051AH]8pizJ!A\n\f\u0002\rA\u0013X\rZ3g\u0013\tA\u0013FA\u0002NCBT!A\n\f\u0011\u0005\u0001Z\u0013B\u0001\u0017*\u0005\u0019\u0019FO]5oOB\u0011a&M\u0007\u0002_)\u0011\u0001\u0007C\u0001\u0006a\u0006\u0014\u0018-\\\u0005\u0003e=\u0012a\u0001U1sC6\u001c\b\"\u0002\u001b\u0004\u0001\u0004i\u0013\u0001C5ogR\fgnY3\u0002\u001b\u001d,G/V5e\u001b\u0006\u0004\u0018*\u001c9m)\t94\tE\u00029{\u0001s!!O\u001e\u000f\u0005\tR\u0014\"A\f\n\u0005q2\u0012a\u00029bG.\fw-Z\u0005\u0003}}\u0012A\u0001T5ti*\u0011AH\u0006\t\u0005+\u0005SS&\u0003\u0002C-\t1A+\u001e9mKJBQ\u0001\u000e\u0003A\u00025\u0002"
)
public final class MetaAlgorithmReadWrite {
   public static Map getUidMap(final Params instance) {
      return MetaAlgorithmReadWrite$.MODULE$.getUidMap(instance);
   }
}
