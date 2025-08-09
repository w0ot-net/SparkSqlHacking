package algebra.ring;

import cats.kernel.Eq;
import scala.math.BigInt;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005u4q\u0001C\u0005\u0011\u0002G\u0005a\u0002C\u0003@\u0001\u0019\u0005\u0001\tC\u0003Q\u0001\u0019\u0005\u0011kB\u0003W\u0013!\u0005qKB\u0003\t\u0013!\u0005\u0001\fC\u0003i\t\u0011\u0005\u0011\u000eC\u0003k\t\u0011\u00151\u000eC\u0004v\t\u0005\u0005I\u0011\u0002<\u0003\u000f\u001d\u001bEIU5oO*\u0011!bC\u0001\u0005e&twMC\u0001\r\u0003\u001d\tGnZ3ce\u0006\u001c\u0001!\u0006\u0002\u00109M\u0019\u0001\u0001\u0005\f\u0011\u0005E!R\"\u0001\n\u000b\u0003M\tQa]2bY\u0006L!!\u0006\n\u0003\u0007\u0005s\u0017\u0010E\u0002\u00181ii\u0011!C\u0005\u00033%\u0011qbQ8n[V$\u0018\r^5wKJKgn\u001a\t\u00037qa\u0001\u0001B\u0005\u001e\u0001\u0001\u0006\t\u0011!b\u0001=\t\t\u0011)\u0005\u0002 !A\u0011\u0011\u0003I\u0005\u0003CI\u0011qAT8uQ&tw\r\u000b\u0004\u001dG\u0019\u0002TG\u000f\t\u0003#\u0011J!!\n\n\u0003\u0017M\u0004XmY5bY&TX\rZ\u0019\u0006G\u001dB#&\u000b\b\u0003#!J!!\u000b\n\u0002\u0007%sG/\r\u0003%W=\u001abB\u0001\u00170\u001b\u0005i#B\u0001\u0018\u000e\u0003\u0019a$o\\8u}%\t1#M\u0003$cI\"4G\u0004\u0002\u0012e%\u00111GE\u0001\u0005\u0019>tw-\r\u0003%W=\u001a\u0012'B\u00127oeBdBA\t8\u0013\tA$#A\u0003GY>\fG/\r\u0003%W=\u001a\u0012'B\u0012<yyjdBA\t=\u0013\ti$#\u0001\u0004E_V\u0014G.Z\u0019\u0005I-z3#A\u0002hG\u0012$2!\u0011'O)\tQ\"\tC\u0003D\u0003\u0001\u000fA)\u0001\u0002fmB\u0019Q)\u0013\u000e\u000f\u0005\u0019;U\"A\u0006\n\u0005![\u0011a\u00029bG.\fw-Z\u0005\u0003\u0015.\u0013!!R9\u000b\u0005![\u0001\"B'\u0002\u0001\u0004Q\u0012!A1\t\u000b=\u000b\u0001\u0019\u0001\u000e\u0002\u0003\t\f1\u0001\\2n)\r\u0011F+\u0016\u000b\u00035MCQa\u0011\u0002A\u0004\u0011CQ!\u0014\u0002A\u0002iAQa\u0014\u0002A\u0002i\tqaR\"E%&tw\r\u0005\u0002\u0018\tM!A!\u0017/a!\t\t\",\u0003\u0002\\%\t1\u0011I\\=SK\u001a\u00042aF/`\u0013\tq\u0016B\u0001\tH\u0007\u0012\u0013\u0016N\\4Gk:\u001cG/[8ogB\u0011q\u0003\u0001\t\u0003C\u001al\u0011A\u0019\u0006\u0003G\u0012\f!![8\u000b\u0003\u0015\fAA[1wC&\u0011qM\u0019\u0002\r'\u0016\u0014\u0018.\u00197ju\u0006\u0014G.Z\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003]\u000bQ!\u00199qYf,\"\u0001\\8\u0015\u00055\u0004\bcA\f\u0001]B\u00111d\u001c\u0003\u0006;\u0019\u0011\rA\b\u0005\u0006\u0007\u001a\u0001\u001d!\u001c\u0015\u0003\rI\u0004\"!E:\n\u0005Q\u0014\"AB5oY&tW-\u0001\u0007xe&$XMU3qY\u0006\u001cW\rF\u0001x!\tA80D\u0001z\u0015\tQH-\u0001\u0003mC:<\u0017B\u0001?z\u0005\u0019y%M[3di\u0002"
)
public interface GCDRing extends CommutativeRing {
   static GCDRing apply(final GCDRing ev) {
      return GCDRing$.MODULE$.apply(ev);
   }

