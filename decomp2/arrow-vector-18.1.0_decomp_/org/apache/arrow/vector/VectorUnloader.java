package org.apache.arrow.vector;

import java.util.ArrayList;
import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.compression.CompressionCodec;
import org.apache.arrow.vector.compression.CompressionUtil;
import org.apache.arrow.vector.compression.NoCompressionCodec;
import org.apache.arrow.vector.ipc.message.ArrowFieldNode;
import org.apache.arrow.vector.ipc.message.ArrowRecordBatch;

public class VectorUnloader {
   private final VectorSchemaRoot root;
   private final boolean includeNullCount;
   private final CompressionCodec codec;
   private final boolean alignBuffers;

   public VectorUnloader(VectorSchemaRoot root) {
      this(root, true, NoCompressionCodec.INSTANCE, true);
   }

   public VectorUnloader(VectorSchemaRoot root, boolean includeNullCount, boolean alignBuffers) {
      this(root, includeNullCount, NoCompressionCodec.INSTANCE, alignBuffers);
   }

   public VectorUnloader(VectorSchemaRoot root, boolean includeNullCount, CompressionCodec codec, boolean alignBuffers) {
      this.root = root;
      this.includeNullCount = includeNullCount;
      this.codec = (CompressionCodec)(codec == null ? NoCompressionCodec.INSTANCE : codec);
      this.alignBuffers = alignBuffers;
   }

   public ArrowRecordBatch getRecordBatch() {
      List<ArrowFieldNode> nodes = new ArrayList();
      List<ArrowBuf> buffers = new ArrayList();
      List<Long> variadicBufferCounts = new ArrayList();

      for(FieldVector vector : this.root.getFieldVectors()) {
         this.appendNodes(vector, nodes, buffers, variadicBufferCounts);
      }

      return new ArrowRecordBatch(this.root.getRowCount(), nodes, buffers, CompressionUtil.createBodyCompression(this.codec), variadicBufferCounts, this.alignBuffers, false);
   }

   private long getVariadicBufferCount(FieldVector vector) {
      return vector instanceof BaseVariableWidthViewVector ? (long)((BaseVariableWidthViewVector)vector).getDataBuffers().size() : 0L;
   }

   private void appendNodes(FieldVector vector, List nodes, List buffers, List variadicBufferCounts) {
      nodes.add(new ArrowFieldNode((long)vector.getValueCount(), this.includeNullCount ? (long)vector.getNullCount() : -1L));
      List<ArrowBuf> fieldBuffers = vector.getFieldBuffers();
      long variadicBufferCount = this.getVariadicBufferCount(vector);
      int expectedBufferCount = (int)((long)TypeLayout.getTypeBufferCount(vector.getField().getType()) + variadicBufferCount);
      if (vector instanceof BaseVariableWidthViewVector) {
         variadicBufferCounts.add(variadicBufferCount);
      }

      if (fieldBuffers.size() != expectedBufferCount) {
         throw new IllegalArgumentException(String.format("wrong number of buffers for field %s in vector %s. found: %s", vector.getField(), vector.getClass().getSimpleName(), fieldBuffers));
      } else {
         for(ArrowBuf buf : fieldBuffers) {
            buf.getReferenceManager().retain();
            buffers.add(this.codec.compress(vector.getAllocator(), buf));
         }

         for(FieldVector child : vector.getChildrenFromFields()) {
            this.appendNodes(child, nodes, buffers, variadicBufferCounts);
         }

      }
   }
}
