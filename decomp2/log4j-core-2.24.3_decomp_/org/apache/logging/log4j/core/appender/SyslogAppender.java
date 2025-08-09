package org.apache.logging.log4j.core.appender;

import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.layout.LoggerFields;
import org.apache.logging.log4j.core.layout.Rfc5424Layout;
import org.apache.logging.log4j.core.layout.SyslogLayout;
import org.apache.logging.log4j.core.net.AbstractSocketManager;
import org.apache.logging.log4j.core.net.Advertiser;
import org.apache.logging.log4j.core.net.Facility;
import org.apache.logging.log4j.core.net.Protocol;
import org.apache.logging.log4j.core.net.ssl.SslConfiguration;
import org.apache.logging.log4j.core.util.Constants;
import org.apache.logging.log4j.util.EnglishEnums;

@Plugin(
   name = "Syslog",
   category = "Core",
   elementType = "appender",
   printObject = true
)
public class SyslogAppender extends SocketAppender {
   protected static final String RFC5424 = "RFC5424";

   protected SyslogAppender(final String name, final Layout layout, final Filter filter, final boolean ignoreExceptions, final boolean immediateFlush, final AbstractSocketManager manager, final Advertiser advertiser, final Property[] properties) {
      super(name, layout, filter, manager, ignoreExceptions, immediateFlush, advertiser, properties);
   }

   /** @deprecated */
   @Deprecated
   protected SyslogAppender(final String name, final Layout layout, final Filter filter, final boolean ignoreExceptions, final boolean immediateFlush, final AbstractSocketManager manager, final Advertiser advertiser) {
      super(name, layout, filter, manager, ignoreExceptions, immediateFlush, advertiser, Property.EMPTY_ARRAY);
   }

   /** @deprecated */
   @Deprecated
   public static SyslogAppender createAppender(final String host, final int port, final String protocolStr, final SslConfiguration sslConfiguration, final int connectTimeoutMillis, final int reconnectDelayMillis, final boolean immediateFail, final String name, final boolean immediateFlush, final boolean ignoreExceptions, final Facility facility, final String id, final int enterpriseNumber, final boolean includeMdc, final String mdcId, final String mdcPrefix, final String eventPrefix, final boolean newLine, final String escapeNL, final String appName, final String msgId, final String excludes, final String includes, final String required, final String format, final Filter filter, final Configuration configuration, final Charset charset, final String exceptionPattern, final LoggerFields[] loggerFields, final boolean advertise) {
      return ((Builder)((Builder)((Builder)((Builder)((Builder)((Builder)((Builder)((Builder)((Builder)((Builder)((Builder)((Builder)((Builder)newSyslogAppenderBuilder().setHost(host)).setPort(port)).setProtocol((Protocol)EnglishEnums.valueOf(Protocol.class, protocolStr))).setSslConfiguration(sslConfiguration)).setConnectTimeoutMillis(connectTimeoutMillis)).setReconnectDelayMillis(reconnectDelayMillis)).setImmediateFail(immediateFail)).setName(appName)).setImmediateFlush(immediateFlush)).setIgnoreExceptions(ignoreExceptions)).setFilter(filter)).setConfiguration(configuration)).setAdvertise(advertise)).setFacility(facility).setId(id).setEnterpriseNumber(enterpriseNumber).setIncludeMdc(includeMdc).setMdcId(mdcId).setMdcPrefix(mdcPrefix).setEventPrefix(eventPrefix).setNewLine(newLine).setAppName(appName).setMsgId(msgId).setExcludes(excludes).setIncludeMdc(includeMdc).setRequired(required).setFormat(format).setCharsetName(charset).setExceptionPattern(exceptionPattern).setLoggerFields(loggerFields).build();
   }

   @PluginBuilderFactory
   public static Builder newSyslogAppenderBuilder() {
      return (Builder)(new Builder()).asBuilder();
   }

