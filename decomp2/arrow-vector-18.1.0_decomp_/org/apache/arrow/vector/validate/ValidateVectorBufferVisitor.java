package org.apache.arrow.vector.validate;

import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.BaseFixedWidthVector;
import org.apache.arrow.vector.BaseIntVector;
import org.apache.arrow.vector.BaseLargeVariableWidthVector;
import org.apache.arrow.vector.BaseVariableWidthVector;
import org.apache.arrow.vector.BaseVariableWidthViewVector;
import org.apache.arrow.vector.BitVector;
import org.apache.arrow.vector.ExtensionTypeVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.NullVector;
import org.apache.arrow.vector.TypeLayout;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.compare.VectorVisitor;
import org.apache.arrow.vector.complex.DenseUnionVector;
import org.apache.arrow.vector.complex.FixedSizeListVector;
import org.apache.arrow.vector.complex.LargeListVector;
import org.apache.arrow.vector.complex.ListVector;
import org.apache.arrow.vector.complex.NonNullableStructVector;
import org.apache.arrow.vector.complex.RunEndEncodedVector;
import org.apache.arrow.vector.complex.UnionVector;
import org.apache.arrow.vector.types.pojo.ArrowType;

public class ValidateVectorBufferVisitor implements VectorVisitor {
   private void validateVectorCommon(ValueVector vector) {
      ArrowType arrowType = vector.getField().getType();
      ValidateUtil.validateOrThrow(vector.getValueCount() >= 0, "Vector valueCount %s is negative.", vector.getValueCapacity());
      if (vector instanceof FieldVector) {
         FieldVector fieldVector = (FieldVector)vector;
         int typeBufferCount = TypeLayout.getTypeBufferCount(arrowType);
         ValidateUtil.validateOrThrow(fieldVector.getFieldBuffers().size() == typeBufferCount, "Expected %s buffers in vector of type %s, got %s.", typeBufferCount, vector.getField().getType().toString(), fieldVector.getFieldBuffers().size());
      }

   }

   private void validateValidityBuffer(ValueVector vector, int valueCount) {
      ArrowBuf validityBuffer = vector.getValidityBuffer();
      ValidateUtil.validateOrThrow(validityBuffer != null, "The validity buffer is null.");
      ValidateUtil.validateOrThrow(validityBuffer.capacity() * 8L >= (long)valueCount, "Not enough capacity for the validity buffer. Minimum capacity %s, actual capacity %s.", (valueCount + 7) / 8, validityBuffer.capacity());
   }

   private void validateOffsetBuffer(ValueVector vector, long minCapacity) {
      ArrowBuf offsetBuffer = vector.getOffsetBuffer();
      ValidateUtil.validateOrThrow(offsetBuffer != null, "The offset buffer is null.");
      ValidateUtil.validateOrThrow(offsetBuffer.capacity() >= minCapacity, "Not enough capacity for the offset buffer. Minimum capacity %s, actual capacity %s.", minCapacity, offsetBuffer.capacity());
   }

   private void validateFixedWidthDataBuffer(ValueVector vector, int valueCount, int bitWidth) {
      ArrowBuf dataBuffer = vector.getDataBuffer();
      ValidateUtil.validateOrThrow(dataBuffer != null, "The fixed width data buffer is null.");
      ValidateUtil.validateOrThrow((long)bitWidth * (long)valueCount <= dataBuffer.capacity() * 8L, "Not enough capacity for fixed width data buffer. Minimum capacity %s, actual capacity %s.", ((long)bitWidth * (long)valueCount + 7L) / 8L, dataBuffer.capacity());
   }

   private void validateDataBuffer(ValueVector vector, long minCapacity) {
      ArrowBuf dataBuffer = vector.getDataBuffer();
      ValidateUtil.validateOrThrow(dataBuffer != null, "The data buffer is null.");
      ValidateUtil.validateOrThrow(dataBuffer.capacity() >= minCapacity, "Not enough capacity for data buffer. Minimum capacity %s, actual capacity %s.", minCapacity, dataBuffer.capacity());
   }

