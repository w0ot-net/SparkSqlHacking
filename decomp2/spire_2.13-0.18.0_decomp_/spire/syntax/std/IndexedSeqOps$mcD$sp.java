package spire.syntax.std;

import cats.kernel.Order;
import scala.collection.immutable.IndexedSeq;
import spire.math.Searching$;

public final class IndexedSeqOps$mcD$sp extends IndexedSeqOps {
   private final IndexedSeq as;

   public int qsearch(final double a, final Order ev) {
      return this.qsearch$mcD$sp(a, ev);
   }

   public int qsearch$mcD$sp(final double a, final Order ev) {
      return Searching$.MODULE$.search$mDc$sp(this.spire$syntax$std$IndexedSeqOps$$as, a, ev);
   }

   public IndexedSeqOps$mcD$sp(final IndexedSeq as) {
      super(as);
      this.as = as;
   }
}
