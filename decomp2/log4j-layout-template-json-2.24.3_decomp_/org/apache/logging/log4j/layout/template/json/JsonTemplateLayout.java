package org.apache.logging.log4j.layout.template.json;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CodingErrorAction;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.StringLayout;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginConfiguration;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.impl.LocationAware;
import org.apache.logging.log4j.core.layout.ByteBufferDestination;
import org.apache.logging.log4j.core.layout.Encoder;
import org.apache.logging.log4j.core.layout.TextEncoderHelper;
import org.apache.logging.log4j.core.util.Constants;
import org.apache.logging.log4j.core.util.StringEncoder;
import org.apache.logging.log4j.layout.template.json.resolver.EventResolverContext;
import org.apache.logging.log4j.layout.template.json.resolver.EventResolverFactories;
import org.apache.logging.log4j.layout.template.json.resolver.EventResolverFactory;
import org.apache.logging.log4j.layout.template.json.resolver.EventResolverInterceptor;
import org.apache.logging.log4j.layout.template.json.resolver.EventResolverInterceptors;
import org.apache.logging.log4j.layout.template.json.resolver.EventResolverStringSubstitutor;
import org.apache.logging.log4j.layout.template.json.resolver.TemplateResolver;
import org.apache.logging.log4j.layout.template.json.resolver.TemplateResolvers;
import org.apache.logging.log4j.layout.template.json.util.JsonWriter;
import org.apache.logging.log4j.layout.template.json.util.Recycler;
import org.apache.logging.log4j.layout.template.json.util.RecyclerFactory;
import org.apache.logging.log4j.layout.template.json.util.Uris;
import org.apache.logging.log4j.status.StatusLogger;
import org.apache.logging.log4j.util.Strings;

@Plugin(
   name = "JsonTemplateLayout",
   category = "Core",
   elementType = "layout"
)
public class JsonTemplateLayout implements StringLayout, LocationAware {
   private static final Map CONTENT_FORMAT = Collections.singletonMap("version", "1");
   private final Charset charset;
   private final String contentType;
   private final boolean locationInfoEnabled;
   private final TemplateResolver eventResolver;
   private final String eventDelimiter;
   private final Recycler contextRecycler;

   private JsonTemplateLayout(final Builder builder) {
      this.charset = builder.charset;
      this.contentType = "application/json; charset=" + this.charset;
      this.locationInfoEnabled = builder.locationInfoEnabled;
      String eventDelimiterSuffix = builder.isNullEventDelimiterEnabled() ? "\u0000" : "";
      this.eventDelimiter = builder.eventDelimiter + eventDelimiterSuffix;
      Configuration configuration = builder.configuration;
      JsonWriter jsonWriter = JsonWriter.newBuilder().setMaxStringLength(builder.maxStringLength).setTruncatedStringSuffix(builder.truncatedStringSuffix).build();
      this.eventResolver = this.createEventResolver(builder, configuration, this.charset, jsonWriter);
      this.contextRecycler = createContextRecycler(builder, jsonWriter);
   }

