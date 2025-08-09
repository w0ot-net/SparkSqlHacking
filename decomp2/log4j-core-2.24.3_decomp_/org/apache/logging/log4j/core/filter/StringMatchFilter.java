package org.apache.logging.log4j.core.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderFactory;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.PerformanceSensitive;

@Plugin(
   name = "StringMatchFilter",
   category = "Core",
   elementType = "filter",
   printObject = true
)
@PerformanceSensitive({"allocation"})
public final class StringMatchFilter extends AbstractFilter {
   public static final String ATTR_MATCH = "match";
   private final String text;

   private StringMatchFilter(final String text, final Filter.Result onMatch, final Filter.Result onMismatch) {
      super(onMatch, onMismatch);
      this.text = text;
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object... params) {
      return this.filter(logger.getMessageFactory().newMessage(msg, params).getFormattedMessage());
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final Object msg, final Throwable t) {
      return this.filter(logger.getMessageFactory().newMessage(msg).getFormattedMessage());
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final Message msg, final Throwable t) {
      return this.filter(msg.getFormattedMessage());
   }

   public Filter.Result filter(final LogEvent event) {
      return this.filter(event.getMessage().getFormattedMessage());
   }

   private Filter.Result filter(final String msg) {
      return msg.contains(this.text) ? this.onMatch : this.onMismatch;
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0) {
      return this.filter(logger.getMessageFactory().newMessage(msg, new Object[]{p0}).getFormattedMessage());
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1) {
      return this.filter(logger.getMessageFactory().newMessage(msg, new Object[]{p0, p1}).getFormattedMessage());
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2) {
      return this.filter(logger.getMessageFactory().newMessage(msg, new Object[]{p0, p1, p2}).getFormattedMessage());
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3) {
      return this.filter(logger.getMessageFactory().newMessage(msg, new Object[]{p0, p1, p2, p3}).getFormattedMessage());
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      return this.filter(logger.getMessageFactory().newMessage(msg, new Object[]{p0, p1, p2, p3, p4}).getFormattedMessage());
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      return this.filter(logger.getMessageFactory().newMessage(msg, new Object[]{p0, p1, p2, p3, p4, p5}).getFormattedMessage());
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      return this.filter(logger.getMessageFactory().newMessage(msg, new Object[]{p0, p1, p2, p3, p4, p5, p6}).getFormattedMessage());
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      return this.filter(logger.getMessageFactory().newMessage(msg, new Object[]{p0, p1, p2, p3, p4, p5, p6, p7}).getFormattedMessage());
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      return this.filter(logger.getMessageFactory().newMessage(msg, new Object[]{p0, p1, p2, p3, p4, p5, p6, p7, p8}).getFormattedMessage());
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      return this.filter(logger.getMessageFactory().newMessage(msg, new Object[]{p0, p1, p2, p3, p4, p5, p6, p7, p8, p9}).getFormattedMessage());
   }

   public String toString() {
      return this.text;
   }

   @PluginBuilderFactory
   public static Builder newBuilder() {
      return new Builder();
   }

   public static class Builder extends AbstractFilter.AbstractFilterBuilder implements org.apache.logging.log4j.core.util.Builder {
      @PluginBuilderAttribute
      private String text = "";

      public Builder setMatchString(final String text) {
         this.text = text;
         return this;
      }

      public StringMatchFilter build() {
         return new StringMatchFilter(this.text, this.getOnMatch(), this.getOnMismatch());
      }
   }
}
