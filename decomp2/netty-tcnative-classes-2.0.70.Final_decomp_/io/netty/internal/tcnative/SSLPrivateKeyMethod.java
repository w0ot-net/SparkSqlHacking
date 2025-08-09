package io.netty.internal.tcnative;

public interface SSLPrivateKeyMethod {
   int SSL_SIGN_RSA_PKCS1_SHA1 = NativeStaticallyReferencedJniMethods.sslSignRsaPkcsSha1();
   int SSL_SIGN_RSA_PKCS1_SHA256 = NativeStaticallyReferencedJniMethods.sslSignRsaPkcsSha256();
   int SSL_SIGN_RSA_PKCS1_SHA384 = NativeStaticallyReferencedJniMethods.sslSignRsaPkcsSha384();
   int SSL_SIGN_RSA_PKCS1_SHA512 = NativeStaticallyReferencedJniMethods.sslSignRsaPkcsSha512();
   int SSL_SIGN_ECDSA_SHA1 = NativeStaticallyReferencedJniMethods.sslSignEcdsaPkcsSha1();
   int SSL_SIGN_ECDSA_SECP256R1_SHA256 = NativeStaticallyReferencedJniMethods.sslSignEcdsaSecp256r1Sha256();
   int SSL_SIGN_ECDSA_SECP384R1_SHA384 = NativeStaticallyReferencedJniMethods.sslSignEcdsaSecp384r1Sha384();
   int SSL_SIGN_ECDSA_SECP521R1_SHA512 = NativeStaticallyReferencedJniMethods.sslSignEcdsaSecp521r1Sha512();
   int SSL_SIGN_RSA_PSS_RSAE_SHA256 = NativeStaticallyReferencedJniMethods.sslSignRsaPssRsaeSha256();
   int SSL_SIGN_RSA_PSS_RSAE_SHA384 = NativeStaticallyReferencedJniMethods.sslSignRsaPssRsaeSha384();
   int SSL_SIGN_RSA_PSS_RSAE_SHA512 = NativeStaticallyReferencedJniMethods.sslSignRsaPssRsaeSha512();
   int SSL_SIGN_ED25519 = NativeStaticallyReferencedJniMethods.sslSignEd25519();
   int SSL_SIGN_RSA_PKCS1_MD5_SHA1 = NativeStaticallyReferencedJniMethods.sslSignRsaPkcs1Md5Sha1();

   byte[] sign(long var1, int var3, byte[] var4) throws Exception;

   byte[] decrypt(long var1, byte[] var3) throws Exception;
}
