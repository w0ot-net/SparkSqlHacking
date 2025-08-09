package org.apache.zookeeper.common;

import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLException;
import javax.net.ssl.X509ExtendedTrustManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZKTrustManager extends X509ExtendedTrustManager {
   private static final Logger LOG = LoggerFactory.getLogger(ZKTrustManager.class);
   private final X509ExtendedTrustManager x509ExtendedTrustManager;
   private final boolean serverHostnameVerificationEnabled;
   private final boolean clientHostnameVerificationEnabled;
   private final ZKHostnameVerifier hostnameVerifier;

   ZKTrustManager(X509ExtendedTrustManager x509ExtendedTrustManager, boolean serverHostnameVerificationEnabled, boolean clientHostnameVerificationEnabled) {
      this(x509ExtendedTrustManager, serverHostnameVerificationEnabled, clientHostnameVerificationEnabled, new ZKHostnameVerifier());
   }

   ZKTrustManager(X509ExtendedTrustManager x509ExtendedTrustManager, boolean serverHostnameVerificationEnabled, boolean clientHostnameVerificationEnabled, ZKHostnameVerifier hostnameVerifier) {
      this.x509ExtendedTrustManager = x509ExtendedTrustManager;
      this.serverHostnameVerificationEnabled = serverHostnameVerificationEnabled;
      this.clientHostnameVerificationEnabled = clientHostnameVerificationEnabled;
      this.hostnameVerifier = hostnameVerifier;
   }

   public X509Certificate[] getAcceptedIssuers() {
      return this.x509ExtendedTrustManager.getAcceptedIssuers();
   }

   public void checkClientTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {
      this.x509ExtendedTrustManager.checkClientTrusted(chain, authType, socket);
      if (this.clientHostnameVerificationEnabled) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Check client trusted socket.getInetAddress(): {}, {}", socket.getInetAddress(), socket);
         }

         this.performHostVerification(socket.getInetAddress(), chain[0]);
      }

   }

   public void checkServerTrusted(X509Certificate[] chain, String authType, Socket socket) throws CertificateException {
      this.x509ExtendedTrustManager.checkServerTrusted(chain, authType, socket);
      if (this.serverHostnameVerificationEnabled) {
         if (LOG.isDebugEnabled()) {
            LOG.debug("Check server trusted socket.getInetAddress(): {}, {}", socket.getInetAddress(), socket);
         }

         this.performHostVerification(socket.getInetAddress(), chain[0]);
      }

   }

   public void checkClientTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws CertificateException {
      this.x509ExtendedTrustManager.checkClientTrusted(chain, authType, engine);
      if (this.clientHostnameVerificationEnabled) {
         try {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Check client trusted engine.getPeerHost(): {}, {}", engine.getPeerHost(), engine);
            }

            this.performHostVerification(InetAddress.getByName(engine.getPeerHost()), chain[0]);
         } catch (UnknownHostException e) {
            throw new CertificateException("Failed to verify host", e);
         }
      }

   }

   public void checkServerTrusted(X509Certificate[] chain, String authType, SSLEngine engine) throws CertificateException {
      this.x509ExtendedTrustManager.checkServerTrusted(chain, authType, engine);
      if (this.serverHostnameVerificationEnabled) {
         try {
            if (LOG.isDebugEnabled()) {
               LOG.debug("Check server trusted engine.getPeerHost(): {}, {}", engine.getPeerHost(), engine);
            }

            this.performHostVerification(InetAddress.getByName(engine.getPeerHost()), chain[0]);
         } catch (UnknownHostException e) {
            throw new CertificateException("Failed to verify host", e);
         }
      }

   }

   public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
      this.x509ExtendedTrustManager.checkClientTrusted(chain, authType);
   }

   public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
      this.x509ExtendedTrustManager.checkServerTrusted(chain, authType);
   }

   private void performHostVerification(InetAddress inetAddress, X509Certificate certificate) throws CertificateException {
      String hostAddress = "";
      String hostName = "";

      try {
         hostAddress = inetAddress.getHostAddress();
         if (LOG.isDebugEnabled()) {
            LOG.debug("Trying to verify host address first: {}", hostAddress);
         }

         this.hostnameVerifier.verify(hostAddress, certificate);
      } catch (SSLException addressVerificationException) {
         try {
            hostName = inetAddress.getHostName();
            if (LOG.isDebugEnabled()) {
               LOG.debug("Failed to verify host address: {}, trying to verify host name: {}", hostAddress, hostName);
            }

            this.hostnameVerifier.verify(hostName, certificate);
         } catch (SSLException hostnameVerificationException) {
            LOG.error("Failed to verify host address: {}", hostAddress, addressVerificationException);
            LOG.error("Failed to verify hostname: {}", hostName, hostnameVerificationException);
            throw new CertificateException("Failed to verify both host address and host name", hostnameVerificationException);
         }
      }

   }
}
