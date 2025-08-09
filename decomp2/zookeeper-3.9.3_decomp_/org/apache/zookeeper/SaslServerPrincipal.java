package org.apache.zookeeper;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import org.apache.zookeeper.client.ZKClientConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SaslServerPrincipal {
   private static final Logger LOG = LoggerFactory.getLogger(SaslServerPrincipal.class);

   static String getServerPrincipal(InetSocketAddress addr, ZKClientConfig clientConfig) {
      return getServerPrincipal(new WrapperInetSocketAddress(addr), clientConfig);
   }

   static String getServerPrincipal(WrapperInetSocketAddress addr, ZKClientConfig clientConfig) {
      String configuredServerPrincipal = clientConfig.getProperty("zookeeper.server.principal");
      if (configuredServerPrincipal != null) {
         return configuredServerPrincipal;
      } else {
         String principalUserName = clientConfig.getProperty("zookeeper.sasl.client.username", "zookeeper");
         String hostName = addr.getHostName();
         boolean canonicalize = true;
         String canonicalizeText = clientConfig.getProperty("zookeeper.sasl.client.canonicalize.hostname", "true");

         try {
            canonicalize = Boolean.parseBoolean(canonicalizeText);
         } catch (IllegalArgumentException var9) {
            LOG.warn("Could not parse config {} \"{}\" into a boolean using default {}", new Object[]{"zookeeper.sasl.client.canonicalize.hostname", canonicalizeText, canonicalize});
         }

         if (canonicalize) {
            WrapperInetAddress ia = addr.getAddress();
            if (ia == null) {
               throw new IllegalArgumentException("Unable to canonicalize address " + addr + " because it's not resolvable");
            }

            String canonicalHostName = ia.getCanonicalHostName();
            if (!canonicalHostName.equals(ia.getHostAddress())) {
               hostName = canonicalHostName;
            }

            LOG.debug("Canonicalized address to {}", hostName);
         }

         String serverPrincipal = principalUserName + "/" + hostName;
         return serverPrincipal;
      }
   }

   static class WrapperInetSocketAddress {
      private final InetSocketAddress addr;

      WrapperInetSocketAddress(InetSocketAddress addr) {
         this.addr = addr;
      }

      public String getHostName() {
         return this.addr.getHostName();
      }

      public WrapperInetAddress getAddress() {
         InetAddress ia = this.addr.getAddress();
         return ia == null ? null : new WrapperInetAddress(ia);
      }

      public String toString() {
         return this.addr.toString();
      }
   }

   static class WrapperInetAddress {
      private final InetAddress ia;

      WrapperInetAddress(InetAddress ia) {
         this.ia = ia;
      }

      public String getCanonicalHostName() {
         return this.ia.getCanonicalHostName();
      }

      public String getHostAddress() {
         return this.ia.getHostAddress();
      }

      public String toString() {
         return this.ia.toString();
      }
   }
}
