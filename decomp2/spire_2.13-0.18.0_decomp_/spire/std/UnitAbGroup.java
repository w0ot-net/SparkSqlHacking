package spire.std;

import cats.kernel.CommutativeGroup;
import scala.reflect.ScalaSignature;
import scala.runtime.BoxedUnit;

@ScalaSignature(
   bytes = "\u0006\u0005M2q!\u0002\u0004\u0011\u0002\u0007\u00051\u0002C\u0003&\u0001\u0011\u0005a\u0005C\u0003(\u0001\u0011\u0005\u0003\u0006C\u0003,\u0001\u0011\u0005C\u0006C\u0003.\u0001\u0011\u0005cFA\u0006V]&$\u0018IY$s_V\u0004(BA\u0004\t\u0003\r\u0019H\u000f\u001a\u0006\u0002\u0013\u0005)1\u000f]5sK\u000e\u00011c\u0001\u0001\r%A\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\u00042aE\u0010#\u001d\t!BD\u0004\u0002\u001659\u0011a#G\u0007\u0002/)\u0011\u0001DC\u0001\u0007yI|w\u000e\u001e \n\u0003%I!a\u0007\u0005\u0002\u000f\u0005dw-\u001a2sC&\u0011QDH\u0001\ba\u0006\u001c7.Y4f\u0015\tY\u0002\"\u0003\u0002!C\t9\u0011IY$s_V\u0004(BA\u000f\u001f!\ti1%\u0003\u0002%\u001d\t!QK\\5u\u0003\u0019!\u0013N\\5uIQ\t!%A\u0004j]Z,'o]3\u0015\u0005\tJ\u0003\"\u0002\u0016\u0003\u0001\u0004\u0011\u0013!A1\u0002\u000b\u0015l\u0007\u000f^=\u0016\u0003\t\nqaY8nE&tW\rF\u0002#_EBQ\u0001\r\u0003A\u0002\t\n\u0011\u0001\u001f\u0005\u0006e\u0011\u0001\rAI\u0001\u0002s\u0002"
)
public interface UnitAbGroup extends CommutativeGroup {
   // $FF: synthetic method
   static void inverse$(final UnitAbGroup $this, final BoxedUnit a) {
      $this.inverse(a);
   }

   default void inverse(final BoxedUnit a) {
   }

   // $FF: synthetic method
   static void empty$(final UnitAbGroup $this) {
      $this.empty();
   }

   default void empty() {
   }

   // $FF: synthetic method
   static void combine$(final UnitAbGroup $this, final BoxedUnit x, final BoxedUnit y) {
      $this.combine(x, y);
   }

   default void combine(final BoxedUnit x, final BoxedUnit y) {
   }

   static void $init$(final UnitAbGroup $this) {
   }
}
