package scala.collection;

import scala.collection.generic.IsSeq;

public final class Searching$ {
   public static final Searching$ MODULE$ = new Searching$();

   /** @deprecated */
   public SeqOps search(final Object coll, final IsSeq fr) {
      return (SeqOps)fr.conversion().apply(coll);
   }

   private Searching$() {
   }
}
