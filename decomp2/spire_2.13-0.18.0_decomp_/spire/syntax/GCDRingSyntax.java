package spire.syntax;

import algebra.ring.GCDRing;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005}2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0003\u001a\u0001\u0011\r!DA\u0007H\u0007\u0012\u0013\u0016N\\4Ts:$\u0018\r\u001f\u0006\u0003\u000b\u0019\taa]=oi\u0006D(\"A\u0004\u0002\u000bM\u0004\u0018N]3\u0004\u0001M\u0019\u0001A\u0003\t\u0011\u0005-qQ\"\u0001\u0007\u000b\u00035\tQa]2bY\u0006L!a\u0004\u0007\u0003\r\u0005s\u0017PU3g!\t\t\"#D\u0001\u0005\u0013\t\u0019BA\u0001\u0006SS:<7+\u001f8uCb\fa\u0001J5oSR$C#\u0001\f\u0011\u0005-9\u0012B\u0001\r\r\u0005\u0011)f.\u001b;\u0002\u0015\u001d\u001cGMU5oO>\u00038/\u0006\u0002\u001cEQ\u0011A$\u0010\u000b\u0003;-\u00022!\u0005\u0010!\u0013\tyBA\u0001\u0006H\u0007\u0012\u0013\u0016N\\4PaN\u0004\"!\t\u0012\r\u0001\u0011)1E\u0001b\u0001I\t\t\u0011)\u0005\u0002&QA\u00111BJ\u0005\u0003O1\u0011qAT8uQ&tw\r\u0005\u0002\fS%\u0011!\u0006\u0004\u0002\u0004\u0003:L\bb\u0002\u0017\u0003\u0003\u0003\u0005\u001d!L\u0001\fKZLG-\u001a8dK\u0012\nd\u0007E\u0002/u\u0001r!aL\u001c\u000f\u0005A*dBA\u00195\u001b\u0005\u0011$BA\u001a\t\u0003\u0019a$o\\8u}%\tq!\u0003\u00027\r\u00059\u0011\r\\4fEJ\f\u0017B\u0001\u001d:\u0003\u001d\u0001\u0018mY6bO\u0016T!A\u000e\u0004\n\u0005mb$aB$D\tJKgn\u001a\u0006\u0003qeBQA\u0010\u0002A\u0002\u0001\n\u0011!\u0019"
)
public interface GCDRingSyntax extends RingSyntax {
   // $FF: synthetic method
   static GCDRingOps gcdRingOps$(final GCDRingSyntax $this, final Object a, final GCDRing evidence$16) {
      return $this.gcdRingOps(a, evidence$16);
   }

   default GCDRingOps gcdRingOps(final Object a, final GCDRing evidence$16) {
      return new GCDRingOps(a, evidence$16);
   }

   static void $init$(final GCDRingSyntax $this) {
   }
}
