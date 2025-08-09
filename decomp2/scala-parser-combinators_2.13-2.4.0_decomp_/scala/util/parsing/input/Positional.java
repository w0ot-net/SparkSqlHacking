package scala.util.parsing.input;

import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005!2q!\u0002\u0004\u0011\u0002\u0007\u0005q\u0002C\u0003\u0015\u0001\u0011\u0005Q\u0003C\u0004\u001a\u0001\u0001\u0007I\u0011\u0001\u000e\t\u000f}\u0001\u0001\u0019!C\u0001A!)1\u0005\u0001C\u0001I\tQ\u0001k\\:ji&|g.\u00197\u000b\u0005\u001dA\u0011!B5oaV$(BA\u0005\u000b\u0003\u001d\u0001\u0018M]:j]\u001eT!a\u0003\u0007\u0002\tU$\u0018\u000e\u001c\u0006\u0002\u001b\u0005)1oY1mC\u000e\u00011C\u0001\u0001\u0011!\t\t\"#D\u0001\r\u0013\t\u0019BB\u0001\u0004B]f\u0014VMZ\u0001\u0007I%t\u0017\u000e\u001e\u0013\u0015\u0003Y\u0001\"!E\f\n\u0005aa!\u0001B+oSR\f1\u0001]8t+\u0005Y\u0002C\u0001\u000f\u001e\u001b\u00051\u0011B\u0001\u0010\u0007\u0005!\u0001vn]5uS>t\u0017a\u00029pg~#S-\u001d\u000b\u0003-\u0005BqAI\u0002\u0002\u0002\u0003\u00071$A\u0002yIE\naa]3u!>\u001cHCA\u0013'\u001b\u0005\u0001\u0001\"B\u0014\u0005\u0001\u0004Y\u0012A\u00028foB|7\u000f"
)
public interface Positional {
   Position pos();

   void pos_$eq(final Position x$1);

   // $FF: synthetic method
   static Positional setPos$(final Positional $this, final Position newpos) {
      return $this.setPos(newpos);
   }

   default Positional setPos(final Position newpos) {
      if (this.pos() == NoPosition$.MODULE$) {
         this.pos_$eq(newpos);
      }

      return this;
   }

   static void $init$(final Positional $this) {
      $this.pos_$eq(NoPosition$.MODULE$);
   }
}
