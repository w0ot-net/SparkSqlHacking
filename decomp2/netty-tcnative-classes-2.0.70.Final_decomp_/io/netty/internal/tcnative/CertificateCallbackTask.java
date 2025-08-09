package io.netty.internal.tcnative;

final class CertificateCallbackTask extends SSLTask {
   private final byte[] keyTypeBytes;
   private final byte[][] asn1DerEncodedPrincipals;
   private final CertificateCallback callback;

   CertificateCallbackTask(long ssl, byte[] keyTypeBytes, byte[][] asn1DerEncodedPrincipals, CertificateCallback callback) {
      super(ssl);
      this.keyTypeBytes = keyTypeBytes;
      this.asn1DerEncodedPrincipals = asn1DerEncodedPrincipals;
      this.callback = callback;
   }

   protected void runTask(long ssl, SSLTask.TaskCallback taskCallback) {
      try {
         this.callback.handle(ssl, this.keyTypeBytes, this.asn1DerEncodedPrincipals);
         taskCallback.onResult(ssl, 1);
      } catch (Exception var5) {
         taskCallback.onResult(ssl, 0);
      }

   }
}
