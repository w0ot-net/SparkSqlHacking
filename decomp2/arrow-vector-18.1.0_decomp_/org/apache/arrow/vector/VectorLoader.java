package org.apache.arrow.vector;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.util.Collections2;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.compression.CompressionCodec;
import org.apache.arrow.vector.compression.CompressionUtil;
import org.apache.arrow.vector.compression.NoCompressionCodec;
import org.apache.arrow.vector.ipc.message.ArrowFieldNode;
import org.apache.arrow.vector.ipc.message.ArrowRecordBatch;
import org.apache.arrow.vector.types.pojo.Field;

public class VectorLoader {
   private final VectorSchemaRoot root;
   private final CompressionCodec.Factory factory;
   private boolean decompressionNeeded;

   public VectorLoader(VectorSchemaRoot root) {
      this(root, CompressionCodec.Factory.INSTANCE);
   }

   public VectorLoader(VectorSchemaRoot root, CompressionCodec.Factory factory) {
      this.root = root;
      this.factory = factory;
   }

   public void load(ArrowRecordBatch recordBatch) {
      Iterator<ArrowBuf> buffers = recordBatch.getBuffers().iterator();
      Iterator<ArrowFieldNode> nodes = recordBatch.getNodes().iterator();
      CompressionUtil.CodecType codecType = CompressionUtil.CodecType.fromCompressionType(recordBatch.getBodyCompression().getCodec());
      this.decompressionNeeded = codecType != CompressionUtil.CodecType.NO_COMPRESSION;
      CompressionCodec codec = (CompressionCodec)(this.decompressionNeeded ? this.factory.createCodec(codecType) : NoCompressionCodec.INSTANCE);
      Iterator<Long> variadicBufferCounts = Collections.emptyIterator();
      if (recordBatch.getVariadicBufferCounts() != null && !recordBatch.getVariadicBufferCounts().isEmpty()) {
         variadicBufferCounts = recordBatch.getVariadicBufferCounts().iterator();
      }

      for(FieldVector fieldVector : this.root.getFieldVectors()) {
         this.loadBuffers(fieldVector, fieldVector.getField(), buffers, nodes, codec, variadicBufferCounts);
      }

      this.root.setRowCount(recordBatch.getLength());
      if (nodes.hasNext() || buffers.hasNext() || variadicBufferCounts.hasNext()) {
         String var10002 = Collections2.toString(nodes);
         throw new IllegalArgumentException("not all nodes, buffers and variadicBufferCounts were consumed. nodes: " + var10002 + " buffers: " + Collections2.toString(buffers) + " variadicBufferCounts: " + Collections2.toString(variadicBufferCounts));
      }
   }

   private void loadBuffers(FieldVector vector, Field field, Iterator buffers, Iterator nodes, CompressionCodec codec, Iterator variadicBufferCounts) {
      Preconditions.checkArgument(nodes.hasNext(), "no more field nodes for field %s and vector %s", field, vector);
      ArrowFieldNode fieldNode = (ArrowFieldNode)nodes.next();
      long variadicBufferLayoutCount = 0L;
      if (vector instanceof BaseVariableWidthViewVector) {
         if (!variadicBufferCounts.hasNext()) {
            throw new IllegalStateException("No variadicBufferCounts available for BaseVariableWidthViewVector");
         }

         variadicBufferLayoutCount = (Long)variadicBufferCounts.next();
      }

      int bufferLayoutCount = (int)(variadicBufferLayoutCount + (long)TypeLayout.getTypeBufferCount(field.getType()));
      List<ArrowBuf> ownBuffers = new ArrayList(bufferLayoutCount);

      for(int j = 0; j < bufferLayoutCount; ++j) {
         ArrowBuf nextBuf = (ArrowBuf)buffers.next();
         ArrowBuf bufferToAdd = nextBuf.writerIndex() > 0L ? codec.decompress(vector.getAllocator(), nextBuf) : nextBuf;
         ownBuffers.add(bufferToAdd);
         if (this.decompressionNeeded) {
            nextBuf.getReferenceManager().retain();
         }
      }

      try {
         vector.loadFieldBuffers(fieldNode, ownBuffers);
         if (this.decompressionNeeded) {
            for(ArrowBuf buf : ownBuffers) {
               buf.close();
            }
         }
      } catch (RuntimeException e) {
         throw new IllegalArgumentException("Could not load buffers for field " + String.valueOf(field) + ". error message: " + e.getMessage(), e);
      }

      List<Field> children = field.getChildren();
      if (children.size() > 0) {
         List<FieldVector> childrenFromFields = vector.getChildrenFromFields();
         Preconditions.checkArgument(children.size() == childrenFromFields.size(), "should have as many children as in the schema: found %s expected %s", childrenFromFields.size(), children.size());

         for(int i = 0; i < childrenFromFields.size(); ++i) {
            Field child = (Field)children.get(i);
            FieldVector fieldVector = (FieldVector)childrenFromFields.get(i);
            this.loadBuffers(fieldVector, child, buffers, nodes, codec, variadicBufferCounts);
         }
      }

   }
}
