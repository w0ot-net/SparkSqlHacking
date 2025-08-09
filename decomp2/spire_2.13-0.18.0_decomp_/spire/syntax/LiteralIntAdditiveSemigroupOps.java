package spire.syntax;

import algebra.ring.Ring;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000514A!\u0004\b\u0003'!A!\u0004\u0001BC\u0002\u0013\u00051\u0004\u0003\u0005 \u0001\t\u0005\t\u0015!\u0003\u001d\u0011\u0015\u0001\u0003\u0001\"\u0001\"\u0011\u0015)\u0003\u0001\"\u0001'\u0011\u001dA\u0005!!A\u0005B%CqA\u0013\u0001\u0002\u0002\u0013\u00053jB\u0004R\u001d\u0005\u0005\t\u0012\u0001*\u0007\u000f5q\u0011\u0011!E\u0001'\")\u0001\u0005\u0003C\u0001/\")\u0001\f\u0003C\u00033\"9A\rCA\u0001\n\u000b)\u0007bB4\t\u0003\u0003%)\u0001\u001b\u0002\u001f\u0019&$XM]1m\u0013:$\u0018\t\u001a3ji&4XmU3nS\u001e\u0014x.\u001e9PaNT!a\u0004\t\u0002\rMLh\u000e^1y\u0015\u0005\t\u0012!B:qSJ,7\u0001A\n\u0003\u0001Q\u0001\"!\u0006\r\u000e\u0003YQ\u0011aF\u0001\u0006g\u000e\fG.Y\u0005\u00033Y\u0011a!\u00118z-\u0006d\u0017a\u00017igV\tA\u0004\u0005\u0002\u0016;%\u0011aD\u0006\u0002\u0004\u0013:$\u0018\u0001\u00027ig\u0002\na\u0001P5oSRtDC\u0001\u0012%!\t\u0019\u0003!D\u0001\u000f\u0011\u0015Q2\u00011\u0001\u001d\u0003\u0015!\u0003\u000f\\;t+\t93\u0006\u0006\u0002)\rR\u0011\u0011\u0006\u000e\t\u0003U-b\u0001\u0001B\u0003-\t\t\u0007QFA\u0001B#\tq\u0013\u0007\u0005\u0002\u0016_%\u0011\u0001G\u0006\u0002\b\u001d>$\b.\u001b8h!\t)\"'\u0003\u00024-\t\u0019\u0011I\\=\t\u000bU\"\u00019\u0001\u001c\u0002\u0005\u00154\bcA\u001cDS9\u0011\u0001\b\u0011\b\u0003syr!AO\u001f\u000e\u0003mR!\u0001\u0010\n\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0012BA \u0011\u0003\u001d\tGnZ3ce\u0006L!!\u0011\"\u0002\u000fA\f7m[1hK*\u0011q\bE\u0005\u0003\t\u0016\u0013AAU5oO*\u0011\u0011I\u0011\u0005\u0006\u000f\u0012\u0001\r!K\u0001\u0004e\"\u001c\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003q\ta!Z9vC2\u001cHC\u0001'P!\t)R*\u0003\u0002O-\t9!i\\8mK\u0006t\u0007b\u0002)\u0007\u0003\u0003\u0005\r!M\u0001\u0004q\u0012\n\u0014A\b'ji\u0016\u0014\u0018\r\\%oi\u0006#G-\u001b;jm\u0016\u001cV-\\5he>,\bo\u00149t!\t\u0019\u0003b\u0005\u0002\t)B\u0011Q#V\u0005\u0003-Z\u0011a!\u00118z%\u00164G#\u0001*\u0002\u001f\u0011\u0002H.^:%Kb$XM\\:j_:,\"A\u00170\u0015\u0005m\u0013GC\u0001/b)\tiv\f\u0005\u0002+=\u0012)AF\u0003b\u0001[!)QG\u0003a\u0002AB\u0019qgQ/\t\u000b\u001dS\u0001\u0019A/\t\u000b\rT\u0001\u0019\u0001\u0012\u0002\u000b\u0011\"\b.[:\u0002%!\f7\u000f[\"pI\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0003\u0013\u001aDQaY\u0006A\u0002\t\n\u0001#Z9vC2\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0005%\\GC\u0001'k\u0011\u001d\u0001F\"!AA\u0002EBQa\u0019\u0007A\u0002\t\u0002"
)
public final class LiteralIntAdditiveSemigroupOps {
   private final int lhs;

   public static boolean equals$extension(final int $this, final Object x$1) {
      return LiteralIntAdditiveSemigroupOps$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final int $this) {
      return LiteralIntAdditiveSemigroupOps$.MODULE$.hashCode$extension($this);
   }

   public static Object $plus$extension(final int $this, final Object rhs, final Ring ev) {
      return LiteralIntAdditiveSemigroupOps$.MODULE$.$plus$extension($this, rhs, ev);
   }

   public int lhs() {
      return this.lhs;
   }

   public Object $plus(final Object rhs, final Ring ev) {
      return LiteralIntAdditiveSemigroupOps$.MODULE$.$plus$extension(this.lhs(), rhs, ev);
   }

   public int hashCode() {
      return LiteralIntAdditiveSemigroupOps$.MODULE$.hashCode$extension(this.lhs());
   }

   public boolean equals(final Object x$1) {
      return LiteralIntAdditiveSemigroupOps$.MODULE$.equals$extension(this.lhs(), x$1);
   }

   public LiteralIntAdditiveSemigroupOps(final int lhs) {
      this.lhs = lhs;
   }
}
