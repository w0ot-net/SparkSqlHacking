package org.apache.arrow.vector;

import java.util.Iterator;
import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.OutOfMemoryException;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.compare.VectorVisitor;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.ipc.message.ArrowFieldNode;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.util.CallBack;
import org.apache.arrow.vector.util.TransferPair;

public abstract class ExtensionTypeVector extends BaseValueVector implements FieldVector {
   private final ValueVector underlyingVector;
   private final String name;

   public ExtensionTypeVector(String name, BufferAllocator allocator, ValueVector underlyingVector) {
      super(allocator);
      Preconditions.checkNotNull(underlyingVector, "underlyingVector cannot be null.");
      this.name = name;
      this.underlyingVector = underlyingVector;
   }

   public ExtensionTypeVector(Field field, BufferAllocator allocator, ValueVector underlyingVector) {
      this(field.getName(), allocator, underlyingVector);
   }

   public String getName() {
      return this.name;
   }

   public ValueVector getUnderlyingVector() {
      return this.underlyingVector;
   }

   public void allocateNew() throws OutOfMemoryException {
      this.underlyingVector.allocateNew();
   }

   public boolean allocateNewSafe() {
      return this.underlyingVector.allocateNewSafe();
   }

   public void reAlloc() {
      this.underlyingVector.reAlloc();
   }

   public void setInitialCapacity(int numRecords) {
      this.underlyingVector.setInitialCapacity(numRecords);
   }

   public int getValueCapacity() {
      return this.underlyingVector.getValueCapacity();
   }

   public void reset() {
      this.underlyingVector.reset();
   }

   public Field getField() {
      return this.underlyingVector.getField();
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.EXTENSIONTYPE;
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return this.underlyingVector.getTransferPair(ref, allocator);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator, CallBack callBack) {
      return this.underlyingVector.getTransferPair(ref, allocator, callBack);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return this.underlyingVector.getTransferPair(field, allocator);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator, CallBack callBack) {
      return this.underlyingVector.getTransferPair(field, allocator, callBack);
   }

   public TransferPair makeTransferPair(ValueVector target) {
      return this.underlyingVector.makeTransferPair(target);
   }

   protected FieldReader getReaderImpl() {
      return this.underlyingVector.getReader();
   }

   public int getBufferSize() {
      return this.underlyingVector.getBufferSize();
   }

   public int getBufferSizeFor(int valueCount) {
      return this.underlyingVector.getBufferSizeFor(valueCount);
   }

   public ArrowBuf[] getBuffers(boolean clear) {
      return this.underlyingVector.getBuffers(clear);
   }

   public ArrowBuf getValidityBuffer() {
      return this.underlyingVector.getValidityBuffer();
   }

   public ArrowBuf getDataBuffer() {
      return this.underlyingVector.getDataBuffer();
   }

   public ArrowBuf getOffsetBuffer() {
      return this.underlyingVector.getOffsetBuffer();
   }

   public int getValueCount() {
      return this.underlyingVector.getValueCount();
   }

   public void setValueCount(int valueCount) {
      this.underlyingVector.setValueCount(valueCount);
   }

   public abstract Object getObject(int var1);

   public int getNullCount() {
      return this.underlyingVector.getNullCount();
   }

   public boolean isNull(int index) {
      return this.underlyingVector.isNull(index);
   }

   public void setNull(int index) {
      ((FieldVector)this.underlyingVector).setNull(index);
   }

   public void initializeChildrenFromFields(List children) {
      ((FieldVector)this.underlyingVector).initializeChildrenFromFields(children);
   }

   public List getChildrenFromFields() {
      return ((FieldVector)this.underlyingVector).getChildrenFromFields();
   }

   public void loadFieldBuffers(ArrowFieldNode fieldNode, List ownBuffers) {
      ((FieldVector)this.underlyingVector).loadFieldBuffers(fieldNode, ownBuffers);
   }

   public List getFieldBuffers() {
      return ((FieldVector)this.underlyingVector).getFieldBuffers();
   }

   /** @deprecated */
   @Deprecated
   public List getFieldInnerVectors() {
      return ((FieldVector)this.underlyingVector).getFieldInnerVectors();
   }

   public long getValidityBufferAddress() {
      return ((FieldVector)this.underlyingVector).getValidityBufferAddress();
   }

   public long getDataBufferAddress() {
      return ((FieldVector)this.underlyingVector).getDataBufferAddress();
   }

   public long getOffsetBufferAddress() {
      return ((FieldVector)this.underlyingVector).getOffsetBufferAddress();
   }

   public void clear() {
      this.underlyingVector.clear();
   }

   public void close() {
      this.underlyingVector.close();
   }

   public TransferPair getTransferPair(BufferAllocator allocator) {
      return this.underlyingVector.getTransferPair(allocator);
   }

   public Iterator iterator() {
      return this.underlyingVector.iterator();
   }

   public BufferAllocator getAllocator() {
      return this.underlyingVector.getAllocator();
   }

   public Object accept(VectorVisitor visitor, Object value) {
      return visitor.visit(this, value);
   }
}
