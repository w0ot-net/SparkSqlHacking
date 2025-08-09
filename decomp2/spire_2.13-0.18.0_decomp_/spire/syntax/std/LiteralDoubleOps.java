package spire.syntax.std;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e3Aa\u0004\t\u0003/!Aa\u0004\u0001BC\u0002\u0013\u0005q\u0004\u0003\u0005$\u0001\t\u0005\t\u0015!\u0003!\u0011\u0015!\u0003\u0001\"\u0001&\u0011\u0015I\u0003\u0001\"\u0001+\u0011\u0015i\u0003\u0001\"\u0001/\u0011\u001d\u0001\u0004!!A\u0005BEBq!\u000e\u0001\u0002\u0002\u0013\u0005cgB\u0004@!\u0005\u0005\t\u0012\u0001!\u0007\u000f=\u0001\u0012\u0011!E\u0001\u0003\")A%\u0003C\u0001\u000b\")a)\u0003C\u0003\u000f\")A*\u0003C\u0003\u001b\"9\u0011+CA\u0001\n\u000b\u0011\u0006b\u0002+\n\u0003\u0003%)!\u0016\u0002\u0011\u0019&$XM]1m\t>,(\r\\3PaNT!!\u0005\n\u0002\u0007M$HM\u0003\u0002\u0014)\u000511/\u001f8uCbT\u0011!F\u0001\u0006gBL'/Z\u0002\u0001'\t\u0001\u0001\u0004\u0005\u0002\u001a95\t!DC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\ti\"D\u0001\u0004B]f4\u0016\r\\\u0001\u0004Y\"\u001cX#\u0001\u0011\u0011\u0005e\t\u0013B\u0001\u0012\u001b\u0005\u0019!u.\u001e2mK\u0006!A\u000e[:!\u0003\u0019a\u0014N\\5u}Q\u0011a\u0005\u000b\t\u0003O\u0001i\u0011\u0001\u0005\u0005\u0006=\r\u0001\r\u0001I\u0001\u0004a><HC\u0001\u0011,\u0011\u0015aC\u00011\u0001!\u0003\r\u0011\bn]\u0001\rIQLW.Z:%i&lWm\u001d\u000b\u0003A=BQ\u0001L\u0003A\u0002\u0001\n\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002eA\u0011\u0011dM\u0005\u0003ii\u00111!\u00138u\u0003\u0019)\u0017/^1mgR\u0011qG\u000f\t\u00033aJ!!\u000f\u000e\u0003\u000f\t{w\u000e\\3b]\"91hBA\u0001\u0002\u0004a\u0014a\u0001=%cA\u0011\u0011$P\u0005\u0003}i\u00111!\u00118z\u0003Aa\u0015\u000e^3sC2$u.\u001e2mK>\u00038\u000f\u0005\u0002(\u0013M\u0011\u0011B\u0011\t\u00033\rK!\u0001\u0012\u000e\u0003\r\u0005s\u0017PU3g)\u0005\u0001\u0015!\u00049po\u0012*\u0007\u0010^3og&|g\u000e\u0006\u0002I\u0015R\u0011\u0001%\u0013\u0005\u0006Y-\u0001\r\u0001\t\u0005\u0006\u0017.\u0001\rAJ\u0001\u0006IQD\u0017n]\u0001\u0017IQLW.Z:%i&lWm\u001d\u0013fqR,gn]5p]R\u0011a\n\u0015\u000b\u0003A=CQ\u0001\f\u0007A\u0002\u0001BQa\u0013\u0007A\u0002\u0019\n!\u0003[1tQ\u000e{G-\u001a\u0013fqR,gn]5p]R\u0011\u0011g\u0015\u0005\u0006\u00176\u0001\rAJ\u0001\u0011KF,\u0018\r\\:%Kb$XM\\:j_:$\"A\u0016-\u0015\u0005]:\u0006bB\u001e\u000f\u0003\u0003\u0005\r\u0001\u0010\u0005\u0006\u0017:\u0001\rA\n"
)
public final class LiteralDoubleOps {
   private final double lhs;

   public static boolean equals$extension(final double $this, final Object x$1) {
      return LiteralDoubleOps$.MODULE$.equals$extension($this, x$1);
   }

   public static int hashCode$extension(final double $this) {
      return LiteralDoubleOps$.MODULE$.hashCode$extension($this);
   }

   public static double $times$times$extension(final double $this, final double rhs) {
      return LiteralDoubleOps$.MODULE$.$times$times$extension($this, rhs);
   }

   public static double pow$extension(final double $this, final double rhs) {
      return LiteralDoubleOps$.MODULE$.pow$extension($this, rhs);
   }

   public double lhs() {
      return this.lhs;
   }

   public double pow(final double rhs) {
      return LiteralDoubleOps$.MODULE$.pow$extension(this.lhs(), rhs);
   }

   public double $times$times(final double rhs) {
      return LiteralDoubleOps$.MODULE$.$times$times$extension(this.lhs(), rhs);
   }

   public int hashCode() {
      return LiteralDoubleOps$.MODULE$.hashCode$extension(this.lhs());
   }

   public boolean equals(final Object x$1) {
      return LiteralDoubleOps$.MODULE$.equals$extension(this.lhs(), x$1);
   }

   public LiteralDoubleOps(final double lhs) {
      this.lhs = lhs;
   }
}
