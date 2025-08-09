package org.apache.logging.log4j.core.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.AbstractLifeCycle;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LifeCycle2;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.util.ObjectArrayIterator;
import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.util.PerformanceSensitive;

@Plugin(
   name = "Filters",
   category = "Core",
   printObject = true
)
@PerformanceSensitive({"allocation"})
public final class CompositeFilter extends AbstractLifeCycle implements Iterable, Filter {
   private final Filter[] filters;

   private CompositeFilter() {
      this.filters = Filter.EMPTY_ARRAY;
   }

   private CompositeFilter(final Filter[] filters) {
      this.filters = filters == null ? Filter.EMPTY_ARRAY : filters;
   }

   public CompositeFilter addFilter(final Filter filter) {
      if (filter == null) {
         return this;
      } else if (filter instanceof CompositeFilter) {
         CompositeFilter compositeFilter = (CompositeFilter)filter;
         Filter[] copy = (Filter[])Arrays.copyOf(this.filters, this.filters.length + compositeFilter.size());
         System.arraycopy(compositeFilter.filters, 0, copy, this.filters.length, compositeFilter.filters.length);
         return new CompositeFilter(copy);
      } else {
         Filter[] copy = (Filter[])Arrays.copyOf(this.filters, this.filters.length + 1);
         copy[this.filters.length] = filter;
         return new CompositeFilter(copy);
      }
   }

   public CompositeFilter removeFilter(final Filter filter) {
      if (filter == null) {
         return this;
      } else {
         List<Filter> filterList = new ArrayList(Arrays.asList(this.filters));
         if (filter instanceof CompositeFilter) {
            for(Filter currentFilter : ((CompositeFilter)filter).filters) {
               filterList.remove(currentFilter);
            }
         } else {
            filterList.remove(filter);
         }

         return new CompositeFilter((Filter[])filterList.toArray(Filter.EMPTY_ARRAY));
      }
   }

   public Iterator iterator() {
      return new ObjectArrayIterator(this.filters);
   }

   /** @deprecated */
   @Deprecated
   public List getFilters() {
      return Arrays.asList(this.filters);
   }

   public Filter[] getFiltersArray() {
      return this.filters;
   }

   public boolean isEmpty() {
      return this.filters.length == 0;
   }

   public int size() {
      return this.filters.length;
   }

   public void start() {
      this.setStarting();

      for(Filter filter : this.filters) {
         filter.start();
      }

      this.setStarted();
   }

   public boolean stop(final long timeout, final TimeUnit timeUnit) {
      this.setStopping();

      for(Filter filter : this.filters) {
         if (filter instanceof LifeCycle2) {
            ((LifeCycle2)filter).stop(timeout, timeUnit);
         } else {
            filter.stop();
         }
      }

      this.setStopped();
      return true;
   }

   public Filter.Result getOnMismatch() {
      return Filter.Result.NEUTRAL;
   }

