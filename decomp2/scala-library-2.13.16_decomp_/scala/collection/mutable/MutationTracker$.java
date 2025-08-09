package scala.collection.mutable;

import java.util.ConcurrentModificationException;

public final class MutationTracker$ {
   public static final MutationTracker$ MODULE$ = new MutationTracker$();

   public void checkMutations(final int expectedCount, final int actualCount, final String message) throws ConcurrentModificationException {
      if (actualCount != expectedCount) {
         throw new ConcurrentModificationException(message);
      }
   }

   public void checkMutationsForIteration(final int expectedCount, final int actualCount) throws ConcurrentModificationException {
      this.checkMutations(expectedCount, actualCount, "mutation occurred during iteration");
   }

   private MutationTracker$() {
   }
}
