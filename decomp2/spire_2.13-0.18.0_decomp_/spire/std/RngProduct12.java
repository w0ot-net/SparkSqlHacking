package spire.std;

import algebra.ring.Rng;
import scala.Tuple12;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ma\u0001C\b\u0011!\u0003\r\tA\u0005\u000b\t\u000b\u0001\u0004A\u0011A1\t\u000b\u0015\u0004a1\u00014\t\u000b!\u0004a1A5\t\u000b-\u0004a1\u00017\t\u000b9\u0004a1A8\t\u000bE\u0004a1\u0001:\t\u000bQ\u0004a1A;\t\u000b]\u0004a1\u0001=\t\u000bi\u0004a1A>\t\u000bu\u0004a1\u0001@\t\u000f\u0005\u0005\u0001Ab\u0001\u0002\u0004!9\u0011q\u0001\u0001\u0007\u0004\u0005%\u0001bBA\u0007\u0001\u0019\r\u0011q\u0002\u0005\b\u0003'\u0001A\u0011AA\u000b\u00051\u0011fn\u001a)s_\u0012,8\r^\u00193\u0015\t\t\"#A\u0002ti\u0012T\u0011aE\u0001\u0006gBL'/Z\u000b\u000e+IbtHQ#I\u0017:\u000bFk\u0016.\u0014\t\u00011B\u0004\u0018\t\u0003/ii\u0011\u0001\u0007\u0006\u00023\u0005)1oY1mC&\u00111\u0004\u0007\u0002\u0007\u0003:L(+\u001a4\u0011\u0007uQSF\u0004\u0002\u001fO9\u0011q$\n\b\u0003A\u0011j\u0011!\t\u0006\u0003E\r\na\u0001\u0010:p_Rt4\u0001A\u0005\u0002'%\u0011aEE\u0001\bC2<WM\u0019:b\u0013\tA\u0013&A\u0004qC\u000e\\\u0017mZ3\u000b\u0005\u0019\u0012\u0012BA\u0016-\u0005\r\u0011fn\u001a\u0006\u0003Q%\u0002bb\u0006\u00181wy\nEi\u0012&N!N3\u0016,\u0003\u000201\t9A+\u001e9mKF\u0012\u0004CA\u00193\u0019\u0001!Qa\r\u0001C\u0002Q\u0012\u0011!Q\t\u0003ka\u0002\"a\u0006\u001c\n\u0005]B\"a\u0002(pi\"Lgn\u001a\t\u0003/eJ!A\u000f\r\u0003\u0007\u0005s\u0017\u0010\u0005\u00022y\u0011)Q\b\u0001b\u0001i\t\t!\t\u0005\u00022\u007f\u0011)\u0001\t\u0001b\u0001i\t\t1\t\u0005\u00022\u0005\u0012)1\t\u0001b\u0001i\t\tA\t\u0005\u00022\u000b\u0012)a\t\u0001b\u0001i\t\tQ\t\u0005\u00022\u0011\u0012)\u0011\n\u0001b\u0001i\t\ta\t\u0005\u00022\u0017\u0012)A\n\u0001b\u0001i\t\tq\t\u0005\u00022\u001d\u0012)q\n\u0001b\u0001i\t\t\u0001\n\u0005\u00022#\u0012)!\u000b\u0001b\u0001i\t\t\u0011\n\u0005\u00022)\u0012)Q\u000b\u0001b\u0001i\t\t!\n\u0005\u00022/\u0012)\u0001\f\u0001b\u0001i\t\t1\n\u0005\u000225\u0012)1\f\u0001b\u0001i\t\tA\n\u0005\b^=BZd(\u0011#H\u00156\u00036KV-\u000e\u0003AI!a\u0018\t\u0003#M+W.\u001b:j]\u001e\u0004&o\u001c3vGR\f$'\u0001\u0004%S:LG\u000f\n\u000b\u0002EB\u0011qcY\u0005\u0003Ib\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0003\u001d\u00042!\b\u00161\u0003)\u0019HO];diV\u0014XMM\u000b\u0002UB\u0019QDK\u001e\u0002\u0015M$(/^2ukJ,7'F\u0001n!\ri\"FP\u0001\u000bgR\u0014Xo\u0019;ve\u0016$T#\u00019\u0011\u0007uQ\u0013)\u0001\u0006tiJ,8\r^;sKV*\u0012a\u001d\t\u0004;)\"\u0015AC:ueV\u001cG/\u001e:fmU\ta\u000fE\u0002\u001eU\u001d\u000b!b\u001d;sk\u000e$XO]38+\u0005I\bcA\u000f+\u0015\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0003q\u00042!\b\u0016N\u0003)\u0019HO];diV\u0014X-O\u000b\u0002\u007fB\u0019QD\u000b)\u0002\u0017M$(/^2ukJ,\u0017\u0007M\u000b\u0003\u0003\u000b\u00012!\b\u0016T\u0003-\u0019HO];diV\u0014X-M\u0019\u0016\u0005\u0005-\u0001cA\u000f+-\u0006Y1\u000f\u001e:vGR,(/Z\u00193+\t\t\t\u0002E\u0002\u001eUe\u000baA\\3hCR,GcA\u0017\u0002\u0018!1\u0011\u0011\u0004\bA\u00025\n!\u0001\u001f\u0019"
)
public interface RngProduct12 extends Rng, SemiringProduct12 {
   Rng structure1();

   Rng structure2();

   Rng structure3();

   Rng structure4();

   Rng structure5();

   Rng structure6();

   Rng structure7();

   Rng structure8();

   Rng structure9();

   Rng structure10();

   Rng structure11();

   Rng structure12();

   // $FF: synthetic method
   static Tuple12 negate$(final RngProduct12 $this, final Tuple12 x0) {
      return $this.negate(x0);
   }

   default Tuple12 negate(final Tuple12 x0) {
      return new Tuple12(this.structure1().negate(x0._1()), this.structure2().negate(x0._2()), this.structure3().negate(x0._3()), this.structure4().negate(x0._4()), this.structure5().negate(x0._5()), this.structure6().negate(x0._6()), this.structure7().negate(x0._7()), this.structure8().negate(x0._8()), this.structure9().negate(x0._9()), this.structure10().negate(x0._10()), this.structure11().negate(x0._11()), this.structure12().negate(x0._12()));
   }

   static void $init$(final RngProduct12 $this) {
   }
}
