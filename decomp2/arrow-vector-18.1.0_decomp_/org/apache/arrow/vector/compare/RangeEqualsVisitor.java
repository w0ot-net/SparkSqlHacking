package org.apache.arrow.vector.compare;

import java.util.List;
import java.util.function.BiFunction;
import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.util.ByteFunctionHelpers;
import org.apache.arrow.memory.util.LargeMemoryUtil;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.BaseFixedWidthVector;
import org.apache.arrow.vector.BaseLargeVariableWidthVector;
import org.apache.arrow.vector.BaseVariableWidthVector;
import org.apache.arrow.vector.BaseVariableWidthViewVector;
import org.apache.arrow.vector.BitVector;
import org.apache.arrow.vector.ExtensionTypeVector;
import org.apache.arrow.vector.FieldVector;
import org.apache.arrow.vector.NullVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.complex.DenseUnionVector;
import org.apache.arrow.vector.complex.FixedSizeListVector;
import org.apache.arrow.vector.complex.LargeListVector;
import org.apache.arrow.vector.complex.LargeListViewVector;
import org.apache.arrow.vector.complex.ListVector;
import org.apache.arrow.vector.complex.ListViewVector;
import org.apache.arrow.vector.complex.NonNullableStructVector;
import org.apache.arrow.vector.complex.RunEndEncodedVector;
import org.apache.arrow.vector.complex.StructVector;
import org.apache.arrow.vector.complex.UnionVector;

public class RangeEqualsVisitor implements VectorVisitor {
   private ValueVector left;
   private ValueVector right;
   private BiFunction typeComparator;
   private boolean typeCompareResult;
   public static final BiFunction DEFAULT_TYPE_COMPARATOR = (v1, v2) -> (new TypeEqualsVisitor(v2)).equals(v1);

   public RangeEqualsVisitor(ValueVector left, ValueVector right) {
      this(left, right, DEFAULT_TYPE_COMPARATOR);
   }

   public RangeEqualsVisitor(ValueVector left, ValueVector right, BiFunction typeComparator) {
      this.left = left;
      this.right = right;
      this.typeComparator = typeComparator;
      Preconditions.checkArgument(left != null, "left vector cannot be null");
      Preconditions.checkArgument(right != null, "right vector cannot be null");
      this.checkType();
   }

   private void checkType() {
      if (this.typeComparator != null && this.left != this.right) {
         this.typeCompareResult = (Boolean)this.typeComparator.apply(this.left, this.right);
      } else {
         this.typeCompareResult = true;
      }

   }

   protected boolean validate(ValueVector left) {
      if (left != this.left) {
         this.left = left;
         this.checkType();
      }

      return this.typeCompareResult;
   }

   public boolean rangeEquals(Range range) {
      if (!this.typeCompareResult) {
         return false;
      } else {
         Preconditions.checkArgument(range.getLeftStart() >= 0, "leftStart %s must be non negative.", range.getLeftStart());
         Preconditions.checkArgument(range.getRightStart() >= 0, "rightStart %s must be non negative.", range.getRightStart());
         Preconditions.checkArgument(range.getRightStart() + range.getLength() <= this.right.getValueCount(), "(rightStart + length) %s out of range[0, %s].", range.getRightStart() + range.getLength(), this.right.getValueCount());
         Preconditions.checkArgument(range.getLeftStart() + range.getLength() <= this.left.getValueCount(), "(leftStart + length) %s out of range[0, %s].", range.getLeftStart() + range.getLength(), this.left.getValueCount());
         return (Boolean)this.left.accept(this, range);
      }
   }

   public ValueVector getLeft() {
      return this.left;
   }

   public ValueVector getRight() {
      return this.right;
   }

   public Boolean visit(BaseFixedWidthVector left, Range range) {
      return !this.validate(left) ? false : this.compareBaseFixedWidthVectors(range);
   }

   public Boolean visit(BaseVariableWidthVector left, Range range) {
      return !this.validate(left) ? false : this.compareBaseVariableWidthVectors(range);
   }

