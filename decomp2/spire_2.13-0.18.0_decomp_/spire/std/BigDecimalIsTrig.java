package spire.std;

import java.io.Serializable;
import java.math.MathContext;
import scala.math.BigDecimal;
import scala.package.;
import scala.reflect.ScalaSignature;
import spire.algebra.Trig;
import spire.math.Real;
import spire.math.Real$;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Ed\u0001\u0002\u0010 \u0001\u0011B\u0001\u0002\u0011\u0001\u0003\u0002\u0003\u0006I!\u0011\u0005\u0006\u0013\u0002!\tA\u0013\u0005\b\u001d\u0002\u0011\r\u0011\"\u0001P\u0011\u0019\u0019\u0006\u0001)A\u0005!\")A\u000b\u0001C\u0001+\"AQ\f\u0001EC\u0002\u0013\u0005a\f\u0003\u0005`\u0001!\u0015\r\u0011\"\u0001_\u0011\u0015\u0001\u0007\u0001\"\u0001b\u0011\u0015!\u0007\u0001\"\u0001f\u0011\u00159\u0007\u0001\"\u0001i\u0011\u0015Y\u0007\u0001\"\u0001m\u0011!q\u0007\u0001#b\u0001\n\u0003q\u0006\"B8\u0001\t\u0003\u0001\b\"\u0002:\u0001\t\u0003\u0019\b\"B;\u0001\t\u00031\b\"\u0002=\u0001\t\u0003I\b\"B>\u0001\t\u0003a\b\"\u0002@\u0001\t\u0003y\bbBA\u0002\u0001\u0011\u0005\u0011Q\u0001\u0005\b\u0003\u0013\u0001A\u0011AA\u0006\u0011\u001d\ty\u0001\u0001C\u0001\u0003#Aq!!\u0007\u0001\t\u0003\tY\u0002C\u0004\u0002 \u0001!\t!!\t\t\u000f\u0005\u0015\u0002\u0001\"\u0001\u0002(\u001dI\u0011qG\u0010\u0002\u0002#\u0005\u0011\u0011\b\u0004\t=}\t\t\u0011#\u0001\u0002<!1\u0011J\u0007C\u0001\u0003\u000fB\u0011\"!\u0013\u001b#\u0003%\t!a\u0013\t\u0013\u0005\u0005$$!A\u0005\n\u0005\r$\u0001\u0005\"jO\u0012+7-[7bY&\u001bHK]5h\u0015\t\u0001\u0013%A\u0002ti\u0012T\u0011AI\u0001\u0006gBL'/Z\u0002\u0001'\u0011\u0001QeK\u001f\u0011\u0005\u0019JS\"A\u0014\u000b\u0003!\nQa]2bY\u0006L!AK\u0014\u0003\r\u0005s\u0017PU3g!\ras&M\u0007\u0002[)\u0011a&I\u0001\bC2<WM\u0019:b\u0013\t\u0001TF\u0001\u0003Ue&<\u0007C\u0001\u001a;\u001d\t\u0019\u0004H\u0004\u00025o5\tQG\u0003\u00027G\u00051AH]8pizJ\u0011\u0001K\u0005\u0003s\u001d\nq\u0001]1dW\u0006<W-\u0003\u0002<y\tQ!)[4EK\u000eLW.\u00197\u000b\u0005e:\u0003C\u0001\u001a?\u0013\tyDH\u0001\u0007TKJL\u0017\r\\5{C\ndW-\u0001\u0002nGB\u0011!iR\u0007\u0002\u0007*\u0011A)R\u0001\u0005[\u0006$\bNC\u0001G\u0003\u0011Q\u0017M^1\n\u0005!\u001b%aC'bi\"\u001cuN\u001c;fqR\fa\u0001P5oSRtDCA&N!\ta\u0005!D\u0001 \u0011\u001d\u0001%\u0001%AA\u0002\u0005\u000bAAY5ugV\t\u0001\u000b\u0005\u0002'#&\u0011!k\n\u0002\u0004\u0013:$\u0018!\u00022jiN\u0004\u0013\u0001\u00034s_6\u0014V-\u00197\u0015\u0005E2\u0006\"B,\u0006\u0001\u0004A\u0016!\u0001:\u0011\u0005e[V\"\u0001.\u000b\u0005\u0011\u000b\u0013B\u0001/[\u0005\u0011\u0011V-\u00197\u0002\u0003\u0015,\u0012!M\u0001\u0003a&\f1!\u001a=q)\t\t$\rC\u0003d\u0011\u0001\u0007\u0011'A\u0001y\u0003\u0015)\u0007\u0010]72)\t\td\rC\u0003d\u0013\u0001\u0007\u0011'A\u0002m_\u001e$\"!M5\t\u000b)T\u0001\u0019A\u0019\u0002\u0003\u0005\fQ\u0001\\8hcA$\"!M7\t\u000b)\\\u0001\u0019A\u0019\u0002!\u0011,wM]3fgB+'OU1eS\u0006t\u0017!\u0003;p%\u0006$\u0017.\u00198t)\t\t\u0014\u000fC\u0003k\u001b\u0001\u0007\u0011'A\u0005u_\u0012+wM]3fgR\u0011\u0011\u0007\u001e\u0005\u0006U:\u0001\r!M\u0001\u0004g&tGCA\u0019x\u0011\u0015Qw\u00021\u00012\u0003\r\u0019wn\u001d\u000b\u0003ciDQA\u001b\tA\u0002E\n1\u0001^1o)\t\tT\u0010C\u0003k#\u0001\u0007\u0011'\u0001\u0003bg&tGcA\u0019\u0002\u0002!)!N\u0005a\u0001c\u0005!\u0011mY8t)\r\t\u0014q\u0001\u0005\u0006UN\u0001\r!M\u0001\u0005CR\fg\u000eF\u00022\u0003\u001bAQA\u001b\u000bA\u0002E\nQ!\u0019;b]J\"R!MA\n\u0003/Aa!!\u0006\u0016\u0001\u0004\t\u0014!A=\t\u000b\r,\u0002\u0019A\u0019\u0002\tMLg\u000e\u001b\u000b\u0004c\u0005u\u0001\"\u00026\u0017\u0001\u0004\t\u0014\u0001B2pg\"$2!MA\u0012\u0011\u0015Qw\u00031\u00012\u0003\u0011!\u0018M\u001c5\u0015\u0007E\nI\u0003C\u0003k1\u0001\u0007\u0011\u0007K\u0004\u0001\u0003[\t\u0019$!\u000e\u0011\u0007\u0019\ny#C\u0002\u00022\u001d\u0012\u0001cU3sS\u0006dg+\u001a:tS>tW+\u0013#\u0002\u000bY\fG.^3\u001f\u0003\u0005\t\u0001CQ5h\t\u0016\u001c\u0017.\\1m\u0013N$&/[4\u0011\u00051S2\u0003\u0002\u000e&\u0003{\u0001B!a\u0010\u0002F5\u0011\u0011\u0011\t\u0006\u0004\u0003\u0007*\u0015AA5p\u0013\ry\u0014\u0011\t\u000b\u0003\u0003s\t1\u0004\n7fgNLg.\u001b;%OJ,\u0017\r^3sI\u0011,g-Y;mi\u0012\nTCAA'U\r\t\u0015qJ\u0016\u0003\u0003#\u0002B!a\u0015\u0002^5\u0011\u0011Q\u000b\u0006\u0005\u0003/\nI&A\u0005v]\u000eDWmY6fI*\u0019\u00111L\u0014\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002`\u0005U#!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006aqO]5uKJ+\u0007\u000f\\1dKR\u0011\u0011Q\r\t\u0005\u0003O\ni'\u0004\u0002\u0002j)\u0019\u00111N#\u0002\t1\fgnZ\u0005\u0005\u0003_\nIG\u0001\u0004PE*,7\r\u001e"
)
public class BigDecimalIsTrig implements Trig, Serializable {
   private static final long serialVersionUID = 1L;
   private BigDecimal e;
   private BigDecimal pi;
   private BigDecimal degreesPerRadian;
   private final int bits;
   private volatile byte bitmap$0;

