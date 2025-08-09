package org.apache.parquet.filter2.bloomfilterlevel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.parquet.Preconditions;
import org.apache.parquet.column.values.bloomfilter.BloomFilter;
import org.apache.parquet.filter2.predicate.FilterPredicate;
import org.apache.parquet.filter2.predicate.Operators;
import org.apache.parquet.hadoop.BloomFilterReader;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BloomFilterImpl implements FilterPredicate.Visitor {
   private static final Logger LOG = LoggerFactory.getLogger(BloomFilterImpl.class);
   private static final boolean BLOCK_MIGHT_MATCH = false;
   private static final boolean BLOCK_CANNOT_MATCH = true;
   private final Map columns = new HashMap();
   private BloomFilterReader bloomFilterReader;

   public static boolean canDrop(FilterPredicate pred, List columns, BloomFilterReader bloomFilterReader) {
      Preconditions.checkNotNull(pred, "pred");
      Preconditions.checkNotNull(columns, "columns");
      return (Boolean)pred.accept(new BloomFilterImpl(columns, bloomFilterReader));
   }

   private BloomFilterImpl(List columnsList, BloomFilterReader bloomFilterReader) {
      for(ColumnChunkMetaData chunk : columnsList) {
         this.columns.put(chunk.getPath(), chunk);
      }

      this.bloomFilterReader = bloomFilterReader;
   }

   private ColumnChunkMetaData getColumnChunk(ColumnPath columnPath) {
      return (ColumnChunkMetaData)this.columns.get(columnPath);
   }

   public Boolean visit(Operators.Eq eq) {
      T value = (T)eq.getValue();
      if (value == null) {
         return false;
      } else {
         Operators.Column<T> filterColumn = eq.getColumn();
         ColumnChunkMetaData meta = this.getColumnChunk(filterColumn.getColumnPath());
         if (meta == null) {
            return true;
         } else {
            try {
               BloomFilter bloomFilter = this.bloomFilterReader.readBloomFilter(meta);
               if (bloomFilter != null && !bloomFilter.findHash(bloomFilter.hash(value))) {
                  return true;
               }
            } catch (RuntimeException e) {
               LOG.warn(e.getMessage());
               return false;
            }

            return false;
         }
      }
   }

   public Boolean visit(Operators.NotEq notEq) {
      return false;
   }

   public Boolean visit(Operators.Lt lt) {
      return false;
   }

   public Boolean visit(Operators.LtEq ltEq) {
      return false;
   }

   public Boolean visit(Operators.Gt gt) {
      return false;
   }

   public Boolean visit(Operators.GtEq gtEq) {
      return false;
   }

   public Boolean visit(Operators.Contains contains) {
      return (Boolean)contains.filter(this, (l, r) -> l || r, (l, r) -> l && r, (v) -> false);
   }

   public Boolean visit(Operators.In in) {
      Set<T> values = in.getValues();
      if (values.contains((Object)null)) {
         return false;
      } else {
         Operators.Column<T> filterColumn = in.getColumn();
         ColumnChunkMetaData meta = this.getColumnChunk(filterColumn.getColumnPath());
         if (meta == null) {
            return true;
         } else {
            BloomFilter bloomFilter = this.bloomFilterReader.readBloomFilter(meta);
            if (bloomFilter != null) {
               for(Comparable value : values) {
                  if (bloomFilter.findHash(bloomFilter.hash(value))) {
                     return false;
                  }
               }

               return true;
            } else {
               return false;
            }
         }
      }
   }

   public Boolean visit(Operators.NotIn notIn) {
      return false;
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
      return false;
   }

   public Boolean visit(Operators.UserDefined udp) {
      return this.visit(udp, false);
   }

   public Boolean visit(Operators.LogicalNotUserDefined udp) {
      return this.visit(udp.getUserDefined(), true);
   }
}
