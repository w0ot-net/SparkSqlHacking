package org.apache.spark.shuffle;

import java.io.Serializable;
import org.apache.spark.annotation.DeveloperApi;
import scala.reflect.ScalaSignature;

@DeveloperApi
@ScalaSignature(
   bytes = "\u0006\u0005M2Q\u0001B\u0003\u0002\u00029A\u0001\"\t\u0001\u0003\u0006\u0004%\tA\t\u0005\tM\u0001\u0011\t\u0011)A\u0005G!)q\u0005\u0001C\u0001Q\ti1\u000b[;gM2,\u0007*\u00198eY\u0016T!AB\u0004\u0002\u000fMDWO\u001a4mK*\u0011\u0001\"C\u0001\u0006gB\f'o\u001b\u0006\u0003\u0015-\ta!\u00199bG\",'\"\u0001\u0007\u0002\u0007=\u0014xm\u0001\u0001\u0014\u0007\u0001yQ\u0003\u0005\u0002\u0011'5\t\u0011CC\u0001\u0013\u0003\u0015\u00198-\u00197b\u0013\t!\u0012C\u0001\u0004B]f\u0014VM\u001a\t\u0003-yq!a\u0006\u000f\u000f\u0005aYR\"A\r\u000b\u0005ii\u0011A\u0002\u001fs_>$h(C\u0001\u0013\u0013\ti\u0012#A\u0004qC\u000e\\\u0017mZ3\n\u0005}\u0001#\u0001D*fe&\fG.\u001b>bE2,'BA\u000f\u0012\u0003%\u0019\b.\u001e4gY\u0016LE-F\u0001$!\t\u0001B%\u0003\u0002&#\t\u0019\u0011J\u001c;\u0002\u0015MDWO\u001a4mK&#\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003S-\u0002\"A\u000b\u0001\u000e\u0003\u0015AQ!I\u0002A\u0002\rB#\u0001A\u0017\u0011\u00059\nT\"A\u0018\u000b\u0005A:\u0011AC1o]>$\u0018\r^5p]&\u0011!g\f\u0002\r\t\u00164X\r\\8qKJ\f\u0005/\u001b"
)
public abstract class ShuffleHandle implements Serializable {
   private final int shuffleId;

   public int shuffleId() {
      return this.shuffleId;
   }

   public ShuffleHandle(final int shuffleId) {
      this.shuffleId = shuffleId;
   }
}
