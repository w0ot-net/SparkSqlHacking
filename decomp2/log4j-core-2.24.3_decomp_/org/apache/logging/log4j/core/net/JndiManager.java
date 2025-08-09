package org.apache.logging.log4j.core.net;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.AbstractManager;
import org.apache.logging.log4j.core.appender.ManagerFactory;
import org.apache.logging.log4j.core.util.JndiCloser;
import org.apache.logging.log4j.util.PropertiesUtil;

public class JndiManager extends AbstractManager {
   private static final JndiManagerFactory FACTORY = new JndiManagerFactory();
   private static final String PREFIX = "log4j2.enableJndi";
   private static final String JAVA_SCHEME = "java";
   private final InitialContext context;

   private static boolean isJndiEnabled(final String subKey) {
      return PropertiesUtil.getProperties().getBooleanProperty("log4j2.enableJndi" + subKey, false);
   }

   public static boolean isJndiEnabled() {
      return isJndiContextSelectorEnabled() || isJndiJdbcEnabled() || isJndiJmsEnabled() || isJndiLookupEnabled();
   }

   public static boolean isJndiContextSelectorEnabled() {
      return isJndiEnabled("ContextSelector");
   }

   public static boolean isJndiJdbcEnabled() {
      return isJndiEnabled("Jdbc");
   }

   public static boolean isJndiJmsEnabled() {
      return isJndiEnabled("Jms");
   }

   public static boolean isJndiLookupEnabled() {
      return isJndiEnabled("Lookup");
   }

   private JndiManager(final String name, final InitialContext context) {
      super((LoggerContext)null, name);
      this.context = context;
   }

   public static JndiManager getDefaultManager() {
      return (JndiManager)getManager(JndiManager.class.getName(), FACTORY, (Object)null);
   }

   public static JndiManager getDefaultManager(final String name) {
      return (JndiManager)getManager(name, FACTORY, (Object)null);
   }

   public static JndiManager getJndiManager(final String initialContextFactoryName, final String providerURL, final String urlPkgPrefixes, final String securityPrincipal, final String securityCredentials, final Properties additionalProperties) {
      Properties properties = createProperties(initialContextFactoryName, providerURL, urlPkgPrefixes, securityPrincipal, securityCredentials, additionalProperties);
      return (JndiManager)getManager(createManagerName(), FACTORY, properties);
   }

   public static JndiManager getJndiManager(final Properties properties) {
      return (JndiManager)getManager(createManagerName(), FACTORY, properties);
   }

   private static String createManagerName() {
      return JndiManager.class.getName() + '@' + JndiManager.class.hashCode();
   }

   public static Properties createProperties(final String initialContextFactoryName, final String providerURL, final String urlPkgPrefixes, final String securityPrincipal, final String securityCredentials, final Properties additionalProperties) {
      if (initialContextFactoryName == null) {
         return null;
      } else {
         Properties properties = new Properties();
         properties.setProperty("java.naming.factory.initial", initialContextFactoryName);
         if (providerURL != null) {
            properties.setProperty("java.naming.provider.url", providerURL);
         } else {
            LOGGER.warn("The JNDI InitialContextFactory class name [{}] was provided, but there was no associated provider URL. This is likely to cause problems.", initialContextFactoryName);
         }

         if (urlPkgPrefixes != null) {
            properties.setProperty("java.naming.factory.url.pkgs", urlPkgPrefixes);
         }

         if (securityPrincipal != null) {
            properties.setProperty("java.naming.security.principal", securityPrincipal);
            if (securityCredentials != null) {
               properties.setProperty("java.naming.security.credentials", securityCredentials);
            } else {
               LOGGER.warn("A security principal [{}] was provided, but with no corresponding security credentials.", securityPrincipal);
            }
         }

         if (additionalProperties != null) {
            properties.putAll(additionalProperties);
         }

         return properties;
      }
   }

   protected boolean releaseSub(final long timeout, final TimeUnit timeUnit) {
      return JndiCloser.closeSilently(this.context);
   }

   @SuppressFBWarnings(
      value = {"LDAP_INJECTION"},
      justification = "This method only accepts an empty or 'java:' URI scheme."
   )
   public Object lookup(final String name) throws NamingException {
      if (this.context == null) {
         return null;
      } else {
         try {
            URI uri = new URI(name);
            if (uri.getScheme() == null || uri.getScheme().equals("java")) {
               return this.context.lookup(name);
            }

            LOGGER.warn("Unsupported JNDI URI - {}", name);
         } catch (URISyntaxException var3) {
            LOGGER.warn("Invalid JNDI URI - {}", name);
         }

         return null;
      }
   }

   public String toString() {
      return "JndiManager [context=" + this.context + ", count=" + this.count + "]";
   }

   private static class JndiManagerFactory implements ManagerFactory {
      private JndiManagerFactory() {
      }

      public JndiManager createManager(final String name, final Properties data) {
         if (!JndiManager.isJndiEnabled()) {
            throw new IllegalStateException(String.format("JNDI must be enabled by setting one of the %s* properties to true", "log4j2.enableJndi"));
         } else {
            try {
               return new JndiManager(name, new InitialContext(data));
            } catch (NamingException e) {
               JndiManager.LOGGER.error("Error creating JNDI InitialContext for '{}'.", name, e);
               return null;
            }
         }
      }
   }
}
