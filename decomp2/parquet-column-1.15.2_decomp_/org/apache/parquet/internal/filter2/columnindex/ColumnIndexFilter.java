package org.apache.parquet.internal.filter2.columnindex;

import java.util.PrimitiveIterator;
import java.util.Set;
import java.util.function.Function;
import org.apache.parquet.filter2.compat.FilterCompat;
import org.apache.parquet.filter2.predicate.FilterPredicate;
import org.apache.parquet.filter2.predicate.Operators;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.internal.column.columnindex.ColumnIndex;
import org.apache.parquet.internal.column.columnindex.OffsetIndex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColumnIndexFilter implements FilterPredicate.Visitor {
   private static final Logger LOGGER = LoggerFactory.getLogger(ColumnIndexFilter.class);
   private final ColumnIndexStore columnIndexStore;
   private final Set columns;
   private final long rowCount;
   private RowRanges allRows;

   public static RowRanges calculateRowRanges(FilterCompat.Filter filter, final ColumnIndexStore columnIndexStore, final Set paths, final long rowCount) {
      return (RowRanges)filter.accept(new FilterCompat.Visitor() {
         public RowRanges visit(FilterCompat.FilterPredicateCompat filterPredicateCompat) {
            try {
               return (RowRanges)filterPredicateCompat.getFilterPredicate().accept(new ColumnIndexFilter(columnIndexStore, paths, rowCount));
            } catch (ColumnIndexStore.MissingOffsetIndexException e) {
               ColumnIndexFilter.LOGGER.info(e.getMessage());
               return RowRanges.createSingle(rowCount);
            }
         }

         public RowRanges visit(FilterCompat.UnboundRecordFilterCompat unboundRecordFilterCompat) {
            return RowRanges.createSingle(rowCount);
         }

         public RowRanges visit(FilterCompat.NoOpFilter noOpFilter) {
            return RowRanges.createSingle(rowCount);
         }
      });
   }

   private ColumnIndexFilter(ColumnIndexStore columnIndexStore, Set paths, long rowCount) {
      this.columnIndexStore = columnIndexStore;
      this.columns = paths;
      this.rowCount = rowCount;
   }

   private RowRanges allRows() {
      if (this.allRows == null) {
         this.allRows = RowRanges.createSingle(this.rowCount);
      }

      return this.allRows;
   }

   public RowRanges visit(Operators.Eq eq) {
      return this.applyPredicate(eq.getColumn(), (ci) -> (PrimitiveIterator.OfInt)ci.visit(eq), eq.getValue() == null ? this.allRows() : RowRanges.EMPTY);
   }

   public RowRanges visit(Operators.NotEq notEq) {
      return this.applyPredicate(notEq.getColumn(), (ci) -> (PrimitiveIterator.OfInt)ci.visit(notEq), notEq.getValue() == null ? RowRanges.EMPTY : this.allRows());
   }

   public RowRanges visit(Operators.Lt lt) {
      return this.applyPredicate(lt.getColumn(), (ci) -> (PrimitiveIterator.OfInt)ci.visit(lt), RowRanges.EMPTY);
   }

   public RowRanges visit(Operators.LtEq ltEq) {
      return this.applyPredicate(ltEq.getColumn(), (ci) -> (PrimitiveIterator.OfInt)ci.visit(ltEq), RowRanges.EMPTY);
   }

   public RowRanges visit(Operators.Gt gt) {
      return this.applyPredicate(gt.getColumn(), (ci) -> (PrimitiveIterator.OfInt)ci.visit(gt), RowRanges.EMPTY);
   }

   public RowRanges visit(Operators.GtEq gtEq) {
      return this.applyPredicate(gtEq.getColumn(), (ci) -> (PrimitiveIterator.OfInt)ci.visit(gtEq), RowRanges.EMPTY);
   }

   public RowRanges visit(Operators.In in) {
      boolean isNull = in.getValues().contains((Object)null);
      return this.applyPredicate(in.getColumn(), (ci) -> (PrimitiveIterator.OfInt)ci.visit(in), isNull ? this.allRows() : RowRanges.EMPTY);
   }

   public RowRanges visit(Operators.NotIn notIn) {
      boolean isNull = notIn.getValues().contains((Object)null);
      return this.applyPredicate(notIn.getColumn(), (ci) -> (PrimitiveIterator.OfInt)ci.visit(notIn), isNull ? RowRanges.EMPTY : this.allRows());
   }

   public RowRanges visit(Operators.Contains contains) {
      return (RowRanges)contains.filter(this, RowRanges::intersection, RowRanges::union, (ranges) -> this.allRows());
   }

   public RowRanges visit(Operators.UserDefined udp) {
      return this.applyPredicate(udp.getColumn(), (ci) -> (PrimitiveIterator.OfInt)ci.visit(udp), udp.getUserDefinedPredicate().acceptsNullValue() ? this.allRows() : RowRanges.EMPTY);
   }

   public RowRanges visit(Operators.LogicalNotUserDefined udp) {
      return this.applyPredicate(udp.getUserDefined().getColumn(), (ci) -> (PrimitiveIterator.OfInt)ci.visit(udp), udp.getUserDefined().getUserDefinedPredicate().acceptsNullValue() ? RowRanges.EMPTY : this.allRows());
   }

   private RowRanges applyPredicate(Operators.Column column, Function func, RowRanges rangesForMissingColumns) {
      ColumnPath columnPath = column.getColumnPath();
      if (!this.columns.contains(columnPath)) {
         return rangesForMissingColumns;
      } else {
         OffsetIndex oi = this.columnIndexStore.getOffsetIndex(columnPath);
         ColumnIndex ci = this.columnIndexStore.getColumnIndex(columnPath);
         if (ci == null) {
            LOGGER.info("No column index for column {} is available; Unable to filter on this column", columnPath);
            return this.allRows();
         } else {
            return RowRanges.create(this.rowCount, (PrimitiveIterator.OfInt)func.apply(ci), oi);
         }
      }
   }

   public RowRanges visit(Operators.And and) {
      RowRanges leftResult = (RowRanges)and.getLeft().accept(this);
      return leftResult.getRanges().isEmpty() ? leftResult : RowRanges.intersection(leftResult, (RowRanges)and.getRight().accept(this));
   }

   public RowRanges visit(Operators.Or or) {
      RowRanges leftResult = (RowRanges)or.getLeft().accept(this);
      return leftResult.getRanges().size() == 1 && leftResult.rowCount() == this.rowCount ? leftResult : RowRanges.union(leftResult, (RowRanges)or.getRight().accept(this));
   }

   public RowRanges visit(Operators.Not not) {
      throw new IllegalArgumentException("Predicates containing a NOT must be run through LogicalInverseRewriter. " + not);
   }
}
