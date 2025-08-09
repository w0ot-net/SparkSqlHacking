package org.apache.arrow.vector.util;

import java.util.HashSet;
import org.apache.arrow.memory.util.LargeMemoryUtil;
import org.apache.arrow.memory.util.MemoryUtil;
import org.apache.arrow.util.Preconditions;
import org.apache.arrow.vector.BaseFixedWidthVector;
import org.apache.arrow.vector.BaseLargeVariableWidthVector;
import org.apache.arrow.vector.BaseVariableWidthVector;
import org.apache.arrow.vector.BaseVariableWidthViewVector;
import org.apache.arrow.vector.BitVector;
import org.apache.arrow.vector.BitVectorHelper;
import org.apache.arrow.vector.ExtensionTypeVector;
import org.apache.arrow.vector.NullVector;
import org.apache.arrow.vector.ValueVector;
import org.apache.arrow.vector.compare.TypeEqualsVisitor;
import org.apache.arrow.vector.compare.VectorVisitor;
import org.apache.arrow.vector.complex.DenseUnionVector;
import org.apache.arrow.vector.complex.FixedSizeListVector;
import org.apache.arrow.vector.complex.LargeListVector;
import org.apache.arrow.vector.complex.ListVector;
import org.apache.arrow.vector.complex.NonNullableStructVector;
import org.apache.arrow.vector.complex.UnionVector;

public class VectorAppender implements VectorVisitor {
   private final ValueVector targetVector;
   private final TypeEqualsVisitor typeVisitor;

   public VectorAppender(ValueVector targetVector) {
      this.targetVector = targetVector;
      this.typeVisitor = new TypeEqualsVisitor(targetVector, false, true);
   }

   public ValueVector visit(BaseFixedWidthVector deltaVector, Void value) {
      Preconditions.checkArgument(this.targetVector.getField().getType().equals(deltaVector.getField().getType()), "The targetVector to append must have the same type as the targetVector being appended");
      if (deltaVector.getValueCount() == 0) {
         return this.targetVector;
      } else {
         int newValueCount = this.targetVector.getValueCount() + deltaVector.getValueCount();

         while(this.targetVector.getValueCapacity() < newValueCount) {
            this.targetVector.reAlloc();
         }

         BitVectorHelper.concatBits(this.targetVector.getValidityBuffer(), this.targetVector.getValueCount(), deltaVector.getValidityBuffer(), deltaVector.getValueCount(), this.targetVector.getValidityBuffer());
         if (this.targetVector instanceof BitVector) {
            BitVectorHelper.concatBits(this.targetVector.getDataBuffer(), this.targetVector.getValueCount(), deltaVector.getDataBuffer(), deltaVector.getValueCount(), this.targetVector.getDataBuffer());
         } else {
            MemoryUtil.copyMemory(deltaVector.getDataBuffer().memoryAddress(), this.targetVector.getDataBuffer().memoryAddress() + (long)(deltaVector.getTypeWidth() * this.targetVector.getValueCount()), (long)(deltaVector.getTypeWidth() * deltaVector.getValueCount()));
         }

         this.targetVector.setValueCount(newValueCount);
         return this.targetVector;
      }
   }

