package spire.math;

import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055a\u0001\u0003\b\u0010!\u0003\r\taD\n\t\u000b\u0005\u0002A\u0011A\u0012\t\u000b\u001d\u0002A\u0011\u0001\u0015\t\u000b9\u0002A\u0011A\u0018\t\u000bQ\u0002A\u0011A\u001b\t\u000bi\u0002A\u0011A\u001e\t\u000b\u0001\u0003A\u0011A!\t\u000b\u0019\u0003A\u0011A$\t\u000b1\u0003A\u0011A'\t\u000bm\u0003A\u0011\u0001/\t\u000b\u0005\u0004A\u0011\u00012\t\u000b\u001d\u0004A\u0011\u00015\t\u000b)\u0004A\u0011A6\t\u000bA\u0004A\u0011A9\u0003-\r{gN^3si\u0006\u0014G.\u001a+p\u00032<WM\u0019:bS\u000eT!\u0001E\t\u0002\t5\fG\u000f\u001b\u0006\u0002%\u0005)1\u000f]5sKN\u0019\u0001\u0001\u0006\u000e\u0011\u0005UAR\"\u0001\f\u000b\u0003]\tQa]2bY\u0006L!!\u0007\f\u0003\r\u0005s\u0017PU3g!\rYBDH\u0007\u0002\u001f%\u0011Qd\u0004\u0002\u000e\u0007>tg/\u001a:uC\ndW\rV8\u0011\u0005my\u0012B\u0001\u0011\u0010\u0005%\tEnZ3ce\u0006L7-\u0001\u0004%S:LG\u000fJ\u0002\u0001)\u0005!\u0003CA\u000b&\u0013\t1cC\u0001\u0003V]&$\u0018\u0001\u00034s_6\u0014\u0015\u0010^3\u0015\u0005yI\u0003\"\u0002\u0016\u0003\u0001\u0004Y\u0013!A1\u0011\u0005Ua\u0013BA\u0017\u0017\u0005\u0011\u0011\u0015\u0010^3\u0002\u0013\u0019\u0014x.\\*i_J$HC\u0001\u00101\u0011\u0015Q3\u00011\u00012!\t)\"'\u0003\u00024-\t)1\u000b[8si\u00069aM]8n\u0013:$HC\u0001\u00107\u0011\u0015QC\u00011\u00018!\t)\u0002(\u0003\u0002:-\t\u0019\u0011J\u001c;\u0002\u0011\u0019\u0014x.\u001c'p]\u001e$\"A\b\u001f\t\u000b)*\u0001\u0019A\u001f\u0011\u0005Uq\u0014BA \u0017\u0005\u0011auN\\4\u0002\u0013\u0019\u0014x.\u001c$m_\u0006$HC\u0001\u0010C\u0011\u0015Qc\u00011\u0001D!\t)B)\u0003\u0002F-\t)a\t\\8bi\u0006QaM]8n\t>,(\r\\3\u0015\u0005yA\u0005\"\u0002\u0016\b\u0001\u0004I\u0005CA\u000bK\u0013\tYeC\u0001\u0004E_V\u0014G.Z\u0001\u000bMJ|WNQ5h\u0013:$HC\u0001\u0010O\u0011\u0015Q\u0003\u00021\u0001P!\t\u0001\u0006L\u0004\u0002R-:\u0011!+V\u0007\u0002'*\u0011AKI\u0001\u0007yI|w\u000e\u001e \n\u0003]I!a\u0016\f\u0002\u000fA\f7m[1hK&\u0011\u0011L\u0017\u0002\u0007\u0005&<\u0017J\u001c;\u000b\u0005]3\u0012A\u00044s_6\u0014\u0015n\u001a#fG&l\u0017\r\u001c\u000b\u0003=uCQAK\u0005A\u0002y\u0003\"\u0001U0\n\u0005\u0001T&A\u0003\"jO\u0012+7-[7bY\u0006aaM]8n%\u0006$\u0018n\u001c8bYR\u0011ad\u0019\u0005\u0006U)\u0001\r\u0001\u001a\t\u00037\u0015L!AZ\b\u0003\u0011I\u000bG/[8oC2\fQB\u001a:p[\u0006cw-\u001a2sC&\u001cGC\u0001\u0010j\u0011\u0015Q3\u00021\u0001\u001f\u0003!1'o\\7SK\u0006dGC\u0001\u0010m\u0011\u0015QC\u00021\u0001n!\tYb.\u0003\u0002p\u001f\t!!+Z1m\u0003!1'o\\7UsB,WC\u0001:|)\r\u0019\u0018\u0011\u0002\u000b\u0003=QDq!^\u0007\u0002\u0002\u0003\u000fa/A\u0006fm&$WM\\2fIE\n\u0004cA\u000exs&\u0011\u0001p\u0004\u0002\u0010\u0007>tg/\u001a:uC\ndWM\u0012:p[B\u0011!p\u001f\u0007\u0001\t\u0015aXB1\u0001~\u0005\u0005\u0011\u0015c\u0001@\u0002\u0004A\u0011Qc`\u0005\u0004\u0003\u00031\"a\u0002(pi\"Lgn\u001a\t\u0004+\u0005\u0015\u0011bAA\u0004-\t\u0019\u0011I\\=\t\r\u0005-Q\u00021\u0001z\u0003\u0005\u0011\u0007"
)
public interface ConvertableToAlgebraic extends ConvertableTo {
   // $FF: synthetic method
   static Algebraic fromByte$(final ConvertableToAlgebraic $this, final byte a) {
      return $this.fromByte(a);
   }

