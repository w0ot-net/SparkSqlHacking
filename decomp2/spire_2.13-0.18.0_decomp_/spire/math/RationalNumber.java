package spire.math;

import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Tuple2;
import scala.Double.;
import scala.collection.Iterator;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.math.ScalaNumber;
import scala.math.ScalaNumericAnyConversions;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import spire.algebra.IsReal;
import spire.algebra.IsReal$;

@ScalaSignature(
   bytes = "\u0006\u0005\tEc!\u0002\u001e<\u0001nz\u0004\u0002\u0003/\u0001\u0005+\u0007I\u0011A/\t\u0011\u0005\u0004!\u0011#Q\u0001\nyCQA\u0019\u0001\u0005\u0002\rDQA\u001a\u0001\u0005B\u001dDQ\u0001\u001d\u0001\u0005\u0002EDQA\u001d\u0001\u0005\u0002MDQa\u001e\u0001\u0005\u0002aDQ\u0001 \u0001\u0005\u0002aDQ! \u0001\u0005\u0002aDQA \u0001\u0005\u0002aDQa \u0001\u0005\u0002aDa!!\u0001\u0001\t\u0003A\bbBA\u0002\u0001\u0011\u0005\u0011Q\u0001\u0005\u0007\u0003\u000f\u0001A\u0011\u0001=\t\u000f\u0005%\u0001\u0001\"\u0001\u0002\f!9\u00111\u0003\u0001\u0005\u0002\u0005U\u0001bBA\u000f\u0001\u0011\u0005\u0011q\u0004\u0005\u0007\u0003O\u0001A\u0011A:\t\u000f\u0005%\u0002\u0001\"\u0001\u0002,!9\u00111\u0007\u0001\u0005\u0002\u0005U\u0002BBA\u001f\u0001\u0011\u0005Q\fC\u0004\u0002@\u0001!\t!!\u0011\t\u000f\u0005\u001d\u0003\u0001\"\u0011\u0002J!9\u0011Q\u000b\u0001\u0005\u0002\u0005]\u0003BBA.\u0001\u0011\u0005\u0011\u000fC\u0004\u0002^\u0001!\t!a\u0018\t\u000f\u0005\r\u0004\u0001\"\u0001\u0002f!9\u0011\u0011\u000e\u0001\u0005\u0002\u0005-\u0004bBA8\u0001\u0011\u0005\u0011\u0011\u000f\u0005\b\u0003k\u0002A\u0011AA<\u0011\u001d\tY\b\u0001C\u0001\u0003{Bq!!!\u0001\t\u0003\t\u0019\tC\u0004\u0002\n\u0002!\t!a#\t\u000f\u0005=\u0005\u0001\"\u0001\u0002\u0012\"9\u0011Q\u0013\u0001\u0005\u0002\u0005]\u0005bBAN\u0001\u0011\u0005\u0011Q\u0014\u0005\b\u0003O\u0003A\u0011AAU\u0011\u001d\ti\u000b\u0001C\u0001\u0003_Ca!a-\u0001\t\u0003\t\bBBA[\u0001\u0011\u0005\u0011\u000f\u0003\u0004\u00028\u0002!\t!\u001d\u0005\n\u0003s\u0003\u0011\u0011!C\u0001\u0003wC\u0011\"a0\u0001#\u0003%\t!!1\t\u0013\u0005]\u0007!!A\u0005B\u0005e\u0007\u0002CAu\u0001\u0005\u0005I\u0011A:\t\u0013\u0005-\b!!A\u0005\u0002\u00055\b\"CAz\u0001\u0005\u0005I\u0011IA{\u0011%\u0011\u0019\u0001AA\u0001\n\u0003\u0011)\u0001C\u0005\u0003\n\u0001\t\t\u0011\"\u0011\u0003\f!I!q\u0002\u0001\u0002\u0002\u0013\u0005#\u0011C\u0004\u000b\u0005'Y\u0014\u0011!E\u0001w\tUa!\u0003\u001e<\u0003\u0003E\ta\u000fB\f\u0011\u0019\u0011G\u0007\"\u0001\u00030!Aa\rNA\u0001\n\u000b\u0012\t\u0004C\u0005\u00034Q\n\t\u0011\"!\u00036!I!\u0011\b\u001b\u0002\u0002\u0013\u0005%1\b\u0005\n\u0005\u000f\"\u0014\u0011!C\u0005\u0005\u0013\u0012aBU1uS>t\u0017\r\u001c(v[\n,'O\u0003\u0002={\u0005!Q.\u0019;i\u0015\u0005q\u0014!B:qSJ,7#\u0002\u0001A\u000f.{\u0005CA!F\u001b\u0005\u0011%B\u0001\u001fD\u0015\u0005!\u0015!B:dC2\f\u0017B\u0001$C\u0005-\u00196-\u00197b\u001dVl'-\u001a:\u0011\u0005!KU\"A\u001e\n\u0005)[$A\u0002(v[\n,'\u000f\u0005\u0002M\u001b6\t1)\u0003\u0002O\u0007\n9\u0001K]8ek\u000e$\bC\u0001)Z\u001d\t\tvK\u0004\u0002S-6\t1K\u0003\u0002U+\u00061AH]8piz\u001a\u0001!C\u0001E\u0013\tA6)A\u0004qC\u000e\\\u0017mZ3\n\u0005i[&\u0001D*fe&\fG.\u001b>bE2,'B\u0001-D\u0003\u0005qW#\u00010\u0011\u0005!{\u0016B\u00011<\u0005!\u0011\u0016\r^5p]\u0006d\u0017A\u00018!\u0003\u0019a\u0014N\\5u}Q\u0011A-\u001a\t\u0003\u0011\u0002AQ\u0001X\u0002A\u0002y\u000b\u0001\u0002^8TiJLgn\u001a\u000b\u0002QB\u0011\u0011.\u001c\b\u0003U.\u0004\"AU\"\n\u00051\u001c\u0015A\u0002)sK\u0012,g-\u0003\u0002o_\n11\u000b\u001e:j]\u001eT!\u0001\\\"\u0002\u0007\u0005\u00147/F\u0001H\u0003\u0019\u0019\u0018n\u001a8v[V\tA\u000f\u0005\u0002Mk&\u0011ao\u0011\u0002\u0004\u0013:$\u0018!C<ji\"Lg.\u00138u+\u0005I\bC\u0001'{\u0013\tY8IA\u0004C_>dW-\u00198\u0002\u0015]LG\u000f[5o\u0019>tw-\u0001\u0007xSRD\u0017N\u001c#pk\ndW-\u0001\u0005dC:\u0014U-\u00138u\u0003%\u0019\u0017M\u001c\"f\u0019>tw-A\u0004jg\u0016C\u0018m\u0019;\u0002\u0015UtG-\u001a:ms&tw\rF\u0001_\u0003\u001dI7o\u00165pY\u0016\f1\u0002Z8vE2,g+\u00197vKV\u0011\u0011Q\u0002\t\u0004\u0019\u0006=\u0011bAA\t\u0007\n1Ai\\;cY\u0016\f!B\u001a7pCR4\u0016\r\\;f+\t\t9\u0002E\u0002M\u00033I1!a\u0007D\u0005\u00151En\\1u\u0003%awN\\4WC2,X-\u0006\u0002\u0002\"A\u0019A*a\t\n\u0007\u0005\u00152I\u0001\u0003M_:<\u0017\u0001C5oiZ\u000bG.^3\u0002\u0011Q|')[4J]R,\"!!\f\u0011\u0007A\u000by#C\u0002\u00022m\u0013aAQ5h\u0013:$\u0018\u0001\u0004;p\u0005&<G)Z2j[\u0006dWCAA\u001c!\r\u0001\u0016\u0011H\u0005\u0004\u0003wY&A\u0003\"jO\u0012+7-[7bY\u0006QAo\u001c*bi&|g.\u00197\u0002\u000f\r|W\u000e]1sKR\u0019A/a\u0011\t\r\u0005\u0015c\u00031\u0001H\u0003\r\u0011\bn]\u0001\u0007KF,\u0018\r\\:\u0015\u0007e\fY\u0005C\u0004\u0002N]\u0001\r!a\u0014\u0002\tQD\u0017\r\u001e\t\u0004\u0019\u0006E\u0013bAA*\u0007\n\u0019\u0011I\\=\u0002\u0013\u0011*\u0017\u000fJ3rI\u0015\fHcA=\u0002Z!1\u0011Q\n\rA\u0002\u001d\u000bA\"\u001e8bef|F%\\5okN\fQ\u0001\n9mkN$2aRA1\u0011\u0019\t)E\u0007a\u0001\u000f\u00061A\u0005^5nKN$2aRA4\u0011\u0019\t)e\u0007a\u0001\u000f\u00061A%\\5okN$2aRA7\u0011\u0019\t)\u0005\ba\u0001\u000f\u0006!A\u0005Z5w)\r9\u00151\u000f\u0005\u0007\u0003\u000bj\u0002\u0019A$\u0002\u000bQ\fXo\u001c;\u0015\u0007\u001d\u000bI\b\u0003\u0004\u0002Fy\u0001\raR\u0001\u0005i6|G\rF\u0002H\u0003\u007fBa!!\u0012 \u0001\u00049\u0015\u0001\u0003:`I5Lg.^:\u0015\u0007\u001d\u000b)\t\u0003\u0004\u0002\b\u0002\u0002\raR\u0001\u0004Y\"\u001c\u0018A\u0002:`I\u0011Lg\u000fF\u0002H\u0003\u001bCa!a\"\"\u0001\u00049\u0015a\u0002:`iF,x\u000e\u001e\u000b\u0004\u000f\u0006M\u0005BBADE\u0001\u0007q)\u0001\u0004s?Rlw\u000e\u001a\u000b\u0004\u000f\u0006e\u0005BBADG\u0001\u0007q)\u0001\u0005ucV|G/\\8e)\u0011\ty*!*\u0011\u000b1\u000b\tkR$\n\u0007\u0005\r6I\u0001\u0004UkBdWM\r\u0005\u0007\u0003\u000b\"\u0003\u0019A$\u0002\u0015I|F/];pi6|G\r\u0006\u0003\u0002 \u0006-\u0006BBADK\u0001\u0007q)A\u0002q_^$2aRAY\u0011\u0019\t)E\na\u0001\u000f\u0006)a\r\\8pe\u0006!1-Z5m\u0003\u0015\u0011x.\u001e8e\u0003\u0011\u0019w\u000e]=\u0015\u0007\u0011\fi\fC\u0004]UA\u0005\t\u0019\u00010\u0002\u001d\r|\u0007/\u001f\u0013eK\u001a\fW\u000f\u001c;%cU\u0011\u00111\u0019\u0016\u0004=\u0006\u00157FAAd!\u0011\tI-a5\u000e\u0005\u0005-'\u0002BAg\u0003\u001f\f\u0011\"\u001e8dQ\u0016\u001c7.\u001a3\u000b\u0007\u0005E7)\u0001\u0006b]:|G/\u0019;j_:LA!!6\u0002L\n\tRO\\2iK\u000e\\W\r\u001a,be&\fgnY3\u0002\u001bA\u0014x\u000eZ;diB\u0013XMZ5y+\t\tY\u000e\u0005\u0003\u0002^\u0006\u001dXBAAp\u0015\u0011\t\t/a9\u0002\t1\fgn\u001a\u0006\u0003\u0003K\fAA[1wC&\u0019a.a8\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011qJAx\u0011!\t\tPLA\u0001\u0002\u0004!\u0018a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0002xB1\u0011\u0011`A\u0000\u0003\u001fj!!a?\u000b\u0007\u0005u8)\u0001\u0006d_2dWm\u0019;j_:LAA!\u0001\u0002|\nA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\rI(q\u0001\u0005\n\u0003c\u0004\u0014\u0011!a\u0001\u0003\u001f\n!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u00111\u001cB\u0007\u0011!\t\t0MA\u0001\u0002\u0004!\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003Q\faBU1uS>t\u0017\r\u001c(v[\n,'\u000f\u0005\u0002IiM)AG!\u0007\u0003&A1!1\u0004B\u0011=\u0012l!A!\b\u000b\u0007\t}1)A\u0004sk:$\u0018.\\3\n\t\t\r\"Q\u0004\u0002\u0012\u0003\n\u001cHO]1di\u001a+hn\u0019;j_:\f\u0004\u0003\u0002B\u0014\u0005[i!A!\u000b\u000b\t\t-\u00121]\u0001\u0003S>L1A\u0017B\u0015)\t\u0011)\u0002\u0006\u0002\u0002\\\u0006)\u0011\r\u001d9msR\u0019AMa\u000e\t\u000bq;\u0004\u0019\u00010\u0002\u000fUt\u0017\r\u001d9msR!!Q\bB\"!\u0011a%q\b0\n\u0007\t\u00053I\u0001\u0004PaRLwN\u001c\u0005\t\u0005\u000bB\u0014\u0011!a\u0001I\u0006\u0019\u0001\u0010\n\u0019\u0002\u0019]\u0014\u0018\u000e^3SKBd\u0017mY3\u0015\u0005\t-\u0003\u0003BAo\u0005\u001bJAAa\u0014\u0002`\n1qJ\u00196fGR\u0004"
)
public class RationalNumber extends ScalaNumber implements Number, Product {
   private final Rational n;

