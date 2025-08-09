package org.apache.curator.shaded.com.google.common.collect;

import java.util.Queue;
import javax.annotation.CheckForNull;
import org.apache.curator.shaded.com.google.common.annotations.GwtCompatible;
import org.apache.curator.shaded.com.google.common.base.Preconditions;

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
