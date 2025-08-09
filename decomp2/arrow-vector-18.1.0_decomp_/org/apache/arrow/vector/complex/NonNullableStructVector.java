package org.apache.arrow.vector.complex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.util.ByteFunctionHelpers;
import org.apache.arrow.memory.util.hash.ArrowBufHasher;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.DensityAwareVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.ValueIterableVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.compare.VectorVisitor;
import org.apache.arrow.vector.complex.impl.SingleStructReaderImpl;
import org.apache.arrow.vector.complex.reader.FieldReader;
import org.apache.arrow.vector.holders.ComplexHolder;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.DictionaryEncoding;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.CallBack;
import org.apache.arrow.vector.util.JsonStringHashMap;
import org.apache.arrow.vector.util.TransferPair;

public class NonNullableStructVector extends AbstractStructVector implements ValueIterableVector {
   private final SingleStructReaderImpl reader;
   protected Field field;
   public int valueCount;
   private transient StructTransferPair ephPair;

   public static NonNullableStructVector empty(String name, BufferAllocator allocator) {
      FieldType fieldType = new FieldType(false, ArrowType.Struct.INSTANCE, (DictionaryEncoding)null, (Map)null);
      return new NonNullableStructVector(name, allocator, fieldType, (CallBack)null, AbstractStructVector.ConflictPolicy.CONFLICT_REPLACE, false);
   }

   public static NonNullableStructVector emptyWithDuplicates(String name, BufferAllocator allocator) {
      FieldType fieldType = new FieldType(false, ArrowType.Struct.INSTANCE, (DictionaryEncoding)null, (Map)null);
      return new NonNullableStructVector(name, allocator, fieldType, (CallBack)null, AbstractStructVector.ConflictPolicy.CONFLICT_APPEND, true);
   }

   public NonNullableStructVector(String name, BufferAllocator allocator, FieldType fieldType, CallBack callBack) {
      this(new Field(name, fieldType, (List)null), allocator, callBack);
   }

   public NonNullableStructVector(Field field, BufferAllocator allocator, CallBack callBack) {
      this(field, allocator, callBack, (AbstractStructVector.ConflictPolicy)null, true);
   }

   public NonNullableStructVector(String name, BufferAllocator allocator, FieldType fieldType, CallBack callBack, AbstractStructVector.ConflictPolicy conflictPolicy, boolean allowConflictPolicyChanges) {
      this(new Field(name, fieldType, (List)null), allocator, callBack, conflictPolicy, allowConflictPolicyChanges);
   }

   public NonNullableStructVector(Field field, BufferAllocator allocator, CallBack callBack, AbstractStructVector.ConflictPolicy conflictPolicy, boolean allowConflictPolicyChanges) {
      super(field.getName(), allocator, callBack, conflictPolicy, allowConflictPolicyChanges);
      this.reader = new SingleStructReaderImpl(this);
      this.field = field;
      this.valueCount = 0;
   }

   public FieldReader getReader() {
      return this.reader;
   }

   public void copyFrom(int fromIndex, int thisIndex, ValueVector from) {
      Preconditions.checkArgument(this.getMinorType() == from.getMinorType());
      if (this.ephPair == null || this.ephPair.from != from) {
         this.ephPair = (StructTransferPair)from.makeTransferPair(this);
      }

      this.ephPair.copyValueSafe(fromIndex, thisIndex);
   }

   public void copyFromSafe(int fromIndex, int thisIndex, ValueVector from) {
      this.copyFrom(fromIndex, thisIndex, from);
   }

   protected boolean supportsDirectRead() {
      return true;
   }

   public Iterator fieldNameIterator() {
      return this.getChildFieldNames().iterator();
   }

   public void setInitialCapacity(int numRecords) {
      for(ValueVector v : this) {
         v.setInitialCapacity(numRecords);
      }

   }

   public void setInitialCapacity(int valueCount, double density) {
      for(ValueVector vector : this) {
         if (vector instanceof DensityAwareVector) {
            ((DensityAwareVector)vector).setInitialCapacity(valueCount, density);
         } else {
            vector.setInitialCapacity(valueCount);
         }
      }

   }

   public int getBufferSize() {
      if (this.valueCount != 0 && this.size() != 0) {
         long buffer = 0L;

         for(ValueVector v : this) {
            buffer += (long)v.getBufferSize();
         }

         return (int)buffer;
      } else {
         return 0;
      }
   }

