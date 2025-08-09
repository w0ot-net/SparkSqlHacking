package spire.random;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005e1Qa\u0001\u0003\u0002\u0002%AQA\u0004\u0001\u0005\u0002=AQ!\u0005\u0001\u0005\u0002I\u0011\u0011#\u00138u\u0005\u0006\u001cX\rZ$f]\u0016\u0014\u0018\r^8s\u0015\t)a!\u0001\u0004sC:$w.\u001c\u0006\u0002\u000f\u0005)1\u000f]5sK\u000e\u00011C\u0001\u0001\u000b!\tYA\"D\u0001\u0005\u0013\tiAAA\u0005HK:,'/\u0019;pe\u00061A(\u001b8jiz\"\u0012\u0001\u0005\t\u0003\u0017\u0001\t\u0001B\\3yi2{gn\u001a\u000b\u0002'A\u0011AcF\u0007\u0002+)\ta#A\u0003tG\u0006d\u0017-\u0003\u0002\u0019+\t!Aj\u001c8h\u0001"
)
public abstract class IntBasedGenerator extends Generator {
   public long nextLong() {
      return ((long)this.nextInt() & 4294967295L) << 32 | (long)this.nextInt() & 4294967295L;
   }
}
