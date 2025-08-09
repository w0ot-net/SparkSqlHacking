package org.apache.logging.log4j.core.layout;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.layout.internal.ExcludeChecker;
import org.apache.logging.log4j.core.layout.internal.IncludeChecker;
import org.apache.logging.log4j.core.layout.internal.ListChecker;
import org.apache.logging.log4j.core.lookup.StrSubstitutor;
import org.apache.logging.log4j.core.net.Severity;
import org.apache.logging.log4j.core.util.JsonUtils;
import org.apache.logging.log4j.core.util.KeyValuePair;
import org.apache.logging.log4j.core.util.NetUtils;
import org.apache.logging.log4j.core.util.Patterns;
import org.apache.logging.log4j.message.MapMessage;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.apache.logging.log4j.util.Strings;
import org.apache.logging.log4j.util.TriConsumer;

@Plugin(
   name = "GelfLayout",
   category = "Core",
   elementType = "layout",
   printObject = true
)
public final class GelfLayout extends AbstractStringLayout {
   private static final char C = ',';
   private static final int COMPRESSION_THRESHOLD = 1024;
   private static final char Q = '"';
   private static final String QC = "\",";
   private static final String QU = "\"_";
   private final KeyValuePair[] additionalFields;
   private final int compressionThreshold;
   private final CompressionType compressionType;
   private final String host;
   private final boolean includeStacktrace;
   private final boolean includeThreadContext;
   private final boolean includeMapMessage;
   private final boolean includeNullDelimiter;
   private final boolean includeNewLineDelimiter;
   private final boolean omitEmptyFields;
   private final PatternLayout layout;
   private final FieldWriter mdcWriter;
   private final FieldWriter mapWriter;
   private static final ThreadLocal messageStringBuilder = new ThreadLocal();
   private static final ThreadLocal timestampStringBuilder = new ThreadLocal();

   /** @deprecated */
   @Deprecated
   public GelfLayout(final String host, final KeyValuePair[] additionalFields, final CompressionType compressionType, final int compressionThreshold, final boolean includeStacktrace) {
      this((Configuration)null, host, additionalFields, compressionType, compressionThreshold, includeStacktrace, true, true, false, false, false, (ListChecker)null, (ListChecker)null, (PatternLayout)null, "", "");
   }

   private GelfLayout(final Configuration config, final String host, final KeyValuePair[] additionalFields, final CompressionType compressionType, final int compressionThreshold, final boolean includeStacktrace, final boolean includeThreadContext, final boolean includeMapMessage, final boolean includeNullDelimiter, final boolean includeNewLineDelimiter, final boolean omitEmptyFields, final ListChecker mdcChecker, final ListChecker mapChecker, final PatternLayout patternLayout, final String mdcPrefix, final String mapPrefix) {
      super(config, StandardCharsets.UTF_8, (AbstractStringLayout.Serializer)null, (AbstractStringLayout.Serializer)null);
      this.host = host != null ? host : NetUtils.getLocalHostname();
      this.additionalFields = additionalFields != null ? additionalFields : KeyValuePair.EMPTY_ARRAY;
      if (config == null) {
         for(KeyValuePair additionalField : this.additionalFields) {
            if (valueNeedsLookup(additionalField.getValue())) {
               throw new IllegalArgumentException("configuration needs to be set when there are additional fields with variables");
            }
         }
      }

      this.compressionType = compressionType;
      this.compressionThreshold = compressionThreshold;
      this.includeStacktrace = includeStacktrace;
      this.includeThreadContext = includeThreadContext;
      this.includeMapMessage = includeMapMessage;
      this.includeNullDelimiter = includeNullDelimiter;
      this.includeNewLineDelimiter = includeNewLineDelimiter;
      this.omitEmptyFields = omitEmptyFields;
      if (includeNullDelimiter && compressionType != GelfLayout.CompressionType.OFF) {
         throw new IllegalArgumentException("null delimiter cannot be used with compression");
      } else {
         this.mdcWriter = new FieldWriter(mdcChecker, mdcPrefix);
         this.mapWriter = new FieldWriter(mapChecker, mapPrefix);
         this.layout = patternLayout;
      }
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("host=").append(this.host);
      sb.append(", compressionType=").append(this.compressionType.toString());
      sb.append(", compressionThreshold=").append(this.compressionThreshold);
      sb.append(", includeStackTrace=").append(this.includeStacktrace);
      sb.append(", includeThreadContext=").append(this.includeThreadContext);
      sb.append(", includeNullDelimiter=").append(this.includeNullDelimiter);
      sb.append(", includeNewLineDelimiter=").append(this.includeNewLineDelimiter);
      String threadVars = this.mdcWriter.getChecker().toString();
      if (threadVars.length() > 0) {
         sb.append(", ").append(threadVars);
      }

      String mapVars = this.mapWriter.getChecker().toString();
      if (mapVars.length() > 0) {
         sb.append(", ").append(mapVars);
      }

      if (this.layout != null) {
         sb.append(", PatternLayout{").append(this.layout.toString()).append("}");
      }

      return sb.toString();
   }