   public ValueVector visit(BaseVariableWidthVector deltaVector, Void value) {
      Preconditions.checkArgument(this.targetVector.getField().getType().equals(deltaVector.getField().getType()), "The targetVector to append must have the same type as the targetVector being appended");
      if (deltaVector.getValueCount() == 0) {
         return this.targetVector;
      } else {
         int newValueCount = this.targetVector.getValueCount() + deltaVector.getValueCount();
         int targetDataSize = this.targetVector.getOffsetBuffer().getInt((long)this.targetVector.getValueCount() * 4L);
         int deltaDataSize = deltaVector.getOffsetBuffer().getInt((long)deltaVector.getValueCount() * 4L);
         int newValueCapacity = targetDataSize + deltaDataSize;

         while(this.targetVector.getValueCapacity() < newValueCount) {
            ((BaseVariableWidthVector)this.targetVector).reallocValidityAndOffsetBuffers();
         }

         while(this.targetVector.getDataBuffer().capacity() < (long)newValueCapacity) {
            ((BaseVariableWidthVector)this.targetVector).reallocDataBuffer();
         }

         BitVectorHelper.concatBits(this.targetVector.getValidityBuffer(), this.targetVector.getValueCount(), deltaVector.getValidityBuffer(), deltaVector.getValueCount(), this.targetVector.getValidityBuffer());
         MemoryUtil.copyMemory(deltaVector.getDataBuffer().memoryAddress(), this.targetVector.getDataBuffer().memoryAddress() + (long)targetDataSize, (long)deltaDataSize);
         MemoryUtil.copyMemory(deltaVector.getOffsetBuffer().memoryAddress() + 4L, this.targetVector.getOffsetBuffer().memoryAddress() + (long)((this.targetVector.getValueCount() + 1) * 4), (long)(deltaVector.getValueCount() * 4));

         for(int i = 0; i < deltaVector.getValueCount(); ++i) {
            int oldOffset = this.targetVector.getOffsetBuffer().getInt((long)(this.targetVector.getValueCount() + 1 + i) * 4L);
            this.targetVector.getOffsetBuffer().setInt((long)(this.targetVector.getValueCount() + 1 + i) * 4L, oldOffset + targetDataSize);
         }

         ((BaseVariableWidthVector)this.targetVector).setLastSet(newValueCount - 1);
         this.targetVector.setValueCount(newValueCount);
         return this.targetVector;
      }
   }

   public ValueVector visit(BaseLargeVariableWidthVector deltaVector, Void value) {
      Preconditions.checkArgument(this.targetVector.getField().getType().equals(deltaVector.getField().getType()), "The targetVector to append must have the same type as the targetVector being appended");
      if (deltaVector.getValueCount() == 0) {
         return this.targetVector;
      } else {
         int newValueCount = this.targetVector.getValueCount() + deltaVector.getValueCount();
         long targetDataSize = this.targetVector.getOffsetBuffer().getLong((long)this.targetVector.getValueCount() * 8L);
         long deltaDataSize = deltaVector.getOffsetBuffer().getLong((long)deltaVector.getValueCount() * 8L);
         long newValueCapacity = targetDataSize + deltaDataSize;

         while(this.targetVector.getValueCapacity() < newValueCount) {
            ((BaseLargeVariableWidthVector)this.targetVector).reallocValidityAndOffsetBuffers();
         }

         while(this.targetVector.getDataBuffer().capacity() < newValueCapacity) {
            ((BaseLargeVariableWidthVector)this.targetVector).reallocDataBuffer();
         }

         BitVectorHelper.concatBits(this.targetVector.getValidityBuffer(), this.targetVector.getValueCount(), deltaVector.getValidityBuffer(), deltaVector.getValueCount(), this.targetVector.getValidityBuffer());
         MemoryUtil.copyMemory(deltaVector.getDataBuffer().memoryAddress(), this.targetVector.getDataBuffer().memoryAddress() + targetDataSize, deltaDataSize);
         MemoryUtil.copyMemory(deltaVector.getOffsetBuffer().memoryAddress() + 8L, this.targetVector.getOffsetBuffer().memoryAddress() + (long)((this.targetVector.getValueCount() + 1) * 8), (long)(deltaVector.getValueCount() * 8));

         for(int i = 0; i < deltaVector.getValueCount(); ++i) {
            long oldOffset = this.targetVector.getOffsetBuffer().getLong((long)(this.targetVector.getValueCount() + 1 + i) * 8L);
            this.targetVector.getOffsetBuffer().setLong((long)(this.targetVector.getValueCount() + 1 + i) * 8L, oldOffset + targetDataSize);
         }

         ((BaseLargeVariableWidthVector)this.targetVector).setLastSet(newValueCount - 1);
         this.targetVector.setValueCount(newValueCount);
         return this.targetVector;
      }
   }

   public ValueVector visit(BaseVariableWidthViewVector left, Void value) {
      throw new UnsupportedOperationException("View vectors are not supported.");
   }

