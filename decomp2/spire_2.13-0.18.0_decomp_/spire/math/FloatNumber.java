package spire.math;

import java.math.BigInteger;
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

@ScalaSignature(
   bytes = "\u0006\u0005\t\u0015d!\u0002\u001f>\u0001v\n\u0005\u0002\u00030\u0001\u0005+\u0007I\u0011A0\t\u0011\r\u0004!\u0011#Q\u0001\n\u0001DQ\u0001\u001a\u0001\u0005\u0002\u0015DQ\u0001\u001b\u0001\u0005B%DQA\u001d\u0001\u0005\u0002MDQ\u0001\u001e\u0001\u0005\u0002UDQ!\u001f\u0001\u0005\u0002iDQA \u0001\u0005\u0002iDQa \u0001\u0005\u0002iDa!!\u0001\u0001\t\u0003Q\bBBA\u0002\u0001\u0011\u0005!\u0010\u0003\u0004\u0002\u0006\u0001!\tA\u001f\u0005\b\u0003\u000f\u0001A\u0011AA\u0005\u0011\u0019\tI\u0002\u0001C\u0001u\"1\u00111\u0004\u0001\u0005\u0002}Cq!!\b\u0001\t\u0003\ty\u0002C\u0004\u0002(\u0001!\t!!\u000b\t\r\u0005E\u0002\u0001\"\u0001v\u0011\u001d\t\u0019\u0004\u0001C\u0001\u0003kAq!!\u0010\u0001\t\u0003\ty\u0004C\u0004\u0002H\u0001!\t!!\u0013\t\u000f\u0005E\u0003\u0001\"\u0001\u0002T!9\u0011\u0011\f\u0001\u0005B\u0005m\u0003bBA4\u0001\u0011\u0005\u0011\u0011\u000e\u0005\b\u0003[\u0002A\u0011AA8\u0011\u001d\t\t\b\u0001C\u0001\u0003gBq!a\u001e\u0001\t\u0003\tI\bC\u0004\u0002~\u0001!\t!a \t\u0011\u0005\r\u0005\u0001\"\u0001>\u0003\u000bCq!a#\u0001\t\u0003\ti\t\u0003\u0005\u0002\u0012\u0002!\t!PAJ\u0011\u001d\t9\n\u0001C\u0001\u00033C\u0001\"!(\u0001\t\u0003i\u0014q\u0014\u0005\b\u0003G\u0003A\u0011AAS\u0011!\tI\u000b\u0001C\u0001{\u0005-\u0006bBAX\u0001\u0011\u0005\u0011\u0011\u0017\u0005\t\u0003w\u0003A\u0011A\u001f\u0002>\"9\u0011\u0011\u0019\u0001\u0005\u0002\u0005\r\u0007bBAd\u0001\u0011\u0005\u0011q\u000e\u0005\b\u0003\u0013\u0004A\u0011AAf\u0011\u001d\t\t\u000e\u0001C\u0001\u0003_Bq!a5\u0001\t\u0003\ty\u0007C\u0004\u0002V\u0002!\t!a\u001c\t\u0013\u0005]\u0007!!A\u0005\u0002\u0005e\u0007\"CAo\u0001E\u0005I\u0011AAp\u0011%\t)\u0010AA\u0001\n\u0003\n9\u0010\u0003\u0005\u0002~\u0002\t\t\u0011\"\u0001v\u0011%\ty\u0010AA\u0001\n\u0003\u0011\t\u0001C\u0005\u0003\b\u0001\t\t\u0011\"\u0011\u0003\n!I!q\u0003\u0001\u0002\u0002\u0013\u0005!\u0011\u0004\u0005\n\u0005;\u0001\u0011\u0011!C!\u0005?A\u0011Ba\t\u0001\u0003\u0003%\tE!\n\b\u0015\t\u001dR(!A\t\u0002u\u0012ICB\u0005={\u0005\u0005\t\u0012A\u001f\u0003,!1AM\u000eC\u0001\u0005\u0007B\u0001\u0002\u001b\u001c\u0002\u0002\u0013\u0015#Q\t\u0005\n\u0005\u000f2\u0014\u0011!CA\u0005\u0013B\u0011B!\u00147\u0003\u0003%\tIa\u0014\t\u0013\tmc'!A\u0005\n\tu#a\u0003$m_\u0006$h*^7cKJT!AP \u0002\t5\fG\u000f\u001b\u0006\u0002\u0001\u0006)1\u000f]5sKN)\u0001AQ%N#B\u00111iR\u0007\u0002\t*\u0011a(\u0012\u0006\u0002\r\u0006)1oY1mC&\u0011\u0001\n\u0012\u0002\f'\u000e\fG.\u0019(v[\n,'\u000f\u0005\u0002K\u00176\tQ(\u0003\u0002M{\t1a*^7cKJ\u0004\"AT(\u000e\u0003\u0015K!\u0001U#\u0003\u000fA\u0013x\u000eZ;diB\u0011!k\u0017\b\u0003'fs!\u0001\u0016-\u000e\u0003US!AV,\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011AR\u0005\u00035\u0016\u000bq\u0001]1dW\u0006<W-\u0003\u0002];\na1+\u001a:jC2L'0\u00192mK*\u0011!,R\u0001\u0002]V\t\u0001\r\u0005\u0002OC&\u0011!-\u0012\u0002\u0007\t>,(\r\\3\u0002\u00059\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002gOB\u0011!\n\u0001\u0005\u0006=\u000e\u0001\r\u0001Y\u0001\ti>\u001cFO]5oOR\t!\u000e\u0005\u0002l_:\u0011A.\u001c\t\u0003)\u0016K!A\\#\u0002\rA\u0013X\rZ3g\u0013\t\u0001\u0018O\u0001\u0004TiJLgn\u001a\u0006\u0003]\u0016\u000b1!\u00192t+\u00051\u0017AB:jO:,X.F\u0001w!\tqu/\u0003\u0002y\u000b\n\u0019\u0011J\u001c;\u0002\u0013]LG\u000f[5o\u0013:$X#A>\u0011\u00059c\u0018BA?F\u0005\u001d\u0011un\u001c7fC:\f!b^5uQ&tGj\u001c8h\u000319\u0018\u000e\u001e5j]\u0012{WO\u00197f\u0003!\u0019\u0017M\u001c\"f\u0013:$\u0018!C2b]\n+Gj\u001c8h\u0003\u001dI7/\u0012=bGR\f!\"\u001e8eKJd\u00170\u001b8h)\t\tY\u0001\u0005\u0003\u0002\u000e\u0005]QBAA\b\u0015\u0011\t\t\"a\u0005\u0002\t1\fgn\u001a\u0006\u0003\u0003+\tAA[1wC&\u0019!-a\u0004\u0002\u000f%\u001cx\u000b[8mK\u0006YAm\\;cY\u00164\u0016\r\\;f\u0003)1Gn\\1u-\u0006dW/Z\u000b\u0003\u0003C\u00012ATA\u0012\u0013\r\t)#\u0012\u0002\u0006\r2|\u0017\r^\u0001\nY>twMV1mk\u0016,\"!a\u000b\u0011\u00079\u000bi#C\u0002\u00020\u0015\u0013A\u0001T8oO\u0006A\u0011N\u001c;WC2,X-\u0001\u0005u_\nKw-\u00138u+\t\t9\u0004E\u0002S\u0003sI1!a\u000f^\u0005\u0019\u0011\u0015nZ%oi\u0006aAo\u001c\"jO\u0012+7-[7bYV\u0011\u0011\u0011\t\t\u0004%\u0006\r\u0013bAA#;\nQ!)[4EK\u000eLW.\u00197\u0002\u0015Q|'+\u0019;j_:\fG.\u0006\u0002\u0002LA\u0019!*!\u0014\n\u0007\u0005=SH\u0001\u0005SCRLwN\\1m\u0003\u001d\u0019w.\u001c9be\u0016$2A^A+\u0011\u0019\t9F\u0006a\u0001\u0013\u0006\u0019!\u000f[:\u0002\r\u0015\fX/\u00197t)\rY\u0018Q\f\u0005\b\u0003?:\u0002\u0019AA1\u0003\u0011!\b.\u0019;\u0011\u00079\u000b\u0019'C\u0002\u0002f\u0015\u00131!\u00118z\u0003%!S-\u001d\u0013fc\u0012*\u0017\u000fF\u0002|\u0003WBa!a\u0018\u0019\u0001\u0004I\u0015\u0001D;oCJLx\fJ7j]V\u001cX#A%\u0002\u000b\u0011\u0002H.^:\u0015\u0007%\u000b)\b\u0003\u0004\u0002Xi\u0001\r!S\u0001\u0007IQLW.Z:\u0015\u0007%\u000bY\b\u0003\u0004\u0002Xm\u0001\r!S\u0001\u0007I5Lg.^:\u0015\u0007%\u000b\t\t\u0003\u0004\u0002Xq\u0001\r!S\u0001\te~#S.\u001b8vgR\u0019\u0011*a\"\t\r\u0005%U\u00041\u0001J\u0003\ra\u0007n]\u0001\u0005I\u0011Lg\u000fF\u0002J\u0003\u001fCa!a\u0016\u001f\u0001\u0004I\u0015A\u0002:`I\u0011Lg\u000fF\u0002J\u0003+Ca!!# \u0001\u0004I\u0015!\u0002;rk>$HcA%\u0002\u001c\"1\u0011q\u000b\u0011A\u0002%\u000bqA]0ucV|G\u000fF\u0002J\u0003CCa!!#\"\u0001\u0004I\u0015\u0001\u0002;n_\u0012$2!SAT\u0011\u0019\t9F\ta\u0001\u0013\u00061!o\u0018;n_\u0012$2!SAW\u0011\u0019\tIi\ta\u0001\u0013\u0006AA/];pi6|G\r\u0006\u0003\u00024\u0006e\u0006#\u0002(\u00026&K\u0015bAA\\\u000b\n1A+\u001e9mKJBa!a\u0016%\u0001\u0004I\u0015A\u0003:`iF,x\u000e^7pIR!\u00111WA`\u0011\u0019\tI)\na\u0001\u0013\u0006\u0019\u0001o\\<\u0015\u0007%\u000b)\r\u0003\u0004\u0002X\u0019\u0002\r!S\u0001\u0005gF\u0014H/A\u0003oe>|G\u000fF\u0002J\u0003\u001bDa!a4)\u0001\u00041\u0018!A6\u0002\u000b\u0019dwn\u001c:\u0002\t\r,\u0017\u000e\\\u0001\u0006e>,h\u000eZ\u0001\u0005G>\u0004\u0018\u0010F\u0002g\u00037DqA\u0018\u0017\u0011\u0002\u0003\u0007\u0001-\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005\u0005(f\u00011\u0002d.\u0012\u0011Q\u001d\t\u0005\u0003O\f\t0\u0004\u0002\u0002j*!\u00111^Aw\u0003%)hn\u00195fG.,GMC\u0002\u0002p\u0016\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\t\u00190!;\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003s\u0004B!!\u0004\u0002|&\u0019\u0001/a\u0004\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011\u0011\rB\u0002\u0011!\u0011)\u0001MA\u0001\u0002\u00041\u0018a\u0001=%c\u0005y\u0001O]8ek\u000e$\u0018\n^3sCR|'/\u0006\u0002\u0003\fA1!Q\u0002B\n\u0003Cj!Aa\u0004\u000b\u0007\tEQ)\u0001\u0006d_2dWm\u0019;j_:LAA!\u0006\u0003\u0010\tA\u0011\n^3sCR|'/\u0001\u0005dC:,\u0015/^1m)\rY(1\u0004\u0005\n\u0005\u000b\u0011\u0014\u0011!a\u0001\u0003C\n!\u0003\u001d:pIV\u001cG/\u00127f[\u0016tGOT1nKR!\u0011\u0011 B\u0011\u0011!\u0011)aMA\u0001\u0002\u00041\u0018\u0001\u00035bg\"\u001cu\u000eZ3\u0015\u0003Y\f1B\u00127pCRtU/\u001c2feB\u0011!JN\n\u0006m\t5\"\u0011\b\t\u0007\u0005_\u0011)\u0004\u00194\u000e\u0005\tE\"b\u0001B\u001a\u000b\u00069!/\u001e8uS6,\u0017\u0002\u0002B\u001c\u0005c\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82!\u0011\u0011YD!\u0011\u000e\u0005\tu\"\u0002\u0002B \u0003'\t!![8\n\u0007q\u0013i\u0004\u0006\u0002\u0003*Q\u0011\u0011\u0011`\u0001\u0006CB\u0004H.\u001f\u000b\u0004M\n-\u0003\"\u00020:\u0001\u0004\u0001\u0017aB;oCB\u0004H.\u001f\u000b\u0005\u0005#\u00129\u0006\u0005\u0003O\u0005'\u0002\u0017b\u0001B+\u000b\n1q\n\u001d;j_:D\u0001B!\u0017;\u0003\u0003\u0005\rAZ\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B0!\u0011\tiA!\u0019\n\t\t\r\u0014q\u0002\u0002\u0007\u001f\nTWm\u0019;"
)
public class FloatNumber extends ScalaNumber implements Number, Product {
   private final double n;

