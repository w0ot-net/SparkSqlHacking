package org.apache.arrow.vector;

import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.ipc.message.ArrowFieldNode;

public interface FieldVector extends ValueVector {
   void initializeChildrenFromFields(List var1);

   List getChildrenFromFields();

   void loadFieldBuffers(ArrowFieldNode var1, List var2);

   List getFieldBuffers();

   default int getExportedCDataBufferCount() {
      return this.getFieldBuffers().size();
   }

   default void exportBuffer(ArrowBuf buffer, List buffers, ArrowBuf buffersPtr, long nullValue, boolean retain) {
      if (buffer != null) {
         if (retain) {
            buffer.getReferenceManager().retain();
         }

         buffersPtr.writeLong(buffer.memoryAddress());
      } else {
         buffersPtr.writeLong(nullValue);
      }

      buffers.add(buffer);
   }

   default void exportCDataBuffers(List buffers, ArrowBuf buffersPtr, long nullValue) {
      for(ArrowBuf arrowBuf : this.getFieldBuffers()) {
         this.exportBuffer(arrowBuf, buffers, buffersPtr, nullValue, true);
      }

   }

   /** @deprecated */
   @Deprecated
   List getFieldInnerVectors();

   long getValidityBufferAddress();

   long getDataBufferAddress();

   long getOffsetBufferAddress();

   void setNull(int var1);
}