   public static Option unapply(final RationalNumber x$0) {
      return RationalNumber$.MODULE$.unapply(x$0);
   }

   public static RationalNumber apply(final Rational n) {
      return RationalNumber$.MODULE$.apply(n);
   }

   public static Function1 andThen(final Function1 g) {
      return RationalNumber$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return RationalNumber$.MODULE$.compose(g);
   }

   public Iterator productElementNames() {
      return Product.productElementNames$(this);
   }

   public final Number $times$times(final Number rhs) {
      return Number.$times$times$(this, rhs);
   }

   public boolean $eq$bang$eq(final Number rhs) {
      return Number.$eq$bang$eq$(this, rhs);
   }

   public Number min(final Number rhs) {
      return Number.min$(this, rhs);
   }

   public Number max(final Number rhs) {
      return Number.max$(this, rhs);
   }

   public final boolean $less(final Number rhs) {
      return Number.$less$(this, rhs);
   }

   public final boolean $less$eq(final Number rhs) {
      return Number.$less$eq$(this, rhs);
   }

   public final boolean $greater(final Number rhs) {
      return Number.$greater$(this, rhs);
   }

   public final boolean $greater$eq(final Number rhs) {
      return Number.$greater$eq$(this, rhs);
   }

   public Number $amp(final Number rhs) {
      return Number.$amp$(this, rhs);
   }

