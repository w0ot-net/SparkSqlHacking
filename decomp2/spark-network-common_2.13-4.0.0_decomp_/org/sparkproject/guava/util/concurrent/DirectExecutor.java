package org.sparkproject.guava.util.concurrent;

import java.util.concurrent.Executor;
import org.sparkproject.guava.annotations.GwtCompatible;

@ElementTypesAreNonnullByDefault
@GwtCompatible
enum DirectExecutor implements Executor {
   INSTANCE;

   public void execute(Runnable command) {
      command.run();
   }

   public String toString() {
      return "MoreExecutors.directExecutor()";
   }

   // $FF: synthetic method
   private static DirectExecutor[] $values() {
      return new DirectExecutor[]{INSTANCE};
   }
}
