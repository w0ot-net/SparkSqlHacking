package scala.reflect.macros;

import scala.reflect.ScalaSignature;
import scala.reflect.api.Position;
import scala.runtime.Nothing;

@ScalaSignature(
   bytes = "\u0006\u0005A3\u0001\u0002C\u0005\u0011\u0002G\u0005\u0001C\u0013\u0005\u0006+\u00011\tA\u0006\u0005\u0006_\u00011\t\u0001\r\u0005\u0006q\u00011\t!\u000f\u0005\u0006u\u00011\ta\u000f\u0005\u0006}\u00011\t!\u000f\u0005\u0006\u007f\u00011\t\u0001\u0011\u0005\u0006\u0007\u00021\t\u0001\u0012\u0002\n\rJ|g\u000e^#oINT!AC\u0006\u0002\r5\f7M]8t\u0015\taQ\"A\u0004sK\u001adWm\u0019;\u000b\u00039\tQa]2bY\u0006\u001c\u0001a\u0005\u0002\u0001#A\u0011!cE\u0007\u0002\u001b%\u0011A#\u0004\u0002\u0007\u0003:L(+\u001a4\u0002\t\u0015\u001c\u0007n\u001c\u000b\u0004/i\u0011\u0003C\u0001\n\u0019\u0013\tIRB\u0001\u0003V]&$\b\"B\u000e\u0002\u0001\u0004a\u0012a\u00019pgB\u0011QDH\u0007\u0002\u0001%\u0011q\u0004\t\u0002\t!>\u001c\u0018\u000e^5p]&\u0011\u0011%\u0003\u0002\b\u00032L\u0017m]3t\u0011\u0015\u0019\u0013\u00011\u0001%\u0003\ri7o\u001a\t\u0003K1r!A\n\u0016\u0011\u0005\u001djQ\"\u0001\u0015\u000b\u0005%z\u0011A\u0002\u001fs_>$h(\u0003\u0002,\u001b\u00051\u0001K]3eK\u001aL!!\f\u0018\u0003\rM#(/\u001b8h\u0015\tYS\"\u0001\u0003j]\u001a|G\u0003B\f2eMBQa\u0007\u0002A\u0002qAQa\t\u0002A\u0002\u0011BQ\u0001\u000e\u0002A\u0002U\nQAZ8sG\u0016\u0004\"A\u0005\u001c\n\u0005]j!a\u0002\"p_2,\u0017M\\\u0001\fQ\u0006\u001cx+\u0019:oS:<7/F\u00016\u0003\u001d9\u0018M\u001d8j]\u001e$2a\u0006\u001f>\u0011\u0015YB\u00011\u0001\u001d\u0011\u0015\u0019C\u00011\u0001%\u0003%A\u0017m]#se>\u00148/A\u0003feJ|'\u000fF\u0002\u0018\u0003\nCQa\u0007\u0004A\u0002qAQa\t\u0004A\u0002\u0011\nQ!\u00192peR$2!\u0012%J!\t\u0011b)\u0003\u0002H\u001b\t9aj\u001c;iS:<\u0007\"B\u000e\b\u0001\u0004a\u0002\"B\u0012\b\u0001\u0004!\u0003CA&O\u001b\u0005a%BA'\n\u0003!\u0011G.Y2lE>D\u0018BA(M\u0005\u001d\u0019uN\u001c;fqR\u0004"
)
public interface FrontEnds {
   void echo(final Position pos, final String msg);

   void info(final Position pos, final String msg, final boolean force);

   boolean hasWarnings();

   void warning(final Position pos, final String msg);

   boolean hasErrors();

   void error(final Position pos, final String msg);

   Nothing abort(final Position pos, final String msg);
}
