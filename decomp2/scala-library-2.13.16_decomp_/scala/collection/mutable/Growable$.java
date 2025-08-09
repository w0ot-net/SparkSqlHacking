package scala.collection.mutable;

import scala.collection.IterableOnce;

public final class Growable$ {
   public static final Growable$ MODULE$ = new Growable$();

   public Growable from(final Growable empty, final IterableOnce it) {
      if (empty == null) {
         throw null;
      } else {
         return empty.addAll(it);
      }
   }

   private Growable$() {
   }
}
