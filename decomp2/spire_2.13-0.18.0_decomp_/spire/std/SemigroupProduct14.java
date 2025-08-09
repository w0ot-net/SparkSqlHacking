package spire.std;

import cats.kernel.Semigroup;
import scala.Tuple14;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005Mb\u0001C\t\u0013!\u0003\r\t\u0001\u0006\f\t\u000b\u0011\u0004A\u0011A3\t\u000b%\u0004a1\u00016\t\u000b1\u0004a1A7\t\u000b=\u0004a1\u00019\t\u000bI\u0004a1A:\t\u000bU\u0004a1\u0001<\t\u000ba\u0004a1A=\t\u000bm\u0004a1\u0001?\t\u000by\u0004a1A@\t\u000f\u0005\r\u0001Ab\u0001\u0002\u0006!9\u0011\u0011\u0002\u0001\u0007\u0004\u0005-\u0001bBA\b\u0001\u0019\r\u0011\u0011\u0003\u0005\b\u0003+\u0001a1AA\f\u0011\u001d\tY\u0002\u0001D\u0002\u0003;Aq!!\t\u0001\r\u0007\t\u0019\u0003C\u0004\u0002(\u0001!\t!!\u000b\u0003%M+W.[4s_V\u0004\bK]8ek\u000e$\u0018\u0007\u000e\u0006\u0003'Q\t1a\u001d;e\u0015\u0005)\u0012!B:qSJ,WcD\f5}\u0005#uIS'Q'ZKFl\u00182\u0014\u0007\u0001Ab\u0004\u0005\u0002\u001a95\t!DC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\ti\"D\u0001\u0004B]f\u0014VM\u001a\t\u0004?1zcB\u0001\u0011*\u001d\t\tsE\u0004\u0002#M5\t1E\u0003\u0002%K\u00051AH]8piz\u001a\u0001!C\u0001\u0016\u0013\tAC#A\u0004bY\u001e,'M]1\n\u0005)Z\u0013a\u00029bG.\fw-\u001a\u0006\u0003QQI!!\f\u0018\u0003\u0013M+W.[4s_V\u0004(B\u0001\u0016,!AI\u0002GM\u001fA\u0007\u001aKEj\u0014*V1ns\u0016-\u0003\u000225\t9A+\u001e9mKF\"\u0004CA\u001a5\u0019\u0001!Q!\u000e\u0001C\u0002Y\u0012\u0011!Q\t\u0003oi\u0002\"!\u0007\u001d\n\u0005eR\"a\u0002(pi\"Lgn\u001a\t\u00033mJ!\u0001\u0010\u000e\u0003\u0007\u0005s\u0017\u0010\u0005\u00024}\u0011)q\b\u0001b\u0001m\t\t!\t\u0005\u00024\u0003\u0012)!\t\u0001b\u0001m\t\t1\t\u0005\u00024\t\u0012)Q\t\u0001b\u0001m\t\tA\t\u0005\u00024\u000f\u0012)\u0001\n\u0001b\u0001m\t\tQ\t\u0005\u00024\u0015\u0012)1\n\u0001b\u0001m\t\ta\t\u0005\u00024\u001b\u0012)a\n\u0001b\u0001m\t\tq\t\u0005\u00024!\u0012)\u0011\u000b\u0001b\u0001m\t\t\u0001\n\u0005\u00024'\u0012)A\u000b\u0001b\u0001m\t\t\u0011\n\u0005\u00024-\u0012)q\u000b\u0001b\u0001m\t\t!\n\u0005\u000243\u0012)!\f\u0001b\u0001m\t\t1\n\u0005\u000249\u0012)Q\f\u0001b\u0001m\t\tA\n\u0005\u00024?\u0012)\u0001\r\u0001b\u0001m\t\tQ\n\u0005\u00024E\u0012)1\r\u0001b\u0001m\t\ta*\u0001\u0004%S:LG\u000f\n\u000b\u0002MB\u0011\u0011dZ\u0005\u0003Qj\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0003-\u00042a\b\u00173\u0003)\u0019HO];diV\u0014XMM\u000b\u0002]B\u0019q\u0004L\u001f\u0002\u0015M$(/^2ukJ,7'F\u0001r!\ryB\u0006Q\u0001\u000bgR\u0014Xo\u0019;ve\u0016$T#\u0001;\u0011\u0007}a3)\u0001\u0006tiJ,8\r^;sKV*\u0012a\u001e\t\u0004?12\u0015AC:ueV\u001cG/\u001e:fmU\t!\u0010E\u0002 Y%\u000b!b\u001d;sk\u000e$XO]38+\u0005i\bcA\u0010-\u0019\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0005\u0005\u0005\u0001cA\u0010-\u001f\u0006Q1\u000f\u001e:vGR,(/Z\u001d\u0016\u0005\u0005\u001d\u0001cA\u0010-%\u0006Y1\u000f\u001e:vGR,(/Z\u00191+\t\ti\u0001E\u0002 YU\u000b1b\u001d;sk\u000e$XO]32cU\u0011\u00111\u0003\t\u0004?1B\u0016aC:ueV\u001cG/\u001e:fcI*\"!!\u0007\u0011\u0007}a3,A\u0006tiJ,8\r^;sKF\u001aTCAA\u0010!\ryBFX\u0001\fgR\u0014Xo\u0019;ve\u0016\fD'\u0006\u0002\u0002&A\u0019q\u0004L1\u0002\u000f\r|WNY5oKR)q&a\u000b\u00020!1\u0011Q\u0006\tA\u0002=\n!\u0001\u001f\u0019\t\r\u0005E\u0002\u00031\u00010\u0003\tA\u0018\u0007"
)
public interface SemigroupProduct14 extends Semigroup {
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

   // $FF: synthetic method
   static Tuple14 combine$(final SemigroupProduct14 $this, final Tuple14 x0, final Tuple14 x1) {
      return $this.combine(x0, x1);
   }

   default Tuple14 combine(final Tuple14 x0, final Tuple14 x1) {
      return new Tuple14(this.structure1().combine(x0._1(), x1._1()), this.structure2().combine(x0._2(), x1._2()), this.structure3().combine(x0._3(), x1._3()), this.structure4().combine(x0._4(), x1._4()), this.structure5().combine(x0._5(), x1._5()), this.structure6().combine(x0._6(), x1._6()), this.structure7().combine(x0._7(), x1._7()), this.structure8().combine(x0._8(), x1._8()), this.structure9().combine(x0._9(), x1._9()), this.structure10().combine(x0._10(), x1._10()), this.structure11().combine(x0._11(), x1._11()), this.structure12().combine(x0._12(), x1._12()), this.structure13().combine(x0._13(), x1._13()), this.structure14().combine(x0._14(), x1._14()));
   }

   static void $init$(final SemigroupProduct14 $this) {
   }
}
