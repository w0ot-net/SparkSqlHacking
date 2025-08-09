package spire.syntax;

import algebra.ring.Field;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000514A!\u0004\b\u0003'!A!\u0004\u0001BC\u0002\u0013\u00051\u0004\u0003\u0005 \u0001\t\u0005\t\u0015!\u0003\u001d\u0011\u0015\u0001\u0003\u0001\"\u0001\"\u0011\u0015)\u0003\u0001\"\u0001'\u0011\u001dA\u0005!!A\u0005B%CqA\u0013\u0001\u0002\u0002\u0013\u00053jB\u0004R\u001d\u0005\u0005\t\u0012\u0001*\u0007\u000f5q\u0011\u0011!E\u0001'\")\u0001\u0005\u0003C\u0001/\")\u0001\f\u0003C\u00033\"9A\rCA\u0001\n\u000b)\u0007bB4\t\u0003\u0003%)\u0001\u001b\u0002!\u0019&$XM]1m\u0013:$X*\u001e7uSBd\u0017nY1uSZ,wI]8va>\u00038O\u0003\u0002\u0010!\u000511/\u001f8uCbT\u0011!E\u0001\u0006gBL'/Z\u0002\u0001'\t\u0001A\u0003\u0005\u0002\u001615\taCC\u0001\u0018\u0003\u0015\u00198-\u00197b\u0013\tIbC\u0001\u0004B]f4\u0016\r\\\u0001\u0004Y\"\u001cX#\u0001\u000f\u0011\u0005Ui\u0012B\u0001\u0010\u0017\u0005\rIe\u000e^\u0001\u0005Y\"\u001c\b%\u0001\u0004=S:LGO\u0010\u000b\u0003E\u0011\u0002\"a\t\u0001\u000e\u00039AQAG\u0002A\u0002q\tA\u0001\n3jmV\u0011qe\u000b\u000b\u0003Q\u0019#\"!\u000b\u001b\u0011\u0005)ZC\u0002\u0001\u0003\u0006Y\u0011\u0011\r!\f\u0002\u0002\u0003F\u0011a&\r\t\u0003+=J!\u0001\r\f\u0003\u000f9{G\u000f[5oOB\u0011QCM\u0005\u0003gY\u00111!\u00118z\u0011\u0015)D\u0001q\u00017\u0003\t)g\u000fE\u00028\u0007&r!\u0001\u000f!\u000f\u0005erdB\u0001\u001e>\u001b\u0005Y$B\u0001\u001f\u0013\u0003\u0019a$o\\8u}%\t\u0011#\u0003\u0002@!\u00059\u0011\r\\4fEJ\f\u0017BA!C\u0003\u001d\u0001\u0018mY6bO\u0016T!a\u0010\t\n\u0005\u0011+%!\u0002$jK2$'BA!C\u0011\u00159E\u00011\u0001*\u0003\r\u0011\bn]\u0001\tQ\u0006\u001c\bnQ8eKR\tA$\u0001\u0004fcV\fGn\u001d\u000b\u0003\u0019>\u0003\"!F'\n\u000593\"a\u0002\"p_2,\u0017M\u001c\u0005\b!\u001a\t\t\u00111\u00012\u0003\rAH%M\u0001!\u0019&$XM]1m\u0013:$X*\u001e7uSBd\u0017nY1uSZ,wI]8va>\u00038\u000f\u0005\u0002$\u0011M\u0011\u0001\u0002\u0016\t\u0003+UK!A\u0016\f\u0003\r\u0005s\u0017PU3g)\u0005\u0011\u0016A\u0004\u0013eSZ$S\r\u001f;f]NLwN\\\u000b\u00035z#\"a\u00172\u0015\u0005q\u000bGCA/`!\tQc\fB\u0003-\u0015\t\u0007Q\u0006C\u00036\u0015\u0001\u000f\u0001\rE\u00028\u0007vCQa\u0012\u0006A\u0002uCQa\u0019\u0006A\u0002\t\nQ\u0001\n;iSN\f!\u0003[1tQ\u000e{G-\u001a\u0013fqR,gn]5p]R\u0011\u0011J\u001a\u0005\u0006G.\u0001\rAI\u0001\u0011KF,\u0018\r\\:%Kb$XM\\:j_:$\"![6\u0015\u00051S\u0007b\u0002)\r\u0003\u0003\u0005\r!\r\u0005\u0006G2\u0001\rA\t"
)
public final class LiteralIntMultiplicativeGroupOps {
   private final int lhs;

   public static boolean equals$extension(final int $this, final Object x$1) {
      return LiteralIntMultiplicativeGroupOps$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final int $this) {
      return LiteralIntMultiplicativeGroupOps$.MODULE$.hashCode$extension($this);
   }

   public static Object $div$extension(final int $this, final Object rhs, final Field ev) {
      return LiteralIntMultiplicativeGroupOps$.MODULE$.$div$extension($this, rhs, ev);
   }

   public int lhs() {
      return this.lhs;
   }

   public Object $div(final Object rhs, final Field ev) {
      return LiteralIntMultiplicativeGroupOps$.MODULE$.$div$extension(this.lhs(), rhs, ev);
   }

   public int hashCode() {
      return LiteralIntMultiplicativeGroupOps$.MODULE$.hashCode$extension(this.lhs());
   }

   public boolean equals(final Object x$1) {
      return LiteralIntMultiplicativeGroupOps$.MODULE$.equals$extension(this.lhs(), x$1);
   }

   public LiteralIntMultiplicativeGroupOps(final int lhs) {
      this.lhs = lhs;
   }
}
