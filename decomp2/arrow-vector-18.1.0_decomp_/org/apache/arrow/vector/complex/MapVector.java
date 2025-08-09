package org.apache.arrow.vector.complex;

import java.util.List;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.AddOrGetResult;
import org.apache.arrow.vector.BaseValueVector;
import org.apache.arrow.vector.BitVectorHelper;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.ZeroVector;
import org.apache.arrow.vector.complex.impl.UnionMapReader;
import org.apache.arrow.vector.complex.impl.UnionMapWriter;
import org.apache.arrow.vector.types.Types;
import org.apache.arrow.vector.types.pojo.ArrowType;
import org.apache.arrow.vector.types.pojo.Field;
import org.apache.arrow.vector.types.pojo.FieldType;
import org.apache.arrow.vector.util.CallBack;
import org.apache.arrow.vector.util.TransferPair;

public class MapVector extends ListVector {
   public static final String KEY_NAME = "key";
   public static final String VALUE_NAME = "value";
   public static final String DATA_VECTOR_NAME = "entries";

   public static MapVector empty(String name, BufferAllocator allocator, boolean keysSorted) {
      return new MapVector(name, allocator, FieldType.nullable(new ArrowType.Map(keysSorted)), (CallBack)null);
   }

   public MapVector(String name, BufferAllocator allocator, FieldType fieldType, CallBack callBack) {
      super(name, allocator, fieldType, callBack);
      this.defaultDataVectorName = "entries";
   }

   public MapVector(Field field, BufferAllocator allocator, CallBack callBack) {
      super(field, allocator, callBack);
      this.defaultDataVectorName = "entries";
   }

   public void initializeChildrenFromFields(List children) {
      Preconditions.checkArgument(children.size() == 1, "Maps have one List child. Found: %s", children.isEmpty() ? "none" : children);
      Field structField = (Field)children.get(0);
      Types.MinorType minorType = Types.getMinorTypeForArrowType(structField.getType());
      Preconditions.checkArgument(minorType == Types.MinorType.STRUCT && !structField.isNullable(), "Map data should be a non-nullable struct type");
      Preconditions.checkArgument(structField.getChildren().size() == 2, "Map data should be a struct with 2 children. Found: %s", children);
      Field keyField = (Field)structField.getChildren().get(0);
      Preconditions.checkArgument(!keyField.isNullable(), "Map data key type should be a non-nullable");
      AddOrGetResult<FieldVector> addOrGetVector = this.addOrGetVector(structField.getFieldType());
      Preconditions.checkArgument(addOrGetVector.isCreated(), "Child vector already existed: %s", addOrGetVector.getVector());
      ((FieldVector)addOrGetVector.getVector()).initializeChildrenFromFields(structField.getChildren());
      this.field = new Field(this.field.getName(), this.field.getFieldType(), children);
   }

   public UnionMapWriter getWriter() {
      return new UnionMapWriter(this);
   }

   public UnionMapReader getReader() {
      if (this.reader == null) {
         this.reader = new UnionMapReader(this);
      }

      return (UnionMapReader)this.reader;
   }

   public Types.MinorType getMinorType() {
      return Types.MinorType.MAP;
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator) {
      return this.getTransferPair((String)ref, allocator, (CallBack)null);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator) {
      return new TransferImpl(field, allocator, (CallBack)null);
   }

   public TransferPair getTransferPair(String ref, BufferAllocator allocator, CallBack callBack) {
      return new TransferImpl(ref, allocator, callBack);
   }

   public TransferPair getTransferPair(Field field, BufferAllocator allocator, CallBack callBack) {
      return new TransferImpl(field, allocator, callBack);
   }

   public TransferPair makeTransferPair(ValueVector target) {
      return new TransferImpl((MapVector)target);
   }

   private class TransferImpl implements TransferPair {
      MapVector to;
      TransferPair dataTransferPair;

      public TransferImpl(String name, BufferAllocator allocator, CallBack callBack) {
         this(new MapVector(name, allocator, MapVector.this.field.getFieldType(), callBack));
      }

      public TransferImpl(Field field, BufferAllocator allocator, CallBack callBack) {
         this(new MapVector(field, allocator, callBack));
      }