   private void validateTypeBuffer(ArrowBuf typeBuf, long minCapacity) {
      ValidateUtil.validateOrThrow(typeBuf != null, "The type buffer is null.");
      ValidateUtil.validateOrThrow(typeBuf.capacity() >= minCapacity, "Not enough capacity for type buffer. Minimum capacity %s, actual capacity %s.", minCapacity, typeBuf.capacity());
   }

   public Void visit(BaseFixedWidthVector vector, Void value) {
      int bitWidth = vector instanceof BitVector ? 1 : vector.getTypeWidth() * 8;
      int valueCount = vector.getValueCount();
      this.validateVectorCommon(vector);
      this.validateValidityBuffer(vector, valueCount);
      this.validateFixedWidthDataBuffer(vector, valueCount, bitWidth);
      return null;
   }

   public Void visit(BaseVariableWidthVector vector, Void value) {
      int valueCount = vector.getValueCount();
      this.validateVectorCommon(vector);
      this.validateValidityBuffer(vector, valueCount);
      long minOffsetCapacity = valueCount == 0 ? 0L : (long)(valueCount + 1) * 4L;
      this.validateOffsetBuffer(vector, minOffsetCapacity);
      int lastOffset = valueCount == 0 ? 0 : vector.getOffsetBuffer().getInt((long)(valueCount * 4));
      this.validateDataBuffer(vector, (long)lastOffset);
      return null;
   }

   public Void visit(BaseLargeVariableWidthVector vector, Void value) {
      int valueCount = vector.getValueCount();
      this.validateVectorCommon(vector);
      this.validateValidityBuffer(vector, valueCount);
      long minOffsetCapacity = valueCount == 0 ? 0L : (long)(valueCount + 1) * 8L;
      this.validateOffsetBuffer(vector, minOffsetCapacity);
      long lastOffset = valueCount == 0 ? 0L : vector.getOffsetBuffer().getLong((long)valueCount * 8L);
      this.validateDataBuffer(vector, lastOffset);
      return null;
   }

   public Void visit(BaseVariableWidthViewVector vector, Void value) {
      throw new UnsupportedOperationException("View vectors are not supported.");
   }

   public Void visit(ListVector vector, Void value) {
      int valueCount = vector.getValueCount();
      this.validateVectorCommon(vector);
      this.validateValidityBuffer(vector, valueCount);
      long minOffsetCapacity = valueCount == 0 ? 0L : (long)(valueCount + 1) * 4L;
      this.validateOffsetBuffer(vector, minOffsetCapacity);
      FieldVector dataVector = vector.getDataVector();
      int lastOffset = valueCount == 0 ? 0 : vector.getOffsetBuffer().getInt((long)(valueCount * 4));
      int dataVectorLength = dataVector == null ? 0 : dataVector.getValueCount();
      ValidateUtil.validateOrThrow(dataVectorLength >= lastOffset, "Inner vector does not contain enough elements. Minimum element count %s, actual element count %s", lastOffset + 1, dataVectorLength);
      if (dataVector != null) {
         dataVector.accept(this, (Object)null);
      }

      return null;
   }

   public Void visit(FixedSizeListVector vector, Void value) {
      int valueCount = vector.getValueCount();
      this.validateVectorCommon(vector);
      this.validateValidityBuffer(vector, valueCount);
      FieldVector dataVector = vector.getDataVector();
      int dataVectorLength = dataVector == null ? 0 : dataVector.getValueCount();
      ValidateUtil.validateOrThrow(dataVectorLength >= valueCount * vector.getListSize(), "Inner vector does not contain enough elements. Minimum element count %s, actual element count %s.", valueCount * vector.getListSize(), dataVectorLength);
      if (dataVector != null) {
         dataVector.accept(this, (Object)null);
      }

      return null;
   }

