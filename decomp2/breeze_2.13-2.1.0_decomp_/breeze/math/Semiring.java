package breeze.math;

import java.io.Serializable;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;
import scala.runtime.ModuleSerializationProxy;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=ha\u0002\u0011\"!\u0003\r\tA\n\u0005\u0006u\u0001!\ta\u000f\u0005\u0006\u007f\u00011\t\u0001\u0011\u0005\u0006S\u00021\t\u0001\u0011\u0005\u0006U\u00021\ta\u001b\u0005\u0006a\u00021\t!\u001d\u0005\u0006i\u00021\t!\u001e\u0005\u0006w\u00021\t\u0001 \u0005\u0007\u007f\u0002!\t!!\u0001\t\u0013\u0005E\u0001!%A\u0005\u0002\u0005MqaBA\u0015C!\u0005\u00111\u0006\u0004\u0007A\u0005B\t!a\f\t\u000f\u0005}2\u0002\"\u0001\u0002B!9\u00111I\u0006\u0005\u0004\u0005\u0015\u0003bBA%\u0017\u0011\r\u00111\n\u0005\b\u0003+ZA1AA,\u0011\u001d\t\tg\u0003C\u0002\u0003GBq!!\u001c\f\t\u0007\ty\u0007C\u0004\u0002z-!\u0019!a\u001f\t\u000f\u0005\u00155\u0002b\u0001\u0002\b\"9\u0011\u0011S\u0006\u0005\u0004\u0005MuaBAU\u0017!\r\u00111\u0016\u0004\b\u0003_[\u0001\u0012AAY\u0011\u001d\tyD\u0006C\u0001\u0003kCaa\u0010\f\u0005\u0002\u0005]\u0006BB5\u0017\t\u0003\t9\f\u0003\u0004u-\u0011\u0005\u0011\u0011\u0018\u0005\u0007wZ!\t!a0\t\r)4B\u0011AAc\u0011\u0019\u0001h\u0003\"\u0001\u0002L\"I\u0011\u0011\u001b\f\u0002\u0002\u0013%\u00111\u001b\u0005\n\u0003#\\\u0011\u0011!C\u0005\u0003'\u0014\u0001bU3nSJLgn\u001a\u0006\u0003E\r\nA!\\1uQ*\tA%\u0001\u0004ce\u0016,'0Z\u0002\u0001+\t93iE\u0002\u0001Q9\u0002\"!\u000b\u0017\u000e\u0003)R\u0011aK\u0001\u0006g\u000e\fG.Y\u0005\u0003[)\u0012a!\u00118z%\u00164\u0007CA\u00188\u001d\t\u0001TG\u0004\u00022i5\t!G\u0003\u00024K\u00051AH]8pizJ\u0011aK\u0005\u0003m)\nq\u0001]1dW\u0006<W-\u0003\u00029s\ta1+\u001a:jC2L'0\u00192mK*\u0011aGK\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003q\u0002\"!K\u001f\n\u0005yR#\u0001B+oSR\fAA_3s_V\t\u0011\t\u0005\u0002C\u00072\u0001A!\u0003#\u0001A\u0003\u0005\tQ1\u0001F\u0005\u00051\u0016C\u0001$J!\tIs)\u0003\u0002IU\t9aj\u001c;iS:<\u0007CA\u0015K\u0013\tY%FA\u0002B]fDsaQ'Q+j{F\r\u0005\u0002*\u001d&\u0011qJ\u000b\u0002\fgB,7-[1mSj,G-M\u0003$#J#6K\u0004\u0002*%&\u00111KK\u0001\u0004\u0013:$\u0018\u0007\u0002\u00131i-\nTa\t,X3bs!!K,\n\u0005aS\u0013!B*i_J$\u0018\u0007\u0002\u00131i-\nTaI.]=vs!!\u000b/\n\u0005uS\u0013\u0001\u0002'p]\u001e\fD\u0001\n\u00195WE*1\u0005Y1dE:\u0011\u0011&Y\u0005\u0003E*\nQA\u00127pCR\fD\u0001\n\u00195WE*1%\u001a4iO:\u0011\u0011FZ\u0005\u0003O*\na\u0001R8vE2,\u0017\u0007\u0002\u00131i-\n1a\u001c8f\u0003\u0015!\u0003\u000f\\;t)\r\tEN\u001c\u0005\u0006[\u0012\u0001\r!Q\u0001\u0002C\")q\u000e\u0002a\u0001\u0003\u0006\t!-\u0001\u0004%i&lWm\u001d\u000b\u0004\u0003J\u001c\b\"B7\u0006\u0001\u0004\t\u0005\"B8\u0006\u0001\u0004\t\u0015A\u0002\u0013fc\u0012*\u0017\u000fF\u0002wsj\u0004\"!K<\n\u0005aT#a\u0002\"p_2,\u0017M\u001c\u0005\u0006[\u001a\u0001\r!\u0011\u0005\u0006_\u001a\u0001\r!Q\u0001\tI\t\fgn\u001a\u0013fcR\u0019a/ @\t\u000b5<\u0001\u0019A!\t\u000b=<\u0001\u0019A!\u0002\u000b\rdwn]3\u0015\u000fY\f\u0019!!\u0002\u0002\b!)Q\u000e\u0003a\u0001\u0003\")q\u000e\u0003a\u0001\u0003\"I\u0011\u0011\u0002\u0005\u0011\u0002\u0003\u0007\u00111B\u0001\ni>dWM]1oG\u0016\u00042!KA\u0007\u0013\r\tyA\u000b\u0002\u0007\t>,(\r\\3\u0002\u001f\rdwn]3%I\u00164\u0017-\u001e7uIM*\"!!\u0006+\t\u0005-\u0011qC\u0016\u0003\u00033\u0001B!a\u0007\u0002&5\u0011\u0011Q\u0004\u0006\u0005\u0003?\t\t#A\u0005v]\u000eDWmY6fI*\u0019\u00111\u0005\u0016\u0002\u0015\u0005tgn\u001c;bi&|g.\u0003\u0003\u0002(\u0005u!!E;oG\",7m[3e-\u0006\u0014\u0018.\u00198dK\u0006A1+Z7je&tw\rE\u0002\u0002.-i\u0011!I\n\u0005\u0017!\n\t\u0004\u0005\u0003\u00024\u0005uRBAA\u001b\u0015\u0011\t9$!\u000f\u0002\u0005%|'BAA\u001e\u0003\u0011Q\u0017M^1\n\u0007a\n)$\u0001\u0004=S:LGO\u0010\u000b\u0003\u0003W\t\u0011b]3nSJLgn\u001a#\u0016\u0005\u0005\u001d\u0003#BA\u0017\u0001\u0005-\u0011!D:f[&\u0014\u0018N\\4GY>\fG/\u0006\u0002\u0002NA)\u0011Q\u0006\u0001\u0002PA\u0019\u0011&!\u0015\n\u0007\u0005M#FA\u0003GY>\fG/A\u0006tK6L'/\u001b8h\u0013:$XCAA-!\u0015\ti\u0003AA.!\rI\u0013QL\u0005\u0004\u0003?R#aA%oi\u0006a1/Z7je&tw\rT8oOV\u0011\u0011Q\r\t\u0006\u0003[\u0001\u0011q\r\t\u0004S\u0005%\u0014bAA6U\t!Aj\u001c8h\u00039\u0019X-\\5sS:<')[4J]R,\"!!\u001d\u0011\u000b\u00055\u0002!a\u001d\u0011\u0007=\n)(C\u0002\u0002xe\u0012aAQ5h\u0013:$\u0018!D:f[&\u0014\u0018N\\4TQ>\u0014H/\u0006\u0002\u0002~A)\u0011Q\u0006\u0001\u0002\u0000A\u0019\u0011&!!\n\u0007\u0005\r%FA\u0003TQ>\u0014H/A\u0007tK6L'/\u001b8h\u00076\u0004H\u000e_\u000b\u0003\u0003\u0013\u0003R!!\f\u0001\u0003\u0017\u0003B!!\f\u0002\u000e&\u0019\u0011qR\u0011\u0003\u000f\r{W\u000e\u001d7fq\u0006\u00012/Z7je&twM\u0012:p[JKgnZ\u000b\u0005\u0003+\u000bY\n\u0006\u0003\u0002\u0018\u0006}\u0005#BA\u0017\u0001\u0005e\u0005c\u0001\"\u0002\u001c\u00121\u0011Q\u0014\u000bC\u0002\u0015\u0013\u0011\u0001\u0016\u0005\b\u0003C#\u00029AAR\u0003\u0011\u0011\u0018N\\4\u0011\r\u00055\u0012QUAM\u0013\r\t9+\t\u0002\u0005%&tw-\u0001\u0004gS\u0016dGM\u0011\t\u0004\u0003[3R\"A\u0006\u0003\r\u0019LW\r\u001c3C'\u00111\u0002&a-\u0011\t\u00055\u0002A\u001e\u000b\u0003\u0003W+\u0012A\u001e\u000b\u0006m\u0006m\u0016Q\u0018\u0005\u0006[j\u0001\rA\u001e\u0005\u0006_j\u0001\rA\u001e\u000b\u0006m\u0006\u0005\u00171\u0019\u0005\u0006[n\u0001\rA\u001e\u0005\u0006_n\u0001\rA\u001e\u000b\u0006m\u0006\u001d\u0017\u0011\u001a\u0005\u0006[r\u0001\rA\u001e\u0005\u0006_r\u0001\rA\u001e\u000b\u0006m\u00065\u0017q\u001a\u0005\u0006[v\u0001\rA\u001e\u0005\u0006_v\u0001\rA^\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0003\u0003+\u0004B!a6\u0002^6\u0011\u0011\u0011\u001c\u0006\u0005\u00037\fI$\u0001\u0003mC:<\u0017\u0002BAp\u00033\u0014aa\u00142kK\u000e$\bf\u0002\f\u0002d\u0006%\u00181\u001e\t\u0004S\u0005\u0015\u0018bAAtU\t\u00012+\u001a:jC24VM]:j_:,\u0016\nR\u0001\u0006m\u0006dW/\u001a\u0010\u0002\u0003!:Q#a9\u0002j\u0006-\b"
)
public interface Semiring extends Serializable {
   static Semiring semiringFromRing(final Ring ring) {
      return Semiring$.MODULE$.semiringFromRing(ring);
   }

