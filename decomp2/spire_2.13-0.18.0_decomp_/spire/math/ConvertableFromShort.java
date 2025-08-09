package spire.math;

import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.package.;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Eb\u0001\u0003\t\u0012!\u0003\r\t!E\u000b\t\u000b\r\u0002A\u0011A\u0013\t\u000b%\u0002A\u0011\u0001\u0016\t\u000bA\u0002A\u0011A\u0019\t\u000bM\u0002A\u0011\u0001\u001b\t\u000be\u0002A\u0011\u0001\u001e\t\u000b}\u0002A\u0011\u0001!\t\u000b\u0015\u0003A\u0011\u0001$\t\u000b-\u0003A\u0011\u0001'\t\u000bi\u0003A\u0011A.\t\u000b\u0001\u0004A\u0011A1\t\u000b\u0019\u0004A\u0011A4\t\u000b1\u0004A\u0011A7\t\u000bI\u0004A\u0011A:\t\u000ba\u0004A\u0011A=\t\u000f\u0005m\u0001\u0001\"\u0001\u0002\u001e\t!2i\u001c8wKJ$\u0018M\u00197f\rJ|Wn\u00155peRT!AE\n\u0002\t5\fG\u000f\u001b\u0006\u0002)\u0005)1\u000f]5sKN\u0019\u0001A\u0006\u000f\u0011\u0005]QR\"\u0001\r\u000b\u0003e\tQa]2bY\u0006L!a\u0007\r\u0003\r\u0005s\u0017PU3g!\rib\u0004I\u0007\u0002#%\u0011q$\u0005\u0002\u0010\u0007>tg/\u001a:uC\ndWM\u0012:p[B\u0011q#I\u0005\u0003Ea\u0011Qa\u00155peR\fa\u0001J5oSR$3\u0001\u0001\u000b\u0002MA\u0011qcJ\u0005\u0003Qa\u0011A!\u00168ji\u00061Ao\u001c\"zi\u0016$\"a\u000b\u0018\u0011\u0005]a\u0013BA\u0017\u0019\u0005\u0011\u0011\u0015\u0010^3\t\u000b=\u0012\u0001\u0019\u0001\u0011\u0002\u0003\u0005\fq\u0001^8TQ>\u0014H\u000f\u0006\u0002!e!)qf\u0001a\u0001A\u0005)Ao\\%oiR\u0011Q\u0007\u000f\t\u0003/YJ!a\u000e\r\u0003\u0007%sG\u000fC\u00030\t\u0001\u0007\u0001%\u0001\u0004u_2{gn\u001a\u000b\u0003wy\u0002\"a\u0006\u001f\n\u0005uB\"\u0001\u0002'p]\u001eDQaL\u0003A\u0002\u0001\nq\u0001^8GY>\fG\u000f\u0006\u0002B\tB\u0011qCQ\u0005\u0003\u0007b\u0011QA\u00127pCRDQa\f\u0004A\u0002\u0001\n\u0001\u0002^8E_V\u0014G.\u001a\u000b\u0003\u000f*\u0003\"a\u0006%\n\u0005%C\"A\u0002#pk\ndW\rC\u00030\u000f\u0001\u0007\u0001%\u0001\u0005u_\nKw-\u00138u)\ti\u0015\f\u0005\u0002O-:\u0011q\n\u0016\b\u0003!Nk\u0011!\u0015\u0006\u0003%\u0012\na\u0001\u0010:p_Rt\u0014\"A\r\n\u0005UC\u0012a\u00029bG.\fw-Z\u0005\u0003/b\u0013aAQ5h\u0013:$(BA+\u0019\u0011\u0015y\u0003\u00021\u0001!\u00031!xNQ5h\t\u0016\u001c\u0017.\\1m)\tav\f\u0005\u0002O;&\u0011a\f\u0017\u0002\u000b\u0005&<G)Z2j[\u0006d\u0007\"B\u0018\n\u0001\u0004\u0001\u0013A\u0003;p%\u0006$\u0018n\u001c8bYR\u0011!-\u001a\t\u0003;\rL!\u0001Z\t\u0003\u0011I\u000bG/[8oC2DQa\f\u0006A\u0002\u0001\n1\u0002^8BY\u001e,'M]1jGR\u0011\u0001n\u001b\t\u0003;%L!A[\t\u0003\u0013\u0005cw-\u001a2sC&\u001c\u0007\"B\u0018\f\u0001\u0004\u0001\u0013A\u0002;p%\u0016\fG\u000e\u0006\u0002ocB\u0011Qd\\\u0005\u0003aF\u0011AAU3bY\")q\u0006\u0004a\u0001A\u0005AAo\u001c(v[\n,'\u000f\u0006\u0002uoB\u0011Q$^\u0005\u0003mF\u0011aAT;nE\u0016\u0014\b\"B\u0018\u000e\u0001\u0004\u0001\u0013A\u0002;p)f\u0004X-\u0006\u0002{}R\u001910!\u0007\u0015\u0007q\fy\u0001\u0005\u0002~}2\u0001AAB@\u000f\u0005\u0004\t\tAA\u0001C#\u0011\t\u0019!!\u0003\u0011\u0007]\t)!C\u0002\u0002\ba\u0011qAT8uQ&tw\rE\u0002\u0018\u0003\u0017I1!!\u0004\u0019\u0005\r\te.\u001f\u0005\n\u0003#q\u0011\u0011!a\u0002\u0003'\t1\"\u001a<jI\u0016t7-\u001a\u00132sA!Q$!\u0006}\u0013\r\t9\"\u0005\u0002\u000e\u0007>tg/\u001a:uC\ndW\rV8\t\u000b=r\u0001\u0019\u0001\u0011\u0002\u0011Q|7\u000b\u001e:j]\u001e$B!a\b\u00020A!\u0011\u0011EA\u0015\u001d\u0011\t\u0019#!\n\u0011\u0005AC\u0012bAA\u00141\u00051\u0001K]3eK\u001aLA!a\u000b\u0002.\t11\u000b\u001e:j]\u001eT1!a\n\u0019\u0011\u0015ys\u00021\u0001!\u0001"
)
public interface ConvertableFromShort extends ConvertableFrom$mcS$sp {
   // $FF: synthetic method
   static byte toByte$(final ConvertableFromShort $this, final short a) {
      return $this.toByte(a);
   }

