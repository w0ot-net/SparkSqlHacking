package spire.math;

import algebra.ring.Signed;
import algebra.ring.TruncatedDivision;
import scala.Tuple2;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import spire.algebra.IsRational;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005uc\u0001C\f\u0019!\u0003\r\t\u0001\u0007\u000f\t\u000b}\u0002A\u0011\u0001!\t\u000b\u0011\u0003A\u0011A#\t\u000b\u001d\u0003A\u0011\t%\t\u000bA\u0003A\u0011I)\t\u000bQ\u0003A\u0011I+\t\u000ba\u0003A\u0011I-\t\u000bq\u0003A\u0011I/\t\u000b\u0001\u0004A\u0011I1\t\u000b\u0011\u0004A\u0011A3\t\u000b-\u0004A\u0011\t7\t\u000bI\u0004A\u0011I:\t\u000bU\u0004A\u0011\t<\t\u000ba\u0004A\u0011A=\t\r}\u0004A\u0011AA\u0001\u0011\u001d\t)\u0001\u0001C\u0001\u0003\u000fAq!a\u0003\u0001\t\u0003\ti\u0001C\u0004\u0002\u0012\u0001!\t!a\u0005\t\u000f\u0005]\u0001\u0001\"\u0001\u0002\u001a!9\u0011Q\u0004\u0001\u0005\u0002\u0005}\u0001bBA \u0001\u0011\u0005\u0011\u0011\t\u0005\b\u0003\u000f\u0002A\u0011AA%\u0011\u001d\ty\u0005\u0001C!\u0003#\u0012aBU1uS>t\u0017\r\\%t%\u0016\fGN\u0003\u0002\u001a5\u0005!Q.\u0019;i\u0015\u0005Y\u0012!B:qSJ,7#\u0002\u0001\u001eG5b\u0004C\u0001\u0010\"\u001b\u0005y\"\"\u0001\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\tz\"AB!osJ+g\rE\u0002%O%j\u0011!\n\u0006\u0003Mi\tq!\u00197hK\n\u0014\u0018-\u0003\u0002)K\tQ\u0011j\u001d*bi&|g.\u00197\u0011\u0005)ZS\"\u0001\r\n\u00051B\"\u0001\u0003*bi&|g.\u00197\u0011\u00079J\u0014F\u0004\u00020o9\u0011\u0001G\u000e\b\u0003cUj\u0011A\r\u0006\u0003gQ\na\u0001\u0010:p_Rt4\u0001A\u0005\u00027%\u0011aEG\u0005\u0003q\u0015\nq\u0001]1dW\u0006<W-\u0003\u0002;w\t)qJ\u001d3fe*\u0011\u0001(\n\t\u0004]uJ\u0013B\u0001 <\u0005Y!&/\u001e8dCR,G\rR5wSNLwN\\\"SS:<\u0017A\u0002\u0013j]&$H\u0005F\u0001B!\tq\")\u0003\u0002D?\t!QK\\5u\u0003\u0015y'\u000fZ3s+\u00051\u0005C\u0001\u0016\u0001\u0003\r)\u0017O\u001e\u000b\u0004\u00132s\u0005C\u0001\u0010K\u0013\tYuDA\u0004C_>dW-\u00198\t\u000b5\u001b\u0001\u0019A\u0015\u0002\u0003aDQaT\u0002A\u0002%\n\u0011!_\u0001\u0005]\u0016\fh\u000fF\u0002J%NCQ!\u0014\u0003A\u0002%BQa\u0014\u0003A\u0002%\n!a\u001a;\u0015\u0007%3v\u000bC\u0003N\u000b\u0001\u0007\u0011\u0006C\u0003P\u000b\u0001\u0007\u0011&A\u0003hi\u0016\fh\u000fF\u0002J5nCQ!\u0014\u0004A\u0002%BQa\u0014\u0004A\u0002%\n!\u0001\u001c;\u0015\u0007%sv\fC\u0003N\u000f\u0001\u0007\u0011\u0006C\u0003P\u000f\u0001\u0007\u0011&A\u0003mi\u0016\fh\u000fF\u0002JE\u000eDQ!\u0014\u0005A\u0002%BQa\u0014\u0005A\u0002%\nqaY8na\u0006\u0014X\rF\u0002gS*\u0004\"AH4\n\u0005!|\"aA%oi\")Q*\u0003a\u0001S!)q*\u0003a\u0001S\u0005!1/[4o)\ti\u0007\u000f\u0005\u0002/]&\u0011qn\u000f\u0002\u0005'&<g\u000eC\u0003r\u0015\u0001\u0007\u0011&A\u0001b\u0003\u0019\u0019\u0018n\u001a8v[R\u0011a\r\u001e\u0005\u0006c.\u0001\r!K\u0001\u0004C\n\u001cHCA\u0015x\u0011\u0015\tH\u00021\u0001*\u0003!!x\u000eR8vE2,GC\u0001>~!\tq20\u0003\u0002}?\t1Ai\\;cY\u0016DQA`\u0007A\u0002%\n\u0011A]\u0001\u0005G\u0016LG\u000eF\u0002*\u0003\u0007AQ!\u001d\bA\u0002%\nQA\u001a7p_J$2!KA\u0005\u0011\u0015\tx\u00021\u0001*\u0003\u0015\u0011x.\u001e8e)\rI\u0013q\u0002\u0005\u0006cB\u0001\r!K\u0001\u000bi>\u0014\u0016\r^5p]\u0006dGcA\u0015\u0002\u0016!)\u0011/\u0005a\u0001S\u00059\u0011n],i_2,GcA%\u0002\u001c!)\u0011O\u0005a\u0001S\u0005YAo\u001c\"jO&sGo\u00149u)\u0011\t\t#!\u0010\u0011\r\u0005\r\u0012\u0011FA\u0017\u001b\t\t)CC\u0002\u0002(i\tA!\u001e;jY&!\u00111FA\u0013\u0005\ry\u0005\u000f\u001e\t\u0005\u0003_\t9D\u0004\u0003\u00022\u0005UbbA\u0019\u00024%\t\u0001%\u0003\u00029?%!\u0011\u0011HA\u001e\u0005\u0019\u0011\u0015nZ%oi*\u0011\u0001h\b\u0005\u0006cN\u0001\r!K\u0001\u0006iF,x\u000e\u001e\u000b\u0006S\u0005\r\u0013Q\t\u0005\u0006\u001bR\u0001\r!\u000b\u0005\u0006\u001fR\u0001\r!K\u0001\u0005i6|G\rF\u0003*\u0003\u0017\ni\u0005C\u0003N+\u0001\u0007\u0011\u0006C\u0003P+\u0001\u0007\u0011&\u0001\u0005ucV|G/\\8e)\u0019\t\u0019&!\u0017\u0002\\A)a$!\u0016*S%\u0019\u0011qK\u0010\u0003\rQ+\b\u000f\\33\u0011\u0015ie\u00031\u0001*\u0011\u0015ye\u00031\u0001*\u0001"
)
public interface RationalIsReal extends IsRational, TruncatedDivision.forCommutativeRing {
   // $FF: synthetic method
   static RationalIsReal order$(final RationalIsReal $this) {
      return $this.order();
   }

