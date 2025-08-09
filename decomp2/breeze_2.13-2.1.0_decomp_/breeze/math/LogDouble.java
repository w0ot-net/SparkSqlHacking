package breeze.math;

import breeze.linalg.logDiff;
import breeze.linalg.logDiff$;
import breeze.linalg.softmax;
import breeze.linalg.softmax$;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005md\u0001B\u0017/\u0001MB\u0001B\u000f\u0001\u0003\u0006\u0004%\ta\u000f\u0005\t\u007f\u0001\u0011\t\u0011)A\u0005y!)\u0001\t\u0001C\u0001\u0003\")Q\t\u0001C\u0001w!)a\t\u0001C\u0001\u000f\")!\n\u0001C\u0001\u0017\")Q\n\u0001C\u0001\u001d\")\u0001\u000b\u0001C\u0001#\")a\t\u0001C\u0001'\")!\n\u0001C\u0001-\")Q\n\u0001C\u00011\")\u0001\u000b\u0001C\u00015\")A\f\u0001C!;\")a\r\u0001C!O\")\u0001\u000f\u0001C!c\u001e)QO\fE\u0001m\u001a)QF\fE\u0001o\")\u0001)\u0005C\u0001q\u001a!\u00110E\u0001{\u0011!)6C!A!\u0002\u0013a\u0004\"\u0002!\u0014\t\u0003Y\bBB@\u0014\t\u0003\t\t\u0001C\u0004\u0002\u0004M!\t!!\u0001\t\u000bi\u001aB\u0011A\u001e\t\r\u0019\u001bB\u0011AA\u0003\u0011\u0019Q5\u0003\"\u0001\u0002\n!1Qj\u0005C\u0001\u0003\u001bAa\u0001U\n\u0005\u0002\u0005E\u0001\"CA\u000b#\u0005\u0005I1AA\f\u0011\u001d\tY\"\u0005C\u0002\u0003;Aq!!\t\u0012\t\u0003\t\u0019\u0003C\u0004\u0002(E!\t!!\u000b\t\u000f\u00055\u0012\u0003\"\u0001\u00020!9\u0011QF\t\u0005\u0002\u0005]raBA\u001f#!\r\u0011q\b\u0004\b\u0003\u0003\n\u0002\u0012AA\"\u0011\u0019\u0001E\u0005\"\u0001\u0002L!9\u0011Q\n\u0013\u0005\u0002\u0005\u0005\u0001bBA(I\u0011\u0005\u0011\u0011\u0001\u0005\u0007\u001b\u0012\"\t!!\u0015\t\r\u0019#C\u0011AA.\u0011\u001d\t\t\u0007\nC\u0001\u0003GBq!!\u001b%\t\u0003\tY\u0007C\u0005\u0002r\u0011\n\t\u0011\"\u0003\u0002t\tIAj\\4E_V\u0014G.\u001a\u0006\u0003_A\nA!\\1uQ*\t\u0011'\u0001\u0004ce\u0016,'0Z\u0002\u0001'\t\u0001A\u0007\u0005\u00026q5\taGC\u00018\u0003\u0015\u00198-\u00197b\u0013\tIdG\u0001\u0004B]f\u0014VMZ\u0001\tY><g+\u00197vKV\tA\b\u0005\u00026{%\u0011aH\u000e\u0002\u0007\t>,(\r\\3\u0002\u00131|wMV1mk\u0016\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002C\tB\u00111\tA\u0007\u0002]!)!h\u0001a\u0001y\u0005)a/\u00197vK\u00061A\u0005^5nKN$\"A\u0011%\t\u000b%+\u0001\u0019\u0001\"\u0002\u000b=$\b.\u001a:\u0002\t\u0011\"\u0017N\u001e\u000b\u0003\u00052CQ!\u0013\u0004A\u0002\t\u000bQ\u0001\n9mkN$\"AQ(\t\u000b%;\u0001\u0019\u0001\"\u0002\r\u0011j\u0017N\\;t)\t\u0011%\u000bC\u0003J\u0011\u0001\u0007!\t\u0006\u0002C)\")Q+\u0003a\u0001y\u0005\tA\r\u0006\u0002C/\")QK\u0003a\u0001yQ\u0011!)\u0017\u0005\u0006+.\u0001\r\u0001\u0010\u000b\u0003\u0005nCQ!\u0016\u0007A\u0002q\n\u0001\u0002^8TiJLgn\u001a\u000b\u0002=B\u0011q\fZ\u0007\u0002A*\u0011\u0011MY\u0001\u0005Y\u0006twMC\u0001d\u0003\u0011Q\u0017M^1\n\u0005\u0015\u0004'AB*ue&tw-\u0001\u0004fcV\fGn\u001d\u000b\u0003Q.\u0004\"!N5\n\u0005)4$a\u0002\"p_2,\u0017M\u001c\u0005\u0006Y:\u0001\r!\\\u0001\u0002_B\u0011QG\\\u0005\u0003_Z\u00121!\u00118z\u0003!A\u0017m\u001d5D_\u0012,G#\u0001:\u0011\u0005U\u001a\u0018B\u0001;7\u0005\rIe\u000e^\u0001\n\u0019><Gi\\;cY\u0016\u0004\"aQ\t\u0014\u0005E!D#\u0001<\u0003\u0017\u0011{WO\u00197f\u000bb$(/Y\n\u0003'Q\"\"\u0001 @\u0011\u0005u\u001cR\"A\t\t\u000bU+\u0002\u0019\u0001\u001f\u0002\u0017\u0005\u001cHj\\4E_V\u0014G.Z\u000b\u0002\u0005\u0006YAo\u001c'pO\u0012{WO\u00197f)\r\u0011\u0015q\u0001\u0005\u0006Yf\u0001\rA\u0011\u000b\u0004\u0005\u0006-\u0001\"\u00027\u001b\u0001\u0004\u0011Ec\u0001\"\u0002\u0010!)An\u0007a\u0001\u0005R\u0019!)a\u0005\t\u000b1d\u0002\u0019\u0001\"\u0002\u0017\u0011{WO\u00197f\u000bb$(/\u0019\u000b\u0004y\u0006e\u0001\"B+\u001e\u0001\u0004a\u0014!\u00057pO\u0012{WO\u00197f)>$u.\u001e2mKR\u0019A(a\b\t\u000bUs\u0002\u0019\u0001\"\u0002\u00071|w\rF\u0002C\u0003KAQ!V\u0010A\u0002\t\u000b1!\u001a=q)\r\u0011\u00151\u0006\u0005\u0006+\u0002\u0002\rAQ\u0001\u0004a><H#\u0002\"\u00022\u0005M\u0002\"B+\"\u0001\u0004\u0011\u0005BBA\u001bC\u0001\u0007A(A\u0001q)\u0015\u0011\u0015\u0011HA\u001e\u0011\u0015)&\u00051\u0001C\u0011\u0019\t)D\ta\u0001\u0005\u0006\t2+Z7je&tw\rT8h\t>,(\r\\3\u0011\u0005u$#!E*f[&\u0014\u0018N\\4M_\u001e$u.\u001e2mKN!A\u0005NA#!\u0011\u0019\u0015q\t\"\n\u0007\u0005%cF\u0001\u0005TK6L'/\u001b8h)\t\ty$\u0001\u0003{KJ|\u0017aA8oKR)!)a\u0015\u0002X!1\u0011Q\u000b\u0015A\u0002\t\u000b\u0011!\u0019\u0005\u0007\u00033B\u0003\u0019\u0001\"\u0002\u0003\t$RAQA/\u0003?Ba!!\u0016*\u0001\u0004\u0011\u0005BBA-S\u0001\u0007!)\u0001\u0004%KF$S-\u001d\u000b\u0006Q\u0006\u0015\u0014q\r\u0005\u0007\u0003+R\u0003\u0019\u0001\"\t\r\u0005e#\u00061\u0001C\u0003!!#-\u00198hI\u0015\fH#\u00025\u0002n\u0005=\u0004BBA+W\u0001\u0007!\t\u0003\u0004\u0002Z-\u0002\rAQ\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003k\u00022aXA<\u0013\r\tI\b\u0019\u0002\u0007\u001f\nTWm\u0019;"
)
public class LogDouble {
   private final double logValue;

