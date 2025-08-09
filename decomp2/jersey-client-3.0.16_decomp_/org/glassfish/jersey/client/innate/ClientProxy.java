package org.glassfish.jersey.client.innate;

import jakarta.ws.rs.ProcessingException;
import jakarta.ws.rs.core.Configuration;
import jakarta.ws.rs.core.MultivaluedMap;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.Proxy.Type;
import java.util.Arrays;
import java.util.Base64;
import java.util.Locale;
import java.util.Optional;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.ClientRequest;
import org.glassfish.jersey.client.internal.LocalizationMessages;

public abstract class ClientProxy {
   protected String userName;
   protected String password;

   private ClientProxy() {
   }

   public static Optional proxyFromRequest(ClientRequest request) {
      return getProxy(request);
   }

   public static Optional proxyFromProperties(URI requestUri) {
      return getSystemPropertiesProxy(requestUri);
   }

   public static Optional proxyFromConfiguration(Configuration configuration) {
      return getProxy(configuration);
   }

   public static ClientProxy proxy(Proxy proxy) {
      return new ProxyClientProxy(proxy);
   }

   public static void setBasicAuthorizationHeader(MultivaluedMap headers, ClientProxy proxy) {
      if (proxy.userName() != null) {
         StringBuilder auth = (new StringBuilder()).append(proxy.userName()).append(":");
         if (proxy.password() != null) {
            auth.append(proxy.password());
         }

         String encoded = "Basic " + Base64.getEncoder().encodeToString(auth.toString().getBytes());
         headers.put("Proxy-Authorization", Arrays.asList(encoded));
      }

   }

   private static ClientProxy toProxy(Object proxy) {
      if (proxy instanceof String) {
         return new UriClientProxy(URI.create((String)proxy));
      } else if (proxy instanceof URI) {
         return new UriClientProxy((URI)proxy);
      } else if (Proxy.class.isInstance(proxy)) {
         Proxy netProxy = (Proxy)Proxy.class.cast(proxy);
         return Type.HTTP.equals(netProxy.type()) ? new ProxyClientProxy((Proxy)Proxy.class.cast(proxy)) : null;
      } else {
         throw new ProcessingException(LocalizationMessages.WRONG_PROXY_URI_TYPE("jersey.config.client.proxy.uri"));
      }
   }

   public abstract Proxy proxy();

   public abstract URI uri();

   public abstract Proxy.Type type();

   public String password() {
      return this.password;
   }

   public String userName() {
      return this.userName;
   }

   private static Optional getProxy(ClientRequest request) {
      Object proxyUri = request.resolveProperty("jersey.config.client.proxy.uri", Object.class);
      if (proxyUri != null) {
         ClientProxy proxy = toProxy(proxyUri);
         if (proxy != null) {
            proxy.userName = (String)request.resolveProperty("jersey.config.client.proxy.username", String.class);
            proxy.password = (String)request.resolveProperty("jersey.config.client.proxy.password", String.class);
            return Optional.of(proxy);
         } else {
            return Optional.empty();
         }
      } else {
         return Optional.empty();
      }
   }

   private static Optional getProxy(Configuration config) {
      Object proxyUri = config.getProperties().get("jersey.config.client.proxy.uri");
      if (proxyUri != null) {
         ClientProxy proxy = toProxy(proxyUri);
         if (proxy != null) {
            proxy.userName = (String)ClientProperties.getValue(config.getProperties(), "jersey.config.client.proxy.username", String.class);
            proxy.password = (String)ClientProperties.getValue(config.getProperties(), "jersey.config.client.proxy.password", String.class);
            return Optional.of(proxy);
         } else {
            return Optional.empty();
         }
      } else {
         return Optional.empty();
      }
   }

   private static Optional getSystemPropertiesProxy(URI requestUri) {
      ProxySelector sel = ProxySelector.getDefault();

      for(Proxy proxy : sel.select(requestUri)) {
         if (Type.HTTP.equals(proxy.type())) {
            return Optional.of(new ProxyClientProxy(proxy));
         }
      }

      return Optional.empty();
   }

   private static final class ProxyClientProxy extends ClientProxy {
      private final Proxy proxy;

      private ProxyClientProxy(Proxy proxy) {
         this.proxy = proxy;
      }

      public Proxy proxy() {
         return this.proxy;
      }

      public Proxy.Type type() {
         return this.proxy.type();
      }

      public URI uri() {
         URI uri = null;
         if (Type.HTTP.equals(this.proxy.type())) {
            SocketAddress proxyAddress = this.proxy.address();
            if (InetSocketAddress.class.isInstance(this.proxy.address())) {
               InetSocketAddress proxyAddr = (InetSocketAddress)proxyAddress;

               try {
                  if (proxyAddr.isUnresolved() && proxyAddr.getHostName() != null && proxyAddr.getHostName().toLowerCase(Locale.ROOT).startsWith("http://")) {
                     String hostString = proxyAddr.getHostString().substring(7);
                     uri = new URI("http", (String)null, hostString, proxyAddr.getPort(), (String)null, (String)null, (String)null);
                  } else {
                     uri = new URI("http", (String)null, proxyAddr.getHostString(), proxyAddr.getPort(), (String)null, (String)null, (String)null);
                  }
               } catch (URISyntaxException e) {
                  throw new ProcessingException(e);
               }
            }
         }

         return uri;
      }
   }

   private static final class UriClientProxy extends ClientProxy {
      private final URI uri;

      private UriClientProxy(URI uri) {
         this.uri = uri;
      }

      public Proxy proxy() {
         return new Proxy(this.type(), new InetSocketAddress(this.uri.getHost(), this.uri.getPort()));
      }

      public Proxy.Type type() {
         return Type.HTTP;
      }

      public URI uri() {
         return this.uri;
      }
   }
}