   private TemplateResolver createEventResolver(final Builder builder, final Configuration configuration, final Charset charset, final JsonWriter jsonWriter) {
      List<String> pluginPackages = configuration.getPluginPackages();
      Map<String, EventResolverFactory> resolverFactoryByName = EventResolverFactories.populateResolverFactoryByName(pluginPackages);
      List<EventResolverInterceptor> resolverInterceptors = EventResolverInterceptors.populateInterceptors(pluginPackages);
      EventResolverStringSubstitutor substitutor = new EventResolverStringSubstitutor(configuration.getStrSubstitutor());
      String eventTemplate = readEventTemplate(builder);
      String stackTraceElementTemplate = readStackTraceElementTemplate(builder);
      float maxByteCountPerChar = builder.charset.newEncoder().maxBytesPerChar();
      int maxStringByteCount = Math.toIntExact(Math.round(Math.ceil((double)(maxByteCountPerChar * (float)builder.maxStringLength))));
      EventTemplateAdditionalField[] eventTemplateAdditionalFields = builder.eventTemplateAdditionalFields != null ? builder.eventTemplateAdditionalFields : JsonTemplateLayout.EventTemplateAdditionalField.EMPTY_ARRAY;
      EventResolverContext resolverContext = EventResolverContext.newBuilder().setConfiguration(configuration).setResolverFactoryByName(resolverFactoryByName).setResolverInterceptors(resolverInterceptors).setSubstitutor(substitutor).setCharset(charset).setJsonWriter(jsonWriter).setRecyclerFactory(builder.recyclerFactory).setMaxStringByteCount(maxStringByteCount).setTruncatedStringSuffix(builder.truncatedStringSuffix).setLocationInfoEnabled(builder.locationInfoEnabled).setStackTraceEnabled(builder.stackTraceEnabled).setStackTraceElementTemplate(stackTraceElementTemplate).setEventTemplateRootObjectKey(builder.eventTemplateRootObjectKey).setEventTemplateAdditionalFields(eventTemplateAdditionalFields).build();
      return TemplateResolvers.ofTemplate(resolverContext, eventTemplate);
   }

   private static String readEventTemplate(final Builder builder) {
      return readTemplate(builder.eventTemplate, builder.eventTemplateUri, builder.charset);
   }

   private static String readStackTraceElementTemplate(final Builder builder) {
      return readTemplate(builder.stackTraceElementTemplate, builder.stackTraceElementTemplateUri, builder.charset);
   }

   private static String readTemplate(final String template, final String templateUri, final Charset charset) {
      return Strings.isBlank(template) ? Uris.readUri(templateUri, charset) : template;
   }

   private static Recycler createContextRecycler(final Builder builder, final JsonWriter jsonWriter) {
      Supplier<Context> supplier = createContextSupplier(builder.charset, jsonWriter);
      return builder.recyclerFactory.create(supplier, Context::close);
   }

   private static Supplier createContextSupplier(final Charset charset, final JsonWriter jsonWriter) {
      return () -> {
         JsonWriter clonedJsonWriter = jsonWriter.clone();
         Encoder<StringBuilder> encoder = new StringBuilderEncoder(charset);
         return new Context(clonedJsonWriter, encoder);
      };
   }

   public byte[] toByteArray(final LogEvent event) {
      String eventJson = this.toSerializable(event);
      return StringEncoder.toBytes(eventJson, this.charset);
   }

   public String toSerializable(final LogEvent event) {
      Recycler<Context> contextRecycler = this.contextRecycler;
      Context context = (Context)contextRecycler.acquire();
      JsonWriter jsonWriter = context.jsonWriter;
      StringBuilder stringBuilder = jsonWriter.getStringBuilder();

      String var6;
      try {
         this.eventResolver.resolve(event, jsonWriter);
         stringBuilder.append(this.eventDelimiter);
         var6 = stringBuilder.toString();
      } finally {
         contextRecycler.release(context);
      }

      return var6;
   }

   public void encode(final LogEvent event, final ByteBufferDestination destination) {
      Recycler<Context> contextRecycler = this.contextRecycler;
      Context context = (Context)contextRecycler.acquire();
      JsonWriter jsonWriter = context.jsonWriter;
      StringBuilder stringBuilder = jsonWriter.getStringBuilder();
      Encoder<StringBuilder> encoder = context.encoder;

      try {
         this.eventResolver.resolve(event, jsonWriter);
         stringBuilder.append(this.eventDelimiter);
         encoder.encode(stringBuilder, destination);
      } finally {
         contextRecycler.release(context);
      }

   }

   public byte[] getFooter() {
      return null;
   }

   public byte[] getHeader() {
      return null;
   }

   public Charset getCharset() {
      return this.charset;
   }

   public boolean requiresLocation() {
      return this.locationInfoEnabled;
   }

   public String getContentType() {
      return this.contentType;
   }

