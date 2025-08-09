package org.glassfish.jersey.client.innate.http;

import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.UriBuilder;
import java.net.InetAddress;
import java.net.URI;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import org.glassfish.jersey.client.ClientRequest;
import org.glassfish.jersey.internal.PropertiesResolver;

public final class SSLParamConfigurator {
   private final URI uri;
   private final Optional sniConfigurator;

   private SSLParamConfigurator(Builder builder) {
      this.uri = builder.uri;
      if (builder.sniHostPrecedence == null) {
         this.sniConfigurator = SniConfigurator.createWhenHostHeader(this.uri, builder.sniHostNameHeader, builder.setAlways);
      } else {
         this.sniConfigurator = SniConfigurator.createWhenHostHeader(this.uri, builder.sniHostPrecedence, false);
      }

   }

   public static Builder builder() {
      return new Builder();
   }

   public String getSNIHostName() {
      return this.sniConfigurator.isPresent() ? ((SniConfigurator)this.sniConfigurator.get()).getHostName() : this.uri.getHost();
   }

   public URI toIPRequestUri() {
      String host = this.uri.getHost();

      try {
         InetAddress ip = InetAddress.getByName(host);
         return UriBuilder.fromUri(this.uri).host(ip.getHostAddress()).build(new Object[0]);
      } catch (UnknownHostException var3) {
         return this.uri;
      }
   }

   public boolean isSNIRequired() {
      return this.sniConfigurator.isPresent();
   }

   public URI getSNIUri() {
      return this.sniConfigurator.isPresent() ? UriBuilder.fromUri(this.uri).host(this.getSNIHostName()).build(new Object[0]) : this.uri;
   }

   public void setSNIServerName(SSLEngine sslEngine) {
      this.sniConfigurator.ifPresent((sni) -> sni.setServerNames(sslEngine));
   }

   public void setSNIServerName(SSLSocket sslSocket) {
      this.sniConfigurator.ifPresent((sni) -> sni.setServerNames(sslSocket));
   }

   public void setEndpointIdentificationAlgorithm(SSLEngine sslEngine) {
      SSLParameters sslParameters = sslEngine.getSSLParameters();
      sslParameters.setEndpointIdentificationAlgorithm("HTTPS");
      sslEngine.setSSLParameters(sslParameters);
   }

   public static final class Builder {
      private URI uri;
      private String sniHostNameHeader = null;
      private String sniHostPrecedence = null;
      private boolean setAlways = false;

      public Builder request(ClientRequest clientRequest) {
         this.sniHostNameHeader = getSniHostNameHeader(clientRequest.getHeaders());
         this.sniHostPrecedence = resolveSniHostNameProperty(clientRequest);
         this.uri = clientRequest.getUri();
         return this;
      }

      public Builder configuration(Configuration configuration) {
         this.sniHostPrecedence = getSniHostNameProperty(configuration);
         return this;
      }

      public Builder uri(URI uri) {
         this.uri = uri;
         return this;
      }

      public Builder headers(Map httpHeaders) {
         this.sniHostNameHeader = getSniHostNameHeader(httpHeaders);
         return this;
      }

      public Builder setSNIAlways(boolean setAlways) {
         this.setAlways = setAlways;
         return this;
      }

      public Builder setSNIHostName(String hostName) {
         this.sniHostPrecedence = hostName;
         return this;
      }

      public Builder setSNIHostName(Configuration configuration) {
         return this.setSNIHostName(getSniHostNameProperty(configuration));
      }

      public Builder setSNIHostName(PropertiesResolver resolver) {
         return this.setSNIHostName(resolveSniHostNameProperty(resolver));
      }

      public SSLParamConfigurator build() {
         return new SSLParamConfigurator(this);
      }

      private static String getSniHostNameHeader(Map httpHeaders) {
         List<Object> hostHeaders = (List)httpHeaders.get("Host");
         if (hostHeaders != null && hostHeaders.get(0) != null) {
            String hostHeader = hostHeaders.get(0).toString();
            return hostHeader;
         } else {
            return null;
         }
      }

      private static String resolveSniHostNameProperty(PropertiesResolver resolver) {
         String property = (String)resolver.resolveProperty("jersey.config.client.sniHostName", String.class);
         if (property == null) {
            property = (String)resolver.resolveProperty("jersey.config.client.sniHostName".toLowerCase(Locale.ROOT), String.class);
         }

         return property;
      }

      private static String getSniHostNameProperty(Configuration configuration) {
         Object property = configuration.getProperty("jersey.config.client.sniHostName");
         if (property == null) {
            property = configuration.getProperty("jersey.config.client.sniHostName".toLowerCase(Locale.ROOT));
         }

         return (String)property;
      }
   }
}
