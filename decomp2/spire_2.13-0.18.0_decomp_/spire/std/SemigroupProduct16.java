package spire.std;

import cats.kernel.Semigroup;
import scala.Tuple16;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005=c\u0001C\n\u0015!\u0003\r\tA\u0006\r\t\u000b1\u0004A\u0011A7\t\u000bE\u0004a1\u0001:\t\u000bQ\u0004a1A;\t\u000b]\u0004a1\u0001=\t\u000bi\u0004a1A>\t\u000bu\u0004a1\u0001@\t\u000f\u0005\u0005\u0001Ab\u0001\u0002\u0004!9\u0011q\u0001\u0001\u0007\u0004\u0005%\u0001bBA\u0007\u0001\u0019\r\u0011q\u0002\u0005\b\u0003'\u0001a1AA\u000b\u0011\u001d\tI\u0002\u0001D\u0002\u00037Aq!a\b\u0001\r\u0007\t\t\u0003C\u0004\u0002&\u00011\u0019!a\n\t\u000f\u0005-\u0002Ab\u0001\u0002.!9\u0011\u0011\u0007\u0001\u0007\u0004\u0005M\u0002bBA\u001c\u0001\u0019\r\u0011\u0011\b\u0005\b\u0003{\u0001a1AA \u0011\u001d\t\u0019\u0005\u0001C\u0001\u0003\u000b\u0012!cU3nS\u001e\u0014x.\u001e9Qe>$Wo\u0019;2m)\u0011QCF\u0001\u0004gR$'\"A\f\u0002\u000bM\u0004\u0018N]3\u0016#e1\u0004i\u0011$J\u0019>\u0013V\u000bW._C\u0012<'nE\u0002\u00015\u0001\u0002\"a\u0007\u0010\u000e\u0003qQ\u0011!H\u0001\u0006g\u000e\fG.Y\u0005\u0003?q\u0011a!\u00118z%\u00164\u0007cA\u0011/c9\u0011!e\u000b\b\u0003G%r!\u0001\n\u0015\u000e\u0003\u0015R!AJ\u0014\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011aF\u0005\u0003UY\tq!\u00197hK\n\u0014\u0018-\u0003\u0002-[\u00059\u0001/Y2lC\u001e,'B\u0001\u0016\u0017\u0013\ty\u0003GA\u0005TK6LwM]8va*\u0011A&\f\t\u00137I\"tHQ#I\u0017:\u000bFk\u0016.^A\u000e4\u0017.\u0003\u000249\t9A+\u001e9mKF2\u0004CA\u001b7\u0019\u0001!Qa\u000e\u0001C\u0002a\u0012\u0011!Q\t\u0003sq\u0002\"a\u0007\u001e\n\u0005mb\"a\u0002(pi\"Lgn\u001a\t\u00037uJ!A\u0010\u000f\u0003\u0007\u0005s\u0017\u0010\u0005\u00026\u0001\u0012)\u0011\t\u0001b\u0001q\t\t!\t\u0005\u00026\u0007\u0012)A\t\u0001b\u0001q\t\t1\t\u0005\u00026\r\u0012)q\t\u0001b\u0001q\t\tA\t\u0005\u00026\u0013\u0012)!\n\u0001b\u0001q\t\tQ\t\u0005\u00026\u0019\u0012)Q\n\u0001b\u0001q\t\ta\t\u0005\u00026\u001f\u0012)\u0001\u000b\u0001b\u0001q\t\tq\t\u0005\u00026%\u0012)1\u000b\u0001b\u0001q\t\t\u0001\n\u0005\u00026+\u0012)a\u000b\u0001b\u0001q\t\t\u0011\n\u0005\u000261\u0012)\u0011\f\u0001b\u0001q\t\t!\n\u0005\u000267\u0012)A\f\u0001b\u0001q\t\t1\n\u0005\u00026=\u0012)q\f\u0001b\u0001q\t\tA\n\u0005\u00026C\u0012)!\r\u0001b\u0001q\t\tQ\n\u0005\u00026I\u0012)Q\r\u0001b\u0001q\t\ta\n\u0005\u00026O\u0012)\u0001\u000e\u0001b\u0001q\t\tq\n\u0005\u00026U\u0012)1\u000e\u0001b\u0001q\t\t\u0001+\u0001\u0004%S:LG\u000f\n\u000b\u0002]B\u00111d\\\u0005\u0003ar\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0003M\u00042!\t\u00185\u0003)\u0019HO];diV\u0014XMM\u000b\u0002mB\u0019\u0011EL \u0002\u0015M$(/^2ukJ,7'F\u0001z!\r\tcFQ\u0001\u000bgR\u0014Xo\u0019;ve\u0016$T#\u0001?\u0011\u0007\u0005rS)\u0001\u0006tiJ,8\r^;sKV*\u0012a \t\u0004C9B\u0015AC:ueV\u001cG/\u001e:fmU\u0011\u0011Q\u0001\t\u0004C9Z\u0015AC:ueV\u001cG/\u001e:foU\u0011\u00111\u0002\t\u0004C9r\u0015AC:ueV\u001cG/\u001e:fqU\u0011\u0011\u0011\u0003\t\u0004C9\n\u0016AC:ueV\u001cG/\u001e:fsU\u0011\u0011q\u0003\t\u0004C9\"\u0016aC:ueV\u001cG/\u001e:fcA*\"!!\b\u0011\u0007\u0005rs+A\u0006tiJ,8\r^;sKF\nTCAA\u0012!\r\tcFW\u0001\fgR\u0014Xo\u0019;ve\u0016\f$'\u0006\u0002\u0002*A\u0019\u0011EL/\u0002\u0017M$(/^2ukJ,\u0017gM\u000b\u0003\u0003_\u00012!\t\u0018a\u0003-\u0019HO];diV\u0014X-\r\u001b\u0016\u0005\u0005U\u0002cA\u0011/G\u0006Y1\u000f\u001e:vGR,(/Z\u00196+\t\tY\u0004E\u0002\"]\u0019\f1b\u001d;sk\u000e$XO]32mU\u0011\u0011\u0011\t\t\u0004C9J\u0017aB2p[\nLg.\u001a\u000b\u0006c\u0005\u001d\u00131\n\u0005\u0007\u0003\u0013\u0012\u0002\u0019A\u0019\u0002\u0005a\u0004\u0004BBA'%\u0001\u0007\u0011'\u0001\u0002yc\u0001"
)
public interface SemigroupProduct16 extends Semigroup {
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

   // $FF: synthetic method
   static Tuple16 combine$(final SemigroupProduct16 $this, final Tuple16 x0, final Tuple16 x1) {
      return $this.combine(x0, x1);
   }

   default Tuple16 combine(final Tuple16 x0, final Tuple16 x1) {
      return new Tuple16(this.structure1().combine(x0._1(), x1._1()), this.structure2().combine(x0._2(), x1._2()), this.structure3().combine(x0._3(), x1._3()), this.structure4().combine(x0._4(), x1._4()), this.structure5().combine(x0._5(), x1._5()), this.structure6().combine(x0._6(), x1._6()), this.structure7().combine(x0._7(), x1._7()), this.structure8().combine(x0._8(), x1._8()), this.structure9().combine(x0._9(), x1._9()), this.structure10().combine(x0._10(), x1._10()), this.structure11().combine(x0._11(), x1._11()), this.structure12().combine(x0._12(), x1._12()), this.structure13().combine(x0._13(), x1._13()), this.structure14().combine(x0._14(), x1._14()), this.structure15().combine(x0._15(), x1._15()), this.structure16().combine(x0._16(), x1._16()));
   }

   static void $init$(final SemigroupProduct16 $this) {
   }
}