   public Map getContentFormat() {
      return CONTENT_FORMAT;
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return new Builder();
   }

   private static final class Context implements AutoCloseable {
      final JsonWriter jsonWriter;
      final Encoder encoder;

      private Context(final JsonWriter jsonWriter, final Encoder encoder) {
         this.jsonWriter = jsonWriter;
         this.encoder = encoder;
      }

      public void close() {
         this.jsonWriter.close();
      }
   }

   private static final class StringBuilderEncoder implements Encoder {
      private final Charset charset;
      private final CharsetEncoder charsetEncoder;
      private final CharBuffer charBuffer;
      private final ByteBuffer byteBuffer;

      private StringBuilderEncoder(final Charset charset) {
         this.charset = charset;
         this.charsetEncoder = charset.newEncoder().onMalformedInput(CodingErrorAction.REPLACE).onUnmappableCharacter(CodingErrorAction.REPLACE);
         this.charBuffer = CharBuffer.allocate(Constants.ENCODER_CHAR_BUFFER_SIZE);
         this.byteBuffer = ByteBuffer.allocate(Constants.ENCODER_BYTE_BUFFER_SIZE);
      }

      public void encode(final StringBuilder source, final ByteBufferDestination destination) {
         try {
            TextEncoderHelper.encodeText(this.charsetEncoder, this.charBuffer, this.byteBuffer, source, destination);
         } catch (Exception error) {
            fallbackEncode(this.charset, source, destination, error);
         }

      }

      private static void fallbackEncode(final Charset charset, final StringBuilder source, final ByteBufferDestination destination, final Exception error) {
         StatusLogger.getLogger().error("TextEncoderHelper.encodeText() failure", error);
         byte[] bytes = source.toString().getBytes(charset);
         destination.writeBytes(bytes, 0, bytes.length);
      }
   }

   public static final class Builder implements org.apache.logging.log4j.core.util.Builder {
      @PluginConfiguration
      private Configuration configuration;
      @PluginBuilderAttribute
      private Charset charset;
      @PluginBuilderAttribute
      private boolean locationInfoEnabled;
      @PluginBuilderAttribute
      private boolean stackTraceEnabled;
      @PluginBuilderAttribute
      private String eventTemplate;
      @PluginBuilderAttribute
      private String eventTemplateUri;
      @PluginBuilderAttribute
      private String eventTemplateRootObjectKey;
      @PluginElement("EventTemplateAdditionalField")
      private EventTemplateAdditionalField[] eventTemplateAdditionalFields;
      @PluginBuilderAttribute
      private String stackTraceElementTemplate;
      @PluginBuilderAttribute
      private String stackTraceElementTemplateUri;
      @PluginBuilderAttribute
      private String eventDelimiter;
      @PluginBuilderAttribute
      private boolean nullEventDelimiterEnabled;
      @PluginBuilderAttribute
      private int maxStringLength;
      @PluginBuilderAttribute
      private String truncatedStringSuffix;
      @PluginBuilderAttribute
      private RecyclerFactory recyclerFactory;

      private Builder() {
         this.charset = JsonTemplateLayoutDefaults.getCharset();
         this.locationInfoEnabled = JsonTemplateLayoutDefaults.isLocationInfoEnabled();
         this.stackTraceEnabled = JsonTemplateLayoutDefaults.isStackTraceEnabled();
         this.eventTemplate = JsonTemplateLayoutDefaults.getEventTemplate();
         this.eventTemplateUri = JsonTemplateLayoutDefaults.getEventTemplateUri();
         this.eventTemplateRootObjectKey = JsonTemplateLayoutDefaults.getEventTemplateRootObjectKey();
         this.stackTraceElementTemplate = JsonTemplateLayoutDefaults.getStackTraceElementTemplate();
         this.stackTraceElementTemplateUri = JsonTemplateLayoutDefaults.getStackTraceElementTemplateUri();
         this.eventDelimiter = JsonTemplateLayoutDefaults.getEventDelimiter();
         this.nullEventDelimiterEnabled = JsonTemplateLayoutDefaults.isNullEventDelimiterEnabled();
         this.maxStringLength = JsonTemplateLayoutDefaults.getMaxStringLength();
         this.truncatedStringSuffix = JsonTemplateLayoutDefaults.getTruncatedStringSuffix();
         this.recyclerFactory = JsonTemplateLayoutDefaults.getRecyclerFactory();
      }

