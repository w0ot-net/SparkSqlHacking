package spire.std;

import algebra.ring.Semiring;
import scala.Tuple3;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q3\u0001\"\u0003\u0006\u0011\u0002\u0007\u0005AB\u0004\u0005\u0006w\u0001!\t\u0001\u0010\u0005\u0006\u0001\u00021\u0019!\u0011\u0005\u0006\u0007\u00021\u0019\u0001\u0012\u0005\u0006\r\u00021\u0019a\u0012\u0005\u0006\u0013\u0002!\tA\u0013\u0005\u0006\u0017\u0002!\t\u0001\u0014\u0005\u0006#\u0002!\tA\u0015\u0005\u0006+\u0002!\tE\u0016\u0002\u0011'\u0016l\u0017N]5oOB\u0013x\u000eZ;diNR!a\u0003\u0007\u0002\u0007M$HMC\u0001\u000e\u0003\u0015\u0019\b/\u001b:f+\u0011yAFN\u001d\u0014\u0007\u0001\u0001b\u0003\u0005\u0002\u0012)5\t!CC\u0001\u0014\u0003\u0015\u00198-\u00197b\u0013\t)\"C\u0001\u0004B]f\u0014VM\u001a\t\u0004/\u0011:cB\u0001\r\"\u001d\tIrD\u0004\u0002\u001b=5\t1D\u0003\u0002\u001d;\u00051AH]8piz\u001a\u0001!C\u0001\u000e\u0013\t\u0001C\"A\u0004bY\u001e,'M]1\n\u0005\t\u001a\u0013a\u00029bG.\fw-\u001a\u0006\u0003A1I!!\n\u0014\u0003\u0011M+W.\u001b:j]\u001eT!AI\u0012\u0011\u000bEA#&\u000e\u001d\n\u0005%\u0012\"A\u0002+va2,7\u0007\u0005\u0002,Y1\u0001A!B\u0017\u0001\u0005\u0004q#!A!\u0012\u0005=\u0012\u0004CA\t1\u0013\t\t$CA\u0004O_RD\u0017N\\4\u0011\u0005E\u0019\u0014B\u0001\u001b\u0013\u0005\r\te.\u001f\t\u0003WY\"Qa\u000e\u0001C\u00029\u0012\u0011A\u0011\t\u0003We\"QA\u000f\u0001C\u00029\u0012\u0011aQ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003u\u0002\"!\u0005 \n\u0005}\u0012\"\u0001B+oSR\f!b\u001d;sk\u000e$XO]32+\u0005\u0011\u0005cA\f%U\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0003\u0015\u00032a\u0006\u00136\u0003)\u0019HO];diV\u0014XmM\u000b\u0002\u0011B\u0019q\u0003\n\u001d\u0002\ti,'o\\\u000b\u0002O\u0005!\u0001\u000f\\;t)\r9Sj\u0014\u0005\u0006\u001d\u001a\u0001\raJ\u0001\u0003qBBQ\u0001\u0015\u0004A\u0002\u001d\n!\u0001_\u0019\u0002\u000bQLW.Z:\u0015\u0007\u001d\u001aF\u000bC\u0003O\u000f\u0001\u0007q\u0005C\u0003Q\u000f\u0001\u0007q%A\u0002q_^$2aJ,Y\u0011\u0015q\u0005\u00021\u0001(\u0011\u0015\u0001\u0006\u00021\u0001Z!\t\t\",\u0003\u0002\\%\t\u0019\u0011J\u001c;"
)
public interface SemiringProduct3 extends Semiring {
   Semiring structure1();

   Semiring structure2();

   Semiring structure3();

   // $FF: synthetic method
   static Tuple3 zero$(final SemiringProduct3 $this) {
      return $this.zero();
   }

   default Tuple3 zero() {
      return new Tuple3(this.structure1().zero(), this.structure2().zero(), this.structure3().zero());
   }

   // $FF: synthetic method
   static Tuple3 plus$(final SemiringProduct3 $this, final Tuple3 x0, final Tuple3 x1) {
      return $this.plus(x0, x1);
   }

   default Tuple3 plus(final Tuple3 x0, final Tuple3 x1) {
      return new Tuple3(this.structure1().plus(x0._1(), x1._1()), this.structure2().plus(x0._2(), x1._2()), this.structure3().plus(x0._3(), x1._3()));
   }

   // $FF: synthetic method
   static Tuple3 times$(final SemiringProduct3 $this, final Tuple3 x0, final Tuple3 x1) {
      return $this.times(x0, x1);
   }

   default Tuple3 times(final Tuple3 x0, final Tuple3 x1) {
      return new Tuple3(this.structure1().times(x0._1(), x1._1()), this.structure2().times(x0._2(), x1._2()), this.structure3().times(x0._3(), x1._3()));
   }

   // $FF: synthetic method
   static Tuple3 pow$(final SemiringProduct3 $this, final Tuple3 x0, final int x1) {
      return $this.pow(x0, x1);
   }

   default Tuple3 pow(final Tuple3 x0, final int x1) {
      return new Tuple3(this.structure1().pow(x0._1(), x1), this.structure2().pow(x0._2(), x1), this.structure3().pow(x0._3(), x1));
   }

   static void $init$(final SemiringProduct3 $this) {
   }
}