   public static Option unapply(final FloatNumber x$0) {
      return FloatNumber$.MODULE$.unapply(x$0);
   }

   public static FloatNumber apply(final double n) {
      return FloatNumber$.MODULE$.apply(n);
   }

   public static Function1 andThen(final Function1 g) {
      return FloatNumber$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return FloatNumber$.MODULE$.compose(g);
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

   public double n() {
      return this.n;
   }

   public String toString() {
      return Double.toString(this.n());
   }

   public FloatNumber abs() {
      return new FloatNumber(Math.abs(this.n()));
   }

   public int signum() {
      return (int)Math.signum(this.n());
   }

   public boolean withinInt() {
      return (double)Integer.MIN_VALUE <= this.n() && this.n() <= (double)Integer.MAX_VALUE;
   }

   public boolean withinLong() {
      return (double)Long.MIN_VALUE <= this.n() && this.n() <= (double)Long.MAX_VALUE;
   }

   public boolean withinDouble() {
      return .MODULE$.MinValue() <= this.n() && this.n() <= Double.MAX_VALUE;
   }

   public boolean canBeInt() {
      return this.isWhole() && this.withinInt();
   }

   public boolean canBeLong() {
      return this.isWhole() && this.withinLong();
   }

   public boolean isExact() {
      return false;
   }

   public Double underlying() {
      return this.n();
   }

   public boolean isWhole() {
      return this.n() % (double)1 == (double)0.0F;
   }

   public double doubleValue() {
      return this.n();
   }

   public float floatValue() {
      return (float)this.n();
   }

   public long longValue() {
      return (long)this.n();
   }

   public int intValue() {
      return (int)this.n();
   }

   public BigInt toBigInt() {
      return scala.package..MODULE$.BigDecimal().apply(this.n()).toBigInt();
   }

   public BigDecimal toBigDecimal() {
      return scala.package..MODULE$.BigDecimal().apply(this.n());
   }

   public Rational toRational() {
      return Rational$.MODULE$.apply(this.n());
   }

   public int compare(final Number rhs) {
      int var2;
      if (rhs instanceof IntNumber) {
         IntNumber var4 = (IntNumber)rhs;
         SafeLong m = var4.n();
         var2 = scala.package..MODULE$.BigDecimal().apply(this.n()).compare(m.toBigDecimal());
      } else if (rhs instanceof FloatNumber) {
         FloatNumber var6 = (FloatNumber)rhs;
         double m = var6.n();
         var2 = spire.algebra.package$.MODULE$.Order().apply(cats.kernel.Eq..MODULE$.catsKernelInstancesForDouble()).compare$mcD$sp(this.n(), m);
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
         var2 = BoxesRunTime.equals(BoxesRunTime.boxToDouble(this.n()), that);
      }

      return var2;
   }

   public boolean $eq$eq$eq(final Number that) {
      boolean var2;
      if (that instanceof FloatNumber) {
         FloatNumber var4 = (FloatNumber)that;
         double n2 = var4.n();
         var2 = this.n() == n2;
      } else if (that instanceof IntNumber) {
         IntNumber var7 = (IntNumber)that;
         SafeLong m = var7.n();
         var2 = BoxesRunTime.equalsNumObject(m, BoxesRunTime.boxToLong((long)m.toDouble())) && BoxesRunTime.equalsNumObject(m, BoxesRunTime.boxToDouble(this.n()));
      } else {
         boolean var10000;
         label33: {
            label32: {
               if (that == null) {
                  if (this == null) {
                     break label32;
                  }
               } else if (that.equals(this)) {
                  break label32;
               }

               var10000 = false;
               break label33;
            }

            var10000 = true;
         }

         var2 = var10000;
      }

      return var2;
   }

   public Number unary_$minus() {
      return Number$.MODULE$.apply(-this.n());
   }

   public Number $plus(final Number rhs) {
      Number var2;
      if (rhs instanceof IntNumber) {
         IntNumber var5 = (IntNumber)rhs;
         SafeLong m = var5.n();
         Number var3;
         if (m instanceof SafeLongLong) {
            SafeLongLong var8 = (SafeLongLong)m;
            long x = var8.x();
            var3 = Number$.MODULE$.apply(this.n() + (double)x);
         } else {
            if (!(m instanceof SafeLongBigInteger)) {
               throw new MatchError(m);
            }

            SafeLongBigInteger var11 = (SafeLongBigInteger)m;
            BigInteger x = var11.x();
            var3 = Number$.MODULE$.apply(scala.package..MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(x)).$plus(scala.math.BigDecimal..MODULE$.double2bigDecimal(this.n())));
         }

         var2 = var3;
      } else if (rhs instanceof FloatNumber) {
         FloatNumber var13 = (FloatNumber)rhs;
         double m = var13.n();
         var2 = Number$.MODULE$.apply(this.n() + m);
      } else {
         var2 = rhs.$plus(this);
      }

      return var2;
   }

