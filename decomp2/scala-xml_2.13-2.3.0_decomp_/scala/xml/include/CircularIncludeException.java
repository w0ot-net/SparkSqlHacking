package scala.xml.include;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\t2A\u0001B\u0003\u0001\u0019!A\u0011\u0003\u0001B\u0001B\u0003%!\u0003C\u0003\u001e\u0001\u0011\u0005a\u0004C\u0003\u001e\u0001\u0011\u0005\u0011E\u0001\rDSJ\u001cW\u000f\\1s\u0013:\u001cG.\u001e3f\u000bb\u001cW\r\u001d;j_:T!AB\u0004\u0002\u000f%t7\r\\;eK*\u0011\u0001\"C\u0001\u0004q6d'\"\u0001\u0006\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0011\u0001!\u0004\t\u0003\u001d=i\u0011!B\u0005\u0003!\u0015\u0011\u0011\u0003W%oG2,H-Z#yG\u0016\u0004H/[8o\u0003\u001diWm]:bO\u0016\u0004\"a\u0005\u000e\u000f\u0005QA\u0002CA\u000b\n\u001b\u00051\"BA\f\f\u0003\u0019a$o\\8u}%\u0011\u0011$C\u0001\u0007!J,G-\u001a4\n\u0005ma\"AB*ue&twM\u0003\u0002\u001a\u0013\u00051A(\u001b8jiz\"\"a\b\u0011\u0011\u00059\u0001\u0001\"B\t\u0003\u0001\u0004\u0011B#A\u0010"
)
public class CircularIncludeException extends XIncludeException {
   public CircularIncludeException(final String message) {
   }

   public CircularIncludeException() {
      this((String)null);
   }
}