   public static MathContext $lessinit$greater$default$1() {
      return BigDecimalIsTrig$.MODULE$.$lessinit$greater$default$1();
   }

   public double e$mcD$sp() {
      return Trig.e$mcD$sp$(this);
   }

   public float e$mcF$sp() {
      return Trig.e$mcF$sp$(this);
   }

   public double pi$mcD$sp() {
      return Trig.pi$mcD$sp$(this);
   }

   public float pi$mcF$sp() {
      return Trig.pi$mcF$sp$(this);
   }

   public double exp$mcD$sp(final double a) {
      return Trig.exp$mcD$sp$(this, a);
   }

   public float exp$mcF$sp(final float a) {
      return Trig.exp$mcF$sp$(this, a);
   }

   public double expm1$mcD$sp(final double a) {
      return Trig.expm1$mcD$sp$(this, a);
   }

   public float expm1$mcF$sp(final float a) {
      return Trig.expm1$mcF$sp$(this, a);
   }

   public double log$mcD$sp(final double a) {
      return Trig.log$mcD$sp$(this, a);
   }

   public float log$mcF$sp(final float a) {
      return Trig.log$mcF$sp$(this, a);
   }

   public double log1p$mcD$sp(final double a) {
      return Trig.log1p$mcD$sp$(this, a);
   }

