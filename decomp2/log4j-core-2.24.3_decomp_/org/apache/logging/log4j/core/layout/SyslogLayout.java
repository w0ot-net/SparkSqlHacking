package org.apache.logging.log4j.core.layout;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.core.net.Facility;
import org.apache.logging.log4j.core.net.Priority;
import org.apache.logging.log4j.core.util.NetUtils;

@Plugin(
   name = "SyslogLayout",
   category = "Core",
   elementType = "layout",
   printObject = true
)
public final class SyslogLayout extends AbstractStringLayout {
   public static final Pattern NEWLINE_PATTERN = Pattern.compile("\\r?\\n");
   private final Facility facility;
   private final boolean includeNewLine;
   private final String escapeNewLine;
   private final SimpleDateFormat dateFormat;
   private final String localHostname;

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return (Builder)(new Builder()).asBuilder();
   }

   protected SyslogLayout(final Facility facility, final boolean includeNL, final String escapeNL, final Charset charset) {
      super(charset);
      this.dateFormat = new SimpleDateFormat("MMM dd HH:mm:ss", Locale.ENGLISH);
      this.localHostname = NetUtils.getLocalHostname();
      this.facility = facility;
      this.includeNewLine = includeNL;
      this.escapeNewLine = escapeNL == null ? null : Matcher.quoteReplacement(escapeNL);
   }

   public String toSerializable(final LogEvent event) {
      StringBuilder buf = getStringBuilder();
      buf.append('<');
      buf.append(Priority.getPriority(this.facility, event.getLevel()));
      buf.append('>');
      this.addDate(event.getTimeMillis(), buf);
      buf.append(' ');
      buf.append(this.localHostname);
      buf.append(' ');
      String message = event.getMessage().getFormattedMessage();
      if (null != this.escapeNewLine) {
         message = NEWLINE_PATTERN.matcher(message).replaceAll(this.escapeNewLine);
      }

      buf.append(message);
      if (this.includeNewLine) {
         buf.append('\n');
      }

      return buf.toString();
   }

   private synchronized void addDate(final long timestamp, final StringBuilder buf) {
      int index = buf.length() + 4;
      buf.append(this.dateFormat.format(new Date(timestamp)));
      if (buf.charAt(index) == '0') {
         buf.setCharAt(index, ' ');
      }

   }

   public Map getContentFormat() {
      Map<String, String> result = new HashMap();
      result.put("structured", "false");
      result.put("formatType", "logfilepatternreceiver");
      result.put("dateFormat", this.dateFormat.toPattern());
      result.put("format", "<LEVEL>TIMESTAMP PROP(HOSTNAME) MESSAGE");
      return result;
   }

   /** @deprecated */
   @Deprecated
   public static SyslogLayout createLayout(final Facility facility, final boolean includeNewLine, final String escapeNL, final Charset charset) {
      return new SyslogLayout(facility, includeNewLine, escapeNL, charset);
   }

   public Facility getFacility() {
      return this.facility;
   }

   public static class Builder extends AbstractStringLayout.Builder implements org.apache.logging.log4j.core.util.Builder {
      @PluginBuilderAttribute
      private Facility facility;
      @PluginBuilderAttribute("newLine")
      private boolean includeNewLine;
      @PluginBuilderAttribute("newLineEscape")
      private String escapeNL;

      public Builder() {
         this.facility = Facility.LOCAL0;
         this.setCharset(StandardCharsets.UTF_8);
      }

      public SyslogLayout build() {
         return new SyslogLayout(this.facility, this.includeNewLine, this.escapeNL, this.getCharset());
      }

      public Facility getFacility() {
         return this.facility;
      }

      public boolean isIncludeNewLine() {
         return this.includeNewLine;
      }

      public String getEscapeNL() {
         return this.escapeNL;
      }

      public Builder setFacility(final Facility facility) {
         this.facility = facility;
         return (Builder)this.asBuilder();
      }

      public Builder setIncludeNewLine(final boolean includeNewLine) {
         this.includeNewLine = includeNewLine;
         return (Builder)this.asBuilder();
      }

      public Builder setEscapeNL(final String escapeNL) {
         this.escapeNL = escapeNL;
         return (Builder)this.asBuilder();
      }
   }
}
