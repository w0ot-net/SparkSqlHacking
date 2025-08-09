package org.apache.logging.log4j.core.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.AbstractLifeCycle;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.plugins.PluginBuilderAttribute;
import org.apache.logging.log4j.message.Message;

public abstract class AbstractFilter extends AbstractLifeCycle implements Filter {
   protected final Filter.Result onMatch;
   protected final Filter.Result onMismatch;

   protected AbstractFilter() {
      this((Filter.Result)null, (Filter.Result)null);
   }

   protected AbstractFilter(final Filter.Result onMatch, final Filter.Result onMismatch) {
      this.onMatch = onMatch == null ? Filter.Result.NEUTRAL : onMatch;
      this.onMismatch = onMismatch == null ? Filter.Result.DENY : onMismatch;
   }

   protected boolean equalsImpl(final Object obj) {
      if (this == obj) {
         return true;
      } else if (!super.equalsImpl(obj)) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         AbstractFilter other = (AbstractFilter)obj;
         if (this.onMatch != other.onMatch) {
            return false;
         } else {
            return this.onMismatch == other.onMismatch;
         }
      }
   }

   public Filter.Result filter(final LogEvent event) {
      return Filter.Result.NEUTRAL;
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final Message msg, final Throwable t) {
      return Filter.Result.NEUTRAL;
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final Object msg, final Throwable t) {
      return Filter.Result.NEUTRAL;
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object... params) {
      return Filter.Result.NEUTRAL;
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0) {
      return this.filter(logger, level, marker, msg, p0);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1) {
      return this.filter(logger, level, marker, msg, p0, p1);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2) {
      return this.filter(logger, level, marker, msg, p0, p1, p2);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3) {
      return this.filter(logger, level, marker, msg, p0, p1, p2, p3);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      return this.filter(logger, level, marker, msg, p0, p1, p2, p3, p4);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      return this.filter(logger, level, marker, msg, p0, p1, p2, p3, p4, p5);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      return this.filter(logger, level, marker, msg, p0, p1, p2, p3, p4, p5, p6);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      return this.filter(logger, level, marker, msg, p0, p1, p2, p3, p4, p5, p6, p7);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      return this.filter(logger, level, marker, msg, p0, p1, p2, p3, p4, p5, p6, p7, p8);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      return this.filter(logger, level, marker, msg, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
   }

   public final Filter.Result getOnMatch() {
      return this.onMatch;
   }

   public final Filter.Result getOnMismatch() {
      return this.onMismatch;
   }

   protected int hashCodeImpl() {
      int prime = 31;
      int result = super.hashCodeImpl();
      result = 31 * result + (this.onMatch == null ? 0 : this.onMatch.hashCode());
      result = 31 * result + (this.onMismatch == null ? 0 : this.onMismatch.hashCode());
      return result;
   }

   public String toString() {
      return this.getClass().getSimpleName();
   }

   public abstract static class AbstractFilterBuilder {
      public static final String ATTR_ON_MISMATCH = "onMismatch";
      public static final String ATTR_ON_MATCH = "onMatch";
      @PluginBuilderAttribute("onMatch")
      private Filter.Result onMatch;
      @PluginBuilderAttribute("onMismatch")
      private Filter.Result onMismatch;

      public AbstractFilterBuilder() {
         this.onMatch = Filter.Result.NEUTRAL;
         this.onMismatch = Filter.Result.DENY;
      }

      public Filter.Result getOnMatch() {
         return this.onMatch;
      }

      public Filter.Result getOnMismatch() {
         return this.onMismatch;
      }

      public AbstractFilterBuilder setOnMatch(final Filter.Result onMatch) {
         this.onMatch = onMatch;
         return this.asBuilder();
      }

      public AbstractFilterBuilder setOnMismatch(final Filter.Result onMismatch) {
         this.onMismatch = onMismatch;
         return this.asBuilder();
      }

      public AbstractFilterBuilder asBuilder() {
         return this;
      }
   }
}
