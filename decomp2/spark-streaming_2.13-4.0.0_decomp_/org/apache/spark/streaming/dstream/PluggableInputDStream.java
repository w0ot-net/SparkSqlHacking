package org.apache.spark.streaming.dstream;

import org.apache.spark.streaming.StreamingContext;
import org.apache.spark.streaming.receiver.Receiver;
import scala.reflect.ClassTag;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053QAB\u0004\u0001\u0013EA\u0001B\n\u0001\u0003\u0002\u0003\u0006Ia\n\u0005\tW\u0001\u0011\t\u0011)A\u0005Y!A\u0011\u0007\u0001B\u0002B\u0003-!\u0007C\u00039\u0001\u0011\u0005\u0011\bC\u0003@\u0001\u0011\u0005\u0001IA\u000bQYV<w-\u00192mK&s\u0007/\u001e;E'R\u0014X-Y7\u000b\u0005!I\u0011a\u00023tiJ,\u0017-\u001c\u0006\u0003\u0015-\t\u0011b\u001d;sK\u0006l\u0017N\\4\u000b\u00051i\u0011!B:qCJ\\'B\u0001\b\u0010\u0003\u0019\t\u0007/Y2iK*\t\u0001#A\u0002pe\u001e,\"AE\r\u0014\u0005\u0001\u0019\u0002c\u0001\u000b\u0016/5\tq!\u0003\u0002\u0017\u000f\t!\"+Z2fSZ,'/\u00138qkR$5\u000b\u001e:fC6\u0004\"\u0001G\r\r\u0001\u0011)!\u0004\u0001b\u00019\t\tAk\u0001\u0001\u0012\u0005u\u0019\u0003C\u0001\u0010\"\u001b\u0005y\"\"\u0001\u0011\u0002\u000bM\u001c\u0017\r\\1\n\u0005\tz\"a\u0002(pi\"Lgn\u001a\t\u0003=\u0011J!!J\u0010\u0003\u0007\u0005s\u00170\u0001\u0003`gN\u001c\u0007C\u0001\u0015*\u001b\u0005I\u0011B\u0001\u0016\n\u0005A\u0019FO]3b[&twmQ8oi\u0016DH/\u0001\u0005sK\u000e,\u0017N^3s!\risfF\u0007\u0002])\u00111&C\u0005\u0003a9\u0012\u0001BU3dK&4XM]\u0001\u000bKZLG-\u001a8dK\u0012\n\u0004cA\u001a7/5\tAG\u0003\u00026?\u00059!/\u001a4mK\u000e$\u0018BA\u001c5\u0005!\u0019E.Y:t)\u0006<\u0017A\u0002\u001fj]&$h\bF\u0002;{y\"\"a\u000f\u001f\u0011\u0007Q\u0001q\u0003C\u00032\t\u0001\u000f!\u0007C\u0003'\t\u0001\u0007q\u0005C\u0003,\t\u0001\u0007A&A\u0006hKR\u0014VmY3jm\u0016\u0014H#\u0001\u0017"
)
public class PluggableInputDStream extends ReceiverInputDStream {
   private final Receiver receiver;

   public Receiver getReceiver() {
      return this.receiver;
   }

   public PluggableInputDStream(final StreamingContext _ssc, final Receiver receiver, final ClassTag evidence$1) {
      super(_ssc, evidence$1);
      this.receiver = receiver;
   }
}
