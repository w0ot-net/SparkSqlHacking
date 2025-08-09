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
   bytes = "\u0006\u0005\u0005%g\u0001B\u0013'\u0001EBQA\u000e\u0001\u0005\n]BQ!\u000f\u0001\u0005BiBa!\u0011\u0001\u0005B)\u0012u!\u0002&'\u0011\u0003[e!B\u0013'\u0011\u0003c\u0005\"\u0002\u001c\u0006\t\u0003afaB/\u0006!\u0003\r\tA\u0018\u0005\u0006[\u001e!\tA\u001c\u0005\u0006e\u001e!\ta\u001d\u0005\u0006q\u001e!\t!\u001f\u0005\u0006y\u001e!\t! \u0005\b\u0003\u00039A\u0011AA\u0002\u0011\u001d\t9a\u0002C\u0001\u0003\u0013Aq!!\u0004\b\t\u0003\ty\u0001C\u0004\u0002\u0014\u001d!\t!!\u0006\t\u000f\u0005}q\u0001\"\u0001\u0002\"!9\u0011QE\u0004\u0005\u0002\u0005\u001d\u0002bBA\u0019\u000f\u0011\u0005\u00131\u0007\u0005\b\u0003o9A\u0011IA\u001d\r%\t)&\u0002I\u0001\u0004\u0003\t9\u0006C\u0003n)\u0011\u0005a\u000eC\u0004\u0002dQ!\t!!\u001a\t\u000f\u0005-D\u0003\"\u0001\u0002n\u001d9\u00111O\u0003\t\u0002\u0005UdaBA+\u000b!\u0005\u0011q\u000f\u0005\u0007me!\t!a\u001f\t\u000f\u0005u\u0014\u0004\"\u0011\u0002\u0000!I\u0011QQ\r\u0002\u0002\u0013%\u0011q\u0011\u0005\n\u0003\u0013+\u0011\u0011!C!\u0003\u0017C\u0001\"!%\u0006\u0003\u0003%\tA\u000f\u0005\n\u0003'+\u0011\u0011!C\u0001\u0003+C\u0011\"!)\u0006\u0003\u0003%\t%a)\t\u0013\u0005EV!!A\u0005\u0002\u0005M\u0006\"CA_\u000b\u0005\u0005I\u0011IA`\u0011%\t\t-BA\u0001\n\u0003\n\u0019\rC\u0005\u0002\u0006\u0016\t\t\u0011\"\u0003\u0002\b\nIa\t\\8biRK\b/\u001a\u0006\u0003O!\nQ\u0001^=qKNT!!\u000b\u0016\u0002\u0007M\fHN\u0003\u0002,Y\u0005)1\u000f]1sW*\u0011QFL\u0001\u0007CB\f7\r[3\u000b\u0003=\n1a\u001c:h\u0007\u0001\u0019\"\u0001\u0001\u001a\u0011\u0005M\"T\"\u0001\u0014\n\u0005U2#A\u0004$sC\u000e$\u0018n\u001c8bYRK\b/Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003a\u0002\"a\r\u0001\u0002\u0017\u0011,g-Y;miNK'0Z\u000b\u0002wA\u0011AhP\u0007\u0002{)\ta(A\u0003tG\u0006d\u0017-\u0003\u0002A{\t\u0019\u0011J\u001c;\u0002\u0015\u0005\u001ch*\u001e7mC\ndW-F\u00019Q\t\u0001A\t\u0005\u0002F\u00116\taI\u0003\u0002HU\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\n\u0005%3%AB*uC\ndW-A\u0005GY>\fG\u000fV=qKB\u00111'B\n\u0005\u000baj\u0005\u000b\u0005\u0002=\u001d&\u0011q*\u0010\u0002\b!J|G-^2u!\t\t\u0016L\u0004\u0002S/:\u00111KV\u0007\u0002)*\u0011Q\u000bM\u0001\u0007yI|w\u000e\u001e \n\u0003yJ!\u0001W\u001f\u0002\u000fA\f7m[1hK&\u0011!l\u0017\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.\u001a\u0006\u00031v\"\u0012a\u0013\u0002\u0012\r2|\u0017\r^%t\u0007>tg\r\\5di\u0016$7cA\u0004`OB\u0011\u0001-Z\u0007\u0002C*\u0011!mY\u0001\u0005Y\u0006twMC\u0001e\u0003\u0011Q\u0017M^1\n\u0005\u0019\f'AB(cU\u0016\u001cG\u000fE\u0002RQ*L!![.\u0003\u000f9+X.\u001a:jGB\u0011Ah[\u0005\u0003Yv\u0012QA\u00127pCR\fa\u0001J5oSR$C#A8\u0011\u0005q\u0002\u0018BA9>\u0005\u0011)f.\u001b;\u0002\tAdWo\u001d\u000b\u0004UR4\b\"B;\n\u0001\u0004Q\u0017!\u0001=\t\u000b]L\u0001\u0019\u00016\u0002\u0003e\fQ!\\5okN$2A\u001b>|\u0011\u0015)(\u00021\u0001k\u0011\u00159(\u00021\u0001k\u0003\u0015!\u0018.\\3t)\rQgp \u0005\u0006k.\u0001\rA\u001b\u0005\u0006o.\u0001\rA[\u0001\u0007]\u0016<\u0017\r^3\u0015\u0007)\f)\u0001C\u0003v\u0019\u0001\u0007!.A\u0004ge>l\u0017J\u001c;\u0015\u0007)\fY\u0001C\u0003v\u001b\u0001\u00071(A\u0003u_&sG\u000fF\u0002<\u0003#AQ!\u001e\bA\u0002)\fa\u0001^8M_:<G\u0003BA\f\u0003;\u00012\u0001PA\r\u0013\r\tY\"\u0010\u0002\u0005\u0019>tw\rC\u0003v\u001f\u0001\u0007!.A\u0004u_\u001acw.\u0019;\u0015\u0007)\f\u0019\u0003C\u0003v!\u0001\u0007!.\u0001\u0005u_\u0012{WO\u00197f)\u0011\tI#a\f\u0011\u0007q\nY#C\u0002\u0002.u\u0012a\u0001R8vE2,\u0007\"B;\u0012\u0001\u0004Q\u0017aA1cgR\u0019!.!\u000e\t\u000bU\u0014\u0002\u0019\u00016\u0002\u0017A\f'o]3TiJLgn\u001a\u000b\u0005\u0003w\t\t\u0005\u0005\u0003=\u0003{Q\u0017bAA {\t1q\n\u001d;j_:Dq!a\u0011\u0014\u0001\u0004\t)%A\u0002tiJ\u0004B!a\u0012\u0002P9!\u0011\u0011JA&!\t\u0019V(C\u0002\u0002Nu\na\u0001\u0015:fI\u00164\u0017\u0002BA)\u0003'\u0012aa\u0015;sS:<'bAA'{\t\tb\t\\8bi\u0006\u001b\u0018JZ%oi\u0016<'/\u00197\u0014\rQy\u0016\u0011LA/!\r\tYfB\u0007\u0002\u000bA!\u0011+a\u0018k\u0013\r\t\tg\u0017\u0002\t\u0013:$Xm\u001a:bY\u0006!\u0011/^8u)\u0015Q\u0017qMA5\u0011\u0015)h\u00031\u0001k\u0011\u00159h\u00031\u0001k\u0003\r\u0011X-\u001c\u000b\u0006U\u0006=\u0014\u0011\u000f\u0005\u0006k^\u0001\rA\u001b\u0005\u0006o^\u0001\rA[\u0001\u0012\r2|\u0017\r^!t\u0013\u001aLe\u000e^3he\u0006d\u0007cAA.3M!\u0011dXA=!\r\tY\u0006\u0006\u000b\u0003\u0003k\nqaY8na\u0006\u0014X\rF\u0003<\u0003\u0003\u000b\u0019\tC\u0003v7\u0001\u0007!\u000eC\u0003x7\u0001\u0007!.\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001`\u00035\u0001(o\u001c3vGR\u0004&/\u001a4jqV\u0011\u0011Q\u0012\t\u0004A\u0006=\u0015bAA)C\u0006a\u0001O]8ek\u000e$\u0018I]5us\u0006q\u0001O]8ek\u000e$X\t\\3nK:$H\u0003BAL\u0003;\u00032\u0001PAM\u0013\r\tY*\u0010\u0002\u0004\u0003:L\b\u0002CAP?\u0005\u0005\t\u0019A\u001e\u0002\u0007a$\u0013'A\bqe>$Wo\u0019;Ji\u0016\u0014\u0018\r^8s+\t\t)\u000b\u0005\u0004\u0002(\u00065\u0016qS\u0007\u0003\u0003SS1!a+>\u0003)\u0019w\u000e\u001c7fGRLwN\\\u0005\u0005\u0003_\u000bIK\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003!\u0019\u0017M\\#rk\u0006dG\u0003BA[\u0003w\u00032\u0001PA\\\u0013\r\tI,\u0010\u0002\b\u0005>|G.Z1o\u0011%\ty*IA\u0001\u0002\u0004\t9*\u0001\u0005iCND7i\u001c3f)\u0005Y\u0014\u0001\u0003;p'R\u0014\u0018N\\4\u0015\u0005\u00055\u0005FA\u0003EQ\t!A\t"
)
public class FloatType extends FractionalType {
   public static boolean canEqual(final Object x$1) {
      return FloatType$.MODULE$.canEqual(x$1);
   }