   static Object defaultFromDouble(final double a, final Ring ringA, final MultiplicativeGroup mgA) {
      return GCDRing$.MODULE$.defaultFromDouble(a, ringA, mgA);
   }

   static Object defaultFromBigInt(final BigInt n, final Ring ev) {
      return GCDRing$.MODULE$.defaultFromBigInt(n, ev);
   }

   static boolean isMultiplicativeCommutative(final MultiplicativeSemigroup ev) {
      return GCDRing$.MODULE$.isMultiplicativeCommutative(ev);
   }

   static boolean isAdditiveCommutative(final AdditiveSemigroup ev) {
      return GCDRing$.MODULE$.isAdditiveCommutative(ev);
   }

   Object gcd(final Object a, final Object b, final Eq ev);

   Object lcm(final Object a, final Object b, final Eq ev);

   // $FF: synthetic method
   static double gcd$mcD$sp$(final GCDRing $this, final double a, final double b, final Eq ev) {
      return $this.gcd$mcD$sp(a, b, ev);
   }

   default double gcd$mcD$sp(final double a, final double b, final Eq ev) {
      return BoxesRunTime.unboxToDouble(this.gcd(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b), ev));
   }

   // $FF: synthetic method
   static float gcd$mcF$sp$(final GCDRing $this, final float a, final float b, final Eq ev) {
      return $this.gcd$mcF$sp(a, b, ev);
   }

   default float gcd$mcF$sp(final float a, final float b, final Eq ev) {
      return BoxesRunTime.unboxToFloat(this.gcd(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b), ev));
   }

   // $FF: synthetic method
   static int gcd$mcI$sp$(final GCDRing $this, final int a, final int b, final Eq ev) {
      return $this.gcd$mcI$sp(a, b, ev);
   }

   default int gcd$mcI$sp(final int a, final int b, final Eq ev) {
      return BoxesRunTime.unboxToInt(this.gcd(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b), ev));
   }

   // $FF: synthetic method
   static long gcd$mcJ$sp$(final GCDRing $this, final long a, final long b, final Eq ev) {
      return $this.gcd$mcJ$sp(a, b, ev);
   }

   default long gcd$mcJ$sp(final long a, final long b, final Eq ev) {
      return BoxesRunTime.unboxToLong(this.gcd(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b), ev));
   }

   // $FF: synthetic method
   static double lcm$mcD$sp$(final GCDRing $this, final double a, final double b, final Eq ev) {
      return $this.lcm$mcD$sp(a, b, ev);
   }

   default double lcm$mcD$sp(final double a, final double b, final Eq ev) {
      return BoxesRunTime.unboxToDouble(this.lcm(BoxesRunTime.boxToDouble(a), BoxesRunTime.boxToDouble(b), ev));
   }

   // $FF: synthetic method
   static float lcm$mcF$sp$(final GCDRing $this, final float a, final float b, final Eq ev) {
      return $this.lcm$mcF$sp(a, b, ev);
   }

   default float lcm$mcF$sp(final float a, final float b, final Eq ev) {
      return BoxesRunTime.unboxToFloat(this.lcm(BoxesRunTime.boxToFloat(a), BoxesRunTime.boxToFloat(b), ev));
   }

   // $FF: synthetic method
   static int lcm$mcI$sp$(final GCDRing $this, final int a, final int b, final Eq ev) {
      return $this.lcm$mcI$sp(a, b, ev);
   }

   default int lcm$mcI$sp(final int a, final int b, final Eq ev) {
      return BoxesRunTime.unboxToInt(this.lcm(BoxesRunTime.boxToInteger(a), BoxesRunTime.boxToInteger(b), ev));
   }

   // $FF: synthetic method
   static long lcm$mcJ$sp$(final GCDRing $this, final long a, final long b, final Eq ev) {
      return $this.lcm$mcJ$sp(a, b, ev);
   }

   default long lcm$mcJ$sp(final long a, final long b, final Eq ev) {
      return BoxesRunTime.unboxToLong(this.lcm(BoxesRunTime.boxToLong(a), BoxesRunTime.boxToLong(b), ev));
   }
}