      public TransferImpl(MapVector to) {
         this.to = to;
         to.addOrGetVector(MapVector.this.vector.getField().getFieldType());
         if (to.getDataVector() instanceof ZeroVector) {
            to.addOrGetVector(MapVector.this.vector.getField().getFieldType());
         }

         this.dataTransferPair = MapVector.this.getDataVector().makeTransferPair(to.getDataVector());
      }

      public void transfer() {
         this.to.clear();
         this.dataTransferPair.transfer();
         this.to.validityBuffer = BaseValueVector.transferBuffer(MapVector.this.validityBuffer, this.to.allocator);
         this.to.offsetBuffer = BaseValueVector.transferBuffer(MapVector.this.offsetBuffer, this.to.allocator);
         this.to.lastSet = MapVector.this.lastSet;
         if (MapVector.this.valueCount > 0) {
            this.to.setValueCount(MapVector.this.valueCount);
         }

         MapVector.this.clear();
      }

      public void splitAndTransfer(int startIndex, int length) {
         Preconditions.checkArgument(startIndex >= 0 && length >= 0 && startIndex + length <= MapVector.this.valueCount, "Invalid parameters startIndex: %s, length: %s for valueCount: %s", startIndex, length, MapVector.this.valueCount);
         int startPoint = MapVector.this.offsetBuffer.getInt((long)(startIndex * 4));
         int sliceLength = MapVector.this.offsetBuffer.getInt((long)((startIndex + length) * 4)) - startPoint;
         this.to.clear();
         this.to.offsetBuffer = this.to.allocateOffsetBuffer((long)((length + 1) * 4));

         for(int i = 0; i < length + 1; ++i) {
            int relativeOffset = MapVector.this.offsetBuffer.getInt((long)((startIndex + i) * 4)) - startPoint;
            this.to.offsetBuffer.setInt((long)(i * 4), relativeOffset);
         }

         this.splitAndTransferValidityBuffer(startIndex, length, this.to);
         this.dataTransferPair.splitAndTransfer(startPoint, sliceLength);
         this.to.lastSet = length - 1;
         this.to.setValueCount(length);
      }

      private void splitAndTransferValidityBuffer(int startIndex, int length, MapVector target) {
         int firstByteSource = BitVectorHelper.byteIndex(startIndex);
         int lastByteSource = BitVectorHelper.byteIndex(MapVector.this.valueCount - 1);
         int byteSizeTarget = MapVector.getValidityBufferSizeFromCount(length);
         int offset = startIndex % 8;
         if (length > 0) {
            if (offset == 0) {
               if (target.validityBuffer != null) {
                  target.validityBuffer.getReferenceManager().release();
               }

               target.validityBuffer = MapVector.this.validityBuffer.slice((long)firstByteSource, (long)byteSizeTarget);
               target.validityBuffer.getReferenceManager().retain(1);
            } else {
               target.allocateValidityBuffer((long)byteSizeTarget);

               for(int i = 0; i < byteSizeTarget - 1; ++i) {
                  byte b1 = BitVectorHelper.getBitsFromCurrentByte(MapVector.this.validityBuffer, firstByteSource + i, offset);
                  byte b2 = BitVectorHelper.getBitsFromNextByte(MapVector.this.validityBuffer, firstByteSource + i + 1, offset);
                  target.validityBuffer.setByte((long)i, b1 + b2);
               }

               if (firstByteSource + byteSizeTarget - 1 < lastByteSource) {
                  byte b1 = BitVectorHelper.getBitsFromCurrentByte(MapVector.this.validityBuffer, firstByteSource + byteSizeTarget - 1, offset);
                  byte b2 = BitVectorHelper.getBitsFromNextByte(MapVector.this.validityBuffer, firstByteSource + byteSizeTarget, offset);
                  target.validityBuffer.setByte((long)(byteSizeTarget - 1), b1 + b2);
               } else {
                  byte b1 = BitVectorHelper.getBitsFromCurrentByte(MapVector.this.validityBuffer, firstByteSource + byteSizeTarget - 1, offset);
                  target.validityBuffer.setByte((long)(byteSizeTarget - 1), b1);
               }
            }
         }

      }

      public ValueVector getTo() {
         return this.to;
      }

      public void copyValueSafe(int from, int to) {
         this.to.copyFrom(from, to, MapVector.this);
      }
   }
}
