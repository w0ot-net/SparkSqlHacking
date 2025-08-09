package algebra.ring;

import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005aa\u0002\u0005\n!\u0003\r\tA\u0004\u0005\u0006\u0019\u0002!\t!\u0014\u0005\u0006#\u0002!\tAU\u0004\u00061&A\t!\u0017\u0004\u0006\u0011%A\tA\u0017\u0005\u0006U\u0012!\ta\u001b\u0005\u0006Y\u0012!)!\u001c\u0005\bq\u0012\t\t\u0011\"\u0003z\u00051!\u0015N^5tS>t'+\u001b8h\u0015\tQ1\"\u0001\u0003sS:<'\"\u0001\u0007\u0002\u000f\u0005dw-\u001a2sC\u000e\u0001QCA\b\u001d'\u0011\u0001\u0001CF%\u0011\u0005E!R\"\u0001\n\u000b\u0003M\tQa]2bY\u0006L!!\u0006\n\u0003\u0007\u0005s\u0017\u0010E\u0002\u00181ii\u0011!C\u0005\u00033%\u0011AAU5oOB\u00111\u0004\b\u0007\u0001\t%i\u0002\u0001)A\u0001\u0002\u000b\u0007aDA\u0001B#\ty\u0002\u0003\u0005\u0002\u0012A%\u0011\u0011E\u0005\u0002\b\u001d>$\b.\u001b8hQ!a2E\n\u00196u}\"\u0005CA\t%\u0013\t)#CA\u0006ta\u0016\u001c\u0017.\u00197ju\u0016$\u0017'B\u0012(Q)JcBA\t)\u0013\tI##\u0001\u0003CsR,\u0017\u0007\u0002\u0013,_Mq!\u0001L\u0018\u000e\u00035R!AL\u0007\u0002\rq\u0012xn\u001c;?\u0013\u0005\u0019\u0012'B\u00122eQ\u001adBA\t3\u0013\t\u0019$#A\u0003TQ>\u0014H/\r\u0003%W=\u001a\u0012'B\u00127oeBdBA\t8\u0013\tA$#A\u0002J]R\fD\u0001J\u00160'E*1e\u000f\u001f?{9\u0011\u0011\u0003P\u0005\u0003{I\tA\u0001T8oOF\"AeK\u0018\u0014c\u0015\u0019\u0003)Q\"C\u001d\t\t\u0012)\u0003\u0002C%\u0005)a\t\\8biF\"AeK\u0018\u0014c\u0015\u0019SI\u0012%H\u001d\t\tb)\u0003\u0002H%\u00051Ai\\;cY\u0016\fD\u0001J\u00160'A\u0019qC\u0013\u000e\n\u0005-K!!C*f[&4\u0017.\u001a7e\u0003\u0019!\u0013N\\5uIQ\ta\n\u0005\u0002\u0012\u001f&\u0011\u0001K\u0005\u0002\u0005+:LG/\u0001\u0006ge>lGi\\;cY\u0016$\"AG*\t\u000bQ\u0013\u0001\u0019A+\u0002\u0003\u0005\u0004\"!\u0005,\n\u0005]\u0013\"A\u0002#pk\ndW-\u0001\u0007ESZL7/[8o%&tw\r\u0005\u0002\u0018\tM!Aa\u00170c!\t\tB,\u0003\u0002^%\t1\u0011I\\=SK\u001a\u00042aF0b\u0013\t\u0001\u0017BA\u000bESZL7/[8o%&twMR;oGRLwN\\:\u0011\u0005]\u0001\u0001CA2i\u001b\u0005!'BA3g\u0003\tIwNC\u0001h\u0003\u0011Q\u0017M^1\n\u0005%$'\u0001D*fe&\fG.\u001b>bE2,\u0017A\u0002\u001fj]&$h\bF\u0001Z\u0003\u0015\t\u0007\u000f\u001d7z+\tq\u0017\u000f\u0006\u0002peB\u0019q\u0003\u00019\u0011\u0005m\tH!B\u000f\u0007\u0005\u0004q\u0002\"B:\u0007\u0001\by\u0017!\u00014)\u0005\u0019)\bCA\tw\u0013\t9(C\u0001\u0004j]2Lg.Z\u0001\roJLG/\u001a*fa2\f7-\u001a\u000b\u0002uB\u00111P`\u0007\u0002y*\u0011QPZ\u0001\u0005Y\u0006tw-\u0003\u0002\u0000y\n1qJ\u00196fGR\u0004"
)
public interface DivisionRing extends Ring, Semifield {
   static DivisionRing apply(final DivisionRing f) {
      return DivisionRing$.MODULE$.apply(f);
   }

   static Object defaultFromDouble(final double a, final Ring ringA, final MultiplicativeGroup mgA) {
      return DivisionRing$.MODULE$.defaultFromDouble(a, ringA, mgA);
   }

   static Object defaultFromBigInt(final BigInt n, final Ring ev) {
      return DivisionRing$.MODULE$.defaultFromBigInt(n, ev);
   }

   static boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return DivisionRing$.MODULE$.isMultiplicativeCommutative(ev);
   }

   static boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return DivisionRing$.MODULE$.isAdditiveCommutative(ev);
   }

   // $FF: synthetic method
   static Object fromDouble$(final DivisionRing $this, final double a) {
      return $this.fromDouble(a);
   }

   default Object fromDouble(final double a) {
      return DivisionRing$.MODULE$.defaultFromDouble(a, this, this);
   }

   // $FF: synthetic method
   static byte fromDouble$mcB$sp$(final DivisionRing $this, final double a) {
      return $this.fromDouble$mcB$sp(a);
   }

   default byte fromDouble$mcB$sp(final double a) {
      return BoxesRunTime.unboxToByte(this.fromDouble(a));
   }

   // $FF: synthetic method
   static double fromDouble$mcD$sp$(final DivisionRing $this, final double a) {
      return $this.fromDouble$mcD$sp(a);
   }

   default double fromDouble$mcD$sp(final double a) {
      return BoxesRunTime.unboxToDouble(this.fromDouble(a));
   }

   // $FF: synthetic method
   static float fromDouble$mcF$sp$(final DivisionRing $this, final double a) {
      return $this.fromDouble$mcF$sp(a);
   }

   default float fromDouble$mcF$sp(final double a) {
      return BoxesRunTime.unboxToFloat(this.fromDouble(a));
   }

   // $FF: synthetic method
   static int fromDouble$mcI$sp$(final DivisionRing $this, final double a) {
      return $this.fromDouble$mcI$sp(a);
   }

   default int fromDouble$mcI$sp(final double a) {
      return BoxesRunTime.unboxToInt(this.fromDouble(a));
   }

   // $FF: synthetic method
   static long fromDouble$mcJ$sp$(final DivisionRing $this, final double a) {
      return $this.fromDouble$mcJ$sp(a);
   }

   default long fromDouble$mcJ$sp(final double a) {
      return BoxesRunTime.unboxToLong(this.fromDouble(a));
   }

   // $FF: synthetic method
   static short fromDouble$mcS$sp$(final DivisionRing $this, final double a) {
      return $this.fromDouble$mcS$sp(a);
   }

   default short fromDouble$mcS$sp(final double a) {
      return BoxesRunTime.unboxToShort(this.fromDouble(a));
   }

   static void $init$(final DivisionRing $this) {
   }
}