   public Boolean visit(BaseLargeVariableWidthVector left, Range range) {
      return !this.validate(left) ? false : this.compareBaseLargeVariableWidthVectors(range);
   }

   public Boolean visit(BaseVariableWidthViewVector left, Range range) {
      return !this.validate(left) ? false : this.compareBaseVariableWidthViewVectors(range);
   }

   public Boolean visit(ListVector left, Range range) {
      return !this.validate(left) ? false : this.compareListVectors(range);
   }

   public Boolean visit(FixedSizeListVector left, Range range) {
      return !this.validate(left) ? false : this.compareFixedSizeListVectors(range);
   }

   public Boolean visit(LargeListVector left, Range range) {
      return !this.validate(left) ? false : this.compareLargeListVectors(range);
   }

   public Boolean visit(NonNullableStructVector left, Range range) {
      return !this.validate(left) ? false : this.compareStructVectors(range);
   }

   public Boolean visit(UnionVector left, Range range) {
      return !this.validate(left) ? false : this.compareUnionVectors(range);
   }

   public Boolean visit(DenseUnionVector left, Range range) {
      return !this.validate(left) ? false : this.compareDenseUnionVectors(range);
   }

   public Boolean visit(NullVector left, Range range) {
      return !this.validate(left) ? false : true;
   }

   public Boolean visit(RunEndEncodedVector left, Range range) {
      return !this.validate(left) ? false : this.compareRunEndEncodedVectors(range);
   }

   public Boolean visit(ExtensionTypeVector left, Range range) {
      if (this.right instanceof ExtensionTypeVector && this.validate(left)) {
         ValueVector rightUnderlying = ((ExtensionTypeVector)this.right).getUnderlyingVector();
         TypeEqualsVisitor typeVisitor = new TypeEqualsVisitor(rightUnderlying);
         RangeEqualsVisitor underlyingVisitor = this.createInnerVisitor(left.getUnderlyingVector(), rightUnderlying, (l, r) -> typeVisitor.equals(l));
         return underlyingVisitor.rangeEquals(range);
      } else {
         return false;
      }
   }

   public Boolean visit(ListViewVector left, Range range) {
      return !this.validate(left) ? false : this.compareListViewVectors(range);
   }

   public Boolean visit(LargeListViewVector left, Range range) {
      return !this.validate(left) ? false : this.compareLargeListViewVectors(range);
   }

   protected boolean compareRunEndEncodedVectors(Range range) {
      RunEndEncodedVector leftVector = (RunEndEncodedVector)this.left;
      RunEndEncodedVector rightVector = (RunEndEncodedVector)this.right;
      int leftRangeEnd = range.getLeftStart() + range.getLength();
      int rightRangeEnd = range.getRightStart() + range.getLength();
      FieldVector leftValuesVector = leftVector.getValuesVector();
      FieldVector rightValuesVector = rightVector.getValuesVector();
      RangeEqualsVisitor innerVisitor = this.createInnerVisitor(leftValuesVector, rightValuesVector, (BiFunction)null);
      int leftLogicalIndex = range.getLeftStart();

      int rightRunEnd;
      for(int rightLogicalIndex = range.getRightStart(); leftLogicalIndex < leftRangeEnd; rightLogicalIndex = rightRunEnd) {
         int leftPhysicalIndex = leftVector.getPhysicalIndex(leftLogicalIndex);
         int rightPhysicalIndex = rightVector.getPhysicalIndex(rightLogicalIndex);
         if (!(Boolean)leftValuesVector.accept(innerVisitor, new Range(leftPhysicalIndex, rightPhysicalIndex, 1))) {
            return false;
         }

         int leftRunEnd = leftVector.getRunEnd(leftLogicalIndex);
         rightRunEnd = rightVector.getRunEnd(rightLogicalIndex);
         int leftRunLength = Math.min(leftRunEnd, leftRangeEnd) - leftLogicalIndex;
         int rightRunLength = Math.min(rightRunEnd, rightRangeEnd) - rightLogicalIndex;
         if (leftRunLength != rightRunLength) {
            return false;
         }

         leftLogicalIndex = leftRunEnd;
      }

      return true;
   }

