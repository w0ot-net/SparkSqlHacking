package org.roaringbitmap.art;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.LongBuffer;

public class Node256 extends Node {
   Node[] children = new Node[256];
   long[] bitmapMask = new long[4];
   private static final long LONG_MASK = -1L;

   public Node256(int compressedPrefixSize) {
      super(NodeType.NODE256, compressedPrefixSize);
   }

   public int getChildPos(byte k) {
      int pos = Byte.toUnsignedInt(k);
      return this.children[pos] != null ? pos : -1;
   }

   public SearchResult getNearestChildPos(byte k) {
      int pos = Byte.toUnsignedInt(k);
      return this.children[pos] != null ? SearchResult.found(pos) : SearchResult.notFound(this.getNextSmallerPos(pos), this.getNextLargerPos(pos));
   }

   public byte getChildKey(int pos) {
      return (byte)pos;
   }

   public Node getChild(int pos) {
      return this.children[pos];
   }

   public void replaceNode(int pos, Node freshOne) {
      this.children[pos] = freshOne;
   }

   public int getMinPos() {
      for(int i = 0; i < 4; ++i) {
         long longVal = this.bitmapMask[i];
         if (longVal != 0L) {
            int v = Long.numberOfTrailingZeros(longVal);
            return i * 64 + v;
         }
      }

      return -1;
   }

   public int getNextLargerPos(int pos) {
      if (pos == -1) {
         pos = 0;
      } else {
         ++pos;
      }

      int longPos = pos >> 6;
      if (longPos >= 4) {
         return -1;
      } else {
         long longVal;
         for(longVal = this.bitmapMask[longPos] & -1L << pos; longVal == 0L; longVal = this.bitmapMask[longPos]) {
            ++longPos;
            if (longPos == 4) {
               return -1;
            }
         }

         return longPos * 64 + Long.numberOfTrailingZeros(longVal);
      }
   }

   public int getMaxPos() {
      for(int i = 3; i >= 0; --i) {
         long longVal = this.bitmapMask[i];
         if (longVal != 0L) {
            int v = Long.numberOfLeadingZeros(longVal);
            return i * 64 + (63 - v);
         }
      }

      return -1;
   }

   public int getNextSmallerPos(int pos) {
      if (pos == -1) {
         pos = 256;
      }

      if (pos == 0) {
         return -1;
      } else {
         --pos;
         int longPos = pos >>> 6;

         long longVal;
         for(longVal = this.bitmapMask[longPos] & -1L >>> -(pos + 1); longVal == 0L; longVal = this.bitmapMask[longPos]) {
            if (longPos-- == 0) {
               return -1;
            }
         }

         return (longPos + 1) * 64 - 1 - Long.numberOfLeadingZeros(longVal);
      }
   }

   public static Node256 insert(Node currentNode, Node child, byte key) {
      Node256 node256 = (Node256)currentNode;
      ++node256.count;
      int i = Byte.toUnsignedInt(key);
      node256.children[i] = child;
      setBit(key, node256.bitmapMask);
      return node256;
   }

   static void setBit(byte key, long[] bitmapMask) {
      int i = Byte.toUnsignedInt(key);
      int longIdx = i >>> 6;
      long previous = bitmapMask[longIdx];
      long newVal = previous | 1L << i;
      bitmapMask[longIdx] = newVal;
   }

   public Node remove(int pos) {
      this.children[pos] = null;
      int longPos = pos >>> 6;
      long[] var10000 = this.bitmapMask;
      var10000[longPos] &= ~(1L << pos);
      --this.count;
      if (this.count > 36) {
         return this;
      } else {
         Node48 node48 = new Node48(this.prefixLength);
         int j = 0;

         for(int currentPos = -1; (currentPos = this.getNextLargerPos(currentPos)) != -1; ++j) {
            Node child = this.getChild(currentPos);
            node48.children[j] = child;
            Node48.setOneByte(currentPos, (byte)j, node48.childIndex);
         }

         node48.count = (short)j;
         copyPrefix(this, node48);
         return node48;
      }
   }

   public void replaceChildren(Node[] children) {
      if (children.length == this.children.length) {
         this.children = children;
      } else {
         int offset = 0;
         int x = 0;

         for(long longv : this.bitmapMask) {
            int w;
            for(w = 0; longv != 0L; ++w) {
               int pos = x * 64 + Long.numberOfTrailingZeros(longv);
               this.children[pos] = children[offset + w];
               longv &= longv - 1L;
            }

            offset += w;
            ++x;
         }

      }
   }

   public void serializeNodeBody(DataOutput dataOutput) throws IOException {
      for(long longv : this.bitmapMask) {
         dataOutput.writeLong(Long.reverseBytes(longv));
      }

   }

   public void serializeNodeBody(ByteBuffer byteBuffer) throws IOException {
      LongBuffer longBuffer = byteBuffer.asLongBuffer();
      longBuffer.put(this.bitmapMask);
      byteBuffer.position(byteBuffer.position() + 32);
   }

   public void deserializeNodeBody(DataInput dataInput) throws IOException {
      for(int i = 0; i < 4; ++i) {
         long longv = Long.reverseBytes(dataInput.readLong());
         this.bitmapMask[i] = longv;
      }

   }

   public void deserializeNodeBody(ByteBuffer byteBuffer) throws IOException {
      LongBuffer longBuffer = byteBuffer.asLongBuffer();
      longBuffer.get(this.bitmapMask);
      byteBuffer.position(byteBuffer.position() + 32);
   }

   public int serializeNodeBodySizeInBytes() {
      return 32;
   }
}
