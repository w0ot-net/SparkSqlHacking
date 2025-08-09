package org.apache.arrow.vector.ipc.message;

import com.google.flatbuffers.FlatBufferBuilder;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.apache.arrow.flatbuf.Buffer;
import org.apache.arrow.flatbuf.DictionaryBatch;
import org.apache.arrow.flatbuf.FieldNode;
import org.apache.arrow.flatbuf.Message;
import org.apache.arrow.flatbuf.RecordBatch;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.util.LargeMemoryUtil;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.compression.NoCompressionCodec;
import org.apache.arrow.vector.ipc.ReadChannel;
import org.apache.arrow.vector.ipc.WriteChannel;
import org.apache.arrow.vector.types.pojo.Schema;

public class MessageSerializer {
   public static final int IPC_CONTINUATION_TOKEN = -1;

   public static int bytesToInt(byte[] bytes) {
      return ((bytes[3] & 255) << 24) + ((bytes[2] & 255) << 16) + ((bytes[1] & 255) << 8) + (bytes[0] & 255);
   }

   public static void intToBytes(int value, byte[] bytes) {
      bytes[3] = (byte)(value >>> 24);
      bytes[2] = (byte)(value >>> 16);
      bytes[1] = (byte)(value >>> 8);
      bytes[0] = (byte)value;
   }

   public static void longToBytes(long value, byte[] bytes) {
      bytes[7] = (byte)((int)(value >>> 56));
      bytes[6] = (byte)((int)(value >>> 48));
      bytes[5] = (byte)((int)(value >>> 40));
      bytes[4] = (byte)((int)(value >>> 32));
      bytes[3] = (byte)((int)(value >>> 24));
      bytes[2] = (byte)((int)(value >>> 16));
      bytes[1] = (byte)((int)(value >>> 8));
      bytes[0] = (byte)((int)value);
   }

   public static int writeMessageBuffer(WriteChannel out, int messageLength, ByteBuffer messageBuffer) throws IOException {
      return writeMessageBuffer(out, messageLength, messageBuffer, IpcOption.DEFAULT);
   }

   public static int writeMessageBuffer(WriteChannel out, int messageLength, ByteBuffer messageBuffer, IpcOption option) throws IOException {
      int prefixSize = option.write_legacy_ipc_format ? 4 : 8;
      if ((messageLength + prefixSize) % 8 != 0) {
         messageLength += 8 - (messageLength + prefixSize) % 8;
      }

      if (!option.write_legacy_ipc_format) {
         out.writeIntLittleEndian(-1);
      }

      out.writeIntLittleEndian(messageLength);
      out.write(messageBuffer);
      out.align();
      return messageLength + prefixSize;
   }

   public static long serialize(WriteChannel out, Schema schema) throws IOException {
      return serialize(out, schema, IpcOption.DEFAULT);
   }

   public static long serialize(WriteChannel out, Schema schema, IpcOption option) throws IOException {
      long start = out.getCurrentPosition();
      Preconditions.checkArgument(start % 8L == 0L, "out is not aligned");
      ByteBuffer serializedMessage = serializeMetadata(schema, option);
      int messageLength = serializedMessage.remaining();
      int bytesWritten = writeMessageBuffer(out, messageLength, serializedMessage, option);
      Preconditions.checkArgument(bytesWritten % 8 == 0, "out is not aligned");
      return (long)bytesWritten;
   }

   /** @deprecated */
   @Deprecated
   public static ByteBuffer serializeMetadata(Schema schema) {
      return serializeMetadata(schema, IpcOption.DEFAULT);
   }

   public static ByteBuffer serializeMetadata(Schema schema, IpcOption writeOption) {
      FlatBufferBuilder builder = new FlatBufferBuilder();
      int schemaOffset = schema.getSchema(builder);
      return serializeMessage(builder, (byte)1, schemaOffset, 0L, writeOption);
   }

   public static Schema deserializeSchema(Message schemaMessage) {
      Preconditions.checkArgument(schemaMessage.headerType() == 1, "Expected schema but result was:  %s", schemaMessage.headerType());
      return Schema.convertSchema((org.apache.arrow.flatbuf.Schema)schemaMessage.header(new org.apache.arrow.flatbuf.Schema()));
   }

   public static Schema deserializeSchema(ReadChannel in) throws IOException {
      MessageMetadataResult result = readMessage(in);
      if (result == null) {
         throw new IOException("Unexpected end of input when reading Schema");
      } else if (result.getMessage().headerType() != 1) {
         throw new IOException("Expected schema but header was " + result.getMessage().headerType());
      } else {
         return deserializeSchema(result);
      }
   }

   public static Schema deserializeSchema(MessageMetadataResult message) {
      return deserializeSchema(message.getMessage());
   }

