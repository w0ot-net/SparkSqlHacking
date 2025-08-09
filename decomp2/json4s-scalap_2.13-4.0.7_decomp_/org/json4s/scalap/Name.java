package org.json4s.scalap;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d2q\u0001B\u0003\u0011\u0002\u0007\u0005A\u0002C\u0003\u0014\u0001\u0011\u0005A\u0003C\u0003\u0019\u0001\u0019\u0005\u0011\u0004C\u0003&\u0001\u0011\u0005cE\u0001\u0003OC6,'B\u0001\u0004\b\u0003\u0019\u00198-\u00197ba*\u0011\u0001\"C\u0001\u0007UN|g\u000eN:\u000b\u0003)\t1a\u001c:h\u0007\u0001\u0019\"\u0001A\u0007\u0011\u00059\tR\"A\b\u000b\u0003A\tQa]2bY\u0006L!AE\b\u0003\r\u0005s\u0017PU3g\u0003\u0019!\u0013N\\5uIQ\tQ\u0003\u0005\u0002\u000f-%\u0011qc\u0004\u0002\u0005+:LG/\u0001\u0003oC6,W#\u0001\u000e\u0011\u0005m\u0011cB\u0001\u000f!!\tir\"D\u0001\u001f\u0015\ty2\"\u0001\u0004=e>|GOP\u0005\u0003C=\ta\u0001\u0015:fI\u00164\u0017BA\u0012%\u0005\u0019\u0019FO]5oO*\u0011\u0011eD\u0001\ti>\u001cFO]5oOR\t!\u0004"
)
public interface Name {
   String name();

   // $FF: synthetic method
   static String toString$(final Name $this) {
      return $this.toString();
   }

   default String toString() {
      return this.name();
   }

   static void $init$(final Name $this) {
   }
}
