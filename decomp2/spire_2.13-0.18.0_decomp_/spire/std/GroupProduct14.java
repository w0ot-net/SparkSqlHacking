package spire.std;

import cats.kernel.Group;
import scala.Tuple14;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005]b\u0001C\t\u0013!\u0003\r\t\u0001\u0006\f\t\u000b!\u0004A\u0011A5\t\u000b5\u0004a1\u00018\t\u000bA\u0004a1A9\t\u000bM\u0004a1\u0001;\t\u000bY\u0004a1A<\t\u000be\u0004a1\u0001>\t\u000bq\u0004a1A?\t\r}\u0004a1AA\u0001\u0011\u001d\t)\u0001\u0001D\u0002\u0003\u000fAq!a\u0003\u0001\r\u0007\ti\u0001C\u0004\u0002\u0012\u00011\u0019!a\u0005\t\u000f\u0005]\u0001Ab\u0001\u0002\u001a!9\u0011Q\u0004\u0001\u0007\u0004\u0005}\u0001bBA\u0012\u0001\u0019\r\u0011Q\u0005\u0005\b\u0003S\u0001a1AA\u0016\u0011\u001d\ty\u0003\u0001C\u0001\u0003c\u0011ab\u0012:pkB\u0004&o\u001c3vGR\fDG\u0003\u0002\u0014)\u0005\u00191\u000f\u001e3\u000b\u0003U\tQa\u001d9je\u0016,rb\u0006\u001b?\u0003\u0012;%*\u0014)T-fcvLY\n\u0005\u0001aqB\r\u0005\u0002\u001a95\t!DC\u0001\u001c\u0003\u0015\u00198-\u00197b\u0013\ti\"D\u0001\u0004B]f\u0014VM\u001a\t\u0004?1zcB\u0001\u0011*\u001d\t\tsE\u0004\u0002#M5\t1E\u0003\u0002%K\u00051AH]8piz\u001a\u0001!C\u0001\u0016\u0013\tAC#A\u0004bY\u001e,'M]1\n\u0005)Z\u0013a\u00029bG.\fw-\u001a\u0006\u0003QQI!!\f\u0018\u0003\u000b\u001d\u0013x.\u001e9\u000b\u0005)Z\u0003\u0003E\r1eu\u00025IR%M\u001fJ+\u0006l\u00170b\u0013\t\t$DA\u0004UkBdW-\r\u001b\u0011\u0005M\"D\u0002\u0001\u0003\u0006k\u0001\u0011\rA\u000e\u0002\u0002\u0003F\u0011qG\u000f\t\u00033aJ!!\u000f\u000e\u0003\u000f9{G\u000f[5oOB\u0011\u0011dO\u0005\u0003yi\u00111!\u00118z!\t\u0019d\bB\u0003@\u0001\t\u0007aGA\u0001C!\t\u0019\u0014\tB\u0003C\u0001\t\u0007aGA\u0001D!\t\u0019D\tB\u0003F\u0001\t\u0007aGA\u0001E!\t\u0019t\tB\u0003I\u0001\t\u0007aGA\u0001F!\t\u0019$\nB\u0003L\u0001\t\u0007aGA\u0001G!\t\u0019T\nB\u0003O\u0001\t\u0007aGA\u0001H!\t\u0019\u0004\u000bB\u0003R\u0001\t\u0007aGA\u0001I!\t\u00194\u000bB\u0003U\u0001\t\u0007aGA\u0001J!\t\u0019d\u000bB\u0003X\u0001\t\u0007aGA\u0001K!\t\u0019\u0014\fB\u0003[\u0001\t\u0007aGA\u0001L!\t\u0019D\fB\u0003^\u0001\t\u0007aGA\u0001M!\t\u0019t\fB\u0003a\u0001\t\u0007aGA\u0001N!\t\u0019$\rB\u0003d\u0001\t\u0007aGA\u0001O!A)gMM\u001fA\u0007\u001aKEj\u0014*V1ns\u0016-D\u0001\u0013\u0013\t9'CA\bN_:|\u0017\u000e\u001a)s_\u0012,8\r^\u00195\u0003\u0019!\u0013N\\5uIQ\t!\u000e\u0005\u0002\u001aW&\u0011AN\u0007\u0002\u0005+:LG/\u0001\u0006tiJ,8\r^;sKF*\u0012a\u001c\t\u0004?1\u0012\u0014AC:ueV\u001cG/\u001e:feU\t!\u000fE\u0002 Yu\n!b\u001d;sk\u000e$XO]34+\u0005)\bcA\u0010-\u0001\u0006Q1\u000f\u001e:vGR,(/\u001a\u001b\u0016\u0003a\u00042a\b\u0017D\u0003)\u0019HO];diV\u0014X-N\u000b\u0002wB\u0019q\u0004\f$\u0002\u0015M$(/^2ukJ,g'F\u0001\u007f!\ryB&S\u0001\u000bgR\u0014Xo\u0019;ve\u0016<TCAA\u0002!\ryB\u0006T\u0001\u000bgR\u0014Xo\u0019;ve\u0016DTCAA\u0005!\ryBfT\u0001\u000bgR\u0014Xo\u0019;ve\u0016LTCAA\b!\ryBFU\u0001\fgR\u0014Xo\u0019;ve\u0016\f\u0004'\u0006\u0002\u0002\u0016A\u0019q\u0004L+\u0002\u0017M$(/^2ukJ,\u0017'M\u000b\u0003\u00037\u00012a\b\u0017Y\u0003-\u0019HO];diV\u0014X-\r\u001a\u0016\u0005\u0005\u0005\u0002cA\u0010-7\u0006Y1\u000f\u001e:vGR,(/Z\u00194+\t\t9\u0003E\u0002 Yy\u000b1b\u001d;sk\u000e$XO]32iU\u0011\u0011Q\u0006\t\u0004?1\n\u0017aB5om\u0016\u00148/\u001a\u000b\u0004_\u0005M\u0002BBA\u001b!\u0001\u0007q&\u0001\u0002ya\u0001"
)
public interface GroupProduct14 extends Group, MonoidProduct14 {
   Group structure1();

   Group structure2();

   Group structure3();

   Group structure4();

   Group structure5();

   Group structure6();

   Group structure7();

   Group structure8();

   Group structure9();

   Group structure10();

   Group structure11();

   Group structure12();

   Group structure13();

   Group structure14();

   // $FF: synthetic method
   static Tuple14 inverse$(final GroupProduct14 $this, final Tuple14 x0) {
      return $this.inverse(x0);
   }

   default Tuple14 inverse(final Tuple14 x0) {
      return new Tuple14(this.structure1().inverse(x0._1()), this.structure2().inverse(x0._2()), this.structure3().inverse(x0._3()), this.structure4().inverse(x0._4()), this.structure5().inverse(x0._5()), this.structure6().inverse(x0._6()), this.structure7().inverse(x0._7()), this.structure8().inverse(x0._8()), this.structure9().inverse(x0._9()), this.structure10().inverse(x0._10()), this.structure11().inverse(x0._11()), this.structure12().inverse(x0._12()), this.structure13().inverse(x0._13()), this.structure14().inverse(x0._14()));
   }

   static void $init$(final GroupProduct14 $this) {
   }
}
