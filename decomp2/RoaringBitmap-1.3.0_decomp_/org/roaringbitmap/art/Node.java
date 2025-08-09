package org.roaringbitmap.art;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;

public abstract class Node {
   protected NodeType nodeType;
   protected byte prefixLength;
   protected byte[] prefix;
   protected short count;
   public static final int ILLEGAL_IDX = -1;

   public Node(NodeType nodeType, int compressedPrefixSize) {
      this.nodeType = nodeType;
      this.prefixLength = (byte)compressedPrefixSize;
      this.prefix = new byte[this.prefixLength];
      this.count = 0;
   }

   protected static byte[] sortSmallByteArray(byte[] key, Node[] children, int left, int right) {
      int i = left;

      for(int j = left; i < right; j = i) {
         byte ai = key[i + 1];
         Node child = children[i + 1];
         int unsignedByteAi = Byte.toUnsignedInt(ai);

         while(unsignedByteAi < Byte.toUnsignedInt(key[j])) {
            key[j + 1] = key[j];
            children[j + 1] = children[j];
            if (j-- == left) {
               break;
            }
         }

         key[j + 1] = ai;
         children[j + 1] = child;
         ++i;
      }

      return key;
   }

   public abstract int getChildPos(byte var1);

   public abstract SearchResult getNearestChildPos(byte var1);

   public abstract byte getChildKey(int var1);

   public abstract Node getChild(int var1);

   public abstract void replaceNode(int var1, Node var2);

   public abstract int getMinPos();

   public abstract int getNextLargerPos(int var1);

   public abstract int getMaxPos();

   public abstract int getNextSmallerPos(int var1);

   public abstract Node remove(int var1);

   public void serialize(DataOutput dataOutput) throws IOException {
      this.serializeHeader(dataOutput);
      this.serializeNodeBody(dataOutput);
   }

   public void serialize(ByteBuffer byteBuffer) throws IOException {
      this.serializeHeader(byteBuffer);
      this.serializeNodeBody(byteBuffer);
   }

   public int serializeSizeInBytes() {
      int size = 0;
      size += this.serializeHeaderSizeInBytes();
      size += this.serializeNodeBodySizeInBytes();
      return size;
   }

   public static Node deserialize(DataInput dataInput) throws IOException {
      Node node = deserializeHeader(dataInput);
      if (node != null) {
         node.deserializeNodeBody(dataInput);
         return node;
      } else {
         return null;
      }
   }

   public static Node deserialize(ByteBuffer byteBuffer) throws IOException {
      Node node = deserializeHeader(byteBuffer);
      if (node != null) {
         node.deserializeNodeBody(byteBuffer);
         return node;
      } else {
         return null;
      }
   }

   abstract void replaceChildren(Node[] var1);

   abstract void serializeNodeBody(DataOutput var1) throws IOException;

   abstract void serializeNodeBody(ByteBuffer var1) throws IOException;

   abstract void deserializeNodeBody(DataInput var1) throws IOException;

   abstract void deserializeNodeBody(ByteBuffer var1) throws IOException;

   public abstract int serializeNodeBodySizeInBytes();

   public static Node insertLeaf(Node current, LeafNode childNode, byte key) {
      switch (current.nodeType) {
         case NODE4:
            return Node4.insert(current, childNode, key);
         case NODE16:
            return Node16.insert(current, childNode, key);
         case NODE48:
            return Node48.insert(current, childNode, key);
         case NODE256:
            return Node256.insert(current, childNode, key);
         default:
            throw new IllegalArgumentException("Not supported node type!");
      }
   }

   public static void copyPrefix(Node src, Node dst) {
      dst.prefixLength = src.prefixLength;
      System.arraycopy(src.prefix, 0, dst.prefix, 0, src.prefixLength);
   }

   public static int binarySearch(byte[] key, int fromIndex, int toIndex, byte k) {
      int inputUnsignedByte = Byte.toUnsignedInt(k);
      int low = fromIndex;
      int high = toIndex - 1;

      while(low <= high) {
         int mid = low + high >>> 1;
         int midVal = Byte.toUnsignedInt(key[mid]);
         if (midVal < inputUnsignedByte) {
            low = mid + 1;
         } else {
            if (midVal <= inputUnsignedByte) {
               return mid;
            }

            high = mid - 1;
         }
      }

      return -1;
   }

   static SearchResult binarySearchWithResult(byte[] key, int fromIndex, int toIndex, byte k) {
      int inputUnsignedByte = Byte.toUnsignedInt(k);
      int low = fromIndex;
      int high = toIndex - 1;

      while(low != high) {
         int mid = low + high + 1 >>> 1;
         int midVal = Byte.toUnsignedInt(key[mid]);
         if (midVal > inputUnsignedByte) {
            high = mid - 1;
         } else {
            low = mid;
         }
      }

      int val = Byte.toUnsignedInt(key[low]);
      if (val == inputUnsignedByte) {
         return SearchResult.found(low);
      } else if (val < inputUnsignedByte) {
         int highIndex = low + 1;
         return SearchResult.notFound(low, highIndex < toIndex ? highIndex : -1);
      } else {
         return SearchResult.notFound(low - 1, low);
      }
   }

