package scala.collection.generic;

import scala.collection.Factory;

public final class GenericParMapCompanion$ {
   public static final GenericParMapCompanion$ MODULE$ = new GenericParMapCompanion$();

   public Factory toFactory(final GenericParMapCompanion parFactory) {
      return new GenericParMapCompanion.ToFactory(parFactory);
   }

   private GenericParMapCompanion$() {
   }
}
