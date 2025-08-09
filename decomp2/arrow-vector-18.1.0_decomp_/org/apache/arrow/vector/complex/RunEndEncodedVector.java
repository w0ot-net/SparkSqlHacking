package org.apache.arrow.vector.complex;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.OutOfMemoryException;
import org.apache.arrow.memory.util.ByteFunctionHelpers;
import org.apache.arrow.memory.util.hash.ArrowBufHasher;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.BaseIntVector;
import org.apache.arrow.vector.BaseValueVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.ZeroVector;
import org.apache.arrow.vector.compare.VectorVisitor;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.complex.writer.FieldWriter;
import org.apache.arrow.vector.ipc.message.ArrowFieldNode;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.CallBack;
import org.apache.arrow.vector.util.TransferPair;

public class RunEndEncodedVector extends BaseValueVector implements FieldVector {
   public static final FieldVector DEFAULT_VALUE_VECTOR;
   public static final FieldVector DEFAULT_RUN_END_VECTOR;
   protected final CallBack callBack;
   protected Field field;
   protected FieldVector runEndsVector;
   protected FieldVector valuesVector;
   protected int valueCount;

   public static RunEndEncodedVector empty(String name, BufferAllocator allocator) {
      return new RunEndEncodedVector(name, allocator, FieldType.notNullable(ArrowType.RunEndEncoded.INSTANCE), (CallBack)null);
   }

   public RunEndEncodedVector(String name, BufferAllocator allocator, FieldType fieldType, CallBack callBack) {
      this(new Field(name, fieldType, (List)null), allocator, callBack);
   }

   public RunEndEncodedVector(Field field, BufferAllocator allocator, CallBack callBack) {
      this(field, allocator, DEFAULT_RUN_END_VECTOR, DEFAULT_VALUE_VECTOR, callBack);
   }

   public RunEndEncodedVector(Field field, BufferAllocator allocator, FieldVector runEndsVector, FieldVector valuesVector, CallBack callBack) {
      super(allocator);
      this.field = field;
      this.callBack = callBack;
      this.valueCount = 0;
      this.runEndsVector = runEndsVector;
      this.valuesVector = valuesVector;
   }

   public void allocateNew() throws OutOfMemoryException {
      if (!this.allocateNewSafe()) {
         throw new OutOfMemoryException("Failure while allocating memory");
      }
   }

   public boolean allocateNewSafe() {
      this.initializeChildrenFromFields(this.field.getChildren());

      for(FieldVector v : this.getChildrenFromFields()) {
         boolean isAllocated = v.allocateNewSafe();
         if (!isAllocated) {
            v.clear();
            return false;
         }
      }

      return true;
   }

   public void reAlloc() {
      for(FieldVector v : this.getChildrenFromFields()) {
         v.reAlloc();
      }

   }

   public BufferAllocator getAllocator() {
      return this.allocator;
   }