   public static Iterator productIterator() {
      return FloatType$.MODULE$.productIterator();
   }

   public static Object productElement(final int x$1) {
      return FloatType$.MODULE$.productElement(x$1);
   }

   public static int productArity() {
      return FloatType$.MODULE$.productArity();
   }

   public static String productPrefix() {
      return FloatType$.MODULE$.productPrefix();
   }

   public static Iterator productElementNames() {
      return FloatType$.MODULE$.productElementNames();
   }

   public static String productElementName(final int n) {
      return FloatType$.MODULE$.productElementName(n);
   }

   public int defaultSize() {
      return 4;
   }

   public FloatType asNullable() {
      return this;
   }

   public interface FloatIsConflicted extends Numeric {
      default float plus(final float x, final float y) {
         return x + y;
      }

      default float minus(final float x, final float y) {
         return x - y;
      }

      default float times(final float x, final float y) {
         return x * y;
      }

      default float negate(final float x) {
         return -x;
      }

      default float fromInt(final int x) {
         return (float)x;
      }

      default int toInt(final float x) {
         return (int)x;
      }

      default long toLong(final float x) {
         return (long)x;
      }

      default float toFloat(final float x) {
         return x;
      }

      default double toDouble(final float x) {
         return (double)x;
      }

      default float abs(final float x) {
         return .MODULE$.abs(x);
      }