   protected RangeEqualsVisitor createInnerVisitor(ValueVector leftInner, ValueVector rightInner, BiFunction typeComparator) {
      return new RangeEqualsVisitor(leftInner, rightInner, typeComparator);
   }

   protected boolean compareUnionVectors(Range range) {
      UnionVector leftVector = (UnionVector)this.left;
      UnionVector rightVector = (UnionVector)this.right;
      Range subRange = new Range(0, 0, 1);

      for(int i = 0; i < range.getLength(); ++i) {
         subRange.setLeftStart(range.getLeftStart() + i).setRightStart(range.getRightStart() + i);
         ValueVector leftSubVector = leftVector.getVector(range.getLeftStart() + i);
         ValueVector rightSubVector = rightVector.getVector(range.getRightStart() + i);
         if (leftSubVector != null && rightSubVector != null) {
            TypeEqualsVisitor typeVisitor = new TypeEqualsVisitor(rightSubVector);
            RangeEqualsVisitor visitor = this.createInnerVisitor(leftSubVector, rightSubVector, (left, right) -> typeVisitor.equals(left));
            if (!visitor.rangeEquals(subRange)) {
               return false;
            }
         } else if (leftSubVector != rightSubVector) {
            return false;
         }
      }

      return true;
   }

   protected boolean compareDenseUnionVectors(Range range) {
      DenseUnionVector leftVector = (DenseUnionVector)this.left;
      DenseUnionVector rightVector = (DenseUnionVector)this.right;
      Range subRange = new Range(0, 0, 1);

      for(int i = 0; i < range.getLength(); ++i) {
         boolean isLeftNull = leftVector.isNull(range.getLeftStart() + i);
         boolean isRightNull = rightVector.isNull(range.getRightStart() + i);
         if (!isLeftNull && !isRightNull) {
            byte leftTypeId = leftVector.getTypeId(range.getLeftStart() + i);
            byte rightTypeId = rightVector.getTypeId(range.getRightStart() + i);
            if (leftTypeId != rightTypeId) {
               return false;
            }

            ValueVector leftSubVector = leftVector.getVectorByType(leftTypeId);
            ValueVector rightSubVector = rightVector.getVectorByType(rightTypeId);
            if (leftSubVector != null && rightSubVector != null) {
               int leftOffset = leftVector.getOffset(range.getLeftStart() + i);
               int rightOffset = rightVector.getOffset(range.getRightStart() + i);
               subRange.setLeftStart(leftOffset).setRightStart(rightOffset);
               TypeEqualsVisitor typeVisitor = new TypeEqualsVisitor(rightSubVector);
               RangeEqualsVisitor visitor = this.createInnerVisitor(leftSubVector, rightSubVector, (left, right) -> typeVisitor.equals(left));
               if (!visitor.rangeEquals(subRange)) {
                  return false;
               }
            } else if (leftSubVector != rightSubVector) {
               return false;
            }
         } else if (isLeftNull != isRightNull) {
            return false;
         }
      }

      return true;
   }

   private boolean compareStructVectorsInternal(NonNullableStructVector leftVector, NonNullableStructVector rightVector, Range range) {
      for(String name : leftVector.getChildFieldNames()) {
         RangeEqualsVisitor visitor = this.createInnerVisitor(leftVector.getChild(name), rightVector.getChild(name), (BiFunction)null);
         if (!visitor.rangeEquals(range)) {
            return false;
         }
      }

      return true;
   }

