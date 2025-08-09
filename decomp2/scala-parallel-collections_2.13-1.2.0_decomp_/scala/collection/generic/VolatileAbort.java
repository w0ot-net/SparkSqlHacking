package scala.collection.generic;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005-2qAB\u0004\u0011\u0002\u0007\u0005a\u0002C\u0003\u0018\u0001\u0011\u0005\u0001\u0004C\u0004\u001d\u0001\u0001\u0007I\u0011B\u000f\t\u000f\u0015\u0002\u0001\u0019!C\u0005M!)\u0011\u0006\u0001C!;!)!\u0006\u0001C!1\tiak\u001c7bi&dW-\u00112peRT!\u0001C\u0005\u0002\u000f\u001d,g.\u001a:jG*\u0011!bC\u0001\u000bG>dG.Z2uS>t'\"\u0001\u0007\u0002\u000bM\u001c\u0017\r\\1\u0004\u0001M\u0019\u0001aD\n\u0011\u0005A\tR\"A\u0006\n\u0005IY!AB!osJ+g\r\u0005\u0002\u0015+5\tq!\u0003\u0002\u0017\u000f\tQ1+[4oC2d\u0017N\\4\u0002\r\u0011Jg.\u001b;%)\u0005I\u0002C\u0001\t\u001b\u0013\tY2B\u0001\u0003V]&$\u0018!C1c_J$h\r\\1h+\u0005q\u0002C\u0001\t \u0013\t\u00013BA\u0004C_>dW-\u00198)\u0005\t\u0011\u0003C\u0001\t$\u0013\t!3B\u0001\u0005w_2\fG/\u001b7f\u00035\t'm\u001c:uM2\fwm\u0018\u0013fcR\u0011\u0011d\n\u0005\bQ\r\t\t\u00111\u0001\u001f\u0003\rAH%M\u0001\nSN\f%m\u001c:uK\u0012\fQ!\u00192peR\u0004"
)
public interface VolatileAbort extends Signalling {
   boolean scala$collection$generic$VolatileAbort$$abortflag();

   void scala$collection$generic$VolatileAbort$$abortflag_$eq(final boolean x$1);

   // $FF: synthetic method
   static boolean isAborted$(final VolatileAbort $this) {
      return $this.isAborted();
   }

   default boolean isAborted() {
      return this.scala$collection$generic$VolatileAbort$$abortflag();
   }

   // $FF: synthetic method
   static void abort$(final VolatileAbort $this) {
      $this.abort();
   }

   default void abort() {
      this.scala$collection$generic$VolatileAbort$$abortflag_$eq(true);
   }

   static void $init$(final VolatileAbort $this) {
      $this.scala$collection$generic$VolatileAbort$$abortflag_$eq(false);
   }
}
