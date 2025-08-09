package scala.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/** @deprecated */
public final class JavaConversions$ {
   public static final JavaConversions$ MODULE$ = new JavaConversions$();

   /** @deprecated */
   public ExecutionContextExecutorService asExecutionContext(final ExecutorService exec) {
      return ExecutionContext$.MODULE$.fromExecutorService(exec);
   }

   /** @deprecated */
   public ExecutionContextExecutor asExecutionContext(final Executor exec) {
      return ExecutionContext$.MODULE$.fromExecutor(exec);
   }

   private JavaConversions$() {
   }
}
