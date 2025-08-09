package algebra;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005)2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005AA\u0002\u0005\u0006#\u0001!\ta\u0005\u0005\u0006/\u0001!\u0019\u0001\u0007\u0002\u000e\r&tG\r\u0015:fM\u0016\u0014(/\u001a3\u000b\u0003\u0015\tq!\u00197hK\n\u0014\u0018mE\u0002\u0001\u000f5\u0001\"\u0001C\u0006\u000e\u0003%Q\u0011AC\u0001\u0006g\u000e\fG.Y\u0005\u0003\u0019%\u0011a!\u00118z%\u00164\u0007C\u0001\b\u0010\u001b\u0005!\u0011B\u0001\t\u0005\u000511\u0015N\u001c3GC2d'-Y2l\u0003\u0019!\u0013N\\5uI\r\u0001A#\u0001\u000b\u0011\u0005!)\u0012B\u0001\f\n\u0005\u0011)f.\u001b;\u0002\u0013A\u0014XMZ3se\u0016$WCA\r )\tQ\u0002\u0006\u0005\u0003\u000f7u\u0011\u0013B\u0001\u000f\u0005\u0005!\u0001&/[8sSRL\bC\u0001\u0010 \u0019\u0001!Q\u0001\t\u0002C\u0002\u0005\u0012\u0011\u0001U\t\u0003E\u0015\u0002\"\u0001C\u0012\n\u0005\u0011J!a\u0002(pi\"Lgn\u001a\t\u0003\u0011\u0019J!aJ\u0005\u0003\u0007\u0005s\u0017\u0010C\u0003*\u0005\u0001\u000fQ$\u0001\u0002fm\u0002"
)
public interface FindPreferred extends FindFallback {
   // $FF: synthetic method
   static Priority preferred$(final FindPreferred $this, final Object ev) {
      return $this.preferred(ev);
   }

   default Priority preferred(final Object ev) {
      return new Priority.Preferred(ev);
   }

   static void $init$(final FindPreferred $this) {
   }
}
