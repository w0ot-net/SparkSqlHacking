package org.apache.parquet.hadoop;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.parquet.bytes.ByteBufferAllocator;
import org.apache.parquet.bytes.ByteBufferReleaser;
import org.apache.parquet.column.ColumnDescriptor;
import org.apache.parquet.column.page.DictionaryPage;
import org.apache.parquet.column.page.DictionaryPageReadStore;
import org.apache.parquet.hadoop.metadata.BlockMetaData;
import org.apache.parquet.hadoop.metadata.ColumnChunkMetaData;
import org.apache.parquet.io.ParquetDecodingException;

class DictionaryPageReader implements DictionaryPageReadStore {
   private final ParquetFileReader reader;
   private final Map columns;
   private final Map dictionaryPageCache;
   private ColumnChunkPageReadStore rowGroup = null;
   private ByteBufferReleaser releaser;

   DictionaryPageReader(ParquetFileReader reader, BlockMetaData block, ByteBufferAllocator allocator) {
      this.reader = (ParquetFileReader)Objects.requireNonNull(reader);
      this.columns = new HashMap();
      this.dictionaryPageCache = new ConcurrentHashMap();
      this.releaser = new ByteBufferReleaser(allocator);

      for(ColumnChunkMetaData column : block.getColumns()) {
         this.columns.put(column.getPath().toDotString(), column);
      }

   }

   void setRowGroup(ColumnChunkPageReadStore rowGroup) {
      this.rowGroup = rowGroup;
   }

   public DictionaryPage readDictionaryPage(ColumnDescriptor descriptor) {
      if (this.rowGroup != null) {
         return this.rowGroup.readDictionaryPage(descriptor);
      } else {
         String dotPath = String.join(".", descriptor.getPath());
         ColumnChunkMetaData column = (ColumnChunkMetaData)this.columns.get(dotPath);
         if (column == null) {
            throw new ParquetDecodingException("Failed to load dictionary, unknown column: " + dotPath);
         } else {
            return (DictionaryPage)((Optional)this.dictionaryPageCache.computeIfAbsent(dotPath, (key) -> {
               try {
                  DictionaryPage dict = column.hasDictionaryPage() ? this.reader.readDictionary(column) : null;
                  return dict != null ? Optional.of(this.reusableCopy(dict)) : Optional.empty();
               } catch (IOException e) {
                  throw new ParquetDecodingException("Failed to read dictionary", e);
               }
            })).orElse((Object)null);
         }
      }
   }

   private DictionaryPage reusableCopy(DictionaryPage dict) throws IOException {
      return new DictionaryPage(dict.getBytes().copy(this.releaser), dict.getDictionarySize(), dict.getEncoding());
   }

   public void close() {
      this.releaser.close();
   }
}