   default byte toByte(final short a) {
      return this.toByte$mcS$sp(a);
   }

   // $FF: synthetic method
   static short toShort$(final ConvertableFromShort $this, final short a) {
      return $this.toShort(a);
   }

   default short toShort(final short a) {
      return this.toShort$mcS$sp(a);
   }

   // $FF: synthetic method
   static int toInt$(final ConvertableFromShort $this, final short a) {
      return $this.toInt(a);
   }

   default int toInt(final short a) {
      return this.toInt$mcS$sp(a);
   }

   // $FF: synthetic method
   static long toLong$(final ConvertableFromShort $this, final short a) {
      return $this.toLong(a);
   }

   default long toLong(final short a) {
      return this.toLong$mcS$sp(a);
   }

   // $FF: synthetic method
   static float toFloat$(final ConvertableFromShort $this, final short a) {
      return $this.toFloat(a);
   }

   default float toFloat(final short a) {
      return this.toFloat$mcS$sp(a);
   }

   // $FF: synthetic method
   static double toDouble$(final ConvertableFromShort $this, final short a) {
      return $this.toDouble(a);
   }

   default double toDouble(final short a) {
      return this.toDouble$mcS$sp(a);
   }

   // $FF: synthetic method
   static BigInt toBigInt$(final ConvertableFromShort $this, final short a) {
      return $this.toBigInt(a);
   }

   default BigInt toBigInt(final short a) {
      return this.toBigInt$mcS$sp(a);
   }

   // $FF: synthetic method
   static BigDecimal toBigDecimal$(final ConvertableFromShort $this, final short a) {
      return $this.toBigDecimal(a);
   }

   default BigDecimal toBigDecimal(final short a) {
      return this.toBigDecimal$mcS$sp(a);
   }

   // $FF: synthetic method
   static Rational toRational$(final ConvertableFromShort $this, final short a) {
      return $this.toRational(a);
   }

