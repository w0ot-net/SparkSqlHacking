package org.apache.logging.log4j.core.layout;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.util.KeyValuePair;

@Plugin(
   name = "JsonLayout",
   category = "Core",
   elementType = "layout",
   printObject = true
)
public final class JsonLayout extends AbstractJacksonLayout {
   private static final String DEFAULT_FOOTER = "]";
   private static final String DEFAULT_HEADER = "[";
   static final String CONTENT_TYPE = "application/json";

   /** @deprecated */
   @Deprecated
   protected JsonLayout(final Configuration config, final boolean locationInfo, final boolean properties, final boolean encodeThreadContextAsList, final boolean complete, final boolean compact, final boolean eventEol, final String endOfLine, final String headerPattern, final String footerPattern, final Charset charset, final boolean includeStacktrace) {
      super(config, (new JacksonFactory.JSON(encodeThreadContextAsList, includeStacktrace, false, false)).newWriter(locationInfo, properties, compact), charset, compact, complete, eventEol, endOfLine, PatternLayout.newSerializerBuilder().setConfiguration(config).setPattern(headerPattern).setDefaultPattern("[").build(), PatternLayout.newSerializerBuilder().setConfiguration(config).setPattern(footerPattern).setDefaultPattern("]").build(), false, (KeyValuePair[])null);
   }

   private JsonLayout(final Configuration config, final boolean locationInfo, final boolean properties, final boolean encodeThreadContextAsList, final boolean complete, final boolean compact, final boolean eventEol, final String endOfLine, final String headerPattern, final String footerPattern, final Charset charset, final boolean includeStacktrace, final boolean stacktraceAsString, final boolean includeNullDelimiter, final boolean includeTimeMillis, final KeyValuePair[] additionalFields, final boolean objectMessageAsJsonObject) {
      super(config, (new JacksonFactory.JSON(encodeThreadContextAsList, includeStacktrace, stacktraceAsString, objectMessageAsJsonObject)).newWriter(locationInfo, properties, compact, includeTimeMillis), charset, compact, complete, eventEol, endOfLine, PatternLayout.newSerializerBuilder().setConfiguration(config).setPattern(headerPattern).setDefaultPattern("[").build(), PatternLayout.newSerializerBuilder().setConfiguration(config).setPattern(footerPattern).setDefaultPattern("]").build(), includeNullDelimiter, additionalFields);
   }

   public byte[] getHeader() {
      if (!this.complete) {
         return null;
      } else {
         StringBuilder buf = new StringBuilder();
         String str = this.serializeToString(this.getHeaderSerializer());
         if (str != null) {
            buf.append(str);
         }

         buf.append(this.eol);
         return this.getBytes(buf.toString());
      }
   }

   public byte[] getFooter() {
      if (!this.complete) {
         return null;
      } else {
         StringBuilder buf = new StringBuilder();
         buf.append(this.eol);
         String str = this.serializeToString(this.getFooterSerializer());
         if (str != null) {
            buf.append(str);
         }

         buf.append(this.eol);
         return this.getBytes(buf.toString());
      }
   }

   public Map getContentFormat() {
      Map<String, String> result = new HashMap();
      result.put("version", "2.0");
      return result;
   }

   public String getContentType() {
      return "application/json; charset=" + this.getCharset();
   }

   /** @deprecated */
   @Deprecated
   public static JsonLayout createLayout(final Configuration config, final boolean locationInfo, final boolean properties, final boolean propertiesAsList, final boolean complete, final boolean compact, final boolean eventEol, final String headerPattern, final String footerPattern, final Charset charset, final boolean includeStacktrace) {
      boolean encodeThreadContextAsList = properties && propertiesAsList;
      return new JsonLayout(config, locationInfo, properties, encodeThreadContextAsList, complete, compact, eventEol, (String)null, headerPattern, footerPattern, charset, includeStacktrace, false, false, false, (KeyValuePair[])null, false);
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return (Builder)(new Builder()).asBuilder();
   }

   public static JsonLayout createDefaultLayout() {
      return new JsonLayout(new DefaultConfiguration(), false, false, false, false, false, false, (String)null, "[", "]", StandardCharsets.UTF_8, true, false, false, false, (KeyValuePair[])null, false);
   }

   public void toSerializable(final LogEvent event, final Writer writer) throws IOException {
      if (this.complete && this.eventCount > 0L) {
         writer.append(", ");
      }

      super.toSerializable(event, writer);
   }

   public static class Builder extends AbstractJacksonLayout.Builder implements org.apache.logging.log4j.core.util.Builder {
      @PluginBuilderAttribute
      private boolean propertiesAsList;
      @PluginBuilderAttribute
      private boolean objectMessageAsJsonObject;
      @PluginElement("AdditionalField")
      private KeyValuePair[] additionalFields;

      public Builder() {
         this.setCharset(StandardCharsets.UTF_8);
      }

      public JsonLayout build() {
         boolean encodeThreadContextAsList = this.isProperties() && this.propertiesAsList;
         String headerPattern = this.toStringOrNull(this.getHeader());
         String footerPattern = this.toStringOrNull(this.getFooter());
         return new JsonLayout(this.getConfiguration(), this.isLocationInfo(), this.isProperties(), encodeThreadContextAsList, this.isComplete(), this.isCompact(), this.getEventEol(), this.getEndOfLine(), headerPattern, footerPattern, this.getCharset(), this.isIncludeStacktrace(), this.isStacktraceAsString(), this.isIncludeNullDelimiter(), this.isIncludeTimeMillis(), this.getAdditionalFields(), this.getObjectMessageAsJsonObject());
      }

      public boolean isPropertiesAsList() {
         return this.propertiesAsList;
      }

      public Builder setPropertiesAsList(final boolean propertiesAsList) {
         this.propertiesAsList = propertiesAsList;
         return (Builder)this.asBuilder();
      }

      public boolean getObjectMessageAsJsonObject() {
         return this.objectMessageAsJsonObject;
      }

      public Builder setObjectMessageAsJsonObject(final boolean objectMessageAsJsonObject) {
         this.objectMessageAsJsonObject = objectMessageAsJsonObject;
         return (Builder)this.asBuilder();
      }

      public KeyValuePair[] getAdditionalFields() {
         return this.additionalFields;
      }

      public Builder setAdditionalFields(final KeyValuePair[] additionalFields) {
         this.additionalFields = additionalFields;
         return (Builder)this.asBuilder();
      }
   }
}