      default Option parseString(final String str) {
         return scala.util.Try..MODULE$.apply((JFunction0.mcF.sp)() -> Float.parseFloat(str)).toOption();
      }

      static void $init$(final FloatIsConflicted $this) {
      }

      // $FF: synthetic method
      private static Object $deserializeLambda$(SerializedLambda var0) {
         return var0.lambdaDeserialize<invokedynamic>(var0);
      }
   }

   public interface FloatAsIfIntegral extends FloatIsConflicted, Integral {
      default float quot(final float x, final float y) {
         return scala.package..MODULE$.BigDecimal().apply((double)x).quot(scala.package..MODULE$.BigDecimal().apply((double)y)).floatValue();
      }

      default float rem(final float x, final float y) {
         return scala.package..MODULE$.BigDecimal().apply((double)x).remainder(scala.package..MODULE$.BigDecimal().apply((double)y)).floatValue();
      }

      static void $init$(final FloatAsIfIntegral $this) {
      }
   }

   public static class FloatAsIfIntegral$ implements FloatAsIfIntegral {
      public static final FloatAsIfIntegral$ MODULE$ = new FloatAsIfIntegral$();

      static {
         PartialOrdering.$init$(MODULE$);
         Ordering.$init$(MODULE$);
         Numeric.$init$(MODULE$);
         FloatType.FloatIsConflicted.$init$(MODULE$);
         Integral.$init$(MODULE$);
         FloatType.FloatAsIfIntegral.$init$(MODULE$);
      }

      public float quot(final float x, final float y) {
         return FloatType.FloatAsIfIntegral.super.quot(x, y);
      }

      public float rem(final float x, final float y) {
         return FloatType.FloatAsIfIntegral.super.rem(x, y);
      }

      public Integral.IntegralOps mkNumericOps(final Object lhs) {
         return Integral.mkNumericOps$(this, lhs);
      }

      public float plus(final float x, final float y) {
         return FloatType.FloatIsConflicted.super.plus(x, y);
      }

      public float minus(final float x, final float y) {
         return FloatType.FloatIsConflicted.super.minus(x, y);
      }

      public float times(final float x, final float y) {
         return FloatType.FloatIsConflicted.super.times(x, y);
      }

      public float negate(final float x) {
         return FloatType.FloatIsConflicted.super.negate(x);
      }

      public float fromInt(final int x) {
         return FloatType.FloatIsConflicted.super.fromInt(x);
      }

      public int toInt(final float x) {
         return FloatType.FloatIsConflicted.super.toInt(x);
      }

      public long toLong(final float x) {
         return FloatType.FloatIsConflicted.super.toLong(x);
      }

      public float toFloat(final float x) {
         return FloatType.FloatIsConflicted.super.toFloat(x);
      }

      public double toDouble(final float x) {
         return FloatType.FloatIsConflicted.super.toDouble(x);
      }

      public float abs(final float x) {
         return FloatType.FloatIsConflicted.super.abs(x);
      }

      public Option parseString(final String str) {
         return FloatType.FloatIsConflicted.super.parseString(str);
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

      public int compare(final float x, final float y) {
         return Float.compare(x, y);
      }

      private Object writeReplace() {
         return new ModuleSerializationProxy(FloatAsIfIntegral$.class);
      }
   }
}
