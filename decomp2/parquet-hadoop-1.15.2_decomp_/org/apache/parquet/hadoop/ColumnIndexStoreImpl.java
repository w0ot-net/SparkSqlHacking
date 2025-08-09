package org.apache.parquet.hadoop;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.apache.parquet.hadoop.metadata.BlockMetaData;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.apache.parquet.hadoop.metadata.ColumnPath;
import org.apache.parquet.internal.column.columnindex.ColumnIndex;
import org.apache.parquet.internal.column.columnindex.OffsetIndex;
import org.apache.parquet.internal.filter2.columnindex.ColumnIndexStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ColumnIndexStoreImpl implements ColumnIndexStore {
   private static final Logger LOGGER = LoggerFactory.getLogger(ColumnIndexStoreImpl.class);
   private static final IndexStore MISSING_INDEX_STORE = new IndexStore() {
      public ColumnIndex getColumnIndex() {
         return null;
      }

      public OffsetIndex getOffsetIndex() {
         return null;
      }
   };
   private static final ColumnIndexStoreImpl EMPTY = new ColumnIndexStoreImpl((ParquetFileReader)null, new BlockMetaData(), Collections.emptySet()) {
      public ColumnIndex getColumnIndex(ColumnPath column) {
         return null;
      }

      public OffsetIndex getOffsetIndex(ColumnPath column) {
         throw new ColumnIndexStore.MissingOffsetIndexException(column);
      }
   };
   private final ParquetFileReader reader;
   private final Map store;

   static ColumnIndexStore create(ParquetFileReader reader, BlockMetaData block, Set paths) {
      try {
         return new ColumnIndexStoreImpl(reader, block, paths);
      } catch (ColumnIndexStore.MissingOffsetIndexException var4) {
         return EMPTY;
      }
   }

   private ColumnIndexStoreImpl(ParquetFileReader reader, BlockMetaData block, Set paths) {
      this.reader = reader;
      Map<ColumnPath, IndexStore> store = new HashMap();

      for(ColumnChunkMetaData column : block.getColumns()) {
         ColumnPath path = column.getPath();
         if (paths.contains(path)) {
            store.put(path, new IndexStoreImpl(column));
         }
      }

      this.store = store;
   }

   public ColumnIndex getColumnIndex(ColumnPath column) {
      return ((IndexStore)this.store.getOrDefault(column, MISSING_INDEX_STORE)).getColumnIndex();
   }

   public OffsetIndex getOffsetIndex(ColumnPath column) {
      return ((IndexStore)this.store.getOrDefault(column, MISSING_INDEX_STORE)).getOffsetIndex();
   }

   private class IndexStoreImpl implements IndexStore {
      private final ColumnChunkMetaData meta;
      private ColumnIndex columnIndex;
      private boolean columnIndexRead;
      private final OffsetIndex offsetIndex;

      IndexStoreImpl(ColumnChunkMetaData meta) {
         this.meta = meta;

         OffsetIndex oi;
         try {
            oi = ColumnIndexStoreImpl.this.reader.readOffsetIndex(meta);
         } catch (IOException e) {
            ColumnIndexStoreImpl.LOGGER.warn("Unable to read offset index for column {}", meta.getPath(), e);
            oi = null;
         }

         if (oi == null) {
            throw new ColumnIndexStore.MissingOffsetIndexException(meta.getPath());
         } else {
            this.offsetIndex = oi;
         }
      }

      public ColumnIndex getColumnIndex() {
         if (!this.columnIndexRead) {
            try {
               this.columnIndex = ColumnIndexStoreImpl.this.reader.readColumnIndex(this.meta);
            } catch (IOException e) {
               ColumnIndexStoreImpl.LOGGER.warn("Unable to read column index for column {}", this.meta.getPath(), e);
            }

            this.columnIndexRead = true;
         }

         return this.columnIndex;
      }

      public OffsetIndex getOffsetIndex() {
         return this.offsetIndex;
      }
   }

   private interface IndexStore {
      ColumnIndex getColumnIndex();

      OffsetIndex getOffsetIndex();
   }
}
