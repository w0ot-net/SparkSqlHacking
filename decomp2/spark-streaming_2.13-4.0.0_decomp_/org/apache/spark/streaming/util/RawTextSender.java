package org.apache.spark.streaming.util;

import org.apache.spark.internal.Logging;
import scala.StringContext;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005]:a\u0001B\u0003\t\u0002\u001dyaAB\t\u0006\u0011\u00039!\u0003C\u0003 \u0003\u0011\u0005\u0011\u0005C\u0003#\u0003\u0011\u00051%A\u0007SC^$V\r\u001f;TK:$WM\u001d\u0006\u0003\r\u001d\tA!\u001e;jY*\u0011\u0001\"C\u0001\ngR\u0014X-Y7j]\u001eT!AC\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u00051i\u0011AB1qC\u000eDWMC\u0001\u000f\u0003\ry'o\u001a\t\u0003!\u0005i\u0011!\u0002\u0002\u000e%\u0006<H+\u001a=u'\u0016tG-\u001a:\u0014\u0007\u0005\u0019\u0012\u0004\u0005\u0002\u0015/5\tQCC\u0001\u0017\u0003\u0015\u00198-\u00197b\u0013\tARC\u0001\u0004B]f\u0014VM\u001a\t\u00035ui\u0011a\u0007\u0006\u00039%\t\u0001\"\u001b8uKJt\u0017\r\\\u0005\u0003=m\u0011q\u0001T8hO&tw-\u0001\u0004=S:LGOP\u0002\u0001)\u0005y\u0011\u0001B7bS:$\"\u0001J\u0014\u0011\u0005Q)\u0013B\u0001\u0014\u0016\u0005\u0011)f.\u001b;\t\u000b!\u001a\u0001\u0019A\u0015\u0002\t\u0005\u0014xm\u001d\t\u0004))b\u0013BA\u0016\u0016\u0005\u0015\t%O]1z!\tiCG\u0004\u0002/eA\u0011q&F\u0007\u0002a)\u0011\u0011\u0007I\u0001\u0007yI|w\u000e\u001e \n\u0005M*\u0012A\u0002)sK\u0012,g-\u0003\u00026m\t11\u000b\u001e:j]\u001eT!aM\u000b"
)
public final class RawTextSender {
   public static void main(final String[] args) {
      RawTextSender$.MODULE$.main(args);
   }

   public static Logging.LogStringContext LogStringContext(final StringContext sc) {
      return RawTextSender$.MODULE$.LogStringContext(sc);
   }
}
