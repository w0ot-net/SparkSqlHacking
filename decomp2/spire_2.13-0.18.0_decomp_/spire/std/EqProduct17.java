package spire.std;

import cats.kernel.Eq;
import scala.Tuple17;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\rd\u0001\u0003\u000b\u0016!\u0003\r\taF\r\t\u000bA\u0004A\u0011A9\t\u000bU\u0004a1\u0001<\t\u000ba\u0004a1A=\t\u000bm\u0004a1\u0001?\t\u000by\u0004a1A@\t\u000f\u0005\r\u0001Ab\u0001\u0002\u0006!9\u0011\u0011\u0002\u0001\u0007\u0004\u0005-\u0001bBA\b\u0001\u0019\r\u0011\u0011\u0003\u0005\b\u0003+\u0001a1AA\f\u0011\u001d\tY\u0002\u0001D\u0002\u0003;Aq!!\t\u0001\r\u0007\t\u0019\u0003C\u0004\u0002(\u00011\u0019!!\u000b\t\u000f\u00055\u0002Ab\u0001\u00020!9\u00111\u0007\u0001\u0007\u0004\u0005U\u0002bBA\u001d\u0001\u0019\r\u00111\b\u0005\b\u0003\u007f\u0001a1AA!\u0011\u001d\t)\u0005\u0001D\u0002\u0003\u000fBq!a\u0013\u0001\r\u0007\ti\u0005C\u0004\u0002R\u0001!\t!a\u0015\u0003\u0017\u0015\u000b\bK]8ek\u000e$\u0018g\u000e\u0006\u0003-]\t1a\u001d;e\u0015\u0005A\u0012!B:qSJ,WC\u0005\u000e8\u0003\u0012;%*\u0014)T-fcvLY3iW:\u001c2\u0001A\u000e\"!\tar$D\u0001\u001e\u0015\u0005q\u0012!B:dC2\f\u0017B\u0001\u0011\u001e\u0005\u0019\te.\u001f*fMB\u0019!e\f\u001a\u000f\u0005\rbcB\u0001\u0013+\u001d\t)\u0013&D\u0001'\u0015\t9\u0003&\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005A\u0012BA\u0016\u0018\u0003\u001d\tGnZ3ce\u0006L!!\f\u0018\u0002\u000fA\f7m[1hK*\u00111fF\u0005\u0003aE\u0012!!R9\u000b\u00055r\u0003c\u0005\u000f4k\u0001\u001be)\u0013'P%VC6LX1eO*l\u0017B\u0001\u001b\u001e\u0005\u001d!V\u000f\u001d7fc]\u0002\"AN\u001c\r\u0001\u0011)\u0001\b\u0001b\u0001s\t\t\u0011)\u0005\u0002;{A\u0011AdO\u0005\u0003yu\u0011qAT8uQ&tw\r\u0005\u0002\u001d}%\u0011q(\b\u0002\u0004\u0003:L\bC\u0001\u001cB\t\u0015\u0011\u0005A1\u0001:\u0005\u0005\u0011\u0005C\u0001\u001cE\t\u0015)\u0005A1\u0001:\u0005\u0005\u0019\u0005C\u0001\u001cH\t\u0015A\u0005A1\u0001:\u0005\u0005!\u0005C\u0001\u001cK\t\u0015Y\u0005A1\u0001:\u0005\u0005)\u0005C\u0001\u001cN\t\u0015q\u0005A1\u0001:\u0005\u00051\u0005C\u0001\u001cQ\t\u0015\t\u0006A1\u0001:\u0005\u00059\u0005C\u0001\u001cT\t\u0015!\u0006A1\u0001:\u0005\u0005A\u0005C\u0001\u001cW\t\u00159\u0006A1\u0001:\u0005\u0005I\u0005C\u0001\u001cZ\t\u0015Q\u0006A1\u0001:\u0005\u0005Q\u0005C\u0001\u001c]\t\u0015i\u0006A1\u0001:\u0005\u0005Y\u0005C\u0001\u001c`\t\u0015\u0001\u0007A1\u0001:\u0005\u0005a\u0005C\u0001\u001cc\t\u0015\u0019\u0007A1\u0001:\u0005\u0005i\u0005C\u0001\u001cf\t\u00151\u0007A1\u0001:\u0005\u0005q\u0005C\u0001\u001ci\t\u0015I\u0007A1\u0001:\u0005\u0005y\u0005C\u0001\u001cl\t\u0015a\u0007A1\u0001:\u0005\u0005\u0001\u0006C\u0001\u001co\t\u0015y\u0007A1\u0001:\u0005\u0005\t\u0016A\u0002\u0013j]&$H\u0005F\u0001s!\ta2/\u0003\u0002u;\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0002oB\u0019!eL\u001b\u0002\u0015M$(/^2ukJ,''F\u0001{!\r\u0011s\u0006Q\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cT#A?\u0011\u0007\tz3)\u0001\u0006tiJ,8\r^;sKR*\"!!\u0001\u0011\u0007\tzc)\u0001\u0006tiJ,8\r^;sKV*\"!a\u0002\u0011\u0007\tz\u0013*\u0001\u0006tiJ,8\r^;sKZ*\"!!\u0004\u0011\u0007\tzC*\u0001\u0006tiJ,8\r^;sK^*\"!a\u0005\u0011\u0007\tzs*\u0001\u0006tiJ,8\r^;sKb*\"!!\u0007\u0011\u0007\tz#+\u0001\u0006tiJ,8\r^;sKf*\"!a\b\u0011\u0007\tzS+A\u0006tiJ,8\r^;sKF\u0002TCAA\u0013!\r\u0011s\u0006W\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0014'\u0006\u0002\u0002,A\u0019!eL.\u0002\u0017M$(/^2ukJ,\u0017GM\u000b\u0003\u0003c\u00012AI\u0018_\u0003-\u0019HO];diV\u0014X-M\u001a\u0016\u0005\u0005]\u0002c\u0001\u00120C\u0006Y1\u000f\u001e:vGR,(/Z\u00195+\t\ti\u0004E\u0002#_\u0011\f1b\u001d;sk\u000e$XO]32kU\u0011\u00111\t\t\u0004E=:\u0017aC:ueV\u001cG/\u001e:fcY*\"!!\u0013\u0011\u0007\tz#.A\u0006tiJ,8\r^;sKF:TCAA(!\r\u0011s&\\\u0001\u0004KF4HCBA+\u00037\ny\u0006E\u0002\u001d\u0003/J1!!\u0017\u001e\u0005\u001d\u0011un\u001c7fC:Da!!\u0018\u0014\u0001\u0004\u0011\u0014A\u0001=1\u0011\u0019\t\tg\u0005a\u0001e\u0005\u0011\u00010\r"
)
public interface EqProduct17 extends Eq {
   Eq structure1();

