package spire.syntax.std;

import cats.kernel.Order;
import scala.collection.immutable.IndexedSeq;
import scala.runtime.BoxedUnit;
import spire.math.Searching$;

public final class IndexedSeqOps$mcV$sp extends IndexedSeqOps {
   private final IndexedSeq as;

   public int qsearch(final BoxedUnit a, final Order ev) {
      return this.qsearch$mcV$sp(a, ev);
   }

   public int qsearch$mcV$sp(final BoxedUnit a, final Order ev) {
      return Searching$.MODULE$.search$mVc$sp(this.spire$syntax$std$IndexedSeqOps$$as, a, ev);
   }

   public IndexedSeqOps$mcV$sp(final IndexedSeq as) {
      super(as);
      this.as = as;
   }
}
