package io.netty.handler.ssl.util;

import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SuppressJava6Requirement;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.security.AccessController;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PrivilegedAction;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.util.Date;
import sun.security.x509.AlgorithmId;
import sun.security.x509.CertificateAlgorithmId;
import sun.security.x509.CertificateSerialNumber;
import sun.security.x509.CertificateSubjectName;
import sun.security.x509.CertificateValidity;
import sun.security.x509.CertificateVersion;
import sun.security.x509.CertificateX509Key;
import sun.security.x509.X500Name;
import sun.security.x509.X509CertImpl;
import sun.security.x509.X509CertInfo;

final class OpenJdkSelfSignedCertGenerator {
   private static final InternalLogger logger = InternalLoggerFactory.getInstance(OpenJdkSelfSignedCertGenerator.class);
   private static final Method CERT_INFO_SET_METHOD;
   private static final Constructor ISSUER_NAME_CONSTRUCTOR;
   private static final Constructor CERT_IMPL_CONSTRUCTOR;
   private static final Method CERT_IMPL_GET_METHOD;
   private static final Method CERT_IMPL_SIGN_METHOD;

   @SuppressJava6Requirement(
      reason = "Usage guarded by dependency check"
   )
   static String[] generate(String fqdn, KeyPair keypair, SecureRandom random, Date notBefore, Date notAfter, String algorithm) throws Exception {
      if (CERT_INFO_SET_METHOD != null && ISSUER_NAME_CONSTRUCTOR != null && CERT_IMPL_CONSTRUCTOR != null && CERT_IMPL_GET_METHOD != null && CERT_IMPL_SIGN_METHOD != null) {
         PrivateKey key = keypair.getPrivate();
         X509CertInfo info = new X509CertInfo();
         X500Name owner = new X500Name("CN=" + fqdn);
         CERT_INFO_SET_METHOD.invoke(info, "version", new CertificateVersion(2));
         CERT_INFO_SET_METHOD.invoke(info, "serialNumber", new CertificateSerialNumber(new BigInteger(64, random)));

         try {
            CERT_INFO_SET_METHOD.invoke(info, "subject", new CertificateSubjectName(owner));
         } catch (InvocationTargetException ex) {
            if (!(ex.getCause() instanceof CertificateException)) {
               throw ex;
            }

            CERT_INFO_SET_METHOD.invoke(info, "subject", owner);
         }

         try {
            CERT_INFO_SET_METHOD.invoke(info, "issuer", ISSUER_NAME_CONSTRUCTOR.newInstance(owner));
         } catch (InvocationTargetException ex) {
            if (!(ex.getCause() instanceof CertificateException)) {
               throw ex;
            }

            CERT_INFO_SET_METHOD.invoke(info, "issuer", owner);
         }

         CERT_INFO_SET_METHOD.invoke(info, "validity", new CertificateValidity(notBefore, notAfter));
         CERT_INFO_SET_METHOD.invoke(info, "key", new CertificateX509Key(keypair.getPublic()));
         CERT_INFO_SET_METHOD.invoke(info, "algorithmID", new CertificateAlgorithmId(AlgorithmId.get("1.2.840.113549.1.1.11")));
         X509CertImpl cert = (X509CertImpl)CERT_IMPL_CONSTRUCTOR.newInstance(info);
         CERT_IMPL_SIGN_METHOD.invoke(cert, key, algorithm.equalsIgnoreCase("EC") ? "SHA256withECDSA" : "SHA256withRSA");
         CERT_INFO_SET_METHOD.invoke(info, "algorithmID.algorithm", CERT_IMPL_GET_METHOD.invoke(cert, "x509.algorithm"));
         cert = (X509CertImpl)CERT_IMPL_CONSTRUCTOR.newInstance(info);
         CERT_IMPL_SIGN_METHOD.invoke(cert, key, algorithm.equalsIgnoreCase("EC") ? "SHA256withECDSA" : "SHA256withRSA");
         cert.verify(keypair.getPublic());
         return SelfSignedCertificate.newSelfSignedCertificate(fqdn, key, cert);
      } else {
         throw new UnsupportedOperationException(OpenJdkSelfSignedCertGenerator.class.getSimpleName() + " not supported on the used JDK version");
      }
   }

   private OpenJdkSelfSignedCertGenerator() {
   }

   static {
      Method certInfoSetMethod = null;
      Constructor<?> issuerNameConstructor = null;
      Constructor<X509CertImpl> certImplConstructor = null;
      Method certImplGetMethod = null;
      Method certImplSignMethod = null;

      try {
         Object maybeCertInfoSetMethod = AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
               try {
                  return X509CertInfo.class.getMethod("set", String.class, Object.class);
               } catch (Throwable cause) {
                  return cause;
               }
            }
         });
         if (!(maybeCertInfoSetMethod instanceof Method)) {
            throw (Throwable)maybeCertInfoSetMethod;
         }

         certInfoSetMethod = (Method)maybeCertInfoSetMethod;
         Object maybeIssuerNameConstructor = AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
               try {
                  Class<?> issuerName = Class.forName("sun.security.x509.CertificateIssuerName", false, PlatformDependent.getClassLoader(OpenJdkSelfSignedCertGenerator.class));
                  return issuerName.getConstructor(X500Name.class);
               } catch (Throwable cause) {
                  return cause;
               }
            }
         });
         if (!(maybeIssuerNameConstructor instanceof Constructor)) {
            throw (Throwable)maybeIssuerNameConstructor;
         }

         issuerNameConstructor = (Constructor)maybeIssuerNameConstructor;
         Object maybeCertImplConstructor = AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
               try {
                  return X509CertImpl.class.getConstructor(X509CertInfo.class);
               } catch (Throwable cause) {
                  return cause;
               }
            }
         });
         if (!(maybeCertImplConstructor instanceof Constructor)) {
            throw (Throwable)maybeCertImplConstructor;
         }

         Constructor<X509CertImpl> constructor = (Constructor)maybeCertImplConstructor;
         certImplConstructor = constructor;
         Object maybeCertImplGetMethod = AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
               try {
                  return X509CertImpl.class.getMethod("get", String.class);
               } catch (Throwable cause) {
                  return cause;
               }
            }
         });
         if (!(maybeCertImplGetMethod instanceof Method)) {
            throw (Throwable)maybeCertImplGetMethod;
         }

         certImplGetMethod = (Method)maybeCertImplGetMethod;
         Object maybeCertImplSignMethod = AccessController.doPrivileged(new PrivilegedAction() {
            public Object run() {
               try {
                  return X509CertImpl.class.getMethod("sign", PrivateKey.class, String.class);
               } catch (Throwable cause) {
                  return cause;
               }
            }
         });
         if (!(maybeCertImplSignMethod instanceof Method)) {
            throw (Throwable)maybeCertImplSignMethod;
         }

         certImplSignMethod = (Method)maybeCertImplSignMethod;
      } catch (Throwable cause) {
         logger.debug(OpenJdkSelfSignedCertGenerator.class.getSimpleName() + " not supported", cause);
      }

      CERT_INFO_SET_METHOD = certInfoSetMethod;
      ISSUER_NAME_CONSTRUCTOR = issuerNameConstructor;
      CERT_IMPL_CONSTRUCTOR = certImplConstructor;
      CERT_IMPL_GET_METHOD = certImplGetMethod;
      CERT_IMPL_SIGN_METHOD = certImplSignMethod;
   }
}
