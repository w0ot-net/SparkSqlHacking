package io.netty.internal.tcnative;

final class AsyncSSLPrivateKeyMethodAdapter implements AsyncSSLPrivateKeyMethod {
   private final SSLPrivateKeyMethod method;

   AsyncSSLPrivateKeyMethodAdapter(SSLPrivateKeyMethod method) {
      if (method == null) {
         throw new NullPointerException("method");
      } else {
         this.method = method;
      }
   }

   public void sign(long ssl, int signatureAlgorithm, byte[] input, ResultCallback resultCallback) {
      byte[] result;
      try {
         result = this.method.sign(ssl, signatureAlgorithm, input);
      } catch (Throwable cause) {
         resultCallback.onError(ssl, cause);
         return;
      }

      resultCallback.onSuccess(ssl, result);
   }

   public void decrypt(long ssl, byte[] input, ResultCallback resultCallback) {
      byte[] result;
      try {
         result = this.method.decrypt(ssl, input);
      } catch (Throwable cause) {
         resultCallback.onError(ssl, cause);
         return;
      }

      resultCallback.onSuccess(ssl, result);
   }
}
