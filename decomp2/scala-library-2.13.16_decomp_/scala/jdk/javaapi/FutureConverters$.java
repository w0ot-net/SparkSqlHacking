package scala.jdk.javaapi;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;
import scala.concurrent.impl.FutureConvertersImpl;
import scala.util.Success;

public final class FutureConverters$ {
   public static final FutureConverters$ MODULE$ = new FutureConverters$();

   public CompletionStage asJava(final Future f) {
      if (f instanceof FutureConvertersImpl.P) {
         return ((FutureConvertersImpl.P)f).wrapped();
      } else if (f instanceof CompletionStage) {
         return (CompletionStage)f;
      } else {
         FutureConvertersImpl.CF cf = new FutureConvertersImpl.CF(f);
         f.onComplete(cf, ExecutionContext.parasitic$.MODULE$);
         return cf;
      }
   }

   public Future asScala(final CompletionStage cs) {
      if (cs instanceof FutureConvertersImpl.CF) {
         return ((FutureConvertersImpl.CF)cs).wrapped();
      } else if (cs instanceof Future) {
         return (Future)cs;
      } else {
         FutureConvertersImpl.P p = new FutureConvertersImpl.P(cs);
         CompletableFuture var10000;
         if (cs instanceof CompletableFuture) {
            CompletableFuture cf = ((CompletableFuture)cs).toCompletableFuture();
            var10000 = cf.isDone() && !cf.isCompletedExceptionally() ? cf : null;
         } else {
            var10000 = null;
         }

         CompletableFuture completedCF = var10000;
         if (completedCF != null) {
            p.tryComplete(new Success(completedCF.join()));
         } else {
            cs.handle(p);
         }

         return p;
      }
   }

   private FutureConverters$() {
   }
}
