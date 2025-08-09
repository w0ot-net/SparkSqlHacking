package org.roaringbitmap.art;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.roaringbitmap.ArrayContainer;
import org.roaringbitmap.BitmapContainer;
import org.roaringbitmap.Container;
import org.roaringbitmap.RunContainer;

public class Containers {
   private List containerArrays = new ArrayList(0);
   private long containerSize = 0L;
   private int firstLevelIdx = -1;
   private int secondLevelIdx = 0;
   private static final int MAX_JVM_ARRAY_LENGTH = 2147483639;
   private static final int MAX_JVM_ARRAY_OFFSET = 2147483638;
   private static final byte NULL_MARK = 0;
   private static final byte NOT_NULL_MARK = 1;
   private static final byte TRIMMED_MARK = -1;
   private static final byte NOT_TRIMMED_MARK = -2;

   public Containers() {
      this.reset();
   }

   private void reset() {
      this.containerSize = 0L;
      this.firstLevelIdx = -1;
      this.secondLevelIdx = 0;
   }

   public void remove(long containerIdx) {
      int firstDimIdx = (int)(containerIdx >>> 32);
      int secondDimIdx = (int)containerIdx;
      ((Container[])this.containerArrays.get(firstDimIdx))[secondDimIdx] = null;
      --this.containerSize;
   }

   public Container getContainer(long idx) {
      int firstDimIdx = (int)(idx >>> 32);
      int secondDimIdx = (int)idx;
      Container[] containers = (Container[])this.containerArrays.get(firstDimIdx);
      return containers[secondDimIdx];
   }

   public long addContainer(Container container) {
      if (this.secondLevelIdx + 1 != 2147483638 && this.firstLevelIdx != -1) {
         ++this.secondLevelIdx;
      } else {
         this.containerArrays.add(new Container[1]);
         ++this.firstLevelIdx;
         this.secondLevelIdx = 0;
      }

      int firstDimIdx = this.firstLevelIdx;
      int secondDimIdx = this.secondLevelIdx;
      this.grow(secondDimIdx + 1, this.firstLevelIdx);
      ((Container[])this.containerArrays.get(firstDimIdx))[secondDimIdx] = container;
      ++this.containerSize;
      return toContainerIdx(this.firstLevelIdx, this.secondLevelIdx);
   }

   public ContainerIterator iterator() {
      return new ContainerIterator(this);
   }

   public void replace(long containerIdx, Container freshContainer) {
      int firstDimIdx = (int)(containerIdx >>> 32);
      int secondDimIdx = (int)containerIdx;
      ((Container[])this.containerArrays.get(firstDimIdx))[secondDimIdx] = freshContainer;
   }

   public void replace(int firstLevelIdx, int secondLevelIdx, Container freshContainer) {
      ((Container[])this.containerArrays.get(firstLevelIdx))[secondLevelIdx] = freshContainer;
   }

   public long getContainerSize() {
      return this.containerSize;
   }

   List getContainerArrays() {
      return this.containerArrays;
   }

   static long toContainerIdx(int firstLevelIdx, int secondLevelIdx) {
      long firstLevelIdxL = (long)firstLevelIdx;
      return firstLevelIdxL << 32 | (long)secondLevelIdx;
   }

