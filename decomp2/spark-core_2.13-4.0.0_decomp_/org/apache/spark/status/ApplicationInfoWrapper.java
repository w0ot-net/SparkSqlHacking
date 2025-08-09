package org.apache.spark.status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.spark.status.api.v1.ApplicationInfo;
import org.apache.spark.util.kvstore.KVIndex;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%3Q!\u0002\u0004\u0001\u00119A\u0001\"\u0006\u0001\u0003\u0006\u0004%\ta\u0006\u0005\tA\u0001\u0011\t\u0011)A\u00051!)\u0011\u0005\u0001C\u0001E!)a\u0005\u0001C\u0001O\t1\u0012\t\u001d9mS\u000e\fG/[8o\u0013:4wn\u0016:baB,'O\u0003\u0002\b\u0011\u000511\u000f^1ukNT!!\u0003\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u0005-a\u0011AB1qC\u000eDWMC\u0001\u000e\u0003\ry'oZ\n\u0003\u0001=\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0017\u0001B5oM>\u001c\u0001!F\u0001\u0019!\tIb$D\u0001\u001b\u0015\tYB$\u0001\u0002wc)\u0011QDB\u0001\u0004CBL\u0017BA\u0010\u001b\u0005=\t\u0005\u000f\u001d7jG\u0006$\u0018n\u001c8J]\u001a|\u0017!B5oM>\u0004\u0013A\u0002\u001fj]&$h\b\u0006\u0002$KA\u0011A\u0005A\u0007\u0002\r!)Qc\u0001a\u00011\u0005\u0011\u0011\u000eZ\u000b\u0002QA\u0011\u0011\u0006\r\b\u0003U9\u0002\"aK\t\u000e\u00031R!!\f\f\u0002\rq\u0012xn\u001c;?\u0013\ty\u0013#\u0001\u0004Qe\u0016$WMZ\u0005\u0003cI\u0012aa\u0015;sS:<'BA\u0018\u0012Q\t!A\u0007\u0005\u00026}5\taG\u0003\u00028q\u0005Q\u0011M\u001c8pi\u0006$\u0018n\u001c8\u000b\u0005eR\u0014a\u00026bG.\u001cxN\u001c\u0006\u0003wq\n\u0011BZ1ti\u0016\u0014\b0\u001c7\u000b\u0003u\n1aY8n\u0013\tydG\u0001\u0006Kg>t\u0017j\u001a8pe\u0016D#\u0001B!\u0011\u0005\t;U\"A\"\u000b\u0005\u0011+\u0015aB6wgR|'/\u001a\u0006\u0003\r\"\tA!\u001e;jY&\u0011\u0001j\u0011\u0002\b\u0017ZKe\u000eZ3y\u0001"
)
public class ApplicationInfoWrapper {
   private final ApplicationInfo info;

   public ApplicationInfo info() {
      return this.info;
   }

   @JsonIgnore
   @KVIndex
   public String id() {
      return this.info().id();
   }

   public ApplicationInfoWrapper(final ApplicationInfo info) {
      this.info = info;
   }
}