   public Filter.Result getOnMatch() {
      return Filter.Result.NEUTRAL;
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object... params) {
      Filter.Result result = Filter.Result.NEUTRAL;

      for(int i = 0; i < this.filters.length; ++i) {
         result = this.filters[i].filter(logger, level, marker, msg, params);
         if (result == Filter.Result.ACCEPT || result == Filter.Result.DENY) {
            return result;
         }
      }

      return result;
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0) {
      Filter.Result result = Filter.Result.NEUTRAL;

      for(int i = 0; i < this.filters.length; ++i) {
         result = this.filters[i].filter(logger, level, marker, msg, p0);
         if (result == Filter.Result.ACCEPT || result == Filter.Result.DENY) {
            return result;
         }
      }

      return result;
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1) {
      Filter.Result result = Filter.Result.NEUTRAL;

      for(int i = 0; i < this.filters.length; ++i) {
         result = this.filters[i].filter(logger, level, marker, msg, p0, p1);
         if (result == Filter.Result.ACCEPT || result == Filter.Result.DENY) {
            return result;
         }
      }

      return result;
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2) {
      Filter.Result result = Filter.Result.NEUTRAL;

      for(int i = 0; i < this.filters.length; ++i) {
         result = this.filters[i].filter(logger, level, marker, msg, p0, p1, p2);
         if (result == Filter.Result.ACCEPT || result == Filter.Result.DENY) {
            return result;
         }
      }

      return result;
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3) {
      Filter.Result result = Filter.Result.NEUTRAL;

      for(int i = 0; i < this.filters.length; ++i) {
         result = this.filters[i].filter(logger, level, marker, msg, p0, p1, p2, p3);
         if (result == Filter.Result.ACCEPT || result == Filter.Result.DENY) {
            return result;
         }
      }

      return result;
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4) {
      Filter.Result result = Filter.Result.NEUTRAL;

      for(int i = 0; i < this.filters.length; ++i) {
         result = this.filters[i].filter(logger, level, marker, msg, p0, p1, p2, p3, p4);
         if (result == Filter.Result.ACCEPT || result == Filter.Result.DENY) {
            return result;
         }
      }

      return result;
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5) {
      Filter.Result result = Filter.Result.NEUTRAL;

      for(int i = 0; i < this.filters.length; ++i) {
         result = this.filters[i].filter(logger, level, marker, msg, p0, p1, p2, p3, p4, p5);
         if (result == Filter.Result.ACCEPT || result == Filter.Result.DENY) {
            return result;
         }
      }

      return result;
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6) {
      Filter.Result result = Filter.Result.NEUTRAL;

      for(int i = 0; i < this.filters.length; ++i) {
         result = this.filters[i].filter(logger, level, marker, msg, p0, p1, p2, p3, p4, p5, p6);
         if (result == Filter.Result.ACCEPT || result == Filter.Result.DENY) {
            return result;
         }
      }

      return result;
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7) {
      Filter.Result result = Filter.Result.NEUTRAL;

      for(int i = 0; i < this.filters.length; ++i) {
         result = this.filters[i].filter(logger, level, marker, msg, p0, p1, p2, p3, p4, p5, p6, p7);
         if (result == Filter.Result.ACCEPT || result == Filter.Result.DENY) {
            return result;
         }
      }

      return result;
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8) {
      Filter.Result result = Filter.Result.NEUTRAL;

      for(int i = 0; i < this.filters.length; ++i) {
         result = this.filters[i].filter(logger, level, marker, msg, p0, p1, p2, p3, p4, p5, p6, p7, p8);
         if (result == Filter.Result.ACCEPT || result == Filter.Result.DENY) {
            return result;
         }
      }

      return result;
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final String msg, final Object p0, final Object p1, final Object p2, final Object p3, final Object p4, final Object p5, final Object p6, final Object p7, final Object p8, final Object p9) {
      Filter.Result result = Filter.Result.NEUTRAL;

      for(int i = 0; i < this.filters.length; ++i) {
         result = this.filters[i].filter(logger, level, marker, msg, p0, p1, p2, p3, p4, p5, p6, p7, p8, p9);
         if (result == Filter.Result.ACCEPT || result == Filter.Result.DENY) {
            return result;
         }
      }

      return result;
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final Object msg, final Throwable t) {
      Filter.Result result = Filter.Result.NEUTRAL;

      for(int i = 0; i < this.filters.length; ++i) {
         result = this.filters[i].filter(logger, level, marker, msg, t);
         if (result == Filter.Result.ACCEPT || result == Filter.Result.DENY) {
            return result;
         }
      }

      return result;
   }

   public Filter.Result filter(final Logger logger, final Level level, final Marker marker, final Message msg, final Throwable t) {
      Filter.Result result = Filter.Result.NEUTRAL;

      for(int i = 0; i < this.filters.length; ++i) {
         result = this.filters[i].filter(logger, level, marker, msg, t);
         if (result == Filter.Result.ACCEPT || result == Filter.Result.DENY) {
            return result;
         }
      }

      return result;
   }

   public Filter.Result filter(final LogEvent event) {
      Filter.Result result = Filter.Result.NEUTRAL;

      for(int i = 0; i < this.filters.length; ++i) {
         result = this.filters[i].filter(event);
         if (result == Filter.Result.ACCEPT || result == Filter.Result.DENY) {
            return result;
         }
      }

      return result;
   }

   public String toString() {
      StringBuilder sb = new StringBuilder();

      for(int i = 0; i < this.filters.length; ++i) {
         if (sb.length() == 0) {
            sb.append('{');
         } else {
            sb.append(", ");
         }

         sb.append(this.filters[i].toString());
      }

      if (sb.length() > 0) {
         sb.append('}');
      }

      return sb.toString();
   }

   @PluginFactory
   public static CompositeFilter createFilters(@PluginElement("Filters") final Filter[] filters) {
      return new CompositeFilter(filters);
   }
}
