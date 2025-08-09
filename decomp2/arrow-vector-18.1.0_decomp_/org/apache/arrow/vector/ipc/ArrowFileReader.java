package org.apache.arrow.vector.ipc;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.arrow.flatbuf.Footer;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.util.VisibleForTesting;
import org.apache.arrow.vector.compression.CompressionCodec;
import org.apache.arrow.vector.ipc.message.ArrowBlock;
import org.apache.arrow.vector.ipc.message.ArrowDictionaryBatch;
import org.apache.arrow.vector.ipc.message.ArrowFooter;
import org.apache.arrow.vector.ipc.message.ArrowRecordBatch;
import org.apache.arrow.vector.ipc.message.MessageSerializer;
import org.apache.arrow.vector.types.pojo.Schema;
import org.apache.arrow.vector.validate.MetadataV4UnionChecker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrowFileReader extends ArrowReader {
   private static final Logger LOGGER = LoggerFactory.getLogger(ArrowFileReader.class);
   private SeekableReadChannel in;
   private ArrowFooter footer;
   private int currentDictionaryBatch;
   private int currentRecordBatch;

   public ArrowFileReader(SeekableReadChannel in, BufferAllocator allocator, CompressionCodec.Factory compressionFactory) {
      super(allocator, compressionFactory);
      this.currentDictionaryBatch = 0;
      this.currentRecordBatch = 0;
      this.in = in;
   }

   public ArrowFileReader(SeekableByteChannel in, BufferAllocator allocator, CompressionCodec.Factory compressionFactory) {
      this(new SeekableReadChannel(in), allocator, compressionFactory);
   }

   public ArrowFileReader(SeekableReadChannel in, BufferAllocator allocator) {
      this(in, allocator, CompressionCodec.Factory.INSTANCE);
   }

   public ArrowFileReader(SeekableByteChannel in, BufferAllocator allocator) {
      this(new SeekableReadChannel(in), allocator);
   }

   public long bytesRead() {
      return this.in.bytesRead();
   }

   protected void closeReadSource() throws IOException {
      this.in.close();
   }

   protected Schema readSchema() throws IOException {
      if (this.footer == null) {
         if (this.in.size() <= (long)(ArrowMagic.MAGIC_LENGTH * 2 + 4)) {
            throw new InvalidArrowFileException("file too small: " + this.in.size());
         }

         ByteBuffer buffer = ByteBuffer.allocate(4 + ArrowMagic.MAGIC_LENGTH);
         long footerLengthOffset = this.in.size() - (long)buffer.remaining();
         this.in.setPosition(footerLengthOffset);
         this.in.readFully(buffer);
         buffer.flip();
         byte[] array = buffer.array();
         if (!ArrowMagic.validateMagic(Arrays.copyOfRange(array, 4, array.length))) {
            throw new InvalidArrowFileException("missing Magic number " + Arrays.toString(buffer.array()));
         }

         int footerLength = MessageSerializer.bytesToInt(array);
         if (footerLength <= 0 || (long)(footerLength + ArrowMagic.MAGIC_LENGTH * 2 + 4) > this.in.size() || (long)footerLength > footerLengthOffset) {
            throw new InvalidArrowFileException("invalid footer length: " + footerLength);
         }

         long footerOffset = footerLengthOffset - (long)footerLength;
         LOGGER.debug("Footer starts at {}, length: {}", footerOffset, footerLength);
         ByteBuffer footerBuffer = ByteBuffer.allocate(footerLength);
         this.in.setPosition(footerOffset);
         this.in.readFully(footerBuffer);
         footerBuffer.flip();
         Footer footerFB = Footer.getRootAsFooter(footerBuffer);
         this.footer = new ArrowFooter(footerFB);
      }

      MetadataV4UnionChecker.checkRead(this.footer.getSchema(), this.footer.getMetadataVersion());
      return this.footer.getSchema();
   }

   public void initialize() throws IOException {
      super.initialize();
      if (this.footer.getRecordBatches().size() != 0) {
         for(int i = 0; i < this.dictionaries.size(); ++i) {
            ArrowDictionaryBatch dictionaryBatch = this.readDictionary();
            this.loadDictionary(dictionaryBatch);
         }

      }
   }

   public Map getMetaData() {
      return (Map)(this.footer != null ? this.footer.getMetaData() : new HashMap());
   }

   public ArrowDictionaryBatch readDictionary() throws IOException {
      if (this.currentDictionaryBatch >= this.footer.getDictionaries().size()) {
         throw new IOException("Requested more dictionaries than defined in footer: " + this.currentDictionaryBatch);
      } else {
         ArrowBlock block = (ArrowBlock)this.footer.getDictionaries().get(this.currentDictionaryBatch++);
         return this.readDictionaryBatch(this.in, block, this.allocator);
      }
   }

   public boolean loadNextBatch() throws IOException {
      this.prepareLoadNextBatch();
      if (this.currentRecordBatch < this.footer.getRecordBatches().size()) {
         ArrowBlock block = (ArrowBlock)this.footer.getRecordBatches().get(this.currentRecordBatch++);
         ArrowRecordBatch batch = this.readRecordBatch(this.in, block, this.allocator);
         this.loadRecordBatch(batch);
         return true;
      } else {
         return false;
      }
   }

   public List getDictionaryBlocks() throws IOException {
      this.ensureInitialized();
      return this.footer.getDictionaries();
   }

   public List getRecordBlocks() throws IOException {
      this.ensureInitialized();
      return this.footer.getRecordBatches();
   }

   public boolean loadRecordBatch(ArrowBlock block) throws IOException {
      this.ensureInitialized();
      int blockIndex = this.footer.getRecordBatches().indexOf(block);
      if (blockIndex == -1) {
         throw new IllegalArgumentException("Arrow block does not exist in record batches: " + String.valueOf(block));
      } else {
         this.currentRecordBatch = blockIndex;
         return this.loadNextBatch();
      }
   }

   @VisibleForTesting
   ArrowFooter getFooter() {
      return this.footer;
   }

   private ArrowDictionaryBatch readDictionaryBatch(SeekableReadChannel in, ArrowBlock block, BufferAllocator allocator) throws IOException {
      LOGGER.debug("DictionaryRecordBatch at {}, metadata: {}, body: {}", new Object[]{block.getOffset(), block.getMetadataLength(), block.getBodyLength()});
      in.setPosition(block.getOffset());
      ArrowDictionaryBatch batch = MessageSerializer.deserializeDictionaryBatch(in, block, allocator);
      if (batch == null) {
         throw new IOException("Invalid file. No batch at offset: " + block.getOffset());
      } else {
         return batch;
      }
   }

   private ArrowRecordBatch readRecordBatch(SeekableReadChannel in, ArrowBlock block, BufferAllocator allocator) throws IOException {
      LOGGER.debug("RecordBatch at {}, metadata: {}, body: {}", new Object[]{block.getOffset(), block.getMetadataLength(), block.getBodyLength()});
      in.setPosition(block.getOffset());
      ArrowRecordBatch batch = MessageSerializer.deserializeRecordBatch(in, block, allocator);
      if (batch == null) {
         throw new IOException("Invalid file. No batch at offset: " + block.getOffset());
      } else {
         return batch;
      }
   }
}
