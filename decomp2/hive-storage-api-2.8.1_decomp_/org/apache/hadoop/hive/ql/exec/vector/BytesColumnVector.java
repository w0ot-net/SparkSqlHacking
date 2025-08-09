package org.apache.hadoop.hive.ql.exec.vector;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import org.apache.hive.common.util.SuppressFBWarnings;

public class BytesColumnVector extends ColumnVector {
   public byte[][] vector;
   public int[] start;
   public int[] length;
   private byte[] currentValue;
   private int currentOffset;
   private byte[] sharedBuffer;
   private int sharedBufferOffset;
   private int bufferAllocationCount;
   static final int DEFAULT_BUFFER_SIZE = 16384;
   static final float EXTRA_SPACE_FACTOR = 1.2F;
   static final int MAX_SIZE_FOR_SMALL_ITEM = 1048576;
   static final int MAX_SIZE_FOR_SHARED_BUFFER = 1073741824;

   public BytesColumnVector() {
      this(1024);
   }

   public BytesColumnVector(int size) {
      super(ColumnVector.Type.BYTES, size);
      this.vector = new byte[size][];
      this.start = new int[size];
      this.length = new int[size];
   }

   public void reset() {
      super.reset();
      this.initBuffer(0);
   }

   public void setRef(int elementNum, byte[] sourceBuf, int start, int length) {
      this.vector[elementNum] = sourceBuf;
      this.start[elementNum] = start;
      this.length[elementNum] = length;
   }

   public void initBuffer(int estimatedValueSize) {
      this.sharedBufferOffset = 0;
      if (this.sharedBuffer != null) {
         if (this.bufferAllocationCount > 0) {
            for(int idx = 0; idx < this.vector.length; ++idx) {
               this.vector[idx] = null;
               this.length[idx] = 0;
            }
         }
      } else {
         long bufferSize = (long)((float)(this.vector.length * estimatedValueSize) * 1.2F);
         if (bufferSize < 16384L) {
            bufferSize = 16384L;
         }

         if (bufferSize > 1073741824L) {
            bufferSize = 1073741824L;
         }

         this.sharedBuffer = new byte[(int)bufferSize];
      }

      this.bufferAllocationCount = 0;
   }

   public void initBuffer() {
      this.initBuffer(0);
   }

   public int bufferSize() {
      return this.sharedBuffer == null ? 0 : this.sharedBuffer.length;
   }

   public void setVal(int elementNum, byte[] sourceBuf, int start, int length) {
      this.ensureValPreallocated(length);
      if (length > 0) {
         System.arraycopy(sourceBuf, start, this.currentValue, this.currentOffset, length);
      }

      this.setValPreallocated(elementNum, length);
   }

   public void setVal(int elementNum, byte[] sourceBuf) {
      this.setVal(elementNum, sourceBuf, 0, sourceBuf.length);
   }

   public void ensureValPreallocated(int length) {
      if (this.sharedBufferOffset + length > this.sharedBuffer.length) {
         this.allocateBuffer(length);
      } else {
         this.currentValue = this.sharedBuffer;
         this.currentOffset = this.sharedBufferOffset;
      }

   }

   @SuppressFBWarnings(
      value = {"EI_EXPOSE_REP"},
      justification = "Expose internal rep for efficiency"
   )
   public byte[] getValPreallocatedBytes() {
      return this.currentValue;
   }

   public int getValPreallocatedStart() {
      return this.currentOffset;
   }

   public void setValPreallocated(int elementNum, int length) {
      this.vector[elementNum] = this.currentValue;
      this.start[elementNum] = this.currentOffset;
      this.length[elementNum] = length;
      if (this.currentValue == this.sharedBuffer) {
         this.sharedBufferOffset += length;
      }

   }

   public void setConcat(int elementNum, byte[] leftSourceBuf, int leftStart, int leftLen, byte[] rightSourceBuf, int rightStart, int rightLen) {
      int newLen = leftLen + rightLen;
      this.ensureValPreallocated(newLen);
      this.setValPreallocated(elementNum, newLen);
      System.arraycopy(leftSourceBuf, leftStart, this.currentValue, this.currentOffset, leftLen);
      System.arraycopy(rightSourceBuf, rightStart, this.currentValue, this.currentOffset + leftLen, rightLen);
   }

   private void allocateBuffer(int nextElemLength) {
      if (nextElemLength <= 1048576 && this.sharedBufferOffset + nextElemLength < 1073741824) {
         if (this.sharedBufferOffset + nextElemLength > this.sharedBuffer.length) {
            int newLength;
            for(newLength = this.sharedBuffer.length * 2; newLength < nextElemLength; newLength *= 2) {
            }

            this.sharedBuffer = new byte[newLength];
            ++this.bufferAllocationCount;
            this.sharedBufferOffset = 0;
         }

         this.currentValue = this.sharedBuffer;
         this.currentOffset = this.sharedBufferOffset;
      } else {
         ++this.bufferAllocationCount;
         this.currentValue = new byte[nextElemLength];
         this.currentOffset = 0;
      }

   }

