package io.netty.internal.tcnative;

abstract class SSLPrivateKeyMethodTask extends SSLTask implements AsyncTask {
   private static final byte[] EMPTY = new byte[0];
   private final AsyncSSLPrivateKeyMethod method;
   private byte[] resultBytes;

   SSLPrivateKeyMethodTask(long ssl, AsyncSSLPrivateKeyMethod method) {
      super(ssl);
      this.method = method;
   }

   public final void runAsync(Runnable completeCallback) {
      this.run(completeCallback);
   }

   protected final void runTask(long ssl, final SSLTask.TaskCallback callback) {
      this.runTask(ssl, this.method, new ResultCallback() {
         public void onSuccess(long ssl, byte[] result) {
            SSLPrivateKeyMethodTask.this.resultBytes = result;
            callback.onResult(ssl, 1);
         }

         public void onError(long ssl, Throwable cause) {
            SSLPrivateKeyMethodTask.this.resultBytes = SSLPrivateKeyMethodTask.EMPTY;
            callback.onResult(ssl, 0);
         }
      });
   }

   protected abstract void runTask(long var1, AsyncSSLPrivateKeyMethod var3, ResultCallback var4);
}
