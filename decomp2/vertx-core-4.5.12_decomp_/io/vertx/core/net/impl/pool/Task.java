package io.vertx.core.net.impl.pool;

public abstract class Task {
   private Task next;

   public Task replaceNext(Task next) {
      Task oldNext = this.next;
      this.next = next;
      return oldNext;
   }

   public Task last() {
      Task current;
      Task next;
      for(current = this; (next = current.next) != null; current = next) {
      }

      return current;
   }

   public Task next() {
      return this.next;
   }

   public void next(Task next) {
      this.next = next;
   }

   protected final void runNextTasks() {
      Task next;
      for(Task task = this; task != null; task = next) {
         task.run();
         next = task.next;
         task.next = null;
      }

   }

   public abstract void run();
}
