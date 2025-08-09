package spire.syntax.std;

import cats.kernel.Order;
import scala.collection.immutable.IndexedSeq;
import spire.math.Searching$;

public final class IndexedSeqOps$mcC$sp extends IndexedSeqOps {
   private final IndexedSeq as;

   public int qsearch(final char a, final Order ev) {
      return this.qsearch$mcC$sp(a, ev);
   }

   public int qsearch$mcC$sp(final char a, final Order ev) {
      return Searching$.MODULE$.search$mCc$sp(this.spire$syntax$std$IndexedSeqOps$$as, a, ev);
   }

   public IndexedSeqOps$mcC$sp(final IndexedSeq as) {
      super(as);
      this.as = as;
   }
}
