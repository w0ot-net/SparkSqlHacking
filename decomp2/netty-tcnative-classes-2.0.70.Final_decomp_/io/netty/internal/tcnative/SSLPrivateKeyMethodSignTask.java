package io.netty.internal.tcnative;

final class SSLPrivateKeyMethodSignTask extends SSLPrivateKeyMethodTask {
   private final int signatureAlgorithm;
   private final byte[] digest;

   SSLPrivateKeyMethodSignTask(long ssl, int signatureAlgorithm, byte[] digest, AsyncSSLPrivateKeyMethod method) {
      super(ssl, method);
      this.signatureAlgorithm = signatureAlgorithm;
      this.digest = digest;
   }

   protected void runTask(long ssl, AsyncSSLPrivateKeyMethod method, ResultCallback resultCallback) {
      method.sign(ssl, this.signatureAlgorithm, this.digest, resultCallback);
   }
}
