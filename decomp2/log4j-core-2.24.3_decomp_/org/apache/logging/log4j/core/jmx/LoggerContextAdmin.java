package org.apache.logging.log4j.core.jmx;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import javax.management.ObjectName;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.util.Closer;
import org.apache.logging.log4j.status.StatusLogger;

public class LoggerContextAdmin extends NotificationBroadcasterSupport implements LoggerContextAdminMBean, PropertyChangeListener {
   private static final int PAGE = 4096;
   private static final int TEXT_BUFFER = 65536;
   private static final int BUFFER_SIZE = 2048;
   private static final StatusLogger LOGGER = StatusLogger.getLogger();
   private final AtomicLong sequenceNo = new AtomicLong();
   private final ObjectName objectName;
   private final LoggerContext loggerContext;

   public LoggerContextAdmin(final LoggerContext loggerContext, final Executor executor) {
      super(executor, new MBeanNotificationInfo[]{createNotificationInfo()});
      this.loggerContext = (LoggerContext)Objects.requireNonNull(loggerContext, "loggerContext");

      try {
         String ctxName = Server.escape(loggerContext.getName());
         String name = String.format("org.apache.logging.log4j2:type=%s", ctxName);
         this.objectName = new ObjectName(name);
      } catch (Exception e) {
         throw new IllegalStateException(e);
      }

      loggerContext.addPropertyChangeListener(this);
   }

   private static MBeanNotificationInfo createNotificationInfo() {
      String[] notifTypes = new String[]{"com.apache.logging.log4j.core.jmx.config.reconfigured"};
      String name = Notification.class.getName();
      String description = "Configuration reconfigured";
      return new MBeanNotificationInfo(notifTypes, name, "Configuration reconfigured");
   }

   public String getStatus() {
      return this.loggerContext.getState().toString();
   }

   public String getName() {
      return this.loggerContext.getName();
   }

   private Configuration getConfig() {
      return this.loggerContext.getConfiguration();
   }

   @SuppressFBWarnings(
      value = {"PATH_TRAVERSAL_IN"},
      justification = "The location of the configuration comes from a running configuration."
   )
   public String getConfigLocationUri() {
      if (this.loggerContext.getConfigLocation() != null) {
         return String.valueOf(this.loggerContext.getConfigLocation());
      } else {
         return this.getConfigName() != null ? String.valueOf((new File(this.getConfigName())).toURI()) : "";
      }
   }

   @SuppressFBWarnings(
      value = {"URLCONNECTION_SSRF_FD", "PATH_TRAVERSAL_IN"},
      justification = "This method should only be called by a secure JMX connection."
   )
   public void setConfigLocationUri(final String configLocation) throws URISyntaxException, IOException {
      if (configLocation != null && !configLocation.isEmpty()) {
         LOGGER.debug("---------");
         LOGGER.debug("Remote request to reconfigure using location " + configLocation);
         File configFile = new File(configLocation);
         ConfigurationSource configSource = null;
         if (configFile.exists()) {
            LOGGER.debug("Opening config file {}", configFile.getAbsolutePath());
            configSource = new ConfigurationSource(new FileInputStream(configFile), configFile);
         } else {
            URL configURL = new URL(configLocation);
            LOGGER.debug("Opening config URL {}", configURL);
            configSource = new ConfigurationSource(configURL.openStream(), configURL);
         }

         Configuration config = ConfigurationFactory.getInstance().getConfiguration(this.loggerContext, configSource);
         this.loggerContext.start(config);
         LOGGER.debug("Completed remote request to reconfigure.");
      } else {
         throw new IllegalArgumentException("Missing configuration location");
      }
   }

   public void propertyChange(final PropertyChangeEvent evt) {
      if ("config".equals(evt.getPropertyName())) {
         Notification notif = new Notification("com.apache.logging.log4j.core.jmx.config.reconfigured", this.getObjectName(), this.nextSeqNo(), this.now(), (String)null);
         this.sendNotification(notif);
      }
   }

   public String getConfigText() throws IOException {
      return this.getConfigText(StandardCharsets.UTF_8.name());
   }

   @SuppressFBWarnings(
      value = {"INFORMATION_EXPOSURE_THROUGH_AN_ERROR_MESSAGE"},
      justification = "JMX should be considered a trusted channel."
   )
   public String getConfigText(final String charsetName) throws IOException {
      try {
         ConfigurationSource source = this.loggerContext.getConfiguration().getConfigurationSource();
         ConfigurationSource copy = source.resetInputStream();
         Charset charset = Charset.forName(charsetName);
         return this.readContents(copy.getInputStream(), charset);
      } catch (Exception ex) {
         StringWriter sw = new StringWriter(2048);
         ex.printStackTrace(new PrintWriter(sw));
         return sw.toString();
      }
   }

   private String readContents(final InputStream in, final Charset charset) throws IOException {
      Reader reader = null;

      String var7;
      try {
         reader = new InputStreamReader(in, charset);
         StringBuilder result = new StringBuilder(65536);
         char[] buff = new char[4096];
         int count = -1;

         while((count = reader.read(buff)) >= 0) {
            result.append(buff, 0, count);
         }

         var7 = result.toString();
      } finally {
         Closer.closeSilently(in);
         Closer.closeSilently(reader);
      }

      return var7;
   }

   public void setConfigText(final String configText, final String charsetName) {
      LOGGER.debug("---------");
      LOGGER.debug("Remote request to reconfigure from config text.");

      try {
         InputStream in = new ByteArrayInputStream(configText.getBytes(charsetName));
         ConfigurationSource source = new ConfigurationSource(in);
         Configuration updated = ConfigurationFactory.getInstance().getConfiguration(this.loggerContext, source);
         this.loggerContext.start(updated);
         LOGGER.debug("Completed remote request to reconfigure from config text.");
      } catch (Exception ex) {
         String msg = "Could not reconfigure from config text";
         LOGGER.error("Could not reconfigure from config text", ex);
         throw new IllegalArgumentException("Could not reconfigure from config text", ex);
      }
   }

   public String getConfigName() {
      return this.getConfig().getName();
   }

   public String getConfigClassName() {
      return this.getConfig().getClass().getName();
   }

   public String getConfigFilter() {
      return String.valueOf(this.getConfig().getFilter());
   }

   public Map getConfigProperties() {
      return this.getConfig().getProperties();
   }

   public ObjectName getObjectName() {
      return this.objectName;
   }

   private long nextSeqNo() {
      return this.sequenceNo.getAndIncrement();
   }

   private long now() {
      return System.currentTimeMillis();
   }
}
