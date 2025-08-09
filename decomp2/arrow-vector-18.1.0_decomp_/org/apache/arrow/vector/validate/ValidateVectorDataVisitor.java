package org.apache.arrow.vector.validate;

import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.BaseFixedWidthVector;
import org.apache.arrow.vector.BaseLargeVariableWidthVector;
import org.apache.arrow.vector.BaseVariableWidthVector;
import org.apache.arrow.vector.BaseVariableWidthViewVector;
import org.apache.arrow.vector.ExtensionTypeVector;
import org.apache.arrow.vector.NullVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.compare.VectorVisitor;
import org.apache.arrow.vector.complex.DenseUnionVector;
import org.apache.arrow.vector.complex.FixedSizeListVector;
import org.apache.arrow.vector.complex.LargeListVector;
import org.apache.arrow.vector.complex.ListVector;
import org.apache.arrow.vector.complex.NonNullableStructVector;
import org.apache.arrow.vector.complex.UnionVector;

public class ValidateVectorDataVisitor implements VectorVisitor {
   private void validateOffsetBuffer(ValueVector vector, int valueCount) {
      if (valueCount != 0) {
         ArrowBuf offsetBuffer = vector.getOffsetBuffer();
         int prevValue = offsetBuffer.getInt(0L);

         for(int i = 1; i <= valueCount; ++i) {
            int curValue = offsetBuffer.getInt((long)(i * 4));
            ValidateUtil.validateOrThrow(curValue >= 0, "The value at position %s of the offset buffer is negative: %s.", i, curValue);
            ValidateUtil.validateOrThrow(curValue >= prevValue, "The values in positions %s and %s of the offset buffer are decreasing: %s, %s.", i - 1, i, prevValue, curValue);
            prevValue = curValue;
         }

      }
   }

   private void validateLargeOffsetBuffer(ValueVector vector, int valueCount) {
      if (valueCount != 0) {
         ArrowBuf offsetBuffer = vector.getOffsetBuffer();
         long prevValue = offsetBuffer.getLong(0L);

         for(int i = 1; i <= valueCount; ++i) {
            long curValue = offsetBuffer.getLong((long)i * 8L);
            ValidateUtil.validateOrThrow(curValue >= 0L, "The value at position %s of the large offset buffer is negative: %s.", i, curValue);
            ValidateUtil.validateOrThrow(curValue >= prevValue, "The values in positions %s and %s of the large offset buffer are decreasing: %s, %s.", i - 1, i, prevValue, curValue);
            prevValue = curValue;
         }

      }
   }

   private void validateTypeBuffer(ArrowBuf typeBuf, int valueCount) {
      for(int i = 0; i < valueCount; ++i) {
         ValidateUtil.validateOrThrow(typeBuf.getByte((long)i) >= 0, "The type id at position %s is negative: %s.", i, typeBuf.getByte((long)i));
      }

   }

   public Void visit(BaseFixedWidthVector vector, Void value) {
      vector.validateScalars();
      return null;
   }

   public Void visit(BaseVariableWidthVector vector, Void value) {
      this.validateOffsetBuffer(vector, vector.getValueCount());
      vector.validateScalars();
      return null;
   }

   public Void visit(BaseLargeVariableWidthVector vector, Void value) {
      this.validateLargeOffsetBuffer(vector, vector.getValueCount());
      vector.validateScalars();
      return null;
   }

   public Void visit(BaseVariableWidthViewVector vector, Void value) {
      throw new UnsupportedOperationException("View vectors are not supported.");
   }

   public Void visit(ListVector vector, Void value) {
      this.validateOffsetBuffer(vector, vector.getValueCount());
      ValueVector innerVector = vector.getDataVector();
      if (innerVector != null) {
         innerVector.accept(this, (Object)null);
      }

      return null;
   }

   public Void visit(FixedSizeListVector vector, Void value) {
      this.validateOffsetBuffer(vector, vector.getValueCount());
      ValueVector innerVector = vector.getDataVector();
      if (innerVector != null) {
         innerVector.accept(this, (Object)null);
      }

      return null;
   }

   public Void visit(LargeListVector vector, Void value) {
      this.validateLargeOffsetBuffer(vector, vector.getValueCount());
      ValueVector innerVector = vector.getDataVector();
      if (innerVector != null) {
         innerVector.accept(this, (Object)null);
      }

      return null;
   }

   public Void visit(NonNullableStructVector vector, Void value) {
      for(ValueVector subVector : vector.getChildrenFromFields()) {
         subVector.accept(this, (Object)null);
      }

      return null;
   }

   public Void visit(UnionVector vector, Void value) {
      this.validateTypeBuffer(vector.getTypeBuffer(), vector.getValueCount());

      for(ValueVector subVector : vector.getChildrenFromFields()) {
         subVector.accept(this, (Object)null);
      }

      return null;
   }

   public Void visit(DenseUnionVector vector, Void value) {
      this.validateTypeBuffer(vector.getTypeBuffer(), vector.getValueCount());

      for(int i = 0; i < vector.getValueCount(); ++i) {
         int offset = vector.getOffset(i);
         byte typeId = vector.getTypeId(i);
         ValueVector subVector = vector.getVectorByType(typeId);
         ValidateUtil.validateOrThrow(offset < subVector.getValueCount(), "Dense union vector offset exceeds sub-vector boundary. Vector offset %s, sub vector size %s", offset, subVector.getValueCount());
      }

      for(ValueVector subVector : vector.getChildrenFromFields()) {
         subVector.accept(this, (Object)null);
      }

      return null;
   }

   public Void visit(NullVector vector, Void value) {
      ValidateUtil.validateOrThrow(vector.getNullCount() == vector.getValueCount(), "NullVector should have only null entries.");
      return null;
   }

   public Void visit(ExtensionTypeVector vector, Void value) {
      vector.getUnderlyingVector().accept(this, value);
      return null;
   }
}
