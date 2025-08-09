package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.Queue;
import javax.annotation.CheckForNull;

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
