package org.apache.arrow.vector.ipc.message;

import com.google.flatbuffers.FlatBufferBuilder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.arrow.flatbuf.RecordBatch;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.compression.NoCompressionCodec;
import org.apache.arrow.vector.util.DataSizeRoundingUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ArrowRecordBatch implements ArrowMessage {
   private static final Logger LOGGER = LoggerFactory.getLogger(ArrowRecordBatch.class);
   private final int length;
   private final List nodes;
   private final List buffers;
   private final ArrowBodyCompression bodyCompression;
   private final List buffersLayout;
   private final List variadicBufferCounts;
   private boolean closed;

   public ArrowRecordBatch(int length, List nodes, List buffers) {
      this(length, nodes, buffers, NoCompressionCodec.DEFAULT_BODY_COMPRESSION, (List)null, true);
   }

   public ArrowRecordBatch(int length, List nodes, List buffers, ArrowBodyCompression bodyCompression) {
      this(length, nodes, buffers, bodyCompression, (List)null, true);
   }

   public ArrowRecordBatch(int length, List nodes, List buffers, ArrowBodyCompression bodyCompression, boolean alignBuffers) {
      this(length, nodes, buffers, bodyCompression, (List)null, alignBuffers, true);
   }

   public ArrowRecordBatch(int length, List nodes, List buffers, ArrowBodyCompression bodyCompression, boolean alignBuffers, boolean retainBuffers) {
      this(length, nodes, buffers, bodyCompression, (List)null, alignBuffers, retainBuffers);
   }

   public ArrowRecordBatch(int length, List nodes, List buffers, ArrowBodyCompression bodyCompression, List variadicBufferCounts, boolean alignBuffers) {
      this(length, nodes, buffers, bodyCompression, variadicBufferCounts, alignBuffers, true);
   }

   public ArrowRecordBatch(int length, List nodes, List buffers, ArrowBodyCompression bodyCompression, List variadicBufferCounts, boolean alignBuffers, boolean retainBuffers) {
      this.closed = false;
      this.length = length;
      this.nodes = nodes;
      this.buffers = buffers;
      Preconditions.checkArgument(bodyCompression != null, "body compression cannot be null");
      this.bodyCompression = bodyCompression;
      this.variadicBufferCounts = variadicBufferCounts;
      List<ArrowBuffer> arrowBuffers = new ArrayList(buffers.size());
      long offset = 0L;

      for(ArrowBuf arrowBuf : buffers) {
         if (retainBuffers) {
            arrowBuf.getReferenceManager().retain();
         }

         long size = arrowBuf.readableBytes();
         arrowBuffers.add(new ArrowBuffer(offset, size));
         if (LOGGER.isTraceEnabled()) {
            LOGGER.trace("Buffer in RecordBatch at {}, length: {}", offset, size);
         }

         offset += size;
         if (alignBuffers) {
            offset = DataSizeRoundingUtil.roundUpTo8Multiple(offset);
         }
      }

      this.buffersLayout = Collections.unmodifiableList(arrowBuffers);
   }

   private ArrowRecordBatch(boolean dummy, int length, List nodes, List buffers, ArrowBodyCompression bodyCompression, List variadicBufferCounts) {
      this.closed = false;
      this.length = length;
      this.nodes = nodes;
      this.buffers = buffers;
      Preconditions.checkArgument(bodyCompression != null, "body compression cannot be null");
      this.bodyCompression = bodyCompression;
      this.variadicBufferCounts = variadicBufferCounts;
      this.closed = false;
      List<ArrowBuffer> arrowBuffers = new ArrayList();
      long offset = 0L;

      for(ArrowBuf arrowBuf : buffers) {
         long size = arrowBuf.readableBytes();
         arrowBuffers.add(new ArrowBuffer(offset, size));
         offset += size;
      }

      this.buffersLayout = Collections.unmodifiableList(arrowBuffers);
   }

   public byte getMessageType() {
      return 3;
   }

   public int getLength() {
      return this.length;
   }

   public ArrowBodyCompression getBodyCompression() {
      return this.bodyCompression;
   }

   public List getNodes() {
      return this.nodes;
   }

   public List getBuffers() {
      if (this.closed) {
         throw new IllegalStateException("already closed");
      } else {
         return this.buffers;
      }
   }

   public List getVariadicBufferCounts() {
      return this.variadicBufferCounts;
   }

   public ArrowRecordBatch cloneWithTransfer(BufferAllocator allocator) {
      List<ArrowBuf> newBufs = (List)this.buffers.stream().map((buf) -> buf.getReferenceManager().transferOwnership(buf, allocator).getTransferredBuffer().writerIndex(buf.writerIndex())).collect(Collectors.toList());
      this.close();
      return new ArrowRecordBatch(false, this.length, this.nodes, newBufs, this.bodyCompression, this.variadicBufferCounts);
   }

   public List getBuffersLayout() {
      return this.buffersLayout;
   }

   public int writeTo(FlatBufferBuilder builder) {
      RecordBatch.startNodesVector(builder, this.nodes.size());
      int nodesOffset = FBSerializables.writeAllStructsToVector(builder, this.nodes);
      RecordBatch.startBuffersVector(builder, this.buffers.size());
      int buffersOffset = FBSerializables.writeAllStructsToVector(builder, this.buffersLayout);
      int compressOffset = 0;
      if (this.bodyCompression.getCodec() != -1) {
         compressOffset = this.bodyCompression.writeTo(builder);
      }

      int variadicBufferCountsOffset = 0;
      if (this.variadicBufferCounts != null && !this.variadicBufferCounts.isEmpty()) {
         variadicBufferCountsOffset = this.variadicBufferCounts.size();
         int elementSizeInBytes = 8;
         builder.startVector(elementSizeInBytes, variadicBufferCountsOffset, elementSizeInBytes);

         for(int i = this.variadicBufferCounts.size() - 1; i >= 0; --i) {
            long value = (Long)this.variadicBufferCounts.get(i);
            builder.addLong(value);
         }

         variadicBufferCountsOffset = builder.endVector();
      }

      RecordBatch.startRecordBatch(builder);
      RecordBatch.addLength(builder, (long)this.length);
      RecordBatch.addNodes(builder, nodesOffset);
      RecordBatch.addBuffers(builder, buffersOffset);
      if (this.bodyCompression.getCodec() != -1) {
         RecordBatch.addCompression(builder, compressOffset);
      }

      if (this.variadicBufferCounts != null && !this.variadicBufferCounts.isEmpty()) {
         RecordBatch.addVariadicBufferCounts(builder, variadicBufferCountsOffset);
      }

      return RecordBatch.endRecordBatch(builder);
   }

   public Object accepts(ArrowMessage.ArrowMessageVisitor visitor) {
      return visitor.visit(this);
   }

   public void close() {
      if (!this.closed) {
         this.closed = true;

         for(ArrowBuf arrowBuf : this.buffers) {
            arrowBuf.getReferenceManager().release();
         }
      }

   }

   public String toString() {
      int variadicBufCount = 0;
      if (this.variadicBufferCounts != null && !this.variadicBufferCounts.isEmpty()) {
         variadicBufCount = this.variadicBufferCounts.size();
      }

      int var10000 = this.length;
      return "ArrowRecordBatch [length=" + var10000 + ", nodes=" + String.valueOf(this.nodes) + ", #buffers=" + this.buffers.size() + ", #variadicBufferCounts=" + variadicBufCount + ", buffersLayout=" + String.valueOf(this.buffersLayout) + ", closed=" + this.closed + "]";
   }

   public long computeBodyLength() {
      long size = 0L;
      List<ArrowBuf> buffers = this.getBuffers();
      List<ArrowBuffer> buffersLayout = this.getBuffersLayout();
      if (buffers.size() != buffersLayout.size()) {
         int var10002 = buffers.size();
         throw new IllegalStateException("the layout does not match: " + var10002 + " != " + buffersLayout.size());
      } else {
         for(int i = 0; i < buffers.size(); ++i) {
            ArrowBuf buffer = (ArrowBuf)buffers.get(i);
            ArrowBuffer layout = (ArrowBuffer)buffersLayout.get(i);
            size = layout.getOffset() + buffer.readableBytes();
            size = DataSizeRoundingUtil.roundUpTo8Multiple(size);
         }

         return size;
      }
   }
}