   public Void visit(LargeListVector vector, Void value) {
      int valueCount = vector.getValueCount();
      this.validateVectorCommon(vector);
      this.validateValidityBuffer(vector, valueCount);
      long minOffsetCapacity = valueCount == 0 ? 0L : (long)(valueCount + 1) * 8L;
      this.validateOffsetBuffer(vector, minOffsetCapacity);
      FieldVector dataVector = vector.getDataVector();
      long lastOffset = valueCount == 0 ? 0L : vector.getOffsetBuffer().getLong((long)(valueCount * 8));
      int dataVectorLength = dataVector == null ? 0 : dataVector.getValueCount();
      ValidateUtil.validateOrThrow((long)dataVectorLength >= lastOffset, "Inner vector does not contain enough elements. Minimum element count %s, actual element count %s", lastOffset + 1L, dataVectorLength);
      if (dataVector != null) {
         dataVector.accept(this, (Object)null);
      }

      return null;
   }

   public Void visit(NonNullableStructVector vector, Void value) {
      int valueCount = vector.getValueCount();
      this.validateVectorCommon(vector);
      this.validateValidityBuffer(vector, valueCount);

      for(ValueVector subVector : vector.getChildrenFromFields()) {
         ValidateUtil.validateOrThrow(valueCount == subVector.getValueCount(), "Struct vector length not equal to child vector length. Struct vector length %s, child vector length %s", valueCount, subVector.getValueCount());
         subVector.accept(this, (Object)null);
      }

      return null;
   }

   public Void visit(UnionVector vector, Void value) {
      int valueCount = vector.getValueCount();
      this.validateVectorCommon(vector);
      this.validateTypeBuffer(vector.getTypeBuffer(), (long)(valueCount * 1));

      for(ValueVector subVector : vector.getChildrenFromFields()) {
         ValidateUtil.validateOrThrow(valueCount == subVector.getValueCount(), "Union vector length not equal to child vector length. Union vector length %s, child vector length %s", valueCount, subVector.getValueCount());
         subVector.accept(this, (Object)null);
      }

      return null;
   }

   public Void visit(DenseUnionVector vector, Void value) {
      int valueCount = vector.getValueCount();
      this.validateVectorCommon(vector);
      this.validateOffsetBuffer(vector, (long)valueCount * 4L);
      this.validateTypeBuffer(vector.getTypeBuffer(), (long)(valueCount * 1));

      for(ValueVector subVector : vector.getChildrenFromFields()) {
         subVector.accept(this, (Object)null);
      }

      return null;
   }

   public Void visit(NullVector vector, Void value) {
      return null;
   }

   public Void visit(ExtensionTypeVector vector, Void value) {
      vector.getUnderlyingVector().accept(this, value);
      return null;
   }

   public Void visit(RunEndEncodedVector vector, Void value) {
      this.validateVectorCommon(vector);
      int valueCount = vector.getValueCount();
      FieldVector runEndsVector = vector.getRunEndsVector();
      if (runEndsVector != null) {
         ValidateUtil.validateOrThrow(runEndsVector.getNullCount() == 0, "Run ends vector cannot contain null values");
         runEndsVector.accept(this, (Object)null);
         int runCount = runEndsVector.getValueCount();
         if (runCount == 0) {
            ValidateUtil.validateOrThrow(valueCount == 0, "Run end vector does not contain enough elements");
         } else if (runCount > 0) {
            double lastEnd = (double)((BaseIntVector)runEndsVector).getValueAsLong(runCount - 1);
            ValidateUtil.validateOrThrow((double)valueCount == lastEnd, "Vector logic length not equal to the last end in run ends vector. Logical length %s, last end %s", valueCount, lastEnd);
         }
      }

      FieldVector valuesVector = vector.getValuesVector();
      if (valuesVector != null) {
         valuesVector.accept(this, (Object)null);
      }

      return null;
   }
}