   public static LogDouble pow(final LogDouble d, final LogDouble p) {
      return LogDouble$.MODULE$.pow(d, p);
   }

   public static LogDouble pow(final LogDouble d, final double p) {
      return LogDouble$.MODULE$.pow(d, p);
   }

   public static LogDouble exp(final LogDouble d) {
      return LogDouble$.MODULE$.exp(d);
   }

   public static LogDouble log(final LogDouble d) {
      return LogDouble$.MODULE$.log(d);
   }

   public static double logDoubleToDouble(final LogDouble d) {
      return LogDouble$.MODULE$.logDoubleToDouble(d);
   }

   public static DoubleExtra DoubleExtra(final double d) {
      return LogDouble$.MODULE$.DoubleExtra(d);
   }

   public double logValue() {
      return this.logValue;
   }

   public double value() {
      return .MODULE$.exp(this.logValue());
   }

   public LogDouble $times(final LogDouble other) {
      return new LogDouble(this.logValue() + other.logValue());
   }

   public LogDouble $div(final LogDouble other) {
      return new LogDouble(this.logValue() - other.logValue());
   }

   public LogDouble $plus(final LogDouble other) {
      return new LogDouble(softmax$.MODULE$.apply$mDDDc$sp(this.logValue(), other.logValue(), softmax.implDoubleDouble$.MODULE$));
   }

