package spire.algebra;

import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005)3qAB\u0004\u0011\u0002G\u0005A\u0002C\u0003\u0015\u0001\u0019\u0005QcB\u00034\u000f!\u0005AGB\u0003\u0007\u000f!\u0005a\u0007C\u0003;\u0007\u0011\u00051\bC\u0003=\u0007\u0011\u0005QH\u0001\u0006MK\u001a$\u0018i\u0019;j_:T!\u0001C\u0005\u0002\u000f\u0005dw-\u001a2sC*\t!\"A\u0003ta&\u0014Xm\u0001\u0001\u0016\u00075Arf\u0005\u0002\u0001\u001dA\u0011qBE\u0007\u0002!)\t\u0011#A\u0003tG\u0006d\u0017-\u0003\u0002\u0014!\t\u0019\u0011I\\=\u0002\t\u0005\u001cG\u000f\u001c\u000b\u0004-1\n\u0004CA\f\u0019\u0019\u0001!\u0011\"\u0007\u0001!\u0002\u0003\u0005)\u0019\u0001\u000e\u0003\u0003A\u000b\"a\u0007\b\u0011\u0005=a\u0012BA\u000f\u0011\u0005\u001dqu\u000e\u001e5j]\u001eD3\u0001G\u0010#!\ty\u0001%\u0003\u0002\"!\tY1\u000f]3dS\u0006d\u0017N_3ec\u0015\u00193\u0005\n\u0014&\u001d\tyA%\u0003\u0002&!\u0005\u0019\u0011J\u001c;2\t\u0011:3&\u0005\b\u0003Q-j\u0011!\u000b\u0006\u0003U-\ta\u0001\u0010:p_Rt\u0014\"A\t\t\u000b5\n\u0001\u0019\u0001\u0018\u0002\u0003\u001d\u0004\"aF\u0018\u0005\u000bA\u0002!\u0019\u0001\u000e\u0003\u0003\u001dCQAM\u0001A\u0002Y\t\u0011\u0001]\u0001\u000b\u0019\u00164G/Q2uS>t\u0007CA\u001b\u0004\u001b\u000591CA\u00028!\ty\u0001(\u0003\u0002:!\t1\u0011I\\=SK\u001a\fa\u0001P5oSRtD#\u0001\u001b\u0002\u000b\u0005\u0004\b\u000f\\=\u0016\u0007y\n5\t\u0006\u0002@\tB!Q\u0007\u0001!C!\t9\u0012\tB\u0003\u001a\u000b\t\u0007!\u0004\u0005\u0002\u0018\u0007\u0012)\u0001'\u0002b\u00015!)Q)\u0002a\u0001\u007f\u0005\tq\t\u000b\u0002\u0006\u000fB\u0011q\u0002S\u0005\u0003\u0013B\u0011a!\u001b8mS:,\u0007"
)
public interface LeftAction {
   static LeftAction apply(final LeftAction G) {
      return LeftAction$.MODULE$.apply(G);
   }

   Object actl(final Object g, final Object p);

   // $FF: synthetic method
   static int actl$mcI$sp$(final LeftAction $this, final Object g, final int p) {
      return $this.actl$mcI$sp(g, p);
   }

   default int actl$mcI$sp(final Object g, final int p) {
      return BoxesRunTime.unboxToInt(this.actl(g, BoxesRunTime.boxToInteger(p)));
   }
}
