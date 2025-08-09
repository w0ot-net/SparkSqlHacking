package org.apache.logging.log4j.core.layout;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.util.KeyValuePair;

@Plugin(
   name = "YamlLayout",
   category = "Core",
   elementType = "layout",
   printObject = true
)
public final class YamlLayout extends AbstractJacksonLayout {
   private static final String DEFAULT_FOOTER = "";
   private static final String DEFAULT_HEADER = "";
   static final String CONTENT_TYPE = "application/yaml";

   /** @deprecated */
   @Deprecated
   protected YamlLayout(final Configuration config, final boolean locationInfo, final boolean properties, final boolean complete, final boolean compact, final boolean eventEol, final String headerPattern, final String footerPattern, final Charset charset, final boolean includeStacktrace) {
      super(config, (new JacksonFactory.YAML(includeStacktrace, false)).newWriter(locationInfo, properties, compact), charset, compact, complete, eventEol, (String)null, PatternLayout.newSerializerBuilder().setConfiguration(config).setPattern(headerPattern).setDefaultPattern("").build(), PatternLayout.newSerializerBuilder().setConfiguration(config).setPattern(footerPattern).setDefaultPattern("").build(), false, (KeyValuePair[])null);
   }

   private YamlLayout(final Configuration config, final boolean locationInfo, final boolean properties, final boolean complete, final boolean compact, final boolean eventEol, final String endOfLine, final String headerPattern, final String footerPattern, final Charset charset, final boolean includeStacktrace, final boolean stacktraceAsString, final boolean includeNullDelimiter, final boolean includeTimeMillis, final KeyValuePair[] additionalFields) {
      super(config, (new JacksonFactory.YAML(includeStacktrace, stacktraceAsString)).newWriter(locationInfo, properties, compact, includeTimeMillis), charset, compact, complete, eventEol, endOfLine, PatternLayout.newSerializerBuilder().setConfiguration(config).setPattern(headerPattern).setDefaultPattern("").build(), PatternLayout.newSerializerBuilder().setConfiguration(config).setPattern(footerPattern).setDefaultPattern("").build(), includeNullDelimiter, additionalFields);
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
      return "application/yaml; charset=" + this.getCharset();
   }

   /** @deprecated */
   @Deprecated
   public static AbstractJacksonLayout createLayout(final Configuration config, final boolean locationInfo, final boolean properties, final String headerPattern, final String footerPattern, final Charset charset, final boolean includeStacktrace) {
      return new YamlLayout(config, locationInfo, properties, false, false, true, (String)null, headerPattern, footerPattern, charset, includeStacktrace, false, false, false, (KeyValuePair[])null);
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return (Builder)(new Builder()).asBuilder();
   }

   public static AbstractJacksonLayout createDefaultLayout() {
      return new YamlLayout(new DefaultConfiguration(), false, false, false, false, false, (String)null, "", "", StandardCharsets.UTF_8, true, false, false, false, (KeyValuePair[])null);
   }

   public static class Builder extends AbstractJacksonLayout.Builder implements org.apache.logging.log4j.core.util.Builder {
      public Builder() {
         this.setCharset(StandardCharsets.UTF_8);
      }

      public YamlLayout build() {
         String headerPattern = this.toStringOrNull(this.getHeader());
         String footerPattern = this.toStringOrNull(this.getFooter());
         return new YamlLayout(this.getConfiguration(), this.isLocationInfo(), this.isProperties(), this.isComplete(), this.isCompact(), this.getEventEol(), this.getEndOfLine(), headerPattern, footerPattern, this.getCharset(), this.isIncludeStacktrace(), this.isStacktraceAsString(), this.isIncludeNullDelimiter(), this.isIncludeTimeMillis(), this.getAdditionalFields());
      }
   }
}
