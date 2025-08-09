package org.roaringbitmap.art;

import org.roaringbitmap.longlong.LongUtils;

public abstract class AbstractShuttle implements Shuttle {
   protected static final int MAX_DEPTH = 7;
   protected NodeEntry[] stack = new NodeEntry[7];
   protected int depth = -1;
   protected boolean hasRun = false;
   protected Art art;
   protected Containers containers;

   public AbstractShuttle(Art art, Containers containers) {
      this.art = art;
      this.containers = containers;
   }

   public void initShuttle() {
      this.visitToLeaf(this.art.getRoot(), false);
   }

   public void initShuttleFrom(long key) {
      this.depth = -1;
      byte[] high = LongUtils.highPart(key);
      long highAsLong = LongUtils.rightShiftHighPart(key);
      this.visitToLeafFrom(high, 0, this.art.getRoot());
      if (this.currentBeforeHigh(this.getCurrentLeafNode().getKey(), highAsLong)) {
         this.hasRun = true;
         this.moveToNextLeaf();
      }

      this.hasRun = false;
   }

   protected abstract boolean currentBeforeHigh(long var1, long var3);

   public boolean moveToNextLeaf() {
      if (this.depth < 0) {
         return false;
      } else if (!this.hasRun) {
         this.hasRun = true;
         Node node = this.stack[this.depth].node;
         return node.nodeType == NodeType.LEAF_NODE;
      } else {
         Node node = this.stack[this.depth].node;
         if (node.nodeType == NodeType.LEAF_NODE) {
            --this.depth;
         }

         while(this.depth >= 0) {
            NodeEntry currentNodeEntry = this.stack[this.depth];
            if (currentNodeEntry.node.nodeType == NodeType.LEAF_NODE) {
               if (this.depth - 1 >= 0) {
                  this.findNextSiblingKeyOfLeafNode();
               }

               return true;
            }

            int nextPos;
            if (!currentNodeEntry.visited) {
               int pos = this.boundaryNodePosition(currentNodeEntry.node, false);
               currentNodeEntry.position = pos;
               nextPos = pos;
               currentNodeEntry.visited = true;
            } else if (currentNodeEntry.startFromNextSiblingPosition) {
               nextPos = currentNodeEntry.position;
               currentNodeEntry.startFromNextSiblingPosition = false;
            } else {
               int pos = currentNodeEntry.position;
               nextPos = this.visitedNodeNextPosition(currentNodeEntry.node, pos);
            }

            if (nextPos != -1) {
               this.stack[this.depth].position = nextPos;
               ++this.depth;
               NodeEntry freshEntry = new NodeEntry();
               freshEntry.node = currentNodeEntry.node.getChild(nextPos);
               this.stack[this.depth] = freshEntry;
            } else {
               --this.depth;
            }
         }

         return false;
      }
   }

   protected abstract int visitedNodeNextPosition(Node var1, int var2);

   public LeafNode getCurrentLeafNode() {
      NodeEntry currentNode = this.stack[this.depth];
      return (LeafNode)currentNode.node;
   }

   public void remove() {
      byte[] currentLeafKey = this.getCurrentLeafNode().getKeyBytes();
      Art.Toolkit toolkit = this.art.removeSpecifyKey(this.art.getRoot(), currentLeafKey, 0);
      if (toolkit != null) {
         if (this.containers != null) {
            this.containers.remove(toolkit.matchedContainerId);
         }

         Node node = toolkit.freshMatchedParentNode;
         if (this.depth - 1 >= 0) {
            NodeEntry oldEntry = this.stack[this.depth - 1];
            oldEntry.visited = oldEntry.node == node;
            oldEntry.node = node;
            oldEntry.startFromNextSiblingPosition = true;
            if (node.nodeType != NodeType.LEAF_NODE) {
               oldEntry.position = node.getChildPos(oldEntry.leafNodeNextSiblingKey);
            }
         }

      }
   }

