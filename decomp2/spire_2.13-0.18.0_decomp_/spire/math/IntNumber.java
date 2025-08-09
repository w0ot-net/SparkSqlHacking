package spire.math;

import java.math.BigInteger;
import scala.Function1;
import scala.MatchError;
import scala.Option;
import scala.Product;
import scala.Tuple2;
import scala.collection.Iterator;
import scala.math.BigDecimal;
import scala.math.BigInt;
import scala.math.ScalaNumber;
import scala.math.ScalaNumericAnyConversions;
import scala.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.Statics;
import spire.algebra.NRoot;

@ScalaSignature(
   bytes = "\u0006\u0005\t=e!B!C\u0001\n3\u0005\u0002C2\u0001\u0005+\u0007I\u0011\u00013\t\u0011!\u0004!\u0011#Q\u0001\n\u0015DQ!\u001b\u0001\u0005\u0002)DQ!\u001c\u0001\u0005B9DQa\u001e\u0001\u0005\u0002aDQ!\u001f\u0001\u0005\u0002iDQA \u0001\u0005\u0002}Da!a\u0002\u0001\t\u0003y\bBBA\u0005\u0001\u0011\u0005q\u0010\u0003\u0004\u0002\f\u0001!\ta \u0005\u0007\u0003\u001b\u0001A\u0011A@\t\r\u0005=\u0001\u0001\"\u0001\u0000\u0011\u001d\t\t\u0002\u0001C\u0001\u0003'Aq!a\u0007\u0001\t\u0003\ti\u0002C\u0004\u0002&\u0001!\t!a\n\t\u000f\u0005=\u0002\u0001\"\u0001\u00022!1\u00111\t\u0001\u0005\u0002}Dq!!\u0012\u0001\t\u0003\t9\u0005C\u0004\u0002P\u0001!\t!!\u0015\t\u000f\u0005e\u0003\u0001\"\u0001\u0002\\!1\u00111\r\u0001\u0005\u0002iDq!!\u001a\u0001\t\u0003\t9\u0007C\u0004\u0002n\u0001!\t%a\u001c\t\u000f\u0005m\u0004\u0001\"\u0001\u0002~!9\u0011\u0011\u0011\u0001\u0005\u0002\u0005\r\u0005bBAC\u0001\u0011\u0005\u0011q\u0011\u0005\b\u0003\u0017\u0003A\u0011AAG\u0011\u001d\t\t\n\u0001C\u0001\u0003'Cq!a&\u0001\t\u0003\tI\nC\u0004\u0002\u001e\u0002!\t!a(\t\u000f\u0005\r\u0006\u0001\"\u0001\u0002&\"9\u0011\u0011\u0016\u0001\u0005\u0002\u0005-\u0006\u0002CA[\u0001\u0011\u0005!)a.\t\u0011\u0005u\u0006\u0001\"\u0001C\u0003\u007fC\u0001\"a1\u0001\t\u0003\u0011\u0015Q\u0019\u0005\t\u0003\u0013\u0004A\u0011\u0001\"\u0002L\"A\u0011q\u001a\u0001\u0005\u0002\t\u000b\t\u000eC\u0004\u0002V\u0002!\t!a6\t\u000f\u0005m\u0007\u0001\"\u0011\u0002^\"9\u0011\u0011\u001d\u0001\u0005B\u0005\r\bbBAt\u0001\u0011\u0005\u0013\u0011\u001e\u0005\b\u0003[\u0004A\u0011IAx\u0011\u001d\t\u0019\u0010\u0001C!\u0003kDq!!?\u0001\t\u0003\t\u0019\tC\u0004\u0002|\u0002!\t!!@\t\u000f\t\r\u0001\u0001\"\u0001\u0002\u0004\"9!Q\u0001\u0001\u0005\u0002\u0005\r\u0005b\u0002B\u0004\u0001\u0011\u0005\u00111\u0011\u0005\n\u0005\u0013\u0001\u0011\u0011!C\u0001\u0005\u0017A\u0011Ba\u0004\u0001#\u0003%\tA!\u0005\t\u0013\t\u001d\u0002!!A\u0005B\t%\u0002\u0002\u0003B\u0018\u0001\u0005\u0005I\u0011\u0001>\t\u0013\tE\u0002!!A\u0005\u0002\tM\u0002\"\u0003B\u001d\u0001\u0005\u0005I\u0011\tB\u001e\u0011%\u0011I\u0005AA\u0001\n\u0003\u0011Y\u0005C\u0005\u0003P\u0001\t\t\u0011\"\u0011\u0003R!I!Q\u000b\u0001\u0002\u0002\u0013\u0005#qK\u0004\u000b\u00053\u0012\u0015\u0011!E\u0001\u0005\nmc!C!C\u0003\u0003E\tA\u0011B/\u0011\u0019I7\b\"\u0001\u0003v!AQnOA\u0001\n\u000b\u00129\bC\u0005\u0003zm\n\t\u0011\"!\u0003|!I!qP\u001e\u0002\u0002\u0013\u0005%\u0011\u0011\u0005\n\u0005\u001b[\u0014\u0011!C\u0005\u0003c\u0011\u0011\"\u00138u\u001dVl'-\u001a:\u000b\u0005\r#\u0015\u0001B7bi\"T\u0011!R\u0001\u0006gBL'/Z\n\u0006\u0001\u001ds%K\u0016\t\u0003\u00112k\u0011!\u0013\u0006\u0003\u0007*S\u0011aS\u0001\u0006g\u000e\fG.Y\u0005\u0003\u001b&\u00131bU2bY\u0006tU/\u001c2feB\u0011q\nU\u0007\u0002\u0005&\u0011\u0011K\u0011\u0002\u0007\u001dVl'-\u001a:\u0011\u0005M#V\"\u0001&\n\u0005US%a\u0002)s_\u0012,8\r\u001e\t\u0003/\u0002t!\u0001\u00170\u000f\u0005ekV\"\u0001.\u000b\u0005mc\u0016A\u0002\u001fs_>$hh\u0001\u0001\n\u0003-K!a\u0018&\u0002\u000fA\f7m[1hK&\u0011\u0011M\u0019\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u0003?*\u000b\u0011A\\\u000b\u0002KB\u0011qJZ\u0005\u0003O\n\u0013\u0001bU1gK2{gnZ\u0001\u0003]\u0002\na\u0001P5oSRtDCA6m!\ty\u0005\u0001C\u0003d\u0007\u0001\u0007Q-\u0001\u0005u_N#(/\u001b8h)\u0005y\u0007C\u00019u\u001d\t\t(\u000f\u0005\u0002Z\u0015&\u00111OS\u0001\u0007!J,G-\u001a4\n\u0005U4(AB*ue&twM\u0003\u0002t\u0015\u0006\u0019\u0011MY:\u0016\u0003-\faa]5h]VlW#A>\u0011\u0005Mc\u0018BA?K\u0005\rIe\u000e^\u0001\no&$\b.\u001b8J]R,\"!!\u0001\u0011\u0007M\u000b\u0019!C\u0002\u0002\u0006)\u0013qAQ8pY\u0016\fg.\u0001\u0006xSRD\u0017N\u001c'p]\u001e\fAb^5uQ&tGi\\;cY\u0016\f\u0001bY1o\u0005\u0016Le\u000e^\u0001\nG\u0006t')\u001a'p]\u001e\fq![:Fq\u0006\u001cG/\u0001\u0005u_\nKw-\u00138u+\t\t)\u0002E\u0002X\u0003/I1!!\u0007c\u0005\u0019\u0011\u0015nZ%oi\u0006aAo\u001c\"jO\u0012+7-[7bYV\u0011\u0011q\u0004\t\u0004/\u0006\u0005\u0012bAA\u0012E\nQ!)[4EK\u000eLW.\u00197\u0002\u0015Q|'+\u0019;j_:\fG.\u0006\u0002\u0002*A\u0019q*a\u000b\n\u0007\u00055\"I\u0001\u0005SCRLwN\\1m\u0003))h\u000eZ3sYfLgn\u001a\u000b\u0003\u0003g\u0001B!!\u000e\u0002@5\u0011\u0011q\u0007\u0006\u0005\u0003s\tY$\u0001\u0003mC:<'BAA\u001f\u0003\u0011Q\u0017M^1\n\t\u0005\u0005\u0013q\u0007\u0002\u0007\u001f\nTWm\u0019;\u0002\u000f%\u001cx\u000b[8mK\u0006YAm\\;cY\u00164\u0016\r\\;f+\t\tI\u0005E\u0002T\u0003\u0017J1!!\u0014K\u0005\u0019!u.\u001e2mK\u0006Qa\r\\8biZ\u000bG.^3\u0016\u0005\u0005M\u0003cA*\u0002V%\u0019\u0011q\u000b&\u0003\u000b\u0019cw.\u0019;\u0002\u00131|gn\u001a,bYV,WCAA/!\r\u0019\u0016qL\u0005\u0004\u0003CR%\u0001\u0002'p]\u001e\f\u0001\"\u001b8u-\u0006dW/Z\u0001\bG>l\u0007/\u0019:f)\rY\u0018\u0011\u000e\u0005\u0007\u0003W2\u0002\u0019\u0001(\u0002\u0007ID7/\u0001\u0004fcV\fGn\u001d\u000b\u0005\u0003\u0003\t\t\bC\u0004\u0002t]\u0001\r!!\u001e\u0002\tQD\u0017\r\u001e\t\u0004'\u0006]\u0014bAA=\u0015\n\u0019\u0011I\\=\u0002\u0013\u0011*\u0017\u000fJ3rI\u0015\fH\u0003BA\u0001\u0003\u007fBa!a\u001d\u0019\u0001\u0004q\u0015\u0001D;oCJLx\fJ7j]V\u001cX#\u0001(\u0002\u000b\u0011\u0002H.^:\u0015\u00079\u000bI\t\u0003\u0004\u0002li\u0001\rAT\u0001\u0007IQLW.Z:\u0015\u00079\u000by\t\u0003\u0004\u0002lm\u0001\rAT\u0001\u0007I5Lg.^:\u0015\u00079\u000b)\n\u0003\u0004\u0002lq\u0001\rAT\u0001\u0005I\u0011Lg\u000fF\u0002O\u00037Ca!a\u001b\u001e\u0001\u0004q\u0015!\u0002;rk>$Hc\u0001(\u0002\"\"1\u00111\u000e\u0010A\u00029\u000bA\u0001^7pIR\u0019a*a*\t\r\u0005-t\u00041\u0001O\u0003!!\u0018/^8u[>$G\u0003BAW\u0003g\u0003RaUAX\u001d:K1!!-K\u0005\u0019!V\u000f\u001d7fe!1\u00111\u000e\u0011A\u00029\u000b\u0001B]0%[&tWo\u001d\u000b\u0004\u001d\u0006e\u0006BBA^C\u0001\u0007a*A\u0002mQN\faA]0%I&4Hc\u0001(\u0002B\"1\u00111\u0018\u0012A\u00029\u000bqA]0ucV|G\u000fF\u0002O\u0003\u000fDa!a/$\u0001\u0004q\u0015A\u0002:`i6|G\rF\u0002O\u0003\u001bDa!a/%\u0001\u0004q\u0015A\u0003:`iF,x\u000e^7pIR!\u0011QVAj\u0011\u0019\tY,\na\u0001\u001d\u0006\u0019\u0001o\\<\u0015\u00079\u000bI\u000e\u0003\u0004\u0002l\u0019\u0002\rAT\u0001\u0005I\u0005l\u0007\u000fF\u0002O\u0003?Da!a\u001b(\u0001\u0004q\u0015\u0001\u0002\u0013cCJ$2ATAs\u0011\u0019\tY\u0007\u000ba\u0001\u001d\u0006\u0019A%\u001e9\u0015\u00079\u000bY\u000f\u0003\u0004\u0002l%\u0002\rAT\u0001\u000bI1,7o\u001d\u0013mKN\u001cHc\u0001(\u0002r\"1\u00111\u000e\u0016A\u00029\u000b\u0001\u0003J4sK\u0006$XM\u001d\u0013he\u0016\fG/\u001a:\u0015\u00079\u000b9\u0010\u0003\u0004\u0002l-\u0002\rAT\u0001\u0005gF\u0014H/A\u0003oe>|G\u000fF\u0002O\u0003\u007fDaA!\u0001.\u0001\u0004Y\u0018!A6\u0002\u000b\u0019dwn\u001c:\u0002\t\r,\u0017\u000e\\\u0001\u0006e>,h\u000eZ\u0001\u0005G>\u0004\u0018\u0010F\u0002l\u0005\u001bAqaY\u0019\u0011\u0002\u0003\u0007Q-\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\tM!fA3\u0003\u0016-\u0012!q\u0003\t\u0005\u00053\u0011\u0019#\u0004\u0002\u0003\u001c)!!Q\u0004B\u0010\u0003%)hn\u00195fG.,GMC\u0002\u0003\")\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\u0011)Ca\u0007\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0005W\u0001B!!\u000e\u0003.%\u0019Q/a\u000e\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011Q\u000fB\u001b\u0011!\u00119$NA\u0001\u0002\u0004Y\u0018a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0003>A1!q\bB#\u0003kj!A!\u0011\u000b\u0007\t\r#*\u0001\u0006d_2dWm\u0019;j_:LAAa\u0012\u0003B\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\u0011\t\tA!\u0014\t\u0013\t]r'!AA\u0002\u0005U\u0014A\u00059s_\u0012,8\r^#mK6,g\u000e\u001e(b[\u0016$BAa\u000b\u0003T!A!q\u0007\u001d\u0002\u0002\u0003\u000710\u0001\u0005iCND7i\u001c3f)\u0005Y\u0018!C%oi:+XNY3s!\ty5hE\u0003<\u0005?\u0012Y\u0007\u0005\u0004\u0003b\t\u001dTm[\u0007\u0003\u0005GR1A!\u001aK\u0003\u001d\u0011XO\u001c;j[\u0016LAA!\u001b\u0003d\t\t\u0012IY:ue\u0006\u001cGOR;oGRLwN\\\u0019\u0011\t\t5$1O\u0007\u0003\u0005_RAA!\u001d\u0002<\u0005\u0011\u0011n\\\u0005\u0004C\n=DC\u0001B.)\t\u0011Y#A\u0003baBd\u0017\u0010F\u0002l\u0005{BQa\u0019 A\u0002\u0015\fq!\u001e8baBd\u0017\u0010\u0006\u0003\u0003\u0004\n%\u0005\u0003B*\u0003\u0006\u0016L1Aa\"K\u0005\u0019y\u0005\u000f^5p]\"A!1R \u0002\u0002\u0003\u00071.A\u0002yIA\nAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016\u0004"
)
public class IntNumber extends ScalaNumber implements Number, Product {
   private final SafeLong n;