   default Algebraic fromByte(final byte a) {
      return Algebraic$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Algebraic fromShort$(final ConvertableToAlgebraic $this, final short a) {
      return $this.fromShort(a);
   }

   default Algebraic fromShort(final short a) {
      return Algebraic$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Algebraic fromInt$(final ConvertableToAlgebraic $this, final int a) {
      return $this.fromInt(a);
   }

   default Algebraic fromInt(final int a) {
      return Algebraic$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Algebraic fromLong$(final ConvertableToAlgebraic $this, final long a) {
      return $this.fromLong(a);
   }

   default Algebraic fromLong(final long a) {
      return Algebraic$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Algebraic fromFloat$(final ConvertableToAlgebraic $this, final float a) {
      return $this.fromFloat(a);
   }

   default Algebraic fromFloat(final float a) {
      return Algebraic$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Algebraic fromDouble$(final ConvertableToAlgebraic $this, final double a) {
      return $this.fromDouble(a);
   }

   default Algebraic fromDouble(final double a) {
      return Algebraic$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Algebraic fromBigInt$(final ConvertableToAlgebraic $this, final BigInt a) {
      return $this.fromBigInt(a);
   }

   default Algebraic fromBigInt(final BigInt a) {
      return Algebraic$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Algebraic fromBigDecimal$(final ConvertableToAlgebraic $this, final BigDecimal a) {
      return $this.fromBigDecimal(a);
   }

   default Algebraic fromBigDecimal(final BigDecimal a) {
      return Algebraic$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Algebraic fromRational$(final ConvertableToAlgebraic $this, final Rational a) {
      return $this.fromRational(a);
   }

   default Algebraic fromRational(final Rational a) {
      return Algebraic$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Algebraic fromAlgebraic$(final ConvertableToAlgebraic $this, final Algebraic a) {
      return $this.fromAlgebraic(a);
   }

   default Algebraic fromAlgebraic(final Algebraic a) {
      return a;
   }

   // $FF: synthetic method
   static Algebraic fromReal$(final ConvertableToAlgebraic $this, final Real a) {
      return $this.fromReal(a);
   }

   default Algebraic fromReal(final Real a) {
      return Algebraic$.MODULE$.apply(a.toRational());
   }

   // $FF: synthetic method
   static Algebraic fromType$(final ConvertableToAlgebraic $this, final Object b, final ConvertableFrom evidence$11) {
      return $this.fromType(b, evidence$11);
   }

   default Algebraic fromType(final Object b, final ConvertableFrom evidence$11) {
      return ConvertableFrom$.MODULE$.apply(evidence$11).toAlgebraic(b);
   }

   static void $init$(final ConvertableToAlgebraic $this) {
   }
}
