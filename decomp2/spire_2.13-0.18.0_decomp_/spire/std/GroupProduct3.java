package spire.std;

import cats.kernel.Group;
import scala.Tuple3;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u000593\u0001BB\u0004\u0011\u0002\u0007\u0005\u0011b\u0003\u0005\u0006y\u0001!\t!\u0010\u0005\u0006\u0003\u00021\u0019A\u0011\u0005\u0006\t\u00021\u0019!\u0012\u0005\u0006\u000f\u00021\u0019\u0001\u0013\u0005\u0006\u0015\u0002!\ta\u0013\u0002\u000e\u000fJ|W\u000f\u001d)s_\u0012,8\r^\u001a\u000b\u0005!I\u0011aA:uI*\t!\"A\u0003ta&\u0014X-\u0006\u0003\rSM24\u0003\u0002\u0001\u000e'a\u0002\"AD\t\u000e\u0003=Q\u0011\u0001E\u0001\u0006g\u000e\fG.Y\u0005\u0003%=\u0011a!\u00118z%\u00164\u0007c\u0001\u000b\"I9\u0011QC\b\b\u0003-qq!aF\u000e\u000e\u0003aQ!!\u0007\u000e\u0002\rq\u0012xn\u001c;?\u0007\u0001I\u0011AC\u0005\u0003;%\tq!\u00197hK\n\u0014\u0018-\u0003\u0002 A\u00059\u0001/Y2lC\u001e,'BA\u000f\n\u0013\t\u00113EA\u0003He>,\bO\u0003\u0002 AA)a\"J\u00143k%\u0011ae\u0004\u0002\u0007)V\u0004H.Z\u001a\u0011\u0005!JC\u0002\u0001\u0003\u0006U\u0001\u0011\ra\u000b\u0002\u0002\u0003F\u0011Af\f\t\u0003\u001d5J!AL\b\u0003\u000f9{G\u000f[5oOB\u0011a\u0002M\u0005\u0003c=\u00111!\u00118z!\tA3\u0007B\u00035\u0001\t\u00071FA\u0001C!\tAc\u0007B\u00038\u0001\t\u00071FA\u0001D!\u0015I$h\n\u001a6\u001b\u00059\u0011BA\u001e\b\u00059iuN\\8jIB\u0013x\u000eZ;diN\na\u0001J5oSR$C#\u0001 \u0011\u00059y\u0014B\u0001!\u0010\u0005\u0011)f.\u001b;\u0002\u0015M$(/^2ukJ,\u0017'F\u0001D!\r!\u0012eJ\u0001\u000bgR\u0014Xo\u0019;ve\u0016\u0014T#\u0001$\u0011\u0007Q\t#'\u0001\u0006tiJ,8\r^;sKN*\u0012!\u0013\t\u0004)\u0005*\u0014aB5om\u0016\u00148/\u001a\u000b\u0003I1CQ!T\u0003A\u0002\u0011\n!\u0001\u001f\u0019"
)
public interface GroupProduct3 extends Group, MonoidProduct3 {
   Group structure1();

   Group structure2();

   Group structure3();

   // $FF: synthetic method
   static Tuple3 inverse$(final GroupProduct3 $this, final Tuple3 x0) {
      return $this.inverse(x0);
   }

   default Tuple3 inverse(final Tuple3 x0) {
      return new Tuple3(this.structure1().inverse(x0._1()), this.structure2().inverse(x0._2()), this.structure3().inverse(x0._3()));
   }

   static void $init$(final GroupProduct3 $this) {
   }
}