   /** @deprecated */
   @Deprecated
   public static GelfLayout createLayout(@PluginAttribute("host") final String host, @PluginElement("AdditionalField") final KeyValuePair[] additionalFields, @PluginAttribute(value = "compressionType",defaultString = "GZIP") final CompressionType compressionType, @PluginAttribute(value = "compressionThreshold",defaultInt = 1024) final int compressionThreshold, @PluginAttribute(value = "includeStacktrace",defaultBoolean = true) final boolean includeStacktrace) {
      return new GelfLayout((Configuration)null, host, additionalFields, compressionType, compressionThreshold, includeStacktrace, true, true, false, false, false, (ListChecker)null, (ListChecker)null, (PatternLayout)null, "", "");
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return (Builder)(new Builder()).asBuilder();
   }

   public Map getContentFormat() {
      return Collections.emptyMap();
   }

   public String getContentType() {
      return "application/json; charset=" + this.getCharset();
   }

   public byte[] toByteArray(final LogEvent event) {
      StringBuilder text = this.toText(event, getStringBuilder(), false);
      byte[] bytes = this.getBytes(text.toString());
      return this.compressionType != GelfLayout.CompressionType.OFF && bytes.length > this.compressionThreshold ? this.compress(bytes) : bytes;
   }

   public void encode(final LogEvent event, final ByteBufferDestination destination) {
      if (this.compressionType != GelfLayout.CompressionType.OFF) {
         super.encode(event, destination);
      } else {
         StringBuilder text = this.toText(event, getStringBuilder(), true);
         Encoder<StringBuilder> helper = this.getStringBuilderEncoder();
         helper.encode(text, destination);
      }
   }

   public boolean requiresLocation() {
      return Objects.nonNull(this.layout) && this.layout.requiresLocation();
   }

   private byte[] compress(final byte[] bytes) {
      try {
         ByteArrayOutputStream baos = new ByteArrayOutputStream(this.compressionThreshold / 8);
         DeflaterOutputStream stream = this.compressionType.createDeflaterOutputStream(baos);

         byte[] var4;
         label48: {
            try {
               if (stream == null) {
                  var4 = bytes;
                  break label48;
               }

               stream.write(bytes);
               stream.finish();
            } catch (Throwable var7) {
               if (stream != null) {
                  try {
                     stream.close();
                  } catch (Throwable var6) {
                     var7.addSuppressed(var6);
                  }
               }

               throw var7;
            }

            if (stream != null) {
               stream.close();
            }

            return baos.toByteArray();
         }

         if (stream != null) {
            stream.close();
         }

         return var4;
      } catch (IOException e) {
         StatusLogger.getLogger().error(e);
         return bytes;
      }
   }

   public String toSerializable(final LogEvent event) {
      StringBuilder text = this.toText(event, getStringBuilder(), false);
      return text.toString();
   }

