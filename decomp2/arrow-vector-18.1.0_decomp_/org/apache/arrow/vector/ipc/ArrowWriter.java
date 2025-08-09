package org.apache.arrow.vector.ipc;

import java.io.IOException;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.VectorSchemaRoot;
import org.apache.arrow.vector.VectorUnloader;
import org.apache.arrow.vector.compression.CompressionCodec;
import org.apache.arrow.vector.compression.CompressionUtil;
import org.apache.arrow.vector.compression.NoCompressionCodec;
import org.apache.arrow.vector.dictionary.Dictionary;
import org.apache.arrow.vector.dictionary.DictionaryProvider;
import org.apache.arrow.vector.ipc.message.ArrowBlock;
import org.apache.arrow.vector.ipc.message.ArrowDictionaryBatch;
import org.apache.arrow.vector.ipc.message.ArrowRecordBatch;
import org.apache.arrow.vector.ipc.message.IpcOption;
import org.apache.arrow.vector.ipc.message.MessageSerializer;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.Schema;
import org.apache.arrow.vector.util.DictionaryUtility;
import org.apache.arrow.vector.validate.MetadataV4UnionChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ArrowWriter implements AutoCloseable {
   protected static final Logger LOGGER = LoggerFactory.getLogger(ArrowWriter.class);
   protected final Schema schema;
   protected final WriteChannel out;
   private final VectorUnloader unloader;
   private final DictionaryProvider dictionaryProvider;
   private final Set dictionaryIdsUsed;
   private final CompressionCodec.Factory compressionFactory;
   private final CompressionUtil.CodecType codecType;
   private final Optional compressionLevel;
   private boolean started;
   private boolean ended;
   private final CompressionCodec codec;
   protected IpcOption option;

   protected ArrowWriter(VectorSchemaRoot root, DictionaryProvider provider, WritableByteChannel out) {
      this(root, provider, out, IpcOption.DEFAULT);
   }

   protected ArrowWriter(VectorSchemaRoot root, DictionaryProvider provider, WritableByteChannel out, IpcOption option) {
      this(root, provider, out, option, NoCompressionCodec.Factory.INSTANCE, CompressionUtil.CodecType.NO_COMPRESSION, Optional.empty());
   }

   protected ArrowWriter(VectorSchemaRoot root, DictionaryProvider provider, WritableByteChannel out, IpcOption option, CompressionCodec.Factory compressionFactory, CompressionUtil.CodecType codecType, Optional compressionLevel) {
      this.dictionaryIdsUsed = new HashSet();
      this.started = false;
      this.ended = false;
      this.out = new WriteChannel(out);
      this.option = option;
      this.dictionaryProvider = provider;
      this.compressionFactory = compressionFactory;
      this.codecType = codecType;
      this.compressionLevel = compressionLevel;
      this.codec = this.compressionLevel.isPresent() ? this.compressionFactory.createCodec(this.codecType, (Integer)this.compressionLevel.get()) : this.compressionFactory.createCodec(this.codecType);
      this.unloader = new VectorUnloader(root, true, this.codec, true);
      List<Field> fields = new ArrayList(root.getSchema().getFields().size());
      MetadataV4UnionChecker.checkForUnion(root.getSchema().getFields().iterator(), option.metadataVersion);

      for(Field field : root.getSchema().getFields()) {
         fields.add(DictionaryUtility.toMessageFormat(field, provider, this.dictionaryIdsUsed));
      }

      this.schema = new Schema(fields, root.getSchema().getCustomMetadata());
   }

   public void start() throws IOException {
      this.ensureStarted();
   }

   public void writeBatch() throws IOException {
      this.ensureStarted();
      this.ensureDictionariesWritten(this.dictionaryProvider, this.dictionaryIdsUsed);

      try (ArrowRecordBatch batch = this.unloader.getRecordBatch()) {
         this.writeRecordBatch(batch);
      }

   }

   protected void writeDictionaryBatch(Dictionary dictionary) throws IOException {
      FieldVector vector = dictionary.getVector();
      long id = dictionary.getEncoding().getId();
      int count = vector.getValueCount();
      VectorSchemaRoot dictRoot = new VectorSchemaRoot(Collections.singletonList(vector.getField()), Collections.singletonList(vector), count);
      VectorUnloader unloader = new VectorUnloader(dictRoot, true, this.codec, true);
      ArrowRecordBatch batch = unloader.getRecordBatch();
      ArrowDictionaryBatch dictionaryBatch = new ArrowDictionaryBatch(id, batch, false);

      try {
         this.writeDictionaryBatch(dictionaryBatch);
      } finally {
         try {
            dictionaryBatch.close();
         } catch (Exception e) {
            throw new RuntimeException("Error occurred while closing dictionary.", e);
         }
      }

   }

   protected ArrowBlock writeDictionaryBatch(ArrowDictionaryBatch batch) throws IOException {
      ArrowBlock block = MessageSerializer.serialize(this.out, batch, this.option);
      if (LOGGER.isDebugEnabled()) {
         LOGGER.debug("DictionaryRecordBatch at {}, metadata: {}, body: {}", new Object[]{block.getOffset(), block.getMetadataLength(), block.getBodyLength()});
      }

      return block;
   }

   protected ArrowBlock writeRecordBatch(ArrowRecordBatch batch) throws IOException {
      ArrowBlock block = MessageSerializer.serialize(this.out, batch, this.option);
      if (LOGGER.isDebugEnabled()) {
         LOGGER.debug("RecordBatch at {}, metadata: {}, body: {}", new Object[]{block.getOffset(), block.getMetadataLength(), block.getBodyLength()});
      }

      return block;
   }

   public void end() throws IOException {
      this.ensureStarted();
      this.ensureEnded();
   }

   public long bytesWritten() {
      return this.out.getCurrentPosition();
   }

   private void ensureStarted() throws IOException {
      if (!this.started) {
         this.started = true;
         this.startInternal(this.out);
         MessageSerializer.serialize(this.out, this.schema, this.option);
      }

   }

   protected abstract void ensureDictionariesWritten(DictionaryProvider var1, Set var2) throws IOException;

   private void ensureEnded() throws IOException {
      if (!this.ended) {
         this.ended = true;
         this.endInternal(this.out);
      }

   }

   protected void startInternal(WriteChannel out) throws IOException {
   }

   protected void endInternal(WriteChannel out) throws IOException {
   }

   public void close() {
      try {
         this.end();
         this.out.close();
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
   }
}
