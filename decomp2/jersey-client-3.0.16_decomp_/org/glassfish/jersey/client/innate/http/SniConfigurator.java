package org.glassfish.jersey.client.innate.http;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import javax.net.ssl.SNIHostName;
import javax.net.ssl.SNIServerName;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import org.glassfish.jersey.client.internal.LocalizationMessages;

final class SniConfigurator {
   private static final Logger LOGGER = Logger.getLogger(SniConfigurator.class.getName());
   private final String hostName;

   private SniConfigurator(String hostName) {
      this.hostName = hostName;
   }

   String getHostName() {
      return this.hostName;
   }

   static Optional createWhenHostHeader(URI hostUri, String sniHost, boolean whenDiffer) {
      if (sniHost != null) {
         int index = sniHost.indexOf(58);
         String trimmedHeader0 = index != -1 ? sniHost.substring(0, index).trim() : sniHost.trim();
         String trimmedHeader = trimmedHeader0.isEmpty() ? sniHost : trimmedHeader0;
         String hostUriString = hostUri.getHost();
         return !whenDiffer && hostUriString.equals(trimmedHeader) ? Optional.empty() : Optional.of(new SniConfigurator(trimmedHeader));
      } else {
         return Optional.empty();
      }
   }

   void setServerNames(SSLEngine sslEngine) {
      SSLParameters sslParameters = sslEngine.getSSLParameters();
      this.updateSSLParameters(sslParameters);
      sslEngine.setSSLParameters(sslParameters);
      LOGGER.fine(LocalizationMessages.SNI_ON_SSLENGINE());
   }

   void setServerNames(SSLSocket sslSocket) {
      SSLParameters sslParameters = sslSocket.getSSLParameters();
      this.updateSSLParameters(sslParameters);
      sslSocket.setSSLParameters(sslParameters);
      LOGGER.fine(LocalizationMessages.SNI_ON_SSLSOCKET());
   }

   private SSLParameters updateSSLParameters(SSLParameters sslParameters) {
      SNIHostName serverName = new SNIHostName(this.hostName);
      List<SNIServerName> serverNames = new LinkedList();
      serverNames.add(serverName);
      sslParameters.setServerNames(serverNames);
      LOGGER.finer(LocalizationMessages.SNI_UPDATE_SSLPARAMS(this.hostName));
      return sslParameters;
   }
}