   public LogDouble $minus(final LogDouble other) {
      return new LogDouble(logDiff$.MODULE$.apply$mDDDc$sp(this.logValue(), other.logValue(), logDiff.implDoubleDouble$.MODULE$));
   }

   public LogDouble $times(final double d) {
      return new LogDouble(this.logValue() + .MODULE$.log(d));
   }

   public LogDouble $div(final double d) {
      return new LogDouble(this.logValue() - .MODULE$.log(d));
   }

   public LogDouble $plus(final double d) {
      return new LogDouble(softmax$.MODULE$.apply$mDDDc$sp(this.logValue(), .MODULE$.log(d), softmax.implDoubleDouble$.MODULE$));
   }

   public LogDouble $minus(final double d) {
      return new LogDouble(logDiff$.MODULE$.apply$mDDDc$sp(this.logValue(), .MODULE$.log(d), logDiff.implDoubleDouble$.MODULE$));
   }

   public String toString() {
      return (new StringBuilder(11)).append("LogDouble(").append(this.logValue()).append(")").toString();
   }

   public boolean equals(final Object o) {
      boolean var2;
      if (o instanceof LogDouble) {
         LogDouble var4 = (LogDouble)o;
         var2 = this.logValue() == var4.logValue();
      } else {
         var2 = false;
      }

      return var2;
   }

   public int hashCode() {
      return Double.hashCode(this.logValue());
   }

   public LogDouble(final double logValue) {
      this.logValue = logValue;
   }

   public static class DoubleExtra {
      private final double d;

      public LogDouble asLogDouble() {
         return new LogDouble(this.d);
      }

      public LogDouble toLogDouble() {
         return new LogDouble(.MODULE$.log(this.d));
      }

      public double logValue() {
         return .MODULE$.log(this.d);
      }

      public LogDouble $times(final LogDouble o) {
         return new LogDouble(o.logValue() + .MODULE$.log(this.d));
      }

      public LogDouble $div(final LogDouble o) {
         return new LogDouble(.MODULE$.log(this.d) - o.logValue());
      }

      public LogDouble $plus(final LogDouble o) {
         return new LogDouble(softmax$.MODULE$.apply$mDDDc$sp(o.logValue(), .MODULE$.log(this.d), softmax.implDoubleDouble$.MODULE$));
      }

      public LogDouble $minus(final LogDouble o) {
         return new LogDouble(logDiff$.MODULE$.apply$mDDDc$sp(.MODULE$.log(this.d), o.logValue(), logDiff.implDoubleDouble$.MODULE$));
      }

      public DoubleExtra(final double d) {
         this.d = d;
      }
   }

   public static class SemiringLogDouble$ implements Semiring {
      public static final SemiringLogDouble$ MODULE$ = new SemiringLogDouble$();

      static {
         Semiring.$init$(MODULE$);
      }

      public double zero$mcD$sp() {
         return Semiring.zero$mcD$sp$(this);
      }

      public float zero$mcF$sp() {
         return Semiring.zero$mcF$sp$(this);
      }

      public int zero$mcI$sp() {
         return Semiring.zero$mcI$sp$(this);
      }

      public long zero$mcJ$sp() {
         return Semiring.zero$mcJ$sp$(this);
      }

      public short zero$mcS$sp() {
         return Semiring.zero$mcS$sp$(this);
      }

      public double one$mcD$sp() {
         return Semiring.one$mcD$sp$(this);
      }

      public float one$mcF$sp() {
         return Semiring.one$mcF$sp$(this);
      }

      public int one$mcI$sp() {
         return Semiring.one$mcI$sp$(this);
      }

      public long one$mcJ$sp() {
         return Semiring.one$mcJ$sp$(this);
      }

      public short one$mcS$sp() {
         return Semiring.one$mcS$sp$(this);
      }

      public double $plus$mcD$sp(final double a, final double b) {
         return Semiring.$plus$mcD$sp$(this, a, b);
      }

      public float $plus$mcF$sp(final float a, final float b) {
         return Semiring.$plus$mcF$sp$(this, a, b);
      }

