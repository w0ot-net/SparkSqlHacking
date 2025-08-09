package spire.math;

import scala.reflect.ScalaSignature;
import spire.algebra.NRoot;

@ScalaSignature(
   bytes = "\u0006\u0005I2\u0001\"\u0002\u0004\u0011\u0002\u0007\u0005aA\u0003\u0005\u00067\u0001!\t!\b\u0005\u0006C\u0001!\tA\t\u0005\u0006U\u0001!\te\u000b\u0005\u0006[\u0001!\tA\f\u0002\u000e\u001dVl'-\u001a:Jg:\u0013vn\u001c;\u000b\u0005\u001dA\u0011\u0001B7bi\"T\u0011!C\u0001\u0006gBL'/Z\n\u0004\u0001-\t\u0002C\u0001\u0007\u0010\u001b\u0005i!\"\u0001\b\u0002\u000bM\u001c\u0017\r\\1\n\u0005Ai!AB!osJ+g\rE\u0002\u0013+]i\u0011a\u0005\u0006\u0003)!\tq!\u00197hK\n\u0014\u0018-\u0003\u0002\u0017'\t)aJU8piB\u0011\u0001$G\u0007\u0002\r%\u0011!D\u0002\u0002\u0007\u001dVl'-\u001a:\u0002\r\u0011Jg.\u001b;%\u0007\u0001!\u0012A\b\t\u0003\u0019}I!\u0001I\u0007\u0003\tUs\u0017\u000e^\u0001\u0006]J|w\u000e\u001e\u000b\u0004/\r*\u0003\"\u0002\u0013\u0003\u0001\u00049\u0012!A1\t\u000b\u0019\u0012\u0001\u0019A\u0014\u0002\u0003-\u0004\"\u0001\u0004\u0015\n\u0005%j!aA%oi\u0006!1/\u001d:u)\t9B\u0006C\u0003%\u0007\u0001\u0007q#\u0001\u0003ga><HcA\f0a!)A\u0005\u0002a\u0001/!)\u0011\u0007\u0002a\u0001/\u0005\t!\r"
)
public interface NumberIsNRoot extends NRoot {
   // $FF: synthetic method
   static Number nroot$(final NumberIsNRoot $this, final Number a, final int k) {
      return $this.nroot(a, k);
   }

   default Number nroot(final Number a, final int k) {
      return a.pow(Number$.MODULE$.apply(k));
   }

   // $FF: synthetic method
   static Number sqrt$(final NumberIsNRoot $this, final Number a) {
      return $this.sqrt(a);
   }

   default Number sqrt(final Number a) {
      return a.pow(Number$.MODULE$.apply((double)0.5F));
   }

   // $FF: synthetic method
   static Number fpow$(final NumberIsNRoot $this, final Number a, final Number b) {
      return $this.fpow(a, b);
   }

   default Number fpow(final Number a, final Number b) {
      return a.pow(b);
   }

   static void $init$(final NumberIsNRoot $this) {
   }
}
