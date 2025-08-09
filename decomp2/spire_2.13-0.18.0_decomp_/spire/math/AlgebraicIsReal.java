package spire.math;

import algebra.ring.Signed;
import algebra.ring.TruncatedDivision;
import java.math.RoundingMode;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import spire.algebra.IsAlgebraic;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rb\u0001\u0003\n\u0014!\u0003\r\taE\f\t\u000bi\u0002A\u0011A\u001e\t\u000b}\u0002A\u0011\u0001!\t\u000b\t\u0003A\u0011A\"\t\u000b%\u0003A\u0011\u0001&\t\u000b1\u0003A\u0011A'\t\u000bA\u0003A\u0011A)\t\u000bM\u0003A\u0011\u0001+\t\u000bY\u0003A\u0011A,\t\u000bq\u0003A\u0011A/\t\u000b5\u0004A\u0011\u00018\t\u000bI\u0004A\u0011A:\t\u000bY\u0004A\u0011I<\t\u000bq\u0004A\u0011I?\t\u000f\u0005\u0015\u0001\u0001\"\u0011\u0002\b!9\u00111\u0002\u0001\u0005B\u00055\u0001bBA\n\u0001\u0011\u0005\u0013Q\u0003\u0005\b\u00037\u0001A\u0011AA\u000f\u0005=\tEnZ3ce\u0006L7-S:SK\u0006d'B\u0001\u000b\u0016\u0003\u0011i\u0017\r\u001e5\u000b\u0003Y\tQa\u001d9je\u0016\u001cR\u0001\u0001\r\u001fQ]\u0002\"!\u0007\u000f\u000e\u0003iQ\u0011aG\u0001\u0006g\u000e\fG.Y\u0005\u0003;i\u0011a!\u00118z%\u00164\u0007cA\u0010#I5\t\u0001E\u0003\u0002\"+\u00059\u0011\r\\4fEJ\f\u0017BA\u0012!\u0005-I5/\u00117hK\n\u0014\u0018-[2\u0011\u0005\u00152S\"A\n\n\u0005\u001d\u001a\"!C!mO\u0016\u0014'/Y5d!\rIC\u0007\n\b\u0003UIr!aK\u0019\u000f\u00051\u0002T\"A\u0017\u000b\u00059z\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003YI!!I\u000b\n\u0005M\u0002\u0013a\u00029bG.\fw-Z\u0005\u0003kY\u0012a\u0003\u0016:v]\u000e\fG/\u001a3ESZL7/[8o\u0007JKgn\u001a\u0006\u0003g\u0001\u00022!\u000b\u001d%\u0013\tIdGA\u0003Pe\u0012,'/\u0001\u0004%S:LG\u000f\n\u000b\u0002yA\u0011\u0011$P\u0005\u0003}i\u0011A!\u00168ji\u0006)qN\u001d3feV\t\u0011\t\u0005\u0002&\u0001\u0005AAo\u001c#pk\ndW\r\u0006\u0002E\u000fB\u0011\u0011$R\u0005\u0003\rj\u0011a\u0001R8vE2,\u0007\"\u0002%\u0004\u0001\u0004!\u0013!\u0001=\u0002\u0017Q|\u0017\t\\4fEJ\f\u0017n\u0019\u000b\u0003I-CQ\u0001\u0013\u0003A\u0002\u0011\nAaY3jYR\u0011AE\u0014\u0005\u0006\u001f\u0016\u0001\r\u0001J\u0001\u0002C\u0006)a\r\\8peR\u0011AE\u0015\u0005\u0006\u001f\u001a\u0001\r\u0001J\u0001\u0006e>,h\u000e\u001a\u000b\u0003IUCQaT\u0004A\u0002\u0011\nq![:XQ>dW\r\u0006\u0002Y7B\u0011\u0011$W\u0005\u00035j\u0011qAQ8pY\u0016\fg\u000eC\u0003P\u0011\u0001\u0007A%A\u0006u_\nKw-\u00138u\u001fB$HC\u00010m!\ry&\rZ\u0007\u0002A*\u0011\u0011-F\u0001\u0005kRLG.\u0003\u0002dA\n\u0019q\n\u001d;\u0011\u0005\u0015LgB\u00014i\u001d\tas-C\u0001\u001c\u0013\t\u0019$$\u0003\u0002kW\n1!)[4J]RT!a\r\u000e\t\u000b=K\u0001\u0019\u0001\u0013\u0002\u000bQ\fXo\u001c;\u0015\u0007\u0011z\u0007\u000fC\u0003I\u0015\u0001\u0007A\u0005C\u0003r\u0015\u0001\u0007A%A\u0001z\u0003\u0011!Xn\u001c3\u0015\u0007\u0011\"X\u000fC\u0003I\u0017\u0001\u0007A\u0005C\u0003r\u0017\u0001\u0007A%\u0001\u0003tS\u001etGC\u0001=|!\tI\u00130\u0003\u0002{m\t!1+[4o\u0011\u0015yE\u00021\u0001%\u0003\u0019\u0019\u0018n\u001a8v[R\u0019a0a\u0001\u0011\u0005ey\u0018bAA\u00015\t\u0019\u0011J\u001c;\t\u000b=k\u0001\u0019\u0001\u0013\u0002\u0007\u0005\u00147\u000fF\u0002%\u0003\u0013AQa\u0014\bA\u0002\u0011\n1!Z9w)\u0015A\u0016qBA\t\u0011\u0015Au\u00021\u0001%\u0011\u0015\tx\u00021\u0001%\u0003\u0011qW-\u001d<\u0015\u000ba\u000b9\"!\u0007\t\u000b!\u0003\u0002\u0019\u0001\u0013\t\u000bE\u0004\u0002\u0019\u0001\u0013\u0002\u000f\r|W\u000e]1sKR)a0a\b\u0002\"!)\u0001*\u0005a\u0001I!)\u0011/\u0005a\u0001I\u0001"
)
public interface AlgebraicIsReal extends IsAlgebraic, TruncatedDivision.forCommutativeRing {
   // $FF: synthetic method
   static AlgebraicIsReal order$(final AlgebraicIsReal $this) {
      return $this.order();
   }

