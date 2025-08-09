package org.apache.spark.streaming.dstream;

import org.apache.spark.streaming.StreamingContext;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%3Q!\u0002\u0004\u0002\"EA\u0001\"\n\u0001\u0003\u0002\u0003\u0006IA\n\u0005\tU\u0001\u0011\u0019\u0011)A\u0006W!)\u0011\u0007\u0001C\u0001e!)\u0001\t\u0001D\u0001\u0003\n\u0019R*\u00199XSRD7\u000b^1uK\u0012\u001bFO]3b[*\u0011q\u0001C\u0001\bIN$(/Z1n\u0015\tI!\"A\u0005tiJ,\u0017-\\5oO*\u00111\u0002D\u0001\u0006gB\f'o\u001b\u0006\u0003\u001b9\ta!\u00199bG\",'\"A\b\u0002\u0007=\u0014xm\u0001\u0001\u0016\u000bI1\u0014\bP\r\u0014\u0005\u0001\u0019\u0002c\u0001\u000b\u0016/5\ta!\u0003\u0002\u0017\r\t9Ai\u0015;sK\u0006l\u0007C\u0001\r\u001a\u0019\u0001!QA\u0007\u0001C\u0002m\u0011!\"T1qa\u0016$G+\u001f9f#\ta\"\u0005\u0005\u0002\u001eA5\taDC\u0001 \u0003\u0015\u00198-\u00197b\u0013\t\tcDA\u0004O_RD\u0017N\\4\u0011\u0005u\u0019\u0013B\u0001\u0013\u001f\u0005\r\te._\u0001\u0004gN\u001c\u0007CA\u0014)\u001b\u0005A\u0011BA\u0015\t\u0005A\u0019FO]3b[&twmQ8oi\u0016DH/\u0001\u0006fm&$WM\\2fIE\u00022\u0001L\u0018\u0018\u001b\u0005i#B\u0001\u0018\u001f\u0003\u001d\u0011XM\u001a7fGRL!\u0001M\u0017\u0003\u0011\rc\u0017m]:UC\u001e\fa\u0001P5oSRtDCA\u001a@)\t!d\b\u0005\u0004\u0015\u0001UB4h\u0006\t\u00031Y\"Qa\u000e\u0001C\u0002m\u0011qaS3z)f\u0004X\r\u0005\u0002\u0019s\u0011)!\b\u0001b\u00017\tIa+\u00197vKRK\b/\u001a\t\u00031q\"Q!\u0010\u0001C\u0002m\u0011\u0011b\u0015;bi\u0016$\u0016\u0010]3\t\u000b)\u001a\u00019A\u0016\t\u000b\u0015\u001a\u0001\u0019\u0001\u0014\u0002\u001dM$\u0018\r^3T]\u0006\u00048\u000f[8ugR\t!\tE\u0002\u0015+\r\u0003B!\b#6w%\u0011QI\b\u0002\u0007)V\u0004H.\u001a\u001a*\u0005\u00019\u0015B\u0001%\u0007\u0005]i\u0015\r],ji\"\u001cF/\u0019;f\tN#(/Z1n\u00136\u0004H\u000e"
)
public abstract class MapWithStateDStream extends DStream {
   public abstract DStream stateSnapshots();

   public MapWithStateDStream(final StreamingContext ssc, final ClassTag evidence$1) {
      super(ssc, evidence$1);
   }
}