   public static Option unapply(final IntNumber x$0) {
      return IntNumber$.MODULE$.unapply(x$0);
   }

   public static IntNumber apply(final SafeLong n) {
      return IntNumber$.MODULE$.apply(n);
   }

   public static Function1 andThen(final Function1 g) {
      return IntNumber$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return IntNumber$.MODULE$.compose(g);
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

   public SafeLong n() {
      return this.n;
   }

   public String toString() {
      return this.n().toString();
   }

   public IntNumber abs() {
      return new IntNumber(this.n().abs());
   }

   public int signum() {
      return this.n().signum();
   }

   public boolean withinInt() {
      return Number$.MODULE$.minInt().$less$eq(this.n()) && this.n().$less$eq(Number$.MODULE$.maxInt());
   }

   public boolean withinLong() {
      return Number$.MODULE$.minLong().$less$eq(this.n()) && this.n().$less$eq(Number$.MODULE$.maxLong());
   }

   public boolean withinDouble() {
      BigDecimal d = this.n().toBigDecimal();
      return Number$.MODULE$.minDouble().$less$eq(d) && d.$less$eq(Number$.MODULE$.maxDouble());
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

   public BigInt toBigInt() {
      return this.n().toBigInt();
   }

   public BigDecimal toBigDecimal() {
      return this.n().toBigDecimal();
   }

   public Rational toRational() {
      return Rational$.MODULE$.apply(this.n());
   }

   public Object underlying() {
      return this.n().underlying();
   }

   public boolean isWhole() {
      return true;
   }

   public double doubleValue() {
      return this.n().doubleValue();
   }

   public float floatValue() {
      return this.n().floatValue();
   }

   public long longValue() {
      return this.n().longValue();
   }

   public int intValue() {
      return this.n().intValue();
   }

   public int compare(final Number rhs) {
      int var2;
      if (rhs instanceof IntNumber) {
         IntNumber var4 = (IntNumber)rhs;
         SafeLong m = var4.n();
         var2 = this.n().compare(m);
      } else {
         var2 = -rhs.compare(this);
      }

      return var2;
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
      if (that instanceof IntNumber) {
         IntNumber var4 = (IntNumber)that;
         SafeLong n2 = var4.n();
         var2 = BoxesRunTime.equalsNumNum(this.n(), n2);
      } else {
         var2 = that.$eq$eq$eq(this);
      }

      return var2;
   }

   public Number unary_$minus() {
      return Number$.MODULE$.apply(this.n().unary_$minus());
   }

   public Number $plus(final Number rhs) {
      Object var2;
      if (rhs instanceof IntNumber) {
         IntNumber var4 = (IntNumber)rhs;
         SafeLong m = var4.n();
         var2 = new IntNumber(this.n().$plus(m));
      } else {
         var2 = rhs.$plus(this);
      }

      return (Number)var2;
   }

   public Number $times(final Number rhs) {
      Object var2;
      if (rhs instanceof IntNumber) {
         IntNumber var4 = (IntNumber)rhs;
         SafeLong m = var4.n();
         var2 = new IntNumber(this.n().$times(m));
      } else {
         var2 = rhs.$times(this);
      }

      return (Number)var2;
   }

   public Number $minus(final Number rhs) {
      Object var2;
      if (rhs instanceof IntNumber) {
         IntNumber var4 = (IntNumber)rhs;
         SafeLong m = var4.n();
         var2 = new IntNumber(this.n().$minus(m));
      } else {
         var2 = rhs.r_$minus(this);
      }

      return (Number)var2;
   }

   public Number $div(final Number rhs) {
      Object var2;
      if (rhs instanceof IntNumber) {
         IntNumber var6 = (IntNumber)rhs;
         SafeLong m = var6.n();
         SafeLong var8 = this.n();
         Object var3;
         if (var8 instanceof SafeLongLong) {
            SafeLongLong var9 = (SafeLongLong)var8;
            long x = var9.x();
            Object var4;
            if (m instanceof SafeLongLong) {
               SafeLongLong var13 = (SafeLongLong)m;
               long y = var13.x();
               var4 = Number$.MODULE$.apply((double)x / (double)y);
            } else {
               if (!(m instanceof SafeLongBigInteger)) {
                  throw new MatchError(m);
               }

               SafeLongBigInteger var16 = (SafeLongBigInteger)m;
               BigInteger y = var16.x();
               var4 = new DecimalNumber(.MODULE$.BigDecimal().apply(x).$div(.MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(y))));
            }

            var3 = var4;
         } else {
            if (!(var8 instanceof SafeLongBigInteger)) {
               throw new MatchError(var8);
            }

            SafeLongBigInteger var18 = (SafeLongBigInteger)var8;
            BigInteger x = var18.x();
            var3 = Number$.MODULE$.apply(.MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(x)).$div(m.toBigDecimal()));
         }

         var2 = var3;
      } else {
         var2 = rhs.r_$div(this);
      }

      return (Number)var2;
   }