      public Configuration getConfiguration() {
         return this.configuration;
      }

      public Builder setConfiguration(final Configuration configuration) {
         this.configuration = configuration;
         return this;
      }

      public Charset getCharset() {
         return this.charset;
      }

      public Builder setCharset(final Charset charset) {
         this.charset = charset;
         return this;
      }

      public boolean isLocationInfoEnabled() {
         return this.locationInfoEnabled;
      }

      public Builder setLocationInfoEnabled(final boolean locationInfoEnabled) {
         this.locationInfoEnabled = locationInfoEnabled;
         return this;
      }

      public boolean isStackTraceEnabled() {
         return this.stackTraceEnabled;
      }

      public Builder setStackTraceEnabled(final boolean stackTraceEnabled) {
         this.stackTraceEnabled = stackTraceEnabled;
         return this;
      }

      public String getEventTemplate() {
         return this.eventTemplate;
      }

      public Builder setEventTemplate(final String eventTemplate) {
         this.eventTemplate = eventTemplate;
         return this;
      }

      public String getEventTemplateUri() {
         return this.eventTemplateUri;
      }

      public Builder setEventTemplateUri(final String eventTemplateUri) {
         this.eventTemplateUri = eventTemplateUri;
         return this;
      }

      public String getEventTemplateRootObjectKey() {
         return this.eventTemplateRootObjectKey;
      }

      public Builder setEventTemplateRootObjectKey(final String eventTemplateRootObjectKey) {
         this.eventTemplateRootObjectKey = eventTemplateRootObjectKey;
         return this;
      }

      public EventTemplateAdditionalField[] getEventTemplateAdditionalFields() {
         return this.eventTemplateAdditionalFields;
      }

      public Builder setEventTemplateAdditionalFields(final EventTemplateAdditionalField[] eventTemplateAdditionalFields) {
         this.eventTemplateAdditionalFields = eventTemplateAdditionalFields;
         return this;
      }

      public String getStackTraceElementTemplate() {
         return this.stackTraceElementTemplate;
      }

      public Builder setStackTraceElementTemplate(final String stackTraceElementTemplate) {
         this.stackTraceElementTemplate = stackTraceElementTemplate;
         return this;
      }

      public String getStackTraceElementTemplateUri() {
         return this.stackTraceElementTemplateUri;
      }

      public Builder setStackTraceElementTemplateUri(final String stackTraceElementTemplateUri) {
         this.stackTraceElementTemplateUri = stackTraceElementTemplateUri;
         return this;
      }

      public String getEventDelimiter() {
         return this.eventDelimiter;
      }

      public Builder setEventDelimiter(final String eventDelimiter) {
         this.eventDelimiter = eventDelimiter;
         return this;
      }

      public boolean isNullEventDelimiterEnabled() {
         return this.nullEventDelimiterEnabled;
      }

      public Builder setNullEventDelimiterEnabled(final boolean nullEventDelimiterEnabled) {
         this.nullEventDelimiterEnabled = nullEventDelimiterEnabled;
         return this;
      }

      public int getMaxStringLength() {
         return this.maxStringLength;
      }

      public Builder setMaxStringLength(final int maxStringLength) {
         this.maxStringLength = maxStringLength;
         return this;
      }

      public String getTruncatedStringSuffix() {
         return this.truncatedStringSuffix;
      }

      public Builder setTruncatedStringSuffix(final String truncatedStringSuffix) {
         this.truncatedStringSuffix = truncatedStringSuffix;
         return this;
      }

