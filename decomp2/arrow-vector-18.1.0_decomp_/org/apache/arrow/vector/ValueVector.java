package org.apache.arrow.vector;

import java.io.Closeable;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.OutOfMemoryException;
import org.apache.arrow.memory.util.hash.ArrowBufHasher;
import org.apache.arrow.vector.compare.VectorVisitor;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.util.CallBack;
import org.apache.arrow.vector.util.TransferPair;
import org.apache.arrow.vector.util.ValueVectorUtility;

public interface ValueVector extends Closeable, Iterable {
   void allocateNew() throws OutOfMemoryException;

   boolean allocateNewSafe();

   void reAlloc();

   BufferAllocator getAllocator();

   void setInitialCapacity(int var1);

   int getValueCapacity();

   void close();

   void clear();

   void reset();

   Field getField();

   Types.MinorType getMinorType();

   TransferPair getTransferPair(BufferAllocator var1);

   TransferPair getTransferPair(String var1, BufferAllocator var2);

   TransferPair getTransferPair(Field var1, BufferAllocator var2);

   TransferPair getTransferPair(String var1, BufferAllocator var2, CallBack var3);

   TransferPair getTransferPair(Field var1, BufferAllocator var2, CallBack var3);

   TransferPair makeTransferPair(ValueVector var1);

   FieldReader getReader();

   int getBufferSize();

   int getBufferSizeFor(int var1);

   ArrowBuf[] getBuffers(boolean var1);

   ArrowBuf getValidityBuffer();

   ArrowBuf getDataBuffer();

   ArrowBuf getOffsetBuffer();

   int getValueCount();

   void setValueCount(int var1);

   Object getObject(int var1);

   int getNullCount();

   boolean isNull(int var1);

   int hashCode(int var1);

   int hashCode(int var1, ArrowBufHasher var2);

   void copyFrom(int var1, int var2, ValueVector var3);

   void copyFromSafe(int var1, int var2, ValueVector var3);

   Object accept(VectorVisitor var1, Object var2);

   String getName();

   default void validate() {
      ValueVectorUtility.validate(this);
   }

   default void validateFull() {
      ValueVectorUtility.validateFull(this);
   }
}
