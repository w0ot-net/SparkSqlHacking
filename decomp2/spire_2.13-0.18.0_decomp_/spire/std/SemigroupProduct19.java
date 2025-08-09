package spire.std;

import cats.kernel.Semigroup;
import scala.Tuple19;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ed\u0001\u0003\f\u0018!\u0003\r\t!G\u000e\t\u000ba\u0004A\u0011A=\t\u000bu\u0004a1\u0001@\t\u000f\u0005\u0005\u0001Ab\u0001\u0002\u0004!9\u0011q\u0001\u0001\u0007\u0004\u0005%\u0001bBA\u0007\u0001\u0019\r\u0011q\u0002\u0005\b\u0003'\u0001a1AA\u000b\u0011\u001d\tI\u0002\u0001D\u0002\u00037Aq!a\b\u0001\r\u0007\t\t\u0003C\u0004\u0002&\u00011\u0019!a\n\t\u000f\u0005-\u0002Ab\u0001\u0002.!9\u0011\u0011\u0007\u0001\u0007\u0004\u0005M\u0002bBA\u001c\u0001\u0019\r\u0011\u0011\b\u0005\b\u0003{\u0001a1AA \u0011\u001d\t\u0019\u0005\u0001D\u0002\u0003\u000bBq!!\u0013\u0001\r\u0007\tY\u0005C\u0004\u0002P\u00011\u0019!!\u0015\t\u000f\u0005U\u0003Ab\u0001\u0002X!9\u00111\f\u0001\u0007\u0004\u0005u\u0003bBA1\u0001\u0019\r\u00111\r\u0005\b\u0003O\u0002a1AA5\u0011\u001d\ti\u0007\u0001C\u0001\u0003_\u0012!cU3nS\u001e\u0014x.\u001e9Qe>$Wo\u0019;2s)\u0011\u0001$G\u0001\u0004gR$'\"\u0001\u000e\u0002\u000bM\u0004\u0018N]3\u0016)qI4IR%M\u001fJ+\u0006l\u00170bI\u001eTW\u000e]:w'\r\u0001Qd\t\t\u0003=\u0005j\u0011a\b\u0006\u0002A\u0005)1oY1mC&\u0011!e\b\u0002\u0007\u0003:L(+\u001a4\u0011\u0007\u0011\nDG\u0004\u0002&]9\u0011a\u0005\f\b\u0003O-j\u0011\u0001\u000b\u0006\u0003S)\na\u0001\u0010:p_Rt4\u0001A\u0005\u00025%\u0011Q&G\u0001\bC2<WM\u0019:b\u0013\ty\u0003'A\u0004qC\u000e\\\u0017mZ3\u000b\u00055J\u0012B\u0001\u001a4\u0005%\u0019V-\\5he>,\bO\u0003\u00020aA)b$N\u001cC\u000b\"[e*\u0015+X5v\u00037MZ5m_J,\u0018B\u0001\u001c \u0005\u001d!V\u000f\u001d7fce\u0002\"\u0001O\u001d\r\u0001\u0011)!\b\u0001b\u0001w\t\t\u0011)\u0005\u0002=\u007fA\u0011a$P\u0005\u0003}}\u0011qAT8uQ&tw\r\u0005\u0002\u001f\u0001&\u0011\u0011i\b\u0002\u0004\u0003:L\bC\u0001\u001dD\t\u0015!\u0005A1\u0001<\u0005\u0005\u0011\u0005C\u0001\u001dG\t\u00159\u0005A1\u0001<\u0005\u0005\u0019\u0005C\u0001\u001dJ\t\u0015Q\u0005A1\u0001<\u0005\u0005!\u0005C\u0001\u001dM\t\u0015i\u0005A1\u0001<\u0005\u0005)\u0005C\u0001\u001dP\t\u0015\u0001\u0006A1\u0001<\u0005\u00051\u0005C\u0001\u001dS\t\u0015\u0019\u0006A1\u0001<\u0005\u00059\u0005C\u0001\u001dV\t\u00151\u0006A1\u0001<\u0005\u0005A\u0005C\u0001\u001dY\t\u0015I\u0006A1\u0001<\u0005\u0005I\u0005C\u0001\u001d\\\t\u0015a\u0006A1\u0001<\u0005\u0005Q\u0005C\u0001\u001d_\t\u0015y\u0006A1\u0001<\u0005\u0005Y\u0005C\u0001\u001db\t\u0015\u0011\u0007A1\u0001<\u0005\u0005a\u0005C\u0001\u001de\t\u0015)\u0007A1\u0001<\u0005\u0005i\u0005C\u0001\u001dh\t\u0015A\u0007A1\u0001<\u0005\u0005q\u0005C\u0001\u001dk\t\u0015Y\u0007A1\u0001<\u0005\u0005y\u0005C\u0001\u001dn\t\u0015q\u0007A1\u0001<\u0005\u0005\u0001\u0006C\u0001\u001dq\t\u0015\t\bA1\u0001<\u0005\u0005\t\u0006C\u0001\u001dt\t\u0015!\bA1\u0001<\u0005\u0005\u0011\u0006C\u0001\u001dw\t\u00159\bA1\u0001<\u0005\u0005\u0019\u0016A\u0002\u0013j]&$H\u0005F\u0001{!\tq20\u0003\u0002}?\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0002\u007fB\u0019A%M\u001c\u0002\u0015M$(/^2ukJ,''\u0006\u0002\u0002\u0006A\u0019A%\r\"\u0002\u0015M$(/^2ukJ,7'\u0006\u0002\u0002\fA\u0019A%M#\u0002\u0015M$(/^2ukJ,G'\u0006\u0002\u0002\u0012A\u0019A%\r%\u0002\u0015M$(/^2ukJ,W'\u0006\u0002\u0002\u0018A\u0019A%M&\u0002\u0015M$(/^2ukJ,g'\u0006\u0002\u0002\u001eA\u0019A%\r(\u0002\u0015M$(/^2ukJ,w'\u0006\u0002\u0002$A\u0019A%M)\u0002\u0015M$(/^2ukJ,\u0007(\u0006\u0002\u0002*A\u0019A%\r+\u0002\u0015M$(/^2ukJ,\u0017(\u0006\u0002\u00020A\u0019A%M,\u0002\u0017M$(/^2ukJ,\u0017\u0007M\u000b\u0003\u0003k\u00012\u0001J\u0019[\u0003-\u0019HO];diV\u0014X-M\u0019\u0016\u0005\u0005m\u0002c\u0001\u00132;\u0006Y1\u000f\u001e:vGR,(/Z\u00193+\t\t\t\u0005E\u0002%c\u0001\f1b\u001d;sk\u000e$XO]32gU\u0011\u0011q\t\t\u0004IE\u001a\u0017aC:ueV\u001cG/\u001e:fcQ*\"!!\u0014\u0011\u0007\u0011\nd-A\u0006tiJ,8\r^;sKF*TCAA*!\r!\u0013'[\u0001\fgR\u0014Xo\u0019;ve\u0016\fd'\u0006\u0002\u0002ZA\u0019A%\r7\u0002\u0017M$(/^2ukJ,\u0017gN\u000b\u0003\u0003?\u00022\u0001J\u0019p\u0003-\u0019HO];diV\u0014X-\r\u001d\u0016\u0005\u0005\u0015\u0004c\u0001\u00132e\u0006Y1\u000f\u001e:vGR,(/Z\u0019:+\t\tY\u0007E\u0002%cU\fqaY8nE&tW\rF\u00035\u0003c\n)\b\u0003\u0004\u0002tU\u0001\r\u0001N\u0001\u0003qBBa!a\u001e\u0016\u0001\u0004!\u0014A\u0001=2\u0001"
)
public interface SemigroupProduct19 extends Semigroup {
   Semigroup structure1();