   public float log1p$mcF$sp(final float a) {
      return Trig.log1p$mcF$sp$(this, a);
   }

   public double sin$mcD$sp(final double a) {
      return Trig.sin$mcD$sp$(this, a);
   }

   public float sin$mcF$sp(final float a) {
      return Trig.sin$mcF$sp$(this, a);
   }

   public double cos$mcD$sp(final double a) {
      return Trig.cos$mcD$sp$(this, a);
   }

   public float cos$mcF$sp(final float a) {
      return Trig.cos$mcF$sp$(this, a);
   }

   public double tan$mcD$sp(final double a) {
      return Trig.tan$mcD$sp$(this, a);
   }

   public float tan$mcF$sp(final float a) {
      return Trig.tan$mcF$sp$(this, a);
   }

   public double asin$mcD$sp(final double a) {
      return Trig.asin$mcD$sp$(this, a);
   }

   public float asin$mcF$sp(final float a) {
      return Trig.asin$mcF$sp$(this, a);
   }

   public double acos$mcD$sp(final double a) {
      return Trig.acos$mcD$sp$(this, a);
   }

   public float acos$mcF$sp(final float a) {
      return Trig.acos$mcF$sp$(this, a);
   }

   public double atan$mcD$sp(final double a) {
      return Trig.atan$mcD$sp$(this, a);
   }

   public float atan$mcF$sp(final float a) {
      return Trig.atan$mcF$sp$(this, a);
   }

   public double atan2$mcD$sp(final double y, final double x) {
      return Trig.atan2$mcD$sp$(this, y, x);
   }

   public float atan2$mcF$sp(final float y, final float x) {
      return Trig.atan2$mcF$sp$(this, y, x);
   }

   public double sinh$mcD$sp(final double x) {
      return Trig.sinh$mcD$sp$(this, x);
   }

   public float sinh$mcF$sp(final float x) {
      return Trig.sinh$mcF$sp$(this, x);
   }

   public double cosh$mcD$sp(final double x) {
      return Trig.cosh$mcD$sp$(this, x);
   }

   public float cosh$mcF$sp(final float x) {
      return Trig.cosh$mcF$sp$(this, x);
   }

   public double tanh$mcD$sp(final double x) {
      return Trig.tanh$mcD$sp$(this, x);
   }

   public float tanh$mcF$sp(final float x) {
      return Trig.tanh$mcF$sp$(this, x);
   }

   public double toRadians$mcD$sp(final double a) {
      return Trig.toRadians$mcD$sp$(this, a);
   }

   public float toRadians$mcF$sp(final float a) {
      return Trig.toRadians$mcF$sp$(this, a);
   }

   public double toDegrees$mcD$sp(final double a) {
      return Trig.toDegrees$mcD$sp$(this, a);
   }

   public float toDegrees$mcF$sp(final float a) {
      return Trig.toDegrees$mcF$sp$(this, a);
   }

   public int bits() {
      return this.bits;
   }

   public BigDecimal fromReal(final Real r) {
      return .MODULE$.BigDecimal().apply(r.apply(this.bits()).toBigInt()).$div(.MODULE$.BigDecimal().apply(.MODULE$.BigInt().apply(2).pow(this.bits())));
   }