   public Number tquot(final Number rhs) {
      Object var2;
      if (rhs instanceof IntNumber) {
         IntNumber var4 = (IntNumber)rhs;
         SafeLong m = var4.n();
         var2 = new IntNumber(this.n().$div(m));
      } else {
         var2 = rhs.r_tquot(this);
      }

      return (Number)var2;
   }

   public Number tmod(final Number rhs) {
      Object var2;
      if (rhs instanceof IntNumber) {
         IntNumber var4 = (IntNumber)rhs;
         SafeLong m = var4.n();
         var2 = new IntNumber(this.n().$percent(m));
      } else {
         var2 = rhs.r_tmod(this);
      }

      return (Number)var2;
   }

   public Tuple2 tquotmod(final Number rhs) {
      Tuple2 var2;
      if (rhs instanceof IntNumber) {
         IntNumber var4 = (IntNumber)rhs;
         SafeLong m = var4.n();
         var2 = new Tuple2(new IntNumber(this.n().$div(m)), new IntNumber(this.n().$percent(m)));
      } else {
         var2 = rhs.r_tquotmod(this);
      }

      return var2;
   }

   public Number r_$minus(final Number lhs) {
      Object var2;
      if (lhs instanceof IntNumber) {
         IntNumber var4 = (IntNumber)lhs;
         SafeLong m = var4.n();
         var2 = new IntNumber(m.$minus(this.n()));
      } else {
         var2 = lhs.$minus(lhs);
      }

      return (Number)var2;
   }

