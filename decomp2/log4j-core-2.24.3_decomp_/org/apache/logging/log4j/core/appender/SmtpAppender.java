package org.apache.logging.log4j.core.appender;

import java.io.Serializable;
import java.util.ServiceLoader;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginConfiguration;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.Required;
import org.apache.logging.log4j.core.config.plugins.validation.constraints.ValidPort;
import org.apache.logging.log4j.core.filter.ThresholdFilter;
import org.apache.logging.log4j.core.layout.AbstractStringLayout;
import org.apache.logging.log4j.core.layout.HtmlLayout;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.core.net.MailManager;
import org.apache.logging.log4j.core.net.MailManagerFactory;
import org.apache.logging.log4j.core.net.SmtpManager;
import org.apache.logging.log4j.core.net.ssl.SslConfiguration;
import org.apache.logging.log4j.core.util.Booleans;
import org.apache.logging.log4j.core.util.Integers;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.ServiceLoaderUtil;
import org.apache.logging.log4j.util.Strings;

@Plugin(
   name = "SMTP",
   category = "Core",
   elementType = "appender",
   printObject = true
)
public final class SmtpAppender extends AbstractAppender {
   private static final int DEFAULT_BUFFER_SIZE = 512;
   private final MailManager manager;

   private SmtpAppender(final String name, final Filter filter, final Layout layout, final MailManager manager, final boolean ignoreExceptions, final Property[] properties) {
      super(name, filter, layout, ignoreExceptions, properties);
      this.manager = manager;
   }

   public MailManager getManager() {
      return this.manager;
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return new Builder();
   }

   /** @deprecated */
   @Deprecated
   public static SmtpAppender createAppender(@PluginConfiguration final Configuration config, @PluginAttribute("name") @Required final String name, @PluginAttribute("to") final String to, @PluginAttribute("cc") final String cc, @PluginAttribute("bcc") final String bcc, @PluginAttribute("from") final String from, @PluginAttribute("replyTo") final String replyTo, @PluginAttribute("subject") final String subject, @PluginAttribute("smtpProtocol") final String smtpProtocol, @PluginAttribute("smtpHost") final String smtpHost, @PluginAttribute(value = "smtpPort",defaultString = "0") @ValidPort final String smtpPortStr, @PluginAttribute("smtpUsername") final String smtpUsername, @PluginAttribute(value = "smtpPassword",sensitive = true) final String smtpPassword, @PluginAttribute("smtpDebug") final String smtpDebug, @PluginAttribute("bufferSize") final String bufferSizeStr, @PluginElement("Layout") Layout layout, @PluginElement("Filter") Filter filter, @PluginAttribute("ignoreExceptions") final String ignore) {
      if (name == null) {
         LOGGER.error("No name provided for SmtpAppender");
         return null;
      } else {
         return ((Builder)((Builder)newBuilder().setIgnoreExceptions(Booleans.parseBoolean(ignore, true))).setSmtpPort(AbstractAppender.parseInt(smtpPortStr, 0)).setSmtpDebug(Boolean.parseBoolean(smtpDebug)).setBufferSize(bufferSizeStr == null ? 512 : Integers.parseInt(bufferSizeStr)).setLayout(layout).setFilter(filter).setConfiguration((Configuration)(config != null ? config : new DefaultConfiguration()))).build();
      }
   }

   public boolean isFiltered(final LogEvent event) {
      boolean filtered = super.isFiltered(event);
      if (filtered) {
         this.manager.add(event);
      }

      return filtered;
   }

   public void append(final LogEvent event) {
      this.manager.sendEvents(this.getLayout(), event);
   }

