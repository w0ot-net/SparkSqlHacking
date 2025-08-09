package org.apache.spark.storage;

import org.apache.spark.SparkException;
import org.apache.spark.annotation.DeveloperApi;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u000512Aa\u0001\u0003\u0001\u001b!A!\u0003\u0001B\u0001B\u0003%1\u0003C\u0003!\u0001\u0011\u0005\u0011EA\nV]J,7m\\4oSj,GM\u00117pG.LEM\u0003\u0002\u0006\r\u000591\u000f^8sC\u001e,'BA\u0004\t\u0003\u0015\u0019\b/\u0019:l\u0015\tI!\"\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002\u0017\u0005\u0019qN]4\u0004\u0001M\u0011\u0001A\u0004\t\u0003\u001fAi\u0011AB\u0005\u0003#\u0019\u0011ab\u00159be.,\u0005pY3qi&|g.\u0001\u0003oC6,\u0007C\u0001\u000b\u001e\u001d\t)2\u0004\u0005\u0002\u001735\tqC\u0003\u0002\u0019\u0019\u00051AH]8pizR\u0011AG\u0001\u0006g\u000e\fG.Y\u0005\u00039e\ta\u0001\u0015:fI\u00164\u0017B\u0001\u0010 \u0005\u0019\u0019FO]5oO*\u0011A$G\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\t\"\u0003CA\u0012\u0001\u001b\u0005!\u0001\"\u0002\n\u0003\u0001\u0004\u0019\u0002F\u0001\u0001'!\t9#&D\u0001)\u0015\tIc!\u0001\u0006b]:|G/\u0019;j_:L!a\u000b\u0015\u0003\u0019\u0011+g/\u001a7pa\u0016\u0014\u0018\t]5"
)
public class UnrecognizedBlockId extends SparkException {
   public UnrecognizedBlockId(final String name) {
      super("Failed to parse " + name + " into a block ID");
   }
}
