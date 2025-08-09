package org.roaringbitmap.art;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.roaringbitmap.ArraysShim;

public class Art {
   private Node root = null;
   private long keySize = 0L;
   private static byte[] EMPTY_BYTES = new byte[0];

   public boolean isEmpty() {
      return this.root == null;
   }

   public void insert(byte[] key, long containerIdx) {
      Node freshRoot = this.insert(this.root, key, 0, containerIdx);
      if (freshRoot != this.root) {
         this.root = freshRoot;
      }

      ++this.keySize;
   }

   public long findByKey(byte[] key) {
      Node node = this.findByKey(this.root, key, 0);
      if (node != null) {
         LeafNode leafNode = (LeafNode)node;
         return leafNode.containerIdx;
      } else {
         return -1L;
      }
   }

   private Node findByKey(Node node, byte[] key, int depth) {
      while(node != null) {
         if (node.nodeType == NodeType.LEAF_NODE) {
            LeafNode leafNode = (LeafNode)node;
            byte[] leafNodeKeyBytes = leafNode.getKeyBytes();
            if (depth == 6) {
               return leafNode;
            }

            int mismatchIndex = ArraysShim.mismatch(leafNodeKeyBytes, depth, 6, key, depth, 6);
            if (mismatchIndex != -1) {
               return null;
            }

            return leafNode;
         }

         if (node.prefixLength > 0) {
            int commonLength = commonPrefixLength(key, depth, key.length, node.prefix, 0, node.prefixLength);
            if (commonLength != node.prefixLength) {
               return null;
            }

            depth += node.prefixLength;
         }

         int pos = node.getChildPos(key[depth]);
         if (pos == -1) {
            return null;
         }

         node = node.getChild(pos);
         ++depth;
      }

      return null;
   }

   public KeyIterator iterator(Containers containers) {
      return new KeyIterator(this, containers);
   }

   public long remove(byte[] key) {
      Toolkit toolkit = this.removeSpecifyKey(this.root, key, 0);
      return toolkit != null ? toolkit.matchedContainerId : -1L;
   }

   protected Toolkit removeSpecifyKey(Node node, byte[] key, int dep) {
      if (node == null) {
         return null;
      } else if (node.nodeType == NodeType.LEAF_NODE) {
         LeafNode leafNode = (LeafNode)node;
         if (this.leafMatch(leafNode, key, dep)) {
            if (node == this.root) {
               this.root = null;
            }

            --this.keySize;
            return new Toolkit((Node)null, leafNode.getContainerIdx(), (Node)null);
         } else {
            return null;
         }
      } else {
         if (node.prefixLength > 0) {
            int commonLength = commonPrefixLength(key, dep, key.length, node.prefix, 0, node.prefixLength);
            if (commonLength != node.prefixLength) {
               return null;
            }

            dep += node.prefixLength;
         }

         int pos = node.getChildPos(key[dep]);
         if (pos != -1) {
            Node child = node.getChild(pos);
            if (child.nodeType == NodeType.LEAF_NODE && this.leafMatch((LeafNode)child, key, dep)) {
               Node freshNode = node.remove(pos);
               --this.keySize;
               if (node == this.root && freshNode != node) {
                  this.root = freshNode;
               }

               long matchedContainerIdx = ((LeafNode)child).getContainerIdx();
               Toolkit toolkit = new Toolkit(freshNode, matchedContainerIdx, node);
               toolkit.needToVerifyReplacing = true;
               return toolkit;
            }

            Toolkit toolkit = this.removeSpecifyKey(child, key, dep + 1);
            if (toolkit != null && toolkit.needToVerifyReplacing && toolkit.freshMatchedParentNode != null && toolkit.freshMatchedParentNode != toolkit.originalMatchedParentNode) {
               node.replaceNode(pos, toolkit.freshMatchedParentNode);
               toolkit.needToVerifyReplacing = false;
               return toolkit;
            }

            if (toolkit != null) {
               return toolkit;
            }
         }

         return null;
      }
   }

   private boolean leafMatch(LeafNode leafNode, byte[] key, int dep) {
      byte[] leafNodeKeyBytes = leafNode.getKeyBytes();
      int mismatchIndex = ArraysShim.mismatch(leafNodeKeyBytes, dep, 6, key, dep, 6);
      return mismatchIndex == -1;
   }

