package cats.kernel;

import scala.reflect.ScalaSignature;
import scala.util.hashing.Hashing;

@ScalaSignature(
   bytes = "\u0006\u0005E2qa\u0001\u0003\u0011\u0002\u0007\u0005\u0011\u0002C\u0003\u0011\u0001\u0011\u0005\u0011\u0003C\u0003\u0016\u0001\u0011\raCA\fICNDGk\u001c%bg\"LgnZ\"p]Z,'o]5p]*\u0011QAB\u0001\u0007W\u0016\u0014h.\u001a7\u000b\u0003\u001d\tAaY1ug\u000e\u00011C\u0001\u0001\u000b!\tYa\"D\u0001\r\u0015\u0005i\u0011!B:dC2\f\u0017BA\b\r\u0005\u0019\te.\u001f*fM\u00061A%\u001b8ji\u0012\"\u0012A\u0005\t\u0003\u0017MI!\u0001\u0006\u0007\u0003\tUs\u0017\u000e^\u0001\u0018G\u0006$8oS3s]\u0016d\u0007*Y:i)>D\u0015m\u001d5j]\u001e,\"a\u0006\u0012\u0015\u0005aY\u0003cA\r\u001fA5\t!D\u0003\u0002\u001c9\u00059\u0001.Y:iS:<'BA\u000f\r\u0003\u0011)H/\u001b7\n\u0005}Q\"a\u0002%bg\"Lgn\u001a\t\u0003C\tb\u0001\u0001B\u0003$\u0005\t\u0007AEA\u0001B#\t)\u0003\u0006\u0005\u0002\fM%\u0011q\u0005\u0004\u0002\b\u001d>$\b.\u001b8h!\tY\u0011&\u0003\u0002+\u0019\t\u0019\u0011I\\=\t\u000b1\u0012\u00019A\u0017\u0002\u0005\u00154\bc\u0001\u00180A5\tA!\u0003\u00021\t\t!\u0001*Y:i\u0001"
)
public interface HashToHashingConversion {
   // $FF: synthetic method
   static Hashing catsKernelHashToHashing$(final HashToHashingConversion $this, final Hash ev) {
      return $this.catsKernelHashToHashing(ev);
   }

   default Hashing catsKernelHashToHashing(final Hash ev) {
      return new Hashing(ev) {
         private final Hash ev$84;

         public int hash(final Object x) {
            return this.ev$84.hash(x);
         }

         public {
            this.ev$84 = ev$84;
         }
      };
   }

   static void $init$(final HashToHashingConversion $this) {
   }
}
