package org.apache.arrow.vector;

import org.apache.arrow.memory.ArrowBuf;
import org.apache.arrow.memory.BoundsChecking;
import org.apache.arrow.memory.BufferAllocator;
import org.apache.arrow.memory.util.LargeMemoryUtil;
import org.apache.arrow.memory.util.MemoryUtil;
import org.apache.arrow.vector.ipc.message.ArrowFieldNode;
import org.apache.arrow.vector.util.DataSizeRoundingUtil;

public class BitVectorHelper {
   private BitVectorHelper() {
   }

   public static long byteIndex(long absoluteBitIndex) {
      return absoluteBitIndex >> 3;
   }

   public static int bitIndex(long absoluteBitIndex) {
      return LargeMemoryUtil.checkedCastToInt(absoluteBitIndex & 7L);
   }

   public static int byteIndex(int absoluteBitIndex) {
      return absoluteBitIndex >> 3;
   }

   public static int bitIndex(int absoluteBitIndex) {
      return absoluteBitIndex & 7;
   }

   public static void setBit(ArrowBuf validityBuffer, long index) {
      long byteIndex = byteIndex(index);
      int bitIndex = bitIndex(index);
      int currentByte = validityBuffer.getByte(byteIndex);
      int bitMask = 1 << bitIndex;
      currentByte |= bitMask;
      validityBuffer.setByte(byteIndex, currentByte);
   }

   public static void unsetBit(ArrowBuf validityBuffer, int index) {
      int byteIndex = byteIndex(index);
      int bitIndex = bitIndex(index);
      int currentByte = validityBuffer.getByte((long)byteIndex);
      int bitMask = 1 << bitIndex;
      currentByte &= ~bitMask;
      validityBuffer.setByte((long)byteIndex, currentByte);
   }

   public static void setValidityBit(ArrowBuf validityBuffer, int index, int value) {
      int byteIndex = byteIndex(index);
      int bitIndex = bitIndex(index);
      int currentByte = validityBuffer.getByte((long)byteIndex);
      int bitMask = 1 << bitIndex;
      if (value != 0) {
         currentByte |= bitMask;
      } else {
         currentByte &= ~bitMask;
      }

      validityBuffer.setByte((long)byteIndex, currentByte);
   }

   public static ArrowBuf setValidityBit(ArrowBuf validityBuffer, BufferAllocator allocator, int valueCount, int index, int value) {
      if (validityBuffer == null) {
         validityBuffer = allocator.buffer((long)getValidityBufferSize(valueCount));
      }

      setValidityBit(validityBuffer, index, value);
      if (index == valueCount - 1) {
         validityBuffer.writerIndex((long)getValidityBufferSize(valueCount));
      }

      return validityBuffer;
   }

   public static int get(ArrowBuf buffer, int index) {
      int byteIndex = index >> 3;
      byte b = buffer.getByte((long)byteIndex);
      int bitIndex = index & 7;
      return b >> bitIndex & 1;
   }

   public static int getValidityBufferSize(int valueCount) {
      return DataSizeRoundingUtil.divideBy8Ceil(valueCount);
   }

   public static int getNullCount(ArrowBuf validityBuffer, int valueCount) {
      if (valueCount == 0) {
         return 0;
      } else {
         int count = 0;
         int sizeInBytes = getValidityBufferSize(valueCount);
         int remainder = valueCount % 8;
         int fullBytesCount = remainder == 0 ? sizeInBytes : sizeInBytes - 1;

         int index;
         for(index = 0; index + 8 <= fullBytesCount; index += 8) {
            long longValue = validityBuffer.getLong((long)index);
            count += Long.bitCount(longValue);
         }

         if (index + 4 <= fullBytesCount) {
            int intValue = validityBuffer.getInt((long)index);
            count += Integer.bitCount(intValue);
            index += 4;
         }

         while(index < fullBytesCount) {
            byte byteValue = validityBuffer.getByte((long)index);
            count += Integer.bitCount(byteValue & 255);
            ++index;
         }

         if (remainder != 0) {
            byte byteValue = validityBuffer.getByte((long)(sizeInBytes - 1));
            byte mask = (byte)(255 << remainder);
            byteValue = (byte)(byteValue | mask);
            count += Integer.bitCount(byteValue & 255);
         }

         return 8 * sizeInBytes - count;
      }
   }

