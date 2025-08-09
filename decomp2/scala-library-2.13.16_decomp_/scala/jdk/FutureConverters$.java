package scala.jdk;

import java.util.concurrent.CompletionStage;
import scala.concurrent.Future;

public final class FutureConverters$ {
   public static final FutureConverters$ MODULE$ = new FutureConverters$();

   public Future FutureOps(final Future f) {
      return f;
   }

   public CompletionStage CompletionStageOps(final CompletionStage cs) {
      return cs;
   }

   private FutureConverters$() {
   }
}
