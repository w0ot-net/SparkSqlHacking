package org.apache.arrow.vector.util;

import java.util.function.BiFunction;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.BaseFixedWidthVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.VectorSchemaRoot;
import org.apache.arrow.vector.validate.ValidateUtil;
import org.apache.arrow.vector.validate.ValidateVectorBufferVisitor;
import org.apache.arrow.vector.validate.ValidateVectorDataVisitor;
import org.apache.arrow.vector.validate.ValidateVectorTypeVisitor;

public class ValueVectorUtility {
   private ValueVectorUtility() {
   }

   public static String getToString(ValueVector vector, int start, int end) {
      return getToString(vector, start, end, (v, i) -> v.getObject(i));
   }

   public static String getToString(ValueVector vector, int start, int end, BiFunction valueToString) {
      Preconditions.checkNotNull(vector);
      int length = end - start;
      Preconditions.checkArgument(length >= 0);
      Preconditions.checkArgument(start >= 0);
      Preconditions.checkArgument(end <= vector.getValueCount());
      if (length == 0) {
         return "[]";
      } else {
         int window = 10;
         boolean skipComma = false;
         StringBuilder sb = new StringBuilder();
         sb.append('[');

         for(int i = start; i < end; ++i) {
            if (skipComma) {
               skipComma = false;
            }

            if (i - start >= 10 && i < end - 10) {
               sb.append("...");
               i = end - 10 - 1;
               skipComma = true;
            } else {
               sb.append(valueToString.apply(vector, i));
            }

            if (i == end - 1) {
               sb.append(']');
            } else {
               if (!skipComma) {
                  sb.append(',');
               }

               sb.append(' ');
            }
         }

         return sb.toString();
      }
   }

   public static void validate(ValueVector vector) {
      Preconditions.checkNotNull(vector);
      ValidateVectorTypeVisitor typeVisitor = new ValidateVectorTypeVisitor();
      vector.accept(typeVisitor, (Object)null);
      ValidateVectorBufferVisitor bufferVisitor = new ValidateVectorBufferVisitor();
      vector.accept(bufferVisitor, (Object)null);
   }

   public static void validateFull(ValueVector vector) {
      validate(vector);
      ValidateVectorDataVisitor dataVisitor = new ValidateVectorDataVisitor();
      vector.accept(dataVisitor, (Object)null);
   }

   public static void validate(VectorSchemaRoot root) {
      Preconditions.checkNotNull(root);
      int valueCount = root.getRowCount();
      ValidateUtil.validateOrThrow(valueCount >= 0, "The row count of vector schema root %s is negative.", valueCount);

      for(ValueVector childVec : root.getFieldVectors()) {
         ValidateUtil.validateOrThrow(valueCount == childVec.getValueCount(), "Child vector and vector schema root have different value counts. Child vector value count %s, vector schema root value count %s", childVec.getValueCount(), valueCount);
         validate(childVec);
      }

   }

   public static void validateFull(VectorSchemaRoot root) {
      Preconditions.checkNotNull(root);
      int valueCount = root.getRowCount();
      ValidateUtil.validateOrThrow(valueCount >= 0, "The row count of vector schema root %s is negative.", valueCount);

      for(ValueVector childVec : root.getFieldVectors()) {
         ValidateUtil.validateOrThrow(valueCount == childVec.getValueCount(), "Child vector and vector schema root have different value counts. Child vector value count %s, vector schema root value count %s", childVec.getValueCount(), valueCount);
         validateFull(childVec);
      }

   }

   public static void preAllocate(VectorSchemaRoot root, int targetSize) {
      for(ValueVector vector : root.getFieldVectors()) {
         if (vector instanceof BaseFixedWidthVector) {
            ((BaseFixedWidthVector)vector).allocateNew(targetSize);
         }
      }

   }

   public static void ensureCapacity(VectorSchemaRoot root, int targetCapacity) {
      for(ValueVector vector : root.getFieldVectors()) {
         if (vector instanceof BaseFixedWidthVector) {
            while(vector.getValueCapacity() < targetCapacity) {
               vector.reAlloc();
            }
         }
      }

   }
}
