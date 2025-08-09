package scala.collection;

import scala.reflect.ScalaSignature;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u000512q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003$\u0001\u0011\u0005A\u0005C\u0003)\u0001\u0019\u0005\u0013\u0006\u0003\u0004+\u0001\u0001&\tf\u000b\u0002\u0015\u0007V\u001cHo\\7QCJ\fG\u000e\\3mSj\f'\r\\3\u000b\u0005\u00199\u0011AC2pY2,7\r^5p]*\t\u0001\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0016\u0007-1RdE\u0002\u0001\u0019A\u0001\"!\u0004\b\u000e\u0003\u001dI!aD\u0004\u0003\u0007\u0005s\u0017\u0010\u0005\u0003\u0012%QaR\"A\u0003\n\u0005M)!A\u0004)be\u0006dG.\u001a7ju\u0006\u0014G.\u001a\t\u0003+Ya\u0001\u0001\u0002\u0004\u0018\u0001\u0011\u0015\r\u0001\u0007\u0002\u0002\u0003F\u0011\u0011\u0004\u0004\t\u0003\u001biI!aG\u0004\u0003\u000f9{G\u000f[5oOB\u0011Q#\b\u0003\u0007=\u0001!)\u0019A\u0010\u0003\u000fA\u000b'OU3qeF\u0011\u0011\u0004\t\t\u0003#\u0005J!AI\u0003\u0003\u0011A\u000b'/\u00197mK2\fa\u0001J5oSR$C#A\u0013\u0011\u000551\u0013BA\u0014\b\u0005\u0011)f.\u001b;\u0002\u0007A\f'/F\u0001\u001d\u0003-\u0001\u0018M]\"p[\nLg.\u001a:\u0016\u0003e\u0001"
)
public interface CustomParallelizable extends Parallelizable {
   Parallel par();

   // $FF: synthetic method
   static Nothing parCombiner$(final CustomParallelizable $this) {
      return $this.parCombiner();
   }

   default Nothing parCombiner() {
      throw new UnsupportedOperationException("");
   }

   static void $init$(final CustomParallelizable $this) {
   }
}
