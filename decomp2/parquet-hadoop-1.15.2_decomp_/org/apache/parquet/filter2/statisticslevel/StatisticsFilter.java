package org.apache.parquet.filter2.statisticslevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import org.apache.parquet.column.MinMax;
import org.apache.parquet.column.statistics.Statistics;
import org.apache.parquet.filter2.predicate.FilterPredicate;
import org.apache.parquet.filter2.predicate.Operators;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.apache.parquet.hadoop.metadata.ColumnPath;

public class StatisticsFilter implements FilterPredicate.Visitor {
   private static final boolean BLOCK_MIGHT_MATCH = false;
   private static final boolean BLOCK_CANNOT_MATCH = true;
   private final Map columns = new HashMap();

   public static boolean canDrop(FilterPredicate pred, List columns) {
      Objects.requireNonNull(pred, "pred cannot be null");
      Objects.requireNonNull(columns, "columns cannot be null");
      return (Boolean)pred.accept(new StatisticsFilter(columns));
   }

   private StatisticsFilter(List columnsList) {
      for(ColumnChunkMetaData chunk : columnsList) {
         this.columns.put(chunk.getPath(), chunk);
      }

   }

   private ColumnChunkMetaData getColumnChunk(ColumnPath columnPath) {
      return (ColumnChunkMetaData)this.columns.get(columnPath);
   }

   private boolean isAllNulls(ColumnChunkMetaData column) {
      return column.getStatistics().getNumNulls() == column.getValueCount();
   }

   private boolean hasNulls(ColumnChunkMetaData column) {
      return column.getStatistics().getNumNulls() > 0L;
   }

   public Boolean visit(Operators.Eq eq) {
      Operators.Column<T> filterColumn = eq.getColumn();
      ColumnChunkMetaData meta = this.getColumnChunk(filterColumn.getColumnPath());
      T value = (T)eq.getValue();
      if (meta == null) {
         return value != null ? true : false;
      } else {
         Statistics<T> stats = meta.getStatistics();
         if (stats.isEmpty()) {
            return false;
         } else if (value == null) {
            return !stats.isNumNullsSet() ? false : !this.hasNulls(meta);
         } else if (this.isAllNulls(meta)) {
            return true;
         } else {
            return !stats.hasNonNullValue() ? false : stats.compareMinToValue(value) > 0 || stats.compareMaxToValue(value) < 0;
         }
      }
   }

   public Boolean visit(Operators.In in) {
      Operators.Column<T> filterColumn = in.getColumn();
      ColumnChunkMetaData meta = this.getColumnChunk(filterColumn.getColumnPath());
      Set<T> values = in.getValues();
      if (meta == null) {
         return !values.contains((Object)null) ? true : false;
      } else {
         Statistics<T> stats = meta.getStatistics();
         if (stats.isEmpty()) {
            return false;
         } else if (this.isAllNulls(meta)) {
            return values.contains((Object)null) ? false : true;
         } else if (!stats.hasNonNullValue()) {
            return false;
         } else {
            if (stats.isNumNullsSet()) {
               if (stats.getNumNulls() == 0L) {
                  if (values.contains((Object)null) && values.size() == 1) {
                     return true;
                  }
               } else if (values.contains((Object)null)) {
                  return false;
               }
            }

            MinMax<T> minMax = new MinMax(meta.getPrimitiveType().comparator(), values);
            T min = (T)((Comparable)minMax.getMin());
            T max = (T)((Comparable)minMax.getMax());
            return stats.compareMinToValue(max) <= 0 && stats.compareMaxToValue(min) >= 0 ? false : true;
         }
      }
   }

   public Boolean visit(Operators.NotIn notIn) {
      return false;
   }

   public Boolean visit(Operators.Contains contains) {
      return (Boolean)contains.filter(this, (l, r) -> l || r, (l, r) -> l && r, (v) -> false);
   }

   public Boolean visit(Operators.NotEq notEq) {
      Operators.Column<T> filterColumn = notEq.getColumn();
      ColumnChunkMetaData meta = this.getColumnChunk(filterColumn.getColumnPath());
      T value = (T)notEq.getValue();
      if (meta == null) {
         return value == null ? true : false;
      } else {
         Statistics<T> stats = meta.getStatistics();
         if (stats.isEmpty()) {
            return false;
         } else if (value == null) {
            return this.isAllNulls(meta);
         } else if (stats.isNumNullsSet() && this.hasNulls(meta)) {
            return false;
         } else {
            return !stats.hasNonNullValue() ? false : stats.compareMinToValue(value) == 0 && stats.compareMaxToValue(value) == 0;
         }
      }
   }