   public static ArrowBlock serialize(WriteChannel out, ArrowRecordBatch batch) throws IOException {
      return serialize(out, batch, IpcOption.DEFAULT);
   }

   public static ArrowBlock serialize(WriteChannel out, ArrowRecordBatch batch, IpcOption option) throws IOException {
      long start = out.getCurrentPosition();
      long bodyLength = batch.computeBodyLength();
      Preconditions.checkArgument(bodyLength % 8L == 0L, "batch is not aligned");
      ByteBuffer serializedMessage = serializeMetadata((ArrowMessage)batch, option);
      int metadataLength = serializedMessage.remaining();
      int prefixSize = 4;
      if (!option.write_legacy_ipc_format) {
         out.writeIntLittleEndian(-1);
         prefixSize = 8;
      }

      int padding = (int)((start + (long)metadataLength + (long)prefixSize) % 8L);
      if (padding != 0) {
         metadataLength += 8 - padding;
      }

      out.writeIntLittleEndian(metadataLength);
      out.write(serializedMessage);
      out.align();
      long bufferLength = writeBatchBuffers(out, batch);
      Preconditions.checkArgument(bufferLength % 8L == 0L, "out is not aligned");
      return new ArrowBlock(start, metadataLength + prefixSize, bufferLength);
   }

   public static long writeBatchBuffers(WriteChannel out, ArrowRecordBatch batch) throws IOException {
      long bufferStart = out.getCurrentPosition();
      List<ArrowBuf> buffers = batch.getBuffers();
      List<ArrowBuffer> buffersLayout = batch.getBuffersLayout();

      for(int i = 0; i < buffers.size(); ++i) {
         ArrowBuf buffer = (ArrowBuf)buffers.get(i);
         ArrowBuffer layout = (ArrowBuffer)buffersLayout.get(i);
         long startPosition = bufferStart + layout.getOffset();
         if (startPosition != out.getCurrentPosition()) {
            out.writeZeros(startPosition - out.getCurrentPosition());
         }

         out.write(buffer);
         if (out.getCurrentPosition() != startPosition + layout.getSize()) {
            throw new IllegalStateException("wrong buffer size: " + out.getCurrentPosition() + " != " + startPosition + layout.getSize());
         }
      }

      out.align();
      return out.getCurrentPosition() - bufferStart;
   }

   /** @deprecated */
   @Deprecated
   public static ByteBuffer serializeMetadata(ArrowMessage message) {
      return serializeMetadata(message, IpcOption.DEFAULT);
   }

   public static ByteBuffer serializeMetadata(ArrowMessage message, IpcOption writeOption) {
      FlatBufferBuilder builder = new FlatBufferBuilder();
      int batchOffset = message.writeTo(builder);
      return serializeMessage(builder, message.getMessageType(), batchOffset, message.computeBodyLength(), writeOption);
   }

   public static ArrowRecordBatch deserializeRecordBatch(Message recordBatchMessage, ArrowBuf bodyBuffer) throws IOException {
      RecordBatch recordBatchFB = (RecordBatch)recordBatchMessage.header(new RecordBatch());
      return deserializeRecordBatch(recordBatchFB, bodyBuffer);
   }

   public static ArrowRecordBatch deserializeRecordBatch(ReadChannel in, BufferAllocator allocator) throws IOException {
      MessageMetadataResult result = readMessage(in);
      if (result == null) {
         throw new IOException("Unexpected end of input when reading a RecordBatch");
      } else if (result.getMessage().headerType() != 3) {
         throw new IOException("Expected RecordBatch but header was " + result.getMessage().headerType());
      } else {
         long bodyLength = result.getMessageBodyLength();
         ArrowBuf bodyBuffer = readMessageBody(in, bodyLength, allocator);
         return deserializeRecordBatch(result.getMessage(), bodyBuffer);
      }
   }

   public static ArrowRecordBatch deserializeRecordBatch(ReadChannel in, ArrowBlock block, BufferAllocator alloc) throws IOException {
      long totalLen = (long)block.getMetadataLength() + block.getBodyLength();
      ArrowBuf buffer = alloc.buffer(totalLen);
      if (in.readFully(buffer, totalLen) != totalLen) {
         throw new IOException("Unexpected end of input trying to read batch.");
      } else {
         int prefixSize = buffer.getInt(0L) == -1 ? 8 : 4;
         ArrowBuf metadataBuffer = buffer.slice((long)prefixSize, (long)(block.getMetadataLength() - prefixSize));
         Message messageFB = Message.getRootAsMessage(metadataBuffer.nioBuffer().asReadOnlyBuffer());
         RecordBatch recordBatchFB = (RecordBatch)messageFB.header(new RecordBatch());
         ArrowBuf body = buffer.slice((long)block.getMetadataLength(), totalLen - (long)block.getMetadataLength());
         return deserializeRecordBatch(recordBatchFB, body);
      }
   }

