package spire.math;

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
import spire.algebra.IsReal;
import spire.algebra.NRoot;

@ScalaSignature(
   bytes = "\u0006\u0005\t}c!\u0002\u001f>\u0001v\n\u0005\u0002\u00030\u0001\u0005+\u0007I\u0011A0\t\u0011\r\u0004!\u0011#Q\u0001\n\u0001DQ\u0001\u001a\u0001\u0005\u0002\u0015DQ\u0001\u001b\u0001\u0005B%DQA\u001d\u0001\u0005\u0002MDQ\u0001\u001e\u0001\u0005\u0002UDQ!\u001f\u0001\u0005\u0002iDQA \u0001\u0005\u0002iDQa \u0001\u0005\u0002iDa!!\u0001\u0001\t\u0003Q\bBBA\u0002\u0001\u0011\u0005!\u0010\u0003\u0004\u0002\u0006\u0001!\tA\u001f\u0005\b\u0003\u000f\u0001A\u0011AA\u0005\u0011\u0019\tY\u0001\u0001C\u0001u\"9\u0011Q\u0002\u0001\u0005\u0002\u0005=\u0001bBA\f\u0001\u0011\u0005\u0011\u0011\u0004\u0005\b\u0003C\u0001A\u0011AA\u0012\u0011\u0019\tY\u0003\u0001C\u0001k\"9\u0011Q\u0006\u0001\u0005\u0002\u0005=\u0002BBA\u001c\u0001\u0011\u0005q\fC\u0004\u0002:\u0001!\t!a\u000f\t\u000f\u0005\r\u0003\u0001\"\u0001\u0002F!9\u00111\n\u0001\u0005B\u00055\u0003bBA-\u0001\u0011\u0005\u00111\f\u0005\u0007\u0003?\u0002A\u0011A:\t\u000f\u0005\u0005\u0004\u0001\"\u0001\u0002d!9\u0011q\r\u0001\u0005\u0002\u0005%\u0004bBA7\u0001\u0011\u0005\u0011q\u000e\u0005\b\u0003g\u0002A\u0011AA;\u0011\u001d\tI\b\u0001C\u0001\u0003wBq!a \u0001\t\u0003\t\t\tC\u0004\u0002\u0006\u0002!\t!a\"\t\u000f\u00055\u0005\u0001\"\u0001\u0002\u0010\"9\u00111\u0013\u0001\u0005\u0002\u0005U\u0005bBAM\u0001\u0011\u0005\u00111\u0014\u0005\b\u0003?\u0003A\u0011AAQ\u0011\u001d\tY\u000b\u0001C\u0001\u0003[Cq!!-\u0001\t\u0003\t\u0019\f\u0003\u0004\u00028\u0002!\ta\u001d\u0005\b\u0003s\u0003A\u0011AA^\u0011\u0019\t\t\r\u0001C\u0001g\"1\u00111\u0019\u0001\u0005\u0002MDa!!2\u0001\t\u0003\u0019\b\"CAd\u0001\u0005\u0005I\u0011AAe\u0011%\ti\rAI\u0001\n\u0003\ty\rC\u0005\u0002f\u0002\t\t\u0011\"\u0011\u0002h\"A\u0011q\u001f\u0001\u0002\u0002\u0013\u0005Q\u000fC\u0005\u0002z\u0002\t\t\u0011\"\u0001\u0002|\"I!\u0011\u0001\u0001\u0002\u0002\u0013\u0005#1\u0001\u0005\n\u0005#\u0001\u0011\u0011!C\u0001\u0005'A\u0011Ba\u0006\u0001\u0003\u0003%\tE!\u0007\t\u0013\tu\u0001!!A\u0005B\t}qA\u0003B\u0011{\u0005\u0005\t\u0012A\u001f\u0003$\u0019IA(PA\u0001\u0012\u0003i$Q\u0005\u0005\u0007IZ\"\tA!\u0010\t\u0011!4\u0014\u0011!C#\u0005\u007fA\u0011B!\u00117\u0003\u0003%\tIa\u0011\t\u0013\t\u001dc'!A\u0005\u0002\n%\u0003\"\u0003B+m\u0005\u0005I\u0011\u0002B,\u00055!UmY5nC2tU/\u001c2fe*\u0011ahP\u0001\u0005[\u0006$\bNC\u0001A\u0003\u0015\u0019\b/\u001b:f'\u0015\u0001!)S'R!\t\u0019u)D\u0001E\u0015\tqTIC\u0001G\u0003\u0015\u00198-\u00197b\u0013\tAEIA\u0006TG\u0006d\u0017MT;nE\u0016\u0014\bC\u0001&L\u001b\u0005i\u0014B\u0001'>\u0005\u0019qU/\u001c2feB\u0011ajT\u0007\u0002\u000b&\u0011\u0001+\u0012\u0002\b!J|G-^2u!\t\u00116L\u0004\u0002T3:\u0011A\u000bW\u0007\u0002+*\u0011akV\u0001\u0007yI|w\u000e\u001e \u0004\u0001%\ta)\u0003\u0002[\u000b\u00069\u0001/Y2lC\u001e,\u0017B\u0001/^\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tQV)A\u0001o+\u0005\u0001\u0007C\u0001*b\u0013\t\u0011WL\u0001\u0006CS\u001e$UmY5nC2\f!A\u001c\u0011\u0002\rqJg.\u001b;?)\t1w\r\u0005\u0002K\u0001!)al\u0001a\u0001A\u0006AAo\\*ue&tw\rF\u0001k!\tYwN\u0004\u0002m[B\u0011A+R\u0005\u0003]\u0016\u000ba\u0001\u0015:fI\u00164\u0017B\u00019r\u0005\u0019\u0019FO]5oO*\u0011a.R\u0001\u0004C\n\u001cX#A%\u0002\rMLwM\\;n+\u00051\bC\u0001(x\u0013\tAXIA\u0002J]R\f\u0011b^5uQ&t\u0017J\u001c;\u0016\u0003m\u0004\"A\u0014?\n\u0005u,%a\u0002\"p_2,\u0017M\\\u0001\u000bo&$\b.\u001b8M_:<\u0017\u0001D<ji\"Lg\u000eR8vE2,\u0017\u0001C2b]\n+\u0017J\u001c;\u0002\u0013\r\fgNQ3M_:<\u0017aB5t\u000bb\f7\r^\u0001\u000bk:$WM\u001d7zS:<G#\u00011\u0002\u000f%\u001cx\u000b[8mK\u0006YAm\\;cY\u00164\u0016\r\\;f+\t\t\t\u0002E\u0002O\u0003'I1!!\u0006F\u0005\u0019!u.\u001e2mK\u0006Qa\r\\8biZ\u000bG.^3\u0016\u0005\u0005m\u0001c\u0001(\u0002\u001e%\u0019\u0011qD#\u0003\u000b\u0019cw.\u0019;\u0002\u00131|gn\u001a,bYV,WCAA\u0013!\rq\u0015qE\u0005\u0004\u0003S)%\u0001\u0002'p]\u001e\f\u0001\"\u001b8u-\u0006dW/Z\u0001\ti>\u0014\u0015nZ%oiV\u0011\u0011\u0011\u0007\t\u0004%\u0006M\u0012bAA\u001b;\n1!)[4J]R\fA\u0002^8CS\u001e$UmY5nC2\f!\u0002^8SCRLwN\\1m+\t\ti\u0004E\u0002K\u0003\u007fI1!!\u0011>\u0005!\u0011\u0016\r^5p]\u0006d\u0017aB2p[B\f'/\u001a\u000b\u0004m\u0006\u001d\u0003BBA%-\u0001\u0007\u0011*A\u0002sQN\fa!Z9vC2\u001cHcA>\u0002P!9\u0011\u0011K\fA\u0002\u0005M\u0013\u0001\u0002;iCR\u00042ATA+\u0013\r\t9&\u0012\u0002\u0004\u0003:L\u0018!\u0003\u0013fc\u0012*\u0017\u000fJ3r)\rY\u0018Q\f\u0005\u0007\u0003#B\u0002\u0019A%\u0002\u0019Ut\u0017M]=`I5Lg.^:\u0002\u000b\u0011\u0002H.^:\u0015\u0007%\u000b)\u0007\u0003\u0004\u0002Ji\u0001\r!S\u0001\u0007IQLW.Z:\u0015\u0007%\u000bY\u0007\u0003\u0004\u0002Jm\u0001\r!S\u0001\u0007I5Lg.^:\u0015\u0007%\u000b\t\b\u0003\u0004\u0002Jq\u0001\r!S\u0001\u0005I\u0011Lg\u000fF\u0002J\u0003oBa!!\u0013\u001e\u0001\u0004I\u0015!\u0002;rk>$HcA%\u0002~!1\u0011\u0011\n\u0010A\u0002%\u000bA\u0001^7pIR\u0019\u0011*a!\t\r\u0005%s\u00041\u0001J\u0003!\u0011x\fJ7j]V\u001cHcA%\u0002\n\"1\u00111\u0012\u0011A\u0002%\u000b1\u0001\u001c5t\u0003\u0019\u0011x\f\n3jmR\u0019\u0011*!%\t\r\u0005-\u0015\u00051\u0001J\u0003\u001d\u0011x\f^9v_R$2!SAL\u0011\u0019\tYI\ta\u0001\u0013\u00061!o\u0018;n_\u0012$2!SAO\u0011\u0019\tYi\ta\u0001\u0013\u0006AA/];pi6|G\r\u0006\u0003\u0002$\u0006%\u0006#\u0002(\u0002&&K\u0015bAAT\u000b\n1A+\u001e9mKJBa!!\u0013%\u0001\u0004I\u0015A\u0003:`iF,x\u000e^7pIR!\u00111UAX\u0011\u0019\tY)\na\u0001\u0013\u0006\u0019\u0001o\\<\u0015\u0007%\u000b)\f\u0003\u0004\u0002J\u0019\u0002\r!S\u0001\u0005gF\u0014H/A\u0003oe>|G\u000fF\u0002J\u0003{Ca!a0)\u0001\u00041\u0018!A6\u0002\u000b\u0019dwn\u001c:\u0002\t\r,\u0017\u000e\\\u0001\u0006e>,h\u000eZ\u0001\u0005G>\u0004\u0018\u0010F\u0002g\u0003\u0017DqA\u0018\u0017\u0011\u0002\u0003\u0007\u0001-\u0001\bd_BLH\u0005Z3gCVdG\u000fJ\u0019\u0016\u0005\u0005E'f\u00011\u0002T.\u0012\u0011Q\u001b\t\u0005\u0003/\f\t/\u0004\u0002\u0002Z*!\u00111\\Ao\u0003%)hn\u00195fG.,GMC\u0002\u0002`\u0016\u000b!\"\u00198o_R\fG/[8o\u0013\u0011\t\u0019/!7\u0003#Ut7\r[3dW\u0016$g+\u0019:jC:\u001cW-A\u0007qe>$Wo\u0019;Qe\u00164\u0017\u000e_\u000b\u0003\u0003S\u0004B!a;\u0002v6\u0011\u0011Q\u001e\u0006\u0005\u0003_\f\t0\u0001\u0003mC:<'BAAz\u0003\u0011Q\u0017M^1\n\u0007A\fi/\u0001\u0007qe>$Wo\u0019;Be&$\u00180\u0001\bqe>$Wo\u0019;FY\u0016lWM\u001c;\u0015\t\u0005M\u0013Q \u0005\t\u0003\u007f\u0004\u0014\u0011!a\u0001m\u0006\u0019\u0001\u0010J\u0019\u0002\u001fA\u0014x\u000eZ;di&#XM]1u_J,\"A!\u0002\u0011\r\t\u001d!QBA*\u001b\t\u0011IAC\u0002\u0003\f\u0015\u000b!bY8mY\u0016\u001cG/[8o\u0013\u0011\u0011yA!\u0003\u0003\u0011%#XM]1u_J\f\u0001bY1o\u000bF,\u0018\r\u001c\u000b\u0004w\nU\u0001\"CA\u0000e\u0005\u0005\t\u0019AA*\u0003I\u0001(o\u001c3vGR,E.Z7f]Rt\u0015-\\3\u0015\t\u0005%(1\u0004\u0005\t\u0003\u007f\u001c\u0014\u0011!a\u0001m\u0006A\u0001.Y:i\u0007>$W\rF\u0001w\u00035!UmY5nC2tU/\u001c2feB\u0011!JN\n\u0006m\t\u001d\"1\u0007\t\u0007\u0005S\u0011y\u0003\u00194\u000e\u0005\t-\"b\u0001B\u0017\u000b\u00069!/\u001e8uS6,\u0017\u0002\u0002B\u0019\u0005W\u0011\u0011#\u00112tiJ\f7\r\u001e$v]\u000e$\u0018n\u001c82!\u0011\u0011)Da\u000f\u000e\u0005\t]\"\u0002\u0002B\u001d\u0003c\f!![8\n\u0007q\u00139\u0004\u0006\u0002\u0003$Q\u0011\u0011\u0011^\u0001\u0006CB\u0004H.\u001f\u000b\u0004M\n\u0015\u0003\"\u00020:\u0001\u0004\u0001\u0017aB;oCB\u0004H.\u001f\u000b\u0005\u0005\u0017\u0012\t\u0006\u0005\u0003O\u0005\u001b\u0002\u0017b\u0001B(\u000b\n1q\n\u001d;j_:D\u0001Ba\u0015;\u0003\u0003\u0005\rAZ\u0001\u0004q\u0012\u0002\u0014\u0001D<sSR,'+\u001a9mC\u000e,GC\u0001B-!\u0011\tYOa\u0017\n\t\tu\u0013Q\u001e\u0002\u0007\u001f\nTWm\u0019;"
)
public class DecimalNumber extends ScalaNumber implements Number, Product {
   private final BigDecimal n;