   public ValueVector visit(ListVector deltaVector, Void value) {
      Preconditions.checkArgument(this.typeVisitor.equals(deltaVector), "The targetVector to append must have the same type as the targetVector being appended");
      if (deltaVector.getValueCount() == 0) {
         return this.targetVector;
      } else {
         int newValueCount = this.targetVector.getValueCount() + deltaVector.getValueCount();
         int targetListSize = this.targetVector.getOffsetBuffer().getInt((long)this.targetVector.getValueCount() * 4L);
         int deltaListSize = deltaVector.getOffsetBuffer().getInt((long)deltaVector.getValueCount() * 4L);
         ListVector targetListVector = (ListVector)this.targetVector;
         targetListVector.getDataVector().setValueCount(targetListSize);
         deltaVector.getDataVector().setValueCount(deltaListSize);

         while(this.targetVector.getValueCapacity() < newValueCount) {
            this.targetVector.reAlloc();
         }

         BitVectorHelper.concatBits(this.targetVector.getValidityBuffer(), this.targetVector.getValueCount(), deltaVector.getValidityBuffer(), deltaVector.getValueCount(), this.targetVector.getValidityBuffer());
         MemoryUtil.copyMemory(deltaVector.getOffsetBuffer().memoryAddress() + 4L, this.targetVector.getOffsetBuffer().memoryAddress() + (long)((this.targetVector.getValueCount() + 1) * 4), (long)deltaVector.getValueCount() * 4L);

         for(int i = 0; i < deltaVector.getValueCount(); ++i) {
            int oldOffset = this.targetVector.getOffsetBuffer().getInt((long)(this.targetVector.getValueCount() + 1 + i) * 4L);
            this.targetVector.getOffsetBuffer().setInt((long)(this.targetVector.getValueCount() + 1 + i) * 4L, oldOffset + targetListSize);
         }

         targetListVector.setLastSet(newValueCount - 1);
         VectorAppender innerAppender = new VectorAppender(targetListVector.getDataVector());
         deltaVector.getDataVector().accept(innerAppender, (Object)null);
         this.targetVector.setValueCount(newValueCount);
         return this.targetVector;
      }
   }

   public ValueVector visit(LargeListVector deltaVector, Void value) {
      Preconditions.checkArgument(this.typeVisitor.equals(deltaVector), "The targetVector to append must have the same type as the targetVector being appended");
      if (deltaVector.getValueCount() == 0) {
         return this.targetVector;
      } else {
         int newValueCount = this.targetVector.getValueCount() + deltaVector.getValueCount();
         long targetListSize = this.targetVector.getOffsetBuffer().getLong((long)this.targetVector.getValueCount() * 8L);
         long deltaListSize = deltaVector.getOffsetBuffer().getLong((long)deltaVector.getValueCount() * 8L);
         ListVector targetListVector = (ListVector)this.targetVector;
         targetListVector.getDataVector().setValueCount(LargeMemoryUtil.checkedCastToInt(targetListSize));
         deltaVector.getDataVector().setValueCount(LargeMemoryUtil.checkedCastToInt(deltaListSize));

         while(this.targetVector.getValueCapacity() < newValueCount) {
            this.targetVector.reAlloc();
         }

         BitVectorHelper.concatBits(this.targetVector.getValidityBuffer(), this.targetVector.getValueCount(), deltaVector.getValidityBuffer(), deltaVector.getValueCount(), this.targetVector.getValidityBuffer());
         MemoryUtil.copyMemory(deltaVector.getOffsetBuffer().memoryAddress() + 4L, this.targetVector.getOffsetBuffer().memoryAddress() + (long)((this.targetVector.getValueCount() + 1) * 8), (long)deltaVector.getValueCount() * 4L);

         for(int i = 0; i < deltaVector.getValueCount(); ++i) {
            long oldOffset = this.targetVector.getOffsetBuffer().getLong((long)(this.targetVector.getValueCount() + 1 + i) * 8L);
            this.targetVector.getOffsetBuffer().setLong((long)(this.targetVector.getValueCount() + 1 + i) * 8L, oldOffset + targetListSize);
         }

         targetListVector.setLastSet(newValueCount - 1);
         VectorAppender innerAppender = new VectorAppender(targetListVector.getDataVector());
         deltaVector.getDataVector().accept(innerAppender, (Object)null);
         this.targetVector.setValueCount(newValueCount);
         return this.targetVector;
      }
   }

