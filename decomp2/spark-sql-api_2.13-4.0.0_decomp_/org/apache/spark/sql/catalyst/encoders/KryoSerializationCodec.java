package org.apache.spark.sql.catalyst.encoders;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q:Q!\u0002\u0004\t\u0002M1Q!\u0006\u0004\t\u0002YAQ\u0001L\u0001\u0005\u00025B\u0001BL\u0001\t\u0006\u0004%Ia\f\u0005\u0006u\u0005!\teO\u0001\u0017\u0017JLxnU3sS\u0006d\u0017N_1uS>t7i\u001c3fG*\u0011q\u0001C\u0001\tK:\u001cw\u000eZ3sg*\u0011\u0011BC\u0001\tG\u0006$\u0018\r\\=ti*\u00111\u0002D\u0001\u0004gFd'BA\u0007\u000f\u0003\u0015\u0019\b/\u0019:l\u0015\ty\u0001#\u0001\u0004ba\u0006\u001c\u0007.\u001a\u0006\u0002#\u0005\u0019qN]4\u0004\u0001A\u0011A#A\u0007\u0002\r\t12J]=p'\u0016\u0014\u0018.\u00197ju\u0006$\u0018n\u001c8D_\u0012,7mE\u0002\u0002/u\u0001\"\u0001G\u000e\u000e\u0003eQ\u0011AG\u0001\u0006g\u000e\fG.Y\u0005\u00039e\u0011a!\u00118z%\u00164\u0007c\u0001\r\u001fA%\u0011q$\u0007\u0002\n\rVt7\r^5p]B\u0002B\u0001F\u0011$M%\u0011!E\u0002\u0002\u0006\u0007>$Wm\u0019\t\u00031\u0011J!!J\r\u0003\u0007\u0005s\u0017\u0010E\u0002\u0019O%J!\u0001K\r\u0003\u000b\u0005\u0013(/Y=\u0011\u0005aQ\u0013BA\u0016\u001a\u0005\u0011\u0011\u0015\u0010^3\u0002\rqJg.\u001b;?)\u0005\u0019\u0012\u0001F6ss>\u001cu\u000eZ3d\u0007>t7\u000f\u001e:vGR|'/F\u00011!\t\t\u0004(D\u00013\u0015\t\u0019D'\u0001\u0004j]Z|7.\u001a\u0006\u0003kY\nA\u0001\\1oO*\tq'\u0001\u0003kCZ\f\u0017BA\u001d3\u00051iU\r\u001e5pI\"\u000bg\u000e\u001a7f\u0003\u0015\t\u0007\u000f\u001d7z)\u0005\u0001\u0003"
)
public final class KryoSerializationCodec {
   public static Codec apply() {
      return KryoSerializationCodec$.MODULE$.apply();
   }

   public static String toString() {
      return KryoSerializationCodec$.MODULE$.toString();
   }
}
