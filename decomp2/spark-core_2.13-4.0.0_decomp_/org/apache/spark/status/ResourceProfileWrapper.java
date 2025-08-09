package org.apache.spark.status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.spark.status.api.v1.ResourceProfileInfo;
import org.apache.spark.util.kvstore.KVIndex;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00053Q!\u0002\u0004\u0001\u00119A\u0001\"\u0006\u0001\u0003\u0006\u0004%\ta\u0006\u0005\tA\u0001\u0011\t\u0011)A\u00051!)\u0011\u0005\u0001C\u0001E!)a\u0005\u0001C\u0001O\t1\"+Z:pkJ\u001cW\r\u0015:pM&dWm\u0016:baB,'O\u0003\u0002\b\u0011\u000511\u000f^1ukNT!!\u0003\u0006\u0002\u000bM\u0004\u0018M]6\u000b\u0005-a\u0011AB1qC\u000eDWMC\u0001\u000e\u0003\ry'oZ\n\u0003\u0001=\u0001\"\u0001E\n\u000e\u0003EQ\u0011AE\u0001\u0006g\u000e\fG.Y\u0005\u0003)E\u0011a!\u00118z%\u00164\u0017A\u0002:q\u0013:4wn\u0001\u0001\u0016\u0003a\u0001\"!\u0007\u0010\u000e\u0003iQ!a\u0007\u000f\u0002\u0005Y\f$BA\u000f\u0007\u0003\r\t\u0007/[\u0005\u0003?i\u00111CU3t_V\u00148-\u001a)s_\u001aLG.Z%oM>\fqA\u001d9J]\u001a|\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003G\u0015\u0002\"\u0001\n\u0001\u000e\u0003\u0019AQ!F\u0002A\u0002a\t!!\u001b3\u0016\u0003!\u0002\"\u0001E\u0015\n\u0005)\n\"aA%oi\"\u0012A\u0001\f\t\u0003[Yj\u0011A\f\u0006\u0003_A\n!\"\u00198o_R\fG/[8o\u0015\t\t$'A\u0004kC\u000e\\7o\u001c8\u000b\u0005M\"\u0014!\u00034bgR,'\u000f_7m\u0015\u0005)\u0014aA2p[&\u0011qG\f\u0002\u000b\u0015N|g.S4o_J,\u0007F\u0001\u0003:!\tQt(D\u0001<\u0015\taT(A\u0004lmN$xN]3\u000b\u0005yB\u0011\u0001B;uS2L!\u0001Q\u001e\u0003\u000f-3\u0016J\u001c3fq\u0002"
)
public class ResourceProfileWrapper {
   private final ResourceProfileInfo rpInfo;

   public ResourceProfileInfo rpInfo() {
      return this.rpInfo;
   }

   @JsonIgnore
   @KVIndex
   public int id() {
      return this.rpInfo().id();
   }

   public ResourceProfileWrapper(final ResourceProfileInfo rpInfo) {
      this.rpInfo = rpInfo;
   }
}
