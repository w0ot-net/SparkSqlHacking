package org.apache.spark.shuffle;

import java.io.File;
import org.apache.spark.storage.BlockId;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-;Q!\u0002\u0004\t\u0002=1Q!\u0005\u0004\t\u0002IAQ!G\u0001\u0005\u0002iAQaG\u0001\u0005\u0002qAQAM\u0001\u0005\u0002M\nAc\u00155vM\u001adWm\u00115fG.\u001cX/\\+uS2\u001c(BA\u0004\t\u0003\u001d\u0019\b.\u001e4gY\u0016T!!\u0003\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u0005-a\u0011AB1qC\u000eDWMC\u0001\u000e\u0003\ry'oZ\u0002\u0001!\t\u0001\u0012!D\u0001\u0007\u0005Q\u0019\u0006.\u001e4gY\u0016\u001c\u0005.Z2lgVlW\u000b^5mgN\u0011\u0011a\u0005\t\u0003)]i\u0011!\u0006\u0006\u0002-\u0005)1oY1mC&\u0011\u0001$\u0006\u0002\u0007\u0003:L(+\u001a4\u0002\rqJg.\u001b;?)\u0005y\u0011aE4fi\u000eCWmY6tk64\u0015\u000e\\3OC6,GcA\u000f)aA\u0011a$\n\b\u0003?\r\u0002\"\u0001I\u000b\u000e\u0003\u0005R!A\t\b\u0002\rq\u0012xn\u001c;?\u0013\t!S#\u0001\u0004Qe\u0016$WMZ\u0005\u0003M\u001d\u0012aa\u0015;sS:<'B\u0001\u0013\u0016\u0011\u0015I3\u00011\u0001+\u0003\u001d\u0011Gn\\2l\u0013\u0012\u0004\"a\u000b\u0018\u000e\u00031R!!\f\u0005\u0002\u000fM$xN]1hK&\u0011q\u0006\f\u0002\b\u00052|7m[%e\u0011\u0015\t4\u00011\u0001\u001e\u0003%\tGnZ8sSRDW.\u0001\td_6\u0004\u0018M]3DQ\u0016\u001c7n];ngR1Ag\u000e\u001f>\u000f&\u0003\"\u0001F\u001b\n\u0005Y*\"a\u0002\"p_2,\u0017M\u001c\u0005\u0006q\u0011\u0001\r!O\u0001\r]Vl\u0007+\u0019:uSRLwN\u001c\t\u0003)iJ!aO\u000b\u0003\u0007%sG\u000fC\u00032\t\u0001\u0007Q\u0004C\u0003?\t\u0001\u0007q(\u0001\u0005dQ\u0016\u001c7n];n!\t\u0001U)D\u0001B\u0015\t\u00115)\u0001\u0002j_*\tA)\u0001\u0003kCZ\f\u0017B\u0001$B\u0005\u00111\u0015\u000e\\3\t\u000b!#\u0001\u0019A \u0002\t\u0011\fG/\u0019\u0005\u0006\u0015\u0012\u0001\raP\u0001\u0006S:$W\r\u001f"
)
public final class ShuffleChecksumUtils {
   public static boolean compareChecksums(final int numPartition, final String algorithm, final File checksum, final File data, final File index) {
      return ShuffleChecksumUtils$.MODULE$.compareChecksums(numPartition, algorithm, checksum, data, index);
   }

   public static String getChecksumFileName(final BlockId blockId, final String algorithm) {
      return ShuffleChecksumUtils$.MODULE$.getChecksumFileName(blockId, algorithm);
   }
}
