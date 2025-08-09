package org.apache.log4j.xml;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import javax.xml.parsers.FactoryConfigurationError;
import org.apache.log4j.config.PropertySetter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.net.UrlConnectionFactory;
import org.apache.logging.log4j.core.util.IOUtils;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.w3c.dom.Element;

public class DOMConfigurator {
   private static boolean isFullCompatibilityEnabled() {
      return PropertiesUtil.getProperties().getBooleanProperty("log4j1.compatibility");
   }

   private static void warnFullCompatibilityDisabled() {
      LogLog.warn("Ignoring `DOMConfigurator` call, since `log4j1.compatibility` is not enabled.\nSee https://logging.staged.apache.org/log4j/2.x/migrate-from-log4j1.html#log4j1.compatibility for details.");
   }

   public static void configure(final Element element) {
   }

   @SuppressFBWarnings(
      value = {"PATH_TRAVERSAL_IN"},
      justification = "The filename comes from a system property."
   )
   public static void configure(final String fileName) throws FactoryConfigurationError {
      if (isFullCompatibilityEnabled()) {
         Path path = Paths.get(fileName);

         try {
            InputStream inputStream = Files.newInputStream(path);

            try {
               ConfigurationSource source = new ConfigurationSource(inputStream, path);
               LoggerContext context = (LoggerContext)LogManager.getContext(false);
               Configuration configuration = (new XmlConfigurationFactory()).getConfiguration(context, source);
               org.apache.log4j.LogManager.getRootLogger().removeAllAppenders();
               Configurator.reconfigure(configuration);
            } catch (Throwable var7) {
               if (inputStream != null) {
                  try {
                     inputStream.close();
                  } catch (Throwable var6) {
                     var7.addSuppressed(var6);
                  }
               }

               throw var7;
            }

            if (inputStream != null) {
               inputStream.close();
            }
         } catch (IOException e) {
            throw new FactoryConfigurationError(e);
         }
      } else {
         warnFullCompatibilityDisabled();
      }

   }

   public static void configure(final URL url) throws FactoryConfigurationError {
      (new DOMConfigurator()).doConfigure(url, org.apache.log4j.LogManager.getLoggerRepository());
   }

   public static void configureAndWatch(final String fileName) {
      configure(fileName);
   }

   public static void configureAndWatch(final String fileName, final long delay) {
      if (isFullCompatibilityEnabled()) {
         XMLWatchdog xdog = new XMLWatchdog(fileName);
         xdog.setDelay(delay);
         xdog.start();
      } else {
         warnFullCompatibilityDisabled();
      }

   }

   public static Object parseElement(final Element element, final Properties props, final Class expectedClass) {
      return null;
   }

   public static void setParameter(final Element elem, final PropertySetter propSetter, final Properties props) {
   }

   public static String subst(final String value, final Properties props) {
      return OptionConverter.substVars(value, props);
   }

   private void doConfigure(final ConfigurationSource source) {
      LoggerContext context = (LoggerContext)LogManager.getContext(false);
      Configuration configuration = (new XmlConfigurationFactory()).getConfiguration(context, source);
      Configurator.reconfigure(configuration);
   }

   public void doConfigure(final Element element, final LoggerRepository repository) {
   }

   public void doConfigure(final InputStream inputStream, final LoggerRepository repository) throws FactoryConfigurationError {
      if (isFullCompatibilityEnabled()) {
         try {
            this.doConfigure(new ConfigurationSource(inputStream));
         } catch (IOException e) {
            throw new FactoryConfigurationError(e);
         }
      } else {
         warnFullCompatibilityDisabled();
      }

   }

   public void doConfigure(final Reader reader, final LoggerRepository repository) throws FactoryConfigurationError {
      if (isFullCompatibilityEnabled()) {
         try {
            StringWriter sw = new StringWriter();
            IOUtils.copy(reader, sw);
            this.doConfigure(new ConfigurationSource(new ByteArrayInputStream(sw.toString().getBytes(StandardCharsets.UTF_8))));
         } catch (IOException e) {
            throw new FactoryConfigurationError(e);
         }
      } else {
         warnFullCompatibilityDisabled();
      }

   }

   public void doConfigure(final String fileName, final LoggerRepository repository) {
      configure(fileName);
   }

   public void doConfigure(final URL url, final LoggerRepository repository) {
      if (isFullCompatibilityEnabled()) {
         try {
            URLConnection connection = UrlConnectionFactory.createConnection(url);
            InputStream inputStream = connection.getInputStream();

            try {
               this.doConfigure(new ConfigurationSource(inputStream, url));
            } catch (Throwable var8) {
               if (inputStream != null) {
                  try {
                     inputStream.close();
                  } catch (Throwable var7) {
                     var8.addSuppressed(var7);
                  }
               }

               throw var8;
            }

            if (inputStream != null) {
               inputStream.close();
            }
         } catch (IOException e) {
            throw new FactoryConfigurationError(e);
         }
      } else {
         warnFullCompatibilityDisabled();
      }

   }
}