   private Node insert(Node node, byte[] key, int depth, long containerIdx) {
      if (node == null) {
         LeafNode leafNode = new LeafNode(key, containerIdx);
         return leafNode;
      } else if (node.nodeType == NodeType.LEAF_NODE) {
         LeafNode leafNode = (LeafNode)node;
         byte[] prefix = leafNode.getKeyBytes();
         int commonPrefix = commonPrefixLength(prefix, depth, prefix.length, key, depth, key.length);
         leafNode.prefixLength = 0;
         leafNode.prefix = EMPTY_BYTES;
         Node4 node4 = new Node4(commonPrefix);
         node4.prefixLength = (byte)commonPrefix;
         System.arraycopy(key, depth, node4.prefix, 0, commonPrefix);
         Node4.insert(node4, leafNode, prefix[depth + commonPrefix]);
         LeafNode anotherLeaf = new LeafNode(key, containerIdx);
         Node4.insert(node4, anotherLeaf, key[depth + commonPrefix]);
         return node4;
      } else {
         if (node.prefixLength > 0) {
            int mismatchPos = ArraysShim.mismatch(node.prefix, 0, node.prefixLength, key, depth, key.length);
            if (mismatchPos != node.prefixLength) {
               Node4 node4 = new Node4(mismatchPos);
               node4.prefixLength = (byte)mismatchPos;
               System.arraycopy(node.prefix, 0, node4.prefix, 0, mismatchPos);
               Node4.insert(node4, node, node.prefix[mismatchPos]);
               int nodeOriginalPrefixLength = node.prefixLength;
               node.prefixLength = (byte)(nodeOriginalPrefixLength - (mismatchPos + 1));
               if (node.prefixLength > 0) {
                  System.arraycopy(node.prefix, mismatchPos + 1, node.prefix, 0, node.prefixLength);
               } else {
                  node.prefix = new byte[0];
               }

               LeafNode leafNode = new LeafNode(key, containerIdx);
               Node4.insert(node4, leafNode, key[mismatchPos + depth]);
               return node4;
            }

            depth += node.prefixLength;
         }

         int pos = node.getChildPos(key[depth]);
         if (pos != -1) {
            Node child = node.getChild(pos);
            Node freshOne = this.insert(child, key, depth + 1, containerIdx);
            if (freshOne != child) {
               node.replaceNode(pos, freshOne);
            }

            return node;
         } else {
            LeafNode leafNode = new LeafNode(key, containerIdx);
            Node freshOne = Node.insertLeaf(node, leafNode, key[depth]);
            return freshOne;
         }
      }
   }

   static int commonPrefixLength(byte[] key1, int aFromIndex, int aToIndex, byte[] key2, int bFromIndex, int bToIndex) {
      int aLength = aToIndex - aFromIndex;
      int bLength = bToIndex - bFromIndex;
      int minLength = Math.min(aLength, bLength);
      int mismatchIndex = ArraysShim.mismatch(key1, aFromIndex, aToIndex, key2, bFromIndex, bToIndex);
      return aLength != bLength && mismatchIndex >= minLength ? minLength : mismatchIndex;
   }

   public Node getRoot() {
      return this.root;
   }

   private LeafNode getExtremeLeaf(boolean reverse) {
      Node parent = this.getRoot();

      for(int depth = 0; depth < 7 && parent.nodeType != NodeType.LEAF_NODE; ++depth) {
         int childIndex = reverse ? parent.getMaxPos() : parent.getMinPos();
         parent = parent.getChild(childIndex);
      }

      return (LeafNode)parent;
   }

   public LeafNode first() {
      return this.getExtremeLeaf(false);
   }

   public LeafNode last() {
      return this.getExtremeLeaf(true);
   }

   public void serializeArt(DataOutput dataOutput) throws IOException {
      dataOutput.writeLong(Long.reverseBytes(this.keySize));
      this.serialize(this.root, dataOutput);
   }

   public void deserializeArt(DataInput dataInput) throws IOException {
      this.keySize = Long.reverseBytes(dataInput.readLong());
      this.root = this.deserialize(dataInput);
   }

