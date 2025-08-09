package org.apache.arrow.vector.ipc;

import java.io.IOException;
import java.io.InputStream;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.compression.CompressionCodec;
import org.apache.arrow.vector.ipc.message.ArrowDictionaryBatch;
import org.apache.arrow.vector.ipc.message.ArrowRecordBatch;
import org.apache.arrow.vector.ipc.message.MessageChannelReader;
import org.apache.arrow.vector.ipc.message.MessageResult;
import org.apache.arrow.vector.ipc.message.MessageSerializer;
import org.apache.arrow.vector.types.MetadataVersion;
import org.apache.arrow.vector.types.pojo.DictionaryEncoding;
import org.apache.arrow.vector.types.pojo.Schema;
import org.apache.arrow.vector.validate.MetadataV4UnionChecker;

public class ArrowStreamReader extends ArrowReader {
   private MessageChannelReader messageReader;
   private int loadedDictionaryCount;

   public ArrowStreamReader(MessageChannelReader messageReader, BufferAllocator allocator, CompressionCodec.Factory compressionFactory) {
      super(allocator, compressionFactory);
      this.messageReader = messageReader;
   }

   public ArrowStreamReader(MessageChannelReader messageReader, BufferAllocator allocator) {
      this(messageReader, allocator, CompressionCodec.Factory.INSTANCE);
   }

   public ArrowStreamReader(ReadableByteChannel in, BufferAllocator allocator, CompressionCodec.Factory compressionFactory) {
      this(new MessageChannelReader(new ReadChannel(in), allocator), allocator, compressionFactory);
   }

   public ArrowStreamReader(ReadableByteChannel in, BufferAllocator allocator) {
      this(new MessageChannelReader(new ReadChannel(in), allocator), allocator);
   }

   public ArrowStreamReader(InputStream in, BufferAllocator allocator, CompressionCodec.Factory compressionFactory) {
      this(Channels.newChannel(in), allocator, compressionFactory);
   }

   public ArrowStreamReader(InputStream in, BufferAllocator allocator) {
      this(Channels.newChannel(in), allocator);
   }

   public long bytesRead() {
      return this.messageReader.bytesRead();
   }

   protected void closeReadSource() throws IOException {
      this.messageReader.close();
   }

   public boolean loadNextBatch() throws IOException {
      this.prepareLoadNextBatch();
      MessageResult result = this.messageReader.readNext();
      if (result == null) {
         return false;
      } else if (result.getMessage().headerType() == 3) {
         ArrowBuf bodyBuffer = result.getBodyBuffer();
         if (bodyBuffer == null) {
            bodyBuffer = this.allocator.getEmpty();
         }

         ArrowRecordBatch batch = MessageSerializer.deserializeRecordBatch(result.getMessage(), bodyBuffer);
         this.loadRecordBatch(batch);
         this.checkDictionaries();
         return true;
      } else if (result.getMessage().headerType() == 2) {
         ArrowDictionaryBatch dictionaryBatch = this.readDictionary(result);
         this.loadDictionary(dictionaryBatch);
         ++this.loadedDictionaryCount;
         return this.loadNextBatch();
      } else {
         throw new IOException("Expected RecordBatch or DictionaryBatch but header was " + result.getMessage().headerType());
      }
   }

   private void checkDictionaries() throws IOException {
      if (this.loadedDictionaryCount != this.dictionaries.size()) {
         for(FieldVector vector : this.getVectorSchemaRoot().getFieldVectors()) {
            DictionaryEncoding encoding = vector.getField().getDictionary();
            if (encoding != null && !this.dictionaries.containsKey(encoding.getId()) && vector.getNullCount() < vector.getValueCount()) {
               throw new IOException("The dictionary was not available, id was:" + encoding.getId());
            }
         }

      }
   }

   protected Schema readSchema() throws IOException {
      MessageResult result = this.messageReader.readNext();
      if (result == null) {
         throw new IOException("Unexpected end of input. Missing schema.");
      } else if (result.getMessage().headerType() != 1) {
         throw new IOException("Expected schema but header was " + result.getMessage().headerType());
      } else {
         Schema schema = MessageSerializer.deserializeSchema(result.getMessage());
         MetadataV4UnionChecker.checkRead(schema, MetadataVersion.fromFlatbufID(result.getMessage().version()));
         return schema;
      }
   }

   private ArrowDictionaryBatch readDictionary(MessageResult result) throws IOException {
      ArrowBuf bodyBuffer = result.getBodyBuffer();
      if (bodyBuffer == null) {
         bodyBuffer = this.allocator.getEmpty();
      }

      return MessageSerializer.deserializeDictionaryBatch(result.getMessage(), bodyBuffer);
   }
}