   default RationalIsReal order() {
      return this;
   }

   // $FF: synthetic method
   static boolean eqv$(final RationalIsReal $this, final Rational x, final Rational y) {
      return $this.eqv(x, y);
   }

   default boolean eqv(final Rational x, final Rational y) {
      return BoxesRunTime.equalsNumNum(x, y);
   }

   // $FF: synthetic method
   static boolean neqv$(final RationalIsReal $this, final Rational x, final Rational y) {
      return $this.neqv(x, y);
   }

   default boolean neqv(final Rational x, final Rational y) {
      return !BoxesRunTime.equalsNumNum(x, y);
   }

   // $FF: synthetic method
   static boolean gt$(final RationalIsReal $this, final Rational x, final Rational y) {
      return $this.gt(x, y);
   }

   default boolean gt(final Rational x, final Rational y) {
      return x.$greater(y);
   }

   // $FF: synthetic method
   static boolean gteqv$(final RationalIsReal $this, final Rational x, final Rational y) {
      return $this.gteqv(x, y);
   }

   default boolean gteqv(final Rational x, final Rational y) {
      return x.$greater$eq(y);
   }

   // $FF: synthetic method
   static boolean lt$(final RationalIsReal $this, final Rational x, final Rational y) {
      return $this.lt(x, y);
   }

