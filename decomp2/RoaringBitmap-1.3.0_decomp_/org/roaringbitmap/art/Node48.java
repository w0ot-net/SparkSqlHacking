package org.roaringbitmap.art;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.LongBuffer;
import java.util.Arrays;

public class Node48 extends Node {
   static final int BYTES_PER_LONG = 8;
   static final int LONGS_USED = 32;
   static final int INDEX_SHIFT = 3;
   static final int POS_MASK = 7;
   long[] childIndex = new long[32];
   Node[] children = new Node[48];
   static final byte EMPTY_VALUE = -1;
   static final long INIT_LONG_VALUE = -1L;

   public Node48(int compressedPrefixSize) {
      super(NodeType.NODE48, compressedPrefixSize);
      Arrays.fill(this.childIndex, -1L);
   }

   public int getChildPos(byte k) {
      int unsignedIdx = Byte.toUnsignedInt(k);
      int childIdx = childrenIdx(unsignedIdx, this.childIndex);
      return childIdx != -1 ? unsignedIdx : -1;
   }

   public SearchResult getNearestChildPos(byte k) {
      int unsignedIdx = Byte.toUnsignedInt(k);
      int childIdx = childrenIdx(unsignedIdx, this.childIndex);
      return childIdx != -1 ? SearchResult.found(unsignedIdx) : SearchResult.notFound(this.getNextSmallerPos(unsignedIdx), this.getNextLargerPos(unsignedIdx));
   }

   public byte getChildKey(int pos) {
      return (byte)pos;
   }

   public Node getChild(int pos) {
      byte idx = childrenIdx(pos, this.childIndex);
      return this.children[idx];
   }

   public void replaceNode(int pos, Node freshOne) {
      byte idx = childrenIdx(pos, this.childIndex);
      this.children[idx] = freshOne;
   }

   public int getMinPos() {
      int pos = 0;

      for(int i = 0; i < 32; ++i) {
         long longv = this.childIndex[i];
         if (longv == -1L) {
            pos += 8;
         } else {
            for(int j = 0; j < 8; ++j) {
               byte v = (byte)((int)(longv >>> (7 - j << 3)));
               if (v != -1) {
                  return pos;
               }

               ++pos;
            }
         }
      }

      return -1;
   }

   public int getNextLargerPos(int pos) {
      if (pos == -1) {
         pos = -1;
      }

      ++pos;

      for(int i = pos >>> 3; i < 32; ++i) {
         long longv = this.childIndex[i];
         if (longv == -1L) {
            pos = pos + 8 & 248;
         } else {
            for(int j = pos & 7; j < 8; ++j) {
               int shiftNum = 7 - j << 3;
               byte v = (byte)((int)(longv >>> shiftNum));
               if (v != -1) {
                  return pos;
               }

               ++pos;
            }
         }
      }

      return -1;
   }

   public int getMaxPos() {
      int pos = 255;

      for(int i = 31; i >= 0; --i) {
         long longv = this.childIndex[i];
         if (longv == -1L) {
            pos -= 8;
         } else {
            for(int j = 0; j < 8; ++j) {
               byte v = (byte)((int)(longv >>> (j << 3)));
               if (v != -1) {
                  return pos;
               }

               --pos;
            }
         }
      }

      return -1;
   }

   public int getNextSmallerPos(int pos) {
      if (pos == -1) {
         pos = 256;
      }

      --pos;

      for(int i = pos >>> 3; i >= 0 && i < 32; --i) {
         long longv = this.childIndex[i];
         if (longv == -1L) {
            pos -= Math.min(8, (pos & 7) + 1);
         } else {
            for(int j = pos & 7; j >= 0; --j) {
               int shiftNum = 7 - j << 3;
               byte v = (byte)((int)(longv >>> shiftNum));
               if (v != -1) {
                  return pos;
               }

               --pos;
            }
         }
      }

      return -1;
   }

