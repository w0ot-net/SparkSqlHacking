package spire.std;

import cats.kernel.Group;
import scala.Tuple8;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E4\u0001b\u0003\u0007\u0011\u0002\u0007\u0005a\u0002\u0005\u0005\u0006!\u0002!\t!\u0015\u0005\u0006+\u00021\u0019A\u0016\u0005\u00061\u00021\u0019!\u0017\u0005\u00067\u00021\u0019\u0001\u0018\u0005\u0006=\u00021\u0019a\u0018\u0005\u0006C\u00021\u0019A\u0019\u0005\u0006I\u00021\u0019!\u001a\u0005\u0006O\u00021\u0019\u0001\u001b\u0005\u0006U\u00021\u0019a\u001b\u0005\u0006[\u0002!\tA\u001c\u0002\u000e\u000fJ|W\u000f\u001d)s_\u0012,8\r\u001e\u001d\u000b\u00055q\u0011aA:uI*\tq\"A\u0003ta&\u0014X-F\u0005\u0012]aZd(\u0011#H\u0015N!\u0001A\u0005\rM!\t\u0019b#D\u0001\u0015\u0015\u0005)\u0012!B:dC2\f\u0017BA\f\u0015\u0005\u0019\te.\u001f*fMB\u0019\u0011DJ\u0015\u000f\u0005i\u0019cBA\u000e\"\u001d\ta\u0002%D\u0001\u001e\u0015\tqr$\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005y\u0011B\u0001\u0012\u000f\u0003\u001d\tGnZ3ce\u0006L!\u0001J\u0013\u0002\u000fA\f7m[1hK*\u0011!ED\u0005\u0003O!\u0012Qa\u0012:pkBT!\u0001J\u0013\u0011\u0015MQCf\u000e\u001e>\u0001\u000e3\u0015*\u0003\u0002,)\t1A+\u001e9mKb\u0002\"!\f\u0018\r\u0001\u0011)q\u0006\u0001b\u0001a\t\t\u0011)\u0005\u00022iA\u00111CM\u0005\u0003gQ\u0011qAT8uQ&tw\r\u0005\u0002\u0014k%\u0011a\u0007\u0006\u0002\u0004\u0003:L\bCA\u00179\t\u0015I\u0004A1\u00011\u0005\u0005\u0011\u0005CA\u0017<\t\u0015a\u0004A1\u00011\u0005\u0005\u0019\u0005CA\u0017?\t\u0015y\u0004A1\u00011\u0005\u0005!\u0005CA\u0017B\t\u0015\u0011\u0005A1\u00011\u0005\u0005)\u0005CA\u0017E\t\u0015)\u0005A1\u00011\u0005\u00051\u0005CA\u0017H\t\u0015A\u0005A1\u00011\u0005\u00059\u0005CA\u0017K\t\u0015Y\u0005A1\u00011\u0005\u0005A\u0005CC'OY]RT\bQ\"G\u00136\tA\"\u0003\u0002P\u0019\tqQj\u001c8pS\u0012\u0004&o\u001c3vGRD\u0014A\u0002\u0013j]&$H\u0005F\u0001S!\t\u00192+\u0003\u0002U)\t!QK\\5u\u0003)\u0019HO];diV\u0014X-M\u000b\u0002/B\u0019\u0011D\n\u0017\u0002\u0015M$(/^2ukJ,''F\u0001[!\rIbeN\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u001cT#A/\u0011\u0007e1#(\u0001\u0006tiJ,8\r^;sKR*\u0012\u0001\u0019\t\u00043\u0019j\u0014AC:ueV\u001cG/\u001e:fkU\t1\rE\u0002\u001aM\u0001\u000b!b\u001d;sk\u000e$XO]37+\u00051\u0007cA\r'\u0007\u0006Q1\u000f\u001e:vGR,(/Z\u001c\u0016\u0003%\u00042!\u0007\u0014G\u0003)\u0019HO];diV\u0014X\rO\u000b\u0002YB\u0019\u0011DJ%\u0002\u000f%tg/\u001a:tKR\u0011\u0011f\u001c\u0005\u0006a*\u0001\r!K\u0001\u0003qB\u0002"
)
public interface GroupProduct8 extends Group, MonoidProduct8 {
   Group structure1();

   Group structure2();

   Group structure3();

   Group structure4();

   Group structure5();

   Group structure6();

   Group structure7();

   Group structure8();

   // $FF: synthetic method
   static Tuple8 inverse$(final GroupProduct8 $this, final Tuple8 x0) {
      return $this.inverse(x0);
   }

   default Tuple8 inverse(final Tuple8 x0) {
      return new Tuple8(this.structure1().inverse(x0._1()), this.structure2().inverse(x0._2()), this.structure3().inverse(x0._3()), this.structure4().inverse(x0._4()), this.structure5().inverse(x0._5()), this.structure6().inverse(x0._6()), this.structure7().inverse(x0._7()), this.structure8().inverse(x0._8()));
   }

   static void $init$(final GroupProduct8 $this) {
   }
}