   private StringBuilder toText(final LogEvent event, final StringBuilder builder, final boolean gcFree) {
      builder.append('{');
      builder.append("\"version\":\"1.1\",");
      builder.append("\"host\":\"");
      JsonUtils.quoteAsString(toNullSafeString(this.host), builder);
      builder.append("\",");
      builder.append("\"timestamp\":").append(formatTimestamp(event.getTimeMillis())).append(',');
      builder.append("\"level\":").append(this.formatLevel(event.getLevel())).append(',');
      if (event.getThreadName() != null) {
         builder.append("\"_thread\":\"");
         JsonUtils.quoteAsString(event.getThreadName(), builder);
         builder.append("\",");
      }

      if (event.getLoggerName() != null) {
         builder.append("\"_logger\":\"");
         JsonUtils.quoteAsString(event.getLoggerName(), builder);
         builder.append("\",");
      }

      if (this.additionalFields.length > 0) {
         StrSubstitutor strSubstitutor = this.getConfiguration().getStrSubstitutor();

         for(KeyValuePair additionalField : this.additionalFields) {
            String value = valueNeedsLookup(additionalField.getValue()) ? strSubstitutor.replace(event, additionalField.getValue()) : additionalField.getValue();
            if (Strings.isNotEmpty(value) || !this.omitEmptyFields) {
               builder.append("\"_");
               JsonUtils.quoteAsString(additionalField.getKey(), builder);
               builder.append("\":\"");
               JsonUtils.quoteAsString(toNullSafeString(value), builder);
               builder.append("\",");
            }
         }
      }

      if (this.includeThreadContext) {
         event.getContextData().forEach(this.mdcWriter, builder);
      }

      if (this.includeMapMessage && event.getMessage() instanceof MapMessage) {
         ((MapMessage)event.getMessage()).forEach((key, valuex) -> this.mapWriter.accept(key, valuex, builder));
      }

      if (event.getThrown() != null || this.layout != null) {
         builder.append("\"full_message\":\"");
         if (this.layout != null) {
            StringBuilder messageBuffer = getMessageStringBuilder();
            this.layout.serialize(event, messageBuffer);
            JsonUtils.quoteAsString(messageBuffer, builder);
         } else if (this.includeStacktrace) {
            JsonUtils.quoteAsString(formatThrowable(event.getThrown()), builder);
         } else {
            JsonUtils.quoteAsString(event.getThrown().toString(), builder);
         }

         builder.append("\",");
      }

      builder.append("\"short_message\":\"");
      Message message = event.getMessage();
      if (message instanceof CharSequence) {
         JsonUtils.quoteAsString((CharSequence)message, builder);
      } else if (gcFree && message instanceof StringBuilderFormattable) {
         StringBuilder messageBuffer = getMessageStringBuilder();

         try {
            ((StringBuilderFormattable)message).formatTo(messageBuffer);
            JsonUtils.quoteAsString(messageBuffer, builder);
         } finally {
            trimToMaxSize(messageBuffer);
         }
      } else {
         JsonUtils.quoteAsString(toNullSafeString(message.getFormattedMessage()), builder);
      }

      builder.append('"');
      builder.append('}');
      if (this.includeNullDelimiter) {
         builder.append('\u0000');
      }

      if (this.includeNewLineDelimiter) {
         builder.append('\n');
      }

      return builder;
   }

   private static boolean valueNeedsLookup(final String value) {
      return value != null && value.contains("${");
   }

   private static StringBuilder getMessageStringBuilder() {
      StringBuilder result = (StringBuilder)messageStringBuilder.get();
      if (result == null) {
         result = new StringBuilder(1024);
         messageStringBuilder.set(result);
      }

      result.setLength(0);
      return result;
   }

   private static CharSequence toNullSafeString(final CharSequence s) {
      return (CharSequence)(s == null ? "" : s);
   }

   static CharSequence formatTimestamp(final long timeMillis) {
      if (timeMillis < 1000L) {
         return "0";
      } else {
         StringBuilder builder = getTimestampStringBuilder();
         builder.append(timeMillis);
         builder.insert(builder.length() - 3, '.');
         return builder;
      }
   }

   private static StringBuilder getTimestampStringBuilder() {
      StringBuilder result = (StringBuilder)timestampStringBuilder.get();
      if (result == null) {
         result = new StringBuilder(20);
         timestampStringBuilder.set(result);
      }

      result.setLength(0);
      return result;
   }

   private int formatLevel(final Level level) {
      return Severity.getSeverity(level).getCode();
   }

   @SuppressFBWarnings(
      value = {"INFORMATION_EXPOSURE_THROUGH_AN_ERROR_MESSAGE"},
      justification = "Log4j prints stacktraces only to logs, which should be private."
   )
   static CharSequence formatThrowable(final Throwable throwable) {
      StringWriter sw = new StringWriter(2048);
      PrintWriter pw = new PrintWriter(sw);
      throwable.printStackTrace(pw);
      pw.flush();
      return sw.getBuffer();
   }

   public static enum CompressionType {
      GZIP {
         public DeflaterOutputStream createDeflaterOutputStream(final OutputStream os) throws IOException {
            return new GZIPOutputStream(os);
         }
      },
      ZLIB {
         public DeflaterOutputStream createDeflaterOutputStream(final OutputStream os) throws IOException {
            return new DeflaterOutputStream(os);
         }
      },
      OFF {
         public DeflaterOutputStream createDeflaterOutputStream(final OutputStream os) throws IOException {
            return null;
         }
      };

