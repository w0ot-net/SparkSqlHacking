package scala.util.parsing.input;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005I:Q\u0001C\u0005\t\u0002I1Q\u0001F\u0005\t\u0002UAQ!H\u0001\u0005\u0002yAQaH\u0001\u0005\u0002\u0001BQ\u0001J\u0001\u0005\u0002\u0001BQ!J\u0001\u0005B\u0019BQaL\u0001\u0005BABQ!M\u0001\u0005\u0002A\n!BT8Q_NLG/[8o\u0015\tQ1\"A\u0003j]B,HO\u0003\u0002\r\u001b\u00059\u0001/\u0019:tS:<'B\u0001\b\u0010\u0003\u0011)H/\u001b7\u000b\u0003A\tQa]2bY\u0006\u001c\u0001\u0001\u0005\u0002\u0014\u00035\t\u0011B\u0001\u0006O_B{7/\u001b;j_:\u001c2!\u0001\f\u001b!\t9\u0002$D\u0001\u0010\u0013\tIrB\u0001\u0004B]f\u0014VM\u001a\t\u0003'mI!\u0001H\u0005\u0003\u0011A{7/\u001b;j_:\fa\u0001P5oSRtD#\u0001\n\u0002\t1Lg.Z\u000b\u0002CA\u0011qCI\u0005\u0003G=\u00111!\u00138u\u0003\u0019\u0019w\u000e\\;n]\u0006AAo\\*ue&tw\rF\u0001(!\tAS&D\u0001*\u0015\tQ3&\u0001\u0003mC:<'\"\u0001\u0017\u0002\t)\fg/Y\u0005\u0003]%\u0012aa\u0015;sS:<\u0017A\u00037p]\u001e\u001cFO]5oOV\tq%\u0001\u0007mS:,7i\u001c8uK:$8\u000f"
)
public final class NoPosition {
   public static String lineContents() {
      return NoPosition$.MODULE$.lineContents();
   }

   public static String longString() {
      return NoPosition$.MODULE$.longString();
   }

   public static String toString() {
      return NoPosition$.MODULE$.toString();
   }

   public static int column() {
      return NoPosition$.MODULE$.column();
   }

   public static int line() {
      return NoPosition$.MODULE$.line();
   }

   public static boolean equals(final Object other) {
      return NoPosition$.MODULE$.equals(other);
   }

   public static boolean $less(final Position that) {
      return NoPosition$.MODULE$.$less(that);
   }
}
