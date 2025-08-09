package io.vertx.core.impl;

import io.netty.channel.EventLoop;
import io.vertx.core.Promise;

class WorkerTaskQueue extends TaskQueue {
   void shutdown(final EventLoop executor, final Promise completion) {
      final TaskQueue.CloseResult closeResult = this.close();

      class InterruptSequence {
         void cancelActiveTask() {
            Thread activeThread = closeResult.activeThread();
            if (activeThread != null) {
               activeThread.interrupt();
               WorkerTask activeTask = (WorkerTask)closeResult.activeTask();
               activeTask.onCompletion(() -> executor.execute(() -> this.cancelSuspended(0)));
            } else {
               this.cancelSuspended(0);
            }

         }

         void cancelSuspended(int idx) {
            int num = closeResult.suspendedThreads().size();
            if (idx < num) {
               Thread suspendedThread = (Thread)closeResult.suspendedThreads().get(idx);
               WorkerTask suspendedTask = (WorkerTask)closeResult.suspendedTasks().get(idx);
               suspendedThread.interrupt();
               suspendedTask.onCompletion(() -> executor.execute(() -> this.cancelSuspended(idx + 1)));
            } else {
               completion.complete();
            }

         }
      }

      (new InterruptSequence()).cancelActiveTask();
   }
}
