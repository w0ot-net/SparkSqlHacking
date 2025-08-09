package org.apache.spark.status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.spark.status.api.v1.RDDStorageInfo;
import org.apache.spark.util.kvstore.KVIndex;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-3QAB\u0004\u0001\u0013=A\u0001B\u0006\u0001\u0003\u0006\u0004%\t\u0001\u0007\u0005\tC\u0001\u0011\t\u0011)A\u00053!)!\u0005\u0001C\u0001G!)q\u0005\u0001C\u0001Q!)!\t\u0001C\u0001\u0007\n)\"\u000b\u0012#Ti>\u0014\u0018mZ3J]\u001a|wK]1qa\u0016\u0014(B\u0001\u0005\n\u0003\u0019\u0019H/\u0019;vg*\u0011!bC\u0001\u0006gB\f'o\u001b\u0006\u0003\u00195\ta!\u00199bG\",'\"\u0001\b\u0002\u0007=\u0014xm\u0005\u0002\u0001!A\u0011\u0011\u0003F\u0007\u0002%)\t1#A\u0003tG\u0006d\u0017-\u0003\u0002\u0016%\t1\u0011I\\=SK\u001a\fA!\u001b8g_\u000e\u0001Q#A\r\u0011\u0005iyR\"A\u000e\u000b\u0005qi\u0012A\u0001<2\u0015\tqr!A\u0002ba&L!\u0001I\u000e\u0003\u001dI#Ei\u0015;pe\u0006<W-\u00138g_\u0006)\u0011N\u001c4pA\u00051A(\u001b8jiz\"\"\u0001\n\u0014\u0011\u0005\u0015\u0002Q\"A\u0004\t\u000bY\u0019\u0001\u0019A\r\u0002\u0005%$W#A\u0015\u0011\u0005EQ\u0013BA\u0016\u0013\u0005\rIe\u000e\u001e\u0015\u0003\t5\u0002\"AL\u001c\u000e\u0003=R!\u0001M\u0019\u0002\u0015\u0005tgn\u001c;bi&|gN\u0003\u00023g\u00059!.Y2lg>t'B\u0001\u001b6\u0003%1\u0017m\u001d;feblGNC\u00017\u0003\r\u0019w.\\\u0005\u0003q=\u0012!BS:p]&;gn\u001c:fQ\t!!\b\u0005\u0002<\u00016\tAH\u0003\u0002>}\u000591N^:u_J,'BA \n\u0003\u0011)H/\u001b7\n\u0005\u0005c$aB&W\u0013:$W\r_\u0001\u0007G\u0006\u001c\u0007.\u001a3\u0016\u0003\u0011\u0003\"!E#\n\u0005\u0019\u0013\"a\u0002\"p_2,\u0017M\u001c\u0015\u0003\u000b5BC!\u0002\u001eJ\u0015\u0006)a/\u00197vK\u0006\n!\t"
)
public class RDDStorageInfoWrapper {
   private final RDDStorageInfo info;

   public RDDStorageInfo info() {
      return this.info;
   }

   @JsonIgnore
   @KVIndex
   public int id() {
      return this.info().id();
   }

   @JsonIgnore
   @KVIndex("cached")
   public boolean cached() {
      return this.info().numCachedPartitions() > 0;
   }

   public RDDStorageInfoWrapper(final RDDStorageInfo info) {
      this.info = info;
   }
}
