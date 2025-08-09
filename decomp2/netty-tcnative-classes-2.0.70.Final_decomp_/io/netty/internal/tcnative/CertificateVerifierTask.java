package io.netty.internal.tcnative;

final class CertificateVerifierTask extends SSLTask {
   private final byte[][] x509;
   private final String authAlgorithm;
   private final CertificateVerifier verifier;

   CertificateVerifierTask(long ssl, byte[][] x509, String authAlgorithm, CertificateVerifier verifier) {
      super(ssl);
      this.x509 = x509;
      this.authAlgorithm = authAlgorithm;
      this.verifier = verifier;
   }

   protected void runTask(long ssl, SSLTask.TaskCallback callback) {
      int result = this.verifier.verify(ssl, this.x509, this.authAlgorithm);
      callback.onResult(ssl, result);
   }
}
