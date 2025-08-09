package spire.syntax;

import algebra.ring.Field;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=4A!\u0004\b\u0003'!A!\u0004\u0001BC\u0002\u0013\u00051\u0004\u0003\u0005 \u0001\t\u0005\t\u0015!\u0003\u001d\u0011\u0015\u0001\u0003\u0001\"\u0001\"\u0011\u0015)\u0003\u0001\"\u0001'\u0011\u001dA\u0005!!A\u0005B%Cq!\u0014\u0001\u0002\u0002\u0013\u0005cjB\u0004U\u001d\u0005\u0005\t\u0012A+\u0007\u000f5q\u0011\u0011!E\u0001-\")\u0001\u0005\u0003C\u00015\")1\f\u0003C\u00039\"9q\rCA\u0001\n\u000bA\u0007b\u00026\t\u0003\u0003%)a\u001b\u0002\"\u0019&$XM]1m\t>,(\r\\3BI\u0012LG/\u001b<f'\u0016l\u0017n\u001a:pkB|\u0005o\u001d\u0006\u0003\u001fA\taa]=oi\u0006D(\"A\t\u0002\u000bM\u0004\u0018N]3\u0004\u0001M\u0011\u0001\u0001\u0006\t\u0003+ai\u0011A\u0006\u0006\u0002/\u0005)1oY1mC&\u0011\u0011D\u0006\u0002\u0007\u0003:Lh+\u00197\u0002\u00071D7/F\u0001\u001d!\t)R$\u0003\u0002\u001f-\t1Ai\\;cY\u0016\fA\u0001\u001c5tA\u00051A(\u001b8jiz\"\"A\t\u0013\u0011\u0005\r\u0002Q\"\u0001\b\t\u000bi\u0019\u0001\u0019\u0001\u000f\u0002\u000b\u0011\u0002H.^:\u0016\u0005\u001dZCC\u0001\u0015G)\tIC\u0007\u0005\u0002+W1\u0001A!\u0002\u0017\u0005\u0005\u0004i#!A!\u0012\u00059\n\u0004CA\u000b0\u0013\t\u0001dCA\u0004O_RD\u0017N\\4\u0011\u0005U\u0011\u0014BA\u001a\u0017\u0005\r\te.\u001f\u0005\u0006k\u0011\u0001\u001dAN\u0001\u0003KZ\u00042aN\"*\u001d\tA\u0004I\u0004\u0002:}9\u0011!(P\u0007\u0002w)\u0011AHE\u0001\u0007yI|w\u000e\u001e \n\u0003EI!a\u0010\t\u0002\u000f\u0005dw-\u001a2sC&\u0011\u0011IQ\u0001\ba\u0006\u001c7.Y4f\u0015\ty\u0004#\u0003\u0002E\u000b\n)a)[3mI*\u0011\u0011I\u0011\u0005\u0006\u000f\u0012\u0001\r!K\u0001\u0004e\"\u001c\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003)\u0003\"!F&\n\u000513\"aA%oi\u00061Q-];bYN$\"a\u0014*\u0011\u0005U\u0001\u0016BA)\u0017\u0005\u001d\u0011un\u001c7fC:Dqa\u0015\u0004\u0002\u0002\u0003\u0007\u0011'A\u0002yIE\n\u0011\u0005T5uKJ\fG\u000eR8vE2,\u0017\t\u001a3ji&4XmU3nS\u001e\u0014x.\u001e9PaN\u0004\"a\t\u0005\u0014\u0005!9\u0006CA\u000bY\u0013\tIfC\u0001\u0004B]f\u0014VM\u001a\u000b\u0002+\u0006yA\u0005\u001d7vg\u0012*\u0007\u0010^3og&|g.\u0006\u0002^CR\u0011a,\u001a\u000b\u0003?\u0012$\"\u0001\u00192\u0011\u0005)\nG!\u0002\u0017\u000b\u0005\u0004i\u0003\"B\u001b\u000b\u0001\b\u0019\u0007cA\u001cDA\")qI\u0003a\u0001A\")aM\u0003a\u0001E\u0005)A\u0005\u001e5jg\u0006\u0011\u0002.Y:i\u0007>$W\rJ3yi\u0016t7/[8o)\tI\u0015\u000eC\u0003g\u0017\u0001\u0007!%\u0001\tfcV\fGn\u001d\u0013fqR,gn]5p]R\u0011AN\u001c\u000b\u0003\u001f6Dqa\u0015\u0007\u0002\u0002\u0003\u0007\u0011\u0007C\u0003g\u0019\u0001\u0007!\u0005"
)
public final class LiteralDoubleAdditiveSemigroupOps {
   private final double lhs;

   public static boolean equals$extension(final double $this, final Object x$1) {
      return LiteralDoubleAdditiveSemigroupOps$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final double $this) {
      return LiteralDoubleAdditiveSemigroupOps$.MODULE$.hashCode$extension($this);
   }

   public static Object $plus$extension(final double $this, final Object rhs, final Field ev) {
      return LiteralDoubleAdditiveSemigroupOps$.MODULE$.$plus$extension($this, rhs, ev);
   }

   public double lhs() {
      return this.lhs;
   }

   public Object $plus(final Object rhs, final Field ev) {
      return LiteralDoubleAdditiveSemigroupOps$.MODULE$.$plus$extension(this.lhs(), rhs, ev);
   }

   public int hashCode() {
      return LiteralDoubleAdditiveSemigroupOps$.MODULE$.hashCode$extension(this.lhs());
   }

   public boolean equals(final Object x$1) {
      return LiteralDoubleAdditiveSemigroupOps$.MODULE$.equals$extension(this.lhs(), x$1);
   }

   public LiteralDoubleAdditiveSemigroupOps(final double lhs) {
      this.lhs = lhs;
   }
}
