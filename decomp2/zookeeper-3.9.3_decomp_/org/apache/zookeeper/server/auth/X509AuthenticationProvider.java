package org.apache.zookeeper.server.auth;

import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import javax.net.ssl.X509KeyManager;
import javax.net.ssl.X509TrustManager;
import javax.security.auth.x500.X500Principal;
import javax.servlet.http.HttpServletRequest;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.common.ClientX509Util;
import org.apache.zookeeper.common.X509Exception;
import org.apache.zookeeper.common.X509Util;
import org.apache.zookeeper.common.ZKConfig;
import org.apache.zookeeper.data.Id;
import org.apache.zookeeper.server.ServerCnxn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class X509AuthenticationProvider implements AuthenticationProvider {
   public static final String X509_CERTIFICATE_ATTRIBUTE_NAME = "javax.servlet.request.X509Certificate";
   static final String ZOOKEEPER_X509AUTHENTICATIONPROVIDER_SUPERUSER = "zookeeper.X509AuthenticationProvider.superUser";
   private static final Logger LOG = LoggerFactory.getLogger(X509AuthenticationProvider.class);
   private final X509TrustManager trustManager;
   private final X509KeyManager keyManager;

   public X509AuthenticationProvider() throws X509Exception {
      ZKConfig config = new ZKConfig();
      X509Util x509Util = new ClientX509Util();

      try {
         String keyStoreLocation = config.getProperty(x509Util.getSslKeystoreLocationProperty(), "");
         String keyStorePassword = x509Util.getPasswordFromConfigPropertyOrFile(config, x509Util.getSslKeystorePasswdProperty(), x509Util.getSslKeystorePasswdPathProperty());
         String keyStoreTypeProp = config.getProperty(x509Util.getSslKeystoreTypeProperty());
         boolean crlEnabled = Boolean.parseBoolean(config.getProperty(x509Util.getSslCrlEnabledProperty()));
         boolean ocspEnabled = Boolean.parseBoolean(config.getProperty(x509Util.getSslOcspEnabledProperty()));
         boolean hostnameVerificationEnabled = Boolean.parseBoolean(config.getProperty(x509Util.getSslHostnameVerificationEnabledProperty()));
         X509KeyManager km = null;
         X509TrustManager tm = null;
         if (keyStoreLocation.isEmpty()) {
            LOG.warn("keystore not specified for client connection");
         } else {
            try {
               km = X509Util.createKeyManager(keyStoreLocation, keyStorePassword, keyStoreTypeProp);
            } catch (X509Exception.KeyManagerException e) {
               LOG.error("Failed to create key manager", e);
            }
         }

         String trustStoreLocation = config.getProperty(x509Util.getSslTruststoreLocationProperty(), "");
         String trustStorePassword = x509Util.getPasswordFromConfigPropertyOrFile(config, x509Util.getSslTruststorePasswdProperty(), x509Util.getSslTruststorePasswdPathProperty());
         String trustStoreTypeProp = config.getProperty(x509Util.getSslTruststoreTypeProperty());
         boolean fipsMode = x509Util.getFipsMode(config);
         if (trustStoreLocation.isEmpty()) {
            LOG.warn("Truststore not specified for client connection");
         } else {
            try {
               tm = X509Util.createTrustManager(trustStoreLocation, trustStorePassword, trustStoreTypeProp, crlEnabled, ocspEnabled, hostnameVerificationEnabled, false, fipsMode);
            } catch (X509Exception.TrustManagerException e) {
               LOG.error("Failed to create trust manager", e);
            }
         }

         this.keyManager = km;
         this.trustManager = tm;
      } catch (Throwable var19) {
         try {
            x509Util.close();
         } catch (Throwable var16) {
            var19.addSuppressed(var16);
         }

         throw var19;
      }

      x509Util.close();
   }

   public X509AuthenticationProvider(X509TrustManager trustManager, X509KeyManager keyManager) {
      this.trustManager = trustManager;
      this.keyManager = keyManager;
   }

   public String getScheme() {
      return "x509";
   }

   public KeeperException.Code handleAuthentication(ServerCnxn cnxn, byte[] authData) {
      List<Certificate> certs = Arrays.asList(cnxn.getClientCertificateChain());
      X509Certificate[] certChain = (X509Certificate[])certs.toArray(new X509Certificate[certs.size()]);
      Collection<Id> ids = this.handleAuthentication(certChain);
      if (ids.isEmpty()) {
         LOG.error("Failed to authenticate session 0x{}", Long.toHexString(cnxn.getSessionId()));
         return KeeperException.Code.AUTHFAILED;
      } else {
         for(Id id : ids) {
            cnxn.addAuthInfo(id);
         }

         return KeeperException.Code.OK;
      }
   }

   public List handleAuthentication(HttpServletRequest request, byte[] authData) {
      X509Certificate[] certChain = (X509Certificate[])request.getAttribute("javax.servlet.request.X509Certificate");
      return this.handleAuthentication(certChain);
   }

   protected String getClientId(X509Certificate clientCert) {
      return clientCert.getSubjectX500Principal().getName();
   }

   public boolean matches(String id, String aclExpr) {
      if (System.getProperty("zookeeper.X509AuthenticationProvider.superUser") == null) {
         return id.equals(aclExpr);
      } else {
         return id.equals(System.getProperty("zookeeper.X509AuthenticationProvider.superUser")) || id.equals(aclExpr);
      }
   }

   public boolean isAuthenticated() {
      return true;
   }

   public boolean isValid(String id) {
      try {
         new X500Principal(id);
         return true;
      } catch (IllegalArgumentException var3) {
         return false;
      }
   }

   public X509TrustManager getTrustManager() throws X509Exception.TrustManagerException {
      if (this.trustManager == null) {
         throw new X509Exception.TrustManagerException("No trust manager available");
      } else {
         return this.trustManager;
      }
   }

   public X509KeyManager getKeyManager() throws X509Exception.KeyManagerException {
      if (this.keyManager == null) {
         throw new X509Exception.KeyManagerException("No key manager available");
      } else {
         return this.keyManager;
      }
   }

   private List handleAuthentication(X509Certificate[] certChain) {
      List<Id> ids = new ArrayList();
      if (certChain != null && certChain.length != 0) {
         if (this.trustManager == null) {
            LOG.error("No trust manager available to authenticate");
            return ids;
         } else {
            X509Certificate clientCert = certChain[0];

            try {
               this.trustManager.checkClientTrusted(certChain, clientCert.getPublicKey().getAlgorithm());
            } catch (CertificateException ce) {
               LOG.error("Failed to trust certificate", ce);
               return ids;
            }

            String clientId = this.getClientId(clientCert);
            if (clientId.equals(System.getProperty("zookeeper.X509AuthenticationProvider.superUser"))) {
               ids.add(new Id("super", clientId));
               LOG.info("Authenticated Id '{}' as super user", clientId);
            }

            Id id = new Id(this.getScheme(), clientId);
            ids.add(id);
            LOG.info("Authenticated Id '{}' for scheme '{}'", id.getId(), id.getScheme());
            return Collections.unmodifiableList(ids);
         }
      } else {
         LOG.warn("No certificate chain available to authenticate");
         return ids;
      }
   }
}
