package spire.std;

import cats.kernel.Group;
import scala.Tuple5;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q3\u0001\u0002C\u0005\u0011\u0002\u0007\u00051\"\u0004\u0005\u0006\t\u0002!\t!\u0012\u0005\u0006\u0013\u00021\u0019A\u0013\u0005\u0006\u0019\u00021\u0019!\u0014\u0005\u0006\u001f\u00021\u0019\u0001\u0015\u0005\u0006%\u00021\u0019a\u0015\u0005\u0006+\u00021\u0019A\u0016\u0005\u00061\u0002!\t!\u0017\u0002\u000e\u000fJ|W\u000f\u001d)s_\u0012,8\r^\u001b\u000b\u0005)Y\u0011aA:uI*\tA\"A\u0003ta&\u0014X-\u0006\u0004\u000fWUB4HP\n\u0005\u0001=)\u0002\t\u0005\u0002\u0011'5\t\u0011CC\u0001\u0013\u0003\u0015\u00198-\u00197b\u0013\t!\u0012C\u0001\u0004B]f\u0014VM\u001a\t\u0004-\r2cBA\f!\u001d\tAbD\u0004\u0002\u001a;5\t!D\u0003\u0002\u001c9\u00051AH]8piz\u001a\u0001!C\u0001\r\u0013\ty2\"A\u0004bY\u001e,'M]1\n\u0005\u0005\u0012\u0013a\u00029bG.\fw-\u001a\u0006\u0003?-I!\u0001J\u0013\u0003\u000b\u001d\u0013x.\u001e9\u000b\u0005\u0005\u0012\u0003c\u0002\t(SQ:$(P\u0005\u0003QE\u0011a\u0001V;qY\u0016,\u0004C\u0001\u0016,\u0019\u0001!Q\u0001\f\u0001C\u00025\u0012\u0011!Q\t\u0003]E\u0002\"\u0001E\u0018\n\u0005A\n\"a\u0002(pi\"Lgn\u001a\t\u0003!IJ!aM\t\u0003\u0007\u0005s\u0017\u0010\u0005\u0002+k\u0011)a\u0007\u0001b\u0001[\t\t!\t\u0005\u0002+q\u0011)\u0011\b\u0001b\u0001[\t\t1\t\u0005\u0002+w\u0011)A\b\u0001b\u0001[\t\tA\t\u0005\u0002+}\u0011)q\b\u0001b\u0001[\t\tQ\tE\u0004B\u0005&\"tGO\u001f\u000e\u0003%I!aQ\u0005\u0003\u001d5{gn\\5e!J|G-^2uk\u00051A%\u001b8ji\u0012\"\u0012A\u0012\t\u0003!\u001dK!\u0001S\t\u0003\tUs\u0017\u000e^\u0001\u000bgR\u0014Xo\u0019;ve\u0016\fT#A&\u0011\u0007Y\u0019\u0013&\u0001\u0006tiJ,8\r^;sKJ*\u0012A\u0014\t\u0004-\r\"\u0014AC:ueV\u001cG/\u001e:fgU\t\u0011\u000bE\u0002\u0017G]\n!b\u001d;sk\u000e$XO]35+\u0005!\u0006c\u0001\f$u\u0005Q1\u000f\u001e:vGR,(/Z\u001b\u0016\u0003]\u00032AF\u0012>\u0003\u001dIgN^3sg\u0016$\"A\n.\t\u000bm;\u0001\u0019\u0001\u0014\u0002\u0005a\u0004\u0004"
)
public interface GroupProduct5 extends Group, MonoidProduct5 {
   Group structure1();

   Group structure2();

   Group structure3();

   Group structure4();

   Group structure5();

   // $FF: synthetic method
   static Tuple5 inverse$(final GroupProduct5 $this, final Tuple5 x0) {
      return $this.inverse(x0);
   }

   default Tuple5 inverse(final Tuple5 x0) {
      return new Tuple5(this.structure1().inverse(x0._1()), this.structure2().inverse(x0._2()), this.structure3().inverse(x0._3()), this.structure4().inverse(x0._4()), this.structure5().inverse(x0._5()));
   }

   static void $init$(final GroupProduct5 $this) {
   }
}