      private CompressionType() {
      }

      public abstract DeflaterOutputStream createDeflaterOutputStream(OutputStream os) throws IOException;

      // $FF: synthetic method
      private static CompressionType[] $values() {
         return new CompressionType[]{GZIP, ZLIB, OFF};
      }
   }

   public static class Builder extends AbstractStringLayout.Builder implements org.apache.logging.log4j.core.util.Builder {
      @PluginBuilderAttribute
      private String host;
      @PluginElement("AdditionalField")
      private KeyValuePair[] additionalFields;
      @PluginBuilderAttribute
      private CompressionType compressionType;
      @PluginBuilderAttribute
      private int compressionThreshold;
      @PluginBuilderAttribute
      private boolean includeStacktrace;
      @PluginBuilderAttribute
      private boolean includeThreadContext;
      @PluginBuilderAttribute
      private boolean includeNullDelimiter;
      @PluginBuilderAttribute
      private boolean includeNewLineDelimiter;
      @PluginBuilderAttribute
      private String threadContextIncludes;
      @PluginBuilderAttribute
      private String threadContextExcludes;
      @PluginBuilderAttribute
      private String mapMessageIncludes;
      @PluginBuilderAttribute
      private String mapMessageExcludes;
      @PluginBuilderAttribute
      private boolean includeMapMessage;
      @PluginBuilderAttribute
      private boolean omitEmptyFields;
      @PluginBuilderAttribute
      private String messagePattern;
      @PluginBuilderAttribute
      private String threadContextPrefix;
      @PluginBuilderAttribute
      private String mapPrefix;
      @PluginElement("PatternSelector")
      private PatternSelector patternSelector;

      public Builder() {
         this.compressionType = GelfLayout.CompressionType.GZIP;
         this.compressionThreshold = 1024;
         this.includeStacktrace = true;
         this.includeThreadContext = true;
         this.includeMapMessage = true;
         this.threadContextPrefix = "";
         this.mapPrefix = "";
         this.setCharset(StandardCharsets.UTF_8);
      }

      public GelfLayout build() {
         ListChecker mdcChecker = this.createChecker(this.threadContextExcludes, this.threadContextIncludes);
         ListChecker mapChecker = this.createChecker(this.mapMessageExcludes, this.mapMessageIncludes);
         PatternLayout patternLayout = null;
         if (this.messagePattern != null && this.patternSelector != null) {
            AbstractLayout.LOGGER.error("A message pattern and PatternSelector cannot both be specified on GelfLayout, ignoring message pattern");
            this.messagePattern = null;
         }

         if (this.messagePattern != null) {
            patternLayout = PatternLayout.newBuilder().withPattern(this.messagePattern).withAlwaysWriteExceptions(this.includeStacktrace).withConfiguration(this.getConfiguration()).build();
         }

         if (this.patternSelector != null) {
            patternLayout = PatternLayout.newBuilder().withPatternSelector(this.patternSelector).withAlwaysWriteExceptions(this.includeStacktrace).withConfiguration(this.getConfiguration()).build();
         }

         return new GelfLayout(this.getConfiguration(), this.host, this.additionalFields, this.compressionType, this.compressionThreshold, this.includeStacktrace, this.includeThreadContext, this.includeMapMessage, this.includeNullDelimiter, this.includeNewLineDelimiter, this.omitEmptyFields, mdcChecker, mapChecker, patternLayout, this.threadContextPrefix, this.mapPrefix);
      }

      private ListChecker createChecker(final String excludes, final String includes) {
         ListChecker checker = null;
         if (excludes != null) {
            String[] array = excludes.split(Patterns.COMMA_SEPARATOR);
            if (array.length > 0) {
               List<String> excludeList = new ArrayList(array.length);

               for(String str : array) {
                  excludeList.add(str.trim());
               }

               checker = new ExcludeChecker(excludeList);
            }
         }

         if (includes != null) {
            String[] array = includes.split(Patterns.COMMA_SEPARATOR);
            if (array.length > 0) {
               List<String> includeList = new ArrayList(array.length);

               for(String str : array) {
                  includeList.add(str.trim());
               }

               checker = new IncludeChecker(includeList);
            }
         }

         if (checker == null) {
            checker = ListChecker.NOOP_CHECKER;
         }

         return checker;
      }

