package org.sparkproject.guava.collect;

import java.util.Queue;
import javax.annotation.CheckForNull;
import org.sparkproject.guava.annotations.GwtCompatible;
import org.sparkproject.guava.base.Preconditions;

@ElementTypesAreNonnullByDefault
@GwtCompatible
final class ConsumingQueueIterator extends AbstractIterator {
   private final Queue queue;

   ConsumingQueueIterator(Queue queue) {
      this.queue = (Queue)Preconditions.checkNotNull(queue);
   }

   @CheckForNull
   protected Object computeNext() {
      return this.queue.isEmpty() ? this.endOfData() : this.queue.remove();
   }
}