   public static class Builder extends AbstractAppender.Builder implements org.apache.logging.log4j.core.util.Builder {
      @PluginBuilderAttribute
      private String to;
      @PluginBuilderAttribute
      private String cc;
      @PluginBuilderAttribute
      private String bcc;
      @PluginBuilderAttribute
      private String from;
      @PluginBuilderAttribute
      private String replyTo;
      @PluginBuilderAttribute
      private String subject;
      @PluginBuilderAttribute
      private String smtpProtocol = "smtp";
      @PluginBuilderAttribute
      private String smtpHost;
      @PluginBuilderAttribute
      @ValidPort
      private int smtpPort;
      @PluginBuilderAttribute
      private String smtpUsername;
      @PluginBuilderAttribute(
         sensitive = true
      )
      private String smtpPassword;
      @PluginBuilderAttribute
      private boolean smtpDebug;
      @PluginBuilderAttribute
      private int bufferSize = 512;
      @PluginElement("SSL")
      private SslConfiguration sslConfiguration;

      public Builder setTo(final String to) {
         this.to = to;
         return this;
      }

      public Builder setCc(final String cc) {
         this.cc = cc;
         return this;
      }

      public Builder setBcc(final String bcc) {
         this.bcc = bcc;
         return this;
      }

      public Builder setFrom(final String from) {
         this.from = from;
         return this;
      }

      public Builder setReplyTo(final String replyTo) {
         this.replyTo = replyTo;
         return this;
      }

      public Builder setSubject(final String subject) {
         this.subject = subject;
         return this;
      }

      public Builder setSmtpProtocol(final String smtpProtocol) {
         this.smtpProtocol = smtpProtocol;
         return this;
      }

      public Builder setSmtpHost(final String smtpHost) {
         this.smtpHost = smtpHost;
         return this;
      }

      public Builder setSmtpPort(final int smtpPort) {
         this.smtpPort = smtpPort;
         return this;
      }

      public Builder setSmtpUsername(final String smtpUsername) {
         this.smtpUsername = smtpUsername;
         return this;
      }

      public Builder setSmtpPassword(final String smtpPassword) {
         this.smtpPassword = smtpPassword;
         return this;
      }

      public Builder setSmtpDebug(final boolean smtpDebug) {
         this.smtpDebug = smtpDebug;
         return this;
      }

      public Builder setBufferSize(final int bufferSize) {
         this.bufferSize = bufferSize;
         return this;
      }

      public Builder setSslConfiguration(final SslConfiguration sslConfiguration) {
         this.sslConfiguration = sslConfiguration;
         return this;
      }

      public Builder setLayout(final Layout layout) {
         return (Builder)super.setLayout(layout);
      }

      public Builder setFilter(final Filter filter) {
         return (Builder)super.setFilter(filter);
      }

      public SmtpAppender build() {
         if (this.getLayout() == null) {
            this.setLayout(HtmlLayout.createDefaultLayout());
         }

         if (this.getFilter() == null) {
            this.setFilter(ThresholdFilter.createFilter((Level)null, (Filter.Result)null, (Filter.Result)null));
         }

         if (Strings.isEmpty(this.smtpProtocol)) {
            this.smtpProtocol = "smtp";
         }

         AbstractStringLayout.Serializer subjectSerializer = PatternLayout.newSerializerBuilder().setConfiguration(this.getConfiguration()).setPattern(this.subject).build();
         MailManager.FactoryData data = new MailManager.FactoryData(this.to, this.cc, this.bcc, this.from, this.replyTo, this.subject, subjectSerializer, this.smtpProtocol, this.smtpHost, this.smtpPort, this.smtpUsername, this.smtpPassword, this.smtpDebug, this.bufferSize, this.sslConfiguration, this.getFilter().toString());
         MailManagerFactory factory = (MailManagerFactory)ServiceLoaderUtil.safeStream(MailManagerFactory.class, ServiceLoader.load(MailManagerFactory.class, this.getClass().getClassLoader()), StatusLogger.getLogger()).findAny().orElseGet(() -> SmtpManager.FACTORY);
         MailManager smtpManager = (MailManager)AbstractManager.getManager(data.getManagerName(), factory, data);
         if (smtpManager == null) {
            SmtpAppender.LOGGER.error("Unabled to instantiate SmtpAppender named {}", this.getName());
            return null;
         } else {
            return new SmtpAppender(this.getName(), this.getFilter(), this.getLayout(), smtpManager, this.isIgnoreExceptions(), this.getPropertyArray());
         }
      }
   }
}
