package spire.syntax;

import algebra.ring.Ring;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000514A!\u0004\b\u0003'!A!\u0004\u0001BC\u0002\u0013\u00051\u0004\u0003\u0005 \u0001\t\u0005\t\u0015!\u0003\u001d\u0011\u0015\u0001\u0003\u0001\"\u0001\"\u0011\u0015)\u0003\u0001\"\u0001'\u0011\u001dA\u0005!!A\u0005B%CqA\u0013\u0001\u0002\u0002\u0013\u00053jB\u0004R\u001d\u0005\u0005\t\u0012\u0001*\u0007\u000f5q\u0011\u0011!E\u0001'\")\u0001\u0005\u0003C\u0001/\")\u0001\f\u0003C\u00033\"9A\rCA\u0001\n\u000b)\u0007bB4\t\u0003\u0003%)\u0001\u001b\u0002%\u0019&$XM]1m\u0013:$X*\u001e7uSBd\u0017nY1uSZ,7+Z7jOJ|W\u000f](qg*\u0011q\u0002E\u0001\u0007gftG/\u0019=\u000b\u0003E\tQa\u001d9je\u0016\u001c\u0001a\u0005\u0002\u0001)A\u0011Q\u0003G\u0007\u0002-)\tq#A\u0003tG\u0006d\u0017-\u0003\u0002\u001a-\t1\u0011I\\=WC2\f1\u0001\u001c5t+\u0005a\u0002CA\u000b\u001e\u0013\tqbCA\u0002J]R\fA\u0001\u001c5tA\u00051A(\u001b8jiz\"\"A\t\u0013\u0011\u0005\r\u0002Q\"\u0001\b\t\u000bi\u0019\u0001\u0019\u0001\u000f\u0002\r\u0011\"\u0018.\\3t+\t93\u0006\u0006\u0002)\rR\u0011\u0011\u0006\u000e\t\u0003U-b\u0001\u0001B\u0003-\t\t\u0007QFA\u0001B#\tq\u0013\u0007\u0005\u0002\u0016_%\u0011\u0001G\u0006\u0002\b\u001d>$\b.\u001b8h!\t)\"'\u0003\u00024-\t\u0019\u0011I\\=\t\u000bU\"\u00019\u0001\u001c\u0002\u0005\u00154\bcA\u001cDS9\u0011\u0001\b\u0011\b\u0003syr!AO\u001f\u000e\u0003mR!\u0001\u0010\n\u0002\rq\u0012xn\u001c;?\u0013\u0005\t\u0012BA \u0011\u0003\u001d\tGnZ3ce\u0006L!!\u0011\"\u0002\u000fA\f7m[1hK*\u0011q\bE\u0005\u0003\t\u0016\u0013AAU5oO*\u0011\u0011I\u0011\u0005\u0006\u000f\u0012\u0001\r!K\u0001\u0004e\"\u001c\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003q\ta!Z9vC2\u001cHC\u0001'P!\t)R*\u0003\u0002O-\t9!i\\8mK\u0006t\u0007b\u0002)\u0007\u0003\u0003\u0005\r!M\u0001\u0004q\u0012\n\u0014\u0001\n'ji\u0016\u0014\u0018\r\\%oi6+H\u000e^5qY&\u001c\u0017\r^5wKN+W.[4s_V\u0004x\n]:\u0011\u0005\rB1C\u0001\u0005U!\t)R+\u0003\u0002W-\t1\u0011I\\=SK\u001a$\u0012AU\u0001\u0011IQLW.Z:%Kb$XM\\:j_:,\"A\u00170\u0015\u0005m\u0013GC\u0001/b)\tiv\f\u0005\u0002+=\u0012)AF\u0003b\u0001[!)QG\u0003a\u0002AB\u0019qgQ/\t\u000b\u001dS\u0001\u0019A/\t\u000b\rT\u0001\u0019\u0001\u0012\u0002\u000b\u0011\"\b.[:\u0002%!\f7\u000f[\"pI\u0016$S\r\u001f;f]NLwN\u001c\u000b\u0003\u0013\u001aDQaY\u0006A\u0002\t\n\u0001#Z9vC2\u001cH%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0005%\\GC\u0001'k\u0011\u001d\u0001F\"!AA\u0002EBQa\u0019\u0007A\u0002\t\u0002"
)
public final class LiteralIntMultiplicativeSemigroupOps {
   private final int lhs;

   public static boolean equals$extension(final int $this, final Object x$1) {
      return LiteralIntMultiplicativeSemigroupOps$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final int $this) {
      return LiteralIntMultiplicativeSemigroupOps$.MODULE$.hashCode$extension($this);
   }

   public static Object $times$extension(final int $this, final Object rhs, final Ring ev) {
      return LiteralIntMultiplicativeSemigroupOps$.MODULE$.$times$extension($this, rhs, ev);
   }

   public int lhs() {
      return this.lhs;
   }

   public Object $times(final Object rhs, final Ring ev) {
      return LiteralIntMultiplicativeSemigroupOps$.MODULE$.$times$extension(this.lhs(), rhs, ev);
   }

   public int hashCode() {
      return LiteralIntMultiplicativeSemigroupOps$.MODULE$.hashCode$extension(this.lhs());
   }

   public boolean equals(final Object x$1) {
      return LiteralIntMultiplicativeSemigroupOps$.MODULE$.equals$extension(this.lhs(), x$1);
   }

   public LiteralIntMultiplicativeSemigroupOps(final int lhs) {
      this.lhs = lhs;
   }
}
