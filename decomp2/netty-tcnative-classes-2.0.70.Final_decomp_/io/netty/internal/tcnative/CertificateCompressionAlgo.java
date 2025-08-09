package io.netty.internal.tcnative;

public interface CertificateCompressionAlgo {
   int TLS_EXT_CERT_COMPRESSION_ZLIB = NativeStaticallyReferencedJniMethods.tlsExtCertCompressionZlib();
   int TLS_EXT_CERT_COMPRESSION_BROTLI = NativeStaticallyReferencedJniMethods.tlsExtCertCompressionBrotli();
   int TLS_EXT_CERT_COMPRESSION_ZSTD = NativeStaticallyReferencedJniMethods.tlsExtCertCompressionZstd();

   byte[] compress(long var1, byte[] var3) throws Exception;

   byte[] decompress(long var1, int var3, byte[] var4) throws Exception;

   int algorithmId();
}
