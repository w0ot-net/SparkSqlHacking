package spire.syntax;

import algebra.ring.Ring;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000514A!\u0004\b\u0003'!A!\u0004\u0001BC\u0002\u0013\u00051\u0004\u0003\u0005 \u0001\t\u0005\t\u0015!\u0003\u001d\u0011\u0015\u0001\u0003\u0001\"\u0001\"\u0011\u0015)\u0003\u0001\"\u0001'\u0011\u001dA\u0005!!A\u0005B%CqA\u0013\u0001\u0002\u0002\u0013\u00053jB\u0004R\u001d\u0005\u0005\t\u0012\u0001*\u0007\u000f5q\u0011\u0011!E\u0001'\")\u0001\u0005\u0003C\u0001/\")\u0001\f\u0003C\u00033\"9A\rCA\u0001\n\u000b)\u0007bB4\t\u0003\u0003%)\u0001\u001b\u0002\u001b\u0019&$XM]1m\u0013:$\u0018\t\u001a3ji&4Xm\u0012:pkB|\u0005o\u001d\u0006\u0003\u001fA\taa]=oi\u0006D(\"A\t\u0002\u000bM\u0004\u0018N]3\u0004\u0001M\u0011\u0001\u0001\u0006\t\u0003+ai\u0011A\u0006\u0006\u0002/\u0005)1oY1mC&\u0011\u0011D\u0006\u0002\u0007\u0003:Lh+\u00197\u0002\u00071D7/F\u0001\u001d!\t)R$\u0003\u0002\u001f-\t\u0019\u0011J\u001c;\u0002\t1D7\u000fI\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0005\t\"\u0003CA\u0012\u0001\u001b\u0005q\u0001\"\u0002\u000e\u0004\u0001\u0004a\u0012A\u0002\u0013nS:,8/\u0006\u0002(WQ\u0011\u0001F\u0012\u000b\u0003SQ\u0002\"AK\u0016\r\u0001\u0011)A\u0006\u0002b\u0001[\t\t\u0011)\u0005\u0002/cA\u0011QcL\u0005\u0003aY\u0011qAT8uQ&tw\r\u0005\u0002\u0016e%\u00111G\u0006\u0002\u0004\u0003:L\b\"B\u001b\u0005\u0001\b1\u0014AA3w!\r94)\u000b\b\u0003q\u0001s!!\u000f \u000f\u0005ijT\"A\u001e\u000b\u0005q\u0012\u0012A\u0002\u001fs_>$h(C\u0001\u0012\u0013\ty\u0004#A\u0004bY\u001e,'M]1\n\u0005\u0005\u0013\u0015a\u00029bG.\fw-\u001a\u0006\u0003\u007fAI!\u0001R#\u0003\tIKgn\u001a\u0006\u0003\u0003\nCQa\u0012\u0003A\u0002%\n1A\u001d5t\u0003!A\u0017m\u001d5D_\u0012,G#\u0001\u000f\u0002\r\u0015\fX/\u00197t)\tau\n\u0005\u0002\u0016\u001b&\u0011aJ\u0006\u0002\b\u0005>|G.Z1o\u0011\u001d\u0001f!!AA\u0002E\n1\u0001\u001f\u00132\u0003ia\u0015\u000e^3sC2Le\u000e^!eI&$\u0018N^3He>,\bo\u00149t!\t\u0019\u0003b\u0005\u0002\t)B\u0011Q#V\u0005\u0003-Z\u0011a!\u00118z%\u00164G#\u0001*\u0002!\u0011j\u0017N\\;tI\u0015DH/\u001a8tS>tWC\u0001._)\tY&\r\u0006\u0002]CR\u0011Ql\u0018\t\u0003Uy#Q\u0001\f\u0006C\u00025BQ!\u000e\u0006A\u0004\u0001\u00042aN\"^\u0011\u00159%\u00021\u0001^\u0011\u0015\u0019'\u00021\u0001#\u0003\u0015!C\u000f[5t\u0003IA\u0017m\u001d5D_\u0012,G%\u001a=uK:\u001c\u0018n\u001c8\u0015\u0005%3\u0007\"B2\f\u0001\u0004\u0011\u0013\u0001E3rk\u0006d7\u000fJ3yi\u0016t7/[8o)\tI7\u000e\u0006\u0002MU\"9\u0001\u000bDA\u0001\u0002\u0004\t\u0004\"B2\r\u0001\u0004\u0011\u0003"
)
public final class LiteralIntAdditiveGroupOps {
   private final int lhs;

   public static boolean equals$extension(final int $this, final Object x$1) {
      return LiteralIntAdditiveGroupOps$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final int $this) {
      return LiteralIntAdditiveGroupOps$.MODULE$.hashCode$extension($this);
   }

   public static Object $minus$extension(final int $this, final Object rhs, final Ring ev) {
      return LiteralIntAdditiveGroupOps$.MODULE$.$minus$extension($this, rhs, ev);
   }

   public int lhs() {
      return this.lhs;
   }

   public Object $minus(final Object rhs, final Ring ev) {
      return LiteralIntAdditiveGroupOps$.MODULE$.$minus$extension(this.lhs(), rhs, ev);
   }

   public int hashCode() {
      return LiteralIntAdditiveGroupOps$.MODULE$.hashCode$extension(this.lhs());
   }

   public boolean equals(final Object x$1) {
      return LiteralIntAdditiveGroupOps$.MODULE$.equals$extension(this.lhs(), x$1);
   }

   public LiteralIntAdditiveGroupOps(final int lhs) {
      this.lhs = lhs;
   }
}
