package org.apache.parquet.filter2.dictionarylevel;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.IntFunction;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.Dictionary;
import org.apache.parquet.column.Encoding;
import org.apache.parquet.column.EncodingStats;
import org.apache.parquet.column.page.DictionaryPage;
import org.apache.parquet.column.page.DictionaryPageReadStore;
import org.apache.parquet.filter2.predicate.FilterPredicate;
import org.apache.parquet.filter2.predicate.Operators;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.schema.PrimitiveType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DictionaryFilter implements FilterPredicate.Visitor {
   private static final Logger LOG = LoggerFactory.getLogger(DictionaryFilter.class);
   private static final boolean BLOCK_MIGHT_MATCH = false;
   private static final boolean BLOCK_CANNOT_MATCH = true;
   private final Map columns = new HashMap();
   private final DictionaryPageReadStore dictionaries;

   public static boolean canDrop(FilterPredicate pred, List columns, DictionaryPageReadStore dictionaries) {
      Objects.requireNonNull(pred, "pred cannnot be null");
      Objects.requireNonNull(columns, "columns cannnot be null");
      return (Boolean)pred.accept(new DictionaryFilter(columns, dictionaries));
   }

   private DictionaryFilter(List columnsList, DictionaryPageReadStore dictionaries) {
      for(ColumnChunkMetaData chunk : columnsList) {
         this.columns.put(chunk.getPath(), chunk);
      }

      this.dictionaries = dictionaries;
   }

   private ColumnChunkMetaData getColumnChunk(ColumnPath columnPath) {
      return (ColumnChunkMetaData)this.columns.get(columnPath);
   }

   private Set expandDictionary(ColumnChunkMetaData meta) throws IOException {
      ColumnDescriptor col = new ColumnDescriptor(meta.getPath().toArray(), meta.getPrimitiveType(), -1, -1);
      DictionaryPage page = this.dictionaries.readDictionaryPage(col);
      if (page == null) {
         return null;
      } else {
         Dictionary dict = page.getEncoding().initDictionary(col, page);
         PrimitiveType.PrimitiveTypeName type = meta.getPrimitiveType().getPrimitiveTypeName();
         IntFunction<Object> dictValueProvider;
         switch (type) {
            case FIXED_LEN_BYTE_ARRAY:
            case BINARY:
               dictValueProvider = dict::decodeToBinary;
               break;
            case INT32:
               dictValueProvider = dict::decodeToInt;
               break;
            case INT64:
               dictValueProvider = dict::decodeToLong;
               break;
            case FLOAT:
               dictValueProvider = dict::decodeToFloat;
               break;
            case DOUBLE:
               dictValueProvider = dict::decodeToDouble;
               break;
            default:
               LOG.warn("Unsupported dictionary type: {}", type);
               return null;
         }

         Set<T> dictSet = new HashSet();

         for(int i = 0; i <= dict.getMaxId(); ++i) {
            dictSet.add((Comparable)dictValueProvider.apply(i));
         }

         return dictSet;
      }
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
         } else if (hasNonDictionaryPages(meta)) {
            return false;
         } else {
            try {
               Set<T> dictSet = this.expandDictionary(meta);
               if (dictSet != null && !dictSet.contains(value)) {
                  return true;
               }
            } catch (IOException e) {
               LOG.warn("Failed to process dictionary for filter evaluation.", e);
            }

            return false;
         }
      }
   }

   public Boolean visit(Operators.NotEq notEq) {
      Operators.Column<T> filterColumn = notEq.getColumn();
      ColumnChunkMetaData meta = this.getColumnChunk(filterColumn.getColumnPath());
      T value = (T)notEq.getValue();
      if (value == null && meta == null) {
         return true;
      } else if (value == null) {
         return false;
      } else if (meta == null) {
         return false;
      } else if (hasNonDictionaryPages(meta)) {
         return false;
      } else {
         try {
            Set<T> dictSet = this.expandDictionary(meta);
            boolean mayContainNull = meta.getStatistics() == null || !meta.getStatistics().isNumNullsSet() || meta.getStatistics().getNumNulls() > 0L;
            if (dictSet != null && dictSet.size() == 1 && dictSet.contains(value) && !mayContainNull) {
               return true;
            }
         } catch (IOException e) {
            LOG.warn("Failed to process dictionary for filter evaluation.", e);
         }

         return false;
      }
   }

   public Boolean visit(Operators.Lt lt) {
      Operators.Column<T> filterColumn = lt.getColumn();
      ColumnChunkMetaData meta = this.getColumnChunk(filterColumn.getColumnPath());
      if (meta == null) {
         return true;
      } else if (hasNonDictionaryPages(meta)) {
         return false;
      } else {
         T value = (T)lt.getValue();

         try {
            Set<T> dictSet = this.expandDictionary(meta);
            if (dictSet == null) {
               return false;
            } else {
               Comparator<T> comparator = meta.getPrimitiveType().comparator();

               for(Comparable entry : dictSet) {
                  if (comparator.compare(value, entry) > 0) {
                     return false;
                  }
               }

               return true;
            }
         } catch (IOException e) {
            LOG.warn("Failed to process dictionary for filter evaluation.", e);
            return false;
         }
      }
   }

   public Boolean visit(Operators.LtEq ltEq) {
      Operators.Column<T> filterColumn = ltEq.getColumn();
      ColumnChunkMetaData meta = this.getColumnChunk(filterColumn.getColumnPath());
      if (meta == null) {
         return true;
      } else if (hasNonDictionaryPages(meta)) {
         return false;
      } else {
         T value = (T)ltEq.getValue();
         filterColumn.getColumnPath();

         try {
            Set<T> dictSet = this.expandDictionary(meta);
            if (dictSet == null) {
               return false;
            } else {
               Comparator<T> comparator = meta.getPrimitiveType().comparator();

               for(Comparable entry : dictSet) {
                  if (comparator.compare(value, entry) >= 0) {
                     return false;
                  }
               }

               return true;
            }
         } catch (IOException e) {
            LOG.warn("Failed to process dictionary for filter evaluation.", e);
            return false;
         }
      }
   }

   public Boolean visit(Operators.Gt gt) {
      Operators.Column<T> filterColumn = gt.getColumn();
      ColumnChunkMetaData meta = this.getColumnChunk(filterColumn.getColumnPath());
      if (meta == null) {
         return true;
      } else if (hasNonDictionaryPages(meta)) {
         return false;
      } else {
         T value = (T)gt.getValue();

         try {
            Set<T> dictSet = this.expandDictionary(meta);
            if (dictSet == null) {
               return false;
            } else {
               Comparator<T> comparator = meta.getPrimitiveType().comparator();

               for(Comparable entry : dictSet) {
                  if (comparator.compare(value, entry) < 0) {
                     return false;
                  }
               }

               return true;
            }
         } catch (IOException e) {
            LOG.warn("Failed to process dictionary for filter evaluation.", e);
            return false;
         }
      }
   }

   public Boolean visit(Operators.GtEq gtEq) {
      Operators.Column<T> filterColumn = gtEq.getColumn();
      ColumnChunkMetaData meta = this.getColumnChunk(filterColumn.getColumnPath());
      if (meta == null) {
         return true;
      } else if (hasNonDictionaryPages(meta)) {
         return false;
      } else {
         T value = (T)gtEq.getValue();
         filterColumn.getColumnPath();

         try {
            Set<T> dictSet = this.expandDictionary(meta);
            if (dictSet == null) {
               return false;
            } else {
               Comparator<T> comparator = meta.getPrimitiveType().comparator();

               for(Comparable entry : dictSet) {
                  if (comparator.compare(value, entry) <= 0) {
                     return false;
                  }
               }

               return true;
            }
         } catch (IOException e) {
            LOG.warn("Failed to process dictionary for filter evaluation.", e);
            return false;
         }
      }
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
         } else if (hasNonDictionaryPages(meta)) {
            return false;
         } else {
            try {
               Set<T> dictSet = this.expandDictionary(meta);
               if (dictSet != null) {
                  return this.drop(dictSet, values);
               }
            } catch (IOException e) {
               LOG.warn("Failed to process dictionary for filter evaluation.", e);
            }

            return false;
         }
      }
   }

   private Boolean drop(Set dictSet, Set values) {
      Set<T> smallerSet;
      Set<T> biggerSet;
      if (values.size() < dictSet.size()) {
         smallerSet = values;
         biggerSet = dictSet;
      } else {
         smallerSet = dictSet;
         biggerSet = values;
      }

      for(Comparable e : smallerSet) {
         if (biggerSet.contains(e)) {
            return false;
         }
      }

      return true;
   }

   public Boolean visit(Operators.NotIn notIn) {
      Set<T> values = notIn.getValues();
      Operators.Column<T> filterColumn = notIn.getColumn();
      ColumnChunkMetaData meta = this.getColumnChunk(filterColumn.getColumnPath());
      if (values.size() == 1 && values.contains((Object)null) && meta == null) {
         return true;
      } else if (values.contains((Object)null)) {
         return false;
      } else if (meta == null) {
         return false;
      } else {
         boolean mayContainNull = meta.getStatistics() == null || !meta.getStatistics().isNumNullsSet() || meta.getStatistics().getNumNulls() > 0L;
         if (mayContainNull) {
            return false;
         } else if (hasNonDictionaryPages(meta)) {
            return false;
         } else {
            try {
               Set<T> dictSet = this.expandDictionary(meta);
               if (dictSet != null) {
                  if (dictSet.size() > values.size()) {
                     return false;
                  }

                  return values.containsAll(dictSet);
               }
            } catch (IOException e) {
               LOG.warn("Failed to process dictionary for filter evaluation.", e);
            }

            return false;
         }
      }
   }

   public Boolean visit(Operators.Contains contains) {
      return (Boolean)contains.filter(this, (l, r) -> l || r, (l, r) -> l && r, (v) -> false);
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
      ColumnChunkMetaData meta = this.getColumnChunk(filterColumn.getColumnPath());
      U udp = (U)ud.getUserDefinedPredicate();
      if (meta == null) {
         return inverted ? udp.acceptsNullValue() : !udp.acceptsNullValue();
      } else if (hasNonDictionaryPages(meta)) {
         return false;
      } else if (udp.acceptsNullValue()) {
         return false;
      } else {
         try {
            Set<T> dictSet = this.expandDictionary(meta);
            if (dictSet == null) {
               return false;
            } else {
               for(Comparable entry : dictSet) {
                  boolean keep = udp.keep(entry);
                  if (keep && !inverted || !keep && inverted) {
                     return false;
                  }
               }

               return true;
            }
         } catch (IOException e) {
            LOG.warn("Failed to process dictionary for filter evaluation.", e);
            return false;
         }
      }
   }

   public Boolean visit(Operators.UserDefined udp) {
      return this.visit(udp, false);
   }

   public Boolean visit(Operators.LogicalNotUserDefined udp) {
      return this.visit(udp.getUserDefined(), true);
   }

   private static boolean hasNonDictionaryPages(ColumnChunkMetaData meta) {
      EncodingStats stats = meta.getEncodingStats();
      if (stats != null) {
         return stats.hasNonDictionaryEncodedPages();
      } else {
         Set<Encoding> encodings = new HashSet(meta.getEncodings());
         if (encodings.remove(Encoding.PLAIN_DICTIONARY)) {
            encodings.remove(Encoding.RLE);
            encodings.remove(Encoding.BIT_PACKED);
            return !encodings.isEmpty();
         } else {
            return true;
         }
      }
   }
}
