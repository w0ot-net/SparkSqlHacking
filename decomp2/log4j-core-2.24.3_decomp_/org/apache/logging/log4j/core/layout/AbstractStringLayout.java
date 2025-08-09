package org.apache.logging.log4j.core.layout;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.StringLayout;
import org.apache.logging.log4j.core.config.AbstractConfiguration;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.impl.DefaultLogEventFactory;
import org.apache.logging.log4j.core.impl.LocationAware;
import org.apache.logging.log4j.core.util.Constants;
import org.apache.logging.log4j.core.util.StringEncoder;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.StringBuilders;

public abstract class AbstractStringLayout extends AbstractLayout implements StringLayout, LocationAware {
   protected static final int DEFAULT_STRING_BUILDER_SIZE = 1024;
   protected static final int MAX_STRING_BUILDER_SIZE = Math.max(1024, size("log4j.layoutStringBuilder.maxSize", 2048));
   private static final ThreadLocal threadLocal = new ThreadLocal();
   private Encoder textEncoder;
   private final Charset charset;
   private final Serializer footerSerializer;
   private final Serializer headerSerializer;

   public boolean requiresLocation() {
      return false;
   }

   protected static StringBuilder getStringBuilder() {
      if (AbstractLogger.getRecursionDepth() > 1) {
         return new StringBuilder(1024);
      } else {
         StringBuilder result = (StringBuilder)threadLocal.get();
         if (result == null) {
            result = new StringBuilder(1024);
            threadLocal.set(result);
         }

         trimToMaxSize(result);
         result.setLength(0);
         return result;
      }
   }

   private static int size(final String property, final int defaultValue) {
      return PropertiesUtil.getProperties().getIntegerProperty(property, defaultValue);
   }

   protected static void trimToMaxSize(final StringBuilder stringBuilder) {
      StringBuilders.trimToMaxSize(stringBuilder, MAX_STRING_BUILDER_SIZE);
   }

   protected AbstractStringLayout(final Charset charset) {
      this(charset, (byte[])null, (byte[])null);
   }

   protected AbstractStringLayout(final Charset aCharset, final byte[] header, final byte[] footer) {
      super((Configuration)null, header, footer);
      this.headerSerializer = null;
      this.footerSerializer = null;
      this.charset = aCharset == null ? StandardCharsets.UTF_8 : aCharset;
      this.textEncoder = Constants.ENABLE_DIRECT_ENCODERS ? new StringBuilderEncoder(this.charset) : null;
   }

   protected AbstractStringLayout(final Configuration config, final Charset aCharset, final Serializer headerSerializer, final Serializer footerSerializer) {
      super(config, (byte[])null, (byte[])null);
      this.headerSerializer = headerSerializer;
      this.footerSerializer = footerSerializer;
      this.charset = aCharset == null ? StandardCharsets.UTF_8 : aCharset;
      this.textEncoder = Constants.ENABLE_DIRECT_ENCODERS ? new StringBuilderEncoder(this.charset) : null;
   }

   protected byte[] getBytes(final String s) {
      return s.getBytes(this.charset);
   }

   public Charset getCharset() {
      return this.charset;
   }

   public String getContentType() {
      return "text/plain";
   }

   public byte[] getFooter() {
      return this.serializeToBytes(this.footerSerializer, super.getFooter());
   }

   public Serializer getFooterSerializer() {
      return this.footerSerializer;
   }

   public byte[] getHeader() {
      return this.serializeToBytes(this.headerSerializer, super.getHeader());
   }

   public Serializer getHeaderSerializer() {
      return this.headerSerializer;
   }

   private DefaultLogEventFactory getLogEventFactory() {
      return DefaultLogEventFactory.getInstance();
   }

   protected Encoder getStringBuilderEncoder() {
      if (this.textEncoder == null) {
         this.textEncoder = new StringBuilderEncoder(this.getCharset());
      }

      return this.textEncoder;
   }

   protected byte[] serializeToBytes(final Serializer serializer, final byte[] defaultValue) {
      String serializable = this.serializeToString(serializer);
      return serializable == null ? defaultValue : StringEncoder.toBytes(serializable, this.getCharset());
   }

   protected String serializeToString(final Serializer serializer) {
      if (serializer == null) {
         return null;
      } else {
         String loggerName;
         Level level;
         if (this.configuration != null) {
            LoggerConfig rootLogger = this.configuration.getRootLogger();
            loggerName = rootLogger.getName();
            level = rootLogger.getLevel();
         } else {
            loggerName = "";
            level = AbstractConfiguration.getDefaultLevel();
         }

         LogEvent logEvent = this.getLogEventFactory().createEvent(loggerName, (Marker)null, "", level, (Message)null, (List)null, (Throwable)null);
         return serializer.toSerializable(logEvent);
      }
   }

   public byte[] toByteArray(final LogEvent event) {
      return this.getBytes((String)this.toSerializable(event));
   }

   public abstract static class Builder extends AbstractLayout.Builder {
      @PluginBuilderAttribute("charset")
      private Charset charset;
      @PluginElement("footerSerializer")
      private Serializer footerSerializer;
      @PluginElement("headerSerializer")
      private Serializer headerSerializer;

      public Charset getCharset() {
         return this.charset;
      }

      public Serializer getFooterSerializer() {
         return this.footerSerializer;
      }

      public Serializer getHeaderSerializer() {
         return this.headerSerializer;
      }

      public Builder setCharset(final Charset charset) {
         this.charset = charset;
         return (Builder)this.asBuilder();
      }

      public Builder setFooterSerializer(final Serializer footerSerializer) {
         this.footerSerializer = footerSerializer;
         return (Builder)this.asBuilder();
      }

      public Builder setHeaderSerializer(final Serializer headerSerializer) {
         this.headerSerializer = headerSerializer;
         return (Builder)this.asBuilder();
      }
   }

   public interface Serializer extends Serializer2 {
      String toSerializable(final LogEvent event);

      default StringBuilder toSerializable(final LogEvent event, final StringBuilder builder) {
         builder.append(this.toSerializable(event));
         return builder;
      }
   }

   public interface Serializer2 {
      StringBuilder toSerializable(final LogEvent event, final StringBuilder builder);
   }
}
