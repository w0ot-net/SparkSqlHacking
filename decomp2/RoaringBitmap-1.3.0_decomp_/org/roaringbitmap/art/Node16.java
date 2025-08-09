package org.roaringbitmap.art;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.roaringbitmap.longlong.LongUtils;

public class Node16 extends Node {
   long firstV = 0L;
   long secondV = 0L;
   Node[] children = new Node[16];

   public Node16(int compressionLength) {
      super(NodeType.NODE16, compressionLength);
   }

   public int getChildPos(byte k) {
      byte[] firstBytes = LongUtils.toBDBytes(this.firstV);
      if (this.count <= 8) {
         return Node.binarySearch(firstBytes, 0, this.count, k);
      } else {
         int pos = Node.binarySearch(firstBytes, 0, 8, k);
         if (pos != -1) {
            return pos;
         } else {
            byte[] secondBytes = LongUtils.toBDBytes(this.secondV);
            pos = Node.binarySearch(secondBytes, 0, this.count - 8, k);
            return pos != -1 ? 8 + pos : -1;
         }
      }
   }

   public SearchResult getNearestChildPos(byte k) {
      byte[] firstBytes = LongUtils.toBDBytes(this.firstV);
      if (this.count <= 8) {
         return Node.binarySearchWithResult(firstBytes, 0, this.count, k);
      } else {
         SearchResult firstResult = Node.binarySearchWithResult(firstBytes, 0, 8, k);
         if (firstResult.outcome != SearchResult.Outcome.FOUND && !firstResult.hasNextLargerPos()) {
            byte[] secondBytes = LongUtils.toBDBytes(this.secondV);
            SearchResult secondResult = Node.binarySearchWithResult(secondBytes, 0, this.count - 8, k);
            switch (secondResult.outcome) {
               case FOUND:
                  return SearchResult.found(8 + secondResult.getKeyPos());
               case NOT_FOUND:
                  int lowPos = secondResult.getNextSmallerPos();
                  int highPos = secondResult.getNextLargerPos();
                  if (lowPos >= 0) {
                     lowPos += 8;
                  }

                  if (highPos >= 0) {
                     highPos += 8;
                  }

                  if (!firstResult.hasNextLargerPos() && !secondResult.hasNextSmallerPos()) {
                     lowPos = firstResult.getNextSmallerPos();
                  }

                  return SearchResult.notFound(lowPos, highPos);
               default:
                  throw new IllegalStateException("There only two possible search outcomes");
            }
         } else {
            return firstResult;
         }
      }
   }

   public byte getChildKey(int pos) {
      if (pos <= 7) {
         byte[] firstBytes = LongUtils.toBDBytes(this.firstV);
         return firstBytes[pos];
      } else {
         int posInLong = pos - 8;
         byte[] secondBytes = LongUtils.toBDBytes(this.secondV);
         return secondBytes[posInLong];
      }
   }

   public Node getChild(int pos) {
      return this.children[pos];
   }

   public void replaceNode(int pos, Node freshOne) {
      this.children[pos] = freshOne;
   }

   public int getMinPos() {
      return 0;
   }

   public int getNextLargerPos(int pos) {
      if (pos == -1) {
         return 0;
      } else {
         ++pos;
         return pos < this.count ? pos : -1;
      }
   }

   public int getMaxPos() {
      return this.count - 1;
   }

   public int getNextSmallerPos(int pos) {
      if (pos == -1) {
         return this.count - 1;
      } else {
         --pos;
         return pos >= 0 ? pos : -1;
      }
   }

   public static Node insert(Node node, Node child, byte key) {
      Node16 currentNode16 = (Node16)node;
      if (currentNode16.count < 8) {
         byte[] bytes = LongUtils.toBDBytes(currentNode16.firstV);
         bytes[currentNode16.count] = key;
         currentNode16.children[currentNode16.count] = child;
         sortSmallByteArray(bytes, currentNode16.children, 0, currentNode16.count);
         ++currentNode16.count;
         currentNode16.firstV = LongUtils.fromBDBytes(bytes);
         return currentNode16;
      } else if (currentNode16.count < 16) {
         ByteBuffer byteBuffer = ByteBuffer.allocate(16).order(ByteOrder.BIG_ENDIAN);
         byteBuffer.putLong(currentNode16.firstV);
         byteBuffer.putLong(currentNode16.secondV);
         byteBuffer.put(currentNode16.count, key);
         currentNode16.children[currentNode16.count] = child;
         sortSmallByteArray(byteBuffer.array(), currentNode16.children, 0, currentNode16.count);
         ++currentNode16.count;
         currentNode16.firstV = byteBuffer.getLong(0);
         currentNode16.secondV = byteBuffer.getLong(8);
         return currentNode16;
      } else {
         Node48 node48 = new Node48(currentNode16.prefixLength);

         for(int i = 0; i < 8; ++i) {
            int unsignedIdx = Byte.toUnsignedInt((byte)((int)(currentNode16.firstV >>> (7 - i << 3))));
            Node48.setOneByte(unsignedIdx, (byte)i, node48.childIndex);
            node48.children[i] = currentNode16.children[i];
         }

         byte[] secondBytes = LongUtils.toBDBytes(currentNode16.secondV);

         for(int i = 8; i < currentNode16.count; ++i) {
            byte v = secondBytes[i - 8];
            int unsignedIdx = Byte.toUnsignedInt(v);
            Node48.setOneByte(unsignedIdx, (byte)i, node48.childIndex);
            node48.children[i] = currentNode16.children[i];
         }

         copyPrefix(currentNode16, node48);
         node48.count = currentNode16.count;
         Node freshOne = Node48.insert(node48, child, key);
         return freshOne;
      }
   }

   public Node remove(int pos) {
      this.children[pos] = null;
      ByteBuffer byteBuffer = ByteBuffer.allocate(16).order(ByteOrder.BIG_ENDIAN);
      byte[] bytes = byteBuffer.putLong(this.firstV).putLong(this.secondV).array();
      System.arraycopy(bytes, pos + 1, bytes, pos, 16 - pos - 1);
      System.arraycopy(this.children, pos + 1, this.children, pos, 16 - pos - 1);
      this.firstV = byteBuffer.getLong(0);
      this.secondV = byteBuffer.getLong(8);
      --this.count;
      if (this.count <= 3) {
         Node4 node4 = new Node4(this.prefixLength);
         node4.key = (int)(this.firstV >> 32);
         System.arraycopy(this.children, 0, node4.children, 0, this.count);
         node4.count = this.count;
         copyPrefix(this, node4);
         return node4;
      } else {
         return this;
      }
   }

   public void serializeNodeBody(DataOutput dataOutput) throws IOException {
      dataOutput.writeLong(Long.reverseBytes(this.firstV));
      dataOutput.writeLong(Long.reverseBytes(this.secondV));
   }

   public void serializeNodeBody(ByteBuffer byteBuffer) throws IOException {
      byteBuffer.putLong(this.firstV);
      byteBuffer.putLong(this.secondV);
   }

   public void deserializeNodeBody(DataInput dataInput) throws IOException {
      this.firstV = Long.reverseBytes(dataInput.readLong());
      this.secondV = Long.reverseBytes(dataInput.readLong());
   }

   public void deserializeNodeBody(ByteBuffer byteBuffer) throws IOException {
      this.firstV = byteBuffer.getLong();
      this.secondV = byteBuffer.getLong();
   }

   public int serializeNodeBodySizeInBytes() {
      return 16;
   }

   public void replaceChildren(Node[] children) {
      System.arraycopy(children, 0, this.children, 0, this.count);
   }
}
