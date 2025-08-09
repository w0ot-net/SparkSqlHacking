package spire.math;

import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00055a\u0001\u0003\b\u0010!\u0003\r\taD\n\t\u000b\u0005\u0002A\u0011A\u0012\t\u000b\u001d\u0002A\u0011\u0001\u0015\t\u000b9\u0002A\u0011A\u0018\t\u000bQ\u0002A\u0011A\u001b\t\u000bi\u0002A\u0011A\u001e\t\u000bu\u0002A\u0011\u0001 \t\u000b\r\u0003A\u0011\u0001#\t\u000b%\u0003A\u0011\u0001&\t\u000ba\u0003A\u0011A-\t\u000by\u0003A\u0011A0\t\u000b\u0011\u0004A\u0011A3\t\u000b)\u0004A\u0011A6\t\u000bA\u0004A\u0011A9\u0003#\r{gN^3si\u0006\u0014G.\u001a+p\u0019>twM\u0003\u0002\u0011#\u0005!Q.\u0019;i\u0015\u0005\u0011\u0012!B:qSJ,7c\u0001\u0001\u00155A\u0011Q\u0003G\u0007\u0002-)\tq#A\u0003tG\u0006d\u0017-\u0003\u0002\u001a-\t1\u0011I\\=SK\u001a\u00042a\u0007\u000f\u001f\u001b\u0005y\u0011BA\u000f\u0010\u00055\u0019uN\u001c<feR\f'\r\\3U_B\u0011QcH\u0005\u0003AY\u0011A\u0001T8oO\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001%!\t)R%\u0003\u0002'-\t!QK\\5u\u0003!1'o\\7CsR,GC\u0001\u0010*\u0011\u0015Q#\u00011\u0001,\u0003\u0005\t\u0007CA\u000b-\u0013\ticC\u0001\u0003CsR,\u0017!\u00034s_6\u001c\u0006n\u001c:u)\tq\u0002\u0007C\u0003+\u0007\u0001\u0007\u0011\u0007\u0005\u0002\u0016e%\u00111G\u0006\u0002\u0006'\"|'\u000f^\u0001\bMJ|W.\u00138u)\tqb\u0007C\u0003+\t\u0001\u0007q\u0007\u0005\u0002\u0016q%\u0011\u0011H\u0006\u0002\u0004\u0013:$\u0018\u0001\u00034s_6duN\\4\u0015\u0005ya\u0004\"\u0002\u0016\u0006\u0001\u0004q\u0012!\u00034s_64En\\1u)\tqr\bC\u0003+\r\u0001\u0007\u0001\t\u0005\u0002\u0016\u0003&\u0011!I\u0006\u0002\u0006\r2|\u0017\r^\u0001\u000bMJ|W\u000eR8vE2,GC\u0001\u0010F\u0011\u0015Qs\u00011\u0001G!\t)r)\u0003\u0002I-\t1Ai\\;cY\u0016\f!B\u001a:p[\nKw-\u00138u)\tq2\nC\u0003+\u0011\u0001\u0007A\n\u0005\u0002N+:\u0011aj\u0015\b\u0003\u001fJk\u0011\u0001\u0015\u0006\u0003#\n\na\u0001\u0010:p_Rt\u0014\"A\f\n\u0005Q3\u0012a\u00029bG.\fw-Z\u0005\u0003-^\u0013aAQ5h\u0013:$(B\u0001+\u0017\u000391'o\\7CS\u001e$UmY5nC2$\"A\b.\t\u000b)J\u0001\u0019A.\u0011\u00055c\u0016BA/X\u0005)\u0011\u0015n\u001a#fG&l\u0017\r\\\u0001\rMJ|WNU1uS>t\u0017\r\u001c\u000b\u0003=\u0001DQA\u000b\u0006A\u0002\u0005\u0004\"a\u00072\n\u0005\r|!\u0001\u0003*bi&|g.\u00197\u0002\u001b\u0019\u0014x.\\!mO\u0016\u0014'/Y5d)\tqb\rC\u0003+\u0017\u0001\u0007q\r\u0005\u0002\u001cQ&\u0011\u0011n\u0004\u0002\n\u00032<WM\u0019:bS\u000e\f\u0001B\u001a:p[J+\u0017\r\u001c\u000b\u0003=1DQA\u000b\u0007A\u00025\u0004\"a\u00078\n\u0005=|!\u0001\u0002*fC2\f\u0001B\u001a:p[RK\b/Z\u000b\u0003en$2a]A\u0005)\tqB\u000fC\u0004v\u001b\u0005\u0005\t9\u0001<\u0002\u0015\u00154\u0018\u000eZ3oG\u0016$S\u0007E\u0002\u001cofL!\u0001_\b\u0003\u001f\r{gN^3si\u0006\u0014G.\u001a$s_6\u0004\"A_>\r\u0001\u0011)A0\u0004b\u0001{\n\t!)E\u0002\u007f\u0003\u0007\u0001\"!F@\n\u0007\u0005\u0005aCA\u0004O_RD\u0017N\\4\u0011\u0007U\t)!C\u0002\u0002\bY\u00111!\u00118z\u0011\u0019\tY!\u0004a\u0001s\u0006\t!\r"
)
public interface ConvertableToLong extends ConvertableTo$mcJ$sp {
   // $FF: synthetic method
   static long fromByte$(final ConvertableToLong $this, final byte a) {
      return $this.fromByte(a);
   }

