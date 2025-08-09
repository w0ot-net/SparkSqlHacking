package spire.algebra.partial;

import cats.kernel.Semigroup;
import scala.reflect.ScalaSignature;
import spire.util.Opt.;

@ScalaSignature(
   bytes = "\u0006\u0005M2qa\u0001\u0003\u0011\u0002\u0007\u00051\u0002C\u0003\u0013\u0001\u0011\u00051\u0003C\u0003\u0018\u0001\u0011\r\u0001DA\fTK6LwM]8va>LG\rT8x!JLwN]5us*\u0011QAB\u0001\ba\u0006\u0014H/[1m\u0015\t9\u0001\"A\u0004bY\u001e,'M]1\u000b\u0003%\tQa\u001d9je\u0016\u001c\u0001a\u0005\u0002\u0001\u0019A\u0011Q\u0002E\u0007\u0002\u001d)\tq\"A\u0003tG\u0006d\u0017-\u0003\u0002\u0012\u001d\t1\u0011I\\=SK\u001a\fa\u0001J5oSR$C#\u0001\u000b\u0011\u00055)\u0012B\u0001\f\u000f\u0005\u0011)f.\u001b;\u0002\u001b\u0019\u0014x.\\*f[&<'o\\;q+\tI\u0002\u0005\u0006\u0002\u001bSA\u00191\u0004\b\u0010\u000e\u0003\u0011I!!\b\u0003\u0003\u0019M+W.[4s_V\u0004x.\u001b3\u0011\u0005}\u0001C\u0002\u0001\u0003\u0006C\t\u0011\rA\t\u0002\u0002\u0003F\u00111E\n\t\u0003\u001b\u0011J!!\n\b\u0003\u000f9{G\u000f[5oOB\u0011QbJ\u0005\u0003Q9\u00111!\u00118z\u0011\u0015Q#\u0001q\u0001,\u0003%\u0019X-\\5he>,\b\u000fE\u0002-ayq!!\f\u0018\u000e\u0003\u0019I!a\f\u0004\u0002\u000fA\f7m[1hK&\u0011\u0011G\r\u0002\n'\u0016l\u0017n\u001a:pkBT!a\f\u0004"
)
public interface SemigroupoidLowPriority {
   // $FF: synthetic method
   static Semigroupoid fromSemigroup$(final SemigroupoidLowPriority $this, final Semigroup semigroup) {
      return $this.fromSemigroup(semigroup);
   }

   default Semigroupoid fromSemigroup(final Semigroup semigroup) {
      return new Semigroupoid(semigroup) {
         private final Semigroup semigroup$1;

         public boolean opIsDefined(final Object x, final Object y) {
            return true;
         }

         public Object partialOp(final Object x, final Object y) {
            return .MODULE$.apply(this.semigroup$1.combine(x, y));
         }

         public {
            this.semigroup$1 = semigroup$1;
            Semigroupoid.$init$(this);
         }
      };
   }

   static void $init$(final SemigroupoidLowPriority $this) {
   }
}