   public Number $bar(final Number rhs) {
      return Number.$bar$(this, rhs);
   }

   public Number $up(final Number rhs) {
      return Number.$up$(this, rhs);
   }

   public Number $less$less(final Number rhs) {
      return Number.$less$less$(this, rhs);
   }

   public Number $greater$greater(final Number rhs) {
      return Number.$greater$greater$(this, rhs);
   }

   public char toChar() {
      return ScalaNumericAnyConversions.toChar$(this);
   }

   public byte toByte() {
      return ScalaNumericAnyConversions.toByte$(this);
   }

   public short toShort() {
      return ScalaNumericAnyConversions.toShort$(this);
   }

   public int toInt() {
      return ScalaNumericAnyConversions.toInt$(this);
   }

   public long toLong() {
      return ScalaNumericAnyConversions.toLong$(this);
   }

   public float toFloat() {
      return ScalaNumericAnyConversions.toFloat$(this);
   }

   public double toDouble() {
      return ScalaNumericAnyConversions.toDouble$(this);
   }

   public boolean isValidByte() {
      return ScalaNumericAnyConversions.isValidByte$(this);
   }

   public boolean isValidShort() {
      return ScalaNumericAnyConversions.isValidShort$(this);
   }

   public boolean isValidInt() {
      return ScalaNumericAnyConversions.isValidInt$(this);
   }

