package spire.std;

import algebra.ring.Rng;
import scala.Tuple9;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005a4\u0001\u0002D\u0007\u0011\u0002\u0007\u0005q\"\u0005\u0005\u0006)\u0002!\t!\u0016\u0005\u00063\u00021\u0019A\u0017\u0005\u00069\u00021\u0019!\u0018\u0005\u0006?\u00021\u0019\u0001\u0019\u0005\u0006E\u00021\u0019a\u0019\u0005\u0006K\u00021\u0019A\u001a\u0005\u0006Q\u00021\u0019!\u001b\u0005\u0006W\u00021\u0019\u0001\u001c\u0005\u0006]\u00021\u0019a\u001c\u0005\u0006c\u00021\u0019A\u001d\u0005\u0006i\u0002!\t!\u001e\u0002\f%:<\u0007K]8ek\u000e$\u0018H\u0003\u0002\u000f\u001f\u0005\u00191\u000f\u001e3\u000b\u0003A\tQa\u001d9je\u0016,\"BE\u0018:y}\u0012U\tS&O'\u0011\u00011#\u0007)\u0011\u0005Q9R\"A\u000b\u000b\u0003Y\tQa]2bY\u0006L!\u0001G\u000b\u0003\r\u0005s\u0017PU3g!\rQrE\u000b\b\u00037\u0011r!\u0001\b\u0012\u000f\u0005u\tS\"\u0001\u0010\u000b\u0005}\u0001\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003AI!aI\b\u0002\u000f\u0005dw-\u001a2sC&\u0011QEJ\u0001\ba\u0006\u001c7.Y4f\u0015\t\u0019s\"\u0003\u0002)S\t\u0019!K\\4\u000b\u0005\u00152\u0003c\u0003\u000b,[aZd(\u0011#H\u00156K!\u0001L\u000b\u0003\rQ+\b\u000f\\3:!\tqs\u0006\u0004\u0001\u0005\u000bA\u0002!\u0019A\u0019\u0003\u0003\u0005\u000b\"AM\u001b\u0011\u0005Q\u0019\u0014B\u0001\u001b\u0016\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0006\u001c\n\u0005]*\"aA!osB\u0011a&\u000f\u0003\u0006u\u0001\u0011\r!\r\u0002\u0002\u0005B\u0011a\u0006\u0010\u0003\u0006{\u0001\u0011\r!\r\u0002\u0002\u0007B\u0011af\u0010\u0003\u0006\u0001\u0002\u0011\r!\r\u0002\u0002\tB\u0011aF\u0011\u0003\u0006\u0007\u0002\u0011\r!\r\u0002\u0002\u000bB\u0011a&\u0012\u0003\u0006\r\u0002\u0011\r!\r\u0002\u0002\rB\u0011a\u0006\u0013\u0003\u0006\u0013\u0002\u0011\r!\r\u0002\u0002\u000fB\u0011af\u0013\u0003\u0006\u0019\u0002\u0011\r!\r\u0002\u0002\u0011B\u0011aF\u0014\u0003\u0006\u001f\u0002\u0011\r!\r\u0002\u0002\u0013BY\u0011KU\u00179wy\nEi\u0012&N\u001b\u0005i\u0011BA*\u000e\u0005A\u0019V-\\5sS:<\u0007K]8ek\u000e$\u0018(\u0001\u0004%S:LG\u000f\n\u000b\u0002-B\u0011AcV\u0005\u00031V\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0003m\u00032AG\u0014.\u0003)\u0019HO];diV\u0014XMM\u000b\u0002=B\u0019!d\n\u001d\u0002\u0015M$(/^2ukJ,7'F\u0001b!\rQreO\u0001\u000bgR\u0014Xo\u0019;ve\u0016$T#\u00013\u0011\u0007i9c(\u0001\u0006tiJ,8\r^;sKV*\u0012a\u001a\t\u00045\u001d\n\u0015AC:ueV\u001cG/\u001e:fmU\t!\u000eE\u0002\u001bO\u0011\u000b!b\u001d;sk\u000e$XO]38+\u0005i\u0007c\u0001\u000e(\u000f\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0003A\u00042AG\u0014K\u0003)\u0019HO];diV\u0014X-O\u000b\u0002gB\u0019!dJ'\u0002\r9,w-\u0019;f)\tQc\u000fC\u0003x\u0017\u0001\u0007!&\u0001\u0002ya\u0001"
)
public interface RngProduct9 extends Rng, SemiringProduct9 {
   Rng structure1();

   Rng structure2();

   Rng structure3();

   Rng structure4();

   Rng structure5();

   Rng structure6();

   Rng structure7();

   Rng structure8();

   Rng structure9();

   // $FF: synthetic method
   static Tuple9 negate$(final RngProduct9 $this, final Tuple9 x0) {
      return $this.negate(x0);
   }

   default Tuple9 negate(final Tuple9 x0) {
      return new Tuple9(this.structure1().negate(x0._1()), this.structure2().negate(x0._2()), this.structure3().negate(x0._3()), this.structure4().negate(x0._4()), this.structure5().negate(x0._5()), this.structure6().negate(x0._6()), this.structure7().negate(x0._7()), this.structure8().negate(x0._8()), this.structure9().negate(x0._9()));
   }

   static void $init$(final RngProduct9 $this) {
   }
}
