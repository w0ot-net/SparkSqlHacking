package cats.kernel.instances;

import cats.kernel.Eq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005=2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005AA\u0003\u0005\u0006#\u0001!\ta\u0005\u0005\u0006/\u0001!\u0019\u0001\u0007\u0002\u0011\u001fB$\u0018n\u001c8J]N$\u0018M\\2fgJR!!\u0002\u0004\u0002\u0013%t7\u000f^1oG\u0016\u001c(BA\u0004\t\u0003\u0019YWM\u001d8fY*\t\u0011\"\u0001\u0003dCR\u001c8C\u0001\u0001\f!\taq\"D\u0001\u000e\u0015\u0005q\u0011!B:dC2\f\u0017B\u0001\t\u000e\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001\u0015!\taQ#\u0003\u0002\u0017\u001b\t!QK\\5u\u0003a\u0019\u0017\r^:LKJtW\r\\*uI\u0016\u000bhi\u001c:PaRLwN\\\u000b\u00033\r\"\"A\u0007\u0017\u0011\u0007mab$D\u0001\u0007\u0013\tibA\u0001\u0002FcB\u0019AbH\u0011\n\u0005\u0001j!AB(qi&|g\u000e\u0005\u0002#G1\u0001A!\u0002\u0013\u0003\u0005\u0004)#!A!\u0012\u0005\u0019J\u0003C\u0001\u0007(\u0013\tASBA\u0004O_RD\u0017N\\4\u0011\u00051Q\u0013BA\u0016\u000e\u0005\r\te.\u001f\u0005\b[\t\t\t\u0011q\u0001/\u0003))g/\u001b3f]\u000e,GE\u000e\t\u00047q\t\u0003"
)
public interface OptionInstances2 {
   // $FF: synthetic method
   static Eq catsKernelStdEqForOption$(final OptionInstances2 $this, final Eq evidence$6) {
      return $this.catsKernelStdEqForOption(evidence$6);
   }

   default Eq catsKernelStdEqForOption(final Eq evidence$6) {
      return new OptionEq(evidence$6);
   }

   static void $init$(final OptionInstances2 $this) {
   }
}