   public static boolean checkAllBitsEqualTo(ArrowBuf validityBuffer, int valueCount, boolean checkOneBits) {
      if (valueCount == 0) {
         return true;
      } else {
         int sizeInBytes = getValidityBufferSize(valueCount);
         validityBuffer.checkBytes(0L, (long)sizeInBytes);
         int remainder = valueCount % 8;
         int fullBytesCount = remainder == 0 ? sizeInBytes : sizeInBytes - 1;
         int intToCompare = checkOneBits ? -1 : 0;

         int index;
         for(index = 0; index + 8 <= fullBytesCount; index += 8) {
            long longValue = MemoryUtil.getLong(validityBuffer.memoryAddress() + (long)index);
            if (longValue != (long)intToCompare) {
               return false;
            }
         }

         if (index + 4 <= fullBytesCount) {
            int intValue = MemoryUtil.getInt(validityBuffer.memoryAddress() + (long)index);
            if (intValue != intToCompare) {
               return false;
            }

            index += 4;
         }

         while(index < fullBytesCount) {
            byte byteValue = MemoryUtil.getByte(validityBuffer.memoryAddress() + (long)index);
            if (byteValue != (byte)intToCompare) {
               return false;
            }

            ++index;
         }

         if (remainder != 0) {
            byte byteValue = MemoryUtil.getByte(validityBuffer.memoryAddress() + (long)sizeInBytes - 1L);
            byte mask = (byte)((1 << remainder) - 1);
            byteValue = (byte)(byteValue & mask);
            if (checkOneBits) {
               if ((mask & byteValue) != mask) {
                  return false;
               }
            } else if (byteValue != 0) {
               return false;
            }
         }

         return true;
      }
   }

   public static byte getBitsFromCurrentByte(ArrowBuf data, int index, int offset) {
      return (byte)((data.getByte((long)index) & 255) >>> offset);
   }

   public static byte getBitsFromNextByte(ArrowBuf data, int index, int offset) {
      return (byte)(data.getByte((long)index) << 8 - offset);
   }

   public static ArrowBuf loadValidityBuffer(ArrowFieldNode fieldNode, ArrowBuf sourceValidityBuffer, BufferAllocator allocator) {
      int valueCount = fieldNode.getLength();
      ArrowBuf newBuffer = null;
      boolean isValidityBufferNull = sourceValidityBuffer == null || sourceValidityBuffer.capacity() == 0L;
      if (isValidityBufferNull && (fieldNode.getNullCount() == 0 || fieldNode.getNullCount() == valueCount)) {
         newBuffer = allocator.buffer((long)getValidityBufferSize(valueCount));
         newBuffer.setZero(0L, newBuffer.capacity());
         if (fieldNode.getNullCount() != 0) {
            return newBuffer;
         }

         int fullBytesCount = valueCount / 8;
         newBuffer.setOne(0, fullBytesCount);
         int remainder = valueCount % 8;
         if (remainder > 0) {
            byte bitMask = (byte)((int)(255L >>> (8 - remainder & 7)));
            newBuffer.setByte((long)fullBytesCount, bitMask);
         }
      } else {
         newBuffer = sourceValidityBuffer.getReferenceManager().retain(sourceValidityBuffer, allocator);
      }

      return newBuffer;
   }

   static void setBitMaskedByte(ArrowBuf data, int byteIndex, byte bitMask) {
      byte currentByte = data.getByte((long)byteIndex);
      currentByte = (byte)(currentByte | bitMask);
      data.setByte((long)byteIndex, currentByte);
   }

   public static void concatBits(ArrowBuf input1, int numBits1, ArrowBuf input2, int numBits2, ArrowBuf output) {
      int numBytes1 = DataSizeRoundingUtil.divideBy8Ceil(numBits1);
      int numBytes2 = DataSizeRoundingUtil.divideBy8Ceil(numBits2);
      int numBytesOut = DataSizeRoundingUtil.divideBy8Ceil(numBits1 + numBits2);
      if (BoundsChecking.BOUNDS_CHECKING_ENABLED) {
         output.checkBytes(0L, (long)numBytesOut);
      }

      if (input1 != output) {
         MemoryUtil.copyMemory(input1.memoryAddress(), output.memoryAddress(), (long)numBytes1);
      }

      if (bitIndex(numBits1) == 0) {
         MemoryUtil.copyMemory(input2.memoryAddress(), output.memoryAddress() + (long)numBytes1, (long)numBytes2);
      } else {
         int numBitsToFill = 8 - bitIndex(numBits1);
         int mask = (1 << 8 - numBitsToFill) - 1;
         int numFullBytes = numBits2 / 8;
         int prevByte = output.getByte((long)(numBytes1 - 1)) & mask;

         for(int i = 0; i < numFullBytes; ++i) {
            int curByte = input2.getByte((long)i) & 255;
            int byteToFill = curByte << 8 - numBitsToFill & 255;
            output.setByte((long)(numBytes1 + i - 1), byteToFill | prevByte);
            prevByte = curByte >>> numBitsToFill;
         }

         int numTrailingBits = bitIndex(numBits2);
         if (numTrailingBits == 0) {
            output.setByte((long)(numBytes1 + numFullBytes - 1), prevByte);
         } else {
            int remByte = input2.getByte((long)(numBytes2 - 1)) & 255;
            int byteToFill = remByte << 8 - numBitsToFill;
            int var17 = prevByte | byteToFill;
            output.setByte((long)(numBytes1 + numFullBytes - 1), var17);
            if (numTrailingBits > numBitsToFill) {
               output.setByte((long)(numBytes1 + numFullBytes), 0);
               int leftByte = remByte >>> numBitsToFill;
               output.setByte((long)(numBytes1 + numFullBytes), leftByte);
            }

         }
      }
   }
}