   private BigDecimal e$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 1) == 0) {
            this.e = this.fromReal(Real$.MODULE$.e());
            this.bitmap$0 = (byte)(this.bitmap$0 | 1);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.e;
   }

   public BigDecimal e() {
      return (byte)(this.bitmap$0 & 1) == 0 ? this.e$lzycompute() : this.e;
   }

   private BigDecimal pi$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 2) == 0) {
            this.pi = this.fromReal(Real$.MODULE$.pi());
            this.bitmap$0 = (byte)(this.bitmap$0 | 2);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.pi;
   }

   public BigDecimal pi() {
      return (byte)(this.bitmap$0 & 2) == 0 ? this.pi$lzycompute() : this.pi;
   }

   public BigDecimal exp(final BigDecimal x) {
      return this.fromReal(Real$.MODULE$.exp(Real$.MODULE$.apply(x)));
   }

   public BigDecimal expm1(final BigDecimal x) {
      return this.fromReal(Real$.MODULE$.exp(Real$.MODULE$.apply(x)).$minus(Real$.MODULE$.one()));
   }

   public BigDecimal log(final BigDecimal a) {
      return this.fromReal(Real$.MODULE$.log(Real$.MODULE$.apply(a)));
   }

   public BigDecimal log1p(final BigDecimal a) {
      return this.fromReal(Real$.MODULE$.log(Real$.MODULE$.apply(a).$plus(Real$.MODULE$.one())));
   }

   private BigDecimal degreesPerRadian$lzycompute() {
      synchronized(this){}

      try {
         if ((byte)(this.bitmap$0 & 4) == 0) {
            this.degreesPerRadian = this.fromReal(Real$.MODULE$.apply(360).$div(Real$.MODULE$.pi().$times(Real$.MODULE$.apply(2))));
            this.bitmap$0 = (byte)(this.bitmap$0 | 4);
         }
      } catch (Throwable var3) {
         throw var3;
      }

      return this.degreesPerRadian;
   }

   public BigDecimal degreesPerRadian() {
      return (byte)(this.bitmap$0 & 4) == 0 ? this.degreesPerRadian$lzycompute() : this.degreesPerRadian;
   }

   public BigDecimal toRadians(final BigDecimal a) {
      return a.$div(this.degreesPerRadian());
   }

   public BigDecimal toDegrees(final BigDecimal a) {
      return a.$times(this.degreesPerRadian());
   }

   public BigDecimal sin(final BigDecimal a) {
      return this.fromReal(Real$.MODULE$.sin(Real$.MODULE$.apply(a)));
   }

   public BigDecimal cos(final BigDecimal a) {
      return this.fromReal(Real$.MODULE$.cos(Real$.MODULE$.apply(a)));
   }

   public BigDecimal tan(final BigDecimal a) {
      return this.fromReal(Real$.MODULE$.tan(Real$.MODULE$.apply(a)));
   }

   public BigDecimal asin(final BigDecimal a) {
      return this.fromReal(Real$.MODULE$.asin(Real$.MODULE$.apply(a)));
   }

   public BigDecimal acos(final BigDecimal a) {
      return this.fromReal(Real$.MODULE$.acos(Real$.MODULE$.apply(a)));
   }

   public BigDecimal atan(final BigDecimal a) {
      return this.fromReal(Real$.MODULE$.atan(Real$.MODULE$.apply(a)));
   }

   public BigDecimal atan2(final BigDecimal y, final BigDecimal x) {
      return this.fromReal(Real$.MODULE$.atan2(Real$.MODULE$.apply(y), Real$.MODULE$.apply(x)));
   }

   public BigDecimal sinh(final BigDecimal a) {
      return this.fromReal(Real$.MODULE$.sinh(Real$.MODULE$.apply(a)));
   }

   public BigDecimal cosh(final BigDecimal a) {
      return this.fromReal(Real$.MODULE$.cosh(Real$.MODULE$.apply(a)));
   }

   public BigDecimal tanh(final BigDecimal a) {
      return this.fromReal(Real$.MODULE$.tanh(Real$.MODULE$.apply(a)));
   }

   public BigDecimalIsTrig(final MathContext mc) {
      this.bits = Real$.MODULE$.digitsToBits(mc.getPrecision() + 1);
   }
}