   default boolean lt(final Rational x, final Rational y) {
      return x.$less(y);
   }

   // $FF: synthetic method
   static boolean lteqv$(final RationalIsReal $this, final Rational x, final Rational y) {
      return $this.lteqv(x, y);
   }

   default boolean lteqv(final Rational x, final Rational y) {
      return x.$less$eq(y);
   }

   // $FF: synthetic method
   static int compare$(final RationalIsReal $this, final Rational x, final Rational y) {
      return $this.compare(x, y);
   }

   default int compare(final Rational x, final Rational y) {
      return x.compare(y);
   }

   // $FF: synthetic method
   static Signed.Sign sign$(final RationalIsReal $this, final Rational a) {
      return $this.sign(a);
   }

   default Signed.Sign sign(final Rational a) {
      return a.sign();
   }

   // $FF: synthetic method
   static int signum$(final RationalIsReal $this, final Rational a) {
      return $this.signum(a);
   }

   default int signum(final Rational a) {
      return a.signum();
   }

   // $FF: synthetic method
   static Rational abs$(final RationalIsReal $this, final Rational a) {
      return $this.abs(a);
   }

   default Rational abs(final Rational a) {
      return a.abs();
   }

   // $FF: synthetic method
   static double toDouble$(final RationalIsReal $this, final Rational r) {
      return $this.toDouble(r);
   }

   default double toDouble(final Rational r) {
      return r.toDouble();
   }

   // $FF: synthetic method
   static Rational ceil$(final RationalIsReal $this, final Rational a) {
      return $this.ceil(a);
   }

   default Rational ceil(final Rational a) {
      return a.ceil();
   }

   // $FF: synthetic method
   static Rational floor$(final RationalIsReal $this, final Rational a) {
      return $this.floor(a);
   }

   default Rational floor(final Rational a) {
      return a.floor();
   }

   // $FF: synthetic method
   static Rational round$(final RationalIsReal $this, final Rational a) {
      return $this.round(a);
   }

   default Rational round(final Rational a) {
      return a.round();
   }

   // $FF: synthetic method
   static Rational toRational$(final RationalIsReal $this, final Rational a) {
      return $this.toRational(a);
   }

   default Rational toRational(final Rational a) {
      return a;
   }

   // $FF: synthetic method
   static boolean isWhole$(final RationalIsReal $this, final Rational a) {
      return $this.isWhole(a);
   }

   default boolean isWhole(final Rational a) {
      return a.isWhole();
   }

   // $FF: synthetic method
   static BigInt toBigIntOpt$(final RationalIsReal $this, final Rational a) {
      return $this.toBigIntOpt(a);
   }

   default BigInt toBigIntOpt(final Rational a) {
      return a.isWhole() ? (BigInt).MODULE$.apply(a.toBigInt()) : (BigInt).MODULE$.empty();
   }

   // $FF: synthetic method
   static Rational tquot$(final RationalIsReal $this, final Rational x, final Rational y) {
      return $this.tquot(x, y);
   }

   default Rational tquot(final Rational x, final Rational y) {
      return x.tquot(y);
   }

   // $FF: synthetic method
   static Rational tmod$(final RationalIsReal $this, final Rational x, final Rational y) {
      return $this.tmod(x, y);
   }

   default Rational tmod(final Rational x, final Rational y) {
      return x.tmod(y);
   }

   // $FF: synthetic method
   static Tuple2 tquotmod$(final RationalIsReal $this, final Rational x, final Rational y) {
      return $this.tquotmod(x, y);
   }

   default Tuple2 tquotmod(final Rational x, final Rational y) {
      return x.tquotmod(y);
   }

   static void $init$(final RationalIsReal $this) {
   }
}
