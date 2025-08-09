package io.vertx.core.net.impl.pool;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.PlatformDependent;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class CombinerExecutor implements Executor {
   private final Queue q = PlatformDependent.newMpscQueue();
   private final AtomicInteger s = new AtomicInteger();
   private final Object state;
   private static final FastThreadLocal current = new FastThreadLocal();

   public CombinerExecutor(Object state) {
      this.state = state;
   }

   public void submit(Executor.Action action) {
      this.q.add(action);
      if (this.s.get() == 0 && this.s.compareAndSet(0, 1)) {
         Task head = null;
         Task tail = null;

         do {
            try {
               while(true) {
                  Executor.Action<S> a = (Executor.Action)this.q.poll();
                  if (a == null) {
                     break;
                  }

                  Task task = a.execute(this.state);
                  if (task != null) {
                     Task last = task.last();
                     if (head == null) {
                        assert tail == null;

                        tail = last;
                        head = task;
                     } else {
                        tail.next(task);
                        tail = last;
                     }
                  }
               }
            } finally {
               this.s.set(0);
            }
         } while(!this.q.isEmpty() && this.s.compareAndSet(0, 1));

         if (head != null) {
            InProgressTail<S> inProgress = (InProgressTail)current.get();
            if (inProgress == null) {
               inProgress = new InProgressTail(this, tail);
               current.set(inProgress);

               try {
                  head.runNextTasks();

                  assert inProgress.others == null || inProgress.others.isEmpty();
               } finally {
                  current.remove();
               }
            } else if (inProgress.combiner == this) {
               Task oldNextTail = inProgress.task.replaceNext(head);

               assert oldNextTail == null;

               inProgress.task = tail;
            } else {
               Map<CombinerExecutor<S>, Task> map = inProgress.others;
               if (map == null) {
                  map = inProgress.others = new HashMap(1);
               }

               Task task = (Task)map.get(this);
               if (task == null) {
                  map.put(this, tail);

                  try {
                     head.runNextTasks();
                  } finally {
                     map.remove(this);
                  }
               } else {
                  Task oldNextTail = task.replaceNext(head);

                  assert oldNextTail == null;

                  map.put(this, tail);
               }
            }
         }

      }
   }

   protected static final class InProgressTail {
      final CombinerExecutor combiner;
      Task task;
      Map others;

      public InProgressTail(CombinerExecutor combiner, Task task) {
         this.combiner = combiner;
         this.task = task;
      }
   }
}