   protected boolean compareStructVectors(Range range) {
      NonNullableStructVector leftVector = (NonNullableStructVector)this.left;
      NonNullableStructVector rightVector = (NonNullableStructVector)this.right;
      List<String> leftChildNames = leftVector.getChildFieldNames();
      if (!leftChildNames.equals(rightVector.getChildFieldNames())) {
         return false;
      } else if (!(leftVector instanceof StructVector) && !(rightVector instanceof StructVector)) {
         return this.compareStructVectorsInternal(leftVector, rightVector, range);
      } else {
         Range subRange = new Range(0, 0, 0);
         boolean lastIsNull = true;
         int lastNullIndex = -1;

         for(int i = 0; i < range.getLength(); ++i) {
            int leftIndex = range.getLeftStart() + i;
            int rightIndex = range.getRightStart() + i;
            boolean isLeftNull = leftVector.isNull(leftIndex);
            boolean isRightNull = rightVector.isNull(rightIndex);
            if (isLeftNull != isRightNull) {
               return false;
            }

            if (isLeftNull) {
               if (!lastIsNull) {
                  subRange.setLeftStart(range.getLeftStart() + lastNullIndex + 1).setRightStart(range.getRightStart() + lastNullIndex + 1).setLength(i - (lastNullIndex + 1));
                  if (!this.compareStructVectorsInternal(leftVector, rightVector, subRange)) {
                     return false;
                  }
               }

               lastIsNull = true;
               lastNullIndex = i;
            } else {
               lastIsNull = false;
            }
         }

         if (!lastIsNull) {
            subRange.setLeftStart(range.getLeftStart() + lastNullIndex + 1).setRightStart(range.getRightStart() + lastNullIndex + 1).setLength(range.getLength() - (lastNullIndex + 1));
            return this.compareStructVectorsInternal(leftVector, rightVector, subRange);
         } else {
            return true;
         }
      }
   }

   protected boolean compareBaseFixedWidthVectors(Range range) {
      BaseFixedWidthVector leftVector = (BaseFixedWidthVector)this.left;
      BaseFixedWidthVector rightVector = (BaseFixedWidthVector)this.right;

      for(int i = 0; i < range.getLength(); ++i) {
         int leftIndex = range.getLeftStart() + i;
         int rightIndex = range.getRightStart() + i;
         boolean isNull = leftVector.isNull(leftIndex);
         if (isNull != rightVector.isNull(rightIndex)) {
            return false;
         }

         int typeWidth = leftVector.getTypeWidth();
         if (!isNull) {
            if (!(leftVector instanceof BitVector)) {
               int startIndexLeft = typeWidth * leftIndex;
               int endIndexLeft = typeWidth * (leftIndex + 1);
               int startIndexRight = typeWidth * rightIndex;
               int endIndexRight = typeWidth * (rightIndex + 1);
               int ret = ByteFunctionHelpers.equal(leftVector.getDataBuffer(), (long)startIndexLeft, (long)endIndexLeft, rightVector.getDataBuffer(), (long)startIndexRight, (long)endIndexRight);
               if (ret == 0) {
                  return false;
               }
            } else {
               boolean ret = ((BitVector)leftVector).get(leftIndex) == ((BitVector)rightVector).get(rightIndex);
               if (!ret) {
                  return false;
               }
            }
         }
      }

      return true;
   }

   protected boolean compareBaseVariableWidthVectors(Range range) {
      BaseVariableWidthVector leftVector = (BaseVariableWidthVector)this.left;
      BaseVariableWidthVector rightVector = (BaseVariableWidthVector)this.right;

      for(int i = 0; i < range.getLength(); ++i) {
         int leftIndex = range.getLeftStart() + i;
         int rightIndex = range.getRightStart() + i;
         boolean isNull = leftVector.isNull(leftIndex);
         if (isNull != rightVector.isNull(rightIndex)) {
            return false;
         }

         int offsetWidth = 4;
         if (!isNull) {
            int startIndexLeft = leftVector.getOffsetBuffer().getInt((long)(leftIndex * offsetWidth));
            int endIndexLeft = leftVector.getOffsetBuffer().getInt((long)((leftIndex + 1) * offsetWidth));
            int startIndexRight = rightVector.getOffsetBuffer().getInt((long)(rightIndex * offsetWidth));
            int endIndexRight = rightVector.getOffsetBuffer().getInt((long)((rightIndex + 1) * offsetWidth));
            int ret = ByteFunctionHelpers.equal(leftVector.getDataBuffer(), (long)startIndexLeft, (long)endIndexLeft, rightVector.getDataBuffer(), (long)startIndexRight, (long)endIndexRight);
            if (ret == 0) {
               return false;
            }
         }
      }

      return true;
   }