   private void visitToLeaf(Node node, boolean inRunDirection) {
      if (node != null) {
         if (node == this.art.getRoot()) {
            NodeEntry nodeEntry = new NodeEntry();
            nodeEntry.node = node;
            this.depth = 0;
            this.stack[this.depth] = nodeEntry;
         }

         if (node.nodeType == NodeType.LEAF_NODE) {
            if (this.depth - 1 >= 0) {
               this.findNextSiblingKeyOfLeafNode();
            }

         } else if (this.depth != 7) {
            int pos = this.boundaryNodePosition(node, inRunDirection);
            this.stack[this.depth].position = pos;
            this.stack[this.depth].visited = true;
            Node child = node.getChild(pos);
            NodeEntry childNodeEntry = new NodeEntry();
            childNodeEntry.node = child;
            ++this.depth;
            this.stack[this.depth] = childNodeEntry;
            this.visitToLeaf(child, inRunDirection);
         }
      }
   }

   private void visitToLeafFrom(byte[] high, int keyDepth, Node node) {
      if (node != null) {
         if (node == this.art.getRoot()) {
            NodeEntry nodeEntry = new NodeEntry();
            nodeEntry.node = node;
            this.depth = 0;
            this.stack[this.depth] = nodeEntry;
         }

         if (node.nodeType == NodeType.LEAF_NODE) {
            if (this.depth - 1 >= 0) {
               this.findNextSiblingKeyOfLeafNode();
            }

         } else if (this.depth != 7) {
            if (node.prefixLength > 0) {
               int commonLength = Art.commonPrefixLength(high, keyDepth, high.length, node.prefix, 0, node.prefixLength);
               if (commonLength != node.prefixLength) {
                  byte nodeValue = node.prefix[commonLength];
                  byte highValue = high[keyDepth + commonLength];
                  boolean visitDirection = this.prefixMismatchIsInRunDirection(nodeValue, highValue);
                  this.visitToLeaf(node, visitDirection);
                  return;
               }

               keyDepth += node.prefixLength;
            }

            SearchResult result = node.getNearestChildPos(high[keyDepth]);
            boolean continueAtBoundary = false;
            boolean continueInRunDirection = false;
            int pos;
            switch (result.outcome) {
               case FOUND:
                  pos = result.getKeyPos();
                  break;
               case NOT_FOUND:
                  pos = this.searchMissNextPosition(result);
                  continueAtBoundary = true;
                  if (pos == -1) {
                     pos = this.boundaryNodePosition(node, true);
                     continueInRunDirection = true;
                  }
                  break;
               default:
                  throw new IllegalStateException("There only two possible search outcomes");
            }

            this.stack[this.depth].position = pos;
            this.stack[this.depth].visited = true;
            Node child = node.getChild(pos);
            NodeEntry childNodeEntry = new NodeEntry();
            childNodeEntry.node = child;
            ++this.depth;
            this.stack[this.depth] = childNodeEntry;
            if (continueAtBoundary) {
               this.visitToLeaf(child, continueInRunDirection);
            } else {
               this.visitToLeafFrom(high, keyDepth + 1, child);
            }

         }
      }
   }

   protected abstract int boundaryNodePosition(Node var1, boolean var2);

   protected abstract boolean prefixMismatchIsInRunDirection(byte var1, byte var2);

   protected abstract int searchMissNextPosition(SearchResult var1);

   private void findNextSiblingKeyOfLeafNode() {
      Node parentNode = this.stack[this.depth - 1].node;
      int nextSiblingPos = this.visitedNodeNextPosition(parentNode, this.stack[this.depth - 1].position);
      if (nextSiblingPos != -1) {
         byte nextSiblingKey = parentNode.getChildKey(nextSiblingPos);
         this.stack[this.depth - 1].leafNodeNextSiblingKey = nextSiblingKey;
      }

   }

   class NodeEntry {
      Node node = null;
      int position = -1;
      boolean visited = false;
      boolean startFromNextSiblingPosition = false;
      byte leafNodeNextSiblingKey;
   }
}