   protected FieldReader getReaderImpl() {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   public void setInitialCapacity(int numRecords) {
   }

   public int getValueCapacity() {
      return this.getChildrenFromFields().stream().mapToInt((item) -> item != null ? item.getValueCapacity() : 0).min().orElseThrow(NoSuchElementException::new);
   }

   public void close() {
      for(FieldVector v : this.getChildrenFromFields()) {
         v.close();
      }

   }

   public void clear() {
      for(FieldVector v : this.getChildrenFromFields()) {
         v.clear();
      }

   }

   public void reset() {
      for(FieldVector v : this.getChildrenFromFields()) {
         v.reset();
      }

      this.valueCount = 0;
   }

   public Field getField() {
      return this.field;
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.RUNENDENCODED;
   }

   public TransferPair getTransferPair(BufferAllocator allocator) {
      throw new UnsupportedOperationException("RunEndEncodedVector does not support getTransferPair(BufferAllocator)");
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return this.getTransferPair((String)ref, allocator, (CallBack)null);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return this.getTransferPair((Field)field, allocator, (CallBack)null);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator, CallBack callBack) {
      throw new UnsupportedOperationException("RunEndEncodedVector does not support getTransferPair(String, BufferAllocator, CallBack)");
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator, CallBack callBack) {
      throw new UnsupportedOperationException("RunEndEncodedVector does not support getTransferPair(Field, BufferAllocator, CallBack)");
   }

   public TransferPair makeTransferPair(ValueVector target) {
      throw new UnsupportedOperationException("RunEndEncodedVector does not support makeTransferPair(ValueVector)");
   }

   public FieldReader getReader() {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   public FieldWriter getWriter() {
      throw new UnsupportedOperationException("Not yet implemented.");
   }

   public int getBufferSize() {
      int bufferSize = 0;

      for(FieldVector v : this.getChildrenFromFields()) {
         bufferSize += v.getBufferSize();
      }

      return bufferSize;
   }

   public int getBufferSizeFor(int valueCount) {
      return 0;
   }

   public ArrowBuf[] getBuffers(boolean clear) {
      return new ArrowBuf[0];
   }

   public ArrowBuf getValidityBuffer() {
      throw new UnsupportedOperationException("Run-end encoded vectors do not have a validity buffer.");
   }

   public ArrowBuf getDataBuffer() {
      throw new UnsupportedOperationException("Run-end encoded vectors do not have a data buffer.");
   }

   public ArrowBuf getOffsetBuffer() {
      throw new UnsupportedOperationException("Run-end encoded vectors do not have a offset buffer.");
   }

   public int getValueCount() {
      return this.valueCount;
   }

   public void setValueCount(int valueCount) {
      this.valueCount = valueCount;
   }

   public Object getObject(int index) {
      this.checkIndex(index);
      int physicalIndex = this.getPhysicalIndex(index);
      return this.valuesVector.getObject(physicalIndex);
   }

   public int getRunEnd(int index) {
      this.checkIndex(index);
      int physicalIndex = this.getPhysicalIndex(index);
      return (int)((BaseIntVector)this.runEndsVector).getValueAsLong(physicalIndex);
   }

   public int getNullCount() {
      return 0;
   }

   public boolean isNull(int index) {
      int physicalIndex = getPhysicalIndex(this.runEndsVector, index);
      return this.valuesVector.isNull(physicalIndex);
   }

   public int hashCode(int index) {
      return this.hashCode(index, (ArrowBufHasher)null);
   }

   public int hashCode(int index, ArrowBufHasher hasher) {
      int hash = 0;

      for(FieldVector v : this.getChildrenFromFields()) {
         if (index < v.getValueCount()) {
            hash = ByteFunctionHelpers.combineHash(hash, v.hashCode(index, hasher));
         }
      }

      return hash;
   }

   public Object accept(VectorVisitor visitor, Object value) {
      return visitor.visit(this, value);
   }

   public String getName() {
      return this.field.getName();
   }

   public Iterator iterator() {
      return Collections.unmodifiableCollection(this.getChildrenFromFields()).iterator();
   }

   public void initializeChildrenFromFields(List children) {
      Preconditions.checkArgument(children.size() == 2, "Run-end encoded vectors must have two child Fields. Found: %s", children.isEmpty() ? "none" : children);
      Preconditions.checkArgument(Arrays.asList(Types.MinorType.SMALLINT.getType(), Types.MinorType.INT.getType(), Types.MinorType.BIGINT.getType()).contains(((Field)children.get(0)).getType()), "The first field represents the run-end vector and must be of type int with size 16, 32, or 64 bits. Found: %s", ((Field)children.get(0)).getType());
      this.runEndsVector = (BaseIntVector)((Field)children.get(0)).createVector(this.allocator);
      this.valuesVector = ((Field)children.get(1)).createVector(this.allocator);
      this.field = new Field(this.field.getName(), this.field.getFieldType(), children);
   }

   public List getChildrenFromFields() {
      return Arrays.asList(this.runEndsVector, this.valuesVector);
   }

   public void loadFieldBuffers(ArrowFieldNode fieldNode, List ownBuffers) {
      if (!ownBuffers.isEmpty()) {
         throw new UnsupportedOperationException("Run-end encoded vectors do not have any associated buffers.");
      }
   }

   public List getFieldBuffers() {
      return List.of();
   }

   /** @deprecated */
   @Deprecated
   public List getFieldInnerVectors() {
      throw new UnsupportedOperationException("There are no inner vectors. Use getFieldBuffers().");
   }

   public long getValidityBufferAddress() {
      throw new UnsupportedOperationException("Run-end encoded vectors do not have a validity buffer.");
   }

   public long getDataBufferAddress() {
      throw new UnsupportedOperationException("Run-end encoded vectors do not have a data buffer.");
   }

   public long getOffsetBufferAddress() {
      throw new UnsupportedOperationException("Run-end encoded vectors do not have an offset buffer.");
   }

   public void setNull(int index) {
      throw new UnsupportedOperationException("Run-end encoded vectors do not have a validity buffer.");
   }

   public FieldVector getRunEndsVector() {
      return this.runEndsVector;
   }

   public FieldVector getValuesVector() {
      return this.valuesVector;
   }

   private void checkIndex(int logicalIndex) {
      if (logicalIndex < 0 || logicalIndex >= this.valueCount) {
         throw new IndexOutOfBoundsException(String.format("index: %s, expected range (0, %s)", logicalIndex, this.valueCount));
      }
   }

   public int getPhysicalIndex(int logicalIndex) {
      return getPhysicalIndex(this.runEndsVector, logicalIndex);
   }

   static int getPhysicalIndex(FieldVector runEndVector, int logicalIndex) {
      if (runEndVector != null && runEndVector.getValueCount() != 0) {
         int low = 0;
         int high = runEndVector.getValueCount() - 1;
         int result = -1;

         while(low <= high) {
            int mid = low + (high - low) / 2;
            long valueAsLong = ((BaseIntVector)runEndVector).getValueAsLong(mid);
            if (valueAsLong > (long)logicalIndex) {
               result = mid;
               high = mid - 1;
            } else {
               low = mid + 1;
            }
         }

         return result;
      } else {
         return -1;
      }
   }

   static {
      DEFAULT_VALUE_VECTOR = ZeroVector.INSTANCE;
      DEFAULT_RUN_END_VECTOR = ZeroVector.INSTANCE;
   }
}