   public static class Builder extends SocketAppender.AbstractBuilder implements org.apache.logging.log4j.core.util.Builder {
      @PluginBuilderAttribute("facility")
      private Facility facility;
      @PluginBuilderAttribute("id")
      private String id;
      @PluginBuilderAttribute("enterpriseNumber")
      private String enterpriseNumber;
      @PluginBuilderAttribute("includeMdc")
      private boolean includeMdc;
      @PluginBuilderAttribute("mdcId")
      private String mdcId;
      @PluginBuilderAttribute("mdcPrefix")
      private String mdcPrefix;
      @PluginBuilderAttribute("eventPrefix")
      private String eventPrefix;
      @PluginBuilderAttribute("newLine")
      private boolean newLine;
      @PluginBuilderAttribute("newLineEscape")
      private String escapeNL;
      @PluginBuilderAttribute("appName")
      private String appName;
      @PluginBuilderAttribute("messageId")
      private String msgId;
      @PluginBuilderAttribute("mdcExcludes")
      private String excludes;
      @PluginBuilderAttribute("mdcIncludes")
      private String includes;
      @PluginBuilderAttribute("mdcRequired")
      private String required;
      @PluginBuilderAttribute("format")
      private String format;
      @PluginBuilderAttribute("charset")
      private Charset charsetName;
      @PluginBuilderAttribute("exceptionPattern")
      private String exceptionPattern;
      @PluginElement("LoggerFields")
      private LoggerFields[] loggerFields;

      public Builder() {
         this.facility = Facility.LOCAL0;
         this.enterpriseNumber = String.valueOf(32473);
         this.includeMdc = true;
         this.charsetName = StandardCharsets.UTF_8;
      }

      public SyslogAppender build() {
         Protocol protocol = this.getProtocol();
         SslConfiguration sslConfiguration = this.getSslConfiguration();
         boolean useTlsMessageFormat = sslConfiguration != null || protocol == Protocol.SSL;
         Configuration configuration = this.getConfiguration();
         Layout<? extends Serializable> layout = this.getLayout();
         if (layout == null) {
            layout = (Layout<? extends Serializable>)("RFC5424".equalsIgnoreCase(this.format) ? ((Rfc5424Layout.Rfc5424LayoutBuilder)(new Rfc5424Layout.Rfc5424LayoutBuilder()).setFacility(this.facility).setId(this.id).setEin(this.enterpriseNumber).setIncludeMDC(this.includeMdc).setMdcId(this.mdcId).setMdcPrefix(this.mdcPrefix).setEventPrefix(this.eventPrefix).setIncludeNL(this.newLine).setEscapeNL(this.escapeNL).setAppName(this.appName).setMessageId(this.msgId).setExcludes(this.excludes).setIncludes(this.includes).setRequired(this.required).setExceptionPattern(this.exceptionPattern).setUseTLSMessageFormat(useTlsMessageFormat).setLoggerFields(this.loggerFields).setConfiguration(configuration)).build() : ((SyslogLayout.Builder)SyslogLayout.newBuilder().setFacility(this.facility).setIncludeNewLine(this.newLine).setEscapeNL(this.escapeNL).setCharset(this.charsetName)).build());
         }

         String name = this.getName();
         if (name == null) {
            SyslogAppender.LOGGER.error("No name provided for SyslogAppender");
            return null;
         } else {
            AbstractSocketManager manager = SocketAppender.createSocketManager(name, protocol, this.getHost(), this.getPort(), this.getConnectTimeoutMillis(), sslConfiguration, this.getReconnectDelayMillis(), this.getImmediateFail(), layout, Constants.ENCODER_BYTE_BUFFER_SIZE, this.getSocketOptions());
            return new SyslogAppender(name, layout, this.getFilter(), this.isIgnoreExceptions(), this.isImmediateFlush(), manager, this.getAdvertise() ? configuration.getAdvertiser() : null, (Property[])null);
         }
      }

