package org.apache.orc.impl.filter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.apache.orc.OrcFilterContext;
import org.apache.orc.filter.BatchFilter;

class BatchFilterFactory {
   static BatchFilter create(List filters) {
      if (filters.isEmpty()) {
         return null;
      } else {
         return (BatchFilter)(filters.size() == 1 ? (BatchFilter)filters.get(0) : new AndBatchFilterImpl((BatchFilter[])filters.toArray(new BatchFilter[0])));
      }
   }

   static BatchFilter create(Consumer filter, String[] colNames) {
      return (BatchFilter)(filter instanceof BatchFilter ? (BatchFilter)filter : new WrappedFilterImpl(filter, colNames));
   }

   static BatchFilter create(VectorFilter filter, String[] colNames) {
      return new BatchFilterImpl(filter, colNames);
   }

   private static class BatchFilterImpl implements BatchFilter {
      final VectorFilter filter;
      private final String[] colNames;
      private final Selected bound = new Selected();
      private final Selected selOut = new Selected();

      private BatchFilterImpl(VectorFilter filter, String[] colNames) {
         this.filter = filter;
         this.colNames = colNames;
      }

      public void accept(OrcFilterContext fc) {
         this.bound.initialize(fc);
         this.selOut.sel = fc.getSelected();
         this.selOut.selSize = 0;
         this.filter.filter(fc, this.bound, this.selOut);
         if (this.selOut.selSize < fc.getSelectedSize()) {
            fc.setSelectedSize(this.selOut.selSize);
            fc.setSelectedInUse(true);
         } else if (this.selOut.selSize > fc.getSelectedSize()) {
            throw new RuntimeException(String.format("Unexpected state: Filtered size %s > input size %s", this.selOut.selSize, fc.getSelectedSize()));
         }

      }

      public String[] getColumnNames() {
         return this.colNames;
      }
   }

   static class AndBatchFilterImpl implements BatchFilter {
      private final BatchFilter[] filters;
      private final String[] colNames;

      AndBatchFilterImpl(BatchFilter... filters) {
         this.filters = filters;
         Set<String> names = new HashSet();

         for(BatchFilter filter : this.filters) {
            names.addAll(Arrays.asList(filter.getColumnNames()));
         }

         this.colNames = (String[])names.toArray(new String[0]);
      }

      public void accept(OrcFilterContext fc) {
         for(int i = 0; fc.getSelectedSize() > 0 && i < this.filters.length; ++i) {
            this.filters[i].accept(fc);
         }

      }

      public String[] getColumnNames() {
         return this.colNames;
      }
   }

   private static class WrappedFilterImpl implements BatchFilter {
      private final Consumer filter;
      private final String[] colNames;

      private WrappedFilterImpl(Consumer filter, String[] colNames) {
         this.filter = filter;
         this.colNames = colNames;
      }

      public String[] getColumnNames() {
         return this.colNames;
      }

      public void accept(OrcFilterContext filterContext) {
         this.filter.accept(filterContext);
      }
   }
}