   default Rational toRational(final short a) {
      return this.toRational$mcS$sp(a);
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$(final ConvertableFromShort $this, final short a) {
      return $this.toAlgebraic(a);
   }

   default Algebraic toAlgebraic(final short a) {
      return this.toAlgebraic$mcS$sp(a);
   }

   // $FF: synthetic method
   static Real toReal$(final ConvertableFromShort $this, final short a) {
      return $this.toReal(a);
   }

   default Real toReal(final short a) {
      return this.toReal$mcS$sp(a);
   }

   // $FF: synthetic method
   static Number toNumber$(final ConvertableFromShort $this, final short a) {
      return $this.toNumber(a);
   }

   default Number toNumber(final short a) {
      return this.toNumber$mcS$sp(a);
   }

   // $FF: synthetic method
   static Object toType$(final ConvertableFromShort $this, final short a, final ConvertableTo evidence$19) {
      return $this.toType(a, evidence$19);
   }

   default Object toType(final short a, final ConvertableTo evidence$19) {
      return this.toType$mcS$sp(a, evidence$19);
   }

   // $FF: synthetic method
   static String toString$(final ConvertableFromShort $this, final short a) {
      return $this.toString(a);
   }

   default String toString(final short a) {
      return this.toString$mcS$sp(a);
   }

   // $FF: synthetic method
   static byte toByte$mcS$sp$(final ConvertableFromShort $this, final short a) {
      return $this.toByte$mcS$sp(a);
   }

   default byte toByte$mcS$sp(final short a) {
      return (byte)a;
   }

   // $FF: synthetic method
   static short toShort$mcS$sp$(final ConvertableFromShort $this, final short a) {
      return $this.toShort$mcS$sp(a);
   }

   default short toShort$mcS$sp(final short a) {
      return a;
   }

   // $FF: synthetic method
   static int toInt$mcS$sp$(final ConvertableFromShort $this, final short a) {
      return $this.toInt$mcS$sp(a);
   }

   default int toInt$mcS$sp(final short a) {
      return a;
   }

   // $FF: synthetic method
   static long toLong$mcS$sp$(final ConvertableFromShort $this, final short a) {
      return $this.toLong$mcS$sp(a);
   }

   default long toLong$mcS$sp(final short a) {
      return (long)a;
   }

   // $FF: synthetic method
   static float toFloat$mcS$sp$(final ConvertableFromShort $this, final short a) {
      return $this.toFloat$mcS$sp(a);
   }

   default float toFloat$mcS$sp(final short a) {
      return (float)a;
   }

   // $FF: synthetic method
   static double toDouble$mcS$sp$(final ConvertableFromShort $this, final short a) {
      return $this.toDouble$mcS$sp(a);
   }

   default double toDouble$mcS$sp(final short a) {
      return (double)a;
   }

   // $FF: synthetic method
   static BigInt toBigInt$mcS$sp$(final ConvertableFromShort $this, final short a) {
      return $this.toBigInt$mcS$sp(a);
   }

   default BigInt toBigInt$mcS$sp(final short a) {
      return .MODULE$.BigInt().apply(a);
   }

   // $FF: synthetic method
   static BigDecimal toBigDecimal$mcS$sp$(final ConvertableFromShort $this, final short a) {
      return $this.toBigDecimal$mcS$sp(a);
   }

   default BigDecimal toBigDecimal$mcS$sp(final short a) {
      return .MODULE$.BigDecimal().apply(a);
   }

   // $FF: synthetic method
   static Rational toRational$mcS$sp$(final ConvertableFromShort $this, final short a) {
      return $this.toRational$mcS$sp(a);
   }

   default Rational toRational$mcS$sp(final short a) {
      return Rational$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Algebraic toAlgebraic$mcS$sp$(final ConvertableFromShort $this, final short a) {
      return $this.toAlgebraic$mcS$sp(a);
   }

   default Algebraic toAlgebraic$mcS$sp(final short a) {
      return Algebraic$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Real toReal$mcS$sp$(final ConvertableFromShort $this, final short a) {
      return $this.toReal$mcS$sp(a);
   }

   default Real toReal$mcS$sp(final short a) {
      return Real$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Number toNumber$mcS$sp$(final ConvertableFromShort $this, final short a) {
      return $this.toNumber$mcS$sp(a);
   }

   default Number toNumber$mcS$sp(final short a) {
      return Number$.MODULE$.apply(a);
   }

   // $FF: synthetic method
   static Object toType$mcS$sp$(final ConvertableFromShort $this, final short a, final ConvertableTo evidence$19) {
      return $this.toType$mcS$sp(a, evidence$19);
   }

   default Object toType$mcS$sp(final short a, final ConvertableTo evidence$19) {
      return ConvertableTo$.MODULE$.apply(evidence$19).fromShort(a);
   }

   // $FF: synthetic method
   static String toString$mcS$sp$(final ConvertableFromShort $this, final short a) {
      return $this.toString$mcS$sp(a);
   }

   default String toString$mcS$sp(final short a) {
      return Short.toString(a);
   }

   static void $init$(final ConvertableFromShort $this) {
   }
}
