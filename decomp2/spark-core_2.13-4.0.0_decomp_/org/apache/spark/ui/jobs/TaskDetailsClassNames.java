package org.apache.spark.ui.jobs;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0001;a!\u0005\n\t\u0002YabA\u0002\u0010\u0013\u0011\u00031r\u0004C\u0003'\u0003\u0011\u0005\u0001\u0006C\u0004*\u0003\t\u0007I\u0011\u0001\u0016\t\rM\n\u0001\u0015!\u0003,\u0011\u001d!\u0014A1A\u0005\u0002)Ba!N\u0001!\u0002\u0013Y\u0003b\u0002\u001c\u0002\u0005\u0004%\tA\u000b\u0005\u0007o\u0005\u0001\u000b\u0011B\u0016\t\u000fa\n!\u0019!C\u0001U!1\u0011(\u0001Q\u0001\n-BqAO\u0001C\u0002\u0013\u0005!\u0006\u0003\u0004<\u0003\u0001\u0006Ia\u000b\u0005\by\u0005\u0011\r\u0011\"\u0001+\u0011\u0019i\u0014\u0001)A\u0005W!9a(\u0001b\u0001\n\u0003Q\u0003BB \u0002A\u0003%1&A\u000bUCN\\G)\u001a;bS2\u001c8\t\\1tg:\u000bW.Z:\u000b\u0005M!\u0012\u0001\u00026pENT!!\u0006\f\u0002\u0005UL'BA\f\u0019\u0003\u0015\u0019\b/\u0019:l\u0015\tI\"$\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u00027\u0005\u0019qN]4\u0011\u0005u\tQ\"\u0001\n\u0003+Q\u000b7o\u001b#fi\u0006LGn]\"mCN\u001ch*Y7fgN\u0011\u0011\u0001\t\t\u0003C\u0011j\u0011A\t\u0006\u0002G\u0005)1oY1mC&\u0011QE\t\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?\u0007\u0001!\u0012\u0001H\u0001\u0010'\u000eCU\tR+M\u000bJ{F)\u0012'B3V\t1\u0006\u0005\u0002-c5\tQF\u0003\u0002/_\u0005!A.\u00198h\u0015\u0005\u0001\u0014\u0001\u00026bm\u0006L!AM\u0017\u0003\rM#(/\u001b8h\u0003A\u00196\tS#E+2+%k\u0018#F\u0019\u0006K\u0006%A\rU\u0003N[u\fR#T\u000bJK\u0015\tT%[\u0003RKuJT0U\u00136+\u0015A\u0007+B'.{F)R*F%&\u000bE*\u0013.B)&{ej\u0018+J\u001b\u0016\u0003\u0013\u0001H*I+\u001a3E*R0S\u000b\u0006#uLR#U\u0007\"{v+Q%U?RKU*R\u0001\u001e'\"+fI\u0012'F?J+\u0015\tR0G\u000bR\u001b\u0005jX,B\u0013R{F+S'FA\u0005A2\u000bS+G\r2+uLU#B\t~\u0013V)T(U\u000b~\u001b\u0016JW#\u00023MCUK\u0012$M\u000b~\u0013V)\u0011#`%\u0016ku\nV#`'&SV\tI\u0001\u001a%\u0016\u001bV\u000b\u0014+`'\u0016\u0013\u0016*\u0011'J5\u0006#\u0016j\u0014(`)&kU)\u0001\u000eS\u000bN+F\nV0T\u000bJK\u0015\tT%[\u0003RKuJT0U\u00136+\u0005%A\nH\u000bR#\u0016JT$`%\u0016\u001bV\u000b\u0014+`)&kU)\u0001\u000bH\u000bR#\u0016JT$`%\u0016\u001bV\u000b\u0014+`)&kU\tI\u0001\u0016!\u0016\u000b5jX#Y\u000b\u000e+F+S(O?6+Uj\u0014*Z\u0003Y\u0001V)Q&`\u000bb+5)\u0016+J\u001f:{V*R'P%f\u0003\u0003"
)
public final class TaskDetailsClassNames {
   public static String PEAK_EXECUTION_MEMORY() {
      return TaskDetailsClassNames$.MODULE$.PEAK_EXECUTION_MEMORY();
   }

   public static String GETTING_RESULT_TIME() {
      return TaskDetailsClassNames$.MODULE$.GETTING_RESULT_TIME();
   }

   public static String RESULT_SERIALIZATION_TIME() {
      return TaskDetailsClassNames$.MODULE$.RESULT_SERIALIZATION_TIME();
   }

   public static String SHUFFLE_READ_REMOTE_SIZE() {
      return TaskDetailsClassNames$.MODULE$.SHUFFLE_READ_REMOTE_SIZE();
   }

   public static String SHUFFLE_READ_FETCH_WAIT_TIME() {
      return TaskDetailsClassNames$.MODULE$.SHUFFLE_READ_FETCH_WAIT_TIME();
   }

   public static String TASK_DESERIALIZATION_TIME() {
      return TaskDetailsClassNames$.MODULE$.TASK_DESERIALIZATION_TIME();
   }

   public static String SCHEDULER_DELAY() {
      return TaskDetailsClassNames$.MODULE$.SCHEDULER_DELAY();
   }
}