      public RecyclerFactory getRecyclerFactory() {
         return this.recyclerFactory;
      }

      public Builder setRecyclerFactory(final RecyclerFactory recyclerFactory) {
         this.recyclerFactory = recyclerFactory;
         return this;
      }

      public JsonTemplateLayout build() {
         this.validate();
         return new JsonTemplateLayout(this);
      }

      private void validate() {
         Objects.requireNonNull(this.configuration, "configuration");
         if (Strings.isBlank(this.eventTemplate) && Strings.isBlank(this.eventTemplateUri)) {
            throw new IllegalArgumentException("both eventTemplate and eventTemplateUri are blank");
         } else if (this.stackTraceEnabled && Strings.isBlank(this.stackTraceElementTemplate) && Strings.isBlank(this.stackTraceElementTemplateUri)) {
            throw new IllegalArgumentException("both stackTraceElementTemplate and stackTraceElementTemplateUri are blank");
         } else if (this.maxStringLength <= 0) {
            throw new IllegalArgumentException("was expecting a non-zero positive maxStringLength: " + this.maxStringLength);
         } else {
            Objects.requireNonNull(this.truncatedStringSuffix, "truncatedStringSuffix");
            Objects.requireNonNull(this.recyclerFactory, "recyclerFactory");
         }
      }
   }

   @Plugin(
      name = "EventTemplateAdditionalField",
      category = "Core",
      printObject = true
   )
   public static final class EventTemplateAdditionalField {
      static final EventTemplateAdditionalField[] EMPTY_ARRAY = new EventTemplateAdditionalField[0];
      private final String key;
      private final String value;
      private final Format format;

      private EventTemplateAdditionalField(final Builder builder) {
         this.key = builder.key;
         this.value = builder.value;
         this.format = builder.format;
      }

      public String getKey() {
         return this.key;
      }

      public String getValue() {
         return this.value;
      }

      public Format getFormat() {
         return this.format;
      }

      public boolean equals(final Object object) {
         if (this == object) {
            return true;
         } else if (object != null && this.getClass() == object.getClass()) {
            EventTemplateAdditionalField that = (EventTemplateAdditionalField)object;
            return this.key.equals(that.key) && this.value.equals(that.value) && this.format == that.format;
         } else {
            return false;
         }
      }

      public int hashCode() {
         return Objects.hash(new Object[]{this.key, this.value, this.format});
      }

      public String toString() {
         String formattedValue = JsonTemplateLayout.EventTemplateAdditionalField.Format.STRING.equals(this.format) ? String.format("\"%s\"", this.value) : this.value;
         return String.format("%s=%s", this.key, formattedValue);
      }

      @PluginBuilderFactory
      public static Builder newBuilder() {
         return new Builder();
      }

      public static enum Format {
         STRING,
         JSON;

         // $FF: synthetic method
         private static Format[] $values() {
            return new Format[]{STRING, JSON};
         }
      }

      public static class Builder implements org.apache.logging.log4j.core.util.Builder {
         @PluginBuilderAttribute
         private String key;
         @PluginBuilderAttribute
         private String value;
         @PluginBuilderAttribute
         private Format format;

         public Builder() {
            this.format = JsonTemplateLayout.EventTemplateAdditionalField.Format.STRING;
         }

         public Builder setKey(final String key) {
            this.key = key;
            return this;
         }

         public Builder setValue(final String value) {
            this.value = value;
            return this;
         }

         public Builder setFormat(final Format format) {
            this.format = format;
            return this;
         }

         public EventTemplateAdditionalField build() {
            this.validate();
            return new EventTemplateAdditionalField(this);
         }

         private void validate() {
            if (Strings.isBlank(this.key)) {
               throw new IllegalArgumentException("blank key");
            } else if (Strings.isBlank(this.value)) {
               throw new IllegalArgumentException("blank value");
            } else {
               Objects.requireNonNull(this.format, "format");
            }
         }
      }
   }
}
