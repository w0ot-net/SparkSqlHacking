package org.apache.logging.log4j.core.filter;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import org.apache.logging.log4j.core.AbstractLifeCycle;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LifeCycle2;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.PluginElement;

public abstract class AbstractFilterable extends AbstractLifeCycle implements Filterable {
   private volatile Filter filter;
   @PluginElement("Properties")
   private final Property[] propertyArray;

   protected AbstractFilterable() {
      this((Filter)null, Property.EMPTY_ARRAY);
   }

   protected AbstractFilterable(final Filter filter) {
      this(filter, Property.EMPTY_ARRAY);
   }

   protected AbstractFilterable(final Filter filter, final Property[] propertyArray) {
      this.filter = filter;
      this.propertyArray = propertyArray == null ? Property.EMPTY_ARRAY : propertyArray;
   }

   public synchronized void addFilter(final Filter filter) {
      if (filter != null) {
         if (this.filter == null) {
            this.filter = filter;
         } else if (this.filter instanceof CompositeFilter) {
            this.filter = ((CompositeFilter)this.filter).addFilter(filter);
         } else {
            Filter[] filters = new Filter[]{this.filter, filter};
            this.filter = CompositeFilter.createFilters(filters);
         }

      }
   }

   public Filter getFilter() {
      return this.filter;
   }

   public boolean hasFilter() {
      return this.filter != null;
   }

   public boolean isFiltered(final LogEvent event) {
      return this.filter != null && this.filter.filter(event) == Filter.Result.DENY;
   }

   public synchronized void removeFilter(final Filter filter) {
      if (this.filter != null && filter != null) {
         if (this.filter != filter && !this.filter.equals(filter)) {
            if (this.filter instanceof CompositeFilter) {
               CompositeFilter composite = (CompositeFilter)this.filter;
               composite = composite.removeFilter(filter);
               if (composite.size() > 1) {
                  this.filter = composite;
               } else if (composite.size() == 1) {
                  Iterator<Filter> iter = composite.iterator();
                  this.filter = (Filter)iter.next();
               } else {
                  this.filter = null;
               }
            }
         } else {
            this.filter = null;
         }

      }
   }

   public void start() {
      this.setStarting();
      if (this.filter != null) {
         this.filter.start();
      }

      this.setStarted();
   }

   public boolean stop(final long timeout, final TimeUnit timeUnit) {
      return this.stop(timeout, timeUnit, true);
   }

   protected boolean stop(final long timeout, final TimeUnit timeUnit, final boolean changeLifeCycleState) {
      if (changeLifeCycleState) {
         this.setStopping();
      }

      boolean stopped = true;
      if (this.filter != null) {
         if (this.filter instanceof LifeCycle2) {
            stopped = ((LifeCycle2)this.filter).stop(timeout, timeUnit);
         } else {
            this.filter.stop();
            stopped = true;
         }
      }

      if (changeLifeCycleState) {
         this.setStopped();
      }

      return stopped;
   }

   public Property[] getPropertyArray() {
      return this.propertyArray;
   }

   public abstract static class Builder {
      @PluginElement("Filter")
      private Filter filter;
      @PluginElement("Properties")
      private Property[] propertyArray;

      public Builder asBuilder() {
         return this;
      }

      public Filter getFilter() {
         return this.filter;
      }

      public Property[] getPropertyArray() {
         return this.propertyArray;
      }

      public Builder setFilter(final Filter filter) {
         this.filter = filter;
         return this.asBuilder();
      }

      public Builder setPropertyArray(final Property[] properties) {
         this.propertyArray = properties;
         return this.asBuilder();
      }

      /** @deprecated */
      @Deprecated
      public Builder withFilter(final Filter filter) {
         return this.setFilter(filter);
      }
   }
}
