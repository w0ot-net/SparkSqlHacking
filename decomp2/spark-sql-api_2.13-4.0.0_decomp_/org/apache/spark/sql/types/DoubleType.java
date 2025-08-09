package org.apache.spark.sql.types;

import java.lang.invoke.SerializedLambda;
import org.apache.spark.annotation.Stable;
import scala.Function1;
import scala.Option;
import scala.Some;
import scala.collection.Iterator;
import scala.math.Integral;
import scala.math.Numeric;
import scala.math.Ordering;
import scala.math.PartialOrdering;
import scala.math.package.;
import scala.reflect.ScalaSignature;
import scala.runtime.ModuleSerializationProxy;
import scala.runtime.java8.JFunction0;

@Stable
@ScalaSignature(
   bytes = "\u0006\u0005\u0005%g\u0001B\u0013'\u0001EBQA\u000e\u0001\u0005\n]BQ!\u000f\u0001\u0005BiBa!\u0011\u0001\u0005B)\u0012u!\u0002&'\u0011\u0003[e!B\u0013'\u0011\u0003c\u0005\"\u0002\u001c\u0006\t\u0003afaB/\u0006!\u0003\r\tA\u0018\u0005\u0006[\u001e!\tA\u001c\u0005\u0006e\u001e!\ta\u001d\u0005\u0006q\u001e!\t!\u001f\u0005\u0006y\u001e!\t! \u0005\b\u0003\u00039A\u0011AA\u0002\u0011\u001d\t9a\u0002C\u0001\u0003\u0013Aq!!\u0004\b\t\u0003\ty\u0001C\u0004\u0002\u0014\u001d!\t!!\u0006\t\u000f\u0005}q\u0001\"\u0001\u0002\"!9\u00111F\u0004\u0005\u0002\u00055\u0002bBA\u0019\u000f\u0011\u0005\u00131\u0007\u0005\b\u0003o9A\u0011IA\u001d\r%\t)&\u0002I\u0001\u0004\u0003\t9\u0006C\u0003n)\u0011\u0005a\u000eC\u0004\u0002dQ!\t!!\u001a\t\u000f\u0005-D\u0003\"\u0001\u0002n\u001d9\u00111O\u0003\t\u0002\u0005UdaBA+\u000b!\u0005\u0011q\u000f\u0005\u0007me!\t!a\u001f\t\u000f\u0005u\u0014\u0004\"\u0011\u0002\u0000!I\u0011QQ\r\u0002\u0002\u0013%\u0011q\u0011\u0005\n\u0003\u0013+\u0011\u0011!C!\u0003\u0017C\u0001\"!%\u0006\u0003\u0003%\tA\u000f\u0005\n\u0003'+\u0011\u0011!C\u0001\u0003+C\u0011\"!)\u0006\u0003\u0003%\t%a)\t\u0013\u0005EV!!A\u0005\u0002\u0005M\u0006\"CA_\u000b\u0005\u0005I\u0011IA`\u0011%\t\t-BA\u0001\n\u0003\n\u0019\rC\u0005\u0002\u0006\u0016\t\t\u0011\"\u0003\u0002\b\nQAi\\;cY\u0016$\u0016\u0010]3\u000b\u0005\u001dB\u0013!\u0002;za\u0016\u001c(BA\u0015+\u0003\r\u0019\u0018\u000f\u001c\u0006\u0003W1\nQa\u001d9be.T!!\f\u0018\u0002\r\u0005\u0004\u0018m\u00195f\u0015\u0005y\u0013aA8sO\u000e\u00011C\u0001\u00013!\t\u0019D'D\u0001'\u0013\t)dE\u0001\bGe\u0006\u001cG/[8oC2$\u0016\u0010]3\u0002\rqJg.\u001b;?)\u0005A\u0004CA\u001a\u0001\u0003-!WMZ1vYR\u001c\u0016N_3\u0016\u0003m\u0002\"\u0001P \u000e\u0003uR\u0011AP\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0001v\u00121!\u00138u\u0003)\t7OT;mY\u0006\u0014G.Z\u000b\u0002q!\u0012\u0001\u0001\u0012\t\u0003\u000b\"k\u0011A\u0012\u0006\u0003\u000f*\n!\"\u00198o_R\fG/[8o\u0013\tIeI\u0001\u0004Ti\u0006\u0014G.Z\u0001\u000b\t>,(\r\\3UsB,\u0007CA\u001a\u0006'\u0011)\u0001(\u0014)\u0011\u0005qr\u0015BA(>\u0005\u001d\u0001&o\u001c3vGR\u0004\"!U-\u000f\u0005I;fBA*W\u001b\u0005!&BA+1\u0003\u0019a$o\\8u}%\ta(\u0003\u0002Y{\u00059\u0001/Y2lC\u001e,\u0017B\u0001.\\\u00051\u0019VM]5bY&T\u0018M\u00197f\u0015\tAV\bF\u0001L\u0005I!u.\u001e2mK&\u001b8i\u001c8gY&\u001cG/\u001a3\u0014\u0007\u001dyv\r\u0005\u0002aK6\t\u0011M\u0003\u0002cG\u0006!A.\u00198h\u0015\u0005!\u0017\u0001\u00026bm\u0006L!AZ1\u0003\r=\u0013'.Z2u!\r\t\u0006N[\u0005\u0003Sn\u0013qAT;nKJL7\r\u0005\u0002=W&\u0011A.\u0010\u0002\u0007\t>,(\r\\3\u0002\r\u0011Jg.\u001b;%)\u0005y\u0007C\u0001\u001fq\u0013\t\tXH\u0001\u0003V]&$\u0018\u0001\u00029mkN$2A\u001b;w\u0011\u0015)\u0018\u00021\u0001k\u0003\u0005A\b\"B<\n\u0001\u0004Q\u0017!A=\u0002\u000b5Lg.^:\u0015\u0007)T8\u0010C\u0003v\u0015\u0001\u0007!\u000eC\u0003x\u0015\u0001\u0007!.A\u0003uS6,7\u000fF\u0002k}~DQ!^\u0006A\u0002)DQa^\u0006A\u0002)\faA\\3hCR,Gc\u00016\u0002\u0006!)Q\u000f\u0004a\u0001U\u00069aM]8n\u0013:$Hc\u00016\u0002\f!)Q/\u0004a\u0001w\u0005)Ao\\%oiR\u00191(!\u0005\t\u000bUt\u0001\u0019\u00016\u0002\rQ|Gj\u001c8h)\u0011\t9\"!\b\u0011\u0007q\nI\"C\u0002\u0002\u001cu\u0012A\u0001T8oO\")Qo\u0004a\u0001U\u00069Ao\u001c$m_\u0006$H\u0003BA\u0012\u0003S\u00012\u0001PA\u0013\u0013\r\t9#\u0010\u0002\u0006\r2|\u0017\r\u001e\u0005\u0006kB\u0001\rA[\u0001\ti>$u.\u001e2mKR\u0019!.a\f\t\u000bU\f\u0002\u0019\u00016\u0002\u0007\u0005\u00147\u000fF\u0002k\u0003kAQ!\u001e\nA\u0002)\f1\u0002]1sg\u0016\u001cFO]5oOR!\u00111HA!!\u0011a\u0014Q\b6\n\u0007\u0005}RH\u0001\u0004PaRLwN\u001c\u0005\b\u0003\u0007\u001a\u0002\u0019AA#\u0003\r\u0019HO\u001d\t\u0005\u0003\u000f\nyE\u0004\u0003\u0002J\u0005-\u0003CA*>\u0013\r\ti%P\u0001\u0007!J,G-\u001a4\n\t\u0005E\u00131\u000b\u0002\u0007'R\u0014\u0018N\\4\u000b\u0007\u00055SH\u0001\nE_V\u0014G.Z!t\u0013\u001aLe\u000e^3he\u0006d7C\u0002\u000b`\u00033\ni\u0006E\u0002\u0002\\\u001di\u0011!\u0002\t\u0005#\u0006}#.C\u0002\u0002bm\u0013\u0001\"\u00138uK\u001e\u0014\u0018\r\\\u0001\u0005cV|G\u000fF\u0003k\u0003O\nI\u0007C\u0003v-\u0001\u0007!\u000eC\u0003x-\u0001\u0007!.A\u0002sK6$RA[A8\u0003cBQ!^\fA\u0002)DQa^\fA\u0002)\f!\u0003R8vE2,\u0017i]%g\u0013:$Xm\u001a:bYB\u0019\u00111L\r\u0014\tey\u0016\u0011\u0010\t\u0004\u00037\"BCAA;\u0003\u001d\u0019w.\u001c9be\u0016$RaOAA\u0003\u0007CQ!^\u000eA\u0002)DQa^\u000eA\u0002)\fAb\u001e:ji\u0016\u0014V\r\u001d7bG\u0016$\u0012aX\u0001\u000eaJ|G-^2u!J,g-\u001b=\u0016\u0005\u00055\u0005c\u00011\u0002\u0010&\u0019\u0011\u0011K1\u0002\u0019A\u0014x\u000eZ;di\u0006\u0013\u0018\u000e^=\u0002\u001dA\u0014x\u000eZ;di\u0016cW-\\3oiR!\u0011qSAO!\ra\u0014\u0011T\u0005\u0004\u00037k$aA!os\"A\u0011qT\u0010\u0002\u0002\u0003\u00071(A\u0002yIE\nq\u0002\u001d:pIV\u001cG/\u0013;fe\u0006$xN]\u000b\u0003\u0003K\u0003b!a*\u0002.\u0006]UBAAU\u0015\r\tY+P\u0001\u000bG>dG.Z2uS>t\u0017\u0002BAX\u0003S\u0013\u0001\"\u0013;fe\u0006$xN]\u0001\tG\u0006tW)];bYR!\u0011QWA^!\ra\u0014qW\u0005\u0004\u0003sk$a\u0002\"p_2,\u0017M\u001c\u0005\n\u0003?\u000b\u0013\u0011!a\u0001\u0003/\u000b\u0001\u0002[1tQ\u000e{G-\u001a\u000b\u0002w\u0005AAo\\*ue&tw\r\u0006\u0002\u0002\u000e\"\u0012Q\u0001\u0012\u0015\u0003\t\u0011\u0003"
)
public class DoubleType extends FractionalType {
   public static boolean canEqual(final Object x$1) {
      return DoubleType$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return DoubleType$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return DoubleType$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return DoubleType$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return DoubleType$.MODULE$.productPrefix();
   }

