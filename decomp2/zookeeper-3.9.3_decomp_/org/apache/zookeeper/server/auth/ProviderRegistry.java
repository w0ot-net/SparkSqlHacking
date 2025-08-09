package org.apache.zookeeper.server.auth;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.apache.zookeeper.server.ZooKeeperServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProviderRegistry {
   private static final Logger LOG = LoggerFactory.getLogger(ProviderRegistry.class);
   public static final String AUTHPROVIDER_PROPERTY_PREFIX = "zookeeper.authProvider.";
   private static boolean initialized = false;
   private static final Map authenticationProviders = new HashMap();

   public static void reset() {
      synchronized(ProviderRegistry.class) {
         initialized = false;
         authenticationProviders.clear();
      }
   }

   public static void initialize() {
      synchronized(ProviderRegistry.class) {
         IPAuthenticationProvider ipp = new IPAuthenticationProvider();
         authenticationProviders.put(ipp.getScheme(), ipp);
         if (DigestAuthenticationProvider.isEnabled()) {
            DigestAuthenticationProvider digp = new DigestAuthenticationProvider();
            authenticationProviders.put(digp.getScheme(), digp);
         }

         Enumeration<Object> en = System.getProperties().keys();

         while(en.hasMoreElements()) {
            String k = (String)en.nextElement();
            addOrUpdateProvider(k);
         }

         initialized = true;
      }
   }

   public static void addOrUpdateProvider(String authKey) {
      synchronized(ProviderRegistry.class) {
         if (authKey.startsWith("zookeeper.authProvider.")) {
            String className = System.getProperty(authKey);

            try {
               Class<?> c = ZooKeeperServer.class.getClassLoader().loadClass(className);
               AuthenticationProvider ap = (AuthenticationProvider)c.getDeclaredConstructor().newInstance();
               authenticationProviders.put(ap.getScheme(), ap);
            } catch (Exception e) {
               LOG.warn("Problems loading {}", className, e);
            }
         }

      }
   }

   public static ServerAuthenticationProvider getServerProvider(String scheme) {
      return WrappedAuthenticationProvider.wrap(getProvider(scheme));
   }

   public static AuthenticationProvider getProvider(String scheme) {
      if (!initialized) {
         initialize();
      }

      return (AuthenticationProvider)authenticationProviders.get(scheme);
   }

   public static void removeProvider(String scheme) {
      authenticationProviders.remove(scheme);
   }

   public static String listProviders() {
      StringBuilder sb = new StringBuilder();

      for(String s : authenticationProviders.keySet()) {
         sb.append(s).append(" ");
      }

      return sb.toString();
   }
}