      public Facility getFacility() {
         return this.facility;
      }

      public String getId() {
         return this.id;
      }

      public String getEnterpriseNumber() {
         return this.enterpriseNumber;
      }

      public boolean isIncludeMdc() {
         return this.includeMdc;
      }

      public String getMdcId() {
         return this.mdcId;
      }

      public String getMdcPrefix() {
         return this.mdcPrefix;
      }

      public String getEventPrefix() {
         return this.eventPrefix;
      }

      public boolean isNewLine() {
         return this.newLine;
      }

      public String getEscapeNL() {
         return this.escapeNL;
      }

      public String getAppName() {
         return this.appName;
      }

      public String getMsgId() {
         return this.msgId;
      }

      public String getExcludes() {
         return this.excludes;
      }

      public String getIncludes() {
         return this.includes;
      }

      public String getRequired() {
         return this.required;
      }

      public String getFormat() {
         return this.format;
      }

      public Charset getCharsetName() {
         return this.charsetName;
      }

      public String getExceptionPattern() {
         return this.exceptionPattern;
      }

      public LoggerFields[] getLoggerFields() {
         return this.loggerFields;
      }

      public Builder setFacility(final Facility facility) {
         this.facility = facility;
         return (Builder)this.asBuilder();
      }

      public Builder setId(final String id) {
         this.id = id;
         return (Builder)this.asBuilder();
      }

      public Builder setEnterpriseNumber(final String enterpriseNumber) {
         this.enterpriseNumber = enterpriseNumber;
         return (Builder)this.asBuilder();
      }

      /** @deprecated */
      public Builder setEnterpriseNumber(final int enterpriseNumber) {
         this.enterpriseNumber = String.valueOf(enterpriseNumber);
         return (Builder)this.asBuilder();
      }

      public Builder setIncludeMdc(final boolean includeMdc) {
         this.includeMdc = includeMdc;
         return (Builder)this.asBuilder();
      }

      public Builder setMdcId(final String mdcId) {
         this.mdcId = mdcId;
         return (Builder)this.asBuilder();
      }

      public Builder setMdcPrefix(final String mdcPrefix) {
         this.mdcPrefix = mdcPrefix;
         return (Builder)this.asBuilder();
      }

      public Builder setEventPrefix(final String eventPrefix) {
         this.eventPrefix = eventPrefix;
         return (Builder)this.asBuilder();
      }

      public Builder setNewLine(final boolean newLine) {
         this.newLine = newLine;
         return (Builder)this.asBuilder();
      }

      public Builder setEscapeNL(final String escapeNL) {
         this.escapeNL = escapeNL;
         return (Builder)this.asBuilder();
      }

      public Builder setAppName(final String appName) {
         this.appName = appName;
         return (Builder)this.asBuilder();
      }

      public Builder setMsgId(final String msgId) {
         this.msgId = msgId;
         return (Builder)this.asBuilder();
      }

      public Builder setExcludes(final String excludes) {
         this.excludes = excludes;
         return (Builder)this.asBuilder();
      }

      public Builder setIncludes(final String includes) {
         this.includes = includes;
         return (Builder)this.asBuilder();
      }

      public Builder setRequired(final String required) {
         this.required = required;
         return (Builder)this.asBuilder();
      }

      public Builder setFormat(final String format) {
         this.format = format;
         return (Builder)this.asBuilder();
      }

      public Builder setCharsetName(final Charset charset) {
         this.charsetName = charset;
         return (Builder)this.asBuilder();
      }

      public Builder setExceptionPattern(final String exceptionPattern) {
         this.exceptionPattern = exceptionPattern;
         return (Builder)this.asBuilder();
      }

      public Builder setLoggerFields(final LoggerFields[] loggerFields) {
         this.loggerFields = loggerFields;
         return (Builder)this.asBuilder();
      }
   }
}
