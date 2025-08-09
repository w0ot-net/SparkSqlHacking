package spire.std;

import cats.kernel.Eq;
import scala.Tuple9;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e4\u0001\u0002D\u0007\u0011\u0002\u0007\u0005q\"\u0005\u0005\u0006!\u0002!\t!\u0015\u0005\u0006+\u00021\u0019A\u0016\u0005\u00061\u00021\u0019!\u0017\u0005\u00067\u00021\u0019\u0001\u0018\u0005\u0006=\u00021\u0019a\u0018\u0005\u0006C\u00021\u0019A\u0019\u0005\u0006I\u00021\u0019!\u001a\u0005\u0006O\u00021\u0019\u0001\u001b\u0005\u0006U\u00021\u0019a\u001b\u0005\u0006[\u00021\u0019A\u001c\u0005\u0006a\u0002!\t!\u001d\u0002\u000b\u000bF\u0004&o\u001c3vGRL$B\u0001\b\u0010\u0003\r\u0019H\u000f\u001a\u0006\u0002!\u0005)1\u000f]5sKVQ!cL\u001d=\u007f\t+\u0005j\u0013(\u0014\u0007\u0001\u0019\u0012\u0004\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VM\u001a\t\u00045\u001dRcBA\u000e%\u001d\ta\"E\u0004\u0002\u001eC5\taD\u0003\u0002 A\u00051AH]8piz\u001a\u0001!C\u0001\u0011\u0013\t\u0019s\"A\u0004bY\u001e,'M]1\n\u0005\u00152\u0013a\u00029bG.\fw-\u001a\u0006\u0003G=I!\u0001K\u0015\u0003\u0005\u0015\u000b(BA\u0013'!-!2&\f\u001d<}\u0005#uIS'\n\u00051*\"A\u0002+va2,\u0017\b\u0005\u0002/_1\u0001A!\u0002\u0019\u0001\u0005\u0004\t$!A!\u0012\u0005I*\u0004C\u0001\u000b4\u0013\t!TCA\u0004O_RD\u0017N\\4\u0011\u0005Q1\u0014BA\u001c\u0016\u0005\r\te.\u001f\t\u0003]e\"QA\u000f\u0001C\u0002E\u0012\u0011A\u0011\t\u0003]q\"Q!\u0010\u0001C\u0002E\u0012\u0011a\u0011\t\u0003]}\"Q\u0001\u0011\u0001C\u0002E\u0012\u0011\u0001\u0012\t\u0003]\t#Qa\u0011\u0001C\u0002E\u0012\u0011!\u0012\t\u0003]\u0015#QA\u0012\u0001C\u0002E\u0012\u0011A\u0012\t\u0003]!#Q!\u0013\u0001C\u0002E\u0012\u0011a\u0012\t\u0003]-#Q\u0001\u0014\u0001C\u0002E\u0012\u0011\u0001\u0013\t\u0003]9#Qa\u0014\u0001C\u0002E\u0012\u0011!S\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003I\u0003\"\u0001F*\n\u0005Q+\"\u0001B+oSR\f!b\u001d;sk\u000e$XO]32+\u00059\u0006c\u0001\u000e([\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0003i\u00032AG\u00149\u0003)\u0019HO];diV\u0014XmM\u000b\u0002;B\u0019!dJ\u001e\u0002\u0015M$(/^2ukJ,G'F\u0001a!\rQrEP\u0001\u000bgR\u0014Xo\u0019;ve\u0016,T#A2\u0011\u0007i9\u0013)\u0001\u0006tiJ,8\r^;sKZ*\u0012A\u001a\t\u00045\u001d\"\u0015AC:ueV\u001cG/\u001e:foU\t\u0011\u000eE\u0002\u001bO\u001d\u000b!b\u001d;sk\u000e$XO]39+\u0005a\u0007c\u0001\u000e(\u0015\u0006Q1\u000f\u001e:vGR,(/Z\u001d\u0016\u0003=\u00042AG\u0014N\u0003\r)\u0017O\u001e\u000b\u0004eV<\bC\u0001\u000bt\u0013\t!XCA\u0004C_>dW-\u00198\t\u000bY\\\u0001\u0019\u0001\u0016\u0002\u0005a\u0004\u0004\"\u0002=\f\u0001\u0004Q\u0013A\u0001=2\u0001"
)
public interface EqProduct9 extends Eq {
   Eq structure1();

   Eq structure2();

   Eq structure3();

   Eq structure4();

   Eq structure5();

   Eq structure6();

   Eq structure7();

   Eq structure8();

   Eq structure9();

   // $FF: synthetic method
   static boolean eqv$(final EqProduct9 $this, final Tuple9 x0, final Tuple9 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple9 x0, final Tuple9 x1) {
      return this.structure1().eqv(x0._1(), x1._1()) && this.structure2().eqv(x0._2(), x1._2()) && this.structure3().eqv(x0._3(), x1._3()) && this.structure4().eqv(x0._4(), x1._4()) && this.structure5().eqv(x0._5(), x1._5()) && this.structure6().eqv(x0._6(), x1._6()) && this.structure7().eqv(x0._7(), x1._7()) && this.structure8().eqv(x0._8(), x1._8()) && this.structure9().eqv(x0._9(), x1._9());
   }

   static void $init$(final EqProduct9 $this) {
   }
}
