package org.apache.parquet.filter2.compat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.apache.parquet.column.page.DictionaryPageReadStore;
import org.apache.parquet.filter2.bloomfilterlevel.BloomFilterImpl;
import org.apache.parquet.filter2.dictionarylevel.DictionaryFilter;
import org.apache.parquet.filter2.predicate.FilterPredicate;
import org.apache.parquet.filter2.predicate.SchemaCompatibilityValidator;
import org.apache.parquet.filter2.statisticslevel.StatisticsFilter;
import org.apache.parquet.hadoop.ParquetFileReader;
import org.apache.parquet.hadoop.metadata.BlockMetaData;
import org.apache.parquet.schema.MessageType;

public class RowGroupFilter implements FilterCompat.Visitor {
   private final List blocks;
   private final MessageType schema;
   private final List levels;
   private final ParquetFileReader reader;

   /** @deprecated */
   @Deprecated
   public static List filterRowGroups(FilterCompat.Filter filter, List blocks, MessageType schema) {
      Objects.requireNonNull(filter, "filter cannot be null");
      return (List)filter.accept(new RowGroupFilter(blocks, schema));
   }

   public static List filterRowGroups(List levels, FilterCompat.Filter filter, List blocks, ParquetFileReader reader) {
      Objects.requireNonNull(filter, "filter cannot be null");
      return (List)filter.accept(new RowGroupFilter(levels, blocks, reader));
   }

   /** @deprecated */
   @Deprecated
   private RowGroupFilter(List blocks, MessageType schema) {
      this.blocks = (List)Objects.requireNonNull(blocks, "blocks cannnot be null");
      this.schema = (MessageType)Objects.requireNonNull(schema, "schema cannnot be null");
      this.levels = Collections.singletonList(RowGroupFilter.FilterLevel.STATISTICS);
      this.reader = null;
   }

   private RowGroupFilter(List levels, List blocks, ParquetFileReader reader) {
      this.blocks = (List)Objects.requireNonNull(blocks, "blocks cannnot be null");
      this.reader = (ParquetFileReader)Objects.requireNonNull(reader, "reader cannnot be null");
      this.schema = reader.getFileMetaData().getSchema();
      this.levels = levels;
   }

   public List visit(FilterCompat.FilterPredicateCompat filterPredicateCompat) {
      FilterPredicate filterPredicate = filterPredicateCompat.getFilterPredicate();
      SchemaCompatibilityValidator.validate(filterPredicate, this.schema);
      List<BlockMetaData> filteredBlocks = new ArrayList();

      for(BlockMetaData block : this.blocks) {
         boolean drop = false;
         if (this.levels.contains(RowGroupFilter.FilterLevel.STATISTICS)) {
            drop = StatisticsFilter.canDrop(filterPredicate, block.getColumns());
         }

         if (!drop && this.levels.contains(RowGroupFilter.FilterLevel.DICTIONARY)) {
            DictionaryPageReadStore dictionaryPageReadStore = this.reader.getDictionaryReader(block);
            Throwable var8 = null;

            try {
               drop = DictionaryFilter.canDrop(filterPredicate, block.getColumns(), dictionaryPageReadStore);
            } catch (Throwable var17) {
               var8 = var17;
               throw var17;
            } finally {
               if (dictionaryPageReadStore != null) {
                  if (var8 != null) {
                     try {
                        dictionaryPageReadStore.close();
                     } catch (Throwable var16) {
                        var8.addSuppressed(var16);
                     }
                  } else {
                     dictionaryPageReadStore.close();
                  }
               }

            }
         }

         if (!drop && this.levels.contains(RowGroupFilter.FilterLevel.BLOOMFILTER)) {
            drop = BloomFilterImpl.canDrop(filterPredicate, block.getColumns(), this.reader.getBloomFilterDataReader(block));
         }

         if (!drop) {
            filteredBlocks.add(block);
         }
      }

      return filteredBlocks;
   }

   public List visit(FilterCompat.UnboundRecordFilterCompat unboundRecordFilterCompat) {
      return this.blocks;
   }

   public List visit(FilterCompat.NoOpFilter noOpFilter) {
      return this.blocks;
   }

   public static enum FilterLevel {
      STATISTICS,
      DICTIONARY,
      BLOOMFILTER;
   }
}
