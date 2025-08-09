package org.apache.logging.log4j.core.filter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.ContextDataInjector;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.impl.ContextDataFactory;
import org.apache.logging.log4j.core.impl.ContextDataInjectorFactory;
import org.apache.logging.log4j.core.util.KeyValuePair;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.PerformanceSensitive;
import org.apache.logging.log4j.util.StringMap;

@Plugin(
   name = "DynamicThresholdFilter",
   category = "Core",
   elementType = "filter",
   printObject = true
)
@PerformanceSensitive({"allocation"})
public final class DynamicThresholdFilter extends AbstractFilter {
   private Level defaultThreshold;
   private final String key;
   private final ContextDataInjector injector;
   private Map levelMap;

   @PluginFactory
   public static DynamicThresholdFilter createFilter(@PluginAttribute("key") final String key, @PluginElement("Pairs") final KeyValuePair[] pairs, @PluginAttribute("defaultThreshold") final Level defaultThreshold, @PluginAttribute("onMatch") final Filter.Result onMatch, @PluginAttribute("onMismatch") final Filter.Result onMismatch) {
      Map<String, Level> map = new HashMap();

      for(KeyValuePair pair : pairs) {
         map.put(pair.getKey(), Level.toLevel(pair.getValue()));
      }

      Level level = defaultThreshold == null ? Level.ERROR : defaultThreshold;
      return new DynamicThresholdFilter(key, map, level, onMatch, onMismatch);
   }

   private DynamicThresholdFilter(final String key, final Map pairs, final Level defaultLevel, final Filter.Result onMatch, final Filter.Result onMismatch) {
      super(onMatch, onMismatch);
      this.defaultThreshold = Level.ERROR;
      this.injector = ContextDataInjectorFactory.createInjector();
      this.levelMap = new HashMap();
      StringMap map = ContextDataFactory.createContextData();
      LOGGER.debug("Successfully initialized ContextDataFactory by retrieving the context data with {} entries", map.size());
      Objects.requireNonNull(key, "key cannot be null");
      this.key = key;
      this.levelMap = pairs;
      this.defaultThreshold = defaultLevel;
   }

   public boolean equals(final Object obj) {
      if (this == obj) {
         return true;
      } else if (!super.equalsImpl(obj)) {
         return false;
      } else if (this.getClass() != obj.getClass()) {
         return false;
      } else {
         DynamicThresholdFilter other = (DynamicThresholdFilter)obj;
         if (!Objects.equals(this.defaultThreshold, other.defaultThreshold)) {
            return false;
         } else if (!Objects.equals(this.key, other.key)) {
            return false;
         } else {
            return Objects.equals(this.levelMap, other.levelMap);
         }
      }
   }

   private Filter.Result filter(final Level level, final Object value) {
      if (value != null) {
         Level ctxLevel = (Level)this.levelMap.get(Objects.toString(value, (String)null));
         if (ctxLevel == null) {
            ctxLevel = this.defaultThreshold;
         }

         return level.isMoreSpecificThan(ctxLevel) ? this.onMatch : this.onMismatch;
      } else {
         return Filter.Result.NEUTRAL;
      }
   }

   public Filter.Result filter(final LogEvent event) {
      return this.filter(event.getLevel(), event.getContextData().getValue(this.key));
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final Message msg, final Throwable t) {
      return this.filter(level, this.injector.getValue(this.key));
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final Object msg, final Throwable t) {
      return this.filter(level, this.injector.getValue(this.key));
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object... params) {
      return this.filter(level, this.injector.getValue(this.key));
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0) {
      return this.filter(level, this.injector.getValue(this.key));
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1) {
      return this.filter(level, this.injector.getValue(this.key));
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2) {
      return this.filter(level, this.injector.getValue(this.key));
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3) {
      return this.filter(level, this.injector.getValue(this.key));
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      return this.filter(level, this.injector.getValue(this.key));
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      return this.filter(level, this.injector.getValue(this.key));
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      return this.filter(level, this.injector.getValue(this.key));
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      return this.filter(level, this.injector.getValue(this.key));
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      return this.filter(level, this.injector.getValue(this.key));
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      return this.filter(level, this.injector.getValue(this.key));
   }

   public String getKey() {
      return this.key;
   }

   public Map getLevelMap() {
      return this.levelMap;
   }

   public int hashCode() {
      int prime = 31;
      int result = super.hashCodeImpl();
      result = 31 * result + (this.defaultThreshold == null ? 0 : this.defaultThreshold.hashCode());
      result = 31 * result + (this.key == null ? 0 : this.key.hashCode());
      result = 31 * result + (this.levelMap == null ? 0 : this.levelMap.hashCode());
      return result;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();
      sb.append("key=").append(this.key);
      sb.append(", default=").append(this.defaultThreshold);
      if (this.levelMap.size() > 0) {
         sb.append('{');
         boolean first = true;

         for(Map.Entry entry : this.levelMap.entrySet()) {
            if (!first) {
               sb.append(", ");
               first = false;
            }

            sb.append((String)entry.getKey()).append('=').append(entry.getValue());
         }

         sb.append('}');
      }

      return sb.toString();
   }
}
