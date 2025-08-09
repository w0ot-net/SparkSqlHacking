package spire.std;

import algebra.ring.Semiring;
import scala.Tuple5;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)4\u0001b\u0003\u0007\u0011\u0002\u0007\u0005a\u0002\u0005\u0005\u0006\u0007\u0002!\t\u0001\u0012\u0005\u0006\u0011\u00021\u0019!\u0013\u0005\u0006\u0017\u00021\u0019\u0001\u0014\u0005\u0006\u001d\u00021\u0019a\u0014\u0005\u0006#\u00021\u0019A\u0015\u0005\u0006)\u00021\u0019!\u0016\u0005\u0006/\u0002!\t\u0001\u0017\u0005\u00063\u0002!\tA\u0017\u0005\u0006?\u0002!\t\u0001\u0019\u0005\u0006G\u0002!\t\u0005\u001a\u0002\u0011'\u0016l\u0017N]5oOB\u0013x\u000eZ;diVR!!\u0004\b\u0002\u0007M$HMC\u0001\u0010\u0003\u0015\u0019\b/\u001b:f+\u0019\tb\u0006O\u001e?\u0003N\u0019\u0001A\u0005\r\u0011\u0005M1R\"\u0001\u000b\u000b\u0003U\tQa]2bY\u0006L!a\u0006\u000b\u0003\r\u0005s\u0017PU3g!\rIb%\u000b\b\u00035\rr!aG\u0011\u000f\u0005q\u0001S\"A\u000f\u000b\u0005yy\u0012A\u0002\u001fs_>$hh\u0001\u0001\n\u0003=I!A\t\b\u0002\u000f\u0005dw-\u001a2sC&\u0011A%J\u0001\ba\u0006\u001c7.Y4f\u0015\t\u0011c\"\u0003\u0002(Q\tA1+Z7je&twM\u0003\u0002%KA91C\u000b\u00178uu\u0002\u0015BA\u0016\u0015\u0005\u0019!V\u000f\u001d7fkA\u0011QF\f\u0007\u0001\t\u0015y\u0003A1\u00011\u0005\u0005\t\u0015CA\u00195!\t\u0019\"'\u0003\u00024)\t9aj\u001c;iS:<\u0007CA\n6\u0013\t1DCA\u0002B]f\u0004\"!\f\u001d\u0005\u000be\u0002!\u0019\u0001\u0019\u0003\u0003\t\u0003\"!L\u001e\u0005\u000bq\u0002!\u0019\u0001\u0019\u0003\u0003\r\u0003\"!\f \u0005\u000b}\u0002!\u0019\u0001\u0019\u0003\u0003\u0011\u0003\"!L!\u0005\u000b\t\u0003!\u0019\u0001\u0019\u0003\u0003\u0015\u000ba\u0001J5oSR$C#A#\u0011\u0005M1\u0015BA$\u0015\u0005\u0011)f.\u001b;\u0002\u0015M$(/^2ukJ,\u0017'F\u0001K!\rIb\u0005L\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u0014T#A'\u0011\u0007e1s'\u0001\u0006tiJ,8\r^;sKN*\u0012\u0001\u0015\t\u00043\u0019R\u0014AC:ueV\u001cG/\u001e:fiU\t1\u000bE\u0002\u001aMu\n!b\u001d;sk\u000e$XO]36+\u00051\u0006cA\r'\u0001\u0006!!0\u001a:p+\u0005I\u0013\u0001\u00029mkN$2!K.^\u0011\u0015a\u0006\u00021\u0001*\u0003\tA\b\u0007C\u0003_\u0011\u0001\u0007\u0011&\u0001\u0002yc\u0005)A/[7fgR\u0019\u0011&\u00192\t\u000bqK\u0001\u0019A\u0015\t\u000byK\u0001\u0019A\u0015\u0002\u0007A|w\u000fF\u0002*K\u001aDQ\u0001\u0018\u0006A\u0002%BQA\u0018\u0006A\u0002\u001d\u0004\"a\u00055\n\u0005%$\"aA%oi\u0002"
)
public interface SemiringProduct5 extends Semiring {
   Semiring structure1();

   Semiring structure2();

   Semiring structure3();

   Semiring structure4();

   Semiring structure5();

   // $FF: synthetic method
   static Tuple5 zero$(final SemiringProduct5 $this) {
      return $this.zero();
   }

   default Tuple5 zero() {
      return new Tuple5(this.structure1().zero(), this.structure2().zero(), this.structure3().zero(), this.structure4().zero(), this.structure5().zero());
   }

   // $FF: synthetic method
   static Tuple5 plus$(final SemiringProduct5 $this, final Tuple5 x0, final Tuple5 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple5 plus(final Tuple5 x0, final Tuple5 x1) {
      return new Tuple5(this.structure1().plus(x0._1(), x1._1()), this.structure2().plus(x0._2(), x1._2()), this.structure3().plus(x0._3(), x1._3()), this.structure4().plus(x0._4(), x1._4()), this.structure5().plus(x0._5(), x1._5()));
   }

   // $FF: synthetic method
   static Tuple5 times$(final SemiringProduct5 $this, final Tuple5 x0, final Tuple5 x1) {
      return $this.times(x0, x1);
   }

   default Tuple5 times(final Tuple5 x0, final Tuple5 x1) {
      return new Tuple5(this.structure1().times(x0._1(), x1._1()), this.structure2().times(x0._2(), x1._2()), this.structure3().times(x0._3(), x1._3()), this.structure4().times(x0._4(), x1._4()), this.structure5().times(x0._5(), x1._5()));
   }

   // $FF: synthetic method
   static Tuple5 pow$(final SemiringProduct5 $this, final Tuple5 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple5 pow(final Tuple5 x0, final int x1) {
      return new Tuple5(this.structure1().pow(x0._1(), x1), this.structure2().pow(x0._2(), x1), this.structure3().pow(x0._3(), x1), this.structure4().pow(x0._4(), x1), this.structure5().pow(x0._5(), x1));
   }

   static void $init$(final SemiringProduct5 $this) {
   }
}
