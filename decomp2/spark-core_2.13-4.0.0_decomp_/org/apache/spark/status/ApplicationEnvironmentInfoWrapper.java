package org.apache.spark.status;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.spark.status.api.v1.ApplicationEnvironmentInfo;
import org.apache.spark.util.kvstore.KVIndex;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005%3Q!\u0002\u0004\u0001\u00119A\u0001\"\u0006\u0001\u0003\u0006\u0004%\ta\u0006\u0005\tA\u0001\u0011\t\u0011)A\u00051!)\u0011\u0005\u0001C\u0001E!)a\u0005\u0001C\u0001O\t\t\u0013\t\u001d9mS\u000e\fG/[8o\u000b:4\u0018N]8o[\u0016tG/\u00138g_^\u0013\u0018\r\u001d9fe*\u0011q\u0001C\u0001\u0007gR\fG/^:\u000b\u0005%Q\u0011!B:qCJ\\'BA\u0006\r\u0003\u0019\t\u0007/Y2iK*\tQ\"A\u0002pe\u001e\u001c\"\u0001A\b\u0011\u0005A\u0019R\"A\t\u000b\u0003I\tQa]2bY\u0006L!\u0001F\t\u0003\r\u0005s\u0017PU3g\u0003\u0011IgNZ8\u0004\u0001U\t\u0001\u0004\u0005\u0002\u001a=5\t!D\u0003\u0002\u001c9\u0005\u0011a/\r\u0006\u0003;\u0019\t1!\u00199j\u0013\ty\"D\u0001\u000eBaBd\u0017nY1uS>tWI\u001c<je>tW.\u001a8u\u0013:4w.A\u0003j]\u001a|\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0003G\u0015\u0002\"\u0001\n\u0001\u000e\u0003\u0019AQ!F\u0002A\u0002a\t!!\u001b3\u0016\u0003!\u0002\"!\u000b\u0019\u000f\u0005)r\u0003CA\u0016\u0012\u001b\u0005a#BA\u0017\u0017\u0003\u0019a$o\\8u}%\u0011q&E\u0001\u0007!J,G-\u001a4\n\u0005E\u0012$AB*ue&twM\u0003\u00020#!\u0012A\u0001\u000e\t\u0003kyj\u0011A\u000e\u0006\u0003oa\n!\"\u00198o_R\fG/[8o\u0015\tI$(A\u0004kC\u000e\\7o\u001c8\u000b\u0005mb\u0014!\u00034bgR,'\u000f_7m\u0015\u0005i\u0014aA2p[&\u0011qH\u000e\u0002\u000b\u0015N|g.S4o_J,\u0007F\u0001\u0003B!\t\u0011u)D\u0001D\u0015\t!U)A\u0004lmN$xN]3\u000b\u0005\u0019C\u0011\u0001B;uS2L!\u0001S\"\u0003\u000f-3\u0016J\u001c3fq\u0002"
)
public class ApplicationEnvironmentInfoWrapper {
   private final ApplicationEnvironmentInfo info;

   public ApplicationEnvironmentInfo info() {
      return this.info;
   }

   @JsonIgnore
   @KVIndex
   public String id() {
      return ApplicationEnvironmentInfoWrapper.class.getName();
   }

   public ApplicationEnvironmentInfoWrapper(final ApplicationEnvironmentInfo info) {
      this.info = info;
   }
}
