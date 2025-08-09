package spire.optional;

import cats.kernel.Semigroup;
import scala.collection.Factory;
import scala.collection.IterableOps;
import scala.collection.Iterator;
import scala.collection.mutable.Builder;
import scala.reflect.ScalaSignature;
import spire.algebra.partial.Semigroupoid;
import spire.util.Opt;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005\u00054AAB\u0004\u0003\u0019!AA\u0007\u0001B\u0001B\u0003-Q\u0007\u0003\u00059\u0001\t\u0005\t\u0015a\u0003:\u0011\u00159\u0005\u0001\"\u0001I\u0011\u0015q\u0005\u0001\"\u0011P\u0011\u00159\u0006\u0001\"\u0001Y\u0005QIE/\u001a:bE2,7+Z7jOJ|W\u000f]8jI*\u0011\u0001\"C\u0001\t_B$\u0018n\u001c8bY*\t!\"A\u0003ta&\u0014Xm\u0001\u0001\u0016\u00075YcdE\u0002\u0001\u001dQ\u0001\"a\u0004\n\u000e\u0003AQ\u0011!E\u0001\u0006g\u000e\fG.Y\u0005\u0003'A\u0011a!\u00118z%\u00164\u0007cA\u000b\u001b95\taC\u0003\u0002\u00181\u00059\u0001/\u0019:uS\u0006d'BA\r\n\u0003\u001d\tGnZ3ce\u0006L!a\u0007\f\u0003\u0019M+W.[4s_V\u0004x.\u001b3\u0011\u0005uqB\u0002\u0001\u0003\u0006?\u0001\u0011\r\u0001\t\u0002\u0003'\u0006\u000b\"!\t\u0013\u0011\u0005=\u0011\u0013BA\u0012\u0011\u0005\u001dqu\u000e\u001e5j]\u001e\u0004R!\n\u0015+cqi\u0011A\n\u0006\u0003OA\t!bY8mY\u0016\u001cG/[8o\u0013\tIcEA\u0006Ji\u0016\u0014\u0018M\u00197f\u001fB\u001c\bCA\u000f,\t\u0015a\u0003A1\u0001.\u0005\u0005\t\u0015CA\u0011/!\tyq&\u0003\u00021!\t\u0019\u0011I\\=\u0011\u0005\u0015\u0012\u0014BA\u001a'\u0005!IE/\u001a:bE2,\u0017aA2cMB!QE\u000e\u0016\u001d\u0013\t9dEA\u0004GC\u000e$xN]=\u0002\u0003\u0005\u00032A\u000f#+\u001d\tY$I\u0004\u0002=\u0003:\u0011Q\bQ\u0007\u0002})\u0011qhC\u0001\u0007yI|w\u000e\u001e \n\u0003)I!!G\u0005\n\u0005\rC\u0012a\u00029bG.\fw-Z\u0005\u0003\u000b\u001a\u0013\u0011bU3nS\u001e\u0014x.\u001e9\u000b\u0005\rC\u0012A\u0002\u001fj]&$h\bF\u0001J)\rQE*\u0014\t\u0005\u0017\u0002QC$D\u0001\b\u0011\u0015!4\u0001q\u00016\u0011\u0015A4\u0001q\u0001:\u0003-y\u0007/S:EK\u001aLg.\u001a3\u0015\u0007A\u001bV\u000b\u0005\u0002\u0010#&\u0011!\u000b\u0005\u0002\b\u0005>|G.Z1o\u0011\u0015!F\u00011\u0001\u001d\u0003\u0005A\b\"\u0002,\u0005\u0001\u0004a\u0012!A=\u0002\u0013A\f'\u000f^5bY>\u0003HcA-`AB\u0019!,\u0018\u000f\u000e\u0003mS!\u0001X\u0005\u0002\tU$\u0018\u000e\\\u0005\u0003=n\u00131a\u00149u\u0011\u0015!V\u00011\u0001\u001d\u0011\u00151V\u00011\u0001\u001d\u0001"
)
public final class IterableSemigroupoid implements Semigroupoid {
   private final Factory cbf;
   private final Semigroup A;

   public boolean opIsDefined(final IterableOps x, final IterableOps y) {
      return x.size() == y.size();
   }

   public IterableOps partialOp(final IterableOps x, final IterableOps y) {
      IterableOps var6;
      if (this.opIsDefined(x, y)) {
         Opt var10000 = .MODULE$;
         Iterator xIt = x.iterator();
         Iterator yIt = y.iterator();
         Builder builder = this.cbf.newBuilder();

         while(xIt.nonEmpty()) {
            scala.Predef..MODULE$.assert(yIt.nonEmpty());
            builder.$plus$eq(this.A.combine(xIt.next(), yIt.next()));
         }

         var6 = (IterableOps)var10000.apply(builder.result());
      } else {
         var6 = (IterableOps).MODULE$.empty();
      }

      return var6;
   }

   public IterableSemigroupoid(final Factory cbf, final Semigroup A) {
      this.cbf = cbf;
      this.A = A;
      Semigroupoid.$init$(this);
   }
}
