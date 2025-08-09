package spire.std;

import cats.kernel.Semigroup;
import scala.Tuple5;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i3\u0001\u0002C\u0005\u0011\u0002\u0007\u00051\"\u0004\u0005\u0006\u0001\u0002!\t!\u0011\u0005\u0006\u000b\u00021\u0019A\u0012\u0005\u0006\u0011\u00021\u0019!\u0013\u0005\u0006\u0017\u00021\u0019\u0001\u0014\u0005\u0006\u001d\u00021\u0019a\u0014\u0005\u0006#\u00021\u0019A\u0015\u0005\u0006)\u0002!\t!\u0016\u0002\u0012'\u0016l\u0017n\u001a:pkB\u0004&o\u001c3vGR,$B\u0001\u0006\f\u0003\r\u0019H\u000f\u001a\u0006\u0002\u0019\u0005)1\u000f]5sKV1abK\u001b9wy\u001a2\u0001A\b\u0016!\t\u00012#D\u0001\u0012\u0015\u0005\u0011\u0012!B:dC2\f\u0017B\u0001\u000b\u0012\u0005\u0019\te.\u001f*fMB\u0019ac\t\u0014\u000f\u0005]\u0001cB\u0001\r\u001f\u001d\tIR$D\u0001\u001b\u0015\tYB$\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005a\u0011BA\u0010\f\u0003\u001d\tGnZ3ce\u0006L!!\t\u0012\u0002\u000fA\f7m[1hK*\u0011qdC\u0005\u0003I\u0015\u0012\u0011bU3nS\u001e\u0014x.\u001e9\u000b\u0005\u0005\u0012\u0003c\u0002\t(SQ:$(P\u0005\u0003QE\u0011a\u0001V;qY\u0016,\u0004C\u0001\u0016,\u0019\u0001!Q\u0001\f\u0001C\u00025\u0012\u0011!Q\t\u0003]E\u0002\"\u0001E\u0018\n\u0005A\n\"a\u0002(pi\"Lgn\u001a\t\u0003!IJ!aM\t\u0003\u0007\u0005s\u0017\u0010\u0005\u0002+k\u0011)a\u0007\u0001b\u0001[\t\t!\t\u0005\u0002+q\u0011)\u0011\b\u0001b\u0001[\t\t1\t\u0005\u0002+w\u0011)A\b\u0001b\u0001[\t\tA\t\u0005\u0002+}\u0011)q\b\u0001b\u0001[\t\tQ)\u0001\u0004%S:LG\u000f\n\u000b\u0002\u0005B\u0011\u0001cQ\u0005\u0003\tF\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0003\u001d\u00032AF\u0012*\u0003)\u0019HO];diV\u0014XMM\u000b\u0002\u0015B\u0019ac\t\u001b\u0002\u0015M$(/^2ukJ,7'F\u0001N!\r12eN\u0001\u000bgR\u0014Xo\u0019;ve\u0016$T#\u0001)\u0011\u0007Y\u0019#(\u0001\u0006tiJ,8\r^;sKV*\u0012a\u0015\t\u0004-\rj\u0014aB2p[\nLg.\u001a\u000b\u0004MYC\u0006\"B,\b\u0001\u00041\u0013A\u0001=1\u0011\u0015Iv\u00011\u0001'\u0003\tA\u0018\u0007"
)
public interface SemigroupProduct5 extends Semigroup {
   Semigroup structure1();

   Semigroup structure2();

   Semigroup structure3();

   Semigroup structure4();

   Semigroup structure5();

   // $FF: synthetic method
   static Tuple5 combine$(final SemigroupProduct5 $this, final Tuple5 x0, final Tuple5 x1) {
      return $this.combine(x0, x1);
   }

   default Tuple5 combine(final Tuple5 x0, final Tuple5 x1) {
      return new Tuple5(this.structure1().combine(x0._1(), x1._1()), this.structure2().combine(x0._2(), x1._2()), this.structure3().combine(x0._3(), x1._3()), this.structure4().combine(x0._4(), x1._4()), this.structure5().combine(x0._5(), x1._5()));
   }

   static void $init$(final SemigroupProduct5 $this) {
   }
}