   public static ArrowRecordBatch deserializeRecordBatch(RecordBatch recordBatchFB, ArrowBuf body) throws IOException {
      int nodesLength = recordBatchFB.nodesLength();
      List<ArrowFieldNode> nodes = new ArrayList();

      for(int i = 0; i < nodesLength; ++i) {
         FieldNode node = recordBatchFB.nodes(i);
         if ((long)((int)node.length()) != node.length() || (long)((int)node.nullCount()) != node.nullCount()) {
            throw new IOException("Cannot currently deserialize record batches with node length larger than INT_MAX records.");
         }

         nodes.add(new ArrowFieldNode(node.length(), node.nullCount()));
      }

      List<ArrowBuf> buffers = new ArrayList();

      for(int i = 0; i < recordBatchFB.buffersLength(); ++i) {
         Buffer bufferFB = recordBatchFB.buffers(i);
         ArrowBuf vectorBuffer = body.slice(bufferFB.offset(), bufferFB.length());
         buffers.add(vectorBuffer);
      }

      ArrowBodyCompression bodyCompression = recordBatchFB.compression() == null ? NoCompressionCodec.DEFAULT_BODY_COMPRESSION : new ArrowBodyCompression(recordBatchFB.compression().codec(), recordBatchFB.compression().method());
      List<Long> variadicBufferCounts = new ArrayList();

      for(int i = 0; i < recordBatchFB.variadicBufferCountsLength(); ++i) {
         variadicBufferCounts.add(recordBatchFB.variadicBufferCounts(i));
      }

      if ((long)((int)recordBatchFB.length()) != recordBatchFB.length()) {
         throw new IOException("Cannot currently deserialize record batches with more than INT_MAX records.");
      } else {
         ArrowRecordBatch arrowRecordBatch = new ArrowRecordBatch(LargeMemoryUtil.checkedCastToInt(recordBatchFB.length()), nodes, buffers, bodyCompression, variadicBufferCounts, true);
         body.getReferenceManager().release();
         return arrowRecordBatch;
      }
   }

   public static ArrowRecordBatch deserializeRecordBatch(MessageMetadataResult serializedMessage, ArrowBuf underlying) throws IOException {
      return deserializeRecordBatch(serializedMessage.getMessage(), underlying);
   }

   public static ArrowBlock serialize(WriteChannel out, ArrowDictionaryBatch batch) throws IOException {
      return serialize(out, batch, IpcOption.DEFAULT);
   }

   public static ArrowBlock serialize(WriteChannel out, ArrowDictionaryBatch batch, IpcOption option) throws IOException {
      long start = out.getCurrentPosition();
      long bodyLength = batch.computeBodyLength();
      Preconditions.checkArgument(bodyLength % 8L == 0L, "batch is not aligned");
      ByteBuffer serializedMessage = serializeMetadata((ArrowMessage)batch, option);
      int metadataLength = serializedMessage.remaining();
      int prefixSize = 4;
      if (!option.write_legacy_ipc_format) {
         out.writeIntLittleEndian(-1);
         prefixSize = 8;
      }

      int padding = (int)((start + (long)metadataLength + (long)prefixSize) % 8L);
      if (padding != 0) {
         metadataLength += 8 - padding;
      }

      out.writeIntLittleEndian(metadataLength);
      out.write(serializedMessage);
      out.align();
      long bufferLength = writeBatchBuffers(out, batch.getDictionary());
      Preconditions.checkArgument(bufferLength % 8L == 0L, "out is not aligned");
      return new ArrowBlock(start, metadataLength + prefixSize, bufferLength);
   }

   public static ArrowDictionaryBatch deserializeDictionaryBatch(Message message, ArrowBuf bodyBuffer) throws IOException {
      DictionaryBatch dictionaryBatchFB = (DictionaryBatch)message.header(new DictionaryBatch());
      ArrowRecordBatch recordBatch = deserializeRecordBatch(dictionaryBatchFB.data(), bodyBuffer);
      return new ArrowDictionaryBatch(dictionaryBatchFB.id(), recordBatch, dictionaryBatchFB.isDelta());
   }

   public static ArrowDictionaryBatch deserializeDictionaryBatch(MessageMetadataResult message, ArrowBuf bodyBuffer) throws IOException {
      return deserializeDictionaryBatch(message.getMessage(), bodyBuffer);
   }

   public static ArrowDictionaryBatch deserializeDictionaryBatch(ReadChannel in, BufferAllocator allocator) throws IOException {
      MessageMetadataResult result = readMessage(in);
      if (result == null) {
         throw new IOException("Unexpected end of input when reading a DictionaryBatch");
      } else if (result.getMessage().headerType() != 2) {
         throw new IOException("Expected DictionaryBatch but header was " + result.getMessage().headerType());
      } else {
         long bodyLength = result.getMessageBodyLength();
         ArrowBuf bodyBuffer = readMessageBody(in, bodyLength, allocator);
         return deserializeDictionaryBatch(result.getMessage(), bodyBuffer);
      }
   }