   protected boolean compareBaseLargeVariableWidthVectors(Range range) {
      BaseLargeVariableWidthVector leftVector = (BaseLargeVariableWidthVector)this.left;
      BaseLargeVariableWidthVector rightVector = (BaseLargeVariableWidthVector)this.right;

      for(int i = 0; i < range.getLength(); ++i) {
         int leftIndex = range.getLeftStart() + i;
         int rightIndex = range.getRightStart() + i;
         boolean isNull = leftVector.isNull(leftIndex);
         if (isNull != rightVector.isNull(rightIndex)) {
            return false;
         }

         int offsetWidth = 8;
         if (!isNull) {
            long startIndexLeft = leftVector.getOffsetBuffer().getLong((long)leftIndex * (long)offsetWidth);
            long endIndexLeft = leftVector.getOffsetBuffer().getLong((long)(leftIndex + 1) * (long)offsetWidth);
            long startIndexRight = rightVector.getOffsetBuffer().getLong((long)rightIndex * (long)offsetWidth);
            long endIndexRight = rightVector.getOffsetBuffer().getLong((long)(rightIndex + 1) * (long)offsetWidth);
            int ret = ByteFunctionHelpers.equal(leftVector.getDataBuffer(), startIndexLeft, endIndexLeft, rightVector.getDataBuffer(), startIndexRight, endIndexRight);
            if (ret == 0) {
               return false;
            }
         }
      }

      return true;
   }

   protected boolean compareBaseVariableWidthViewVectors(Range range) {
      BaseVariableWidthViewVector leftVector = (BaseVariableWidthViewVector)this.left;
      BaseVariableWidthViewVector rightVector = (BaseVariableWidthViewVector)this.right;
      ArrowBuf leftViewBuffer = leftVector.getDataBuffer();
      ArrowBuf rightViewBuffer = rightVector.getDataBuffer();
      int elementSize = 16;
      int lengthWidth = 4;
      int prefixWidth = 4;
      int bufIndexWidth = 4;
      List<ArrowBuf> leftDataBuffers = leftVector.getDataBuffers();
      List<ArrowBuf> rightDataBuffers = rightVector.getDataBuffers();

      for(int i = 0; i < range.getLength(); ++i) {
         int leftIndex = range.getLeftStart() + i;
         int rightIndex = range.getRightStart() + i;
         boolean isNull = leftVector.isNull(leftIndex);
         if (isNull != rightVector.isNull(rightIndex)) {
            return false;
         }

         if (!isNull) {
            int startLeftByteOffset = leftIndex * 16;
            int startRightByteOffset = rightIndex * 16;
            int leftDataBufferValueLength = leftVector.getValueLength(leftIndex);
            int rightDataBufferValueLength = rightVector.getValueLength(rightIndex);
            if (leftDataBufferValueLength != rightDataBufferValueLength) {
               return false;
            }

            if (leftDataBufferValueLength > 12) {
               int leftDataBufferIndex = leftViewBuffer.getInt((long)(startLeftByteOffset + 4 + 4));
               int rightDataBufferIndex = rightViewBuffer.getInt((long)(startRightByteOffset + 4 + 4));
               int leftDataOffset = leftViewBuffer.getInt((long)(startLeftByteOffset + 4 + 4 + 4));
               int rightDataOffset = rightViewBuffer.getInt((long)(startRightByteOffset + 4 + 4 + 4));
               ArrowBuf leftDataBuffer = (ArrowBuf)leftDataBuffers.get(leftDataBufferIndex);
               ArrowBuf rightDataBuffer = (ArrowBuf)rightDataBuffers.get(rightDataBufferIndex);
               int retDataBuf = ByteFunctionHelpers.equal(leftDataBuffer, (long)leftDataOffset, (long)(leftDataOffset + leftDataBufferValueLength), rightDataBuffer, (long)rightDataOffset, (long)(rightDataOffset + rightDataBufferValueLength));
               if (retDataBuf == 0) {
                  return false;
               }
            } else {
               int leftDataOffset = startLeftByteOffset + 4;
               int rightDataOffset = startRightByteOffset + 4;
               int retDataBuf = ByteFunctionHelpers.equal(leftViewBuffer, (long)leftDataOffset, (long)(leftDataOffset + leftDataBufferValueLength), rightViewBuffer, (long)rightDataOffset, (long)(rightDataOffset + rightDataBufferValueLength));
               if (retDataBuf == 0) {
                  return false;
               }
            }
         }
      }

      return true;
   }