   public Number $times(final Number rhs) {
      Number var2;
      if (rhs instanceof IntNumber) {
         IntNumber var5 = (IntNumber)rhs;
         SafeLong m = var5.n();
         Number var3;
         if (m instanceof SafeLongLong) {
            SafeLongLong var8 = (SafeLongLong)m;
            long x = var8.x();
            var3 = Number$.MODULE$.apply(this.n() * (double)x);
         } else {
            if (!(m instanceof SafeLongBigInteger)) {
               throw new MatchError(m);
            }

            SafeLongBigInteger var11 = (SafeLongBigInteger)m;
            BigInteger x = var11.x();
            var3 = Number$.MODULE$.apply(scala.package..MODULE$.BigDecimal().apply(this.n()).$times(scala.package..MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(x))));
         }

         var2 = var3;
      } else if (rhs instanceof FloatNumber) {
         FloatNumber var13 = (FloatNumber)rhs;
         double m = var13.n();
         var2 = Number$.MODULE$.apply(this.n() * m);
      } else {
         var2 = rhs.$times(this);
      }

      return var2;
   }

   public Number $minus(final Number rhs) {
      Number var2;
      if (rhs instanceof IntNumber) {
         IntNumber var5 = (IntNumber)rhs;
         SafeLong m = var5.n();
         Number var3;
         if (m instanceof SafeLongLong) {
            SafeLongLong var8 = (SafeLongLong)m;
            long x = var8.x();
            var3 = Number$.MODULE$.apply(this.n() - (double)x);
         } else {
            if (!(m instanceof SafeLongBigInteger)) {
               throw new MatchError(m);
            }

            SafeLongBigInteger var11 = (SafeLongBigInteger)m;
            BigInteger x = var11.x();
            var3 = Number$.MODULE$.apply(scala.package..MODULE$.BigDecimal().apply(this.n()).$plus(scala.package..MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(x))));
         }

         var2 = var3;
      } else if (rhs instanceof FloatNumber) {
         FloatNumber var13 = (FloatNumber)rhs;
         double m = var13.n();
         var2 = Number$.MODULE$.apply(this.n() - m);
      } else {
         var2 = rhs.r_$minus(this);
      }

      return var2;
   }

   public Number r_$minus(final Number lhs) {
      Number var2;
      if (lhs instanceof IntNumber) {
         IntNumber var5 = (IntNumber)lhs;
         SafeLong m = var5.n();
         Number var3;
         if (m instanceof SafeLongLong) {
            SafeLongLong var8 = (SafeLongLong)m;
            long x = var8.x();
            var3 = Number$.MODULE$.apply((double)x - this.n());
         } else {
            if (!(m instanceof SafeLongBigInteger)) {
               throw new MatchError(m);
            }

            SafeLongBigInteger var11 = (SafeLongBigInteger)m;
            BigInteger x = var11.x();
            var3 = Number$.MODULE$.apply(scala.package..MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(x)).$minus(scala.package..MODULE$.BigDecimal().apply(this.n())));
         }

         var2 = var3;
      } else if (lhs instanceof FloatNumber) {
         FloatNumber var13 = (FloatNumber)lhs;
         double m = var13.n();
         var2 = Number$.MODULE$.apply(m - this.n());
      } else {
         var2 = lhs.$minus(lhs);
      }

      return var2;
   }

   public Number $div(final Number rhs) {
      Number var2;
      if (rhs instanceof IntNumber) {
         IntNumber var5 = (IntNumber)rhs;
         SafeLong m = var5.n();
         Number var3;
         if (m instanceof SafeLongLong) {
            SafeLongLong var8 = (SafeLongLong)m;
            long x = var8.x();
            var3 = Number$.MODULE$.apply(this.n() / (double)x);
         } else {
            if (!(m instanceof SafeLongBigInteger)) {
               throw new MatchError(m);
            }

            SafeLongBigInteger var11 = (SafeLongBigInteger)m;
            BigInteger x = var11.x();
            var3 = Number$.MODULE$.apply(scala.package..MODULE$.BigDecimal().apply(this.n()).$div(scala.package..MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(x))));
         }

         var2 = var3;
      } else if (rhs instanceof FloatNumber) {
         FloatNumber var13 = (FloatNumber)rhs;
         double m = var13.n();
         var2 = Number$.MODULE$.apply(this.n() / m);
      } else {
         var2 = rhs.r_$div(this);
      }

      return var2;
   }

   public Number r_$div(final Number lhs) {
      Number var2;
      if (lhs instanceof IntNumber) {
         IntNumber var5 = (IntNumber)lhs;
         SafeLong m = var5.n();
         Number var3;
         if (m instanceof SafeLongLong) {
            SafeLongLong var8 = (SafeLongLong)m;
            long x = var8.x();
            var3 = Number$.MODULE$.apply((double)x / this.n());
         } else {
            if (!(m instanceof SafeLongBigInteger)) {
               throw new MatchError(m);
            }

            SafeLongBigInteger var11 = (SafeLongBigInteger)m;
            BigInteger x = var11.x();
            var3 = Number$.MODULE$.apply(scala.package..MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(x)).$div(scala.package..MODULE$.BigDecimal().apply(this.n())));
         }

         var2 = var3;
      } else if (lhs instanceof FloatNumber) {
         FloatNumber var13 = (FloatNumber)lhs;
         double m = var13.n();
         var2 = Number$.MODULE$.apply(m / this.n());
      } else {
         var2 = lhs.$div(lhs);
      }

      return var2;
   }

   public Number tquot(final Number rhs) {
      Number var2;
      if (rhs instanceof IntNumber) {
         IntNumber var5 = (IntNumber)rhs;
         SafeLong m = var5.n();
         Number var3;
         if (m instanceof SafeLongLong) {
            SafeLongLong var8 = (SafeLongLong)m;
            long x = var8.x();
            var3 = Number$.MODULE$.apply(Math.floor(this.n() / (double)x));
         } else {
            if (!(m instanceof SafeLongBigInteger)) {
               throw new MatchError(m);
            }

            SafeLongBigInteger var11 = (SafeLongBigInteger)m;
            BigInteger x = var11.x();
            var3 = Number$.MODULE$.apply(scala.package..MODULE$.BigDecimal().apply(this.n()).quot(scala.package..MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(x))));
         }

         var2 = var3;
      } else if (rhs instanceof FloatNumber) {
         FloatNumber var13 = (FloatNumber)rhs;
         double m = var13.n();
         var2 = Number$.MODULE$.apply(Math.floor(this.n() / m));
      } else {
         var2 = rhs.r_tquot(this);
      }

      return var2;
   }

   public Number r_tquot(final Number lhs) {
      Number var2;
      if (lhs instanceof IntNumber) {
         IntNumber var5 = (IntNumber)lhs;
         SafeLong m = var5.n();
         Number var3;
         if (m instanceof SafeLongLong) {
            SafeLongLong var8 = (SafeLongLong)m;
            long x = var8.x();
            var3 = Number$.MODULE$.apply(Math.floor((double)x / this.n()));
         } else {
            if (!(m instanceof SafeLongBigInteger)) {
               throw new MatchError(m);
            }

            SafeLongBigInteger var11 = (SafeLongBigInteger)m;
            BigInteger x = var11.x();
            var3 = Number$.MODULE$.apply(scala.package..MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(x)).quot(scala.math.BigDecimal..MODULE$.double2bigDecimal(this.n())));
         }

         var2 = var3;
      } else if (lhs instanceof FloatNumber) {
         FloatNumber var13 = (FloatNumber)lhs;
         double m = var13.n();
         var2 = Number$.MODULE$.apply(Math.floor(m / this.n()));
      } else {
         var2 = lhs.tquot(lhs);
      }

      return var2;
   }

   public Number tmod(final Number rhs) {
      Number var2;
      if (rhs instanceof IntNumber) {
         IntNumber var5 = (IntNumber)rhs;
         SafeLong m = var5.n();
         Number var3;
         if (m instanceof SafeLongLong) {
            SafeLongLong var8 = (SafeLongLong)m;
            long x = var8.x();
            var3 = Number$.MODULE$.apply(this.n() % (double)x);
         } else {
            if (!(m instanceof SafeLongBigInteger)) {
               throw new MatchError(m);
            }

            SafeLongBigInteger var11 = (SafeLongBigInteger)m;
            BigInteger x = var11.x();
            var3 = Number$.MODULE$.apply(scala.package..MODULE$.BigDecimal().apply(this.n()).$percent(scala.package..MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(x))));
         }

         var2 = var3;
      } else if (rhs instanceof FloatNumber) {
         FloatNumber var13 = (FloatNumber)rhs;
         double m = var13.n();
         var2 = Number$.MODULE$.apply(this.n() % m);
      } else {
         var2 = rhs.r_tmod(this);
      }

      return var2;
   }

   public Number r_tmod(final Number lhs) {
      Number var2;
      if (lhs instanceof IntNumber) {
         IntNumber var5 = (IntNumber)lhs;
         SafeLong m = var5.n();
         Number var3;
         if (m instanceof SafeLongLong) {
            SafeLongLong var8 = (SafeLongLong)m;
            long x = var8.x();
            var3 = Number$.MODULE$.apply((double)x % this.n());
         } else {
            if (!(m instanceof SafeLongBigInteger)) {
               throw new MatchError(m);
            }

            SafeLongBigInteger var11 = (SafeLongBigInteger)m;
            BigInteger x = var11.x();
            var3 = Number$.MODULE$.apply(scala.package..MODULE$.BigDecimal().apply(scala.math.BigInt..MODULE$.javaBigInteger2bigInt(x)).$percent(scala.math.BigDecimal..MODULE$.double2bigDecimal(this.n())));
         }

         var2 = var3;
      } else if (lhs instanceof FloatNumber) {
         FloatNumber var13 = (FloatNumber)lhs;
         double m = var13.n();
         var2 = Number$.MODULE$.apply(m % this.n());
      } else {
         var2 = lhs.tmod(lhs);
      }

      return var2;
   }

   public Tuple2 tquotmod(final Number rhs) {
      Tuple2 var2;
      if (rhs instanceof IntNumber) {
         IntNumber var4 = (IntNumber)rhs;
         SafeLong m = var4.n();
         var2 = new Tuple2(Number$.MODULE$.apply(this.n() / m.toDouble()), Number$.MODULE$.apply(this.n() % m.toDouble()));
      } else if (rhs instanceof FloatNumber) {
         FloatNumber var6 = (FloatNumber)rhs;
         double m = var6.n();
         var2 = new Tuple2(Number$.MODULE$.apply(this.n() / m), Number$.MODULE$.apply(this.n() % m));
      } else {
         var2 = rhs.r_tquotmod(this);
      }

      return var2;
   }

   public Tuple2 r_tquotmod(final Number lhs) {
      Tuple2 var2;
      if (lhs instanceof IntNumber) {
         IntNumber var4 = (IntNumber)lhs;
         SafeLong m = var4.n();
         var2 = new Tuple2(Number$.MODULE$.apply(m.toDouble() / this.n()), Number$.MODULE$.apply(m.toDouble() % this.n()));
      } else if (lhs instanceof FloatNumber) {
         FloatNumber var6 = (FloatNumber)lhs;
         double m = var6.n();
         var2 = new Tuple2(Number$.MODULE$.apply(m / this.n()), Number$.MODULE$.apply(m % this.n()));
      } else {
         var2 = lhs.tquotmod(lhs);
      }

      return var2;
   }

   public Number pow(final Number rhs) {
      Number var2;
      if (rhs instanceof FloatNumber) {
         FloatNumber var4 = (FloatNumber)rhs;
         double m = var4.n();
         var2 = Number$.MODULE$.apply(package$.MODULE$.pow(this.n(), m));
      } else if (rhs.withinDouble()) {
         var2 = Number$.MODULE$.apply(package$.MODULE$.pow(this.n(), rhs.doubleValue()));
      } else {
         var2 = Number$.MODULE$.apply(package$.MODULE$.pow(scala.package..MODULE$.BigDecimal().apply(this.n()), rhs.toBigDecimal()));
      }

      return var2;
   }

   public Number sqrt() {
      return Number$.MODULE$.apply(Math.sqrt(this.n()));
   }

   public Number nroot(final int k) {
      return Number$.MODULE$.apply(Math.pow(this.n(), (double)1.0F / (double)k));
   }

   public Number floor() {
      return Number$.MODULE$.apply(Math.floor(this.n()));
   }

   public Number ceil() {
      return Number$.MODULE$.apply(Math.ceil(this.n()));
   }

   public Number round() {
      return Number$.MODULE$.apply(Math.round(this.n()));
   }

   public FloatNumber copy(final double n) {
      return new FloatNumber(n);
   }

   public double copy$default$1() {
      return this.n();
   }

   public String productPrefix() {
      return "FloatNumber";
   }

   public int productArity() {
      return 1;
   }

   public Object productElement(final int x$1) {
      Object var10000;
      switch (x$1) {
         case 0:
            var10000 = BoxesRunTime.boxToDouble(this.n());
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
      return x$1 instanceof FloatNumber;
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
      int var1 = -889275714;
      var1 = Statics.mix(var1, this.productPrefix().hashCode());
      var1 = Statics.mix(var1, Statics.doubleHash(this.n()));
      return Statics.finalizeHash(var1, 1);
   }

   public FloatNumber(final double n) {
      this.n = n;
      ScalaNumericAnyConversions.$init$(this);
      Number.$init$(this);
      Product.$init$(this);
   }
}