   public ValueVector visit(FixedSizeListVector deltaVector, Void value) {
      Preconditions.checkArgument(this.typeVisitor.equals(deltaVector), "The vector to append must have the same type as the targetVector being appended");
      if (deltaVector.getValueCount() == 0) {
         return this.targetVector;
      } else {
         FixedSizeListVector targetListVector = (FixedSizeListVector)this.targetVector;
         Preconditions.checkArgument(targetListVector.getListSize() == deltaVector.getListSize(), "FixedSizeListVector must have the same list size to append");
         int newValueCount = this.targetVector.getValueCount() + deltaVector.getValueCount();
         int targetListSize = targetListVector.getValueCount() * targetListVector.getListSize();
         int deltaListSize = deltaVector.getValueCount() * deltaVector.getListSize();
         targetListVector.getDataVector().setValueCount(targetListSize);
         deltaVector.getDataVector().setValueCount(deltaListSize);

         while(this.targetVector.getValueCapacity() < newValueCount) {
            this.targetVector.reAlloc();
         }

         BitVectorHelper.concatBits(this.targetVector.getValidityBuffer(), this.targetVector.getValueCount(), deltaVector.getValidityBuffer(), deltaVector.getValueCount(), this.targetVector.getValidityBuffer());
         VectorAppender innerAppender = new VectorAppender(targetListVector.getDataVector());
         deltaVector.getDataVector().accept(innerAppender, (Object)null);
         this.targetVector.setValueCount(newValueCount);
         return this.targetVector;
      }
   }

   public ValueVector visit(NonNullableStructVector deltaVector, Void value) {
      Preconditions.checkArgument(this.typeVisitor.equals(deltaVector), "The vector to append must have the same type as the targetVector being appended");
      if (deltaVector.getValueCount() == 0) {
         return this.targetVector;
      } else {
         NonNullableStructVector targetStructVector = (NonNullableStructVector)this.targetVector;
         int newValueCount = this.targetVector.getValueCount() + deltaVector.getValueCount();

         while(this.targetVector.getValueCapacity() < newValueCount) {
            this.targetVector.reAlloc();
         }

         BitVectorHelper.concatBits(this.targetVector.getValidityBuffer(), this.targetVector.getValueCount(), deltaVector.getValidityBuffer(), deltaVector.getValueCount(), this.targetVector.getValidityBuffer());

         for(int i = 0; i < targetStructVector.getChildrenFromFields().size(); ++i) {
            ValueVector targetChild = targetStructVector.getVectorById(i);
            ValueVector deltaChild = deltaVector.getVectorById(i);
            targetChild.setValueCount(targetStructVector.getValueCount());
            deltaChild.setValueCount(deltaVector.getValueCount());
            VectorAppender innerAppender = new VectorAppender(targetChild);
            deltaChild.accept(innerAppender, (Object)null);
         }

         this.targetVector.setValueCount(newValueCount);
         return this.targetVector;
      }
   }

   public ValueVector visit(UnionVector deltaVector, Void value) {
      Preconditions.checkArgument(this.targetVector.getMinorType() == deltaVector.getMinorType(), "The vector to append must have the same type as the targetVector being appended");
      if (deltaVector.getValueCount() == 0) {
         return this.targetVector;
      } else {
         UnionVector targetUnionVector = (UnionVector)this.targetVector;
         int newValueCount = this.targetVector.getValueCount() + deltaVector.getValueCount();

         while(targetUnionVector.getValueCapacity() < newValueCount) {
            targetUnionVector.reAlloc();
         }

         MemoryUtil.copyMemory(deltaVector.getTypeBufferAddress(), targetUnionVector.getTypeBufferAddress() + (long)this.targetVector.getValueCount(), (long)deltaVector.getValueCount());
         HashSet<Integer> targetTypes = new HashSet();

         for(int i = 0; i < targetUnionVector.getValueCount(); ++i) {
            targetTypes.add(targetUnionVector.getTypeValue(i));
         }

         HashSet<Integer> deltaTypes = new HashSet();

         for(int i = 0; i < deltaVector.getValueCount(); ++i) {
            deltaTypes.add(deltaVector.getTypeValue(i));
         }

         for(int i = 0; i < 127; ++i) {
            if (targetTypes.contains(i) || deltaTypes.contains(i)) {
               ValueVector targetChild = targetUnionVector.getVectorByType(i);
               if (!targetTypes.contains(i)) {
                  while(targetChild.getValueCapacity() < newValueCount) {
                     targetChild.reAlloc();
                  }
               }

               if (deltaTypes.contains(i)) {
                  ValueVector deltaChild = deltaVector.getVectorByType(i);
                  targetChild.setValueCount(targetUnionVector.getValueCount());
                  deltaChild.setValueCount(deltaVector.getValueCount());
                  VectorAppender innerAppender = new VectorAppender(targetChild);
                  deltaChild.accept(innerAppender, (Object)null);
               }

               targetChild.setValueCount(newValueCount);
            }
         }

         this.targetVector.setValueCount(newValueCount);
         return this.targetVector;
      }
   }

