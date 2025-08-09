package spire.std;

import algebra.ring.Semiring;
import scala.Tuple8;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}4\u0001BD\b\u0011\u0002\u0007\u0005\u0011c\u0005\u0005\u0006\u001f\u0002!\t\u0001\u0015\u0005\u0006)\u00021\u0019!\u0016\u0005\u0006/\u00021\u0019\u0001\u0017\u0005\u00065\u00021\u0019a\u0017\u0005\u0006;\u00021\u0019A\u0018\u0005\u0006A\u00021\u0019!\u0019\u0005\u0006G\u00021\u0019\u0001\u001a\u0005\u0006M\u00021\u0019a\u001a\u0005\u0006S\u00021\u0019A\u001b\u0005\u0006Y\u0002!\t!\u001c\u0005\u0006]\u0002!\ta\u001c\u0005\u0006i\u0002!\t!\u001e\u0005\u0006q\u0002!\t%\u001f\u0002\u0011'\u0016l\u0017N]5oOB\u0013x\u000eZ;dibR!\u0001E\t\u0002\u0007M$HMC\u0001\u0013\u0003\u0015\u0019\b/\u001b:f+%!\u0012g\u000f B\t\u001eSUjE\u0002\u0001+m\u0001\"AF\r\u000e\u0003]Q\u0011\u0001G\u0001\u0006g\u000e\fG.Y\u0005\u00035]\u0011a!\u00118z%\u00164\u0007c\u0001\u000f*Y9\u0011QD\n\b\u0003=\u0011r!aH\u0012\u000e\u0003\u0001R!!\t\u0012\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011AE\u0005\u0003KE\tq!\u00197hK\n\u0014\u0018-\u0003\u0002(Q\u00059\u0001/Y2lC\u001e,'BA\u0013\u0012\u0013\tQ3F\u0001\u0005TK6L'/\u001b8h\u0015\t9\u0003\u0006\u0005\u0006\u0017[=RT\bQ\"G\u00132K!AL\f\u0003\rQ+\b\u000f\\39!\t\u0001\u0014\u0007\u0004\u0001\u0005\u000bI\u0002!\u0019A\u001a\u0003\u0003\u0005\u000b\"\u0001N\u001c\u0011\u0005Y)\u0014B\u0001\u001c\u0018\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"A\u0006\u001d\n\u0005e:\"aA!osB\u0011\u0001g\u000f\u0003\u0006y\u0001\u0011\ra\r\u0002\u0002\u0005B\u0011\u0001G\u0010\u0003\u0006\u007f\u0001\u0011\ra\r\u0002\u0002\u0007B\u0011\u0001'\u0011\u0003\u0006\u0005\u0002\u0011\ra\r\u0002\u0002\tB\u0011\u0001\u0007\u0012\u0003\u0006\u000b\u0002\u0011\ra\r\u0002\u0002\u000bB\u0011\u0001g\u0012\u0003\u0006\u0011\u0002\u0011\ra\r\u0002\u0002\rB\u0011\u0001G\u0013\u0003\u0006\u0017\u0002\u0011\ra\r\u0002\u0002\u000fB\u0011\u0001'\u0014\u0003\u0006\u001d\u0002\u0011\ra\r\u0002\u0002\u0011\u00061A%\u001b8ji\u0012\"\u0012!\u0015\t\u0003-IK!aU\f\u0003\tUs\u0017\u000e^\u0001\u000bgR\u0014Xo\u0019;ve\u0016\fT#\u0001,\u0011\u0007qIs&\u0001\u0006tiJ,8\r^;sKJ*\u0012!\u0017\t\u00049%R\u0014AC:ueV\u001cG/\u001e:fgU\tA\fE\u0002\u001dSu\n!b\u001d;sk\u000e$XO]35+\u0005y\u0006c\u0001\u000f*\u0001\u0006Q1\u000f\u001e:vGR,(/Z\u001b\u0016\u0003\t\u00042\u0001H\u0015D\u0003)\u0019HO];diV\u0014XMN\u000b\u0002KB\u0019A$\u000b$\u0002\u0015M$(/^2ukJ,w'F\u0001i!\ra\u0012&S\u0001\u000bgR\u0014Xo\u0019;ve\u0016DT#A6\u0011\u0007qIC*\u0001\u0003{KJ|W#\u0001\u0017\u0002\tAdWo\u001d\u000b\u0004YA\u0014\b\"B9\f\u0001\u0004a\u0013A\u0001=1\u0011\u0015\u00198\u00021\u0001-\u0003\tA\u0018'A\u0003uS6,7\u000fF\u0002-m^DQ!\u001d\u0007A\u00021BQa\u001d\u0007A\u00021\n1\u0001]8x)\ra#p\u001f\u0005\u0006c6\u0001\r\u0001\f\u0005\u0006g6\u0001\r\u0001 \t\u0003-uL!A`\f\u0003\u0007%sG\u000f"
)
public interface SemiringProduct8 extends Semiring {
   Semiring structure1();

   Semiring structure2();

   Semiring structure3();

   Semiring structure4();

   Semiring structure5();

   Semiring structure6();

   Semiring structure7();

   Semiring structure8();

   // $FF: synthetic method
   static Tuple8 zero$(final SemiringProduct8 $this) {
      return $this.zero();
   }

   default Tuple8 zero() {
      return new Tuple8(this.structure1().zero(), this.structure2().zero(), this.structure3().zero(), this.structure4().zero(), this.structure5().zero(), this.structure6().zero(), this.structure7().zero(), this.structure8().zero());
   }

   // $FF: synthetic method
   static Tuple8 plus$(final SemiringProduct8 $this, final Tuple8 x0, final Tuple8 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple8 plus(final Tuple8 x0, final Tuple8 x1) {
      return new Tuple8(this.structure1().plus(x0._1(), x1._1()), this.structure2().plus(x0._2(), x1._2()), this.structure3().plus(x0._3(), x1._3()), this.structure4().plus(x0._4(), x1._4()), this.structure5().plus(x0._5(), x1._5()), this.structure6().plus(x0._6(), x1._6()), this.structure7().plus(x0._7(), x1._7()), this.structure8().plus(x0._8(), x1._8()));
   }

   // $FF: synthetic method
   static Tuple8 times$(final SemiringProduct8 $this, final Tuple8 x0, final Tuple8 x1) {
      return $this.times(x0, x1);
   }

   default Tuple8 times(final Tuple8 x0, final Tuple8 x1) {
      return new Tuple8(this.structure1().times(x0._1(), x1._1()), this.structure2().times(x0._2(), x1._2()), this.structure3().times(x0._3(), x1._3()), this.structure4().times(x0._4(), x1._4()), this.structure5().times(x0._5(), x1._5()), this.structure6().times(x0._6(), x1._6()), this.structure7().times(x0._7(), x1._7()), this.structure8().times(x0._8(), x1._8()));
   }

   // $FF: synthetic method
   static Tuple8 pow$(final SemiringProduct8 $this, final Tuple8 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple8 pow(final Tuple8 x0, final int x1) {
      return new Tuple8(this.structure1().pow(x0._1(), x1), this.structure2().pow(x0._2(), x1), this.structure3().pow(x0._3(), x1), this.structure4().pow(x0._4(), x1), this.structure5().pow(x0._5(), x1), this.structure6().pow(x0._6(), x1), this.structure7().pow(x0._7(), x1), this.structure8().pow(x0._8(), x1));
   }

   static void $init$(final SemiringProduct8 $this) {
   }
}