   public Number r_$div(final Number lhs) {
      Object var2;
      if (lhs instanceof IntNumber) {
         IntNumber var6 = (IntNumber)lhs;
         SafeLong m = var6.n();
         SafeLong var8 = this.n();
         Object var3;
         if (var8 instanceof SafeLongLong) {
            SafeLongLong var9 = (SafeLongLong)var8;
            long x = var9.x();
            Object var4;
            if (m instanceof SafeLongLong) {
               SafeLongLong var13 = (SafeLongLong)m;
               long y = var13.x();
               var4 = Number$.MODULE$.apply((double)y / (double)x);
            } else {
               if (!(m instanceof SafeLongBigInteger)) {
                  throw new MatchError(m);
               }

               SafeLongBigInteger var16 = (SafeLongBigInteger)m;
               BigInteger y = var16.x();
               var4 = new DecimalNumber(.MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(y)).$div(.MODULE$.BigDecimal().apply(x)));
            }

            var3 = var4;
         } else {
            if (!(var8 instanceof SafeLongBigInteger)) {
               throw new MatchError(var8);
            }

            SafeLongBigInteger var18 = (SafeLongBigInteger)var8;
            BigInteger x = var18.x();
            var3 = Number$.MODULE$.apply(m.toBigDecimal().$div(.MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(x))));
         }

