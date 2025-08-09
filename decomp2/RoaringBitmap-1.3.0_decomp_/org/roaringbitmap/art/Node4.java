package org.roaringbitmap.art;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.roaringbitmap.longlong.IntegerUtil;
import org.roaringbitmap.longlong.LongUtils;

public class Node4 extends Node {
   int key = 0;
   Node[] children = new Node[4];

   public Node4(int compressedPrefixSize) {
      super(NodeType.NODE4, compressedPrefixSize);
   }

   public int getChildPos(byte k) {
      for(int i = 0; i < this.count; ++i) {
         int shiftLeftLen = (3 - i) * 8;
         byte v = (byte)(this.key >> shiftLeftLen);
         if (v == k) {
            return i;
         }
      }

      return -1;
   }

   public SearchResult getNearestChildPos(byte k) {
      byte[] firstBytes = IntegerUtil.toBDBytes(this.key);
      return Node.binarySearchWithResult(firstBytes, 0, this.count, k);
   }

   public byte getChildKey(int pos) {
      int shiftLeftLen = (3 - pos) * 8;
      byte v = (byte)(this.key >> shiftLeftLen);
      return v;
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

   public static Node insert(Node node, Node childNode, byte key) {
      Node4 current = (Node4)node;
      if (current.count < 4) {
         current.key = IntegerUtil.setByte(current.key, key, current.count);
         current.children[current.count] = childNode;
         ++current.count;
         insertionSort(current);
         return current;
      } else {
         Node16 node16 = new Node16(current.prefixLength);
         node16.count = 4;
         node16.firstV = LongUtils.initWithFirst4Byte(current.key);
         System.arraycopy(current.children, 0, node16.children, 0, 4);
         copyPrefix(current, node16);
         Node freshOne = Node16.insert(node16, childNode, key);
         return freshOne;
      }
   }

   public Node remove(int pos) {
      assert pos < this.count;

      this.children[pos] = null;
      --this.count;

      for(this.key = IntegerUtil.shiftLeftFromSpecifiedPosition(this.key, pos, 4 - pos - 1); pos < this.count; ++pos) {
         this.children[pos] = this.children[pos + 1];
      }

      if (this.count == 1) {
         Node child = this.children[0];
         byte newLength = (byte)(child.prefixLength + this.prefixLength + 1);
         byte[] newPrefix = new byte[newLength];
         System.arraycopy(this.prefix, 0, newPrefix, 0, this.prefixLength);
         newPrefix[this.prefixLength] = IntegerUtil.firstByte(this.key);
         System.arraycopy(child.prefix, 0, newPrefix, this.prefixLength + 1, child.prefixLength);
         child.prefixLength = newLength;
         child.prefix = newPrefix;
         return child;
      } else {
         return this;
      }
   }

   public void serializeNodeBody(DataOutput dataOutput) throws IOException {
      dataOutput.writeInt(Integer.reverseBytes(this.key));
   }

   public void serializeNodeBody(ByteBuffer byteBuffer) throws IOException {
      byteBuffer.putInt(this.key);
   }

   public void deserializeNodeBody(DataInput dataInput) throws IOException {
      int v = dataInput.readInt();
      this.key = Integer.reverseBytes(v);
   }

   public void deserializeNodeBody(ByteBuffer byteBuffer) throws IOException {
      this.key = byteBuffer.getInt();
   }

   public int serializeNodeBodySizeInBytes() {
      return 4;
   }

   public void replaceChildren(Node[] children) {
      System.arraycopy(children, 0, this.children, 0, this.count);
   }

   private static void insertionSort(Node4 node4) {
      byte[] key = IntegerUtil.toBDBytes(node4.key);
      byte[] sortedKey = Node.sortSmallByteArray(key, node4.children, 0, node4.count - 1);
      node4.key = IntegerUtil.fromBDBytes(sortedKey);
   }
}
