package org.apache.arrow.vector.validate;

import java.util.List;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.vector.BaseFixedWidthVector;
import org.apache.arrow.vector.BaseLargeVariableWidthVector;
import org.apache.arrow.vector.BaseVariableWidthVector;
import org.apache.arrow.vector.BaseVariableWidthViewVector;
import org.apache.arrow.vector.ExtensionTypeVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.NullVector;
import org.apache.arrow.vector.compare.VectorVisitor;
import org.apache.arrow.vector.complex.DenseUnionVector;
import org.apache.arrow.vector.complex.FixedSizeListVector;
import org.apache.arrow.vector.complex.LargeListVector;
import org.apache.arrow.vector.complex.ListVector;
import org.apache.arrow.vector.complex.NonNullableStructVector;
import org.apache.arrow.vector.complex.UnionVector;
import org.apache.arrow.vector.types.pojo.Field;

public class ValidateVectorVisitor implements VectorVisitor {
   public Void visit(BaseFixedWidthVector vector, Void value) {
      if (vector.getValueCount() <= 0 || vector.getDataBuffer() != null && vector.getDataBuffer().capacity() != 0L) {
         return null;
      } else {
         throw new IllegalArgumentException("valueBuffer is null or capacity is 0");
      }
   }

   public Void visit(BaseVariableWidthVector vector, Void value) {
      if (vector.getValueCount() > 0) {
         if (vector.getDataBuffer() == null || vector.getDataBuffer().capacity() == 0L) {
            throw new IllegalArgumentException("valueBuffer is null or capacity is 0");
         }

         ArrowBuf offsetBuf = vector.getOffsetBuffer();
         int minBufferSize = (vector.getValueCount() + 1) * 4;
         if (offsetBuf.capacity() < (long)minBufferSize) {
            throw new IllegalArgumentException(String.format("offsetBuffer too small in vector of type %s and valueCount %s : expected at least %s byte(s), got %s", vector.getField().getType().toString(), vector.getValueCount(), minBufferSize, offsetBuf.capacity()));
         }

         int firstOffset = vector.getOffsetBuffer().getInt(0L);
         int lastOffset = vector.getOffsetBuffer().getInt((long)(vector.getValueCount() * 4));
         if (firstOffset < 0 || lastOffset < 0) {
            throw new IllegalArgumentException("Negative offsets in vector");
         }

         int dataExtent = lastOffset - firstOffset;
         if (dataExtent > 0 && vector.getDataBuffer().capacity() == 0L) {
            throw new IllegalArgumentException("dataBuffer capacity is 0");
         }

         if ((long)dataExtent > vector.getDataBuffer().capacity()) {
            throw new IllegalArgumentException(String.format("Length spanned by offsets %s larger than dataBuffer capacity %s", dataExtent, vector.getValueCount()));
         }
      }

      return null;
   }

   public Void visit(BaseLargeVariableWidthVector left, Void value) {
      return null;
   }

   public Void visit(BaseVariableWidthViewVector left, Void value) {
      throw new UnsupportedOperationException("View vectors are not supported.");
   }

   public Void visit(ListVector vector, Void value) {
      FieldVector dataVector = vector.getDataVector();
      if (vector.getValueCount() > 0) {
         ArrowBuf offsetBuf = vector.getOffsetBuffer();
         int minBufferSize = (vector.getValueCount() + 1) * 4;
         if (offsetBuf.capacity() < (long)minBufferSize) {
            throw new IllegalArgumentException(String.format("offsetBuffer too small in vector of type %s and valueCount %s : expected at least %s byte(s), got %s", vector.getField().getType().toString(), vector.getValueCount(), minBufferSize, offsetBuf.capacity()));
         }

         int firstOffset = vector.getOffsetBuffer().getInt(0L);
         int lastOffset = vector.getOffsetBuffer().getInt((long)(vector.getValueCount() * 4));
         if (firstOffset < 0 || lastOffset < 0) {
            throw new IllegalArgumentException("Negative offsets in list vector");
         }

         int dataExtent = lastOffset - firstOffset;
         if (dataExtent > 0 && (dataVector.getDataBuffer() == null || dataVector.getDataBuffer().capacity() == 0L)) {
            throw new IllegalArgumentException("valueBuffer is null or capacity is 0");
         }

         if (dataExtent > dataVector.getValueCount()) {
            throw new IllegalArgumentException(String.format("Length spanned by list offsets (%s) larger than data vector valueCount (length %s)", dataExtent, dataVector.getValueCount()));
         }
      }

      return (Void)dataVector.accept(this, (Object)null);
   }

