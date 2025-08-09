package spire.std;

import cats.kernel.Semigroup;
import scala.Tuple8;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=4\u0001b\u0003\u0007\u0011\u0002\u0007\u0005a\u0002\u0005\u0005\u0006\u0019\u0002!\t!\u0014\u0005\u0006#\u00021\u0019A\u0015\u0005\u0006)\u00021\u0019!\u0016\u0005\u0006/\u00021\u0019\u0001\u0017\u0005\u00065\u00021\u0019a\u0017\u0005\u0006;\u00021\u0019A\u0018\u0005\u0006A\u00021\u0019!\u0019\u0005\u0006G\u00021\u0019\u0001\u001a\u0005\u0006M\u00021\u0019a\u001a\u0005\u0006S\u0002!\tA\u001b\u0002\u0012'\u0016l\u0017n\u001a:pkB\u0004&o\u001c3vGRD$BA\u0007\u000f\u0003\r\u0019H\u000f\u001a\u0006\u0002\u001f\u0005)1\u000f]5sKVI\u0011C\f\u001d<}\u0005#uIS\n\u0004\u0001IA\u0002CA\n\u0017\u001b\u0005!\"\"A\u000b\u0002\u000bM\u001c\u0017\r\\1\n\u0005]!\"AB!osJ+g\rE\u0002\u001aM%r!AG\u0012\u000f\u0005m\tcB\u0001\u000f!\u001b\u0005i\"B\u0001\u0010 \u0003\u0019a$o\\8u}\r\u0001\u0011\"A\b\n\u0005\tr\u0011aB1mO\u0016\u0014'/Y\u0005\u0003I\u0015\nq\u0001]1dW\u0006<WM\u0003\u0002#\u001d%\u0011q\u0005\u000b\u0002\n'\u0016l\u0017n\u001a:pkBT!\u0001J\u0013\u0011\u0015MQCf\u000e\u001e>\u0001\u000e3\u0015*\u0003\u0002,)\t1A+\u001e9mKb\u0002\"!\f\u0018\r\u0001\u0011)q\u0006\u0001b\u0001a\t\t\u0011)\u0005\u00022iA\u00111CM\u0005\u0003gQ\u0011qAT8uQ&tw\r\u0005\u0002\u0014k%\u0011a\u0007\u0006\u0002\u0004\u0003:L\bCA\u00179\t\u0015I\u0004A1\u00011\u0005\u0005\u0011\u0005CA\u0017<\t\u0015a\u0004A1\u00011\u0005\u0005\u0019\u0005CA\u0017?\t\u0015y\u0004A1\u00011\u0005\u0005!\u0005CA\u0017B\t\u0015\u0011\u0005A1\u00011\u0005\u0005)\u0005CA\u0017E\t\u0015)\u0005A1\u00011\u0005\u00051\u0005CA\u0017H\t\u0015A\u0005A1\u00011\u0005\u00059\u0005CA\u0017K\t\u0015Y\u0005A1\u00011\u0005\u0005A\u0015A\u0002\u0013j]&$H\u0005F\u0001O!\t\u0019r*\u0003\u0002Q)\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0002'B\u0019\u0011D\n\u0017\u0002\u0015M$(/^2ukJ,''F\u0001W!\rIbeN\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cT#A-\u0011\u0007e1#(\u0001\u0006tiJ,8\r^;sKR*\u0012\u0001\u0018\t\u00043\u0019j\u0014AC:ueV\u001cG/\u001e:fkU\tq\fE\u0002\u001aM\u0001\u000b!b\u001d;sk\u000e$XO]37+\u0005\u0011\u0007cA\r'\u0007\u0006Q1\u000f\u001e:vGR,(/Z\u001c\u0016\u0003\u0015\u00042!\u0007\u0014G\u0003)\u0019HO];diV\u0014X\rO\u000b\u0002QB\u0019\u0011DJ%\u0002\u000f\r|WNY5oKR\u0019\u0011f[7\t\u000b1T\u0001\u0019A\u0015\u0002\u0005a\u0004\u0004\"\u00028\u000b\u0001\u0004I\u0013A\u0001=2\u0001"
)
public interface SemigroupProduct8 extends Semigroup {
   Semigroup structure1();

   Semigroup structure2();

   Semigroup structure3();

   Semigroup structure4();

   Semigroup structure5();

   Semigroup structure6();

   Semigroup structure7();

   Semigroup structure8();

   // $FF: synthetic method
   static Tuple8 combine$(final SemigroupProduct8 $this, final Tuple8 x0, final Tuple8 x1) {
      return $this.combine(x0, x1);
   }

   default Tuple8 combine(final Tuple8 x0, final Tuple8 x1) {
      return new Tuple8(this.structure1().combine(x0._1(), x1._1()), this.structure2().combine(x0._2(), x1._2()), this.structure3().combine(x0._3(), x1._3()), this.structure4().combine(x0._4(), x1._4()), this.structure5().combine(x0._5(), x1._5()), this.structure6().combine(x0._6(), x1._6()), this.structure7().combine(x0._7(), x1._7()), this.structure8().combine(x0._8(), x1._8()));
   }

   static void $init$(final SemigroupProduct8 $this) {
   }
}
