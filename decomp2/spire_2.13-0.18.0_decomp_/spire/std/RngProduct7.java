package spire.std;

import algebra.ring.Rng;
import scala.Tuple7;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)4\u0001BC\u0006\u0011\u0002\u0007\u0005Qb\u0004\u0005\u0006\u0019\u0002!\t!\u0014\u0005\u0006#\u00021\u0019A\u0015\u0005\u0006)\u00021\u0019!\u0016\u0005\u0006/\u00021\u0019\u0001\u0017\u0005\u00065\u00021\u0019a\u0017\u0005\u0006;\u00021\u0019A\u0018\u0005\u0006A\u00021\u0019!\u0019\u0005\u0006G\u00021\u0019\u0001\u001a\u0005\u0006M\u0002!\ta\u001a\u0002\f%:<\u0007K]8ek\u000e$xG\u0003\u0002\r\u001b\u0005\u00191\u000f\u001e3\u000b\u00039\tQa\u001d9je\u0016,\u0002\u0002E\u00178uu\u00025IR\n\u0005\u0001E9\u0002\n\u0005\u0002\u0013+5\t1CC\u0001\u0015\u0003\u0015\u00198-\u00197b\u0013\t12C\u0001\u0004B]f\u0014VM\u001a\t\u00041\u0015BcBA\r#\u001d\tQ\u0002E\u0004\u0002\u001c?5\tAD\u0003\u0002\u001e=\u00051AH]8piz\u001a\u0001!C\u0001\u000f\u0013\t\tS\"A\u0004bY\u001e,'M]1\n\u0005\r\"\u0013a\u00029bG.\fw-\u001a\u0006\u0003C5I!AJ\u0014\u0003\u0007IswM\u0003\u0002$IAI!#K\u00167sqz$)R\u0005\u0003UM\u0011a\u0001V;qY\u0016<\u0004C\u0001\u0017.\u0019\u0001!QA\f\u0001C\u0002=\u0012\u0011!Q\t\u0003aM\u0002\"AE\u0019\n\u0005I\u001a\"a\u0002(pi\"Lgn\u001a\t\u0003%QJ!!N\n\u0003\u0007\u0005s\u0017\u0010\u0005\u0002-o\u0011)\u0001\b\u0001b\u0001_\t\t!\t\u0005\u0002-u\u0011)1\b\u0001b\u0001_\t\t1\t\u0005\u0002-{\u0011)a\b\u0001b\u0001_\t\tA\t\u0005\u0002-\u0001\u0012)\u0011\t\u0001b\u0001_\t\tQ\t\u0005\u0002-\u0007\u0012)A\t\u0001b\u0001_\t\ta\t\u0005\u0002-\r\u0012)q\t\u0001b\u0001_\t\tq\tE\u0005J\u0015.2\u0014\bP C\u000b6\t1\"\u0003\u0002L\u0017\t\u00012+Z7je&tw\r\u0015:pIV\u001cGoN\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u00039\u0003\"AE(\n\u0005A\u001b\"\u0001B+oSR\f!b\u001d;sk\u000e$XO]32+\u0005\u0019\u0006c\u0001\r&W\u0005Q1\u000f\u001e:vGR,(/\u001a\u001a\u0016\u0003Y\u00032\u0001G\u00137\u0003)\u0019HO];diV\u0014XmM\u000b\u00023B\u0019\u0001$J\u001d\u0002\u0015M$(/^2ukJ,G'F\u0001]!\rAR\u0005P\u0001\u000bgR\u0014Xo\u0019;ve\u0016,T#A0\u0011\u0007a)s(\u0001\u0006tiJ,8\r^;sKZ*\u0012A\u0019\t\u00041\u0015\u0012\u0015AC:ueV\u001cG/\u001e:foU\tQ\rE\u0002\u0019K\u0015\u000baA\\3hCR,GC\u0001\u0015i\u0011\u0015I\u0017\u00021\u0001)\u0003\tA\b\u0007"
)
public interface RngProduct7 extends Rng, SemiringProduct7 {
   Rng structure1();

   Rng structure2();

   Rng structure3();

   Rng structure4();

   Rng structure5();

   Rng structure6();

   Rng structure7();

   // $FF: synthetic method
   static Tuple7 negate$(final RngProduct7 $this, final Tuple7 x0) {
      return $this.negate(x0);
   }

   default Tuple7 negate(final Tuple7 x0) {
      return new Tuple7(this.structure1().negate(x0._1()), this.structure2().negate(x0._2()), this.structure3().negate(x0._3()), this.structure4().negate(x0._4()), this.structure5().negate(x0._5()), this.structure6().negate(x0._6()), this.structure7().negate(x0._7()));
   }

   static void $init$(final RngProduct7 $this) {
   }
}
