package cats.kernel.instances;

import cats.kernel.Hash;
import scala.collection.immutable.List;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005y2A\u0001B\u0003\u0001\u0019!A\u0001\u0007\u0001B\u0001B\u0003-\u0011\u0007C\u00033\u0001\u0011\u00051\u0007C\u00038\u0001\u0011\u0005\u0001H\u0001\u0005MSN$\b*Y:i\u0015\t1q!A\u0005j]N$\u0018M\\2fg*\u0011\u0001\"C\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003)\tAaY1ug\u000e\u0001QCA\u0007\u0015'\r\u0001a\u0002\t\t\u0004\u001fA\u0011R\"A\u0003\n\u0005E)!A\u0002'jgR,\u0015\u000f\u0005\u0002\u0014)1\u0001A!B\u000b\u0001\u0005\u00041\"!A!\u0012\u0005]i\u0002C\u0001\r\u001c\u001b\u0005I\"\"\u0001\u000e\u0002\u000bM\u001c\u0017\r\\1\n\u0005qI\"a\u0002(pi\"Lgn\u001a\t\u00031yI!aH\r\u0003\u0007\u0005s\u0017\u0010E\u0002\"E\u0011j\u0011aB\u0005\u0003G\u001d\u0011A\u0001S1tQB\u0019Q%\f\n\u000f\u0005\u0019ZcBA\u0014+\u001b\u0005A#BA\u0015\f\u0003\u0019a$o\\8u}%\t!$\u0003\u0002-3\u00059\u0001/Y2lC\u001e,\u0017B\u0001\u00180\u0005\u0011a\u0015n\u001d;\u000b\u00051J\u0012AA3w!\r\t#EE\u0001\u0007y%t\u0017\u000e\u001e \u0015\u0003Q\"\"!\u000e\u001c\u0011\u0007=\u0001!\u0003C\u00031\u0005\u0001\u000f\u0011'\u0001\u0003iCNDGCA\u001d=!\tA\"(\u0003\u0002<3\t\u0019\u0011J\u001c;\t\u000bu\u001a\u0001\u0019\u0001\u0013\u0002\u0003a\u0004"
)
public class ListHash extends ListEq implements Hash {
   private final Hash ev;

   public int hash$mcZ$sp(final boolean x) {
      return Hash.hash$mcZ$sp$(this, x);
   }

   public int hash$mcB$sp(final byte x) {
      return Hash.hash$mcB$sp$(this, x);
   }

   public int hash$mcC$sp(final char x) {
      return Hash.hash$mcC$sp$(this, x);
   }

   public int hash$mcD$sp(final double x) {
      return Hash.hash$mcD$sp$(this, x);
   }

   public int hash$mcF$sp(final float x) {
      return Hash.hash$mcF$sp$(this, x);
   }

   public int hash$mcI$sp(final int x) {
      return Hash.hash$mcI$sp$(this, x);
   }

   public int hash$mcJ$sp(final long x) {
      return Hash.hash$mcJ$sp$(this, x);
   }

   public int hash$mcS$sp(final short x) {
      return Hash.hash$mcS$sp$(this, x);
   }

   public int hash$mcV$sp(final BoxedUnit x) {
      return Hash.hash$mcV$sp$(this, x);
   }

   public int hash(final List x) {
      return StaticMethods$.MODULE$.listHash(x, this.ev);
   }

   public ListHash(final Hash ev) {
      super(ev);
      this.ev = ev;
   }
}
