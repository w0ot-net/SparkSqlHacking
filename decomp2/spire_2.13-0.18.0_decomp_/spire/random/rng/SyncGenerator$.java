package spire.random.rng;

import spire.random.Generator;

public final class SyncGenerator$ {
   public static final SyncGenerator$ MODULE$ = new SyncGenerator$();

   public SyncGenerator apply(final Generator gen) {
      return new SyncGenerator(gen);
   }

   private SyncGenerator$() {
   }
}
