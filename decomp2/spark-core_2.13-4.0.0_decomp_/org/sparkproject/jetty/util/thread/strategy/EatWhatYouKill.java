package org.sparkproject.jetty.util.thread.strategy;

import java.util.concurrent.Executor;
import org.sparkproject.jetty.util.thread.ExecutionStrategy;

/** @deprecated */
@Deprecated(
   forRemoval = true
)
public class EatWhatYouKill extends AdaptiveExecutionStrategy {
   public EatWhatYouKill(ExecutionStrategy.Producer producer, Executor executor) {
      super(producer, executor);
   }
}
