package scala.collection;

import scala.Function1;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u0015;a!\u0002\u0004\t\u0002\u0019QaA\u0002\u0007\u0007\u0011\u00031Q\u0002C\u0003\u0013\u0003\u0011\u0005A\u0003C\u0003\u0016\u0003\u0011\u0005a\u0003C\u0003/\u0003\u0011\u0005q&\u0001\u0006EK\n,x-\u0016;jYNT!a\u0002\u0005\u0002\u0015\r|G\u000e\\3di&|gNC\u0001\n\u0003\u0015\u00198-\u00197b!\tY\u0011!D\u0001\u0007\u0005)!UMY;h+RLGn]\n\u0003\u00039\u0001\"a\u0004\t\u000e\u0003!I!!\u0005\u0005\u0003\r\u0005s\u0017PU3g\u0003\u0019a\u0014N\\5u}\r\u0001A#\u0001\u0006\u0002\u0017\t,\u0018\u000e\u001c3TiJLgn\u001a\u000b\u0003/\t\u0002\"\u0001G\u0010\u000f\u0005ei\u0002C\u0001\u000e\t\u001b\u0005Y\"B\u0001\u000f\u0014\u0003\u0019a$o\\8u}%\u0011a\u0004C\u0001\u0007!J,G-\u001a4\n\u0005\u0001\n#AB*ue&twM\u0003\u0002\u001f\u0011!)1e\u0001a\u0001I\u000591\r\\8tkJ,\u0007\u0003B\b&O-J!A\n\u0005\u0003\u0013\u0019+hn\u0019;j_:\f\u0004\u0003B\b&Q-\u0002\"aD\u0015\n\u0005)B!aA!osB\u0011q\u0002L\u0005\u0003[!\u0011A!\u00168ji\u0006Y\u0011M\u001d:bsN#(/\u001b8h+\t\u0001\u0004\b\u0006\u0003\u0018cy\u001a\u0005\"\u0002\u001a\u0005\u0001\u0004\u0019\u0014!B1se\u0006L\bcA\b5m%\u0011Q\u0007\u0003\u0002\u0006\u0003J\u0014\u0018-\u001f\t\u0003oab\u0001\u0001B\u0003:\t\t\u0007!HA\u0001U#\tY\u0004\u0006\u0005\u0002\u0010y%\u0011Q\b\u0003\u0002\b\u001d>$\b.\u001b8h\u0011\u0015yD\u00011\u0001A\u0003\u00111'o\\7\u0011\u0005=\t\u0015B\u0001\"\t\u0005\rIe\u000e\u001e\u0005\u0006\t\u0012\u0001\r\u0001Q\u0001\u0006k:$\u0018\u000e\u001c"
)
public final class DebugUtils {
   public static String arrayString(final Object array, final int from, final int until) {
      return DebugUtils$.MODULE$.arrayString(array, from, until);
   }

   public static String buildString(final Function1 closure) {
      return DebugUtils$.MODULE$.buildString(closure);
   }
}
