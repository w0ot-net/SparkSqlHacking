package org.apache.ivy.core.event;

import org.apache.ivy.util.filter.Filter;

public class FilteredIvyListener implements IvyListener {
   private IvyListener listener;
   private Filter filter;

   public FilteredIvyListener(IvyListener listener, Filter filter) {
      this.listener = listener;
      this.filter = filter;
   }

   public IvyListener getIvyListener() {
      return this.listener;
   }

   public Filter getFilter() {
      return this.filter;
   }

   public void progress(IvyEvent event) {
      if (this.filter.accept(event)) {
         this.listener.progress(event);
      }

   }
}