   Semigroup structure2();

   Semigroup structure3();

   Semigroup structure4();

   Semigroup structure5();

   Semigroup structure6();

   Semigroup structure7();

   Semigroup structure8();

   Semigroup structure9();

   Semigroup structure10();

   Semigroup structure11();

   Semigroup structure12();

   Semigroup structure13();

   Semigroup structure14();

   Semigroup structure15();

   Semigroup structure16();

   Semigroup structure17();

   Semigroup structure18();

   Semigroup structure19();

   // $FF: synthetic method
   static Tuple19 combine$(final SemigroupProduct19 $this, final Tuple19 x0, final Tuple19 x1) {
      return $this.combine(x0, x1);
   }

   default Tuple19 combine(final Tuple19 x0, final Tuple19 x1) {
      return new Tuple19(this.structure1().combine(x0._1(), x1._1()), this.structure2().combine(x0._2(), x1._2()), this.structure3().combine(x0._3(), x1._3()), this.structure4().combine(x0._4(), x1._4()), this.structure5().combine(x0._5(), x1._5()), this.structure6().combine(x0._6(), x1._6()), this.structure7().combine(x0._7(), x1._7()), this.structure8().combine(x0._8(), x1._8()), this.structure9().combine(x0._9(), x1._9()), this.structure10().combine(x0._10(), x1._10()), this.structure11().combine(x0._11(), x1._11()), this.structure12().combine(x0._12(), x1._12()), this.structure13().combine(x0._13(), x1._13()), this.structure14().combine(x0._14(), x1._14()), this.structure15().combine(x0._15(), x1._15()), this.structure16().combine(x0._16(), x1._16()), this.structure17().combine(x0._17(), x1._17()), this.structure18().combine(x0._18(), x1._18()), this.structure19().combine(x0._19(), x1._19()));
   }

   static void $init$(final SemigroupProduct19 $this) {
   }
}