   public static Iterator productElementNames() {
      return DoubleType$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return DoubleType$.MODULE$.productElementName(n);
   }

   public int defaultSize() {
      return 8;
   }

   public DoubleType asNullable() {
      return this;
   }

   public interface DoubleIsConflicted extends Numeric {
      default double plus(final double x, final double y) {
         return x + y;
      }

      default double minus(final double x, final double y) {
         return x - y;
      }

      default double times(final double x, final double y) {
         return x * y;
      }

      default double negate(final double x) {
         return -x;
      }

      default double fromInt(final int x) {
         return (double)x;
      }

      default int toInt(final double x) {
         return (int)x;
      }

      default long toLong(final double x) {
         return (long)x;
      }

      default float toFloat(final double x) {
         return (float)x;
      }

      default double toDouble(final double x) {
         return x;
      }

      default double abs(final double x) {
         return .MODULE$.abs(x);
      }

      default Option parseString(final String str) {
         return scala.util.Try..MODULE$.apply((JFunction0.mcD.sp)() -> Double.parseDouble(str)).toOption();
      }

      static void $init$(final DoubleIsConflicted $this) {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public interface DoubleAsIfIntegral extends DoubleIsConflicted, Integral {
      default double quot(final double x, final double y) {
         return scala.package..MODULE$.BigDecimal().apply(x).quot(scala.package..MODULE$.BigDecimal().apply(y)).doubleValue();
      }

      default double rem(final double x, final double y) {
         return scala.package..MODULE$.BigDecimal().apply(x).remainder(scala.package..MODULE$.BigDecimal().apply(y)).doubleValue();
      }

      static void $init$(final DoubleAsIfIntegral $this) {
      }
   }

   public static class DoubleAsIfIntegral$ implements DoubleAsIfIntegral {
      public static final DoubleAsIfIntegral$ MODULE$ = new DoubleAsIfIntegral$();

      static {
         PartialOrdering.$init$(MODULE$);
         Ordering.$init$(MODULE$);
         Numeric.$init$(MODULE$);
         DoubleType.DoubleIsConflicted.$init$(MODULE$);
         Integral.$init$(MODULE$);
         DoubleType.DoubleAsIfIntegral.$init$(MODULE$);
      }

      public double quot(final double x, final double y) {
         return DoubleType.DoubleAsIfIntegral.super.quot(x, y);
      }

      public double rem(final double x, final double y) {
         return DoubleType.DoubleAsIfIntegral.super.rem(x, y);
      }

      public Integral.IntegralOps mkNumericOps(final Object lhs) {
         return Integral.mkNumericOps$(this, lhs);
      }

      public double plus(final double x, final double y) {
         return DoubleType.DoubleIsConflicted.super.plus(x, y);
      }

      public double minus(final double x, final double y) {
         return DoubleType.DoubleIsConflicted.super.minus(x, y);
      }

      public double times(final double x, final double y) {
         return DoubleType.DoubleIsConflicted.super.times(x, y);
      }

      public double negate(final double x) {
         return DoubleType.DoubleIsConflicted.super.negate(x);
      }

      public double fromInt(final int x) {
         return DoubleType.DoubleIsConflicted.super.fromInt(x);
      }

      public int toInt(final double x) {
         return DoubleType.DoubleIsConflicted.super.toInt(x);
      }

      public long toLong(final double x) {
         return DoubleType.DoubleIsConflicted.super.toLong(x);
      }

      public float toFloat(final double x) {
         return DoubleType.DoubleIsConflicted.super.toFloat(x);
      }

      public double toDouble(final double x) {
         return DoubleType.DoubleIsConflicted.super.toDouble(x);
      }

      public double abs(final double x) {
         return DoubleType.DoubleIsConflicted.super.abs(x);
      }

      public Option parseString(final String str) {
         return DoubleType.DoubleIsConflicted.super.parseString(str);
      }

      public Object zero() {
         return Numeric.zero$(this);
      }

      public Object one() {
         return Numeric.one$(this);
      }

      /** @deprecated */
      public int signum(final Object x) {
         return Numeric.signum$(this, x);
      }

      public Object sign(final Object x) {
         return Numeric.sign$(this, x);
      }

      public Some tryCompare(final Object x, final Object y) {
         return Ordering.tryCompare$(this, x, y);
      }

      public boolean lteq(final Object x, final Object y) {
         return Ordering.lteq$(this, x, y);
      }

      public boolean gteq(final Object x, final Object y) {
         return Ordering.gteq$(this, x, y);
      }

      public boolean lt(final Object x, final Object y) {
         return Ordering.lt$(this, x, y);
      }

      public boolean gt(final Object x, final Object y) {
         return Ordering.gt$(this, x, y);
      }

      public boolean equiv(final Object x, final Object y) {
         return Ordering.equiv$(this, x, y);
      }

      public Object max(final Object x, final Object y) {
         return Ordering.max$(this, x, y);
      }

      public Object min(final Object x, final Object y) {
         return Ordering.min$(this, x, y);
      }

      public Ordering reverse() {
         return Ordering.reverse$(this);
      }

      public boolean isReverseOf(final Ordering other) {
         return Ordering.isReverseOf$(this, other);
      }

      public Ordering on(final Function1 f) {
         return Ordering.on$(this, f);
      }

      public Ordering orElse(final Ordering other) {
         return Ordering.orElse$(this, other);
      }

      public Ordering orElseBy(final Function1 f, final Ordering ord) {
         return Ordering.orElseBy$(this, f, ord);
      }

      public Ordering.OrderingOps mkOrderingOps(final Object lhs) {
         return Ordering.mkOrderingOps$(this, lhs);
      }

      public int compare(final double x, final double y) {
         return Double.compare(x, y);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(DoubleAsIfIntegral$.class);
      }
   }
}