   public ValueVector visit(DenseUnionVector deltaVector, Void value) {
      Preconditions.checkArgument(this.targetVector.getMinorType() == deltaVector.getMinorType(), "The vector to append must have the same type as the targetVector being appended");
      if (deltaVector.getValueCount() == 0) {
         return this.targetVector;
      } else {
         DenseUnionVector targetDenseUnionVector = (DenseUnionVector)this.targetVector;
         int newValueCount = this.targetVector.getValueCount() + deltaVector.getValueCount();

         while(targetDenseUnionVector.getValueCapacity() < newValueCount) {
            targetDenseUnionVector.reAlloc();
         }

         MemoryUtil.copyMemory(deltaVector.getTypeBuffer().memoryAddress(), targetDenseUnionVector.getTypeBuffer().memoryAddress() + (long)this.targetVector.getValueCount(), (long)deltaVector.getValueCount());

         for(int i = 0; i < deltaVector.getValueCount(); ++i) {
            byte typeId = deltaVector.getTypeId(i);
            ValueVector targetChildVector = targetDenseUnionVector.getVectorByType(typeId);
            int offsetBase = targetChildVector == null ? 0 : targetChildVector.getValueCount();
            int deltaOffset = deltaVector.getOffset(i);
            long index = (long)(this.targetVector.getValueCount() + i) * 4L;
            this.targetVector.getOffsetBuffer().setInt(index, offsetBase + deltaOffset);
         }

         for(int i = 0; i <= 127; ++i) {
            ValueVector targetChildVector = targetDenseUnionVector.getVectorByType((byte)i);
            ValueVector deltaChildVector = deltaVector.getVectorByType((byte)i);
            if (targetChildVector != null || deltaChildVector != null) {
               if (targetChildVector == null && deltaChildVector != null) {
                  targetDenseUnionVector.registerNewTypeId(deltaChildVector.getField());
                  ValueVector var14 = targetDenseUnionVector.addVector((byte)i, deltaChildVector.getField().createVector(targetDenseUnionVector.getAllocator()));
                  VectorAppender childAppender = new VectorAppender(var14);
                  deltaChildVector.accept(childAppender, (Object)null);
               } else if (targetChildVector == null || deltaChildVector != null) {
                  TypeEqualsVisitor childTypeVisitor = new TypeEqualsVisitor(targetChildVector, false, false);
                  if (!childTypeVisitor.equals(deltaChildVector)) {
                     throw new IllegalArgumentException("dense union vectors have different child vector types with type id " + i);
                  }

                  VectorAppender childAppender = new VectorAppender(targetChildVector);
                  deltaChildVector.accept(childAppender, (Object)null);
               }
            }
         }

         this.targetVector.setValueCount(newValueCount);
         return this.targetVector;
      }
   }

   public ValueVector visit(NullVector deltaVector, Void value) {
      Preconditions.checkArgument(this.targetVector.getField().getType().equals(deltaVector.getField().getType()), "The targetVector to append must have the same type as the targetVector being appended");
      return this.targetVector;
   }

   public ValueVector visit(ExtensionTypeVector deltaVector, Void value) {
      ValueVector targetUnderlying = ((ExtensionTypeVector)this.targetVector).getUnderlyingVector();
      VectorAppender underlyingAppender = new VectorAppender(targetUnderlying);
      deltaVector.getUnderlyingVector().accept(underlyingAppender, (Object)null);
      return this.targetVector;
   }
}