   public Void visit(LargeListVector vector, Void value) {
      FieldVector dataVector = vector.getDataVector();
      if (vector.getValueCount() > 0) {
         ArrowBuf offsetBuf = vector.getOffsetBuffer();
         long minBufferSize = (long)((vector.getValueCount() + 1) * 8);
         if (offsetBuf.capacity() < minBufferSize) {
            throw new IllegalArgumentException(String.format("offsetBuffer too small in vector of type %s and valueCount %s : expected at least %s byte(s), got %s", vector.getField().getType().toString(), vector.getValueCount(), minBufferSize, offsetBuf.capacity()));
         }

         long firstOffset = vector.getOffsetBuffer().getLong(0L);
         long lastOffset = vector.getOffsetBuffer().getLong((long)(vector.getValueCount() * 8));
         if (firstOffset < 0L || lastOffset < 0L) {
            throw new IllegalArgumentException("Negative offsets in list vector");
         }

         long dataExtent = lastOffset - firstOffset;
         if (dataExtent > 0L && (dataVector.getDataBuffer() == null || dataVector.getDataBuffer().capacity() == 0L)) {
            throw new IllegalArgumentException("valueBuffer is null or capacity is 0");
         }

         if (dataExtent > (long)dataVector.getValueCount()) {
            throw new IllegalArgumentException(String.format("Length spanned by list offsets (%s) larger than data vector valueCount (length %s)", dataExtent, dataVector.getValueCount()));
         }
      }

      return (Void)dataVector.accept(this, (Object)null);
   }

   public Void visit(FixedSizeListVector vector, Void value) {
      FieldVector dataVector = vector.getDataVector();
      int valueCount = vector.getValueCount();
      int listSize = vector.getListSize();
      if (valueCount <= 0 || dataVector.getDataBuffer() != null && dataVector.getDataBuffer().capacity() != 0L) {
         if (valueCount * listSize != dataVector.getValueCount()) {
            throw new IllegalArgumentException(String.format("data vector valueCount invalid, expect %s, actual is: %s", valueCount * listSize, dataVector.getValueCount()));
         } else {
            return null;
         }
      } else {
         throw new IllegalArgumentException("valueBuffer is null or capacity is 0");
      }
   }

   public Void visit(NonNullableStructVector vector, Void value) {
      List<Field> childFields = vector.getField().getChildren();
      int valueCount = vector.getValueCount();

      for(int i = 0; i < childFields.size(); ++i) {
         FieldVector child = (FieldVector)vector.getChildrenFromFields().get(i);
         if (child.getValueCount() != valueCount) {
            throw new IllegalArgumentException(String.format("struct child vector #%s valueCount is not equals with struct vector, expect %s, actual %s", i, vector.getValueCount(), child.getValueCount()));
         }

         if (!((Field)childFields.get(i)).getType().equals(child.getField().getType())) {
            throw new IllegalArgumentException(String.format("struct child vector #%s does not match type: %s vs %s", i, ((Field)childFields.get(i)).getType().toString(), child.getField().getType().toString()));
         }

         child.accept(this, (Object)null);
      }

      return null;
   }

   public Void visit(UnionVector vector, Void value) {
      List<Field> childFields = vector.getField().getChildren();
      int valueCount = vector.getValueCount();

      for(int i = 0; i < childFields.size(); ++i) {
         FieldVector child = (FieldVector)vector.getChildrenFromFields().get(i);
         if (child.getValueCount() != valueCount) {
            throw new IllegalArgumentException(String.format("union child vector #%s valueCount is not equals with union vector, expect %s, actual %s", i, vector.getValueCount(), child.getValueCount()));
         }

         if (!((Field)childFields.get(i)).getType().equals(child.getField().getType())) {
            throw new IllegalArgumentException(String.format("union child vector #%s does not match type: %s vs %s", i, ((Field)childFields.get(i)).getType().toString(), child.getField().getType().toString()));
         }

         child.accept(this, (Object)null);
      }

      return null;
   }

   public Void visit(DenseUnionVector vector, Void value) {
      List<Field> childFields = vector.getField().getChildren();

      for(int i = 0; i < childFields.size(); ++i) {
         FieldVector child = (FieldVector)vector.getChildrenFromFields().get(i);
         if (!((Field)childFields.get(i)).getType().equals(child.getField().getType())) {
            throw new IllegalArgumentException(String.format("union child vector #%s does not match type: %s vs %s", i, ((Field)childFields.get(i)).getType().toString(), child.getField().getType().toString()));
         }

         child.accept(this, (Object)null);
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
}
