package breeze.stats.distributions;

import breeze.linalg.DenseVector;
import java.io.Serializable;
import scala.Option;
import scala.Some;
import scala.Tuple4;
import scala.None.;
import scala.collection.immutable.IndexedSeq;
import scala.runtime.ModuleSerializationProxy;

public final class AliasTable$ implements Serializable {
   public static final AliasTable$ MODULE$ = new AliasTable$();

   public final String toString() {
      return "AliasTable";
   }

   public AliasTable apply(final DenseVector probs, final DenseVector aliases, final IndexedSeq outcomes, final RandBasis rand) {
      return new AliasTable(probs, aliases, outcomes, rand);
   }

   public Option unapply(final AliasTable x$0) {
      return (Option)(x$0 == null ? .MODULE$ : new Some(new Tuple4(x$0.probs(), x$0.aliases(), x$0.outcomes(), x$0.rand())));
   }

   private Object writeReplace() {
      return new ModuleSerializationProxy(AliasTable$.class);
   }

   private AliasTable$() {
   }
}
