package org.apache.spark.ml.util;

import org.apache.spark.annotation.Unstable;
import scala.reflect.ScalaSignature;

@Unstable
@ScalaSignature(
   bytes = "\u0006\u0005}2q!\u0002\u0004\u0011\u0002\u0007\u0005\u0011\u0003C\u0003\u001d\u0001\u0011\u0005Q\u0004C\u0003\"\u0001\u0019\u0005!\u0005C\u00038\u0001\u0019\u0005!\u0005\u0003\u0004:\u0001\u0011\u0005\u0001B\t\u0002\u0011\u001b23uN]7biJ+w-[:uKJT!a\u0002\u0005\u0002\tU$\u0018\u000e\u001c\u0006\u0003\u0013)\t!!\u001c7\u000b\u0005-a\u0011!B:qCJ\\'BA\u0007\u000f\u0003\u0019\t\u0007/Y2iK*\tq\"A\u0002pe\u001e\u001c\u0001aE\u0002\u0001%a\u0001\"a\u0005\f\u000e\u0003QQ\u0011!F\u0001\u0006g\u000e\fG.Y\u0005\u0003/Q\u0011a!\u00118z%\u00164\u0007CA\r\u001b\u001b\u00051\u0011BA\u000e\u0007\u00059iEj\u0016:ji\u0016\u0014hi\u001c:nCR\fa\u0001J5oSR$C#\u0001\u0010\u0011\u0005My\u0012B\u0001\u0011\u0015\u0005\u0011)f.\u001b;\u0002\r\u0019|'/\\1u)\u0005\u0019\u0003C\u0001\u0013,\u001d\t)\u0013\u0006\u0005\u0002')5\tqE\u0003\u0002)!\u00051AH]8pizJ!A\u000b\u000b\u0002\rA\u0013X\rZ3g\u0013\taSF\u0001\u0004TiJLgn\u001a\u0006\u0003UQA3AA\u00186!\t\u00014'D\u00012\u0015\t\u0011$\"\u0001\u0006b]:|G/\u0019;j_:L!\u0001N\u0019\u0003\u000bMKgnY3\"\u0003Y\nQA\r\u00185]A\n\u0011b\u001d;bO\u0016t\u0015-\\3)\u0007\ryS'A\u0005tQ>\u0014HOT1nK\"\u0012\u0001a\u000f\t\u0003aqJ!!P\u0019\u0003\u0011Us7\u000f^1cY\u0016D3\u0001A\u00186\u0001"
)
public interface MLFormatRegister extends MLWriterFormat {
   String format();

   String stageName();

   // $FF: synthetic method
   static String shortName$(final MLFormatRegister $this) {
      return $this.shortName();
   }

   default String shortName() {
      String var10000 = this.format();
      return var10000 + "+" + this.stageName();
   }

   static void $init$(final MLFormatRegister $this) {
   }
}
