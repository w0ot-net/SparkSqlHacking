package spire.std;

import algebra.ring.Rig;
import scala.Tuple5;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005i3\u0001\u0002C\u0005\u0011\u0002\u0007\u00051\"\u0004\u0005\u0006\t\u0002!\t!\u0012\u0005\u0006\u0013\u00021\u0019A\u0013\u0005\u0006\u0019\u00021\u0019!\u0014\u0005\u0006\u001f\u00021\u0019\u0001\u0015\u0005\u0006%\u00021\u0019a\u0015\u0005\u0006+\u00021\u0019A\u0016\u0005\u00061\u0002!\t!\u0017\u0002\f%&<\u0007K]8ek\u000e$XG\u0003\u0002\u000b\u0017\u0005\u00191\u000f\u001e3\u000b\u00031\tQa\u001d9je\u0016,bAD\u00166qmr4\u0003\u0002\u0001\u0010+\u0001\u0003\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0007c\u0001\f$M9\u0011q\u0003\t\b\u00031yq!!G\u000f\u000e\u0003iQ!a\u0007\u000f\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011\u0001D\u0005\u0003?-\tq!\u00197hK\n\u0014\u0018-\u0003\u0002\"E\u00059\u0001/Y2lC\u001e,'BA\u0010\f\u0013\t!SEA\u0002SS\u001eT!!\t\u0012\u0011\u000fA9\u0013\u0006N\u001c;{%\u0011\u0001&\u0005\u0002\u0007)V\u0004H.Z\u001b\u0011\u0005)ZC\u0002\u0001\u0003\u0006Y\u0001\u0011\r!\f\u0002\u0002\u0003F\u0011a&\r\t\u0003!=J!\u0001M\t\u0003\u000f9{G\u000f[5oOB\u0011\u0001CM\u0005\u0003gE\u00111!\u00118z!\tQS\u0007B\u00037\u0001\t\u0007QFA\u0001C!\tQ\u0003\bB\u0003:\u0001\t\u0007QFA\u0001D!\tQ3\bB\u0003=\u0001\t\u0007QFA\u0001E!\tQc\bB\u0003@\u0001\t\u0007QFA\u0001F!\u001d\t%)\u000b\u001b8uuj\u0011!C\u0005\u0003\u0007&\u0011\u0001cU3nSJLgn\u001a)s_\u0012,8\r^\u001b\u0002\r\u0011Jg.\u001b;%)\u00051\u0005C\u0001\tH\u0013\tA\u0015C\u0001\u0003V]&$\u0018AC:ueV\u001cG/\u001e:fcU\t1\nE\u0002\u0017G%\n!b\u001d;sk\u000e$XO]33+\u0005q\u0005c\u0001\f$i\u0005Q1\u000f\u001e:vGR,(/Z\u001a\u0016\u0003E\u00032AF\u00128\u0003)\u0019HO];diV\u0014X\rN\u000b\u0002)B\u0019ac\t\u001e\u0002\u0015M$(/^2ukJ,W'F\u0001X!\r12%P\u0001\u0004_:,W#\u0001\u0014"
)
public interface RigProduct5 extends Rig, SemiringProduct5 {
   Rig structure1();

   Rig structure2();

   Rig structure3();

   Rig structure4();

   Rig structure5();

   // $FF: synthetic method
   static Tuple5 one$(final RigProduct5 $this) {
      return $this.one();
   }

   default Tuple5 one() {
      return new Tuple5(this.structure1().one(), this.structure2().one(), this.structure3().one(), this.structure4().one(), this.structure5().one());
   }

   static void $init$(final RigProduct5 $this) {
   }
}
