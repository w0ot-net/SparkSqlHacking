package spire.syntax.std;

import cats.kernel.Order;
import scala.collection.immutable.IndexedSeq;
import spire.math.Searching$;

public final class IndexedSeqOps$mcS$sp extends IndexedSeqOps {
   private final IndexedSeq as;

   public int qsearch(final short a, final Order ev) {
      return this.qsearch$mcS$sp(a, ev);
   }

   public int qsearch$mcS$sp(final short a, final Order ev) {
      return Searching$.MODULE$.search$mSc$sp(this.spire$syntax$std$IndexedSeqOps$$as, a, ev);
   }

   public IndexedSeqOps$mcS$sp(final IndexedSeq as) {
      super(as);
      this.as = as;
   }
}
