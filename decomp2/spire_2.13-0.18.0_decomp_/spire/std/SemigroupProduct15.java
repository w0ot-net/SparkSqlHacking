package spire.std;

import cats.kernel.Semigroup;
import scala.Tuple15;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005\u0005c\u0001\u0003\n\u0014!\u0003\r\t!F\f\t\u000b!\u0004A\u0011A5\t\u000b5\u0004a1\u00018\t\u000bA\u0004a1A9\t\u000bM\u0004a1\u0001;\t\u000bY\u0004a1A<\t\u000be\u0004a1\u0001>\t\u000bq\u0004a1A?\t\r}\u0004a1AA\u0001\u0011\u001d\t)\u0001\u0001D\u0002\u0003\u000fAq!a\u0003\u0001\r\u0007\ti\u0001C\u0004\u0002\u0012\u00011\u0019!a\u0005\t\u000f\u0005]\u0001Ab\u0001\u0002\u001a!9\u0011Q\u0004\u0001\u0007\u0004\u0005}\u0001bBA\u0012\u0001\u0019\r\u0011Q\u0005\u0005\b\u0003S\u0001a1AA\u0016\u0011\u001d\ty\u0003\u0001D\u0002\u0003cAq!!\u000e\u0001\t\u0003\t9D\u0001\nTK6LwM]8vaB\u0013x\u000eZ;diF*$B\u0001\u000b\u0016\u0003\r\u0019H\u000f\u001a\u0006\u0002-\u0005)1\u000f]5sKV\u0001\u0002$N C\u000b\"[e*\u0015+X5v\u00037MZ\n\u0004\u0001ey\u0002C\u0001\u000e\u001e\u001b\u0005Y\"\"\u0001\u000f\u0002\u000bM\u001c\u0017\r\\1\n\u0005yY\"AB!osJ+g\rE\u0002![Ar!!\t\u0016\u000f\u0005\tBcBA\u0012(\u001b\u0005!#BA\u0013'\u0003\u0019a$o\\8u}\r\u0001\u0011\"\u0001\f\n\u0005%*\u0012aB1mO\u0016\u0014'/Y\u0005\u0003W1\nq\u0001]1dW\u0006<WM\u0003\u0002*+%\u0011af\f\u0002\n'\u0016l\u0017n\u001a:pkBT!a\u000b\u0017\u0011#i\t4GP!E\u000f*k\u0005k\u0015,Z9~\u0013W-\u0003\u000237\t9A+\u001e9mKF*\u0004C\u0001\u001b6\u0019\u0001!QA\u000e\u0001C\u0002]\u0012\u0011!Q\t\u0003qm\u0002\"AG\u001d\n\u0005iZ\"a\u0002(pi\"Lgn\u001a\t\u00035qJ!!P\u000e\u0003\u0007\u0005s\u0017\u0010\u0005\u00025\u007f\u0011)\u0001\t\u0001b\u0001o\t\t!\t\u0005\u00025\u0005\u0012)1\t\u0001b\u0001o\t\t1\t\u0005\u00025\u000b\u0012)a\t\u0001b\u0001o\t\tA\t\u0005\u00025\u0011\u0012)\u0011\n\u0001b\u0001o\t\tQ\t\u0005\u00025\u0017\u0012)A\n\u0001b\u0001o\t\ta\t\u0005\u00025\u001d\u0012)q\n\u0001b\u0001o\t\tq\t\u0005\u00025#\u0012)!\u000b\u0001b\u0001o\t\t\u0001\n\u0005\u00025)\u0012)Q\u000b\u0001b\u0001o\t\t\u0011\n\u0005\u00025/\u0012)\u0001\f\u0001b\u0001o\t\t!\n\u0005\u000255\u0012)1\f\u0001b\u0001o\t\t1\n\u0005\u00025;\u0012)a\f\u0001b\u0001o\t\tA\n\u0005\u00025A\u0012)\u0011\r\u0001b\u0001o\t\tQ\n\u0005\u00025G\u0012)A\r\u0001b\u0001o\t\ta\n\u0005\u00025M\u0012)q\r\u0001b\u0001o\t\tq*\u0001\u0004%S:LG\u000f\n\u000b\u0002UB\u0011!d[\u0005\u0003Yn\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0003=\u00042\u0001I\u00174\u0003)\u0019HO];diV\u0014XMM\u000b\u0002eB\u0019\u0001%\f \u0002\u0015M$(/^2ukJ,7'F\u0001v!\r\u0001S&Q\u0001\u000bgR\u0014Xo\u0019;ve\u0016$T#\u0001=\u0011\u0007\u0001jC)\u0001\u0006tiJ,8\r^;sKV*\u0012a\u001f\t\u0004A5:\u0015AC:ueV\u001cG/\u001e:fmU\ta\u0010E\u0002![)\u000b!b\u001d;sk\u000e$XO]38+\t\t\u0019\u0001E\u0002![5\u000b!b\u001d;sk\u000e$XO]39+\t\tI\u0001E\u0002![A\u000b!b\u001d;sk\u000e$XO]3:+\t\ty\u0001E\u0002![M\u000b1b\u001d;sk\u000e$XO]32aU\u0011\u0011Q\u0003\t\u0004A52\u0016aC:ueV\u001cG/\u001e:fcE*\"!a\u0007\u0011\u0007\u0001j\u0013,A\u0006tiJ,8\r^;sKF\u0012TCAA\u0011!\r\u0001S\u0006X\u0001\fgR\u0014Xo\u0019;ve\u0016\f4'\u0006\u0002\u0002(A\u0019\u0001%L0\u0002\u0017M$(/^2ukJ,\u0017\u0007N\u000b\u0003\u0003[\u00012\u0001I\u0017c\u0003-\u0019HO];diV\u0014X-M\u001b\u0016\u0005\u0005M\u0002c\u0001\u0011.K\u000691m\\7cS:,G#\u0002\u0019\u0002:\u0005u\u0002BBA\u001e#\u0001\u0007\u0001'\u0001\u0002ya!1\u0011qH\tA\u0002A\n!\u0001_\u0019"
)
public interface SemigroupProduct15 extends Semigroup {
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

   // $FF: synthetic method
   static Tuple15 combine$(final SemigroupProduct15 $this, final Tuple15 x0, final Tuple15 x1) {
      return $this.combine(x0, x1);
   }

   default Tuple15 combine(final Tuple15 x0, final Tuple15 x1) {
      return new Tuple15(this.structure1().combine(x0._1(), x1._1()), this.structure2().combine(x0._2(), x1._2()), this.structure3().combine(x0._3(), x1._3()), this.structure4().combine(x0._4(), x1._4()), this.structure5().combine(x0._5(), x1._5()), this.structure6().combine(x0._6(), x1._6()), this.structure7().combine(x0._7(), x1._7()), this.structure8().combine(x0._8(), x1._8()), this.structure9().combine(x0._9(), x1._9()), this.structure10().combine(x0._10(), x1._10()), this.structure11().combine(x0._11(), x1._11()), this.structure12().combine(x0._12(), x1._12()), this.structure13().combine(x0._13(), x1._13()), this.structure14().combine(x0._14(), x1._14()), this.structure15().combine(x0._15(), x1._15()));
   }

   static void $init$(final SemigroupProduct15 $this) {
   }
}
