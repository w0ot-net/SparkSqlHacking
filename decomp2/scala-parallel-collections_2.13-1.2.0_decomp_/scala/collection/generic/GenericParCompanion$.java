package scala.collection.generic;

import scala.collection.Factory;

public final class GenericParCompanion$ {
   public static final GenericParCompanion$ MODULE$ = new GenericParCompanion$();

   public Factory toFactory(final GenericParCompanion parFactory) {
      return new GenericParCompanion.ToFactory(parFactory);
   }

   private GenericParCompanion$() {
   }
}
