package spire.std;

import cats.kernel.Eq;
import cats.kernel.Semigroup;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u001d3q\u0001B\u0003\u0011\u0002\u0007\u0005!\u0002C\u0003\u0012\u0001\u0011\u0005!\u0003C\u0003\u0017\u0001\u0011\rq\u0003C\u0003;\u0001\u0011\r1H\u0001\tPaRLwN\\%ogR\fgnY3ta)\u0011aaB\u0001\u0004gR$'\"\u0001\u0005\u0002\u000bM\u0004\u0018N]3\u0004\u0001M\u0011\u0001a\u0003\t\u0003\u0019=i\u0011!\u0004\u0006\u0002\u001d\u0005)1oY1mC&\u0011\u0001#\u0004\u0002\u0007\u0003:L(+\u001a4\u0002\r\u0011Jg.\u001b;%)\u0005\u0019\u0002C\u0001\u0007\u0015\u0013\t)RB\u0001\u0003V]&$\u0018\u0001C(qi&|g.R9\u0016\u0005ayBCA\r)!\rQ2$H\u0007\u0002\u000b%\u0011A$\u0002\u0002\t\u001fB$\u0018n\u001c8FcB\u0011ad\b\u0007\u0001\t\u0015\u0001#A1\u0001\"\u0005\u0005\t\u0015C\u0001\u0012&!\ta1%\u0003\u0002%\u001b\t9aj\u001c;iS:<\u0007C\u0001\u0007'\u0013\t9SBA\u0002B]fDq!\u000b\u0002\u0002\u0002\u0003\u000f!&\u0001\u0006fm&$WM\\2fI]\u00022aK\u001c\u001e\u001d\taCG\u0004\u0002.e9\u0011a&M\u0007\u0002_)\u0011\u0001'C\u0001\u0007yI|w\u000e\u001e \n\u0003!I!aM\u0004\u0002\u000f\u0005dw-\u001a2sC&\u0011QGN\u0001\ba\u0006\u001c7.Y4f\u0015\t\u0019t!\u0003\u00029s\t\u0011Q)\u001d\u0006\u0003kY\nAb\u00149uS>tWj\u001c8pS\u0012,\"\u0001P!\u0015\u0005u\u0012\u0005c\u0001\u000e?\u0001&\u0011q(\u0002\u0002\r\u001fB$\u0018n\u001c8N_:|\u0017\u000e\u001a\t\u0003=\u0005#Q\u0001I\u0002C\u0002\u0005BqaQ\u0002\u0002\u0002\u0003\u000fA)\u0001\u0006fm&$WM\\2fIa\u00022aK#A\u0013\t1\u0015HA\u0005TK6LwM]8va\u0002"
)
public interface OptionInstances0 {
   // $FF: synthetic method
   static OptionEq OptionEq$(final OptionInstances0 $this, final Eq evidence$7) {
      return $this.OptionEq(evidence$7);
   }

   default OptionEq OptionEq(final Eq evidence$7) {
      return new OptionEq(evidence$7);
   }

   // $FF: synthetic method
   static OptionMonoid OptionMonoid$(final OptionInstances0 $this, final Semigroup evidence$8) {
      return $this.OptionMonoid(evidence$8);
   }

   default OptionMonoid OptionMonoid(final Semigroup evidence$8) {
      return new OptionMonoid(evidence$8);
   }

   static void $init$(final OptionInstances0 $this) {
   }
}
