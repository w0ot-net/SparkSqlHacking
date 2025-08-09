package spire.syntax.std;

import cats.kernel.Order;
import scala.collection.immutable.IndexedSeq;
import spire.math.Searching$;

public final class IndexedSeqOps$mcJ$sp extends IndexedSeqOps {
   private final IndexedSeq as;

   public int qsearch(final long a, final Order ev) {
      return this.qsearch$mcJ$sp(a, ev);
   }

   public int qsearch$mcJ$sp(final long a, final Order ev) {
      return Searching$.MODULE$.search$mJc$sp(this.spire$syntax$std$IndexedSeqOps$$as, a, ev);
   }

   public IndexedSeqOps$mcJ$sp(final IndexedSeq as) {
      super(as);
      this.as = as;
   }
}