   public static ArrowDictionaryBatch deserializeDictionaryBatch(ReadChannel in, ArrowBlock block, BufferAllocator alloc) throws IOException {
      long totalLen = (long)block.getMetadataLength() + block.getBodyLength();
      ArrowBuf buffer = alloc.buffer(totalLen);
      if (in.readFully(buffer, totalLen) != totalLen) {
         throw new IOException("Unexpected end of input trying to read batch.");
      } else {
         int prefixSize = buffer.getInt(0L) == -1 ? 8 : 4;
         ArrowBuf metadataBuffer = buffer.slice((long)prefixSize, (long)(block.getMetadataLength() - prefixSize));
         Message messageFB = Message.getRootAsMessage(metadataBuffer.nioBuffer().asReadOnlyBuffer());
         DictionaryBatch dictionaryBatchFB = (DictionaryBatch)messageFB.header(new DictionaryBatch());
         ArrowBuf body = buffer.slice((long)block.getMetadataLength(), totalLen - (long)block.getMetadataLength());
         ArrowRecordBatch recordBatch = deserializeRecordBatch(dictionaryBatchFB.data(), body);
         return new ArrowDictionaryBatch(dictionaryBatchFB.id(), recordBatch, dictionaryBatchFB.isDelta());
      }
   }

   public static ArrowMessage deserializeMessageBatch(MessageChannelReader reader) throws IOException {
      MessageResult result = reader.readNext();
      if (result == null) {
         return null;
      } else if (result.getMessage().bodyLength() > 2147483647L) {
         throw new IOException("Cannot currently deserialize record batches over 2GB");
      } else if (result.getMessage().version() != 3 && result.getMessage().version() != 4) {
         throw new IOException("Received metadata with an incompatible version number: " + result.getMessage().version());
      } else {
         switch (result.getMessage().headerType()) {
            case 2:
               return deserializeDictionaryBatch(result.getMessage(), result.getBodyBuffer());
            case 3:
               return deserializeRecordBatch(result.getMessage(), result.getBodyBuffer());
            default:
               throw new IOException("Unexpected message header type " + result.getMessage().headerType());
         }
      }
   }

   public static ArrowMessage deserializeMessageBatch(ReadChannel in, BufferAllocator alloc) throws IOException {
      return deserializeMessageBatch(new MessageChannelReader(in, alloc));
   }

   /** @deprecated */
   @Deprecated
   public static ByteBuffer serializeMessage(FlatBufferBuilder builder, byte headerType, int headerOffset, long bodyLength) {
      return serializeMessage(builder, headerType, headerOffset, bodyLength, IpcOption.DEFAULT);
   }

   public static ByteBuffer serializeMessage(FlatBufferBuilder builder, byte headerType, int headerOffset, long bodyLength, IpcOption writeOption) {
      Message.startMessage(builder);
      Message.addHeaderType(builder, headerType);
      Message.addHeader(builder, headerOffset);
      Message.addVersion(builder, writeOption.metadataVersion.toFlatbufID());
      Message.addBodyLength(builder, bodyLength);
      builder.finish(Message.endMessage(builder));
      return builder.dataBuffer();
   }

   public static MessageMetadataResult readMessage(ReadChannel in) throws IOException {
      ByteBuffer buffer = ByteBuffer.allocate(4);
      if (in.readFully(buffer) == 4) {
         int messageLength = bytesToInt(buffer.array());
         if (messageLength == -1) {
            ((java.nio.Buffer)buffer).clear();
            if (in.readFully(buffer) == 4) {
               messageLength = bytesToInt(buffer.array());
            }
         }

         if (messageLength != 0) {
            ByteBuffer messageBuffer = ByteBuffer.allocate(messageLength);
            if (in.readFully(messageBuffer) != messageLength) {
               throw new IOException("Unexpected end of stream trying to read message.");
            }

            ByteBuffer rewindBuffer = (ByteBuffer)((java.nio.Buffer)messageBuffer).rewind();
            Message message = Message.getRootAsMessage(messageBuffer);
            return new MessageMetadataResult(messageLength, messageBuffer, message);
         }
      }

      return null;
   }

   public static ArrowBuf readMessageBody(ReadChannel in, long bodyLength, BufferAllocator allocator) throws IOException {
      ArrowBuf bodyBuffer = allocator.buffer(bodyLength);

      try {
         if (in.readFully(bodyBuffer, bodyLength) != bodyLength) {
            throw new IOException("Unexpected end of input trying to read batch.");
         } else {
            return bodyBuffer;
         }
      } catch (IOException | RuntimeException e) {
         bodyBuffer.close();
         throw e;
      }
   }
}
