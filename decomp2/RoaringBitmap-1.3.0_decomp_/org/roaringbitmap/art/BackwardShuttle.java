package org.roaringbitmap.art;

public class BackwardShuttle extends AbstractShuttle {
   BackwardShuttle(Art art, Containers containers) {
      super(art, containers);
   }

   protected boolean currentBeforeHigh(long current, long high) {
      return current > high;
   }

   protected int visitedNodeNextPosition(Node node, int pos) {
      return node.getNextSmallerPos(pos);
   }

   protected int boundaryNodePosition(Node node, boolean inRunDirection) {
      return inRunDirection ? node.getMinPos() : node.getMaxPos();
   }

   protected boolean prefixMismatchIsInRunDirection(byte nodeValue, byte highValue) {
      return Byte.toUnsignedInt(nodeValue) > Byte.toUnsignedInt(highValue);
   }

   protected int searchMissNextPosition(SearchResult result) {
      return result.getNextSmallerPos();
   }
}
