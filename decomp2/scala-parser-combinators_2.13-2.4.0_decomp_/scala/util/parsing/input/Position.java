package scala.util.parsing.input;

import java.lang.invoke.SerializedLambda;
import scala.collection.StringOps.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005-3q!\u0003\u0006\u0011\u0002\u0007\u00051\u0003C\u0003\u0019\u0001\u0011\u0005\u0011\u0004C\u0003\u001e\u0001\u0019\u0005a\u0004C\u0003#\u0001\u0019\u0005a\u0004C\u0003$\u0001\u0019EA\u0005C\u00031\u0001\u0011\u0005\u0013\u0007C\u0003:\u0001\u0011\u0005!\bC\u0003<\u0001\u0011\u0005A\bC\u0003E\u0001\u0011\u0005SI\u0001\u0005Q_NLG/[8o\u0015\tYA\"A\u0003j]B,HO\u0003\u0002\u000e\u001d\u00059\u0001/\u0019:tS:<'BA\b\u0011\u0003\u0011)H/\u001b7\u000b\u0003E\tQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001)A\u0011QCF\u0007\u0002!%\u0011q\u0003\u0005\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005Q\u0002CA\u000b\u001c\u0013\ta\u0002C\u0001\u0003V]&$\u0018\u0001\u00027j]\u0016,\u0012a\b\t\u0003+\u0001J!!\t\t\u0003\u0007%sG/\u0001\u0004d_2,XN\\\u0001\rY&tWmQ8oi\u0016tGo]\u000b\u0002KA\u0011a%\f\b\u0003O-\u0002\"\u0001\u000b\t\u000e\u0003%R!A\u000b\n\u0002\rq\u0012xn\u001c;?\u0013\ta\u0003#\u0001\u0004Qe\u0016$WMZ\u0005\u0003]=\u0012aa\u0015;sS:<'B\u0001\u0017\u0011\u0003!!xn\u0015;sS:<G#\u0001\u001a\u0011\u0005MBT\"\u0001\u001b\u000b\u0005U2\u0014\u0001\u00027b]\u001eT\u0011aN\u0001\u0005U\u00064\u0018-\u0003\u0002/i\u0005QAn\u001c8h'R\u0014\u0018N\\4\u0016\u0003I\nQ\u0001\n7fgN$\"!\u0010!\u0011\u0005Uq\u0014BA \u0011\u0005\u001d\u0011un\u001c7fC:DQ!Q\u0004A\u0002\t\u000bA\u0001\u001e5biB\u00111\tA\u0007\u0002\u0015\u00051Q-];bYN$\"!\u0010$\t\u000b\u001dC\u0001\u0019\u0001%\u0002\u000b=$\b.\u001a:\u0011\u0005UI\u0015B\u0001&\u0011\u0005\r\te.\u001f"
)
public interface Position {
   int line();

   int column();

   String lineContents();

   // $FF: synthetic method
   static String toString$(final Position $this) {
      return $this.toString();
   }

   default String toString() {
      return (new StringBuilder(1)).append(this.line()).append(".").append(this.column()).toString();
   }

   // $FF: synthetic method
   static String longString$(final Position $this) {
      return $this.longString();
   }

   default String longString() {
      return (new StringBuilder(2)).append(this.lineContents()).append("\n").append(.MODULE$.map$extension(scala.Predef..MODULE$.augmentString(.MODULE$.take$extension(scala.Predef..MODULE$.augmentString(this.lineContents()), this.column() - 1)), (x) -> BoxesRunTime.boxToCharacter($anonfun$longString$1(BoxesRunTime.unboxToChar(x))))).append("^").toString();
   }

   // $FF: synthetic method
   static boolean $less$(final Position $this, final Position that) {
      return $this.$less(that);
   }

   default boolean $less(final Position that) {
      return this.line() < that.line() || this.line() == that.line() && this.column() < that.column();
   }

   // $FF: synthetic method
   static boolean equals$(final Position $this, final Object other) {
      return $this.equals(other);
   }

   default boolean equals(final Object other) {
      if (!(other instanceof Position)) {
         return false;
      } else {
         Position var4 = (Position)other;
         return this.line() == var4.line() && this.column() == var4.column();
      }
   }

   // $FF: synthetic method
   static char $anonfun$longString$1(final char x) {
      return x == '\t' ? x : ' ';
   }

   static void $init$(final Position $this) {
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return var0.lambdaDeserialize<invokedynamic>(var0);
   }
}
