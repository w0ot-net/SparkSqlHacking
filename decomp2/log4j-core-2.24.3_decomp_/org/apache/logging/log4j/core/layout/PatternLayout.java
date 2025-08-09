package org.apache.logging.log4j.core.layout;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginConfiguration;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.impl.LocationAware;
import org.apache.logging.log4j.core.pattern.FormattingInfo;
import org.apache.logging.log4j.core.pattern.LogEventPatternConverter;
import org.apache.logging.log4j.core.pattern.PatternFormatter;
import org.apache.logging.log4j.core.pattern.PatternParser;
import org.apache.logging.log4j.core.pattern.RegexReplacement;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.logging.log4j.util.Strings;

@Plugin(
   name = "PatternLayout",
   category = "Core",
   elementType = "layout",
   printObject = true
)
public final class PatternLayout extends AbstractStringLayout {
   public static final String DEFAULT_CONVERSION_PATTERN = "%m%n";
   public static final String TTCC_CONVERSION_PATTERN = "%r [%t] %p %c %notEmpty{%x }- %m%n";
   public static final String SIMPLE_CONVERSION_PATTERN = "%d [%t] %p %c - %m%n";
   public static final String KEY = "Converter";
   private final String conversionPattern;
   private final PatternSelector patternSelector;
   private final AbstractStringLayout.Serializer eventSerializer;

   private PatternLayout(final Configuration config, final RegexReplacement replace, final String eventPattern, final PatternSelector patternSelector, final Charset charset, final boolean alwaysWriteExceptions, final boolean disableAnsi, final boolean noConsoleNoAnsi, final String headerPattern, final String footerPattern) {
      super(config, charset, newSerializerBuilder().setConfiguration(config).setReplace(replace).setPatternSelector(patternSelector).setAlwaysWriteExceptions(alwaysWriteExceptions).setDisableAnsi(disableAnsi).setNoConsoleNoAnsi(noConsoleNoAnsi).setPattern(headerPattern).build(), newSerializerBuilder().setConfiguration(config).setReplace(replace).setPatternSelector(patternSelector).setAlwaysWriteExceptions(alwaysWriteExceptions).setDisableAnsi(disableAnsi).setNoConsoleNoAnsi(noConsoleNoAnsi).setPattern(footerPattern).build());
      this.conversionPattern = eventPattern;
      this.patternSelector = patternSelector;
      this.eventSerializer = newSerializerBuilder().setConfiguration(config).setReplace(replace).setPatternSelector(patternSelector).setAlwaysWriteExceptions(alwaysWriteExceptions).setDisableAnsi(disableAnsi).setNoConsoleNoAnsi(noConsoleNoAnsi).setPattern(eventPattern).setDefaultPattern("%m%n").build();
   }

   public static SerializerBuilder newSerializerBuilder() {
      return new SerializerBuilder();
   }

   public boolean requiresLocation() {
      return this.eventSerializer instanceof LocationAware && ((LocationAware)this.eventSerializer).requiresLocation();
   }

   /** @deprecated */
   @Deprecated
   public static AbstractStringLayout.Serializer createSerializer(final Configuration configuration, final RegexReplacement replace, final String pattern, final String defaultPattern, final PatternSelector patternSelector, final boolean alwaysWriteExceptions, final boolean noConsoleNoAnsi) {
      SerializerBuilder builder = newSerializerBuilder();
      builder.setAlwaysWriteExceptions(alwaysWriteExceptions);
      builder.setConfiguration(configuration);
      builder.setDefaultPattern(defaultPattern);
      builder.setNoConsoleNoAnsi(noConsoleNoAnsi);
      builder.setPattern(pattern);
      builder.setPatternSelector(patternSelector);
      builder.setReplace(replace);
      return builder.build();
   }

   public String getConversionPattern() {
      return this.conversionPattern;
   }

   public Map getContentFormat() {
      Map<String, String> result = new HashMap();
      result.put("structured", "false");
      result.put("formatType", "conversion");
      result.put("format", this.conversionPattern);
      return result;
   }

   public String toSerializable(final LogEvent event) {
      return this.eventSerializer.toSerializable(event);
   }

   public void serialize(final LogEvent event, final StringBuilder stringBuilder) {
      this.eventSerializer.toSerializable(event, stringBuilder);
   }

   public void encode(final LogEvent event, final ByteBufferDestination destination) {
      StringBuilder text = this.toText(this.eventSerializer, event, getStringBuilder());
      Encoder<StringBuilder> encoder = this.getStringBuilderEncoder();
      encoder.encode(text, destination);
      trimToMaxSize(text);
   }