   private void serializeHeader(DataOutput dataOutput) throws IOException {
      dataOutput.writeByte((byte)this.nodeType.ordinal());
      dataOutput.writeShort(Short.reverseBytes(this.count));
      dataOutput.writeByte(this.prefixLength);
      if (this.prefixLength > 0) {
         dataOutput.write(this.prefix, 0, this.prefixLength);
      }

   }

   private void serializeHeader(ByteBuffer byteBuffer) throws IOException {
      byteBuffer.put((byte)this.nodeType.ordinal());
      byteBuffer.putShort(this.count);
      byteBuffer.put(this.prefixLength);
      if (this.prefixLength > 0) {
         byteBuffer.put(this.prefix, 0, this.prefixLength);
      }

   }

   private int serializeHeaderSizeInBytes() {
      int size = 4;
      if (this.prefixLength > 0) {
         size += this.prefixLength;
      }

      return size;
   }

   private static Node deserializeHeader(DataInput dataInput) throws IOException {
      int nodeTypeOrdinal = dataInput.readByte();
      short count = Short.reverseBytes(dataInput.readShort());
      byte prefixLength = dataInput.readByte();
      byte[] prefix = new byte[0];
      if (prefixLength > 0) {
         prefix = new byte[prefixLength];
         dataInput.readFully(prefix);
      }

      if (nodeTypeOrdinal == NodeType.NODE4.ordinal()) {
         Node4 node4 = new Node4(prefixLength);
         node4.prefixLength = prefixLength;
         node4.prefix = prefix;
         node4.count = count;
         return node4;
      } else if (nodeTypeOrdinal == NodeType.NODE16.ordinal()) {
         Node16 node16 = new Node16(prefixLength);
         node16.prefixLength = prefixLength;
         node16.prefix = prefix;
         node16.count = count;
         return node16;
      } else if (nodeTypeOrdinal == NodeType.NODE48.ordinal()) {
         Node48 node48 = new Node48(prefixLength);
         node48.prefixLength = prefixLength;
         node48.prefix = prefix;
         node48.count = count;
         return node48;
      } else if (nodeTypeOrdinal == NodeType.NODE256.ordinal()) {
         Node256 node256 = new Node256(prefixLength);
         node256.prefixLength = prefixLength;
         node256.prefix = prefix;
         node256.count = count;
         return node256;
      } else if (nodeTypeOrdinal == NodeType.LEAF_NODE.ordinal()) {
         LeafNode leafNode = new LeafNode(0L, 0L);
         leafNode.prefixLength = prefixLength;
         leafNode.prefix = prefix;
         leafNode.count = count;
         return leafNode;
      } else {
         return null;
      }
   }

   private static Node deserializeHeader(ByteBuffer byteBuffer) throws IOException {
      int nodeTypeOrdinal = byteBuffer.get();
      short count = byteBuffer.getShort();
      byte prefixLength = byteBuffer.get();
      byte[] prefix = new byte[0];
      if (prefixLength > 0) {
         prefix = new byte[prefixLength];
         byteBuffer.get(prefix);
      }

      if (nodeTypeOrdinal == NodeType.NODE4.ordinal()) {
         Node4 node4 = new Node4(prefixLength);
         node4.prefixLength = prefixLength;
         node4.prefix = prefix;
         node4.count = count;
         return node4;
      } else if (nodeTypeOrdinal == NodeType.NODE16.ordinal()) {
         Node16 node16 = new Node16(prefixLength);
         node16.prefixLength = prefixLength;
         node16.prefix = prefix;
         node16.count = count;
         return node16;
      } else if (nodeTypeOrdinal == NodeType.NODE48.ordinal()) {
         Node48 node48 = new Node48(prefixLength);
         node48.prefixLength = prefixLength;
         node48.prefix = prefix;
         node48.count = count;
         return node48;
      } else if (nodeTypeOrdinal == NodeType.NODE256.ordinal()) {
         Node256 node256 = new Node256(prefixLength);
         node256.prefixLength = prefixLength;
         node256.prefix = prefix;
         node256.count = count;
         return node256;
      } else if (nodeTypeOrdinal == NodeType.LEAF_NODE.ordinal()) {
         LeafNode leafNode = new LeafNode(0L, 0L);
         leafNode.prefixLength = prefixLength;
         leafNode.prefix = prefix;
         leafNode.count = count;
         return leafNode;
      } else {
         return null;
      }
   }
}
