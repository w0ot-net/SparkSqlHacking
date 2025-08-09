package io.netty.internal.tcnative;

final class SSLPrivateKeyMethodDecryptTask extends SSLPrivateKeyMethodTask {
   private final byte[] input;

   SSLPrivateKeyMethodDecryptTask(long ssl, byte[] input, AsyncSSLPrivateKeyMethod method) {
      super(ssl, method);
      this.input = input;
   }

   protected void runTask(long ssl, AsyncSSLPrivateKeyMethod method, ResultCallback resultCallback) {
      method.decrypt(ssl, this.input, resultCallback);
   }
}