   static Semiring semiringCmplx() {
      return Semiring$.MODULE$.semiringCmplx();
   }

   static Semiring semiringShort() {
      return Semiring$.MODULE$.semiringShort();
   }

   static Semiring semiringBigInt() {
      return Semiring$.MODULE$.semiringBigInt();
   }

   static Semiring semiringLong() {
      return Semiring$.MODULE$.semiringLong();
   }

   static Semiring semiringInt() {
      return Semiring$.MODULE$.semiringInt();
   }

   static Semiring semiringFloat() {
      return Semiring$.MODULE$.semiringFloat();
   }

   static Semiring semiringD() {
      return Semiring$.MODULE$.semiringD();
   }

   Object zero();

   Object one();

   Object $plus(final Object a, final Object b);

   Object $times(final Object a, final Object b);

   boolean $eq$eq(final Object a, final Object b);

   boolean $bang$eq(final Object a, final Object b);

   default boolean close(final Object a, final Object b, final double tolerance) {
      return BoxesRunTime.equals(a, b);
   }

   default double close$default$3() {
      return 1.0E-4;
   }

   default double zero$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.zero());
   }

   default float zero$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.zero());
   }

   default int zero$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.zero());
   }

   default long zero$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.zero());
   }

   default short zero$mcS$sp() {
      return BoxesRunTime.unboxToShort(this.zero());
   }

   default double one$mcD$sp() {
      return BoxesRunTime.unboxToDouble(this.one());
   }

   default float one$mcF$sp() {
      return BoxesRunTime.unboxToFloat(this.one());
   }

   default int one$mcI$sp() {
      return BoxesRunTime.unboxToInt(this.one());
   }

   default long one$mcJ$sp() {
      return BoxesRunTime.unboxToLong(this.one());
   }

   default short one$mcS$sp() {
      return BoxesRunTime.unboxToShort(this.one());
   }

   default double $plus$mcD$sp(final double a, final double b) {
      return BoxesRunTime.unboxToDouble(this.$plus(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b)));
   }

   default float $plus$mcF$sp(final float a, final float b) {
      return BoxesRunTime.unboxToFloat(this.$plus(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b)));
   }

   default int $plus$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.$plus(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   default long $plus$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.$plus(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   default short $plus$mcS$sp(final short a, final short b) {
      return BoxesRunTime.unboxToShort(this.$plus(BoxesRunTime.boxToShort(a), BoxesRunTime.boxToShort(b)));
   }

   default double $times$mcD$sp(final double a, final double b) {
      return BoxesRunTime.unboxToDouble(this.$times(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b)));
   }

   default float $times$mcF$sp(final float a, final float b) {
      return BoxesRunTime.unboxToFloat(this.$times(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b)));
   }

   default int $times$mcI$sp(final int a, final int b) {
      return BoxesRunTime.unboxToInt(this.$times(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b)));
   }

   default long $times$mcJ$sp(final long a, final long b) {
      return BoxesRunTime.unboxToLong(this.$times(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b)));
   }

   default short $times$mcS$sp(final short a, final short b) {
      return BoxesRunTime.unboxToShort(this.$times(BoxesRunTime.boxToShort(a), BoxesRunTime.boxToShort(b)));
   }

   default boolean $eq$eq$mcD$sp(final double a, final double b) {
      return this.$eq$eq(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b));
   }

   default boolean $eq$eq$mcF$sp(final float a, final float b) {
      return this.$eq$eq(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b));
   }

   default boolean $eq$eq$mcI$sp(final int a, final int b) {
      return this.$eq$eq(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b));
   }

   default boolean $eq$eq$mcJ$sp(final long a, final long b) {
      return this.$eq$eq(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b));
   }

   default boolean $eq$eq$mcS$sp(final short a, final short b) {
      return this.$eq$eq(BoxesRunTime.boxToShort(a), BoxesRunTime.boxToShort(b));
   }

   default boolean $bang$eq$mcD$sp(final double a, final double b) {
      return this.$bang$eq(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b));
   }

   default boolean $bang$eq$mcF$sp(final float a, final float b) {
      return this.$bang$eq(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b));
   }

   default boolean $bang$eq$mcI$sp(final int a, final int b) {
      return this.$bang$eq(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b));
   }

   default boolean $bang$eq$mcJ$sp(final long a, final long b) {
      return this.$bang$eq(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b));
   }

   default boolean $bang$eq$mcS$sp(final short a, final short b) {
      return this.$bang$eq(BoxesRunTime.boxToShort(a), BoxesRunTime.boxToShort(b));
   }

   default boolean close$mcD$sp(final double a, final double b, final double tolerance) {
      return this.close(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b), tolerance);
   }

   default boolean close$mcF$sp(final float a, final float b, final double tolerance) {
      return this.close(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b), tolerance);
   }

   default boolean close$mcI$sp(final int a, final int b, final double tolerance) {
      return this.close(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b), tolerance);
   }

   default boolean close$mcJ$sp(final long a, final long b, final double tolerance) {
      return this.close(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b), tolerance);
   }

   default boolean close$mcS$sp(final short a, final short b, final double tolerance) {
      return this.close(BoxesRunTime.boxToShort(a), BoxesRunTime.boxToShort(b), tolerance);
   }

   static void $init$(final Semiring $this) {
   }

   public static class fieldB$ implements Semiring {
      public static final fieldB$ MODULE$ = new fieldB$();
      private static final long serialVersionUID = 1L;

      static {
         Semiring.$init$(MODULE$);
      }

      public double zero$mcD$sp() {
         return Semiring.super.zero$mcD$sp();
      }

      public float zero$mcF$sp() {
         return Semiring.super.zero$mcF$sp();
      }

      public int zero$mcI$sp() {
         return Semiring.super.zero$mcI$sp();
      }

      public long zero$mcJ$sp() {
         return Semiring.super.zero$mcJ$sp();
      }

      public short zero$mcS$sp() {
         return Semiring.super.zero$mcS$sp();
      }

      public double one$mcD$sp() {
         return Semiring.super.one$mcD$sp();
      }

      public float one$mcF$sp() {
         return Semiring.super.one$mcF$sp();
      }

      public int one$mcI$sp() {
         return Semiring.super.one$mcI$sp();
      }

      public long one$mcJ$sp() {
         return Semiring.super.one$mcJ$sp();
      }

      public short one$mcS$sp() {
         return Semiring.super.one$mcS$sp();
      }

      public double $plus$mcD$sp(final double a, final double b) {
         return Semiring.super.$plus$mcD$sp(a, b);
      }

      public float $plus$mcF$sp(final float a, final float b) {
         return Semiring.super.$plus$mcF$sp(a, b);
      }

      public int $plus$mcI$sp(final int a, final int b) {
         return Semiring.super.$plus$mcI$sp(a, b);
      }

      public long $plus$mcJ$sp(final long a, final long b) {
         return Semiring.super.$plus$mcJ$sp(a, b);
      }

      public short $plus$mcS$sp(final short a, final short b) {
         return Semiring.super.$plus$mcS$sp(a, b);
      }

      public double $times$mcD$sp(final double a, final double b) {
         return Semiring.super.$times$mcD$sp(a, b);
      }

      public float $times$mcF$sp(final float a, final float b) {
         return Semiring.super.$times$mcF$sp(a, b);
      }

      public int $times$mcI$sp(final int a, final int b) {
         return Semiring.super.$times$mcI$sp(a, b);
      }

      public long $times$mcJ$sp(final long a, final long b) {
         return Semiring.super.$times$mcJ$sp(a, b);
      }

      public short $times$mcS$sp(final short a, final short b) {
         return Semiring.super.$times$mcS$sp(a, b);
      }

      public boolean $eq$eq$mcD$sp(final double a, final double b) {
         return Semiring.super.$eq$eq$mcD$sp(a, b);
      }

      public boolean $eq$eq$mcF$sp(final float a, final float b) {
         return Semiring.super.$eq$eq$mcF$sp(a, b);
      }

      public boolean $eq$eq$mcI$sp(final int a, final int b) {
         return Semiring.super.$eq$eq$mcI$sp(a, b);
      }

      public boolean $eq$eq$mcJ$sp(final long a, final long b) {
         return Semiring.super.$eq$eq$mcJ$sp(a, b);
      }

      public boolean $eq$eq$mcS$sp(final short a, final short b) {
         return Semiring.super.$eq$eq$mcS$sp(a, b);
      }

      public boolean $bang$eq$mcD$sp(final double a, final double b) {
         return Semiring.super.$bang$eq$mcD$sp(a, b);
      }

      public boolean $bang$eq$mcF$sp(final float a, final float b) {
         return Semiring.super.$bang$eq$mcF$sp(a, b);
      }

      public boolean $bang$eq$mcI$sp(final int a, final int b) {
         return Semiring.super.$bang$eq$mcI$sp(a, b);
      }

      public boolean $bang$eq$mcJ$sp(final long a, final long b) {
         return Semiring.super.$bang$eq$mcJ$sp(a, b);
      }

      public boolean $bang$eq$mcS$sp(final short a, final short b) {
         return Semiring.super.$bang$eq$mcS$sp(a, b);
      }

      public boolean close(final Object a, final Object b, final double tolerance) {
         return Semiring.super.close(a, b, tolerance);
      }

      public boolean close$mcD$sp(final double a, final double b, final double tolerance) {
         return Semiring.super.close$mcD$sp(a, b, tolerance);
      }

      public boolean close$mcF$sp(final float a, final float b, final double tolerance) {
         return Semiring.super.close$mcF$sp(a, b, tolerance);
      }

      public boolean close$mcI$sp(final int a, final int b, final double tolerance) {
         return Semiring.super.close$mcI$sp(a, b, tolerance);
      }

      public boolean close$mcJ$sp(final long a, final long b, final double tolerance) {
         return Semiring.super.close$mcJ$sp(a, b, tolerance);
      }

      public boolean close$mcS$sp(final short a, final short b, final double tolerance) {
         return Semiring.super.close$mcS$sp(a, b, tolerance);
      }

      public double close$default$3() {
         return Semiring.super.close$default$3();
      }

      public boolean zero() {
         return false;
      }

      public boolean one() {
         return true;
      }

      public boolean $eq$eq(final boolean a, final boolean b) {
         return a == b;
      }

      public boolean $bang$eq(final boolean a, final boolean b) {
         return a != b;
      }

      public boolean $plus(final boolean a, final boolean b) {
         return a || b;
      }

      public boolean $times(final boolean a, final boolean b) {
         return a && b;
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(fieldB$.class);
      }
   }
}