   default long fromByte(final byte a) {
      return this.fromByte$mcJ$sp(a);
   }

   // $FF: synthetic method
   static long fromShort$(final ConvertableToLong $this, final short a) {
      return $this.fromShort(a);
   }

   default long fromShort(final short a) {
      return this.fromShort$mcJ$sp(a);
   }

   // $FF: synthetic method
   static long fromInt$(final ConvertableToLong $this, final int a) {
      return $this.fromInt(a);
   }

   default long fromInt(final int a) {
      return this.fromInt$mcJ$sp(a);
   }

   // $FF: synthetic method
   static long fromLong$(final ConvertableToLong $this, final long a) {
      return $this.fromLong(a);
   }

   default long fromLong(final long a) {
      return this.fromLong$mcJ$sp(a);
   }

   // $FF: synthetic method
   static long fromFloat$(final ConvertableToLong $this, final float a) {
      return $this.fromFloat(a);
   }

   default long fromFloat(final float a) {
      return this.fromFloat$mcJ$sp(a);
   }

   // $FF: synthetic method
   static long fromDouble$(final ConvertableToLong $this, final double a) {
      return $this.fromDouble(a);
   }

   default long fromDouble(final double a) {
      return this.fromDouble$mcJ$sp(a);
   }

   // $FF: synthetic method
   static long fromBigInt$(final ConvertableToLong $this, final BigInt a) {
      return $this.fromBigInt(a);
   }

   default long fromBigInt(final BigInt a) {
      return this.fromBigInt$mcJ$sp(a);
   }

   // $FF: synthetic method
   static long fromBigDecimal$(final ConvertableToLong $this, final BigDecimal a) {
      return $this.fromBigDecimal(a);
   }

   default long fromBigDecimal(final BigDecimal a) {
      return this.fromBigDecimal$mcJ$sp(a);
   }

   // $FF: synthetic method
   static long fromRational$(final ConvertableToLong $this, final Rational a) {
      return $this.fromRational(a);
   }

   default long fromRational(final Rational a) {
      return this.fromRational$mcJ$sp(a);
   }

   // $FF: synthetic method
   static long fromAlgebraic$(final ConvertableToLong $this, final Algebraic a) {
      return $this.fromAlgebraic(a);
   }

   default long fromAlgebraic(final Algebraic a) {
      return this.fromAlgebraic$mcJ$sp(a);
   }

   // $FF: synthetic method
   static long fromReal$(final ConvertableToLong $this, final Real a) {
      return $this.fromReal(a);
   }