   public boolean isValidChar() {
      return ScalaNumericAnyConversions.isValidChar$(this);
   }

   public int unifiedPrimitiveHashcode() {
      return ScalaNumericAnyConversions.unifiedPrimitiveHashcode$(this);
   }

   public boolean unifiedPrimitiveEquals(final Object x) {
      return ScalaNumericAnyConversions.unifiedPrimitiveEquals$(this, x);
   }

   public Rational n() {
      return this.n;
   }

   public String toString() {
      return this.n().toString();
   }

   public Number abs() {
      return new RationalNumber(this.n().abs());
   }

   public int signum() {
      return this.n().signum();
   }

   public boolean withinInt() {
      return Rational$.MODULE$.apply(Integer.MIN_VALUE).$less$eq(this.n()) && this.n().$less$eq(Rational$.MODULE$.apply(Integer.MAX_VALUE));
   }

   public boolean withinLong() {
      return Rational$.MODULE$.apply(Long.MIN_VALUE).$less$eq(this.n()) && this.n().$less$eq(Rational$.MODULE$.apply(Long.MAX_VALUE));
   }

   public boolean withinDouble() {
      return Rational$.MODULE$.apply(.MODULE$.MinValue()).$less$eq(this.n()) && this.n().$less$eq(Rational$.MODULE$.apply(Double.MAX_VALUE));
   }

