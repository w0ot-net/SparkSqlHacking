package spire.syntax.std;

import cats.kernel.Order;
import scala.collection.immutable.IndexedSeq;
import spire.math.Searching$;

public final class IndexedSeqOps$mcZ$sp extends IndexedSeqOps {
   private final IndexedSeq as;

   public int qsearch(final boolean a, final Order ev) {
      return this.qsearch$mcZ$sp(a, ev);
   }

   public int qsearch$mcZ$sp(final boolean a, final Order ev) {
      return Searching$.MODULE$.search$mZc$sp(this.spire$syntax$std$IndexedSeqOps$$as, a, ev);
   }

   public IndexedSeqOps$mcZ$sp(final IndexedSeq as) {
      super(as);
      this.as = as;
   }
}
