package org.sparkproject.jetty.server;

import org.sparkproject.jetty.http.HostPortHttpField;
import org.sparkproject.jetty.http.HttpField;
import org.sparkproject.jetty.http.HttpFields;
import org.sparkproject.jetty.http.HttpHeader;
import org.sparkproject.jetty.http.HttpScheme;
import org.sparkproject.jetty.http.HttpURI;
import org.sparkproject.jetty.http.HttpVersion;

public class HostHeaderCustomizer implements HttpConfiguration.Customizer {
   private final String serverName;
   private final int serverPort;

   public HostHeaderCustomizer() {
      this((String)null, 0);
   }

   public HostHeaderCustomizer(String serverName) {
      this(serverName, 0);
   }

   public HostHeaderCustomizer(String serverName, int serverPort) {
      this.serverName = serverName;
      this.serverPort = serverPort;
   }

   public void customize(Connector connector, HttpConfiguration channelConfig, Request request) {
      if (request.getHttpVersion() != HttpVersion.HTTP_1_1 && !request.getHttpFields().contains(HttpHeader.HOST)) {
         String host = this.serverName == null ? request.getServerName() : this.serverName;
         int port = HttpScheme.normalizePort(request.getScheme(), this.serverPort == 0 ? request.getServerPort() : this.serverPort);
         if (this.serverName != null || this.serverPort > 0) {
            request.setHttpURI(HttpURI.build(request.getHttpURI()).authority(host, port));
         }

         HttpFields original = request.getHttpFields();
         HttpFields.Mutable httpFields = HttpFields.build(original.size() + 1);
         httpFields.add((HttpField)(new HostPortHttpField(host, port)));
         httpFields.add(request.getHttpFields());
         request.setHttpFields(httpFields);
      }

   }
}
