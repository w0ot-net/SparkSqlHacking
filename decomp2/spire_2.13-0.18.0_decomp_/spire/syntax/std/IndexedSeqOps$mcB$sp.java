package spire.syntax.std;

import cats.kernel.Order;
import scala.collection.immutable.IndexedSeq;
import spire.math.Searching$;

public final class IndexedSeqOps$mcB$sp extends IndexedSeqOps {
   private final IndexedSeq as;

   public int qsearch(final byte a, final Order ev) {
      return this.qsearch$mcB$sp(a, ev);
   }

   public int qsearch$mcB$sp(final byte a, final Order ev) {
      return Searching$.MODULE$.search$mBc$sp(this.spire$syntax$std$IndexedSeqOps$$as, a, ev);
   }

   public IndexedSeqOps$mcB$sp(final IndexedSeq as) {
      super(as);
      this.as = as;
   }
}
