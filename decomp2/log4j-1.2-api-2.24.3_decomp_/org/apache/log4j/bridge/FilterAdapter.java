package org.apache.log4j.bridge;

import org.apache.log4j.spi.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.filter.AbstractFilter;
import org.apache.logging.log4j.core.filter.CompositeFilter;

public final class FilterAdapter extends AbstractFilter {
   private final Filter filter;

   public static org.apache.logging.log4j.core.Filter adapt(final Filter filter) {
      if (filter instanceof org.apache.logging.log4j.core.Filter) {
         return (org.apache.logging.log4j.core.Filter)filter;
      } else if (filter instanceof FilterWrapper && filter.getNext() == null) {
         return ((FilterWrapper)filter).getFilter();
      } else {
         return filter != null ? new FilterAdapter(filter) : null;
      }
   }

   public static Filter addFilter(final Filter first, final Filter second) {
      if (first == null) {
         return second;
      } else if (second == null) {
         return first;
      } else {
         CompositeFilter composite;
         if (first instanceof FilterWrapper && ((FilterWrapper)first).getFilter() instanceof CompositeFilter) {
            composite = (CompositeFilter)((FilterWrapper)first).getFilter();
         } else {
            composite = CompositeFilter.createFilters(new org.apache.logging.log4j.core.Filter[]{adapt(first)});
         }

         return FilterWrapper.adapt(composite.addFilter(adapt(second)));
      }
   }

   private FilterAdapter(final Filter filter) {
      this.filter = filter;
   }

   public org.apache.logging.log4j.core.Filter.Result filter(LogEvent param1) {
      // $FF: Couldn't be decompiled
   }

   public Filter getFilter() {
      return this.filter;
   }

   public void start() {
      this.filter.activateOptions();
   }
}
