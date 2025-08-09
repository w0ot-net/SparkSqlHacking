package org.apache.parquet.filter2.compat;

import java.util.Objects;
import org.apache.parquet.Preconditions;
import org.apache.parquet.filter.UnboundRecordFilter;
import org.apache.parquet.filter2.predicate.ContainsRewriter;
import org.apache.parquet.filter2.predicate.FilterPredicate;
import org.apache.parquet.filter2.predicate.LogicalInverseRewriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilterCompat {
   private static final Logger LOG = LoggerFactory.getLogger(FilterCompat.class);
   public static final Filter NOOP = new NoOpFilter();

   public static Filter get(FilterPredicate filterPredicate) {
      Objects.requireNonNull(filterPredicate, "filterPredicate cannot be null");
      LOG.info("Filtering using predicate: {}", filterPredicate);
      FilterPredicate collapsedPredicate = LogicalInverseRewriter.rewrite(filterPredicate);
      if (!filterPredicate.equals(collapsedPredicate)) {
         LOG.info("Predicate has been collapsed to: {}", collapsedPredicate);
      }

      FilterPredicate rewrittenContainsPredicate = ContainsRewriter.rewrite(collapsedPredicate);
      if (!collapsedPredicate.equals(rewrittenContainsPredicate)) {
         LOG.info("Contains() Predicate has been rewritten to: {}", rewrittenContainsPredicate);
      }

      return new FilterPredicateCompat(rewrittenContainsPredicate);
   }

   public static Filter get(UnboundRecordFilter unboundRecordFilter) {
      return new UnboundRecordFilterCompat(unboundRecordFilter);
   }

   public static Filter get(FilterPredicate filterPredicate, UnboundRecordFilter unboundRecordFilter) {
      Preconditions.checkArgument(filterPredicate == null || unboundRecordFilter == null, "Cannot provide both a FilterPredicate and an UnboundRecordFilter");
      if (filterPredicate != null) {
         return get(filterPredicate);
      } else {
         return unboundRecordFilter != null ? get(unboundRecordFilter) : NOOP;
      }
   }

   public static boolean isFilteringRequired(Filter filter) {
      return filter != null && !(filter instanceof NoOpFilter);
   }

   public static final class FilterPredicateCompat implements Filter {
      private final FilterPredicate filterPredicate;

      private FilterPredicateCompat(FilterPredicate filterPredicate) {
         this.filterPredicate = (FilterPredicate)Objects.requireNonNull(filterPredicate, "filterPredicate cannot be null");
      }

      public FilterPredicate getFilterPredicate() {
         return this.filterPredicate;
      }

      public Object accept(Visitor visitor) {
         return visitor.visit(this);
      }
   }

   public static final class UnboundRecordFilterCompat implements Filter {
      private final UnboundRecordFilter unboundRecordFilter;

      private UnboundRecordFilterCompat(UnboundRecordFilter unboundRecordFilter) {
         this.unboundRecordFilter = (UnboundRecordFilter)Objects.requireNonNull(unboundRecordFilter, "unboundRecordFilter cannot be null");
      }

      public UnboundRecordFilter getUnboundRecordFilter() {
         return this.unboundRecordFilter;
      }

      public Object accept(Visitor visitor) {
         return visitor.visit(this);
      }
   }

   public static final class NoOpFilter implements Filter {
      private NoOpFilter() {
      }

      public Object accept(Visitor visitor) {
         return visitor.visit(this);
      }
   }

   public interface Filter {
      Object accept(Visitor var1);
   }

   public interface Visitor {
      Object visit(FilterPredicateCompat var1);

      Object visit(UnboundRecordFilterCompat var1);

      Object visit(NoOpFilter var1);
   }
}
