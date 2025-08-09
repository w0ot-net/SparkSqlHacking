package org.apache.logging.log4j.core.layout;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.util.KeyValuePair;

@Plugin(
   name = "XmlLayout",
   category = "Core",
   elementType = "layout",
   printObject = true
)
public final class XmlLayout extends AbstractJacksonLayout {
   private static final String ROOT_TAG = "Events";

   /** @deprecated */
   @Deprecated
   protected XmlLayout(final boolean locationInfo, final boolean properties, final boolean complete, final boolean compact, final Charset charset, final boolean includeStacktrace) {
      this((Configuration)null, locationInfo, properties, complete, compact, (String)null, charset, includeStacktrace, false, false, false, (KeyValuePair[])null);
   }

   private XmlLayout(final Configuration config, final boolean locationInfo, final boolean properties, final boolean complete, final boolean compact, final String endOfLine, final Charset charset, final boolean includeStacktrace, final boolean stacktraceAsString, final boolean includeNullDelimiter, final boolean includeTimeMillis, final KeyValuePair[] additionalFields) {
      super(config, (new JacksonFactory.XML(includeStacktrace, stacktraceAsString)).newWriter(locationInfo, properties, compact, includeTimeMillis), charset, compact, complete, false, endOfLine, (AbstractStringLayout.Serializer)null, (AbstractStringLayout.Serializer)null, includeNullDelimiter, additionalFields);
   }

   public byte[] getHeader() {
      if (!this.complete) {
         return null;
      } else {
         StringBuilder buf = new StringBuilder();
         buf.append("<?xml version=\"1.0\" encoding=\"");
         buf.append(this.getCharset().name());
         buf.append("\"?>");
         buf.append(this.eol);
         buf.append('<');
         buf.append("Events");
         buf.append(" xmlns=\"http://logging.apache.org/log4j/2.0/events\">");
         buf.append(this.eol);
         return buf.toString().getBytes(this.getCharset());
      }
   }

   public byte[] getFooter() {
      return !this.complete ? null : this.getBytes("</Events>" + this.eol);
   }

   public Map getContentFormat() {
      Map<String, String> result = new HashMap();
      result.put("xsd", "log4j-events.xsd");
      result.put("version", "2.0");
      return result;
   }

   public String getContentType() {
      return "text/xml; charset=" + this.getCharset();
   }

   /** @deprecated */
   @Deprecated
   public static XmlLayout createLayout(final boolean locationInfo, final boolean properties, final boolean complete, final boolean compact, final Charset charset, final boolean includeStacktrace) {
      return new XmlLayout((Configuration)null, locationInfo, properties, complete, compact, (String)null, charset, includeStacktrace, false, false, false, (KeyValuePair[])null);
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return (Builder)(new Builder()).asBuilder();
   }

   public static XmlLayout createDefaultLayout() {
      return new XmlLayout((Configuration)null, false, false, false, false, (String)null, StandardCharsets.UTF_8, true, false, false, false, (KeyValuePair[])null);
   }

   public static class Builder extends AbstractJacksonLayout.Builder implements org.apache.logging.log4j.core.util.Builder {
      public Builder() {
         this.setCharset(StandardCharsets.UTF_8);
      }

      public XmlLayout build() {
         return new XmlLayout(this.getConfiguration(), this.isLocationInfo(), this.isProperties(), this.isComplete(), this.isCompact(), this.getEndOfLine(), this.getCharset(), this.isIncludeStacktrace(), this.isStacktraceAsString(), this.isIncludeNullDelimiter(), this.isIncludeTimeMillis(), this.getAdditionalFields());
      }
   }
}
