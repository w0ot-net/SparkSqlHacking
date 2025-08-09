package org.sparkproject.jetty.util.thread;

public interface Invocable {
   ThreadLocal __nonBlocking = new ThreadLocal();

   static Task from(InvocationType type, Runnable task) {
      return new ReadyTask(type, task);
   }

   static boolean isNonBlockingInvocation() {
      return Boolean.TRUE.equals(__nonBlocking.get());
   }

   static void invokeNonBlocking(Runnable task) {
      Boolean wasNonBlocking = (Boolean)__nonBlocking.get();

      try {
         __nonBlocking.set(Boolean.TRUE);
         task.run();
      } finally {
         __nonBlocking.set(wasNonBlocking);
      }

   }

   static InvocationType combine(InvocationType it1, InvocationType it2) {
      if (it1 != null && it2 != null) {
         if (it1 == it2) {
            return it1;
         }

         if (it1 == Invocable.InvocationType.EITHER) {
            return it2;
         }

         if (it2 == Invocable.InvocationType.EITHER) {
            return it1;
         }
      }

      return Invocable.InvocationType.BLOCKING;
   }

   static InvocationType getInvocationType(Object o) {
      return o instanceof Invocable ? ((Invocable)o).getInvocationType() : Invocable.InvocationType.BLOCKING;
   }

   default InvocationType getInvocationType() {
      return Invocable.InvocationType.BLOCKING;
   }

   public static enum InvocationType {
      BLOCKING,
      NON_BLOCKING,
      EITHER;

      // $FF: synthetic method
      private static InvocationType[] $values() {
         return new InvocationType[]{BLOCKING, NON_BLOCKING, EITHER};
      }
   }

   public static class ReadyTask implements Task {
      private final InvocationType type;
      private final Runnable task;

      public ReadyTask(InvocationType type, Runnable task) {
         this.type = type;
         this.task = task;
      }

      public void run() {
         this.task.run();
      }

      public InvocationType getInvocationType() {
         return this.type;
      }

      public String toString() {
         return String.format("%s@%x[%s|%s]", this.getClass().getSimpleName(), this.hashCode(), this.type, this.task);
      }
   }

   public interface Task extends Invocable, Runnable {
   }
}
