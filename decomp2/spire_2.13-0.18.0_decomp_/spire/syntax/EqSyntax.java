package spire.syntax;

import cats.kernel.Eq;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005q2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raC\u0001\u0005FcNKh\u000e^1y\u0015\t)a!\u0001\u0004ts:$\u0018\r\u001f\u0006\u0002\u000f\u0005)1\u000f]5sK\u000e\u00011C\u0001\u0001\u000b!\tYa\"D\u0001\r\u0015\u0005i\u0011!B:dC2\f\u0017BA\b\r\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012A\u0005\t\u0003\u0017MI!\u0001\u0006\u0007\u0003\tUs\u0017\u000e^\u0001\u0006KF|\u0005o]\u000b\u0003/}!\"\u0001\u0007\u001e\u0015\u0005eA\u0003c\u0001\u000e\u001c;5\tA!\u0003\u0002\u001d\t\t)Q)](qgB\u0011ad\b\u0007\u0001\t\u0015\u0001#A1\u0001\"\u0005\u0005\t\u0015C\u0001\u0012&!\tY1%\u0003\u0002%\u0019\t9aj\u001c;iS:<\u0007CA\u0006'\u0013\t9CBA\u0002B]fDq!\u000b\u0002\u0002\u0002\u0003\u000f!&\u0001\u0006fm&$WM\\2fIE\u00022aK\u001c\u001e\u001d\taCG\u0004\u0002.e9\u0011a&M\u0007\u0002_)\u0011\u0001\u0007C\u0001\u0007yI|w\u000e\u001e \n\u0003\u001dI!a\r\u0004\u0002\u000f\u0005dw-\u001a2sC&\u0011QGN\u0001\ba\u0006\u001c7.Y4f\u0015\t\u0019d!\u0003\u00029s\t\u0011Q)\u001d\u0006\u0003kYBQa\u000f\u0002A\u0002u\t\u0011!\u0019"
)
public interface EqSyntax {
   // $FF: synthetic method
   static EqOps eqOps$(final EqSyntax $this, final Object a, final Eq evidence$1) {
      return $this.eqOps(a, evidence$1);
   }

   default EqOps eqOps(final Object a, final Eq evidence$1) {
      return new EqOps(a, evidence$1);
   }

   static void $init$(final EqSyntax $this) {
   }
}