   protected boolean compareListVectors(Range range) {
      ListVector leftVector = (ListVector)this.left;
      ListVector rightVector = (ListVector)this.right;
      RangeEqualsVisitor innerVisitor = this.createInnerVisitor(leftVector.getDataVector(), rightVector.getDataVector(), (BiFunction)null);
      Range innerRange = new Range();

      for(int i = 0; i < range.getLength(); ++i) {
         int leftIndex = range.getLeftStart() + i;
         int rightIndex = range.getRightStart() + i;
         boolean isNull = leftVector.isNull(leftIndex);
         if (isNull != rightVector.isNull(rightIndex)) {
            return false;
         }

         int offsetWidth = 4;
         if (!isNull) {
            int startIndexLeft = leftVector.getOffsetBuffer().getInt((long)(leftIndex * offsetWidth));
            int endIndexLeft = leftVector.getOffsetBuffer().getInt((long)((leftIndex + 1) * offsetWidth));
            int startIndexRight = rightVector.getOffsetBuffer().getInt((long)(rightIndex * offsetWidth));
            int endIndexRight = rightVector.getOffsetBuffer().getInt((long)((rightIndex + 1) * offsetWidth));
            if (endIndexLeft - startIndexLeft != endIndexRight - startIndexRight) {
               return false;
            }

            innerRange = innerRange.setRightStart(startIndexRight).setLeftStart(startIndexLeft).setLength(endIndexLeft - startIndexLeft);
            if (!innerVisitor.rangeEquals(innerRange)) {
               return false;
            }
         }
      }

      return true;
   }

