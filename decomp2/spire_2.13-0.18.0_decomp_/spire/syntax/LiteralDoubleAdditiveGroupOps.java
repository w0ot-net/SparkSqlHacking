package spire.syntax;

import algebra.ring.Field;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=4A!\u0004\b\u0003'!A!\u0004\u0001BC\u0002\u0013\u00051\u0004\u0003\u0005 \u0001\t\u0005\t\u0015!\u0003\u001d\u0011\u0015\u0001\u0003\u0001\"\u0001\"\u0011\u0015)\u0003\u0001\"\u0001'\u0011\u001dA\u0005!!A\u0005B%Cq!\u0014\u0001\u0002\u0002\u0013\u0005cjB\u0004U\u001d\u0005\u0005\t\u0012A+\u0007\u000f5q\u0011\u0011!E\u0001-\")\u0001\u0005\u0003C\u00015\")1\f\u0003C\u00039\"9q\rCA\u0001\n\u000bA\u0007b\u00026\t\u0003\u0003%)a\u001b\u0002\u001e\u0019&$XM]1m\t>,(\r\\3BI\u0012LG/\u001b<f\u000fJ|W\u000f](qg*\u0011q\u0002E\u0001\u0007gftG/\u0019=\u000b\u0003E\tQa\u001d9je\u0016\u001c\u0001a\u0005\u0002\u0001)A\u0011Q\u0003G\u0007\u0002-)\tq#A\u0003tG\u0006d\u0017-\u0003\u0002\u001a-\t1\u0011I\\=WC2\f1\u0001\u001c5t+\u0005a\u0002CA\u000b\u001e\u0013\tqbC\u0001\u0004E_V\u0014G.Z\u0001\u0005Y\"\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0003E\u0011\u0002\"a\t\u0001\u000e\u00039AQAG\u0002A\u0002q\ta\u0001J7j]V\u001cXCA\u0014,)\tAc\t\u0006\u0002*iA\u0011!f\u000b\u0007\u0001\t\u0015aCA1\u0001.\u0005\u0005\t\u0015C\u0001\u00182!\t)r&\u0003\u00021-\t9aj\u001c;iS:<\u0007CA\u000b3\u0013\t\u0019dCA\u0002B]fDQ!\u000e\u0003A\u0004Y\n!!\u001a<\u0011\u0007]\u001a\u0015F\u0004\u00029\u0001:\u0011\u0011H\u0010\b\u0003uuj\u0011a\u000f\u0006\u0003yI\ta\u0001\u0010:p_Rt\u0014\"A\t\n\u0005}\u0002\u0012aB1mO\u0016\u0014'/Y\u0005\u0003\u0003\n\u000bq\u0001]1dW\u0006<WM\u0003\u0002@!%\u0011A)\u0012\u0002\u0006\r&,G\u000e\u001a\u0006\u0003\u0003\nCQa\u0012\u0003A\u0002%\n1A\u001d5t\u0003!A\u0017m\u001d5D_\u0012,G#\u0001&\u0011\u0005UY\u0015B\u0001'\u0017\u0005\rIe\u000e^\u0001\u0007KF,\u0018\r\\:\u0015\u0005=\u0013\u0006CA\u000bQ\u0013\t\tfCA\u0004C_>dW-\u00198\t\u000fM3\u0011\u0011!a\u0001c\u0005\u0019\u0001\u0010J\u0019\u0002;1KG/\u001a:bY\u0012{WO\u00197f\u0003\u0012$\u0017\u000e^5wK\u001e\u0013x.\u001e9PaN\u0004\"a\t\u0005\u0014\u0005!9\u0006CA\u000bY\u0013\tIfC\u0001\u0004B]f\u0014VM\u001a\u000b\u0002+\u0006\u0001B%\\5okN$S\r\u001f;f]NLwN\\\u000b\u0003;\u0006$\"AX3\u0015\u0005}#GC\u00011c!\tQ\u0013\rB\u0003-\u0015\t\u0007Q\u0006C\u00036\u0015\u0001\u000f1\rE\u00028\u0007\u0002DQa\u0012\u0006A\u0002\u0001DQA\u001a\u0006A\u0002\t\nQ\u0001\n;iSN\f!\u0003[1tQ\u000e{G-\u001a\u0013fqR,gn]5p]R\u0011\u0011*\u001b\u0005\u0006M.\u0001\rAI\u0001\u0011KF,\u0018\r\\:%Kb$XM\\:j_:$\"\u0001\u001c8\u0015\u0005=k\u0007bB*\r\u0003\u0003\u0005\r!\r\u0005\u0006M2\u0001\rA\t"
)
public final class LiteralDoubleAdditiveGroupOps {
   private final double lhs;

   public static boolean equals$extension(final double $this, final Object x$1) {
      return LiteralDoubleAdditiveGroupOps$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final double $this) {
      return LiteralDoubleAdditiveGroupOps$.MODULE$.hashCode$extension($this);
   }

   public static Object $minus$extension(final double $this, final Object rhs, final Field ev) {
      return LiteralDoubleAdditiveGroupOps$.MODULE$.$minus$extension($this, rhs, ev);
   }

   public double lhs() {
      return this.lhs;
   }

   public Object $minus(final Object rhs, final Field ev) {
      return LiteralDoubleAdditiveGroupOps$.MODULE$.$minus$extension(this.lhs(), rhs, ev);
   }

   public int hashCode() {
      return LiteralDoubleAdditiveGroupOps$.MODULE$.hashCode$extension(this.lhs());
   }

   public boolean equals(final Object x$1) {
      return LiteralDoubleAdditiveGroupOps$.MODULE$.equals$extension(this.lhs(), x$1);
   }

   public LiteralDoubleAdditiveGroupOps(final double lhs) {
      this.lhs = lhs;
   }
}