         var2 = var3;
      } else {
         var2 = lhs.$div(lhs);
      }

      return (Number)var2;
   }

   public Number r_tquot(final Number lhs) {
      Object var2;
      if (lhs instanceof IntNumber) {
         IntNumber var4 = (IntNumber)lhs;
         SafeLong m = var4.n();
         var2 = new IntNumber(m.$div(this.n()));
      } else {
         var2 = lhs.tquot(lhs);
      }

      return (Number)var2;
   }

   public Number r_tmod(final Number lhs) {
      Object var2;
      if (lhs instanceof IntNumber) {
         IntNumber var4 = (IntNumber)lhs;
         SafeLong m = var4.n();
         var2 = new IntNumber(m.$percent(this.n()));
      } else {
         var2 = lhs.tmod(lhs);
      }

      return (Number)var2;
   }

   public Tuple2 r_tquotmod(final Number lhs) {
      Tuple2 var2;
      if (lhs instanceof IntNumber) {
         IntNumber var4 = (IntNumber)lhs;
         SafeLong m = var4.n();
         var2 = new Tuple2(new IntNumber(m.$div(this.n())), new IntNumber(m.$percent(this.n())));
      } else {
         var2 = lhs.tquotmod(lhs);
      }

      return var2;
   }

   public Number pow(final Number rhs) {
      Number var2;
      if (rhs.canBeInt()) {
         var2 = Number$.MODULE$.apply(this.n().pow(rhs.intValue()));
      } else {
         if (rhs instanceof FloatNumber) {
            FloatNumber var4 = (FloatNumber)rhs;
            double m = var4.n();
            if (this.withinDouble()) {
               var2 = Number$.MODULE$.apply(package$.MODULE$.pow(this.doubleValue(), m));
               return var2;
            }
         }

         var2 = Number$.MODULE$.apply(package$.MODULE$.pow(this.toBigDecimal(), rhs.toBigDecimal()));
      }

      return var2;
   }

   public Number $amp(final Number rhs) {
      if (rhs instanceof IntNumber) {
         IntNumber var4 = (IntNumber)rhs;
         SafeLong x = var4.n();
         IntNumber var2 = new IntNumber(this.n().$amp(x));
         return var2;
      } else {
         throw new IllegalArgumentException(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s not an integer"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{rhs})));
      }
   }

   public Number $bar(final Number rhs) {
      if (rhs instanceof IntNumber) {
         IntNumber var4 = (IntNumber)rhs;
         SafeLong x = var4.n();
         IntNumber var2 = new IntNumber(this.n().$bar(x));
         return var2;
      } else {
         throw new IllegalArgumentException(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s not an integer"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{rhs})));
      }
   }

   public Number $up(final Number rhs) {
      if (rhs instanceof IntNumber) {
         IntNumber var4 = (IntNumber)rhs;
         SafeLong x = var4.n();
         IntNumber var2 = new IntNumber(this.n().$up(x));
         return var2;
      } else {
         throw new IllegalArgumentException(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s not an integer"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{rhs})));
      }
   }

   public Number $less$less(final Number rhs) {
      if (rhs instanceof IntNumber) {
         IntNumber var4 = (IntNumber)rhs;
         SafeLong x = var4.n();
         IntNumber var2 = new IntNumber(this.n().$less$less(x.toInt()));
         return var2;
      } else {
         throw new IllegalArgumentException(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s not an integer"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{rhs})));
      }
   }

   public Number $greater$greater(final Number rhs) {
      if (rhs instanceof IntNumber) {
         IntNumber var4 = (IntNumber)rhs;
         SafeLong x = var4.n();
         IntNumber var2 = new IntNumber(this.n().$greater$greater(x.toInt()));
         return var2;
      } else {
         throw new IllegalArgumentException(scala.collection.StringOps..MODULE$.format$extension(scala.Predef..MODULE$.augmentString("%s not an integer"), scala.runtime.ScalaRunTime..MODULE$.genericWrapArray(new Object[]{rhs})));
      }
   }

   public Number sqrt() {
      return this.withinDouble() ? Number$.MODULE$.apply(Math.sqrt(this.n().toDouble())) : Number$.MODULE$.apply((BigDecimal)((NRoot)spire.std.package.bigDecimal$.MODULE$.BigDecimalAlgebra()).sqrt(this.n().toBigDecimal()));
   }

   public Number nroot(final int k) {
      return this.withinDouble() ? Number$.MODULE$.apply(Math.pow(this.n().toDouble(), (double)1.0F / (double)k)) : Number$.MODULE$.apply((BigDecimal)((NRoot)spire.std.package.bigDecimal$.MODULE$.BigDecimalAlgebra()).nroot(this.n().toBigDecimal(), k));
   }

   public Number floor() {
      return this;
   }

   public Number ceil() {
      return this;
   }

   public Number round() {
      return this;
   }

   public IntNumber copy(final SafeLong n) {
      return new IntNumber(n);
   }

   public SafeLong copy$default$1() {
      return this.n();
   }

   public String productPrefix() {
      return "IntNumber";
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
      return x$1 instanceof IntNumber;
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

   public IntNumber(final SafeLong n) {
      this.n = n;
      ScalaNumericAnyConversions.$init$(this);
      Number.$init$(this);
      Product.$init$(this);
   }
}
