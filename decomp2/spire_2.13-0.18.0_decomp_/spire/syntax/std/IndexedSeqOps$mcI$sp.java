package spire.syntax.std;

import cats.kernel.Order;
import scala.collection.immutable.IndexedSeq;
import spire.math.Searching$;

public final class IndexedSeqOps$mcI$sp extends IndexedSeqOps {
   private final IndexedSeq as;

   public int qsearch(final int a, final Order ev) {
      return this.qsearch$mcI$sp(a, ev);
   }

   public int qsearch$mcI$sp(final int a, final Order ev) {
      return Searching$.MODULE$.search$mIc$sp(this.spire$syntax$std$IndexedSeqOps$$as, a, ev);
   }

   public IndexedSeqOps$mcI$sp(final IndexedSeq as) {
      super(as);
      this.as = as;
   }
}