      public String getHost() {
         return this.host;
      }

      public CompressionType getCompressionType() {
         return this.compressionType;
      }

      public int getCompressionThreshold() {
         return this.compressionThreshold;
      }

      public boolean isIncludeStacktrace() {
         return this.includeStacktrace;
      }

      public boolean isIncludeThreadContext() {
         return this.includeThreadContext;
      }

      public boolean isIncludeNullDelimiter() {
         return this.includeNullDelimiter;
      }

      public boolean isIncludeNewLineDelimiter() {
         return this.includeNewLineDelimiter;
      }

      public KeyValuePair[] getAdditionalFields() {
         return this.additionalFields;
      }

      public Builder setHost(final String host) {
         this.host = host;
         return (Builder)this.asBuilder();
      }

      public Builder setCompressionType(final CompressionType compressionType) {
         this.compressionType = compressionType;
         return (Builder)this.asBuilder();
      }

      public Builder setCompressionThreshold(final int compressionThreshold) {
         this.compressionThreshold = compressionThreshold;
         return (Builder)this.asBuilder();
      }

      public Builder setIncludeStacktrace(final boolean includeStacktrace) {
         this.includeStacktrace = includeStacktrace;
         return (Builder)this.asBuilder();
      }

      public Builder setIncludeThreadContext(final boolean includeThreadContext) {
         this.includeThreadContext = includeThreadContext;
         return (Builder)this.asBuilder();
      }

      public Builder setIncludeNullDelimiter(final boolean includeNullDelimiter) {
         this.includeNullDelimiter = includeNullDelimiter;
         return (Builder)this.asBuilder();
      }

      public Builder setIncludeNewLineDelimiter(final boolean includeNewLineDelimiter) {
         this.includeNewLineDelimiter = includeNewLineDelimiter;
         return (Builder)this.asBuilder();
      }

      public Builder setAdditionalFields(final KeyValuePair[] additionalFields) {
         this.additionalFields = additionalFields;
         return (Builder)this.asBuilder();
      }

      public Builder setMessagePattern(final String pattern) {
         this.messagePattern = pattern;
         return (Builder)this.asBuilder();
      }

      public Builder setPatternSelector(final PatternSelector patternSelector) {
         this.patternSelector = patternSelector;
         return (Builder)this.asBuilder();
      }

      public Builder setMdcIncludes(final String mdcIncludes) {
         this.threadContextIncludes = mdcIncludes;
         return (Builder)this.asBuilder();
      }

      public Builder setMdcExcludes(final String mdcExcludes) {
         this.threadContextExcludes = mdcExcludes;
         return (Builder)this.asBuilder();
      }

      public Builder setIncludeMapMessage(final boolean includeMapMessage) {
         this.includeMapMessage = includeMapMessage;
         return (Builder)this.asBuilder();
      }

      public Builder setMapMessageIncludes(final String mapMessageIncludes) {
         this.mapMessageIncludes = mapMessageIncludes;
         return (Builder)this.asBuilder();
      }

      public Builder setMapMessageExcludes(final String mapMessageExcludes) {
         this.mapMessageExcludes = mapMessageExcludes;
         return (Builder)this.asBuilder();
      }

      public Builder setThreadContextPrefix(final String prefix) {
         if (prefix != null) {
            this.threadContextPrefix = prefix;
         }

         return (Builder)this.asBuilder();
      }

      public Builder setMapPrefix(final String prefix) {
         if (prefix != null) {
            this.mapPrefix = prefix;
         }

         return (Builder)this.asBuilder();
      }
   }

   private class FieldWriter implements TriConsumer {
      private final ListChecker checker;
      private final String prefix;

      FieldWriter(final ListChecker checker, final String prefix) {
         this.checker = checker;
         this.prefix = prefix;
      }

      public void accept(final String key, final Object value, final StringBuilder stringBuilder) {
         String stringValue = String.valueOf(value);
         if (this.checker.check(key) && (Strings.isNotEmpty(stringValue) || !GelfLayout.this.omitEmptyFields)) {
            stringBuilder.append("\"_");
            JsonUtils.quoteAsString(Strings.concat(this.prefix, key), stringBuilder);
            stringBuilder.append("\":\"");
            JsonUtils.quoteAsString(GelfLayout.toNullSafeString(stringValue), stringBuilder);
            stringBuilder.append("\",");
         }

      }

      public ListChecker getChecker() {
         return this.checker;
      }
   }
}