   public static Option unapply(final DecimalNumber x$0) {
      return DecimalNumber$.MODULE$.unapply(x$0);
   }

   public static DecimalNumber apply(final BigDecimal n) {
      return DecimalNumber$.MODULE$.apply(n);
   }

   public static Function1 andThen(final Function1 g) {
      return DecimalNumber$.MODULE$.andThen(g);
   }

   public static Function1 compose(final Function1 g) {
      return DecimalNumber$.MODULE$.compose(g);
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

   public BigDecimal n() {
      return this.n;
   }

   public String toString() {
      return this.n().toString();
   }

   public Number abs() {
      return new DecimalNumber(this.n().abs());
   }

   public int signum() {
      return this.n().signum();
   }

   public boolean withinInt() {
      return .MODULE$.BigDecimal().apply(Integer.MIN_VALUE).$less$eq(this.n()) && this.n().$less$eq(.MODULE$.BigDecimal().apply(Integer.MAX_VALUE));
   }

   public boolean withinLong() {
      return .MODULE$.BigDecimal().apply(Long.MIN_VALUE).$less$eq(this.n()) && this.n().$less$eq(.MODULE$.BigDecimal().apply(Long.MAX_VALUE));
   }

   public boolean withinDouble() {
      return .MODULE$.BigDecimal().apply(scala.Double..MODULE$.MinValue()).$less$eq(this.n()) && this.n().$less$eq(.MODULE$.BigDecimal().apply(Double.MAX_VALUE));
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

   public BigDecimal underlying() {
      return this.n();
   }

   public boolean isWhole() {
      return BoxesRunTime.equalsNumObject(this.n().$percent(scala.math.BigDecimal..MODULE$.int2bigDecimal(1)), BoxesRunTime.boxToInteger(0));
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
      return this.n();
   }

   public Rational toRational() {
      return Rational$.MODULE$.apply(this.n());
   }

   public int compare(final Number rhs) {
      return this.n().compare(rhs.toBigDecimal());
   }

   public boolean equals(final Object that) {
      boolean var2;
      if (that instanceof Number) {
         Number var4 = (Number)that;
         var2 = this.$eq$eq$eq(var4);
      } else {
         var2 = BoxesRunTime.equals(that, this.n());
      }

      return var2;
   }

   public boolean $eq$eq$eq(final Number that) {
      boolean var2;
      if (that instanceof DecimalNumber) {
         boolean var15;
         label58: {
            label57: {
               DecimalNumber var4 = (DecimalNumber)that;
               BigDecimal n2 = var4.n();
               BigDecimal var10000 = this.n();
               if (var10000 == null) {
                  if (n2 == null) {
                     break label57;
                  }
               } else if (var10000.equals(n2)) {
                  break label57;
               }

               var15 = false;
               break label58;
            }

            var15 = true;
         }

         var2 = var15;
      } else if (that instanceof IntNumber) {
         boolean var17;
         label49: {
            label48: {
               IntNumber var7 = (IntNumber)that;
               SafeLong m = var7.n();
               BigDecimal var16 = this.n();
               BigDecimal var9 = m.toBigDecimal();
               if (var16 == null) {
                  if (var9 == null) {
                     break label48;
                  }
               } else if (var16.equals(var9)) {
                  break label48;
               }

               var17 = false;
               break label49;
            }

            var17 = true;
         }

         var2 = var17;
      } else if (that instanceof FloatNumber) {
         FloatNumber var10 = (FloatNumber)that;
         double m = var10.n();
         var2 = BoxesRunTime.equalsNumObject(this.n(), BoxesRunTime.boxToDouble(m));
      } else {
         if (!(that instanceof RationalNumber)) {
            throw new MatchError(that);
         }

         RationalNumber var13 = (RationalNumber)that;
         Rational m = var13.n();
         var2 = BoxesRunTime.equalsNumNum(m, this.n());
      }

      return var2;
   }

   public Number unary_$minus() {
      return Number$.MODULE$.apply(this.n().unary_$minus());
   }

   public Number $plus(final Number rhs) {
      return Number$.MODULE$.apply(this.n().$plus(rhs.toBigDecimal()));
   }

   public Number $times(final Number rhs) {
      return Number$.MODULE$.apply(this.n().$times(rhs.toBigDecimal()));
   }

   public Number $minus(final Number rhs) {
      return Number$.MODULE$.apply(this.n().$minus(rhs.toBigDecimal()));
   }

   public Number $div(final Number rhs) {
      return Number$.MODULE$.apply(this.n().$div(rhs.toBigDecimal()));
   }

   public Number tquot(final Number rhs) {
      return Number$.MODULE$.apply(this.n().quot(rhs.toBigDecimal()));
   }

   public Number tmod(final Number rhs) {
      return Number$.MODULE$.apply(this.n().$percent(rhs.toBigDecimal()));
   }

   public Number r_$minus(final Number lhs) {
      return Number$.MODULE$.apply(lhs.toBigDecimal().$minus(this.n()));
   }

   public Number r_$div(final Number lhs) {
      return Number$.MODULE$.apply(lhs.toBigDecimal().$div(this.n()));
   }

   public Number r_tquot(final Number lhs) {
      return Number$.MODULE$.apply(lhs.toBigDecimal().quot(this.n()));
   }

   public Number r_tmod(final Number lhs) {
      return Number$.MODULE$.apply(lhs.toBigDecimal().$percent(this.n()));
   }

   public Tuple2 tquotmod(final Number rhs) {
      Tuple2 t = this.n().$div$percent(rhs.toBigDecimal());
      return new Tuple2(Number$.MODULE$.apply((BigDecimal)t._1()), Number$.MODULE$.apply((BigDecimal)t._2()));
   }

   public Tuple2 r_tquotmod(final Number lhs) {
      Tuple2 t = lhs.toBigDecimal().$div$percent(this.n());
      return new Tuple2(Number$.MODULE$.apply((BigDecimal)t._1()), Number$.MODULE$.apply((BigDecimal)t._2()));
   }

   public Number pow(final Number rhs) {
      return rhs.canBeInt() ? Number$.MODULE$.apply(this.n().pow(rhs.intValue())) : Number$.MODULE$.apply(package$.MODULE$.pow(this.n(), rhs.toBigDecimal()));
   }

   public Number sqrt() {
      return Number$.MODULE$.apply((BigDecimal)((NRoot)spire.std.package.bigDecimal$.MODULE$.BigDecimalAlgebra()).sqrt(this.n()));
   }

   public Number nroot(final int k) {
      return Number$.MODULE$.apply((BigDecimal)((NRoot)spire.std.package.bigDecimal$.MODULE$.BigDecimalAlgebra()).nroot(this.n(), k));
   }

   public Number floor() {
      return Number$.MODULE$.apply((BigDecimal)((IsReal)spire.std.package.bigDecimal$.MODULE$.BigDecimalAlgebra()).floor(this.n()));
   }

   public Number ceil() {
      return Number$.MODULE$.apply((BigDecimal)((IsReal)spire.std.package.bigDecimal$.MODULE$.BigDecimalAlgebra()).ceil(this.n()));
   }

   public Number round() {
      return Number$.MODULE$.apply((BigDecimal)((IsReal)spire.std.package.bigDecimal$.MODULE$.BigDecimalAlgebra()).round(this.n()));
   }

   public DecimalNumber copy(final BigDecimal n) {
      return new DecimalNumber(n);
   }

   public BigDecimal copy$default$1() {
      return this.n();
   }

   public String productPrefix() {
      return "DecimalNumber";
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
      return x$1 instanceof DecimalNumber;
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

   public DecimalNumber(final BigDecimal n) {
      this.n = n;
      ScalaNumericAnyConversions.$init$(this);
      Number.$init$(this);
      Product.$init$(this);
   }
}