   private void grow(int minCapacity, int firstLevelIdx) {
      Container[] elementData = (Container[])this.containerArrays.get(firstLevelIdx);
      int oldCapacity = elementData.length;
      if (minCapacity - oldCapacity > 0) {
         int newCapacity = oldCapacity + (oldCapacity >> 1);
         if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
         }

         if (newCapacity - 2147483639 > 0) {
            newCapacity = hugeCapacity(minCapacity);
         }

         Container[] freshElementData = (Container[])Arrays.copyOf(elementData, newCapacity);
         this.containerArrays.set(firstLevelIdx, freshElementData);
      }
   }

   private static int hugeCapacity(int minCapacity) {
      if (minCapacity < 0) {
         throw new OutOfMemoryError();
      } else {
         return minCapacity > 2147483639 ? Integer.MAX_VALUE : 2147483639;
      }
   }

   public long serializedSizeInBytes() {
      long totalSize = 0L;
      totalSize += 4L;
      int firstLevelSize = this.containerArrays.size();

      for(int i = 0; i < firstLevelSize; ++i) {
         Container[] containers = (Container[])this.containerArrays.get(i);
         totalSize += 5L;

         for(int j = 0; j < containers.length; ++j) {
            Container container = containers[j];
            if (container != null) {
               totalSize += 2L;
               totalSize += 4L;
               totalSize += (long)container.getArraySizeInBytes();
            } else {
               ++totalSize;
            }
         }
      }

      totalSize += 16L;
      return totalSize;
   }

   public void serialize(DataOutput dataOutput) throws IOException {
      int firstLevelSize = this.containerArrays.size();
      dataOutput.writeInt(Integer.reverseBytes(firstLevelSize));

      for(int i = 0; i < firstLevelSize; ++i) {
         Container[] containers = (Container[])this.containerArrays.get(i);
         int secondLevelSize = containers.length;
         dataOutput.writeByte(-2);
         dataOutput.writeInt(Integer.reverseBytes(secondLevelSize));

         for(int j = 0; j < containers.length; ++j) {
            Container container = containers[j];
            if (container != null) {
               dataOutput.writeByte(1);
               byte containerType = this.containerType(container);
               dataOutput.writeByte(containerType);
               dataOutput.writeInt(Integer.reverseBytes(container.getCardinality()));
               container.writeArray(dataOutput);
            } else {
               dataOutput.writeByte(0);
            }
         }
      }

      dataOutput.writeLong(Long.reverseBytes(this.containerSize));
      dataOutput.writeInt(Integer.reverseBytes(this.firstLevelIdx));
      dataOutput.writeInt(Integer.reverseBytes(this.secondLevelIdx));
   }

   public void serialize(ByteBuffer byteBuffer) throws IOException {
      int firstLevelSize = this.containerArrays.size();
      byteBuffer.putInt(firstLevelSize);

      for(int i = 0; i < firstLevelSize; ++i) {
         Container[] containers = (Container[])this.containerArrays.get(i);
         int secondLevelSize = containers.length;
         byteBuffer.put((byte)-2);
         byteBuffer.putInt(secondLevelSize);

         for(int j = 0; j < containers.length; ++j) {
            Container container = containers[j];
            if (container != null) {
               byteBuffer.put((byte)1);
               byte containerType = this.containerType(container);
               byteBuffer.put(containerType);
               byteBuffer.putInt(container.getCardinality());
               container.writeArray(byteBuffer);
            } else {
               byteBuffer.put((byte)0);
            }
         }
      }

      byteBuffer.putLong(this.containerSize);
      byteBuffer.putInt(this.firstLevelIdx);
      byteBuffer.putInt(this.secondLevelIdx);
   }

   public void deserialize(DataInput dataInput) throws IOException {
      int firstLevelSize = Integer.reverseBytes(dataInput.readInt());
      ArrayList<Container[]> containersArray = new ArrayList(firstLevelSize);

      for(int i = 0; i < firstLevelSize; ++i) {
         byte trimTag = dataInput.readByte();
         int secondLevelSize = Integer.reverseBytes(dataInput.readInt());
         Container[] containers = new Container[secondLevelSize];

         for(int j = 0; j < secondLevelSize; ++j) {
            byte nullTag = dataInput.readByte();
            if (nullTag == 0) {
               containers[j] = null;
            } else {
               if (nullTag != 1) {
                  throw new RuntimeException("the null tag byte value:" + nullTag + " is not right!");
               }

               byte containerType = dataInput.readByte();
               int cardinality = Integer.reverseBytes(dataInput.readInt());
               Container container = this.instanceContainer(containerType, cardinality, dataInput);
               containers[j] = container;
            }
         }

         containersArray.add(containers);
      }

      this.containerArrays = containersArray;
      this.containerSize = Long.reverseBytes(dataInput.readLong());
      this.firstLevelIdx = Integer.reverseBytes(dataInput.readInt());
      this.secondLevelIdx = Integer.reverseBytes(dataInput.readInt());
   }

   public void deserialize(ByteBuffer byteBuffer) throws IOException {
      int firstLevelSize = byteBuffer.getInt();
      ArrayList<Container[]> containersArray = new ArrayList(firstLevelSize);

      for(int i = 0; i < firstLevelSize; ++i) {
         byte trimTag = byteBuffer.get();
         int secondLevelSize = byteBuffer.getInt();
         Container[] containers = new Container[secondLevelSize];

         for(int j = 0; j < secondLevelSize; ++j) {
            byte nullTag = byteBuffer.get();
            if (nullTag == 0) {
               containers[j] = null;
            } else {
               if (nullTag != 1) {
                  throw new RuntimeException("the null tag byte value:" + nullTag + " is not right!");
               }

               byte containerType = byteBuffer.get();
               int cardinality = byteBuffer.getInt();
               Container container = this.instanceContainer(containerType, cardinality, byteBuffer);
               containers[j] = container;
            }
         }

         containersArray.add(containers);
      }

      this.containerArrays = containersArray;
      this.containerSize = byteBuffer.getLong();
      this.firstLevelIdx = byteBuffer.getInt();
      this.secondLevelIdx = byteBuffer.getInt();
   }

   private byte containerType(Container container) {
      if (container instanceof RunContainer) {
         return 0;
      } else if (container instanceof BitmapContainer) {
         return 1;
      } else if (container instanceof ArrayContainer) {
         return 2;
      } else {
         throw new UnsupportedOperationException("Not supported container type");
      }
   }

   private Container instanceContainer(byte containerType, int cardinality, DataInput dataInput) throws IOException {
      if (containerType == 0) {
         int nbrruns = Character.reverseBytes(dataInput.readChar());
         char[] lengthsAndValues = new char[2 * nbrruns];

         for(int j = 0; j < 2 * nbrruns; ++j) {
            lengthsAndValues[j] = Character.reverseBytes(dataInput.readChar());
         }

         return new RunContainer(lengthsAndValues, nbrruns);
      } else if (containerType == 1) {
         long[] bitmapArray = new long[1024];

         for(int l = 0; l < bitmapArray.length; ++l) {
            bitmapArray[l] = Long.reverseBytes(dataInput.readLong());
         }

         return new BitmapContainer(bitmapArray, cardinality);
      } else if (containerType != 2) {
         throw new UnsupportedOperationException("Not supported container type:" + containerType);
      } else {
         char[] charArray = new char[cardinality];

         for(int l = 0; l < charArray.length; ++l) {
            charArray[l] = Character.reverseBytes(dataInput.readChar());
         }

         return new ArrayContainer(charArray);
      }
   }

   private Container instanceContainer(byte containerType, int cardinality, ByteBuffer byteBuffer) throws IOException {
      if (containerType == 0) {
         int nbrruns = byteBuffer.getChar();
         char[] lengthsAndValues = new char[2 * nbrruns];
         byteBuffer.asCharBuffer().get(lengthsAndValues);
         byteBuffer.position(byteBuffer.position() + lengthsAndValues.length * 2);
         return new RunContainer(lengthsAndValues, nbrruns);
      } else if (containerType == 1) {
         long[] bitmapArray = new long[1024];
         byteBuffer.asLongBuffer().get(bitmapArray);
         byteBuffer.position(byteBuffer.position() + bitmapArray.length * 8);
         return new BitmapContainer(bitmapArray, cardinality);
      } else if (containerType == 2) {
         char[] charArray = new char[cardinality];
         byteBuffer.asCharBuffer().get(charArray);
         byteBuffer.position(byteBuffer.position() + charArray.length * 2);
         return new ArrayContainer(charArray);
      } else {
         throw new UnsupportedOperationException("Not supported container type:" + containerType);
      }
   }
}
