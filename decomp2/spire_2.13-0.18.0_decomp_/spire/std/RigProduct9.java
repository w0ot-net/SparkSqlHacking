package spire.std;

import algebra.ring.Rig;
import scala.Tuple9;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005Y4\u0001\u0002D\u0007\u0011\u0002\u0007\u0005q\"\u0005\u0005\u0006)\u0002!\t!\u0016\u0005\u00063\u00021\u0019A\u0017\u0005\u00069\u00021\u0019!\u0018\u0005\u0006?\u00021\u0019\u0001\u0019\u0005\u0006E\u00021\u0019a\u0019\u0005\u0006K\u00021\u0019A\u001a\u0005\u0006Q\u00021\u0019!\u001b\u0005\u0006W\u00021\u0019\u0001\u001c\u0005\u0006]\u00021\u0019a\u001c\u0005\u0006c\u00021\u0019A\u001d\u0005\u0006i\u0002!\t!\u001e\u0002\f%&<\u0007K]8ek\u000e$\u0018H\u0003\u0002\u000f\u001f\u0005\u00191\u000f\u001e3\u000b\u0003A\tQa\u001d9je\u0016,\"BE\u0018:y}\u0012U\tS&O'\u0011\u00011#\u0007)\u0011\u0005Q9R\"A\u000b\u000b\u0003Y\tQa]2bY\u0006L!\u0001G\u000b\u0003\r\u0005s\u0017PU3g!\rQrE\u000b\b\u00037\u0011r!\u0001\b\u0012\u000f\u0005u\tS\"\u0001\u0010\u000b\u0005}\u0001\u0013A\u0002\u001fs_>$hh\u0001\u0001\n\u0003AI!aI\b\u0002\u000f\u0005dw-\u001a2sC&\u0011QEJ\u0001\ba\u0006\u001c7.Y4f\u0015\t\u0019s\"\u0003\u0002)S\t\u0019!+[4\u000b\u0005\u00152\u0003c\u0003\u000b,[aZd(\u0011#H\u00156K!\u0001L\u000b\u0003\rQ+\b\u000f\\3:!\tqs\u0006\u0004\u0001\u0005\u000bA\u0002!\u0019A\u0019\u0003\u0003\u0005\u000b\"AM\u001b\u0011\u0005Q\u0019\u0014B\u0001\u001b\u0016\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"\u0001\u0006\u001c\n\u0005]*\"aA!osB\u0011a&\u000f\u0003\u0006u\u0001\u0011\r!\r\u0002\u0002\u0005B\u0011a\u0006\u0010\u0003\u0006{\u0001\u0011\r!\r\u0002\u0002\u0007B\u0011af\u0010\u0003\u0006\u0001\u0002\u0011\r!\r\u0002\u0002\tB\u0011aF\u0011\u0003\u0006\u0007\u0002\u0011\r!\r\u0002\u0002\u000bB\u0011a&\u0012\u0003\u0006\r\u0002\u0011\r!\r\u0002\u0002\rB\u0011a\u0006\u0013\u0003\u0006\u0013\u0002\u0011\r!\r\u0002\u0002\u000fB\u0011af\u0013\u0003\u0006\u0019\u0002\u0011\r!\r\u0002\u0002\u0011B\u0011aF\u0014\u0003\u0006\u001f\u0002\u0011\r!\r\u0002\u0002\u0013BY\u0011KU\u00179wy\nEi\u0012&N\u001b\u0005i\u0011BA*\u000e\u0005A\u0019V-\\5sS:<\u0007K]8ek\u000e$\u0018(\u0001\u0004%S:LG\u000f\n\u000b\u0002-B\u0011AcV\u0005\u00031V\u0011A!\u00168ji\u0006Q1\u000f\u001e:vGR,(/Z\u0019\u0016\u0003m\u00032AG\u0014.\u0003)\u0019HO];diV\u0014XMM\u000b\u0002=B\u0019!d\n\u001d\u0002\u0015M$(/^2ukJ,7'F\u0001b!\rQreO\u0001\u000bgR\u0014Xo\u0019;ve\u0016$T#\u00013\u0011\u0007i9c(\u0001\u0006tiJ,8\r^;sKV*\u0012a\u001a\t\u00045\u001d\n\u0015AC:ueV\u001cG/\u001e:fmU\t!\u000eE\u0002\u001bO\u0011\u000b!b\u001d;sk\u000e$XO]38+\u0005i\u0007c\u0001\u000e(\u000f\u0006Q1\u000f\u001e:vGR,(/\u001a\u001d\u0016\u0003A\u00042AG\u0014K\u0003)\u0019HO];diV\u0014X-O\u000b\u0002gB\u0019!dJ'\u0002\u0007=tW-F\u0001+\u0001"
)
public interface RigProduct9 extends Rig, SemiringProduct9 {
   Rig structure1();

   Rig structure2();

   Rig structure3();

   Rig structure4();

   Rig structure5();

   Rig structure6();

   Rig structure7();

   Rig structure8();

   Rig structure9();

   // $FF: synthetic method
   static Tuple9 one$(final RigProduct9 $this) {
      return $this.one();
   }

   default Tuple9 one() {
      return new Tuple9(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one(), this.structure6().one(), this.structure7().one(), this.structure8().one(), this.structure9().one());
   }

   static void $init$(final RigProduct9 $this) {
   }
}