   private StringBuilder toText(final AbstractStringLayout.Serializer2 serializer, final LogEvent event, final StringBuilder destination) {
      return serializer.toSerializable(event, destination);
   }

   public static PatternParser createPatternParser(final Configuration config) {
      if (config == null) {
         return new PatternParser(config, "Converter", LogEventPatternConverter.class);
      } else {
         PatternParser parser = (PatternParser)config.getComponent("Converter");
         if (parser == null) {
            parser = new PatternParser(config, "Converter", LogEventPatternConverter.class);
            config.addComponent("Converter", parser);
            parser = (PatternParser)config.getComponent("Converter");
         }

         return parser;
      }
   }

   public String toString() {
      return this.patternSelector == null ? this.conversionPattern : this.patternSelector.toString();
   }

   /** @deprecated */
   @PluginFactory
   @Deprecated
   public static PatternLayout createLayout(@PluginAttribute(value = "pattern",defaultString = "%m%n") final String pattern, @PluginElement("PatternSelector") final PatternSelector patternSelector, @PluginConfiguration final Configuration config, @PluginElement("Replace") final RegexReplacement replace, @PluginAttribute("charset") final Charset charset, @PluginAttribute(value = "alwaysWriteExceptions",defaultBoolean = true) final boolean alwaysWriteExceptions, @PluginAttribute("noConsoleNoAnsi") final boolean noConsoleNoAnsi, @PluginAttribute("header") final String headerPattern, @PluginAttribute("footer") final String footerPattern) {
      return newBuilder().withPattern(pattern).withPatternSelector(patternSelector).withConfiguration(config).withRegexReplacement(replace).withCharset(charset).withAlwaysWriteExceptions(alwaysWriteExceptions).withNoConsoleNoAnsi(noConsoleNoAnsi).withHeader(headerPattern).withFooter(footerPattern).build();
   }

   public static PatternLayout createDefaultLayout() {
      return newBuilder().build();
   }

   public static PatternLayout createDefaultLayout(final Configuration configuration) {
      return newBuilder().withConfiguration(configuration).build();
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return new Builder();
   }

   public AbstractStringLayout.Serializer getEventSerializer() {
      return this.eventSerializer;
   }

   private static final class NoFormatPatternSerializer implements PatternSerializer {
      private final LogEventPatternConverter[] converters;

      private NoFormatPatternSerializer(final PatternFormatter[] formatters) {
         this.converters = new LogEventPatternConverter[formatters.length];

         for(int i = 0; i < formatters.length; ++i) {
            this.converters[i] = formatters[i].getConverter();
         }

      }

      public String toSerializable(final LogEvent event) {
         StringBuilder sb = AbstractStringLayout.getStringBuilder();

         String var3;
         try {
            var3 = this.toSerializable(event, sb).toString();
         } finally {
            AbstractStringLayout.trimToMaxSize(sb);
         }

         return var3;
      }

      public StringBuilder toSerializable(final LogEvent event, final StringBuilder buffer) {
         for(LogEventPatternConverter converter : this.converters) {
            converter.format(event, buffer);
         }

         return buffer;
      }

      public boolean requiresLocation() {
         for(LogEventPatternConverter converter : this.converters) {
            if (converter instanceof LocationAware && ((LocationAware)converter).requiresLocation()) {
               return true;
            }
         }

         return false;
      }

      public String toString() {
         return super.toString() + "[converters=" + Arrays.toString(this.converters) + "]";
      }
   }

   private static final class PatternFormatterPatternSerializer implements PatternSerializer {
      private final PatternFormatter[] formatters;

      private PatternFormatterPatternSerializer(final PatternFormatter[] formatters) {
         this.formatters = formatters;
      }

      public String toSerializable(final LogEvent event) {
         StringBuilder sb = AbstractStringLayout.getStringBuilder();

         String var3;
         try {
            var3 = this.toSerializable(event, sb).toString();
         } finally {
            AbstractStringLayout.trimToMaxSize(sb);
         }

         return var3;
      }

      public StringBuilder toSerializable(final LogEvent event, final StringBuilder buffer) {
         for(PatternFormatter formatter : this.formatters) {
            formatter.format(event, buffer);
         }

         return buffer;
      }

