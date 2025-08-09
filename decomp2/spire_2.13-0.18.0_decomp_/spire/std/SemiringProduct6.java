package spire.std;

import algebra.ring.Semiring;
import scala.Tuple6;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005E4\u0001\u0002D\u0007\u0011\u0002\u0007\u0005q\"\u0005\u0005\u0006\u000f\u0002!\t\u0001\u0013\u0005\u0006\u0019\u00021\u0019!\u0014\u0005\u0006\u001f\u00021\u0019\u0001\u0015\u0005\u0006%\u00021\u0019a\u0015\u0005\u0006+\u00021\u0019A\u0016\u0005\u00061\u00021\u0019!\u0017\u0005\u00067\u00021\u0019\u0001\u0018\u0005\u0006=\u0002!\ta\u0018\u0005\u0006A\u0002!\t!\u0019\u0005\u0006M\u0002!\ta\u001a\u0005\u0006U\u0002!\te\u001b\u0002\u0011'\u0016l\u0017N]5oOB\u0013x\u000eZ;diZR!AD\b\u0002\u0007M$HMC\u0001\u0011\u0003\u0015\u0019\b/\u001b:f+\u001d\u0011r&\u000f\u001f@\u0005\u0016\u001b2\u0001A\n\u001a!\t!r#D\u0001\u0016\u0015\u00051\u0012!B:dC2\f\u0017B\u0001\r\u0016\u0005\u0019\te.\u001f*fMB\u0019!d\n\u0016\u000f\u0005m!cB\u0001\u000f#\u001d\ti\u0012%D\u0001\u001f\u0015\ty\u0002%\u0001\u0004=e>|GOP\u0002\u0001\u0013\u0005\u0001\u0012BA\u0012\u0010\u0003\u001d\tGnZ3ce\u0006L!!\n\u0014\u0002\u000fA\f7m[1hK*\u00111eD\u0005\u0003Q%\u0012\u0001bU3nSJLgn\u001a\u0006\u0003K\u0019\u0002\u0002\u0002F\u0016.qmr\u0014\tR\u0005\u0003YU\u0011a\u0001V;qY\u00164\u0004C\u0001\u00180\u0019\u0001!Q\u0001\r\u0001C\u0002E\u0012\u0011!Q\t\u0003eU\u0002\"\u0001F\u001a\n\u0005Q*\"a\u0002(pi\"Lgn\u001a\t\u0003)YJ!aN\u000b\u0003\u0007\u0005s\u0017\u0010\u0005\u0002/s\u0011)!\b\u0001b\u0001c\t\t!\t\u0005\u0002/y\u0011)Q\b\u0001b\u0001c\t\t1\t\u0005\u0002/\u007f\u0011)\u0001\t\u0001b\u0001c\t\tA\t\u0005\u0002/\u0005\u0012)1\t\u0001b\u0001c\t\tQ\t\u0005\u0002/\u000b\u0012)a\t\u0001b\u0001c\t\ta)\u0001\u0004%S:LG\u000f\n\u000b\u0002\u0013B\u0011ACS\u0005\u0003\u0017V\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u00039\u00032AG\u0014.\u0003)\u0019HO];diV\u0014XMM\u000b\u0002#B\u0019!d\n\u001d\u0002\u0015M$(/^2ukJ,7'F\u0001U!\rQreO\u0001\u000bgR\u0014Xo\u0019;ve\u0016$T#A,\u0011\u0007i9c(\u0001\u0006tiJ,8\r^;sKV*\u0012A\u0017\t\u00045\u001d\n\u0015AC:ueV\u001cG/\u001e:fmU\tQ\fE\u0002\u001bO\u0011\u000bAA_3s_V\t!&\u0001\u0003qYV\u001cHc\u0001\u0016cI\")1-\u0003a\u0001U\u0005\u0011\u0001\u0010\r\u0005\u0006K&\u0001\rAK\u0001\u0003qF\nQ\u0001^5nKN$2A\u000b5j\u0011\u0015\u0019'\u00021\u0001+\u0011\u0015)'\u00021\u0001+\u0003\r\u0001xn\u001e\u000b\u0004U1l\u0007\"B2\f\u0001\u0004Q\u0003\"B3\f\u0001\u0004q\u0007C\u0001\u000bp\u0013\t\u0001XCA\u0002J]R\u0004"
)
public interface SemiringProduct6 extends Semiring {
   Semiring structure1();

   Semiring structure2();

   Semiring structure3();

   Semiring structure4();

   Semiring structure5();

   Semiring structure6();

   // $FF: synthetic method
   static Tuple6 zero$(final SemiringProduct6 $this) {
      return $this.zero();
   }

   default Tuple6 zero() {
      return new Tuple6(this.structure1().zero(), this.structure2().zero(), this.structure3().zero(), this.structure4().zero(), this.structure5().zero(), this.structure6().zero());
   }

   // $FF: synthetic method
   static Tuple6 plus$(final SemiringProduct6 $this, final Tuple6 x0, final Tuple6 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple6 plus(final Tuple6 x0, final Tuple6 x1) {
      return new Tuple6(this.structure1().plus(x0._1(), x1._1()), this.structure2().plus(x0._2(), x1._2()), this.structure3().plus(x0._3(), x1._3()), this.structure4().plus(x0._4(), x1._4()), this.structure5().plus(x0._5(), x1._5()), this.structure6().plus(x0._6(), x1._6()));
   }

   // $FF: synthetic method
   static Tuple6 times$(final SemiringProduct6 $this, final Tuple6 x0, final Tuple6 x1) {
      return $this.times(x0, x1);
   }

   default Tuple6 times(final Tuple6 x0, final Tuple6 x1) {
      return new Tuple6(this.structure1().times(x0._1(), x1._1()), this.structure2().times(x0._2(), x1._2()), this.structure3().times(x0._3(), x1._3()), this.structure4().times(x0._4(), x1._4()), this.structure5().times(x0._5(), x1._5()), this.structure6().times(x0._6(), x1._6()));
   }

   // $FF: synthetic method
   static Tuple6 pow$(final SemiringProduct6 $this, final Tuple6 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple6 pow(final Tuple6 x0, final int x1) {
      return new Tuple6(this.structure1().pow(x0._1(), x1), this.structure2().pow(x0._2(), x1), this.structure3().pow(x0._3(), x1), this.structure4().pow(x0._4(), x1), this.structure5().pow(x0._5(), x1), this.structure6().pow(x0._6(), x1));
   }

   static void $init$(final SemiringProduct6 $this) {
   }
}