      public int $plus$mcI$sp(final int a, final int b) {
         return Semiring.$plus$mcI$sp$(this, a, b);
      }

      public long $plus$mcJ$sp(final long a, final long b) {
         return Semiring.$plus$mcJ$sp$(this, a, b);
      }

      public short $plus$mcS$sp(final short a, final short b) {
         return Semiring.$plus$mcS$sp$(this, a, b);
      }

      public double $times$mcD$sp(final double a, final double b) {
         return Semiring.$times$mcD$sp$(this, a, b);
      }

      public float $times$mcF$sp(final float a, final float b) {
         return Semiring.$times$mcF$sp$(this, a, b);
      }

      public int $times$mcI$sp(final int a, final int b) {
         return Semiring.$times$mcI$sp$(this, a, b);
      }

      public long $times$mcJ$sp(final long a, final long b) {
         return Semiring.$times$mcJ$sp$(this, a, b);
      }

      public short $times$mcS$sp(final short a, final short b) {
         return Semiring.$times$mcS$sp$(this, a, b);
      }

      public boolean $eq$eq$mcD$sp(final double a, final double b) {
         return Semiring.$eq$eq$mcD$sp$(this, a, b);
      }

      public boolean $eq$eq$mcF$sp(final float a, final float b) {
         return Semiring.$eq$eq$mcF$sp$(this, a, b);
      }

      public boolean $eq$eq$mcI$sp(final int a, final int b) {
         return Semiring.$eq$eq$mcI$sp$(this, a, b);
      }

      public boolean $eq$eq$mcJ$sp(final long a, final long b) {
         return Semiring.$eq$eq$mcJ$sp$(this, a, b);
      }

      public boolean $eq$eq$mcS$sp(final short a, final short b) {
         return Semiring.$eq$eq$mcS$sp$(this, a, b);
      }

      public boolean $bang$eq$mcD$sp(final double a, final double b) {
         return Semiring.$bang$eq$mcD$sp$(this, a, b);
      }

      public boolean $bang$eq$mcF$sp(final float a, final float b) {
         return Semiring.$bang$eq$mcF$sp$(this, a, b);
      }

      public boolean $bang$eq$mcI$sp(final int a, final int b) {
         return Semiring.$bang$eq$mcI$sp$(this, a, b);
      }

      public boolean $bang$eq$mcJ$sp(final long a, final long b) {
         return Semiring.$bang$eq$mcJ$sp$(this, a, b);
      }

      public boolean $bang$eq$mcS$sp(final short a, final short b) {
         return Semiring.$bang$eq$mcS$sp$(this, a, b);
      }

      public boolean close(final Object a, final Object b, final double tolerance) {
         return Semiring.close$(this, a, b, tolerance);
      }

      public boolean close$mcD$sp(final double a, final double b, final double tolerance) {
         return Semiring.close$mcD$sp$(this, a, b, tolerance);
      }

      public boolean close$mcF$sp(final float a, final float b, final double tolerance) {
         return Semiring.close$mcF$sp$(this, a, b, tolerance);
      }

      public boolean close$mcI$sp(final int a, final int b, final double tolerance) {
         return Semiring.close$mcI$sp$(this, a, b, tolerance);
      }

      public boolean close$mcJ$sp(final long a, final long b, final double tolerance) {
         return Semiring.close$mcJ$sp$(this, a, b, tolerance);
      }

      public boolean close$mcS$sp(final short a, final short b, final double tolerance) {
         return Semiring.close$mcS$sp$(this, a, b, tolerance);
      }

      public double close$default$3() {
         return Semiring.close$default$3$(this);
      }

      public LogDouble zero() {
         return new LogDouble(Double.NEGATIVE_INFINITY);
      }

      public LogDouble one() {
         return new LogDouble((double)0.0F);
      }

      public LogDouble $plus(final LogDouble a, final LogDouble b) {
         return a.$plus(b);
      }

      public LogDouble $times(final LogDouble a, final LogDouble b) {
         return a.$times(b);
      }

      public boolean $eq$eq(final LogDouble a, final LogDouble b) {
         boolean var10000;
         label23: {
            if (a == null) {
               if (b == null) {
                  break label23;
               }
            } else if (a.equals(b)) {
               break label23;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }

      public boolean $bang$eq(final LogDouble a, final LogDouble b) {
         boolean var10000;
         label23: {
            if (a == null) {
               if (b != null) {
                  break label23;
               }
            } else if (!a.equals(b)) {
               break label23;
            }

            var10000 = false;
            return var10000;
         }

         var10000 = true;
         return var10000;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(SemiringLogDouble$.class);
      }
   }
}
