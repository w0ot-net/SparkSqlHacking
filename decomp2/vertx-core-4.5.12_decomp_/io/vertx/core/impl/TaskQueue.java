package io.vertx.core.impl;

import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.function.Consumer;

public class TaskQueue {
   static final Logger log = LoggerFactory.getLogger(TaskQueue.class);
   private final LinkedList tasks = new LinkedList();
   private final Set continuations = new HashSet();
   private boolean closed;
   private Executor currentExecutor;
   private Thread currentThread;
   private ExecuteTask currentTask;
   private final Runnable runner = this::run;

   private void run() {
      while(true) {
         ExecuteTask execute;
         synchronized(this.tasks) {
            Task task = (Task)this.tasks.poll();
            if (task == null) {
               this.currentExecutor = null;
               return;
            }

            if (task instanceof ContinuationTask) {
               ContinuationTask resume = (ContinuationTask)task;
               this.currentExecutor = resume.executor;
               this.currentThread = resume.thread;
               this.currentTask = resume.task;
               resume.latch.run();
               return;
            }

            execute = (ExecuteTask)task;
            if (execute.exec != this.currentExecutor) {
               this.tasks.addFirst(execute);
               execute.exec.execute(this.runner);
               this.currentExecutor = execute.exec;
               return;
            }
         }

         try {
            this.currentThread = Thread.currentThread();
            this.currentTask = execute;
            execute.runnable.run();
         } catch (Throwable t) {
            log.error("Caught unexpected Throwable", t);
         } finally {
            this.currentThread = null;
            this.currentTask = null;
         }
      }
   }

   private ContinuationTask continuationTask() {
      ExecuteTask task;
      Thread thread;
      Executor executor;
      synchronized(this.tasks) {
         if (Thread.currentThread() != this.currentThread) {
            throw new IllegalStateException();
         }

         thread = this.currentThread;
         executor = this.currentExecutor;
         task = this.currentTask;
      }

      return new ContinuationTask(task, thread, executor);
   }

   public void execute(Runnable task, Executor executor) throws RejectedExecutionException {
      synchronized(this.tasks) {
         if (this.currentExecutor == null) {
            this.currentExecutor = executor;

            try {
               executor.execute(this.runner);
            } catch (RejectedExecutionException e) {
               this.currentExecutor = null;
               throw e;
            }
         }

         this.tasks.add(new ExecuteTask(task, executor));
      }
   }

   public boolean isEmpty() {
      synchronized(this.tasks) {
         return this.tasks.isEmpty() && this.currentExecutor == null;
      }
   }

   public CloseResult close() {
      List<Thread> suspendedThreads;
      List<Runnable> suspendedTasks;
      Thread activeThread;
      Runnable activeTask;
      synchronized(this.tasks) {
         if (this.closed) {
            throw new IllegalStateException("Already closed");
         }

         suspendedThreads = new ArrayList(this.continuations.size());
         suspendedTasks = new ArrayList(this.continuations.size());
         Iterator<Task> it = this.tasks.iterator();

         while(it.hasNext()) {
            Task task = (Task)it.next();
            if (task instanceof ContinuationTask) {
               ContinuationTask continuationTask = (ContinuationTask)task;
               suspendedThreads.add(continuationTask.thread);
               suspendedTasks.add(continuationTask.task.runnable);
               it.remove();
            }
         }

         for(ContinuationTask cont : this.continuations) {
            suspendedThreads.add(cont.thread);
            suspendedTasks.add(cont.task.runnable);
         }

         this.continuations.clear();
         activeThread = this.currentThread;
         activeTask = this.currentTask != null ? this.currentTask.runnable : null;
         this.currentExecutor = null;
         this.closed = true;
      }

      return new CloseResult(activeThread, activeTask, suspendedThreads, suspendedTasks);
   }

   public CountDownLatch suspend() {
      return this.suspend((cont) -> {
      });
   }

   public CountDownLatch suspend(Consumer abc) {
      ContinuationTask continuationTask = this.continuationTask();
      abc.accept(continuationTask);
      return continuationTask.suspend() ? continuationTask : null;
   }

   public static final class CloseResult {
      private final Thread activeThread;
      private final Runnable activeTask;
      private final List suspendedTasks;
      private final List suspendedThreads;

      private CloseResult(Thread activeThread, Runnable activeTask, List suspendedThreads, List suspendedTasks) {
         this.activeThread = activeThread;
         this.activeTask = activeTask;
         this.suspendedThreads = suspendedThreads;
         this.suspendedTasks = suspendedTasks;
      }

      public Thread activeThread() {
         return this.activeThread;
      }

      public Runnable activeTask() {
         return this.activeTask;
      }

      public List suspendedThreads() {
         return this.suspendedThreads;
      }

      public List suspendedTasks() {
         return this.suspendedTasks;
      }
   }

   private class ContinuationTask extends CountDownLatch implements WorkerExecutor.Continuation, Task {
      private static final int ST_CREATED = 0;
      private static final int ST_SUSPENDED = 1;
      private static final int ST_RESUMED = 2;
      private final ExecuteTask task;
      private final Thread thread;
      private final Executor executor;
      private int status;
      private Runnable latch;

      public ContinuationTask(ExecuteTask task, Thread thread, Executor executor) {
         super(1);
         this.task = task;
         this.thread = thread;
         this.executor = executor;
         this.status = 0;
      }

      public void resume(Runnable callback) {
         synchronized(TaskQueue.this.tasks) {
            if (TaskQueue.this.closed) {
               return;
            }

            switch (this.status) {
               case 0:
                  assert TaskQueue.this.currentExecutor == this.executor;

                  assert TaskQueue.this.currentThread == this.thread;

                  assert TaskQueue.this.currentTask == this.task;

                  this.latch = callback;
                  break;
               case 1:
                  boolean removed = TaskQueue.this.continuations.remove(this);

                  assert removed;

                  this.latch = () -> {
                     callback.run();
                     this.countDown();
                  };
                  if (TaskQueue.this.currentExecutor != null) {
                     TaskQueue.this.tasks.addFirst(this);
                     return;
                  }

                  TaskQueue.this.currentExecutor = this.executor;
                  TaskQueue.this.currentThread = this.thread;
                  TaskQueue.this.currentTask = this.task;
                  break;
               default:
                  throw new IllegalStateException();
            }

            this.status = 2;
         }

         this.latch.run();
      }

      public boolean suspend() {
         if (Thread.currentThread() != this.thread) {
            throw new IllegalStateException();
         } else {
            synchronized(TaskQueue.this.tasks) {
               if (TaskQueue.this.closed) {
                  return false;
               }

               if (TaskQueue.this.currentThread == null || TaskQueue.this.currentThread != this.thread) {
                  throw new IllegalStateException();
               }

               switch (this.status) {
                  case 1:
                     throw new IllegalStateException();
                  case 2:
                     this.countDown();
                     return false;
               }

               this.status = 1;
               boolean added = TaskQueue.this.continuations.add(this);

               assert added;

               TaskQueue.this.currentThread = null;
               TaskQueue.this.currentTask = null;
            }

            this.executor.execute(TaskQueue.this.runner);
            return true;
         }
      }
   }

   private static class ExecuteTask implements Task {
      private final Runnable runnable;
      private final Executor exec;

      public ExecuteTask(Runnable runnable, Executor exec) {
         this.runnable = runnable;
         this.exec = exec;
      }
   }

   private interface Task {
   }
}