      public boolean requiresLocation() {
         for(PatternFormatter formatter : this.formatters) {
            if (formatter.requiresLocation()) {
               return true;
            }
         }

         return false;
      }

      public String toString() {
         return super.toString() + "[formatters=" + Arrays.toString(this.formatters) + "]";
      }
   }

   private static final class PatternSerializerWithReplacement implements AbstractStringLayout.Serializer, LocationAware {
      private final PatternSerializer delegate;
      private final RegexReplacement replace;

      private PatternSerializerWithReplacement(final PatternSerializer delegate, final RegexReplacement replace) {
         this.delegate = delegate;
         this.replace = replace;
      }

      public String toSerializable(final LogEvent event) {
         StringBuilder sb = AbstractStringLayout.getStringBuilder();

         String var3;
         try {
            var3 = this.toSerializable(event, sb).toString();
         } finally {
            AbstractStringLayout.trimToMaxSize(sb);
         }

         return var3;
      }

      public StringBuilder toSerializable(final LogEvent event, final StringBuilder buf) {
         StringBuilder buffer = this.delegate.toSerializable(event, buf);
         String str = buffer.toString();
         str = this.replace.format(str);
         buffer.setLength(0);
         buffer.append(str);
         return buffer;
      }

      public boolean requiresLocation() {
         return this.delegate.requiresLocation();
      }

      public String toString() {
         return super.toString() + "[delegate=" + this.delegate + ", replace=" + this.replace + "]";
      }
   }

   public static class SerializerBuilder implements org.apache.logging.log4j.core.util.Builder {
      private Configuration configuration;
      private RegexReplacement replace;
      private String pattern;
      private String defaultPattern;
      private PatternSelector patternSelector;
      private boolean alwaysWriteExceptions;
      private boolean disableAnsi;
      private boolean noConsoleNoAnsi;

      public AbstractStringLayout.Serializer build() {
         if (Strings.isEmpty(this.pattern) && Strings.isEmpty(this.defaultPattern)) {
            return null;
         } else if (this.patternSelector == null) {
            try {
               PatternParser parser = PatternLayout.createPatternParser(this.configuration);
               List<PatternFormatter> list = parser.parse(this.pattern == null ? this.defaultPattern : this.pattern, this.alwaysWriteExceptions, this.disableAnsi, this.noConsoleNoAnsi);
               PatternFormatter[] formatters = (PatternFormatter[])list.toArray(PatternFormatter.EMPTY_ARRAY);
               boolean hasFormattingInfo = false;

               for(PatternFormatter formatter : formatters) {
                  FormattingInfo info = formatter.getFormattingInfo();
                  if (info != null && info != FormattingInfo.getDefault()) {
                     hasFormattingInfo = true;
                     break;
                  }
               }

               PatternSerializer serializer = (PatternSerializer)(hasFormattingInfo ? new PatternFormatterPatternSerializer(formatters) : new NoFormatPatternSerializer(formatters));
               return (AbstractStringLayout.Serializer)(this.replace == null ? serializer : new PatternSerializerWithReplacement(serializer, this.replace));
            } catch (RuntimeException ex) {
               throw new IllegalArgumentException("Cannot parse pattern '" + this.pattern + "'", ex);
            }
         } else {
            return new PatternSelectorSerializer(this.patternSelector, this.replace);
         }
      }

      public SerializerBuilder setConfiguration(final Configuration configuration) {
         this.configuration = configuration;
         return this;
      }

      public SerializerBuilder setReplace(final RegexReplacement replace) {
         this.replace = replace;
         return this;
      }

      public SerializerBuilder setPattern(final String pattern) {
         this.pattern = pattern;
         return this;
      }

      public SerializerBuilder setDefaultPattern(final String defaultPattern) {
         this.defaultPattern = defaultPattern;
         return this;
      }

      public SerializerBuilder setPatternSelector(final PatternSelector patternSelector) {
         this.patternSelector = patternSelector;
         return this;
      }

      public SerializerBuilder setAlwaysWriteExceptions(final boolean alwaysWriteExceptions) {
         this.alwaysWriteExceptions = alwaysWriteExceptions;
         return this;
      }

      public SerializerBuilder setDisableAnsi(final boolean disableAnsi) {
         this.disableAnsi = disableAnsi;
         return this;
      }

      public SerializerBuilder setNoConsoleNoAnsi(final boolean noConsoleNoAnsi) {
         this.noConsoleNoAnsi = noConsoleNoAnsi;
         return this;
      }
   }

