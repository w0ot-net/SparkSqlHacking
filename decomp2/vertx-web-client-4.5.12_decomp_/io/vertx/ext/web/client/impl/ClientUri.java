package io.vertx.ext.web.client.impl;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

class ClientUri {
   final String protocol;
   final Boolean ssl;
   final int port;
   final String host;
   final String uri;

   private ClientUri(String protocol, boolean ssl, int port, String host, String uri) {
      this.protocol = protocol;
      this.ssl = ssl;
      this.port = port;
      this.host = host;
      this.uri = uri;
   }

   static ClientUri parse(String suri) throws URISyntaxException, MalformedURLException {
      URL url = new URL(suri);
      boolean ssl = false;
      int port = url.getPort();
      String protocol = url.getProtocol();
      if ("ftp".equals(protocol)) {
         if (port == -1) {
            port = 21;
         }
      } else {
         char chend = protocol.charAt(protocol.length() - 1);
         if (chend == 'p') {
            if (port == -1) {
               port = 80;
            }
         } else if (chend == 's') {
            ssl = true;
            if (port == -1) {
               port = 443;
            }
         }
      }

      String file = url.getFile();
      String host = url.getHost();
      return new ClientUri(protocol, ssl, port, host, file);
   }
}