   public boolean canBeInt() {
      return this.isWhole() && this.withinInt();
   }

   public boolean canBeLong() {
      return this.isWhole() && this.withinLong();
   }

   public boolean isExact() {
      return true;
   }

   public Rational underlying() {
      return this.n();
   }

   public boolean isWhole() {
      return this.n().isWhole();
   }

   public double doubleValue() {
      return this.n().toDouble();
   }

   public float floatValue() {
      return this.n().toFloat();
   }

   public long longValue() {
      return this.n().toLong();
   }

   public int intValue() {
      return this.n().toInt();
   }

   public BigInt toBigInt() {
      return this.n().toBigInt();
   }

   public BigDecimal toBigDecimal() {
      return this.n().toBigDecimal(scala.package..MODULE$.BigDecimal().defaultMathContext());
   }

   public Rational toRational() {
      return this.n();
   }

   public int compare(final Number rhs) {
      return this.n().compare(rhs.toRational());
   }

   public boolean equals(final Object that) {
      boolean var2;
      if (that instanceof Number) {
         Number var4 = (Number)that;
         var2 = this.$eq$eq$eq(var4);
      } else {
         var2 = BoxesRunTime.equalsNumObject(this.n(), that);
      }

      return var2;
   }

   public boolean $eq$eq$eq(final Number that) {
      boolean var2;
      if (that instanceof RationalNumber) {
         RationalNumber var4 = (RationalNumber)that;
         Rational n2 = var4.n();
         var2 = BoxesRunTime.equalsNumNum(this.n(), n2);
      } else if (that instanceof IntNumber) {
         IntNumber var6 = (IntNumber)that;
         SafeLong m = var6.n();
         var2 = BoxesRunTime.equalsNumNum(this.n(), m.toBigDecimal());
      } else if (that instanceof FloatNumber) {
         FloatNumber var8 = (FloatNumber)that;
         double m = var8.n();
         var2 = BoxesRunTime.equalsNumObject(this.n(), BoxesRunTime.boxToDouble(m));
      } else {
         if (!(that instanceof DecimalNumber)) {
            throw new MatchError(that);
         }

         DecimalNumber var11 = (DecimalNumber)that;
         BigDecimal m = var11.n();
         var2 = BoxesRunTime.equalsNumNum(this.n(), m);
      }

      return var2;
   }

