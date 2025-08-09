package scala.collection.parallel.mutable;

import scala.collection.parallel.Combiner;
import scala.reflect.ScalaSignature;

@ScalaSignature(
   bytes = "\u0006\u0005\u00153\u0001\u0002B\u0003\u0011\u0002\u0007\u0005Q!\u0004\u0005\u0006[\u0001!\tA\f\u0005\u0006e\u0001!\ta\r\u0005\u0006\u0001\u0002!\t%\u0011\u0002\u0013!\u0006\u0014HK]5f\u001b\u0006\u00048i\\7cS:,'O\u0003\u0002\u0007\u000f\u00059Q.\u001e;bE2,'B\u0001\u0005\n\u0003!\u0001\u0018M]1mY\u0016d'B\u0001\u0006\f\u0003)\u0019w\u000e\u001c7fGRLwN\u001c\u0006\u0002\u0019\u0005)1oY1mCV\u0019a\u0002H\u0014\u0014\u0007\u0001y1\u0003\u0005\u0002\u0011#5\t1\"\u0003\u0002\u0013\u0017\t1\u0011I\\=SK\u001a\u0004B\u0001F\u000b\u0018S5\tq!\u0003\u0002\u0017\u000f\tA1i\\7cS:,'\u000f\u0005\u0003\u00111i1\u0013BA\r\f\u0005\u0019!V\u000f\u001d7feA\u00111\u0004\b\u0007\u0001\t\u0015i\u0002A1\u0001 \u0005\u0005Y5\u0001A\t\u0003A\r\u0002\"\u0001E\u0011\n\u0005\tZ!a\u0002(pi\"Lgn\u001a\t\u0003!\u0011J!!J\u0006\u0003\u0007\u0005s\u0017\u0010\u0005\u0002\u001cO\u0011)\u0001\u0006\u0001b\u0001?\t\ta\u000b\u0005\u0003+Wi1S\"A\u0003\n\u00051*!A\u0003)beR\u0013\u0018.Z'ba\u00061A%\u001b8ji\u0012\"\u0012a\f\t\u0003!AJ!!M\u0006\u0003\tUs\u0017\u000e^\u0001\bG>l'-\u001b8f+\r!tg\u000f\u000b\u0003ky\u0002B\u0001F\u000b7uA\u00111d\u000e\u0003\u0006q\t\u0011\r!\u000f\u0002\u0002\u001dF\u0011\u0001e\u0006\t\u00037m\"Q\u0001\u0010\u0002C\u0002u\u0012QAT3x)>\f\"!K\u0012\t\u000b}\u0012\u0001\u0019A\u001b\u0002\u000b=$\b.\u001a:\u0002\u0017\r\fgNQ3TQ\u0006\u0014X\rZ\u000b\u0002\u0005B\u0011\u0001cQ\u0005\u0003\t.\u0011qAQ8pY\u0016\fg\u000e"
)
public interface ParTrieMapCombiner extends Combiner {
   // $FF: synthetic method
   static Combiner combine$(final ParTrieMapCombiner $this, final Combiner other) {
      return $this.combine(other);
   }

   default Combiner combine(final Combiner other) {
      if (this == other) {
         return this;
      } else {
         throw new UnsupportedOperationException("This shouldn't have been called in the first place.");
      }
   }

   // $FF: synthetic method
   static boolean canBeShared$(final ParTrieMapCombiner $this) {
      return $this.canBeShared();
   }

   default boolean canBeShared() {
      return true;
   }

   static void $init$(final ParTrieMapCombiner $this) {
   }
}
