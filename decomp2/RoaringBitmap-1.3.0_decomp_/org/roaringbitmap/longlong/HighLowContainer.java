package org.roaringbitmap.longlong;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.NoSuchElementException;
import org.roaringbitmap.Container;
import org.roaringbitmap.art.Art;
import org.roaringbitmap.art.ContainerIterator;
import org.roaringbitmap.art.Containers;
import org.roaringbitmap.art.KeyIterator;
import org.roaringbitmap.art.LeafNode;
import org.roaringbitmap.art.LeafNodeIterator;

public class HighLowContainer {
   private Art art = new Art();
   private Containers containers = new Containers();
   private static final byte EMPTY_TAG = 0;
   private static final byte NOT_EMPTY_TAG = 1;

   public Container getContainer(long containerIdx) {
      return this.containers.getContainer(containerIdx);
   }

   public ContainerWithIndex searchContainer(byte[] highPart) {
      long containerIdx = this.art.findByKey(highPart);
      if (containerIdx < 0L) {
         return null;
      } else {
         Container container = this.containers.getContainer(containerIdx);
         return new ContainerWithIndex(container, containerIdx);
      }
   }

   public void put(byte[] highPart, Container container) {
      long containerIdx = this.containers.addContainer(container);
      this.art.insert(highPart, containerIdx);
   }

   public void remove(byte[] highPart) {
      long containerIdx = this.art.remove(highPart);
      if (containerIdx != -1L) {
         this.containers.remove(containerIdx);
      }

   }

   public ContainerIterator containerIterator() {
      return this.containers.iterator();
   }

   public KeyIterator highKeyIterator() {
      return this.art.iterator(this.containers);
   }

   public LeafNodeIterator highKeyLeafNodeIterator(boolean reverse) {
      return this.art.leafNodeIterator(reverse, this.containers);
   }

   public LeafNodeIterator highKeyLeafNodeIteratorFrom(long bound, boolean reverse) {
      return this.art.leafNodeIteratorFrom(bound, reverse, this.containers);
   }

   public void replaceContainer(long containerIdx, Container container) {
      this.containers.replace(containerIdx, container);
   }

   public boolean isEmpty() {
      return this.art.isEmpty();
   }

   private void assertNonEmpty() {
      if (this.isEmpty()) {
         throw new NoSuchElementException("Empty " + this.getClass().getSimpleName());
      }
   }

   public long first() {
      this.assertNonEmpty();
      LeafNode firstNode = this.art.first();
      long containerIdx = firstNode.getContainerIdx();
      Container container = this.getContainer(containerIdx);
      byte[] high = firstNode.getKeyBytes();
      char low = (char)container.first();
      return LongUtils.toLong(high, low);
   }

   public long last() {
      this.assertNonEmpty();
      LeafNode lastNode = this.art.last();
      long containerIdx = lastNode.getContainerIdx();
      Container container = this.getContainer(containerIdx);
      byte[] high = lastNode.getKeyBytes();
      char low = (char)container.last();
      return LongUtils.toLong(high, low);
   }

   public static int compareUnsigned(byte[] a, byte[] b) {
      if (a == null) {
         return b == null ? 0 : 1;
      } else if (b == null) {
         return -1;
      } else {
         for(int i = 0; i < Math.min(a.length, b.length); ++i) {
            int aVal = a[i] & 255;
            int bVal = b[i] & 255;
            if (aVal != bVal) {
               return Integer.compare(aVal, bVal);
            }
         }

         return Integer.compare(a.length, b.length);
      }
   }

   public void serialize(ByteBuffer buffer) throws IOException {
      ByteBuffer byteBuffer = buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffer : buffer.slice().order(ByteOrder.LITTLE_ENDIAN);
      if (this.art.isEmpty()) {
         byteBuffer.put((byte)0);
      } else {
         byteBuffer.put((byte)1);
         this.art.serializeArt(byteBuffer);
         this.containers.serialize(byteBuffer);
         if (byteBuffer != buffer) {
            buffer.position(buffer.position() + byteBuffer.position());
         }

      }
   }

   public void deserialize(ByteBuffer buffer) throws IOException {
      ByteBuffer byteBuffer = buffer.order() == ByteOrder.LITTLE_ENDIAN ? buffer : buffer.slice().order(ByteOrder.LITTLE_ENDIAN);
      this.clear();
      byte emptyTag = byteBuffer.get();
      if (emptyTag != 0) {
         this.art.deserializeArt(byteBuffer);
         this.containers.deserialize(byteBuffer);
      }
   }

   public long serializedSizeInBytes() {
      long totalSize = 1L;
      if (this.art.isEmpty()) {
         return totalSize;
      } else {
         totalSize += this.art.serializeSizeInBytes();
         totalSize += this.containers.serializedSizeInBytes();
         return totalSize;
      }
   }

   public void serialize(DataOutput dataOutput) throws IOException {
      if (this.art.isEmpty()) {
         dataOutput.writeByte(0);
      } else {
         dataOutput.writeByte(1);
         this.art.serializeArt(dataOutput);
         this.containers.serialize(dataOutput);
      }
   }

   public void deserialize(DataInput dataInput) throws IOException {
      this.clear();
      byte emptyTag = dataInput.readByte();
      if (emptyTag != 0) {
         this.art.deserializeArt(dataInput);
         this.containers.deserialize(dataInput);
      }
   }

   public void clear() {
      this.art = new Art();
      this.containers = new Containers();
   }

   public int hashCode() {
      int hashCode = 0;

      int result;
      Container container;
      for(KeyIterator keyIterator = this.highKeyIterator(); keyIterator.hasNext(); hashCode = 31 * hashCode + result + container.hashCode()) {
         byte[] key = keyIterator.next();
         result = 1;

         for(byte element : key) {
            result = 31 * result + element;
         }

         long containerIdx = keyIterator.currentContainerIdx();
         container = this.containers.getContainer(containerIdx);
      }

      return hashCode;
   }

   public boolean equals(Object object) {
      if (object instanceof HighLowContainer) {
         HighLowContainer otherHighLowContainer = (HighLowContainer)object;
         if (this.art.getKeySize() != otherHighLowContainer.art.getKeySize()) {
            return false;
         } else {
            KeyIterator thisKeyIte = this.highKeyIterator();

            while(thisKeyIte.hasNext()) {
               byte[] thisHigh = thisKeyIte.next();
               long containerIdx = thisKeyIte.currentContainerIdx();
               Container thisContainer = this.getContainer(containerIdx);
               ContainerWithIndex containerWithIndex = otherHighLowContainer.searchContainer(thisHigh);
               if (containerWithIndex == null) {
                  return false;
               }

               Container otherContainer = containerWithIndex.getContainer();
               if (!thisContainer.equals(otherContainer)) {
                  return false;
               }
            }

            return true;
         }
      } else {
         return false;
      }
   }
}