   Eq structure2();

   Eq structure3();

   Eq structure4();

   Eq structure5();

   Eq structure6();

   Eq structure7();

   Eq structure8();

   Eq structure9();

   Eq structure10();

   Eq structure11();

   Eq structure12();

   Eq structure13();

   Eq structure14();

   Eq structure15();

   Eq structure16();

   Eq structure17();

   // $FF: synthetic method
   static boolean eqv$(final EqProduct17 $this, final Tuple17 x0, final Tuple17 x1) {
      return $this.eqv(x0, x1);
   }

   default boolean eqv(final Tuple17 x0, final Tuple17 x1) {
      return this.structure1().eqv(x0._1(), x1._1()) && this.structure2().eqv(x0._2(), x1._2()) && this.structure3().eqv(x0._3(), x1._3()) && this.structure4().eqv(x0._4(), x1._4()) && this.structure5().eqv(x0._5(), x1._5()) && this.structure6().eqv(x0._6(), x1._6()) && this.structure7().eqv(x0._7(), x1._7()) && this.structure8().eqv(x0._8(), x1._8()) && this.structure9().eqv(x0._9(), x1._9()) && this.structure10().eqv(x0._10(), x1._10()) && this.structure11().eqv(x0._11(), x1._11()) && this.structure12().eqv(x0._12(), x1._12()) && this.structure13().eqv(x0._13(), x1._13()) && this.structure14().eqv(x0._14(), x1._14()) && this.structure15().eqv(x0._15(), x1._15()) && this.structure16().eqv(x0._16(), x1._16()) && this.structure17().eqv(x0._17(), x1._17());
   }

   static void $init$(final EqProduct17 $this) {
   }
}
