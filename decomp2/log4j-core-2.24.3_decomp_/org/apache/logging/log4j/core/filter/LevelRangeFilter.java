package org.apache.logging.log4j.core.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.PerformanceSensitive;

@Plugin(
   name = "LevelRangeFilter",
   category = "Core",
   elementType = "filter",
   printObject = true
)
@PerformanceSensitive({"allocation"})
public final class LevelRangeFilter extends AbstractFilter {
   public static final Level DEFAULT_MIN_LEVEL;
   public static final Level DEFAULT_MAX_LEVEL;
   public static final Filter.Result DEFAULT_ON_MATCH;
   public static final Filter.Result DEFAULT_ON_MISMATCH;
   private final Level maxLevel;
   private final Level minLevel;

   @PluginFactory
   public static LevelRangeFilter createFilter(@PluginAttribute("minLevel") final Level minLevel, @PluginAttribute("maxLevel") final Level maxLevel, @PluginAttribute("onMatch") final Filter.Result onMatch, @PluginAttribute("onMismatch") final Filter.Result onMismatch) {
      Level effectiveMinLevel = minLevel == null ? DEFAULT_MIN_LEVEL : minLevel;
      Level effectiveMaxLevel = maxLevel == null ? DEFAULT_MAX_LEVEL : maxLevel;
      Filter.Result effectiveOnMatch = onMatch == null ? DEFAULT_ON_MATCH : onMatch;
      Filter.Result effectiveOnMismatch = onMismatch == null ? DEFAULT_ON_MISMATCH : onMismatch;
      return new LevelRangeFilter(effectiveMinLevel, effectiveMaxLevel, effectiveOnMatch, effectiveOnMismatch);
   }

   private LevelRangeFilter(final Level minLevel, final Level maxLevel, final Filter.Result onMatch, final Filter.Result onMismatch) {
      super(onMatch, onMismatch);
      this.minLevel = minLevel;
      this.maxLevel = maxLevel;
   }

   private Filter.Result filter(final Level level) {
      return level.isInRange(this.minLevel, this.maxLevel) ? this.onMatch : this.onMismatch;
   }

   public Filter.Result filter(final LogEvent event) {
      return this.filter(event.getLevel());
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final Message msg, final Throwable t) {
      return this.filter(level);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final Object msg, final Throwable t) {
      return this.filter(level);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object... params) {
      return this.filter(level);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0) {
      return this.filter(level);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1) {
      return this.filter(level);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2) {
      return this.filter(level);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3) {
      return this.filter(level);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      return this.filter(level);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      return this.filter(level);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      return this.filter(level);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      return this.filter(level);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      return this.filter(level);
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      return this.filter(level);
   }

   public Level getMinLevel() {
      return this.minLevel;
   }

   public Level getMaxLevel() {
      return this.maxLevel;
   }

   public String toString() {
      return String.format("[%s,%s]", this.minLevel, this.maxLevel);
   }

   static {
      DEFAULT_MIN_LEVEL = Level.OFF;
      DEFAULT_MAX_LEVEL = Level.ALL;
      DEFAULT_ON_MATCH = Filter.Result.NEUTRAL;
      DEFAULT_ON_MISMATCH = Filter.Result.DENY;
   }
}
