package spire.std;

import cats.kernel.Group;
import scala.Tuple12;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0005ma\u0001C\b\u0011!\u0003\r\tA\u0005\u000b\t\u000b\u0001\u0004A\u0011A1\t\u000b\u0015\u0004a1\u00014\t\u000b!\u0004a1A5\t\u000b-\u0004a1\u00017\t\u000b9\u0004a1A8\t\u000bE\u0004a1\u0001:\t\u000bQ\u0004a1A;\t\u000b]\u0004a1\u0001=\t\u000bi\u0004a1A>\t\u000bu\u0004a1\u0001@\t\u000f\u0005\u0005\u0001Ab\u0001\u0002\u0004!9\u0011q\u0001\u0001\u0007\u0004\u0005%\u0001bBA\u0007\u0001\u0019\r\u0011q\u0002\u0005\b\u0003'\u0001A\u0011AA\u000b\u000599%o\\;q!J|G-^2ucIR!!\u0005\n\u0002\u0007M$HMC\u0001\u0014\u0003\u0015\u0019\b/\u001b:f+5)\"\u0007P C\u000b\"[e*\u0015+X5N!\u0001A\u0006\u000f]!\t9\"$D\u0001\u0019\u0015\u0005I\u0012!B:dC2\f\u0017BA\u000e\u0019\u0005\u0019\te.\u001f*fMB\u0019QDK\u0017\u000f\u0005y9cBA\u0010&\u001d\t\u0001C%D\u0001\"\u0015\t\u00113%\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005\u0019\u0012B\u0001\u0014\u0013\u0003\u001d\tGnZ3ce\u0006L!\u0001K\u0015\u0002\u000fA\f7m[1hK*\u0011aEE\u0005\u0003W1\u0012Qa\u0012:pkBT!\u0001K\u0015\u0011\u001d]q\u0003g\u000f B\t\u001eSU\nU*W3&\u0011q\u0006\u0007\u0002\b)V\u0004H.Z\u00193!\t\t$\u0007\u0004\u0001\u0005\u000bM\u0002!\u0019\u0001\u001b\u0003\u0003\u0005\u000b\"!\u000e\u001d\u0011\u0005]1\u0014BA\u001c\u0019\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"aF\u001d\n\u0005iB\"aA!osB\u0011\u0011\u0007\u0010\u0003\u0006{\u0001\u0011\r\u0001\u000e\u0002\u0002\u0005B\u0011\u0011g\u0010\u0003\u0006\u0001\u0002\u0011\r\u0001\u000e\u0002\u0002\u0007B\u0011\u0011G\u0011\u0003\u0006\u0007\u0002\u0011\r\u0001\u000e\u0002\u0002\tB\u0011\u0011'\u0012\u0003\u0006\r\u0002\u0011\r\u0001\u000e\u0002\u0002\u000bB\u0011\u0011\u0007\u0013\u0003\u0006\u0013\u0002\u0011\r\u0001\u000e\u0002\u0002\rB\u0011\u0011g\u0013\u0003\u0006\u0019\u0002\u0011\r\u0001\u000e\u0002\u0002\u000fB\u0011\u0011G\u0014\u0003\u0006\u001f\u0002\u0011\r\u0001\u000e\u0002\u0002\u0011B\u0011\u0011'\u0015\u0003\u0006%\u0002\u0011\r\u0001\u000e\u0002\u0002\u0013B\u0011\u0011\u0007\u0016\u0003\u0006+\u0002\u0011\r\u0001\u000e\u0002\u0002\u0015B\u0011\u0011g\u0016\u0003\u00061\u0002\u0011\r\u0001\u000e\u0002\u0002\u0017B\u0011\u0011G\u0017\u0003\u00067\u0002\u0011\r\u0001\u000e\u0002\u0002\u0019BqQL\u0018\u0019<}\u0005#uIS'Q'ZKV\"\u0001\t\n\u0005}\u0003\"aD'p]>LG\r\u0015:pIV\u001cG/\r\u001a\u0002\r\u0011Jg.\u001b;%)\u0005\u0011\u0007CA\fd\u0013\t!\u0007D\u0001\u0003V]&$\u0018AC:ueV\u001cG/\u001e:fcU\tq\rE\u0002\u001eUA\n!b\u001d;sk\u000e$XO]33+\u0005Q\u0007cA\u000f+w\u0005Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u00035\u00042!\b\u0016?\u0003)\u0019HO];diV\u0014X\rN\u000b\u0002aB\u0019QDK!\u0002\u0015M$(/^2ukJ,W'F\u0001t!\ri\"\u0006R\u0001\u000bgR\u0014Xo\u0019;ve\u00164T#\u0001<\u0011\u0007uQs)\u0001\u0006tiJ,8\r^;sK^*\u0012!\u001f\t\u0004;)R\u0015AC:ueV\u001cG/\u001e:fqU\tA\u0010E\u0002\u001eU5\u000b!b\u001d;sk\u000e$XO]3:+\u0005y\bcA\u000f+!\u0006Y1\u000f\u001e:vGR,(/Z\u00191+\t\t)\u0001E\u0002\u001eUM\u000b1b\u001d;sk\u000e$XO]32cU\u0011\u00111\u0002\t\u0004;)2\u0016aC:ueV\u001cG/\u001e:fcI*\"!!\u0005\u0011\u0007uQ\u0013,A\u0004j]Z,'o]3\u0015\u00075\n9\u0002\u0003\u0004\u0002\u001a9\u0001\r!L\u0001\u0003qB\u0002"
)
public interface GroupProduct12 extends Group, MonoidProduct12 {
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

   // $FF: synthetic method
   static Tuple12 inverse$(final GroupProduct12 $this, final Tuple12 x0) {
      return $this.inverse(x0);
   }

   default Tuple12 inverse(final Tuple12 x0) {
      return new Tuple12(this.structure1().inverse(x0._1()), this.structure2().inverse(x0._2()), this.structure3().inverse(x0._3()), this.structure4().inverse(x0._4()), this.structure5().inverse(x0._5()), this.structure6().inverse(x0._6()), this.structure7().inverse(x0._7()), this.structure8().inverse(x0._8()), this.structure9().inverse(x0._9()), this.structure10().inverse(x0._10()), this.structure11().inverse(x0._11()), this.structure12().inverse(x0._12()));
   }

   static void $init$(final GroupProduct12 $this) {
   }
}