   private static final class PatternSelectorSerializer implements AbstractStringLayout.Serializer, LocationAware {
      private final PatternSelector patternSelector;
      private final RegexReplacement replace;

      private PatternSelectorSerializer(final PatternSelector patternSelector, final RegexReplacement replace) {
         this.patternSelector = patternSelector;
         this.replace = replace;
      }

      public String toSerializable(final LogEvent event) {
         StringBuilder sb = AbstractStringLayout.getStringBuilder();

         String var3;
         try {
            var3 = this.toSerializable(event, sb).toString();
         } finally {
            AbstractStringLayout.trimToMaxSize(sb);
         }

         return var3;
      }

      public StringBuilder toSerializable(final LogEvent event, final StringBuilder buffer) {
         for(PatternFormatter formatter : this.patternSelector.getFormatters(event)) {
            formatter.format(event, buffer);
         }

         if (this.replace != null) {
            String str = buffer.toString();
            str = this.replace.format(str);
            buffer.setLength(0);
            buffer.append(str);
         }

         return buffer;
      }

      public boolean requiresLocation() {
         return this.patternSelector instanceof LocationAware && ((LocationAware)this.patternSelector).requiresLocation();
      }

      public String toString() {
         StringBuilder builder = new StringBuilder();
         builder.append(super.toString());
         builder.append("[patternSelector=");
         builder.append(this.patternSelector);
         builder.append(", replace=");
         builder.append(this.replace);
         builder.append("]");
         return builder.toString();
      }
   }

   public static class Builder implements org.apache.logging.log4j.core.util.Builder {
      @PluginBuilderAttribute
      private String pattern;
      @PluginElement("PatternSelector")
      private PatternSelector patternSelector;
      @PluginConfiguration
      private Configuration configuration;
      @PluginElement("Replace")
      private RegexReplacement regexReplacement;
      @PluginBuilderAttribute
      private Charset charset;
      @PluginBuilderAttribute
      private boolean alwaysWriteExceptions;
      @PluginBuilderAttribute
      private boolean disableAnsi;
      @PluginBuilderAttribute
      private boolean noConsoleNoAnsi;
      @PluginBuilderAttribute
      private String header;
      @PluginBuilderAttribute
      private String footer;

      private Builder() {
         this.pattern = "%m%n";
         this.charset = Charset.defaultCharset();
         this.alwaysWriteExceptions = true;
         this.disableAnsi = !this.useAnsiEscapeCodes();
      }

      private boolean useAnsiEscapeCodes() {
         PropertiesUtil propertiesUtil = PropertiesUtil.getProperties();
         boolean isPlatformSupportsAnsi = !propertiesUtil.isOsWindows();
         boolean isJansiRequested = !propertiesUtil.getBooleanProperty("log4j.skipJansi", true);
         return isPlatformSupportsAnsi || isJansiRequested;
      }

      public Builder withPattern(final String pattern) {
         this.pattern = pattern;
         return this;
      }

      public Builder withPatternSelector(final PatternSelector patternSelector) {
         this.patternSelector = patternSelector;
         return this;
      }

      public Builder withConfiguration(final Configuration configuration) {
         this.configuration = configuration;
         return this;
      }

      public Builder withRegexReplacement(final RegexReplacement regexReplacement) {
         this.regexReplacement = regexReplacement;
         return this;
      }

      public Builder withCharset(final Charset charset) {
         if (charset != null) {
            this.charset = charset;
         }

         return this;
      }

      public Builder withAlwaysWriteExceptions(final boolean alwaysWriteExceptions) {
         this.alwaysWriteExceptions = alwaysWriteExceptions;
         return this;
      }

      public Builder withDisableAnsi(final boolean disableAnsi) {
         this.disableAnsi = disableAnsi;
         return this;
      }

      public Builder withNoConsoleNoAnsi(final boolean noConsoleNoAnsi) {
         this.noConsoleNoAnsi = noConsoleNoAnsi;
         return this;
      }

      public Builder withHeader(final String header) {
         this.header = header;
         return this;
      }

      public Builder withFooter(final String footer) {
         this.footer = footer;
         return this;
      }

      public PatternLayout build() {
         return new PatternLayout(this.configuration, this.regexReplacement, this.pattern, this.patternSelector, this.charset, this.alwaysWriteExceptions, this.disableAnsi, this.noConsoleNoAnsi, this.header, this.footer);
      }
   }

   private interface PatternSerializer extends AbstractStringLayout.Serializer, LocationAware {
   }
}