   public static Node insert(Node currentNode, Node child, byte key) {
      Node48 node48 = (Node48)currentNode;
      if (node48.count < 48) {
         int pos = node48.count;
         if (node48.children[pos] != null) {
            for(pos = 0; node48.children[pos] != null; ++pos) {
            }
         }

         node48.children[pos] = child;
         int unsignedByte = Byte.toUnsignedInt(key);
         setOneByte(unsignedByte, (byte)pos, node48.childIndex);
         ++node48.count;
         return node48;
      } else {
         Node256 node256 = new Node256(node48.prefixLength);
         int currentPos = -1;

         while((currentPos = node48.getNextLargerPos(currentPos)) != -1) {
            Node childNode = node48.getChild(currentPos);
            node256.children[currentPos] = childNode;
            Node256.setBit((byte)currentPos, node256.bitmapMask);
         }

         node256.count = node48.count;
         copyPrefix(node48, node256);
         Node freshOne = Node256.insert(node256, child, key);
         return freshOne;
      }
   }

   public Node remove(int pos) {
      byte idx = childrenIdx(pos, this.childIndex);
      setOneByte(pos, (byte)-1, this.childIndex);
      this.children[idx] = null;
      --this.count;
      if (this.count > 12) {
         return this;
      } else {
         Node16 node16 = new Node16(this.prefixLength);
         int j = 0;
         ByteBuffer byteBuffer = ByteBuffer.allocate(16).order(ByteOrder.BIG_ENDIAN);

         for(int currentPos = -1; (currentPos = this.getNextLargerPos(currentPos)) != -1; ++j) {
            Node child = this.getChild(currentPos);
            byteBuffer.put(j, (byte)currentPos);
            node16.children[j] = child;
         }

         node16.firstV = byteBuffer.getLong(0);
         node16.secondV = byteBuffer.getLong(8);
         node16.count = (short)j;
         copyPrefix(this, node16);
         return node16;
      }
   }

   public void serializeNodeBody(DataOutput dataOutput) throws IOException {
      for(int i = 0; i < 32; ++i) {
         long longv = this.childIndex[i];
         dataOutput.writeLong(Long.reverseBytes(longv));
      }

   }

   public void serializeNodeBody(ByteBuffer byteBuffer) throws IOException {
      LongBuffer longBuffer = byteBuffer.asLongBuffer();
      longBuffer.put(this.childIndex);
      byteBuffer.position(byteBuffer.position() + 256);
   }

   public void deserializeNodeBody(DataInput dataInput) throws IOException {
      for(int i = 0; i < 32; ++i) {
         this.childIndex[i] = Long.reverseBytes(dataInput.readLong());
      }

   }

   public void deserializeNodeBody(ByteBuffer byteBuffer) throws IOException {
      LongBuffer longBuffer = byteBuffer.asLongBuffer();
      longBuffer.get(this.childIndex);
      byteBuffer.position(byteBuffer.position() + 256);
   }

   public int serializeNodeBodySizeInBytes() {
      return 256;
   }

   void replaceChildren(Node[] children) {
      int step = 0;

      for(int i = 0; i < 32; ++i) {
         long longv = Long.reverseBytes(this.childIndex[i]);
         if (longv != -1L) {
            for(int j = 0; j < 8; ++j) {
               long currentByte = longv & 255L;
               if (currentByte != 255L) {
                  this.children[(int)currentByte] = children[step];
                  ++step;
               }

               longv >>>= 8;
            }
         }
      }

   }

   private static byte childrenIdx(int pos, long[] childIndex) {
      int longPos = pos >>> 3;
      int bytePos = pos & 7;
      long longV = childIndex[longPos];
      byte idx = (byte)((int)(longV >>> (7 - bytePos << 3)));
      return idx;
   }

   static void setOneByte(int pos, byte v, long[] childIndex) {
      int longPos = pos >>> 3;
      int bytePos = pos & 7;
      int shift = 7 - bytePos << 3;
      long preVal = childIndex[longPos];
      long newVal = preVal & ~(255L << shift) | Byte.toUnsignedLong(v) << shift;
      childIndex[longPos] = newVal;
   }
}
