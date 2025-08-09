package scala.reflect.io;

import java.lang.invoke.SerializedLambda;
import scala.collection.Iterator;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxesRunTime;

@ScalaSignature(
   bytes = "\u0006\u0005=2AAB\u0004\u0001\u001d!I1\u0003\u0001B\u0001B\u0003%Ac\u0006\u0005\u00061\u0001!\t!\u0007\u0005\u00069\u0001!\t%\b\u0005\u0006E\u0001!\te\t\u0005\u0006U\u0001!\te\u000b\u0002\u000f!2\f\u0017N\u001c#je\u0016\u001cGo\u001c:z\u0015\tA\u0011\"\u0001\u0002j_*\u0011!bC\u0001\be\u00164G.Z2u\u0015\u0005a\u0011!B:dC2\f7\u0001A\n\u0003\u0001=\u0001\"\u0001E\t\u000e\u0003\u001dI!AE\u0004\u0003\u0013Ac\u0017-\u001b8GS2,\u0017!C4jm\u0016t\u0007+\u0019;i!\t\u0001R#\u0003\u0002\u0017\u000f\tIA)\u001b:fGR|'/_\u0005\u0003'E\ta\u0001P5oSRtDC\u0001\u000e\u001c!\t\u0001\u0002\u0001C\u0003\u0014\u0005\u0001\u0007A#A\u0006jg\u0012K'/Z2u_JLX#\u0001\u0010\u0011\u0005}\u0001S\"A\u0006\n\u0005\u0005Z!a\u0002\"p_2,\u0017M\\\u0001\tSR,'/\u0019;peV\tA\u0005E\u0002&Q=i\u0011A\n\u0006\u0003O-\t!bY8mY\u0016\u001cG/[8o\u0013\tIcE\u0001\u0005Ji\u0016\u0014\u0018\r^8s\u0003\u0019!W\r\\3uKR\tA\u0006\u0005\u0002 [%\u0011af\u0003\u0002\u0005+:LG\u000f"
)
public class PlainDirectory extends PlainFile {
   public boolean isDirectory() {
      return true;
   }

   public Iterator iterator() {
      return ((Directory)super.givenPath()).list().filter((x$1) -> BoxesRunTime.boxToBoolean($anonfun$iterator$1(x$1))).map((x) -> new PlainFile(x));
   }

   public void delete() {
      ((Directory)super.givenPath()).deleteRecursively();
   }

   // $FF: synthetic method
   public static final boolean $anonfun$iterator$1(final Path x$1) {
      return x$1.exists();
   }

   public PlainDirectory(final Directory givenPath) {
      super(givenPath);
   }

   // $FF: synthetic method
   private static Object $deserializeLambda$(SerializedLambda var0) {
      return Class.lambdaDeserialize<invokedynamic>(var0);
   }
}
