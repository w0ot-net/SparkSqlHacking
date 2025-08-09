package algebra;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d2\u0001b\u0001\u0003\u0011\u0002\u0007\u0005AA\u0002\u0005\u0006\u001b\u0001!\ta\u0004\u0005\u0006'\u0001!\u0019\u0001\u0006\u0002\r\r&tGMR1mY\n\f7m\u001b\u0006\u0002\u000b\u00059\u0011\r\\4fEJ\f7C\u0001\u0001\b!\tA1\"D\u0001\n\u0015\u0005Q\u0011!B:dC2\f\u0017B\u0001\u0007\n\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\u001a\u0001\u0001F\u0001\u0011!\tA\u0011#\u0003\u0002\u0013\u0013\t!QK\\5u\u0003!1\u0017\r\u001c7cC\u000e\\WCA\u000b )\t1R\u0005\u0005\u0003\u00181iiR\"\u0001\u0003\n\u0005e!!\u0001\u0003)sS>\u0014\u0018\u000e^=\u0011\u0005!Y\u0012B\u0001\u000f\n\u0005\u001dqu\u000e\u001e5j]\u001e\u0004\"AH\u0010\r\u0001\u0011)\u0001E\u0001b\u0001C\t\ta)\u0005\u0002\u001bEA\u0011\u0001bI\u0005\u0003I%\u00111!\u00118z\u0011\u00151#\u0001q\u0001\u001e\u0003\t)g\u000f"
)
public interface FindFallback {
   // $FF: synthetic method
   static Priority fallback$(final FindFallback $this, final Object ev) {
      return $this.fallback(ev);
   }

   default Priority fallback(final Object ev) {
      return new Priority.Fallback(ev);
   }

   static void $init$(final FindFallback $this) {
   }
}
