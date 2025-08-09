package scala.reflect.internal.util;

import scala.Predef.;
import scala.collection.immutable.Seq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-2AAB\u0004\u0001!!AQ\u0003\u0001B\u0001B\u0003%a\u0003\u0003\u0005\"\u0001\t\u0005\t\u0015!\u0003\u0017\u0011!\u0011\u0003A!b\u0001\n\u0003\u0019\u0003\u0002\u0003\u0013\u0001\u0005\u0003\u0005\u000b\u0011B\t\t\u000b\u0015\u0002A\u0011\u0001\u0014\u0003'I+\u0007\u000f\u001c\"bi\u000eD7k\\;sG\u00164\u0015\u000e\\3\u000b\u0005!I\u0011\u0001B;uS2T!AC\u0006\u0002\u0011%tG/\u001a:oC2T!\u0001D\u0007\u0002\u000fI,g\r\\3di*\ta\"A\u0003tG\u0006d\u0017m\u0001\u0001\u0014\u0005\u0001\t\u0002C\u0001\n\u0014\u001b\u00059\u0011B\u0001\u000b\b\u0005=\u0011\u0015\r^2i'>,(oY3GS2,\u0017\u0001\u00034jY\u0016t\u0017-\\3\u0011\u0005]qbB\u0001\r\u001d!\tIR\"D\u0001\u001b\u0015\tYr\"\u0001\u0004=e>|GOP\u0005\u0003;5\ta\u0001\u0015:fI\u00164\u0017BA\u0010!\u0005\u0019\u0019FO]5oO*\u0011Q$D\u0001\bG>tG/\u001a8u\u00031\u0001\u0018M]:feN{WO]2f+\u0005\t\u0012!\u00049beN,'oU8ve\u000e,\u0007%\u0001\u0004=S:LGO\u0010\u000b\u0005O!J#\u0006\u0005\u0002\u0013\u0001!)Q#\u0002a\u0001-!)\u0011%\u0002a\u0001-!)!%\u0002a\u0001#\u0001"
)
public class ReplBatchSourceFile extends BatchSourceFile {
   private final BatchSourceFile parserSource;

   public BatchSourceFile parserSource() {
      return this.parserSource;
   }

   public ReplBatchSourceFile(final String filename, final String content, final BatchSourceFile parserSource) {
      super((String)filename, (Seq).MODULE$.wrapString(content));
      this.parserSource = parserSource;
   }
}
