package org.apache.arrow.vector.ipc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.VectorLoader;
import org.apache.arrow.vector.VectorSchemaRoot;
import org.apache.arrow.vector.compression.CompressionCodec;
import org.apache.arrow.vector.dictionary.Dictionary;
import org.apache.arrow.vector.dictionary.DictionaryProvider;
import org.apache.arrow.vector.ipc.message.ArrowDictionaryBatch;
import org.apache.arrow.vector.ipc.message.ArrowRecordBatch;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.Schema;
import org.apache.arrow.vector.util.DictionaryUtility;
import org.apache.arrow.vector.util.VectorBatchAppender;

public abstract class ArrowReader implements DictionaryProvider, AutoCloseable {
   protected final BufferAllocator allocator;
   private VectorLoader loader;
   private VectorSchemaRoot root;
   protected Map dictionaries;
   private boolean initialized;
   private final CompressionCodec.Factory compressionFactory;

   protected ArrowReader(BufferAllocator allocator) {
      this(allocator, CompressionCodec.Factory.INSTANCE);
   }

   protected ArrowReader(BufferAllocator allocator, CompressionCodec.Factory compressionFactory) {
      this.initialized = false;
      this.allocator = allocator;
      this.compressionFactory = compressionFactory;
   }

   public VectorSchemaRoot getVectorSchemaRoot() throws IOException {
      this.ensureInitialized();
      return this.root;
   }

   public Map getDictionaryVectors() throws IOException {
      this.ensureInitialized();
      return this.dictionaries;
   }

   public Dictionary lookup(long id) {
      if (!this.initialized) {
         throw new IllegalStateException("Unable to lookup until reader has been initialized");
      } else {
         return (Dictionary)this.dictionaries.get(id);
      }
   }

   public Set getDictionaryIds() {
      return this.dictionaries.keySet();
   }

   public abstract boolean loadNextBatch() throws IOException;

   public abstract long bytesRead();

   public void close() throws IOException {
      this.close(true);
   }

   public void close(boolean closeReadSource) throws IOException {
      if (this.initialized) {
         this.root.close();

         for(Dictionary dictionary : this.dictionaries.values()) {
            dictionary.getVector().close();
         }
      }

      if (closeReadSource) {
         this.closeReadSource();
      }

   }

   protected abstract void closeReadSource() throws IOException;

   protected abstract Schema readSchema() throws IOException;

   protected void ensureInitialized() throws IOException {
      if (!this.initialized) {
         this.initialize();
         this.initialized = true;
      }

   }

   protected void initialize() throws IOException {
      Schema originalSchema = this.readSchema();
      List<Field> fields = new ArrayList(originalSchema.getFields().size());
      List<FieldVector> vectors = new ArrayList(originalSchema.getFields().size());
      Map<Long, Dictionary> dictionaries = new HashMap();

      for(Field field : originalSchema.getFields()) {
         Field updated = DictionaryUtility.toMemoryFormat(field, this.allocator, dictionaries);
         fields.add(updated);
         vectors.add(updated.createVector(this.allocator));
      }

      Schema schema = new Schema(fields, originalSchema.getCustomMetadata());
      this.root = new VectorSchemaRoot(schema, vectors, 0);
      this.loader = new VectorLoader(this.root, this.compressionFactory);
      this.dictionaries = Collections.unmodifiableMap(dictionaries);
   }

   protected void prepareLoadNextBatch() throws IOException {
      this.ensureInitialized();
      this.root.setRowCount(0);
   }

   protected void loadRecordBatch(ArrowRecordBatch batch) {
      try {
         this.loader.load(batch);
      } finally {
         batch.close();
      }

   }

   protected void loadDictionary(ArrowDictionaryBatch dictionaryBatch) {
      long id = dictionaryBatch.getDictionaryId();
      Dictionary dictionary = (Dictionary)this.dictionaries.get(id);
      if (dictionary == null) {
         throw new IllegalArgumentException("Dictionary ID " + id + " not defined in schema");
      } else {
         FieldVector vector = dictionary.getVector();
         if (dictionaryBatch.isDelta()) {
            FieldVector deltaVector = vector.getField().createVector(this.allocator);

            try {
               this.load(dictionaryBatch, deltaVector);
               VectorBatchAppender.batchAppend(vector, deltaVector);
            } catch (Throwable var10) {
               if (deltaVector != null) {
                  try {
                     deltaVector.close();
                  } catch (Throwable var9) {
                     var10.addSuppressed(var9);
                  }
               }

               throw var10;
            }

            if (deltaVector != null) {
               deltaVector.close();
            }

         } else {
            this.load(dictionaryBatch, vector);
         }
      }
   }

   private void load(ArrowDictionaryBatch dictionaryBatch, FieldVector vector) {
      VectorSchemaRoot root = new VectorSchemaRoot(Collections.singletonList(vector.getField()), Collections.singletonList(vector), 0);
      VectorLoader loader = new VectorLoader(root, this.compressionFactory);

      try {
         loader.load(dictionaryBatch.getDictionary());
      } finally {
         dictionaryBatch.close();
      }

   }
}
