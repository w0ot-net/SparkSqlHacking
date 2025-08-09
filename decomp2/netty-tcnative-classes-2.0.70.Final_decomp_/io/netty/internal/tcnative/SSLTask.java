package io.netty.internal.tcnative;

abstract class SSLTask implements Runnable {
   private static final Runnable NOOP = new Runnable() {
      public void run() {
      }
   };
   private final long ssl;
   private int returnValue;
   private boolean complete;
   protected boolean didRun;

   protected SSLTask(long ssl) {
      this.ssl = ssl;
   }

   public final void run() {
      this.run(NOOP);
   }

   protected final void run(final Runnable completeCallback) {
      if (!this.didRun) {
         this.didRun = true;
         this.runTask(this.ssl, new TaskCallback() {
            public void onResult(long ssl, int result) {
               SSLTask.this.returnValue = result;
               SSLTask.this.complete = true;
               completeCallback.run();
            }
         });
      } else {
         completeCallback.run();
      }

   }

   protected abstract void runTask(long var1, TaskCallback var3);

   interface TaskCallback {
      void onResult(long var1, int var3);
   }
}