   public Number unary_$minus() {
      return Number$.MODULE$.apply(this.n().unary_$minus());
   }

   public Number $plus(final Number rhs) {
      return Number$.MODULE$.apply(this.n().$plus(rhs.toRational()));
   }

   public Number $times(final Number rhs) {
      return Number$.MODULE$.apply(this.n().$times(rhs.toRational()));
   }

   public Number $minus(final Number rhs) {
      return Number$.MODULE$.apply(this.n().$minus(rhs.toRational()));
   }

   public Number $div(final Number rhs) {
      return Number$.MODULE$.apply(this.n().$div(rhs.toRational()));
   }

   public Number tquot(final Number rhs) {
      return Number$.MODULE$.apply(this.n().tquot(rhs.toRational()));
   }

   public Number tmod(final Number rhs) {
      return Number$.MODULE$.apply(this.n().tmod(rhs.toRational()));
   }

   public Number r_$minus(final Number lhs) {
      return Number$.MODULE$.apply(lhs.toRational().$minus(this.n()));
   }

   public Number r_$div(final Number lhs) {
      return Number$.MODULE$.apply(lhs.toRational().$div(this.n()));
   }

   public Number r_tquot(final Number lhs) {
      return Number$.MODULE$.apply(lhs.toRational().tquot(this.n()));
   }

   public Number r_tmod(final Number lhs) {
      return Number$.MODULE$.apply(lhs.toRational().tmod(this.n()));
   }

   public Tuple2 tquotmod(final Number rhs) {
      Tuple2 t = this.n().tquotmod(rhs.toRational());
      return new Tuple2(Number$.MODULE$.apply((Rational)t._1()), Number$.MODULE$.apply((Rational)t._2()));
   }

   public Tuple2 r_tquotmod(final Number lhs) {
      Tuple2 t = lhs.toRational().tquotmod(this.n());
      return new Tuple2(Number$.MODULE$.apply((Rational)t._1()), Number$.MODULE$.apply((Rational)t._2()));
   }

   public Number pow(final Number rhs) {
      return rhs.canBeInt() ? Number$.MODULE$.apply(this.n().pow(rhs.intValue())) : Number$.MODULE$.apply(package$.MODULE$.pow(this.n().toDouble(), rhs.toDouble()));
   }

   public Number floor() {
      return new RationalNumber((Rational)IsReal$.MODULE$.apply((IsReal)Rational$.MODULE$.RationalAlgebra()).floor(this.n()));
   }

   public Number ceil() {
      return new RationalNumber((Rational)IsReal$.MODULE$.apply((IsReal)Rational$.MODULE$.RationalAlgebra()).ceil(this.n()));
   }

   public Number round() {
      return new RationalNumber((Rational)IsReal$.MODULE$.apply((IsReal)Rational$.MODULE$.RationalAlgebra()).round(this.n()));
   }

   public RationalNumber copy(final Rational n) {
      return new RationalNumber(n);
   }

   public Rational copy$default$1() {
      return this.n();
   }

   public String productPrefix() {
      return "RationalNumber";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = this.n();
            break;
         default:
            var10000 = Statics.ioobe(x$1);
      }

      return var10000;
   }

   public Iterator productIterator() {
      return scala.runtime.ScalaRunTime..MODULE$.typedProductIterator(this);
   }

   public boolean canEqual(final Object x$1) {
      return x$1 instanceof RationalNumber;
   }

   public String productElementName(final int x$1) {
      String var10000;
      switch (x$1) {
         case 0:
            var10000 = "n";
            break;
         default:
            var10000 = (String)Statics.ioobe(x$1);
      }

      return var10000;
   }

   public int hashCode() {
      return scala.runtime.ScalaRunTime..MODULE$._hashCode(this);
   }

   public RationalNumber(final Rational n) {
      this.n = n;
      ScalaNumericAnyConversions.$init$(this);
      Number.$init$(this);
      Product.$init$(this);
   }
}