   protected boolean compareFixedSizeListVectors(Range range) {
      FixedSizeListVector leftVector = (FixedSizeListVector)this.left;
      FixedSizeListVector rightVector = (FixedSizeListVector)this.right;
      if (leftVector.getListSize() != rightVector.getListSize()) {
         return false;
      } else {
         int listSize = leftVector.getListSize();
         RangeEqualsVisitor innerVisitor = this.createInnerVisitor(leftVector.getDataVector(), rightVector.getDataVector(), (BiFunction)null);
         Range innerRange = new Range(0, 0, listSize);

         for(int i = 0; i < range.getLength(); ++i) {
            int leftIndex = range.getLeftStart() + i;
            int rightIndex = range.getRightStart() + i;
            boolean isNull = leftVector.isNull(leftIndex);
            if (isNull != rightVector.isNull(rightIndex)) {
               return false;
            }

            if (!isNull) {
               int startIndexLeft = leftIndex * listSize;
               int endIndexLeft = (leftIndex + 1) * listSize;
               int startIndexRight = rightIndex * listSize;
               int endIndexRight = (rightIndex + 1) * listSize;
               if (endIndexLeft - startIndexLeft != endIndexRight - startIndexRight) {
                  return false;
               }

               innerRange = innerRange.setLeftStart(startIndexLeft).setRightStart(startIndexRight);
               if (!innerVisitor.rangeEquals(innerRange)) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   protected boolean compareLargeListVectors(Range range) {
      LargeListVector leftVector = (LargeListVector)this.left;
      LargeListVector rightVector = (LargeListVector)this.right;
      RangeEqualsVisitor innerVisitor = this.createInnerVisitor(leftVector.getDataVector(), rightVector.getDataVector(), (BiFunction)null);
      Range innerRange = new Range();

      for(int i = 0; i < range.getLength(); ++i) {
         int leftIndex = range.getLeftStart() + i;
         int rightIndex = range.getRightStart() + i;
         boolean isNull = leftVector.isNull(leftIndex);
         if (isNull != rightVector.isNull(rightIndex)) {
            return false;
         }

         long offsetWidth = 8L;
         if (!isNull) {
            long startIndexLeft = leftVector.getOffsetBuffer().getLong((long)leftIndex * offsetWidth);
            long endIndexLeft = leftVector.getOffsetBuffer().getLong((long)(leftIndex + 1) * offsetWidth);
            long startIndexRight = rightVector.getOffsetBuffer().getLong((long)rightIndex * offsetWidth);
            long endIndexRight = rightVector.getOffsetBuffer().getLong((long)(rightIndex + 1) * offsetWidth);
            if (endIndexLeft - startIndexLeft != endIndexRight - startIndexRight) {
               return false;
            }

            innerRange = innerRange.setRightStart(LargeMemoryUtil.checkedCastToInt(startIndexRight)).setLeftStart(LargeMemoryUtil.checkedCastToInt(startIndexLeft)).setLength(LargeMemoryUtil.checkedCastToInt(endIndexLeft - startIndexLeft));
            if (!innerVisitor.rangeEquals(innerRange)) {
               return false;
            }
         }
      }

      return true;
   }

   protected boolean compareListViewVectors(Range range) {
      ListViewVector leftVector = (ListViewVector)this.left;
      ListViewVector rightVector = (ListViewVector)this.right;
      RangeEqualsVisitor innerVisitor = this.createInnerVisitor(leftVector.getDataVector(), rightVector.getDataVector(), (BiFunction)null);
      Range innerRange = new Range();

      for(int i = 0; i < range.getLength(); ++i) {
         int leftIndex = range.getLeftStart() + i;
         int rightIndex = range.getRightStart() + i;
         boolean isNull = leftVector.isNull(leftIndex);
         if (isNull != rightVector.isNull(rightIndex)) {
            return false;
         }

         int offsetWidth = 4;
         int sizeWidth = 4;
         if (!isNull) {
            int startIndexLeft = leftVector.getOffsetBuffer().getInt((long)leftIndex * (long)offsetWidth);
            int leftSize = leftVector.getSizeBuffer().getInt((long)leftIndex * (long)sizeWidth);
            int startIndexRight = rightVector.getOffsetBuffer().getInt((long)rightIndex * (long)offsetWidth);
            int rightSize = rightVector.getSizeBuffer().getInt((long)rightIndex * (long)sizeWidth);
            if (leftSize != rightSize) {
               return false;
            }

            innerRange = innerRange.setRightStart(startIndexRight).setLeftStart(startIndexLeft).setLength(leftSize);
            if (!innerVisitor.rangeEquals(innerRange)) {
               return false;
            }
         }
      }

      return true;
   }

   protected boolean compareLargeListViewVectors(Range range) {
      LargeListViewVector leftVector = (LargeListViewVector)this.left;
      LargeListViewVector rightVector = (LargeListViewVector)this.right;
      RangeEqualsVisitor innerVisitor = this.createInnerVisitor(leftVector.getDataVector(), rightVector.getDataVector(), (BiFunction)null);
      Range innerRange = new Range();

      for(int i = 0; i < range.getLength(); ++i) {
         int leftIndex = range.getLeftStart() + i;
         int rightIndex = range.getRightStart() + i;
         boolean isNull = leftVector.isNull(leftIndex);
         if (isNull != rightVector.isNull(rightIndex)) {
            return false;
         }

         int offsetWidth = 8;
         int sizeWidth = 8;
         if (!isNull) {
            int startIndexLeft = leftVector.getOffsetBuffer().getInt((long)leftIndex * (long)offsetWidth);
            int leftSize = leftVector.getSizeBuffer().getInt((long)leftIndex * (long)sizeWidth);
            int startIndexRight = rightVector.getOffsetBuffer().getInt((long)rightIndex * (long)offsetWidth);
            int rightSize = rightVector.getSizeBuffer().getInt((long)rightIndex * (long)sizeWidth);
            if (leftSize != rightSize) {
               return false;
            }

            innerRange = innerRange.setRightStart(startIndexRight).setLeftStart(startIndexLeft).setLength(leftSize);
            if (!innerVisitor.rangeEquals(innerRange)) {
               return false;
            }
         }
      }

      return true;
   }
}
