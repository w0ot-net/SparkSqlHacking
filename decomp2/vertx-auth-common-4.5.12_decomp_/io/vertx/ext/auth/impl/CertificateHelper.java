package io.vertx.ext.auth.impl;

import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SignatureException;
import java.security.cert.CertificateException;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;

public final class CertificateHelper {
   private static final Logger LOG = LoggerFactory.getLogger(CertificateHelper.class);

   private CertificateHelper() {
   }

   public static void checkValidity(List certificates, List crls) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchProviderException {
      checkValidity(certificates, true, crls);
   }

   public static void checkValidity(List certificates, boolean withRootCA, List crls) throws CertificateException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, NoSuchProviderException {
      if (certificates != null && certificates.size() != 0) {
         long now = System.currentTimeMillis();

         for(int i = 0; i < certificates.size(); ++i) {
            X509Certificate subjectCert = (X509Certificate)certificates.get(i);
            subjectCert.checkValidity();
            if (crls != null) {
               for(X509CRL crl : crls) {
                  if (crl.getNextUpdate().getTime() < now) {
                     LOG.warn("CRL is out of date nextUpdate < now");
                  }

                  if (crl.isRevoked(subjectCert)) {
                     throw new CertificateException("Certificate is revoked");
                  }
               }
            }

            if (certificates.size() == 1) {
               return;
            }

            if (i + 1 < certificates.size()) {
               X509Certificate issuerCert = (X509Certificate)certificates.get(i + 1);
               if (!subjectCert.getIssuerX500Principal().equals(issuerCert.getSubjectX500Principal())) {
                  throw new CertificateException("Certificate path issuers dont match: [" + subjectCert.getIssuerX500Principal() + "] != [" + issuerCert.getSubjectX500Principal() + "]");
               }

               subjectCert.verify(issuerCert.getPublicKey());
            }
         }

         if (withRootCA) {
            X509Certificate root = (X509Certificate)certificates.get(certificates.size() - 1);
            root.verify(root.getPublicKey());
         }

      } else {
         throw new CertificateException("empty chain");
      }
   }

   public static CertInfo getCertInfo(X509Certificate cert) {
      String subject = cert.getSubjectX500Principal().getName("RFC2253");
      Map<String, String> sub = null;
      if (subject != null && !"".equals(subject)) {
         try {
            LdapName rfc2253 = new LdapName(subject);
            sub = new HashMap();

            for(int i = 0; i < rfc2253.size(); ++i) {
               String value = rfc2253.get(i);
               int idx = value.indexOf(61);
               if (idx != -1) {
                  sub.put(value.substring(0, idx), value.substring(idx + 1));
               } else {
                  sub.put(value, (Object)null);
               }
            }
         } catch (InvalidNameException var7) {
         }
      }

      return new CertInfo(sub, cert.getVersion(), cert.getBasicConstraints());
   }

   public static final class CertInfo {
      private final Map subject;
      private final int version;
      private final int basicConstraintsCA;

      private CertInfo(Map subject, int version, int basicConstraintsCA) {
         this.subject = subject;
         this.version = version;
         this.basicConstraintsCA = basicConstraintsCA;
      }

      public boolean subjectHas(String key) {
         return this.subject != null ? this.subject.containsKey(key) : false;
      }

      public @Nullable String subject(String key) {
         return this.subject != null ? (String)this.subject.get(key) : null;
      }

      public int version() {
         return this.version;
      }

      public int basicConstraintsCA() {
         return this.basicConstraintsCA;
      }

      public boolean isEmpty() {
         return this.subject != null ? this.subject.isEmpty() : true;
      }
   }
}