   public void serializeArt(ByteBuffer byteBuffer) throws IOException {
      byteBuffer.putLong(this.keySize);
      this.serialize(this.root, byteBuffer);
   }

   public void deserializeArt(ByteBuffer byteBuffer) throws IOException {
      this.keySize = byteBuffer.getLong();
      this.root = this.deserialize(byteBuffer);
   }

   public LeafNodeIterator leafNodeIterator(boolean reverse, Containers containers) {
      return new LeafNodeIterator(this, reverse, containers);
   }

   public LeafNodeIterator leafNodeIteratorFrom(long bound, boolean reverse, Containers containers) {
      return new LeafNodeIterator(this, reverse, containers, bound);
   }

   private void serialize(Node node, DataOutput dataOutput) throws IOException {
      if (node.nodeType != NodeType.LEAF_NODE) {
         node.serialize(dataOutput);

         for(int nexPos = node.getNextLargerPos(-1); nexPos != -1; nexPos = node.getNextLargerPos(nexPos)) {
            Node child = node.getChild(nexPos);
            this.serialize(child, dataOutput);
         }
      } else {
         node.serialize(dataOutput);
      }

   }

   private void serialize(Node node, ByteBuffer byteBuffer) throws IOException {
      if (node.nodeType != NodeType.LEAF_NODE) {
         node.serialize(byteBuffer);

         for(int nexPos = node.getNextLargerPos(-1); nexPos != -1; nexPos = node.getNextLargerPos(nexPos)) {
            Node child = node.getChild(nexPos);
            this.serialize(child, byteBuffer);
         }
      } else {
         node.serialize(byteBuffer);
      }

   }

   private Node deserialize(DataInput dataInput) throws IOException {
      Node oneNode = Node.deserialize(dataInput);
      if (oneNode == null) {
         return null;
      } else if (oneNode.nodeType == NodeType.LEAF_NODE) {
         return oneNode;
      } else {
         int count = oneNode.count;
         Node[] children = new Node[count];

         for(int i = 0; i < count; ++i) {
            Node child = this.deserialize(dataInput);
            children[i] = child;
         }

         oneNode.replaceChildren(children);
         return oneNode;
      }
   }

   private Node deserialize(ByteBuffer byteBuffer) throws IOException {
      Node oneNode = Node.deserialize(byteBuffer);
      if (oneNode == null) {
         return null;
      } else if (oneNode.nodeType == NodeType.LEAF_NODE) {
         return oneNode;
      } else {
         int count = oneNode.count;
         Node[] children = new Node[count];

         for(int i = 0; i < count; ++i) {
            Node child = this.deserialize(byteBuffer);
            children[i] = child;
         }

         oneNode.replaceChildren(children);
         return oneNode;
      }
   }

   public long serializeSizeInBytes() {
      return this.serializeSizeInBytes(this.root) + 8L;
   }

   public long getKeySize() {
      return this.keySize;
   }

   private long serializeSizeInBytes(Node node) {
      if (node.nodeType == NodeType.LEAF_NODE) {
         int nodeSize = node.serializeSizeInBytes();
         return (long)nodeSize;
      } else {
         int currentNodeSize = node.serializeSizeInBytes();
         long childrenTotalSize = 0L;

         long childSize;
         for(int nexPos = node.getNextLargerPos(-1); nexPos != -1; childrenTotalSize += childSize) {
            Node child = node.getChild(nexPos);
            childSize = this.serializeSizeInBytes(child);
            nexPos = node.getNextLargerPos(nexPos);
         }

         return (long)currentNodeSize + childrenTotalSize;
      }
   }

   class Toolkit {
      Node freshMatchedParentNode;
      long matchedContainerId;
      Node originalMatchedParentNode;
      boolean needToVerifyReplacing = false;

      Toolkit(Node freshMatchedParentNode, long matchedContainerId, Node originalMatchedParentNode) {
         this.freshMatchedParentNode = freshMatchedParentNode;
         this.matchedContainerId = matchedContainerId;
         this.originalMatchedParentNode = originalMatchedParentNode;
      }
   }
}