   public void copySelected(boolean selectedInUse, int[] sel, int size, ColumnVector outputColVector) {
      BytesColumnVector output = (BytesColumnVector)outputColVector;
      boolean[] outputIsNull = output.isNull;
      output.isRepeating = false;
      if (this.isRepeating) {
         if (!this.noNulls && this.isNull[0]) {
            outputIsNull[0] = true;
            output.noNulls = false;
         } else {
            outputIsNull[0] = false;
            output.setVal(0, this.vector[0], this.start[0], this.length[0]);
         }

         output.isRepeating = true;
      } else {
         if (this.noNulls) {
            if (selectedInUse) {
               if (!outputColVector.noNulls) {
                  for(int j = 0; j != size; ++j) {
                     int i = sel[j];
                     outputIsNull[i] = false;
                     output.setVal(i, this.vector[i], this.start[i], this.length[i]);
                  }
               } else {
                  for(int j = 0; j != size; ++j) {
                     int i = sel[j];
                     output.setVal(i, this.vector[i], this.start[i], this.length[i]);
                  }
               }
            } else {
               if (!outputColVector.noNulls) {
                  Arrays.fill(outputIsNull, false);
                  outputColVector.noNulls = true;
               }

               for(int i = 0; i != size; ++i) {
                  output.setVal(i, this.vector[i], this.start[i], this.length[i]);
               }
            }
         } else if (selectedInUse) {
            for(int j = 0; j < size; ++j) {
               int i = sel[j];
               if (!this.isNull[i]) {
                  output.isNull[i] = false;
                  output.setVal(i, this.vector[i], this.start[i], this.length[i]);
               } else {
                  output.isNull[i] = true;
                  output.noNulls = false;
               }
            }
         } else {
            for(int i = 0; i < size; ++i) {
               if (!this.isNull[i]) {
                  output.isNull[i] = false;
                  output.setVal(i, this.vector[i], this.start[i], this.length[i]);
               } else {
                  output.isNull[i] = true;
                  output.noNulls = false;
               }
            }
         }

      }
   }

   public void flatten(boolean selectedInUse, int[] sel, int size) {
      this.flattenPush();
      if (this.isRepeating) {
         this.isRepeating = false;
         if (this.noNulls || !this.isNull[0]) {
            if (selectedInUse) {
               for(int j = 0; j < size; ++j) {
                  int i = sel[j];
                  this.setRef(i, this.vector[0], this.start[0], this.length[0]);
               }
            } else {
               for(int i = 0; i < size; ++i) {
                  this.setRef(i, this.vector[0], this.start[0], this.length[0]);
               }
            }
         }

         this.flattenRepeatingNulls(selectedInUse, sel, size);
      }

      this.flattenNoNulls(selectedInUse, sel, size);
   }

   public void fill(byte[] value) {
      this.isRepeating = true;
      this.isNull[0] = false;
      this.setVal(0, value, 0, value.length);
   }

   public void fillWithNulls() {
      this.noNulls = false;
      this.isRepeating = true;
      this.vector[0] = null;
      this.isNull[0] = true;
   }

   public void setElement(int outputElementNum, int inputElementNum, ColumnVector inputColVector) {
      if (this.isRepeating && outputElementNum != 0) {
         throw new AssertionError("Output column number expected to be 0 when isRepeating");
      } else {
         if (inputColVector.isRepeating) {
            inputElementNum = 0;
         }

         if (this.noNulls || !this.isNull[outputElementNum]) {
            if (!inputColVector.noNulls && inputColVector.isNull[inputElementNum]) {
               this.isNull[outputElementNum] = true;
               this.noNulls = false;
            } else {
               BytesColumnVector in = (BytesColumnVector)inputColVector;
               this.setVal(outputElementNum, in.vector[inputElementNum], in.start[inputElementNum], in.length[inputElementNum]);
            }

         }
      }
   }

   public void init() {
      this.initBuffer(0);
   }

   public String toString(int row) {
      if (this.isRepeating) {
         row = 0;
      }

      return !this.noNulls && this.isNull[row] ? null : new String(this.vector[row], this.start[row], this.length[row], StandardCharsets.UTF_8);
   }

   public void stringifyValue(StringBuilder buffer, int row) {
      if (this.isRepeating) {
         row = 0;
      }

      if (!this.noNulls && this.isNull[row]) {
         buffer.append("null");
      } else {
         buffer.append('"');
         buffer.append(new String(this.vector[row], this.start[row], this.length[row], StandardCharsets.UTF_8));
         buffer.append('"');
      }

   }

   public void ensureSize(int size, boolean preserveData) {
      super.ensureSize(size, preserveData);
      if (size > this.vector.length) {
         int[] oldStart = this.start;
         this.start = new int[size];
         int[] oldLength = this.length;
         this.length = new int[size];
         byte[][] oldVector = this.vector;
         this.vector = new byte[size][];
         if (preserveData) {
            if (this.isRepeating) {
               this.vector[0] = oldVector[0];
               this.start[0] = oldStart[0];
               this.length[0] = oldLength[0];
            } else {
               System.arraycopy(oldVector, 0, this.vector, 0, oldVector.length);
               System.arraycopy(oldStart, 0, this.start, 0, oldStart.length);
               System.arraycopy(oldLength, 0, this.length, 0, oldLength.length);
            }
         }
      }

   }

   public void shallowCopyTo(ColumnVector otherCv) {
      BytesColumnVector other = (BytesColumnVector)otherCv;
      super.shallowCopyTo(other);
      other.currentOffset = this.currentOffset;
      other.vector = this.vector;
      other.start = this.start;
      other.length = this.length;
      other.currentValue = this.currentValue;
      other.sharedBuffer = this.sharedBuffer;
      other.sharedBufferOffset = this.sharedBufferOffset;
   }
}