   default AlgebraicIsReal order() {
      return this;
   }

   // $FF: synthetic method
   static double toDouble$(final AlgebraicIsReal $this, final Algebraic x) {
      return $this.toDouble(x);
   }

   default double toDouble(final Algebraic x) {
      return x.toDouble();
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$(final AlgebraicIsReal $this, final Algebraic x) {
      return $this.toAlgebraic(x);
   }

   default Algebraic toAlgebraic(final Algebraic x) {
      return x;
   }

   // $FF: synthetic method
   static Algebraic ceil$(final AlgebraicIsReal $this, final Algebraic a) {
      return $this.ceil(a);
   }

   default Algebraic ceil(final Algebraic a) {
      return Algebraic$.MODULE$.apply(a.toBigDecimal(0, RoundingMode.CEILING));
   }

   // $FF: synthetic method
   static Algebraic floor$(final AlgebraicIsReal $this, final Algebraic a) {
      return $this.floor(a);
   }

   default Algebraic floor(final Algebraic a) {
      return Algebraic$.MODULE$.apply(a.toBigDecimal(0, RoundingMode.FLOOR));
   }

   // $FF: synthetic method
   static Algebraic round$(final AlgebraicIsReal $this, final Algebraic a) {
      return $this.round(a);
   }

   default Algebraic round(final Algebraic a) {
      return Algebraic$.MODULE$.apply(a.toBigDecimal(0, RoundingMode.HALF_EVEN));
   }

   // $FF: synthetic method
   static boolean isWhole$(final AlgebraicIsReal $this, final Algebraic a) {
      return $this.isWhole(a);
   }

   default boolean isWhole(final Algebraic a) {
      return a.isWhole();
   }

   // $FF: synthetic method
   static BigInt toBigIntOpt$(final AlgebraicIsReal $this, final Algebraic a) {
      return $this.toBigIntOpt(a);
   }

   default BigInt toBigIntOpt(final Algebraic a) {
      return this.isWhole(a) ? (BigInt).MODULE$.apply(a.toBigInt()) : (BigInt).MODULE$.empty();
   }

   // $FF: synthetic method
   static Algebraic tquot$(final AlgebraicIsReal $this, final Algebraic x, final Algebraic y) {
      return $this.tquot(x, y);
   }

   default Algebraic tquot(final Algebraic x, final Algebraic y) {
      return x.tquot(y);
   }

   // $FF: synthetic method
   static Algebraic tmod$(final AlgebraicIsReal $this, final Algebraic x, final Algebraic y) {
      return $this.tmod(x, y);
   }

   default Algebraic tmod(final Algebraic x, final Algebraic y) {
      return x.tmod(y);
   }

   // $FF: synthetic method
   static Signed.Sign sign$(final AlgebraicIsReal $this, final Algebraic a) {
      return $this.sign(a);
   }

   default Signed.Sign sign(final Algebraic a) {
      return a.sign();
   }

   // $FF: synthetic method
   static int signum$(final AlgebraicIsReal $this, final Algebraic a) {
      return $this.signum(a);
   }

   default int signum(final Algebraic a) {
      return a.signum();
   }

   // $FF: synthetic method
   static Algebraic abs$(final AlgebraicIsReal $this, final Algebraic a) {
      return $this.abs(a);
   }

   default Algebraic abs(final Algebraic a) {
      return a.abs();
   }

   // $FF: synthetic method
   static boolean eqv$(final AlgebraicIsReal $this, final Algebraic x, final Algebraic y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final Algebraic x, final Algebraic y) {
      return x.compare(y) == 0;
   }

   // $FF: synthetic method
   static boolean neqv$(final AlgebraicIsReal $this, final Algebraic x, final Algebraic y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final Algebraic x, final Algebraic y) {
      return x.compare(y) != 0;
   }

   // $FF: synthetic method
   static int compare$(final AlgebraicIsReal $this, final Algebraic x, final Algebraic y) {
      return $this.compare(x, y);
   }

   default int compare(final Algebraic x, final Algebraic y) {
      return x.compare(y);
   }

   static void $init$(final AlgebraicIsReal $this) {
   }
}