   default long fromReal(final Real a) {
      return this.fromReal$mcJ$sp(a);
   }

   // $FF: synthetic method
   static long fromType$(final ConvertableToLong $this, final Object b, final ConvertableFrom evidence$5) {
      return $this.fromType(b, evidence$5);
   }

   default long fromType(final Object b, final ConvertableFrom evidence$5) {
      return this.fromType$mcJ$sp(b, evidence$5);
   }

   // $FF: synthetic method
   static long fromByte$mcJ$sp$(final ConvertableToLong $this, final byte a) {
      return $this.fromByte$mcJ$sp(a);
   }

   default long fromByte$mcJ$sp(final byte a) {
      return (long)a;
   }

   // $FF: synthetic method
   static long fromShort$mcJ$sp$(final ConvertableToLong $this, final short a) {
      return $this.fromShort$mcJ$sp(a);
   }

   default long fromShort$mcJ$sp(final short a) {
      return (long)a;
   }

   // $FF: synthetic method
   static long fromInt$mcJ$sp$(final ConvertableToLong $this, final int a) {
      return $this.fromInt$mcJ$sp(a);
   }

   default long fromInt$mcJ$sp(final int a) {
      return (long)a;
   }

   // $FF: synthetic method
   static long fromLong$mcJ$sp$(final ConvertableToLong $this, final long a) {
      return $this.fromLong$mcJ$sp(a);
   }

   default long fromLong$mcJ$sp(final long a) {
      return a;
   }

   // $FF: synthetic method
   static long fromFloat$mcJ$sp$(final ConvertableToLong $this, final float a) {
      return $this.fromFloat$mcJ$sp(a);
   }

   default long fromFloat$mcJ$sp(final float a) {
      return (long)a;
   }

   // $FF: synthetic method
   static long fromDouble$mcJ$sp$(final ConvertableToLong $this, final double a) {
      return $this.fromDouble$mcJ$sp(a);
   }

   default long fromDouble$mcJ$sp(final double a) {
      return (long)a;
   }

   // $FF: synthetic method
   static long fromBigInt$mcJ$sp$(final ConvertableToLong $this, final BigInt a) {
      return $this.fromBigInt$mcJ$sp(a);
   }

   default long fromBigInt$mcJ$sp(final BigInt a) {
      return a.toLong();
   }

   // $FF: synthetic method
   static long fromBigDecimal$mcJ$sp$(final ConvertableToLong $this, final BigDecimal a) {
      return $this.fromBigDecimal$mcJ$sp(a);
   }

   default long fromBigDecimal$mcJ$sp(final BigDecimal a) {
      return a.toLong();
   }

   // $FF: synthetic method
   static long fromRational$mcJ$sp$(final ConvertableToLong $this, final Rational a) {
      return $this.fromRational$mcJ$sp(a);
   }

   default long fromRational$mcJ$sp(final Rational a) {
      return a.toBigInt().toLong();
   }

   // $FF: synthetic method
   static long fromAlgebraic$mcJ$sp$(final ConvertableToLong $this, final Algebraic a) {
      return $this.fromAlgebraic$mcJ$sp(a);
   }

   default long fromAlgebraic$mcJ$sp(final Algebraic a) {
      return a.toLong();
   }

   // $FF: synthetic method
   static long fromReal$mcJ$sp$(final ConvertableToLong $this, final Real a) {
      return $this.fromReal$mcJ$sp(a);
   }

   default long fromReal$mcJ$sp(final Real a) {
      return a.toLong();
   }

   // $FF: synthetic method
   static long fromType$mcJ$sp$(final ConvertableToLong $this, final Object b, final ConvertableFrom evidence$5) {
      return $this.fromType$mcJ$sp(b, evidence$5);
   }

   default long fromType$mcJ$sp(final Object b, final ConvertableFrom evidence$5) {
      return ConvertableFrom$.MODULE$.apply(evidence$5).toLong(b);
   }

   static void $init$(final ConvertableToLong $this) {
   }
}
