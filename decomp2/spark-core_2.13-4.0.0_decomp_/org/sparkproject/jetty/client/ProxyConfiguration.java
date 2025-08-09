package org.sparkproject.jetty.client;

import java.net.URI;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.sparkproject.jetty.http.HttpScheme;
import org.sparkproject.jetty.io.ClientConnectionFactory;
import org.sparkproject.jetty.util.BlockingArrayQueue;
import org.sparkproject.jetty.util.HostPort;
import org.sparkproject.jetty.util.ssl.SslContextFactory;

public class ProxyConfiguration {
   private final List proxies = new BlockingArrayQueue();

   /** @deprecated */
   @Deprecated
   public List getProxies() {
      return this.proxies;
   }

   public void addProxy(Proxy proxy) {
      this.proxies.add((Proxy)Objects.requireNonNull(proxy));
   }

   public boolean removeProxy(Proxy proxy) {
      return this.proxies.remove(proxy);
   }

   public Proxy match(Origin origin) {
      for(Proxy proxy : this.proxies) {
         if (proxy.matches(origin)) {
            return proxy;
         }
      }

      return null;
   }

   public abstract static class Proxy {
      private final Set included;
      private final Set excluded;
      private final Origin origin;
      private final SslContextFactory.Client sslContextFactory;

      protected Proxy(Origin.Address address, boolean secure, SslContextFactory.Client sslContextFactory, Origin.Protocol protocol) {
         this(new Origin(secure ? HttpScheme.HTTPS.asString() : HttpScheme.HTTP.asString(), address, (Object)null, protocol), sslContextFactory);
      }

      protected Proxy(Origin origin, SslContextFactory.Client sslContextFactory) {
         this.included = new HashSet();
         this.excluded = new HashSet();
         this.origin = origin;
         this.sslContextFactory = sslContextFactory;
      }

      public Origin getOrigin() {
         return this.origin;
      }

      public Origin.Address getAddress() {
         return this.origin.getAddress();
      }

      public boolean isSecure() {
         return HttpScheme.HTTPS.is(this.origin.getScheme());
      }

      public SslContextFactory.Client getSslContextFactory() {
         return this.sslContextFactory;
      }

      public Origin.Protocol getProtocol() {
         return this.origin.getProtocol();
      }

      public Set getIncludedAddresses() {
         return this.included;
      }

      public Set getExcludedAddresses() {
         return this.excluded;
      }

      public URI getURI() {
         return null;
      }

      public boolean matches(Origin origin) {
         if (this.getAddress().equals(origin.getAddress())) {
            return false;
         } else {
            boolean result = this.included.isEmpty();
            Origin.Address address = origin.getAddress();

            for(String included : this.included) {
               if (this.matches(address, included)) {
                  result = true;
                  break;
               }
            }

            for(String excluded : this.excluded) {
               if (this.matches(address, excluded)) {
                  result = false;
                  break;
               }
            }

            return result;
         }
      }

      private boolean matches(Origin.Address address, String pattern) {
         HostPort hostPort = new HostPort(pattern);
         String host = hostPort.getHost();
         int port = hostPort.getPort();
         return host.equals(address.getHost()) && (port <= 0 || port == address.getPort());
      }

      public abstract ClientConnectionFactory newClientConnectionFactory(ClientConnectionFactory var1);

      public String toString() {
         return this.origin.toString();
      }
   }
}
