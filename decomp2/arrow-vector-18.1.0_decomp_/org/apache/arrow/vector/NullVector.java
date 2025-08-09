package org.apache.arrow.vector;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.OutOfMemoryException;
import org.apache.arrow.memory.util.hash.ArrowBufHasher;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.compare.VectorVisitor;
import org.apache.arrow.vector.complex.impl.NullReader;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.ipc.message.ArrowFieldNode;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.CallBack;
import org.apache.arrow.vector.util.TransferPair;

public class NullVector implements FieldVector, ValueIterableVector {
   private int valueCount;
   protected Field field;

   public NullVector(String name) {
      this(name, FieldType.nullable(Types.MinorType.NULL.getType()));
   }

   public NullVector(String name, int valueCount) {
      this(new Field(name, FieldType.nullable(Types.MinorType.NULL.getType()), (List)null), valueCount);
   }

   public NullVector(String name, FieldType fieldType) {
      this(new Field(name, fieldType, (List)null));
   }

   public NullVector(Field field) {
      this((Field)field, 0);
   }

   public NullVector(Field field, int valueCount) {
      this.field = field;
      this.valueCount = valueCount;
   }

   /** @deprecated */
   @Deprecated
   public NullVector() {
      this(new Field("$data$", FieldType.nullable(new ArrowType.Null()), (List)null));
   }

   public void close() {
   }

   public void clear() {
   }

   public void reset() {
   }

   public Field getField() {
      return this.field;
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.NULL;
   }

   public TransferPair getTransferPair(BufferAllocator allocator) {
      return this.getTransferPair(this.getName(), allocator);
   }

   public Iterator iterator() {
      return Collections.emptyIterator();
   }

   public int getBufferSize() {
      return 0;
   }

   public int getBufferSizeFor(int valueCount) {
      return 0;
   }

   public ArrowBuf[] getBuffers(boolean clear) {
      return new ArrowBuf[0];
   }

   public void allocateNew() throws OutOfMemoryException {
      this.allocateNewSafe();
   }

   public boolean allocateNewSafe() {
      return true;
   }

   public void reAlloc() {
   }

   public BufferAllocator getAllocator() {
      throw new UnsupportedOperationException("Tried to get allocator from NullVector");
   }

   public void setInitialCapacity(int numRecords) {
   }

   public int getValueCapacity() {
      return this.valueCount;
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return new TransferImpl(ref);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return new TransferImpl(field.getName());
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator, CallBack callBack) {
      return this.getTransferPair(ref, allocator);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator, CallBack callBack) {
      return this.getTransferPair(field, allocator);
   }

   public TransferPair makeTransferPair(ValueVector target) {
      return new TransferImpl((NullVector)target);
   }

   public FieldReader getReader() {
      return NullReader.INSTANCE;
   }

   public void initializeChildrenFromFields(List children) {
      if (!children.isEmpty()) {
         throw new IllegalArgumentException("Null vector has no children");
      }
   }

   public List getChildrenFromFields() {
      return Collections.emptyList();
   }

   public void loadFieldBuffers(ArrowFieldNode fieldNode, List ownBuffers) {
      Preconditions.checkArgument(ownBuffers.isEmpty(), "Null vector has no buffers");
      this.valueCount = fieldNode.getLength();
   }

   public List getFieldBuffers() {
      return Collections.emptyList();
   }

   /** @deprecated */
   @Deprecated
   public List getFieldInnerVectors() {
      return Collections.emptyList();
   }

   public long getValidityBufferAddress() {
      throw new UnsupportedOperationException();
   }

   public long getDataBufferAddress() {
      throw new UnsupportedOperationException();
   }

   public long getOffsetBufferAddress() {
      throw new UnsupportedOperationException();
   }

   public ArrowBuf getValidityBuffer() {
      throw new UnsupportedOperationException();
   }

   public ArrowBuf getDataBuffer() {
      throw new UnsupportedOperationException();
   }

   public ArrowBuf getOffsetBuffer() {
      throw new UnsupportedOperationException();
   }

   public int getValueCount() {
      return this.valueCount;
   }

   public void setValueCount(int valueCount) {
      this.valueCount = valueCount;
   }

   public Object getObject(int index) {
      return null;
   }

   public int getNullCount() {
      return this.valueCount;
   }

   public void setNull(int index) {
   }

   public boolean isNull(int index) {
      return true;
   }

   public int hashCode(int index) {
      return 31;
   }

   public int hashCode(int index, ArrowBufHasher hasher) {
      return 31;
   }

   public Object accept(VectorVisitor visitor, Object value) {
      return visitor.visit(this, value);
   }

   public void copyFrom(int fromIndex, int thisIndex, ValueVector from) {
      throw new UnsupportedOperationException();
   }

   public void copyFromSafe(int fromIndex, int thisIndex, ValueVector from) {
      throw new UnsupportedOperationException();
   }

   public String getName() {
      return this.getField().getName();
   }

   private class TransferImpl implements TransferPair {
      NullVector to;

      public TransferImpl(String ref) {
         this.to = new NullVector(ref);
      }

      /** @deprecated */
      @Deprecated
      public TransferImpl() {
         this.to = new NullVector();
      }

      public TransferImpl(NullVector to) {
         this.to = to;
      }

      public NullVector getTo() {
         return this.to;
      }

      public void transfer() {
         this.to.valueCount = NullVector.this.valueCount;
      }

      public void splitAndTransfer(int startIndex, int length) {
         this.to.valueCount = length;
      }

      public void copyValueSafe(int fromIndex, int toIndex) {
         if (toIndex > this.to.valueCount) {
            this.to.valueCount = toIndex;
         }

      }
   }
}
