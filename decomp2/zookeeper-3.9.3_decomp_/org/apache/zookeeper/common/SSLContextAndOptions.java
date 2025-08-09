package org.apache.zookeeper.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.Objects;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SSLContextAndOptions {
   private static final Logger LOG = LoggerFactory.getLogger(SSLContextAndOptions.class);
   private final X509Util x509Util;
   private final String[] enabledProtocols;
   private final String[] cipherSuites;
   private final X509Util.ClientAuth clientAuth;
   private final SSLContext sslContext;
   private final int handshakeDetectionTimeoutMillis;

   SSLContextAndOptions(X509Util x509Util, ZKConfig config, SSLContext sslContext) {
      this.x509Util = (X509Util)Objects.requireNonNull(x509Util);
      this.sslContext = (SSLContext)Objects.requireNonNull(sslContext);
      this.enabledProtocols = this.getEnabledProtocols((ZKConfig)Objects.requireNonNull(config), sslContext);
      this.cipherSuites = this.getCipherSuites(config);
      this.clientAuth = this.getClientAuth(config);
      this.handshakeDetectionTimeoutMillis = this.getHandshakeDetectionTimeoutMillis(config);
   }

   public SSLContext getSSLContext() {
      return this.sslContext;
   }

   public SSLSocket createSSLSocket() throws IOException {
      return this.configureSSLSocket((SSLSocket)this.sslContext.getSocketFactory().createSocket(), true);
   }

   public SSLSocket createSSLSocket(Socket socket, byte[] pushbackBytes) throws IOException {
      SSLSocket sslSocket;
      if (pushbackBytes != null && pushbackBytes.length > 0) {
         sslSocket = (SSLSocket)this.sslContext.getSocketFactory().createSocket(socket, new ByteArrayInputStream(pushbackBytes), true);
      } else {
         sslSocket = (SSLSocket)this.sslContext.getSocketFactory().createSocket(socket, (String)null, socket.getPort(), true);
      }

      return this.configureSSLSocket(sslSocket, false);
   }

   public SSLServerSocket createSSLServerSocket() throws IOException {
      SSLServerSocket sslServerSocket = (SSLServerSocket)this.sslContext.getServerSocketFactory().createServerSocket();
      return this.configureSSLServerSocket(sslServerSocket);
   }

   public SSLServerSocket createSSLServerSocket(int port) throws IOException {
      SSLServerSocket sslServerSocket = (SSLServerSocket)this.sslContext.getServerSocketFactory().createServerSocket(port);
      return this.configureSSLServerSocket(sslServerSocket);
   }

   public int getHandshakeDetectionTimeoutMillis() {
      return this.handshakeDetectionTimeoutMillis;
   }

   private SSLSocket configureSSLSocket(SSLSocket socket, boolean isClientSocket) {
      SSLParameters sslParameters = socket.getSSLParameters();
      this.configureSslParameters(sslParameters, isClientSocket);
      socket.setSSLParameters(sslParameters);
      socket.setUseClientMode(isClientSocket);
      return socket;
   }

   private SSLServerSocket configureSSLServerSocket(SSLServerSocket socket) {
      SSLParameters sslParameters = socket.getSSLParameters();
      this.configureSslParameters(sslParameters, false);
      socket.setSSLParameters(sslParameters);
      socket.setUseClientMode(false);
      return socket;
   }

   private void configureSslParameters(SSLParameters sslParameters, boolean isClientSocket) {
      if (this.cipherSuites != null) {
         LOG.debug("Setup cipher suites for {} socket: {}", isClientSocket ? "client" : "server", Arrays.toString(this.cipherSuites));
         sslParameters.setCipherSuites(this.cipherSuites);
      }

      if (this.enabledProtocols != null) {
         LOG.debug("Setup enabled protocols for {} socket: {}", isClientSocket ? "client" : "server", Arrays.toString(this.enabledProtocols));
         sslParameters.setProtocols(this.enabledProtocols);
      }

      if (!isClientSocket) {
         switch (this.clientAuth) {
            case NEED:
               sslParameters.setNeedClientAuth(true);
               break;
            case WANT:
               sslParameters.setWantClientAuth(true);
               break;
            default:
               sslParameters.setNeedClientAuth(false);
         }
      }

   }

   private String[] getEnabledProtocols(ZKConfig config, SSLContext sslContext) {
      String enabledProtocolsInput = config.getProperty(this.x509Util.getSslEnabledProtocolsProperty());
      return enabledProtocolsInput == null ? sslContext.getDefaultSSLParameters().getProtocols() : enabledProtocolsInput.split(",");
   }

   private String[] getCipherSuites(ZKConfig config) {
      String cipherSuitesInput = config.getProperty(this.x509Util.getSslCipherSuitesProperty());
      return cipherSuitesInput == null ? X509Util.getDefaultCipherSuites() : cipherSuitesInput.split(",");
   }

   private X509Util.ClientAuth getClientAuth(ZKConfig config) {
      return X509Util.ClientAuth.fromPropertyValue(config.getProperty(this.x509Util.getSslClientAuthProperty()));
   }

   private int getHandshakeDetectionTimeoutMillis(ZKConfig config) {
      String propertyString = config.getProperty(this.x509Util.getSslHandshakeDetectionTimeoutMillisProperty());
      int result;
      if (propertyString == null) {
         result = 5000;
      } else {
         result = Integer.parseInt(propertyString);
         if (result < 1) {
            LOG.warn("Invalid value for {}: {}, using the default value of {}", new Object[]{this.x509Util.getSslHandshakeDetectionTimeoutMillisProperty(), result, 5000});
            result = 5000;
         }
      }

      return result;
   }
}