   public int getBufferSizeFor(int valueCount) {
      if (valueCount == 0) {
         return 0;
      } else {
         long bufferSize = 0L;

         for(ValueVector v : this) {
            bufferSize += (long)v.getBufferSizeFor(valueCount);
         }

         return (int)bufferSize;
      }
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

   public TransferPair getTransferPair(BufferAllocator allocator) {
      return this.getTransferPair((String)this.name, allocator, (CallBack)null);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator, CallBack callBack) {
      return new StructTransferPair(this, new NonNullableStructVector(this.name, allocator, this.field.getFieldType(), callBack, this.getConflictPolicy(), this.allowConflictPolicyChanges), false);
   }

   public TransferPair makeTransferPair(ValueVector to) {
      return new StructTransferPair(this, (NonNullableStructVector)to);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return new StructTransferPair(this, new NonNullableStructVector(ref, allocator, this.field.getFieldType(), this.callBack, this.getConflictPolicy(), this.allowConflictPolicyChanges), false);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return new StructTransferPair(this, new NonNullableStructVector(field, allocator, this.callBack, this.getConflictPolicy(), this.allowConflictPolicyChanges), false);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator, CallBack callBack) {
      return new StructTransferPair(this, new NonNullableStructVector(field, allocator, callBack, this.getConflictPolicy(), this.allowConflictPolicyChanges), false);
   }

   public int getValueCapacity() {
      return this.size() == 0 ? 0 : this.getChildren().stream().mapToInt((child) -> child.getValueCapacity()).min().getAsInt();
   }

   public Map getObject(int index) {
      Map<String, Object> vv = new JsonStringHashMap();

      for(String child : this.getChildFieldNames()) {
         ValueVector v = this.getChild(child);
         if (v != null && index < v.getValueCount()) {
            Object value = v.getObject(index);
            if (value != null) {
               vv.put(child, value);
            }
         }
      }

      return vv;
   }

   public int hashCode(int index) {
      return this.hashCode(index, (ArrowBufHasher)null);
   }

   public int hashCode(int index, ArrowBufHasher hasher) {
      int hash = 0;

      for(FieldVector v : this.getChildren()) {
         if (index < v.getValueCount()) {
            hash = ByteFunctionHelpers.combineHash(hash, v.hashCode(index, hasher));
         }
      }

      return hash;
   }

   public Object accept(VectorVisitor visitor, Object value) {
      return visitor.visit(this, value);
   }

   public boolean isNull(int index) {
      return false;
   }

   public int getNullCount() {
      return 0;
   }

   public void get(int index, ComplexHolder holder) {
      this.reader.setPosition(index);
      holder.reader = this.reader;
   }

   public int getValueCount() {
      return this.valueCount;
   }

   public ValueVector getVectorById(int id) {
      return this.getChildByOrdinal(id);
   }

   public ValueVector getVectorById(int id, Class clazz) {
      ValueVector untyped = this.getVectorById(id);
      if (clazz.isInstance(untyped)) {
         return (ValueVector)clazz.cast(untyped);
      } else {
         throw new ClassCastException("Id " + id + " had the wrong type. Expected " + clazz.getCanonicalName() + " but was " + untyped.getClass().getCanonicalName());
      }
   }

   public void setValueCount(int valueCount) {
      for(ValueVector v : this.getChildren()) {
         v.setValueCount(valueCount);
      }

      this.valueCount = valueCount;
   }

   public void clear() {
      for(ValueVector v : this.getChildren()) {
         v.clear();
      }

      this.valueCount = 0;
   }

   public void reset() {
      for(ValueVector v : this.getChildren()) {
         v.reset();
      }

      this.valueCount = 0;
   }

   public Field getField() {
      List<Field> children = new ArrayList();

      for(ValueVector child : this.getChildren()) {
         children.add(child.getField());
      }

      if (!children.isEmpty() && !this.field.getChildren().equals(children)) {
         this.field = new Field(this.field.getName(), this.field.getFieldType(), children);
         return this.field;
      } else {
         return this.field;
      }
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.STRUCT;
   }

   public void close() {
      Collection<FieldVector> vectors = this.getChildren();

      for(FieldVector v : vectors) {
         v.close();
      }

      vectors.clear();
      this.valueCount = 0;
      super.close();
   }

   public void initializeChildrenFromFields(List children) {
      for(Field field : children) {
         FieldVector vector = (FieldVector)this.add(field.getName(), field.getFieldType());
         vector.initializeChildrenFromFields(field.getChildren());
      }

   }

   public List getChildrenFromFields() {
      return this.getChildren();
   }

   protected static class StructTransferPair implements TransferPair {
      private final TransferPair[] pairs;
      private final NonNullableStructVector from;
      private final NonNullableStructVector to;

      public StructTransferPair(NonNullableStructVector from, NonNullableStructVector to) {
         this(from, to, true);
      }

      protected StructTransferPair(NonNullableStructVector from, NonNullableStructVector to, boolean allocate) {
         this.from = from;
         this.to = to;
         this.pairs = new TransferPair[from.size()];
         this.to.ephPair = null;
         int i = 0;

         for(String child : from.getChildFieldNames()) {
            int preSize = to.size();
            FieldVector vector = from.getChild(child);
            if (vector != null) {
               FieldVector newVector = to.addOrGet(child, vector.getField().getFieldType(), vector.getClass());
               if (allocate && to.size() != preSize) {
                  newVector.allocateNew();
               }

               this.pairs[i++] = vector.makeTransferPair(newVector);
            }
         }

      }

      public void transfer() {
         for(TransferPair p : this.pairs) {
            p.transfer();
         }

         this.to.valueCount = this.from.valueCount;
         this.from.clear();
      }

      public ValueVector getTo() {
         return this.to;
      }

      public void copyValueSafe(int from, int to) {
         for(TransferPair p : this.pairs) {
            p.copyValueSafe(from, to);
         }

      }

      public void splitAndTransfer(int startIndex, int length) {
         for(TransferPair p : this.pairs) {
            p.splitAndTransfer(startIndex, length);
         }

         this.to.setValueCount(length);
      }
   }
}