   public Boolean visit(Operators.Lt lt) {
      Operators.Column<T> filterColumn = lt.getColumn();
      ColumnChunkMetaData meta = this.getColumnChunk(filterColumn.getColumnPath());
      if (meta == null) {
         return true;
      } else {
         Statistics<T> stats = meta.getStatistics();
         if (stats.isEmpty()) {
            return false;
         } else if (this.isAllNulls(meta)) {
            return true;
         } else if (!stats.hasNonNullValue()) {
            return false;
         } else {
            T value = (T)lt.getValue();
            return stats.compareMinToValue(value) >= 0;
         }
      }
   }

   public Boolean visit(Operators.LtEq ltEq) {
      Operators.Column<T> filterColumn = ltEq.getColumn();
      ColumnChunkMetaData meta = this.getColumnChunk(filterColumn.getColumnPath());
      if (meta == null) {
         return true;
      } else {
         Statistics<T> stats = meta.getStatistics();
         if (stats.isEmpty()) {
            return false;
         } else if (this.isAllNulls(meta)) {
            return true;
         } else if (!stats.hasNonNullValue()) {
            return false;
         } else {
            T value = (T)ltEq.getValue();
            return stats.compareMinToValue(value) > 0;
         }
      }
   }

   public Boolean visit(Operators.Gt gt) {
      Operators.Column<T> filterColumn = gt.getColumn();
      ColumnChunkMetaData meta = this.getColumnChunk(filterColumn.getColumnPath());
      if (meta == null) {
         return true;
      } else {
         Statistics<T> stats = meta.getStatistics();
         if (stats.isEmpty()) {
            return false;
         } else if (this.isAllNulls(meta)) {
            return true;
         } else if (!stats.hasNonNullValue()) {
            return false;
         } else {
            T value = (T)gt.getValue();
            return stats.compareMaxToValue(value) <= 0;
         }
      }
   }

   public Boolean visit(Operators.GtEq gtEq) {
      Operators.Column<T> filterColumn = gtEq.getColumn();
      ColumnChunkMetaData meta = this.getColumnChunk(filterColumn.getColumnPath());
      if (meta == null) {
         return true;
      } else {
         Statistics<T> stats = meta.getStatistics();
         if (stats.isEmpty()) {
            return false;
         } else if (this.isAllNulls(meta)) {
            return true;
         } else if (!stats.hasNonNullValue()) {
            return false;
         } else {
            T value = (T)gtEq.getValue();
            return stats.compareMaxToValue(value) < 0;
         }
      }
   }

   public Boolean visit(Operators.And and) {
      return (Boolean)and.getLeft().accept(this) || (Boolean)and.getRight().accept(this);
   }

   public Boolean visit(Operators.Or or) {
      return (Boolean)or.getLeft().accept(this) && (Boolean)or.getRight().accept(this);
   }

   public Boolean visit(Operators.Not not) {
      throw new IllegalArgumentException("This predicate contains a not! Did you forget to run this predicate through LogicalInverseRewriter? " + not);
   }

   private Boolean visit(Operators.UserDefined ud, boolean inverted) {
      Operators.Column<T> filterColumn = ud.getColumn();
      ColumnChunkMetaData columnChunk = this.getColumnChunk(filterColumn.getColumnPath());
      U udp = (U)ud.getUserDefinedPredicate();
      if (columnChunk == null) {
         return inverted ? udp.acceptsNullValue() : !udp.acceptsNullValue();
      } else {
         Statistics<T> stats = columnChunk.getStatistics();
         if (stats.isEmpty()) {
            return false;
         } else if (this.isAllNulls(columnChunk)) {
            return inverted ? udp.acceptsNullValue() : !udp.acceptsNullValue();
         } else if (!stats.hasNonNullValue()) {
            return false;
         } else {
            org.apache.parquet.filter2.predicate.Statistics<T> udpStats = new org.apache.parquet.filter2.predicate.Statistics(stats.genericGetMin(), stats.genericGetMax(), stats.comparator());
            return inverted ? udp.inverseCanDrop(udpStats) : udp.canDrop(udpStats);
         }
      }
   }

   public Boolean visit(Operators.UserDefined ud) {
      return this.visit(ud, false);
   }

   public Boolean visit(Operators.LogicalNotUserDefined lnud) {
      return this.visit(lnud.getUserDefined(), true);
   }
}
