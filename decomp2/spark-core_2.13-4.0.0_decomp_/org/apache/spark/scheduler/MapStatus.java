package org.apache.spark.scheduler;

import org.apache.spark.storage.BlockManagerId;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00114\u0001\"\u0004\b\u0011\u0002G\u0005\u0002C\u0006\u0005\u0006C\u00011\ta\t\u0005\u0006U\u00011\ta\u000b\u0005\u0006c\u00011\tA\r\u0005\u0006w\u00011\t\u0001P\u0004\u0007\u0005:A\t\u0001E\"\u0007\r5q\u0001\u0012\u0001\tE\u0011\u0015)e\u0001\"\u0001G\u0011!9e\u0001#b\u0001\n\u0013A\u0005\"B%\u0007\t\u0003Q\u0005BB+\u0007A\u0003%a\u000bC\u0003Z\r\u0011\u0005!\fC\u0003a\r\u0011\u0005\u0011MA\u0005NCB\u001cF/\u0019;vg*\u0011q\u0002E\u0001\ng\u000eDW\rZ;mKJT!!\u0005\n\u0002\u000bM\u0004\u0018M]6\u000b\u0005M!\u0012AB1qC\u000eDWMC\u0001\u0016\u0003\ry'oZ\n\u0004\u0001]i\u0002C\u0001\r\u001c\u001b\u0005I\"\"\u0001\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005qI\"AB!osJ+g\r\u0005\u0002\u001f?5\ta\"\u0003\u0002!\u001d\t\u00192\u000b[;gM2,w*\u001e;qkR\u001cF/\u0019;vg\u0006AAn\\2bi&|gn\u0001\u0001\u0016\u0003\u0011\u0002\"!\n\u0015\u000e\u0003\u0019R!a\n\t\u0002\u000fM$xN]1hK&\u0011\u0011F\n\u0002\u000f\u00052|7m['b]\u0006<WM]%e\u00039)\b\u000fZ1uK2{7-\u0019;j_:$\"\u0001L\u0018\u0011\u0005ai\u0013B\u0001\u0018\u001a\u0005\u0011)f.\u001b;\t\u000bA\u0012\u0001\u0019\u0001\u0013\u0002\r9,w\u000fT8d\u0003=9W\r^*ju\u00164uN\u001d\"m_\u000e\\GCA\u001a7!\tAB'\u0003\u000263\t!Aj\u001c8h\u0011\u001594\u00011\u00019\u0003!\u0011X\rZ;dK&#\u0007C\u0001\r:\u0013\tQ\u0014DA\u0002J]R\fQ!\\1q\u0013\u0012,\u0012aM\u0015\u0004\u0001y\u0002\u0015BA \u000f\u0005M\u0019u.\u001c9sKN\u001cX\rZ'baN#\u0018\r^;t\u0013\t\teBA\rIS\u001eDG._\"p[B\u0014Xm]:fI6\u000b\u0007o\u0015;biV\u001c\u0018!C'baN#\u0018\r^;t!\tqba\u0005\u0002\u0007/\u00051A(\u001b8jiz\"\u0012aQ\u0001*[&t\u0007+\u0019:uSRLwN\\:U_V\u001bX\rS5hQ2L8i\\7qe\u0016\u001c8/T1q'R\fG/^:\u0016\u0003a\nQ!\u00199qYf$Ba\u0013'O'B\u0011a\u0004\u0001\u0005\u0006\u001b&\u0001\r\u0001J\u0001\u0004Y>\u001c\u0007\"B(\n\u0001\u0004\u0001\u0016!E;oG>l\u0007O]3tg\u0016$7+\u001b>fgB\u0019\u0001$U\u001a\n\u0005IK\"!B!se\u0006L\b\"\u0002+\n\u0001\u0004\u0019\u0014!C7baR\u000b7o[%e\u0003!aujR0C\u0003N+\u0005C\u0001\rX\u0013\tA\u0016D\u0001\u0004E_V\u0014G.Z\u0001\rG>l\u0007O]3tgNK'0\u001a\u000b\u00037z\u0003\"\u0001\u0007/\n\u0005uK\"\u0001\u0002\"zi\u0016DQaX\u0006A\u0002M\nAa]5{K\u0006qA-Z2p[B\u0014Xm]:TSj,GCA\u001ac\u0011\u0015\u0019G\u00021\u0001\\\u00039\u0019w.\u001c9sKN\u001cX\rZ*ju\u0016\u0004"
)
public interface MapStatus extends ShuffleOutputStatus {
   static long decompressSize(final byte compressedSize) {
      return MapStatus$.MODULE$.decompressSize(compressedSize);
   }

   static byte compressSize(final long size) {
      return MapStatus$.MODULE$.compressSize(size);
   }

   static MapStatus apply(final BlockManagerId loc, final long[] uncompressedSizes, final long mapTaskId) {
      return MapStatus$.MODULE$.apply(loc, uncompressedSizes, mapTaskId);
   }

   BlockManagerId location();

   void updateLocation(final BlockManagerId newLoc);

   long getSizeForBlock(final int reduceId);

   long mapId();
}
