package breeze.io;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005M:Qa\u0002\u0005\t\u000251Qa\u0004\u0005\t\u0002AAQaF\u0001\u0005\u0002aAq!G\u0001C\u0002\u0013\u0005!\u0004\u0003\u0004\u001f\u0003\u0001\u0006Ia\u0007\u0005\u0006?\u0005!\t\u0001\t\u0005\u0006[\u0005!\tAL\u0001\f\r&dWm\u0015;sK\u0006l7O\u0003\u0002\n\u0015\u0005\u0011\u0011n\u001c\u0006\u0002\u0017\u00051!M]3fu\u0016\u001c\u0001\u0001\u0005\u0002\u000f\u00035\t\u0001BA\u0006GS2,7\u000b\u001e:fC6\u001c8CA\u0001\u0012!\t\u0011R#D\u0001\u0014\u0015\u0005!\u0012!B:dC2\f\u0017B\u0001\f\u0014\u0005\u0019\te.\u001f*fM\u00061A(\u001b8jiz\"\u0012!D\u0001\f\u0005V3e)\u0012*`'&SV)F\u0001\u001c!\t\u0011B$\u0003\u0002\u001e'\t\u0019\u0011J\u001c;\u0002\u0019\t+fIR#S?NK%,\u0012\u0011\u0002\u000b%t\u0007/\u001e;\u0015\u0005\u0005B\u0003C\u0001\u0012'\u001b\u0005\u0019#BA\u0005%\u0015\u0005)\u0013\u0001\u00026bm\u0006L!aJ\u0012\u0003\u0017%s\u0007/\u001e;TiJ,\u0017-\u001c\u0005\u0006S\u0015\u0001\rAK\u0001\u0005a\u0006$\b\u000e\u0005\u0002#W%\u0011Af\t\u0002\u0005\r&dW-\u0001\u0004pkR\u0004X\u000f\u001e\u000b\u0003_I\u0002\"A\t\u0019\n\u0005E\u001a#\u0001D(viB,Ho\u0015;sK\u0006l\u0007\"B\u0015\u0007\u0001\u0004Q\u0003"
)
public final class FileStreams {
   public static OutputStream output(final File path) {
      return FileStreams$.MODULE$.output(path);
   }

   public static InputStream input(final File path) {
      return FileStreams$.MODULE$.input(path);
   }

   public static int BUFFER_SIZE() {
      return FileStreams$.MODULE$.BUFFER_SIZE();
   }
}
